package strings;

public class Solution {
	public static void main(String[] args) {
		System.out.println(Solution.class.getSimpleName());
		Solution solu = new Solution();
		solu.substringsOfAString("abc");
		
	}
	
	public void substringsOfAString(String s){
		//System.out.println(s.substring(0, 2));
		for(int i=0; i<s.length(); i++){ // normal iteration 0 - N
			for(int k=1; k<=s.length()-i; k++){ // sub iteration 0 - N+1
				String sub = s.substring(i, i+k);
				System.out.println(sub + " " + i + " " + (i+k));
			}
		}
	}
}
