package zapmyparcel.utility.date;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sun.security.action.GetLongAction;

public class DateTimeFormater {
	
	
	public static String format_datetime(){
		Calendar c = Calendar.getInstance();
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = f.format(c.getTime());
		System.out.println(date);
		return date;
	}
	
	public static String getCurrentDate(){
		Calendar c = Calendar.getInstance();
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String date = f.format(c.getTime());
		System.out.println(date);
		return date;
	}
	
	public static String getCurrentTime(){
		 Calendar currentDate = Calendar.getInstance();
		  SimpleDateFormat formatter= 
		  new SimpleDateFormat("HH:mm:ss");
		  String time = formatter.format(currentDate.getTime());
		  System.out.println("Now the time is :=>  " + time);
		  return time;
	}
	
	
	public static String display_datetime(){
		Calendar c = Calendar.getInstance();
		DateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String date = f.format(c.getTime());
		System.out.println(date);
		return date;
	}
	
	public static String ftp_onlydate(){ 
		Calendar c = Calendar.getInstance();
		DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		String date = f.format(c.getTime());
		System.out.println(date);
		return date;
	}
	
	public static String onlydate(){ // method for date in format of dd-MMM-yyyy eg. 10-OCT-2011
		Calendar c = Calendar.getInstance();
		DateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
		String date = f.format(c.getTime());
		System.out.println(date);
		return date;
	}
	public static String format_date(){
		Calendar c = Calendar.getInstance();
		DateFormat f = new SimpleDateFormat("ddMMyyyy HHmm");
		String date = f.format(c.getTime());
		System.out.println(date);
		return date;
	}
	
	public static String ftp_format_date(){
		Calendar c = Calendar.getInstance();
		DateFormat f = new SimpleDateFormat("dd-MM-yyyy HHmmss");
		String date = f.format(c.getTime());
		System.out.println(date);
		return date;
	}
	public static String date_formate(String dateParam){
		String str="";
		try {  
	    //  String str_date="11-June-2007";
	     String str_date=dateParam;
		 DateFormat formatter ; 
		 Date date ; 
		  formatter = new SimpleDateFormat("dd-MMM-yy");
		  date = (Date)formatter.parse(str_date);  
		 
		  String strDate=String.valueOf(date);
		  String[]splitDates=strDate.split(" ");
		  str=splitDates[1]+"-"+splitDates[2]+"-"+splitDates[splitDates.length-1];
		  System.out.println("converted date is " +str);
		} catch (ParseException e)
		  {
			System.out.println("Exception :"+e); e.printStackTrace(); }  
		 
		return str;
	}
	
	public static String validateFileDate(String date, String date_format){
		try {  
		    //  String str_date="11-June-2007";
			DateFormat formatter ; 
			Date d;
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			if(date_format.equals("mm-dd-yyyy")){
				String temp[] = date.split("-");
				String str = temp[2]+"-"+temp[0]+"-"+temp[1];
				System.out.println(str);
				return str;
			}else if(date_format.equals("yyyy-mm-dd")){
				return date;
			}else if(date_format.equals("dd-mm-yyyy")){
				String temp[] = date.split("-");
				String str = temp[2]+"-"+temp[1]+"-"+temp[0];
				System.out.println(str);
				return str;
			}else if(date_format.equals("dd/mm/yyyy")){
				String temp[] = date.split("/");
				String str = temp[2]+"-"+temp[1]+"-"+temp[0];
				
				System.out.println(str);
				return str;
			}else if(date_format.equals("yyyy/mm/dd")){
				date = date.replace('/', '-');
				return date;
			}else if(date_format.equals("mm/dd/yyyy")){
				String temp[] = date.split("/");
				String str = temp[2]+"-"+temp[0]+"-"+temp[1];
				return str;
			}else{
				return "error";
			}
			 
		}catch (Exception e){
			System.out.println("Exception :"+e); e.printStackTrace();
			return "error";
		} 
	}
	
	public static int getAM_PM(){
		Calendar cal = new GregorianCalendar();
		int ampm = cal.get(Calendar.AM_PM);             // 0=AM, 1=PM
		return ampm;
	}
	//get current day of week
	public static String getDay_Week(){
		// Create a calendar with year and day of year.
        Calendar calendar = Calendar.getInstance();
        // See the full information of the calendar object.
        System.out.println(calendar.getTime().toString());
        // Get the weekday and print it
        int weekday = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("Weekday: " + weekday);
		  String weekname="";
		  switch(weekday){
		  case 1:
			  weekname= "Sunday";
			  break;
		  case 2:
			  weekname= "Monday";
			  break;
		  case 3:
			  weekname= "Tuesday";
			  break;
		  case 4:
			  weekname= "Wednesday";
			  break;
		  case 5:
			  weekname= "Thrusday";
			  break;
		  case 6:
			  weekname= "Friday";
			  break;
		  case 7:
			  weekname= "Saturday";
			  break;
		  }
	//	  System.out.println("weekname ::"+weekname);
		  return weekname;
	}
	
	public static void test(){
		 Calendar now = Calendar.getInstance();
		    now.set(2011,8, 25);
		    System.out.println("Current date : " + (now.get(Calendar.MONTH)) + "-"
		        + now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR));

