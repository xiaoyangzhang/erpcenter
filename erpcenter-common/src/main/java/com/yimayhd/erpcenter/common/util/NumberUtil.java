package com.yimayhd.erpcenter.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {

	private static String DECIMAL_FORMAT_DOUBLE = "0.##";
	
	public static String formatDouble(BigDecimal number){
		if(number == null){
			return "0";
		}
		DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT_DOUBLE);
		return df.format(number);
	}
	
	public static String formatDouble(Double number){
		if(number == null){
			return "0";
		}
		DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT_DOUBLE);
		return df.format(number);
	}
	
	public static int intValue(Integer number){
		if(number == null){
			return 0;
		}
		return number.intValue();
	}
	
	public static double doubleValue(Double number){
		if(number == null){
			return 0;
		}
		return number.doubleValue();
	}
	
	public static BigDecimal parseStr2Num(String number){
		if(number == null || "".equals(number)){
			return new BigDecimal(0);
		}
		return new BigDecimal(number);
	}
	
	public static BigDecimal parseObj2Num(Object number){
		if(number == null){
			return parseStr2Num("");
		}
		return parseStr2Num(number.toString());
	}
	
	public static String formatDate(Date inputDate){
		SimpleDateFormat sy1 = new SimpleDateFormat("yyyy-MM-dd"); 
		return sy1.format(inputDate);  
	}
	
	public static boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}

	/**
	 * if arg == null,or arg <= 0,return false
	 * @param arg
	 * @return
	 */
	public static boolean isIntegerValid(Integer arg) {
		if (arg == null || arg <= 0) {
			return false;
		}
		return true;
	}
	public static void main(String[] args) {

	}

}
