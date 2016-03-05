public class ProblemsTreesGraphs {

	public static void main(String[] args) {
		System.out.println(ProblemsTreesGraphs.class.getSimpleName());
		println("testing");
	}

	public static void println(String s) {
		System.out.println(s);
	}

	public static void print(String s) {
		System.out.print(s);
	}

	// check if tree is balanced
	// O(n) time and O(h) space where h is height
	// usage in - BinaryStree2
	public static boolean isTreeBalanced(NodeB root) {
		if (root == null)
			return true; // throw exception
		// height difference cannot be greater than 1 
		return Math.abs(getHeight(root.leftChild) - getHeight(root.rightChild)) < 2;
	}

	// @params - rootLeft or rootRight
	// you don't want to count root as 1
	public static int getHeight(NodeB node) {
		if (node == null)
			return 0;
		return 1 + Math.max(getHeight(node.leftChild), getHeight(node.rightChild));
	}

}
