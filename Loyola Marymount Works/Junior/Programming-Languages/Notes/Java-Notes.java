Lecture 19



/*

Java.

*/

import java.util.List;
import java.util.LinkedList;

class JavaTime {
	public static void main(String[] args) {
		System.out.println("Java time.");
	}
}

// $ javac lecture19.java
// $ java JavaTime

/*

Syntax is C/C++ style:
 - curly braces, whitespace doesn't matter, ;, if-then-else are statements
   return, break, etc

Fundamental improvements:
 - Strong typing. aka type safety. memory safety
 - Garbage collection.
 - Before Java, thought to be impractical.
 - After Java, pretty much standard

Object orientation: 
 - Classes. 
   - Collection of methods shared by a group of objects
   - Abstract data type.
   - blueprint for objects
 - Inheritance.
   - Classes, prototype, mix-ins
   - Code reuse
   - Take (inherit) some existing code, change it in some way
 - Objects
   - bundle of some state and some functions that operate on that state
 - Subtyping (polymorphism)
   - classes, interfaces

Note:
 - classes and interfaces are not essential to OOP.
 - JS, Python, Ruby, don't have interfaces
   - interfaces there for static type checking
 - classes are only one way of getting code reuse
   - prototypes (JS, self, swift? maybe both)
 - objects are the only essential idea!

Generics:
  - Parametric polymorphism (from FP)

    append : 'a list -> 'a list -> 'a list

    'a  is a type parameter
       all occurrences can be replaced by some type.

    append : F('a) = 'a list -> 'a list -> 'a list

    List<Integer> 
    List<A>

  - Java made parametric polymorphism mainstream.

Other high-level features:
  - concurrency, first-class functions

Really nice tools:
  - IDEs, debuggers, build systems, package manages, code analyzers.

Posted a getting started note to blackboard.



OO decomposes programs into objects that communicate with each other
by sending *messages*.
  - instead of calling a function f(x)
  - send a message "f(x)" to an object:   o.f(x)
  - what's the difference between f(o,x) and o.f(x)?
  - the "receiver" o decides what "f(x)"
  - extra layer of indirection adds a lot of flexibility.
  - this idea is called "late binding"
  - the meaning of "f" is "bound" as late as possible.

Messages are also called "methods" or "member functions" in OO terminology.

Messages sends not always "synchronous"
 - synchronous: wait for the response (return value)
 - asynchronous: keep running 

Objects are about separating interface from implementation.
 - objects have some *internal* state that only they themselves
   can access.
 - clients have to send messages to the object
 - the idea of public member variable goes against the philosophy of OOP
 - but people do it anyway, for performance and/or convenience

Why separate interface from implementation?
 - Implementation flexibility.
 - Information hiding / encapsulation. Security. 
 - Modularity.
   - Modular type checking / separate compilation.
   - check once that an implementation meets its interface
   - separately check that clients meet the interface
   - then we can safely compose the client and the implementation.
 
 All three of these are critical for *component-based programming*:
   - people produce libraries or component
   - validate them once
   - can be safely used by clients
   - can be safely modified/upgraded later

*/

interface Set {
	boolean contains(String s);
	void addElem(String s);
}

/* Also part of the "interface" but not expressible in Java:

   Set s = ...;
   s.addElem("hey");
   s.contain("hey") == true;


   Other languages have more expressive facilities for these
   kinds of behavioral properties, e.g. contracts.

   But in Java, we instead write unit tests.

We can later implement this interface.

If a class SomeSet implements Set, then:
  - if o has type SomeSet then o also has the type Set.
  - if o has type Set, it *may* or *may not* have type SomeSet.

An interface defines a new type.
  - same name as the name of the interface  
  - object of type Set are receivers for contains and addElem
  - interfaces cannot have any implementation
    - no actual methods, no fields

 */

class Client {
	void myClient(Set s) {
		s.addElem("hi");
		if(!s.contains("hi")) {
			System.out.println("WAT!");
		} else {
			System.out.println("Cool.");
		}
	}
}

class ListSet implements Set {
	private List<String> elems;
	ListSet(){
		elems = new LinkedList<String>();
	}

	public boolean contains(String s) {
		return elems.contains(s);
	} 

	public void addElem(String s) {
		if (! this.elems.contains(s)) {
			elems.add(s);
		}
	}
}




class TestListSet {
	public static void main(String[] args) {
		Client c = new Client();
		ListSet s = new ListSet();
		//System.out.println(s.elems);
		c.myClient(s);
	}
}


