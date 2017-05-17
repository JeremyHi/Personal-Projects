package math;

public class MonteCarloTest {
    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String[] args) {

        test_polynomial();
        test_pistimator();

        System.out.println(successes + "/" + attempts + " tests passed.");
    }

    private static void displaySuccessIfTrue(boolean value, int number) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

    private static void displayFailure(int testNumber) {
        displaySuccessIfTrue(false, testNumber++);
    }

    private static void test_polynomial() {
        System.out.println("Testing Polynomial...");
        int testNumber = 1;

        double[] coefficient = new double[4];
        for (int i = 0;  i < coefficient.length; i++) {
            coefficient[i] += 2.0;
        }
        Polynomial p = new Polynomial(coefficient);

        try {
            displaySuccessIfTrue(p.f(3) == 80, testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(p.f(-3) == -40, testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(p.f(-5) == -208, testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        coefficient = new double[] {3.5,6.7,4.2,1.2,8.9};
        p = new Polynomial(coefficient);

        try {
            displaySuccessIfTrue(p.f(3) == 514.7, testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        System.out.println();
    }

    private static void test_pistimator() {
        System.out.println("Testing Pistimator Constructors...");
        int testNumber = 1;

        Pistimator number = new Pistimator();
        Pistimator number2 = new Pistimator(142434);


        try {
            displaySuccessIfTrue(number.getDarts() == 100000, testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(number2.getDarts() == 142434, testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }
        double pi = number2.estimatePi();
        try {
            displaySuccessIfTrue(((pi > 3.0) && (pi < 3.3)), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }
    }
}