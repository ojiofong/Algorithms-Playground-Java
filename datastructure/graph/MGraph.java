import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MGraph {
	Map<Object, ArrayList<Object>> map;
	Map<Object, NodeG> adjacencyList;
	int size;

	public MGraph() {
		map = new HashMap<Object, ArrayList<Object>>();
		adjacencyList = new HashMap<Object, NodeG>();
		size = 0;
	}
	
	public void addEdge(NodeG startNode, NodeG destNode) {
//		if(map.containsKey(startNode.getKey()))
//			return;

		Object startNodeKey = startNode.getKey();
		Object destNodeKey = destNode.getKey();
		
		adjacencyList.put(startNodeKey, startNode);
		adjacencyList.put(destNodeKey, destNode);
		addEdgeKey(startNodeKey, destNodeKey);
		
		
	}

	public void addEdgeKey(Object startKey, Object destKey) {
		ArrayList<Object> list = map.get(startKey);
		if(list==null){
			map.put(startKey, list = new ArrayList<Object>());
		}
		list.add(destKey);
	}
	
	public Iterable<Object> getNeighbours(Object key){
		ArrayList<Object> list = map.get(key);
		if(list == null){
			return Collections.emptyList();
		}
		
		return Collections.unmodifiableList(list);
	}
	
	public ArrayList<Object> getNeighboursList(Object key){
		
		ArrayList<Object> list = map.get(key);
		if(list == null){
			return new ArrayList<>();
		}
		
		return list;
	}
	
	public NodeG getNode(Object key){
		return adjacencyList.get(key);
	}

}
