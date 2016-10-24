package com.yimayhd.erpcenter.dal.sales.sales.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupRequirementDal;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupRequirementMapper;

public class GroupRequirementDalImpl implements GroupRequirementDal {
	@Autowired
	private GroupRequirementMapper groupRequirementMapper ;
	
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return groupRequirementMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(GroupRequirement record) {
		// TODO Auto-generated method stub
		return groupRequirementMapper.insert(record);
	}

	@Override
	public int insertSelective(GroupRequirement record) {
		// TODO Auto-generated method stub
		return groupRequirementMapper.insertSelective(record);
	}

	@Override
	public GroupRequirement selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return groupRequirementMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GroupRequirement record) {
		// TODO Auto-generated method stub
		return groupRequirementMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(GroupRequirement record) {
		// TODO Auto-generated method stub
		return groupRequirementMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<GroupRequirement> selectByOrderAndType(Integer orderId,
			Integer type) {
		// TODO Auto-generated method stub
		return groupRequirementMapper.selectByOrderAndType(orderId, type);
	}

	@Override
	public List<GroupRequirement> selectByGroupIdAndType(Integer groupId,
			Integer type) {
		// TODO Auto-generated method stub
		return groupRequirementMapper.selectByGroupIdAndType(groupId, type);
	}

	@Override
	public List<GroupRequirement> selectByOrderId(Integer orderId) {
		// TODO Auto-generated method stub
		return groupRequirementMapper.selectByOrderId(orderId);
	}

}
