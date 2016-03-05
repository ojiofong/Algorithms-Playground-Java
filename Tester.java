import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Tester {
	static MGraph graph;

	public static void main(String[] args) throws Exception {
		// System.out.println("good");
		MHashTable ht = new MHashTable();
		ht.put("one", "one");
		ht.put("two", "two");
		ht.put("three", "three");
		ht.put("key4", "four");
		ht.put("key5", "five");
		ht.put("key6", "six");
		ht.put("key7", "seven");
		ht.put("key8", "eight");
		ht.put("key9", "nine");

		// System.out.println("size " + ht.size());
		// System.out.println("value1 " + ht.get("one"));
		// System.out.println("value2 " + ht.get("key2"));
		// System.out.println("value3 " + ht.get("three"));
		// System.out.println("capacity " + ht.capacity());
		// System.out.println("remove " + ht.remove("one"));
		// System.out.println("size " + ht.size());
		// System.out.println("value1 " + ht.get("one"));
		// System.out.println("value2 " + ht.get("two"));
		// System.out.println("toString " + stack.toString());

		int[] arr = { 3, 5, 7, 9, 2, 1, 0, 4, 8 };
		// System.out.println("binarySearch found " + binarySearch(11, arr));

		graph = new MGraph();
		// mm.add("A", "A1");
		// mm.add("A", "A2");
		// mm.add("A2", "A");

		NodeG nodeA = new NodeG("A", "DataA");
		NodeG nodeB = new NodeG("B", "DataB");
		NodeG nodeC = new NodeG("C", "DataC");
		NodeG nodeD = new NodeG("D", "DataD");
		NodeG nodeE = new NodeG("E", "DataE");
		NodeG nodeF = new NodeG("F", "DataF");
		NodeG nodeG = new NodeG("G", "DataG");

		graph.addEdge(nodeE, nodeF);
		graph.addEdge(nodeA, nodeB);
		graph.addEdge(nodeA, nodeC);
		graph.addEdge(nodeC, nodeD);
		graph.addEdge(nodeC, nodeE);
		graph.addEdge(nodeA, nodeG);

		// Iterable<Object> iterableList = graph.getNeighbours("A");
		// ArrayList<Object> arrayList = graph.getNeighboursList("A");
		// System.out.println("MMultimap iterable " + iterableList);
		// System.out.println("MMultimap arrayList" + arrayList);
		// System.out.println("MMultimap arrayList size " + arrayList.size());
		//
		// for (int i = 0; i < arrayList.size(); i++) {
		// Object objKey = arrayList.get(i);
		// NodeB node = graph.getNode(objKey);
		// System.out.println("node data " + node.data);
		//
		// }

		breathFirstSearch(nodeA);
		// printVisited(nodeA);

	}

	private static int binarySearch(int key, int[] arr) {
		Arrays.sort(arr);

		int lo = 0;
		int hi = arr.length - 1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (key < arr[mid])
				hi = mid - 1;
			else if (key > arr[mid])
				lo = mid + 1;
			else
				return key;
		}
		return -1;
	}

	private static void printVisited(Object key) {
		System.out.print("[visited " + key + "]");
	}

	private static void breathFirstSearch(NodeG focus) {
		MQueue queue = new MQueue();
		Set<Object> visited = new HashSet<>();

		if (focus == null)
			return;

		queue.enqueue(focus.key);
		visited.add(focus.key);
		printVisited(focus.key);

		while (!queue.isEmpty()) {
			Object r = queue.dequeue();
			for (Object key : graph.getNeighboursList(r)) {
				if (!visited.contains(key)) {
					queue.enqueue(key);
					visited.add(key);
					printVisited(key);
				}
			}
		}
	}

	private static void preOrder(NodeG focus) {
//		if (focus != null) {
//			printVisited(focus.key);
//			preOrder(focus);
//			preOrder(focus);
//		}
	}
}
