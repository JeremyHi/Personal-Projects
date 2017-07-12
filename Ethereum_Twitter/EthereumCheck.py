#!/usr/bin/env python

import json, requests, time
import TwitterCredentials as tc
from twython import Twython

def getTwitter():
  credentials = tc.getTwitterCredentials()
  return Twython(
    credentials['APP_KEY'],
    credentials['APP_SECRECT'],
    credentials['OAUTH_TOKEN'],
    credentials['OAUTH_TOKEN_SECRET'])

def getPrices():
  response_Coin_Makret_Cap = json.loads(requests.get("https://coinmarketcap-nexuist.rhcloud.com/api/eth").text)['price']['usd']
  response_Crypto_Compare = json.loads(requests.get("https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=USD").text)['USD']
  response_GDax = json.loads(requests.get("https://api.gdax.com/products/ETH-USD/ticker").text)['price']
  return [float(i) for i in [response_Coin_Makret_Cap, response_Crypto_Compare, response_GDax]]

def postToJSON(numbers):
  with open('PriceData.txt', 'a') as outfile:
    outfile.write(numbers + '\n')

def hourly_update():
  twitter = getTwitter()
  response_list = getPrices()

  response_average = '{0:.2f}'.format(sum(response_list) / len(response_list))
  postToJSON(response_average)
  tweet = "The current price of Ethereum in USD is: $" + response_average + " #Ethereum #ETH"

  twitter.update_status(status=tweet)
  print(tweet)

try:
  hourly_update()
except Exception as e:
  print(e)
