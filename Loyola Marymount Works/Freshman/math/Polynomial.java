package math;

	import java.util.*;

public class Polynomial implements Integratable {

    // TODO: Add instance variables, constructors and methods.
    public String[] fx;


	public Polynomial() {
		fx[0] = "1.0";
	}

	//Just constructs a polynomial.

	//f(x) = +4x^4 -2.45x^3  +8x^3-4.22x^1  5
	//f(x) = 4x^4 - 2.45x^3 + 8x^3 - 4.22x^1 + 5

	public Polynomial(double[] coefficients) {
		int power = coefficients.length-1;
		fx = new String[coefficients.length];
		if (coefficients.length == 1) {
			fx[0] = "" + coefficients[0];
		}
		for (int i = 0; i < coefficients.length; i++) {
			if (power != 0) {
				if (coefficients[i] >= 0) {
					if (i == 0) {
						fx[i] = Math.abs(coefficients[i]) + "x^" + power;
					} else {
						fx[i] = " + " + Math.abs(coefficients[i]) + "x^" + power;
					}
				} else if (coefficients[i] < 0) {
					if (i == 0) {
						fx[i] = Math.abs(coefficients[i]) + "x^" + power;
					} else {
						fx[i] = " - " + Math.abs(coefficients[i]) + "x^" + power;
					}
				}
				power--;
			} else {
				fx[i] = coefficients[i] + "";
			}
		}
	}

	//prints out: f(x) = 4x^4 - 2.45x^3   + 8x^3 - 4.22x^1  + 5.3
		//figure out the minus sign thing.
	public String toString(String[] function) {
		String ans = "";
		int power = function.length-1;
		for (int i = 0; i < fx.length-1; i++) {
			if (Integer.parseInt(function[i].substring(function[i].indexOf(".")+1, function[i].indexOf('x'))) == 0) {
				ans += function[i].substring(0, function[i].indexOf(".")) + "x^" + power;
				power--;
			} else {
				ans += function[i] + "";
			}
		}
		
		if (Integer.parseInt(function[function.length-1].substring(function[function.length-1].indexOf('.') + 1)) == 0) {
			if (Double.parseDouble(function[function.length-1]) >= 0) {
				ans += "+ " + function[function.length-1].substring(0, function[function.length-1].indexOf('.'));
			} else {
				ans += "- " + function[function.length-1].substring(0, function[function.length-1].indexOf('.'));
			}
		} else {
			if (Double.parseDouble(function[function.length-1]) >= 0) {
				ans += "+ " + function[function.length-1];
			} else {
				ans += "- " + function[function.length-1];
			}
			
		}

		System.out.println("f(x) = " + ans);
		return ans;
	}


	//Returns the function of the inputed number, i.e. f(5) return "Coefficient * (5)^power."
    @Override
    public double f(double x) {
        double answer = Double.parseDouble
        (fx[fx.length-1]);
        CharSequence search = "x";
        if (fx[0].contains(search)) {
        	int power = Integer.parseInt(fx[0].substring(fx[0].indexOf("^")+1, fx[0].indexOf("^")+2));
        	for (int i = 0; i < fx.length-1; i++) {
        		double coefficient = Double.parseDouble(fx[i].substring(2, fx[i].indexOf("x")));
        		answer += coefficient * Math.pow(x, power);
        		power--;
        	}
        }
        
        return answer;
    }
}