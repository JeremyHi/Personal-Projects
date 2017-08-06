#!/usr/bin/env python

import json, requests, time
import praw
import RedditCredentials as rc

credentials = rc.getTwitterCredentials()
reddit = praw.Reddit(
	client_id=credentials['CLIENT_ID'],
	client_secret=credentials['CLIENT_SECRECT'],
	user_agent=credentials['USER_AGENT'])

def getHotLink():
	for submission in reddit.subreddit('Ethereum').hot(limit=10):
	    print(submission.url)
	    # return(submission.url)

try:
	getHotLink()
except Exception as e:
	print(e)