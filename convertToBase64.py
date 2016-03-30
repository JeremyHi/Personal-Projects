import base64
import binascii

def splitByTwo(str):
    return [i+j for i,j in zip(list(str)[::2], list(str)[1::2])]

def bytesToBase64(str):
    return base64.b64encode(binascii.unhexlify(str))
