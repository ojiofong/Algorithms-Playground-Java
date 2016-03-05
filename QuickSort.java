import java.util.Arrays;

public class QuickSort {

	private static int[] elements;
	private static int length;

	public static void main(String[] args) {
		print("yeah");

		int[] arr = { 6, 5, 4, 2, 1, 3, 7 };
		print("before sort array: " + Arrays.toString(arr));
		print("before sort array size: " + arr.length);
		// QuickSort.sort(arr);
		// MergeSort.sort(arr);
		// bubbleSort(arr);
		selectionSort(arr);
		print("After sort array: " + Arrays.toString(arr));
		print("After sort array size: " + arr.length);

	}

	private static void print(String str) {
		System.out.println(str);
	}

	public static void sort(int[] inputArr) {
		elements = inputArr;
		length = elements.length;
		doQuickSort(0, length - 1);
	}

	private static void doQuickSort(int lo, int hi) {
		int i = lo;
		int j = hi;
		int pivot = elements[lo + (hi - lo) / 2];

		while (i <= j) {
			while (elements[i] < pivot)
				i++;
			while (elements[j] > pivot)
				j--;
			if (i <= j)
				swap(i++, j--);
		}

		// Recursion if not done
		if (i < hi)
			doQuickSort(i, hi);
		if (j > lo)
			doQuickSort(lo, j);
	}

	private static void swap(int i, int j) {
		int temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}

	public static void bubbleSort(int[] arr) {
		int length = arr.length;
		boolean swapped = true;

		for (int i = 0; i < length && swapped; i++) {
			swapped = false;
			for (int k = 0; k < length - 1; k++) {
				if (arr[k] > arr[k + 1]) {
					int temp = arr[k];
					arr[k] = arr[k + 1];
					arr[k + 1] = temp;
					swapped = true;
				}
			}

		}
	}

	public static void selectionSort(int[] arr) {
		int length = arr.length;

		for (int i = 0; i < length; i++) {
			for (int k = 0; k < length - 1; k++) {
				if (arr[k]  > arr[i]) {
					int min = arr[k];
					arr[k] = arr[i];
					arr[i] = min;
				}
			}
		}
	}
}