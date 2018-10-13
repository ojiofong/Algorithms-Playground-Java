package zzz;

import java.util.Arrays;

public class Test3 {

	public static void main(String[] args) throws Exception {

		System.out.println(Test3.class.getSimpleName());
		int[] arr = new int[] { 3, 1, 2,3 };
		int ans = unitsOfWater(arr);
		System.out.println("ans-> " + ans);

	}

	private static int unitsOfWater(int[] arr) {
		if (arr == null || arr.length == 0)
			return 0;

		int count = 0;
		int i = 0;
		int j = arr.length - 1;
		int leftMax = 0;
		int rightMax = 0;

		while (i <= j) {
			if (arr[i] < arr[j]) {
				if (arr[i] > leftMax) {
					leftMax = arr[i];
				} else {
					count += (leftMax - arr[i]);
				}
				i++;
			} else {
				if (arr[j] > rightMax) {
					rightMax = arr[j];
				} else {
					count += (rightMax - arr[j]);
				}
				j--;
			}
		}

		return count;
	}

}