		    String[] strDays = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday",
		        "Friday", "Saturday" };
		    // Day_OF_WEEK starts from 1 while array index starts from 0
		    System.out.println("Current day is : " + strDays[now.get(Calendar.DAY_OF_WEEK) - 1]);
		 
	}
	
	public static String getCurrentMonthYear(){
		Calendar cal = Calendar.getInstance();
		int cmonth = cal.get(Calendar.MONTH);
		
		int cyear = cal.get(Calendar.YEAR);
		String currMonth= "";
		switch (cmonth){
			case 0:
				currMonth = "January";
				break;
			case 1:
				currMonth = "February";
				break;
			case 2:
				currMonth = "March";
				break;
			case 3:
				currMonth = "Arpil";
				break;
			case 4:
				currMonth = "May";
				break;
			case 5:
				currMonth = "June";
				break;
			case 6:
				currMonth = "July";
				break;
			case 7:
				currMonth = "August";
				break;
			case 8:
				currMonth = "September";
				break;
			case 9:
				currMonth = "October";
				break;
			case 10:
				currMonth = "November";
				break;
			case 11:
				currMonth = "December";
				break;
		}
		return currMonth+"-"+cyear;
	}
	
	public static int getCurrentDay(){
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		System.out.println(day);
		return day;
	}
	public static String getCurrentMonth(){
		Calendar cal = Calendar.getInstance();
		int cmonth = cal.get(Calendar.MONTH);
		
		return String.valueOf(cmonth);
	}
	public static String getCurrentYear(){
		Calendar cal = Calendar.getInstance();
		int cyear = cal.get(Calendar.YEAR);
		
		return String.valueOf(cyear);
	}
	
	public static List<String> getMonthList(){
		
		List<String> monthList = new ArrayList<String>();
			
		monthList.add("January");
		monthList.add("February");
		monthList.add("March");
		monthList.add("Arpil");
		monthList.add("May");
		monthList.add("June");
		monthList.add("July");
		monthList.add("August");
		monthList.add("September");
		monthList.add("October");
		monthList.add("November");
		monthList.add("December");
		
		return monthList;
	}
	
	public static List<String> getYearList(){
		
		List<String> yearList = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		int cyear = cal.get(Calendar.YEAR);
		
		for(int i=cyear-5;i<(cyear+5);i++){
			yearList.add(String.valueOf(i));
		}
		return yearList;
	}

	public static int getMonthNo(String month){
		
	   if(month.equalsIgnoreCase("January")){
		   return 1;
	   }else if(month.equalsIgnoreCase("February")){
		   return 2;
	   }else if(month.equalsIgnoreCase("March")){
		   return 3;
	   }else if(month.equalsIgnoreCase("April")){
		   return 4;
	   }else if(month.equalsIgnoreCase("May")){
		   return 5;
	   }else if(month.equalsIgnoreCase("June")){
		   return 6;
	   }else if(month.equalsIgnoreCase("July")){
		   return 7;
	   }else if(month.equalsIgnoreCase("August")){
		   return 8;
	   }else if(month.equalsIgnoreCase("September")){
		   return 9;
	   }else if(month.equalsIgnoreCase("October")){
		   return 10;
	   }else if(month.equalsIgnoreCase("November")){
		   return 11;
	   }else if(month.equalsIgnoreCase("December")){
		   return 12;
	   }else {
		   return 0;
	   }
	   	  
	}
	
public static String dateIncrementByOne(String date){
	String dt = date;  // Start date
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Calendar c = Calendar.getInstance();
	try {
		c.setTime(sdf.parse(dt));
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	c.add(Calendar.DATE, 1);  // number of days to add
	String str= sdf.format(c.getTime());  // dt is now the new date
	return str;
}
	

public static String getMatchingDate(String dateString){
//	String monthname="05/15/25";
	String remaining=dateString.substring(dateString.indexOf("/"));
	remaining=remaining.replace("/","-");
	System.out.println(remaining);
	dateString =dateString.substring(0,dateString.indexOf("/"));
	
	String monthNo[]={"01","02","03","04","05","06","07","08","09","10","11","12"};
	String monthnames[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	for(int i=0;i<monthNo.length;i++){
	  if(monthNo[i].equalsIgnoreCase(dateString)){
		  return monthnames[i]+remaining;
	  }
	}
	return "0";
	
}

public static String convertDateFormat(String date) throws Exception{
	System.out.println("Date "+date);
	String dates[] = date.split("-");
	System.out.println(dates[0]);
	System.out.println(dates[1]);
	System.out.println(dates[2]);
	String convertdate = dates[2]+"-"+dates[1]+"-"+dates[0];
	System.out.println(convertdate);
	return convertdate;
}

public static String getOrderPlanningDate(Date date){
	/*Date date ="Mon Mar 05 10:38:34 IST 2012";*/
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM");
	String formattedDate = formatter.format(date);
	System.out.println(formattedDate);
	return formattedDate;
}

public static String getOrderPlanningDateFormat(Date date){
	if(date != null){
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = f.format(date.getTime());
		System.out.println(date);
		return dateStr;
	}else{
		return "";
	}
}
public static String getOrderPlanningDateFormat(Date date,String format){
	if(date != null){
		DateFormat f = new SimpleDateFormat(format);
		String dateStr = f.format(date.getTime());
		System.out.println(date);
		return dateStr;
	}else{
		return "";
	}
}

public static String getTodayDate(){
	Calendar c = Calendar.getInstance();
	DateFormat f = new SimpleDateFormat("ddMMyyyy");
	String date = f.format(c.getTime());
	return date;
}
public static void main(String[] args) {
	try{
			Calendar c=Calendar.getInstance();
			
			System.out.println(getOrderPlanningDateFormat(null,"dd MMM yy"));
	}catch(Exception e){
		e.printStackTrace();
	}
	
}
}
