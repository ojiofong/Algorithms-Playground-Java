package mathematics;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(squareRoot(10));
		System.out.println(power(3, 2));
		
		
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
	
	public static double power(double base, double exponent){
		double ans = 1;
		if(exponent == 0) return ans;
		if(base == 0) return 0;
		
		for(int i=0; i< Math.abs(exponent); i++){
			ans *= base;
		}
		
		if(exponent < 0){
			ans = 1.0/ans; //invert
		}
		
		return ans;
	}

}
