count = 0
def f(n):
	global count
	if n > 1:
		print("still going")
		f(n/2)
		f(n/2)
		count = count + 1

print(f(4))
print(count)