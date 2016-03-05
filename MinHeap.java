import java.util.Arrays;

public class MinHeap {

	public static void main(String[] args) {
		System.out.println(MinHeap.class.getSimpleName());

		MinHeap mh = new MinHeap();
		mh.insert(10);
		mh.insert(11);
		mh.insert(12);
		mh.insert(13);
		mh.insert(14);

		mh.remove(2);

		mh.inOrder(0);

		for (int i = 0; i < size; i++)
			System.out.print(heap[i] + ", ");

	}

	static int[] heap;
	static int size;
	int capacity = 32;
	double loadFactor = 0.75;

	public MinHeap() {
		heap = new int[capacity];
		size = 0;
	}

	public int parent(int index) {
		return (index - 1) / 2;
	}

	public int kthChild(int index, int k) {
		return 2 * index + k;
	}

	public int minChild(int index) {
		int left = kthChild(index, 1);
		int right = kthChild(index, 2);
		return heap[left] < heap[right] ? left : right;
	}

	public void insert(int data) {
		heap[size++] = data;
		heapifyUp(size - 1);
	}

	public void heapifyUp(int index) {
		int temp = heap[index];
		while (index > 0 && temp < heap[parent(index)]) {
			heap[index] = heap[parent(index)];
			index = parent(index);
		}
		heap[index] = temp;
	}

	public void remove(int index) {
		heap[index] = heap[--size];
		heapifyDown(index);
	}

	public void heapifyDown(int index) {
		int temp = heap[index];
		while (kthChild(index, 1) < size && temp > heap[minChild(index)]) {
			heap[index] = heap[minChild(index)];
			index = parent(index);
		}
		heap[index] = temp;
	}

	public void inOrder(int index) {
		if (index >= 0 && index < size) {
			visitIndex(index); // visit node
			inOrder(kthChild(index, 1)); // left child
			inOrder(kthChild(index, 2)); // right Child
		}
	}

	public void visitIndex(int index) {
		System.out.println("visited index " + index + " value-> " + heap[index]);
	}

	// End of class
}