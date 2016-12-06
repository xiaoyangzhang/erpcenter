package com.yimayhd.erpcenter.dal.sys.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class StrUtils {

	/**
	 * 
	 * @param str 1,2,3
	 * @return List<String>
	 */
	public static List<String> strSplitList(String str){
		if(StringUtils.isNotEmpty(str)){
			String[] s = str.split(",");
			List<String> asList = Arrays.asList(s);
			return asList;
		}
		return null;
		
	}
	
	/**
	 * 去除首尾字符
	 * @author daixiaoman
	 */
	public static String trimLastAndFirstStr(String srcStr,String strChar)
	{
		String resStr = srcStr;
		if(null != srcStr){
			resStr = srcStr.trim();
			if(resStr.startsWith(strChar)){
				resStr = resStr.replaceAll("^("+strChar+")+","");
			}
			
			if(resStr.endsWith(strChar)){
				resStr = resStr.replaceAll("("+strChar+")+$","");
			}
		}
		return resStr;
	}
	
	public static List<String> strSplitList(String str,String splitChar){
		if(StringUtils.isNotEmpty(str)){
			String[] s = str.split(splitChar);
			List<String> asList = new ArrayList<String>();
			for(String splitS : s){
				asList.add(splitS);
			}
			return asList;
		}
		return new ArrayList<String>();
	}
	
}
