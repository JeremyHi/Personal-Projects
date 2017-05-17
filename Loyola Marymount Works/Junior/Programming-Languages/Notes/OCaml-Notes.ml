(*  This is a comment in OCaml *)

(*

Functional Programming.

Distinguishing characteristic:
- no update to "variables" (named values)
- can never reassign after initialization

- why is this a good thing (or at least interesting?)
  - greatly simplified reasoning about program behavior
  - functions behave like math functions
    - on the same inputs, always return the same output
    - won't erase your hard drive
    - no other side effects (no memory side effects)
  - don't need to worry about what's mutable or not.
    - everything is immutable
  - eliminates a large class of program errors that are 
    notoriously hard to debug.
  - simultaneous development
  - parallelism "for free"

- "first-class" functions
  - you can assign them to variables.
  - you can pass them as arguments to another function
  - you can return them from other functions
  - you can declare them anywhere you write an expression
  - in general, they are just a kind of expression

  - "functions as data"
  - increasingly popular idea. first-class functions now in
    Java, C++, C#, JS, Swift, Rust, ...

  - keep the language small. Functional languages tend to be simpler,
    and often more expressive, than other kinds of languages.

OCaml.

Check out:
https://ocaml.org/learn/

OCaml has a REPL. It's a nice interactive
environment for exploring the language.
 
REPL: read-eval-print-loop

*)

(* Declare the global name x.
   It is defined to hold the value 5 for
   the rest of the program
*)

let x = 5;;

(* Declare a function (an expression with
   inputs). Note there is no return.
*)

let double x = x + x;;

(* Declare a recursive function. Need to use
   "let rec".
*)

let rec factorial n =
  if n = 0 
  then 1 
  else n * (factorial (n-1))

(* general if-then-else syntax: 

if EXP then EXP else EXP 

Note you always need both the then and else
branches in OCaml. Why?

if-then-else is an expression in OCaml,
like the ternary operator ?: in C:

EXP ? EXP : EXP 

*)

(* Case analysis: pattern matching *)

let rec factorial n =
  match n with
  | 0 -> 1
  | _ -> n * factorial (n-1)










Lecture 2











(*

Announcements:
Office now Doolan 220. If not there,
try Doolan 200.

No office hours tomorrow.

Hw1: due next friday. Post by saturday

Recap:
  REPL.  Good for programming on the fly

Pattern matching:
*)

let rec fact n =
  match n with
  | 0 -> 1
  | _ -> n * fact (n-1)
;;

(* 

Patterns:
 P ::= num | _ 

Pattern matchin is "good style" in ocaml
- preferred to if-then-else
- declarative: focus on "what" (the pattern) over
  "how" (how to match)
- match expressions are easier to read and understand, update, etc.
- the interpreter can do fancy static analysis

*)

let not b = if b then false else true;;
let not b = 
  match b with
  | true -> false
  | false -> true
;;

let rec parity n =
  match n with
  | 0 -> "even"
  | 1 -> "odd"
  | _ -> parity (n-2)
;;

let rec parity n =
  match n with
  | 0 -> "even"
  | 1 -> "odd"
  | x -> if x > 5 then "Gotta go lower fam." else parity (n-2)
;;



let rec parity n =  (* the case | 1 -> "odd" is redundant. If n evaluates to 1, then *)
  match n with    (* the second case will always be chosen *)
  | 0 -> "even"
  | _ -> parity (n-2)
  | 1 -> "odd"
;;

(* Pay attention to warnings from the compiler about exhaustiveness and redundancy in
  your pattern match expressions.   
 *)

(* Lists

  In imperative languages, arrays the most commonly used
  built-in data structure. They play well with loops.

  Instead of arrays, we're going to use lists in OCaml.
  Lists play well with recursion.

  Lists are defined by induction.
  - base case: empty list []
  - inductive case: "cons" operator  E1::E2

Pattern matching on lists.

 *)

let rec sumList lst =
  match lst with
  | [] -> 0                       (*  lst = []  *)
  | hd::tl -> hd + sumList tl;;   (*  lst = hd::tl  *)

(* 

  Three new kinds of pattern:

  nil pattern []
  cons pattern   P1 :: P2
  variable/name pattern 
    hd, tl

nil pattern matches one value: []

cons pattern P1::P2 matches _any_ cons 
cell V1::V2 (V1 and V2 are values), if
P1 matches V1 and P2 matches V2.

variable/name pattern matches any value (just like _)
AND it assigns that name to that value in the branch

*)

let is123 lst =
  match lst with
  | 1::(2::(3::[])) -> true
  | _ -> false

(* is123 (1::(2::(3::(4::[]))));; *)

(* 

1. is (1::(2::(3::(4::[])))) a cons value?
2. match 1 against 1. matches.
3. match (2::(3::[])) against (2::(3::(4::[])))
   is (2::(3::(4::[]))) a cons value? yes.
4. match 2 against 2. matches.
5. match (3::[]) against (3::(4::[])).
   is (3::(4::[])) a cons value? yes.
6. match 3 against 3.
7. match the tail pattern against the tail value.
   match [] pattern against (4::[]) value.
   is (4::[]) = []?
   fail.

pattern match fails.   

*)

Lecture 3






(* Office hours today in Doolan 200 or Keck lab.
 *)

 (* Pattern matching

  match E with
  | P1 -> E1
  | P2 -> E2
  | ...
  | Pn -> En

  1. evaluate E to some value, V
  2. match V against the patterns, in top to bottom order
  3. If V matches P1, then evaluate E1. The value of E1 is the
     value of the whole thing.
     Otherwise, continue to the next pattern.
  4. If V matches no patterns, we get a pattern match error.
     The match expression has no value.

    Patterns we've seen so far:

    P ::= _        (wildcard, matches every value)
        | []       (nil pattern, matches "nil" [], the empty list)
        | P1::P2   (cons pattern, matches non-empty lists if
                    the first element matches P1, and the rest of the list
                    matches P2)
        | X        (variable name pattern, matches all values)    
        | C        (constants)

    C ::= 0 | 1 | 2 | 123 | ...
        | true | false
        | [] | ...

    X ::= <variable names>


Global vs Local let:

  let X = E;;    is a global or top-level expression.
                 can be used at the REPL, or in a file 
                 OUTSIDE of any other expression. After
                 0 or more other top-level expressions.
                 the bindings are available for the rest of
                 the file or REPL session

  let X = E1 in E2
                 is a local expression (or just an expression).
                 can be used anywhere an expression is expected
                 the bindings is only available in (or local to) E2


(Global Expressions)
G ::= let X = E;;          (global let declaration)
    | let X1 X2 = E;;      (global function declaration)
    | let rec X1 X2 = E;;  (global recursive function decl)
    | #use "foo.ml";;      (evaluate the file foo.ml)
    | E;;
    | G G                  (sequence of global expressions)

(local expressions)
E ::= E OP E       (binary operations)
    | E E          (function application)
    | C            (constants)
    | X            (variables)
    | match E1 with 
      '|' P1  -> E1
      '|' P2  -> E2
      '|' ...
      '|' Pn  -> En
    | []
    | let X = E1 in E2
    | if E1 then E2 else E3

OP ::= + | - | * | / | mod | ^ | ...

  *)


