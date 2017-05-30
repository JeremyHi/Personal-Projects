import os

def Time_Machine_Status():
	tmResults = subprocess.check_output(['tmutil status'])
	print(type(tmResults))

Time_Machine_Status()