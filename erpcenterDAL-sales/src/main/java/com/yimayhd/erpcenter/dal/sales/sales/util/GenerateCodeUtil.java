/**
 * 
 */
package com.yimayhd.erpcenter.dal.sales.sales.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @ClassName: GenerateCode
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月24日
 */
public class GenerateCodeUtil {

	 /**
     * @param bizCode 当前商家编码
     * @return
     */
	 public static String makeCodeByMode(Integer bizId, String bizCode,
             String dateTime, int sort) {
			String code = bizCode + dateTime.replace("-", "") + sort;
			return code;

	 }
	 	/**
		 * 团号规则-组团社专用 商家编码-产品编码-160101/02(标识)-序号 2016-01-01
		 */
	 public static String makeCodeForAgency(String bizCode, String productCode, String startTime, String endTime, String mark,
				int sort) {

			SimpleDateFormat sdf1 = new SimpleDateFormat("yy-MM-dd");
			try {
				startTime = sdf1.format(sdf1.parse(startTime));
				endTime = sdf1.format(sdf1.parse(endTime));

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			mark = (mark == null || "".equals(mark)) ? "" : "(" + mark.toUpperCase() + ")";

			String code = "";
			if (productCode == null || "".equals(productCode)) {
				code = bizCode + "-" + startTime.replace("-", "") + "/" + endTime.split("-")[2] + mark + "-" + sort;
			} else {
				code = bizCode + "-" + productCode + "-" + startTime.replace("-", "") + "/" + endTime.split("-")[2] + mark
						+ "-" + sort;
			}
			return code;
		}
	 	/**
		 * 团号规则-地接社专用
		 */
	 public static String makeCodeByMode(String bizCode, Integer mode, String dateTime, String mark, int count) {

			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
			try {
				dateTime = sdf.format(sdf.parse(dateTime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// int count = tourGroupMapper.selectGroupCodeSort(mode, dateTime);
			String groupCode = "";
			if (mode != null && mode == 0) {
				groupCode = "S";
			} else {
				groupCode = "T";
			}
			mark = (mark == null || "".equals(mark)) ? "" : "(" + mark.toUpperCase() + ")";
			String code = bizCode + "-" + dateTime.replace("-", "") + groupCode + mark + "-" + count;
			return code;
		}
}
