package com.teamgreen.pollconapp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is a utility class which will have all the date, date formats related utility methods.
 * @author ayavagal001
 */
public class DateUtil {
	
	

	/**
	 * Convert to date format.
	 * @param src the src format
	 * @param target the target format
	 * @param date the date to be formatted
	 * @return the string
	 */
	public static String convertToDateFormat(DateFormat src, DateFormat target,String srcDate)throws Exception{
		String targetDate;
		try {
			Date tempDate = src.parse(srcDate);
			targetDate = target.format(tempDate);
		} catch (ParseException e) {
			e.printStackTrace();
			throw e;
		}
		return targetDate;
	}
	
	public static Date formatStringToDate(String dateStr, DateFormat sdf ) {
		
		Date target = null;
		try {
			target  = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return target;
	}

	/** Date Format: yyyy-MM-dd HH:mm:ss   Example: 2011-06-28 03:13:01*/
	public static final DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/** Date Format: MM/dd/yyyy HH:mm a    Example: 28/06/2011 03:13 PM*/
	public static final DateFormat format2 = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
	
	/** Date Format: MM/dd/yyyy HH:mm:ss a zzz   Example: 28/06/2011 03:13:44 PM GMT*/
	public static final DateFormat format3 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a zzz");
	
	/** Date Format: MM/dd/yyyy*/
	public static final DateFormat format4 = new SimpleDateFormat("yyyy-MM-dd");
	
	
	
}
