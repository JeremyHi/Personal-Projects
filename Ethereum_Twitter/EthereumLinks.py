#!/usr/bin/env python

import praw
import RedditCredentials as rc
import TwitterCredentials as tc
from twython import Twython

credentials = rc.getTwitterCredentials()
reddit = praw.Reddit(
  client_id=credentials['CLIENT_ID'],
  client_secret=credentials['CLIENT_SECRECT'],
  user_agent=credentials['USER_AGENT'])

def getTwitter():
  credentials = tc.getTwitterCredentials()
  return Twython(
    credentials['APP_KEY'],
    credentials['APP_SECRECT'],
    credentials['OAUTH_TOKEN'],
    credentials['OAUTH_TOKEN_SECRET'])

def getHotLink():
  imageExtensions = ['.gif','.jpeg','.jpg','.jif','.jfif','.jp2','.jpx','.j2k','.j2c','.fpx','.pcd','.png','.pdf','www.reddit.com']

  linkList = []
  for submission in reddit.subreddit('Ethereum').hot(limit=20):
      if not any(extensions in submission.url for extensions in imageExtensions):
        linkList.append(submission.url)
  
  return linkList

def postToTwitter(urlList):
  twitter = getTwitter()
  for url in urlList:
    try:
      tweet = url + " #Ethereum #ETH"
      print(tweet)
      return twitter.update_status(status=tweet)
    except Exception as e:    
      print(e)

try:
  postToTwitter(getHotLink())
except Exception as e:
  print(e)