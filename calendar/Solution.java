package calendar;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {

	public static void main(String[] args) {
		String S = "15:15:00";
		String T = "15:15:12";
		int ans = noOfSpecialTimesBetweenTwoTimes(S, T);
		System.out.print("special count-> " + ans);
	}

	/**
	 * Here special time is defined by times with at most 2 different digits
	 * For e.g. 22:22:21
	 * */
	public static int noOfSpecialTimesBetweenTwoTimes(String S, String T) {
		if(S==null || T==null)return 0;
		
		int count = 0;
		
		SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
		Date d1 = null;
		Date d2 = null;
		  try {
				d1 = df.parse(S);
				d2 = df.parse(T);
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}

			Calendar calS = Calendar.getInstance();
			Calendar calT = Calendar.getInstance();
			calS.setTime(d1);
			calT.setTime(d2);

			DecimalFormat f = new DecimalFormat("00");
			
			while(true){
				String str = f.format(calS.get(Calendar.HOUR_OF_DAY)) + f.format(calS.get(Calendar.MINUTE)) + f.format(calS.get(Calendar.SECOND));
				
				System.out.println(str);
				
				Set<Character> set = new HashSet<>();
				for(char c : str.toCharArray()){
					set.add(c);
				}
				
				if(set.size() <= 2) count++;
				
				if((calS.getTimeInMillis() == calT.getTimeInMillis())) break;

				calS.add(Calendar.SECOND, 1);
			}

		return count;

	}

}
