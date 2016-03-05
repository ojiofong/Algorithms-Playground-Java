public class MStack {

	public static void main(String[] args) {
		// System.out.println("good");
		MStack stack = new MStack();
		for (int i = 0; i < 5; i++)
			stack.push("data" + i);

		System.out.println("toString " + stack.toString());
		System.out.println("peek " + stack.peek());
		System.out.println("pop " + stack.pop());
		System.out.println("toString " + stack.toString());
		System.out.println("peek " + stack.peek());
	}

	private class Node {
		private Node next;
		private Object data;

		public Node(Object data) {
			this.data = data;
			this.next = null;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public Node getNext() {
			return this.next;
		}

		public Object getData() {
			return this.data;
		}
	}

	Node top;

	public MStack() {
		top = null;
	}

	// push,pop,peek

	public void push(Object data) {
		Node temp = new Node(data);
		temp.setNext(top);
		top = temp;
	}

	public Object pop() {
		if (top != null) {
			Object obj = top.getData();
			top = top.getNext();
			return obj;
		}
		return null;
	}

	public Object peek() {
		if (top != null) {
			return top.getData();
		}
		return null;
	}
	
	public boolean isEmpty(){
		return top == null;
	}

	public String toString() {
		String s = "";
		Node cur = top;
		while (cur != null) {
			s += "[" + cur.getData() + "]";
			cur = cur.getNext();
		}
		return s;
	}

}
