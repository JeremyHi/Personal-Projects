# Do not import anything other than sys and re!
import sys
import re

# this function removes punctuation from a string.
# you should use this before adding a word to the index,
# so that "asdf." and "asdf" and "asdf," are considered to be
# the same word.

def remove_punctuation(s):
  return re.sub(r'[^\w\s]', '', s)

assert(remove_punctuation('asdf.') == 'asdf')
assert(remove_punctuation('asdf,') == 'asdf')
assert(remove_punctuation("?;'.!") == '')

# index is a global dictionary. The keys should be words, and the
# values should be tuples of (filename, line number, position in line).

index = {}

def build_index():
  files = list(sys.argv)
  for f in files[1:]:
    with open(f) as file:
      for lineNum, line in enumerate(file):
        for word in line.split():
          if remove_punctuation(word).lower() not in index:
            index[remove_punctuation(word).lower()] = [(f,str(lineNum),str(line.index(word)))]
          else:
            index[remove_punctuation(word).lower()].append((f,str(lineNum),str(line.index(word))))

build_index()

# commands

def words(args):
  if len(args) < 1:
    return [print(key) for key in sorted(list(index.keys()))]
  return [print(key) for key in sorted(list(index.keys())) if key.startswith(args[0].lower())]

def occurrences(args):
  for occurrence, word in enumerate(index[args[0]]):
    print('  (' + str(occurrence) + ') ' + 'File ' + word[0] + ', Line ' + str(word[1]) + ', Character ' + str(word[2]))

def context(args):
  for f in (list(sys.argv))[1:]:
    with open(f) as file:
      for lineNum, line in enumerate(file):
        if (int(lineNum)-1) == int(args[1]) and args[0] in line.lower():
          print(line + re.sub(r'[A-Za-z0-9 _.,!"]', ' ', (line.lower().replace(args[0], ("^" * len(args[0]))))))

def output(args):
  for x in sorted(index):
    print(x)
    occurrences([x])
    
cmds = {
  'words' : words,
  'occurrences' : occurrences,
  'context' : context,
  'output' : output,
  }

def interact():
  # print the prompt line
  print('> ', end='', flush=True)
  
  for ln in sys.stdin:
    ln = ln.strip().split(' ')
    if ln[0] == 'quit':
      return
    else:
      cmds[ln[0]](ln[1:])

    # print the next prompt line
    print('> ', end='', flush=True)

interact()