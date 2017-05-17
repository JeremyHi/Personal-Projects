import graph, math, sys
from graph import Graph, Vertex, PriorityQueue

def makeGraph(string, fullGraph):
    splitString = string.replace(",", "")
    splitString = splitString.split("|")

    for item in splitString:
            if not (fullGraph.get_vertex(item[0])):
                fullGraph.add_vertex(item[0])

    for vertices in fullGraph.get_vertices():
        for item in splitString:
            if item[0] == vertices:
                fullGraph.get_vertex(vertices).add_neighbor(item[1], int(item[2:]))

    return fullGraph

def prim(graph, startNode):
    priorityQueue = PriorityQueue()
    for vertex in graph.get_vertices():
        for adjacentList in graph.get_vertex(vertex).adjacent:
            graph.get_vertex(vertex).add_neighbor(adjacentList)

    graph.get_vertex(startNode).add_neighbor(startNode, 0)
    for key in graph.get_vertices():
        for adjacentList in fullGraph.get_vertex(key).adjacent:
            priorityQueue.push(key, adjacentList, fullGraph.get_vertex(key).adjacent[adjacentList])

    while not priorityQueue.isEmpty(): 
        currentVert = graph.get_vertex(priorityQueue.pop())
        for nextVert in currentVert.get_connections():
            newCost = currentVert.get_weight(nextVert)
            if nextVert in priorityQueue._queue and newCost < nextVert.get_weight():
                nextVert.add_parent(currentVert)
                graph.get_vertex(nextVert).add_neighbor(startNode, newCost)
                priorityQueue._index = priorityQueue._index - 1
    return graph

input = "A,B,3|B,C,10|C,A,4|D,E,2|E,A,6"
fullGraph = Graph()
makeGraph(input, fullGraph)

prim(fullGraph, fullGraph.get_vertex('A').get_id())