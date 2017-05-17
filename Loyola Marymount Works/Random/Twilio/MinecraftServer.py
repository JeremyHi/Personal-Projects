from twilio.rest import TwilioRestClient
import os
import time

ACCOUNT_SID = "AC6ca9b432885df372f4d6c6952bdc6cc5"
AUTH_TOKEN = "9d7f2b788e887fad893b35b895d03c5c"

client = TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN)

hostname = "Piss.serv.nu"
response = os.system("ping -c 1 -W 2000 " + hostname)
numbers = ["+16502882217","+16506193477","+16508631158"]

def pingTest(host, code):
	if code == 0:
	  print(host, 'is up!')

	  for person in numbers:
		  client.messages.create(
		    to=person,
		    from_="+16507535638",
		    body="The Piss is On!"
		  )
	else:
	  print(host, 'Piss is down!')
	  time.sleep(5)
	  code = os.system("ping -c 1 -W 2000 " + hostname)
	  pingTest(host, code)

pingTest(hostname, response)