counter = 0

call_cache = {}
def callTest(n, k):
	global counter

	if k in call_cache:
		return call_cache[k]  
	if k == 0:
		call_cache[k] = k
		counter += 1
		print(counter)
		return 1
	elif k == n:
		call_cache[k] = k
		counter += 1
		print(counter)
		return 1 
	elif 1 <= k and k <= n-1:
		counter += 1
		call_cache[n] = n - 1
		call_cache[k] = k - 1
		return (callTest(n, k-1) + callTest(n-1, k-1))





print(callTest(20, 11))