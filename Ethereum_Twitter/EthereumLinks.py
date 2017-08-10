#!/usr/bin/env python

import praw, json, requests
import RedditCredentials as rc
import TwitterCredentials as tc
from twython import Twython

def getReddit():
  redditCredentials = rc.getRedditCredentials()
  return praw.Reddit(
  client_id=redditCredentials['CLIENT_ID'],
  client_secret=redditCredentials['CLIENT_SECRECT'],
  user_agent=redditCredentials['USER_AGENT'])

def getTwitter():
  twitterCredentials = tc.getTwitterCredentials()
  return Twython(
    twitterCredentials['APP_KEY'],
    twitterCredentials['APP_SECRECT'],
    twitterCredentials['OAUTH_TOKEN'],
    twitterCredentials['OAUTH_TOKEN_SECRET'])

def checkIfRecentTweet(url):
  twitter = getTwitter()
  previousTweets = twitter.get("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=Current_ETH&count=40")
  previousUrls = [tweet['entities']['urls'][0]['expanded_url'] for tweet in previousTweets if tweet['entities']['urls']]
  
  return url not in previousUrls

def getHotLink():
  reddit = getReddit()
  imageExtensions = ['.gif','.jpeg','.jpg','.jif','.jfif','.jp2','.jpx','.j2k','.j2c','.fpx','.pcd','.png','.pdf','www.reddit.com']
  linkList = [submission.url for submission in reddit.subreddit('Ethereum').hot(limit=20) if not any(extensions in submission.url for extensions in imageExtensions) and checkIfRecentTweet(submission.url)]

  return linkList

def postToTwitter(urlList):
  twitter = getTwitter()
  for url in urlList:
    tweet = url + " #Ethereum #ETH"
    print(tweet)
    return twitter.update_status(status=tweet)

try:
  postToTwitter(getHotLink())
except Exception as e:
  print(e)