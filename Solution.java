
import java.util.Arrays;
import java.util.LinkedList;

public class Solution {

	public static void main(String[] args) {
		System.out.println(Solution.class.getSimpleName());
		Solution solu = new Solution();
		System.out.println("solu " + solu.solution(123));
		
		int[] arr = {1,2,3,4,5};
		int pos = 3;
		int num = 7;
		//System.out.println(Arrays.toString(arr));
		//System.out.println(Arrays.toString(addPos(arr, pos, num)));
		

	}

	public int solution(int X) {
		if (X == 0) return 0;
		
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

		//System.out.println(Arrays.toString(c));
		// Go down until *before* index
		for(int i = c.length-1; i > index ; i--){
			c[i] = c[i-1];
		}
		// Add to index
		c[index] = s.charAt(index);

		System.out.println(Arrays.toString(c));
		
		String str = new String(c);

		return Integer.parseInt(str);
	}
	
	public static int[] addPos(int[] a, int index, int num) {
	    int[] result = new int[a.length + 1];
	    for(int i = 0; i < index; i++)
	        result[i] = a[i];
	    result[index] = num;
	    for(int i = index + 1; i < result.length; i++)
	        result[i] = a[i - 1];
	    return result;
	}
	
	

}
