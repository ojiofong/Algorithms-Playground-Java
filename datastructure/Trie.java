package datastructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import zzz.Test2;

/**
 * Prefix based binary tree l = average length of words & n = number of words
 * Insertion/Deletion - 0(l) 
 * Search 0(l)
 */
class Trie {
	
	public static void main(String[] args) throws Exception {
		Trie trie = new Trie();
		trie.add("one");
		trie.add("two");
		trie.add("three");
		trie.bfs();
		System.out.println("remove-> " + trie.remove("two"));
		System.out.println("remove-> " + trie.remove("one"));
		trie.bfs();
	}

	class Node {
		Character c;
		String word;
		Map<Character, Node> children = new HashMap<>();
		boolean isTerminal;

		public Node(Character c) {
			this.c = c;
		}
	}

	Node root;

	public Trie() {
		root = new Node(null);
	}

	public void add(String word) {
		checkInput(word);
		Node cur = root;
		for (char c : word.toCharArray()) {
			if (cur.children.get(c) == null) {
				cur.children.put(c, new Node(c));
			}
			cur = cur.children.get(c);
		}
		cur.isTerminal = true;
		cur.word = word;
	}

	public boolean remove(String word) {
		checkInput(word);
		Node cur = root;

		// Check if word exists
		if (!contains(word)) {
			return false;
		}

		// Perform deletion
		for (char c : word.toCharArray()) {
			if (cur.children.get(c).children.isEmpty()) {
				cur.children.remove(c);
				return true;
			}
			cur = cur.children.get(c);
		}
		return false;
	}

	public boolean contains(String word) {
		checkInput(word);
		Node cur = root;
		for (char c : word.toCharArray()) {
			if (cur.children.get(c) == null) {
				return false; // word does not exist
			}
			cur = cur.children.get(c);
		}
		return true;
	}

	private void checkInput(String word) {
		if (word == null || word.trim().isEmpty())
			throw new IllegalArgumentException();
	}

	public void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			Node r = q.remove();
			if (r.isTerminal) {
				System.out.println("visited-> " + r.word);
			}
			for (Map.Entry<Character, Node> entry : r.children.entrySet()) {
				q.add(entry.getValue());
			}
		}
	}
}