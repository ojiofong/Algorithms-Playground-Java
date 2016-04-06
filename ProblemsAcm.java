import java.util.Arrays;

/**
 * Five programming problems every Software Engineer should be able to solve in
 * less than 1 hour
 * {@link https://www.shiftedup.com/2015/05/07/five-programming-problems-every-software-engineer-should-be-able-to-solve-in-less-than-1-hour}
 */
public class ProblemsAcm {

	public static void main(String[] args) {
		System.out.println("sumNumsInListForLoop " + sumNumsInListForLoop());
		System.out.println("sumNumsInListWhileLoop " + sumNumsInListWhileLoop());
		System.out.println("sumNumsInListRecursion " + sumNumsInListRecursion(0, 0));
		System.out.println("combineTwoLists " + combineTwoLists());
		listFibonnaci(0, 1, 4);
		System.out.println("\nlargestFormedNumberFromList " + largestFormedNumberFromList());
	}

	// Problem 1 - Start
	// Write three functions that compute the sum of the numbers in a given list
	// using a for-loop, a while-loop, and recursion.

	private static int sumNumsInListForLoop() {
		// Expected Output should be 1+2+3+4 = 10
		int[] arr = { 1, 2, 3, 4 };
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		return sum;
	}

	private static int sumNumsInListWhileLoop() {
		// Expected Output should be 1+2+3+4 = 10
		int[] arr = { 1, 2, 3, 4 };
		int sum = 0;
		int i = 0;
		while (i < arr.length) {
			sum += arr[i];
			i++;
		}
		return sum;
	}

	private static int sumNumsInListRecursion(int sum, int count) {
		// Expected Output should be 1+2+3+4 = 10
		int[] arr = new int[] { 1, 2, 3, 4 };
		int length = arr.length;
		if (count == length)
			return sum;
		return sumNumsInListRecursion(sum + arr[count], ++count);
	}

	// Problem 1 - End

	// Problem 2 - Start
	// Write a function that combines two lists by alternatingly taking
	// elements.
	// For example: given the two lists [a, b, c] and [1, 2, 3],
	// the function should return [a, 1, b, 2, c, 3].

	private static String combineTwoLists() {
		char[] list1 = { 'a', 'b', 'c' };
		char[] list2 = { '1', '2', '3' };
		char[] both = new char[list1.length + list2.length];
		int i = 0;
		int j = 0;
		int k = 0;

		// This is sufficient If both lists are of the same length
		while (i < list1.length && j < list2.length) {
			both[k++] = list1[i++];
			both[k++] = list2[j++];
		}

		// In case both lists don't have the same length
		while (i < list1.length) {
			both[k++] = list1[i++];
		}

		while (j < list2.length) {
			both[k++] = list1[j++];
		}

		// Just return the array as a String
		return Arrays.toString(both);

	}

	// Problem 2 - End

	// Problem 3
	// Write a function that computes the list of the first 100 Fibonacci
	// numbers.
	// By definition, the first two numbers in the Fibonacci sequence are 0 and
	// 1,
	// and each subsequent number is the sum of the previous two. As an example,
	// here are the first 10 Fibonnaci numbers: 0, 1, 1, 2, 3, 5, 8, 13, 21, and
	// 34.

	private static void listFibonnaci(int a, int b, int limit) {

		if (limit < 0)
			return;

		System.out.print(a + " ");

		listFibonnaci(b, b + a, --limit);

	}

	// Problem 3 - End

	// Problem 4 - Start
	//
	// Write a function that given a list of non negative integers, arranges
	// them
	// such that they form the largest possible number. For example, given [50,
	// 2, 1, 9],
	// the largest formed number is 95021.

	private static String largestFormedNumberFromList() {
		int[] arr = { 50, 2, 1, 9 };

		// Special Bubble Sort (Ascending) by largest first character
		boolean flag = true;
		while (flag) {
			flag = false;
			for (int i = 0; i < arr.length - 1; i++) {
				// sort by first character
				if (firstChar(arr[i]) < firstChar(arr[i + 1])) {
					 int temp = arr[i];
					 arr[i] = arr[i+1];
					 arr[i+1] = temp;
					 flag = true;
				}
			}
		}

		StringBuffer buffer = new StringBuffer();
		for(int i : arr)
			buffer.append(i);
		
		return buffer.toString();
	}

	// return the integer value of the first character
	private static int firstChar(int num) {
		String s = String.valueOf(num);
		return s.charAt(0);
	}

	// Problem 4 - Start

}
