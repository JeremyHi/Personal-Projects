import java.util.Scanner;
import java.math.BigInteger;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
 
class Factorial {

  public String evaluate(int[] integerArray) throws Exception{
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine engine = mgr.getEngineByName("JavaScript");
    Scanner input = new Scanner(System.in);
    System.out.println("What equation would you like to evalute? (input factorials as '[number entered])'");
    String foo = input.toString();
    //replace entered in value "[]" with factorial number.
    for (int i = 0; i < foo.length(); i++) {
      
    }
    String answer = "" + engine.eval(foo);
    return answer;
  }

  public static void main(String args[]) {
    int n, c, m, o;
    BigInteger inc = new BigInteger("1");
    BigInteger factN = new BigInteger("1");
    BigInteger factM = new BigInteger("1");
    BigInteger factO = new BigInteger("1");
 
    Scanner input = new Scanner(System.in);

    System.out.println("Are we finding the factorial or exponetnial value of? (Enter in [F] or [E])");
    String value = input.toString();
    if (value.equals("[E]") || value.equals("[e]")) {
      System.out.println("What power are we going to?");
    }
    
    System.out.println("How many numbers do we need the factorial of?");
    int length = input.nextInt();
    int[] factArray = new int[length];
    int counter = 1;
    for (int i = 0; i < factArray.length; i++) {
      System.out.println("Input integer " + counter + " to be factorialized");
      factArray[i] = input.nextInt();
      counter++;
    }


    /** This is the math that computates the factorials for the three numbers. */
    BigInteger[] factArrayBig = new BigInteger[factArray.length];

    for (int i = 0; i < factArrayBig.length; i++) {
      factArrayBig[i] = new BigInteger("1");
      inc = new BigInteger("1");
      for (int p = 1; p <= factArray[i]; p++) {
        factArrayBig[i] = factArrayBig[i].multiply(inc);
        inc = inc.add(BigInteger.ONE);
      }
    }

    for (BigInteger i : factArrayBig) {
      System.out.println("Factorial for number is: " + i.toString());
    }
  }
}











