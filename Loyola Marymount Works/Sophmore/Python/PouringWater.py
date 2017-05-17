def iterDeepDepthSearch(root):
    for depth in range(0, 5):
        print(root)
        print(depth)
        found = DLS(root, depth)
        if found != None:
            return found


def DLS(node, depth):
    if (depth == 0) and (2 in node):
        return node
    elif depth > 0:
        for child in node:
            found = DLS(child, depth-1)
            if found != None:
                return found
    return None