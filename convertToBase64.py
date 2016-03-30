import base64

def splitByTwo(str):
    return [i+j for i,j in zip(list(str)[::2], list(str)[1::2])]

def bytesToBase64(str):
    b64List = []
    stringsByTwo = splitByTwo(str.upper())
    

print(bytesToBase64("49276d206b696c639f3C4D8a"))
