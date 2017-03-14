package miscellaneous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

/**
 * Miscellaneous
 */
public class Solution {

	public static void main(String[] args) {
		getMaxMemory(null);
		maxNoOfMeetingRooms(null);
		getValuesRandomlyByWeight(null);
		performAutoComplete(null, null);
		directorySolution(getSampleListing());
	}

	static class Node {
		long start;
		long end;
		long ram;

		public Node(long start, long end, long ram) {
			this.start = start;
			this.end = end;
			this.ram = ram;
		}

		public boolean collission(Node n) {
			boolean startCollides = n.start > this.start && n.start < this.end;
			boolean endCollides = n.end > this.start && n.end < this.end;
			return startCollides || endCollides;
		}

		public boolean collissionMeeting(Node n) {
			boolean startCollides = n.start >= this.start && n.start <= this.end;
			boolean endCollides = n.end > this.start && n.end < this.end;
			return startCollides || endCollides;
		}
	}

	/*-
	 * per process RAM consumption
	|
	|            3GB
	|          |----------------------|
	| 2GB      |                      |
	|-----|    |                      |
	|     |    |          1GB         |
	|     |    |     |-----------|    |
	|     |    |     |           |    |
	|_____|____|_____|___________|____|_____time
	t0    t1   t2    t3          t4   t5
	
	Here we'll return 4GB = (3+1) max memory needed
	
	*/
	// Time- O(n^2)
	// Space average O(V + E)
	public static long getMaxMemory(List<Node> list) {
		// test list data
		list = new ArrayList<>();
		list.add(new Node(0, 1, 2));
		list.add(new Node(2, 7, 3));
		list.add(new Node(3, 4, 1)); // expect 4
		list.add(new Node(5, 8, 2)); // expect 6

		if (list == null || list.isEmpty())
			throw new IllegalArgumentException();

		long maxRam = 0;

		// Graph - Adjacency list of neighbors with collision
		Map<Node, List<Node>> multi = new HashMap<>();

		for (Node n : list)
			System.out.print(n.end + ", ");

		// O(n^2)
		for (Node a : list) {
			for (Node b : list) {
				if (a != b && a.collission(b)) {
					List<Node> neighbors = multi.get(a);
					if (neighbors == null)
						neighbors = new ArrayList<>();

					neighbors.add(b);
					multi.put(a, neighbors);
				}
			}
		}

		// Calculate maxRam by adding collided neighbors ram
		// Space Average - O(V + E) -- worst O(V^2)
		// Time - same as "Space" for this solution
		for (Node a : list) {
			maxRam = Math.max(maxRam, a.ram);
			if (multi.containsKey(a)) {
				long sum = a.ram;
				for (Node b : multi.get(a)) {
					sum += b.ram;
				}
				maxRam = Math.max(maxRam, sum);
			}
		}

		System.out.println("\nmaxRam -> " + maxRam);

		return maxRam;
	}

	/*
	 * Given a list of meetings and times, find the max number of meeting rooms
	 * Request additional room if there's a time collision
	 */
	public static int maxNoOfMeetingRooms(List<Node> list) {
		// test list data
		list = new ArrayList<>();
		list.add(new Node(900, 1100, 0));
		list.add(new Node(900, 1100, 0));
		list.add(new Node(1330, 1500, 0));
		list.add(new Node(730, 1200, 0));
		list.add(new Node(900, 1100, 0)); // expect 4

		if (list == null || list.isEmpty())
			return 0;

		// Sort by end times
		Collections.sort(list, new Comparator<Node>() {
			public int compare(Node lhs, Node rhs) {
				return Long.compare(lhs.end, rhs.end);
			}
		});

		int rooms = list.isEmpty() ? 0 : 1;

		for (int i = 0; i < list.size() - 1; i++) {
			// if there's collision, increment rooms
			if (list.get(i).end >= list.get(i + 1).start) {
				rooms++;
			}
		}

		for (Node node : list)
			System.out.print(node.start + "-" + node.end + ", ");

		System.out.println("\nMax no. of rooms4 -> " + rooms);

		// returns 4
		return rooms;
	}

