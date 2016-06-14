package sort;

import java.util.Arrays;

public class QuickSort {
	
	static int[] arr;

	public static void main(String[] args) {
		int[] a = {5,3,1,2,6,4};
		QuickSort.sort(a);
		System.out.println(Arrays.toString(a));
	}
	
	public static void sort(int[] inputArr){
		arr = inputArr;
		doQuickSort(0, arr.length-1);
	}

	private static void doQuickSort(int lo, int hi) {
		
		int i = lo;
		int j = hi;
		int pivot = arr[lo + (hi - lo)/2];
		
		while(i <= j){
			while(arr[i] < pivot) i++;
			while(arr[j] > pivot) j--;
			if(i <= j) swap(i++, j--);
		}
		
		if(i <= hi) doQuickSort(i, hi);
		if(j >= lo) doQuickSort(lo, j);
		
	}

	private static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
