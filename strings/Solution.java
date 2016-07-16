package strings;

public class Solution {
	public static void main(String[] args) {
		System.out.println(Solution.class.getSimpleName());
		Solution solu = new Solution();
		solu.allSubstringsOfAString("abc");
		longestDuplicateSub("ababcaabcabcaab");
		allCaseComboOfString("", "abc");
		permutation("", "abc");
	}
	
	public void allSubstringsOfAString(String s){
		//System.out.println(s.substring(0, 2));
		for(int i=0; i<s.length(); i++){ // normal iteration 0 - N
			for(int k=1; k<=s.length()-i; k++){ // sub iteration 1 - N-i
				String sub = s.substring(i, i+k);
				System.out.println(sub + " " + i + " " + (i+k));
			}
		}
	}
	
	public static void longestDuplicateSub(String str){
		// Get all substrings of str
		// check duplicate on the fly
		// hold the longest if true 
		
		String longest = "";
		
		for(int i=0; i<str.length(); i++){
			for(int k=1; k<=str.length()-i; k++){
				String sub = str.substring(i,k+i);
				boolean isSubDuplicate = str.replaceFirst(sub, "").contains(sub);
				if(isSubDuplicate){
					longest = sub.length() > longest.length() ? sub : longest;
				}
			}
		}
		
		System.out.println("\nlongestDuplicateSub-> " + longest);
	}
	
	private static void allCaseComboOfString(String prefix, String word){
		if(word.length() == 0){
			System.out.print(prefix + " ");
			return;
		}
		
		String first = word.substring(0,1);
		String last = word.substring(1);

		allCaseComboOfString(prefix + first.toLowerCase(), last);
		allCaseComboOfString(prefix + first.toUpperCase(), last);
		
	}
	
	private static void permutation(String prefix, String str) {
		int n = str.length();
		if (n == 0)
			System.out.print(prefix + ", ");
		else {
			for (int i = 0; i < n; i++)
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
		}
	}
	
	
	

//	static ReentrantLock mObject = new ReentrantLock();
//	static Object mObject2 = new Object();
//	public static void varTest(String... strs){
//		List<Integer> list = new ArrayList<>();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		Iterator<Integer> mIterator = null;
//		
//		synchronized(mObject2){
//			mIterator = list.iterator();
//			while(mIterator.hasNext()){
//				System.out.println("iterator "+ mIterator.next());
//			}
//		}
//		
//		
//		System.out.println(""+strs.length);
//		mObject.lock();
//		mObject.unlock();
//	}
}
