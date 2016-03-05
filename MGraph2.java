
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MGraph2 {

	public static void main(String[] args) {
		System.out.println(MGraph2.class.getSimpleName());

		MGraph2 graph = new MGraph2();
		graph.addEdge(new NodeG("A", "Data A"), new NodeG("B", "Data B"));
		graph.addEdge(new NodeG("A", "Data A"), new NodeG("E", "Data E"));
		graph.addEdge(new NodeG("A", "Data A"), new NodeG("C", "Data C"));
		graph.addEdge(new NodeG("C", "Data C"), new NodeG("B", "Data B"));
		graph.addEdge(new NodeG("C", "Data C"), new NodeG("D", "Data D"));
		graph.addEdge(new NodeG("E", "Data E"), new NodeG("F", "Data F"));

		// graph.BFS(new NodeG("A", "Data A"));
		// graph.DFS(new NodeG("A", "Data A"));
		boolean flag = graph.isDirectionBtw(new NodeG("A", "Data A"), new NodeG("E", "Data E"));
		System.out.println("is direction: " + flag);

	}

	Map<String, List<String>> multi;
	Map<String, NodeG> adjacencyMap;

	public MGraph2() {
		multi = new HashMap<>();
		adjacencyMap = new HashMap<>();
	}

	public void addEdge(NodeG src, NodeG dest) {
		List<String> list = multi.get(src.key);
		if (list == null) {
			multi.put(src.key, list = new ArrayList<>());
		}
		list.add(dest.key);
		adjacencyMap.put(src.key, src);
		adjacencyMap.put(dest.key, dest);
	}

	public List<String> getNeigbours(NodeG node) {
		List<String> list = multi.get(node.key);
		if (list == null) {
			return new ArrayList<>();
		}
		return list;
	}

	public List<String> getNeigboursNode(NodeG node) {
		List<String> list = multi.get(node.key);
		if (list == null) {
			return new ArrayList<>();
		}
		return list;
	}

	public NodeG getNode(String key) {
		return adjacencyMap.get(key);
	}

	public static void visitNode(NodeG node) {
		System.out.println("visited node: " + node.key);
	}

	public static void visitNode(NodeG focus, NodeG node2) {
		System.out.println("visited node1: " + focus.key + " node2: " + node2.key);
		//return focus.key.equals(node2.key);
	}

	public void BFS(NodeG root) {
		// Set<String> visited = new HashSet<>();
		MQueue2 queue = new MQueue2();
		visitNode(root);
		// visited.add(root.key);
		root.visited = true;
		queue.enqueue(root);

		while (!queue.isEmpty()) {
			NodeG r = (NodeG) queue.dequeue();
			for (String nodeKey : getNeigbours(r)) {
				if (!getNode(nodeKey).visited) {
					visitNode(getNode(nodeKey));
					// visited.add(nodeKey);
					getNode(nodeKey).visited = true;
					queue.enqueue(getNode(nodeKey));
				}
			}
		}
	}

	// Set<String> visited = new HashSet<>();
	public void DFS(NodeG node) {
		visitNode(node);
		node.visited = true;

		for (String nodeKey : getNeigbours(node)) {
			if (!getNode(nodeKey).visited) {
				DFS(getNode(nodeKey));
			}
		}
	}

	// Given a directed graph, design an algorithm to find out whether
	// there is a route between two nodes.
	public boolean isDirectionBtw(NodeG node1, NodeG node2) {

		// Go DFS from Node1 and try to find node2
		node1.visited = true;
		visitNode(node1, node2);
		if (node1.key.equals(node2.key)){
			System.out.println("equal node is found");
		}

		for (String nodeKey : getNeigboursNode(node1)) {
			NodeG n = getNode(nodeKey);
			if (!n.visited) {
				isDirectionBtw(n, node2);
			}
		}

		return false;
	}
	

}
