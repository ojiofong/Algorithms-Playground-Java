package tree;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserialize {

	public static void main(String[] args) throws Exception {
		System.out.println(SerializeDeserialize.class.getSimpleName());
		String str = serializeBinaryTree(createTree());
		Node root = deSerializeBinaryTree(str);
		BTreePrinter.printNode(root);
	}

	/*-
	 E.g. Tree
	 		0
	 	   / \
	 	  1
	 	 / \
	 	3   4
	 		 \
	 		  5
	  Serialized-> 0,1,E,3,4,E,E,E,5,E,E
	  Advance by children from every parent. 
	  E is Empty. 1st=left and 2nd=right
	*/
	public static String serializeBinaryTree(Node root) {
		if (root == null)
			return null;
		StringBuilder sb = new StringBuilder();
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		sb.append(root.key).append(",");
		// Add root first and afterwards advance by children e.g. left and right

		while (!queue.isEmpty()) {
			Node r = queue.remove();
			// Advance assigning children by 2 e.g. left and right
			sb.append(r.left == null ? "E" : r.left.key).append(",");
			sb.append(r.right == null ? "E" : r.right.key).append(",");

			// Continue BFS
			if (r.left != null) {
				queue.add(r.left);
			}
			if (r.right != null) {
				queue.add(r.right);
			}

		}

		String output = sb.deleteCharAt(sb.length() - 1).toString();

		System.out.println("Serialized-> " + output);

		return output;
	}

	/*-
	 
	  Given Serialized Input string -> 0,1,E,3,4,E,E,E,5,E,E
	  Output tree:
	 		0
	 	   / \
	 	  1
	 	 / \
	 	3   4
	 		 \
	 		  5
	  Add root. Then advance by children from every parent. 
	  E is Empty. 1st=left and 2nd=right
	*/
	public static Node deSerializeBinaryTree(String str) {
		// Todo- validate inputStr, array length etc.

		String[] arr = str.split(",");
		int i = 0;

		Node root = new Node(Integer.parseInt(arr[0]), arr[0]);
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		i++;

		while (!queue.isEmpty()) {

			Node r = queue.remove();

			// Advance assigning children by 2 e.g. left and right
			r.left = createNode(arr[i++]);
			r.right = createNode(arr[i++]);

			// Continue BFS
			if (r.left != null) {
				queue.add(r.left);
			}
			if (r.right != null) {
				queue.add(r.right);
			}

		}

		return root;
	}

	private static Node createNode(String key) {
		if (key == null || key.equals("E"))
			return null;
		return new Node(Integer.parseInt(key), key);
	}

	private static Node createTree() {
		Node root = new Node(0, "0");
		Node one = new Node(1, "1");
		Node four = new Node(4, "4");
		Node five = new Node(5, "5");
		root.left = one;
		one.left = new Node(3, "3");
		one.right = four;
		four.right = five;

		BTreePrinter.printNode(root);

		return root;
	}

}
