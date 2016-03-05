import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SortMethod {

	public static void main(String[] args) {
		System.out.println(SortMethod.class.getSimpleName());

		int[] arr = { 6, 5, 4, 2, 1, 3, 7, 1 };
		System.out.println("before sort array: " + Arrays.toString(arr));
		System.out.println("before sort array size: " + arr.length);
		// quickSort(arr);
		selectionSort(arr);
		System.out.println("After  sort array: " + Arrays.toString(arr));
		System.out.println("After  sort array size: " + arr.length);

		System.out.println("Fibtest " + fiboTest(0,1,3));
		System.out.println("math pow " + (Math.pow(2, 16)));
		String str = "abc";
		int l = str.length();
		System.out.println("substringTest " + str.substring(1, 3));
	}

	private static int[] arr, helper;
	private static int length;

	// Merge Sort start
	public static void mergeSort(int[] input) {
		arr = input;
		length = arr.length;
		helper = new int[length];
		doMergeSort(0, length - 1);
	}

	private static void doMergeSort(int lo, int hi) {
		if (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			doMergeSort(lo, mid); // left
			doMergeSort(mid + 1, hi); // right
			doMergeAll(lo, mid, hi); // merge all
		}
	}

	private static void doMergeAll(int lo, int mid, int hi) {
		// backup
		for (int i = 0; i < length; i++)
			helper[i] = arr[i];

		int i = lo;
		int j = mid + 1;
		int k = lo;

		while (i <= mid && j <= hi) {
			arr[k++] = (helper[i] < helper[j]) ? helper[i++] : helper[j++];
		}
		// rest of left side
		while (i <= mid) {
			arr[k++] = helper[i++];
		}
	}
	// Merge Sort end

	// QuickSort Start
	public static void quickSort(int[] input) {
		arr = input;
		length = arr.length;
		doQuickSort(0, length - 1);
	}

	private static void doQuickSort(int lo, int hi) {
		int i = lo;
		int j = hi;
		int pivot = arr[(lo + hi) / 2];

		while (i <= j) {
			while (arr[i] < pivot)
				i++;
			while (arr[j] > pivot)
				j--;
			if (i <= j)
				swap(i++, j--);
		}
		// recursion here
		if (i < hi)
			doQuickSort(i, hi);
		if (j > lo)
			doQuickSort(lo, j);
	}

	private static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	// QuickSort End

	// BubbleSort Start
	public static void bubbleSort(int[] arr) {
		int length = arr.length;
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for (int i = 0; i < length - 1; i++) {
				if (arr[i] > arr[i + 1]) {
					int temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
					swapped = true;
				}
			}
		}
	}

	public static void selectionSort(int[] arr) {
		int length = arr.length;

		for (int k = 0; k < length; k++) {
			for (int i = 0; i < length - 1; i++) {
				if (arr[k] < arr[i]) {
					int temp = arr[k];
					arr[k] = arr[i];
					arr[i] = temp;
				}
			}
		}
	}
	// BubbleSort End

	// FiboTest
	public static int fiboTest(int a, int b, int limit) {

		System.out.print(a + " ");
		
		if(limit < 0) return a;
		
		return fiboTest(b, b+a, --limit);
	}
	
	public static int combin(int n,int m){
	//	if()
		return 0;
	}

}
