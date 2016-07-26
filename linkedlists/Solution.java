package linkedlists;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

	public static void main(String[] args) {
		System.out.println("hey");

		Node head = getLinkedNodes();
		printLinkedNodes(head);

		System.out.println("\nnth last node -> " + nthLastNode(head, 2).data);

	}
	
	private static void printLinkedNodes(Node head){
		Node cur = head;
		while (cur != null) {
			System.out.print("[" + cur.data + "]");
			cur = cur.next;
		}
	}
	
	private static Node getLinkedNodes(){
		Node head = new Node(1);
		head.next(new Node(2)).next(new Node(3))
		.next(new Node(4)).next(new Node(5));
		return head;
	}

	/**
	 * Given the head of a linked list return the nth last node in a single
	 * loop.
	 */
	private static Node nthLastNode(Node head, int n) {

		Node behind = head;
		Node cur = head;
		while (cur.next != null) {
			if (n-- <= 0)
				behind = behind.next;
			cur = cur.next;
		}

		return behind;

	}

	private static class Node {
		public Node next, behind;
		int data;

		public Node(int data) {
			this.data = data;
		}

		public Node next(Node n) {
			this.next = n;
			return n;
		}
	}

}
