"""

Ocaml pros and cons:
pros:
  - simple, clean, relatively straightforward
  - not too difficult to express an idea, once you have it.
  - requires forethought (also a con)
  - pattern matching
  - type inference
  - immutability

cons:
  - requires forethought
  - parser is not very smart
  - lots of nested scopes can get confusing
  - immutability is sometimes a pain

FP idioms are becoming mainstream:
  - JS, Java, C++, swift, rust, scala all combine
    imperative and FP concepts.

Python:
  - efficient (programmer effort). expressiveness
  - very expressive libraries.
    - both official and third-party
    - good documentation
    - FFI (foreign function interface) for using
      C/C++/etc code from python

  - high level language
  - intuitive syntax
    - avoids unpleasant syntax
    - for x in l:
    - whitespace-aware syntax
      - pro and con
      - can be error-prone

Python is a scripting languages.
- usually interpreted -- doesn't need to be compiled.
- other: JS, Ruby, Perl, bash, lua, VBScript, ...
- usually for interacting with some larger program or
  system
  - web browser (JS)
  - web server (PHP)
  - OS command line interface (bash, python, perl)
  - games (lua)
  - excel (VBscript)
- usually dynamically typed
- usually good for writing smaller programs
  - good for:
    - manipulating text 
    - files I/O
    - file systems
    - invoking other programs
- "rapid prototyping"
  - pro: explore the problem you are trying to solve
  - con: often just end up supporting a prototype long term
- Scripting languages tend to be messy
  - lots of features
    - from many paradigms
    - pro: many ways to tackle a problem
    - con: features tend to interact in surprising ways

- Functional/imperative/OO
  - we'll focus on imperative and functional
  - key point: FP with side effects
    - reassign variables
    - mutate data structures
    - IO
  - this is increasing widely used    

"""

# Tuples
(1, "hi")
tup = (1, "hi")

# tuples in python are just like ocaml's
# except: indexing instead of pattern matching

# lists vs tuples:
# tuples immutable, lists mutable.

def prodList(l):
   result = 1
   for i in l:
     if type(i) == int:
       result *= i
     elif type(i) == str:
       result *= int(i)
     else:
       raise Exception("what? ", i)
   return result   

# Example: break a string into substrings
# based on a delimiter.
#   

def splitAndStrip(x, delim):
	l = x.split(delim)
	i = 0
	while(i < len(l)):
		l[i] = l[i].strip()
		i += 1
	return l


# better: use range

def splitAndStrip(x, delim):
	l = x.split(delim)
	for i in range(len(l)):
		l[i] = l[i].strip()
	return l

# better: map and lambda

def splitAndStrip(x, delim):
  return list(map(lambda s: s.strip(), x.split(delim)))

# even better still: list comprehension

def splitAndStrip(x, delim):
  return [s.strip() for s in x.split(delim)]

# remove empty strings from the result 

def splitAndStrip(x, delim):
  return list(filter(lambda s: len(s) > 0,
        	 map(lambda s: s.strip(), x.split(delim))))

# do the same thing using list comprehensions


def splitAndStrip(x, delim):
  l = [s.strip() for s in x.split(delim)]
  return [s for s in l if len(s) > 0]

# if statements vs expressions
# a function or method body is a sequence of statements
# at the same level of indentation
# 123 is an expression
# foo() is an expression
# expressions can be used where statements are expected
# the arguments to a function are expressions. can't be statements
# 
# if statement:
#  > if cond_expr:
#  >    then_stmt
#  > else:
#  >    else_stmt

# if expressions:
#    then_expr if cond_expr
#    then_expr if cond_expr else else_expr
#    then_expr if cond_expr (else None) if you leave off the else branch
#



"""

Higher order functions in Python. 
- Currying
- Closures
- Mutation

"""

def plus(x,y):
  return x + y

# No auto-currying!

def plus(x):
  return lambda y: x+y

def plus(x):
  def plusX(y):
    return x+y
  return plusX

# combination of first-class functions and mutation.

# a simple way of modeling objects:
# an object is some data and some associated operations on that data.


def Counter():
  n = 0
  def callMethod(methodName):
    if methodName  == "get":
      return n
    else:
      raise "Unknown method"
  return callMethod

def Counter(m):
  n = m
  def callMethod(methodName):
    if methodName  == "get":
      return n
    elif methodName == "inc":
      n += 1
    else:
      raise "Unknown method"
  return callMethod

# Error! Subtle interaction between mutation and closures in python
# Rule: if a function assigns to a variable, it can't be a closure
# variable.
# - Closures are immutable in Python.
# 

def Counter(m):
  state = {'n' : m}
  def callMethod(methodName):
    if methodName  == "get":
      return state['n']
    elif methodName == "inc":
      state['n'] += 1
    else:
      raise "Unknown method"
  return callMethod

# Works!
# Even though closures are immutable, they can contain refer to
# mutable data.

"""
  Closure { n = 5 } --> Closure { n = 6 }  CANT DO THIS!

  Closure { state = {'n':5 }} --> Closure { state = {'n':6 }}

                    ^ this doesn't change.


Subtle interaction between closures and mutation.
- This is design choice that Python made


Example: JS

function Counter() {
  var c = 0;
  return function(methodName) {
    switch(methodName) {
    case "get":
      return c;
    case "inc":
      c+=1;
      break;
    default:
      throw new Error("unknown method");
    }
  }
}


function Counter2() {
  var c = 0;
  return [() => c, () => {c++; return}];
}

var [get,inc] = Counter2();
console.log(get());
inc();
console.log(get());



"""

