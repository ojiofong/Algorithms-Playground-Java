import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Problems {
	public static void main(String[] args) {
		System.out.println(Problems.class.getSimpleName());

		int[] arr = { 4, 3, 5, 1, 2 };
		sumOf3(arr, 6);

		String s1 = "abcd";
		String s2 = "dbca";
		String s3 = "aaabcdaaaaa";

		// System.out.println("length: " + s1.length());
		// System.out.println("first char: " + s1.charAt(0));
		// System.out.println("last char: " + s1.charAt(s1.length() - 1));
		// System.out.println("Has all unique char: " + allUniqueChar(s1));
		// System.out.println("permutation of other: " + permutationOfOther(s1,
		// s2));
		// System.out.println("compress string: " + s3 + " to " + compress(s3));
		// System.out.println("compress string better: " + s3 + " to " +
		// compressBetter(s3));
		// System.out.println("compress string Remix: " + s3 + " to " +
		// compressRemix(s3));
		//
		// String textString = "abcNjhgAhGjhfhAljhRkhgRbhjbevfhO";
		// String sample = "NAGARRO";
		// System.out.println("nagarro : " + nagarro(textString, sample));
		mergeLinkedList();
		// System.out.println("isIsomorphic : " + isIsomorphic("foo", "bar"));
		// duplicateAtIndex("abc", 2);
		// deleteArrayAtIndex("abc", 0);
		// System.out.println("isAnagram : " + isAnagram(s1, s2));
		// System.out.println("isAnagram2 : " + isAnagram2(s1, s2));
		// String shortStr = "xyz";
		// String longStr = "longlonglongxzyfoo";
		// System.out.println("isSubStringAnagram : " +
		// isSubStringAnagram(shortStr, longStr));
		// System.out.println("isSubStringAnagramBetter : " +
		// isSubStringAnagramBetter(shortStr, longStr));
		// // twoDArray();
		// permutationz("", "abc");
		// String str = "abc";
		// int i = 2;
		// System.out.println("\ntesting substing normal : " + str.substring(0,
		// str.length()));
		// System.out.println("\ntesting substing : " + str.substring(0, i) +
		// ".." + str.substring(i + 1, str.length()));
		// System.out.println("\nisSubStringDuplicate : " +
		// isSubStringDuplicate("foo", "bafoobafoo"));
		// System.out.println("isSubStringDuplicateFast : " +
		// isSubStringDuplicateFast("foo", "bafoobafoo"));
		// System.out.println("addArrayAndNumber : " +
		// Arrays.toString(addArrayAndNumber(new int[] { 0, 0, 1 }, 4)));
		// System.out.println("lengthOfLargestSubArray : " +
		// lengthOfLargestSubArray(arr, 2));
		// String intToBinary = Integer.toBinaryString(5);
		// System.out.println("binary print " + intToBinary);
		// String s = "10";
		// int b = Integer.parseInt(s, 2); // converts binary string to int -
		// // Throws NumberFormatException if not
		// // binary
		// System.out.println("binary addition " + Integer.toString(b, 2));
		// println("is_power_of_3Slow " + is_power_of_3Slow(27));
		// println("is_power_of_3Fast " + is_power_of_3Fast(27));
		// println("findDuplicateInArray " + findDuplicateInArray());
		// println("findDuplicateInArrayBetter " +
		// findDuplicateInArrayBetter());
		// println("joinTwoSortedArrays " + joinTwoSortedArrays());
		// println("combinationsofString " + combinationsofString("abc"));
		// println("removeDuplicates " + removeDuplicates("cutcopypaste"));
		// println("canSuffleWithoutRepeatingChar: " +
		// canSuffleWithoutRepeatingChar("apple"));
		// System.out.println("checkPalindroneAdvanced : " +
		// checkPalindrone("101", "101"));
		// System.out.println("biggestSumOfConsecutiveIntegers : " +
		// biggestSumOfConsecutiveIntegers());
		// System.out.println("getModeInArray : " + getModeInArray());
		//
		// // println("" + (int)'a');
	}

	private static String getModeInArray() {
		// TODO Auto-generated method stub
		int[] arr = new int[] { 2, 1, 3, 5, 6, 2, 4, 2, 9 };
		int length = arr.length;
		int tempCount = 0;
		int biggest = 0;

		for (int i = 0; i < length; i++) {
			for (int k = 0; k < length - 1; k++) {
				if (arr[i] == arr[k]) {
					tempCount++;
				}
			}
			// reset
			biggest = Math.max(biggest, tempCount);
			tempCount = 0;
		}
		return null;
	}

	public static void println(String s) {
		System.out.println(s);
	}

	// Isomorphic strings
	public static boolean isIsomorphic(String str1, String str2) {
		if (str1 == null || str2 == null)
			return false;
		if (str1.length() != str2.length())
			return false;
		if (str1.length() == 0)
			return true;

		Map<Character, Character> map = new HashMap<>();
		for (int i = 0; i < str1.length(); i++) {
			char c1 = str1.charAt(i);
			char c2 = str2.charAt(i);

			if (map.containsKey(c1) && map.get(c1) != c2) {
				return false;
			} else {
				map.put(c1, c2);
			}
		}

		return true;
	}

	// Find if the characters of the sample string is in the same order in the
	// text string..
	// Give a simple algo.. Eg..
	// TextString: abcNjhgAhGjhfhAljhRkhgRbhjbevfhO
	// Sample string :NAGARRO
	// checks for subsequence
	public static boolean nagarro(String textString, String sample) {
		// check for evil inputs later

		int mainLength = textString.length();
		int sampleLength = sample.length();
		int count = 0;
		for (int i = 0; i < mainLength; i++) {
			if (sample.charAt(count) == textString.charAt(i)) {
				count++;
			}
			if (count == sampleLength) {
				return true;
			}
		}

		return false;
	}

	// Given an integer linked list of which both first half and second half
	// are sorted independently. Write a function to merge the two parts to
	// create one single sorted linked list in place [do not use any extra
	// space].
	public static void mergeLinkedList() {

		// working now using merge from quick sort

		LinkedList<Integer> list = new LinkedList<>();
		list.addLast(3);
		list.addLast(5);
		list.addLast(6);
		// ----second half-----
		list.addLast(1);
		list.addLast(2);
		list.addLast(4);

		// Quick Sort

		System.out.println("mergeLinkedList -> " + list.toString());

		quickSortMerge(list, 0, list.size() - 1);

		System.out.println("mergeLinkedList -> " + list.toString());

	}

	private static void quickSortMerge(LinkedList<Integer> list, int lo, int hi) {

		int i = lo;
		int j = hi;
		// int mid = lo + (hi -lo)/2;

		while (i <= j) {
			// while(list.get(i) < list.get(mid)) i++;
			// while(list.get(j) > list.get(mid)) j--;
			if (i <= j)
				swap(list, i++, j--);
		}

		if (i <= hi)
			quickSortMerge(list, i, hi);
		if (lo <= j)
			quickSortMerge(list, lo, j);
	}

	private static void swap(LinkedList<Integer> list, int i, int j) {
		// TODO Auto-generated method stub
		int temp1 = list.get(i);
		int temp2 = list.get(j);
		list.remove(i);
		list.add(i, temp2);
		list.remove(j);
		list.add(j, temp1);

	}

	// Implement a method to perform basic string compression using the counts
	// of repeated
	// characters. For example, the string a a b c c c c c a a a would become
	// a2blc5a3.
	// If the "compressed" string would not become smaller than the original
	// string,
	// your method should return the original string.
	public static String compressBetter(String str) {

		if (null == str || str.length() == 0)
			return null;
		char last = str.charAt(0);
		int count = 1;
		StringBuffer buffer = new StringBuffer();
		for (int i = 1; i < str.length(); i++) {
			if (last == str.charAt(i)) {
				count++;
			} else {
				buffer.append(last).append(count);
				last = str.charAt(i);
				count = 1;
			}
		}

		buffer.append(last).append(count);

		String compressed = buffer.toString();
		if (compressed.length() < str.length()) {
			return compressed;
		}

		return str;
	}

	// Given two strings, write a method to decide if one is a permutation of
	// the other
	public static boolean permutationOfOther(String str1, String str2) {

		if (str1.length() != str2.length())
			return false;

		int k = str1.length() - 1;
		for (int i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) != str2.charAt(k)) {
				return false;
			}
			k--;
		}
		return true;
	}

	// Implement an algorithm to determine if a string has all unique
	// characters.
	// What if you cannot use additional data structures?
	public static boolean allUniqueCharSlow(String str) {

		for (int i = 0; i < str.length(); i++) {
			for (int k = i + 1; k < str.length(); k++) {
				if (str.charAt(i) == str.charAt(k)) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean allUniqueChar(String str) {

		// Assume ASCII string
		if (str.length() > 256)
			return false;

		boolean[] arr = new boolean[256];

		for (int i = 0; i < str.length(); i++) {
			if (arr[str.charAt(i)] == true) {
				return false;
			}

			arr[str.charAt(i)] = true;
		}

		return true;
	}

	public static void sumOf3(int[] arr, int sum) {
		int length = arr.length;
		Arrays.sort(arr);

		for (int i = 0; i < length; i++) {
			for (int k = i + 1; k < length - 1; k++) {
				int z = (sum - (arr[k] + arr[i]));
				if (binarySearch(arr, z) != -1) {
					System.out.println("" + z + " + " + arr[k] + " + " + arr[i] + " = " + sum);
					return;
				}
			}
		}

		System.out.println("sum of 3 not found");

	}

	public static int binarySearch(int[] arr, int key) {
		int lo = 0;
		int hi = arr.length - 1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (key < arr[mid])
				hi = mid - 1;
			else if (key > arr[mid])
				lo = mid + 1;
			else
				return key;
		}
		return -1;
	}

	public static boolean noDuplicate(int i, int j, int k) {
		return i != j && i != k && j != k;
	}

	public static void duplicateAtIndex(String str, int index) {
		int oldLength = str.length();
		char[] c = str.toCharArray();
		c = Arrays.copyOf(c, oldLength + 1);
		int newLength = c.length;

		System.out.println("before ->" + new String(c));

		for (int i = newLength - 1; i > index; i--) {
			c[i] = c[i - 1];
		}
		// c[index] = is already duplicated
		System.out.println("After ->" + new String(c));
	}

	public static void deleteArrayAtIndex(String str, int index) {
		char[] c = str.toCharArray();
		int length = c.length;

		System.out.println("before deleting index ->" + new String(c));

		for (int i = 0; i < length - 1; i++) {
			if (i >= index)
				c[i] = c[i + 1];
		}
		c = Arrays.copyOf(c, --length);
		System.out.println("After deleting index ->" + new String(c));
	}

	public static void twoDArray() {
		int[][] A = { { 1, 0, 12, -1 }, { 7, -3, 2, 5 }, { -5, -2, 2, -9 } };
		System.out.println("2D Array -> " + A[2][3]);

	}

	public static boolean isAnagram(String s1, String s2) {
		if (s1 == null || s2 == null)
			return false;
		if (s1.length() != s2.length())
			return false;
		if (s1.length() == 0)
			return true;

		int sum = 0;

		for (int i = 0; i < s1.length(); i++) {
			sum += (int) s1.charAt(i);
			sum -= (int) s2.charAt(i);
		}
		return sum == 0;
	}

	public static boolean isAnagram2(String s1, String s2) {
		if (s1 == null || s2 == null)
			return false;
		if (s1.length() != s2.length())
			return false;
		if (s1.length() == 0)
			return true;

		int[] ascii = new int[256]; 

		for (int i = 0; i < s1.length(); i++) {
			ascii[s1.charAt(i)]++;
			ascii[s2.charAt(i)]--;
		}

		for (int i = 0; i < s1.length(); i++) {
			if (ascii[s1.charAt(i)] != 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Time O(n^2) Space O(1)
	 */
	public static boolean isSubStringAnagramNew(String sub, String word) {

		if (sub == null || word == null)
			throw new IllegalArgumentException();
		if (sub.length() > word.length())
			return false;

		int subSum = 0;
		int subLength = sub.length();

		for (int i = 0; i < subLength; i++) {
			char c = sub.charAt(i);
			subSum += Math.pow(c, 2);
		}

		for (int i = 0; i < word.length(); i++) {
			int index = i;
			int curSum = 0;
			// subLength number of times starting from index i
			for (int k = 0; k < subLength && index < word.length(); k++) {
				char c = word.charAt(index);
				curSum += Math.pow(c, 2);
			}

			if (curSum == subSum)
				return true;
		}

		return false;
	}

	/**
	 * O(n) time O(256) space
	 */
	public static boolean isSubStringAnagramBetter(String shortStr, String longStr) {

		if (shortStr == null || longStr == null)
			return false;
		if (shortStr.length() > longStr.length())
			return false;
		if (shortStr.length() == 0 && longStr.length() == 0)
			return true;

		int[] arr = new int[256];
		int shortSum = 0;
		int anagramSum = 0;
		int count = 0;

		for (int i = 0; i < shortStr.length(); i++) {
			arr[shortStr.charAt(i)]++;
			shortSum += (int) shortStr.charAt(i); // square this Math.pow(a, b)
		}

		for (int i = 0; i < longStr.length(); i++) {
			if (arr[longStr.charAt(i)] != 0) {
				anagramSum += (int) longStr.charAt(i); // square this
														// Math.pow(a, b)

				count++;

				if (count == shortStr.length() && anagramSum == shortSum) {
					return true; // anagram found in long string
				}
			} else if (count > 0) {
				// reset
				anagramSum = 0;
				count = 0;
			}
		}

		return false;
	}

	public static boolean isSubStringAnagram(String shortStr, String longStr) {
		if (shortStr == null || longStr == null)
			return false;
		if (shortStr.length() > longStr.length())
			return false;
		if (shortStr.length() == 0 && longStr.length() == 0)
			return true;

		int[] arr = new int[256];
		int[] arrOriginal = new int[256];

		for (int i = 0; i < shortStr.length(); i++) {
			arr[shortStr.charAt(i)]++;
			arrOriginal[shortStr.charAt(i)]++;
		}

		int count = 0;

		for (int i = 0; i < longStr.length(); i++) {

			if (arr[longStr.charAt(i)] != 0) {
				arr[longStr.charAt(i)]--;
				count++;

				if (count == shortStr.length())
					return true; // found

			} else if (count > 0) {
				count = 0; // reset
				arr = arrOriginal.clone();
			}

		}

		return false;
	}

	public static void permutationz(String prefix, String str) {

		int length = str.length();

		if (length == 0) {
			System.out.print("[" + prefix + "]");
		} else {

			for (int i = 0; i < length; i++)
				permutationz(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, length));

		}
	}

	public static boolean isSubStringDuplicate(String sub, String str) {

		// check for evil inputs later
		int subLength = sub.length();
		int mainLength = str.length();
		int count = 0;
		boolean found = false;
		for (int i = 0; i < mainLength; i++) {
			if (str.charAt(i) == sub.charAt(count)) {
				count++;
				if (count == subLength) {
					if (found)
						return true;
					System.out.print("[Found substring " + sub + "]");
					found = true;
					count = 0;
				}
			} else if (count > 0) {
				// reset
				count = 0;
			}
		}

		return false;
	}

	public static boolean isSubStringDuplicateFast(String sub, String str) {

		return str.replaceFirst(sub, "").contains(sub);
	}

	// Given an array and a number, add it in such a way
	// where array is [0,0,1] and number is 4 output will be [0,0,5]
	// Example 2 : array is [1] and number is 9 output will be [1,0]
	public static int[] addArrayAndNumber(int[] arr, int num) {

		arr = new int[] { 1, 9 };
		num = 9;

		int length = arr.length;
		int lastIndexValue1 = arr[length - 1];
		int lastIndexValue2 = lastIndexValue1 + num;
		String valueAsString = "" + lastIndexValue2;
		System.out.println("finally last " + valueAsString);

		arr = Arrays.copyOf(arr, length + valueAsString.length() - 1);

		for (int i = 0; i < valueAsString.length(); i++) {
			arr[length - 1 + i] = Integer.parseInt("" + valueAsString.charAt(i));
		}

		return arr;
	}

	public static int lengthOfLargestSubArray(int[] arr, int S) {

		arr = new int[] { 0, 1, 0, 1, 1, -1, 1 };
		S = 2;

		// int runningLength = 0;
		int sum = 0;
		int maxIndex = -1;

		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (sum == S) {
				maxIndex = i;
			}
		}

		int length = maxIndex + 1;

		return length;
	}

	// Write function to determine if given unsigned 32-bit number is a power of
	// 3
	//
	// int is_power_of_3(uint32_t n)
	// return 1 if yes, 0 otherwise.
	//
	// e.g.
	// is_power_of_3(27) = 1
	// is_power_of_3(9) = 1
	// is_power_of_3(42) = 0
	// is_power_of_3(0) = 0
	public static int is_power_of_3Slow(int n) {
		if (n == 0)
			return 0;
		while (n % 3 == 0) {
			n = n / 3;
		}
		return n == 1 ? 1 : 0;
	}

	public static int is_power_of_3Fast(int n) {
		if (n == 0)
			return 0;
		// Bcos 3^20 is less than (2^32 - 1)
		// int literal is out range for int so use long
		long largestPowerOf3ForUint32 = (long) Math.pow(3, 20); // 3^20
		// long largestPowerOf3ForUint32 = Long.valueOf(("3486784401")); //3^20
		println("finally largetst " + largestPowerOf3ForUint32);
		return largestPowerOf3ForUint32 % n == 0 ? 1 : 0;
	}

	// In an integer array, there is 1 to 100 number, out of one is duplicate,
	// how to find ?
	// Better Add to Set.
	public static int findDuplicateInArray() {
		// total serially = n(n+1)/2;
		int[] arr = { 1, 2, 3, 3, 4, 5 };
		int n = arr.length;
		int totalSerially = n * (n + 1) / 2;
		int sum = 0;
		for (int i : arr) {
			sum += i;
		}
		return totalSerially - sum;
	}

	public static int findDuplicateInArrayBetter() {
		String s = "abcbc";
		String s2 = "";
		System.out.println("string reverse: " + s + " -> " + new StringBuffer(s).reverse());
		for (int i = s.length() - 1; i >= 0; i--) {
			s2 += "" + s.charAt(i);
		}
		System.out.println("string reverse2: " + s + " -> " + s2);

		int[] arr = { 1, 2, 3, 3, 4, 5 };
		Set<Integer> set = new HashSet<>();
		for (int i : arr) {
			if (!set.add(i)) {
				return i;
			}
		}

		return 0;
	}

	// [1, 7, 8] and [2, 5, 6, 9]
	public static String joinTwoSortedArrays() {
		int[] arr1 = { 1, 7, 8 };
		int[] arr2 = { 2, 5, 6, 9 };
		int[] arr3 = new int[arr1.length + arr2.length];

		int i = 0;
		int j = 0;
		int k = 0;
		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] < arr2[j]) {
				arr3[k++] = arr1[i++];
			} else {
				arr3[k++] = arr2[j++];
			}
		}

		while (i < arr1.length) {
			arr3[k++] = arr1[i++];
		}

		while (j < arr2.length) {
			arr3[k++] = arr2[j++];
		}

		return Arrays.toString(arr3);
	}

	public static String combinationsofString(String str) {

		List<String> mList = new ArrayList<>();
		for (int i = 0; i < str.length(); i++) {
			int listLength = mList.size();
			for (int j = 0; j < listLength; j++) {
				mList.add("" + str.charAt(i) + mList.get(j));
			}
			mList.add("" + str.charAt(i));
		}
		return mList.toString();
	}

	// Remove duplicates from string given " cutcopypaste " Return "uoyase"
	public static String removeDuplicates(String str) {

		Set<Character> set = new HashSet<>();

		for (char c : str.toCharArray()) {
			if (!set.add(c)) {
				set.remove(c);
			}
		}

		StringBuffer buffer = new StringBuffer();
		Iterator<Character> iterator = set.iterator();
		while (iterator.hasNext()) {
			buffer.append(iterator.next());
		}

		return buffer.toString();
	}

	// Define a function that can detect whether the characters of a string can
	// be shuffled
	// without repeating same characters as one other's neighbors. E.g. :
	// apple >> alpep, so valid
	// a >> a, valid
	// aa >> aa, invalid/impossible
	// aab >> aba, valid
	// aaaabbcc >> acabacab, valid
	// etc.
	// You do not have to find one representation, just have to detect if it is
	// possible or not!
	public static boolean canSuffleWithoutRepeatingChar(String str) {
		if (str.length() == 1)
			return true;
		int length = str.length();
		// detect if (length - char count ) >= char count - 1;
		int[] arr = new int[256]; // unicode

		for (int i = 0; i < length; i++) {
			arr[str.charAt(i)]++;
		}

		for (int i = 0; i < length; i++) {
			if (((length - arr[str.charAt(i)])) >= (arr[str.charAt(i)] - 1)) {
				return true;

			}
		}

		return false;
	}

	public static boolean checkPalindrone(String s1, String s2) {

		int length = s1.length();
		char[] c2 = s2.toCharArray();
		char[] c1 = s1.toCharArray();

		int k = 0;
		for (int i = length - 1; i >= 0; i--) {
			if (c2[i] != c1[k]) {
				return false;
			}
			k++;
		}

		return true;
	}

	// find consecutive integers in a list that give you the biggest sum
	// Like for -2 5 -1 7 -3 it would be 5 -1 7
	public static String biggestSumOfConsecutiveIntegers() {
		// this is Kadane's algorithm
		// algorithm relies on at least one positive number else
		// if all negative return the largest negative value

		int[] arr = new int[] { -2, 5, -1, 7, -3, };
		int length = arr.length;

		int sumCurrent = arr[0];
		int sumSoFar = arr[0];
		int minIndex = 0;
		int maxIndex = 0;

		for (int i = 0; i < length; i++) {
			sumCurrent += arr[i];
			if (sumCurrent < arr[i])
				minIndex = i;

			// sumCurrent = Math.max(0, sumCurrent); // return zero if negative
			if (sumCurrent < 0) {
				sumCurrent = 0;
			} else {
				// sumSoFar = Math.max(sumCurrent, sumSoFar); // get the max
				// only
				if (sumSoFar < sumCurrent) {
					sumSoFar = sumCurrent;
					maxIndex = i;
				}

			}
		}

		String ans = "sum of biggest subArray: " + sumSoFar;
		ans += " min:" + arr[minIndex] + " max;" + arr[maxIndex];

		return ans;
	}

	public static String biggestSumOfConsecutiveIntegersSumOnly() {
		// this is Kadane's algorithm
		// algorithm relies on at least one positive number else
		// if all negative return the largest negative value
		// This method assumes there's at least one positive number

		int[] arr = new int[] { -2, 5, -1, 7, -3 };
		int length = arr.length;

		int sumCurrent = 0;
		int sumSoFar = 0;

		for (int i = 0; i < length; i++) {
			sumCurrent += arr[i];
			sumCurrent = Math.max(0, sumCurrent); // return zero if negative
			sumSoFar = Math.max(sumCurrent, sumSoFar); // get the max only
		}

		return "sum of biggest subArray: " + sumSoFar;
	}

}
