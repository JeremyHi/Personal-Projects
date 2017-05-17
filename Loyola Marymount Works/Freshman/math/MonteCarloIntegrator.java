package math;

	import java.util.*;

public class MonteCarloIntegrator {

    // TODO: Add instance variables, constructors and methods.
	private int dartsHits;
	private Integratable function; 


	public MonteCarloIntegrator(Integratable function) {
		this.function = function;
	}

	public double integrate(double lowerBound, double upperBound) {
		return integrate(lowerBound, upperBound, 1000000);
	}

	public double integrate(double lowerBound, double upperBound, long darts) {
		if (lowerBound > upperBound) {
			System.out.println("Argument Exception: upperBound < lowerBound. Switching values.");
			double temp = upperBound;
			upperBound = lowerBound;
			lowerBound = temp;
		}
		double ceiling = 0;
		double floor = 0;
		for (int i = 0; i < darts; i++) {
			double randomNumX = (upperBound - lowerBound) * Math.random() + lowerBound;
			if (function.f(randomNumX) > ceiling) {
				ceiling = function.f(randomNumX);
			} else if (function.f(randomNumX) < floor) {
				floor = function.f(randomNumX);
			}
		}
		System.out.println("upperBound: " + ceiling + " lowerBound: " + floor);
		
		double sum = 0;
		for (long i = 0; i <= darts; i++) {
			double x = Math.random();
			sum = sum + function.f(lowerBound + ((upperBound - lowerBound) * x));
		}
		sum = ((upperBound - lowerBound) / darts) * sum;
		return sum;
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Required fields missing: Explain");
			System.out.println("Example: MonteCarloIntegrator: 100000 -2 2 5 7 2 4 --- returns Integral from [-2,2] of 5x^3 + 7x^2 + 2x + 4");
			System.out.println("Use: MonteCarloIntegrator- [number of Darts] [lowerBound] [upperBound] [~coefficients of polynomial]");
		} else if (args.length < 3) {
			System.out.println("Required fields missing: Explain");
			System.out.println("Not enough arguments, please have at least 3 arguments.");
		}
		try {
			for (int i = 0;  i < args.length; i++) {
				Double.parseDouble(args[i]);
			}
		} catch (IllegalArgumentException e) {
			System.out.println("One or more of your arguments is not a number, try again.");
			System.exit(0);
		}

		double[] test = new double[args.length-3];
		int count = 0;
		for (int i = 3; i < args.length; i++) {
			test[count] = Double.parseDouble(args[i]);
			count++;
		}
		Polynomial p = new Polynomial(test);
		MonteCarloIntegrator integrater = new MonteCarloIntegrator(p);
		p.toString(p.fx);
		System.out.println(integrater.integrate(Double.parseDouble(args[0]), Double.parseDouble(args[1]), Long.parseLong(args[2])));		
	}

    private static void printCoordinates(double x, double y, boolean in) {
        System.out.println(x + " " + y + " " + (in ? "in" : "out"));
    }

}