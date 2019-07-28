package tree;

public class DistBtwNodes {

	private static Node root;

	public static void main(String[] args) throws Exception {
		System.out.println(DistBtwNodes.class.getSimpleName());
		DistBtwNodes bst = new DistBtwNodes();

		int v1 = 1;
		int v2 = 3;

		Node lca = bst.lca(root, 1, 3);
		int dist2 = bst.heightDownToV(lca, v1) + bst.heightDownToV(lca, v2);
		System.out.println("ans** " + dist2);

	}

	public int heightDownToV(Node root, int v) {
		if (root == null)
			return 0;
		if (root.data == v)
			return 0;
		if (v < root.data)
			return 1 + heightDownToV(root.left, v);
		if (v > root.data)
			return 1 + heightDownToV(root.right, v);

		return 0;
	}

	public Node lca(Node root, int v1, int v2) {

		if (v1 < root.data && v2 < root.data) {
			// focus left
			return lca(root.left, v1, v2);
		}

		if (v1 > root.data && v2 > root.data) {
			// focus right
			return lca(root.right, v1, v2);
		}

		// Can't go further down to left or right
		// return the lca
		return root;

	}

}
