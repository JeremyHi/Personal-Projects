package math;

	import java.util.*;
/**
 * Estimates pi by inscribing a circle of radius 1 in a square of side length 2.
 * A given number of "darts" (points) will then be thrown randomly at the square, and
 * the ratio of darts inside the circle to total darts thrown (plus some algebra)
 * will be used to estimate pi.
 */
public class Pistimator {

    // TODO: Add instance variables, constructors and methods.
	private long darts;
	private int dartsHit;
	private double squareLength;
	private double circleRadius;


    public Pistimator() {
    	darts = 100000;
    	squareLength = 2;
    	circleRadius = 1;
    }

    public Pistimator(long darts) {
    	this.darts = darts;
    	squareLength = 2;
    	circleRadius = 1;
    }


    public long getDarts() {
    	return darts;
    }

    public boolean isInCircle(double x, double y) {
    	return ((x*x + y*y) <= 1);
    }

    public double estimatePi() {
        double squareArea = 4;
        //System.out.println("start");
    	for (int i = 0; i < darts; i++) {
    		double randomNumX = 2 * Math.random() - 1;
    		double randomNumY = 2 * Math.random() - 1;
    		if (isInCircle(randomNumX, randomNumY)) {
    			//System.out.println(randomNumX + " " + randomNumY + " in");
                dartsHit++;
    		} else {
                //System.out.println(randomNumX + " " + randomNumY + " out");
            }
    	}
        //System.out.println("end");
    	double points = (double)dartsHit/darts;
    	double circleArea = points * squareArea;
    	return circleArea;
    }

    public int getHits() {
    	return dartsHit;
    }

    public static void main(String[] args) {
    	if (args[0] == "") {
    		Pistimator result = new Pistimator();
    	}
    	Pistimator result = new Pistimator(Long.valueOf(args[0]).longValue());
    	
    	System.out.println("Hits = " + result.getHits());
    	System.out.println("Darts thrown = " + result.getDarts());
        System.out.println("PI ~= " + result.estimatePi());

    }

    private static void printCoordinates(double x, double y, boolean in) {
        System.out.println(x + " " + y + " " + (in ? "in" : "out"));
    }
}