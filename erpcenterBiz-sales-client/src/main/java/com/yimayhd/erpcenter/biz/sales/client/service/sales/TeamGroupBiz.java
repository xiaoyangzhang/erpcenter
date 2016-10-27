package com.yimayhd.erpcenter.biz.sales.client.service.sales;

import java.text.ParseException;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TeamGroupVO;


public interface TeamGroupBiz {
	public TeamGroupVO selectTeamGroupVOByGroupId(Integer groupId,Integer bizId);
	
	public TeamGroupVO saveOrUpdateTeamGroupVO(Integer bizId,Integer userId,String userName,TeamGroupVO teamGroupVO)throws ParseException;
	
	public TeamGroupVO saveOrUpdateRequirement(TeamGroupVO teamGroupVO,Integer userId,String userName);
}
