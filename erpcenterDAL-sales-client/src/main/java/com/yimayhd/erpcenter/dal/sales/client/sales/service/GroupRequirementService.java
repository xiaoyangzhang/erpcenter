package com.yimayhd.erpcenter.dal.sales.client.sales.service;

import java.util.List;

import com.yihg.sales.po.GroupRequirement;

public interface GroupRequirementService {
	 int deleteByPrimaryKey(Integer id);

	    int insert(GroupRequirement record);

	    int insertSelective(GroupRequirement record);

	    GroupRequirement selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(GroupRequirement record);

	    int updateByPrimaryKey(GroupRequirement record);
	    
	    List<GroupRequirement> selectByOrderAndType(Integer orderId,Integer type);
	    
	    List<GroupRequirement> selectByGroupIdAndType(Integer groupId,Integer type);
	    
	    List<GroupRequirement> selectByOrderId(Integer orderId);
}
