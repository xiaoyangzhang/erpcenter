package com.yimayhd.erpcenter.dal.sales.client.constants;

import java.util.HashMap;
import java.util.Map;



public class SupplierConstant {
	public static final Map<Integer, String> supplierTypeMap = new HashMap<Integer, String>();
	public static final Map<Integer, String> supplierTypeMapIn = new HashMap<Integer, String>();
	public static final Map<Integer, String> supplierTypeMapPay = new HashMap<Integer, String>();
	public static final Map<Integer, String> supplierTypeCSS = new HashMap<Integer, String>();
	
	public static final Map<Integer, String> supplierTypeSubjectSummary = new HashMap<Integer, String>();
	public static final Map<Integer, String> supplierTypeSubjectSummaryQT = new HashMap<Integer, String>();
	
	public static final Map<Integer, String> supplierTypeMapAccount = new HashMap<Integer, String>();
	static{
        supplierTypeMap.put(Constants.TRAVELAGENCY, "组团社");
        supplierTypeMap.put(Constants.LOCALTRAVEL, "地接社");
        supplierTypeMap.put(Constants.RESTAURANT, "餐厅");
        supplierTypeMap.put(Constants.HOTEL, "酒店");
        supplierTypeMap.put(Constants.FLEET, "车队");
        supplierTypeMap.put(Constants.SCENICSPOT, "景区");
        supplierTypeMap.put(Constants.SHOPPING, "购物");
        supplierTypeMap.put(Constants.ENTERTAINMENT, "娱乐");
       supplierTypeMap.put(Constants.GUIDE, "导游");
        supplierTypeMap.put(Constants.AIRTICKETAGENT, "机票");
        supplierTypeMap.put(Constants.TRAINTICKETAGENT, "火车票");
        supplierTypeMap.put(Constants.GOLF, "高尔夫");
        supplierTypeMap.put(Constants.OTHER, "其他");
        supplierTypeMap.put(Constants.OTHERINCOME, "其他收入");
        supplierTypeMap.put(Constants.OTHEROUTCOME, "其他支出");
        supplierTypeMap.put(Constants.INSURANCE, "保险");

        supplierTypeCSS.put(Constants.RESTAURANT, "bg_foodpri");
        supplierTypeCSS.put(Constants.HOTEL, "bg_roompri");
        supplierTypeCSS.put(Constants.FLEET, "bg_carpri");
        supplierTypeCSS.put(Constants.SCENICSPOT, "bg_tick");
        supplierTypeCSS.put(Constants.AIRTICKETAGENT, "bg_airtick");
        supplierTypeCSS.put(Constants.TRAINTICKETAGENT, "bg_carpri");
        supplierTypeCSS.put(Constants.OTHER, "bg_other");  
        supplierTypeCSS.put(Constants.OTHERINCOME, "bg_other"); 
        supplierTypeCSS.put(Constants.OTHEROUTCOME, "bg_other"); 
        supplierTypeCSS.put(Constants.INSURANCE, "bg_safe");
        supplierTypeCSS.put(Constants.GOLF, "bg_golf");
        supplierTypeCSS.put(Constants.ENTERTAINMENT, "bg_yule");

        supplierTypeMapIn.put(Constants.TRAVELAGENCY, "组团社");
        supplierTypeMapIn.put(Constants.SHOPPING, "购物");
        supplierTypeMapIn.put(Constants.OTHER, "其他");   
        
        supplierTypeMapPay.put(Constants.LOCALTRAVEL, "地接社");
        supplierTypeMapPay.put(Constants.RESTAURANT, "餐厅");
        supplierTypeMapPay.put(Constants.HOTEL, "酒店");
        supplierTypeMapPay.put(Constants.FLEET, "车队");
        supplierTypeMapPay.put(Constants.SCENICSPOT, "景区");
        supplierTypeMapPay.put(Constants.ENTERTAINMENT, "娱乐");
        supplierTypeMapPay.put(Constants.GUIDE, "导游");
        supplierTypeMapPay.put(Constants.AIRTICKETAGENT, "机票");
        supplierTypeMapPay.put(Constants.TRAINTICKETAGENT, "火车票");
        supplierTypeMapPay.put(Constants.GOLF, "高尔夫");
        supplierTypeMapPay.put(Constants.INSURANCE, "保险");
        supplierTypeMapPay.put(Constants.OTHER, "其他");   
        
        
        supplierTypeSubjectSummary.put(Constants.HOTEL, "酒店");
        supplierTypeSubjectSummary.put(Constants.RESTAURANT, "餐厅");
        supplierTypeSubjectSummary.put(Constants.FLEET, "车队");
        supplierTypeSubjectSummary.put(Constants.SCENICSPOT, "景区");
        supplierTypeSubjectSummary.put(Constants.INSURANCE, "保险");
        supplierTypeSubjectSummary.put(Constants.OTHERINCOME, "其他收入");
        supplierTypeSubjectSummary.put(Constants.OTHEROUTCOME, "其他支出");
        
        supplierTypeSubjectSummaryQT.put(Constants.LOCALTRAVEL, "地接社");
        supplierTypeSubjectSummaryQT.put(Constants.TRAINTICKETAGENT, "火车票");
        supplierTypeSubjectSummaryQT.put(Constants.AIRTICKETAGENT, "机票");
        
        
        supplierTypeMapAccount.put(Constants.LOCALTRAVEL, "地接社");
        supplierTypeMapAccount.put(Constants.RESTAURANT, "餐厅");
        supplierTypeMapAccount.put(Constants.HOTEL, "酒店");
        supplierTypeMapAccount.put(Constants.FLEET, "车队");
        supplierTypeMapAccount.put(Constants.SCENICSPOT, "景区");
        supplierTypeMapAccount.put(Constants.AIRTICKETAGENT, "机票");
        supplierTypeMapAccount.put(Constants.TRAINTICKETAGENT, "火车票");
        supplierTypeMapAccount.put(Constants.INSURANCE, "保险");
        supplierTypeMapAccount.put(Constants.OTHEROUTCOME, "其他支出");   
        
    }
}

