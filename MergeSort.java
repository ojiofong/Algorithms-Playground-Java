
public class MergeSort {

	private static int[] arr, helper;
	private static int length;

	public static void sort(int[] inputArr) {
		arr = inputArr;
		length = arr.length;
		helper = new int[length];
		doMergeSort(0, length - 1);
	}

	private static void doMergeSort(int lo, int hi) {

		if (lo < hi) {
			
			int mid = lo + (hi - lo) / 2;

			// left
			doMergeSort(lo, mid);
			// right
			doMergeSort(mid + 1, hi);
			// Merge all
			doMergeAll(lo, mid, hi);
		}
	}

	private static void doMergeAll(int lo, int mid, int hi) {
		// Back up - O(n) space complexity
		for (int i = 0; i < length; i++) {
			helper[i] = arr[i];
		}

		int i = lo;
		int j = mid + 1;
		int k = lo;

		while (i <= mid && j <= hi) {
			if (helper[i] < helper[j]) {
				arr[k++] = helper[i++];
			} else {
				arr[k++] = helper[j++];
			}
		}

		while (i <= mid) {
			arr[k++] = helper[i++];
		}
	}
}
