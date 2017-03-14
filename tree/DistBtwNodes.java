package tree;

import java.util.Scanner;

public class DistBtwNodes {

	private static Node root;
	private static Scanner in;

	public static void main(String[] args) throws Exception {
		System.out.println(DistBtwNodes.class.getSimpleName());
		DistBtwNodes bst = new DistBtwNodes();
//		bst.add(4);
//		bst.add(2);
//		bst.add(1);
//		bst.add(3);
		


//		System.out.println("....");
//		 bst.inOrder(root);
//		System.out.println("....");
		
		
		
		in = new Scanner(System.in);
		int A = in.nextInt(); // value of first node
		int B = in.nextInt(); // value of second node
		int N = in.nextInt(); // Number of nodes in tree
		boolean foundA = false;
		boolean foundB = false;

		if(!isNInRange(N)) throw new Exception("All trees must have fewer than 2^20 nodes");  
		
		
		// N integers the node values
		for (int i = 0; i < N; i++) {
			int mData = in.nextInt();

			if(!isValueInRange(mData)) throw new Exception("All values must fall in the range [0, 2^32]");
			
			bst.add(mData);

			foundA = foundA ? true : mData == A;
			foundB = foundB ? true : mData == B;
		}
		
		if(!foundA || !foundB){
			System.out.print("Not found");
			return;
		}
		

		 bst.inOrder(root);
		 
		 BTreePrinter.printNode(root);
		 int v1 = 1;
		 int v2 = 3;
		 
//		int dist =  bst.distanceBetween(root, v1, v2);
//		System.out.println("** " + dist);

		Node lca = bst.lca(root, v1, v2);
		int dist2 =  bst.heightDownToV(lca, v1) + bst.heightDownToV(lca, v2);
		System.out.println("f** " + dist2);
		
	}
	
	public int heightDownToV(Node root, int v) {
		if(root == null) return 0;
		if(root.data == v) return 0;
		if(v < root.data) return heightDownToV(root.left, v) + 1;
		if(v > root.data) return heightDownToV(root.right, v) + 1;
		
		return 0;
	}
	
	 private static boolean isNInRange(int N){
	        return N < Math.pow(2,20);
	    }

	     private static boolean isValueInRange(int value){
	        return value >=0 && value <= Math.pow(2,32);
	    }

	public void add(int data) {
		Node newNode = new Node(data);

		if (root == null) {
			root = newNode;
		} else {
			Node focus = root;
			Node parent = null;
			while (true) {
				parent = focus;
				if (data < focus.data) {
					// focus left
					focus = focus.left;
					if (focus == null) {
						parent.left = newNode;
						newNode.parent = parent;
						return; // Jump out of loop
					}
				} else {
					// focus right
					focus = focus.right;
					if (focus == null) {
						parent.right = newNode;
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
			inOrder(focus.left);
			visitNode(focus);
			inOrder(focus.right);
		}
	}

	// VisitNode
	public void visitNode(Node node) {
		System.out.println("visited node-> " + node.data);
	}
	
	
	public Node lca(Node root,int v1,int v2){

	    if(v1 < root.data && v2 < root.data){
	        // focus left
	        return lca(root.left, v1, v2);
	    }
	    
	    if(v1 > root.data && v2 > root.data){
	        // focus right
	        return lca(root.right, v1, v2);
	    }
	    
	    // Can't go further down to left or right
	    // return the lca
	    return root;
	    
	       
	}

}
