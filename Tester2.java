import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Tester2 {

	public static void main(String[] args) {
		//System.out.println(Tester2.class.getSimpleName());
		Tester2 t = new Tester2();
		int[] arr = {1,2,3,4,5,6};
		binarySearchAndInsertAtIndex(arr, 7);

	}
	
	private static void binarySearchAndInsertAtIndex(int[] arr, int key){
		Arrays.sort(arr);
		int lo = 0;
		int hi = arr.length-1;
		int mid = 0;
		while(lo <= hi){
			
			if(key < arr[0]){
				mid = 0;
				break;
			}
			if(key > arr[hi]){
				mid = hi+1;
				break;
			}
			
			mid = lo +(hi-lo)/2;
			if(key < arr[mid]){
				hi = mid-1;
			}else if(key > arr[mid]){
				lo = mid+1;
			}else{
				System.out.println("found key at index " + mid);
				return;
			}
		}
		
		System.out.println("Not found. Put at index " + mid);
		
		arr= Arrays.copyOf(arr, arr.length+1);
		for(int i=arr.length-1; i>mid; i--){
			int temp = arr[i];
			arr[i] = arr[i-1];
			arr[i-1] = temp;
		}
		arr[mid] = key;
		
		System.out.println("New Array " + Arrays.toString(arr));
		
	}
	


}
