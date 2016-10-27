package com.yimayhd.erpcenter.dal.sales.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SolrDateUtil {

	public static Date getDate(String dateString , int beforeDays) throws ParseException{
		  
		  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		  Date inputDate = dateFormat.parse(dateString);
		  
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(inputDate);
		  
		  int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		  cal.set(Calendar.DAY_OF_YEAR , inputDayOfYear-beforeDays );
		  
		  return cal.getTime();
		 }
	

	public static String getLong2Date(long timeStart ,String format) throws ParseException{
		 SimpleDateFormat sdf=new SimpleDateFormat(format);
		 Date date = new Date(timeStart);
		return sdf.format(date);
		 }

	public static void main(String[] args) throws Exception{
		long timeStart=Long.parseLong("1451577600000");
		
		System.out.println(getLong2Date(timeStart,"yyyy-MM-dd"));
	}
}
