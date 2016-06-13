
public class Tester2 {
	Node top;
	Node first, last;

	public static void main(String[] args) {
		//System.out.println(Tester2.class.getSimpleName());
		Tester2 t = new Tester2();
		t.enqueue(1);
		t.enqueue(2);
		t.enqueue(3);
		System.out.println(t.toString()); //123
		t.dequeue();
		System.out.println(t.toString()); //12
		t.peek(); //2

	}
	
	public Tester2(){
		top = null;
		first = last = null;
	}
	
	public void enqueue(int data){
		Node newNode = new Node(data);
		if(first == null){
			last = first = newNode;
		}else{
			last.next = newNode;
			last = last.next;
		}
	}
	
	public int dequeue(){
		if(first==null) return 0;
		int item = first.data;
		first = first.next;
		return item;
	}
	
	public void push(int data){
		Node newNode = new Node(data);
		if(top==null){
			top = newNode;
		}else{
			newNode.next = top;
			top = newNode;
		}
	}
	
	public int pop(){
		if(top==null) return 0;
		
		int item = top.data;
		top = top.next;
		
		return item;
	}
	
	public int peek(){
		return top==null ? 0 : top.data;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[ ");
		
		// stack
		Node cur = top;
		while(cur!=null){
			buffer.append("[").append(cur.data).append("]");
			cur = cur.next;
		}
		
		// queue
		cur = first;
		while(cur!=null){
			buffer.append("[").append(cur.data).append("]");
			cur = cur.next;
		}

		buffer.append(" ]");
		return buffer.toString();
	}
	
	private class Node{
		int data;
		Node next;
		public Node(int data){
			this.data = data;
			this.next = null;
		}
	}

}
