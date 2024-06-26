package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
		solu.allSubstringsFaster("abc");
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

		System.out.println("isSpecial -> " + isSpecial("sprint"));
	}
	
	/**
	 * O(n^2) Time
	 * O(n^2) Space for indices
         * Faster because we get the start/end indices instead of the actual string within the nested loop
	 */
	public static List<String> allSubstringsFaster(String str) {
		List<String> result = new ArrayList<>();
		List<Integer> startList = new ArrayList<>();
		List<Integer> endList = new ArrayList<>();

		for (int i = 0; i < str.length(); i++) {
			for (int j = i; j < str.length(); j++) {
				startList.add(i);
				endList.add(j);
			}
		}

		for (int i = 0; i < startList.size(); i++) {
			int start = startList.get(i);
			int end = endList.get(i);
			String sub = str.substring(start, end + 1);
			result.add(sub);
		}
		
		return result;
	}

	public static void longestDuplicateSub(String str) {

		String longest = "";

		// Get all substrings of str
		List<String> substrings = allSubstringsFaster(str);
		Set<String> set = new HashSet<>();

		for (String sub : substrings) {
			if (!set.add(sub) && sub.length() > longest.length()) {
				longest = sub;
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
	 * Returns the first non repeating character in a string 
  	 * Time 0(n)
         * Space 0(256) or 0(1)
	 */
	private static Character firstNonRepeatingCharInString(String str) {
		if (str == null || str.isEmpty())
			return null;

		int[] arr = new int[256]; // ascii

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			arr[c]++;
		}

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (arr[c] == 1) {
				System.out.println("1st non repeating char:" + c);
				return c;
			}
		}

		System.out.println("no non repeating char found");
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

	/*-
	 * return true if substring of {str} is an anagram of {word}
	 * (str: "abcdef", word: "cba") => true because substring abc is anagram of cba
	 */
	private static boolean isSubstringAnagram(String str, String word){
		if (word.length() > str.length()) return false;

		int i =0; 
		int j = i + word.length() - 1;

		while (i<str.length() && j<str.length()){
			// substring of word length only
			String sub = str.substring(i, j+1); 
			System.out.println(sub);
			if (isAnagram(sub, word)){
				return true;
			}
			i += 1;
			j = i + word.length() - 1;
		}
		return false;
	}

	private static boolean isAnagram(String s1, String s2){
		if (s1.length() != s2.length()) return false;
		int i = 0;
		int sum = 0;

		while (i < Math.max(s1.length(), s2.length())){
			if (i < s1.length()){
				sum += s1.charAt(i) * s1.charAt(i); // perfect squares are unique
			}
			if (i < s2.length()){
				sum -= s2.charAt(i) * s2.charAt(i); // perfect squares are unique
			}
			i++;
		}

	    return sum == 0;
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
		for (int i = 0, j = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (ascii[c] > 0) {
				j = Math.max(j, ascii[c]); // latest first index of new
											// substring
			}
			ascii[c] = i + 1; // initial index end of new substring
			result = Math.max(result, i - j + 1); // + 1 for size since index
													// starts with zero
		}
		System.out.println("lengthOfLongestSubstringFast-> " + result);
		return result;
	}
 
	/*-
	 * Get Length of Longest Substring Without Repeating Characters
  	 * Input: s = "abcabcbb"
	 * Output: 3
	 * Explanation: The answer is "abc", with the length of 3.
         *
	 * O(n^2) time
	 * O(1) space
	 */
	 private static int lengthOfLongestSubstringWithoutRepeatingChars(String s) {
	    int longestLength = 0;
	    
	    for (int i=0; i<s.length(); s++){
	       Set<String> set = new HashSet<>();
	       for (j=i; j<s.length(); j++){
	         char c = s.charAt(j);
	         if (set.contains(c)){
	           break; // duplicate
	         }else{
	           set.add(c);
	           int currentLength = j - i + 1;
	           longestLength = Math.max(longestLength, currentLength)
	         }
	       }
	    }
		 
	    return longestLength;
	  }

	/*-
	 * Get Longest Palindrome Substring
	 * O(n^2) time
	 * O(1) space
	 */
	private static String longestPalindromeSubstring(String s) {    
	    if (s.isEmpty()) return s;
	    
	    int targetStart = -1;
	    int targetEnd = -1;
	    
	    for (int i=0; i<s.length(); i++) {
	       for (j=i, j<s.length(), j++){
	           int start = i;
	           int end = j;
	           int currentLength = end - start + 1;
	           int longestLengthSoFar = targetEnd - targetStart + 1;
	           if (currentLength > longestLengthSoFar) {
	             if (isPalindrome(s, start, end)) {  
	                targetStart = start;
	                targetEnd = end;
	             }
	           }
	       }
	    }
	    
	    return targetStart == -1 ? "" : s.substring(targetStart, targetEnd + 1);
	}

	private static boolean isPalindrome(String s) {
		int i = 0;
		int j = s.length() - 1;
		while (i <= j) {
			if (s.charAt(i) != s.charAt(j)) return false;
			i++;
			j--;
		}
		return true;
	}
	
	/*-
	 * Get all substrings that are palindrome
	 * O(n^2) time
	 * O(n) space
	 */
	public static Set<String> getPalindromeSubstrings(String str) {
		Set<String> set = new HashSet<>();
		for (int i = 0; i < str.length(); i++) {
			getPal(set, str, i, i);
			getPal(set, str, i, i + 1);
		}

		return set;
	}

	private static void getPal(Set<String> set, String s, int i, int j) {
		while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
			set.add(s.substring(i, j + 1)); // add start + end indices to avoid str.substring time complexity
			i--;
			j++;
		}
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
		dict.add("pit");
		dict.add("it");
		dict.add("i");

		Set<String> memo = new HashSet<>(); // memoization
		Set<Integer> result = new HashSet<>(); // hold word.length() found in
												// dict

		isSpecial(word, memo, dict, result);

		return result.size() == word.length();
	}

	private static void isSpecial(String word, Set<String> memo, Set<String> dict, Set<Integer> result) {

		if ( word == null || word.isEmpty() || memo.contains(word) )
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

	/**
	print(isMatch("f6k", "facebook")) // True
	print(isMatch("f6s", "focus"))      // False
	print(isMatch("i18n", "internationalization")) // True
	print(isMatch("i19n", "internationalization")) // False
	print(isMatch("f2eb2k", "facebook")) // True
	print(isMatch("7k", "facebook")) // True
	*/
	public boolean isMatchF6k(String pattern, String word){
		if (pattern.length() > word.length()) return false;
		StringBuilder sb = new StringBuilder();
		int i = 0; // pattern index
		int j = 0; // word index
		
		while (i < pattern.length() && j < word.length()) {
			char cur = pattern.charAt(i);
			boolean isPatternLastIndex = i == pattern.length() - 1;
			boolean isNextCharLetter = i + 1 < pattern.length() && !isDigit(pattern.charAt(i + 1));
			if (isDigit(cur)) {
				sb.append(cur);
				if (isPatternLastIndex || isNextCharLetter) {
					j += Integer.valueOf(sb.toString());
				}
			} else {
				if (cur != word.charAt(j)) return false;
				sb = new StringBuilder(); // reset numeric numbers e.g. 18, 123 (not just single digits)
				j++;
			}
			i++;
		}

		return i == pattern.length() && j == word.length();
       }

	public String addBinary(String a, String b) {
	        int carry = 0;
	        int aIndex = a.length() - 1;
	        int bIndex = b.length() - 1;
	        StringBuilder sb = new StringBuilder();
	        
	        while (aIndex >= 0 || bIndex >= 0){
	            int aValue = aIndex >= 0 ? Integer.valueOf("" + a.charAt(aIndex)) : 0;
	            int bValue = bIndex  >= 0 ? Integer.valueOf("" + b.charAt(bIndex)) : 0;
	            int result = aValue + bValue + carry;
	            
	            if (result == 3){
	                sb.insert(0, 1); // prepend 1: ([1 + 1] + 1 =>  [0] + 1 carry 1 => 1 carry 1)
	                carry = 1;
	            } else if (result == 2){
	                sb.insert(0, 0); // prepend 0: (1 + 1 => 0 carry 1)
	                carry = 1;
	            } else {
	                sb.insert(0, result); // prepend 0 or 1
	                carry = 0;
	            }
	            
	            aIndex--;
	            bIndex--;
	        }
	        
	        if (carry > 0){
	            sb.insert(0, carry); // prepend carry of 1
	        }
	        
	        return sb.toString();
    }
	
} // End of class - Strings
