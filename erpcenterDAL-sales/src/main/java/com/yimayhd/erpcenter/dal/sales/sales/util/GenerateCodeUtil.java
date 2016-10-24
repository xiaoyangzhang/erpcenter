/**
 * 
 */
package com.yimayhd.erpcenter.dal.sales.sales.util;

/**
 * @ClassName: GenerateCode
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月24日
 */
public class GenerateCodeUtil {

	 public static String makeCodeByMode(Integer bizId, String bizCode,
             String dateTime, int sort) {
			String code = bizCode + dateTime.replace("-", "") + sort;
			return code;
}
}
