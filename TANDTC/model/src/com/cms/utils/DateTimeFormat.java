package com.cms.utils;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class DateTimeFormat {

	public static Date formatDateTime(Date date,String dateformat ){


		DateFormat dateFormat = new SimpleDateFormat (dateformat);

		String dateStr = dateFormat.format (date);
		try{
			Date date2 = dateFormat.parse (dateStr);

			System.out.println(date2);
			return date2;
		}catch(ParseException pe){
			pe.printStackTrace();
			return date;    
		}
	}
	
	public static String formatDate(Date date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		
		return dateFormat.format(date);
	}

	public static Date convertStringtoDate(String dateString,String dateformat){
		try{
		 DateFormat dateFormat = new SimpleDateFormat(dateformat);
		    Date date = dateFormat.parse(dateString);
		   return date;
		    }catch(ParseException pe){
		        pe.printStackTrace();
		        return null;
		    }
		}
	public static String formatDateSQL(Date date) {
		String[] newMonths = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT","NOV", "DEC" };
		DateFormatSymbols symbols = new DateFormatSymbols();
	    symbols.setMonths(newMonths);	    
	    DateFormat format = new SimpleDateFormat("dd-MMMM-yy", symbols);
		return format.format(date);
	}
}

