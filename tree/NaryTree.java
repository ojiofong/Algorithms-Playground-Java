package tree;

import java.util.LinkedList;
import java.util.Queue;

public class NaryTree {

	public static void main(String[] args) throws Exception {
		System.out.println(NaryTree.class.getSimpleName());

		String str = serializeNaryTree(createTestTree());
		Node root = deSerializeNaryTree(str);
		levelOrderNary(root);
	}

	/*-
	 E.g. Tree
	 		 0
	 	   / | \
	 	  1  2  3
	 	 / \     \
	 	4   5     7
	 	
	  Serialized-> 0,1,2,3,N,4,5,N,E,7,N,E,E,E
	  Advance by children from every parent. 
	  E is flag for Empty children. Just one index i.e. No left or right. 
	  N is flag for moving to New Node. Adding children is done.
	*/
	public static String serializeNaryTree(Node root) {
		if (root == null)
			return null;
		StringBuilder sb = new StringBuilder();
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		sb.append(root.key).append(",");
		// Add root first and afterwards advance by children e.g. left and right

		while (!queue.isEmpty()) {
			Node r = queue.remove();

			// Advance by assigning n-ary tree children
			if (r.children.isEmpty()) {
				sb.append("E").append(",");
			} else {
				for (Node child : r.children) {
					sb.append(child.key).append(",");
					// Continue BFS
					queue.add(child);
				}
				// flag N to move to New Node i.e. done adding children
				sb.append("N").append(",");
			}

		}

		String output = sb.deleteCharAt(sb.length() - 1).toString();

		System.out.println("Serialized-> " + output);

		return output;
	}

	/*-
	  Given Serialized Input string -> 0,1,2,3,N,4,5,N,E,7,N,E,E,E
	  Output tree:
	
	 		 0
	 	   / | \
	 	  1  2  3
	 	 / \     \
	 	4   5     7
	 	
	  Advance by children from every parent. 
	  E is flag for Empty children. Just one index i.e. No left or right. 
	  N is flag for moving to New Node. Adding children is done.
	*/
	public static Node deSerializeNaryTree(String str) {
		// Todo- validate inputStr, array length etc.

		String[] arr = str.split(",");
		int i = 1; // skip root index

		Node root = new Node(Integer.parseInt(arr[0]), arr[0]);
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {

			Node r = queue.remove();

			if (arr[i].equals("N") || (arr[i].equals("E"))) {
				i++; // Step-over one at a time to next index
			}

			while (!arr[i].equals("E") && !arr[i].equals("N")) {
				r.children.add(createNode(arr[i++]));
			}

			// Continue BFS
			for (Node child : r.children) {
				queue.add(child);
			}

		}

		return root;
	}

	private static Node createNode(String key) {
		return new Node(Integer.parseInt(key), key);
	}

	/*-
	       Tree
	 		 0
	 	   / | \
	 	  1  2  3
	 	 / \     \
	 	4   5     7 
	*/
	private static Node createTestTree() {
		Node root = new Node(0, "0");
		Node one = new Node(1, "1");
		Node two = new Node(2, "2");
		Node three = new Node(3, "3");
		Node four = new Node(4, "4");
		Node five = new Node(5, "5");
		Node seven = new Node(7, "7");

		root.children.add(one);
		root.children.add(two);
		root.children.add(three);
		one.children.add(four);
		one.children.add(five);
		three.children.add(seven);

		levelOrderNary(root);

		return root;
	}

	public static void levelOrderNary(Node root) {
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			int level = q.size();
			while (level-- > 0) {
				Node r = q.remove();
				System.out.print(r.key + " ");
				for (Node child : r.children) {
					q.add(child);
				}
			}
			System.out.println("** level");
		}
	}

}
