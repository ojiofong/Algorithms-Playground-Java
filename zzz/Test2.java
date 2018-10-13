package zzz;

import java.util.HashSet;
import java.util.Set;

public class Test2 {

	public static void main(String[] args) throws Exception {
		System.out.println(Test2.class.getSimpleName());

		String word = "sprint";
		System.out.println("\nisSpecial->" + isSpecial(word));

	}
	/*-
	 * Given a string, determine if it's a special word
	 * A special word is a word where every String minus one character is a dictionary word
	 * E.g. sprint -> print -> pint -> pin -> in -> i
	   
	   O(n!) time and O(n) space
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
		
		System.out.println("recurse-> " + word);

		if (word == null || word.isEmpty() || memo.contains(word) || result.contains(word.length()))
			return;

		memo.add(word);

		if (dict.contains(word)) {
			result.add(word.length());

			for (int i = 0; i < word.length(); i++) {
				String sub = new StringBuilder(word).deleteCharAt(i).toString();
				isSpecial(sub, memo, dict, result);
			}
		}

	}

	private static Set<String> getDict() {
		Set<String> dict = new HashSet<>();
		dict.add("sprint");
		dict.add("print");
		dict.add("pint");
		dict.add("pin");
		dict.add("in");
		dict.add("i");
		return dict;
	}
	// endregion

}
