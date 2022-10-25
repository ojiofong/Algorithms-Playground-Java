import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

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
		levelOrderTraversal(root);
		BTreePrinter.printNode(root);
		//levelOrder2()
		findBSTMinimumValueDifference(root);
		System.out.println("minDiff: " + minDiff);
		// preOrderTraversal(root);
		// System.out.println("found: " + bst.findNode(7));
		System.out.println("height left: " + ProblemsTreesGraphs.getHeight(root.leftChild));
		System.out.println("height right: " + ProblemsTreesGraphs.getHeight(root.rightChild));
		System.out.println("isTreeBalanced: " + ProblemsTreesGraphs.isTreeBalanced(root));

	}

	static void findBSTMinimumValueDifference(NodeB root) {
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
	static NodeB previous;

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

	// Similar to Breath First Search for Binary Tree
	public static void levelOrderTraversal(NodeB focus) {
		if (focus == null)
			return;
		MQueueDoubly q = new MQueueDoubly();
		//visitNode(focus);
		q.enqueue(focus);

		while (!q.isEmpty()) {

			int levelCount = q.size();

			// get neighbors in the same level
			while (levelCount > 0) {

				NodeB r = (NodeB) q.dequeue();

				visitNode(r);
				if (r.leftChild != null)
					q.enqueue(r.leftChild);
				if (r.rightChild != null)
					q.enqueue(r.rightChild);

				levelCount--;
			}

			System.out.println("...new level...");
		}
	}
	// END - Traverse
	
	
	private static void levelOrder2(NodeB root){
		if(root==null) return;
		
		Queue<NodeB> queue = new LinkedList<>();
		queue.add(root);
		
		while(!queue.isEmpty()){
			int level = queue.size();
			while(level-- > 0){
				NodeB r = queue.remove();
				System.out.print(" " + r.key);
				if(r.leftChild!=null)queue.add(r.leftChild);
				if(r.rightChild!=null)queue.add(r.rightChild);
			}
			System.out.println("*"); // New level
		}
		
		
	}

}
