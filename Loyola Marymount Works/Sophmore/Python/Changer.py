class Changer:
    def can_make_change_for(coins, value):
        table = [None for x in range(value + 1)]
        table[0] = []
        for i in range(1, value + 1):
            for coin in coins:
                if coin > i: continue
                elif not table[i] or len(table[i - coin]) + 1 < len(table[i]):
                    if table[i - coin] != None:
                        table[i] = table[i - coin][:]
                        table[i].append(coin)

        if table[-1] != None:
            return True
        else:
            print("No solution possible")

    def can_make_change_using_each_coin_once(coins, value):
        table = [None for x in range(value + 1)]
        table[0] = []
        for i in range(1, value + 1):
            for coin in coins:
                if coin > i: continue
                elif not table[i] or len(table[i - coin]) + 1 < len(table[i]):
                    if table[i - coin] != None:
                        table[i] = table[i - coin][:]
                        table[i].append(coin)
        if table[-1] != None:
            print('%d coins: %s' % (len(table[-1]), table[-1]))
            for element in table[-1]:
                if element > 1:
                    return False
            return True
        else:
            print('No solution possible')

    def can_make_change_with_limited_coins(coins, change, minCoins, max_coins):
        for cents in range(change+1):
            coinCount = cents
            for j in [c for c in coins if c <= cents]:
                if minCoins[cents-j] + 1 < coinCount:
                   coinCount = minCoins[cents-j]+1
            minCoins[cents] = coinCount
            if minCoins[change] <= max_coins:
                return True
        return False


    #print(can_make_change_for([1], 3))
    print(can_make_change_using_each_coin_once([1], 4))
    amount = 3
    coins = [2]
    coinsUsed = [0] * (amount + 1)
    #print(can_make_change_with_limited_coins(coins, 1, coinsUsed, amount))


















