package com.yimayhd.erpcenter.biz.basic.basic.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

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
	
	public static void main(String[] args) {

	}

}
