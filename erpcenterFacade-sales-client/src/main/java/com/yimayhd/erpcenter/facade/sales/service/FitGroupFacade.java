package com.yimayhd.erpcenter.facade.sales.service;

import com.yimayhd.erpcenter.facade.sales.query.*;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToSecImpNotGroupListDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.FitGroupInfoQueryResult;
import com.yimayhd.erpcenter.facade.sales.result.FitTotalSKGroupQueryResult;
import com.yimayhd.erpcenter.facade.sales.result.ToFitGroupTableResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToSecImpNotGroupListResult;

/**
 * 销售-散客团管理
 * 
 * @author gaotingping
 * 2016年10月25日
 */
public interface FitGroupFacade {

	//散客团查询
	public FitTotalSKGroupQueryResult toFitGroupList(FitTotalSKGroupQueryDTO totalSKGroupQueryDTO);

	//去查询团信息
	public FitGroupInfoQueryResult toFitGroupInfo(FitGroupInfoQueryDTO fitGroupInfoQueryDTO);

	//从散客团里批量删除散客订单
	public void delFitOrderBatch(String ids);

	//从散客团里删除散客订单
	public void delFitOrder(Integer id);

	//更新散客团
	public void updateFitGroupInfo(FitGroupInfoUpdateDTO fitGroupInfoUpdateDTO);

	//删除散客团
	public BaseStateResult delFitTourGroup(Integer groupId);

	//修改散客团信息
	public BaseStateResult updateFitTourGroup(FitUpdateTourGroupDTO fitUpdateTourGroupDTO);

	//散客团订单列表中添加订单
	public void addOrderToTourGroup(Integer groupId, String ids);

	//合团
	public ToSecImpNotGroupListResult toSecImpNotGroupList(ToSecImpNotGroupListDTO toSecImpNotGroupListDTO);

	public ToFitGroupTableResult toFitGroupTable(ToFitGroupTableDTO toFitGroupTableDTO);

	public ToFitGroupTableResult toSelectTotalPerson(ToFitGroupTableDTO toFitGroupTableDTO);


}
