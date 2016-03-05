public class MStackArray {

	public static void main(String[] args) {
		// System.out.println("good");
		MStackArray stack = new MStackArray(20);
		for (int i = 0; i < 5; i++)
			stack.push(0, "data" + i);

		System.out.println("toString " + stack.toString());
		System.out.println("peek " + stack.peek());
		System.out.println("pop " + stack.pop());
		System.out.println("pop " + stack.pop());
		System.out.println("toString " + stack.toString());
//		System.out.println("peek " + stack.peek());
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
	int size;
	Object[] elements;
	int count;

	public MStackArray(int size) {
		this.size = size;
		count = 0;
		elements = new Object[size];
	}

	// push,pop,peek

	public void push(int stackNum, Object data) {
	
			elements[count++] = data;
		
	
	}

	public Object pop() {
		
		if(count > 0){
			Object item = elements[count-1];
			count --;
			return item;
		}

		return null;
	}

	public Object peek() {
	return elements[count-1];
	}
	
	public boolean isEmpty(){
		return count == 0;
	}

	public String toString() {
		String s = "";
		for(int i = 0; i < count; i ++){
			s += "[" + elements[i] + "]";
		}
		return s;
	}

}
