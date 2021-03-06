package mathematics;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(squareRoot(10));
		System.out.println("pow -> " + power(2, 4));
		System.out.println("powFast -> " + powFastSolu(2, 4));
		System.out.println("multiply -> " + multiply(1, 6));
		System.out.println("divide -> " + divide(5, 2));

	}

	public static double squareRoot(int num) {
		if (num <= 0)
			return 0;

		double sqrt = num / 2;
		double t = 0;

		do {
			t = sqrt;
			sqrt = (t + num / t) / 2.0;
		} while (t - sqrt != 0);

		return sqrt;
	}

	public static double power(double base, double exponent) {
		double ans = 1;

		if (exponent == 0)
			return ans;

		if (base == 0)
			return 0;

		for (int i = 0; i < Math.abs(exponent); i++) {
			ans *= base;
		}

		if (exponent < 0) {
			ans = 1.0 / ans; // invert
		}

		return ans;
	}
	
	// Log n time
	private static double powFast(int base, int exponent){
	    if(base == 0) return 0;
	    if(exponent == 0) return 1;
	    if(exponent == 1) return base;
		
		if (exponent % 2 == 0) {
			return powFast(base, exponent / 2) * powFast(base, exponent / 2);
		} else {
			return powFast(base, exponent / 2) * powFast(base, exponent / 2) * base;
		}
	}
	
	// Log n time
	public static double powFastSolu(int base, int exponent) {
		if (exponent < 0) {
			return 1 / powFast(base, exponent);
		} else {
			return powFast(base, exponent);
		}
	}

	private static double multiply(double a, double b) {

		double sum = 0;

		for (int i = 0; i < b; i++) {
			sum += a;
		}

		return sum;
	}

	/*
	 * Integer division
	 */
	private static double divide(double a, double b) {

		if (b == 0)
			throw new ArithmeticException("Division by zero not allowed");

		double result = -1;
		int sign = 1;

		if (a < 0) {
			a = -a;
			sign = -sign;
		}

		if (b < 0) {
			b = -b;
			sign = -sign;
		}

		while (a >= 0) {
			a -= b;
			result++;
		}

		return result * sign;
	}

	public static boolean checkPrime(int num) {
		if (num <= 2 || num % 2 == 0)
			return false;
		// check prime numbers only up to square root
		for (int i = 3; i <= Math.sqrt(num); i += 2) {
			if (num % i == 0)
				return false;
		}

		return true;
	}

	/*-
	 *
	  Given a positive integer, return True if a perfect square else False.
	  Note: Do not use any built-in library function such as sqrt.
	*/
	public static boolean isPerfectSquare(int num) {

		int sqrt = (int) squareRoot(num);

		return (num == (sqrt * sqrt));
	}

}
