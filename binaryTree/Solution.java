package binaryTree;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/swap-nodes-algo
 * **/
public class Solution {
	
	static int N, T, K;
	static final int NMAX = 1024; 
	static final int TMAX = 100; 
	static int[] left = new int[NMAX + 1];
	static int[] right = new int[NMAX + 1];
	static int[] depth = new int[NMAX + 1];
	private static Scanner in;
	
	
	// Hint. Validate N, T, K
	
	 public static void main(String[] args) {
	        /* Enter your code here. Read input from STDIN. Print output to 
	         * STDOUT. Your class should be named Solution. */
		 System.out.println(Solution.class.getSimpleName());
		 
		 in = new Scanner(System.in);
		 
		 // First line of input contains N, number of nodes in tree
		 System.out.print("Enter no. of nodes N: ");
		 N = in.nextInt();
		 System.out.println(N);
		 
		 // Then N lines follow. Here each of ith line (1 <= i <= N) contains two integers, a b, 
		 // where a is the index of left child, and b is the index of right child of ith node. 
		 // -1 is used to represent null node.
		 
		 for(int i = 1; i <= N; i++){
			 int a = in.nextInt();
			 int b = in.nextInt();
			 left[i] = a;
			 right[i] = b;
			 System.out.println("");
		 }
		 

		 // Next line contain an integer, T. 
		 // Then again T lines follows. 
		 // Each of these line contains an integer K.
		 
		 // Next line contain an integer, T. 
		 System.out.print("\nEnter an integer T: ");
		 T = in.nextInt();

		 
		 
		 // Calculate the depths ahead of time starting
		 // root node 1 = depth 1
		 getDepth(1, 1);
		 
		 // Then again T lines follows. 
		 for(int i = 1; i <= T; i++){
			 
			 // Each of these line contains an integer K. 
			 // Constraints 1 <= K <= N 
			 System.out.print("\nEnter an integer K: ");
			 K = in.nextInt();
			 
			 // Swap entire tree at depth h ∈ [K, 2K, 3K,...]
			 // i.e. depth h that is a multiple of K
			 
			 // Search each node's depth in the tree
			 // swap subtree if necessary
			 for(int j = 1; j <= N; j++){
				 int h = depth[j];
				 if(h % K == 0){
					 // swap subTree of j	 
					 swap(j);
				 }
			 }
			 
			 

			 // Output Format - For each K, perform swap operation as mentioned above 
			 // and print the inorder traversal of the current state of tree.
			 inOrder(1);
		 }
		 
	 
	 }
	 
	 private static void getDepth(int index, int d){
		 depth[index] = d;
		 if(left[index] > 0) getDepth(left[index], depth[index] + 1);
		 if(right[index] > 0) getDepth(right[index], depth[index] + 1);
	 }
	 
	 private static void inOrder(int index){
		 if(index > 0){
			 inOrder(left[index]); 
			 System.out.print(index + " ");
			 inOrder(right[index]); 
		 }
	 }
	 
	 private static void swap(int h){
		 int temp = left[h];
		 left[h] = right[h];
		 right[h] = temp;
	 }
}
