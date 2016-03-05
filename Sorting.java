import java.util.Arrays;

public class Sorting {

	public static void main(String[] args) {
		print("yeah");

		int[] arr = { 6, 5, 4, 2, 1, 3, 7 };
		print("before sort array: " + Arrays.toString(arr));
		print("before sort array size: " + arr.length);
		//bubbleSort(arr);
		// bubbleSort2(arr);
		//selectionSort(arr);
		QuickSort.sort(arr);
		print("After sort array: " + Arrays.toString(arr));
		print("After sort array size: " + arr.length);

	}

	private static void print(String str) {
		System.out.println(str);
	}

	public static void bubbleSort(int[] inputArr) {
		int[] arr = inputArr;
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

	public static void bubbleSort2(int[] inputArr) {
		int[] arr = inputArr;
		int length = arr.length;
		boolean swapped = true;

		while (swapped) {
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

	public static void selectionSort(int[] inputArr) {
		int[] arr = inputArr;
		int length = arr.length;

		for (int i = 0; i < length; i++) {
			for (int k = i; k < length - 1; k++) {
				if (arr[k] < arr[i]) {
					int min = arr[k];
					arr[k] = arr[i];
					arr[i]= min;
				}
			}
		}
	}

}