# We can do similarly in Python


def Counter(m):
  state = {'n' : m}
  def inc():
    state['n'] += 1
  return (lambda : state['n'], inc)

c = Counter(5)

"""

This idea is idiomatic in JS, for:
- objects
- libraries.
Closures (mutable in particular) can provide *modularity*
- hiding implementation details
- closure variables are used in objects as *private* field/members
- closure variables are used in libraries as *internals*
  - state, helper functions, ... that should be hidden from clients
Why?
- JS has no other way to enforce abstraction boundaries of objects
  and/or libraries.
- Closure variables can't be accessed from outside the closure.


Not idiomatic for python.
Instead, uses a *convention* for privacy.
Decorate your private names with a "__" prefix
Signals/warns your clients that this is private


"""

class Counter:
  def __init__(self):
    self.__n = 0
  def get(self):
    return self.__n
  def inc(self):
    self.__n += 1
  def __reset(self):
    self.__n = 0

"""
So modularity in Python is *encouraged* but not *enforced*

If we want true privacy, we can use a closure object.


"""

def Counter():
  private = {}
  class Impl:
    def __init__(self):
      private['n'] = 0
    def get(self):
      return private['n']
    def inc(self):
      private['n'] += 1
    def __reset(self):
      private['n'] = 0
  return Impl()

# We can make reset private too, by storing it in private

def Counter():
  private = {}
  class Impl:
    def __init__(self):
      private['n'] = 0
      def reset():
        private['n'] = 0
      private['reset'] = reset
    def get(self):
      return private['n']
    def inc(self):
      private['n'] += 1
  return Impl()

# private --> reset --> private --> reset --> private --> reset --> ...

"""

>>> type(c1)
<class '__main__.Counter.<locals>.Impl'>
>>> type(c2)
<class '__main__.Counter.<locals>.Impl'>
>>> isinstance(c1, c2.__class__)


- Classes are different! 
- Dynamically generated. New Impl for each call to Counter.

"""

# mkCounter returns a new Counter class
def mkCounter():
  private = {}
  class Impl:
    def __init__(self):
      private['n'] = 0
      def reset():
        private['n'] = 0
      private['reset'] = reset
    def get(self):
      return private['n']
    def inc(self):
      private['n'] += 1
  return Impl

# Changed the private variable n from being a "instance variable"
# - each instance has an independent copy
# ... to a "class variable" or "static member variable"
# - all instances share a single copy
# - class variables are somewhere between globals and instance variables.
#    - globals: shared by the entire program
#    - class vars shared by all instances of a class

# OCaml has first class functions... Python has first class classes!

# Let's make private unique per-instance
# move the declaration of private to inside the constructor __init__.

import types
def mkCounter():
  class Impl:
    def __init__(self):
      # move private into __init__ so we get a new one per instance
      private = {}
      private['n'] = 0
      def reset():
        private['n'] = 0
      private['reset'] = reset
      # move get and inc into __init__ to grab private in their closure
      def get(self):
        return private['n']
      # make get (just a function) a method of self
      self.get = types.MethodType(get,self)
      def inc(self):
        private['n'] += 1
      self.inc = types.MethodType(inc,self)
  return Impl


"""
Methods are also first class.
- We can define them on the fly
- We can add a unique per-object method


"""










"""

HW6 is simple database query language
- file format is CSV
- one table. the CSV file
- the queries are simple. map and fold

Use python3!

"""

import sys

#print(type(sys.argv))
#print(sys.argv)

for fname in sys.argv[1:]:
  # open the file
  f = open(fname)

  # iterate over the lines of the file
  for ln in f:
    print(ln[:-1])

  f.close()

""" 

Loops, iterators, generators, and yield


Map doesn't return a list.
Returns a generator
- avoids allocating the entire list up front
- generate each element on demand
- Can lead to subtle behaviors
  - possible that not every element is generated
  - watch out for side effects

We can define our own generators using the yield keyword  


"""

def trace(x):
  print ("generating " + str(x))
  return x

def myGen():
  print("calling myGen")
  yield trace(1)
  yield trace("hey")
  yield trace(False)

"""

- myGen is called a *generator* in Python.
- it's a kind of *iterator*, which is what the for loop is looking for.
- An iterator is any object that has:
  - an __iter__ method
    - begins a new iteration
    - a list has an __iter__ method
  - a __next__ method
    - provides the next value in the iteration
    - when no more values, raise StopIteration

"""

def intsFrom(n):
  while(True):
    yield n
    n += 1

# generator expressions: like list comprehensions, but for generators
# (x*2 for x in intsFrom(1))

class MyGen: 
  def __init__(self):
    self.n = 0
  def __iter__(self):
    return self
  def __next__(self):
    print("calling __next__")
    self.n += 1
    if self.n > 5:
      raise StopIteration
    return self.n

"""

Note: there is no Iterator interface

Python's typing discipline is called "Duck typing"
 - if it walks like a duck and quacks like a duck, its a duck.
 - if an object has the methods expected of an interator, its an iterator.

Python's iterator interface makes for loops extensible.
 - Java, C++, Scala also have iteratable, etc for similar idea
 - not as seemlessly integrated into that language as in python
 - fancy generator expressions that are unique to python

"""







