import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recursion {
	public static void main(String[] args) {
		System.out.println(Recursion.class.getSimpleName());
		// factorial(3);
		System.out.println(fibonacci(4));
		System.out.println("factorial " + factorial2(3));
		System.out.println("fibonacci test " + test(7));
		fibonnaciSequence(0, 1, 7);
		System.out.println("....... ");
		permutation("", "abc");
		System.out.println("....... ");
		System.out.println(permutation2("", "abc"));
		char ch = 'c';
		System.out.print(ch + "->" + (int)ch);
		int[] arr = new int[128];
		arr[ch]++;
		arr[ch]++;
		arr[ch] = arr[ch] + 1;
		arr.clone();
		System.out.println("\nArray " + arr[99] + " "+ arr[100]);
		
		
	}
	
	

	private static int factorial(int n) {
		if (n == 1)
			return 1;
		print("" + n + ", ");
		return n * factorial(n - 1);
	}

	private static void print(String s) {
		System.out.print(s);
	}

	private static int fibonacci(int i) {
		if (i == 0)
			return 0;
		if (i == 1)
			return 1;
		return fibonacci(i - 1) + fibonacci(i - 2);
	}

	private static int factorial2(int n) {
		if (n <= 1) {
			return 1;
		}
		return n * factorial2(n - 1);
	}

	private static int test(int n) {

		// System.out.print(n + " ");

		if (n == 0) {
			return 0;
		}

		if (n == 1)
			return 1;

		return test(n - 1) + test(n - 2);

		// System.out.print(".....");
		// System.out.print(n + " ");
		// return test(--n);
	}

	private static void fibonnaciSequence(int a, int b, int limit) {

		if (limit < 0) {
			return;
		}
		System.out.print(a + " ");

		fibonnaciSequence(b, b + a, --limit);
	}

	static List<String> list = new ArrayList<>();
	private static String permutation2(String prefix, String word) {
		int n = word.length();
		if (n == 0) list.add(prefix);
		else {
			for (int i = 0; i < n; i++)
				permutation2(prefix + word.charAt(i), word.substring(0,i) + word.substring(i+1, n));
		}
		return list.toString();
	}

	private static void permutation(String prefix, String str) {
		int n = str.length();
		if (n == 0)
			System.out.print(prefix + ", ");
		else {
			for (int i = 0; i < n; i++)
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
		}
	}

}
