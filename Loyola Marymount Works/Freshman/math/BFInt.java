package math;

//Imported solely for HashCode purposes. Do not use.
import java.util.Arrays;
import java.util.*;

/**
 * The B stands for Big.
 *
 * Object that represents an arbitrarliy large integer
 * (Max value has around Integer.MAX_INT (~2 billion) digits.
 */
public class BFInt {


    // TODO: Set these.
    /**
     * A constant for a BFInt with a value of 0.
     */
    public static final BFInt ZERO = new BFInt(0);
    /**
    * A constant for a BFInt with a value of -1.
    */
    public static final BFInt NEGATIVE_ONE = new BFInt("-1");
    /**
    * A constant for a BFInt with a value of 1.
    */
    public static final BFInt ONE = new BFInt(1);
    /**
    * A constant for a BFInt with a value of 10.
    */
    public static final BFInt TEN = new BFInt(10);

    // These are the private instance variables for BFInt. You can change these if you
    // have a different idea for how to represent a BFInt object.
    private boolean negative;
    private byte[] digits;
    private boolean plusTest = false;
    

    public BFInt() {
        this(0);
    }

    public BFInt(int number) {
        // TODO: Finish me, pretty please.
        this(Integer.toString(number));
    }

    public BFInt(long number) {
        // TODO: Finish me, pretty please.
        this(String.valueOf(number));
    }

    public BFInt(short number) {
        // TODO: Finish me, pretty please.
        this(String.valueOf(number));
        
    }

    public BFInt(byte number) {
        // TODO: Finish me, pretty please
        this(String.valueOf(number));
    }

    public BFInt(BFInt bFInt) {
        // TODO: Finish me, pretty please.
        this(bFInt.toString());
    }

    public BFInt(String numberString) {
        //adds in backwards
        if (numberString.charAt(0) == '-' ) {
            negative = true;
            numberString = numberString.substring(1);
        } else if (numberString.charAt(0) == '+' ) {
            negative = false;
            numberString = numberString.substring(1);
        } else {
           negative = false; 
        }

        int counter = 0;
        for (int i = 0; i < numberString.length()-1; i++) {
            if (numberString.charAt(i) == '0') {
                counter++;
            } else if (numberString.charAt(i) == '.') {
                throw new IllegalArgumentException();
            } else if (numberString.charAt(i) != '0') {
                i = numberString.length() - 1;
            } 
        }
        numberString = numberString.substring(counter);

        //adds in numbers backwards
        digits = new byte[numberString.length()];
        for (int i = 0; i < (digits.length); i++) {
            digits[i] = Byte.parseByte(numberString.substring((digits.length - (i + 1)),(digits.length - (i + 1)) + 1));
        }
    }

    public boolean isEqualTo(BFInt other) {
        return (this.toString().equals(other.toString()));
    }

    public boolean isGreaterThan(BFInt other) {
        int first = this.toString().length(); 
        int second = other.toString().length();
        if (this.isNegative() || other.isNegative()) {
            first = first - 1;
            second = second - 1;
            if (first < second) {
                return true;
            }
        } else if (first > second) {
            return true;
        } else if (first < second) {
            return false;
        }
        if (first == second) {
            for (int i = first-1; i >= 0; i--) {
                if (this.digits[i] > other.digits[i]) {
                    if (this.isNegative() || other.isNegative()) {
                        return false;
                    }
                    return true;
                } else if (this.digits[i] < other.digits[i]) {
                    if (this.isNegative() || other.isNegative()) {
                        return true;
                    }
                    return false;
                }
            }            
        }
        return false;
        
    }

