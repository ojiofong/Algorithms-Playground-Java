
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
		fizzbuzz(15);
		squareRoot(25.0);

	}
	
	private static void squareRoot(double n){
		
		double sqrt = n/2;
		double t = 0;
		
		do{
			
			t = sqrt;
			sqrt = (t + n/t)/2;
			
		}while(t-sqrt!=0);

		System.out.println("sqrt is: " + sqrt);
		System.out.println("sqrt2 is: " + Math.pow(n, 0.5));
		System.out.println("sqrt2 is: " + Math.sqrt(n));
		
	}
	
	private static void fizzbuzz(int n){
		String s = n%5==0 ? (n%3==0 ? "fizzbuzz" : "fizz") : n%3==0 ? "buzz" : "none";
		s = "none";
		String fb = "" + (n%15==0);
		String f = "" + (n%5==0);
		String b = "" + (n%3==0);
		
		switch(fb+f+b){
		case "truetruetrue": s = "fizzbuzz"; break;
		case "falsetruefalse": s = "fizz"; break;
		case "falsefalsetrue": s = "buzz"; break;
		}
		
		System.out.println(s);
		
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
