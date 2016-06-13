package mathematics;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			System.out.println(squareRoot(10));
	}
	
	
	public static double squareRoot(int n){
		if(n==0) return 0;
		if(n<0) return 0;
		
		double sqrt = n/2;
		double t = 0;
		do{
			t = sqrt;
			sqrt = (t + n/t)/2.0;
		}while(t-sqrt != 0);
		
		return sqrt;
	}

}
