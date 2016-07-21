package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(doRange100());
		System.out.println(incrementArrayAsNumberBy1());
		int[][] arr2d = get2dArray();
		int numRows = arr2d.length;
		int numCols = arr2d[0].length;
		spiralPrint(numRows, numCols, arr2d);
		spiralPrint2(arr2d);
		System.out.println("\nEquilibrium index -> " + equilibriumIndex(new int[]{1,2,3,0,3}));
		addTwoArraysAsDigits(new int[]{9,9,5}, new int[]{2,7});
		unitsOfWaterOnIsland(new int[]{});
	}

	/**
	 * Array of Integer 1-100 Return string with ranges that does not exist e.g.
	 * {5,6,80,90} -> "1-4,7-79,81-89,91-100"
	 **/
	private static String doRange100() {
		int[] arr = { 5, 6, 80, 90 };
		Arrays.sort(arr);
		boolean flag = false;
		int prev = 1;
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			int a = arr[i];
			flag = (a - prev) > 1;
			if (i == 0 && a != 1) {
				buffer.append((a == 2 ? "1" : "1-" + (a - 1)));
			} else if (flag) {
				int x = prev + 1;
				int y = a - 1;
				buffer.append(",").append(x - y == 0 ? "" + x : x + "-" + y);
			}

			if (i == arr.length - 1 && a < 100) {
				int x = a + 1;
				int y = 100;
				buffer.append(",").append(x - y == 0 ? "" + x : x + "-" + y);
			}

			prev = a;
		}

		return buffer.toString();
	}

	/**
	 * Given an integer array e.g. [9, 8, 8, 3] where each item in array could
	 * be 0 to 9, interpret the array [9, 8, 8, 3] as a number 9883 and
	 * increment it by 1. return an integer array e.g. [9,8,8,4]. No zeros in
	 * the first position like [0,1,2,3]. No String conversion
	 */
	private static String incrementArrayAsNumberBy1() {
		int[] arr = { 8, 9, 9, 9 };
		List<Integer> list = new ArrayList<>();
		boolean flag = true;

		for (int i = arr.length - 1; i >= 0; i--) {
			int a = arr[i];
			int v = flag ? a + 1 : a;
			flag = v == 10;
			if (i == 0 && flag) {
				list.add(0);
				list.add(1);
			} else if (flag) {
				list.add(0);
			} else if (!flag) {
				list.add(v);
			}
		}

		int index = 0;
		arr = new int[list.size()];
		for (int i = list.size() - 1; i >= 0; i--) {
			arr[index++] = list.get(i);
		}

		return Arrays.toString(arr);
	}

	private static int[][] get2dArray() {

		System.out.println("...Fetching Input Array........");
		int arr[][] = new int[3][6];
		int rows = arr.length;
		int cols = arr[0].length;
		int data = 1;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				arr[r][c] = data++;
				System.out.print(arr[r][c] + " ");
			}
			System.out.println("");
		}

		System.out.println("...Done Fetching Array...");

		return arr;
	}

	/**
	 * print2DArraySpiral 
	 * Input: 
	 * 1 2 3 4 
	 * 5 6 7 8 
	 * 9 10 11 12 
	 * 13 14 15 16 
	 * Output:
	 * 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
	 * {@linkplain http://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/}
	 */
	static void spiralPrint(int m, int n, int a[][])
	{
	    int i, k = 0, l = 0;
	 
	    /*  k - starting row index
	        m - ending row index
	        l - starting column index
	        n - ending column index
	        i - iterator
	    */
	 
	    while (k < m && l < n)
	    {
	        /* Print the first row from the remaining rows */
	        for (i = l; i < n; i++){
	        	System.out.print(" "+ a[k][i]);
	        }
	        k++;
	 
	        /* Print the last column from the remaining columns */
	        for (i = k; i < m; i++){
	        	System.out.print(" "+ a[i][n-1]);
	        }
	        n--;
	 
	        /* Print the last row from the remaining rows */
	        if ( k < m){
	            for (i = n-1; i >= l; i--){
	            	System.out.print(" "+  a[m-1][i]);
	            }
	            m--;
	        }
	 
	        /* Print the first column from the remaining columns */
	        if (l < n){
	            for (i = m-1; i >= k; i--){
	            	System.out.print(" "+ a[i][l]);
	            }
	            l++;    
	        }        
	    }
	}
	
	static void spiralPrint2(int[][] arr){
		

		System.out.print("\n....spiralPrint2....\n");
		
		int r = 0; // row start
		int c = 0; // col start
		int rows = arr.length;
		int cols = arr[0].length;
		
		while(r < rows && c < cols){
			//right
			for(int i=c; i<cols; i++){
				System.out.print(" " + arr[r][i]);
			}
			r++; // done with this row
			
			//down
			for(int i=r; i<rows; i++){
				System.out.print(" " + arr[i][cols-1]);
			}
			cols--; // done with this column
			
			//left
			if(r < rows){
				for(int i=cols-1; i >= c; i--){
					System.out.print(" " + arr[rows-1][i]);
				}
				rows--; // done with this end row
			}
			
			//up
			if(c < cols){
				for(int i = rows-1; i >= r; i--){
					System.out.print(" " + arr[i][c]);
				}
				c++; // done with this start column
			}
		}
	}
	
	// Equilibrium index i has sum of left side values = sum of right side values
	// Returns the first equilibrium index i.
	// e.g. [1,2,3,0,3] returns index 2 because 1+2->3<-0+3
	// Time O(n)
    private static int equilibriumIndex(int[] arr) {
        int n = arr.length;
        if (n==0) return -1; 
        long sum = 0;
        
        for(int i=0;i<n;i++) sum += arr[i]; 
    
        long sum_left = 0;    
        for(int i=0;i<n;i++) {
            long sum_right = sum - sum_left - arr[i];
            if (sum_left == sum_right) return i;
            sum_left += arr[i];
        } 
        return -1; 
    } 
    
    
    /**
     * Add two arrays of digits at each index. Carry over if needed.
     * Returns array as digits only.
     * For e.g. [9,9,5] and [2,7] returns [1,0,2,2] i.e. 995+27=1022
     * */
    private static int[] addTwoArraysAsDigits(int[] arr1, int[] arr2){

//    	arr1 = new int[]{9,9,5};
//    	arr2 = new int[]{2,7};

    	int carry = 0;
    	LinkedList<Integer> linkedList = new LinkedList<>();
    	
    	// start addition from right to left
    	int indexOfArr1 = arr1.length - 1;
    	int indexOfArr2 = arr2.length - 1;
    	
    	while(indexOfArr1 >= 0 || indexOfArr2 >= 0){
    		
    		// Avoid indexOutOfBoundsException
    		int v1 = indexOfArr1 >= 0 ? arr1[indexOfArr1--] : 0;
    		int v2 = indexOfArr2 >= 0 ? arr2[indexOfArr2--] : 0;
    		int sum = v1 + v2 + carry;
    		
    		// if nothing to carry over
    		if(sum < 10){
    			carry = 0;
    			linkedList.addFirst(sum);
    		}else{
    			int a = Integer.parseInt(String.valueOf(sum).charAt(0)+"");
    			int b = Integer.parseInt(String.valueOf(sum).charAt(1)+"");
    			carry = a;
    			linkedList.addFirst(b);
    		}
    		
    	}
    	
    	if(carry > 0) linkedList.addFirst(carry);
    	
    	System.out.println(linkedList.toString());
    	
    	// Just return array, let's reuse arr1 object.
    	arr1 = new int[linkedList.size()];
    	for(int i=0 ; i < linkedList.size(); i++){
    		arr1[i] = linkedList.get(i);
    	}
    	
    	System.out.println(Arrays.toString(arr1));
    	
    	return arr1;
    	
    }
    
    /**
     *      x o x
     *	  x x x x o x
 	 *	x x x x x x x x    
	 *	1,2,3,2,3,1,2,1 -> 2 units of water
	 *
	 *   	x o x
   	 *		x o x
 	 *	  x x x x     
 	 *    1,3,1,3 -> 2 units of water
	 *
	 *	Find number of units of water on an island
	 *
	 *  Time O(n)  Space O(1)
     * 
     * */
    private static int unitsOfWaterOnIsland(int[] arr){

    	//arr = new int[]{1,2,3,2,3,1,2,1};
    	arr = new int[]{1, 3, 1, 3};

    	int peak = 0; 
    	int left = 0;
    	int right = arr.length - 1;
    	int ans = 0;
    	
    	while(left < right){
    		if(arr[left] < arr[right]){
    			peak = Math.max(peak, arr[left]);
    			ans += peak - arr[left++];
    		}else{
    			peak = Math.max(peak, arr[right]);
    			ans += peak - arr[right--];
    		}
    	}
    	
    	System.out.println("max units of water -> " + ans);
    	
    	return ans;
    }

}// End of class
