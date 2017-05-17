import random
import timeit

time = 0
for x in range(0,1000):
	start = timeit.default_timer()
	
	def is_sorted(list):
		return all(list[i] <= list[i+1] for i in range(0, len(list)-1))
	
	def bozoSort(myList):
		count = 0
		while ((is_sorted(myList)) != True):
			a = random.randint(0, len(myList)-1)
			b = random.randint(0, len(myList)-1)
			myList[a], myList[b] = myList[b], myList[a]
			count += 1
		return myList

	bozoSort([3,2,5,17,123,66,12312,3434])
	stop = timeit.default_timer()
	#print(stop - start)
	time = time + (stop - start)



print(time/10)