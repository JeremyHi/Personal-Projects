/** 
    This class takes in three inputs, a starting number, and the base that it is in
    as well as a base to convert to.
 */
public class BaseConverter {

    /**  
        Method that converts the first argument input from a String
        to an array of Longs.
    */
	public static Long[] convertToArray(String argumentString) {
        argumentString = argumentString.replace("[", "");
        String[] argumentArrayString = argumentString.split("]");
        Long[] argArrayLong = new Long[argumentArrayString.length];
        for (int i = 0; i < argumentArrayString.length; i++) {
            if (isLong(argumentArrayString[i])) {
                argArrayLong[i] = Long.parseLong(argumentArrayString[i]);
            } else {
                throw new IllegalArgumentException();
            }
        }
        return argArrayLong;
	}

    /**  
        Method checks to see if a number can be parsed as a Long,
        will return true or false. (To be used in conjuction with args[0])
    */
	public static boolean isLong(String numberToCheck) {
    	try {
       		Long.parseLong(numberToCheck);
    	}  
    	catch(Exception e) {
        	return false;
    	}
    	return true;
	}

    /**  
        Method that checks the entirety of the collected arguments, 
        and will tell whether it can be an excepted argument. If not,
        throws IllegalArgumentException().
    */
	public static boolean validArgs(String[] args) {
		try {
    		Long.parseLong(args[1]);
    		Long.parseLong(args[2]);
		} catch (NumberFormatException | NullPointerException e) { 
            throw new IllegalArgumentException();
		}
        Long[] testArray = convertToArray(args[0]);
            for (int i = 0; i < testArray.length; i++) {
                if (testArray[i] == null || testArray[i] >= Long.parseLong(args[1])) {
                    throw new IllegalArgumentException();
                }
            }
		return true;
	}

    /**  
        Method will convert the first argument, "toConvert" from its original base, 
        "firstBase" to a base 10 number.
    */
    public static Long convertToBaseTen(Long[] toConvert, Long firstBase) {
        Long numberToReturn = 0L;
        Long powerValue = (long)toConvert.length-1;
        for (int i = 0; i < toConvert.length; i++) {
            numberToReturn += toConvert[i] * (long)Math.pow(firstBase, powerValue);
            powerValue--;
        }
        return numberToReturn;
    }

    /**
        Method to count the number of divisons the base 10 number
        will have until it reaches a number that cant be divided.
        Resulting number to be used as counter variable.
    */
    public static int countNumberOfDivisions(Long copyOfBaseTen, Long secondPower) {
        String changedBase = "";
        int counter = 0;
        while (copyOfBaseTen > 0) {
            copyOfBaseTen = copyOfBaseTen / secondPower;
            counter++;
        }
        return counter;
    }

    /**
        Method will turn the two arguments into an array of the answer characters,
        to be made into the actual answer.
    */
    public static String calculateAnswer(int counter, Long baseTenNum) {
        String[] changedBaseArr = new String[counter];
        for (int i = 0; i < changedBaseArr.length; i++ ) {
            changedBaseArr[i] = "[" + Long.toString(baseTenNum % arg2) + "]";
            baseTenNum = baseTenNum / arg2;
        }

        String finalAnswerOutput = "";
        for (int i = changedBaseArr.length - 1; i >= 0; i--) {
            finalAnswerOutput += changedBaseArr[i];
        }
        return finalAnswerOutput;
    }

    public static Long argLengthChecker(String[] arguments) {
        if (arguments.length < 3) {
            return Long.parseLong("10");
        }
        return Long.parseLong(arguments[2]);
    }

    //case if no arg2
	public static void main(String[] args) {
		Long arg2 = argLengthChecker(args);
        System.out.println(args.length);
        validArgs(args);
        Long[] arg0Array = convertToArray(args[0]);
        Long arg1 = Long.parseLong(args[1]);
        System.out.println(calculateAnswer(countNumberOfDivisions(baseTenNum, arg2),
         convertToBaseTen(arg0Array, arg1)));
    }
}



