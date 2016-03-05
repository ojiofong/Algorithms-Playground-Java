public class MQueue {

	public static void main(String[] args) {
		// System.out.println("good");
		MQueue queue = new MQueue();
		 for (int i = 0; i < 5; i++)
			 queue.enqueue("data" + i);

		 System.out.println("toString " + queue.toString());
		 System.out.println("size " + queue.size());
		 System.out.println("dequeue " + queue.dequeue());
		 System.out.println("toString " + queue.toString());
		 System.out.println("size " + queue.size());
		 System.out.println("dequeue " + queue.dequeue());
		 System.out.println("toString " + queue.toString());
		 System.out.println("size " + queue.size());
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

	Node first, last;

	public MQueue() {
		first = last = null;
	}

	// enqueue,dequeue

	public void enqueue(Object data) {
		// append to tail
		Node temp = new Node(data);
		if (first == null) {
			first = last = temp;
		} else {
			last.setNext(temp);
			last = last.getNext();
		}
	}
	
	public Object dequeue(){
		if(first!=null){
		Object obj = first.getData();
		first = first.getNext();
		return obj;
		}
		return null;
	}
	
	public String toString() {
		String s = "";
		Node cur = first;
		while (cur != null) {
			s += "[" + cur.getData() + "]";
			cur = cur.getNext();
		}
		return s;
	}
	
	public int size() {
		int count = 0;
		Node cur = first;
		while (cur != null) {
			count ++;
			cur = cur.getNext();
		}
		return count;
	}
	
	public boolean isEmpty(){
		return first == null;
	}

}
