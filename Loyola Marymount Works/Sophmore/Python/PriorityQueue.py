import heapq
from graph import add_vertex(a, b)

class PriorityQueue:
    def __init__(self):
        self._queue = []
        self._index = 0

    def push(self, item, priority):
        heapq.heappush(self._queue, (-priority, self._index, item))
        self._index += 1

    def pop(self):
        return heapq.heappop(self._queue)[-1]

    def heapsort(iterable):
    	h = []
    	for value in iterable:
    	    heappush(h, value)

graphMethodName = 'add_vertex'

testGraph = getattr(Graph, graphMethodName)

testGraph('A')