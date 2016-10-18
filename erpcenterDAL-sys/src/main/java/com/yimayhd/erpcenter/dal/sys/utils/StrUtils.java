package com.yimayhd.erpcenter.dal.sys.utils;

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
	
}
