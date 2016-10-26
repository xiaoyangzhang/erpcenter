package com.yimayhd.erpcenter.biz.sales.client.service.sales;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupFeedback;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupFeedbackGroupStaticsVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupFeedbackPersonalStaticsVO;

/**
 * 当前行程  旅游服务评价
 * @author lyl
 *
 */
public interface GroupFeedbackBiz {
	int deleteByPrimaryKey(Integer id);

	int insert(GroupFeedback record);

	int insertSelective(GroupFeedback record);

	GroupFeedback selectByPrimaryKey(Integer id);
	
	GroupFeedback selectByPrimaryGroupId(Integer groupId,Integer createId);

	int updateByPrimaryKeySelective(GroupFeedback record);

	int updateByPrimaryKey(GroupFeedback record);
	
	/**
	 * 根据团id统计团评分（总平均分及各项平均分）
	 * @param groupId
	 * @return
	 */
	GroupFeedbackGroupStaticsVO getGroupStaticsByGroupId(Integer groupId);
	
	/**
	 * 根据团id和身份证号获取某个顾客对某个团的评议分及平均分
	 * @param groupId
	 * @param idNo
	 * @return
	 */
	GroupFeedbackPersonalStaticsVO getPersonalStaticsByGroupIdAndIdNo(Integer groupId,String idNo);
}
