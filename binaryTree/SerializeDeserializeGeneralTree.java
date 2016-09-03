package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*-
 * Serialize and de-serialize a general tree
 *         A
 *      /  |   \
 *    B    C     D
 *   / \        / | \ \
 *  E   F      I  G  H  J
 *      |
 *      K
 */
public class SerializeDeserializeGeneralTree {
	
	public static void main(String[] args){
		String s = serialize(getTestTree());
		Node tree = deSerialize(s) ;
		serialize(tree); // test deSerialized tree
	}

	private static class Node {
		char data;
		List<Node> children;

		public Node(char data) {
			this.data = data;
			this.children = new ArrayList<>();
		}
	}

	// output - A,>,B,C,D,#,B,>,E,F,#,C,>,#,D,>,I,G,H,J,#,K,>,
	public static String serialize(Node root) {

		if (root == null)
			return null;

		StringBuilder sb = new StringBuilder();

		Queue<Node> q = new LinkedList<>();
		q.add(root);

		while (!q.isEmpty()) {

			Node r = q.remove();

			if (r != null) {
				sb.append(r.data).append(",>,");

				for (Node n : r.children) {
					sb.append(n.data).append(",");
					q.add(n);
				}
				
				sb.append("#,");

			}
		}
		
		System.out.println("serialized " + sb.toString());

		return sb.toString();
	}
	
	
	public static Node deSerialize(String str){
		if(str == null || str.isEmpty()) return null;
		
		String[] arr = str.split(",");
		
		Node root = new Node(str.charAt(0));
		
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		
		int i = 0;
		while(!q.isEmpty()){
			
			Node r = q.remove();
			
			if(r != null){
				
				while(i < arr.length){
					if(arr[i].equals(">") || arr[i].equals(String.valueOf(r.data))){
						i++; // step over index
					}else if (arr[i].equals("#")){
						i++; // step over
						break; // break out
					}else{
						Node child = new Node(arr[i].charAt(0));
						r.children.add(child);
						q.add(child);
						i++;
					}
				}
				
			}
			
			
		}
		
		return root;
	}
	
	private static Node getTestTree(){
		Node A = new Node('A');
		Node B = new Node('B');
		Node C = new Node('C');
		Node D = new Node('D');
		Node E = new Node('E');
		Node F = new Node('F');
		Node G = new Node('G');
		Node H = new Node('H');
		Node I = new Node('I');
		Node J = new Node('J');
		Node K = new Node('K');
		
		A.children.add(B);
		A.children.add(C);
		A.children.add(D);

		B.children.add(E);
		B.children.add(F);

		D.children.add(I);
		D.children.add(G);
		D.children.add(H);
		D.children.add(J);

		F.children.add(K);
		
		return A;
	}

}
