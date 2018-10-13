package interview;

public class Solution {

	/*-
	 * Magic Index - index with the same value at the index
	 * O(Log n) time and O(1) space.
	 */
	private static int getMagicIndex(int[] arr) {
		int lo = 0;
		int hi = arr.length - 1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (arr[mid] == mid) {
				return mid;
			} else if (arr[hi] == hi) {
				return hi;
			} else if (arr[lo] == lo) {
				return lo;
			} else if (arr[mid] > hi) {
				hi = mid - 1;
			} else if (arr[mid] < lo) {
				lo = mid + 1;
			} else {
				lo += 1;
				hi -= 1;
			}
		}
		return -1;
	}
}
