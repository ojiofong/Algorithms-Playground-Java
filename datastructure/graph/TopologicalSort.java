package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;

public class Solution {

	public static void main(String[] args) {
		System.out.println(Solution.class.getSimpleName());

		topologicalSort(getTestGraph());
	}

	/*-
	 *
	  Topological sort: Requires Directed Acyclic Graph (DAG)
	  All input graph keys/nodes must have edges directed outward
	  See: https://www.geeksforgeeks.org/topological-sorting/
	  
	  In DFS we print the vertex and make recursive call to the adjacent vertices but 
	  here we will make the recursive call to the adjacent vertices and then push the vertex to stack.
	*/
	public static void topologicalSort(Map<Integer, int[]> graph) {
		Set<Integer> visited = new HashSet<>();
		Stack<Integer> stack = new Stack<>();

		for (Entry<Integer, int[]> entry : graph.entrySet()) {
			int node = entry.getKey();
			if (!visited.contains(node)) {
				topoSortUtil(node, visited, stack, graph);
			}
		}

		System.out.println("topologicalSort-> " + stack);
		// while !stack.isEmpty pop to print topologicalSort order in LIFO.
	}

	private static void topoSortUtil(int node, Set<Integer> visited, Stack<Integer> stack, Map<Integer, int[]> graph) {
		visited.add(node);
		int[] neighbours = graph.get(node);
		for (int n : neighbours) {
			if (!visited.contains(n)) {
				topoSortUtil(n, visited, stack, graph);
			}
		}
		stack.push(node);
	}

	private static Map<Integer, int[]> getTestGraph() {
		Map<Integer, int[]> graph = new HashMap<>();
		graph.put(5, new int[] { 2, 0 });
		graph.put(4, new int[] { 0, 1 });
		graph.put(2, new int[] { 3 });
		graph.put(3, new int[] { 1 });
		graph.put(1, new int[] {});
		graph.put(0, new int[] {});
		return graph;
	}

}
