package com.yimayhd.erpcenter.dal.sales.sales.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;

public interface GroupRequirementMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(GroupRequirement record);

	int insertSelective(GroupRequirement record);

	GroupRequirement selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(GroupRequirement record);

	int updateByPrimaryKey(GroupRequirement record);

	List<GroupRequirement> selectByOrderAndType(@Param("orderId")Integer orderId, @Param("type")Integer type);
	
	List<GroupRequirement> selectByGroupIdAndType(@Param("groupId")Integer groupId,@Param("type")Integer type);
	
	List<GroupRequirement> selectByOrderId(Integer orderId);
	
	List<GroupRequirement> selectGroupRequirementByGroupId(@Param("orderId")Integer orderId,@Param("type")Integer type) ;
	
	List<Map<String,Object>> selectRequirementForOrders(@Param("page")PageBean pageBean, @Param("orderIds")String orderIds);
	
}