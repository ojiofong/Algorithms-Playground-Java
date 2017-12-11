package integer;

public class Solution {

	public static void main(String[] args) {
		System.out.println("reverseNumber-> " + reverseNumber(-123));
		System.out.println("isPalindromeNumber-> " + isPalindromeNumber(1));
		System.out.println("convertStringToNumber-> " + convertStringToNumber("123"));
		System.out.println("convertNumberToWords-> " + convertNumberToWords(1100000));
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
			if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE)
				return 0;
		}
		return (int) rev * sign; // Restore negative sign if needed after
									// calculation
	}

	/*-
	 * Check if an integer is a palindrome e.g. 123->false, 121->true etc.
	 * Consider Overflow or underflow
	 * No String conversion
	 * O(n) time and O(1) space.
	 */
	private static boolean isPalindromeNumber(int x) {
		if (x < 0)
			return false;
		int copy = x;
		long rev = 0;
		// Stop one loop/char earlier before overflow
		while (x >= 10) {
			rev = (rev * 10) + (x % 10);
			x = x / 10;
		}
		return rev == copy / 10;
		// E.g. Int16 - 63556 will (6553 == 6355) rev's 1st digit comes from
		// last digit of copy
	}

	/*-
	 * Convert String to integer Number.
	 * O(n) time and O(1) space.
	 */
	private static int convertStringToNumber(String str) {
		if (str == null)
			return 0;
		str = str.trim();
		if (str.isEmpty())
			return 0;
		int startIndex = 0;
		long sum = 0;
		int sign = 1;
		char[] arr = str.toCharArray();
		char firstChar = arr[0];

		if (firstChar == '-') {
			sign = -1;
			startIndex = 1;
		} else if (firstChar == '+') {
			sign = 1;
			startIndex = 1;
		}

		for (int i = startIndex; i < arr.length; i++) {
			char c = arr[i];
			if (!Character.isDigit(c)) {
				return (int) sum * sign;
			}
			sum = (sum * 10) + (c - '0');
			if (sign == 1 && sum > Integer.MAX_VALUE) {
				return Integer.MAX_VALUE;
			}
			if (sign == -1 && (-1 * sum) < Integer.MIN_VALUE) {
				return Integer.MIN_VALUE;
			}
		}
		return (int) sum * sign;
	}

	/*-
	 * Convert Number to words.
	 * Input: 123456789
	 * Output: One hundred twenty three million four hundred fifty six thousand seven hundred eighty nine
	 * O(n/1000) time and O(1) space.
	 */
	private static String convertNumberToWords(int num) {
		String[] specialNames = { "", " thousand", " million", " billion", " trillion", " quadrillion",
				" quintillion" };
		int places = 0;
		String current = "";
		while (num > 0) {
			current = convertLessThan1000(num % 1000) + specialNames[places] + current;
			num = num / 1000;
			places = places + 1;
		}

		return current;
	}

	private static String convertLessThan1000(int num) {
		if (num > 999)
			throw new IllegalArgumentException("num cannot be more than 999");

		String[] tensNames = { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty", " seventy", " eighty",
				" ninety" };
		String[] unitNames = { "", " one", " two", " three", " four", " five", " six", " seven", " eight", " nine",
				" ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen", " seventeen",
				" eighteen", " nineteen" };
		String current;

		if (num % 100 <= 19) {
			current = unitNames[num % 100];
			num = num / 100;
		} else {
			current = unitNames[num % 10]; // get unit digit before tenth
			num = num / 10;
			current = tensNames[num % 10] + current; // get tens digit
			num = num / 10;
		}

		if (num != 0) {
			// Number did not stop at tenth. It is in the hundreds
			current = unitNames[num % 10] + " hundred" + current;
		}

		return current;
	}

}
