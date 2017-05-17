/* Plan for today:

   11:20 - 12:00 ... How the Prolog interpreter works
   12:00 - 12:20 ... Summary and Final Topics
   12:20 - 12:35 ... Course Evaluations
   
 */

/*

How does prolog evaluate queries?

- Fundamental operation: unification.
  - Given two terms t1 and t2, find a *substitution* s 
    - a mapping from variables to terms
    - such that s(t1) == s(t2)
    - == means "structural equality"
    - the exact same terms syntactically
    - if this succeeds, then s is a *unifier* for t1 and t2.

Prolog's predicate t1 = t2 tests for *unifiability*

unify(X,X).
unify(X,Y) :- X = Y.

?- a = b.
false.

?- f(X,b) = f(a,Y).
X = a,
Y = b.

?- g(a,X,X) = g(Y,a,Z).
X = Y, Y = Z, Z = a.

?- g(a,X,X) = g(Y,b,Y).
false.

Unification is the way that *all* computation is done in prolog
- Except for arithmetic.

Running a query is based on unification too.
- e.g. append([1,2,3], [4,5], X)
- try to unify with the head of each rule in the database
- pass the first two terms in as parameters,
- "return" the third term as the result.

Comma operator:
  logically *and*, but can be used as sequence (;)
  can model assignment, but single assignment only
  X=0, X=[1,2] fails.

Unification can deconstruct values, as in OCaml's pattern matching
  [X|Y] = [1,2,3] succeeds with X=1 and Y=[2,3]

Note: not always a *unique* unifier

?- f(X,Y) = f(a,Y).
X = a.

Y can be anything, and Prolog *could* assign some arbitrary value to it.

An important property that makes Prolog work is that
while there is not always a *unique* unifier, there is
a *best* one. 
 - A *most general unifier* (MGU)
 - constrains the values as little as possible to get them to unify.
 - s is a MGU for t1 and t2 if any other substitution is more specific.

In the above example,
  [X = a, Y = 0] is more specific than [X=a]

*/

g(X,Y) :- f(X,Y) = f(a,Y), Y=1234.


/* Overall search algorithm:
   
   Given a goal to be proven (initially the query).
     - Try to unify the goal with one of the rules.
     - If it succeeds, then replace the goal with the body (conditions)
       of the rule.
     - These conditions become (sub)goals
     - Continue recursively until all the goals have been proven.
 */

p(f(Y)) :- q(Y), r(Y).
q(h(Z)) :- t(Z).
r(h(a)).
t(a).

/* 

Query: p(X).

Goals: [p(X)]

1) Try the first rule: p(f(Y)) = p(X)
     MGU = [X=f(Y)]
     new goals: [q(Y), r(Y)]

2) Need to prove q(Y). Try the second rule: q(h(Z)) = q(Y)
     MGU = [Y=h(Z)]
     new goals: [t(Z), r(h(Z))]
       - note that we substituted for Y in the goal list!

3) Need to prove t(Z). Try the last rule: t(a) = t(Z)
     MGU = [Z=a]
     new goals: [r(h(a))] 

4) Need to prove r(h(a)). Try the third rule: r(h(a)) = r(h(a))
     MGU = []
     new goals: []. Done!    

This tells us our query is true.
  If we want to return the value of X for which p(X) is true,
  we just need to keep track of a substitution for X
    after 1, we have X=f(Y)
    after 2, we have X=f(h(Z))
    after 3, we have X=f(h(a))

Search.
  - for each (sub)goal, prolog tries to unify against each rule in the DB
    in top-down order.
  - this is a depth-first search strategy.
  - If we succeed in proving all the goals, *or* if one of the goals
    is unprovable, then we backtrack.    

The database is a finite description of a potentially infinite search tree
  - recursion!

 */


