	private class NodeRandom {
		String value;
		int weight;

		public NodeRandom(String value, int weight) {
			this.value = value;
			this.weight = weight;
		}
	}

	/*
	 * Write a function that returns values randomly, according to their weight.
	 */
	public static String getValuesRandomlyByWeight(NodeRandom[] inputArr) {
		// Start - Creating sample input
		Solution solu = new Solution();
		NodeRandom n1 = solu.new NodeRandom("Apple", 4);
		NodeRandom n2 = solu.new NodeRandom("Orange", 2);
		NodeRandom n3 = solu.new NodeRandom("Grape", 7);
		inputArr = new NodeRandom[] { n1, n2, n3 };
		// End - Creating sample input

		int sum = 0;
		int prev = 0;
		int[] arr = new int[inputArr.length];
		for (int i = 0; i < inputArr.length; i++) {
			arr[i] = inputArr[i].weight + prev;
			prev = arr[i];
			sum += arr[i];
		}

		// now arr = {4, 6, 17}
		// sum = 4 + 6 + 17

		int key = new Random().nextInt(sum) + 1;

		for (int i = 0; i < arr.length; i++) {
			//
			if (key <= arr[i]) {
				String retValue = inputArr[i].value;
				System.out.println("Random value by weight -> " + retValue);
				return retValue;
			}
		}

		String retValue = inputArr[arr.length - 1].value;
		System.out.println("Random value by weight -> " + retValue);
		return retValue;
	}

	/*
	 * Implement Autocomplete.
	 */
	public static void performAutoComplete(List<String> listOfString, String sub) {
		// Start - Creating sample input
		sub = "subs";
		listOfString = new ArrayList<>();
		listOfString.add("substance");
		listOfString.add("sublet");
		listOfString.add("subconcious");
		listOfString.add("substandard");
		listOfString.add("subordinate");
		// End - Creating sample input

		List<String> mList = new ArrayList<>();
		for (String s : listOfString) {
			if (s.contains(sub)) {
				mList.add(s);
			}
		}

		System.out.println("Autocomplete -> " + mList.toString());
	}

	private class TrieNode {
		Map<Character, TrieNode> children;
		Set<String> words;
		boolean isTerminal;
		char c;

		public TrieNode(char c, Map next) {
			this.c = c;
			this.children = next;
		}
	}

	/*
	 * Implement Autocomplete with Trie. Incomplete solution
	 */
	public static void performAutoCompleteWithTrie(TrieNode root, String sub) {
		// Start - Creating sample input
		sub = "subs";
		// End - Creating sample input
		TrieNode cur = root;

		for (char c : sub.toCharArray()) {
			TrieNode next = cur.children.get(c);
			if (next == null)
				return;
			cur = next;
		}

		// Got to the last sub node
		// Now BFS to print all strings or Add to List<String>
		List<String> mList = new ArrayList<>();

		Queue<TrieNode> q = new LinkedList<>();
		q.add(cur);

		while (!q.isEmpty()) {
			TrieNode r = q.remove();
			if (r != null) {
				// print sub
				if (r.isTerminal)
					// add next
					for (char c = 'a'; c <= 'z'; c++) {
					if (r.children.get(c) != null) {
					q.add(r.children.get(c));
					}
					}
			}
		}

		System.out.println("Autocomplete Trie -> ");
	}

	// ---------START directorySolution ----------------//

