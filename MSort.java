import java.util.Arrays;

public class MSort {

	public static void main(String[] args) {
		// System.out.println("good");
		int[] a = { 5, 7, 1, 3, 4, 9, 11, 2, 3 };
		System.out.println("before: " + Arrays.toString(a));
		bubbleSort(a);
		System.out.println("After: " + Arrays.toString(a));
	}

	private static void bubbleSort33(int[] arr) {
		int length = arr.length;

		for (int i = 0; i < length; i++) {
			// -1 prevents index out of bounds exception in arr[k+1]
			for (int k = 0; k < (length -1); k++) {
				if (arr[k] > arr[k+1]) {
					int temp = arr[k];
					arr[k] = arr[k+1];
					arr[k+1] = temp;
				}
			}
		}

	}

	private static void bubbleSort(int[] arr) {
	
		int length = arr.length;
		boolean flag = true; // to all first pass
		
		while(flag){
			flag = false;
			for(int k = 0; k < length -1; k++){
				if (arr[k] > arr[k+1]) {
					int temp = arr[k];
					arr[k] = arr[k+1];
					arr[k+1] = temp;
					flag = true;
				}
			}
		}
	}

}
