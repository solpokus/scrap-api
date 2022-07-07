package id.digital.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class TimeStamp {

	public static void main(String[] args) {
		
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
		df.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
		String dateStr = df.format(cal.getTime());
		System.out.println("dateStr"+dateStr);
		System.out.println(String.valueOf(cal.getTimeInMillis()));
	}
}
