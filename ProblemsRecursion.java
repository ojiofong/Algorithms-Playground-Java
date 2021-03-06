import java.util.Iterator;
import java.util.LinkedList;

public class ProblemsRecursion {

	public static void main(String[] args) {
		System.out.println(ProblemsRecursion.class.getSimpleName());
		println("testing");
		print("nthFibonnaci: " + nthFibonnaci(3) +"\n");
		printFibonnaci(0, 1, 2);
		println("\nsumRecursing: " + sumRecursing(4, 7));
	}

	public static void println(String s) {
		System.out.println(s);
	}

	public static void print(String s) {
		System.out.print(s);
	}

	// find nth Fibonacci number
	public static int nthFibonnaci(int k) {
		if (k == 0)
			return 0;
		if (k == 1)
			return 1;

		// 0 1 1 2 3 5 8 13
		//print(k + " | ");
		return nthFibonnaci(k - 1) + nthFibonnaci(k - 2);
	}

	// print Fibonacci sequence
	public static void printFibonnaci(int a, int b, int limit) {
		
		print(a + " | ");
		
		if (limit <= 0) {
			return;
		}
		
		printFibonnaci(b, b + a, --limit);
	}
	
	// Write a recursive function: that calculates the sum of the numbers from x to max (inclusive). 
	// For example, sum (4, 7) would compute 4 + 5 + 6 + 7 and return the value 22. 
	// The function must be recursive so you are not allowed to use any conventional loop constructs
	public static int sumRecursing( int x, int max ) { 
		if(x ==  max) return max;
		
		return x + sumRecursing(x + 1, max);
	} 
}
