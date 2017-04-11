package datastructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackWithQueue<T> {

	Queue<T> queue; // For one queue solution

	Queue<T> q1;
	Queue<T> q2;

	public static void main(String[] args) {
		System.out.println(StackWithQueue.class.getSimpleName());

		StackWithQueue<Integer> st = new StackWithQueue<>();
		st.pushWithOneQueue(1);
		st.pushWithOneQueue(2);
		st.pushWithOneQueue(3);
		
		st.pushWithTwoQueues(1);
		st.pushWithTwoQueues(2);
		st.pushWithTwoQueues(3);

		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);

		System.out.println("st    -> " + st.toStringWithOneQueue());
		System.out.println("st2   -> " + st.toStringWithTwoQueues());
		System.out.println("stack -> " + stack.toString());


		for(int i=0; i<3; i++){
			System.out.println("st    pop -> " + st.popWithOneQueue());
			System.out.println("st2   pop -> " + st.popWithTwoQueues());
			System.out.println("stack pop -> " + stack.pop());

			System.out.println("st    -> " + st.toStringWithOneQueue());
			System.out.println("st2   -> " + st.toStringWithTwoQueues());
			System.out.println("stack -> " + stack.toString());
		}

	}

	public StackWithQueue() {
		queue = new LinkedList<>();
		q1 = new LinkedList<>();
		q2 = new LinkedList<>();

	}

	// using one queue solution
	public void pushWithOneQueue(T val) {
		queue.add(val);
		int size = queue.size();
		// Reverse every value to back and omit new value added at the end
		for (int i = 0; i < size - 1; i++) {
			queue.add(queue.remove());
		}
	}

	public T popWithOneQueue() {
		if (!queue.isEmpty())
			return queue.remove();
		return null;
	}

	public String toStringWithOneQueue() {
		return queue.toString();
	}

	// using two queues solution begin
	public void pushWithTwoQueues(T val) {
		q1.add(val);
	}

	public T popWithTwoQueues() {
		while(q1.size() > 1) {
			q2.add(q1.remove());
		}
		
		if(!q1.isEmpty()){
			T item = q1.remove();
			Queue<T> temp = q1;
			q1 = q2;
			q2 = temp;
			return item;
		}
		return null;
	}
	
	public String toStringWithTwoQueues() {
		return q1.toString() + " " + q2.toString();
	}

}
