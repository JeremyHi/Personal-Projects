def neas(solution, solutionLength):
    if solutionLength == 1:
        return True

    rowHalfOne = solution[:solutionLength//2]
    rowHalfTwo = solution[solutionLength//2:]

    if rowHalfOne == rowHalfTwo:
        return False

    for i in range(1, solutionLength//2):
        if solution[solutionLength - 2 * i: solutionLength - 1] == solution[solutionLength - i: solutionLength]:
            return False
    
    return True