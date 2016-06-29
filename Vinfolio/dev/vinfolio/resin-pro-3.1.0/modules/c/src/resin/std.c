/*
 * Copyright (c) 1998-2004 Caucho Technology -- all rights reserved
 *
 * Caucho Technology permits modification and use of this file in
 * source and binary form ("the Software") subject to the Caucho
 * Developer Source License 1.1 ("the License") which accompanies
 * this file.  The License is also available at
 *   http://www.caucho.com/download/cdsl1-1.xtp
 *
 * In addition to the terms of the License, the following conditions
 * must be met:
 *
 * 1. Each copy or derived work of the Software must preserve the copyright
 *    notice and this notice unmodified.
 *
 * 2. Each copy of the Software in source or binary form must include 
 *    an unmodified copy of the License in a plain ASCII text file named
 *    LICENSE.
 *
 * 3. Caucho reserves all rights to its names, trademarks and logos.
 *    In particular, the names "Resin" and "Caucho" are trademarks of
 *    Caucho and may not be used to endorse products derived from
 *    this software.  "Resin" and "Caucho" may not appear in the names
 *    of products derived from this software.
 *
 * This Software is provided "AS IS," without a warranty of any kind. 
 * ALL EXPRESS OR IMPLIED REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED.
 *
 * CAUCHO TECHNOLOGY AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE OR ANY THIRD PARTY AS A RESULT OF USING OR
 * DISTRIBUTING SOFTWARE. IN NO EVENT WILL CAUCHO OR ITS LICENSORS BE LIABLE
 * FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL,
 * CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND
 * REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF OR
 * INABILITY TO USE SOFTWARE, EVEN IF HE HAS BEEN ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGES.      
 *
 * @author Scott Ferguson
 */

#include <sys/types.h>
#ifdef WIN32
#ifndef _WINSOCKAPI_ 
#define _WINSOCKAPI_
#endif 
#include <windows.h>
#include <winsock2.h>
#else
#include <sys/time.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <netdb.h>
#include <unistd.h>
#ifdef POLL
#include <sys/poll.h>
#else
#include <sys/select.h>
#endif
#endif
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <string.h>
/* probably system-dependent */
#include <jni.h>
#include <errno.h>
#include <signal.h>

#include "resin.h"

static int std_read(connection_t *conn, char *buf, int len, int timeout);
static int std_read_nonblock(connection_t *conn, char *buf, int len);
static int std_write(connection_t *conn, char *buf, int len);
static int std_write_nonblock(connection_t *conn, char *buf, int len);
int conn_close(connection_t *conn);
void std_free(connection_t *conn);
static int std_read_client_certificate(connection_t *conn, char *buf, int len);

struct connection_ops_t std_ops = {
  std_read,
  std_read_nonblock,
  std_write,
  std_write_nonblock,
  conn_close,
  std_read_client_certificate,
};

static int
write_exception_status(connection_t *conn, int error)
{
  if (error == EAGAIN || error == EWOULDBLOCK) {
    conn->ops->close(conn);
    
    return TIMEOUT_EXN;
  }
  else if (error == EPIPE || errno == ECONNRESET || errno == EINTR) {
    conn->ops->close(conn);
    return DISCONNECT_EXN;
  }
  else {
    conn->ops->close(conn);
    return -1;
  }
}

static int
read_exception_status(connection_t *conn, int error)
{
  if (error == EAGAIN || error == EWOULDBLOCK) {
    conn->ops->close(conn);
    
    return TIMEOUT_EXN;
  }
  else if (error == EPIPE || errno == ECONNRESET || errno == EINTR) {
    /* XXX: */
    conn->ops->close(conn);
    return -1;
  }
  else {
    conn->ops->close(conn);
    return -1;
  }
}

static int
std_read_nonblock(connection_t *conn, char *buf, int len)
{
  int fd;
  int result;

  if (! conn)
    return -1;
  
  fd = conn->fd;
  
  if (fd < 0)
    return -1;

  result = recv(fd, buf, len, 0);

  return result;
}

#ifdef POLL
int
poll_read(int fd, int ms)
{
  struct pollfd pollfd[1];
  int result;
  
  pollfd[0].fd = fd;
  pollfd[0].events = POLLIN|POLLPRI;
  pollfd[0].revents = 0;

  result = poll(pollfd, 1, ms);

  if (result > 0 && (pollfd[0].revents & (POLLIN|POLLPRI)) == 0)
    return 1;
  else
    return result;
}

static int
poll_write(int fd, int ms)
{
  struct pollfd pollfd[1];
  
  pollfd[0].fd = fd;
  pollfd[0].events = POLLOUT;
  pollfd[0].revents = 0;

  return poll(pollfd, 1, ms);
}
#else /* select */
int
poll_read(int fd, int ms)
{
  fd_set read_set;
  struct timeval timeout;
  int result;

  FD_ZERO(&read_set);
  FD_SET(fd, &read_set);

  timeout.tv_sec = ms / 1000;
  timeout.tv_usec = (ms % 1000) * 1000;

  result = select(fd + 1, &read_set, 0, 0, &timeout);

  return result;
}

static int
poll_write(int fd, int ms)
{
  fd_set write_set;
  struct timeval timeout;

  FD_ZERO(&write_set);
  FD_SET(fd, &write_set);

  timeout.tv_sec = ms / 1000;
  timeout.tv_usec = (ms % 1000) * 1000;

  return select(fd + 1, 0, &write_set, 0, &timeout);
}
#endif

