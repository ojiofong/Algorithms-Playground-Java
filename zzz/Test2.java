package zzz;

import java.util.Stack;

import datastructure.MinStack.Node;

public class Test2 {

	public static void main(String[] args) {
		Node head = getLinkedNodes();
		Node head2 = getLinkedNodes2();
		print(head);
		print(head2);
		System.out.println("nthlast " + nthLastNode(head).data);
		// print(reverseLinkedList(head));
		// print(head);
		// print(removeDuplicate(head2));

	}

	private static Node nthLastNode(Node head) {
		int n = 2;
		n = n-1;
		Node cur = head;
		Node behind = head;
		
		while(cur.next != null && cur.next != head){
			if(n > 0){
				n--;
			}else{
				behind = behind.next;
			}

			cur = cur.next;
		}
		
		if(n > 0) return null;
		
		return behind;
	}

	private static Node getIntersect(Node head1, Node head2) {

		Node cur1 = reverseLinkedList(head1);
		Node cur2 = reverseLinkedList(head2);
		Node prev = null;
		while (cur1 != null && cur2 != null) {

			if (cur1.data != cur2.data)
				return prev;

			prev = cur1;
			cur1 = cur1.next;
			cur2 = cur2.next;
		}

		return prev;
	}


	private static Node reverseLinkedList(Node head) {
		Node cur = head;
		Node prev = null;
		while (cur != null) {
			Node next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		head = prev;
		return head;
	}

	private static void print(Node head) {
		Node cur = head;
		StringBuilder builder = new StringBuilder();
		while (cur != null) {
			builder.append("[" + cur.data + "]");
			cur = cur.next;
		}
		System.out.println(builder.toString());
	}

	private static Node getLinkedNodes() {
		Node head = new Node(1);
		head.next(new Node(2)).next(new Node(3)).next(new Node(4)).next(new Node(5));
		return head;
	}

	private static Node getLinkedNodes2() {
		Node head = new Node(7);
		head.next(new Node(7)).next(new Node(8)).next(new Node(3)).next(new Node(4)).next(new Node(5));
		return head;
	}

	private static class Node {
		public Node next;
		public Node behind;
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
