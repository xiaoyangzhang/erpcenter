package com.yimayhd.erpcenter.facade.sales.service;

import java.text.ParseException;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TeamGroupVO;
import com.yimayhd.erpcenter.facade.sales.result.AgencyOrderResult;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;
import com.yimayhd.erpcenter.facade.sales.result.WebResult;

public interface AgencyTeamFacade {

	WebResult<Integer> saveTeamGroupInfo(TeamGroupVO vo,Integer bizId,String userName, Integer userId,String bizCode)throws ParseException;
	ResultSupport deleteGroupOrderById(Integer orderId, Integer groupId,Integer curBizId);
	AgencyOrderResult toEditTeamGroupInfo(Integer groupId, Integer curBizId);
}
