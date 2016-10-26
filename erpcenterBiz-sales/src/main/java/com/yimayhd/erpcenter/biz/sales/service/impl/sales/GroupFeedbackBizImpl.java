package com.yimayhd.erpcenter.biz.sales.service.impl.sales;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupFeedbackBiz;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupFeedback;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupFeedbackDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupFeedbackGroupStaticsVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupFeedbackPersonalStaticsVO;

public class GroupFeedbackBizImpl implements GroupFeedbackBiz{

	@Autowired
	private GroupFeedbackDal groupFeedbackDal;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return groupFeedbackDal.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(GroupFeedback record) {
		return groupFeedbackDal.insert(record);
	}

	@Override
	public int insertSelective(GroupFeedback record) {
		return groupFeedbackDal.insertSelective(record);
	}

	@Override
	public GroupFeedback selectByPrimaryKey(Integer id) {
		return groupFeedbackDal.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GroupFeedback record) {
		return groupFeedbackDal.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(GroupFeedback record) {
		return groupFeedbackDal.updateByPrimaryKey(record);
	}

	@Override
	public GroupFeedback selectByPrimaryGroupId(Integer groupId,Integer createId) {
		return groupFeedbackDal.selectByPrimaryGroupId(groupId,createId);
	}

	@Override
	public GroupFeedbackGroupStaticsVO getGroupStaticsByGroupId(Integer groupId) {
		if(groupId!=null){
			return groupFeedbackDal.getGroupStaticsByGroupId(groupId);
		}
		return null;
	}

	@Override
	public GroupFeedbackPersonalStaticsVO getPersonalStaticsByGroupIdAndIdNo(
			Integer groupId, String idNo) {
		return groupFeedbackDal.getPersonalStaticsByGroupIdAndIdNo(groupId, idNo);
//		if(groupId!=null && StringUtils.isNotBlank(idNo)){
//			List<GroupFeedbackPersonalStaticsVO> list = groupFeedbackDal.getPersonalStaticsByGroupIdAndIdNo(groupId, idNo);
//			if(list!=null && list.size()>0){
//				return list.get(0);
//			}
//		}
//		return null;
	}

}