	/*-
	 * Given a listing of files and directories in a file system
	 * as a String separated by new lines and space indentation
	 * 
	 * dir1
	 *  dir11
	 *  dir12
	 *   picture.jpg
	 *   dir121
	 *   file1.txt
	 * dir2
	 *  file2.gif
	 *  
	 * Find the longest absolute path to an image file (.jpg or .gif)
	 * From sample /dir1/dir12/picture.jpg is 24 characters long
	 * 
	 * */
	private static int directorySolution(String str) {
		int max = 0;
		String[] arr = str.split("\n");

		System.out.println(str);
		System.out.println(Arrays.toString(arr));
		System.out.println(isImage("test.jpg"));

		// Algorithm is to read from reverse once we find an image
		for (int i = 0; i < arr.length; i++) {
			String s = arr[i];
			if (isImage(s)) {
				int indentCount = indentCount(s);
				int loopTime = indentCount;
				int index = i;
				StringBuilder sb = new StringBuilder();
				sb.append(s.trim());
				while (loopTime > 0) {
					index--;
					int parentIndent = indentCount(arr[index]);
					if (parentIndent == indentCount)
						continue;

					sb.append("/").append(arr[index].trim());

					loopTime--;
				}
				sb.append("/");
				System.out.println(sb.toString());

				max = Math.max(max, sb.length());

				System.out.println("Max Directory " + max);
			}
		}

		return max;
	}

	private static String getSampleListing() {
		StringBuilder sb = new StringBuilder();
		sb.append("dir1").append("\n");
		sb.append(" dir11").append("\n");
		sb.append(" dir12").append("\n");
		sb.append("  picture.jpg").append("\n");
		sb.append("  dir121").append("\n");
		sb.append("  file1.txt").append("\n");
		sb.append("dir2").append("\n");
		sb.append(" file2.gif");

		return sb.toString();
	}

	private static int indentCount(String str) {
		return str.length() - str.replace(" ", "").length();
	}

	private static String ext(String str) {
		if (!str.contains("."))
			return null;
		return str.substring(str.lastIndexOf("."), str.length());
	}

	private static boolean isImage(String str) {
		return ".jpg".equals(ext(str)) || ".gif".equals(ext(str));
	}

	// ---------END directorySolution ----------------//

	/*-
	 *
	    Given a nested list of integers, returns the sum of all integers in the list
	    weighted by their depth  
	    given the list {{1,1},2,{1,1}} 
		return 10 (four 1's at depth 2, one 2 at depth 1) 
		10 = (4*1*2) + (1*2*1)
	*/
	public static int sumWeightedByDepth(List<Object> root) {
		// Get test data to match example - START
		root = new LinkedList<>();
		List<Object> twoOnes = new LinkedList<>();
		twoOnes.add(1);
		twoOnes.add(1);
		root.add(twoOnes);
		root.add(2);
		root.add(twoOnes);
		System.out.println("root->" + root);
		// Get test data to match example - END

		int sum = 0;
		int depth = -1; //
		Map<String, Integer> map = new HashMap<>();

		Queue<Object> q = new LinkedList<>();
		q.add(root);

		while (!q.isEmpty()) {

			int level = q.size();
			depth++; // depth == 0 i.e. no depth at root level

			while (level-- > 0) {

				Object r = q.remove();

				if (r instanceof Integer) {
					String key = depth + "," + r;
					int count = map.containsKey(key) ? map.get(key) : 0;
					map.put(key, count + 1);
				}

				if (r instanceof List) {
					for (Object obj : (List<?>) r) {
						q.add(obj);
					}
				}

			}
		}

		// Print all results from HashMap
		for (Entry<String, Integer> entry : map.entrySet()) {
			String mDepth = entry.getKey().split(",")[0];
			String num = entry.getKey().split(",")[1];
			String count = entry.getValue() + "";
			String output = String.format(Locale.US, "%s %s(s) at depth %s", count, num, mDepth);
			System.out.println(output);

			sum += Integer.parseInt(mDepth) * Integer.parseInt(num) * Integer.parseInt(count);
		}

		System.out.println("Total weighted sum->" + sum);

		return sum;
	}
}
