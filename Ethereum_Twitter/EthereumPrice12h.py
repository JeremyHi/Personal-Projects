#!/usr/bin/env python

import json, requests
from twython import Twython
import TwitterCredentials as tc

def getPrices():
  response_Coin_Makret_Cap = json.loads(requests.get("https://coinmarketcap-nexuist.rhcloud.com/api/eth").text)['price']['usd']
  response_Crypto_Compare = json.loads(requests.get("https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=USD").text)['USD']
  response_GDax = json.loads(requests.get("https://api.gdax.com/products/ETH-USD/ticker").text)['price']
  return [float(i) for i in [response_Coin_Makret_Cap, response_Crypto_Compare, response_GDax]]

def twelve_hour_update():
  credentials = tc.getTwitterCredentials()
  twitter = Twython(
    credentials['APP_KEY'],
    credentials['APP_SECRECT'],
    credentials['OAUTH_TOKEN'],
    credentials['OAUTH_TOKEN_SECRET'])

  twelve_hours_ago = 0
  counter = 1
  for line in reversed(list(open("PriceData.txt"))):
    if counter == 12: break
    try:
      print(float(line.rstrip()))
      twelve_hours_ago += float(line.rstrip())
    except Exception as e:
      print(e)
    counter += 1

  currentPrices = getPrices()
  current_price = sum(currentPrices) / len(currentPrices)

  percent_change = (current_price - (twelve_hours_ago/12)) / twelve_hours_ago * 100
  change = str(round(percent_change, 2)) + "% " + ("increase" if percent_change > 0 else "decrease")

  tweet = "In the past 12 hours, we have seen a " + change + " in the price of Ethereum to USD. #Ethereum #ETH"
  # twitter.update_status(status=tweet)
  print(tweet)

try:
  twelve_hour_update()
except Exception as e:
  print(e)
