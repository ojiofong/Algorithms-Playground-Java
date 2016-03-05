import java.util.Hashtable;

public class StackSort {

	public static void main(String[] args) {
		System.out.println(StackSort.class.getSimpleName());

		MStack stack1 = new MStack();
		stack1.push(2);
		stack1.push(5);
		stack1.push(3);
		stack1.push(1);
		stack1.push(4);

		System.out.println(stack1.toString());

		// while (!stack1.isEmpty()) {
		// System.out.print(stack1.pop()+", ");
		// }
		
		//Hashtable<K, V>

		System.out.println("sorted " + sortStack(stack1).toString());

	}

	/**
	 * O(n^2) time and O(n) space
	 * 
	 */
	private static MStack sortStack(MStack stack1) {

		MStack stack2 = new MStack();

		while (!stack1.isEmpty()) {
			Object temp = stack1.pop();
			while (!stack2.isEmpty() && (Integer) stack2.peek() > (Integer) temp) {
				stack1.push(stack2.pop());
			}
			stack2.push(temp);
		}

		return stack2;
	}

}
