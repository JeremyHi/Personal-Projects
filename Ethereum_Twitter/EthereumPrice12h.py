#!/usr/bin/env python

import json, requests, time
from twython import Twython
import TwitterCredentials as tc

starttime = time.time()

def twelve_hour_update():
  credentials = tc.getTwitterCredentials()

  twitter = Twython(
    credentials['APP_KEY'],
    credentials['APP_SECRECT'], 
    credentials['OAUTH_TOKEN'], 
    credentials['OAUTH_TOKEN_SECRET'])

  response_Coin_Makret_Cap = json.loads(requests.get("https://coinmarketcap-nexuist.rhcloud.com/api/eth").text)['price']['usd']
  response_Crypto_Compare = json.loads(requests.get("https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=USD").text)['USD']
  current_price = round((response_Coin_Makret_Cap + response_Crypto_Compare) / 2, 2)

  past_twelve_hours = twitter.get_user_timeline(screen_name="Current_ETH", count=12)

  twelve_hours_ago = 0
  for tweet in past_twelve_hours:
    if '$' in tweet['text']:
      print(tweet['text'][tweet['text'].index('$')+1:tweet['text'].index('$')+7])
      twelve_hours_ago+= float(tweet['text'][tweet['text'].index('$')+1:tweet['text'].index('$')+7])

  percent_change = (current_price - (twelve_hours_ago/len(past_twelve_hours))) / twelve_hours_ago * 100

  change = str(round(percent_change, 2)) + "% " + ("increase" if percent_change > 0 else "decrease")

  tweet = "In the past 12 hours, we have seen a " + change + " in the price of Ethereum to USD. #Ethereum #ETH"

  twitter.update_status(status=tweet)
  print(tweet)

try:
  twelve_hour_update()
except Exception as e:
  print(e)