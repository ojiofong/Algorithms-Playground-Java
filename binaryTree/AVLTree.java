package binaryTree;

/**
 * AVLTree - Self Balancing Binary Search Tree
 * */
public class AVLTree {

    private static class Node {
       int data;   //Data
       int ht;      //Height
       Node left;   //Left child
       Node right;   //Right child
       public Node(int data){
    	   this.data = data;
       }

   }

   public Node insert(Node root,int data){
       
       Node newNode = new Node(data);
       
       if(root == null){
           root = newNode;
       }else if(data < root.data){
           // focus left
           root.left = insert(root.left, data);
           if(unbalanced(root)){
                if(data < root.left.data){
                    // left left
                    root = rotateWithLeft(root);
                }else{
                    // left right
                    root = doubleWithLeft(root);
                }
           }
           
       }else if(data > root.data){
           // focus right
           root.right = insert(root.right, data);
           if(unbalanced(root)){
               if(data > root.right.data){
                    // right right
                    root = rotateWithRight(root);
                }else{
                    // right left
                    root = doubleWithRight(root);
                }
           }
       }
       
       //Update height
       root.ht = height(root);
       
       return root;
   }

   private boolean unbalanced(Node root){
       return Math.abs(height(root.left)-height(root.right)) > 1;
   }

   private static int height(Node root){
       if(root == null) return -1;
       if(isLeaf(root)) return 0;
       return 1 + Math.max(height(root.left), height(root.right));
   }

    private static boolean isLeaf(Node root){
        return root.left == null && root.right == null;
    }

    private static Node rotateWithLeft(Node root){
       
        Node n = root.left;
        root.left = n.right;
        n.right = root;
        
        //update heights
        root.ht = height(root);
        n.ht = height(n);
        
        return n;
   }

    private static Node rotateWithRight(Node root){

            Node n = root.right;
            root.right = n.left;
            n.left = root;

            //update heights
            root.ht = height(root);
            n.ht = height(n);

            return n;
    }

    private static Node doubleWithLeft(Node root){
            root.left = rotateWithRight(root.left);
            return rotateWithLeft(root);
    }

    private static Node doubleWithRight(Node root){
        root.right = rotateWithLeft(root.right);
        return rotateWithRight(root);
    }
    

	
	
}
