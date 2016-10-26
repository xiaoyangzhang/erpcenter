package com.yimayhd.erpcenter.dal.sales.operation.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.GroupBookingInfo;

public interface OperationMapper {
	List<GroupBookingInfo> selectGroupBookingListPage(@Param("page") PageBean pageBean, @Param("bizId") Integer bizId,
			@Param("set")Set<Integer> set);
	
	Map<String, Object> getPersonCount(@Param("page") PageBean pageBean, @Param("bizId") Integer bizId,
			@Param("set")Set<Integer> set);
	
	
	List<Map<String, Object>> selectBookingSupplierCountForGroups(@Param("groupIds")String groupIds, 
			@Param("supplierType")Integer supplierType, @Param("supplierName")String supplierName);
	
	List<Map<String, Object>> selectBookingShopCountForGroups(@Param("groupIds")String groupIds, @Param("supplierName")String supplierName);
	
	List<Map<String, Object>> selectBookingGuideForGroups(@Param("groupIds")String groupIds, @Param("supplierName")String supplierName);
	
	List<Map<String, Object>> selectBookingDeliveryForGroups(@Param("groupIds")String groupIds, @Param("supplierName")String supplierName);
}