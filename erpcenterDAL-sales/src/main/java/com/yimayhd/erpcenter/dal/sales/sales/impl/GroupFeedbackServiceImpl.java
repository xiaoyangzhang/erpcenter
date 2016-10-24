package com.yihg.sales.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.sales.api.GroupFeedbackService;
import com.yihg.sales.dao.GroupFeedbackMapper;
import com.yihg.sales.po.GroupFeedback;
import com.yihg.sales.vo.GroupFeedbackGroupStaticsVO;
import com.yihg.sales.vo.GroupFeedbackPersonalStaticsVO;

public class GroupFeedbackServiceImpl implements GroupFeedbackService{

	@Autowired
	private GroupFeedbackMapper groupFeedbackMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return groupFeedbackMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(GroupFeedback record) {
		return groupFeedbackMapper.insert(record);
	}

	@Override
	public int insertSelective(GroupFeedback record) {
		return groupFeedbackMapper.insertSelective(record);
	}

	@Override
	public GroupFeedback selectByPrimaryKey(Integer id) {
		return groupFeedbackMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GroupFeedback record) {
		return groupFeedbackMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(GroupFeedback record) {
		return groupFeedbackMapper.updateByPrimaryKey(record);
	}

	@Override
	public GroupFeedback selectByPrimaryGroupId(Integer groupId,Integer createId) {
		return groupFeedbackMapper.selectByPrimaryGroupId(groupId,createId);
	}

	@Override
	public GroupFeedbackGroupStaticsVO getGroupStaticsByGroupId(Integer groupId) {
		if(groupId!=null){
			return groupFeedbackMapper.selectGroupStaticsByGroupId(groupId);
		}
		return null;
	}

	@Override
	public GroupFeedbackPersonalStaticsVO getPersonalStaticsByGroupIdAndIdNo(
			Integer groupId, String idNo) {
		if(groupId!=null && StringUtils.isNotBlank(idNo)){
			List<GroupFeedbackPersonalStaticsVO> list = groupFeedbackMapper.selectPersonalStaticsByGroupIdAndIdNo(groupId, idNo);
			if(list!=null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

}
