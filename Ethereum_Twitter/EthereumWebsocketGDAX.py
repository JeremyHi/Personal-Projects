import gdax, time

class myWebsocketClient(gdax.WebsocketClient):

  def on_open(self):
    self.url = "wss://ws-feed.gdax.com/"
    self.products = ["ETH-USD"]
    self.message_count = 0
    print("Begin Program: price_check_stream")

  def on_message(self, msg):
    if 'price' in msg and 'type' in msg:
      print ("{}".format(float(msg["price"])))
      time.sleep(.5)
  
  def on_close(self):
    print("-- Goodbye! --")

wsClient = myWebsocketClient()
wsClient.start()
time.sleep(3)  
wsClient.close()
