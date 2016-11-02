package datastructure;

import java.util.Arrays;

public class HashMap_M<K, V> {

	public static void main(String[] args) {
		
		System.out.println(HashMap_M.class.getSimpleName());
		
		HashMap_M<String, String> map = new HashMap_M<>();
		map.put("1", "one");
		map.put("2", "two");
		map.put("3", "three");
		
		System.out.println("" + map.get("1") + " " + map.containsKey("1"));
		System.out.println("" + map.get("2") + " " + map.containsKey("2"));
		System.out.println("" + map.get("3") + " " + map.containsKey("3"));
		System.out.println(map.toString());

	}

	Entry<K, V>[] table;
	int size;
	int capacity = 13;
	double loadFactor = 0.85;

	public static class Entry<K, V> {
		K key;
		V value;
		Entry<K, V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}
	}

	@SuppressWarnings("unchecked")
	public HashMap_M() {
		table = new Entry[capacity];
		size = 0;
	}

	public void put(K key, V value) {
		if (key == null)
			return;
		checkSize();
		Entry<K, V> newEntry = new Entry<>(key, value);
		int hash = hash(key);
		if (table[hash] == null) {
			table[hash] = newEntry;
		} else {
			newEntry.next = table[hash];
			table[hash] = newEntry;
		}

		size++;
	}

	public V remove(K key) {
		if (key == null)
			return null;
		int hash = hash(key);
		Entry<K, V> entry = table[hash];
		if (entry == null)
			return null;

		V value = null;

		Entry<K, V> cur = entry;
		Entry<K, V> prev = null;

		while (cur != null) {
			if (cur.key.equals(key)) {
				value = cur.value;
				if (prev == null) {
					table[hash] = null;
				} else {
					prev.next = prev.next.next;
				}
			}
			prev = cur;
			cur = cur.next;
		}

		size--;

		return value;
	}

	public V get(K key) {
		if (key == null)
			return null;
		int hash = hash(key);
		Entry<K, V> entry = table[hash];
		if (entry == null)
			return null;
		return entry.value;
	}

	public boolean containsKey(K key) {
		return (key != null && table[hash(key)] != null);
	}

	private void checkSize() {
		if (size >= loadFactor * capacity) {
			capacity += 13;
			table = Arrays.copyOf(table, capacity);
		}
	}

	public int size() {
		return size;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<capacity; i++){
			Entry<K,V> entry = table[i];
			Entry<K,V> cur = entry;
			while(cur!=null){
				sb.append(String.format("[%s=%s]", cur.key, cur.value));
				cur= cur.next;
			}
		}
		
		return sb.toString();
	}

	private int hash(K key) {
		return Math.abs(key.hashCode()) % capacity;
	}

}
