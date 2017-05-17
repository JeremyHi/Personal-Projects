package clock;

/**
 * Represents an object whose sole purpose is to find the times when a
 * given angle is made by the hour and minute hand of the clock (within a margin
 * of error equal to half the grain of the Clock).
 */
public class ClockSolver {

    private double angleToFind;
    private Clock createClock;
    private double angleBetween;

    /**
     * Constructs a ClockSolver Object with the given angle to find and
     * passes the DEFAULT_GRAIN to the Clock constructor.
     */
    public ClockSolver(double angleToFind) {
        this.angleToFind = angleToFind;
        this.createClock = new Clock();
    }

    //Throws an IllegalArgumentException if 
    //the grain is <= 0.0 or the angleToFind < 0.0.
    public ClockSolver(double angleToFind, double grain) {
        if (grain <= 0.0 || angleToFind < 0.0) {
            throw new IllegalArgumentException();
        }
        this.angleToFind = angleToFind;
        this.createClock = new Clock(12,0,0.0,grain);
    }

    public String getClockTime() {
        return createClock.toString();
    }

    public double getSecondsPassed() {
        return createClock.getSecondsPassed();
    }

    public double getAngleBetween() {
        return angleBetween;
    }

    public void tickClock() {
        this.createClock.tick();
        angleBetween += (5.5/60) * createClock.getGrain();
        while (angleBetween >= 360) {
            angleBetween -= 360;
        }
    }
 
    public boolean foundAngle() {
        double errorMargin = (createClock.getGrain())*((5.5/60)/(2));
        if ((angleBetween <= (angleToFind + errorMargin)) && (angleBetween > (angleToFind - errorMargin)) 
            || ((360 - angleBetween) <= (angleToFind + errorMargin)) && ((360 - angleBetween) > (angleToFind - errorMargin))) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java clock.ClockSolver <angleToFind> [ <grain> ] ");
            System.exit(0);
        }
        if (Integer.parseInt(args[0]) >= 360) {
            args[0] = Integer.toString((Integer.parseInt(args[0]) - 360));
            System.out.println("Angle was over 360, converting angle to input minus 360: " + args[0]);
        }

        try {
            double argsAngle = Integer.parseInt(args[0]);
            if (argsAngle < 0) {
                System.out.println("Angle must be >= 0.");
                System.exit(0);
            }
        } catch (NumberFormatException e) {
            System.out.println("Given angle is not a valid number.");
            System.exit(0);
        }
        double argsAngle = Integer.parseInt(args[0]);

        double grain = 1.0;
        if (args.length == 2) {
            try {
                grain = Integer.parseInt(args[1]);
                if (grain <= 0) {
                    System.out.println("Grain must be > 0.0. Using default: 1.0s");
                } else if (grain > 1800.0) {
                    System.out.println("Grain must be <= 1800.0s. Using default: 1.0s");
                }
            } catch (NumberFormatException e) {
                System.out.println("Grain not a number. Using default: 1.0s");
            }
        }

        ClockSolver clock = new ClockSolver(argsAngle, grain);
        int counter = 0;
        for (double i = grain; i < 43200; i += grain ) {
            if (clock.foundAngle()) {
                System.out.println(clock.getClockTime());
                counter++;
            }
            clock.tickClock();
        }

        System.out.println("Your angle was found: " + counter + " times!");
    }
}