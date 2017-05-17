def is_legal(row, rowLength):
    if rowLength == 1:
        return True
    for x in range(0, rowLength-1):
        if row.count(x) > 1 or (abs((row[x] - row[rowLength-1])) == abs(x - (rowLength-1))):
            return False
    return True