static int
std_read(connection_t *conn, char *buf, int len, int timeout)
{
  int fd;
  int result;
  int retry = 10;

  if (! conn)
    return -1;
  
  fd = conn->fd;
  
  if (fd < 0)
    return -1;

  if (timeout > 0 && poll_read(fd, timeout) <= 0) {
    return 0;
  }
  
#ifdef WIN32
  if (timeout < 0 && poll_read(fd, conn->socket_timeout) <= 0)
    return -1;
#endif

  do {
    result = recv(fd, buf, len, 0);
  } while (result < 0 && (errno == EINTR) && retry-- >= 0);
    
  if (result >= 0)
    return result;
  else
    return read_exception_status(conn, errno);
}

static int
std_write(connection_t *conn, char *buf, int len)
{
  int fd;
  int result;
  int retry = 10;

  if (! conn)
    return -1;

  fd = conn->fd;

  if (fd < 0)
    return -1;

  conn->sent_data = 1;

#ifdef WIN32
  if (poll_write(fd, conn->socket_timeout) <= 0)
    return -1;

  retry = 0;
#endif

  do {
    result = send(fd, buf, len, 0);
  } while (result < 0 && (errno == EINTR) && retry-- >= 0);

  if (result >= 0)
    return result;
  else
    return write_exception_status(conn, errno);
}

static int
std_write_nonblock(connection_t *conn, char *buf, int len)
{
  int fd;
  int result;

  if (! conn)
    return -1;

  fd = conn->fd;

  if (fd < 0)
    return -1;

#ifndef O_NONBLOCK
  if (poll_write(fd, 0) <= 0)
    return 0;
#endif  

  result = send(fd, buf, len, 0);

  return result;
}

int
conn_close(connection_t *conn)
{
  int fd;

  if (! conn)
    return -1;

  fd = conn->fd;
  
  conn->fd = -1;

  if (fd >= 0) {
    closesocket(fd);
  }

  return 1;
}

int
std_accept(server_socket_t *ss, connection_t *conn)
{
  int fd;
  int sock = -1;
  char sin_data[256];
  struct sockaddr *sin = (struct sockaddr *) sin_data;
  unsigned int sin_len;
  int tcp_no_delay = 1;
  int poll_result;
  struct timeval timeout;
  int result;

  if (! ss || ! conn)
    return 0;
  
  fd = ss->fd;
  if (fd < 0)
    return 0;

  sin_len = sizeof(sin_data);
  memset(sin, 0, sin_len);

#ifdef WIN32
  WaitForSingleObject(ss->accept_lock, INFINITE);
  fd = ss->fd;
  if (fd < 0) {
    ReleaseMutex(ss->accept_lock);
    return 0;
  }
#endif
  /* pthread_mutex_lock(&ss->accept_lock); */
  
  sock = -1;

  /* XXX: no need to for the poll?  needs to match nonblock.
  poll_result = poll_read(fd, 5000);

  if (poll_result > 0)
    sock = accept(fd, sin, &sin_len);
  */
  sock = accept(fd, sin, &sin_len);
  
#ifdef WIN32
  ReleaseMutex(ss->accept_lock);
#endif
  /* pthread_mutex_unlock(&ss->accept_lock); */
  
  if (sock < 0)
    return 0;

  if (tcp_no_delay) {
    int flag = 1;

    setsockopt(sock, IPPROTO_TCP, TCP_NODELAY, (char *) &flag, sizeof(int));
  }

#ifndef WIN32  
  timeout.tv_sec = ss->conn_socket_timeout / 1000;
  timeout.tv_usec = ss->conn_socket_timeout % 1000 * 1000;
  setsockopt(sock, SOL_SOCKET, SO_RCVTIMEO,
	     (char *) &timeout, sizeof(timeout));

  timeout.tv_sec = ss->conn_socket_timeout / 1000;
  timeout.tv_usec = ss->conn_socket_timeout % 1000 * 1000;
  setsockopt(sock, SOL_SOCKET, SO_SNDTIMEO,
	     (char *) &timeout, sizeof(timeout));
#endif

  conn->ss = ss;
  conn->socket_timeout = ss->conn_socket_timeout;
  conn->ssl_lock = &ss->ssl_lock;

  conn->fd = sock;
  conn->sock = 0;
  conn->ops = &std_ops;
  memcpy(conn->client_sin, sin, sizeof(conn->client_data));
  conn->is_init = 0;
  sin_len = sizeof(conn->server_data);
  getsockname(sock, conn->server_sin, &sin_len);
  conn->ssl_cipher = 0;
  conn->ssl_bits = 0;

  return 1;
}

void
std_close_ss(server_socket_t *ss)
{
  int fd;
  connection_t *conn;
  connection_t *next;
  char server_data[128];
  struct sockaddr *server_sin = (struct sockaddr *) server_data;
  unsigned int sin_len;
  int result;

   if (! ss)
    return;
  
  fd = ss->fd;
  ss->fd = -1;

  if (fd < 0)
    return;

  sin_len = sizeof(server_data);
  
  if (! getsockname(fd, server_sin, &sin_len)) {
    int retry;

    /* probably should check for 0 socket name for local host*/

    /* connect enough times to clear the threads waiting for a connection */
    for (retry = 20; retry >= 0; retry--) {
      int sock = socket(AF_INET, SOCK_STREAM, 0);
      int result;

      if (sock < 0)
	break;

      result = connect(sock, server_sin, sin_len);

      closesocket(sock);

      if (result < 0)
	break;
    }
  }
  
  result = closesocket(fd);
}

static int
std_read_client_certificate(connection_t *conn, char *buffer, int length)
{
  return -1;
}