(* Write a function that returns every other element of a list.

   everyOther [1;2;3;4;5] = [1;3;5]

 *)

(* OCaml's type  "int list" is analogous to Java's "List<Integer>" 
   A list of integer values.
 *)

let rec everyOther (l : int list) : int list =
  match l with
  | []                     -> []
  | (hd::[])   (* [hd] *)  -> l (* [hd], (hd::[]) *)
  | (x::_::tl)             -> x :: everyOther tl

(* we can leave the type annotations off, and get
   everyOther to work for string lists as well *)

let rec everyOther l =
  match l with
  | []                     -> []
  | (hd::[])   (* [hd] *)  -> l (* [hd], (hd::[]) *)
  | (x::_::tl)             -> x :: everyOther tl


(* Same behavior, because the patters don't overlap *)

let rec everyOther l =
  match l with
  | (x::_::tl)             -> x :: everyOther tl
  | (hd::[])   (* [hd] *)  -> l (* [hd], (hd::[]) *)
  | []                     -> [];;

(* Nested lets *)

(let y = 5 in
 let x = y+1 in
 let z = x+1 in
 x + y + z);;

(* Same as *)
(let y = 5 in
 (let x = y+1 in
  (let z = x+1 in
   x + y + z)))

(* another common data type: tuples *)
let tup = (1, "hi", 2.0, 4+5)

(* tuples are sequences of values, like lists.

   lists have unboundedly many elements, all of the same type
   tuples have a fixed number of elements, but of possibly different type

 *)












Lecture 4









(*

More about tuples.

*)

let tup = (1, "hi", 2.0, 4+5);;

(* 

New cases for our grammar:

E ::= ... | (E1, E2, ..., En)
P ::= ... | (P1, P2, ..., Pn)

*)

(* 

Example: zip

  zip ([1;2;3], [4;5;6]) = [(1,4); (2,5); (3,6)]

 *)

let rec zip args = 
  match args with
  | (_ , []) -> []
  | ([],  _) -> []
  | (h1::t1 , h2::t2) -> (h1,h2) :: zip (t1,t2)

(* Can we implement zip with 2 cases only? *)

(* Example: unzip

  unzip [(1,4); (2,5); (3,6)] = ([1;2;3], [4;5;6])

*)

let rec unzip args =
  match args with
  | []        -> ([],[])
  | [(x,y)]   -> ([x],[y])
  | (x1,y1)::(x2,y2)::rest  ->
     let (xs,ys) = unzip rest in 
     (x1 :: x2 :: xs, y1 :: y2 :: ys)  

(* Simplify *)

let rec unzip args =
  match args with
  | []        -> ([],[])
  | (x,y)::rest  ->
     let (xs,ys) = unzip rest in 
     (x :: xs, y :: ys)  

(* Index 

   index (3, [1;2;3;4;5]) = 2
   index (6, [1;2;3;4;5]) = -1

 *)

let rec index (x,l) =
  match l with
  | []     -> -1
  | hd::tl -> if hd=x then 0 else 1 + index(x,tl)
;;

(*
   Try it out:

# index (3, [1;2;3;4;5]);;
- : int = 2
# index (6, [1;2;3;4;5]);;
- : int = 4

What happened?

     index(6, [1;2;3;4;5]) 
   = 1 + index(6, [2;3;4;5]) 
   = 1 + (1 + index(6, [3;4;5])) 
   = 1 + (1 + (1 + index(6, [4;5]))) 
   = 1 + (1 + (1 + (1 + index(6, [5]))))
   = 1 + (1 + (1 + (1 + (1 + index(6, [])))))
   = 1 + (1 + (1 + (1 + (1 + (-1))))
   = 4

How do we fix this? Can use a helper function.   
 *)


(* Fixing the 6 case *)

(* index: return the index of the first occurrence of x in l, or -1
    if x is not in l.
  *)
let rec index (x,l) =
  (* helper: return i + the index of the first occurrence of x in l, or -1
      if x is not in l.
   *)
  let rec helper (i,x,l) =
    match l with
    | []     -> -1
    | hd::tl -> if hd=x then i else helper (i+1, x, tl)
  in helper (0, x, l)  
;;

(* Simplify a bit: helper can refer to index's x directly. *)

let rec index (x,l) =
  let rec helper (i,l) =
    match l with
    | []     -> -1
    | hd::tl -> if hd=x then i else helper (i+1, tl)
  in helper (0, l)  
;;

(* A bit simpler: *)

let index (x,l) =
  let rec helper (i,l) =
    match l with
    | []                -> -1
    | hd::_ when hd=x   -> i
    | _ ::tl            -> helper (i+1, tl)
  in helper (0, l)  
;;

(* Equality    E1 = E2

  You can compare equality of any two elements *of the same type*.

# [1;2;3] = (1::2::3::[]);;
- : bool = true
# (1,2,3) = (4,5,6);;
- : bool = false
# (1,2,3) = [1;2;3];;    

Characters 10-17:
  (1,2,3) = [1;2;3];;
            ^^^^^^^
Error: This expression has type 'a list
       but an expression was expected of type int * int * int
# '0' = "0";;
Characters 6-9:
  '0' = "0";;
        ^^^
Error: This expression has type string but an expression was expected of type
         char
# '0' = '0';;
- : bool = true

A string is *not* a list of characters (as in some other languages):

# "0" = ['0'];;
Characters 6-11:
  "0" = ['0'];;
        ^^^^^
Error: This expression has type 'a list
       but an expression was expected of type string

*)

(* Functions 

Functions are *first-class* -- they are just data. 
Can be treated like any other value -- ints, lists, tuples, etc.


# let twice (f, x) = f (f x);;
val twice : ('a -> 'a) * 'a -> 'a = <fun>
# let square x = x * x;;
val square : int -> int = <fun>
# twice (square, 5);;
- : int = 625
# twice (square, 2);;
- : int = 16

*)









Lecture 5










(* 

First-class functions.
- you can treat them like any other type of value.
- assign them to variables
- pass them as arguments to other functions
- return them from other functions.
- store them in lists, tuples, etc.

Higher-order function is a function that takes another function as its input.

HOFs are good for:
- expressiveness.
  - "get more done with less code"
  - makes it easier to read and write code.
- reduction of boilerplate

*)

let inc x = x+1;;

(* Anonymous functions *)
let inc = (fun x -> x+1);;

(* 

let inc x = x+1;;

is just an abbreviation for

let inc = (fun x -> x+1);;

*)

(* Define a function that adds 1 to the first component of a pair of 
   ints
 *)

let incFirst = fun p ->
  match p with
  | (x,y) -> (x+1,y)

(* Define a function that appends "!" to the first component of a pair 
   of strings.
*)

let exclaimFirst = fun p ->
  match p with
  | (x,y) -> (x ^ "!", y)

(* Boilerplate! How can we get rid of it?

  Higher order function!

  Parameter: what to do to the first component.
 *)

let applyToFirst = fun (f, p) ->
  match p with
  | (x,y) -> (f x, y)
;;

(* Define incFirst and exclaimFirst using applyToFirst *)
let incFirst = fun p -> applyToFirst ((fun x -> x+1), p);;
let exclaimFirst = fun p -> applyToFirst ((fun x -> x^"!"), p);;

(* Passing multiple arguments. *) 

let add = fun (x,y) -> x+y;;

(* Using higher-order functions: the ability to return one function
   from another.
 *)
let add = fun x -> (fun y -> x+y);;

let inc = add 1;;

(*
  inc = (fun x -> (fun y -> x + y)) 1
        = (fun y -> 1 + y)
 *)

(* Let's use higher-order functions to simplify applyToFirst *)

let applyToFirst = fun f -> fun p ->
  match p with
  | (x,y) -> (f x, y)
;;

(* Doing this allows us to pass the arguments to applyToFirst
  one at a time.

 *)

let incFirst = fun p -> applyToFirst (fun x -> x+1) p;;
(*                      ^^^^^^^^^^^^^^^^^^^^^^^^^^^ <- This is a function *)

let incFirst = applyToFirst (fun x -> x+1);;
let exclaimFirst = applyToFirst (fun x -> x^"!");;

(*
   incFirst (1,2) = applyToFirst (fun x -> x+1) (1,2)
*) 

(* This style of functions taking arguments one-at-a-time is 
  preferred in ocaml.

  It has syntactic support:

    Instead of this:  (fun x -> (fun y -> x+y))
    We can write:     (fun x y -> x+y)
 *)

(* Example: define incList that adds 1 to each element of a list of
  ints.
 *)

let rec incList = fun l ->
  match l with
  | []     -> []
  | x::xs  -> (x+1) :: incList xs

(* Define exclaimList that appends "!" to each string in a list of
   strings.
 *)

let rec exclaimList = fun l ->
  match l with
  | []     -> []
  | x::xs  -> (x^"!") :: exclaimList xs

(* Boilerplate! How can we remove it? *)

let rec applyToEach = fun f l ->
  match l with
  | []     -> []
  | x::xs  -> (f x) :: applyToEach f xs
;;

let incList = applyToEach (fun x -> x+1);;
let exlaimList = applyToEach (fun x -> x^"!");;

(* applyToEach is usually called map.
   similar to "forEach" in other languages, but map returns
   a new list.
 *)

let map = applyToEach;;

(* Increment each int in a list of lists of ints.

   JavaScript:
  var ls = [[1,2,3], [4,5,6], [7,8,9]];
    for(var i = 0; i < ls.length; i++) {
    for(var j = 0; j < ls[i].length; j++) {
        ls[i][j]++; 
    }
    }

   Let's do this in OCaml.
 *)

let l = [[1;2;3]; [4;5;6]; [7;8;9]];;
let incListList = map (map (fun x -> x + 1));;








Lecture 6








(* 

Higher-order functions.
- Give a name to a common pattern of function.
- Argument is a *piece of code* that instantiates the pattern.
  (What is different between two instances of the pattern.)
  By *piece of code*, we mean a function.
- Some examples:
  - Do something to first the component of a pair.
  - Do something to every element of a list.

 *)

(* Functions can be returned from other functions *) 

let returnsAdd () =
  (let add (x,y) = x+y
     in add);;


(* The above is shorthand for: *) 

let returnsAdd () =
  (let add = fun (x,y) -> x+y
     in add);;

(* Since we are just return add, don't need to give it a name. *)

let returnsAdd () =
  (fun (x,y) -> x+y);;

(* The inner (returned) function can refer to parameters of the
   outer function *)

let incBy m = fun n -> m+n;;
let incBy5 = incBy 5;;    (* captures m = 5 *)
let incBy6 = incBy 6;;    (* captures m = 6 *)

(* defining m later doesn't affect incBy5 or incBy6 *)
let m = 1;;
incBy5 6;;    (* still 11 *)

(* can also capture globally defined names *)

let x = 1;;
let incByX = fun y -> x+y;;
let x = "hello";;

(* can redefine x, incByX's definition of x doesn't change. *)

(* Back to the program of removing boilerplate. *)

let rec removeNegatives = fun l ->
  match l with
  | []    -> []
  | x::xs -> let xs' = removeNegatives xs in
             if x < 0
         then xs'
         else x :: xs'
;;

let rec removeEmpties = fun (l : string list) ->
  (* String.length : string -> int *)
  match l with
  | []    -> []
  | x::xs -> let xs' = removeEmpties xs in
             if String.length x = 0
         then xs'
         else x :: xs'
;;

let rec removeBy = fun f l -> (* fun f -> fun l -> *)
  match l with
  | []    -> []
  | x::xs -> let xs' = removeBy f xs in
             if f x
         then xs'
         else x :: xs'
;;

(* Use removeBy to implement removeNegatives and removeEmpties *)
let removeNegatives = removeBy (fun x -> x < 0);;
let removeEmpties = removeBy (fun s -> s = "");;

(* This idea of removing elements of a list according to some 
   predicate is commonly used in functional programming. 

   OCaml has this built-in: List.filter.
   NOTE: Its argument tells which elements to keep.
   *)

(* sum the elements of a non-empty list *)
let rec sumList = fun l ->
  match l with
  | [x]    -> x
  | hd::tl -> hd + sumList tl
;;

let rec prodList = fun l ->
  match l with
  | [x]     -> x
  | hd::tl  -> hd * prodList tl
;;

let rec combineInts = 
  fun (f : int -> (int -> int)) (l : int list) ->
  match l with
  | [x]      -> x
  | hd::tl   -> let tlCombined = combineInts f tl in 
                f hd tlCombined
;;

let sumList = combineInts (fun x y -> x + y);;
let prodList = combineInts (fun x y -> x * y);;

let sumList = combineInts ( + );;
let prodList = combineInts ( * );;

(* Concatenate a non-empty list of strings, with intermediate spaces *)
let rec combineAll = 
  fun f l ->
  match l with
  | [x]      -> x
  | hd::tl   -> let tlCombined = combineAll f tl in 
                f hd tlCombined
;;

let concatWords = combineAll (fun x y -> x ^ " " ^ y)

(*
combineAll (fun x y -> x - y) [1;2;3];;

  1 - (2 - 3) = 2

  combineAll f [x;y;z]
= f x (combineAll f [y;z])
= f x (f y (combineAll f [z]))
= f x (f y z)

combineAll is right-associative.

*)

(* Define a left-associative version of combineAll *)
let rec combineAllLeft = fun f l ->
 (* Hint: start with subtractList [1;2;3] = (1 - 2) - 3. *)
 (* Preview for HW3. *)









Lecture 7














(*
- Next Tuesday: Quiz 1.
- 35 minutes. 12pm to 12:35pm.
- Topics
  - Everything up till this Thursday is fair game.
  - Functional programming concepts
    - Recursion over loops (iteration)
    - Can't update (reassign) variables.
    - Higher-order functions. Functions are data (first-class). 
  - OCaml. Mainly reading. Maybe some small writing.
  - Local vs Global let
  - Pattern Matching
  - Recursive Functions
  - First-Class Functions
    +functions ARE *firstClass* -- they are just data. 
      can be treated like any other value -- ints, lists, tuples, etc.
  - Lists and tuples
  - Rebinding names vs updating variables

Last week: higher-order functions.
- Working with pairs.
- Patterns for working with lists.
  - apply a function to each element of a list
  - select some of the elements of a list
  - combine all the elements into an aggregate value.
- Demonstrate the power of first-class functions.
  - Reduces boilerplate code, making programs:
    - less tedious to write
    - easier to read
    - easier to maintain and evolve. 
- These ideas scale gracefully
  - More interesting and complex forms of boilerplate
  - other kinds of languages, including imperative and OO
  - especially good for distributed and parallel programming
    - parallel: utilizing multiple cores on a single CPU (or multiple CPUs on a single machine)
    - distributed: utilizing multiple machines on a network
    - Java8 parallel stream API: automatically distributes map/filter/reduce 
      over multiple cores.
    - map and reduce are at the core Google's distributed computing infrastructue. It's called Map/Reduce
      Open source version called Hadoop.
- Operations similar to map, filter, and reduce are the fundamental operations provided by database
  query languages.


*)


(* 

- So far, we've used Ocaml's built types (tuples, lists, ints, strings, ...)
- This is sufficient for anything, but there are better ways.

- Abstract data types: are a new type, with some associated operations.
  - The internals of the type are hidden from the client code.
  - Forces the clients to use the interface (operations provided).
  - Clients have to follow the original intent of the type.
    - Can maintain some *invariants* in the operations.
  - Allows us to ignore the implementation details. Think at a level.
  - Manages complexity
    - of a large code base
    - of developing in a large team
  - Key idea: information hiding.

- User-defined types:
  - C: struct, enum, union
  - Java/C++: classes/ojects

- Ocaml supports similar ideas, but different in several ways.
  - They're designed to be compatible with other features of OCaml.
    - they support pattern matching.
    - they promote immutability


*)


(* Enum-like *)
type sign = Pos | Neg | Zero;;

(* 

Pos, Neg, Zero are the three values of type sign.
can't add any more values.

Rules:
 - type names like "sign" begin with a lower-case letter.
 - the values (called constructors) begin with an upper-case letter.

What can we do these things?

- compare them for equality
- Hint: they are first-class values
- pattern match.
*)

let signToInt s =
  match s with
  | Pos -> 1
  | Neg -> -1
  | Zero -> 0
;;  

(*

Exhaustiveness and redundancy checks work for user-defined types too! 
*)

(* Constructors can have associated fields too *)


type point = Cartesian of (float * float);;

(* 

this is like a struct in C, except there are no field names.

*)

(* 

Syntax:  <ConstructorName> of <type>

point is distinct from (float * float).
- you can't use one where the other is expected.



*)

let negate (p : point) : point = 
  match p with
  | Cartesian (x,y) -> Cartesian (-. x, -. y)
;;

let negate (p : point) : point = 
  let Cartesian (x,y) = p in
  Cartesian (-. x, -. y)
;;

let negate (Cartesian (x,y)) = 
  Cartesian (-. x, -. y)
;;

(* 

what's the C equivalent of this:
 *)

type point1 = float * float;;

(* 

introduces a type alias, a new name for another type.

equivalent in C: typedef

 *)

(*

Recall: Ocaml does not have null pointers.
- this is a good thing. null pointers are the bane of imperative programming.
- why do langauges like C/C++/Java/Python/JS/... have something like null?
  - Tony Hoare invented the concept of a null or nothing or undefined value that
    is included in every type. In his language Algol (predecessor of C) in 1960s.

    Square x = null;
    Triangle x = null;

  - It's been included in just about every imperative language since.
  - Caused untold many bugs.
    - They can crash program, or worse
    - These bugs are subtle, and difficult to diagnose and fix.
  - Tony Hoare calls this his "billion dollar mistake"
  
  - Can we live without nullable pointers/references?

 *)

type nullableInt = Null | NonNull of int;;

(* Increment a nullableInt *)
let incNullableInt n = 
  match n with 
  | Null -> Null
  | NonNull m -> NonNull (m+1)
;;  

(* 

Is this any better than nullable references/pointers?

- the types nullableInt and int are distinct.
- you can + on a nullableInt.
- the type checker forces you to match.

 *)

let incNullableInt n = 
  match n with 
  | NonNull m -> NonNull (m+1)
;;  

(* Exhaustiveness warning *)

let addNullables m n =
  match m,n with
  | NonNull j, NonNull k -> NonNull (j+k)
  | _, _ -> Null
;;










Lecture 8









(*

Last time: user-defined data types.


*)

(* enum-style: a finite number of values in a new type *)

type color = Red | Blue | Green;;

(* struct-style: one constructor, mulitple fields *)

type colorPoint = ColorPoint of (float * float * color);;

(* typedef-style: a new name (alias) for an existing type *)

type colorPoint2 = (float * float * color);;

(* we can combine multiple constructors (enum) and fields *)

type nullableInt = Null | NonNull of int;;

(*
Advantages of modelling nullable values:
  - type system helps keep track of when you need to do null-checking.
 *)

let incNullableInt (ni : nullableInt) : nullableInt =
  match ni with
  | Null -> Null
  | NonNull i -> NonNull (i+1)
;;

let doubleNullableInt (ni : nullableInt) : nullableInt =
  match ni with
  | Null -> Null
  | NonNull i -> NonNull (i*2)
;;

let updNullableInt (f : int -> int) (ni : nullableInt) : nullableInt =
  match ni with
  | Null -> Null
  | NonNull i -> NonNull (f i)
;;

let incNullableInt = updNullableInt (fun x -> x+1);;
let dblNullableInt = updNullableInt (fun x -> x*2);;

(*

Which kind of higher-order function is updNullableInt?
  a) the kind that takes a function as input
  b) the kind that returns a function as output

  Both!
*)

(* How can we convert a nullableInt to some other type, say string?
   - we need a string for the Null nullableInt
   - we need a function that converts an int to a string.
 *)

let string_of_nullableInt ni =
  match ni with
  | Null -> "Null nullableInt"
  | NonNull i -> string_of_int i
;;

let fromNullableInt (ifNull : 'a) (ifNonNull : int -> 'a) (ni : nullableInt) : 'a = 
   match ni with
   | Null -> ifNull
   | NonNull i -> ifNonNull i
;;

let string_of_nullableInt = fromNullableInt "Null nullableInt" string_of_int;;
let int_of_nullableInt = fromNullableInt (-5000) (fun x -> x);;

(* What if we want a nullableString type?

  We could copy/paste the definition of nullableInt,
  and all of its higher-order functions, and update the field type.

   Bad. Boilerplate.

   Instead, use a polymorphic data type.

 *)

type 'a nullable = Null | NonNull of 'a;;

type nullableInt = int nullable;;
type nullableString = string nullable;;

let updNullable f n =
  match n with
  | Null -> Null
  | NonNull x -> NonNull (f x)
;;

let fromNullable ifNull ifNonNull n =
   match n with
   | Null -> ifNull
   | NonNull x -> ifNonNull x
;;

(* This nullable type is widely used in FP. In OCaml, it's called option.
    Null is called None
    NonNull is called Some.

    Used for reporting errors, modelling null, etc.

 *)

let rec nth (n:int) (l:'a list) : 'a option = 
  match (n,l) with
  | (_, []) -> None
  | (0, hd::_) -> Some hd
  | (_, _::tl) -> nth (n-1) tl
;;


(* Recursive types *)
type 'a myList = Nil | Cons of ('a * 'a myList);;


(* Recursive because myList occurs in its own definition. *)

(* [1;2]  = (1::2::[]) 

    as a myList:

    Cons (1, Cons(2, Nil))

    This is exactly how Ocaml's built-in lists are represented.
    [] and :: are just syntactic sugar (shorthand)


 *)

let rec myMap (f : 'a -> 'b) (l : 'a myList) : 'b myList =
  match l with
    Nil         -> Nil
  | Cons(hd,tl) -> Cons(f hd, myMap f tl)
;;

(* 

How would you implement a singly-linked list in C/C++/Java?

class Node { 
  int elem;
  Node next;
}

  Empty lists are usually represented as a null Node.

*)

type 'a node = Node of ('a * ('a node) option);;
type 'a linked_list = ('a node) option;;

let rec mapLL (f : 'a -> 'b) (l : 'a linked_list) : 'b linked_list =
  match l with
  | None -> None
  | Some (Node(hd,tl)) -> Some(Node(f hd, mapLL f tl)) 
;;

(* Can we convert a linked_list to a myList?
   Can we convert a myList to a linked_list?
 
    Yes! They are effectively the same types.

    Nil = None
    Cons(hd,tl) = Some(Node(hd,tl))
 
   In fancy math lingo, they are called isomorphic.
 *)

(* Here's a thing *)

type tree = Leaf
          | Node of (tree * int * tree);;n

(* This thing is a binary tree *)

let t1 = Node (Leaf, 1, Leaf);;
let t3 = Node (Leaf, 3, Leaf);;
let t123 = Node (t1, 2, t3);;

(* count the number of nodes in a tree *)
let rec size (t : tree) : int =
  match t with
  | Leaf -> 0
  | Node(t1,i,t2) -> size t1 + size t2 + 1
;;  

(* preorder traversal: convert a tree to list in pre-order 
    The list should contain all the ints in tree, in pre-order
 *)

let rec preorder t : int list =
  match t with
  | Leaf -> []
  | Node(t1,i,t2) -> i:: (preorder t1) @ (preorder t2)

(* in-order traversal: convert a tree to list in in-order 
    The list should contain all the ints in tree, in in-order
 *)

let rec inorder t : int list =
  match t with
  | Leaf -> []
  | Node(t1,i,t2) -> (inorder t1) @ (i :: (inorder t2))
;;

(* Definition: a binary tree is a binary search tree if:
  for every Node(l,i,r)
    every element in l is less than i
    every element in r is greater than i

 *)

(* Insert into a binary search tree *)


let rec insert i t =
  match t with
  | Leaf -> Node (Leaf, i, Leaf)
  | Node(t1,j,t2) -> if i<j then Node(insert i t1, j, t2)
                     else if i>j then Node(t1, j, insert i t2)
                   else t
;;

(* We just implemented a *fast* sort algorithm. 

   Insert all elements of a list into a BST.
   in-order traversal outputs them in sorted order.
 *)                  

let rec treeSort : int list -> int list = 
  fun l ->
  let t = combineAll insert Leaf l in inorder t
;;











Lecture 9














type tree = Leaf
          | Node of (tree * int * tree);;


(* in-order traversal: convert a tree to list in in-order 
    The list should contain all the ints in tree, in in-order
 *)

let rec inorder t : int list =
  match t with
  | Leaf -> []
  | Node(t1,i,t2) -> (inorder t1) @ (i :: (inorder t2))
;;

(* Definition: a binary tree is a binary search tree if:
  for every Node(l,i,r)
    every element in l is less than or equal i
    every element in r is greater than i

 *)

(* Insert into a binary search tree *)


let rec insert i t =
  match t with
  | Leaf -> Node (Leaf, i, Leaf)
  | Node(t1,j,t2) -> if i<=j then Node(insert i t1, j, t2)
                     else Node(t1, j, insert i t2)
                   
;;

(* We just implemented a *fast* sort algorithm. 

   Insert all elements of a list into a BST.
   in-order traversal outputs them in sorted order.
 *)                  

let rec combineAll f x l =
  match l with
      [] -> x
    | hd::tl -> f hd (combineAll f x tl)
;;    

let rec treeSort : int list -> int list = 
  (* try implementing this using combineAll to insert all the elements
     into the BST. Then use in-order
     *)
  fun l ->
  let t = combineAll insert Leaf l in
  inorder t
;;

(* Heterogeneous lists.  

int -> intOrString
string -> intOrString

*)

type intOrString = Int of int | String of string;;

let l = [Int 4; String "hello"; Int (-12)];;

(* How is this different from heterogeneous lists in a dynamically typed language?

   This list is still homogeneous.  

   intOrString is distinct from int and string. The type system forces
   us to do a check. 

   How do we check whether an intOrString is an int or a string?
   - pattern matching

   can we use map/applyToEach?
     no, because it preserves the number of elements
   can we use filter?
     no, becuase it preserves the type of the elements
   can we use combineAll/fold_right? 
     yes, combineAll allows to change both the type and the number of elements
 *)

let lInts = combineAll (fun hd tlInts -> 
                        match hd with
                          Int i -> i :: tlInts
                        | _     -> tlInts) [] l
;;

(* What if we want a boolOrString, or a boolOrIntOrString?
   Do we have to redefine the user-defined type in each case?
 *)

type ('a, 'b) choice2 = C1of2 of 'a | C2of2 of 'b
type ('a, 'b, 'c) choice3 = C1of3 of 'a | C2of3 of 'b | C3of3 of 'c

(* Scope

The key question is to know for each *reference* of a particular name,
which *definition* of that name is currently *in scope*.

There are different ways to declare/define a variable, with
different scopes.

 *)

let x = 3;;
let double n = n * 2;;

(* x and double are globally defined. we can reference them for
   the rest of the REPL session or file.
 *)

(* n is local to double. it's in scope within double's definition,
   and out of scope everywhere else.

   *)

(* Ocaml keeps track of an *environment* in which each expression should
   be evaluated. The environment maps the names in scope to their *values*.

   Some notation for environments.
   {}                     empty environment
   {x = 32, y = "foo" }   environment containing bindings for x and y

 *)





Lecture 10












(* Static Scoping
  - What does it mean
    - Each variable reference refers to its *nearest* *enclosing* definition in the source code
  - What if there is no nearest enclosing definition?
    - Thats an unbound variable error

  - let x = E1 in E2
      the definition "e = E1" encloses E2

  - (fun x -> E)
      the formal parameter x encloses e

  - To implement static scoping with functions we use closures
    A closure is the value of a function
    It stores the environment from the time the function was evaluated
    The environment contains the statically-scoped bindings
    We use it to evalute the function body. It gives values to variables in the function.

    let x = 45 in
    let f = (fun y -> x + y) in
    let x = 7 in
    f x
  
  Evaluates to (in one step):
    - evaluate the definition of x, extend the current environment
      evaluate the body in the extended environment

    let f = (fun y -> x + y) in     Current env : (x=45)
    let x = 7 in
    f x 




 *)











 Lecture 11











 (*

Takeaways from HW4/5:
  - understand a language by implementing it.
  - Functional languages are really good at this:
    - OCaml's user-defined types are good for representing syntax.
    - Pattern matching, recursion are good for lots of
      operations on syntax.

let rec eval_expr (env : env) (e : expr) : value option =
  match e with
  | Int i -> Some (VInt i)
  | Var x -> get x env
  | BinOp (e1, op, e2) ->
    (match (eval_expr env e1, eval_expr env e2) with
     | (Some (VInt i1), Some (VInt i2)) ->
        (match op with
         | Plus -> Some (VInt (i1 + i2))
         | Gt -> Some (VBool (i1 > i2))) 
     | _ -> None)
  ;;      

Last time:
  static scoping.
  - what does it mean?
  - each variable reference refers to its *nearest* *enclosing* definition
    in the source code.
  - what if there is no nearest enclosing definition? 
    that's a unbound variable error.

  - let x = E1 in E2
      the definition "e = E1" encloses E2.

  - (fun x -> E)
      the formal parameter x encloses E.

  - To implement static scoping with functions, we use closures.
    A closure is the value of a function. 
    It stores the environment from the time the function was evaluated
    The environment contains the statically-scoped bindings.
    We use it to evaluate the function body. It gives values to
    variables in the function body.

    let x = 45 in
    let f = (fun y -> x + y) in
    let x = 7 in
    f x

    The x in the function refers to "let x = 45"

    let x = 45 in
    let f = (fun y -> x + y) in               Current env: {}
    let x = 7 in
    f x

 evaluates to (in 1 step):
   - evaluate the definition of x, extend the current environment,
     evaluate the body in the extended environment

    let f = (fun y -> x + y) in      Current env : {x=45}
    let x = 7 in
    f x

 evaluates to:
   - value of f is the closure (fun y -> x + y){x=45}

    let x = 7 in       Current env: {f = (fun y -> x + y){x=45},
    f x                              x = 45}

 evaluates to:
    f x        Current env: {f = (fun y -> x + y){x=45},
                             x = 7}

evaluates to:
  - evaluate the expression "f". the value must be a closure
    extract the environment from the closure
    extend it with the binding for the formal parameter
      "formal parameter : value of actual parameter"
    evaluate the body under the extended environment

  - value of f is (fun y -> x + y){x=45}
  - formal parameter is "y"
  - actual parameter is the value of "x" under {f = (fun y -> x + y){x=45}, x=7}
  - updated environment is {x=45,y=7}

  result is:
    x + y    under {x=45, y = 7}

After evaluating the function body, discard the extended environment 

  result is:
    52
*)


(* Static scoping is required for passing arguments one-by-one,
    instead of all at once in a tuple.

    Name for passing arguments 1-by-1: Currying

    Named after Stephen curry.
      Wait. I mean Haskell Curry. A famous logician.
      The programming language haskell is named after him.

    Env: {}

  let add x y = x + y in
  let addTwo = add 2 in
  let x = 12 in
  addTwo x

 Evaluates to:
 
  Env: {add = (fun x -> fun y -> x+y){}}

  let addTwo = add 2 in
  let x = 12 in
  addTwo x

 Next: evaluate (add 2)
    1. evaluate "add" to the closure (fun x -> fun y -> x+y){}
    2. evaluate the actual parameter 2.
    3. extend the closure environment with formal=actual
    4. evaluate the function body under the extended environment
       (fun y -> x+y)   under {x = 2}
    5. the value is (fun y -> x+y){x = 2}

Go back to evaluating the let. Extend the current environment 
  {add = (fun x -> fun y -> x+y){}}
with the value of the definition of addTwo:

   Env : {add = (fun x -> fun y -> x+y){},
          addTwo = (fun y -> x+y){x = 2}}

   let x = 12 in
   addTwo x

Evaluates to
   
    Env : {add = (fun x -> fun y -> x+y){},
           addTwo = (fun y -> x+y){x = 2},
           x = 12}

   addTwo x

Evaluates to:
    1. evaluate "addTwo" to the closure (fun y -> x+y){x = 2}
    2. evaluate the actual parameter x to its value 12.
    3. extend the closure environment with formal=actual
    4. evaluate the function body under the extended environment
         (x + y)   Env {x = 2, y = 12}
    5. the value is 
          14

Good exam questions:
  - trace the evaluation steps for an expression involving functions,
    let/in, etc.
  - what is a closure?
  - what is currying?
  - why does currying require static scoping?
  - why is steph curry so good?

Consider the environment:

    Env : {add = (fun x -> fun y -> x+y){},
           addTwo = (fun y -> x+y){x = 2},
           x = 12}

  We have two bindings for x.
  One is in currently in scope: x=12
  The other is out of scope in the current expression, but
  in scope for the body of addTwo

  So there is a concept of *lifetime* of variable bindings.
    - how long the binding is in scope somewhere

In this environment, x is out of scope, but still live.

  {add = (fun x -> fun y -> x+y){},
   addTwo = (fun y -> x+y){x = 2}}

In this environment, x = 12 is in scope, x = 2 is out of scope, but live.

   {add = (fun x -> fun y -> x+y){},
    addTwo = (fun y -> x+y){x = 2},
    x = 12}

A variable definition (x = ...) can be in scope or out of scope.
A variable is in scope if it has a definition that is in scope.
A variable is live if it has a live definition.


Good exam questions:
 - which variables are in scope after evaluating some expression?
 - which are live?

let x = 1 in
let x = x+1 in 
x+2

When we evaluate (x+1), x=1 is live and in-scope
When we evaluate (x+2), x=2 is live and in-scope, x=1 is dead and out of scope.

let x = 1 in
let f = fun y -> x + y in
let x = x+1 in
x+2

When we evaluate (x+2), x=2 is live and in-scope, x=1 is live (in the closure of f) and out of scope.

 *)













Lecture 12












(* 
Last time: we began talking about types and type systems.
  - static vs dynamic type checking
    pros/cons:
      -static
        - pros: early detection of errors (before you run the program),
                managing complexity of large projects
        - cons: often cryptic error messages, conservative, rejects your way.
                Can lead to code duplication
      -dynamic
        - pros: good for rapid prototyping, experimentation, small projects
        - cons: dynamic type errors are hard to debug

  String vs. Weak typing
    - strong: when you access a variable of some type
    - strongly types means the language *never* allows us to:
      - use a value of one type as if it were a value of another type
        e. g. adding two booleans
              adding two strings
              adding two binary trees
              string concatenation of binary trees
      - access/manipulate the internal representation (memory bits) of a type.
        "enforcing each type's abstraction"

      - OCaml is strongly typed
      - Python is strongly typed, even though it is dynamically typed.

    - weakly typed means
      - we can do some of the things prevented by a strongly typed langauge.
      - allows some errors to go undetected
        - after these errors occur, the program is in a "garbage state"
          no guarantees about how it will run.
      - Just C and C++ amoung main-stream languages


SEE: example.cpp


  - Strongly typed languages provide *memory safety*
    - can't read or write memory that was not allocated by the program
      -can't do out-of-bounds array accesses
      - a form of *buffer overruns* used by many hackers and viruses
      - these exploits target C/C++ code because of this vulnerability.
    - no *dangling pointers*
      - dereferece a pointer to a memory location that has been freed
        and possibly re-allocated (at a different type)
    - no uninitialized variables

  - Why is memory safety necessary for strong typing?
    - prevents garbage values.
    - if we read out-of-bounds from an int array, are we getting back 
      an int? how do we know?

  - How does a langauage ensure lack of memory errors?
    - a combination of things
      - run-time array bounds checking
      - hide the pointers.
      - automatic garbage collection

  Both statically and dynamically typed languages do array bounds checking.
    - Java, OCaml, Python, JS

  Why are C/C++ weakely typed (and not memory safe)?
    - designed for systems programming (OS, other performance-critical sw)
    - at the time they were invented, costs of memory safety were considered
      prohibitve.
    - so the static type checker does "best effort" but no run-time checks
      - no run-time checks for type casts
      - no array bounds checks
    - arguably still necessary today, particularily in certain constrained settings.

  Tradeoffs:
    - strong type checking costs more at run time, but is *much* safer.
      - popularized in the mainstream by Java, C#, scripting langs (JS and Python)
      - everything but C/C++



 *)










 Lecture 14










 (*

Midterm Tuesday.
  - Last year's midterm. and solution

No office hours tomorrow. Today is the last chance for midterm questions.

Midterm topics:
  - OCaml: pattern matching, recursion, 1st class functions,
    higher order functions, user defined types.
  - Syntax and grammar
  - Variables: scoping, lifetime
  - Typing: static vs dynamic, strong vs weak, automatic conversion
  - Hallmarks of functional programming
  - List functions map, filter, fold_right
  - Things from today.

This is a *big idea*. Look for these in the notes.

Last time:
  Weak typing:
    - The programmer is smarter than the type checker.
    - Types are just a suggestion. We can work around them.
    - Ex: convert an int to a pointer.
      - corrupt the heap
      - corrupt the call stack
      - adversary (virus or hacker) can take control of our system

  Strong typing:
    - Types are enforced.

  Most languages (static and dynamic) have strong typing.
    - Exceptions: C/C++

  Auto conversion:
    - Often conflated with weak typing.
    - pro: avoiding dynamic type errors.
           convenience (converting between numeric types)
    - cons: WAT
            conversion heuristics are heuristics.
            its hard to devise a simple, general rule that 
            always works
    - middle ground: user-controlled conversions (scala)
       - tools (IDE) can help show what's going on.


Back to typing (static typing in particular):
  - type checking vs type inference.
    - type checking requires user type annotations
      - e.g. Java, C, C++. 
      - pro: type errors are easier to understand
             type annotations are "checked documentation"
      - con: writing the type annotations is tedious
    - type inference does not require annotations (or requires fewer)
      - e.g. OCaml, Scala, Haskell
      - pro: less tedium
      - con: sometimes more confusing type errors.
    - middle ground: (scala)
      - require *some* annotations, but not all.
      - annotate inputs to functions, but not locals.

static/strong/check: Java
static/strong/infer: OCaml
static/weak/check: C/C++

dynamic/strong: Lisp,JS,Python,Prolog,...
dynamic/weak:
  - essentially "untyped". assembly language.
  - "use the contents of this register as a memory address"
  - "use the contents of this register as an integer"
  - "use the contents of this register as a float"

dynamic/strong/infer: ?
dynamic/strong/check: ?
  None! the distinction between inference/checking is for statically
  typed languages.

*)



(*  Modules and modularity.

Example: List.map, List.fold_right
  - List is a module.
  - Modules provide *namespaces* 
    Allows for two parts of a program to use the same name in
    different ways.
  - Set.map and List.map
     "Inside" the Set module, "map" refers to Set.map
  - We can refer to names in the module scope from outside.
    That's what List.map is doing.
  - Modules provide something more (and more important) than
    namespaces.
    - modularity
    - separate interface from implementation.
    - encapsulation
  - How do we separate interface from implementation in an OO language?
    - use classes.
    - can have an "interface" or "abstract class" that defines the interface
    - concrete class is the implementation.
    - the internals of class can be *private*, or hidden

*)

(* Example: a component for tracking word counts.
   Data structure to store the data. Dictionary.
   We'll have some operations for incrementing the count,
   or getting the count
 *)

type word_counts = (string * int) list ;;
let empty = [];;
let rec incr w wcs =
  match wcs with
    [] -> [(w,1)]
  | (w1,c)::tl ->
    if w = w1
    then (w1,c+1) :: tl
    else (w1,c)   :: incr w tl
;;
let rec get w wcs =
  match wcs with
    [] -> 0
  | (w1,c)::tl ->
    if w = w1
    then c 
      else get w tl
;;

let wcs = incr "hi" (incr "hi" empty);;

(* 

Problem: the names word_counts, empty, incr, get could be used
elsewhere in the program.

Limits the ability to use the component

Solution: use a module to put the names into a separate namespace.

*)

module WordCount =
  struct
    type word_counts = (string * int) list ;;
    let empty = [];;
    let rec incr w wcs =
      match wcs with
        [] -> [(w,1)]
      | (w1,c)::tl ->
        if w = w1
        then (w1,c+1) :: tl
        else (w1,c)   :: incr w tl
    ;;
    let rec get w wcs =
      match wcs with
        [] -> 0
      | (w1,c)::tl ->
        if w = w1
        then c 
          else get w tl
    ;;
  end
;;


let wcs = WordCount.incr "hi" (WordCount.incr "hi" WordCount.empty)

(*

In general, a module can contain *any kind of declaration*.
  - functions, types, exceptions, etc

*)


(*

A new problem:
  - let's be the client of WordCount now.
  - we want to get the word with max count

*)

let rec max_wc wcs =
  match wcs with
    [] -> None
  | (w,c)::tl ->
    (match max_wc tl with
       None -> Some (w,c)
     | Some (w1,c1) -> 
       Some (if c1>c then (w1,c1) else (w,c)))
;;

(*

 - We're still the client.
 - We notice that WordCount is super slow.
 - We complain to the author.
   - They nicely agree to upgrade to use a hash table.
 - Now max_wc breaks, so we can't use the better version!
 - We are angry, and we cancel that paypal tip.
 - Now the author is also angry, so they put a trojan into their
   component. 

 - Diagnosis: the client was tightly-coupled to the internals of
   the component. 
   - The author can't upgrade their component.
   - The author is responsible, but ham-strung.

 - We want the author to have the flexibility to change their design
   over time.
   - To make something more efficient
   - To add new features

 - Why not just always write efficient code?
   - Why not just always program in assembly language?
   - There's a trade off between efficiency and 
     clarity, ability to maintain it, fix bugs, add features, etc.
   - "Premature optimization"
     - we don't know what needs to be fast until we start using
       the program.
   - leads to brittle code: hard to maintain, easy to break, hard to fix.
   - better to "optimize" for clarity, simplicity, flexibility

- We want to separate the interface of the component from its
  implementation.
  - what is an interface?
    - everything the client needs to know about the component 
      in order to use it.
    - in particular: what the author will not change, vs
      what might change.
    - includes documentation, test cases, etc.

- Divide and conquer.
  - break a large/complex problem into multiple smaller/simpler problems
    that can be solved *independently*. repeat
  - canonical example: sorting
  - what about programming?
    - break the work into separate components/modules 
    - they can be developed/tested/etc independently, *in parallel*
      - by different developers.
    - code reuse. libraries, application frameworks, open source projects, etc.
*)








(* What to know from Midterm 1 *)

- Static vs Dynamic Typechecking
  - Static
    - Checks at compiletime, as opposed to run-time code like 1+true would be caught before runnning program
  - Dynamic
    - Does not have to be conservative. OCaml does not allow heterogenous lists like [1; true] 
      since it cannot give such a list a type, while C/C++ allows this.
- Strongly Typed
  -  A program cannot access memory that it did not allocate.
- First-Class Functions
  - Can take functions as argument
  - Can return a function
  - Functions can be stored in lists
  - Functions can have variable names
- Static Scoping
  - Each variable reference refers to its *nearest* *enclosing* definition in the source code.
    - Unbound variable error if not
- Functional Language
  - 
- Pattern Matching



Good exam questions:
  - Trace the evaluation steps for an expression involving functions,
    let/in, etc.
  - What is a closure?
    - A closure is the value of a function
      It stores the environment from the time the function was evaluated
  - What is currying?
    - Passing arguments 1-by-1
    - Each variable usage can be bound to its associated declaration at compile time.
    - Properly preserves the behavior of passing multiple arguments to a function.
  - Why does currying require static scoping?
    - It doesn't, but static scoping with closures simplifies currying. 
      Runs into problems with anonymous functions when the stack variables have been discarded.
  - Which variables are in scope after evaluating some expression?
    - https://www.codecademy.com/en/forum_questions/514900b642e721e65d0003f1 
  - Which are live?
    - https://www.codecademy.com/en/forum_questions/514900b642e721e65d0003f1


  Midterm topics:
  - OCaml: pattern matching, recursion, first-class functions,
    higher order functions, user defined types.
  - Syntax and grammar
  - Variables: scoping, lifetime
    - Lifetime
      - The lifetime of a variable or object is the time period in which the variable/object has valid memory. 
        Lifetime is also called "allocation method" or "storage duration."
  - Typing: static vs dynamic, strong vs weak, automatic conversion
    - Static vs Dynamic type checking
      -pros/cons:
        - Static
          - Pros: early detection of errors (before you run the program),
                  managing complexity of large projects
          - Cons: often cryptic error messages, conservative, rejects your way.
                  can lead to code duplication
        - Dynamic
          - Pros: good for rapid prototyping, experimentation, small projects
          - Cons: dynamic type errors are hard to debug
    - Strong vs Weak typing
      - Strong: variables are predefined as part of the programming language and 
        all constants or variables defined for a given program must be 
        described with one of the data types.   
      - Weak: you can work around the type system. C is notoriously weakly typed 
        because any pointer type is convertible to any other pointer type simply by casting.
  - Hallmarks of functional programming
    - Higher-order functions, Recursion, Immutable data
  - List functions map, filter, fold_right
    - http://caml.inria.fr/pub/docs/manual-ocaml/libref/List.html 
    - map f l
      - map takes in a function (f) and a list (l), applies function to each element in (l)
      - List.map (fun x -> x + 1) [1;2;3;4] = [2;3;4;5]
    - filter f list
      - filter applies a function to a list, and returns a list of the elements that match the function
      - List.filter (fun x -> x > 2) [1;2;3;4] = [3;4]
    - fold_right f list acc
      - fold right takes a function, list, and accumulator, and applies a function starting from right of 
        list and returns it in the accumulator
      - List.fold_right (fun x -> x + 2) [1;2;3;4] [] = [3;4;5;6]
    - fold_left f acc list
      - same thing as fold right, but from left, and in a different order
      - List.fold_left (fun x -> x + 2) [1;2;3;4] [] = [3;4;5;6]
  - Things from today.
