import re
import requests

def grab_memes():
	params = (('hl', 'en'))
	response = requests.get('https://www.instagram.com/memes/?hl=en', params=params).text
	images_url_list = []
	while 'https://sconten' in response:
		images_url_list.append(response[response.index('https://sconten'):response.index('.jpg') + 4])
		response = response.replace(response[response.index('https://sconten'):response.index('.jpg') + 4], '')
	print(images_url_list)

grab_memes()