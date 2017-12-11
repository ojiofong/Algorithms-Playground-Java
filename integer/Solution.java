package integer;

public class Solution {

	public static void main(String[] args) {
		System.out.println("reverseNumber-> " + reverseNumber(-123));
		System.out.println("isPalindromeNumber-> " + isPalindromeNumber(1));
	}

	/*-
	 * Reverse an integer e.g. 123->321, -123->-321 etc.
	 * if Overflow or underflow return 0;
	 * No String conversion
	 * O(n) time and O(1) space.
	 */
	private static int reverseNumber(int x) {
		int sign = x < 0 ? -1 : 1;
		x = x * sign; // Remove negative sign if needed before calculation
		long rev = 0;
		while (x > 0) {
			rev = (rev * 10) + (x % 10);
			x = x / 10;
			if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) return 0; 
		}
		return (int) rev * sign; // Restore negative sign if needed after calculation
	}
	
	/*-
	 * Check if an integer is a palindrome e.g. 123->false, 121->true etc.
	 * Consider Overflow or underflow
	 * No String conversion
	 * O(n) time and O(1) space.
	 */
	private static boolean isPalindromeNumber(int x){
		if (x < 0) return false;
		int copy = x;
		long rev = 0;
		// Stop one loop/char earlier before overflow
		while(x >= 10){
			rev = (rev * 10) + (x % 10);
			x = x/10;
		}
		return rev == copy/10;
		// E.g. Int16 - 63556 will (6553 == 6355) rev's 1st digit comes from last digit of copy
	}

}
