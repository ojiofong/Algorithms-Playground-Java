package arrays;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Multi-dimensional arrays
 */
public class SolutionMultiDimen {
	public static void main(String[] args) {
		swapBitmapColors(new int[3][5], 1, 1, 2);
		spiralPrint(new int[3][3]);
	}

	private static void printArray(int[][] arr) {
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[0].length; c++) {
				System.out.print(arr[r][c] + " ");
			}
			System.out.println("");
		}
	}

	private static int[][] getTestArr() {
		int[][] arr = new int[3][5];
		arr[0][0] = 2;
		arr[0][1] = 2;
		arr[0][2] = 1;
		arr[0][3] = 1;
		arr[0][4] = 0;
		// -----------
		arr[1][0] = 1;
		arr[1][1] = 2;
		arr[1][2] = 2;
		arr[1][3] = 1;
		arr[1][4] = 0;
		// -----------
		arr[2][0] = 1;
		arr[2][1] = 1;
		arr[2][2] = 1;
		arr[2][3] = 0;
		arr[2][4] = 0;
		// -----------

		return arr;

	}

	// Given arr1 points (1,1) & color 2 return arr2.
	// Note - check if neighbors have same color
	// 22110
	// 12210
	// 11100
	// =========== arr1
	// 00110
	// 10010
	// 11100
	// ========== arr2
	// Solution - 0(n) time and 0(n) space
	private static void swapBitmapColors(int[][] arr, int x, int y, int color) {

		// sample test array
		arr = getTestArr();

		System.out.println("Before color swapping");
		printArray(arr);

		if (arr.length <= 0 || arr[0].length <= 0)
			throw new IllegalArgumentException();

		// using BFS to search for neighbors that have same color
		Queue<Node> q = new LinkedList<>();
		if (isInBoundsAndColorMatch(arr, x, y, color)) {
			q.add(new Node(x, y));
		}

		while (!q.isEmpty()) {
			Node node = q.remove();
			// swap out color to zero
			// sufficient to indicate visited
			arr[node.x][node.y] = 0;

			// left
			if (isInBoundsAndColorMatch(arr, node.x, node.y - 1, color)) {
				q.add(new Node(node.x, node.y - 1));
			}

			// right
			if (isInBoundsAndColorMatch(arr, node.x, node.y + 1, color)) {
				q.add(new Node(node.x, node.y + 1));
			}

			// up
			if (isInBoundsAndColorMatch(arr, node.x - 1, node.y, color)) {
				q.add(new Node(node.x - 1, node.y));
			}

			// down
			if (isInBoundsAndColorMatch(arr, node.x + 1, node.y, color)) {
				q.add(new Node(node.x + 1, node.y));
			}

			// ------- new Level --------- //
		}

		System.out.println("After color swapping");
		printArray(arr);

	}

	private static boolean isInBounds(int[][] arr, int x, int y) {
		boolean xInRange = x >= 0 && x < arr.length;
		boolean yInRange = y >= 0 && y < arr[0].length;
		return xInRange && yInRange;
	}

	private static boolean isInBoundsAndColorMatch(int[][] arr, int x, int y, int color) {
		boolean xInRange = x >= 0 && x < arr.length;
		boolean yInRange = y >= 0 && y < arr[0].length;
		return xInRange && yInRange && arr[x][y] == color;
	}

	private static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	/**
	 * print2DArraySpiral Input: 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 Output:
	 * 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
	 * {@linkplain http://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/}
	 */
	static void spiralPrint(int[][] arr) {

		System.out.print("\n....spiralPrint....\n");

		int r = 0; // row start
		int c = 0; // col start
		int rows = arr.length;
		int cols = arr[0].length;

		while (r < rows && c < cols) {
			// right
			for (int i = c; i < cols; i++) {
				System.out.print(" " + arr[r][i]);
			}
			r++; // done with this row

			// down
			for (int i = r; i < rows; i++) {
				System.out.print(" " + arr[i][cols - 1]);
			}
			cols--; // done with this column

			// left
			if (r < rows) {
				for (int i = cols - 1; i >= c; i--) {
					System.out.print(" " + arr[rows - 1][i]);
				}
				rows--; // done with this end row
			}

			// up
			if (c < cols) {
				for (int i = rows - 1; i >= r; i--) {
					System.out.print(" " + arr[i][c]);
				}
				c++; // done with this start column
			}
		}
	}
	
	

} // End of class
