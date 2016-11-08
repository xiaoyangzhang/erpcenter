package com.yimayhd.erpcenter.dal.sales.client.constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	
	public static final Integer PAGESIZE = 15;// 每页个数,用于PageBean
	public static final Integer TRAVELAGENCY = 1; // 组团社
	public static final Integer RESTAURANT = 2; // 餐厅
	public static final Integer HOTEL = 3; // 酒店
	public static final Integer FLEET = 4; // 车队
	public static final Integer SCENICSPOT = 5; // 景区
	public static final Integer SHOPPING = 6; // 购物
	public static final Integer ENTERTAINMENT = 7; // 娱乐
	public static final Integer GUIDE = 8; // 导游

	public static final Integer AIRTICKETAGENT = 9; // 机票代理
	public static final Integer TRAINTICKETAGENT = 10; // 火车票代理
	public static final Integer GOLF = 11; // 高尔夫
	public static final Integer OTHER = 12; // 其他
	public static final Integer OTHERINCOME = 120; // 其他收入
	public static final Integer OTHEROUTCOME = 121; // 其他支出
	public static final Integer CONTRACTAGREEMENT = 13; // 合同协议
    public static final Integer SUPPLIERCOMMENT = 14; // 商家评论
    public static final Integer INSURANCE = 15; // 保险
    public static final Integer LOCALTRAVEL = 16; // 地接社


    /**
     * 打印单价格中添加一行默认数据 -- 其他=3000*订单人数*次数
     */
    public static final String PRICETYPE = "其他" ; //价格类型
    public static final Double PRICE = new Double(3000) ; //价格
    public static final Double TIMES = new Double(1) ; //次数

	public static final String RESTAURANT_TYPE_CODE = "GYS_CT_TC";
    public static final String SHOPPING_TYPE_CODE = "GYS_GW_XM";
    public static final String FLEET_TYPE_CODE = "GYS_CD_CX";
    public static final String FLEET_LINE_BRAND_TYPE_CODE = "CPXL_PP";
    public static final String HOTEL_TYPE_CODE_1 = "GYS_JD_LX";
    public static final String HOTEL_TYPE_CODE_2 = "GYS_JD_FX";
    public static final String SCENICSPOT_TYPE_CODE = "GYS_JQ_XM";
    public static final String ENTERTAINMENT_TYPE_CODE = "GYS_YL_XM";
    public static final String GUIDE_TYPE_CODE = "GYS_DY_XM";
    public static final String GOLF_TYPE_CODE = "GYS_GEF_XM";
    public static final String OTHER_TYPE_CODE = "GYS_QT_XM";
    public static final String TRAVELAGENCY_TYPE_CODE = "GYS_TRA_XM";
    public static final String AIRTICKET_TYPE_CODE = "GYS_JP_LB";
    public static final String TRAINTICKET_TYPE_CODE = "GYS_HCP_LB";
    public static final String INSURANCE_TYPE_CODE = "GYS_BX_XM";
    public static final String SUPPLIER_IMG_TYPE_BUSSINESS = "SUPPLIER_IMG_TYPE_BUSSINESS";
    public static final String LOCALTRAVEL_TYPE_CODE = "GYXX_LYSFXM";
    public static final String CNM = "BOOKING_HOTEL_REF_CONTRACT";
    //供应商-保险-项目
    public static final String GYS_BX_XM = "GYS_BX_XM";
    public static final String GYS_HCP_LB = "GYS_HCP_LB";
    public static final String GYS_JP_LB = "GYS_JP_LB";
    //意向游玩
    public static final String MSGL_YXYW = "MSGL_YXYW";
    //客人来源
    public static final String MSGL_KRLY = "MSGL_KRLY";
    //信息渠道
    public static final String MSGL_XXQD = "MSGL_XXQD";
    //跟进方式
    public static final String MSGL_GJFS = "MSGL_GJFS";
    
    //客源地类别 
    public static final String GUEST_SOURCE_TYPE="GUEST_SOURCE_TYPE";
    
    public static final Map<Integer, String> dictTypeMap = new HashMap<Integer, String>();
    public static final Map<Integer, String> dictType2Map = new HashMap<Integer, String>();
    //供应商类型supplierType和字典类型表中的code的映射关系
    public static final Map<Integer, String> dictType3Map = new HashMap<Integer, String>();
    /***
     * 计调管理订单编号缩写
     */
    public static final Map<Integer, String> SUPPLIERSHORTCODEMAP = new HashMap<Integer, String>();  
    
    public static final String SUPPLIER_IMG_TYPE_ENVIRONMENT_HOTEL = "SUPPLIER_IMG_TYPE_ENVIRONMENT_HOTEL";
    public static final String SUPPLIER_IMG_TYPE_ENVIRONMENT_RESTANRANT = "SUPPLIER_IMG_TYPE_ENVIRONMENT_RESTANRANT";
    public static final String SUPPLIER_IMG_TYPE_ENVIRONMENT_OTHER = "SUPPLIER_IMG_TYPE_ENVIRONMENT_OTHER";
    public static final String SUPPLIER_IMG_TYPE_ENVIRONMENT_MOTORCADE = "SUPPLIER_IMG_TYPE_ENVIRONMENT_MOTORCADE";
    public static final String SUPPLIER_IMG_TYPE_ENVIRONMENT_SECNIC = "SUPPLIER_IMG_TYPE_ENVIRONMENT_SECNIC";
    public static final String SUPPLIER_IMG_TYPE_ENVIRONMENT_SHOP = "SUPPLIER_IMG_TYPE_ENVIRONMENT_SHOP";
    public static final String SUPPLIER_IMG_TYPE_ENVIRONMENT_ENTERTANMENT = "SUPPLIER_IMG_TYPE_ENVIRONMENT_ENTERTANMENT";
    public static final String SUPPLIER_IMG_TYPE_ENVIRONMENT_GOLF = "SUPPLIER_IMG_TYPE_ENVIRONMENT_GOLF";    
    
    static{
        dictTypeMap.put(RESTAURANT, RESTAURANT_TYPE_CODE);
        dictTypeMap.put(SHOPPING, SHOPPING_TYPE_CODE);
        dictTypeMap.put(FLEET, FLEET_TYPE_CODE);
        dictTypeMap.put(HOTEL, HOTEL_TYPE_CODE_1);
        dictTypeMap.put(SCENICSPOT, SCENICSPOT_TYPE_CODE);
        dictTypeMap.put(ENTERTAINMENT, ENTERTAINMENT_TYPE_CODE);
        dictTypeMap.put(GUIDE, GUIDE_TYPE_CODE);
        dictTypeMap.put(GOLF, GOLF_TYPE_CODE);
        dictTypeMap.put(OTHER, OTHER_TYPE_CODE);
        dictTypeMap.put(TRAVELAGENCY, TRAVELAGENCY_TYPE_CODE);
        dictTypeMap.put(AIRTICKETAGENT, AIRTICKET_TYPE_CODE);
        dictTypeMap.put(TRAINTICKETAGENT, TRAINTICKET_TYPE_CODE);
        dictTypeMap.put(INSURANCE, INSURANCE_TYPE_CODE);
        dictTypeMap.put(LOCALTRAVEL, LOCALTRAVEL_TYPE_CODE);
        dictType2Map.put(HOTEL, HOTEL_TYPE_CODE_2);
      
        dictType3Map.put(HOTEL, SUPPLIER_IMG_TYPE_ENVIRONMENT_HOTEL);
        dictType3Map.put(RESTAURANT, SUPPLIER_IMG_TYPE_ENVIRONMENT_RESTANRANT);
        dictType3Map.put(SHOPPING, SUPPLIER_IMG_TYPE_ENVIRONMENT_SHOP);
        dictType3Map.put(FLEET, SUPPLIER_IMG_TYPE_ENVIRONMENT_MOTORCADE);
        dictType3Map.put(SCENICSPOT, SUPPLIER_IMG_TYPE_ENVIRONMENT_SECNIC);
        dictType3Map.put(ENTERTAINMENT, SUPPLIER_IMG_TYPE_ENVIRONMENT_ENTERTANMENT);
        dictType3Map.put(GOLF, SUPPLIER_IMG_TYPE_ENVIRONMENT_GOLF);
        dictType3Map.put(OTHER, SUPPLIER_IMG_TYPE_ENVIRONMENT_OTHER);
        
        SUPPLIERSHORTCODEMAP.put(HOTEL, "H");
        SUPPLIERSHORTCODEMAP.put(RESTAURANT, "R");
        SUPPLIERSHORTCODEMAP.put(FLEET, "C");
        SUPPLIERSHORTCODEMAP.put(SCENICSPOT, "S");
        SUPPLIERSHORTCODEMAP.put(AIRTICKETAGENT, "A");
        SUPPLIERSHORTCODEMAP.put(TRAINTICKETAGENT, "T");
        SUPPLIERSHORTCODEMAP.put(GOLF, "G");
        SUPPLIERSHORTCODEMAP.put(INSURANCE, "I");
        SUPPLIERSHORTCODEMAP.put(ENTERTAINMENT, "E");
        SUPPLIERSHORTCODEMAP.put(OTHERINCOME, "OI");
        SUPPLIERSHORTCODEMAP.put(OTHEROUTCOME, "OO");
        SUPPLIERSHORTCODEMAP.put(TRAVELAGENCY, "TA");
        SUPPLIERSHORTCODEMAP.put(LOCALTRAVEL, "LR");
    }
    
    
    //科目汇总
    public static final String SUMJECT_SUMMARY_QDYJ = "签单月结";
    public static final String SUMJECT_SUMMARY_DYXF = "导游现付";
    public static final String SUMJECT_SUMMARY_GSXF = "公司现付";
    
    public static final String SUMJECT_SUMMARY_DYXS = "导游现收";
    public static final String SUMJECT_SUMMARY_GSXS = "公司现收";
    
    /**
     * 爱游供应商与组团社对应关系 
     */
    public static final Map<String,Integer> supplierMap = new HashMap<String,Integer>();
    static{
    	supplierMap.put("AY", 79);
    	supplierMap.put("YM", 79);
    	supplierMap.put("JY", 79);
    	supplierMap.put("TX", 79);
    }
    
    
    /**
     * 接口地址
     */
    public static final Map<String,String> aiYouUrlMap = new HashMap<String,String>();
    static{
    	aiYouUrlMap.put("AY", "121.41.173.162");
    	aiYouUrlMap.put("YM", "121.41.173.162:30001");
    	aiYouUrlMap.put("JY", "121.41.173.162:30002");
    	aiYouUrlMap.put("TX", "121.41.173.162:30003");
    }


    /**
     *  爱游数据访问项目(yihg-aiyou-api)接口URL
     */
    public static final String AIYOU_API_URL = "http://121.41.173.162:30005/yihg-aiyou-api";
}
