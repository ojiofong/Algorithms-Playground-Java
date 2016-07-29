import java.util.Iterator;
import java.util.LinkedList;

public class ProblemsLinkedList {

	public static void main(String[] args) {
		System.out.println(ProblemsLinkedList.class.getSimpleName());
		println("testing");
		print("reverseLinkedList: " + reverseLinkedList());

	}

	public static void println(String s) {
		System.out.println(s);
	}

	public static void print(String s) {
		System.out.print(s);
	}

	// Reverse LinkedList
	public static String reverseLinkedList() {
		
		return null;
	}

	// Reverse LinkedList
	public static String reverseLinkedList2() {

		LinkedList<String> list = new LinkedList<>();
		list.addLast("A");
		list.addLast("B");
		list.addLast("C");

		LinkedList<String> list2 = new LinkedList<>();

		Iterator<String> mIterator = list.iterator();

		while (mIterator.hasNext()) {
			String next = mIterator.next();
			print(next + " | ");
			list2.addFirst(next);
		}

		return list.toString() + "->" + list2.toString();
	}

}
