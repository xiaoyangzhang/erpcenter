package com.yimayhd.erpcenter.dal.sales.client.query.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.ProductGuestCondition;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.ProductGuestStaticsVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SaleOperatorVo;

public interface QueryDAL {
	/**
	 * 根据订单和订单收客情况进行产品收客统计
	 * @param condition
	 * @return
	 */
	List<ProductGuestStaticsVo> productGuestStatics(ProductGuestCondition condition,Set<Integer> userIds);
	//导出使用
	List<ProductGuestStaticsVo> productGuestStatics2(ProductGuestCondition condition,Set<Integer> userIds);
	
	/**
	 * 收客统计
	 * @param condition
	 * @return
	 */
	List<Map<String,Object>> guestSourceStatics(ProductGuestCondition condition,Set<Integer> userIds);
	//导出使用
	List<Map<String, Object>> guestSourceStatics2(ProductGuestCondition condition,Set<Integer> userIds);
	
	
	
	/**
	 * 客户团量分析专用拼串
	 */
	public String getGroupNumStatics(List<GroupOrder> allGroup,Integer dataType);
	
	/**
	 * 计调预定安排
	 * @param pageBean
	 * @param bizId
	 * @param set
	 * @return
	 */
	PageBean selectGroupBookingListPage( PageBean pageBean,Integer bizId,Set<Integer> set);
	/**
	 * 销售计调单
	 * @param pageBean
	 * @param bizId
	 * @param set
	 * @return
	 */
	PageBean<SaleOperatorVo> selectSaleOperatorByConListPage(PageBean<SaleOperatorVo> pageBean,Integer bizId,Set<Integer> set);
   /**
    * 查询人数总计
    * @param pageBean
    * @param bizId
    * @param set
    * @return
    */
	Map<String, Object>	 selectSaleOperatorTotalPerson(PageBean<SaleOperatorVo> pageBean,Integer bizId,Set<Integer> set);
	
	/***
	 * 产品客源地购物统计
	 * @param pageBean
	 * @return
	 */
	PageBean selectProductGuestShopStatics(PageBean pageBean);
/*	*//**
	 * 客户客源地购物分析
	 * @param pageBean
	 * @return
	 */
	PageBean selectSupplierGuestShopStatics(PageBean pageBean,Set<Integer> set);
//	Map<String, Object> getPersonCount(PageBean pageBean, Integer bizId, Set<Integer> set);
	/**
	 * 客户客源地购物分析1
	 * @param pageBean
	 * @return
	 */
	PageBean selectSupplierGuestShopStatic(PageBean pageBean,Set<Integer> set);
	Map<String, Object> getPersonCount(PageBean pageBean, Integer bizId, Set<Integer> set);
	/**
	 * 获取客人性别信息
	 * @param parameters
	 * @return
	 */
	String getGuestSexInfo(Map parameters);
	/**
	 * 获取客人年龄段信息
	 * @param parameters
	 * @return
	 */
	String getGuestAgeInfo(Map parameters);
	/**
	 * 获取客人航班落地时间信息
	 * @param parameters
	 * @return
	 */
	String getGuestAirTimeInfo(Map parameters);
	/**
	 * 获取客人客源地信息
	 * @param parameters
	 * @return
	 */
	String getGuestSouceInfo(Map parameters);
	/**
	 * 接待人数统计
	 * @param parameters
	 * @return
	 */
	Map<String, Object> getReceivePersonStatistics(Map parameters);
	/**
	 * 客人年龄段分析总人数统计
	 * @param parameters
	 * @return
	 */
	Map<String, Object> getPersonCountMap(Map parameters);
	/**
	 * 获取地接社统计期初未付总计
	 * @param parameters
	 * @return
	 */
	Map<String, Object> selectDeliveryOriginalTotal(Map parameters);
	/**
	 * 获取餐厅、门票的期初未付总计
	 * @param parameters
	 * @return
	 */
	Map<String, Object> selectOriginalTotal1(Map parameters);
	/**
	 * 获取车队的期初未付总计
	 * @param parameters
	 * @return
	 */
	Map<String, Object> selectOriginalTotal2(Map parameters);
	
	List<Map<String, Object>> selectBookingSupplierCountForGroups(String groupIds, Integer supplierType, String supplierName);
	
	List<Map<String, Object>> selectBookingShopCountForGroups(String groupIds, String supplierName);
	
	List<Map<String, Object>> selectBookingGuideForGroups(String groupIds, String supplierName);
	
	List<Map<String, Object>> selectBookingDeliveryForGroups(String groupIds, String supplierName);
}
