import java.util.Arrays;
import java.util.LinkedList;

public class MQueueDoubly {

	public static void main(String[] args) {
		System.out.println("good");
		MQueueDoubly queue = new MQueueDoubly();
		// for (int i = 0; i < 5; i++)
		// queue.enqueue(i);
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);

		System.out.println("toString " + queue.toString());
		System.out.println("size " + queue.size());
		// System.out.println("dequeue " + queue.dequeue());
		// System.out.println("toString " + queue.toString());
		// System.out.println("size " + queue.size());
		// System.out.println("dequeue " + queue.dequeue());
		// System.out.println("toString " + queue.toString());
		// System.out.println("size " + queue.size());
		// System.out.println("removeDuplicate " + queue.removeDuplicate());
		// System.out.println("toString " + queue.toString());
		// System.out.println("partition at " + queue.partitionAtx(5));
		// System.out.println("toString " + queue.toString());
		// System.out.println("Reverse nodes " + queue.reverseNodes());
		System.out.println("is palindrone " + queue.isPalindrome());
		System.out.println("toString " + queue.toString());

	}

	Node first, last;
	int size;

	public MQueueDoubly() {
		first = last = null;
	}

	public void enqueue(Object data) {
		Node newNode = new Node(data);

		if (first == null) {
			first = last = newNode;
		} else {
			newNode.setPrev(last);
			last.setNext(newNode);
			last = newNode;
		}

		size++;
	}

	public void enqueue(int index , Object data) {
		Node newNode = new Node(data);

		Node cur = first;
		int i = 0;
		while(cur!=null && i <= index){
			if(i == index){
				newNode.setNext(cur.getNext());
				cur.setNext(newNode);
				size++;
			}
			i++;
			cur = cur.getNext();
		}

	}


	public Object dequeue() {
		if (!isEmpty()) {
			Object item = first.getData();
			first = first.getNext();
			size--;
			return item;
		}

		return null;
	}

	public String toString() {
		String s = "";
		Node focus = first;
		while (focus != null) {
			s += "[" + focus.getData() + "]";
			focus = focus.getNext();
		}
		return s;
	}

	public String toStringMerged() {
		String s = "";
		Node focus = first;
		while (focus != null) {
			s += focus.getData();
			focus = focus.getNext();
		}
		return s;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return size;
	}

	public boolean removeDuplicate() {
		Node current = first;
		while (current != null) {
			Node runner = current;
			while (runner.next != null) {
				if (runner.next.data.equals(current.data)) {
					runner.next = runner.next.next;
				} else {
					runner = runner.getNext();
				}
			}

			current = current.getNext();
		}

		return true;
	}

	// Reverse doubly linked list
	public boolean reverseNodes() {
		Node cur = first;
		Node last = null;
		Node pivot = null;
		while (cur != null) {
			if (cur.getNext() == null) {
				last = cur;
				pivot = cur;
			}
			cur = cur.getNext();
		}

		cur = last;

		while (cur != null) {

			last.setNext(cur.getPrev());
			last = cur.getPrev();

			cur = cur.getPrev();
		}
		//
		pivot.setPrev(null);
		first = pivot;

		return true;
	}

	// Write code to partition a linked list around a value x,
	// such that all nodes less than x come before all nodes greater than or
	// equal to x.

	public int partitionAtx(int x) {
		Node focus = first;
		Node leftFirst = null, rightFirst = null, left = null, right = null;
		while (focus != null) {
			if ((Integer) focus.data < x) {
				if (leftFirst == null) {
					leftFirst = focus;
					left = leftFirst;
				} else {
					left.setNext(focus);
					;
					left = left.getNext();
				}
			} else {
				// add to right
				if (rightFirst == null) {
					rightFirst = focus;
					right = rightFirst;
				} else {
					right.setNext(focus);
					right = right.getNext();
				}
			}

			focus = focus.next;
		}

		// left.setNext(rightFirst);
		first = leftFirst;
		// first = rightFirst;
		// last = right;

		return x;
	}

	public class Node {
		Object data;
		Node next;
		Node prev;

		public Node(Object data) {
			this.data = data;
		}

		public void setNext(Node nextNode) {
			this.next = nextNode;
		}

		public Node getNext() {
			return this.next;
		}

		public Object getData() {
			return this.data;
		}

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}

		public void setData(Object data) {
			this.data = data;
		}

	}

	// Implement a function to check if a linked list is a palindrome,

	public boolean isPalindrome() {
		Node focus = first;
		Node last = null;
		int size = size();
		int[] arr = new int[size];

		for (int i = 0; i < size && focus != null; i++) {
			arr[i] = (Integer) focus.data;
			focus = focus.getNext();
		}

		int i = 0;
		int k = size;

		while (i < k) {
			// System.out.println("val " + arr[i]);
			System.out.println("val " + arr[k - 1]);

			if (arr[k-1] != arr[i]) {
				return false;
			}

			k--;
			i++;

		}

		return true;
	}

}
