# Problem 1
def forSummation(_list):
    result = 0
    for x in _list:
        result += x
    return result

def whileSummation(_list):
    bound = sum(_list)
    result = 0
    while result < bound:
        result += _list.pop()
    return result

def recSummation(_list):
    return _list.pop() + recSummation(_list) if len(_list) > 0 else False


# Problem 2
def altAdd(list_one, list_two):
    result = []
    for x, y in zip(list_one,list_two):
        result.append(x)
        result.append(y)
    return result


# Problem 3
__fib_cache = {}
def fib(n):
    if n in __fib_cache:
        return __fib_cache[n]
    else:
        __fib_cache[n] = n if n < 2 else fib(n-2) + fib(n-1)
        return __fib_cache[n]


# Problem 4
import functools
def cmp(x, y):
    return int(x + y) - int(y + x)

def min_cat(line, largest=True):
    return ''.join(sorted(line.split(),
                          key=functools.cmp_to_key(cmp),
                          reverse=largest))
