package binaryTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialize and Deserialize any binary Tree to/from a String
 * This solution uses Level Order traversal
 * */
public class SerializeDeserialize {

	public static String serialize(Node root) {
		if (root == null)
			return null;
		StringBuffer buffer = new StringBuffer();
		Queue<Node> q = new LinkedList<>();
		q.add(root);

		while (!q.isEmpty()) {
			Node r = q.remove();
			if (r != null) {
				buffer.append(r.data).append(",");
				q.add(r.left);
				q.add(r.right);
			} else {
				buffer.append("#").append(",");
			}
		}
		String s = buffer.deleteCharAt(buffer.length() - 1).toString();
		System.out.println("serialized -> " + s);
		return s;
	}

	public static Node deSerialize(String str) {
		if (str == null || str.isEmpty())
			return null;

		String[] arr = str.split(",");
		System.out.println(Arrays.toString(arr));
		Node root = new Node(Integer.parseInt(arr[0]));
		Queue<Node> q = new LinkedList<>();
		q.add(root);

		int i = 1;
		while (!q.isEmpty()) {

			Node r = q.remove();

			if (r != null) {
				// left
				r.left = arr[i].equals("#") ? null : new Node(Integer.parseInt(arr[i]));
				q.add(r.left);
				i++;

				// right
				r.right = arr[i].equals("#") ? null : new Node(Integer.parseInt(arr[i]));
				q.add(r.right);
				i++;
			}

		}

		return root;
	}
}
