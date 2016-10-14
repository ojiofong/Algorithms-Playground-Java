package zzz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test2 {

	public static void main(String[] args) {
		// solve(null, null);
		System.out.println("ans " + solve(null, null));
		int[] arr = new int[] { 1, 2, 3, 4, 5 };
        int r = 3;
        int n = arr.length;
        int data[] = new int[r];
        combinationsOfSizeR(arr, n, r, 0, data, 0);

	}


	/**
	 * determine if a given word is comprised of two words within a given list.
	 * e.g. Given list: ["test", "hello", "world"] e.g. Given word:
	 * "abcHellomyWorldstar" Returns true - not case sensitive
	 */
	private static boolean solve(String word, List<String> list) {
		// Test input start
		word = "abcHellomyWorldstar";
		word = word.toLowerCase();
		list = Arrays.asList(new String[] { "test", "hello", "world" });
		// Test input end

		int count = 0;
		for (String s : list) {
			if (word.contains(s.toLowerCase()))
				count++;
			if (count >= 2)
				return true;
		}

		return false;

	}

	// The main function that prints all combinations of size r in arr[] of size n.
	/**
	 * Generate all combinations of size r in arr[] of size n:
	 * e.g. arr= { 1, 2,3, 4, 5 } and r = 3 prints = 123, 124, 125, 134...345
	 */
	static void combinationsOfSizeR(int arr[], int n, int r, int index, int data[], int i) {
		// Current combination is ready to be printed, print it
		if (index == r) {
			System.out.println(Arrays.toString(data));
			return;
		}

		// When no more elements are there to put in data[]
		if (i >= n)
			return;

		// current is included, put next at next location
		data[index] = arr[i];
		combinationsOfSizeR(arr, n, r, index + 1, data, i + 1);

		// current is excluded, replace it with next (Note that i+1 is passed, but index is not changed)
		combinationsOfSizeR(arr, n, r, index, data, i + 1);
	}

}
