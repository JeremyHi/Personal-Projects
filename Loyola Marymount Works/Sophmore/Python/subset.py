def positive_negative_sums(seq):
    P, N = 0, 0
    for e in seq:
        if e >= 0:
            P += e
        else:
            N += e
    return P, N

def subset_sum(seq, s=0):
    P, N = positive_negative_sums(seq)
    if not seq or s < N or s > P:
        return False
    n, m = len(seq), P - N + 1
    table = [[False] * m for x in range(n)]
    print(table)
    table[0][seq[0]] = True
    for i in range(1, n):
        for j in range(N, P+1):
            table[i][j] = seq[i] == j or table[i-1][j] or table[i-1][j-seq[i]]
    return table[n-1][s]


print(subset_sum([2,4,5,4],0))