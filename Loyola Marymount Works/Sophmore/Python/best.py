def find_shortest_path(graph, start, end, path=[]):
    path = path + [start]
    if start == end:
        return path
    if start not in graph:
        return None
    shortest = None
    for node in graph[start]:
        if node not in path:
            newpath = find_shortest_path(graph, node, end, path)
            if newpath:
                if not shortest or len(newpath) < len(shortest):
                    shortest = newpath
        return shortest



Wgraph = {'S': ['A', 'B'],
             'B': ['C'],
             'A': ['C'],
             'C': ['D', 'E'],
             'D': ['F'],
             'E': ['F']}
print(find_shortest_path(Wgraph, 'S', 'F'))