package com.yimayhd.erpcenter.dal.sales.client.sales.constants;

/**
 * Created by ZhengZiyu on 2015/7/2.
 */
public class Constants {

	public static final Integer PAGESIZE = 10;// 每页个数,用于PageBean
	public static final Integer TRAVELAGENCY = 1; // 旅行社
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
	public static final Integer OTHER = 12; // 其他支出
	public static final Integer CONTRACTAGREEMENT = 13; // 合同协议
	
	/**
	 * 打印单价格中添加一行默认数据 -- 其他=3000*订单人数*次数
	 */
	public static final String PRICETYPE = "其他" ; //价格类型
	public static final Double PRICE = new Double(3000) ; //价格
	public static final Double TIMES = new Double(1) ; //次数
}
