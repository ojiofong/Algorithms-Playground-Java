package strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
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
		lengthOfLongestSubstring("ababcaabcabcaab");
		lengthOfLongestSubstringFast("ababcaabcabcaab");
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

	public static boolean isBracketBalanced(String s) {
		if (s == null || s.isEmpty())
			return false;

		HashMap<Character, Character> map = new HashMap<>();
		map.put('(', ')');
		map.put('{', '}');
		map.put('[', ']');

		Stack<Character> stack = new Stack<>();

		for (char c : s.toCharArray()) {
			if (map.containsKey(c)) {
				stack.push(map.get(c)); // push closing tag to match later
			} else if (stack.isEmpty() || c != stack.pop()) {
				return false;
			}
		}

		return stack.isEmpty();

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

		int i = 0;
		int j = arr.length - 1;

		while (i < j) {

			if (arr[i] == 0) {
				i++;
			} else if (arr[j] != 0) {
				j--;
			} else {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		System.out.println(Arrays.toString(arr));
	}

	/**
	 * Reverse String words only Given: "one two three" Expected: "three two
	 * one" Note - No extra copy or String.split() Time O(n^2) Space O(1)
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

	// subtracting by char '0'(zero) a char (of digit '0' to '9')
	// can be converted into int(0 to 9)
	public static void stringToInt(String str) throws Exception {

		System.out.println("stringToInt-> " + Integer.valueOf(str));

		char[] arr = str.toCharArray();
		double n = Math.pow(10, arr.length - 1);
		int ans = 0;
		int sign = str.charAt(0) == '-' ? -1 : 1;
		for (char c : arr) {
			if (c < '0' || c > '9')
				throw new Exception("Failed to parse string input");
			ans += (c - '0') * n;
			n = n / 10;
		}

		System.out.println("stringToInt-> " + ans);

	}

	// Start from right to left and apply factor (multiplication) appropriately
	public static void stringToInt2(String str) throws Exception {

		int factor = 1;
		int ans = 0;
		boolean isNegative = str.charAt(0) == '-';
		int firstCharIndex = isNegative ? 1 : 0;
		for (int j = str.length() - 1; j >= firstCharIndex; j--) {
			char c = str.charAt(j);
			if (c < '0' || c > '9') {
				throw new IllegalArgumentException("numbers only bro");
			}
			ans += (c - '0') * factor;
			factor *= 10;
		}

		ans = isNegative ? (ans * -1) : ans;

		System.out.println("ans-> " + ans);

	}

	/*-
	 * Given a string, find the length of the longest substring without repeating characters.
	   "abcabcbb" -> "abc", length is 3.
	   "bbbbb" -> "b", length of 1.
	   "pwwkew" ->"wke", length of 3.
	   Must be a substring, "pwke" is a subsequence and not a substring.

	   O(n^2) time and O(256) space
	 */
	private static int lengthOfLongestSubstring(String s) {
		int max = 0;
		if (s == null)
			return max;
		Set<Character> set = new HashSet<>();

		for (int i = 0; i < s.length(); i++) {
			for (int k = i; k < s.length(); k++) {
				char c = s.charAt(k);
				if (!set.add(c)) {
					set.clear();
					break; // Start from the next char
				}
				max = Math.max(max, set.size());
			}
		}
		System.out.println("lengthOfLongestSubstring-> " + max);
		return max;
	}

	/*-
	 * Given a string, find the length of the longest substring without repeating characters.
	   "abcabcbb" -> "abc", length is 3.
	   "bbbbb" -> "b", length of 1.
	   "pwwkew" ->"wke", length of 3.
	   Must be a substring, "pwke" is a subsequence and not a substring.

	   O(n) time and O(256) space
	 */
	private static int lengthOfLongestSubstringFast(String s) {
		if (s == null || s.trim().isEmpty())
			return 0;

		int result = 0;
		int[] ascii = new int[256];
		for(int i=0, j=0; i < s.length(); i++){
			char c = s.charAt(i);
			if(ascii[c] > 0){
				j = Math.max(j, ascii[c]); // latest first index of new substring
			}
			ascii[c] = i + 1; // first index of new substring
			result = Math.max(result, i-j+1); // + 1 for size since index starts with zero
		}
		System.out.println("lengthOfLongestSubstringFast-> " + result);
		return result;
	}
	
	private static String longestPalindrome(String s) {
		if (s == null || s.trim().isEmpty())
			return null;
		int length = s.length();
		String longest = null;
		for (int k = 0; k < length && longest == null || (length - k) > longest.length(); k++) {
			int j = length;
			while (k < j && longest == null || (j - k) > longest.length()) {
				String sub = s.substring(k, j);
				if (isPalindrome(sub)) {
					longest = sub;
				}
				j--;
			}
		}
		return longest == null ? s.charAt(0) + "" : longest;
	}

	private static boolean isPalindrome(String s) {
		int i = 0;
		int j = s.length() - 1;
		while (i <= j) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
	
	/*-
	 * Given a string, determine if it's a special word
	 * A special word is a word where every String minus one character is a dictionary word
	 * E.g. sprint -> print -> pint -> pin -> in -> i
	   
	   O(n!) time - n is number of chars in word
	   O(n) space - n is number of chars in word
	 */
	public static boolean isSpecial(String word) {
		
		Set<String> dict = new HashSet<>(); // sample dictionary
		dict.add("sprint");
		dict.add("print");
		dict.add("pint");
		dict.add("pin");
		dict.add("in");
		dict.add("i");
		
		Set<String> memo = new HashSet<>(); // memoization
		Set<Integer> result = new HashSet<>(); // hold word.length() found in dict
		
		isSpecial(word, memo, dict, result);
		
		return result.size() == word.length();
	}

	private static void isSpecial(String word, Set<String> memo, Set<String> dict, Set<Integer> result) {

		if (word == null || word.isEmpty() || memo.contains(word) || result.contains(word.length()))
			return;

		memo.add(word);

		if (dict.contains(word)) {
			result.add(word.length()); // nice trick to track the valid word level by String length

			for (int i = 0; i < word.length(); i++) {
				String sub = new StringBuilder(word).deleteCharAt(i).toString();
				isSpecial(sub, memo, dict, result);
			}
		}

	}
} // End of class - Strings
