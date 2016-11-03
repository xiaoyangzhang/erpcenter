package com.yimayhd.erpcenter.facade.sales.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroupComment;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitGroupInfoVO;
import com.yimayhd.erpcenter.facade.sales.query.AgencyOrderQueryDTO;
import com.yimayhd.erpcenter.facade.sales.result.AgencyOrderResult;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;

public interface AgencyFitGroupFacade {

	ResultSupport secMergeGroup(Integer groupId,String[] idArr);
	ResultSupport updateByPrimaryKeySelective(TourGroup tourGroup);
	ResultSupport delFitTour(Integer groupId);
	ResultSupport delFitOrder(Integer groupId);
	ResultSupport updateFitGroupInfo(FitGroupInfoVO fitgroupVO,Integer userId,String userName);
	FitGroupInfoVO selectFitGroupInfoById(Integer groupId);
	AgencyOrderResult toFitGroupList(AgencyOrderQueryDTO queryDTO);
	AgencyOrderResult toEditGroupComment(Integer bizId,List<Integer> groupIds);
	AgencyOrderResult toSKConfirmPreview(Integer bizId,List<Integer> groupIds);
	ResultSupport updateTourGroupComment(Integer bizId,TourGroupComment comment);
}
