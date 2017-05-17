package money;

import util.IntTuple;

/**
 * A utility class for making change given an amount of cents and an IntTuple
 * containing the denominations for the coins to use.
 */
public class DynamicChangeMaker {

    /**
     * Using the dynamic programming algorithm, returns an IntTuple containing
     * how many coins of each denomination are required to make the given cents.
     * The returned IntTuple should be the most efficient answer (least possible
     * amount of coins). Returns null if it's impossible to find a solution.
     */
    public static IntTuple makeChange(IntTuple denominations, int cents) {
        //TODO: Implement me!
       
        IntTuple[][] changeTable = new IntTuple[denominations.length()][cents + 1];

        for (int i = 0; i < denominations.length(); i++) {
            denominations[i][0] = new IntTuple(denominations.length());
        }










        return null;
    }

    private static void printCoins(IntTuple denominations) {
        System.out.print("coins: ");
        for (int i = 0; i < denominations.length(); i++) {
            System.out.print(denominations.intAt(i) + "\u00a2" +
                    (i < denominations.length() - 1 ? ", " : ""));
        }
        System.out.println();
    }

    private static void printCoinAmounts(IntTuple denominations, IntTuple amounts) {
        if (amounts == null) {
            System.out.println("Impossible for these coins.");
        } else {
            for (int i = 0; i < amounts.length(); i++) {
                System.out.println(denominations.intAt(i) +
                        "\u00a2: " + amounts.intAt(i));
            }
            System.out.println("total used: " + amounts.sumOfElements());
        }
    }

    private static int[] stringArrayToIntArray(String[] args) {
        int[] coins = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            try {
                coins[i - 1] = Integer.parseInt(args[i]);
                if (coins[i - 1] < 1) {
                    printError("Coin values must be > 0. " +
                            "Value found: " + coins[i - 1]);
                    return null;
                }
                for (int j = 0; j < i - 1; j++) {
                    if (coins[j] == coins[i - 1]) {
                        printError("Duplicate coin value found: " + coins[j]);
                        return null;
                    }
                }
            } catch (NumberFormatException nfe) {
                printError("Not a valid integer: " + args[i]);
                return null;
            }
        }
        return coins;
    }

    private static void printError(String message) {
        System.out.println("ERROR: " + message);
        System.out.println(
                "Usage: java money.DynamicChangeMaker <cents> <demoniations...>");
    }

    public static void main(String[] args) {
        boolean validInput = true;
        int cents = 0;
        int[] coinArray = new int[0];
        if (args.length < 1) {
            validInput = false;
            printError("No amount of cents given.");
        } else if (args.length < 2) {
            validInput = false;
            printError("No denominations given.");
        } else {
            try {
                cents = Integer.parseInt(args[0]);
                if (cents < 0) {
                    validInput = false;
                    printError("Can't make change for negative cents: " + cents);
                } else {
                    coinArray = stringArrayToIntArray(args);
                    validInput = coinArray != null;
                }
            } catch (NumberFormatException nfe) {
                validInput = false;
                printError("Not a valid integer: " + args[0]);
            }
        }

        if (validInput) {
            IntTuple denominations = new IntTuple(coinArray);
            IntTuple amounts = makeChange(denominations, cents);
            printCoins(denominations);
            System.out.println("To make change for " + cents +
                    "\u00a2, use the following coins:");
            printCoinAmounts(denominations, amounts);
        }
    }

    private DynamicChangeMaker() {
        // Constructor left private to prevent instantiating a utility class.
    }
}