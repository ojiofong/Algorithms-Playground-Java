package zzz;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Test.class.getSimpleName());
		// reverseString("12345");
		// printReverse(1);
		// isBinaryPalindrome(17);
		// appendItemToArray(new String[] { "a", "b", "c" }, 3, "#");
		// isPrime(81);
		System.out.println("prob -> " + prob());

	}

	private static double prob() {

		// delete at pos
		String s = "one two three";
		char[] arr = s.toCharArray();
		int N = s.length();
		System.out.println("before -> " + s);
		int i = 0;
		int j = N - 1;
		reverseA(arr, i, j);
		
		for (int k = 0; k < N; k++) {
			char c = arr[k];
			if (String.valueOf(c).equals(" ")) {
				reverseA(arr, i, k - 1);
				i = k + 1;
			} else if (k == N - 1) {
				reverseA(arr, i, k);
			}
		}

		System.out.println("after -> " + new String(arr));
		// Expected: three two one.. no space

		return 0;
	}

	private static void reverseA(char[] arr, int lo, int hi) {
		int i = lo;
		int j = hi;
		while (i < j) {
			char temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
	}
}
