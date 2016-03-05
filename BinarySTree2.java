
public class BinarySTree2 {

	static NodeB root;

	public static void main(String[] args) {
		System.out.println("yes");
		BinarySTree2 bst = new BinarySTree2();
		bst.add(5, "5");
		bst.add(7, "7");
		bst.add(9, "9");
		bst.add(2, "2");
		bst.add(1, "1");
		bst.add(17, "17");

		// visitNode(root);
		// inOrderTraversal(root);
		// preOrderTraversal(root);
		 BTreePrinter.printNode(root);
		//preOrderTraversal(root);
			//System.out.println("found: " + bst.findNode(7));
			System.out.println("height left: " + ProblemsTreesGraphs.getHeight(root.leftChild));
			System.out.println("height right: " + ProblemsTreesGraphs.getHeight(root.rightChild));
			System.out.println("isTreeBalanced: " + ProblemsTreesGraphs.isTreeBalanced(root));

	}

	public BinarySTree2() {
		root = null;
	}

	// START - add
	public void add(int key, Object data) {
		NodeB newNode = new NodeB(key, data);
		if (root == null) {
			root = newNode;
		} else {
			NodeB focus = root;
			NodeB parent;
			while (true) {
				parent = focus;
				if (key < parent.getKey()) {
					// focus on left child
					focus = parent.getLeftChild();
					// if there's nothing on the left
					if (focus == null) {
						parent.setLeftChild(newNode);
						return; // done;
					}
				} else {
					// focus on the right child
					focus = parent.getRightChild();
					// if there's no right child
					if (focus == null) {
						parent.setRightChild(newNode);
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
	public NodeB findNode(int key) {

		NodeB focus = root;

		while (focus != null) {
			if (key == focus.getKey()) {
				return focus;
			}

			if (key < focus.getKey()) {
				// focus on the left
				focus = focus.getLeftChild();
			} else {
				// focus on the right
				focus = focus.getRightChild();
			}
		}

		return null;

	}
	// END - find

	// START - visitNode
	public static void visitNode(NodeB node) {
		System.out.println("visited " + node);
	}
	// END - visitNode

	// START - Traverse
	public static void inOrderTraversal(NodeB focus) {
		if (focus != null) {
			inOrderTraversal(focus.getLeftChild());
			visitNode(focus);
			inOrderTraversal(focus.getRightChild());
		}
	}

	public static void preOrderTraversal(NodeB focus) {
		if (focus != null) {
			visitNode(focus);
			inOrderTraversal(focus.getLeftChild());
			inOrderTraversal(focus.getRightChild());
		}
	}

	public static void postOrderTraversal(NodeB focus) {
		if (focus != null) {
			inOrderTraversal(focus.getLeftChild());
			inOrderTraversal(focus.getRightChild());
			visitNode(focus);
		}
	}
	// END - Traverse

}
