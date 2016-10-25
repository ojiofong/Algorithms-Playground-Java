package zzz;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Test2 {

	public static void main(String[] args) {
		// solve(null, null);
		// System.out.println("ans " + solve(null, null));

		System.out.println("ans " + Pattern.matches("a.b", "adb"));
		seperateEvenOdd(null);

	}

	/*-
	  	Input: 17->15->8->12->10->5->4->1->7->6->NULL
		Output: 8->12->10->4->6->17->15->5->1->7->NULL
	 **/
	private static void seperateEvenOdd(int[] arr) {

		arr = new int[] { 17, 15, 8, 12, 10, 5, 4, 1, 7, 6 };
		System.out.println("input: " + Arrays.toString(arr));

		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 == 0) {
				moveToIndex(arr, i, index++);
			}
		}
		System.out.println("output: " + Arrays.toString(arr));

	}

	private static void moveToIndex(int[] arr, int i, int index) {
		int temp = arr[i];
		for (int k = i; k >= index + 1; k--) {
			arr[k] = arr[k - 1];
		}
		arr[index] = temp;
	}

}