// Another Set implementation

class ArraySet implements Set {
	// Invariant: length of elems == the number of elements in the set.
	private String[] elems;

	ArraySet(){
		elems = new String[0];
	}

	public boolean contains(String s) {
		for(int i = 0; i < elems.length; i++) {
			if(s.equals(elems[i]))
				return true;
		}
		return false;
	} 

	public void addElem(String s) {
		if(!contains(s)) {
			String[] newElems = new String[this.elems.length + 1];
			for(int i = 0; i < elems.length; i++) {
				newElems[i] = elems[i];
			}
			newElems[elems.length] = s;
			elems = newElems;
		}
	}
}

class TestArraySet {
	public static void main(String[] args) {
		Client c = new Client();
		Set s = new ArraySet();
		//System.out.println(s.elems);
		c.myClient(s);
	}
}












Lecture 20








/*

News:
 - midterm grades today or tomorrow.
 - quiz 2 a week from thursday (11/17)
   - python and java.
 - hw7 posted saturday

Last time:

*/

import java.util.List;
import java.util.LinkedList;

/*
interface Set {
	boolean contains(String s);
	void addElem(String s);
}

class Client {
	void myClient(Set s) {
		s.addElem("hi");
		if(!s.contains("hi")) {
			System.out.println("WAT!");
		} else {
			System.out.println("Cool.");
		}
	}
}

class ListSet implements Set {
	private List<String> elems;
	ListSet(){
		elems = new LinkedList<String>();
	}

	public boolean contains(String s) {
		return elems.contains(s);
	} 

	public void addElem(String s) {
		if (! this.elems.contains(s)) {
			elems.add(s);
		}
	}
}


class TestListSet {
	public static void main(String[] args) {
		Client c = new Client();
		ListSet s = new ListSet();
		c.myClient(s);
	}
}

class ArraySet implements Set {
	// Invariant: length of elems == the number of elements in the set.
	private String[] elems;

	ArraySet(){
		elems = new String[0];
	}

	public boolean contains(String s) {
		for(int i = 0; i < elems.length; i++) {
			if(s.equals(elems[i]))
				return true;
		}
		return false;
	} 

	public void addElem(String s) {
		if(!contains(s)) {
			String[] newElems = new String[this.elems.length + 1];
			for(int i = 0; i < elems.length; i++) {
				newElems[i] = elems[i];
			}
			newElems[elems.length] = s;
			elems = newElems;
		}
	}
}

class TestArraySet {
	public static void main(String[] args) {
		Client c = new Client();
		Set s = new ArraySet();
		c.myClient(s);
	}
}

*/

/*

Modularity: protecting components from each other.
 - enforcing the boundary between components
 - separate interface from implementation
 - loose coupling
   - two components coupled in the sense that they depend upon each other
     for some functionality
   - loosely, so one components can easily be replaced by another
Modularity:
 - enables parallel developments
 - enables code reuse
 - makes code more flexible, adaptable, robust to change
 - makes code more understandable, by enabling local reasoning.

*/

/*

Java supports two kinds of *polymorphism*:
 1) parametric polymorphism
    - called generics in Java
    - called polymorphism in OCaml
    - Idea: a type can be parameterized by *type variables*

    List<String> listOfStrings;
    List<Shape>  listOfShapes;

Example from OCaml:

  type 'a option = None | Some of 'a
  applyInOption : ('a -> 'b) -> 'a option -> 'b option
  applyInOption f x =
    match x with
      None   -> None
    | Some a -> Some (f a)


We can make our Set interface polymorphic in the element type.

*/

interface Set<T> {
	boolean contains(T s);
	void addElem(T s);
}

// Set<T> is like 'a option
// T is a type variable in Java, where 'a is type variable in ocaml

/*

Set<String> s1 = ...
s1.addElem("hello");
Set<Shape> s2 = ...
s2.addElem(new Square());

s2.addElem("hello"); // static type error

*/

/* 

Implementations of a parametric interface like Set<T> can
be parametric or not:

*/

class ListSet<T> implements Set<T> {
	List<T> elems;
	// ...
}

class StringListSet implements Set<String> {
	List<String> elems;
    // ...
}

/* 
Second kind of polymorphism: subtype polymorphism.
 */

interface RemovableStringSet extends Set<String> {
	void remove(String s);
}

