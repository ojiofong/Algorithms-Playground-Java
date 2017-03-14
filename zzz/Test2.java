package zzz;

public class Test2 {

	public static void main(String[] args) throws Exception {
		System.out.println(Test2.class.getSimpleName());

		xor();
	}

	static void xor() {
		int size = 100;
		int[] arr = new int[size];
		for (int i = 0; i < 100; i++) {
			// skip value
			if (i + 1 == 12)
				continue;
			arr[i] = i + 1;
		}
		
		int ans = 0;
		
		int i = 1;
		for (int value : arr){
			int x = i^value;
			if(x != 0){
				ans = i;
				System.out.println(String.format("missing-> %s", ans));
				return;
			}
			i++;
		}

		int a = 5;
		int b = 5;
		int xor = a ^ b;

		System.out.println("Nothing is missing");

	}

}
