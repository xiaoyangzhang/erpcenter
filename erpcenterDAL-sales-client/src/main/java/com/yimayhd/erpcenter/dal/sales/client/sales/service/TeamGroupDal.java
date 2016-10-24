package com.yimayhd.erpcenter.dal.sales.client.sales.service;

import java.text.ParseException;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TeamGroupVO;


public interface TeamGroupDal {
	public TeamGroupVO selectTeamGroupVOByGroupId(Integer groupId,Integer bizId);
	
	public TeamGroupVO saveOrUpdateTeamGroupVO(Integer bizId,Integer userId,String userName,TeamGroupVO teamGroupVO)throws ParseException;
	
	public TeamGroupVO saveOrUpdateRequirement(TeamGroupVO teamGroupVO,Integer userId,String userName);
}
