package com.yimayhd.erpcenter.dal.sales.sales.dao;

import com.yihg.sales.po.GroupRoute;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupRouteMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(GroupRoute record);

	int insertSelective(GroupRoute record);

	GroupRoute selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(GroupRoute record);

	int updateByPrimaryKey(GroupRoute record);

	List<GroupRoute> selectByGroupId(Integer groupId);
	List<GroupRoute> selectByGroupIdAndSupplierId(@Param("groupId") Integer groupId, @Param("supplierId") Integer supplierId);
	List<GroupRoute> selectByGroupIdAndBookingId(@Param("groupId") Integer groupId, @Param("bookingId") Integer bookingId);

	List<GroupRoute> selectByOrderId(Integer orderId);
	
	GroupRoute selectDayNumAndMaxday(@Param("orderId") Integer orderId, @Param("groupId") Integer groupId);
	
	void deleteByGroupId(Integer id);
	
	void deleteByOrderId(Integer id);
	
	

}