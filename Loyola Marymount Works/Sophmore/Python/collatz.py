def collatz(n):
	counter = 0
	
	while n > 1:
		if n%2 == 0:
			n = n/2
			counter += 1
		elif n%2 != 0:
			n =  (3*n)+1
			counter += 1
	return counter

print(collatz(30))