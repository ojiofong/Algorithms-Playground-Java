
public class BinarySTree {

	public static void main(String[] args) throws Exception {
		BinarySTree bst = new BinarySTree();
		bst.addNode(5, "value5");
		bst.addNode(7, "value7");
		bst.addNode(3, "value3");
		bst.addNode(1, "value1");
		bst.addNode(2, "value2");
		bst.addNode(6, "value6");

		postOrderTraversal(bst.root);
		System.out.println("found key: " + bst.findKey(5));

	}

	Node root;

	public BinarySTree() {
		root = null;
	}

	private void addNode(int key, Object data) {
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

					// if no left child
					if (focus == null) {
						// set a left child to parent
						parent.setLeftChild(newNode);
						return; // done
					}
				} else {
					// focus on right child
					focus = parent.getRightChild();
					// if no right child
					if (focus == null) {
						// set parent right child
						parent.setRightChild(newNode);
						return; // done
					}
				}
			}

		}
	}

	public Node findKey(int key) {

		Node focus = root;
		
		while (focus != null) {
			
			if(focus.getKey() == key){
				return focus;
			}
			
			if (key < focus.getKey()) {
				// focus on left child
				focus = focus.getLeftChild();
			} else {
				// focus on right child
				focus = focus.getRightChild();
			}

		}

		return null;
	}

	/**
	 * 
	 * Node Class Below ***************************************
	 **/
	private class Node {

		Node leftChild, rightChild;
		Object data;
		int key;
		boolean visited;

		public Node(int key, Object data) {
			this.key = key;
			this.data = data;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public Node getLeftChild() {
			return leftChild;
		}

		public void setLeftChild(Node leftChild) {
			this.leftChild = leftChild;
		}

		public Node getRightChild() {
			return rightChild;
		}

		public void setRightChild(Node rightChild) {
			this.rightChild = rightChild;
		}
		
		public boolean isVisited(){
			return this.visited;
		}
		
		public void setVisited(boolean isVisited){
			this.visited = isVisited;
		}

		public String toString() {
			return data + " has the key " + key;
		}

	}

	private static void inOrderTraversal(Node focusNode) {
		if (focusNode != null) {
			inOrderTraversal(focusNode.getLeftChild());
			System.out.println(focusNode);
			inOrderTraversal(focusNode.getRightChild());
		}

	}
	private static void preOrderTraversal(Node focusNode) {
		if (focusNode != null) {
			System.out.println(focusNode);
			preOrderTraversal(focusNode.getLeftChild());
			preOrderTraversal(focusNode.getRightChild());
		}

	}
	private static void postOrderTraversal(Node focusNode) {
		if (focusNode != null) {
			postOrderTraversal(focusNode.getLeftChild());
			postOrderTraversal(focusNode.getRightChild());
			System.out.println(focusNode);
		}

	}
	
	public static void BreathFirstSearch(Node focusNode){
		if(focusNode !=null){
			visitNode(focusNode);
			focusNode.setVisited(true);
			MQueue queue = new MQueue();
			queue.enqueue(focusNode);
			
			Node f = focusNode;
			Node parent;
			while(!queue.isEmpty()){
				parent = f;
				Node r = (Node) queue.dequeue();
				// for (Node r : r.adjacent)
				if(r.getKey() < f.getKey()){
					//focus left
					f = f.getLeftChild();
					if(f!=null){
						
					}
				}
			}
		}
	}
	
	private static void visitNode(Node node){
		System.out.println(node);
	}

}
