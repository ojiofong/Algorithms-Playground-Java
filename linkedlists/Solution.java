package linkedlists;

/**
 * LinkedList
 */
public class Solution {

	public static void main(String[] args) {
		System.out.println("hey");

		Node head = getLinkedNodes();
		printLinkedNodes(head);
		printLinkedNodes(reverseLL(head));
		// reversePrint(head);
		System.out.println("\nnth last node -> " + nthLastNode(head, 2).data);
		getIntersect(getLinkedNodes(), getLinkedNodes2());

	}

	private static void printLinkedNodes(Node head) {
		Node cur = head;
		while (cur != null) {
			System.out.print("[" + cur.data + "]");
			cur = cur.next;
		}
		System.out.println("");
	}

	private static Node getLinkedNodes() {
		Node head = new Node(1);
		head.next(new Node(2)).next(new Node(3)).next(new Node(4)).next(new Node(5));
		return head;
	}

	private static Node getLinkedNodes2() {
		Node head = new Node(7);
		head.next(new Node(7)).next(new Node(3)).next(new Node(4)).next(new Node(5));
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

	/**
	 * Remove duplicate nodes from a sorted Linked List
	 */
	public static Node removeDuplicates(Node head) {
		// Write your code here
		Node cur = head;
		while (cur.next != null) {
			if (cur.data == cur.next.data) {
				cur.next = cur.next.next;
			} else {
				cur = cur.next;
			}
		}

		return head;
	}

	/**
	 * Merge two sorted linked lists
	 */
	public static Node mergeLists(Node a, Node b) {
		if (a == null)
			return b;
		if (b == null)
			return a;

		if (a.data <= b.data) {
			a.next = mergeLists(a.next, b);
			return a;
		} else {
			b.next = mergeLists(a, b.next);
			return b;
		}

	}

	/**
	 * Reverse a linked list and return pointer to the head
	 */
	public static Node reverseLinkedList(Node head) {
		Node cur = head;
		Node next = null;
		Node prev = null;
		while (cur != null) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		head = prev;
		return head;
	}

	/**
	 * Print in reverse. Recursion stored in stack so print happens in LIFO
	 */
	public static void reversePrint(Node head) {
		if (head != null) {
			reversePrint(head.next);
			System.out.print("[" + head.data + "]");
		}

	}

	public static Node deleteNodeAtPosition(Node head, int pos) {
		if (head != null) {
			if (pos == 0) {
				head = head.next;
			} else {
				Node cur = head;
				// get to node before position
				for (int i = 0; i < pos - 1 && cur.next != null; i++) {
					cur = cur.next;
				}
				// replace node at position via previous.next
				cur.next = cur.next.next;
			}
		}
		return head;
	}

	public static Node insertNodeAtPosition(Node head, int data, int pos) {
		Node newNode = new Node(data);

		if (head == null) {
			head = newNode;
		} else if (pos == 0) {
			newNode.next = head;
			head = newNode;
		} else {
			Node cur = head;
			for (int i = 0; i < pos - 1 && cur != null; i++) {
				cur = cur.next;
			}
			newNode.next = cur.next;
			cur.next = newNode;
		}

		return head;
	}

	public static Node reverseLL(Node head) {
		Node cur = head;
		Node prev = null;
		while (cur != null) {
			Node temp = cur.next;
			cur.next = prev;
			prev = cur;
			cur = temp;
		}
		// very important
		head = prev;

		return head;
	}

	public static Node getIntersect(Node head1, Node head2) {
		// Reverse both LinkedLists and traverse backwards stopping when both
		// dont match
		printLinkedNodes(head1);
		printLinkedNodes(head2);

		Node revHead1 = reverseLinkedList(head1);
		Node revHead2 = reverseLinkedList(head2);
		Node prev1 = null;

		while (revHead1 != null || revHead2 != null) {

			if (revHead1.data != revHead2.data) {
				// println Will throw NullPointerException if no intersection
				System.out.println("Intersect at " + prev1.data);
				return prev1;
			}

			if (revHead1 != null) {
				prev1 = revHead1;
				revHead1 = revHead1.next;
			}

			if (revHead2 != null) {
				revHead2 = revHead2.next;
			}

		}

		return null;
	}

}// End of class
