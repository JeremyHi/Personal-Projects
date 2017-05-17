from itertools import permutations as p

def next_largest(x):
    perm = sorted(list({''.join(i) for i in p(x, len(x))}))
    return perm[perm.index(x)+1]

for x in ['1234', '1243', '234765', '19000']:
    print(next_largest(x))