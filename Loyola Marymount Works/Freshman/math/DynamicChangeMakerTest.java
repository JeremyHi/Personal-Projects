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
            1, 5, 10, 25
        });
        IntTuple answer = new IntTuple(new int[] {
            1, 0, 2, 5
        });

        try {
            displaySuccessIfTrue(makeChange(denominations, 146).equals(answer));
        } catch (Exception e) {
            displayFailure();
        }

        System.out.println();
    }
}