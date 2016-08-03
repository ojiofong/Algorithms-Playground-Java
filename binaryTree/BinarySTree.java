package binaryTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySTree {

	static Node root;

	// private class Node {
	// Node left, right, parent;
	// public Object data;
	// public boolean visited;
	// int key;
	//
	// public Node(int key, Object data) {
	// this.key = key;
	// this.data = data;
	// }
	// }

	public static void main(String[] args) {
		System.out.println("yes");
		BinarySTree bst = new BinarySTree();
		bst.add(5, "5");
		bst.add(7, "7");
		bst.add(9, "9");
		bst.add(2, "2");
		bst.add(1, "1");
		bst.add(4, "4");
		bst.add(17, "17");

		// NodeB root = new NodeB(5);
		// root.leftChild = new NodeB(10);
		// root.rightChild = new NodeB(15);
		// root.leftChild.leftChild = new NodeB(20);
		// root.leftChild.rightChild = new NodeB(25);
		// root.rightChild.leftChild = new NodeB(30);
		// root.rightChild.rightChild = new NodeB(35);

		// NodeB root = new NodeB(5);
		// root.leftChild = new NodeB(10);
		// root.rightChild = new NodeB(15);
		// root.leftChild.leftChild = new NodeB(20);
		// root.leftChild.rightChild = new NodeB(25);
		// root.rightChild.leftChild = new NodeB(30);
		// root.rightChild.rightChild = new NodeB(35);

		// visitNode(root);
		// inOrderTraversal(root);
		// preOrderTraversal(root);
		levelOrder(root);
		BTreePrinter.printNode(root);
		serialize(root);
		// levelOrder2()
		findBSTMinimumValueDifference(root);
		System.out.println("minDiff: " + minDiff);
		// preOrderTraversal(root);
		// System.out.println("found: " + bst.findNode(7));
		// System.out.println("height left: " +
		// ProblemsTreesGraphs.getHeight(root.leftChild));
		// System.out.println("height right: " +
		// ProblemsTreesGraphs.getHeight(root.rightChild));
		// System.out.println("isTreeBalanced: " +
		// ProblemsTreesGraphs.isTreeBalanced(root));

	}

	static void findBSTMinimumValueDifference(Node root) {
		if (root != null) {
			findBSTMinimumValueDifference(root.left);
			if (previous != null && minDiff > root.key - previous.key) {
				minDiff = root.key - previous.key;
			}
			previous = root;
			findBSTMinimumValueDifference(root.right);
		}
	}

	static int minDiff = Integer.MAX_VALUE;
	static Node previous;

	public BinarySTree() {
		root = null;
	}

	// START - add
	public void add(int key, Object data) {
		Node newNode = new Node(key, data);
		if (root == null) {
			root = newNode;
		} else {
			Node focus = root;
			Node parent;
			while (true) {
				parent = focus;
				if (key < parent.key) {
					// focus on left child
					focus = parent.left;
					// if there's nothing on the left
					if (focus == null) {
						parent.left = newNode;
						return; // done;
					}
				} else {
					// focus on the right child
					focus = parent.right;
					// if there's no right child
					if (focus == null) {
						parent.right = newNode;
						return; // done
					}
				}
			}
		}
	}
	// END - add

	// START - name
	// END - name

	// START - find
	public Node findNode(int key) {

		Node focus = root;

		while (focus != null) {
			if (key == focus.key) {
				return focus;
			}

			if (key < focus.key) {
				// focus on the left
				focus = focus.left;
			} else {
				// focus on the right
				focus = focus.right;
			}
		}

		return null;

	}
	// END - find

	// START - visitNode
	public static void visitNode(Node node) {
		System.out.println("visited " + node);
	}
	// END - visitNode

	// START - Traverse
	public static void inOrderTraversal(Node focus) {
		if (focus != null) {
			inOrderTraversal(focus.left);
			visitNode(focus);
			inOrderTraversal(focus.right);
		}
	}

	public static void preOrderTraversal(Node focus) {
		if (focus != null) {
			visitNode(focus);
			inOrderTraversal(focus.left);
			inOrderTraversal(focus.right);
		}
	}

	public static void postOrderTraversal(Node focus) {
		if (focus != null) {
			inOrderTraversal(focus.left);
			inOrderTraversal(focus.right);
			visitNode(focus);
		}
	}

	// Similar to Breath First Search for Binary Tree
	public static void levelOrder(Node root) {
		if (root == null)
			return;

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int level = queue.size();
			while (level-- > 0) {
				Node r = queue.remove();
				System.out.print(" " + r.key);
				if (r.left != null)
					queue.add(r.left);
				if (r.right != null)
					queue.add(r.right);
			}
			System.out.println("*"); // New level
		}

	}

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
		deSerialize(s);
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

		BTreePrinter.printNode(root);
		// System.out.println("root yep " );

		return root;
	}

}
