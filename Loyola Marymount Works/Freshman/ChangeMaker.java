import java.util.Arrays;

public class ChangeMaker {

	public static int getQuarters(int cents) {
		int quarters = cents/25;
		return quarters;
	}
	public static int getDimes(int cents) {
		int dimes = cents/10;
		return dimes;
	}
	public static int getNickels(int cents) {
		int nickles = cents/5;
		return nickles;
	}
	public static int getPennies(int cents) {
		int pennies = cents/1;
		return pennies;
	}
	public static int[] getChange(int cents) {
		int[] changeArray = new int[4];
		changeArray[0] = getQuarters(cents);
		cents = cents%25;
		changeArray[1] = getDimes(cents);
		cents = cents%10;
		changeArray[2] = getNickels(cents);
		cents = cents%5;
		changeArray[3] = getPennies(cents);
		cents = cents%1;
		return changeArray;
	}
	public static void main(String[] args) {
		try {
            if (args.length == 0) {
                System.out.println("What are you high? You forgot an argument.");
            } else if (args[0].charAt(0) == '-') {
            	System.out.println("What are you high? You do a negative num.");
            }
            else {
            	System.out.println(Arrays.toString(getChange(Integer.parseInt(args[0]))));
            }
        } catch(NumberFormatException e) {
            System.out.println("What are you high? That's not a number, bro.");
        }
    }
}