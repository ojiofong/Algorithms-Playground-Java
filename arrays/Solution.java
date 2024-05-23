package arrays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("doRange100 -> " + doRange100());
		// System.out.println("incrementArrayAsNumberBy1 -> " +
		// incrementArrayAsNumberBy1(new int[2]));
		// System.out.println("\nEquilibrium index -> " + equilibriumIndex(new
		// int[] { 1, 2, 3, 0, 3 }));
		// addTwoArraysAsDigits(new int[] { 9, 9, 5 }, new int[] { 2, 7 });
		// unitsOfWaterOnIsland(new int[] {});
		// combinationsOfSizeR(null, 0, 0, null, 0);
		// seperateEvenOdd(null);
		// reverseWithoutSpecialChar(null);
		
		System.out.print("" + (int)'a');
	}

	/*-
	 * Given an Array of Integer 1-100. 
	 * Return individual numbers or ranges that do not exist e.g.
	 * {5,6,80,90} -> "1-4,7-79,81-89,91-100"
	 * {1,3,99} -> "2,4-98,100"
	 **/
	private static String doRange100() {
		int[] arr = { 5, 6, 80, 90 };
		Arrays.sort(arr);

		StringBuilder sb = new StringBuilder();
		int size = arr.length;
		int prev = 0;

		for (int i = 0; i < size; i++) {

			int a = arr[i];
			boolean isFirstIndex = i == 0;
			boolean isLastIndex = i == size - 1;

			if (isFirstIndex) {
				int diff = a - 1;
				if (diff == 2) {
					sb.append(String.format("%d,", a - 1));
				} else if (diff > 2) {
					sb.append(String.format("1-%d,", a - 1));
				}
			} else {
				int diff = a - prev;
				if (diff == 2) {
					sb.append(String.format("%d,", a - 1));
				} else if (diff > 2) {
					sb.append(String.format("%d-%d,", prev + 1, a - 1));
				}
			}

			if (isLastIndex) {
				int diff = 100 - a;
				if (diff == 1) {
					sb.append(String.format("%d,", 100));
				} else if (diff == 2) {
					sb.append(String.format("%d,", 99));
				} else if (diff > 2) {
					sb.append(String.format("%d-%d,", a + 1, 100));
				}
			}

			prev = a;
		}

		// delete last comma ","
		return sb.deleteCharAt(sb.length() - 1).toString();

	}

	/**
	 * Given an integer array e.g. [9, 8, 8, 3] where each item in array could
	 * be 0 to 9, interpret the array [9, 8, 8, 3] as a number 9883 and
	 * increment it by 1. return an integer array e.g. [9,8,8,4]. No zeros in
	 * the first position like [0,1,2,3]. No String conversion
	 */
	private static String incrementArrayAsNumberBy1(int[] arr) {
		arr = new int[] { 8, 9, 9, 9 };

		// LinkedList 0(1) insertion
		// Be sure to add to first or queue to top
		LinkedList<Integer> linkedList = new LinkedList<>();
		int carry = 0; // holds the value to carry over
		int lastIndex = arr.length - 1;
		// start from last index length-1;
		for (int i = lastIndex; i >= 0; i--) {
			// add one at last index only
			int sum = (i == lastIndex) ? arr[i] + 1 + carry : arr[i] + carry;

			if (sum < 10) {
				linkedList.addFirst(sum);
				carry = 0;
			} else {
				// incrementing digit of ONLY LAST INDEX by 1 guarantees sum
				// can't be > 10 i.e. 9+1 = 10, so add zero and carry one
				carry = 1;
				linkedList.addFirst(0);
			}
		}

		// if we have something carried then add it
		if (carry > 0)
			linkedList.addFirst(carry);

		return linkedList.toString();

	}

	/**
	 * Equilibrium index i has sum of left side values = sum of right side
	 * values Returns the first equilibrium index i. e.g. [1,2,3,0,3] returns
	 * index 2 because 1+2->3<-0+3 Time O(n)
	 */
	private static int equilibriumIndex(int[] arr) {
		int n = arr.length;
		if (n <= 0)
			throw new IllegalArgumentException();
		;
		long totalSum = 0;

		for (int i = 0; i < n; i++)
			totalSum += arr[i];

		long sumLeft = 0;
		for (int i = 0; i < n; i++) {
			long sumRight = totalSum - sumLeft - arr[i];
			if (sumLeft == sumRight)
				return i;
			sumLeft += arr[i];
		}

		return -1;
	}

	/**
	 * Add two arrays of digits at each index. Carry over if needed. Returns
	 * array as digits only. For e.g. [9,9,5] and [2,7] returns [1,0,2,2] i.e.
	 * 995+27=1022
	 */
	private static String addTwoArraysAsDigits(int[] arr1, int[] arr2) {

		// arr1 = new int[]{9,9,5};
		// arr2 = new int[] { 2, 2 };

		int carry = 0;
		LinkedList<Integer> linkedList = new LinkedList<>();

		// start addition from right to left
		int indexOfArr1 = arr1.length - 1;
		int indexOfArr2 = arr2.length - 1;

		while (indexOfArr1 >= 0 || indexOfArr2 >= 0) {

			// Avoid indexOutOfBoundsException
			int v1 = indexOfArr1 >= 0 ? arr1[indexOfArr1--] : 0;
			int v2 = indexOfArr2 >= 0 ? arr2[indexOfArr2--] : 0;
			int sum = v1 + v2 + carry;

			// if nothing to carry over
			if (sum < 10) {
				carry = 0;
				linkedList.addFirst(sum);
			} else {
				carry = 1;
				linkedList.addFirst(sum - 10);
			}

		}

		if (carry > 0)
			linkedList.addFirst(carry);

		System.out.println("addTwoArraysAsDigits-> " + linkedList.toString());

		return linkedList.toString();

	}

	// x o x
	// x x x x o x
	// x x x x x x x x
	// 1,2,3,2,3,1,2,1 -> 2 units of water
	// =================
	// x o x
	// x o x
	// x x x x
	// 1,3,1,3 -> 2 units of water
	// ==================
	// Find number of units of water on an island
	// Time O(n) Space O(1)
	private static int unitsOfWaterOnIsland(int[] arr) {

		// arr = new int[]{1,2,3,2,3,1,2,1};
		arr = new int[] { 3, 1, 2, 3 };

		int peak = 0;
		int left = 0;
		int right = arr.length - 1;
		int ans = 0;

		while (left < right) {
			if (arr[left] < arr[right]) {
				peak = Math.max(peak, arr[left]);
				ans += peak - arr[left++];
			} else {
				peak = Math.max(peak, arr[right]);
				ans += peak - arr[right--];
			}
		}

		System.out.println("max units of water -> " + ans);

		return ans;
	}


        // TODO: CONVERT TO JAVA AND CLEANUP.
	// Container With Most Water
	// https://leetcode.com/problems/container-with-most-water/description/
       func maxWaterArea(_ height: [Int]) -> Int {
	        var maxArea = 0
	        var left = 0
	        var right = height.count - 1
	        
	        // Two pointers approach:
	        while left < right {
	            // Calculate the current area based on the shorter height
	            let currentArea = (right - left) * min(height[left], height[right])
	            maxArea = max(maxArea, currentArea)  // Update maxArea if needed
	            
	            // skip the smaller heights
	            if height[left] < height[right] {
	                left += 1
	            } else {
	                right -= 1
	            }
	        }
	        
	        return maxArea
     }

	// Kadane's algorithm algorithm relies on at least one positive number
	// if all negative return the max negative value
	// This method assumes there's at least one positive number
	public static int maxSubArray(int[] arr) {

		int negativeMax = Integer.MIN_VALUE;
		if (isAllNegative(arr)) {
			// return the largest negative
			for (int i : arr) {
				negativeMax = Math.max(negativeMax, i);
			}
			return negativeMax;
		}

		int length = arr.length;

		int sumCurrent = 0;
		int sumMaxSub = 0;

		for (int i = 0; i < length; i++) {
			sumCurrent += arr[i];
			sumCurrent = Math.max(0, sumCurrent); // return zero if negative
			sumMaxSub = Math.max(sumCurrent, sumMaxSub); // get the max only

		}

		return sumMaxSub;
	}

	private static boolean isAllNegative(int[] arr) {
		for (int i : arr) {
			if (i > 0)
				return false;
		}
		return true;
	}

	/**
	 * For N number of input arrays. Find the intersection of N-integer arrays.
	 */
	public static Integer intersectionOfNIntegerArrays(List<Integer[]> list) {
		if (list == null || list.isEmpty())
			return 0;

		int listIndex = 0;
		int i = 0;
		Integer prev = null;
		while (listIndex < list.size()) {
			int t = list.size() - 1;
			while (t-- >= 0) {
				Integer[] arr = list.get(t);

			}

		}

		return 0;
	}

	/*-
	 * Generate all combinations of size r in arr[] of size n:
	 * 
	    char[] arr = new int[] { 1, 2, 3, 4, 5 };
	    int r = 3;
	    int n = arr.length;
	    char[] data = new char[r];
	    
	 *  outputs: 123, 124, 125, 134...345
	 *  
	 *  Time: O(nCr * r)
         *  Space: O(r) for the output array 
	 */
	static void combinationOfSizeR(int r, char[] input, char[] output, int inputIndex, int outputIndex){
		if (outputIndex == r){
			System.out.println(Arrays.toString(output));
			return;
		}

		if (inputIndex >= input.length) return; // out of bounds

		output[outputIndex] = input[inputIndex];
		combinationOfSizeR(r, input, output , inputIndex + 1, outputIndex + 1);
		combinationOfSizeR(r, input, output , inputIndex + 1, outputIndex);
	}

	 /*-
	   input: "aab".toCharArray();
	    stdout:
	    a a b 
	    a ab  
	    aa b  
	    aab 
	    
	    char[] input = s.toCharArray();
	    char[] output = new char[input.length * 2]; // double size to allow for spacing
	    substringCombinations(input, 0, output, 0);
	    
	    Time O(2^n)
	    Space O(2^n) needed to save every output string
	  */
	static void substringCombinations(char[] input, char[] output, int inputIndex, int outputIndex) {
	    if (input.length == inputIndex){
	        System.out.println(String.valueOf(output));
	        return;
	    }
	     
	    output[outputIndex] = input[inputIndex]; // place current digit in input string
	    output[outputIndex + 1] = ' '; // separate next digit with a space

	    substringCombinations(input, output, inputIndex + 1,  outputIndex + 2);
	    if(input.length != inputIndex + 1){
	        substringCombinations(input, output, inputIndex + 1, outputIndex + 1); 
	    }   
	}

	/*-
	Input: 17->15->8->12->10->5->4->1->7->6->NULL
	Output: 8->12->10->4->6->17->15->5->1->7->NULL
	**/
	private static void seperateEvenOdd(int[] arr) {

		arr = new int[] { 17, 15, 8, 12, 10, 5, 4, 1, 7, 6 };
		System.out.println("input: " + Arrays.toString(arr));

		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 == 0) {
				moveToIndex(arr, i, index++);
			}
		}
		System.out.println("output: " + Arrays.toString(arr));

	}

	private static void moveToIndex(int[] arr, int i, int index) {
		int temp = arr[i];
		for (int k = i; k >= index + 1; k--) {
			arr[k] = arr[k - 1];
		}
		arr[index] = temp;
	}

	/*-
	 * 	Reverse an array without affecting special characters
	 * 	Input:   str = "a,b$c"
	 *	Output:  str = "c,b$a"
	 *	Note that $ and , are not moved anywhere.  
	 *	Only subsequence "abc" is reversed
	 *
	 *	Input:   str = "Ab,c,de!$"
	 *	Output:  str = "ed,c,bA!$"
	 */
	private static String reverseWithoutSpecialChar(String str) {

		str = "Ab,c,de!$";

		if (str == null || str.isEmpty())
			return str;

		char[] arr = str.toCharArray();
		int i = 0;
		int j = arr.length - 1;

		while (i < j) {

			if (isCharAlphanumeric(arr[i]) && isCharAlphanumeric(arr[j])) {
				// swap
				char temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}

			if (!isCharAlphanumeric(arr[i]))
				i++;
			if (!isCharAlphanumeric(arr[j]))
				j--;

		}

		String output = new String(arr);

		System.out.println(String.format("reverseWithoutSpecialChar Input: %s Output: %s", str, output));

		return output;
	}

	private static boolean isCharAlphanumeric(char c) {
		boolean isAlphaNumeric = (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
		// System.out.println(String.format("isAphanumeric %s->%s", c,
		// isAlphaNumeric));
		return isAlphaNumeric;
	}
	
	// Rotate array e.g. 12345->23451->34512
	static String rotate() {
		int[] arr = { 1, 2, 3, 4, 5 };
		int n = arr.length;
		int d = 1; // number of rotations
		for (int i = 0; i < n; i++) {
			arr[(i + n - d) % n] = i+1;
		}

		return Arrays.toString(arr);

	}

	/**
 	Longest Consecutive Sequence
	Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
	You must write an algorithm that runs in O(n) time.
	Example:
	Input: nums = [100,4,200,1,3,2]
	Output: 4
	Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 	*/
	public int longestConsecutive(int[] nums) {
	        Set<Integer> fullSet = new HashSet<>();
	        Set<Integer> startOfSequenceSet = new HashSet<>();
	        int longest = 0;

		// We need the fullSet for O(1) lookup
	        for (int value: nums){
	            fullSet.add(value);
	        }
	        
	        for (int value: fullSet){
		    // Check if value is the start a sequence. i.e. no other value before it, hence (value - 1)
	            if (!fullSet.contains(value - 1)){
	                startOfSequenceSet.add(value);
	            }
	        }
	        
	        // We need startOfSequenceSet since it contains only values that start each sequence 
		// startOfSequenceSet excludes other sequence values, hence guaranteeing linear time 
		// by preventing O(n^2) time in the nested while loop that counts the sequences.
	        for (int value: startOfSequenceSet){
	            int curValue = value;
	            int sequenceCount = 1;
	            
	            while (fullSet.contains(curValue + 1)){
	                curValue += 1;
	                sequenceCount += 1;
	            }
	            
	            longest = Math.max(longest, sequenceCount);
	        }
	        return longest;
    }

   /**
   	Merge Sorted Array 
	You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
	
	Merge nums1 and nums2 into a single array sorted in non-decreasing order.
	
	The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
	
	Example 1:
	Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
	Output: [1,2,2,3,5,6]
	
	Example 2:
	Input: nums1 = [1], m = 1, nums2 = [], n = 0
	Output: [1]
	
	Example 3:
	Input: nums1 = [0], m = 0, nums2 = [1], n = 1
	Output: [1]
   */
   public void mergeSortedArray(int[] nums1, int m, int[] nums2, int n) {
        // Pointer for the end of the merged array
        int i = m - 1; // End of nums1 array that is filled
        int j = n - 1; // End of nums2
        int k = m + n - 1; // End of merged array
        
        // Merge in reverse order
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        // Fill nums1 with remaining nums2 elements
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }


   /**
    Median of Two Sorted Arrays
    
    Example 1:
    Input: nums1 = [1,3], nums2 = [2]
    Output: 2.00000
    Explanation: merged array = [1,2,3] and median is 2.
    
    Example 2:
    Input: nums1 = [1,2], nums2 = [3,4]
    Output: 2.50000
    Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
    
    https://leetcode.com/problems/median-of-two-sorted-arrays/
    */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Key is to know how to merge 2 sorted arrays
        // create a new array with combined size m + n
        // find the median index for even and odd lengths
        // O(m+n) time and space due to the combined merged array
        
        Integer[] merged = new Integer[nums1.length + nums2.length];
        
        // copy all nums1
        for (int i=0; i<nums1.length; i++){
            merged[i] = nums1[i];
        }
        
        // do sorted merging
        int i = nums1.length - 1;
        int j = nums2.length - 1;
        int k = merged.length - 1;
        
        while (i>=0 && j>=0){
            if (nums1[i] > nums2[j]){
                merged[k] = nums1[i];
                i--;
                k--;
            }else{
                merged[k] = nums2[j];
                j--;
                k--;
            }
        }
        
        while (j>=0){
            merged[k] = nums2[j];
            j--;
            k--;
        }
        
        // done merging the sorted arrays
        System.out.println(Arrays.toString(merged));
        
        if (merged.length % 2 != 0){
            // odd 
            int medianIndex = merged.length/2;
            return merged[medianIndex];
        }{
            // even
            int medianIndex1 = merged.length/2;
            int medianIndex2 = medianIndex1 - 1;
            return (merged[medianIndex1] + merged[medianIndex2]) / 2.0;
        }
        
    }

 /**
    Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

    You must write an algorithm with O(log n) runtime complexity.

    Example 1:
    Input: nums = [1,3,5,6], target = 5
    Output: 2
    
    Example 2:
    Input: nums = [1,3,5,6], target = 2
    Output: 1
    */
    public int searchInsertIntoArray(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        
        while (lo <= hi){
            int mid = (hi + lo)/2;
            if (target < nums[mid]){
                hi = mid - 1;
            }else if (target > nums[mid]) {
                lo = mid + 1;
            }else {
                return mid;
            }
        }
        
        // If we exit the loop, 'lo' is the position where the target should be inserted
        // because 'lo' is the index of the smallest element greater than the target
        return lo;
    }

}// End of class
