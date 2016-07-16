package binaryTree;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySTree {

	static Node root;

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
		
		
//		NodeB root = new NodeB(5);
//		root.leftChild = new NodeB(10);
//		root.rightChild = new NodeB(15);
//		root.leftChild.leftChild = new NodeB(20);
//		root.leftChild.rightChild = new NodeB(25);
//		root.rightChild.leftChild = new NodeB(30);
//		root.rightChild.rightChild = new NodeB(35);

//		 NodeB root = new NodeB(5);
//		 root.leftChild = new NodeB(10);
//		 root.rightChild = new NodeB(15);
//		 root.leftChild.leftChild = new NodeB(20);
//		 root.leftChild.rightChild = new NodeB(25);
//		 root.rightChild.leftChild = new NodeB(30);
//		 root.rightChild.rightChild = new NodeB(35);

		// visitNode(root);
		// inOrderTraversal(root);
		// preOrderTraversal(root);
		levelOrder(root);
		//BTreePrinter.printNode(root);
		//levelOrder2()
		findBSTMinimumValueDifference(root);
		System.out.println("minDiff: " + minDiff);
		// preOrderTraversal(root);
		// System.out.println("found: " + bst.findNode(7));
//		System.out.println("height left: " + ProblemsTreesGraphs.getHeight(root.leftChild));
//		System.out.println("height right: " + ProblemsTreesGraphs.getHeight(root.rightChild));
//		System.out.println("isTreeBalanced: " + ProblemsTreesGraphs.isTreeBalanced(root));

	}

	static void findBSTMinimumValueDifference(Node root) {
		if (root != null) {
			findBSTMinimumValueDifference(root.leftChild);
			if (previous != null && minDiff > root.key - previous.key) {
				minDiff = root.key - previous.key;
			}
			previous = root;
			findBSTMinimumValueDifference(root.rightChild);
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
					focus = parent.leftChild;
					// if there's nothing on the left
					if (focus == null) {
						parent.leftChild = newNode;
						return; // done;
					}
				} else {
					// focus on the right child
					focus = parent.rightChild;
					// if there's no right child
					if (focus == null) {
						parent.rightChild = newNode;
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
				focus = focus.leftChild;
			} else {
				// focus on the right
				focus = focus.rightChild;
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
			inOrderTraversal(focus.leftChild);
			visitNode(focus);
			inOrderTraversal(focus.rightChild);
		}
	}

	public static void preOrderTraversal(Node focus) {
		if (focus != null) {
			visitNode(focus);
			inOrderTraversal(focus.leftChild);
			inOrderTraversal(focus.rightChild);
		}
	}

	public static void postOrderTraversal(Node focus) {
		if (focus != null) {
			inOrderTraversal(focus.leftChild);
			inOrderTraversal(focus.rightChild);
			visitNode(focus);
		}
	}

	// Similar to Breath First Search for Binary Tree
	private static void levelOrder(Node root){
		if(root==null) return;
		
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		
		while(!queue.isEmpty()){
			int level = queue.size();
			while(level-- > 0){
				Node r = queue.remove();
				System.out.print(" " + r.key);
				if(r.leftChild!=null)queue.add(r.leftChild);
				if(r.rightChild!=null)queue.add(r.rightChild);
			}
			System.out.println("*"); // New level
		}
		
		
	}
	
	private class Node {
		public Node leftChild, rightChild, parent;
		public Object data;
		public boolean visited;
		int key;
		
		public Node(int key, Object data) {
			this.key = key;
			this.data = data;
		}
	}

}
