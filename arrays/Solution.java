package arrays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("doRange100 -> " + doRange100());
		// System.out.println("incrementArrayAsNumberBy1 -> " +
		// incrementArrayAsNumberBy1(new int[2]));
		// System.out.println("\nEquilibrium index -> " + equilibriumIndex(new
		// int[] { 1, 2, 3, 0, 3 }));
		// addTwoArraysAsDigits(new int[] { 9, 9, 5 }, new int[] { 2, 7 });
		// unitsOfWaterOnIsland(new int[] {});
		// combinationsOfSizeR(null, 0, 0, null, 0);
		// seperateEvenOdd(null);
		// reverseWithoutSpecialChar(null);
		
		System.out.print("" + (int)'a');
	}

	/*-
	 * Given an Array of Integer 1-100. 
	 * Return individual numbers or ranges that do not exist e.g.
	 * {5,6,80,90} -> "1-4,7-79,81-89,91-100"
	 * {1,3,99} -> "2,4-98,100"
	 **/
	private static String doRange100() {
		int[] arr = { 5, 6, 80, 90 };
		Arrays.sort(arr);

		StringBuilder sb = new StringBuilder();
		int size = arr.length;
		int prev = 0;

		for (int i = 0; i < size; i++) {

			int a = arr[i];
			boolean isFirstIndex = i == 0;
			boolean isLastIndex = i == size - 1;

			if (isFirstIndex) {
				int diff = a - 1;
				if (diff == 2) {
					sb.append(String.format("%d,", a - 1));
				} else if (diff > 2) {
					sb.append(String.format("1-%d,", a - 1));
				}
			} else {
				int diff = a - prev;
				if (diff == 2) {
					sb.append(String.format("%d,", a - 1));
				} else if (diff > 2) {
					sb.append(String.format("%d-%d,", prev + 1, a - 1));
				}
			}

			if (isLastIndex) {
				int diff = 100 - a;
				if (diff == 1) {
					sb.append(String.format("%d,", 100));
				} else if (diff == 2) {
					sb.append(String.format("%d,", 99));
				} else if (diff > 2) {
					sb.append(String.format("%d-%d,", a + 1, 100));
				}
			}

			prev = a;
		}

		// delete last comma ","
		return sb.deleteCharAt(sb.length() - 1).toString();

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
	 *  
	 *  Complexity O(n choose k) = O(n!/k!(n-k)!) = O(n!) since k is constant
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

	// Second solution one less argument
	static void combinationsOfSizeR(int arr[], int r, int count, int data[], int i) {
		// Current combination is ready to be printed, print it
		if (count == r) {
			System.out.println(Arrays.toString(data));
			return;
		}

		// When no more elements are there to put in data[]
		if (i >= arr.length)
			return;

		data[count] = arr[i];
		combinationsOfSizeR(arr, r, count + 1, data, i + 1);
		combinationsOfSizeR(arr, r, count, data, i + 1);
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

	/*-
	 * 	Reverse an array without affecting special characters
	 * 	Input:   str = "a,b$c"
	 *	Output:  str = "c,b$a"
	 *	Note that $ and , are not moved anywhere.  
	 *	Only subsequence "abc" is reversed
	 *
	 *	Input:   str = "Ab,c,de!$"
	 *	Output:  str = "ed,c,bA!$"
	 */
	private static String reverseWithoutSpecialChar(String str) {

		str = "Ab,c,de!$";

		if (str == null || str.isEmpty())
			return str;

		char[] arr = str.toCharArray();
		int i = 0;
		int j = arr.length - 1;

		while (i < j) {

			if (isCharAlphanumeric(arr[i]) && isCharAlphanumeric(arr[j])) {
				// swap
				char temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}

			if (!isCharAlphanumeric(arr[i]))
				i++;
			if (!isCharAlphanumeric(arr[j]))
				j--;

		}

		String output = new String(arr);

		System.out.println(String.format("reverseWithoutSpecialChar Input: %s Output: %s", str, output));

		return output;
	}

	private static boolean isCharAlphanumeric(char c) {
		boolean isAlphaNumeric = (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
		// System.out.println(String.format("isAphanumeric %s->%s", c,
		// isAlphaNumeric));
		return isAlphaNumeric;
	}
	
	// Rotate array e.g. 12345->23451->34512
	static String rotate() {
		int[] arr = { 1, 2, 3, 4, 5 };
		int n = arr.length;
		int d = 1; // number of rotations
		for (int i = 0; i < n; i++) {
			arr[(i + n - d) % n] = i+1;
		}

		return Arrays.toString(arr);

	}

}// End of class
