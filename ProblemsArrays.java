import java.util.Arrays;

public class ProblemsArrays {

	public static void main(String[] args) {
		System.out.println(ProblemsArrays.class.getSimpleName());
		println("testing");
		println("reOrderArray: " + reOrderArray());
		println("customSortArray: " + customSortArray());
		println("reOrderArrayWithoutCopying: " + reOrderArrayWithoutCopying());

	}

	public static void println(String s) {
		System.out.println(s);
	}

	public static void print(String s) {
		System.out.print(s);
	}

	// Suppose there were n numbers in an
	// array S1, S2, S3, S4.......SN rearrange them in a such a way that they
	// satisfy bellow
	// property. S1<S2>S3<S4>
	public static String reOrderArray() {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
		Arrays.sort(arr);
		for (int i = 1; i < arr.length - 1; i += 2) {
			int temp = arr[i];
			arr[i] = arr[i + 1];
			arr[i + 1] = temp;
		}

		return Arrays.toString(arr);
	}

	// Give you an array which has n integers,it has both positive and negative
	// integers.
	// Now you need sort this array in a special way.After that,the negative
	// integers should
	// in the front,and the positive integers should in the back.Also the
	// relative position
	// should not be changed.
	// eg. -1 1 3 -2 2 ans: -1 -2 1 3 2.
	// o(n)time complexity and o(1) space complexity is perfect.
	public static String customSortArray() {
		int[] arr = { -1, 1, 3, -2, 2 };
		int length = arr.length;
		int[] arrNegative = new int[length];
		int[] arrPositive = new int[length];
		int negativeCount = 0;
		int positiveCount = 0;

		for (int i = 0; i < length; i++) {
			if (arr[i] < 0) {
				arrNegative[negativeCount++] = arr[i];

			}
		}

		for (int i = 0; i < length; i++) {
			if (arr[i] >= 0) {
				arrPositive[positiveCount++] = arr[i];
			}
		}

		int k = 0;
		for (int i = 0; i < length; i++) {
			if (i < negativeCount) {
				arr[i] = arrNegative[i];
			} else {
				arr[i] = arrPositive[k++];
			}
		}

		arrNegative = arrPositive = null;

		return Arrays.toString(arr);
	}

	// input [0,1,3,4,8,2,0,0,0,4,5];
	// output [1,3,4,8,2,4,5,00000];
	public static String reOrderArrayWithoutCopying() {
		int[] arr = { 0, 1, 3, 4, 8, 2, 0, 0, 0, 4, 5 };
		int zeroIndex = 0;
		int nonZeroIndex = 0;

		println(Arrays.toString(arr));

		for (int i = 0; i <  arr.length -1; i++) {
			if (arr[i] == 0 && arr[i + 1] != 0) {
				swap(arr, i, i+1);
			}
		}
		
		for (int i = arr.length -1; i >= 1  ; i--) {
			if (arr[i] != 0 && arr[i - 1] == 0) {
				swap(arr, i, i-1);
			}
		}
		

		return Arrays.toString(arr);
	}


	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
