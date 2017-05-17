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

