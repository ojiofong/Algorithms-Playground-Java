package strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * Strings
 */
public class Solution {
	public static void main(String[] args) {
		System.out.println(Solution.class.getSimpleName());
		Solution solu = new Solution();
		solu.allSubstringsOfAString("abc");
		longestDuplicateSub("ababcaabcabcaab");
		allCaseComboOfString("", "abc");
		System.out.println("");
		permutation("", "abc");
		firstNonRepeatingCharInString("abracadabra");
		Set<String> dict = new HashSet<>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lat");
		dict.add("log");
		System.out.println("wordLadder -> " + wordLadder("hit", "cog", dict));
		printOpenCloseParenthesis(2);
		reverseStringWordsOnly("one two three");
	}

	public void allSubstringsOfAString(String str) {
		int N = str.length();
		for (int i = 0; i < N; i++) {
			int t = N;
			while (t > i) {
				String sub = str.substring(i, t--);
				System.out.print("[" + sub + "]");
			}
		}
		System.out.println("");
	}

	public static void longestDuplicateSub(String str) {
		// Get all substrings of str
		// check duplicate on the fly
		// hold the longest if true

		String longest = "";

		for (int i = 0; i < str.length(); i++) {
			for (int k = 1; k <= str.length() - i; k++) {
				String sub = str.substring(i, k + i);
				boolean isSubDuplicate = str.replaceFirst(sub, "").contains(sub);
				if (isSubDuplicate) {
					longest = sub.length() > longest.length() ? sub : longest;
				}
			}
		}

		System.out.println("\nlongestDuplicateSub-> " + longest);
	}

	private static void allCaseComboOfString(String prefix, String word) {
		if (word.length() == 0) {
			System.out.print(prefix + " ");
			return;
		}

		String first = word.substring(0, 1);
		String last = word.substring(1);

		allCaseComboOfString(prefix + first.toLowerCase(), last);
		allCaseComboOfString(prefix + first.toUpperCase(), last);

	}

	private static void permutation(String prefix, String str) {
		int n = str.length();
		if (n == 0)
			System.out.print(prefix + "* ");
		else {
			for (int i = 0; i < n; i++)
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
		}
	}

	/**
	 * Returns the first non repeating character in a string Time 0(2n). Space
	 * 0(256) or 0(1)
	 */
	private static Character firstNonRepeatingCharInString(String str) {

		System.out.println("");

		str = "abcda";

		// 0(n^2)
		// for (int i = 0; i < str.length(); i++) { // 0(n)
		// char c = str.charAt(i);
		// int lastindex = str.lastIndexOf(c); // 0(n)
		// if (lastindex == str.indexOf(c)) {
		// System.out.println("1st non repeating char -> " + c);
		// return c;
		// }
		// }

		if (str == null || str.isEmpty())
			return null;

		// Hold ascii numbers only ...
		int[] arr = new int[256];

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			arr[c]++;
		}

		for (int i = 0; i < str.length(); i++) {

			char c = str.charAt(i);

			if (arr[c] == 1) {
				System.out.println("1st non repeating char -> " + c);
				return c;
			}
		}

		System.out.println("no non repeating char detected");

