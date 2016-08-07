package bits;

import java.util.HashMap;
import java.util.Map;

public class Solution {
	static Map<Character, Integer> map = new HashMap<>();

	static final long MAX_U_INT_32 = (long) Math.pow(2, 32) - 1;
	static final long maxUint = (long) Integer.MAX_VALUE;

	public static void main(String[] args) {
		bitFlip32BitInteger(2147483647);

	}

	private static void bitFlip32BitInteger(int a) {
		a = a * -1;
		String s = Long.toBinaryString(a).trim();
		s = String.format("%32s", s);
		s = s.replace(" ", "0");

		char[] arr = s.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i] == '0' ? '1' : '0';
		}
		String flipped = new String(arr);
		long bb = Long.parseLong(flipped, 2);
		System.out.println("binary: " + s);
		System.out.println("flippd: " + flipped);
		System.out.println(bb);
		//System.out.println(MAX_U_INT_32 ^ a); // Awesome one line bitwise XOR
		int b = ~a;
		int sign = a < 0 ? 1 : -1; // flip sign
		System.out.println(b * sign); // Awesome one line bitwise XOR
	}

}