    public boolean isLessThan(BFInt other) {
        if (this.isGreaterThan(other)) {
            return false;
        }
        if (this.isEqualTo(other)) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if the this BFInt is negative.
     */
     public boolean isNegative() {
         return negative;
     }

    /**
     * Returns a new BFInt that is the absoulte value of this BFInt.
     */
    public BFInt abs() {
        BFInt abs = new BFInt(this);
        abs.negative = false;
        return abs;
    }

    public byte[] pad(BFInt bigger, BFInt smaller) { 
        byte[] answer = new byte[bigger.digits.length];
        if (bigger.isNegative() || smaller.isNegative()) {
            if (bigger.isNegative()) {
                if (bigger.digits[bigger.digits.length-1] > smaller.digits[smaller.digits.length-1]) {
                    for (int i = 0; i < smaller.digits.length; i++) {
                        answer[i] = smaller.digits[i];
                    }
                    for (int i = smaller.digits.length; i < ((bigger.digits.length) - smaller.digits.length); i++) {
                        answer[i] = (byte)0;
                    }
                    return answer;
                }
            } else if (smaller.isNegative()) {
                if (bigger.digits[bigger.digits.length-1] < smaller.digits[smaller.digits.length-1]) {
                    if (bigger.digits.length > smaller.digits.length) {
                        for (int i = 0; i < smaller.digits.length; i++) {
                            answer[i] = smaller.digits[i];
                        }
                        for (int i = smaller.digits.length; i < ((bigger.digits.length) - smaller.digits.length); i++) {
                            answer[i] = (byte)0;
                        }
                            return answer;
                    } else {
                        for (int i = 0; i < smaller.digits.length; i++) {
                            answer[i] = smaller.digits[i];
                        }
                        for (int i = smaller.digits.length; i < ((bigger.digits.length) - smaller.digits.length); i++) {
                            answer[i] = (byte)0;
                        }
                            return answer;
                    }
                }
            }
        }
        for (int i = 0; i < smaller.digits.length; i++) {
            answer[i] = smaller.digits[i];
        }
        for (int i = smaller.digits.length; i < ((bigger.digits.length) - smaller.digits.length); i++) {
            answer[i] = (byte)0;
        }
        return answer;
    }

    public BFInt isLonger(BFInt other) {
        if (this.digits.length > other.digits.length) {
            return this;
        }
        return other;
    }
    public BFInt isShorter(BFInt other) {
        if (this.digits.length < other.digits.length) {
            return this;
        }
        return other;
    }

    /**
    * Returns the sum of this BFInt plus the addend.
    */
    public BFInt plus(BFInt addend) {
        if (this.isNegative() || addend.isNegative()) {
            if (this.isNegative() && !(addend.isNegative())) {
                plusTest = true;
                BFInt minusAns = addend.minus(this);
                return new BFInt(minusAns);
            } else if (addend.isNegative() && !(this.isNegative())) {
                plusTest = true;
                BFInt minusAns = this.minus(addend);
                return new BFInt(minusAns);
            }
        }

        BFInt longer = (this.toString().length() > addend.toString().length()) ? this : addend;
        BFInt shorter = (this.toString().length() < addend.toString().length()) ? this : addend;
        byte[] addendArr = shorter.digits;
        byte[] result = new byte[longer.digits.length + 1];

        if (longer.digits.length != shorter.digits.length) {
            addendArr = shorter.pad(longer, shorter);
        } else if (longer.digits.length == shorter.digits.length) {
            addendArr = addend.digits;
            longer = this;
            shorter = addend;
        }

        for (int i = 0; i < longer.digits.length; i++) {
            int value = (addendArr[i] + longer.digits[i]);
            if (value > 9) {
                result[i] += (value % 10);
                int tensPlace = value / 10;
                result[i+1] += tensPlace;
            } else if (value < 10) {
                result[i] += value;
            }
            if (result[i] >= 10) {
                result[i+1] += result[i] / 10;
                result[i] = (byte)(result[i] % 10);
            }
        }
        String input = "";
        for (int i = result.length-1; i >= 0; i--) {
            if (longer.isNegative() || shorter.isNegative()) {
                if (longer.isNegative()) {
                    input += "-";
                    longer.negative = false;
                    shorter.negative = false;
                }
            }
            input += "" + result[i];
        }
        if (input.charAt(0) == '-') {
            String ans = "-";
            for (int i = 1; i < input.length(); i++) {
                if (input.charAt(i) != '-') {
                    ans += input.charAt(i);
                }
            }
            return new BFInt(ans);
        }
        return new BFInt(input);
    }

    /**
    * Returns the difference of this BFInt minus the given subtrahend.
    */
    public BFInt minus(BFInt subtrahend) {
        if ((this.toString().equals(subtrahend.toString())) && (subtrahend.isNegative())) {
            return new BFInt("0");
        }
        BFInt greaterVal = (this.isGreaterThan(subtrahend)) ? this : subtrahend;
        BFInt lesserVal = (!(this.isGreaterThan(subtrahend))) ? this : subtrahend;
        if (greaterVal.digits.length == lesserVal.digits.length && greaterVal.digits[greaterVal.digits.length-1] < lesserVal.digits[lesserVal.digits.length-1]) {
            for (int i = greaterVal.digits.length-1; i >= 0; i--) {
                if (lesserVal.digits[i] > greaterVal.digits[i]) {
                    BFInt temp = greaterVal;
                    greaterVal = lesserVal;
                    lesserVal = temp;
                    break;
                }
            }
        } else if (greaterVal.digits.length < lesserVal.digits.length) {
            BFInt temp = greaterVal;
            greaterVal = lesserVal;
            lesserVal = temp;
        }
        byte[] subtrahendArr = lesserVal.pad(greaterVal, lesserVal);
        int[] result = new int[greaterVal.digits.length + 1];
        String zeroAns = "";
        if (lesserVal.toString().equals("0")) { //0 - (-722)
            if (subtrahend.isGreaterThan(this) && !subtrahend.isNegative()) {
                zeroAns = "-" + greaterVal.toString();
                return new BFInt(zeroAns);
            }
            zeroAns = greaterVal.toString().substring(1);
            return new BFInt(zeroAns);
        }
        if ((lesserVal.isNegative() && !greaterVal.isNegative()) && plusTest == false) {
            for (int i = 0; i < greaterVal.digits.length; i++) {
                int value = (subtrahendArr[i] + greaterVal.digits[i]);
                if (value > 9) {
                    result[i] += (value % 10);
                    int tensPlace = value / 10;
                    result[i+1] += tensPlace;
                } else if (value < 10) {
                    result[i] += value;
                }
                if (result[i] >= 10) {
                    result[i+1] += result[i] / 10;
                    result[i] = (result[i] % 10);
                }
            }
        } else {
            for (int i = 0; i < greaterVal.digits.length; i++) {
                if (subtrahendArr[i] > greaterVal.digits[i]) {
                    if (greaterVal.digits[i+1] != 0) {
                        greaterVal.digits[i] += 10;
                        greaterVal.digits[i+1] -= 1;

                    } else if (greaterVal.digits[i+1] == 0) {
                        greaterVal.digits[i+2] -= 1;
                        greaterVal.digits[i+1] += 9;
                        greaterVal.digits[i] += 10;
                    }
                }
                int value = (greaterVal.digits[i] - subtrahendArr[i]);
                result[i] += value;
            }
        }
        String input = "";
        for (int i = result.length-1; i >= 0; i--) {
            input += "" + result[i];
        }
        if ((subtrahend.isGreaterThan(this) && !subtrahend.isNegative()) || (greaterVal.isNegative())) {
            if (((subtrahend.digits[subtrahend.digits.length-1] > this.digits[this.digits.length-1]) && subtrahend.isNegative() && (subtrahend.digits.length >= this.digits.length)) && this.isNegative()) {
            } else {
                input = "-" + input;
            }
        } else if ((input.charAt(0) == '-') && !(greaterVal.isNegative())) {
            input = input.substring(1);
        }
        if ((subtrahend.toString().equals(greaterVal.toString())) && !(subtrahend.isNegative())  && (greaterVal.digits.length > lesserVal.digits.length) && this.isNegative()) {
                input = "-" + input;
            }
        if ((subtrahend.isNegative() && input.charAt(0) == '-') && !greaterVal.isNegative()) {
            input = input.substring(1);
        } else if ((greaterVal.isNegative() && lesserVal.isNegative()) && (greaterVal.toString().equals(subtrahend.toString())) && input.charAt(0) == '-') {
            input = input.substring(1);
        }
        return new BFInt(input);
    }

    public BFInt divideByTwo() {
        BFInt dividedByTwo = this;
        if (dividedByTwo.digits.length < 10) {
            int theString = Integer.parseInt(this.toString());
            return new BFInt(theString/2);
        }
        byte[] answerARR = new byte[this.digits.length];
        for (int i = 0; i < dividedByTwo.digits.length-1; i++) {
            if (dividedByTwo.digits[i] % 2 == 0) {
                if (dividedByTwo.digits[i+1] >= 1) {
                    answerARR[i] = 0;
                } else if (dividedByTwo.digits[i+1] >= 3) {
                    answerARR[i] = 1;
                } else if (dividedByTwo.digits[i+1] >= 5) {
                    answerARR[i] = 2;
                } else if (dividedByTwo.digits[i+1] >= 7) {
                    answerARR[i] = 3;
                } else if (dividedByTwo.digits[i+1] >= 9) {
                    answerARR[i] = 4;
                }
            } else if (dividedByTwo.digits[i+1] >= 1) {
                answerARR[i] = 5;
            } else if (dividedByTwo.digits[i+1] >= 3) {
                answerARR[i] = 6;
            } else if (dividedByTwo.digits[i+1] >= 5) {
                answerARR[i] = 7;
            } else if (dividedByTwo.digits[i+1] >= 7) {
                answerARR[i] = 8;
            } else if (dividedByTwo.digits[i+1] >= 9) {
                answerARR[i] = 9;
            }
        }
        String ansString = "";
        for (int i = 0; i < answerARR.length; i++) {
            ansString += "" + answerARR[i];
        }
        return new BFInt(ansString);
    }

    /**
    * Returns the product of this BFInt times the given multiplier.
    */

    //just try out ancient egyptian STYLE
    public BFInt times(BFInt multiplier) {
        if (this.abs().toString().equals("1") || multiplier.abs().toString().equals("1")) {
            if (this.abs().toString().equals("1")) {
                if (this.isNegative() ^ multiplier.isNegative()) {
                    String asnwerNeg = "-" + multiplier.toString();
                    return new BFInt(asnwerNeg);
                }
                return multiplier;
            } else if (multiplier.abs().toString().equals("1")) {
                if (this.isNegative() ^ multiplier.isNegative()) {
                    String asnwerNeg = "-" + this.toString();
                    return new BFInt(asnwerNeg);
                }
                return this;
            }
        } else if ((this.isEqualTo(new BFInt(0))) || multiplier.isEqualTo(new BFInt(0))) {
            return new BFInt(0);
        }

        BFInt largerNum = (this.isGreaterThan(multiplier)) ? this : multiplier;
        BFInt smallerNum = !(this.isGreaterThan(multiplier)) ? this : multiplier;
        if (largerNum.digits.length < smallerNum.digits.length && (largerNum.isNegative() && smallerNum.isNegative())) {
            
            BFInt temp = largerNum;
            largerNum = smallerNum;
            smallerNum = temp;
        }
        if (largerNum.abs().toString().equals("1")) {
            if (this.isNegative() && multiplier.isNegative()) {
                BFInt answerTest = multiplier.abs();
                return answerTest;
            }
            return multiplier;
        } else if (smallerNum.abs().toString().equals("2")) {
            if ((!this.isNegative() && multiplier.isNegative()) || this.isNegative() && !multiplier.isNegative()) {
                String negAns = "-" + largerNum.plus(largerNum).toString();
                return new BFInt(negAns);
            } else if ((this.isNegative() && multiplier.isNegative())) {
                return largerNum.abs().plus(largerNum.abs());
            }
            
            return largerNum.plus(largerNum);
        }

        BFInt ans = new BFInt(0);
        if ((smallerNum.digits[0] % 2) != 0) {
            ans = ans.plus(largerNum.abs());
        }
        while(smallerNum.abs().isGreaterThan(new BFInt(1))) {
            largerNum = largerNum.abs().plus(largerNum.abs());
            smallerNum = smallerNum.divideByTwo();
            if ((smallerNum.digits[0] % 2) != 0) {
                ans = ans.plus(largerNum.abs());
            } 
        }
        if (this.isNegative() || multiplier.isNegative()) {
            if (((this.isNegative() && !multiplier.isNegative()) || (!this.isNegative() && multiplier.isNegative())) && (ans.isGreaterThan(new BFInt(0)))) {
                String wordAns = "-" + ans.toString();
                return new BFInt(wordAns);
            }
        }
        return ans;
    }

    /**
    * Returns the quotient of this BFInt divided by the given divisor.
    * Throws an IllegalArgumentException if the divisor is 0.
    */
    public BFInt dividedBy(BFInt divisor) {
        BFInt largerNum = (this.abs().isGreaterThan(divisor.abs())) ? this.abs() : divisor.abs();
        BFInt smallerNum = !(this.abs().isGreaterThan(divisor.abs())) ? this.abs() : divisor.abs();
        
        //caveats
        if (this.toString().equals("0") || divisor.toString().equals("0")) {
            throw new IllegalArgumentException();
        } else if (this.digits.length < 10 && divisor.digits.length < 10) {
            int firstNum = Integer.parseInt(this.abs().toString());
            int secondNum = Integer.parseInt(divisor.abs().toString());
            if ((this.isNegative() && !divisor.isNegative()) || (!this.isNegative() && divisor.isNegative())) {
                return new BFInt((-1)*(firstNum/secondNum));
            }
            return new BFInt(firstNum/secondNum);
        } else if (divisor.abs().toString().equals("1")) {
            if (divisor.isNegative() && this.isNegative()) {
                return new BFInt(this.abs());
            }
            return new BFInt(this);
        } else if (largerNum.abs().isEqualTo(smallerNum.abs())) {
            if (divisor.isNegative() && this.isNegative()) {
                return new BFInt(1);
            }
            return new BFInt(-1);
        } else if (divisor.abs().isGreaterThan(this.abs())) {
            return new BFInt(0);
        }


        //divide them now

        BFInt result = new BFInt(1);
        BFInt temp = divisor.abs();
        int counter = 0;
        while (temp.times(new BFInt(10)).isLessThan(this.abs())) {
            result = result.abs();
            result = result.times(new BFInt(10));
            temp = temp.times(new BFInt(10));
            counter++;

        }
        BFInt subtractVar = new BFInt(this.abs().minus(temp));
        BFInt asnwerAns = ((result.plus((((this.abs()).minus(temp))).dividedBy(divisor))));
        return asnwerAns;
    }

    /**
     * Returns the remainder of this BFInt divided by the given divisor.
     * Throws an IllegalArgumentException if the divisor is 0.
     */
    public BFInt mod(BFInt divisor) {
        if (this.abs().isEqualTo(divisor.abs())) {
            return new BFInt(0);
        } else if (this.isLessThan(divisor)) {
            return this;
        }
        return new BFInt(this.minus((this.dividedBy(divisor)).times(divisor)));
    }

    @Override
    public String toString() {
        //check if one zero only
        String stringNum = "";
        for (int i = digits.length - 1; i >= 0; i--) {
            stringNum += digits[i];
        }
        if (this.isNegative()) {
          stringNum = "-" + stringNum;  
        }

        return stringNum;
    }



    // This is advanced java down here...you don't need to worry about these.

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }
        return isEqualTo((BFInt)obj);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(digits);
        result = prime * result + (negative ? 0 : 1);
        return result;
    }

}