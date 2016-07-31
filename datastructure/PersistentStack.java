package datastructure;

import java.util.NoSuchElementException;

/**
 * Immutable stack. push returns the new stack and pop returns the next stack.
 * peek is used to view top
 */
public class PersistentStack<T> {
	
	public static void main(String[] args){
		PersistentStack<Integer> s1 = EMPTY_STACK;
		PersistentStack<Integer> s2 = s1.push(1);
		PersistentStack<Integer> s3 = s2.push(2);
		PersistentStack<Integer> s4 = s3.pop();
		
		System.out.println("size s1 -> " + s1.size);
		System.out.println("size s2 -> " + s2.size);
		System.out.println("size s3 -> " + s3.size);
		System.out.println("size s4 -> " + s4.size);
	}

	// Singleton empty stack via lazy loading
	@SuppressWarnings("rawtypes")
	public static final PersistentStack EMPTY_STACK = new PersistentStack<>();

	private final T element;
	private final int size;
	private final PersistentStack<T> next;

	private PersistentStack() {
		this.element = null;
		this.size = 0;
		this.next = null;
	}

	private PersistentStack(T e, int size, PersistentStack<T> stack) {
		this.element = e;
		this.size = size;
		this.next = stack;
	}

	public PersistentStack<T> push(T element) {
		return new PersistentStack<>(element, this.size + 1, this);
	}

	/**
	 * Returns the next stack, not the popped element
	 */
	public PersistentStack<T> pop() {
		if (isEmpty())
			throw new NoSuchElementException();

		return this.next;
	}

	/**
	 * Only way to get the top element since pop returns the next stack
	 */
	public T peek() {
		if (isEmpty())
			throw new NoSuchElementException();

		return this.element;
	}

	public boolean isEmpty() {
		return this.next == null;
	}

}
