package com.yimayhd.erpcenter.dal.sales.query.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;


public interface QueryMapper {
	
	/**
	 * 产品客源地购物分析查询条件
	 * @param condition
	 * @return
	 */
	List<Map<String,Object>> getProductGuestShoppingGroupOrderListPage(@Param("page")PageBean pageBean);
	
	List<Map<String,Object>> getGuestSourceShoppingByOrderId(@Param("orderIds")String orderIds);
	List<Map<String,Object>> getGuestSourceShoppingByOrderIds(@Param("page") PageBean pageBean,@Param("set")Set<Integer> set);
	List<Map<String,Object>>	getSupplierGuestShoppingGroupOrderListPage(@Param("page")PageBean pageBean,@Param("set")Set<Integer> set);
	//List<Map<String,Object>>	getGuestSexInfo(@Param("parameter")Map parameters);
	Map<String,Object> getReceivePersonStatistics(@Param("parameter")Map parameters);
	/**
	 * 客人年龄段分析总人数统计
	 * @param parameters
	 * @return
	 */
	Map<String, Object> getPersonCountMap(@Param("map")Map parameters);
	/**
	 * 获取地接社的期初未付款总计（业务查询下）
	 * @param parameters
	 * @return
	 */
	Map<String, Object> selectDeliveryOriginalTotal(@Param("parameter")Map parameters);
	/**
	 * 获取餐厅、门票的期初未付款总计（业务查询下）
	 * @param parameters
	 * @return
	 */
	Map<String, Object> selectOriginalTotal1(@Param("parameter")Map parameters);
	/**
	 * 获取车队的期初未付款总计（业务查询下）
	 * @param parameters
	 * @return
	 */
	Map<String, Object> selectOriginalTotal2(@Param("parameter")Map parameters);

	/**
	 * 
	 * @param pageBean
	 * @param set
	 * @return
	 */
	List<Map<String, Object>> getSupplierGuestShoppingGroupOrderListPages(@Param("page")PageBean pageBean, @Param("set")Set<Integer> set);

}
