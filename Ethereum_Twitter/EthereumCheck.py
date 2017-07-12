import json, requests, time
import TwitterCredentials as tc
from twython import Twython

starttime = time.time()

def hourly_update():
  credentials = tc.getTwitterCredentials()

  twitter = Twython(
    credentials['APP_KEY'],
    credentials['APP_SECRECT'], 
    credentials['OAUTH_TOKEN'], 
    credentials['OAUTH_TOKEN_SECRET'])

  response_Coin_Makret_Cap = json.loads(requests.get("https://coinmarketcap-nexuist.rhcloud.com/api/eth").text)['price']['usd']
  response_Crypto_Compare = json.loads(requests.get("https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=USD").text)['USD']
  response_GDax = json.loads(requests.get("https://api.gdax.com/products/ETH-USD/ticker").text)['price']
  
  response_list = [float(i) for i in [response_Coin_Makret_Cap, response_Crypto_Compare, response_GDax]]
  response_average = '{0:.2f}'.format(sum(response_list) / len(response_list))

  tweet = "The current price of Ethereum in USD is: $" + response_average + " #Ethereum #ETH"

  twitter.update_status(status=tweet)
  print(tweet)
  
try:
  hourly_update()
except Exception as e:
  print(e)
