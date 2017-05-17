def backtracker(toSolve, size, correct):
    solution = [0]
    whereAt = 1
    while len(solution) < size:
        if whereAt == len(toSolve):
            whereAt = solution[-1] + 1
            del solution[-1]
        else:
            solution.append(toSolve[whereAt])
            if correct(solution, len(solution)) == False:
                del solution[-1]
                whereAt += 1
            else:
                whereAt = 0
    return solution