import java.util.HashMap;
import java.util.Map;

public class BinarySTree5 {

	private static Node root;
	private static boolean isLeftChild = true;

	public static void main(String[] args) {
		System.out.println(BinarySTree5.class.getSimpleName());
		BinarySTree5 bst = new BinarySTree5();
		bst.add(10, "data");
		bst.add(6, "data");
		bst.add(7, "data");
		bst.add(9, "data");
		bst.add(2, "data");
		bst.add(4, "data");

		System.out.println("....");
		//System.out.println("found node -> " + bst.findNode(91).key);
		 ;
		 bst.inOrder(root);
		System.out.println("....");
		// bst.remove(2);
		System.out.println("....");
		// inOrderTraversal(root);
		// System.out.println("countSize: " + countSize(root));
		// hasDupicateSubTree(bst);

	}

	public void add(int key, Object data) {
		Node newNode = new Node(key, data);

		if (root == null) {
			root = newNode;
		} else {
			Node focus = root;
			Node parent = null;
			while (true) {
				parent = focus;
				if (key < focus.key) {
					// focus left
					focus = focus.leftChild;
					if (focus == null) {
						parent.leftChild = newNode;
						newNode.parent = parent;
						return; // Jump out of loop
					}
				} else {
					// focus right
					focus = focus.rightChild;
					if (focus == null) {
						parent.rightChild = newNode;
						newNode.parent = parent;
						return; // Jump out of loop
					}
				}
			}
		}
	}

	// Traversal
	public void inOrder(Node focus) {
		if (focus != null) {
			inOrder(focus.leftChild);
			visitNode(focus);
			inOrder(focus.rightChild);
		}
	}

	// VisitNode
	public void visitNode(Node node) {
		System.out.println("visited node-> " + node.key);
	}
	
	// find 
	public Node findNode(int key){
		Node focus = root;
		while(focus!=null){
			if(key == focus.key)
				return focus;
			
			if(key < focus.key){
				//focus left
				focus = focus.leftChild;
				isLeftChild = true;
			}else{
				// focus right
				focus = focus.rightChild;
				isLeftChild = false;
			}
		}
		return null;
	}

	public class Node {

		public Node leftChild, rightChild, parent;
		public int key;
		public Object data;

		public Node(int key, Object data) {
			this.key = key;
			this.data = data;
		}

	}
}
