import java.util.Stack;

public class MStack2 {
	

	Node top;
	int size;

	public static void main(String[] args) {
		// System.out.println("good");
		MStack2 stack = new MStack2();
		for (int i = 0; i < 5; i++)
			stack.push("data" + i);

		System.out.println("toString " + stack.toString());
		System.out.println("size " + stack.size());
		System.out.println("peek " + stack.peek());
		System.out.println("pop " + stack.pop());
		System.out.println("toString " + stack.toString());
		System.out.println("size " + stack.size());
		System.out.println("peek " + stack.peek());
		System.out.println("pop " + stack.pop());
		System.out.println("toString " + stack.toString());
		System.out.println("size " + stack.size());
		sortStack();
	}


	public MStack2() {
		top = null;
		size = 0;
	}

	public void push(Object data) {
		Node newNode = new Node(data);

		if (top == null) {
			top = newNode;
		} else {
			newNode.setNext(top);
			top = newNode;
		}

		size++;
	}

	public Object pop() {
		if (!isEmpty()) {
			Object item = top.getData();
			top = top.getNext();
			size--;
			return item;
		}

		return null;
	}

	public Object peek() {
		if (!isEmpty()) {
			Object item = top.getData();
			return item;
		}

		return null;
	}

	public String toString() {
		String s = "";
		Node focus = top;
		while (focus != null) {
			s += "[" + focus.getData() + "]";
			focus = focus.getNext();
		}
		return s;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return top == null;
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

	public static void sortStack() {
		Stack<Integer> r = new Stack<>();
		Stack<Integer> s = new Stack<>();
		s.push(3);
		s.push(1);
		s.push(2);
		

		System.out.println(s.toString());
		
		while(!s.isEmpty()){
			int temp = s.pop();
			while(!r.isEmpty() && r.peek() > temp){
				s.push(r.pop());
			}
			r.push(temp);
		}

		System.out.println(s.toString());
		System.out.println(r.toString());

	}

}
