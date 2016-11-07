package zzz;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import linkedlists.Solution;
import linkedlists.Solution.Node;

public class Test2 {

	public static void main(String[] args) {
		// solve(null, null);
//		System.out.println(Test2.class.getSimpleName());
//		allCase("", "abc");
//		System.out.println("");
//		permu("", "abc");
//		System.out.println("");
//		allSubSequence("abc");
//		System.out.println("");
//		combination("abcd");
//		Node n = merge(Solution.getLinkedNodes(), Solution.getLinkedNodes());
//		Solution.printLinkedNodes(n);
//		Solution.printLinkedNodes(removeDuplicates(n));
		oddEven(Solution.getLinkedNodes());
		

	}
	
	private static void oddEven(Node head){
		System.out.print("oddEven");
		Solution.printLinkedNodes(head);
		
		Node cur = head;
		Node lastEven = null;
		Node prev = null;
		Node firstOdd = null;
		boolean foundOdd = false;
		
		while(cur!=null){
			
			Node next = cur.next;
			
			if(!foundOdd){
				if(isOdd(cur.data)){
					foundOdd = true;
					firstOdd = cur;
				}
			}
			
			if(foundOdd && isEven(cur.data)){
				prev.next = prev.next.next;
				if(lastEven == null){
					cur.next = firstOdd;
					head = cur;
				}else{
					cur.next = firstOdd;
					lastEven.next = cur;
				}
			}
			
			if(isEven(cur.data))lastEven = cur;
			
			prev = cur;
			cur = next;
		}
		
	

		System.out.print("oddEven");
		Solution.printLinkedNodes(head);
		
	}

	private static boolean isOdd(int n){
		return n%2!=0;
	}

	private static boolean isEven(int n){
		return n%2==0;
	}
	
	private static Node removeDuplicates(Node root){
		Node cur = root;
		while(cur.next !=null){
			if(cur.data == cur.next.data){
				cur.next = cur.next.next;
			}else{
				cur = cur.next;
			}
		}
		
		return root;
	}
	
	private static Node merge(Node a, Node b){
//		Solution.printLinkedNodes(a);
//		Solution.printLinkedNodes(b);
		
		if(a==null)return b;
		if(b==null)return a;
		
		if(a.data < b.data){
			a.next = merge(a.next, b);
			return a;
		}else{
			b.next = merge(a, b.next);
			return b;
		}
		
	}

	private static void combination(String str) {
		char[] arr = str.toCharArray();
		int N = arr.length;
		char[] data = new char[N];
		
		doCombo(arr, data, 0, 0, 3);
	}

	private static void doCombo(char[] arr, char[] data, int i, int count, int N) {

		if (count == N) {
			System.out.print(new String(data) + " ");
			return;
		}

		if (i >= arr.length)
			return;

		data[count] = arr[i];

		doCombo(arr, data, i + 1, count + 1, N);
		doCombo(arr, data, i + 1, count, N);

	}

	private static void allCase(String prefix, String word) {
		if (word.length() == 0) {
			System.out.print(prefix + " ");
			return;
		}
		String first = word.substring(0, 1);
		String last = word.substring(1);

		allCase(prefix + first.toLowerCase(), last);
		allCase(prefix + first.toUpperCase(), last);
	}

	private static void permu(String prefix, String word) {
		int N = word.length();
		if (N == 0) {
			System.out.print(prefix + " ");
			return;
		}

		for (int i = 0; i < N; i++) {
			String first = prefix + word.charAt(i);
			String last = word.substring(0, i) + word.substring(i + 1);
			permu(first, last);
		}

	}

	private static void allSubSequence(String str) {
		int N = str.length();
		for (int i = 0; i < N; i++) {
			int t = N;
			while (t > i) {
				String sub = str.substring(i, t--);
				System.out.print(sub + " ");
			}
		}
	}

}
