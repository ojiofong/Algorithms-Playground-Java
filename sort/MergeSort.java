package sort;

import java.util.Arrays;

public class MergeSort {
	static int[] arr, helper;
	static int size;
	
	public static void main(String[] args){
		int[] a = {5,3,1,2,6,4};
		MergeSort.sort(a);
		System.out.println(Arrays.toString(a));
	}
	
	public MergeSort(){}
	
	public static void sort(int[] inputArr){
		arr = inputArr;
		doMergeSort(0, arr.length-1);
	}

	private static void doMergeSort(int lo, int hi) {
		if(lo < hi){
			int mid = lo + (hi-lo)/2;
			doMergeSort(lo, mid); // left
			doMergeSort(mid+1, hi); // right
			doMergeAll(lo, mid, hi);
		}
		
	}

	private static void doMergeAll(int lo, int mid, int hi) {
		helper = Arrays.copyOf(arr, arr.length);
		int i = lo;
		int j = mid + 1;
		int k = lo;
		
		while(i <= mid && j <= hi){
			arr[k++] = helper[i] < helper[j] ? helper[i++] : helper[j++];
		}
		
		while(i <= mid){
			arr[k++] = helper[i++];
		}
		
	}
	
	

}