/*

RemoveStringSet supports all the operations of Set<String>
  ... and void remove(String)

This establishes a *subtype relationship* between RemovableStringSet and Set<String>
  - key idea: *subtype substitutability*
    - anywhere a Set<String> is expected, a RemovableStringSet can be used.
    - anywhere some type is expected, a *subtype* of that type can be used.
  - if X "implements" or "extends" Y, then X <= Y
  - read: "X is a subtype of Y" or "X is a Y"
  - anywhere a Y is expected, an X can be used.
  - because X supports all the operations of Y
  - a function expecting a Y is polymorphic over all subtypes of Y
  - as with parametric polymorphism, we have one function that
    can be used with arguments of many different types.

*/

class ListRemovableStringSet implements RemovableStringSet {
	// ...
}

// ListRemovableStringSet <= RemovableStringSet <= Set<String>
// subtyping is transitive!

class Client {
	Set union(Set<String> s1, Set<String> s2) {
		// ...
	}
}

// subtype polymorphism allows many different combinations of
// types of s1 and s2. 
// - that's a lot harder to do with param. poly. alone

class Client {
	void test(RemoveStringSet rs) {
		Set<String> s = rs; // legal by subtype polymorphism.
		s.addElem("hello"); // OK
		rs.remove("hello"); // OK
		s.remove("hello");  // type error: Set<String> does not have remove
		rs = s;  // type error: s is not known (by only looking at the declared type of s) to be
		         // a RemoveStringSet.
		         // RemoveStringSet is a (subtype of) Set<String>
		         // "Set<String> is a RemovableStringSet" is false.
	}

}

/*

Subtyping is not symmetric.
 if X <= Y, it's not necessarily true that Y <= X.

*/

// Its possible to combine parametric and subtype polymorphism.

interface RemovableSet<T> extends Set<T> {
	// ... 
}

/*
  
  Set s = rs;   // looks like a coercion/conversion
    - does not change the underlying data at all.

  In Java:

  double x = 2; 
    - conversion between types can introduce imprecision

  Automatic conversion can look like a kind of polymorphism

	void f(double x) { ...}
	f(4.5)
	f(12)

  int is NOT a subtype of double
   - they have different representation and different operations
   - conversion introduces loss of precision

  automatic conversion is just a convenience

*/ 

// Why have two kinds of polymorphism at all? in the same language?
// - they are incomparable or orthogonal.
// - each can do things the other can't

// Consider union:

class Client {

	<T> void union(T s1, T s2) {
		// stuck: we don't even know that T is a set.

	}
}  

// So subtype polymorphism is good.

// What can parametric polymorphism do that subtype polymorphism can't?

// Why is this:

interface Set<T> {
	void add(T e);
	boolean contains(T e);
	List<T> getElems();
}

// better than:

interface ObjectSet {
	void add(Object o);
	boolean contains(Object o);
	List<Object> getElems();
}


class SetClient {
	Set<Program> pgms = // ...
	for (Program p : pgms.getElems()) {
		p.run();
	}
}


class SetClient {
	ObjectSet pgms = // ...
	pgms.add("hello");
	for (Object o : pgms.getElems()) {
		Program p = (Program) o; // downcast could fail.
		p.run();
	}
}

/*

Parametric polymorphism helps us avoid errors.
 - giving us more precise types.
 - can't put a Shape into a Set<Program>
 - eliminates annoying boilerplate casting and error handling

Generics added to Java 1.5 for this reason.

*/

















Lecture 21














/*

Last: two kinds of polymorphism in Java:
  - parametric and subtyping.

What can we do with parametric?
 - define functions and data types that are *parametric*
   in a type. We treat those uniformly.
   e.g. Set<T> can contain elements of any type.
     - and all elements have that type.

What can we do subtyping?
  - establishes an "is a" relationship between types.
  - ListSet "is a" Set
    ArraySet "is a" Set
  - Defined union that operates on any two sets.

Key difference: 
  - with subtyping, we know *something* about the type.
  - with parametric, we know *nothing* about the type.

*/

interface Set {
	boolean contains(String s);
	void addElem(String s);
}

class ListSet implements Set {
	private List<String> elems;
	ListSet(){
		elems = new LinkedList<String>();
	}

	public boolean contains(String s) {
		return elems.contains(s);
	} 

	public void addElem(String s) {
		if (! this.elems.contains(s)) {
			elems.add(s);
		}
	}
}

interface RemovableSet extends Set {
	void remove(String s);
}

/*

Suppose we want to implement RemovableSet.
  - need remove
  - also need contains and addElem to satisfy the subtyping constraint.

*/

