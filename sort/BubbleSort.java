package sort;

import java.util.Arrays;

public class BubbleSort {
	
	public static void main(String[] args) {
		int[] a = {5,3,1,2,6,4};
		BubbleSort.sort(a);
		System.out.println(Arrays.toString(a));
	}
	
	public static void sort(int[] arr){
		boolean flag = true;
		while(flag){
			flag = false;
			for(int i=0; i<arr.length-1; i++){
				if(arr[i] > arr[i+1]){
					int temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
					flag = true;
				}
			}
		}
	}

}


