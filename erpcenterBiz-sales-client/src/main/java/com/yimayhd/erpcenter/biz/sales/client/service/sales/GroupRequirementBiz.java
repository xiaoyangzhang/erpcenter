package com.yimayhd.erpcenter.biz.sales.client.service.sales;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;


public interface GroupRequirementBiz {
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