class RemovableListSet implements RemovableSet {
	private List<String> elems;
	RemovableListSet(){
		elems = new LinkedList<String>();
	}

	public boolean contains(String s) {
		return elems.contains(s);
	} 

	public void addElem(String s) {
		if (! this.elems.contains(s)) {
			elems.add(s);
		}
	}

	public void remove(String s) {
		// ... remove s.
	}
}

/*

Duplicating all this code is bad!

*/

class RemovableListSet extends ListSet implements RemovableSet {
	// all of ListSet's methods are "copied" into RemovableListSet.
	public void remove(String s) {
		// ...
	}
}

/*

when one class A extends another B.

- It establishes a subtyping relationship A <= B.
  - wherever a B is expected, we can use an A.
  - a *typing* or *interface* concern.

- Reuses the code from B in A.
  - an *implementation* concern.

We have the following subtyping relationships:

	RemovableListSet <= ListSet
	RemovableListSet <= RemovableSet
	RemovableListSet <= Set   (by transitivity of subtyping)

*/

/*
interface A { void foo(); }
class B implements A { void foo() {} }
class C implements A { void foo() { System.exit(1); } }
class D extends B, C {} // multiple inheritance
*/

/*

This is not allowed in Java.
 - which implementation of foo should D inherit?
 - called *the diamond problem*
 - other languages do allow this.

*/

// Java 8 allows interfaces to provide default implementations of methods.
// We've already seen that a class can implement multiple interfaces.

interface A { void foo(); }
interface B extends A {
	default void foo() {};
}
interface C extends A {
	default void foo() { System.exit(1); } 
}
class D implements B, C {
	// looks a lot like MI.
	public void foo() {
		B.super.foo();   // use the implementation from B.
		// this is allowed in Java.
	}
}

// So the diamond problem (the motivation for banning MI in Java)
// is not too bad for methods.

class B {
	int foo;
	// ...
}

class C {
	string foo;
	// ...
}

class D extend B,C {
	// which foo do we get?
	// no easy way to resolve the conflict between the types of foo
	// used in B and C.
	// if we choose int, we break C etc
}

/*

So MI is bigger problem for classes than interfaces even with default
method implementations.
  - why? because... classes have fields!

So MI is banned in Java, but allowed in other languages.
  - notably C++.

*/

/*

Java has a special class called Object.
  - all classes ultimately inherit from Object
  - all types of objects are subtypes of Object
  - why is it there?
    - provides default implementations of methods that
      are generally useful.
      - toString
      - equals()  pointer equality
      - hashCode() 
    - before generics, containers always used Object for
      their element type.

*/ 

/*

Privacy:
  - three levels of privacy on fields and methods: 
      public, protected, and private.
    - public: can be accessed from anywhere
    - protected: can be accessed from subclasses
    - private: can be accessed from only this class.

class ListSet implements Set {
	private List<String> elems;
	ListSet(){
	}
}

class RemovableList extends ListSet implements RemovableSet {
	// ... 
}

Can be tricky deciding what to make public, private, or protected.

Balance the need for information hiding etc 
  with the potential need for reuse via subclassing.

*/

/* Subtyping vs subclassing 

In Java they are conflated, easy to mix up.
- is they often introduced simultaneously

But there are cases where we to subtype without subclassing.

Example: Rectangles and Squares
 */

class Rectangle {
	private int x, int y, width, height;
	Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
	void scaleWidth(int factor) {
		this.width *= factor;
	}
	void scaleHeight(int factor) {
		this.height *= factor;
	}
	void draw() {
		// ...
	}
}

class Square extend Rectangle {
	// private int x, int y, width;
	Square(int x, int y, int width) {
		super(x, y, width, width);  // invariant: width = height
	}
	// inherit everthing from Rectangle.

	// scaleWidth and scaleHeight can break the invariant.
	// override to avoid inheriting the bad implementations
	void scaleWidth(int factor) {
		super.scaleWidth(factor);
		super.scaleHeight(factor);
	}
	void scaleHeight(factor) { scaleWidth(factor); }
}

// Problem solved!
// ... or is it?

// what if we want to add a method addWidth(int) or addHeight(int)
// - we have to remember to fix square again!
// - this goes against the philosophy of Java and OOP
// - implementers of a class shouldn't have to worry about breaking
//   subclasses. too many of them, new ones added at any time.

// Moral: sometimes we want subtyping without subclassing (inheritance).
// How do we get that? interfaces! (next time)



















