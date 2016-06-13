package strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantLock;

public class Solution {
	public static void main(String[] args) {
		System.out.println(Solution.class.getSimpleName());
		Solution solu = new Solution();
		solu.substringsOfAString("abc");
		varTest();
		
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

	static ReentrantLock mObject = new ReentrantLock();
	static Object mObject2 = new Object();
	public static void varTest(String... strs){
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		Iterator<Integer> mIterator = null;
		
		synchronized(mObject2){
			mIterator = list.iterator();
			while(mIterator.hasNext()){
				System.out.println("iterator "+ mIterator.next());
			}
		}
		
		
		System.out.println(""+strs.length);
		mObject.lock();
		mObject.unlock();
	}
}
