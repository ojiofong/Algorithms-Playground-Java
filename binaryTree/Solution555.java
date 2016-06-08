package binaryTree;

import java.util.HashMap;
import java.util.Map;

public class Solution555 {
	static Map<Character, Integer> map = new HashMap<>();

    static final long MAX_U_INT_32 = (long) Math.pow(2, 32) - 1;

    public static void main(String[] args) {
    	int a = 2147483647;
        String s = Long.toBinaryString(a).trim();
        s = String.format("%32s", s);
        s = s.replace(" ", "0");
       // Double d = Double.parseDouble(s);
       // Long b = Long.valueOf(s).longValue();
       // Long b = new Long(s);
       // Long b = d.longValue();
        
        char[] arr = s.toCharArray();
        for(int i = 0; i < arr.length; i++){
            arr[i] = arr[i] == '0' ? '1' : '0';
        }
        String flipped = new String(arr);
        long bb = Long.parseLong(flipped, 2);
        System.out.println("binary: " + s);
        System.out.println("flippd: " + flipped);
        System.out.println(bb);
        System.out.println(MAX_U_INT_32^a);
        
    }
    
}