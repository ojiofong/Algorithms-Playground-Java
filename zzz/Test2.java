package zzz;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Test2 {

	public static void main(String[] args) throws Exception {
		System.out.println(Test2.class.getSimpleName());

		// String[] arr = {"A","B","C","D","E","F"};
		String[] arr = { "A", "B", "C", "D" };
		int len = arr.length - 1;
		combo(arr, len, 0, new String[len]);
		print("a bc");
	}

	static void combo(String[] arr, int len, int startPosition, String[] result) {
		if (len == 0) {
			System.out.println(Arrays.toString(result));
			return;
		}
		for (int i = startPosition; i <= arr.length - len; i++) {
			result[result.length - len] = arr[i];
			combo(arr, len - 1, i + 1, result);
		}
	}

	static void print(String str) {
		char[] arr = str.toCharArray();

		for (char c : arr) {
			if (!String.valueOf(c).equals(" "))
				System.out.print(c);
		}
		System.out.println("");
	}

}
