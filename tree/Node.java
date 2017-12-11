package tree;

import java.util.LinkedList;
import java.util.List;

public class Node {

	// usage 1
	public Node left, right, parent;
	public int data;
	public int ht;
	public List<Node> children = new LinkedList<>();
	
	public Node(int data) {
		this.data = data;
	}
	

	// usage 2
	public Object objectData;
	public int key;

	public Node(int key, Object data) {
		this.key = key;
		this.objectData = data;
		this.data = Integer.parseInt((String)data);
	}

}
