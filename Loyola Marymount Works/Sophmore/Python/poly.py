#poly.py

class Polynomial:
    def __init__(coefficients):
        self.coefficients = coefficients

    def evaluate(coefficients, x):
        coefList = []
        powerList = []
        for key, value in coefficients.items():
            value = value
            power = key
            coefList.append(value)
            powerList.append(power)
        missingCofPos = list(set(list(range(int(powerList[len(powerList)-1]+1)))).difference(powerList))
        for g in range(len(missingCofPos)):
            coefList.insert(missingCofPos[g], 0)
        coefList.reverse()
        result = 0;
        for i in range(0, len(coefList)):
            result *= x
            result += coefList[i]
        print(result)


    evaluate({5:3,3:-2,1:5,0:-3}, 2)

#max(d.keys()) to return the max power