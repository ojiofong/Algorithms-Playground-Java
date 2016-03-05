import java.util.Iterator;
import java.util.LinkedList;

public class ProblemsRecursion {

	public static void main(String[] args) {
		System.out.println(ProblemsRecursion.class.getSimpleName());
		println("testing");
		print("nthFibonnaci: " + nthFibonnaci(3) +"\n");
		printFibonnaci(0, 1, 2);
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

}
