package com.yimayhd.erpcenter.biz.sales.service.impl.sales;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sales.client.service.sales.TeamGroupBiz;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.TeamGroupDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TeamGroupVO;


public class TeamGroupBizImpl implements TeamGroupBiz {
	private static final Logger log = LoggerFactory
			.getLogger(TeamGroupBizImpl.class);
	@Autowired
	private TeamGroupDal teamGroupDal;
	
	@Override
	public TeamGroupVO selectTeamGroupVOByGroupId(Integer groupId, Integer bizId) {
		return teamGroupDal.selectTeamGroupVOByGroupId(groupId, bizId);
	}

	@Override
	public TeamGroupVO saveOrUpdateTeamGroupVO(Integer bizId, Integer userId,
			String userName, TeamGroupVO teamGroupVO) throws ParseException {
		return teamGroupDal.saveOrUpdateTeamGroupVO(bizId, userId, userName, teamGroupVO);
	}

	@Override
	public TeamGroupVO saveOrUpdateRequirement(TeamGroupVO teamGroupVO,
			Integer userId, String userName) {
		return teamGroupDal.saveOrUpdateRequirement(teamGroupVO, userId, userName);
	}

}
