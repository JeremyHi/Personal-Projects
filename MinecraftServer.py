from twilio.rest import TwilioRestClient
import os
import time

ACCOUNT_SID = "AC6ca9b432885df372f4d6c6952bdc6cc5"
AUTH_TOKEN = "9d7f2b788e887fad893b35b895d03c5c"

client = TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN)

hostname = "Piss.serv.nu"
response = os.system("ping -c 1 -W 2000 " + hostname)

def pingTest():
	if response == 0:
	  print(hostname, 'is up!')

	  client.messages.create(
	    to="+16502882217",
	    from_="+16507535638",
	    body="Server is Running"
	  )
	else:
	  print(hostname, 'is down!')
	  time.sleep(5)
	  pingTest()



pingTest()