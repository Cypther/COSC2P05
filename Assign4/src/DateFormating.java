/**
 * This is the DateFormating class, that takes care of the 
 * date/time formatting,
 * @author Long Nguyen
 * 
 * @version 1.0 (March 2016) Compiler Version Java 1.7
 */


import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class DateFormating {
	
	public DateFormating(){
		
	}
	
	/* For the Date */
	public Date getDateFormat(String s){
		
		DateFormat dateformat;
		Calendar myCal = Calendar.getInstance();
		Scanner scanDate = new Scanner(s);
		scanDate.useDelimiter("/");
		myCal.set(scanDate.nextInt(), scanDate.nextInt() - 1, scanDate.nextInt());
		dateformat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.JAPAN);
		Date curDate = myCal.getTime();
		
		return curDate;
	}
	
	/* For the Time */
	public Date getTimeFormat(String s){

		DateFormat timeformat;
		timeformat = DateFormat.getTimeInstance(DateFormat.SHORT);
		Scanner scanTime = new Scanner(s).useDelimiter(":| ");
		Date time = null;
		if (s.contains("PM")) {
			time = new Date(0, 0, 0, scanTime.nextInt() + 12, scanTime.nextInt());
		} else {
			time = new Date(0, 0, 0, scanTime.nextInt(), scanTime.nextInt());
		}
		return time;
		
	}

}
