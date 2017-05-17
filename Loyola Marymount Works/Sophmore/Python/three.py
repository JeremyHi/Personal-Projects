"""Yield successive n-sized chunks from l."""
"Found on: http://stackoverflow.com/questions/312443/how-do-you-split-a-list-into-evenly-sized-chunks-in-python"
def chunks(l, n):
    for i in range(0, len(l), n):
        yield l[i:i+n]


def three_partition(numbers):
	if len(numbers) % 3 != 0:
		return False
	