Lecture 22
















/*

Quiz 2: Thursday.

Topics:
  - Python and Java
  - Polymorphism: subtype and parametric
  - Subclassing and inheritance
  - Constraints/limitations/problems with each of
    subtyping, parametric poly., inheritance
  - Privacy: private, public, protected
  - Modularity in Java
  - Multiple inheritance
    - Java can only inherit from once class. Why?
    - Can implement multiple interfaces that have 
      default versions of the same method. Why/How?
*/



/* Example from last time: Rectangles and Squares */

class Rectangle {
	private int x, y, width, height;
	Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
	void scaleWidth(int factor) {
		this.width *= factor;
	}
	void scaleHeight(int factor) {
		this.height *= factor;
	}
	void draw() {
		// ...
	}
}

/*
class Square extends Rectangle {
	// private int x, int y, width;
	Square(int x, int y, int width) {
		super(x, y, width, width);  // invariant: width = height
	}
	// inherit everthing from Rectangle.
}
*/

// solution: factor out the interface of rectangular things
/*
interface Rectangular {
	void scaleWidth(int f);
	void scaleHeight(int f);
	void draw();
}

class Rectangle implements Rectangular {
	// ...
}

class Square implements Rectangular {
	// Avoid having to copy/paste definitions from rectange:
	private Rectangle r;

	void scaleWidth(int factor) {
		// use Rectangle's implementation,
		// but preserve the invariant
		r.scaleWidth(factor);
		r.scaleHeight(factor);
	}
}
*/

// Now, if we add addWidth method to rectangular,
// type checker will force us to implement it in square.
// have to write a bit of code to use Rectangle's implementation,
// but avoids the problems of automatic inheritance.

// Another issue.

class Bag {
	// aka multiset. like a set, but can have duplicates.
	Object[] elements;

int size() { /* ... */ }
void add(Object elem) { /* ... */ }
boolean contains(Object elem) {  /* ... */ }

}

// Now we want to add a Set class. Should Set extend Bag?
// Subclassing = subtyping + inheritance.
// Inheritance:
//   - Set can reuse Bag's implementation. Good!
//   - Would want to override add, but inheriting the other 
//     methods would make sense.
// Subtyping:
//   - A Set "is a" Bag without duplicates.
//   - Seems ok.

class Set extends Bag {
  void add(Object elem) {
  	if(! this.contains(elem))
  		super.add(elem);
  }
}

class BagClient {
	void foo() {
		Bag  bag = somewhere.getBag();
		int n = bag.size();
		bag.add("something");
		assert(bag.size() == n + 1);
	}
}

// Our intuition was off: a set isn't a bag!
// doesn't always behave like one.
// The Bag interface is  not just the types of its methods;
//   but also the behavioral "contract" guaranteed to the bag clients.
// Set does not follow the contract.
// So: Set should not be a subtype of Bag

// Moral: Inheritance sometimes makes sense without subtyping.
// Impossible in Java!

// Solution: factor out the code into a class "Collection"
// Interface doesn't provide any behavioral guarantees for its methods.
// Collection is just a repository for shared code.
class Collection {
	Object[] elements;

    int size() { /* ... */ }
    void add(Object elem) { /* ... */ }
	boolean contains(Object elem) {  /* ... */ }
}

class Bag extends Collection {}
class Set extends Collection {}

// get code reuse, but can't use a set where a bag is expected.

// could make add abstract in Collection, so Set and Bag would
// be forced to implement it.
// can make elements private to retain modularity for Collection

abstract class Collection {
	// nobody gets access to elements
	private Object[] elements;

	// only subclasses get access to this.
    protected void insert(int pos, Object elem) {
    	// ...
    }

    // these are provided to external clients
    public int size() { /* ... */ }
    public abstract void add(Object elem); // subclasses have to implement this!
	public boolean contains(Object elem) {  /* ... */ }
}

// type checker forces Bag and Set to implement add
// two problems: 1 can't access elements.
//               2 code duplication!
// solution: 
class Bag extends Collection {
	void add(Object elem) {
		// do something
	}
}
class Set extends Collection {
	void add(Object elem) {
		// do something
	}
}

/*

Interfaces vs Abstract classes.
- Neither can be instantiated. 
- Interfaces have no fields.
- Abstract classes can have fields.

- An interface just declares a new type.
- An abstract class declares a new type, but also the beginning of 
  a real class (but one that has holes in it)

*/