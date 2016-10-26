package com.yimayhd.erpcenter.biz.sales.service.impl.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRequirementBiz;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupRequirementDal;

public class GroupRequirementBizImpl implements GroupRequirementBiz {
	@Autowired
	private GroupRequirementDal groupRequirementDal ;
	
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return groupRequirementDal.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(GroupRequirement record) {
		
		return groupRequirementDal.insert(record);
	}

	@Override
	public int insertSelective(GroupRequirement record) {
		
		return groupRequirementDal.insertSelective(record);
	}

	@Override
	public GroupRequirement selectByPrimaryKey(Integer id) {
		
		return groupRequirementDal.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GroupRequirement record) {
		
		return groupRequirementDal.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(GroupRequirement record) {
		
		return groupRequirementDal.updateByPrimaryKey(record);
	}

	@Override
	public List<GroupRequirement> selectByOrderAndType(Integer orderId,
			Integer type) {
		
		return groupRequirementDal.selectByOrderAndType(orderId, type);
	}

	@Override
	public List<GroupRequirement> selectByGroupIdAndType(Integer groupId,
			Integer type) {
		
		return groupRequirementDal.selectByGroupIdAndType(groupId, type);
	}

	@Override
	public List<GroupRequirement> selectByOrderId(Integer orderId) {
		
		return groupRequirementDal.selectByOrderId(orderId);
	}

}
