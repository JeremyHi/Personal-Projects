from collections import Counter
def majority(a):
	counts = Counter()
	for x in a:
		counts[x] += 1
		if counts[x] > len(a) // 2:
			return x
	return None