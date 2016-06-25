package zzz;

public class Test2 {

	public static void main(String[] args) {
		int x1 = 0;
		int y1 = 0;
		int x2 = 1;
		int y2 = 1;
		System.out.println(dynamicPaths(x1, y1, x2, y2, 2));
		int[] arr = {0,3,5,6};
		//while(arr[i])
	}
 
	public static int dynamicPaths(int x1, int y1, int x2, int y2, int n) {
		//int[][] array = new int[x2 - x1 + 1][y2 - y1 + 1];
		int[][] array = new int[n][n];
		array[0][0] = 0;
 
		for (int i = 0; i < x2 - x1; i++) {
			array[i][0] = 1;
		}
 
		for (int i = 0; i < y2 - y1; i++) {
			array[0][i] = 1;
		}
 
		for (int i = 1; i < x2 - x1 + 1; i++) {
			for (int j = 1; j < y2 - y1 + 1; j++) {
				array[i][j] = array[i][j - 1] + array[i - 1][j];
			}
		}
		return array[x2 - x1 ][y2 - y1 ];
	}
	
	public static int dynamicPaths22(int x1, int y1, int x2, int y2) {
		int[][] array = new int[x2 - x1 + 1][y2 - y1 + 1];
		//int[][] array = new int[11][11];
		array[0][0] = 0;
 
		for (int i = 1; i < x2 - x1 + 1; i++) {
			array[i][0] = 1;
		}
 
		for (int i = 1; i < y2 - y1 + 1; i++) {
			array[0][i] = 1;
		}
 
		for (int i = 1; i < x2 - x1 + 1; i++) {
			for (int j = 1; j < y2 - y1 + 1; j++) {
				array[i][j] = array[i][j - 1] + array[i - 1][j];
			}
		}
		return array[x2 - x1 ][y2 - y1 ];
	}
}
