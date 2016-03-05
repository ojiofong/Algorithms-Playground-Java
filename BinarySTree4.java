import java.util.HashMap;
import java.util.Map;

public class BinarySTree4 {

	private static Node root;
	private static boolean isLeftChild = true;

	public static void main(String[] args) {
		System.out.println(BinarySTree4.class.getSimpleName());
		BinarySTree4 bst = new BinarySTree4();
		bst.add(10, "data");
		bst.add(6, "data");
		bst.add(7, "data");
		bst.add(10, "data");
		bst.add(6, "data");
		bst.add(7, "data");

		// bst.findNode(7);
		//inOrderTraversal(root);
		System.out.println("....");
		// bst.remove(2);
		System.out.println("....");
		//inOrderTraversal(root);
		System.out.println("countSize: " + countSize(root));
		hasDupicateSubTree(bst);

	}

	// Find if a given binary tree has duplicate sub trees or not.
	// (Two leaf nodes of a parent do not count as subtree)
	public static void hasDupicateSubTree(BinarySTree4 tree) {
		preOrderSubTreeTraversal(tree.getRoot());
	}

	static Map<String, String> visitedSubTree = new HashMap<>();

	public static boolean preOrderSubTreeTraversal(Node focus) {
		if (focus != null) {
			// visit node start//
			//visitNode(focus);

			if (focus.leftChild != null && focus.rightChild != null) {
				String subTree = "" + focus.key + "" + focus.leftChild.key + "" + focus.rightChild.key;

				System.out.println("visited subtree -> " + subTree);
				
				if (visitedSubTree.get(subTree) != null){
					System.out.println("subtree has duplicate");
					return true;
				}
				visitedSubTree.put(subTree, "yep");

			}
			// visit node end//
			preOrderSubTreeTraversal(focus.leftChild);
			preOrderSubTreeTraversal(focus.rightChild);
		}


		//System.out.println("subtree no duplicate");
		return false;
	}

	public BinarySTree4() {
		root = null;
	}

	public Node getRoot() {
		return root;
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
				if (key < parent.getKey()) {
					// focus left
					focus = parent.getLeftChild();
					// if null add to left
					if (focus == null) {
						newNode.setParent(parent);
						parent.setLeftChild(newNode);
						return; // done - exit loop
					}
				} else {
					// focus right
					focus = parent.getRightChild();
					// if null add to right
					if (focus == null) {
						newNode.setParent(parent);
						parent.setRightChild(newNode);
						return; // done - exit loop
					}
				}
			}
		}
	}
	// END - add

	// START - addNode
	public void addNode(Node newNode) {

		if (newNode == null)
			return;

		if (root == null) {
			root = newNode;
		} else {
			Node focus = root;
			Node parent;

			while (true) {
				parent = focus;
				if (newNode.getKey() < parent.getKey()) {
					// focus left
					focus = parent.getLeftChild();
					// if null add to left
					if (focus == null) {
						newNode.setParent(parent);
						parent.setLeftChild(newNode);
						return; // done - exit loop
					}
				} else {
					// focus right
					focus = parent.getRightChild();
					// if null add to right
					if (focus == null) {
						newNode.setParent(parent);
						parent.setRightChild(newNode);
						return; // done - exit loop
					}
				}
			}
		}
	}
	// END - addNode

	// START - findKey
	public Node findNode(int key) {

		Node focus = root;

		while (focus != null) {
			if (key == focus.getKey()) {
				visitNode(focus);
				return focus;
			}

			if (key < focus.getKey()) {
				// focus left
				focus = focus.getLeftChild();
				isLeftChild = true;
			} else {
				// focus right
				focus = focus.getRightChild();
				isLeftChild = false;
			}
		}

		return null;
	}
	// END - findKey

	// START - removeNode
	public Object remove(int key) {

		Node focus = findNode(key);

		if (focus == null)
			return null;

		Object item = focus.getData();

		if (isRoot(focus)) {
			print("is root");
			if (isLeave(focus)) {
				print("is leave");
				root = null;
			} else if (hasLeftChidOnly(focus)) {
				print("hasLeftChidOnly");
				root = focus.getLeftChild();
			} else if (hasRightChidOnly(focus)) {
				print("hasRightChidOnly");
				root = focus.getRightChild();
			} else if (hasBothChildren(focus)) {
				print("hasBothChildren");
				Node left = focus.getLeftChild();
				Node right = focus.getRightChild();
				root = left;
				addNode(right);
			}

		} else {
			print("is not root");
			if (isLeave(focus)) {
				print("is leave");
				if (isLeftChild) {
					focus.getParent().setLeftChild(null);
				} else {
					focus.getParent().setRightChild(null);
				}
			} else if (hasLeftChidOnly(focus)) {
				print("hasLeftChidOnly");
				Node left = focus.getLeftChild();
				if (isLeftChild) {
					focus.getParent().setLeftChild(left);
				} else {
					focus.getParent().setRightChild(left);
				}
			} else if (hasRightChidOnly(focus)) {
				print("hasRightChidOnly");
				Node right = focus.getRightChild();
				if (isLeftChild) {
					focus.getParent().setLeftChild(right);
				} else {
					focus.getParent().setRightChild(right);
				}
			} else if (hasBothChildren(focus)) {
				print("hasBothChildren");
				Node left = focus.getLeftChild();
				Node right = focus.getRightChild();
				if (isLeftChild) {
					focus.getParent().setLeftChild(left);
				} else {
					focus.getParent().setRightChild(left);
				}
				addNode(right);
			}
		}
		return item;
	}
	// END - removeNode

	private void print(String string) {
		System.out.println(string);

	}

	private boolean isRoot(Node node) {
		return node.getParent() == null;
	}

	private boolean isLeave(Node node) {
		return node.getLeftChild() == null && node.getRightChild() == null;
	}

	private boolean hasLeftChidOnly(Node node) {
		return node.getLeftChild() != null && node.getRightChild() == null;
	}

	private boolean hasRightChidOnly(Node node) {
		return node.getRightChild() != null && node.getLeftChild() == null;
	}

	private boolean hasBothChildren(Node node) {
		return node.getLeftChild() != null && node.getRightChild() != null;
	}

	// START - findKey
	// END - findKey

	// START - count size
	public static int countSize(Node focus) {
		if (focus == null)
			return 0;
		return countSize(focus.leftChild) + countSize(focus.rightChild) + 1;

	}
	// END - count size

	// START - TRAVERSAL
	public static void inOrderTraversal(Node focus) {
		if (focus != null) {
			inOrderTraversal(focus.leftChild);
			visitNode(focus);
			inOrderTraversal(focus.rightChild);
		}
	}
	// END - TRAVERSAL

	public static void visitNode(Node focus) {
		System.out.println("visited node with key: " + focus.getKey() + " data: " + focus.getData());
	}

	public class Node {
		Node leftChild, rightChild, parent;
		int key;
		Object data;

		public Node(int key, Object data) {
			this.key = key;
			this.data = data;
		}

		public void setLeftChild(Node leftChild) {
			this.leftChild = leftChild;
		}

		public void setRightChild(Node rightChild) {
			this.rightChild = rightChild;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public Node getLeftChild() {
			return this.leftChild;
		}

		public Node getRightChild() {
			return this.rightChild;
		}

		public Node getParent() {
			return this.parent;
		}

		public int getKey() {
			return this.key;
		}

		public Object getData() {
			return this.data;
		}

	}

}
