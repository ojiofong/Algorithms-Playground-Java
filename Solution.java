
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Solution {

	public static void main(String[] args) {
		System.out.println(Solution.class.getSimpleName());
		Solution solu = new Solution();
		System.out.println("solu " + solu.solution(123));

		int[] arr = { 1, 2, 3, 4, 5 };
		int pos = 3;
		int num = 7;
		// System.out.println(Arrays.toString(arr));
		// System.out.println(Arrays.toString(addPos(arr, pos, num)));
		solu.fizzBuzz(15);
		String s = "abcd";
		System.out.println("yesssss "+ s.substring(s.length()-1, s.length()));
		solu.printReversed(s, s.length());
		String sss = solu.printReversed2(s, "", s.length());
		System.out.println("\n.....yyyyy");
		System.out.println(sss);

	}

	public int solution(int X) {
		if (X == 0)
			return 0;

		String strVal = String.valueOf(X);
		int largest = 0;
		for (int i = 0; i < strVal.length(); i++) {
			int intVal = duplicateAtIndex2(strVal, i);
			largest = Math.max(intVal, largest);
		}

		return largest;
	}

	public int duplicateAtIndex(String s, int index) {

		LinkedList<String> mList = new LinkedList<String>();

		int length = s.length();
		String valToAdd = "" + s.charAt(index);

		for (int i = 0; i < length; i++) {
			String data = "" + s.charAt(i);
			mList.addLast(data);
		}

		mList.add(index, valToAdd);

		// Get back the characters in linked list
		// via string concatenation
		String str2 = "";
		for (String ss : mList) {
			str2 += ss;
		}

		System.out.println(str2);

		return Integer.parseInt(str2);
	}

	public int duplicateAtIndex2(String s, int index) {
		// Shift arrays in reverse replacing last null index value
		// Then add to desired index

		char[] c = s.toCharArray();
		c = Arrays.copyOf(c, c.length + 1);

		// System.out.println(Arrays.toString(c));
		// Go down until *before* index
		for (int i = c.length - 1; i > index; i--) {
			c[i] = c[i - 1];
		}
		// Add to index
		c[index] = s.charAt(index);

		System.out.println(Arrays.toString(c));

		String str = new String(c);

		return Integer.parseInt(str);
	}

	public static int[] addPos(int[] a, int index, int num) {
		int[] result = new int[a.length + 1];
		for (int i = 0; i < index; i++)
			result[i] = a[i];
		result[index] = num;
		for (int i = index + 1; i < result.length; i++)
			result[i] = a[i - 1];
		return result;
	}
	
	public enum FizzEnum{
		FIZZ, BUZZ, FIZZBUZZ
	}; 

	public void fizzBuzz(int n) {
		n = 15;

		System.out.println("..........FizzBuzz....Start....");
		System.out.println("n%15: " + n % 15);
		System.out.println("n%3: " + n % 3);
		System.out.println("n%5: " + n % 5);
		final String fizzbuzz = n%15==0 ? "fizzbuzz" : "";
		final String fizz = n%3==0 && n%5!=0 ? "fizz" : "";
		final String buzz = n%5==0 && n%3!=0 ? "buzz" : "";
		final String none = n%5!=0 && n%3!=0 ? "None" : "";
		final String[] arr = {fizzbuzz, fizz, buzz, none};
	
		//String s = "sn";
		for(String s : arr){
			System.out.print(s);
		}

		System.out.println("\n..........FizzBuzz....end....");

	}

	public String printReversed(String str, int limit) {
		if (--limit >= 0) {
			System.out.print(str.charAt(limit));
			return printReversed(str, limit);
		}
		
		return "Nothing";
	}
	
	public String printReversed2(String str, String retVal, int limit) {
		if (--limit >= 0) {
			//System.out.print(str.charAt(limit));
			return printReversed2(str, retVal + str.charAt(limit), limit);
		}
		
		return retVal;
	}

}
