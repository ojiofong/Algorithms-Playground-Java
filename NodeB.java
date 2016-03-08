
public class NodeB<T> {

	public NodeB leftChild, rightChild, parent;
	public Object data;
	public boolean visited;
	int key;

	public NodeB(Object data) {
		this.data = data;
	}

	public NodeB(int key, Object data) {
		this.key = key;
		this.data = data;
	}

	public String toString() {
		return data + " has the key " + key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getKey() {
		return this.key;
	}

	public NodeB getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(NodeB leftChild) {
		this.leftChild = leftChild;
	}

	public NodeB getRightChild() {
		return rightChild;
	}

	public void setRightChild(NodeB rightChild) {
		this.rightChild = rightChild;
	}

	public NodeB getParent() {
		return parent;
	}

	public void setParent(NodeB parent) {
		this.parent = parent;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	

}
