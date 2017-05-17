package money;

import util.IntTuple;
import static money.DynamicChangeMaker.makeChange;

public class DynamicChangeMakerTest {
    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String[] args) {

        test_makeChange();

        System.out.println(successes + "/" + attempts + " tests passed.");
    }

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

    private static void displayFailure() {
        displaySuccessIfTrue(false);
    }

    private static void test_makeChange() {
        System.out.println("Test makeChange...");

        IntTuple denominations = new IntTuple(new int[] {
            2, 4, 11, 22
        });
        IntTuple answer = new IntTuple(new int[] {
            0, 4, 1, 0
        });

        try {
            displaySuccessIfTrue(makeChange(denominations, 27).equals(answer));
        } catch (Exception e) {
            displayFailure();
        }

        //test 2
        denominations = new IntTuple(new int[] {
            1, 5, 10, 25
        });
        answer = new IntTuple(new int[] {
            2, 0, 0, 1
        });

        try {
            displaySuccessIfTrue(makeChange(denominations, 27).equals(answer));
        } catch (Exception e) {
            displayFailure();
        }

        //test 3
        denominations = new IntTuple(new int[] {
            3, 6, 22, 38
        });
        answer = new IntTuple(new int[] {
            0, 5, 0, 3
        });

        try {
            displaySuccessIfTrue(makeChange(denominations, 144).equals(answer));
        } catch (Exception e) {
            displayFailure();
        }

        //test 4
        denominations = new IntTuple(new int[] {
            2, 4, 44, 21, 18, 31
        });
        answer = new IntTuple(new int[] {
            1, 0, 0, 0, 1, 4
        });

        try {
            displaySuccessIfTrue(makeChange(denominations, 144).equals(answer));
        } catch (Exception e) {
            displayFailure();
        }

        //test 5
        denominations = new IntTuple(new int[] {
            4, 2, 44, 18, 21, 31
        });
        answer = new IntTuple(new int[] {
            0, 1, 0, 1, 0, 4
        });

        try {
            displaySuccessIfTrue(makeChange(denominations, 144).equals(answer));
        } catch (Exception e) {
            displayFailure();
        }

        //test 6
        denominations = new IntTuple(new int[] {
            4, 2, 44, 18, 21, 31
        });
        answer = new IntTuple(new int[] {
            0, 0, 0, 0, 0, 272
        });

        try {
            displaySuccessIfTrue(makeChange(denominations, 8432).equals(answer));
        } catch (Exception e) {
            displayFailure();
        }

        //test 7
        denominations = new IntTuple(new int[] {
            4, 2, 44, 18, 21, 31
        });
        answer = new IntTuple(new int[] {
            0, 3, 0, 0, 0, 398
        });

        try {
            displaySuccessIfTrue(makeChange(denominations, 12344).equals(answer));
        } catch (Exception e) {
            displayFailure();
        }

        //test 8
        denominations = new IntTuple(new int[] {
            3
        });

        try {
            displaySuccessIfTrue(makeChange(denominations, 23134) == null);
        } catch (Exception e) {
            displayFailure();
        }
        
    }
}