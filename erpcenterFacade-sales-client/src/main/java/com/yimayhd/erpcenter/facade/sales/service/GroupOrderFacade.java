package com.yimayhd.erpcenter.facade.sales.service;

import java.text.ParseException;

import com.yimayhd.erpcenter.facade.sales.query.grouporder.*;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.*;

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

	BaseStateResult insertGroupMany(String ids, String code);

	BaseStateResult insertGroup(Integer id, String code);

	//修改散客团信息
	BaseStateResult editOrderGroupInfo(EditOrderGroupInfoDTO editOrderGroupInfoDTO);

	BaseStateResult delFitTourGroup(Integer groupId);

	ToFitOrderListResult toFitOrderList(Integer groupId) throws ParseException;

	ToFitEditResult toFitEdit(Integer groupId, Integer bizId);

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

	BaseStateResult editSupplierAndReceiveMode(EditSupplierAndReceiveModeDTO editSupplierAndReceiveModeDTO);

	BaseStateResult editGroupOrderText(EditGroupOrderDTO editGroupOrderDTO);

	BaseStateResult editGroupOrderContMan(Integer conId, Integer id);

	BaseStateResult editGroupGuest(EditGroupGuestDTO editGroupGuestDTO);

	ToEditGroupGuestResult toEditGroupGuest(Integer id);

	BaseStateResult delGroupGuest(Integer id);

	BaseStateResult addGroupRequirement(AddGroupRequirementDTO addGroupRequirementDTO);

	BaseStateResult editGroupRequirement(AddGroupRequirementDTO addGroupRequirementDTO);

	ToEditGroupRequirementResult toEditGroupRequirement(Integer id);

	BaseStateResult delGroupRequirement(Integer id);

	BaseStateResult addManyGroupOrderTransport(AddManyGroupOrderTransportDTO addManyGroupOrderTransportDTO);

	BaseStateResult addGroupOrderTransport(AddGroupOrderTransportDTO addGroupOrderTransport);

	BaseStateResult editGroupOrderTransport(AddGroupOrderTransportDTO addGroupOrderTransport);

	ToEditGroupOrderTransportResult toEditGroupOrderTransport(Integer id);

	BaseStateResult delGroupOrderTransport(Integer id);

	BaseStateResult addGroupOrderPrice(AddGroupOrderPriceDTO addGroupOrderPriceDTO);

	BaseStateResult addGroupOrderPriceMany(AddGroupOrderPriceManyDTO addGroupOrderPriceManyDTO);

	BaseStateResult editGroupOrderPrice(AddGroupOrderPriceDTO addGroupOrderPriceDTO);

	BaseStateResult delOrderPrice(Integer id);

	ToEditGroupOrderPriceResult toEditGroupOrderPrice(Integer id);

	ToIndividualGuestTicklingResult toIndividualGuestTickling(Integer groupId);

	ToIndividualOrderGuestTicklingResult toIndividualOrderGuestTickling(Integer orderId);

	CreateSKGuideUpOffResult createSKGuideUpOff(Integer groupId);

	CreateSKGuideResult createSKGuide(Integer groupId,String imgPath,String userName);

	ToShoppingDetailPreviewResult toShoppingDetailPreview(Integer groupId);

	CreateShoppingDetailResult createShoppingDetail(Integer groupId,String userName);

	ToShoppingDetailPreviewResult toShoppingDetailPreview1(Integer groupId, String userName);

	CreateShoppingDetailResult createShoppingDetail2(Integer groupId,String userName);

	CreatetTicklingResult createtTickling(Integer groupId);

	CreatetOrderTicklingResult createtOrderTickling(Integer orderId);

	CreatetTicklingResult createtTeamGroupTickling(Integer groupId);

	CreateGuestNamesResult createGuestNames(Integer groupId);

	PreviewGuestWithTransResult previewGuestWithTrans(Integer groupId);

	PreviewGuestWithoutTransResult previewGuestWithoutTrans(Integer groupId);

	PreviewFitGuideResult previewFitGuide(Integer groupId);

	PreviewFitTransferResult previewFitTransfer(Integer groupId);

	CreateIndividualResult createIndividual(Integer groupId);

	ImportOrderTableResult importOrderTable(ImportOrderTableDTO importOrderTableDTO);

	ImportOrderTableResult saveTransferOrder(ImportOrderTableDTO importOrderTableDTO);

}
