package zzz;

import tree.BTreePrinter;
import tree.BinarySTree;
import tree.Node;

public class Test2 {

	public static void main(String[] args) throws Exception {

		System.out.println(Test2.class.getSimpleName());
		AVL bst = new AVL();
		bst.root = bst.insert(1, bst.root);
		bst.root = bst.insert(2, bst.root);
		bst.root = bst.insert(3, bst.root);
		
		
		
		System.out.println("size-> " + bst.size);
		System.out.println("root null-> " + (bst.root == null));
		BinarySTree.levelOrder(bst.root);
		BTreePrinter.printNode(bst.root);

	}

	static class AVL {
		// private static class Node {
		// int data;
		// int ht;
		// Node left, right;
		//
		// public Node(int data) {
		// this.data = data;
		// }
		// }

		Node root;
		int size;

		public AVL() {
			root = null;
			size = 0;
		}

		public Node insert(int data) {
			return insert(data, root);
		}

		private Node insert(int data, Node root) {
			Node newNode = new Node(data);

			if (root == null) {
				root = newNode;
			} else if (data < root.data) {
				root.left = insert(data, root.left);
				if (isUnbalanced(root)) {
					root = data < root.left.data ? rotateWithLeft(root) : doubleWithLeft(root);
				}
			} else {
				root.right = insert(data, root.right);
				if (isUnbalanced(root)) {
					root = data < root.right.data ? rotateWithRight(root) : doubleWithRight(root);
				}
			}

			size += 1;
			root.ht = height(root);
			return root;
		}

		private int height(Node n) {
			if (n == null)
				return -1;
			if (n.left == null && n.right == null)
				return 0;
			return 1 + Math.max(height(n.left), height(n.right));
		}

		private boolean isUnbalanced(Node n) {
			return Math.abs(height(n.left) - height(n.right)) > 1;
		}

		private boolean isUnbalanced2(Node n) {
			return Math.abs(n.left.ht - n.right.ht) > 1;
		}

		private Node rotateWithLeft(Node root) {
			Node n = root.left;
			root.left = n.right;
			n.right = root;
			root.ht = height(root);
			n.ht = height(n);
			return n;
		}

		private Node rotateWithRight(Node root) {
			Node n = root.right;
			root.right = n.left;
			n.left = root;
			root.ht = height(root);
			n.ht = height(n);
			return n;
		}

		private Node doubleWithLeft(Node root) {
			root.left = rotateWithRight(root.left);
			return rotateWithLeft(root);
		}

		private Node doubleWithRight(Node root) {
			root.right = rotateWithLeft(root.right);
			return rotateWithRight(root);
		}
	}

}
