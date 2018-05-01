from twilio.rest import TwilioRestClient
import os
import time

ACCOUNT_SID = "<imported from external file>"
AUTH_TOKEN = "<imported from external file>"

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
