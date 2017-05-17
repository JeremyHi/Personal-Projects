package clock;

public class ClockTest {

    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String[] args) {

        test_clockConstructors();
        test_tick();
        test_clockSolverConstructors();
        test_foundAngle();
        test_toString();
        test_clockSolverGetters();

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

    /**
     * Tests to see if the clock constructors are working properly.
     * In other words, test to see if hours, minutes, seconds, grain, and
     * secondsPassed are getting initialized properly. Also check to make sure
     * an IllegalArgumentException is being thrown when input is invalid.
     */
    private static void test_clockConstructors() {
        System.out.println("Testing Clock Constructors...");
        

        Clock clock1 = new Clock();
        Clock clock2 = new Clock(11);
        Clock clock3 = new Clock(11,9);
        Clock clock4 = new Clock(11,9,0.6);
        Clock clock5 = new Clock(11,9,0.6,2.0);

        //clock1
        try {
            displaySuccessIfTrue(clock1.getHours() == 12 && clock1.getMinutes() == 0 && clock1.getSeconds() == 0.0 && clock1.getGrain() == 1.0);
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }
        //clock2
        try {
            displaySuccessIfTrue(clock2.getHours() == 11 && clock2.getMinutes() == 0 && clock2.getSeconds() == 0.0 && clock2.getGrain() == 1.0);
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }
        //clock3
        try {
            displaySuccessIfTrue(clock3.getHours() == 11 && clock3.getMinutes() == 9 && clock3.getSeconds() == 0.0 && clock3.getGrain() == 1.0);
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }

        //clock4
        try {
            displaySuccessIfTrue(clock4.getHours() == 11 && clock4.getMinutes() == 9 && clock4.getSeconds() == 0.6 && clock4.getGrain() == 1.0);
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }

        //clock5
        try {
            displaySuccessIfTrue(clock5.getHours() == 11 && clock5.getMinutes() == 9 && clock5.getSeconds() == 0.6 && clock5.getGrain() == 2.0);
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }

        //clock6 tests for IllegalArgumentException
        try {
            Clock clock6 = new Clock(11,61,0.0,1.0);
            displayFailure();
        } catch(Exception exc) {
            displaySuccessIfTrue(true);
        }

        System.out.println();
    }

    /**
     * Test the tick method. Make sure that hours, minutes, seconds, and secondsPassed
     * fields are all being incremented properly when the clock ticks. This includes
     * testing that seconds and minutes reset after 60 and hours resets after 12.
     */
    private static void test_tick() {
        System.out.println("Testing tick...");

        // TODO: It's lonely here. Add some tests, and Jimmy Kimmel.
        Clock clock1 = new Clock(11,9,6.0,2.0);
        Clock clock2 = new Clock(11,9,59.0,2.0);
        Clock clock3 = new Clock(12,59,59.0,2.0);
        clock1.tick();
        clock2.tick();
        clock3.tick();

        //clock1
        try {
            displaySuccessIfTrue(clock1.getHours() == 11 && clock1.getMinutes() == 9 && compareDoubles(8.0, clock1.getSeconds()) && compareDoubles(8.0, clock1.getSeconds()) && compareDoubles(2.0, clock1.getGrain()));
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }
        //clock2
        try {
            displaySuccessIfTrue(clock2.getHours() == 11 && clock2.getMinutes() == 10 && clock2.getSeconds() == 1.0 && clock2.getGrain() == 2.0);
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }
        //clock3
        try {
            displaySuccessIfTrue(clock3.getHours() == 1 && clock3.getMinutes() == 0 && clock3.getSeconds() == 1.0 && clock3.getGrain() == 2.0);
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }

        System.out.println();
    }
    private static void test_toString() {
        System.out.println("Testing toString");
        
        Clock clock1 = new Clock();
        Clock clock2 = new Clock(11);
        Clock clock3 = new Clock(11,9);
        Clock clock4 = new Clock(11,9,0.6);

        //clock1
        try {
            displaySuccessIfTrue("12:00:00.0".equals(clock1.toString()));
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }
        //clock2
        try {
            displaySuccessIfTrue("11:00:00.0".equals(clock2.toString()));
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }
        //clock3
        try {
            displaySuccessIfTrue("11:09:00.0".equals(clock3.toString()));
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }
        //clock4
        try {
            displaySuccessIfTrue("11:09:00.6".equals(clock4.toString()));
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }

        System.out.println();
    }

    /**
     * Tests to make sure that the ClockSolver object is being constructed properly.
     * This means ensuring the clock is being initialized properly, the grain, and
     * also the angleToFind and angleBetween, and that an IllegalArgumentException is
     * being thrown when the angleToFind and grain are invalid.
     */
    private static void test_clockSolverConstructors() {
        System.out.println("Testing ClockSolver Constructors...");
        ClockSolver clock1 = new ClockSolver(11);
        //clock1
        try {
            displaySuccessIfTrue("12:00:00.0".equals(clock1.getClockTime()) && clock1.getAngleBetween() == 0);
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }
        //clock2
        try {
            Clock clock2 = new Clock(-22);
            displayFailure();
        } catch(Exception exc) {
            displaySuccessIfTrue(true);
        }
        //clock3
        try {
            Clock clock3 = new Clock(11, -11);
            displayFailure();
        } catch(Exception exc) {
            displaySuccessIfTrue(true);
        }
        System.out.println();
    }

     //Test getAngleBetween, contruct clock, check 00 pos.
        //then tickClock(), then check pos again.
    private static void test_clockSolverGetters() {
        System.out.println("Testing ClockSolver Getter Methods...");
        
        ClockSolver clock1 = new ClockSolver(11);
        
        //clock1
        try {
            displaySuccessIfTrue(compareDoubles(0.0, clock1.getSecondsPassed()) && clock1.getAngleBetween() == 0);
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }

        //clock1 calls "tickClock()"
        try {
            clock1.tickClock();
            displaySuccessIfTrue(compareDoubles(1.0, clock1.getSecondsPassed()) && (clock1.getAngleBetween() < 0.092 || clock1.getAngleBetween() > 0.090));
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }
        System.out.println();
    }

    /**
     * Tests that foundAngle returns true when the angleToFind is within the proper
     * margin of error, and that it returns false when it isn't.
     */
    private static void test_foundAngle() {
        System.out.println("Testing foundAngle...");

        ClockSolver clock1 = new ClockSolver(48.6, 0.001);

        //clock1
        try {
            displaySuccessIfTrue(clock1.foundAngle() == false);
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }

        //clock1 calls "tickClock()"
        try {
            for (double i = 0.001; i < 43200; i += 0.001) {
                clock1.tickClock();
                if (clock1.foundAngle()) {
                    displaySuccessIfTrue(clock1.foundAngle() == true);
                    break;
                }
            }
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }

        System.out.println();
    }

    /**
     * Returns true if the real numbers are within 0.00001 of each other.
     * Helps compare "equality" of two double values since they aren't exact.
     * Don't use == when comparing doubles, but rather, call this method.
     */
    private static boolean compareDoubles(double real1, double real2) {
        return Math.abs(real1 - real2) < 0.00001;
    }

}