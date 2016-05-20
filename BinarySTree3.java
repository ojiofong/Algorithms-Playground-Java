import java.util.LinkedList;

public class BinarySTree3 {

	static Node root;
	private static boolean isLeftChild = true;

	public static void main(String[] args) {
		System.out.println("yes");
		BinarySTree3 bst = new BinarySTree3();
		bst.add(5, "five");
		bst.add(2, "two");
		bst.add(3, "three");
		bst.add(7, "seven");
		bst.add(9, "nine");
		bst.add(1, "one");

		// visitNode(root);
		inOrderTraversal(root);
		// preOrderTraversal(root);
		// preOrderTraversal(root);
		// System.out.println("found: " + bst.findNode(3));
		System.out.println("....");
		System.out.println("remove: " + bst.remove(7));
		System.out.println("remove: " + bst.remove(5));
		System.out.println("....");

		inOrderTraversal(root);
		
		LevelOrder(root);
	

	}

	public BinarySTree3() {
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
				if (key < parent.getKey()) {
					// focus on left child
					focus = parent.getLeftChild();
					// if there's nothing on the left
					if (focus == null) {
						newNode.setParent(parent);
						parent.setLeftChild(newNode);
						return; // done;
					}
				} else {
					// focus on the right child
					focus = parent.getRightChild();
					// if there's no right child
					if (focus == null) {
						newNode.setParent(parent);
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
	public Node findNode(int key) {

		Node focus = root;

		while (focus != null) {
			if (key == focus.getKey()) {
				return focus;
			}

			if (key < focus.getKey()) {
				// focus on the left
				focus = focus.getLeftChild();
				isLeftChild = true;
			} else {
				// focus on the right
				focus = focus.getRightChild();
				isLeftChild = false;
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
			inOrderTraversal(focus.getLeftChild());
			visitNode(focus);
			inOrderTraversal(focus.getRightChild());
		}
	}

	public static void preOrderTraversal(Node focus) {
		if (focus != null) {
			visitNode(focus);
			inOrderTraversal(focus.getLeftChild());
			inOrderTraversal(focus.getRightChild());
		}
	}

	public static void postOrderTraversal(Node focus) {
		if (focus != null) {
			inOrderTraversal(focus.getLeftChild());
			inOrderTraversal(focus.getRightChild());
			visitNode(focus);
		}
	}
	// END - Traverse

	public Object remove(int key) {
		Node focus = findNode(key);
		Node temp = focus;
		
		if (focus == null)
			return null;
		// Object item = focus.getData();

		if (isRoot(focus)) {
			if (isLeave(focus)) {
				root = null;
			} else if (hasLeftChildOnly(focus)) {
				root = focus.getLeftChild();
			} else if (hasRightChildOnly(focus)) {
				root = focus.getRightChild();
			} else if (hasBothChildren(focus)) {
				Node right = focus.getRightChild();
				Node left = focus.getLeftChild();
				root = left;
				addNode(right);
			}
		} else {
			// Not root
			if (isLeave(focus)) {
				if(isLeftChild){
					focus.getParent().setLeftChild(null);
				}else{
					focus.getParent().setRightChild(null);
				}
				//focus = null;
			} else {
				Node right = focus.getRightChild();
				Node left = focus.getLeftChild();
				//focus = null;
				if(isLeftChild){
					focus.getParent().setLeftChild(null);
				}else{
					focus.getParent().setRightChild(null);
				}
				addNode(right);
				addNode(left);
			}
		}

		return temp;
	}

	private boolean isRoot(Node focus) {
		return focus.getKey() == root.getKey();
	}

	private boolean isLeave(Node focus) {
		return focus.getLeftChild() == null && focus.getRightChild() == null;
	}

	private boolean hasLeftChildOnly(Node focus) {
		return focus.getLeftChild() != null && focus.getRightChild() == null;
	}

	private boolean hasRightChildOnly(Node focus) {
		return focus.getRightChild() != null && focus.getLeftChild() == null;
	}

	private boolean hasBothChildren(Node focus) {
		return focus.getLeftChild() != null && focus.getRightChild() != null;
	}

	// START - addNode
	public void addNode(Node newNode) {
		
		if(newNode==null) return;
		
		int key = newNode.getKey();

		if (root == null) {
			root = newNode;
		} else {
			Node focus = root;
			Node parent;
			while (true) {
				parent = focus;
				if (key < parent.getKey()) {
					// focus on left child
					focus = parent.getLeftChild();
					// if there's nothing on the left
					if (focus == null) {
						newNode.setParent(parent);
						parent.setLeftChild(newNode);
						return; // done;
					}
				} else {
					// focus on the right child
					focus = parent.getRightChild();
					// if there's no right child
					if (focus == null) {
						newNode.setParent(parent);
						parent.setRightChild(newNode);
						return; // done
					}

				}
			}
		}
	}
	// END - addNode

	private class Node {
		private Node leftChild, rightChild, parent;
		private int key;
		private Object data;

		public Node(int key, Object data) {
			this.key = key;
			this.data = data;
		}

		public void setLeftChild(Node node) {
			this.leftChild = node;
		}

		public void setRightChild(Node node) {
			this.rightChild = node;
		}
		
		public Node getLeftChild() {
			return this.leftChild;
		}

		public Node getRightChild() {
			return this.rightChild;
		}
		
		public void setParent(Node node){
			this.parent = node;
		}
		
		public Node getParent(){
			return this.parent;
		}


		public int getKey() {
			return this.key;
		}

		public Object getData() {
			return this.getData();
		}

		public String toString() {
			return "key: " + key + " Data: " + data;
		}
	}
	
	
	   static void LevelOrder(Node root){
		   
		   if(root==null)return;
		      
	       //System.out.print(root.data + " ");
	       LinkedList queue = new LinkedList<>();
	       queue.addLast(root);
	       
	       while(!queue.isEmpty()){
	           int levelCount = queue.size();
	           
	           while(levelCount > 0){     
	               Node r = (Node) queue.removeFirst();
	               System.out.print(r.data + " ");    
	               if(r.leftChild != null) queue.addLast(r.leftChild);
	               if(r.rightChild != null) queue.addLast(r.rightChild);
	               levelCount--;
	           }
	           // New Level
	           System.out.println("* ");
	       }
	      
	   }

}
