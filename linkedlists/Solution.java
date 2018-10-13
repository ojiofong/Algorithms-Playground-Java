package linkedlists;

import java.util.HashMap;
import java.util.Map;

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
		deepCopyLinkedList(getLinkedNodes());
		deepCopyLinkedListWithRandomNodes(getLinkedNodes());
		removeNthFromEnd(getLinkedNodes(), 2);
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

	public static void printLinkedNodes(Node head) {
		Node cur = head;
		while (cur != null) {
			System.out.print("[" + cur.data + "]");
			cur = cur.next;
		}
		System.out.println("");
	}

	public static Node getLinkedNodes() {
		Node head = new Node(1);
		head.next(new Node(2)).next(new Node(3)).next(new Node(4)).next(new Node(5)).next(new Node(6));
		return head;
	}

	public static Node getLinkedNodes2() {
		Node head = new Node(7);
		head.next(new Node(7)).next(new Node(7)).next(new Node(3)).next(new Node(4)).next(new Node(5));
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

	public static class Node {
		public Node next;
		public Node behind;
		public Node random;
		public int data;

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
				// System.out.println("Intersect at " + prev.data);
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
		head = new Node(17);
		head.next(new Node(15)).next(new Node(8)).next(new Node(12)).next(new Node(10)).next(new Node(5))
				.next(new Node(4)).next(new Node(1)).next(new Node(7)).next(new Node(6));

		head = getLinkedNodes();

		printLinkedNodes(head);

		Node cur = head;
		Node lastEven = null;
		Node prev = null;
		Node firstOdd = null;
		boolean foundOdd = false;

		while (cur != null) {

			Node next = cur.next;

			if (!foundOdd) {
				if (isOdd(cur.data)) {
					foundOdd = true;
					firstOdd = cur;
				}
			}

			if (foundOdd && isEven(cur.data)) {
				prev.next = prev.next.next; // erase cur
				if (lastEven == null) {
					cur.next = firstOdd;
					head = cur;
				} else {
					cur.next = firstOdd;
					lastEven.next = cur;
				}
			}

			if (isEven(cur.data))
				lastEven = cur;

			prev = cur;
			cur = next;
		}

		printLinkedNodes(head);

	}

	private static boolean isOdd(int n) {
		return n % 2 != 0;
	}

	private static boolean isEven(int n) {
		return n % 2 == 0;
	}

	private static void deepCopyLinkedList(Node head) {
		if (head == null)
			return;

		System.out.println("deepCopyLinkedList");
		printLinkedNodes(head);

		Node cur = head;
		Node copyHead = null;
		Node last = null;

		while (cur != null) {

			Node newNode = new Node(cur.data);

			if (last == null) {
				copyHead = newNode;
				last = copyHead;
			} else {
				last.next = newNode;
				last = newNode;
			}

			cur = cur.next;
		}

		printLinkedNodes(copyHead);
	}

	private static Node deepCopyLinkedListWithRandomNodes(Node root) {

		if (root == null)
			return null;

		Node cur = root;
		Node copyHead = null;
		Node last = null;
		Map<Node, Node> map = new HashMap<>();

		while (cur != null) {
			map.put(cur, new Node(cur.data));
			cur = cur.next;
		}

		cur = root; // reset cur to root/head

		while (cur != null) {
			Node copiedNode = map.get(cur);
			copiedNode.next = map.get(cur.next);
			copiedNode.random = map.get(cur.random);

			if (last == null) {
				copyHead = copiedNode;
				last = copyHead;
			} else {
				last.next = copiedNode;
				last = copiedNode;
			}

			cur = cur.next;
		}

		System.out.println("deepCopyLinkedListWithRandomNodes");
		printLinkedNodes(copyHead);
		return copyHead;

	}

	/*-
	 * O(n) time and constant space.
	 */
	private static Node removeNthFromEnd(Node head, int n) {
		System.out.println("removeNthFromEnd n = " + n);
		if (head == null || n <= 0)
			return head;

		Node start = new Node(0);
		start.next = head;
		Node fast = start;
		Node slow = start;

		int i;
		for (i = 0; i < n && fast.next != null; i++) {
			fast = fast.next;
		}

		if (i < n)
			throw new IllegalArgumentException("LinkedList contains less than " + n + " nodes");

		while (fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}

		slow.next = slow.next.next; // Remove/Delete node

		printLinkedNodes(start.next);
		return start.next; // return head
	}

}// End of class
