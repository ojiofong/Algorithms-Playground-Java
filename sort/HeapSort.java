package sort;

import java.util.Arrays;

public class HeapSort {
	
	static int[] heap;
	static int size;

	public static void main(String[] args) throws Exception{
		System.out.println(HeapSort.class.getSimpleName());
		int[] arr = {13,4,1,5,2};
		System.out.println(Arrays.toString(arr));
		HeapSort.sort(arr);
		System.out.println(Arrays.toString(heap));
	}
	
	public static void sort(int[] arr){
		size = 0;
		heap = new int[arr.length];
		
		for(int i = 0; i < arr.length; i++){
			//
			addToHeap(arr[i], i);
		}
		
	}
	
	private static void addToHeap(int data, int index){
		heap[size++] = data;
		heapifyUp(size - 1);
	}
	
	private static void heapifyUp(int index){
		int temp = heap[index];
		while(index > 0 && temp < heap[parent(index)]){
			heap[index] = heap[parent(index)];
			index = parent(index);
		}
		
		heap[index] = temp;
	}
	
	private static int parent(int index){
		return (index - 1) / 2;
	}
}
