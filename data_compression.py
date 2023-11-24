# -*- coding: utf-8 -*-
"""
The way that my compression algorthim works is that it will first convert all bytes into binary form and record repetitions. 
Since the max size is 127, that means we need at least 7 bits to store the byte. After converting the first byte if the next byte is repeated it appends a 0 to signify repeated byte.  
Otherwise, it will append a 1 to signify a new byte value and use 7 bits to record the next byte correpsonding value.
"""

from sys import getsizeof

# Function that takes in a byte array and returns a compressed version of the byte array
def byte_compress(data):
  if len(data) == 0:
    return []
  # Leading one needed as leading zeros will be removed when converting to an int
  # Convert first byte into binary string. Example 0x30 -> 0011_0000
  bits_array = ["1", format(data[0], 'b').zfill(7)]

  for i in range(1, len(data)):
    # If byte is repeated use a 0 to signify repeated byte
    if data[i] == data[i-1]:
      bits_array.append("0")
    # Otherwise, use a 1 to signify new byte and convert next byte into binary string
    else:
      bits_array.append("1")
      bits_array.append(format(data[i], 'b').zfill(7))

  # Join all bits into a string
  bits = ''.join(bits_array)

  # Need to have bits be divisable by 8 to convert to bytes
  if len(bits) % 8 != 0:
    bits = bits + "1" * (8-len(bits) % 8)

  intger_array = []

  # Convert bits into bytes by making an array of integers with values 0-255
  for i in range(0, len(bits), 8):
    intger_array.append(int(bits[i:i+8], 2))

  # Convert array of intgers into bytes array by calling python's bytes function
  return bytes(intger_array)

# Function that takes in a compressed byte array and returns an uncompressed version of the byte array
def byte_uncompress(bytes_array):

  string_array = []

  # Take byte array and convert into binary strings
  for i in bytes_array:
    string_array.append(format(i, 'b').zfill(8))

  # Join all bits into a string
  bits = ''.join(string_array)

  decompressed_byte_array, index, last_byte = [], 0, None

  while index < len(bits):
    # If in last byte and returning 1 we are hitting the padded 1's and should exit loop
    if bits[index] == '1' and index + 8 > len(bits):
      break
    # New byte (not repeated) find value of byte
    elif bits[index] == '1':
      last_byte = hex(int(bits[index+1:index+8], 2))
      decompressed_byte_array.append(last_byte)
      index += 8
    # Repeated byte, append the last_byte to array
    else:
      decompressed_byte_array.append(last_byte)
      index += 1

  return decompressed_byte_array


data = [0x03, 0x74, 0x04, 0x04, 0x04, 0x35, 0x35, 0x64,
        0x64, 0x64, 0x64, 0x00, 0x00, 0x00, 0x00, 0x00,
        0x56, 0x45, 0x56, 0x56, 0x56, 0x09, 0x09, 0x09]

compressed_bytes = byte_compress(data)

print("Compressed Array: " + str(compressed_bytes))
print("Orginal Array: " + str(byte_uncompress(compressed_bytes)))
print("Orginal size: " + str(getsizeof(bytes(data))))
print("Compressed size: " + str(getsizeof(compressed_bytes)))