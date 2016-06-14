package sort;

import java.util.Arrays;

public class SelectionSort {

	public static void main(String[] args) {
		int[] a = {5,3,1,2,6,4};
		BubbleSort.sort(a);
		System.out.println(Arrays.toString(a));
	}
	
	public static void sort(int[] arr){
		
		int min;
		
		for (int i = 0; i < arr.length; i++) {
			min = i;
			for (int j = i+1; j < arr.length-1; j++) {
				if(arr[j] > arr[min]){
					int temp = arr[j];
					arr[j] = arr[min];
					arr[min] = temp;
				}
			}
		}
	}
}