		return null;
	}

	/**
	 * Returns the shortest number of steps to build a word ladder from String
	 * begin - end given a dictionary of words Given start = "hit" & end = "cog"
	 * dict = ["hot","dot","dog","lot","log"] Shortest transformation is "hit"
	 * --->"hot"->"dot"->"dog"---> "cog" The program should return its length 5.
	 */
	private static int wordLadder(String startWord, String endWord, Set<String> dict) {
		int count = 0;
		Queue<String> q = new LinkedList<>();
		q.add(startWord);
		dict.add(endWord); // Make sure end word is in dictionary

		while (!q.isEmpty()) {
			String word = q.remove();

			if (word.equals(endWord)) // terminate here
				return count;

			char[] arr = word.toCharArray();
			for (int i = 0; i < arr.length; i++) {
				char temp = arr[i];

				for (char c = 'a'; c <= 'z'; c++) {
					if (arr[i] != c) {
						arr[i] = c;
					}

					String newWord = new String(arr);
					if (dict.contains(newWord)) { // check if newWord is in dict
						count++; // found a ladder increment count
						q.add(newWord); // Add the next new word to check
						dict.remove(newWord); // remove the new word we already
												// checked
						System.out.print(newWord + "->");
					}
				}

				arr[i] = temp; // reverse any changes to word
			}
		}

		return count;
	}

	/**
	 * Implement an algorithm to print all valid (e.g., properly opened and
	 * closed) combinations of n-pairs of parentheses 1 => () 2 => ()(), (())
	 */
	private static void printOpenCloseParenthesis(int n) {
		if (n <= 0)
			return; // throw exception
		char[] arr = new char[n * 2]; // need n*2 space
		printOpenCloseParenthesis(n, 0, 0, 0, arr);
	}

	private static void printOpenCloseParenthesis(int n, int pos, int open, int close, char[] arr) {

		if (n == close) { // at the end of length n
			System.out.println(new String(arr));
			return;
		} else {
			// if we need to close i.e. if we incremented open last
			// then it will be greater than close
			if (open > close) {
				arr[pos] = ')';
				// increment pos and close - notify we closed at pos index
				printOpenCloseParenthesis(n, pos + 1, open, close + 1, arr);
			}

			// if we have space to open
			if (open < n) {
				arr[pos] = '(';
				printOpenCloseParenthesis(n, pos + 1, open + 1, close, arr);
			}
		}
	}

	/**
	 * Return number of chars to delete to form anagram between two Strings
	 */
	public static int makeAnagramByDeletingChars(String a, String b) {

		// Match all like characters and frequency - the difference should be
		// deleted
		int count = 0; // num of chars to delete to form anagram
		int[] ascii = new int[256];

		for (char c : a.toCharArray()) {
			ascii[c]++; // frequency addition
		}

		for (char c : b.toCharArray()) {
			ascii[c]--; // frequency subtraction
		}

		for (int i : ascii) {
			count += Math.abs(i);
		}

		return count; // num of chars to delete to form anagram
	}

	public static String isBracketBalanced(String s) {
		if (s == null || s.isEmpty())
			return "NO";

		Map<Character, Character> map = new HashMap<>();
		map.put('(', ')');
		map.put('{', '}');
		map.put('[', ']');

		Stack<Character> stack = new Stack<>();

		for (char c : s.toCharArray()) {
			if (map.containsKey(c)) {
				stack.push(map.get(c)); // push the closing tag to match later
			} else {
				if (stack.isEmpty())
					return "NO";
				if (stack.pop() != c)
					return "NO";
			}
		}

		return stack.isEmpty() ? "YES" : "NO";

	}

	public static boolean isSubAnagram(String sub, String word) {
		if (sub == null || word == null)
			throw new IllegalArgumentException();

		int subLength = sub.length();
		int wordLength = word.length();
		int subSum = 0;

		if (subLength > wordLength)
			return false;

		for (int i = 0; i < subLength; i++) {
			char c = sub.charAt(i);
			subSum += Math.pow(c, 2);
		}

		for (int i = 0; i < wordLength; i++) {
			int index = i;
			int wordSum = 0;
			for (int k = 0; k < subLength && index < wordLength; k++) {
				char c = word.charAt(index++);
				wordSum += Math.pow(c, 2);
			}

			if (subSum == wordSum)
				return true;

		}

		return false;
	}

	/**
	 * Given any array of +ve integers e.g { 0, 3, 0, 0, 5, 6, 0 } put zeros
	 * behind ==> Expected 0,0,0,0,3,5,6
	 * 
	 */
	private static void moveZerosBehind() {
		int[] arr = new int[] { 0, 3, 0, 0, 5, 6, 0 };
		System.out.println(Arrays.toString(arr));

		int N = arr.length;
		int k = N - 1;

		// Assigning K index from right to left
		for (int i = N - 1; i >= 0; i--) {
			if (arr[i] != 0)
				arr[k--] = arr[i];
		}

		// Fill up other spaces with zero
		while (k >= 0) {
			arr[k--] = 0;
		}

		System.out.println(Arrays.toString(arr));

	}

	/**
	 * Reverse String words only Given: "one two three" Expected: "three two
	 * one" Note - No extra copy or String.split()
	 * Time O(n^2)  Space O(1)
	 */
	public static void reverseStringWordsOnly(String str) {

		// String equivalent - no extra copy;
		char[] arr = str.toCharArray();
		int N = arr.length;
		int i = 0;
		int j = N - 1;
		
		reverseChars(arr, i, j);
		
		for (int k = 0; k < N; k++) {
			if (String.valueOf(arr[k]).equals(" ")) {
				reverseChars(arr, i, k - 1); // use k-1
				i = k + 1;
			} else if (k == N - 1) {
				reverseChars(arr, i, k); // use k
			}
		}

		System.out.println(new String(arr));

	}

	private static void reverseChars(char[] arr, int lo, int hi) {
		int i = lo;
		int j = hi;
		while (i < j) {
			char temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
	}

	// static ReentrantLock mObject = new ReentrantLock();
	// static Object mObject2 = new Object();
	// public static void varTest(String... strs){
	// List<Integer> list = new ArrayList<>();
	// list.add(1);
	// list.add(2);
	// list.add(3);
	// Iterator<Integer> mIterator = null;
	//
	// synchronized(mObject2){
	// mIterator = list.iterator();
	// while(mIterator.hasNext()){
	// System.out.println("iterator "+ mIterator.next());
	// }
	// }
	//
	//
	// System.out.println(""+strs.length);
	// mObject.lock();
	// mObject.unlock();
	// }
} // End of class - Strings
