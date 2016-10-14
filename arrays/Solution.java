package arrays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		appendRange100(new int[3]);
		System.out.println("doRange100 -> " + doRange100());
		System.out.println("incrementArrayAsNumberBy1 -> " + incrementArrayAsNumberBy1(new int[2]));
		System.out.println("\nEquilibrium index -> " + equilibriumIndex(new int[] { 1, 2, 3, 0, 3 }));
		addTwoArraysAsDigits(new int[] { 9, 9, 5 }, new int[] { 2, 7 });
		unitsOfWaterOnIsland(new int[] {});
	}

	/**
	 * Array of Integer 1-100 Return string with ranges that does not exist e.g.
	 * {5,6,80,90} -> "1-4,7-79,81-89,91-100"
	 **/
	private static String appendRange100(int[] arr) {
		// test input
		arr = new int[] { 5, 6, 80, 90 };
		StringBuffer buffer = new StringBuffer();
		int prev = 0; // current value starts at zero

		for (int i = 0; i < arr.length; i++) {

			int value = arr[i];
			int diff = Math.abs(value - prev);

			if (diff == 2) {
				buffer.append(String.format("%s,", value - 1));
			} else if (diff > 2) {
				buffer.append(String.format("%s-%s,", prev + 1, value - 1));
			}

			// finally if at last index
			if (i == arr.length - 1 && value != 100) {
				int diff100 = Math.abs(value - 100);
				if (diff100 == 2) {
					buffer.append(String.format("%s,", 100 - 1));
				} else if (diff100 > 2) {
					buffer.append(String.format("%s-%s,", value + 1, 100));
				}
			}

			prev = value;
		}

		String s = buffer.toString();
		s = s.substring(0, s.length() - 1); // remove last comma

		System.out.println("appendRange100 -> " + s);

		return buffer.toString();
	}

	/**
	 * Array of Integer 1-100 Return string with ranges that does not exist e.g.
	 * {5,6,80,90} -> "1-4,7-79,81-89,91-100"
	 **/
	private static String doRange100() {
		int[] arr = { 5, 6, 80, 90 };
		Arrays.sort(arr);
		boolean flag = false;
		int prev = 1;
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			int a = arr[i];
			flag = (a - prev) > 1;
			if (i == 0 && a != 1) {
				buffer.append((a == 2 ? "1" : "1-" + (a - 1)));
			} else if (flag) {
				int x = prev + 1;
				int y = a - 1;
				buffer.append(",").append(x - y == 0 ? "" + x : x + "-" + y);
			}

			if (i == arr.length - 1 && a < 100) {
				int x = a + 1;
				int y = 100;
				buffer.append(",").append(x - y == 0 ? "" + x : x + "-" + y);
			}

			prev = a;
		}

		return buffer.toString();
	}

	/**
	 * Given an integer array e.g. [9, 8, 8, 3] where each item in array could
	 * be 0 to 9, interpret the array [9, 8, 8, 3] as a number 9883 and
	 * increment it by 1. return an integer array e.g. [9,8,8,4]. No zeros in
	 * the first position like [0,1,2,3]. No String conversion
	 */
	private static String incrementArrayAsNumberBy1(int[] arr) {
		arr = new int[] { 8, 9, 9, 9 };

		// LinkedList 0(1) insertion
		// Be sure to add to first or queue to top
		LinkedList<Integer> linkedList = new LinkedList<>();
		int carry = 0; // holds the value to carry over
		int lastIndex = arr.length - 1;
		// start from last index length-1;
		for (int i = lastIndex; i >= 0; i--) {
			// add one at last index only
			int sum = (i == lastIndex) ? arr[i] + 1 + carry : arr[i] + carry;

			if (sum < 10) {
				linkedList.addFirst(sum);
				carry = 0;
			} else {
				// incrementing digit of ONLY LAST INDEX by 1 guarantees sum
				// can't be > 10 i.e. 9+1 = 10, so add zero and carry one
				carry = 1;
				linkedList.addFirst(0);
			}
		}

		// if we have something carried then add it
		if (carry > 0)
			linkedList.addFirst(carry);

		return linkedList.toString();

	}

	/**
	 * Equilibrium index i has sum of left side values = sum of right side
	 * values Returns the first equilibrium index i. e.g. [1,2,3,0,3] returns
	 * index 2 because 1+2->3<-0+3 Time O(n)
	 */
	private static int equilibriumIndex(int[] arr) {
		int n = arr.length;
		if (n <= 0)
			throw new IllegalArgumentException();
		;
		long totalSum = 0;

		for (int i = 0; i < n; i++)
			totalSum += arr[i];

		long sumLeft = 0;
		for (int i = 0; i < n; i++) {
			long sumRight = totalSum - sumLeft - arr[i];
			if (sumLeft == sumRight)
				return i;
			sumLeft += arr[i];
		}

		return -1;
	}

	/**
	 * Add two arrays of digits at each index. Carry over if needed. Returns
	 * array as digits only. For e.g. [9,9,5] and [2,7] returns [1,0,2,2] i.e.
	 * 995+27=1022
	 */
	private static String addTwoArraysAsDigits(int[] arr1, int[] arr2) {

		// arr1 = new int[]{9,9,5};
		// arr2 = new int[] { 2, 2 };

		int carry = 0;
		LinkedList<Integer> linkedList = new LinkedList<>();

		// start addition from right to left
		int indexOfArr1 = arr1.length - 1;
		int indexOfArr2 = arr2.length - 1;

		while (indexOfArr1 >= 0 || indexOfArr2 >= 0) {

			// Avoid indexOutOfBoundsException
			int v1 = indexOfArr1 >= 0 ? arr1[indexOfArr1--] : 0;
			int v2 = indexOfArr2 >= 0 ? arr2[indexOfArr2--] : 0;
			int sum = v1 + v2 + carry;

			// if nothing to carry over
			if (sum < 10) {
				carry = 0;
				linkedList.addFirst(sum);
			} else {
				carry = 1;
				linkedList.addFirst(sum - 10);
			}

		}

		if (carry > 0)
			linkedList.addFirst(carry);

		System.out.println("addTwoArraysAsDigits-> " + linkedList.toString());

		return linkedList.toString();

	}

	// x o x
	// x x x x o x
	// x x x x x x x x
	// 1,2,3,2,3,1,2,1 -> 2 units of water
	// =================
	// x o x
	// x o x
	// x x x x
	// 1,3,1,3 -> 2 units of water
	// ==================
	// Find number of units of water on an island
	// Time O(n) Space O(1)
	private static int unitsOfWaterOnIsland(int[] arr) {

		// arr = new int[]{1,2,3,2,3,1,2,1};
		arr = new int[] { 3, 1, 2, 3 };

		int peak = 0;
		int left = 0;
		int right = arr.length - 1;
		int ans = 0;

		while (left < right) {
			if (arr[left] < arr[right]) {
				peak = Math.max(peak, arr[left]);
				ans += peak - arr[left++];
			} else {
				peak = Math.max(peak, arr[right]);
				ans += peak - arr[right--];
			}
		}

		System.out.println("max units of water -> " + ans);

		return ans;
	}

	// Kadane's algorithm algorithm relies on at least one positive number
	// if all negative return the max negative value
	// This method assumes there's at least one positive number
	public static int maxSubArray(int[] arr) {

		int negativeMax = Integer.MIN_VALUE;
		if (isAllNegative(arr)) {
			// return the largest negative
			for (int i : arr) {
				negativeMax = Math.max(negativeMax, i);
			}
			return negativeMax;
		}

		int length = arr.length;

		int sumCurrent = 0;
		int sumMaxSub = 0;

		for (int i = 0; i < length; i++) {
			sumCurrent += arr[i];
			sumCurrent = Math.max(0, sumCurrent); // return zero if negative
			sumMaxSub = Math.max(sumCurrent, sumMaxSub); // get the max only

		}

		return sumMaxSub;
	}

	private static boolean isAllNegative(int[] arr) {
		for (int i : arr) {
			if (i > 0)
				return false;
		}
		return true;
	}

	/**
	 * For N number of input arrays. Find the intersection of N-integer arrays.
	 */
	public static Integer intersectionOfNIntegerArrays(List<Integer[]> list) {
		if (list == null || list.isEmpty())
			return 0;

		int listIndex = 0;
		int i = 0;
		Integer prev = null;
		while (listIndex < list.size()) {
			int t = list.size() - 1;
			while (t-- >= 0) {
				Integer[] arr = list.get(t);

			}

		}

		return 0;
	}

	/*-
	 * Generate all combinations of size r in arr[] of size n:
	 * 
	    int[] arr = new int[] { 1, 2, 3, 4, 5 };
	    int r = 3;
	    int n = arr.length;
	    int data[] = new int[r];
	    
	 *  outputs: 123, 124, 125, 134...345
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

		// current is excluded, replace it with next (Note that i+1 is passed,
		// but index is not changed)
		combinationsOfSizeR(arr, n, r, index, data, i + 1);
	}

}// End of class
