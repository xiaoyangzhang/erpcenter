package com.yimayhd.erpcenter.facade.sales.service;

import java.text.ParseException;

import com.yimayhd.erpcenter.facade.sales.query.fitorder.DelGroupOrderDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.GetFitOrdersDataDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.GetGroupPirceDataDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.GetInsertFitGroupListDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.InsertGroupDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.MergeGroupDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.SaveFitOrderInfoDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.ToAddGroupOrderDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.ToEditFirOrderDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.ToFitOrderListDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.ToImpNotGroupListDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.fitorder.GetFitOrdersDataResult;
import com.yimayhd.erpcenter.facade.sales.result.fitorder.GetGroupPirceDataResult;
import com.yimayhd.erpcenter.facade.sales.result.fitorder.GetInsertFitGroupListResult;
import com.yimayhd.erpcenter.facade.sales.result.fitorder.GroupOrderListResult;
import com.yimayhd.erpcenter.facade.sales.result.fitorder.SaveFitOrderInfoResult;
import com.yimayhd.erpcenter.facade.sales.result.fitorder.ToAddGroupOrderResult;
import com.yimayhd.erpcenter.facade.sales.result.fitorder.ToEditFirOrderResult;
import com.yimayhd.erpcenter.facade.sales.result.fitorder.ToFitOrderListResult;
import com.yimayhd.erpcenter.facade.sales.result.fitorder.ToImpNotGroupListResult;

/**
 * FitOrderFacade
 * 
 * @author gaotingping
 * 2016年10月27日
 */
public interface FitOrderFacade {

	GetGroupPirceDataResult getGroupPirceData(GetGroupPirceDataDTO getGroupPirceDataDTO);

	ToAddGroupOrderResult toAddGroupOrder(ToAddGroupOrderDTO toAddGroupOrderDTO);

	ToEditFirOrderResult toEditFirOrder(ToEditFirOrderDTO toEditFirOrderDTO) throws ParseException;

	SaveFitOrderInfoResult saveFitOrderInfo(SaveFitOrderInfoDTO saveFitOrderInfoDTO) throws ParseException;

	ToFitOrderListResult toFitOrderList(ToFitOrderListDTO toFitOrderListDTO);

	GetFitOrdersDataResult getFitOrdersData(GetFitOrdersDataDTO getFitOrdersDataDTO) throws ParseException;

	GroupOrderListResult getSubOrderListData(Integer groupId);

	GetInsertFitGroupListResult getInsertFitGroupList(GetInsertFitGroupListDTO getInsertFitGroupListDTO) throws ParseException;

	BaseStateResult delGroupOrder(DelGroupOrderDTO delGroupOrderDTO);

	BaseStateResult beforeInsertGroup(String ids);

	BaseStateResult judgeMergeGroup(String ids);

	BaseStateResult insertGroupMany(String ids, String code);

	BaseStateResult insertGroup(InsertGroupDTO insertGroup);

	GroupOrderListResult toMergeGroup(String ids);

	BaseStateResult mergeGroup(MergeGroupDTO mergeGroupDTO) throws ParseException;

	ToImpNotGroupListResult toImpNotGroupList(ToImpNotGroupListDTO toImpNotGroupListDTO);

	BaseStateResult saveTransportInfo(SaveFitOrderInfoDTO saveFitOrderInfoDTO);

	BaseStateResult saveGuestInfo(SaveFitOrderInfoDTO saveFitOrderInfoDTO);
}
