interface Greeter {
	void greet();
}
class Person implements Greeter {
	public void greet() { hello("stranger"); }
	public void hello(Object o) { System.out.println("hello object"); }
}
class CSPerson extends Person {
	public void hello(Object o) { System.out.println("hello world!"); }
	public void hello(String s) { System.out.println("hello " + s); }
}
class FrenchPerson extends Person {
	public void hello(Object o) { System.out.println("bonjour object"); }
}

Person p = new FrenchPerson();
p.hello("stranger");
//outputs "bonjour object"

Greeter p = new FrenchPerson();
p.hello("stranger");
//Compile error

Person p = new CSPerson();
p.hello("stranger");
//"hello world!"

CSPerson p = new CSPerson();
p.greet();
//runTime Error WRONG: "hello world!"
//CSPErson extends Person, which implements Greeter, which has greet(), so CSPerson has greet()

// Problem 4
	//a) allowed - inheritence WRONG:subtyping
	//b) allowed - subtyping
	//c) allowed - dynamic dispatch WRONG:inheritance
	//d) disallowed
	//e) allowed - dynamic dispatch

// Problem 5
	//a) iv - Neither of polymorphism or code reuse ANSWER:ii - A form of code reuse
	//b) ii - Expressible in Java by a class implementing an interface

// Problem 6

interface Nat {
	Nat plus(Nat n); 			//Returns a representation of the sum of the receiver and the argument numbers, 
									//without changing the value of either of the original numbers.
	
	void loop(CodeBlock b); 	//implements a simple kind of for loop: the body of the loop, represented by the interface
									//CodeBlock, should be executed n times, where n is the number represented by the receiver object.
}

interface CodeBlock { 
	void execute();
}

class Zero implements Nat {
	public Zero() {}
	public Nat plus(Nat n) { return n; }
	public void loop(CodeBlock b) {}
}

public class Succ implements Nat {
	protected Nat pred;
	public Succ(Nat pred) { this.pred = pred; }

	//Two possible implementations of loop:
	public void loop(CodeBlock b) { b.execute(); pred.loop(b); }
	public void loop(CodeBlock b) { pred.loop(b); b.execute(); }
}