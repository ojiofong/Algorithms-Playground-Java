package miscellaneous;

import java.util.HashMap;

public class CustomLRUCache {

	public static void main(String[] args) {
		System.out.println(CustomLRUCache.class.getSimpleName());

		CustomLRUCache cache = new CustomLRUCache(10);
		cache.insert("1", "1");
		cache.insert("2", "2");
		cache.insert("3", "3");
		cache.insert("4", "4");
//		cache.insert("0123456789", "0123456789");
		

		System.out.println(cache.toString());
		cache.get("2");
		System.out.println(cache.toString());

	}

	static class Node {
		String key;
		String value;
		Node next;
		Node behind;

		public Node(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public String toString() {
			String b = behind == null ? "null" : behind.value;
			String v = value == null ? "null" : value;
			String n = next == null ? "null" : next.value;
			return " [" + b + ":" + v + ":" + n + "] ";
		}
	}

	HashMap<String, Node> map;
	Node first, last;
	int curLimit;
	final int maxCharLimit;
	int size;

	public CustomLRUCache() {
		map = new HashMap<>();
		first = last = null;
		curLimit = 0;
		size = 0;
		maxCharLimit = 100;
	}

	public CustomLRUCache(int maxCharLimit) {
		map = new HashMap<>();
		first = last = null;
		curLimit = 0;
		size = 0;
		this.maxCharLimit = maxCharLimit;
	}

	private void checkKey(String key) {
		if (key == null)
			throw new IllegalArgumentException("Key cannot be null");
	}

	private void checkValue(String value) {
		if (value != null && value.length() > maxCharLimit)
			throw new IllegalArgumentException("chars in value are too large to insert");
	}

	public String get(String key) {
		checkKey(key);
		Node node = map.get(key);
		if (node == null)
			return null;
		moveToHead(node);
		return node.value;
	}

	private void unLinkNode(Node node) {
		if (node == null)
			return;
		if (node == first) {
			first = first.next;
		} else {
			node.behind.next = node.next;
			if (node.next != null) {
				node.next.behind = node.behind;
			}
		}
	}

	private void moveToHead(Node node) {
		unLinkNode(node);
		if(node == last){
			last = node.behind;
		}
		node.behind = null;
		node.next = first;
		first.behind = node;
		first = node;
	}

	public String remove(String key) {
		checkKey(key);
		Node removedNode = map.remove(key);
		if (removedNode == null)
			return null;
		unLinkNode(removedNode);
		curLimit = curLimit - removedNode.value.length();
		size = size - 1;
		return removedNode.value;
	}

	private void insert(String key, Node node) {
		while (curLimit + node.value.length() > maxCharLimit) {
			curLimit = curLimit - remove(last.key).length();
			last = last.behind;
		}
		
		map.put(key, node);
		if (first == null) {
			first = last = node;
		} else {
			// Add to head like a stack
			node.next = first;
			first.behind = node;
			first = node;
		}
		curLimit = curLimit + node.value.length();
		size = size + 1;
	}

	public void insert(String key, String value) {
		checkKey(key);
		checkValue(value);
		insert(key, new Node(key, value));
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node cur = first;
		while (cur != null) {
			sb.append(cur.toString());
			cur = cur.next;
		}
		return sb.toString();
	}

}
