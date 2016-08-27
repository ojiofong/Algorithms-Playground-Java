package zzz;

import java.util.LinkedHashMap;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Test.class.getSimpleName());
		flibBinary(73, 30, 31);
		
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		

	}

	private static int flibBinary(int num, int i, int j){

		if(i < 0 || i > 31) throw new IndexOutOfBoundsException();
		if(j < 0 || j > 31) throw new IndexOutOfBoundsException();
		
		String binary = Integer.toBinaryString(num);
		binary = String.format("%32s", binary).replace(" ", "0");
		System.out.println(binary);
		
		
		
		char[] arr = binary.toCharArray();
		char temp = arr[i];
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		
		binary = new String(arr);
		System.out.println(binary);
		
		
		System.out.println(Integer.parseInt(binary, 2));
		return 0;
	}


}
