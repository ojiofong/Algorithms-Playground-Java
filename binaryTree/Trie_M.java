package binaryTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;

/*-
 * Prefix based binary tree 
 * l = average length of words & n = number of words
 * AVg. length of English words is approximately 5.
 * Insertion/Deletion - 0(l*n) 
 * Search 0(l)
 */
public class Trie_M {

	private static class Node {
		char c;
		String word;
		boolean isTerminal = false;
		Map<Character, Node> children = new HashMap<>();

		public Node(char c) {
			this.c = c;
		}
	}

	public static void main(String[] args) {

	}

	private static void printFromTrieSuffix(Node trieRoot, String suffix) {

		Node cur = trieRoot;

		for (int i = 0; i < suffix.length(); i++) {
			char c = suffix.charAt(i);
			Node next = cur.children.get(c);
			if (next == null)
				return;
			cur = next;
		}

		// Got to end node so far so start printing if terminal.

		Queue<Node> q = new LinkedList<>();
		q.add(cur);

		while (!q.isEmpty()) {
			Node r = q.remove();

			if (r.isTerminal) {
				System.out.println(r.word);
			}

			for (Entry<Character, Node> entry : r.children.entrySet()) {
				q.add(entry.getValue());
			}

		}

	}

	private void insert(Node trieRoot, String word) {
		// check input
		Node cur = trieRoot;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			Node next = cur.children.get(c);
			if (next == null) {
				cur.children.put(c, new Node(c));
				next = cur.children.get(c);
			}
			cur = next;
		}

		cur.isTerminal = true;

	}

	private static boolean delete(Node trieRoot, String word) {
		// check input
		Stack<Node> stack = new Stack<>();
		Node cur = trieRoot;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			Node next = cur.children.get(c);
			if (next == null)
				return false;
			
			stack.push(next);
			cur = next;
		}
		
		cur.isTerminal = false;
		
		while(!stack.isEmpty()){
			Node r = stack.pop();
			if(r.children.isEmpty()){
				r.children.remove(r.c);
			}else{
				return true; // get out early
			}
		}

		return true;
	}

	private static boolean contains(Node trieRoot, String word) {
		// check input
		Node cur = trieRoot;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			Node next = cur.children.get(c);
			if (next == null)
				return false;
			cur = next;
		}

		return cur.isTerminal;
	}

}
