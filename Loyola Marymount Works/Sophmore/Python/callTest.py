call_cacheK = {}

count = 0
def callTest(n, k):
	global count
	count += 1
	if k > n or k < 0:
		return False
	elif k == 0:
		return 1
	elif k == n:
		return 1 
	elif (1 <= k) and (k <= (n-1)):
		result = ((callTest(n-1, k) + callTest(n-1, k-1)))
	return count

print(callTest(20, 11))