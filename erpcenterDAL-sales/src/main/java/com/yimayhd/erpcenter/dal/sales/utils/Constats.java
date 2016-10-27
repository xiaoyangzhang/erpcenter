/*
 * FileName: Constats.java
 * Author:   liubb
 * Date:     2016年5月21日 下午3:40:26
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.erpcenter.dal.sales.utils;

/**
 * 〈一句话功能简述〉<br> 
 * 〈全局变量〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Constats {
    

    public static final String HEAD_LINE = "_";

    public static final String END_LINE = "_l";
    
    public static final String  HOTEL = "id,domainId,cityName,cityCode,hotelName,level,icon,stockNum,rateCount,tradeArea,hotelType";
    
    public static final String HOTEL_DISTANCE = HOTEL + ",distance:geodist()";
    
    public static final String SCENIC = "*";
    
    public static final String SCENIC_DISTANCE = "*,distance:geodist()";
    
    public static final String QUERY_ALL = "*:*";
    
    public static final String T = ":";
    
    public static final String DOMAIN_ID = "domainId" + T;

    public static final String DOMAIN = "domain" + T;

    public static final String SELLERID = "sellerId" + T;

    public static final String SELLERTYPE = "sellType" + T;
    
    public static final String CITY_CODE = "cityCode";

    public static final String CITY_NAME = "cityName";
    
    public static final String HOTEL_TYPE  = "hotelType";
    
    public static final String LEVEL  = "level";
    
    public static final String CANTON  = "canton";
    
    public static final String TRADEAREA  = "tradeArea";
    
    public static final String THEME  = "themeCode";

    public static final String THEME_TEXT  = "theme";
    
    public static final String BLANK = " ";

    public static final String FROM_CITY_CODE = "fromCityCode";

    public static final String TO_CITY_CODE = "toCityCode";

    public static final String DAYS = "days";

    public static final String TITLE = "title"+T;

    public static final String LEFTCONTAIN = "[";

    public static final String RIGHTCONTAIN = "]";

    public static final int MIN_QUERY_PAGE = 50;

    public static final String ITEMTYPE  = "itemType";

    public static final String OR = " OR ";

    public static final String ID  = "id";

    public static final String TOPIC_PREFIX  = "#";

    public static final String CONSULTTIME = "consultTime" + T;

    public static final String PRICE = "price";

    public static final String SORT = "sort";

    public static final String GUIDESTATUS ="status:1";

    public static final String ISGUIDE="is_guide:1";
}
