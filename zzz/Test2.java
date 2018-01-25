package zzz;

import java.util.Arrays;

public class Test2 {

	public static void main(String[] args) throws Exception {
		System.out.println(Test2.class.getSimpleName());
		int[] arr = { 3, 2, 5, 0, 1, 4 };
		System.out.println(Arrays.toString(arr));
//		mergeSort(arr);
		quickSort(arr);
		System.out.println(Arrays.toString(arr));

	}

	public static void mergeSort(int[] arr) {
		merge(arr, 0, arr.length - 1);
	}

	public static void quickSort(int[] arr) {
		quick(arr, 0, arr.length - 1);
	}

	private static void merge(int[] arr, int lo, int hi) {
		if (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			merge(arr, lo, mid);
			merge(arr, mid + 1, hi);
			mergeAll(arr, lo, mid, hi);
		}
	}

	private static void mergeAll(int[] arr, int lo, int mid, int hi) {
		int[] helper = Arrays.copyOf(arr, arr.length);

		int i = lo;
		int j = mid + 1;
		int k = lo;

		while (i <= mid && j <= hi) {
			arr[k++] = helper[i] < helper[j] ? helper[i++] : helper[j++];
		}

		while (i <= mid) {
			arr[k++] = helper[i++];
		}
	}

	private static void quick(int[] arr, int lo, int hi) {
		int i = lo;
		int j = hi;
		int pivot = arr[lo + (hi - lo) / 2];
		
		while (i <= j) {
			while (arr[i] < pivot)
				i++;
			while (arr[j] > pivot)
				j--;
			if (i <= j)
				swap(arr, i++, j--);
		}
		if (i <= hi)
			quick(arr, i, hi);
		if (j >= lo)
			quick(arr, lo, j);
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
