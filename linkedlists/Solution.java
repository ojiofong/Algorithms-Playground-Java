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
		Node m = merge(getLinkedNodes(), getLinkedNodes2());
		printLinkedNodes(m);
		seperateEvenOdd(head);

	}

	private static Node merge(Node a, Node b) {

		if (a == null)
			return b;
		if (b == null)
			return a;

		if (a.data > b.data) {
			a.next = merge(a.next, b);
			return a;
		} else {
			b.next = merge(a, b.next);
			return b;
		}
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
		Node prev = null;

		while (revHead1 != null && revHead2 != null) {

			if (revHead1.data != revHead2.data) {
				// println Will throw NullPointerException if no intersection
				System.out.println("Intersect at " + prev.data);
				return prev;
			}

			prev = revHead1;
			revHead1 = revHead1.next;
			revHead2 = revHead2.next;

		}

		return prev;
	}

	/*-
	Input: 17->15->8->12->10->5->4->1->7->6->NULL
	Output: 8->12->10->4->6->17->15->5->1->7->NULL
	**/
	private static void seperateEvenOdd(Node head) {
		System.out.println("seperateEvenOdd");
		head = getLinkedNodes();

		printLinkedNodes(head);

		boolean foundN = false;
		Node cur = head;
		Node prevCur = null;
		Node lastEven = null;
		Node n = head; // pivot odd number

		while (cur != null) {

			// Find n pivot stop point. i.e. first odd number
			// Keep going until odd no. is found
			if (!foundN && n.data % 2 == 0) {
				foundN = true;
				n = cur;
			}

			// if even number.. swap
			if (cur.data % 2 == 0) {
				
				prevCur.next = prevCur.next.next; // erase cur
				
				if (n == head) {
					cur.next = n;
					head = cur;
				}else{
					cur.next = lastEven.next;
					lastEven.next = cur;
				}
				lastEven = cur; // must be at the end
			}

			prevCur = cur;
			cur = cur.next;
		}

		printLinkedNodes(head);

	}

}// End of class
