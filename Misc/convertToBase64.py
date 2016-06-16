import base64
import binascii

def bytesToBase64(str):
    return base64.b64encode(binascii.unhexlify(str))
