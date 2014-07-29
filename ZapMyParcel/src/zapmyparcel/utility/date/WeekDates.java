package zapmyparcel.utility.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class WeekDates {
	
	public static int getMaxDay_Month(){
		Calendar c = Calendar.getInstance();
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		c.set(year, month,1);
		int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		return maxDay;
	}
	public static int getMaxDayLast_Month(){
		Calendar c = Calendar.getInstance();
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		c.set(year, month-1,1);
		int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		//System.out.println("get last monthday::"+maxDay);
		return maxDay;
	}
	
public static int getWeekDayFromDate(String date){
	int weekDay=0;
	try {
	Calendar c = Calendar.getInstance();
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Date  d;
		d = format.parse(date);
		c.setTime(d);
		//System.out.println(c.get(Calendar.DAY_OF_WEEK));
		weekDay=c.get(Calendar.DAY_OF_WEEK);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	return  weekDay;
}
	
public static List getAllWeekDateList(){
		
		Calendar c = Calendar.getInstance();
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String date = f.format(c.getTime());
		c.set(Calendar.DAY_OF_WEEK,
		c.getActualMinimum(Calendar.DAY_OF_WEEK));
		String firstday=f.format(c.getTime());
		//System.out.println(firstday);
		c.set(Calendar.DAY_OF_WEEK, c.getActualMaximum(Calendar.DAY_OF_WEEK));
		String lastday =f.format(c.getTime()); 
	//	System.out.println(lastday);
		List dateList=new ArrayList();
		dateList.add(firstday);
		int maxDay=getMaxDay_Month();
		//System.out.println("MaxDay:: "+maxDay);
		//System.out.println("getMaxDayLast_Month():: "+getMaxDayLast_Month());
		 int first=Integer.parseInt(firstday.substring(firstday.lastIndexOf("-")+1));
		// System.out.println("first:: "+first); 
		
		 int last=Integer.parseInt(lastday.substring(lastday.lastIndexOf("-")+1));
		// System.out.println("last:: "+last);
		 
		 int firstmonth=Integer.parseInt(firstday.substring(firstday.indexOf("-")+1,firstday.lastIndexOf("-")));
		 int lastmonth=Integer.parseInt(lastday.substring(lastday.indexOf("-")+1,lastday.lastIndexOf("-")));
		 
		 String remainingString=firstday.substring(0,firstday.lastIndexOf("-")+1);
		 //System.out.println("remainingString:: "+remainingString);
		 List weekDateList=new ArrayList();
		 if(firstmonth == lastmonth){
			 int monthday=first+1;
			 for(int i=1;i<6;i++){
				 String inbetweenDate=remainingString+String.valueOf(monthday);
			//	 System.out.println("Date is :"+inbetweenDate);
				 weekDateList.add(inbetweenDate);
				 monthday=monthday+1;
			 }
		 }else{
			// System.out.println("else part");
			 int lastmonthday=getMaxDayLast_Month();
			 if(lastmonthday >maxDay){
				// System.out.println("first if "+lastmonthday +" maxday :: "+maxDay);
				 if(maxDay == 28 ){
					//System.out.println("in 28 days ::"+first);
					 int localfirst=first;
					 for(int i=first;i<maxDay;i++){
						// System.out.println("in 29 days  maxday:: ::"+maxDay);
						 localfirst=localfirst+1;
						 String inbetweenDate=remainingString+String.valueOf(localfirst);
						 weekDateList.add(inbetweenDate);
						// System.out.println("Date:: "+inbetweenDate);
					 }
				 }else{
				 if(maxDay == 29){
					// System.out.println("in 28 days ::"+first);
					 int localfirst=first;
					 for(int i=first;i<maxDay;i++){
					//	 System.out.println("in 29 days  maxday:: ::"+maxDay);
						 localfirst=localfirst+1;
						 String inbetweenDate=remainingString+String.valueOf(localfirst);
						 weekDateList.add(inbetweenDate);
				//		 System.out.println("Date:: "+inbetweenDate);
					 }
				 }else{
					 int localfirst=first;
					 for(int i=first;i<maxDay;i++){
						 localfirst=localfirst+1;
						 String inbetweenDate=remainingString+String.valueOf(localfirst);
						 weekDateList.add(inbetweenDate);
					//	 System.out.println("Date:: "+inbetweenDate);
					 }
				 }
				 }
			 }else{
				 if(lastmonthday<maxDay){
					//System.out.println("second if "+lastmonthday +" maxday :: "+maxDay);
					 if(lastmonthday == 28){
						// System.out.println("in 28 days ::"+first);
						 int localfirst=first;
						 for(int i=first;i<lastmonthday;i++){
						//	 System.out.println("in 29 days  maxday:: ::"+maxDay);
							 localfirst=localfirst+1;
							 String inbetweenDate=remainingString+String.valueOf(localfirst);
							 weekDateList.add(inbetweenDate);
							// System.out.println("Date:: "+inbetweenDate);
						 }
					 }else{
						 if(lastmonthday == 29){
							//	System.out.println("in 28 days ::"+first);
								 int localfirst=first;
								 for(int i=first;i<lastmonthday;i++){
								//	System.out.println("in 29 days  maxday:: ::"+maxDay);
									 localfirst=localfirst+1;
									 String inbetweenDate=remainingString+String.valueOf(localfirst);
									 weekDateList.add(inbetweenDate);
								//	 System.out.println("Date:: "+inbetweenDate);
								 }
							 }					 
						 else{
						 for(int i=first;i<=maxDay-1;i++){
							 String inbetweenDate=String.valueOf(first+1);
							 first=first+1;
							// System.out.println("Date:: "+remainingString+inbetweenDate);
							 weekDateList.add(remainingString+inbetweenDate);
					   }
					 }
				 }
				 }else{
					 //System.out.println("third if "+lastmonthday +" maxday :: "+maxDay);
					 int localfirst=first;
					 for(int i=first;i<=maxDay-1;i++){
						 localfirst=localfirst+1;
						 String inbetweenDate=String.valueOf(localfirst);
						//System.out.println("Date:: "+remainingString+inbetweenDate);
						 weekDateList.add(remainingString+inbetweenDate);
					 }
				 }
			 }
			 int startdate=1;
			 remainingString=lastday.substring(0,lastday.lastIndexOf("-")+1);
			 for(int i=1;i<=last-1;i++){
				 String inbetweenDate=String.valueOf(startdate);
				 startdate=startdate+1;
				// System.out.println("Date :::::"+remainingString+inbetweenDate);
				 weekDateList.add(remainingString+inbetweenDate);
			 }
		 }
		 return weekDateList;
	}
		public static void main(String args[]){
			System.out.println(WeekDates.getAllWeekDateList());
			
			
		}
}
