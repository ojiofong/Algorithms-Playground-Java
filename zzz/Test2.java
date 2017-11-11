package zzz;

import java.util.Arrays;

public class Test2 {

	public static void main(String[] args) throws Exception {

		System.out.println(Test2.class.getSimpleName());
		MinHeap<String> mh = new MinHeap<>();
		mh.add("one");
		mh.add("two");
		mh.add("three");
		mh.add("four");

		mh.preOrder(0);
		System.out.println("size " + mh.size());
		mh.remove(1);
		mh.preOrder(0);
		System.out.println("size " + mh.size());

	}

	static class MinHeap<T extends Comparable<T>> {
		private T[] heap;
		private int size;
		private double loadFactor = 0.9;
		private int initialCapacity = 31;

		@SuppressWarnings("unchecked")
		public MinHeap() {
			heap = (T[]) new Comparable[initialCapacity];
			size = 0;
		}

		public void add(T value) {
			checkInput(value);
			checkSize();
			heap[size] = value;
			heapifyUp(size);
			size = size + 1;
		}

		public void remove(int index) {
			checkIndex(index);
			heap[index] = heap[size - 1];
			size = size - 1;
			heapifyDown(index);
		}

		private void heapifyUp(int index) {
			T t = heap[index];
			while (size > 0 && t.compareTo(heap[parent(index)]) < 0) {
				heap[index] = heap[parent(index)];
				index = parent(index);
			}
			heap[index] = t;
		}

		private void heapifyDown(int index) {
			T t = heap[index];
			while (left(index) < size && t.compareTo(heap[min(index)]) > 0) {
				heap[index] = heap[min(index)];
				index = min(index);
			}
			heap[index] = t;
		}

		private int parent(int index) {
			return index - 1 / 2;
		}

		private int left(int index) {
			return index * 2 + 1;
		}

		private int right(int index) {
			return index * 2 + 2;
		}

		private int min(int index) {
			int L = left(index);
			int R = right(index);
			return heap[L].compareTo(heap[R]) < 0 ? L : R;
		}

		private void checkInput(T value) {
			if (value == null)
				throw new IllegalArgumentException("Null is not allowed");
		}

		private void checkIndex(int index) {
			if (!isIndexInBounds(index))
				throw new IndexOutOfBoundsException("Size is " + size + " Index is " + index);
		}

		private boolean isIndexInBounds(int index) {
			return (index >= 0 && index < size);
		}

		private void checkSize() {
			if (size > (loadFactor * heap.length)) {
				heap = Arrays.copyOf(heap, heap.length + initialCapacity);
			}
		}

		public int size() {
			return size;
		}

		public void preOrder(int index) {
			if (isIndexInBounds(index)) {
				System.out.println("visited " + heap[index]);
				preOrder(left(index));
				preOrder(right(index));
			}
		}
	}

}
