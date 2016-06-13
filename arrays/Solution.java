package arrays;

import java.util.Arrays;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			System.out.println(doRange100());
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

}
