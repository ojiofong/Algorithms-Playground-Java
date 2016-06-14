package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(doRange100());
		System.out.println(incrementArrayAsNumberBy1());
	}
	
	/**
	 * Array of Integer 1-100
	 * Return string with ranges that does not exist
	 * e.g. {5,6,80,90} -> "1-4,7-79,81-89,91-100"
	 * **/
	private static String doRange100(){
		int[] arr = {5,6,80,90};
		Arrays.sort(arr);
		boolean flag = false;
		int prev = 1;
		StringBuffer buffer = new StringBuffer();
		for(int i=0; i<arr.length; i++){
			int a = arr[i];
			flag = (a-prev)>1;
			if(i==0 && a!=1){
				buffer.append((a==2 ? "1" : "1-" + (a-1)));
			}else if(flag){
				int x = prev+1;
				int y = a - 1;
				buffer.append(",").append(x-y==0 ? ""+x : x+"-"+y);
			}
			
			if(i == arr.length-1 && a<100){
				int x = a+1;
				int y = 100;
				buffer.append(",").append(x-y==0 ? ""+x : x+"-"+y);
			}
			
			prev = a;
		}
		
		
		return buffer.toString();
	}
	
	/**
	 * Given an integer array e.g. [9, 8, 8, 3] where each item in array could be 0 to 9, 
	 * interpret the array [9, 8, 8, 3] as a number 9883 and increment it by 1. 
	 * return an integer array e.g. [9,8,8,4]. 
	 * No zeros in the first position like [0,1,2,3].
	 * No String coversion
	 * */
	private static String incrementArrayAsNumberBy1(){
		int[] arr = {8,9,9,9};
		List<Integer> list = new ArrayList<>();
		boolean flag = true;
		
		for(int i = arr.length-1; i >= 0; i--){
			int a = arr[i];
			int v = flag ? a+1 : a;
			flag = v==10;
			if(i==0 && flag){
				list.add(0);
				list.add(1);
			}else if(flag){
				list.add(0);
			}else if(!flag){
				list.add(v);
			}
		}
		
		int index = 0;
		arr = new int[list.size()];
		for(int i = list.size()-1; i>=0 ; i--){
			arr[index++] = list.get(i);
		}
		
		return Arrays.toString(arr);
	}

}
