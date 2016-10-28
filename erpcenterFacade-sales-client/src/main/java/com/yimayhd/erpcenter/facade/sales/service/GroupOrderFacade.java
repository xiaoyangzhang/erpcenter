package com.yimayhd.erpcenter.facade.sales.service;

import java.text.ParseException;

import com.yimayhd.erpcenter.facade.sales.query.grouporder.AddGroupOrderDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.EditOrderGroupInfoDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.GetFitOrderListDataDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.MergeGroupDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToDeliveryPriceTableDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToNotGroupListDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToOrderLockTableDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToProductOrdersTableDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToSecImpNotGroupListDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.GetFitOrderListDataResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToAddGroupOrderResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToDeliveryPriceListResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToDeliveryPriceTableResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToEditGroupOrderResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToFitEditResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToFitOrderListResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToImpNotGroupListDTO;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToImpNotGroupListResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToLookGroupOrderResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToMergeGroupResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToNotGroupListResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToOrderLockListResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToOrderLockTableResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToProductOrdersListResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToProductOrdersTableResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToSecImpNotGroupListResult;

/**
 * 
 * @author gaotingping
 * 2016年10月27日
 */
public interface GroupOrderFacade {
	
	//跳到锁单列表(锁单管理)
   	ToOrderLockListResult toOrderLockList(Integer bizId);

   	//锁单查询分页
   	ToOrderLockTableResult toOrderLockTable(ToOrderLockTableDTO orderLockTableDTO) throws ParseException;

   	//更新订单锁单状态
   	BaseStateResult updateOrderLockState(Integer orderId, Integer orderLockState);

   	//日志详细
   	GetFitOrderListDataResult getFitOrderListData(GetFitOrderListDataDTO getFitOrderListDataDTO);

   	//订单日志列表
	ToProductOrdersListResult toProductOrdersList(Integer bizId);

	//订单日志查询分页
	ToProductOrdersTableResult toProductOrdersTable(ToProductOrdersTableDTO toProductOrdersTableDTO);

	//
	ToAddGroupOrderResult toAddGroupOrder(int bizId, Integer productId, Integer groupId, Integer priceId);

	ToDeliveryPriceListResult toDeliveryPriceList(Integer bizId);

	ToDeliveryPriceTableResult toDeliveryPriceTable(ToDeliveryPriceTableDTO toDeliveryPriceTableDTO);

	//FIXME 下面两个方法有重复的
	BaseStateResult insertGroupMany(String ids, String code);

	BaseStateResult insertGroup(Integer id, String code);

	//修改散客团信息
	BaseStateResult editOrderGroupInfo(EditOrderGroupInfoDTO editOrderGroupInfoDTO);

	BaseStateResult delFitTourGroup(Integer groupId);

	ToFitOrderListResult toFitOrderList(Integer groupId) throws ParseException;

	ToFitEditResult toFitEdit(Integer groupId, Integer bizId);

	//FIXME 这个和fitGroup好像有冗余
	BaseStateResult mergeGroup(MergeGroupDTO mergeGroupDTO) throws ParseException;

	BaseStateResult secMergeGroup(Integer groupId, String ids);

	BaseStateResult beforeInsertGroup(String ids);

	BaseStateResult judgeMergeGroup(String ids);

	ToMergeGroupResult toMergeGroup(String ids);

	ToNotGroupListResult toNotGroupList(ToNotGroupListDTO toNotGroupListDTO) throws ParseException;

	ToSecImpNotGroupListResult toSecImpNotGroupList(ToSecImpNotGroupListDTO toSecImpNotGroupListDTO);

	ToImpNotGroupListResult toImpNotGroupList(ToImpNotGroupListDTO toImpNotGroupListDTO);

	BaseStateResult delGroupOrder(Integer curBizId, Integer id);

	BaseStateResult addGroupOrder(AddGroupOrderDTO addGroupOrderDTO);

	ToLookGroupOrderResult toLookGroupOrder(Integer id,Integer bizId);

	ToEditGroupOrderResult toEditGroupOrder(Integer id, int bizId);

	BaseStateResult editGroupOrder(Integer id, Integer employeeId, Integer num);
}
