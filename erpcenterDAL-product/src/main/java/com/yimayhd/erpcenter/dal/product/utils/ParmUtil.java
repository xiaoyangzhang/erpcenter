/*
 * FileName: ParmUtil.java
 * Author:   liubb
 * Date:     2016年5月21日 下午3:06:20
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.erpcenter.dal.product.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
/**
 * 〈一句话功能简述〉<br>
 * 〈参数转换〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ParmUtil {

    private static final Logger logger = LoggerFactory.getLogger(ParmUtil.class);

    private static final String OR = " OR ";

    private static final String PARAMT = ":";

    private static final String SORT = "sort";


    /**
     * 
     * 功能描述: <br>
     * 〈获取查询条件〉
     *
     * @param filedName
     * @param strs
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static SolrQuery getQueryParm(String filedName, String[] strs, SolrQuery solrQuery) {
        if (ParamCheckUtil.checkArrayNull(strs)) {
            return solrQuery;
        }
        List<String> params = new ArrayList<String>();
        for (String str : strs) {
            StringBuilder sb = new StringBuilder();
            sb.append(filedName);
            sb.append(PARAMT);
            sb.append(str);
            params.add(sb.toString());
        }
        solrQuery.addFilterQuery(StringUtils.join(params, OR));
        return solrQuery;
    }


    public static SolrQuery getQueryParm(String filedName, int[] vals, SolrQuery solrQuery) {
        if (vals==null || vals.length==0) {
            return solrQuery;
        }
        List<String> params = new ArrayList<String>();
        for (int val : vals) {
            StringBuilder sb = new StringBuilder();
            sb.append(filedName);
            sb.append(PARAMT);
            sb.append(val);
            params.add(sb.toString());
        }
        solrQuery.addFilterQuery(StringUtils.join(params, OR));
        return solrQuery;
    }


    public static SolrQuery getQueryParm(String filedName, String[] strs, SolrQuery solrQuery, boolean all) {
        if (ParamCheckUtil.checkArrayNull(strs)) {
            return solrQuery;
        }
        List<String> params = new ArrayList<String>();
        for (String str : strs) {
            StringBuilder sb = new StringBuilder();
            sb.append(filedName);
            sb.append(PARAMT);
            if(all) {
                sb.append(Constats.SCENIC).append(str).append(Constats.SCENIC);
            }else {
                sb.append(str);
            }
            params.add(sb.toString());
        }
        solrQuery.addFilterQuery(StringUtils.join(params, OR));
        return solrQuery;
    }






    /**
     * 
     * 功能描述: <br>
     * 〈距离条件〉
     *
     * @param longitude 经度
     * @param latitude 纬度
     * @param distince
     * @param solrQuery
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static SolrQuery getDistanceParm(String[] latitude, String[] longitude, String[] distince,
            SolrQuery solrQuery) {
        solrQuery.addFilterQuery("{!geofilt}");
        solrQuery.setParam("pt", latitude[0] + "," + longitude[0]);
        solrQuery.setParam("sfield", "position");
        solrQuery.setParam("d", meterIntoKm(distince[0]));
        return solrQuery;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈价格条件〉
     *
     * @param strs
     * @param beginDay
     * @param solrQuery
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static SolrQuery getPriceParm(String[] strs, String beginDay, SolrQuery solrQuery) {
        if (ParamCheckUtil.checkArrayNull(strs) || 2 != strs.length) {
            return solrQuery;
        }
        String filedName = Constats.HEAD_LINE + beginDay + Constats.END_LINE;
        solrQuery.addFilterQuery(filedName + ":[" + strs[0] + " TO " + strs[1] + "]");
        return solrQuery;
    }



    public static SolrQuery setBetweenParm(String[] strs,String field, SolrQuery solrQuery) {
        if (ParamCheckUtil.checkArrayNull(strs) || 2 != strs.length) {
            return solrQuery;
        }
        solrQuery.addFilterQuery(field + ":[" + strs[0] + " TO " + strs[1] + "]");
        return solrQuery;
    }

    public static SolrQuery setPrice(long price ,String field, SolrQuery solrQuery) {
        if (price<0) {
            return solrQuery;
        }
        solrQuery.addFilterQuery(field + ":" + price);
        return solrQuery;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈日期条件〉
     *
     * @param beginDay
     * @param endDay
     * @param solrQuery
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static SolrQuery getDaysParm(String[] beginDay, String[] endDay, SolrQuery solrQuery) {
//        if (ParamCheckUtil.checkArrayNull(beginDay) || ParamCheckUtil.checkArrayNull(endDay)) {
//            return solrQuery;
//        }
//        List<String> days = TimeUtil.prepareDays(beginDay[0], endDay[0]);
//        if (ParamCheckUtil.checkListNull(days)) {
//            return solrQuery;
//        }
//        for (String day : days) {
//            solrQuery.addFilterQuery(Constats.HEAD_LINE + day + Constats.END_LINE + ":{0 TO *]");
//            // solrQuery.addField(Constats.HEAD_LINE + day + Constats.END_LINE);
//        }
//        // solrQuery.addField(Constats.HEAD_LINE + beginDay[0] + Constats.END_LINE);
        return solrQuery;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈SKU 日期查询条件〉
     *
     * @param beginDay
     * @param endDay
     * @param solrQuery
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static SolrQuery getDaysParmForSku(String[] beginDay, String[] endDay, SolrQuery solrQuery) {
        if (ParamCheckUtil.checkArrayNull(beginDay) || ParamCheckUtil.checkArrayNull(endDay)) {
            return solrQuery;
        }
        int begin = objectToInt(beginDay[0]);
        int end  = objectToInt(endDay[0]);
        // 开始时间大于结束时间
        if (0 == begin || 0 == end || begin > end) {
            return solrQuery;
        }else if(begin == end){
            solrQuery.addFilterQuery("day:" + beginDay[0]);
        }else{
            solrQuery.addFilterQuery("day:[" + beginDay[0] + " TO " + endDay[0] + "]");  
        }
        return solrQuery;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈转long〉
     *
     * @param object
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static long objectToLong(Object object) {
        try {
            return null == object ? 0 : Long.valueOf(String.valueOf(object.toString()));
        } catch (Exception e) {
            logger.error("objectToLong is error:{}", e);
            return 0;
        }
    }

    /**
     * 
     * 功能描述: <br>
     * 〈转int〉
     *
     * @param object
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static int objectToInt(Object object) {
        try {
            return null == object ? 0 : Integer.valueOf(String.valueOf(object));
        } catch (Exception e) {
            logger.error("objectToInt is error:{}", e);
            return 0;
        }
    }

    /**
     * 
     * 功能描述: <br>
     * 〈转double〉
     *
     * @param object
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static double objectToDouble(Object object) {
        try {
            if (null == object) {
                return 0;
            }
            BigDecimal b = new BigDecimal(Double.valueOf(String.valueOf(object)));
            return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        } catch (Exception e) {
            logger.error("objectToDouble is error:{}", e);
            return 0;
        }
    }

    /**
     * 
     * 功能描述: <br>
     * 〈转string〉
     *
     * @param object
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String objectToString(Object object) {
        return null == object ? "" : String.valueOf(object);
    }

    /**
     * 
     * 功能描述: <br>
     * 〈获取库存〉
     *
     * @param object
     * @param day
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static int queryStockNum(Object object, String day) {
        try {
            if (null != object) {
                JSONObject parseObject = JSONObject.parseObject(String.valueOf(object));
                return Integer.valueOf(parseObject.get(day).toString());
            }
        } catch (Exception e) {
            logger.error("queryStockNum par:{} query stockNum is error:{}", day, e);
        }
        return 0;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈米转换成千米〉
     *
     * @param meter
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String meterIntoKm(String meter) {
        try {
            BigDecimal b1 = new BigDecimal(meter);
            BigDecimal b3 = b1.divide(new BigDecimal("1000"), 2, BigDecimal.ROUND_HALF_EVEN);
            return b3.toString();
        } catch (Exception e) {
            logger.error("meterIntoKm par:{} is error:{}", meter, e);
            return "0";
        }
    }

    public static String transformSolrMetacharactor(String input){
        StringBuffer sb = new StringBuffer();
        String regex = "[+\\-&|!(){}\\[\\]^\"~*?:(\\)]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while(matcher.find()){
            matcher.appendReplacement(sb, "\\\\"+matcher.group());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}