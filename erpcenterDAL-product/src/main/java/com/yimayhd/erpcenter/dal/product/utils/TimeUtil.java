/*
 * FileName: TimeUtil.java
 * Author:   liubb
 * Date:     2016年5月21日 下午2:43:55
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.erpcenter.dal.product.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 〈一句话功能简述〉<br> 
 * 〈时间处理工具类〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TimeUtil {

    private static final Logger logger = LoggerFactory.getLogger(TimeUtil.class); 
    
    private static final String DAY_FORMAT = "yyyyMMdd";
    /**
     * 
     * 功能描述: <br>
     * 〈计算两日期间隔天数〉
     *
     * @param startTime
     * @param endTime
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
//    public static List<String> prepareDays(String startTime, String endTime) {
//        if(StringUtils.isBlank(endTime) || StringUtils.isBlank(startTime)) {
//            return null;
//        }
//        DateTime startDay = new DateTime(getDayTime(startTime));
//        DateTime endDay = new DateTime(getDayTime(endTime));
//        //判断合法性
//        if (startDay.isAfter(endDay.plus(1))) {
//            return null;
//        }
//        Period p = new Period(startDay, endDay, PeriodType.days());
//        // 2 表示包含开始与 结束时间
//        int daysCount = p.getDays() + 1;
//        List<String> days = new ArrayList<String>();
//        DateTime time = startDay;
//        for (int i = 0; i < daysCount; i++) {
//            days.add(time.toString(DAY_FORMAT));
//            time = time.plusDays(1);
//        }
//        return days;
//    }
    
    /**
     * 
     * 功能描述: <br>
     * 〈计算两日期间隔天数〉
     *
     * @param startTime
     * @param endTime
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static int prepareDaysCount(String startTime, String endTime) {
//        if(StringUtils.isBlank(endTime) || StringUtils.isBlank(startTime)) {
//            return 0;
//        }
//        DateTime startDay = new DateTime(getDayTime(startTime));
//        DateTime endDay = new DateTime(getDayTime(endTime));
//        Period p = new Period(startDay, endDay, PeriodType.days());
//        return p.getDays() + 1;
    	return 1;
    }
    /**
     * 
     * 功能描述: <br>
     * 〈获取time〉
     *
     * @param day
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static long getDayTime(String day){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DAY_FORMAT);
            return simpleDateFormat.parse(day).getTime();
        } catch (Exception e) {
            logger.error("getDayTime par:{} yyyyMMdd getTime error, mes:{}", day, e);
        }
        return 0;
    }
    
    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param day
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Date getDay(String day){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DAY_FORMAT);
            return simpleDateFormat.parse(day);
        } catch (Exception e) {
            logger.error("getDayTime par:{} yyyyMMdd getTime error, mes:{}", day, e);
        }
        return null;
    }
}