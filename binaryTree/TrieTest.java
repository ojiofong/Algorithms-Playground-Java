package binaryTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

public class TrieTest {

	public static void main(String[] args) {

		String text = readFile("sample");
		String[] words = getWords(text);
		Trie t = createIndex(words);
		// t.print();

		printPositions(t, "at");
	}

	static void printPositions(Trie t, String s) {
		if (t.contains(s)) {
			System.out.println(s + ": " + t.insertOrGetItem(s));
		}
	}

	static Trie createIndex(String[] words) {
		Trie t = new Trie();
		for (int i = 0; i < words.length; i++) {
			t.insertOrGetItem(words[i]).add(i);
		}
		return t;
	}

	static String[] getWords(String text) {
		return text.toLowerCase().split(" ");
	}

	static String readFile(String filename) {
		URL path = TrieTest.class.getResource(filename);
		StringBuilder sb = new StringBuilder();
		try{ 

			File f = new File(path.getFile());
			
			BufferedReader reader = new BufferedReader(new FileReader(f));
		
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}