/* --------------------- Course Summary ---------------------

Programming Paradigms
  - Imperative
  - Functional
  - Object Oriented
  - Logic
  - Multi-Paradigm
  Hallmarks and strengths of each paradigm.

Languages
  - OCaml
    + Functional: prefer recursion to iteration
    + First-class functions
    + Pattern matching
    + Immutable data types
    + Parametric polymorphism
      length : 'a list -> int
    + Type inference
      reduces the cost of static type checking
    + Anonymous functions
      (fun x -> x + 1)
  - Python
    + Dynamically typed
    + Duck typing
    + Imperative/Functional/OO
    + First-class functions
    + Efficient general purpose data types (mutable)
      Lists, Dictionaries, Sets, etc.
    + List comprehensions
      [x+1 for x in l]
      [x+y for x in l1 for y in l2]
      Ocaml:
      map(fun x -> x + 1, l)
    + Anonymous functions
      (lambda x: x + 1)

      Can't have statements in lambda expressions:

      (lambda x: y = x+1
                 return x+y)
  - Java
    + OO/Imperative (+ increasingly functional)
    + Subtyping
      - "Is a" relationship
      - class A implements I {}
        A is a subtype of I
	A can be used where I is expected
      - Modularity
        important for maintainence
	    managing complexity
    + Subclassing/Inheritance
      - code reuse
      - class B extends A {}
        B inherits A's stuff
	+ B is a subtype of A
      - in java subclass is inheritance + subtyping
      - can't get inheritance without subtyping
    + Generics (parametric polymorphism)
    + Generics vs overloading.
      - Generics: use same code with different types
        - general-purpose code
      - Overloading: use same *name* with different types; each has different code
        - special casing
    + Overloading vs overriding.
      - overloading: the compiler select the method (name) statically
        based on the compile time types of the arguments
        *early binding*
      - overriding: selects the method implementation dynamically
        based on the runtime type (the class) of the receiver object.
        *late binding*
    + Subtyping vs automatic conversion.
      - subtyping changes nothing
        use a ListSet where a Set is expected
      - automatic conversion makes a copy
    + Abstract Classes
    + Downcalls
      Example: Set.addAll(List<E>) calls ListSet.add(E elem)
      a bunch of times
      addAll is a superclass method, add is a subclass method
  - Prolog
    + Logic programming (PROgramming LOGic)
    + Atoms, Predicates, Rules
    + Queries
    + Unification (pattern matching++)
    + Search and Backtracking
    + Declarative: say what you want, not how to get it
      - contrast with imperative
      
The Big Ideas
  - Syntax
    + Grammars
    + Syntax trees
  - Functions
    + Recursion
    + Higher-order functions
    + Closures
    + Currying
  - Naming
    + Static Scoping
    + Lifetime
    + Shadowing
  - Modularity
    + Recurring theme.
    + Separation of interface from implementation.
  - Evaluation
    + Eager evaluation.
    + Lazy evaluation and short-circuiting.
    + Combination with effects!
  - Typing
    + Strong vs Weak
    + Static vs Dynamic
    + Automatic Conversion
    + Polymorphism: parametric, subtype.
  - Control Flow
    + Loops
    + Conditionals
    + Pattern Matching
    + Recursion
    + Dynamic Dispatch
    + Short-circuiting
    + Unification/Backtracking


--------------------------- Final Exam Topics -----------------------
- Read/write small OCaml/Python/Java/Prolog snippets
  - Read: questions like
    - What is the result of this code snippet?
    - What is in the environment after this code snippet?
    - What is the result of this pattern match? (failure or environment)
    - What is the result of this unification?
    - Java: What is printed by this program? 
  - Preparation: review / clean up / repeat homeworks.
- Concepts (as in midterm and quizzes)
  - Hallmarks and strengths of programming language paradigms
  - The big ideas
  - Differences/similarities between concepts
    - Subtyping vs subclassing
    - Overloading vs overriding
      - Overloading: early binding
      - Overriding: late binding (dynamic dispatch)
    - First-class vs higher-order functions
    - Static vs dynamic typing
    - "Strong" vs "Weak" typing vs automatic conversion
      - Strong: memory safety
      - Weak: no memory safety. Reinterpret object's memory as a different type
      - Auto Conversion: Convert object to a different type (check/copy)
        - Still memory safe.
        - Overhead relative to weak typing.
    - Pattern matching (ocaml) vs unification (prolog)
    - Parametric vs subtype polymorphism
    - Scope vs lifetime
    - Shadowing vs updating variables
    - Recursion vs iteration
    - Interfaces vs abstract classes
      - Interfaces: 
        - can implement (default) methods
        - no fields
        - can implement multiple
      - Abstract classes:
        - can declare fields and implement methods
        - can extend only 1
*/ 