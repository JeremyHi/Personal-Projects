def format(string):
    string = string.replace(',', ', ')
    result = []
    commaCount = 0
    for idx, char in enumerate(string):
        result.append(char)
        if char == ',':
            commaCount = commaCount + 1 
        if commaCount == 3:
            commaCount = 0
            result.append('\n')
    print(''.join(result))