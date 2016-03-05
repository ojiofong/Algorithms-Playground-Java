import java.util.Arrays;

public class MHashTable {

	private HashEntry[] table;
	private int tableCapacity = 10;
	private double loadFactor = 0.75;
	private int size;

	public MHashTable() {
		table = new HashEntry[tableCapacity];
		size = 0;
	}

	public void put(String key, Object value) throws Exception {
		checkKey(key);
		checkValue(value);
		checkSize();
		int hash = calculateHash(key);
		boolean notDuplicate = table[hash] == null;
		table[hash] = new HashEntry(key, value);

		if (notDuplicate)
			size++;
	}

	public Object get(String key) throws Exception {
		checkKey(key);
		int hash = calculateHash(key);
		HashEntry entry = table[hash];
		if (entry != null) {
			return entry.getValue();
		}
		return null;
	}

	public Object remove(String key) throws Exception {
		checkKey(key);
		int hash = calculateHash(key);
		Object item = table[hash].getValue();
		table[hash] = new HashEntry(null, null);
		size--;
		return item;
	}

	public int size() {
		return this.size;
	}

	public int capacity() {
		return table.length;
	}

	private void checkSize() {
		if (size > (loadFactor * tableCapacity)) {
			int newCapacity = tableCapacity * 2;
			table = Arrays.copyOf(table, newCapacity);
			tableCapacity = newCapacity;
		}
	}

	private void checkKey(String key) throws Exception {
		if (key == null) {
			throw new Exception("Key cannot be null");
		} else if (key.replace(" ", "").isEmpty()) {
			throw new Exception("Key cannot be empty");
		}
	}

	private void checkValue(Object value) throws Exception {
		if (value == null) {
			throw new Exception("Value cannot be null");
		}
	}

	private int calculateHash(String key) {
//		int hashCode = (key.hashCode() & 0x7fffffff) % initialCapacity;
//		while (table[hashCode] != null && !table[hashCode].getKey().equals(key)) {
//			hashCode = (hashCode + 1) % initialCapacity;
//		}
		int hash = hash(key.hashCode());
		int i = indexFor(hash, tableCapacity);
		return i;
	}
	
	
	/**
	 * Applies a supplemental hash function to a given hashCode, which
	 * defends against poor quality hash functions.  This is critical
	 * because HashMap uses power-of-two length hash tables, that
	 * otherwise encounter collisions for hashCodes that do not differ
	 * in lower bits. Note: Null keys always map to hash 0, thus index 0.
	 */
	static int hash(int h) {
	    // This function ensures that hashCodes that differ only by
	    // constant multiples at each bit position have a bounded
	    // number of collisions (approximately 8 at default load factor).
	    h ^= (h >>> 20) ^ (h >>> 12);
	    return h ^ (h >>> 7) ^ (h >>> 4);
	}
	
	/**
	 * Returns index for hash code h.
	 */
	static int indexFor(int h, int length) {
	    return h & (length-1);
	}

}
