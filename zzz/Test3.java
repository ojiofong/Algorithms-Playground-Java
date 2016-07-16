package zzz;

public class Test3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 allCaseCombo("", "abc");
		// allStringSubs("abc");
		//permutationOfString("", "abc");
		//System.out.println("\nfactorial " + factorial(3));
		//fibonnaci(0, 1, 5);
		//spiralPrint(get2dArray());
		 tester();
	}
	
	private static int[][] get2dArray() {

		System.out.println("...Fetching Input Array........");
		int arr[][] = new int[3][6];
		int rows = arr.length;
		int cols = arr[0].length;
		int data = 1;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				arr[r][c] = data++;
				System.out.print(arr[r][c] + " ");
			}
			System.out.println("");
		}

		System.out.println("...Done Fetching Array...");

		return arr;
	}

	private static void spiralPrint(int[][] arr) {
		int r = 0;
		int c = 0;
		int rows = arr.length;
		int cols = arr[0].length;
		
//		while(r >= 0 && c >= 0){
//			//right
//			for(int i=0; i<cols; i++){
//				System.out.print(arr[][] + "");
//			}
//		}
	}

	private static void permutationOfString(String pref, String word) {
		int N = word.length();
		if(N == 0){
			System.out.print(pref + " ");
		}
		
		for(int i=0; i<N; i++){
			permutationOfString(pref + word.charAt(i), word.substring(0,i) + word.substring(i+1, N));
		}

	}

	private static int fibonnaci(int a, int b, int limit) {
		
		if(limit == 1) return b;
	
		if(a == 0 && b ==1){
			System.out.print(a + " " + b + " ");
		}else{
			System.out.print(b + " ");
		}
		
		
		return fibonnaci(b, b+a, --limit);
	}

	private static int factorial(int n) {
		if(n == 0 || n==1) return 1;
		return n * factorial(n-1);
	}

	private static void allStringSubs(String str) {
		int N = str.length();
		for(int i=0; i<N; i++){
			for(int k=1; k<=N-i; k++){
				String sub = str.substring(i, k+i);
				System.out.print(sub + " ");
			}
		}
	}

	private static void allCaseCombo(String pref, String word) {
		int N = word.length();
		if(N==0){
			System.out.print(pref + " ");
			return;
		}
		
		String first = word.substring(0,1);
		String last = word.substring(1);

		allCaseCombo(pref + first.toLowerCase(), last);
		allCaseCombo(pref + first.toUpperCase(), last);
		
	}
	
	private static void tester(){
	}

}
