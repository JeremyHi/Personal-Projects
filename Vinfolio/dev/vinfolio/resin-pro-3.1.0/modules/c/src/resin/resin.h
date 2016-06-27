/*
 * Copyright (c) 1999-2004 Caucho Technology.  All rights reserved.
 *
 * Caucho Technology permits redistribution, modification and use
 * of this file in source and binary form ("the Software") under the
 * Caucho Public License ("the License").  In particular, the following
 * conditions must be met:
 *
 * 1. Each copy or derived work of the Software must preserve the copyright
 *    notice and this notice unmodified.
 *
 * 2. Redistributions of the Software in source or binary form must include 
 *    an unmodified copy of the License, normally in a plain ASCII text
 *
 * 3. The names "Resin" or "Caucho" are trademarks of Caucho Technology and
 *    may not be used to endorse products derived from this software.
 *    "Resin" or "Caucho" may not appear in the names of products derived
 *    from this software.
 *
 * 4. Caucho Technology requests that attribution be given to Resin
 *    in any manner possible.  We suggest using the "Resin Powered"
 *    button or creating a "powered by Resin(tm)" link to
 *    http://www.caucho.com for each page served by Resin.
 *
 * This Software is provided "AS IS," without a warranty of any kind. 
 * ALL EXPRESS OR IMPLIED REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED.
 *
 * CAUCHO TECHNOLOGY AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE OR ANY THIRD PARTY AS A RESULT OF USING OR
 * DISTRIBUTING SOFTWARE. IN NO EVENT WILL Caucho OR ITS LICENSORS BE LIABLE
 * FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL,
 * CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND
 * REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF OR
 * INABILITY TO USE SOFTWARE, EVEN IF HE HAS BEEN ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGES.      
 */

#ifndef RESIN_H
#define RESIN_H

#undef RESIN_DIRECT_JNI_BUFFER

#ifdef B64
#define PTR jlong
#else
#define PTR jint
#endif

/* Threading */
#ifdef WIN32
#define pthread_t HANDLE
#define pthread_self() GetCurrentThread()
#define pthread_kill(thread,signal) ThreadInterrupt(thread)

#define pthread_mutex_t HANDLE
#define pthread_mutex_init(x,y)
#define pthread_mutex_lock(x) WaitForSingleObject(*(x), INFINITE);

#define pthread_mutex_unlock(x) ReleaseMutex(*(x))
#define ECONNRESET EPIPE
#else
#undef closesocket
#define closesocket(x) close(x)

#ifdef __SOLARIS__
#include <thread.h>
#include <synch.h>

#define pthread_mutex_t mutex_t
#define pthread_mutex_lock(x) mutex_lock(x)
#define pthread_mutex_unlock(x) mutex_unlock(x)

#define pthread_cond_t cond_t
#define pthread_cond_wait(cond,mutex) cond_wait(cond,mutex)
#define pthread_cond_signal(x) cond_signal(x)

#define pthread_t thread_t
#define pthread_create(thread_id, foo, start, arg) \
        thr_create(0, 0, start, arg, 0, thread_id)
#else
#include <pthread.h>
#endif
#endif

typedef struct connection_t connection_t;

typedef struct connection_ops_t {
  int (*read) (connection_t *conn, char *buf, int len, int timeout);
  int (*read_nonblock) (connection_t *conn, char *buf, int len);
  int (*write) (connection_t *conn, char *buf, int len);
  int (*write_nonblock) (connection_t *conn, char *buf, int len);
  int (*close) (connection_t *conn);
  int (*read_client_certificate) (connection_t *conn, char *buf, int len);
} connection_ops_t;

struct connection_t {
  struct server_socket_t *ss;
  
  int id;

  JNIEnv *jni_env;
  
  void *context;
  connection_ops_t *ops;

  int fd;
  int is_init;
  void *sock;

  pthread_mutex_t *ssl_lock;
  int socket_timeout;
  int sent_data;

  char server_data[128];
  struct sockaddr *server_sin;
  char client_data[128];
  struct sockaddr *client_sin;

  char *ssl_cipher;
  int ssl_bits;

#ifdef WIN32
  WSAEVENT event;
#endif
};

typedef struct server_socket_t server_socket_t;

typedef struct resin_t {
  int count;
  int (*get_server_socket)(struct resin_t *);
} resin_t;


typedef struct ssl_config_t {
  JNIEnv *jni_env;
  
  char *certificate_file;
  char *key_file;
  
  char *certificate_chain_file;
  char *ca_certificate_path;
  char *ca_certificate_file;
  char *ca_revocation_path;
  char *ca_revocation_file;
  
  char *password;
  char *crypto_device;
  int alg_flags;

  int enable_session_cache;
  int session_cache_timeout;

  int unclean_shutdown;

  int verify_client;
  int verify_depth;

  char *cipher_suite;

  void *crl;
  
  pthread_mutex_t ssl_lock;
} ssl_config_t;

struct server_socket_t {
  ssl_config_t *ssl_config;
  
  int conn_socket_timeout;
  
  int fd;

  int port;

  pthread_mutex_t ssl_lock;
  pthread_mutex_t accept_lock;
  int verify_client;

  /* ssl context */
  void *context;
  
  int (*accept) (server_socket_t *ss, connection_t *conn);
  void (*close) (server_socket_t *ss);
  int server_index;
};

#define ALG_SSL2 0x01
#define ALG_SSL3 0x02
#define ALG_TLS1 0x04

#define Q_VERIFY_NONE 0
#define Q_VERIFY_OPTIONAL_NO_CA 1
#define Q_VERIFY_OPTIONAL 2
#define Q_VERIFY_REQUIRE 3

/* memory.c */
void cse_mem_init();
void cse_free(void *);
void *cse_malloc(int size);

/* std.c */
extern struct connection_ops_t std_ops;

int std_accept(server_socket_t *ss, connection_t *conn);
void std_close_ss(server_socket_t *ss);

int conn_close(connection_t *conn);

/* ssl.c */
int ssl_create(server_socket_t *ss, ssl_config_t *config);
connection_ops_t *ssl_get_ops();

/* java.c */
void resin_printf_exception(JNIEnv *env, const char *cl, const char *fmt, ...);
void resin_throw_exception(JNIEnv *env, const char *cl, const char *buf);

#define INTERRUPT_EXN -2
#define DISCONNECT_EXN -3
#define TIMEOUT_EXN -4

#ifndef EWOULDBLOCK
#define EWOULDBLOCK EAGAIN
#endif

#endif /* RESIN_H */
