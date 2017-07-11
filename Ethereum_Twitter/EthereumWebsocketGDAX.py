import gdax, time

class myWebsocketClient(gdax.WebsocketClient):

  def on_open(self):
    self.url = "wss://ws-feed.gdax.com/"
    self.products = ["ETH-USD"]
    self.message_count = 0
    print("Lets count the messages!")

  def on_message(self, msg):
    self.message_count += 1
    if 'price' in msg and 'type' in msg:
      print ("{}".format(float(msg["price"])))
  
  def on_close(self):
    print("-- Goodbye! --")

wsClient = myWebsocketClient()
wsClient.start()

while (wsClient.message_count < 10):
  print(wsClient.url, wsClient.products)
  print ("\nmessage_count =", "{} \n".format(wsClient.message_count))
  time.sleep(1)
  wsClient.close()
