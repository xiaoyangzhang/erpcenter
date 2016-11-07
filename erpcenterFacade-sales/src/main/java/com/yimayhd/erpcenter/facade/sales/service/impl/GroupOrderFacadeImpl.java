package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupExtraItemBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupPriceBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupSupplierBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRemarkBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.airticket.AirTicketRequestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderPriceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderTransportBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRequirementBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductAttachment;
import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupExtraItem;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupPrice;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
import com.yimayhd.erpcenter.dal.product.po.ProductRouteSupplier;
import com.yimayhd.erpcenter.dal.product.po.ProductRouteTraffic;
import com.yimayhd.erpcenter.dal.product.vo.ProductGroupSupplierVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductRouteDayVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductRouteVo;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceCommission;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupGuidePrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteAttachment;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteSupplier;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteTraffic;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupOrderPriceVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteDayVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.MergeGroupOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SalePrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TransportVO;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.facade.sales.constants.BizConfigConstant;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.AddGroupOrderDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.AddGroupOrderPriceDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.AddGroupOrderPriceManyDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.AddGroupOrderTransportDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.AddGroupRequirementDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.AddManyGroupOrderTransportDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.EditGroupGuestDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.EditGroupOrderDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.EditOrderGroupInfoDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.EditSupplierAndReceiveModeDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.GetFitOrderListDataDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.MergeGroupDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToDeliveryPriceTableDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToImpNotGroupListDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToNotGroupListDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToOrderLockTableDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToProductOrdersTableDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToSecImpNotGroupListDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.CreateGuestNamesResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.CreateIndividualResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.CreateSKGuideResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.CreateSKGuideUpOffResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.CreateShoppingDetailResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.CreatetOrderTicklingResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.CreatetTicklingResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.GetFitOrderListDataResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.PreviewFitGuideResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.PreviewFitTransferResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.PreviewGuestWithTransResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.PreviewGuestWithoutTransResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToAddGroupOrderResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToDeliveryPriceListResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToDeliveryPriceTableResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToEditGroupGuestResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToEditGroupOrderPriceResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToEditGroupOrderResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToEditGroupOrderTransportResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToEditGroupRequirementResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToFitEditResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToFitOrderListResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToImpNotGroupListResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToIndividualGuestTicklingResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToIndividualOrderGuestTicklingResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToLookGroupOrderResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToMergeGroupResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToNotGroupListResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToOrderLockListResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToOrderLockTableResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToProductOrdersListResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToProductOrdersTableResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToSecImpNotGroupListResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToShoppingDetailPreviewResult;
import com.yimayhd.erpcenter.facade.sales.service.GroupOrderFacade;
import com.yimayhd.erpcenter.facade.sales.utils.DateUtils;
import com.yimayhd.erpcenter.facade.sales.utils.GroupCodeUtil;
import com.yimayhd.erpcenter.facade.sales.utils.MergeGroupUtils;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.biz.service.SupplierGuideBiz;
import com.yimayhd.erpresource.dal.po.SupplierContactMan;
import com.yimayhd.erpresource.dal.po.SupplierGuide;
import com.yimayhd.erpresource.dal.po.SupplierInfo;

public class GroupOrderFacadeImpl implements GroupOrderFacade {

	@Autowired
	private SupplierGuideBiz guideService;

	@Autowired
	private DicBiz dicService;

	@Autowired
	private RegionBiz regionService;

	@Autowired
	private GroupOrderBiz groupOrderService;

	@Autowired
	private GroupOrderGuestBiz groupOrderGuestService;

	@Autowired
	private GroupOrderPriceBiz groupOrderPriceService;

	@Autowired
	private GroupOrderTransportBiz groupOrderTransportService;

	@Autowired
	private GroupRequirementBiz groupRequirementService;

	@Autowired
	private PlatformEmployeeBiz platformEmployeeService;

	@Autowired
	private SupplierBiz supplierService;

	@Autowired
	private ProductInfoBiz productInfoService;

	@Autowired
	private ProductGroupBiz productGroupService;

	@Autowired
	private ProductGroupExtraItemBiz productGroupExtraItemService;

	@Autowired
	private ProductGroupPriceBiz productGroupPriceService;

	@Autowired
	private ProductRouteBiz productRouteService;

	@Autowired
	private GroupRouteBiz groupRouteService;

	@Autowired
	private TourGroupBiz tourGroupService;

	@Autowired
	private PlatformOrgBiz orgService;

	@Autowired
	private ProductRemarkBiz productRemarkService;

	@Autowired
	private ProductGroupSupplierBiz productGroupSupplierService;

	@Autowired
	private BookingGuideBiz bookingGuideService;

	@Autowired
	private BookingDeliveryBiz deliveryService;

	@Autowired
	private BookingShopBiz shopService;

	@Autowired
	private BookingSupplierBiz bookingSupplierService;

	@Autowired
	private BookingSupplierDetailBiz detailService;

	@Autowired
	private AirTicketRequestBiz airTicketRequestService;

	@Override
	public ToOrderLockListResult toOrderLockList(Integer bizId) {

		ToOrderLockListResult result = new ToOrderLockListResult();

		result.setAllProvince(regionService.getAllProvince());
		result.setOrgJsonStr(orgService.getComponentOrgTreeJsonStr(bizId));
		result.setOrgUserJsonStr(platformEmployeeService.getComponentOrgUserTreeJsonStr(bizId));

		return result;
	}

	@Override
	public ToOrderLockTableResult toOrderLockTable(ToOrderLockTableDTO orderLockTableDTO) throws ParseException {

		Integer bizId = orderLockTableDTO.getBizId();
		GroupOrder order = orderLockTableDTO.getOrder();
		Set<Integer> userIdSet = orderLockTableDTO.getUserIdSet();

		if (order != null && order.getDateType() == 2) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (!"".equals(order.getStartTime())) {
				order.setStartTime(sdf.parse(order.getStartTime()).getTime() + "");
			}
			if (!"".equals(order.getEndTime())) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(sdf.parse(order.getEndTime()));
				calendar.add(Calendar.DAY_OF_MONTH, +1);// 让日期加1
				order.setEndTime(calendar.getTime().getTime() + "");
			}
		}

		PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();
		pageBean.setPage(order.getPage());
		pageBean.setPageSize(order.getPageSize() == null ? Constants.PAGESIZE : order.getPageSize());
		pageBean.setParameter(order);
		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(order.getSaleOperatorIds()) && StringUtils.isNotBlank(order.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = order.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				order.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}
		pageBean = groupOrderService.selectOrderLockByConListPage(pageBean, bizId, userIdSet);
		String totalPb = groupOrderService.selectOrderLockByCon(pageBean, bizId, userIdSet);

		ToOrderLockTableResult result = new ToOrderLockTableResult();
		result.setPageBean(pageBean);
		result.setTotalPb(totalPb);

		return result;
	}

	@Override
	public BaseStateResult updateOrderLockState(Integer orderId, Integer orderLockState) {
		BaseStateResult result = new BaseStateResult();
		int i = groupOrderService.updateOrderLockState(orderId, orderLockState);
		if (i == 1) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
			result.setError("服务器忙！");
		}
		return result;
	}

	@Override
	public GetFitOrderListDataResult getFitOrderListData(GetFitOrderListDataDTO getFitOrderListDataDTO) {

		GroupOrder groupOrder = getFitOrderListDataDTO.getGroupOrder();

		if (null == groupOrder.getTourGroup()) {
			TourGroup tourGroup = new TourGroup();
			groupOrder.setTourGroup(tourGroup);
		}
		PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();
		pageBean.setPageSize(1000);
		pageBean.setPage(1);
		pageBean.setParameter(groupOrder);
		pageBean = groupOrderService.selectProductOrderListPage(pageBean, getFitOrderListDataDTO.getCurBizId(),
				getFitOrderListDataDTO.getUserIdSet());

		GetFitOrderListDataResult result = new GetFitOrderListDataResult();
		result.setPageBean(pageBean);

		return result;
	}

	@Override
	public ToProductOrdersListResult toProductOrdersList(Integer bizId) {

		ToProductOrdersListResult result = new ToProductOrdersListResult();

		result.setAllProvince(regionService.getAllProvince());
		result.setOrgJsonStr(orgService.getComponentOrgTreeJsonStr(bizId));
		result.setOrgUserJsonStr(platformEmployeeService.getComponentOrgUserTreeJsonStr(bizId));

		return result;
	}

	@Override
	public ToProductOrdersTableResult toProductOrdersTable(ToProductOrdersTableDTO toProductOrdersTableDTO) {

		GroupOrder order = toProductOrdersTableDTO.getOrder();

		// FIXME 下面这快代码存重复的
		PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();
		pageBean.setPage(order.getPage());
		pageBean.setPageSize(order.getPageSize() == null ? Constants.PAGESIZE : order.getPageSize());
		pageBean.setParameter(order);

		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(order.getSaleOperatorIds()) && StringUtils.isNotBlank(order.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = order.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(toProductOrdersTableDTO.getCurBizId(), set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				order.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}

		pageBean = groupOrderService.selectOrderGroupByProductIdListPage(pageBean,
				toProductOrdersTableDTO.getCurBizId(), toProductOrdersTableDTO.getUserIdSet());

		GroupOrder go = groupOrderService.selectOrderGroupByProductIdList(pageBean,
				toProductOrdersTableDTO.getCurBizId(), toProductOrdersTableDTO.getUserIdSet());

		ToProductOrdersTableResult result = new ToProductOrdersTableResult();

		result.setGo(go);
		result.setPageBean(pageBean);

		return result;
	}

	@Override
	public ToAddGroupOrderResult toAddGroupOrder(int bizId, Integer productId, Integer groupId, Integer priceId) {

		ProductGroupPrice productGroupPrice = productGroupPriceService.selectByPrimaryKey(priceId).getGroupPrice();
		ProductGroup group = productGroupService.getGroupInfoById(groupId);
		ProductInfo productInfo = productInfoService.findProductInfoById(productId);
		ProductRemark productRemark = productRemarkService.findProductRemarkByProductId(productId);
		List<ProductGroupExtraItem> grouplist = productGroupExtraItemService.findByGroupId(groupId);
		ProductGroupPrice groupPrice = productGroupPriceService.selectByPrimaryKey(priceId).getGroupPrice();
		List<DicInfo> jdxjList = dicService.getListByTypeCode(BasicConstants.GYXX_JDXJ);
		List<DicInfo> jtfsList = dicService.getListByTypeCode(BasicConstants.GYXX_JTFS, bizId);
		List<DicInfo> zjlxList = dicService.getListByTypeCode(BasicConstants.GYXX_ZJLX);
		List<DicInfo> sourceTypeList = dicService.getListByTypeCode(Constants.GUEST_SOURCE_TYPE, bizId);
		List<RegionInfo> allProvince = regionService.getAllProvince();
		List<DicInfo> lysfxmList = dicService.getListByTypeCode(BasicConstants.GYXX_LYSFXM, bizId);
		List<ProductGroupSupplierVo> groupSuppliersList = productGroupSupplierService
				.selectProductGroupSupplierVos(groupId, priceId);

		ToAddGroupOrderResult result = new ToAddGroupOrderResult();
		result.setAllProvince(allProvince);
		result.setGroup(group);
		result.setGrouplist(grouplist);
		result.setGroupPrice(groupPrice);
		result.setGroupSuppliersList(groupSuppliersList);
		result.setJdxjList(jdxjList);
		result.setJtfsList(jtfsList);
		result.setLysfxmList(lysfxmList);
		result.setProductGroupPrice(productGroupPrice);
		result.setProductInfo(productInfo);
		result.setProductRemark(productRemark);
		result.setSourceTypeList(sourceTypeList);
		result.setZjlxList(zjlxList);

		return result;
	}

	@Override
	public ToDeliveryPriceListResult toDeliveryPriceList(Integer bizId) {

		ToDeliveryPriceListResult result = new ToDeliveryPriceListResult();

		result.setLysfxmList(dicService.getListByTypeCode(BasicConstants.GYXX_LYSFXM, bizId));
		result.setOrgJsonStr(orgService.getComponentOrgTreeJsonStr(bizId));
		result.setOrgUserJsonStr(platformEmployeeService.getComponentOrgUserTreeJsonStr(bizId));

		return result;
	}

	@Override
	public ToDeliveryPriceTableResult toDeliveryPriceTable(ToDeliveryPriceTableDTO toDeliveryPriceTableDTO) {

		SalePrice sp = toDeliveryPriceTableDTO.getSp();
		Integer bizId = toDeliveryPriceTableDTO.getBizId();
		Set<Integer> userIdSet = toDeliveryPriceTableDTO.getUserIdSet();

		// FIXME 此代码重复
		PageBean<SalePrice> pageBean = new PageBean<SalePrice>();
		pageBean.setPage(sp.getPage());
		pageBean.setPageSize(sp.getPageSize() == null ? Constants.PAGESIZE : sp.getPageSize());
		if (StringUtils.isBlank(sp.getSaleOperatorIds()) && StringUtils.isNotBlank(sp.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = sp.getOrgIds().toString().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				sp.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}

		pageBean.setParameter(sp);
		BigDecimal totals = groupOrderService.getSalePriceToalPrice(pageBean, bizId, userIdSet);
		pageBean = groupOrderService.getSalePriceListPage(pageBean, bizId, userIdSet);

		ToDeliveryPriceTableResult result = new ToDeliveryPriceTableResult();

		result.setPageBean(pageBean);
		result.setTotals(totals);

		return result;
	}

	@Override
	public BaseStateResult insertGroupMany(String ids, String code) {

		BaseStateResult result = new BaseStateResult();
		result.setSuccess(false);

		TourGroup tourGroup = tourGroupService.selectByGroupCode(code);
		if (tourGroup == null) {
			result.setError("未查到该团号对应的散客团信息!");
			return result;
		}
		String[] split = ids.split(",");
		for (String str : split) {
			GroupOrder groupOrder = groupOrderService.selectByPrimaryKey(Integer.parseInt(str));
			groupOrder.setGroupId(tourGroup.getId());
			groupOrderService.updateGroupOrder(groupOrder);
			tourGroup.setOrderNum(tourGroup.getOrderNum() == null ? 1 : tourGroup.getOrderNum() + 1);
			tourGroupService.updateByPrimaryKey(tourGroup);
			groupOrderService.updateGroupPersonNum(tourGroup.getId());
			groupOrderService.updateGroupPrice(tourGroup.getId());

			List<GroupRequirement> list = groupRequirementService.selectByOrderId(Integer.parseInt(str));
			if (list != null && list.size() > 0) {
				for (GroupRequirement groupRequirement : list) {
					groupRequirement.setGroupId(tourGroup.getId());
					groupRequirementService.updateByPrimaryKeySelective(groupRequirement);
				}
			}
		}

		result.setSuccess(true);
		return result;
	}

	@Override
	public BaseStateResult insertGroup(Integer id, String code) {

		BaseStateResult result = new BaseStateResult();
		result.setSuccess(false);

		TourGroup tourGroup = tourGroupService.selectByGroupCode(code);
		if (tourGroup == null) {
			result.setError("未查到该团号对应的散客团信息!");
			return result;
		}
		GroupOrder groupOrder = groupOrderService.selectByPrimaryKey(id);
		groupOrder.setGroupId(tourGroup.getId());
		groupOrderService.updateGroupOrder(groupOrder);
		tourGroup.setOrderNum(tourGroup.getOrderNum() == null ? 1 : tourGroup.getOrderNum() + 1);
		tourGroupService.updateByPrimaryKey(tourGroup);
		groupOrderService.updateGroupPersonNum(tourGroup.getId());
		groupOrderService.updateGroupPrice(tourGroup.getId());

		List<GroupRequirement> list = groupRequirementService.selectByOrderId(id);
		if (list != null && list.size() > 0) {
			for (GroupRequirement groupRequirement : list) {
				groupRequirement.setGroupId(tourGroup.getId());
				groupRequirementService.updateByPrimaryKeySelective(groupRequirement);
			}
		}

		result.setSuccess(true);
		return result;
	}

	@Override
	public BaseStateResult editOrderGroupInfo(EditOrderGroupInfoDTO editOrderGroupInfoDTO) {

		BaseStateResult result = new BaseStateResult();
		result.setSuccess(false);

		TourGroup tourGroup = editOrderGroupInfoDTO.getTourGroup();

		if (tourGroup.getPrudctBrandId() != null) {
			DicInfo dicInfo = dicService.getById(tourGroup.getPrudctBrandId());
			tourGroup.setProductBrandName(dicInfo.getValue());
		}
		if (tourGroup.getGroupCode() != null) {
			tourGroup.setGroupCode(GroupCodeUtil.getCode(tourGroup.getGroupCode(), tourGroup.getGroupCodeMark()));
		}
		if (tourGroup.getGroupState() == 2) {
			List<FinanceCommission> fc1 = groupOrderService.selectFinanceCommissionByGroupId(tourGroup.getId());
			if (fc1 != null && fc1.size() > 0) {
				result.setError("该团已有购物及佣金被审核！");
				return result;
			}
			List<FinanceCommission> fc2 = groupOrderService.selectFCByGroupId(tourGroup.getId());
			if (fc2 != null && fc2.size() > 0) {
				result.setError("该团已有购物及佣金被审核！");
				return result;
			}
		}
		tourGroupService.updateByPrimaryKeySelective(tourGroup);

		result.setSuccess(true);
		return result;
	}

	@Override
	public BaseStateResult delFitTourGroup(Integer groupId) {
		tourGroupService.delFitTourGroup(groupId);
		return new BaseStateResult(true, null);
	}

	@Override
	public ToFitOrderListResult toFitOrderList(Integer groupId) throws ParseException {
		List<GroupOrder> result = groupOrderService.selectOrderByGroupId(groupId);
		for (GroupOrder groupOrder2 : result) {
			groupOrder2.setGroupNo(tourGroupService.selectByPrimaryKey(groupOrder2.getGroupId()).getGroupCode());
			List<GroupRoute> list = groupRouteService.selectByOrderId(groupOrder2.getId());
			if (list != null && list.size() > 0) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				cal.setTime(sdf.parse(groupOrder2.getDepartureDate()));
				cal.add(Calendar.DAY_OF_MONTH, +(list.size() - 1));
				Date time = cal.getTime();
				groupOrder2.setFitDate(sdf.format(time));
			}
		}

		TourGroup tourGroup = tourGroupService.selectByPrimaryKey(groupId);

		ToFitOrderListResult toFitOrderListResult = new ToFitOrderListResult();
		toFitOrderListResult.setList(result);
		toFitOrderListResult.setTourGroup(tourGroup);

		return toFitOrderListResult;
	}

	@Override
	public ToFitEditResult toFitEdit(Integer groupId, Integer bizId) {

		ToFitEditResult result = new ToFitEditResult();

		result.setPpList(dicService.getListByTypeCode(BasicConstants.CPXL_PP, bizId));
		result.setTourGroup(tourGroupService.selectByPrimaryKey(groupId));

		return result;
	}

	@Override
	public BaseStateResult mergeGroup(MergeGroupDTO mergeGroupDTO) throws ParseException {

		BaseStateResult baseStateResult = new BaseStateResult();
		baseStateResult.setSuccess(false);

		MergeGroupOrderVO mergeGroupOrderVO = mergeGroupDTO.getMergeGroupOrderVO();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<GroupOrder> orderList = mergeGroupOrderVO.getOrderList();

		List<MergeGroupOrderVO> result = new ArrayList<MergeGroupOrderVO>();
		for (int i = 0; i < orderList.size();) {
			GroupOrder order = orderList.get(i);
			GroupOrder groupOrder = groupOrderService.findById(order.getId());
			groupOrder.setGroupCode(order.getGroupCode());
			orderList.remove(order);
			MergeGroupOrderVO mov = new MergeGroupOrderVO();
			mov.getOrderList().add(groupOrder);

			for (int j = 0; j < orderList.size();) {
				GroupOrder order2 = orderList.get(j);
				GroupOrder go = groupOrderService.findById(order2.getId());
				go.setGroupCode(order2.getGroupCode());
				// 相同，分组，并加入到组容器集合
				if (go.getGroupCode().equals(groupOrder.getGroupCode())) {
					mov.getOrderList().add(go);
					orderList.remove(order2);
				} else {
					j++;
				}
			}
			result.add(mov);
		}
		// --------------------------------------------------------------------------------
		for (MergeGroupOrderVO mergeGroupOrderVO2 : result) {
			List<GroupOrder> orderList2 = mergeGroupOrderVO2.getOrderList();
			ProductInfo productInfo = null;
			for (GroupOrder groupOrder : orderList2) {
				ProductInfo productInfo2 = productInfoService.findProductInfoById(groupOrder.getProductId());
				if (productInfo == null) {
					productInfo = productInfo2;
				} else if (productInfo2 != null && productInfo2.getTravelDays() > productInfo.getTravelDays()) {
					productInfo = productInfo2;
				}
			}
			// -------------------------------------------------------------------------------
			ProductRouteVo productRouteVo = productRouteService.findByProductId(productInfo.getId());
			ProductRemark productRemark = productRemarkService.findProductRemarkByProductId(productInfo.getId());

			TourGroup tourGroup = new TourGroup();
			tourGroup.setServiceStandard(productRemark.getItemInclude() + productRemark.getItemExclude());
			tourGroup.setRemark(productRemark.getChildPlan() + productRemark.getShoppingPlan()
					+ productRemark.getItemCharge() + productRemark.getItemFree());
			tourGroup.setWarmNotice(
					productRemark.getAttention() + productRemark.getItemOther() + productRemark.getWarmTip());

			tourGroup.setServiceStandard(productRemark.getItemInclude() + productRemark.getItemExclude());
			tourGroup.setRemark(productRemark.getChildPlan() + productRemark.getShoppingPlan()
					+ productRemark.getItemCharge() + productRemark.getItemFree());
			tourGroup.setWarmNotice(
					productRemark.getAttention() + productRemark.getItemOther() + productRemark.getWarmTip());

			mergeGroupOrderVO2.setTourGroup(tourGroup);

			GroupRouteVO groupRouteVO = new GroupRouteVO();
			List<GroupRouteDayVO> groupRouteDayVOList = new ArrayList<GroupRouteDayVO>();
			List<ProductRouteDayVo> productRoteDayVoList = productRouteVo.getProductRoteDayVoList();

			if (productRoteDayVoList != null && productRoteDayVoList.size() > 0) {
				for (int i = 0; i < productRoteDayVoList.size(); i++) {
					ProductRoute productRoute = productRoteDayVoList.get(i).getProductRoute();
					GroupRoute groupRoute = new GroupRoute();
					GroupRouteDayVO groupRouteDayVO = new GroupRouteDayVO();
					try {
						BeanUtils.copyProperties(groupRoute, productRoute);
						Calendar cal = Calendar.getInstance();
						cal.setTime(sdf.parse(mergeGroupOrderVO2.getOrderList().get(0).getDepartureDate()));
						cal.add(Calendar.DAY_OF_MONTH, i);
						Date time = cal.getTime();
						groupRoute.setId(null);
						groupRoute.setGroupDate(time);
						List<ProductAttachment> productAttachmentsList = productRoteDayVoList.get(i)
								.getProductAttachments();
						List<GroupRouteAttachment> groupRouteAttachmentList = new ArrayList<GroupRouteAttachment>();
						if (productAttachmentsList != null && productAttachmentsList.size() > 0) {
							for (ProductAttachment productAttachment : productAttachmentsList) {
								GroupRouteAttachment groupRouteAttachment = new GroupRouteAttachment();
								BeanUtils.copyProperties(groupRouteAttachment, productAttachment);
								groupRouteAttachment.setId(null);
								groupRouteAttachmentList.add(groupRouteAttachment);
							}
						}

						List<ProductRouteTraffic> productRouteTrafficList = productRoteDayVoList.get(i)
								.getProductRouteTrafficList();
						List<GroupRouteTraffic> groupRouteTrafficList = new ArrayList<GroupRouteTraffic>();
						if (productRouteTrafficList != null && productRouteTrafficList.size() > 0) {
							for (ProductRouteTraffic productRouteTraffic : productRouteTrafficList) {
								GroupRouteTraffic groupRouteTraffic = new GroupRouteTraffic();
								BeanUtils.copyProperties(groupRouteTraffic, productRouteTraffic);
								groupRouteTraffic.setId(null);
								groupRouteTrafficList.add(groupRouteTraffic);

							}
						}

						// List<GroupRouteSupplier> groupRouteScenicSupplierList
						// =
						// new ArrayList<GroupRouteSupplier>();
						// List<ProductRouteSupplier> productScenicSupplierList
						// =
						// productRoteDayVoList
						// .get(i).getProductScenicSupplierList();
						// if (productScenicSupplierList != null
						// && productScenicSupplierList.size() > 0) {
						// for (ProductRouteSupplier productRouteScenicSupplier
						// :
						// productScenicSupplierList) {
						// GroupRouteSupplier groupRouteScenicSupplier = new
						// GroupRouteSupplier();
						// BeanUtils.copyProperties(groupRouteScenicSupplier,
						// productRouteScenicSupplier);
						// groupRouteScenicSupplier.setId(null);
						// groupRouteScenicSupplierList
						// .add(groupRouteScenicSupplier);
						// }
						// }

						List<GroupRouteSupplier> groupRouteOptionsSupplierList = new ArrayList<GroupRouteSupplier>();
						List<ProductRouteSupplier> productOptionsSupplierList = productRoteDayVoList.get(i)
								.getProductOptionsSupplierList();
						if (productOptionsSupplierList != null && productOptionsSupplierList.size() > 0) {
							for (ProductRouteSupplier productRouteSupplier : productOptionsSupplierList) {
								GroupRouteSupplier groupRouteOptionsSupplier = new GroupRouteSupplier();
								BeanUtils.copyProperties(groupRouteOptionsSupplier, productRouteSupplier);
								groupRouteOptionsSupplier.setId(null);
								groupRouteOptionsSupplierList.add(groupRouteOptionsSupplier);
							}
						}
						groupRouteDayVO.setGroupRoute(groupRoute);
						groupRouteDayVO.setGroupRouteAttachmentList(groupRouteAttachmentList);
						groupRouteDayVO.setGroupRouteOptionsSupplierList(groupRouteOptionsSupplierList);
						// groupRouteDayVO
						// .setGroupRouteScenicSupplierList(groupRouteScenicSupplierList);
						groupRouteDayVO.setGroupRouteTrafficList(groupRouteTrafficList);

					} catch (Exception e) {
						baseStateResult.setError("行程转换错误!");
						return baseStateResult;
					}
					groupRouteDayVOList.add(groupRouteDayVO);

				}

			}
			groupRouteVO.setGroupRouteDayVOList(groupRouteDayVOList);

			mergeGroupOrderVO2.setGroupRouteVO(groupRouteVO);
		}

		// -------------------------------------------------------------------------------

		groupOrderService.mergeGroup(result, mergeGroupDTO.getBizId(), mergeGroupDTO.getUserId(),
				mergeGroupDTO.getUserName(), mergeGroupDTO.getBizCode());

		baseStateResult.setSuccess(true);
		return baseStateResult;
	}

	@Override
	public BaseStateResult secMergeGroup(Integer groupId, String ids) {
		List<GroupOrder> glist = groupOrderService.selectOrderByGroupId(groupId);
		List<String> datelist = new ArrayList<String>();
		List<Integer> productlist = new ArrayList<Integer>();
		List<Integer> statelist = new ArrayList<Integer>();
		if (glist != null && glist.size() > 0) {
			datelist.add(glist.get(0).getDepartureDate());
		}
		String[] split = ids.split(",");
		for (String id : split) {
			datelist.add(groupOrderService.findById(Integer.parseInt(id)).getDepartureDate());
			productlist.add(groupOrderService.findById(Integer.parseInt(id)).getProductId());
			statelist.add(groupOrderService.findById(Integer.parseInt(id)).getStateFinance());
		}

		// if (!Utils.hasSame(datelist)) {
		// return errorJson("发团日期一致的订单才允许添加!");
		// }
		// if (!Utils.hasSame(productlist)) {
		// return errorJson("产品一致的订单才允许并团!");
		// }

		for (String str : split) {
			tourGroupService.addFitOrder(groupId, Integer.parseInt(str));
		}

		return new BaseStateResult(true, null);
	}

	@Override
	public BaseStateResult beforeInsertGroup(String ids) {

		BaseStateResult result = new BaseStateResult();
		result.setSuccess(false);

		String[] split = ids.split(",");
		List<String> datelist = new ArrayList<String>();

		// List<Integer> productlist = new ArrayList<Integer>();
		// List<Integer> brandlist = new ArrayList<Integer>();
		// List<Integer> statelist = new ArrayList<Integer>();

		for (String id : split) {
			GroupOrder groupOrder = groupOrderService.findById(Integer.parseInt(id));
			datelist.add(groupOrder.getDepartureDate());
			// productlist.add(groupOrder.getProductId());
			// statelist.add(groupOrder.getStateFinance());
			// brandlist.add(groupOrder.getProductBrandId());
			List<GroupOrderGuest> guestList = groupOrderGuestService.selectByOrderId(Integer.parseInt(id));
			if (guestList == null || guestList.size() == 0) {
				result.setError("订单号:" + groupOrder.getOrderNo() + "无客人信息,无法并团!");
				return result;
			}

		}

		if (!MergeGroupUtils.hasSame(datelist)) {
			result.setError("发团日期一致的订单才允许加入到团中!");
			return result;
		}

		result.setSuccess(true);
		return result;
	}

	@Override
	public BaseStateResult judgeMergeGroup(String ids) {

		BaseStateResult result = new BaseStateResult();
		result.setSuccess(false);

		String[] split = ids.split(",");
		List<String> datelist = new ArrayList<String>();
		List<Integer> productlist = new ArrayList<Integer>();
		List<Integer> brandlist = new ArrayList<Integer>();
		List<Integer> statelist = new ArrayList<Integer>();
		for (String id : split) {
			GroupOrder groupOrder = groupOrderService.findById(Integer.parseInt(id));
			datelist.add(groupOrder.getDepartureDate());
			productlist.add(groupOrder.getProductId());
			statelist.add(groupOrder.getStateFinance());
			brandlist.add(groupOrder.getProductBrandId());
			List<GroupOrderGuest> guestList = groupOrderGuestService.selectByOrderId(Integer.parseInt(id));
			if (guestList == null || guestList.size() == 0) {
				result.setError("订单号:" + groupOrder.getOrderNo() + "无客人信息,无法并团!");
				return result;
			}

		}

		if (!MergeGroupUtils.hasSame(datelist)) {
			result.setError("发团日期一致的订单才允许并团!");
			return result;
		}

		// if (!MergeGroupUtils.hasSame(productlist)) {
		// return errorJson("产品一致的订单才允许并团!");
		// }

		if (!MergeGroupUtils.hasSame(brandlist)) {
			result.setError("产品品牌一致的订单才允许并团!");
			return result;
		}

		result.setSuccess(true);
		return result;
	}

	@Override
	public ToMergeGroupResult toMergeGroup(String ids) {

		List<GroupOrder> list = new ArrayList<GroupOrder>();
		String[] split = ids.split(",");
		for (String str : split) {
			GroupOrder groupOrder = groupOrderService.findById(Integer.parseInt(str));
			groupOrder.setGroupOrderGuestList((groupOrderGuestService.selectByOrderId(groupOrder.getId())));
			list.add(groupOrder);
		}

		ToMergeGroupResult result = new ToMergeGroupResult();
		result.setList(list);
		return result;
	}

	@Override
	public ToNotGroupListResult toNotGroupList(ToNotGroupListDTO toNotGroupListDTO) throws ParseException {

		ToNotGroupListResult returnResult = new ToNotGroupListResult();

		GroupOrder groupOrder = toNotGroupListDTO.getGroupOrder();
		Integer bizId = toNotGroupListDTO.getBizId();
		Set<Integer> userIdSet = toNotGroupListDTO.getUserIdSet();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (groupOrder.getReqType() != null && groupOrder.getReqType() == 0) {
			groupOrder.setDateType(1);
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			c.set(year, month, 1);
			groupOrder.setDepartureDate(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-01");
			c.set(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			groupOrder
					.setEndTime(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE));
		}

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(groupOrder.getPageSize() == null ? Constants.PAGESIZE : groupOrder.getPageSize());
		pageBean.setPage(groupOrder.getPage());
		pageBean.setParameter(groupOrder);

		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(groupOrder.getSaleOperatorIds()) && StringUtils.isNotBlank(groupOrder.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = groupOrder.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				groupOrder.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}
		// model.addAttribute("groupOrder", groupOrder);
		returnResult.setGroupOrder(groupOrder);

		if (groupOrder.getDateType() != null && groupOrder.getDateType() == 2) {
			if (groupOrder.getDepartureDate() != null) {
				groupOrder.setDepartureDate(sdf.parse(groupOrder.getDepartureDate()).getTime() + "");
			}
			if (groupOrder.getEndTime() != null) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(sdf.parse(groupOrder.getEndTime()));
				calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
				groupOrder.setEndTime(calendar.getTime().getTime() + "");
			}

		}

		pageBean = groupOrderService.selectNotGroupListPage(pageBean, bizId, userIdSet);

		GroupOrder order = groupOrderService.selectTotalNotGroup(groupOrder, bizId, userIdSet);

		if (groupOrder.getDateType() != null && groupOrder.getDateType() == 2) {
			if (groupOrder.getDepartureDate() != null) {
				groupOrder.setDepartureDate(sdf.format(new Date(Long.valueOf(groupOrder.getDepartureDate()))));
			}
			if (groupOrder.getEndTime() != null) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTimeInMillis(Long.valueOf(groupOrder.getEndTime()));
				calendar.add(calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
				groupOrder.setEndTime(sdf.format(calendar.getTime()));
			}
		}

		// model.addAttribute("totalAudit",order == null ? 0 :
		// order.getNumAdult());
		// model.addAttribute("totalChild",order == null ? 0 :
		// order.getNumChild());
		// model.addAttribute("total", order == null ? 0 : order.getTotal());
		returnResult.setTotalAudit(order == null ? 0 : order.getNumAdult());
		returnResult.setTotalChild(order == null ? 0 : order.getNumChild());
		returnResult.setTotal(order.getTotal());

		List<GroupOrder> result = pageBean.getResult();
		Integer pageTotalAudit = 0;
		Integer pageTotalChild = 0;
		BigDecimal pageTotal = new BigDecimal(0);

		for (GroupOrder groupOrder2 : result) {
			List<GroupRoute> list = groupRouteService.selectByOrderId(groupOrder2.getId());
			if (list != null && list.size() > 0) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(sdf.parse(groupOrder2.getDepartureDate()));
				cal.add(Calendar.DAY_OF_MONTH, +(list.size() - 1));
				Date time = cal.getTime();
				groupOrder2.setFitDate(sdf.format(time));
			}
			pageTotalAudit += groupOrder2.getNumAdult();
			pageTotalChild += groupOrder2.getNumChild();
			pageTotal = pageTotal.add(groupOrder2.getTotal() == null ? new BigDecimal(0) : groupOrder2.getTotal());

			if (groupOrder2.getCreateTime() != null) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Long createTime = groupOrder2.getCreateTime();
				String dateStr = sf.format(createTime);
				groupOrder2.setCreateTimeStr(dateStr);
			}

		}
		// model.addAttribute("pageTotalAudit", pageTotalAudit);
		// model.addAttribute("pageTotalChild", pageTotalChild);
		// model.addAttribute("pageTotal", pageTotal);
		returnResult.setPageTotalAudit(pageTotalAudit);
		returnResult.setPageTotalChild(pageTotalChild);
		returnResult.setPageTotal(pageTotal);

		List<DicInfo> pp = dicService.getListByTypeCode(BasicConstants.CPXL_PP, bizId);

		// model.addAttribute("pp", pp);
		// model.addAttribute("page", pageBean);
		returnResult.setPp(pp);
		returnResult.setPageBean(pageBean);

		List<RegionInfo> allProvince = regionService.getAllProvince();
		// model.addAttribute("allProvince", allProvince);
		returnResult.setAllProvince(allProvince);

		List<RegionInfo> cityList = null;
		if (groupOrder.getProvinceId() != null && groupOrder.getProvinceId() != -1) {
			cityList = regionService.getRegionById(groupOrder.getProvinceId() + "");
		}
		// model.addAttribute("allCity", cityList);
		// Integer bizId = WebUtils.getCurBizId(request);
		// model.addAttribute("orgJsonStr",
		// orgService.getComponentOrgTreeJsonStr(bizId));
		// model.addAttribute("orgUserJsonStr",
		// sysPlatformEmployeeFacade.getComponentOrgUserTreeJsonStr(bizId));
		returnResult.setCityList(cityList);
		returnResult.setOrgJsonStr(orgService.getComponentOrgTreeJsonStr(bizId));
		returnResult.setOrgUserJsonStr(platformEmployeeService.getComponentOrgUserTreeJsonStr(bizId));

		return returnResult;
	}

	@Override
	public ToSecImpNotGroupListResult toSecImpNotGroupList(ToSecImpNotGroupListDTO toSecImpNotGroupListDTO) {

		GroupOrder groupOrder = toSecImpNotGroupListDTO.getGroupOrder();
		Integer bizId = toSecImpNotGroupListDTO.getCurBizId();
		Set<Integer> userIdSet = toSecImpNotGroupListDTO.getUserIdSet();

		if (groupOrder.getReqType() != null && groupOrder.getReqType() == 0) {
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			c.set(year, month, 1);
			groupOrder.setDepartureDate(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-01");
			c.set(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			groupOrder
					.setEndTime(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE));
			groupOrder.setDateType(1);
		}

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(groupOrder.getPageSize() == null ? Constants.PAGESIZE : groupOrder.getPageSize());
		pageBean.setPage(groupOrder.getPage());
		pageBean.setParameter(groupOrder);
		pageBean = groupOrderService.selectNotGroupListPage(pageBean, bizId, userIdSet);
		List<DicInfo> pp = dicService.getListByTypeCode(BasicConstants.CPXL_PP, bizId);

		ToSecImpNotGroupListResult result = new ToSecImpNotGroupListResult();

		result.setGroupOrder(groupOrder);
		result.setPageBean(pageBean);
		result.setPp(pp);

		return result;
	}

	@Override
	public ToImpNotGroupListResult toImpNotGroupList(ToImpNotGroupListDTO toImpNotGroupListDTO) {

		GroupOrder groupOrder = toImpNotGroupListDTO.getGroupOrder();

		if (null == groupOrder.getEndTime() && null == groupOrder.getDepartureDate()) {
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			c.set(year, month, 1);
			groupOrder.setDepartureDate(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-01");
			c.set(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			groupOrder
					.setEndTime(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE));

		}
		String idLists = toImpNotGroupListDTO.getIdLists();
		String[] split = idLists.split(",");
		List<Integer> intIds = new ArrayList<Integer>();
		for (String id : split) {
			intIds.add(Integer.parseInt(id));
		}
		groupOrder.setIdList(intIds);
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(groupOrder.getPageSize() == null ? Constants.PAGESIZE : groupOrder.getPageSize());
		pageBean.setPage(groupOrder.getPage());
		pageBean.setParameter(groupOrder);
		pageBean = groupOrderService.selectNotGroupListPage(pageBean, toImpNotGroupListDTO.getBizId(),
				toImpNotGroupListDTO.getUserIdSet());
		List<DicInfo> pp = dicService.getListByTypeCode(BasicConstants.CPXL_PP, toImpNotGroupListDTO.getBizId());

		ToImpNotGroupListResult result = new ToImpNotGroupListResult();
		result.setGroupOrder(groupOrder);
		result.setPageBean(pageBean);
		result.setPp(pp);
		return result;
	}

	@Override
	public BaseStateResult delGroupOrder(Integer curBizId, Integer id) {

		BaseStateResult result = new BaseStateResult();
		result.setSuccess(false);

		if (airTicketRequestService.doesOrderhaveRequested(curBizId, id)) {
			result.setError("删除订单前请先取消机票申请。");
			return result;
		}

		GroupOrder groupOrder = groupOrderService.findById(id);
		groupOrder.setState(-1);
		groupOrderService.updateGroupOrder(groupOrder);
		if (groupOrder.getPriceId() != null) {

			boolean updateStock = productGroupPriceService.updateStock(groupOrder.getPriceId(),
					groupOrder.getSupplierId(), -(groupOrder.getNumAdult() + groupOrder.getNumChild()));
		}
		bookingSupplierService.upateGroupIdAfterDelOrderFromGroup(id);

		result.setSuccess(true);
		return result;
	}

	@Override
	public BaseStateResult addGroupOrder(AddGroupOrderDTO addGroupOrderDTO) {

		BaseStateResult result = new BaseStateResult();
		result.setSuccess(false);

		GroupOrderVO groupOrderVO = addGroupOrderDTO.getGroupOrderVO();
		Integer priceId = addGroupOrderDTO.getPriceId();
		Integer priceGroupId = addGroupOrderDTO.getPriceGroupId();

		ProductRouteVo productRouteVo = productRouteService
				.findByProductId(groupOrderVO.getGroupOrder().getProductId());
		GroupRouteVO groupRouteVO = new GroupRouteVO();
		// -----------------------------------------------------
		List<GroupRouteDayVO> groupRouteDayVOList = new ArrayList<GroupRouteDayVO>();
		List<ProductRouteDayVo> productRoteDayVoList = productRouteVo.getProductRoteDayVoList();
		// -----------------------------------------------------
		if (productRoteDayVoList != null && productRoteDayVoList.size() > 0) {
			for (int i = 0; i < productRoteDayVoList.size(); i++) {
				ProductRoute productRoute = productRoteDayVoList.get(i).getProductRoute();
				GroupRoute groupRoute = new GroupRoute();
				GroupRouteDayVO groupRouteDayVO = new GroupRouteDayVO();
				try {
					BeanUtils.copyProperties(groupRoute, productRoute);
					groupRoute.setId(null);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					cal.setTime(sdf.parse(groupOrderVO.getGroupOrder().getDepartureDate()));
					cal.add(Calendar.DAY_OF_MONTH, +(i + 1));
					Date time = cal.getTime();
					groupRoute.setGroupDate(time);
					List<ProductAttachment> productAttachmentsList = productRoteDayVoList.get(i)
							.getProductAttachments();
					List<GroupRouteAttachment> groupRouteAttachmentList = new ArrayList<GroupRouteAttachment>();
					if (productAttachmentsList != null && productAttachmentsList.size() > 0) {
						GroupRouteAttachment groupRouteAttachment = new GroupRouteAttachment();
						for (ProductAttachment productAttachment : productAttachmentsList) {
							BeanUtils.copyProperties(groupRouteAttachment, productAttachment);
							groupRouteAttachment.setId(null);
							groupRouteAttachmentList.add(groupRouteAttachment);
						}
					}

					List<ProductRouteTraffic> productRouteTrafficList = productRoteDayVoList.get(i)
							.getProductRouteTrafficList();
					List<GroupRouteTraffic> groupRouteTrafficList = new ArrayList<GroupRouteTraffic>();
					if (productRouteTrafficList != null && productRouteTrafficList.size() > 0) {
						for (ProductRouteTraffic productRouteTraffic : productRouteTrafficList) {
							GroupRouteTraffic groupRouteTraffic = new GroupRouteTraffic();
							BeanUtils.copyProperties(groupRouteTraffic, productRouteTraffic);
							groupRouteTraffic.setId(null);
							groupRouteTrafficList.add(groupRouteTraffic);

						}
					}

					// List<GroupRouteSupplier> groupRouteScenicSupplierList =
					// new ArrayList<GroupRouteSupplier>();
					// List<ProductRouteSupplier> productScenicSupplierList =
					// productRoteDayVoList
					// .get(i).getProductScenicSupplierList();
					// if (productScenicSupplierList != null
					// && productScenicSupplierList.size() > 0) {
					// for (ProductRouteSupplier productRouteScenicSupplier :
					// productScenicSupplierList) {
					// GroupRouteSupplier groupRouteScenicSupplier = new
					// GroupRouteSupplier();
					// BeanUtils.copyProperties(groupRouteScenicSupplier,
					// productRouteScenicSupplier);
					// groupRouteScenicSupplier.setId(null);
					// groupRouteScenicSupplierList
					// .add(groupRouteScenicSupplier);
					// }
					// }

					List<GroupRouteSupplier> groupRouteOptionsSupplierList = new ArrayList<GroupRouteSupplier>();
					List<ProductRouteSupplier> productOptionsSupplierList = productRoteDayVoList.get(i)
							.getProductOptionsSupplierList();
					if (productOptionsSupplierList != null && productOptionsSupplierList.size() > 0) {
						for (ProductRouteSupplier productRouteSupplier : productOptionsSupplierList) {
							GroupRouteSupplier groupRouteOptionsSupplier = new GroupRouteSupplier();
							BeanUtils.copyProperties(groupRouteOptionsSupplier, productRouteSupplier);
							groupRouteOptionsSupplier.setId(null);
							groupRouteOptionsSupplierList.add(groupRouteOptionsSupplier);
						}
					}
					groupRouteDayVO.setGroupRoute(groupRoute);
					groupRouteDayVO.setGroupRouteAttachmentList(groupRouteAttachmentList);
					groupRouteDayVO.setGroupRouteOptionsSupplierList(groupRouteOptionsSupplierList);
					// groupRouteDayVO
					// .setGroupRouteScenicSupplierList(groupRouteScenicSupplierList);

				} catch (Exception e) {
					result.setError("行程转换错误!");
					return result;
				}
				groupRouteDayVOList.add(groupRouteDayVO);

			}

		}
		groupRouteVO.setGroupRouteDayVOList(groupRouteDayVOList);

		List<GroupOrderGuest> audlt = new ArrayList<GroupOrderGuest>();
		List<GroupOrderGuest> child = new ArrayList<GroupOrderGuest>();
		int isSingle = 0;
		List<GroupOrderGuest> groupOrderGuestList2 = groupOrderVO.getGroupOrderGuestList();
		List<GroupOrderPrice> insertList = new ArrayList<GroupOrderPrice>();
		int totalIncome = 0;
		if (groupOrderGuestList2 != null && groupOrderGuestList2.size() > 0) {
			for (GroupOrderGuest groupOrderGuest : groupOrderGuestList2) {

				if (groupOrderGuest.getIsSingleRoom() == 1) {
					isSingle++;
				}
				if (groupOrderGuest.getType() == 1) {
					audlt.add(groupOrderGuest);
				} else {
					child.add(groupOrderGuest);
				}

			}
			ProductGroupPrice productGroupPrice = productGroupPriceService.selectByPrimaryKey(priceId).getGroupPrice();

			// ----------------------收入----------------------

			GroupOrderPrice audltIn = new GroupOrderPrice();
			audltIn.setCreateTime(System.currentTimeMillis());
			audltIn.setCreatorId(addGroupOrderDTO.getUserId());
			audltIn.setCreatorName(addGroupOrderDTO.getUserName());
			audltIn.setMode(0);
			audltIn.setNumPerson(Double.valueOf(audlt.size() + ""));
			audltIn.setUnitPrice(productGroupPrice.getPriceSettlementAdult().doubleValue());
			audltIn.setNumTimes(1.0);
			totalIncome += audlt.size() * productGroupPrice.getPriceSettlementAdult().doubleValue() * 1;
			audltIn.setTotalPrice(audlt.size() * productGroupPrice.getPriceSettlementAdult().doubleValue() * 1);
			DicInfo dicInfo = dicService.getDicInfoByTypeCodeAndDicCode(BasicConstants.GGXX_LYSFXM, BasicConstants.CR);
			audltIn.setItemId(dicInfo.getId());// 大人
			audltIn.setItemName(dicInfo.getValue());
			if (audlt.size() != 0) {
				insertList.add(audltIn);
			}

			GroupOrderPrice childIn = new GroupOrderPrice();
			childIn.setCreateTime(System.currentTimeMillis());
			childIn.setCreatorId(addGroupOrderDTO.getUserId());
			childIn.setCreatorName(addGroupOrderDTO.getUserName());
			childIn.setMode(0);
			childIn.setNumPerson(Double.valueOf(child.size() + ""));
			childIn.setUnitPrice(productGroupPrice.getPriceSettlementChild().doubleValue());
			childIn.setNumTimes(1.0);
			totalIncome += child.size() * productGroupPrice.getPriceSettlementChild().doubleValue() * 1;
			childIn.setTotalPrice(child.size() * productGroupPrice.getPriceSettlementChild().doubleValue() * 1);
			dicInfo = dicService.getDicInfoByTypeCodeAndDicCode(BasicConstants.GGXX_LYSFXM, BasicConstants.ERT);
			childIn.setItemId(dicInfo.getId());// 小孩
			childIn.setItemName(dicInfo.getValue());
			if (child.size() != 0) {
				insertList.add(childIn);
			}
			if (isSingle > 0) {
				GroupOrderPrice roomIn = new GroupOrderPrice();
				roomIn.setCreateTime(System.currentTimeMillis());
				roomIn.setCreatorId(addGroupOrderDTO.getUserId());
				roomIn.setCreatorName(addGroupOrderDTO.getUserName());
				roomIn.setMode(0);
				roomIn.setNumPerson(Double.valueOf(isSingle + ""));
				roomIn.setUnitPrice(productGroupPrice.getPriceSettlementRoomeSpread().doubleValue());
				roomIn.setNumTimes(1.0);
				totalIncome += isSingle * productGroupPrice.getPriceSettlementRoomeSpread().doubleValue() * 1;
				roomIn.setTotalPrice(isSingle * productGroupPrice.getPriceSettlementRoomeSpread().doubleValue() * 1);
				dicInfo = dicService.getDicInfoByTypeCodeAndDicCode(BasicConstants.GGXX_LYSFXM, BasicConstants.DFC);
				roomIn.setItemId(dicInfo.getId());
				roomIn.setItemName(dicInfo.getValue()); // 单房差
				insertList.add(roomIn);
			}

			// -------------------成本------------------------

			GroupOrderPrice audltOut = new GroupOrderPrice();
			audltOut.setCreateTime(System.currentTimeMillis());
			audltOut.setCreatorId(addGroupOrderDTO.getUserId());
			audltOut.setCreatorName(addGroupOrderDTO.getUserName());
			audltOut.setMode(1); // 成本
			audltOut.setNumPerson(Double.valueOf(audlt.size() + ""));
			audltOut.setUnitPrice(productGroupPrice.getPriceCostAdult().doubleValue());
			audltOut.setNumTimes(1.0);
			audltOut.setTotalPrice(audlt.size() * productGroupPrice.getPriceCostAdult().doubleValue() * 1);
			dicInfo = dicService.getDicInfoByTypeCodeAndDicCode(BasicConstants.GGXX_LYSFXM, BasicConstants.CR);
			audltOut.setItemId(dicInfo.getId());
			audltOut.setItemName(dicInfo.getValue());
			if (audlt.size() != 0) {
				insertList.add(audltOut);
			}

			GroupOrderPrice childOut = new GroupOrderPrice();
			childOut.setCreateTime(System.currentTimeMillis());
			childOut.setCreatorId(addGroupOrderDTO.getUserId());
			childOut.setCreatorName(addGroupOrderDTO.getUserName());
			childOut.setMode(1); // 成本
			childOut.setNumPerson(Double.valueOf(child.size() + ""));
			childOut.setUnitPrice(productGroupPrice.getPriceCostChild().doubleValue());
			childOut.setNumTimes(1.0);
			childOut.setTotalPrice(child.size() * productGroupPrice.getPriceCostChild().doubleValue() * 1);
			dicInfo = dicService.getDicInfoByTypeCodeAndDicCode(BasicConstants.GGXX_LYSFXM, BasicConstants.ERT);
			childOut.setItemId(dicInfo.getId());
			childOut.setItemName(dicInfo.getValue());
			if (child.size() != 0) {
				insertList.add(childOut);
			}

			if (isSingle > 0) {
				GroupOrderPrice roomIn = new GroupOrderPrice();
				roomIn.setCreateTime(System.currentTimeMillis());
				roomIn.setCreatorId(addGroupOrderDTO.getUserId());
				roomIn.setCreatorName(addGroupOrderDTO.getUserName());
				roomIn.setMode(1);
				roomIn.setNumPerson(Double.valueOf(isSingle + ""));
				roomIn.setUnitPrice(productGroupPrice.getPriceCostRoomSpread().doubleValue());
				roomIn.setNumTimes(1.0);
				roomIn.setTotalPrice(isSingle * productGroupPrice.getPriceCostRoomSpread().doubleValue() * 1);
				dicInfo = dicService.getDicInfoByTypeCodeAndDicCode(BasicConstants.GGXX_LYSFXM, BasicConstants.DFC);
				roomIn.setItemId(dicInfo.getId());
				roomIn.setItemName(dicInfo.getValue()); // 单房差
				insertList.add(roomIn);
			}

		}
		List<GroupOrderPrice> groupOrderPriceList = groupOrderVO.getGroupOrderPriceList();
		if (groupOrderPriceList != null && groupOrderPriceList.size() > 0) {
			for (GroupOrderPrice groupOrderPrice : groupOrderPriceList) {
				totalIncome += groupOrderPrice.getUnitPrice() * groupOrderPrice.getNumPerson()
						* groupOrderPrice.getNumTimes();
			}
		}
		groupOrderVO.getGroupOrder().setTotal(new BigDecimal(totalIncome));

		// modified by gejinjun 2105-11-03 防止出现保存出错但是也更新库存的问题，修改为如下逻辑
		try {
			// 修改订单信息
			groupOrderService.saveGroupOrder(groupOrderVO, groupRouteVO, insertList);
			// 更新库存信息
			ProductGroup group = productGroupService.getGroupInfoById(priceGroupId);
			productGroupPriceService.updateStock(priceId,
					group.getGroupSetting() == 0 ? groupOrderVO.getGroupOrder().getSupplierId() : null,
					groupOrderVO.getGroupOrderGuestList().size());
		} catch (Exception ex) {
			result.setError(ex.getMessage());
			return result;
		}

		result.setSuccess(true);
		return result;
	}

	@Override
	public ToLookGroupOrderResult toLookGroupOrder(Integer id, Integer bizId) {

		ToLookGroupOrderResult result = new ToLookGroupOrderResult();

		GroupOrder groupOrder = groupOrderService.findById(id);
		groupOrder.setStateFinance(1);
		// model.addAttribute("groupOrder", groupOrder);
		result.setGroupOrder(groupOrder);

		PlatformEmployeePo saleEmployeePo = platformEmployeeService.findByEmployeeId(groupOrder.getSaleOperatorId());
		// model.addAttribute("saleEmployeePo", saleEmployeePo);
		result.setSaleEmployeePo(saleEmployeePo);

		PlatformEmployeePo operaEmployeePo = platformEmployeeService.findByEmployeeId(groupOrder.getOperatorId());
		// model.addAttribute("operaEmployeePo", operaEmployeePo);
		result.setOperaEmployeePo(operaEmployeePo);

		ProductInfo productInfo = productInfoService.findProductInfoById(groupOrder.getProductId());
		// model.addAttribute("productInfo", productInfo);
		result.setProductInfo(productInfo);

		SupplierInfo supplierInfo = supplierService.selectBySupplierId(groupOrder.getSupplierId());
		// model.addAttribute("supplierInfo", supplierInfo);
		result.setSupplierInfo(supplierInfo);

		List<DicInfo> jdxjList = dicService.getListByTypeCode(BasicConstants.GYXX_JDXJ);
		// model.addAttribute("jdxjList", jdxjList);
		result.setJdxjList(jdxjList);

		List<GroupOrderPrice> costList = groupOrderPriceService.selectByOrderAndType(groupOrder.getId(), 0); // mode
																												// 0：收入

		Double income = 0.0;
		if (costList != null && costList.size() > 0) {
			for (GroupOrderPrice groupOrderPrice : costList) {
				income += groupOrderPrice.getTotalPrice();
			}
		}
		// model.addAttribute("income", income);
		result.setIncome(income);

		List<GroupOrderPrice> budgetList = groupOrderPriceService.selectByOrderAndType(groupOrder.getId(), 1);// 1：预算

		Double budget = 0.0;
		if (budgetList != null && budgetList.size() > 0) {
			for (GroupOrderPrice groupOrderPrice : budgetList) {
				budget += groupOrderPrice.getTotalPrice();
			}
		}
		// model.addAttribute("budget", budget);
		result.setBudget(budget);

		// model.addAttribute("costList", costList);
		// model.addAttribute("budgetList", budgetList);
		result.setCostList(costList);
		result.setBudgetList(budgetList);

		List<GroupOrderTransport> transportList = groupOrderTransportService.selectByOrderId(groupOrder.getId());
		// model.addAttribute("transportList", transportList);
		result.setTransportList(transportList);

		List<DicInfo> jtfsList = dicService.getListByTypeCode(BasicConstants.GYXX_JTFS, bizId);
		// model.addAttribute("jtfsList", jtfsList);
		result.setJtfsList(jtfsList);

		List<DicInfo> zjlxList = dicService.getListByTypeCode(BasicConstants.GYXX_ZJLX);
		// model.addAttribute("zjlxList", zjlxList);
		result.setZjlxList(zjlxList);

		List<DicInfo> lysfxmList = dicService.getListByTypeCode(BasicConstants.GYXX_LYSFXM, bizId);
		// model.addAttribute("lysfxmList", lysfxmList);
		result.setLysfxmList(lysfxmList);

		List<GroupRequirement> restaurantList = groupRequirementService.selectByOrderAndType(groupOrder.getId(),
				Constants.HOTEL);// 酒店
		List<GroupRequirement> airticketagentList = groupRequirementService.selectByOrderAndType(groupOrder.getId(),
				Constants.AIRTICKETAGENT); // 机票
		List<GroupRequirement> trainticketagentList = groupRequirementService.selectByOrderAndType(groupOrder.getId(),
				Constants.TRAINTICKETAGENT);// 火车票

		// model.addAttribute("restaurantList", restaurantList);
		// model.addAttribute("airticketagentList", airticketagentList);
		// model.addAttribute("trainticketagentList", trainticketagentList);
		result.setRestaurantList(restaurantList);
		result.setAirticketagentList(airticketagentList);
		result.setTrainticketagentList(trainticketagentList);

		List<GroupOrderGuest> guestList = groupOrderGuestService.selectByOrderId(groupOrder.getId());
		for (GroupOrderGuest guest : guestList) {
			List<GroupOrderGuest> guests = groupOrderGuestService
					.getGuestByGuestCertificateNum(guest.getCertificateNum(), guest.getOrderId());
			guest.setHistoryNum(guests.size());
		}
		// model.addAttribute("guestList", guestList);
		result.setGuestList(guestList);

		return result;
	}

	@Override
	public ToEditGroupOrderResult toEditGroupOrder(Integer id, int bizId) {

		ToEditGroupOrderResult result = new ToEditGroupOrderResult();

		GroupOrder groupOrder = groupOrderService.findById(id);
		// model.addAttribute("groupOrder", groupOrder);
		result.setGroupOrder(groupOrder);

		ProductInfo productInfo = productInfoService.findProductInfoById(groupOrder.getProductId());
		// model.addAttribute("productInfo", productInfo);
		result.setProductInfo(productInfo);

		PlatformEmployeePo saleEmployeePo = platformEmployeeService.findByEmployeeId(groupOrder.getSaleOperatorId());
		// model.addAttribute("saleEmployeePo", saleEmployeePo);
		result.setSaleEmployeePo(saleEmployeePo);

		PlatformEmployeePo operaEmployeePo = platformEmployeeService.findByEmployeeId(groupOrder.getOperatorId());
		// model.addAttribute("operaEmployeePo", operaEmployeePo);
		result.setOperaEmployeePo(operaEmployeePo);

		SupplierInfo supplierInfo = supplierService.selectBySupplierId(groupOrder.getSupplierId());
		// model.addAttribute("supplierInfo", supplierInfo);
		result.setSupplierInfo(supplierInfo);

		List<DicInfo> jdxjList = dicService.getListByTypeCode(BasicConstants.GYXX_JDXJ);
		// model.addAttribute("jdxjList", jdxjList);
		result.setJdxjList(jdxjList);

		List<DicInfo> sourceTypeList = dicService.getListByTypeCode(Constants.GUEST_SOURCE_TYPE, bizId);
		// model.addAttribute("sourceTypeList", sourceTypeList);
		result.setSourceTypeList(sourceTypeList);

		List<RegionInfo> allProvince = regionService.getAllProvince();
		// model.addAttribute("allProvince", allProvince);
		result.setAllProvince(allProvince);

		List<RegionInfo> cityList = null;
		if (groupOrder.getProvinceId() != null && groupOrder.getProvinceId() != -1) {
			cityList = regionService.getRegionById(groupOrder.getProvinceId() + "");
		}
		// model.addAttribute("allCity", cityList);
		result.setCityList(cityList);

		List<GroupOrderPrice> costList = groupOrderPriceService.selectByOrderAndType(groupOrder.getId(), 0); // mode
																												// 0：收入
		Double income = 0.0;
		if (costList != null && costList.size() > 0) {

			for (GroupOrderPrice groupOrderPrice : costList) {
				income += groupOrderPrice.getTotalPrice();
			}

		}
		// model.addAttribute("income", income);
		result.setIncome(income);

		List<GroupOrderPrice> budgetList = groupOrderPriceService.selectByOrderAndType(groupOrder.getId(), 1);// 1：预算

		Double budget = 0.0;
		if (budgetList != null && budgetList.size() > 0) {
			for (GroupOrderPrice groupOrderPrice : budgetList) {
				budget += groupOrderPrice.getTotalPrice();
			}
		}
		// model.addAttribute("budget", budget);
		result.setBudget(budget);

		// model.addAttribute("costList", costList);
		// model.addAttribute("budgetList", budgetList);
		result.setCostList(costList);
		result.setBudgetList(budgetList);

		List<GroupOrderTransport> transportList = groupOrderTransportService.selectByOrderId(groupOrder.getId());
		// model.addAttribute("transportList", transportList);
		result.setTransportList(transportList);

		List<DicInfo> jtfsList = dicService.getListByTypeCode(BasicConstants.GYXX_JTFS, bizId);
		// model.addAttribute("jtfsList", jtfsList);
		result.setJtfsList(jtfsList);

		List<DicInfo> zjlxList = dicService.getListByTypeCode(BasicConstants.GYXX_ZJLX);
		// model.addAttribute("zjlxList", zjlxList);
		result.setZjlxList(zjlxList);

		List<DicInfo> lysfxmList = dicService.getListByTypeCode(BasicConstants.GYXX_LYSFXM, bizId);
		// model.addAttribute("lysfxmList", lysfxmList);
		result.setLysfxmList(lysfxmList);

		List<GroupRequirement> restaurantList = groupRequirementService.selectByOrderAndType(groupOrder.getId(),
				Constants.HOTEL);// 酒店
		List<GroupRequirement> airticketagentList = groupRequirementService.selectByOrderAndType(groupOrder.getId(),
				Constants.AIRTICKETAGENT); // 机票
		List<GroupRequirement> trainticketagentList = groupRequirementService.selectByOrderAndType(groupOrder.getId(),
				Constants.TRAINTICKETAGENT);// 火车票

		// model.addAttribute("restaurantList", restaurantList);
		// model.addAttribute("airticketagentList", airticketagentList);
		// model.addAttribute("trainticketagentList", trainticketagentList);
		result.setRestaurantList(restaurantList);
		result.setAirticketagentList(airticketagentList);
		result.setTrainticketagentList(trainticketagentList);

		List<GroupOrderGuest> guestList = groupOrderGuestService.selectByOrderId(groupOrder.getId());
		List<Integer> guestIdList = airTicketRequestService.findIssuedGuestIdList(bizId, groupOrder.getId());
		for (GroupOrderGuest guest : guestList) {
			List<GroupOrderGuest> guests = groupOrderGuestService
					.getGuestByGuestCertificateNum(guest.getCertificateNum(), guest.getOrderId());
			guest.setHistoryNum(guests.size());
			guest.setEditType(!guestIdList.contains(guest.getId()));
		}
		// model.addAttribute("guestList", guestList);
		result.setGuestList(guestList);

		ProductGroupPrice productGroupPrice = productGroupPriceService.selectByPrimaryKey(groupOrder.getPriceId())
				.getGroupPrice();
		if (productGroupPrice != null) {

			ProductGroup groductGroup = productGroupService.getGroupInfoById(productGroupPrice.getGroupId());

			if (groductGroup.getGroupSetting() == 0) {

				ProductGroupSupplierVo supplierVosToSales = productGroupSupplierService
						.selectProductGroupSupplierVosToSales(productGroupPrice.getGroupId(), groupOrder.getPriceId(),
								groupOrder.getSupplierId());
				if (supplierVosToSales == null) {
					// model.addAttribute(
					// "allowNum",
					// productGroupPrice.getStockCount()
					// - productGroupPrice.getReceiveCount());
					result.setAllowNum(productGroupPrice.getStockCount() - productGroupPrice.getReceiveCount());
				} else {
					if (supplierVosToSales.getStock() == -1) {
						// model.addAttribute("allowNum",
						// productGroupPrice.getStockCount()-productGroupPrice.getReceiveCount());
						result.setAllowNum(productGroupPrice.getStockCount() - productGroupPrice.getReceiveCount());
					} else {
						// model.addAttribute("allowNum",supplierVosToSales.getStock());
						result.setAllowNum(supplierVosToSales.getStock());
					}
				}

			} else {
				// model.addAttribute("allowNum",
				// productGroupPrice.getStockCount()-productGroupPrice.getReceiveCount());
				result.setAllowNum(productGroupPrice.getStockCount() - productGroupPrice.getReceiveCount());
			}
		} else {
			// model.addAttribute("allowNum",10000);
			result.setAllowNum(10000);
		}

		return result;
	}

	public BaseStateResult editGroupOrder(Integer id, Integer employeeId, Integer num) {

		PlatformEmployeePo platformEmployeePo = platformEmployeeService.findByEmployeeId(employeeId);

		GroupOrder groupOrder = groupOrderService.findById(id);
		if (num == 1) {
			groupOrder.setSaleOperatorId(employeeId);
			groupOrder.setSaleOperatorName(platformEmployeePo.getName());
		} else {
			groupOrder.setOperatorId(employeeId);
			groupOrder.setOperatorName(platformEmployeePo.getName());
		}
		groupOrderService.updateGroupOrder(groupOrder);

		return new BaseStateResult(true, null);
	}

	public BaseStateResult editSupplierAndReceiveMode(EditSupplierAndReceiveModeDTO editSupplierAndReceiveModeDTO) {

		GroupOrder groupOrder = groupOrderService.findById(editSupplierAndReceiveModeDTO.getOrderId());
		groupOrder.setSupplierCode(editSupplierAndReceiveModeDTO.getSupplierCode());
		groupOrder.setReceiveMode(editSupplierAndReceiveModeDTO.getReceiveMode());
		groupOrderService.updateGroupOrder(groupOrder);

		return new BaseStateResult(true, null);
	}

	public BaseStateResult editGroupOrderText(EditGroupOrderDTO editGroupOrderDTO) {
		GroupOrder groupOrder = editGroupOrderDTO.getGroupOrder();
		groupOrderService.updateGroupOrder(groupOrder);
		return new BaseStateResult(true, null);
	}

	public BaseStateResult editGroupOrderContMan(Integer conId, Integer id) {

		SupplierContactMan man = supplierService.selectSupplierContactManById(conId);
		GroupOrder groupOrder = groupOrderService.findById(id);
		groupOrder.setContactFax(man.getFax());
		groupOrder.setContactName(man.getName());
		groupOrder.setContactTel(man.getTel());
		groupOrder.setContactMobile(man.getMobile());
		groupOrderService.updateGroupOrder(groupOrder);

		return new BaseStateResult(true, null);
	}

	public BaseStateResult editGroupGuest(EditGroupGuestDTO editGroupGuestDTO) {
		
		GroupOrderGuest groupGuest = editGroupGuestDTO.getGroupGuest();

		if (groupGuest.getId() == null) {
			groupGuest.setCreateTime(System.currentTimeMillis());
			groupOrderGuestService.insert(groupGuest);
			GroupOrder groupOrder = groupOrderService.selectByPrimaryKey(groupGuest.getOrderId());

			ProductGroupPrice productGroupPrice = productGroupPriceService.selectByPrimaryKey(groupOrder.getPriceId())
					.getGroupPrice();
			ProductGroup groductGroup = productGroupService.getGroupInfoById(productGroupPrice.getGroupId());
			if (groupOrder.getPriceId() != null) {
				productGroupPriceService.updateStock(groupOrder.getPriceId(),groductGroup.getGroupSetting() == 0 ? groupOrder.getSupplierId() : null, 1);
			}
		} else {
			groupOrderGuestService.updateByPrimaryKeySelective(groupGuest);
		}

		return new BaseStateResult(true, null);
	}

	public ToEditGroupGuestResult toEditGroupGuest(Integer id) {

		ToEditGroupGuestResult result = new ToEditGroupGuestResult();

		GroupOrderGuest orderGuest = groupOrderGuestService.selectByPrimaryKey(id);
		result.setOrderGuest(orderGuest);

		return result;
	}

	public BaseStateResult delGroupGuest(Integer id) {

		GroupOrderGuest orderGuest = groupOrderGuestService.selectByPrimaryKey(id);
		GroupOrder groupOrder = groupOrderService.selectByPrimaryKey(orderGuest.getOrderId());

		ProductGroupPrice productGroupPrice = productGroupPriceService.selectByPrimaryKey(groupOrder.getPriceId())
				.getGroupPrice();

		ProductGroup groductGroup = productGroupService.getGroupInfoById(productGroupPrice.getGroupId());

		productGroupPriceService.updateStock(groupOrder.getPriceId(),groductGroup.getGroupSetting() == 0 ? groupOrder.getSupplierId() : null, -1);

		groupOrderGuestService.deleteByPrimaryKey(id);

		return new BaseStateResult(true, null);
	}

	public BaseStateResult addGroupRequirement(AddGroupRequirementDTO addGroupRequirementDTO){
		
		GroupRequirement groupRequirement=addGroupRequirementDTO.getGroupRequirement(); 
		Integer userId=addGroupRequirementDTO.getUserId();
		String userName=addGroupRequirementDTO.getUserName();

		groupRequirement.setCreateTime(System.currentTimeMillis());
		groupRequirement.setCreatorId(userId);
		groupRequirement.setCreatorName(userName);
		groupRequirementService.insertSelective(groupRequirement);

		return new BaseStateResult(true, null);
	}

	public BaseStateResult editGroupRequirement(AddGroupRequirementDTO addGroupRequirementDTO){
		
		GroupRequirement groupRequirement=addGroupRequirementDTO.getGroupRequirement();
		
		groupRequirementService.updateByPrimaryKeySelective(groupRequirement);
		
		return new BaseStateResult(true, null);
	}

	public ToEditGroupRequirementResult toEditGroupRequirement(Integer id) {

		GroupRequirement requirement = groupRequirementService.selectByPrimaryKey(id);

		ToEditGroupRequirementResult result = new ToEditGroupRequirementResult();
		result.setRequirement(requirement);

		return result;
	}

	public BaseStateResult delGroupRequirement(Integer id) {
		groupRequirementService.deleteByPrimaryKey(id);
		return new BaseStateResult(true, null);
	}

	// 接送信息
	public BaseStateResult addManyGroupOrderTransport(AddManyGroupOrderTransportDTO addManyGroupOrderTransportDTO){
		
		TransportVO transportVO=addManyGroupOrderTransportDTO.getTransportVO();

		if (transportVO.getGroupOrderTransportList() != null && transportVO.getGroupOrderTransportList().size() > 0) {
			List<GroupOrderTransport> list = transportVO.getGroupOrderTransportList();
			for (GroupOrderTransport groupOrderTransport2 : list) {
				groupOrderTransport2.setCreateTime(System.currentTimeMillis());
				groupOrderTransportService.insertSelective(groupOrderTransport2);
			}
		}

		return new BaseStateResult(true, null);
	}

	public BaseStateResult addGroupOrderTransport(AddGroupOrderTransportDTO addGroupOrderTransport){
		
		GroupOrderTransport groupOrderTransport=addGroupOrderTransport.getGroupOrderTransport();

		groupOrderTransport.setCreateTime(System.currentTimeMillis());
		groupOrderTransportService.insertSelective(groupOrderTransport);

		return new BaseStateResult(true, null);
	}

	public BaseStateResult editGroupOrderTransport(AddGroupOrderTransportDTO addGroupOrderTransport){
		
		GroupOrderTransport groupOrderTransport=addGroupOrderTransport.getGroupOrderTransport();
		
		groupOrderTransportService.updateByPrimaryKeySelective(groupOrderTransport);
		
		return new BaseStateResult(true, null);
	}

	public ToEditGroupOrderTransportResult toEditGroupOrderTransport(Integer id) {

		GroupOrderTransport orderTransport = groupOrderTransportService.selectByPrimaryKey(id);

		ToEditGroupOrderTransportResult result = new ToEditGroupOrderTransportResult();
		result.setOrderTransport(orderTransport);

		return result;
	}

	public BaseStateResult delGroupOrderTransport(Integer id) {
		
		groupOrderTransportService.deleteByPrimaryKey(id);
		
		return new BaseStateResult(true, null);
	}

	// 价格信息
	public BaseStateResult addGroupOrderPrice(AddGroupOrderPriceDTO addGroupOrderPriceDTO){

		GroupOrderPrice groupOrderPrice=addGroupOrderPriceDTO.getGroupOrderPrice();
		Integer userId=addGroupOrderPriceDTO.getUserId();
		String userName=addGroupOrderPriceDTO.getUserName();

		groupOrderPrice.setCreateTime(System.currentTimeMillis());
		groupOrderPrice.setRowState(0);
		groupOrderPrice.setItemName(dicService.getById(groupOrderPrice.getItemId()).getValue());
		groupOrderPrice.setCreatorId(userId);
		groupOrderPrice.setCreatorName(userName);
		groupOrderPriceService.insertSelective(groupOrderPrice);

		return new BaseStateResult(true, null);
	}

	public BaseStateResult addGroupOrderPriceMany(AddGroupOrderPriceManyDTO addGroupOrderPriceManyDTO){
		
		GroupOrderPriceVO groupOrderPriceVO=addGroupOrderPriceManyDTO.getGroupOrderPriceVO();
		Integer userId=addGroupOrderPriceManyDTO.getUserId();
		String userName=addGroupOrderPriceManyDTO.getUserName();

		List<GroupOrderPrice> groupOrderPriceList = groupOrderPriceVO.getGroupOrderPriceList();
		for (GroupOrderPrice groupOrderPrice : groupOrderPriceList) {
			groupOrderPrice.setCreateTime(System.currentTimeMillis());
			groupOrderPrice.setRowState(0);
			groupOrderPrice.setItemName(dicService.getById(groupOrderPrice.getItemId()).getValue());
			groupOrderPrice.setCreatorId(userId);
			groupOrderPrice.setCreatorName(userName);
			groupOrderPriceService.insertSelective(groupOrderPrice);
		}

		return new BaseStateResult(true, null);
	}

	public BaseStateResult editGroupOrderPrice(AddGroupOrderPriceDTO addGroupOrderPriceDTO){
		
		GroupOrderPrice groupOrderPrice=addGroupOrderPriceDTO.getGroupOrderPrice();
		
		if (groupOrderPrice.getItemId() != null) {
			groupOrderPrice.setItemName(dicService.getById(groupOrderPrice.getItemId()).getValue());
		}
		groupOrderPriceService.updateByPrimaryKeySelective(groupOrderPrice);

		return new BaseStateResult(true, null);
	}

	public BaseStateResult delOrderPrice(Integer id) {
		groupOrderPriceService.deleteByPrimaryKey(id);
		return new BaseStateResult(true, null);
	}

	public ToEditGroupOrderPriceResult toEditGroupOrderPrice(Integer id) {

		GroupOrderPrice groupOrderPrice = groupOrderPriceService.selectByPrimaryKey(id);

		ToEditGroupOrderPriceResult result = new ToEditGroupOrderPriceResult();
		result.setGroupOrderPrice(groupOrderPrice);

		return result;
	}

	// 意见反馈
	public ToIndividualGuestTicklingResult toIndividualGuestTickling(Integer groupId) {

		TourGroup tg = tourGroupService.selectByPrimaryKey(groupId);
		List<BookingGuide> guides = bookingGuideService.selectGuidesByGroupId(groupId);
		String guideString = "";
		if (guides.size() > 0) {
			guideString = getGuides(guides);
		}

		List<GroupOrder> orders = groupOrderService.selectOrderByGroupId(groupId);
		List<GroupOrderPrintPo> gops = new ArrayList<GroupOrderPrintPo>();
		GroupOrderPrintPo gop = null;
		for (GroupOrder order : orders) {
			gop = new GroupOrderPrintPo();
			gop.setReceiveMode(order.getReceiveMode());
			Integer numAdult = order.getNumAdult();
			Integer numChild = order.getNumChild();
			gop.setPersonNum((numAdult == null ? 0 : numAdult) + "大" + (numChild == null ? 0 : numChild) + "小");
			gops.add(gop);
		}

		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			list.add(i);
		}

		ToIndividualGuestTicklingResult result = new ToIndividualGuestTicklingResult();
		result.setTg(tg);
		result.setList(list);
		result.setGops(gops);
		result.setGuideString(guideString);

		return result;
	}

	public ToIndividualOrderGuestTicklingResult toIndividualOrderGuestTickling(Integer orderId) {

		GroupOrder go = groupOrderService.selectByPrimaryKey(orderId);
		TourGroup tg = new TourGroup();
		List<BookingGuide> guides = new ArrayList<BookingGuide>();
		String guideString = "";
		if (go != null && go.getGroupId() != null) {
			tg = tourGroupService.selectByPrimaryKey(go.getGroupId());
			guides = bookingGuideService.selectGuidesByGroupId(go.getGroupId());
			if (guides.size() > 0) {
				guideString = getGuides(guides);
			}
		}

		GroupOrderPrintPo gop = new GroupOrderPrintPo();
		gop.setReceiveMode(go.getReceiveMode());
		gop.setPersonNum((go.getNumAdult() == null ? 0 : go.getNumAdult()) + "大"
				+ (go.getNumChild() == null ? 0 : go.getNumChild()) + "小");

		
		ToIndividualOrderGuestTicklingResult result = new ToIndividualOrderGuestTicklingResult();
		result.setTg(tg);
		result.setGuideString(guideString);
		result.setGop(gop);

		return result;
	}
	
	
	public CreateSKGuideUpOffResult createSKGuideUpOff(Integer groupId){
		
		
		TourGroup tourGroup = tourGroupService.selectByPrimaryKey(groupId);
		
		// 查询导游信息
		List<BookingGuide> guides = bookingGuideService.selectGuidesByGroupId(groupId);
		String guideString = "";
		String driverString = "";
		if (guides.size() > 0) {
			guideString = getGuides(guides);
			driverString = getDrivers(guides);
		}
		
		//预定车信息
		List<BookingSupplier> bookingSuppliers = bookingSupplierService.getBookingSupplierByGroupIdAndSupplierType(groupId, 4) ;
		StringBuilder sbsb = new StringBuilder() ;
		for (BookingSupplier bs : bookingSuppliers) {
			List<BookingSupplierDetail> details = detailService.selectByPrimaryBookId(bs.getId()) ;
			for (BookingSupplierDetail bsd : details) {
				sbsb.append(bsd.getDriverName()+" "+bsd.getDriverTel()+" "+bsd.getCarLisence()+"\n") ;
			}
		}
		driverString = sbsb.toString() ;
		List<GroupOrder> orders = groupOrderService.selectOrderByGroupId(groupId);
		List<GroupOrderPrintPo> gopps = new ArrayList<GroupOrderPrintPo>();
		GroupOrderPrintPo gopp = null;
		
		// 房量总计
		String total = getHotelTotalNum(orders);
		for (GroupOrder order : orders) {
			// 拿到单条订单信息
			gopp = new GroupOrderPrintPo();
			gopp.setPlace((order.getProvinceName() == null ? "" : order
					.getProvinceName())
					+ (order.getCityName() == null ? "" : order.getCityName()));
			gopp.setRemark(order.getRemarkInternal());
			
			// 根据散客订单统计客人信息
			List<GroupOrderGuest> guests = groupOrderGuestService.selectByOrderId(order.getId());
			gopp.setGuesStatic(order.getReceiveMode());
			gopp.setGuestInfo(getGuestInfo2(guests));

			gopp.setPersonNum(order.getNumAdult()+"+"+order.getNumChild());
			
			// 根据散客订单统计酒店信息
			List<GroupRequirement> grogShopList = groupRequirementService.selectByOrderAndType(order.getId(), 3);
			StringBuilder sb = new StringBuilder();
			for (GroupRequirement gsl : grogShopList) {
				if (gsl.getHotelLevel() != null) {
					sb.append(dicService.getById(Integer.parseInt(gsl.getHotelLevel())).getValue() + "\n");
				}
			}
			gopp.setHotelLevel(sb.toString());
			gopp.setHotelNum(getHotelNum(grogShopList));
			
			// 省外交通
			// 根据散客订单统计接机信息
			List<GroupOrderTransport> groupOrderTransports = groupOrderTransportService.selectByOrderId(order.getId());
			gopp.setAirPickup(getAirInfo(groupOrderTransports, 0));
			// 根据散客订单统计送机信息
			gopp.setAirOff(getAirInfo(groupOrderTransports, 1));
			// 省内交通
			gopp.setTrans(getSourceType(groupOrderTransports));
			gopps.add(gopp);
		}
		
		CreateSKGuideUpOffResult result=new CreateSKGuideUpOffResult();
		result.setDriverString(driverString);
		result.setGopps(gopps);
		result.setGuideString(guideString);
		result.setTotal(total);
		result.setTourGroup(tourGroup);
		
		return result;
	}
	
	//FIXME 这个嵌套的太多，整体放facade中
	public CreateSKGuideResult createSKGuide(Integer groupId,String imgPath,String userName){
		
		//Map<String, Object> datas = bookingSupplierService.selectBookingInfo(groupId, 1);
		
		// 查询导游信息
		List<BookingGuide> guides = bookingGuideService.selectGuidesByGroupId(groupId);
		String guideString = getGuides(guides);
		String driverString = getDrivers(guides);
		
		//预定车信息
		List<BookingSupplier> bookingSuppliers = bookingSupplierService.getBookingSupplierByGroupIdAndSupplierType(groupId, 4) ;
		StringBuilder sbsb = new StringBuilder() ;
		for (BookingSupplier bs : bookingSuppliers) {
			List<BookingSupplierDetail> details = detailService.selectByPrimaryBookId(bs.getId()) ;
			for (BookingSupplierDetail bsd : details) {
				sbsb.append(bsd.getDriverName()+" "+bsd.getDriverTel()+" "+bsd.getCarLisence()+"\n") ;
			}
		}
		driverString = sbsb.toString() ;
		
		// 团信息
		TourGroup tourGroup = tourGroupService.selectByPrimaryKey(groupId);
		
		/**
		 * 获取全陪，定制团一个团对应一个订单
		 */
//		List<GroupOrder> orders = groupOrderService.selectOrderByGroupId(tourGroup.getId());
//		GroupOrder order = orders.get(0);
		
//		/**
//		 * logo图片
//		 */
//		String imgPath = settingCommon.getMyBizLogo(request);
		Map<String, Object> params1 = new HashMap<String, Object>();
		params1.put("print_time",DateUtils.format(new Date()));
		
		if (imgPath != null) {
			Map<String, String> picMap = new HashMap<String, String>();
			picMap.put("width", BizConfigConstant.BIZ_LOGO_WIDTH);
			picMap.put("height", BizConfigConstant.BIZ_LOGO_HEIGHT);
			picMap.put("type", "jpg");
			picMap.put("path", imgPath);
			params1.put("logo", picMap);
		} else {
			params1.put("logo", "");
		}
		params1.put("printName", userName);
		params1.put("printTime", DateUtils.format(new Date()));
		
		/**
		 * 第一个表格
		 */
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("groupCode", tourGroup.getGroupCode());
		map1.put("operator", tourGroup.getOperatorName());
		if (null != tourGroup.getDateStart()) {
			map1.put("startTime", DateUtils.format(tourGroup.getDateStart()));
		} else {
			map1.put("startTime", "");
		}
		map1.put("guide", guideString);
		map1.put("driver", driverString);
		map1.put("totalNum",tourGroup.getTotalAdult() + "大" + tourGroup.getTotalChild()+ "小");
		map1.put("productBrandName", tourGroup.getProductBrandName());
		map1.put("productName", tourGroup.getProductName());

		GroupRouteVO groupRouteVO = groupRouteService.findGroupRouteByGroupId(groupId);
		List<GroupRouteDayVO> routeDayVOList = groupRouteVO.getGroupRouteDayVOList();
		
		/**
		 * 行程列表表格
		 */
		List<Map<String, String>> routeList = new ArrayList<Map<String, String>>();
		for (GroupRouteDayVO groupRoute : routeDayVOList) {
			Map<String, String> map = new HashMap<String, String>();
			if (null != groupRoute.getGroupRoute().getGroupDate()) {
				map.put("day_num", DateUtils.format(groupRoute.getGroupRoute().getGroupDate()));
			} else {
				map.put("day_num", "");
			}
			map.put("route_desp", groupRoute.getGroupRoute().getRouteDesp());
			map.put("breakfast", groupRoute.getGroupRoute().getBreakfast());
			map.put("lunch", groupRoute.getGroupRoute().getLunch());
			map.put("supper", groupRoute.getGroupRoute().getSupper());
			map.put("hotel_name", groupRoute.getGroupRoute().getHotelName());
			routeList.add(map);
		}
		
		/**
		 * 酒店信息
		 */
		List<Map<String, String>> hotelList = new ArrayList<Map<String, String>>();
		List<BookingSupplier> hlList = bookingSupplierService.getHotelInfoByGroupId(groupId) ;
		for (BookingSupplier bs : hlList) {
			//SupplierInfo supplierInfo = supplierInfoService.selectBySupplierId(bs.getSupplierId()) ;
			SupplierInfo supplierInfo = supplierService.selectBySupplierId(bs.getSupplierId()) ;
			bs.setCityName(supplierInfo.getCityName());
		}
		int i =1 ;
		for (BookingSupplier bs : hlList) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("bookingDate", ""+i++);
			map.put("cityName", bs.getCityName());
			map.put("supplierName", bs.getSupplierName());
			map.put("hotelNumStatic", bs.getHotelNumStatic().replace(",", "\n"));
			map.put("contact", bs.getContact());
			map.put("cashType", bs.getCashType());
			hotelList.add(map) ;
		}
		
		List<Map<String, String>> orderList = null;
		List<GroupOrder> orders1 = groupOrderService.selectOrderByGroupId(tourGroup.getId());
		List<GroupOrderPrintPo> gopps = new ArrayList<GroupOrderPrintPo>();
		GroupOrderPrintPo gopp = null;
		for (GroupOrder order1 : orders1) {
			
			// 拿到单条订单信息
			gopp = new GroupOrderPrintPo();
			gopp.setRemark(order1.getRemarkInternal());
			gopp.setPlace((order1.getProvinceName() == null ? "" : order1
					.getProvinceName())
					+ (order1.getCityName() == null ? "" : order1.getCityName()));
			// 根据散客订单统计人数order1
			Integer numAdult = groupOrderGuestService.selectNumAdultByOrderID(order1.getId());
			Integer numChild = groupOrderGuestService.selectNumChildByOrderID(order1.getId());
			gopp.setPersonNum((numAdult == null ? "" : numAdult) + "+" + (numChild == null ? "" : numChild));
			
			// 根据散客订单统计客人信息
			List<GroupOrderGuest> guests = groupOrderGuestService.selectByOrderId(order1.getId());
			gopp.setGuesStatic(order1.getReceiveMode());
			gopp.setGuestInfo(getGuestInfo2(guests));

			// 根据散客订单统计酒店信息
			List<GroupRequirement> grogShopList = groupRequirementService.selectByOrderAndType(order1.getId(), 3);
			StringBuilder sb = new StringBuilder();
			for (GroupRequirement gsl : grogShopList) {
				if (gsl.getHotelLevel() != null) {
					sb.append(dicService.getById(Integer.parseInt(gsl.getHotelLevel())).getValue() + "\n");
				}
			}
			gopp.setHotelLevel(sb.toString());
			gopp.setHotelNum(getHotelNum(grogShopList));
			
			// 省外交通
			// 根据散客订单统计接机信息
			List<GroupOrderTransport> groupOrderTransports = groupOrderTransportService.selectByOrderId(order1.getId());
			gopp.setAirPickup(getAirInfo(groupOrderTransports, 0));
			
			// 根据散客订单统计送机信息
			gopp.setAirOff(getAirInfo(groupOrderTransports, 1));
			
			// 省内交通
			gopp.setTrans(getSourceType(groupOrderTransports));
			gopps.add(gopp);
		}
		orderList = new ArrayList<Map<String, String>>();
		int x = 1;
		for (GroupOrderPrintPo po1 : gopps) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", "" + x++);
			map.put("guestStatic", po1.getGuesStatic());
			map.put("personNum", po1.getPersonNum());
			map.put("place", po1.getPlace());
			map.put("hotelLevel", po1.getHotelLevel());
			map.put("hotelNum", po1.getHotelNum());
			map.put("up", po1.getAirPickup());
			map.put("off", po1.getAirOff());
			map.put("trans", po1.getTrans());
			map.put("guestInfo", po1.getGuestInfo());
			map.put("remark", po1.getRemark());
			orderList.add(map);
		}

		/**
		 * 计调信息
		 */
		List<GroupGuidePrintPo> pos = new ArrayList<GroupGuidePrintPo>();
		GroupGuidePrintPo po = null;
		// 预定下接社信息
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		List<BookingDelivery> deliveries = deliveryService.getDeliveryListByGroupId(tourGroup.getId());
		for (BookingDelivery bd : deliveries) {
			po = new GroupGuidePrintPo();
			po.setSupplierType("下接社");
			po.setSupplierName(bd.getSupplierName());
			po.setContacktWay(bd.getContact() + "-" + bd.getContactMobile());
			po.setPaymentWay("");
			String dd = "";
			if (bd.getDateArrival() != null) {
				dd = DateUtils.format(bd.getDateArrival());
			}
			po.setDetail(dd + " " + "人数：" + bd.getPersonAdult() + "大" + bd.getPersonChild() + "小" + bd.getPersonGuide() + "陪");
			pos.add(po);
		}
		
		/*// 预定购物
		List<BookingShop> shops = shopService.getShopListByGroupId(tourGroup
				.getId());
		for (BookingShop bs : shops) {
			po = new GroupGuidePrintPo();
			po.setSupplierType("购物店");
			po.setSupplierName(bs.getSupplierName());
			po.setContacktWay("");
			po.setPaymentWay("");
			po.setDetail(bs.getShopDate());
			pos.add(po);
		}*/
		
		/**
		 * 预订房信息
		 */
		List<BookingSupplier> bs3 = bookingSupplierService.getBookingSupplierByGroupIdAndSupplierType(tourGroup.getId(),3);
		for (BookingSupplier bs : bs3) {
			po = new GroupGuidePrintPo();
			po.setSupplierType("房");
			po.setSupplierName(bs.getSupplierName());
			po.setContacktWay(bs.getContact() + "-" + bs.getContactMobile());
			po.setPaymentWay(bs.getCashType());
			List<BookingSupplierDetail> details = detailService.selectByPrimaryBookId(bs.getId());
			StringBuilder sb = new StringBuilder();
			for (BookingSupplierDetail bsd : details) {
				String dd = "";
				if (bsd.getItemDate() != null) {
					dd = DateUtils.format(bsd.getItemDate());
				}
				sb.append(dd+
						" 【"+bsd.getType1Name()+"】 "+
						"("+bsd.getItemNum().toString().replace(".0","")+
						"-"+bsd.getItemNumMinus().toString().replace(".0","")+")" +"\n");
			}
			po.setDetail(sb.toString());
			pos.add(po);
		}
		
		/**
		 * 预定车信息
		 */
		List<BookingSupplier> bs4 = bookingSupplierService
				.getBookingSupplierByGroupIdAndSupplierType(tourGroup.getId(),
						4);
		for (BookingSupplier bs : bs4) {
			List<BookingSupplierDetail> details = detailService.selectByPrimaryBookId(bs.getId());
			StringBuilder sb = new StringBuilder();
			for (BookingSupplierDetail bsd : details) {
				po = new GroupGuidePrintPo();
				po.setSupplierType("司机");
				po.setSupplierName(bsd.getDriverName());
				po.setContacktWay(bsd.getDriverTel());
				po.setPaymentWay(bs.getCashType());
				sb.append(bsd.getType1Name() + "," + bsd.getType2Name() + "座"
						+ "," + bsd.getCarLisence() + "," + "价格："
						+ bsd.getItemPrice());
				if (bs.getRemark() != "" && bs.getRemark() != null) {
					sb.append(",备注：" + bs.getRemark());
				}
				po.setDetail(sb.toString());
				pos.add(po);
			}
		}
		/**
		 * 预定景区信息
		 */
		/*List<BookingSupplier> bs5 = bookingSupplierService
				.getBookingSupplierByGroupIdAndSupplierType(tourGroup.getId(),
						5);
		for (BookingSupplier bs : bs5) {
			po = new GroupGuidePrintPo();
			po.setSupplierType("景区");
			po.setSupplierName(bs.getSupplierName());
			po.setContacktWay(bs.getContact() + "-" + bs.getContactMobile());
			po.setPaymentWay(bs.getCashType());
			List<BookingSupplierDetail> details = detailService
					.selectByPrimaryBookId(bs.getId());
			StringBuilder sb = new StringBuilder();
			for (BookingSupplierDetail bsd : details) {
				String dd = "";
				if (bsd.getItemDate() != null) {
					dd = com.yihg.erp.utils.DateUtils.format(bsd.getItemDate());
				}
				sb.append(dd + " 【" + bsd.getType1Name() + "】 "
						+ bsd.getItemPrice().toString().replace(".0", "")
						+ "*(" + bsd.getItemNum().toString().replace(".0", "")
						+ "-"
						+ bsd.getItemNumMinus().toString().replace(".0", "")
						+ ")");
			}
			po.setDetail(sb.toString());

			pos.add(po);
		}*/
		/**
		 * 组织打印数据
		 */
		for (GroupGuidePrintPo ggp : pos) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("supplierType", ggp.getSupplierType());
			map.put("supplierName", ggp.getSupplierName());
			map.put("contactWay", ggp.getContacktWay());
			map.put("paymentWay", ggp.getPaymentWay());
			map.put("detail", ggp.getDetail());
			mapList.add(map);
		}
		
		/**
		 * 备注信息
		 */
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("remarkInternal", tourGroup.getRemarkInternal());
		
//		try {
//			export.export(params1);
//			export.export(map1, 0);
//			export.export(routeList, 1);
//			export.export(hotelList, 2);
//			export.export(orderList, 3);
//			export.export(mapList, 4);
//			export.export(map2, 5);
//			export.generate(url);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return url;
		
		CreateSKGuideResult result=new CreateSKGuideResult();
		
		result.setHotelList(hotelList);
		result.setMap1(map1);
		result.setMap2(map2);
		result.setMapList(mapList);
		result.setOrderList(orderList);
		result.setParams1(params1);
		result.setRouteList(routeList);
		
		return result;
	}

	public ToShoppingDetailPreviewResult toShoppingDetailPreview(Integer groupId) {

		TourGroup tg = tourGroupService.selectByPrimaryKey(groupId);
		List<BookingGuide> guides = bookingGuideService.selectGuidesByGroupId(groupId);

		String guideString = "";
		if (guides.size() > 0) {
			guideString = getGuides(guides);
		}
		List<GroupOrder> orders = groupOrderService.selectOrderByGroupId(groupId);

		List<GroupOrderPrintPo> gops = new ArrayList<GroupOrderPrintPo>();
		GroupOrderPrintPo gop = null;
		for (GroupOrder order : orders) {
			gop = new GroupOrderPrintPo();
			gop.setReceiveMode(order.getReceiveMode());
			Integer numAdult = groupOrderGuestService.selectNumAdultByOrderID(order.getId());
			Integer numChild = groupOrderGuestService.selectNumChildByOrderID(order.getId());
			gop.setPersonNum((numAdult == null ? 0 : numAdult) + "大" + (numChild == null ? 0 : numChild) + "小");
			gop.setPlace(order.getProvinceName() + order.getCityName());
			gops.add(gop);
		}

		ToShoppingDetailPreviewResult result = new ToShoppingDetailPreviewResult();
		result.setGops(gops);
		result.setTg(tg);
		result.setGuideString(guideString);

		return result;
	}
	
	@Override
	public CreateShoppingDetailResult createShoppingDetail(Integer groupId, String userName) {

		TourGroup tg = tourGroupService.selectByPrimaryKey(groupId);
		List<BookingGuide> guides = bookingGuideService.selectGuidesByGroupId(groupId);
		String guideString = "";
		if (guides.size() > 0) {
			guideString = getGuides(guides);
		}

		List<GroupOrder> orders = groupOrderService.selectOrderByGroupId(groupId);
		List<GroupOrderPrintPo> gops = new ArrayList<GroupOrderPrintPo>();
		GroupOrderPrintPo gop = null;
		for (GroupOrder order : orders) {
			gop = new GroupOrderPrintPo();
			gop.setReceiveMode(order.getReceiveMode());
			Integer numAdult = groupOrderGuestService.selectNumAdultByOrderID(order.getId());
			Integer numChild = groupOrderGuestService.selectNumChildByOrderID(order.getId());
			gop.setPersonNum((numAdult == null ? 0 : numAdult) + "大" + (numChild == null ? 0 : numChild) + "小");
			gop.setPlace(order.getProvinceName() + order.getCityName());
			gops.add(gop);
		}

		CreateShoppingDetailResult result = new CreateShoppingDetailResult();
		result.setGops(gops);
		result.setGuideString(guideString);
		result.setTg(tg);

		return result;
	}
	
	@Override
	public ToShoppingDetailPreviewResult toShoppingDetailPreview1(Integer groupId, String name) {

		TourGroup tg = tourGroupService.selectByPrimaryKey(groupId);
		List<BookingGuide> guides = bookingGuideService.selectGuidesByGroupId(groupId);
		String guideString = "";
		if (guides.size() > 0) {
			guideString = getGuides(guides);
		}

		List<GroupOrder> orders = groupOrderService.selectOrderByGroupId(groupId);
		List<GroupOrderPrintPo> gops = new ArrayList<GroupOrderPrintPo>();
		GroupOrderPrintPo gop = null;
		for (GroupOrder order : orders) {
			gop = new GroupOrderPrintPo();
			gop.setReceiveMode(order.getReceiveMode());
			Integer numAdult = groupOrderGuestService.selectNumAdultByOrderID(order.getId());
			Integer numChild = groupOrderGuestService.selectNumChildByOrderID(order.getId());
			gop.setPersonNum((numAdult == null ? 0 : numAdult) + "大" + (numChild == null ? 0 : numChild) + "小");
			gop.setPlace(order.getProvinceName() + order.getCityName());
			List<GroupOrderGuest> guests = groupOrderGuestService.selectByOrderId(order.getId());
			gop.setGuestInfo(getGuestInfo2(guests));
			gop.setGuests(guests);
			gops.add(gop);
		}

		ToShoppingDetailPreviewResult result = new ToShoppingDetailPreviewResult();
		result.setGops(gops);
		result.setGuideString(guideString);
		result.setTg(tg);

		return result;
	}
	
	@Override
	public CreateShoppingDetailResult createShoppingDetail2(Integer groupId,String userName) {
		
		TourGroup tg = tourGroupService.selectByPrimaryKey(groupId);
		List<BookingGuide> guides = bookingGuideService.selectGuidesByGroupId(groupId);
		String guideString = "";
		if (guides.size() > 0) {
			guideString = getGuides(guides);
		}
		List<GroupOrder> orders = groupOrderService.selectOrderByGroupId(groupId);
		List<GroupOrderPrintPo> gops = new ArrayList<GroupOrderPrintPo>();
		GroupOrderPrintPo gop = null;
		for (GroupOrder order : orders) {
			gop = new GroupOrderPrintPo();
			gop.setReceiveMode(order.getReceiveMode());
			Integer numAdult = groupOrderGuestService.selectNumAdultByOrderID(order.getId());
			Integer numChild = groupOrderGuestService.selectNumChildByOrderID(order.getId());
			gop.setPersonNum((numAdult == null ? 0 : numAdult) + "大" + (numChild == null ? 0 : numChild) + "小");
			gop.setPlace(order.getProvinceName() + order.getCityName());
			List<GroupOrderGuest> guests = groupOrderGuestService.selectByOrderId(order.getId());
			gop.setGuestInfo(getGuestInfo3(guests));
			gop.setGuests(guests);
			gops.add(gop);
		}

		CreateShoppingDetailResult result = new CreateShoppingDetailResult();
		result.setGops(gops);
		result.setGuideString(guideString);
		result.setTg(tg);

		return result;
	}
	
	
	@Override
	public CreatetTicklingResult createtTickling(Integer groupId) {
		
		TourGroup tg = tourGroupService.selectByPrimaryKey(groupId);
		List<BookingGuide> guides = bookingGuideService.selectGuidesByGroupId(groupId);
		String guideString = "";
		if (guides.size() > 0) {
			guideString = getGuides(guides);
		}
		List<GroupOrder> orders = groupOrderService.selectOrderByGroupId(groupId);
		List<GroupOrderPrintPo> gops = new ArrayList<GroupOrderPrintPo>();
		GroupOrderPrintPo gop = null;
		for (GroupOrder order : orders) {
			gop = new GroupOrderPrintPo();
			gop.setReceiveMode(order.getReceiveMode());
			Integer numAdult = order.getNumAdult();
			Integer numChild = order.getNumChild();
			gop.setPersonNum((numAdult == null ? 0 : numAdult) + "大" + (numChild == null ? 0 : numChild) + "小");
			gops.add(gop);
		}
		
		CreatetTicklingResult result=new CreatetTicklingResult();
		result.setGops(gops);
		result.setGuideString(guideString);
		result.setTg(tg);
		
		return result;
	}
	
	@Override
	public CreatetOrderTicklingResult createtOrderTickling(Integer orderId) {

		GroupOrder go = groupOrderService.selectByPrimaryKey(orderId);
		TourGroup tg = new TourGroup();
		List<BookingGuide> guides = new ArrayList<BookingGuide>();
		String guideString = "";
		if (go != null && go.getGroupId() != null) {
			tg = tourGroupService.selectByPrimaryKey(go.getGroupId());
			guides = bookingGuideService.selectGuidesByGroupId(go.getGroupId());
			if (guides.size() > 0) {
				guideString = getGuides(guides);
			}
		}
		List<GroupOrderPrintPo> gops = new ArrayList<GroupOrderPrintPo>();
		GroupOrderPrintPo gop = new GroupOrderPrintPo();
		gop.setReceiveMode(go.getReceiveMode());
		Integer numAdult = go.getNumAdult();
		Integer numChild = go.getNumChild();
		gop.setPersonNum((numAdult == null ? 0 : numAdult) + "大" + (numChild == null ? 0 : numChild) + "小");
		gops.add(gop);

		CreatetOrderTicklingResult result = new CreatetOrderTicklingResult();
		result.setGop(gop);
		result.setGuideString(guideString);
		result.setNumAdult(numAdult);
		result.setNumChild(numChild);
		result.setTg(tg);
		return result;
	}
	
	@Override
	public CreatetTicklingResult createtTeamGroupTickling(Integer groupId) {
		
		TourGroup tg = tourGroupService.selectByPrimaryKey(groupId);
		List<BookingGuide> guides = bookingGuideService
				.selectGuidesByGroupId(groupId);
		String guideString = "";
		if (guides.size() > 0) {
			guideString = getGuides(guides);
		}
		List<GroupOrder> orders = groupOrderService
				.selectOrderByGroupId(groupId);
		List<GroupOrderPrintPo> gops = new ArrayList<GroupOrderPrintPo>();
		GroupOrderPrintPo gop = null;
		for (GroupOrder order : orders) {
			gop = new GroupOrderPrintPo();
			gop.setReceiveMode(order.getReceiveMode());
			Integer numAdult = groupOrderGuestService
					.selectNumAdultByOrderID(order.getId());
			Integer numChild = groupOrderGuestService
					.selectNumChildByOrderID(order.getId());
			gop.setPersonNum((numAdult == null ? 0 : numAdult) + "大"
					+ (numChild == null ? 0 : numChild) + "小");
			gops.add(gop);
		}

		CreatetTicklingResult result=new CreatetTicklingResult();
		result.setGops(gops);
		result.setGuideString(guideString);
		result.setTg(tg);
		
		return result;
	}
	
	@Override
	public CreateGuestNamesResult createGuestNames(Integer groupId) {
		
		TourGroup tg = tourGroupService.selectByPrimaryKey(groupId);
		List<GroupOrder> orders = groupOrderService
				.selectOrderByGroupId(groupId);
		List<GroupOrderGuest> guestAllList = new ArrayList<GroupOrderGuest>();
		for (GroupOrder order : orders) {
			List<GroupOrderGuest> guests = groupOrderGuestService
					.selectByOrderId(order.getId());
			guestAllList.addAll(guests);
		}
		
		CreateGuestNamesResult result=new CreateGuestNamesResult();
		result.setGuestAllList(guestAllList);
		result.setTg(tg);
		
		return result;
		
	}
	
	@Override
	public PreviewGuestWithTransResult previewGuestWithTrans(Integer groupId) {
		
		
		TourGroup tourGroup = tourGroupService.selectByPrimaryKey(groupId);
		String operatorMobile = platformEmployeeService.findByEmployeeId(tourGroup.getOperatorId()).getMobile();

		// 查询导游信息
		List<BookingGuide> guides = bookingGuideService
				.selectGuidesByGroupId(groupId);
		String guideString = "";
		String driverString = "";
		if (guides.size() > 0) {
			guideString = getGuides(guides);
			driverString = getDrivers(guides);
		}
		//预定车信息
		List<BookingSupplier> bookingSuppliers = bookingSupplierService.getBookingSupplierByGroupIdAndSupplierType(groupId, 4) ;
		StringBuilder sbsb = new StringBuilder() ;
		for (BookingSupplier bs : bookingSuppliers) {
			List<BookingSupplierDetail> details = detailService.selectByPrimaryBookId(bs.getId()) ;
			for (BookingSupplierDetail bsd : details) {
				sbsb.append(bsd.getDriverName()+" "+bsd.getDriverTel()+" "+bsd.getCarLisence()+"\n") ;
			}
		}
		driverString = sbsb.toString() ;
		List<GroupOrder> orders = groupOrderService
				.selectOrderByGroupId(groupId);
		List<GroupOrderPrintPo> gopps = new ArrayList<GroupOrderPrintPo>();
		GroupOrderPrintPo gopp = null;
		// 房量总计
		String total = getHotelTotalNum(orders);
		for (GroupOrder order : orders) {
			// 拿到单条订单信息
			gopp = new GroupOrderPrintPo();
			gopp.setPlace((order.getProvinceName() == null ? "" : order
					.getProvinceName())
					+ (order.getCityName() == null ? "" : order.getCityName()));
			gopp.setRemark(order.getRemarkInternal());
			// 根据散客订单统计客人信息
			List<GroupOrderGuest> guests = groupOrderGuestService
					.selectByOrderId(order.getId());
			gopp.setGuestInfo(getGuestInfo2(guests));
			gopp.setGuests(guests);
			gopp.setReceiveMode(order.getReceiveMode());
			
			gopp.setPersonNum(order.getNumAdult()+"+"+order.getNumChild());
			// 根据散客订单统计酒店信息
			List<GroupRequirement> grogShopList = groupRequirementService
					.selectByOrderAndType(order.getId(), 3);
			StringBuilder sb = new StringBuilder();
			for (GroupRequirement gsl : grogShopList) {
				if (gsl.getHotelLevel() != null) {
					sb.append(dicService.getById(Integer.parseInt(gsl.getHotelLevel()))
							.getValue() + "\n");
				}
			}
			gopp.setHotelLevel(sb.toString());
			gopp.setHotelNum(getHotelNum(grogShopList));
			// 省外交通
			// 根据散客订单统计接机信息
			List<GroupOrderTransport> groupOrderTransports = groupOrderTransportService
					.selectByOrderId(order.getId());
			gopp.setAirPickup(getAirInfo(groupOrderTransports, 0));
			// 根据散客订单统计送机信息
			gopp.setAirOff(getAirInfo(groupOrderTransports, 1));
			// 省内交通
			gopp.setTrans(getSourceType(groupOrderTransports));
			gopps.add(gopp);
		}
		
		PreviewGuestWithTransResult result = new PreviewGuestWithTransResult();
		result.setDriverString(driverString);
		result.setGopps(gopps);
		result.setGuideString(guideString);
		result.setOperatorMobile(operatorMobile);
		result.setTotal(total);
		result.setTourGroup(tourGroup);
		return result;
	}
	
	@Override
	public PreviewGuestWithoutTransResult previewGuestWithoutTrans(Integer groupId) {

		TourGroup tg = tourGroupService.selectByPrimaryKey(groupId);
		List<GroupOrder> orders = groupOrderService.selectOrderByGroupId(groupId);
		List<GroupOrderGuest> guestAllList = new ArrayList<GroupOrderGuest>();
		for (GroupOrder order : orders) {
			List<GroupOrderGuest> guests = groupOrderGuestService.selectByOrderId(order.getId());
			guestAllList.addAll(guests);
		}

		PreviewGuestWithoutTransResult result = new PreviewGuestWithoutTransResult();
		result.setGuestAllList(guestAllList);
		result.setTg(tg);
		return result;
	}
	
	@Override
	public PreviewFitGuideResult previewFitGuide(Integer groupId) {
		
		PreviewFitGuideResult result=new PreviewFitGuideResult();
		
		// 查询导游信息
		List<BookingGuide> guides = bookingGuideService.selectGuidesByGroupId(groupId);
		String guideString = getGuides(guides);
		String driverString = getDrivers(guides);
		
		//预定车信息
		List<BookingSupplier> bookingSuppliers = bookingSupplierService.getBookingSupplierByGroupIdAndSupplierType(groupId, 4) ;
		StringBuilder sbsb = new StringBuilder() ;
		for (BookingSupplier bs : bookingSuppliers) {
			List<BookingSupplierDetail> details = detailService.selectByPrimaryBookId(bs.getId()) ;
			for (BookingSupplierDetail bsd : details) {
				sbsb.append(bsd.getDriverName()+" "+bsd.getDriverTel()+" "+bsd.getCarLisence()+"\n") ;
			}
		}
		driverString = sbsb.toString() ;
		
		//model.addAttribute("guideString", guideString);
		//model.addAttribute("driverString", driverString);
		result.setGuideString(guideString);
		result.setDriverString(driverString);
		
		// 团信息
		TourGroup tourGroup = tourGroupService.selectByPrimaryKey(groupId);
		
		//model.addAttribute("tourGroup", tourGroup);
		//model.addAttribute("printName", WebUtils.getCurUser(request).getName());
		//model.addAttribute("operatorMobile",sysPlatformEmployeeFacade.findByEmployeeId(tourGroup.getOperatorId()).getPlatformEmployeePo().getMobile());
		result.setTourGroup(tourGroup);
		result.setOperatorMobile(platformEmployeeService.findByEmployeeId(tourGroup.getOperatorId()).getMobile());
		
		//改1
		/**
		 * 获取全陪，定制团一个团对应一个订单
		 */
//		List<GroupOrder> orders = groupOrderService.selectOrderByGroupId(tourGroup.getId());
//		/**
//		 * logo图片
//		 */
//		String imgPath = bizSettingCommon.getMyBizLogo(request);
//		model.addAttribute("imgPath", imgPath);
		
		GroupRouteVO groupRouteVO = groupRouteService.findGroupRouteByGroupId(groupId);
		List<GroupRouteDayVO> routeDayVOList = groupRouteVO.getGroupRouteDayVOList();
		
		List<GroupOrder> orders1 = groupOrderService.selectOrderByGroupId(tourGroup.getId());
		List<GroupOrderPrintPo> gopps = new ArrayList<GroupOrderPrintPo>();
		GroupOrderPrintPo gopp = null;
		for (GroupOrder order1 : orders1) {
			// 拿到单条订单信息
			gopp = new GroupOrderPrintPo();
			gopp.setRemark(order1.getRemarkInternal());
			gopp.setPlace((order1.getProvinceName() == null ? "" : order1
					.getProvinceName())
					+ (order1.getCityName() == null ? "" : order1.getCityName()));
			gopp.setPersonNum(order1.getNumAdult()+"+"+order1.getNumChild());
			// 根据散客订单统计客人信息
			List<GroupOrderGuest> guests = groupOrderGuestService
					.selectByOrderId(order1.getId());
			gopp.setGuestInfo(getGuestInfo2(guests));
			gopp.setGuests(guests);
			gopp.setReceiveMode(order1.getReceiveMode());
			// 根据散客订单统计酒店信息
			List<GroupRequirement> grogShopList = groupRequirementService
					.selectByOrderAndType(order1.getId(), 3);
			StringBuilder sb = new StringBuilder();
			for (GroupRequirement gsl : grogShopList) {
				if (gsl.getHotelLevel() != null) {
					sb.append(dicService.getById(Integer.parseInt(gsl.getHotelLevel()))
							.getValue() + "\n");
				}
			}
			gopp.setHotelLevel(sb.toString());
			gopp.setHotelNum(getHotelNum(grogShopList));
			// 省外交通
			// 根据散客订单统计接机信息
			List<GroupOrderTransport> groupOrderTransports = groupOrderTransportService
					.selectByOrderId(order1.getId());
			gopp.setAirPickup(getAirInfo(groupOrderTransports, 0));
			// 根据散客订单统计送机信息
			gopp.setAirOff(getAirInfo(groupOrderTransports, 1));
			// 省内交通
			gopp.setTrans(getSourceType(groupOrderTransports));
			gopps.add(gopp);
		}
		//model.addAttribute("orderList", gopps);
		result.setGopps(gopps);

		/**
		 * 计调信息
		 */
		List<GroupGuidePrintPo> pos = new ArrayList<GroupGuidePrintPo>();
		GroupGuidePrintPo po = null;
		// 预定下接社信息
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		List<BookingDelivery> deliveries = deliveryService
				.getDeliveryListByGroupId(tourGroup.getId());
		for (BookingDelivery bd : deliveries) {
			po = new GroupGuidePrintPo();
			po.setSupplierType("下接社");
			po.setSupplierName(bd.getSupplierName());
			po.setContacktWay(bd.getContact() + "-" + bd.getContactMobile());
			po.setPaymentWay("");
			String dd = "";
			if (bd.getDateArrival() != null) {
				dd = DateUtils.format(bd.getDateArrival());
			}
			po.setDetail(dd + " " + "人数：" + bd.getPersonAdult() + "大"
					+ bd.getPersonChild() + "小" + bd.getPersonGuide() + "陪");
			pos.add(po);
		}
		// 预定购物
		List<BookingShop> shops = shopService.getShopListByGroupId(tourGroup
				.getId());
		for (BookingShop bs : shops) {
			po = new GroupGuidePrintPo();
			po.setSupplierType("购物店");
			po.setSupplierName(bs.getSupplierName());
			po.setContacktWay("");
			po.setPaymentWay("");
			po.setDetail(bs.getShopDate());
			pos.add(po);
		}
		/**
		 * 预订房信息
		 */
		List<BookingSupplier> bs3 = bookingSupplierService
				.getBookingSupplierByGroupIdAndSupplierType(tourGroup.getId(),
						3);
		for (BookingSupplier bs : bs3) {
			po = new GroupGuidePrintPo();
			po.setSupplierType("房");
			po.setSupplierName(bs.getSupplierName());
			po.setContacktWay(bs.getContact() + "-" + bs.getContactMobile());
			po.setPaymentWay(bs.getCashType());
			List<BookingSupplierDetail> details = detailService
					.selectByPrimaryBookId(bs.getId());
			StringBuilder sb = new StringBuilder();
			for (BookingSupplierDetail bsd : details) {
				String dd = "";
				if (bsd.getItemDate() != null) {
					dd = DateUtils.format(bsd.getItemDate());
				}
				sb.append(dd + " 【" + bsd.getType1Name() + "】 "
						+ bsd.getItemPrice().toString().replace(".0", "")
						+ "*(" + bsd.getItemNum().toString().replace(".0", "")
						+ "-"
						+ bsd.getItemNumMinus().toString().replace(".0", "")
						+ ")");
			}
			po.setDetail(sb.toString());

			pos.add(po);
		}
		/**
		 * 预定车信息
		 */
		List<BookingSupplier> bs4 = bookingSupplierService
				.getBookingSupplierByGroupIdAndSupplierType(tourGroup.getId(),
						4);
		for (BookingSupplier bs : bs4) {
			List<BookingSupplierDetail> details = detailService
					.selectByPrimaryBookId(bs.getId());
			StringBuilder sb = new StringBuilder();
			for (BookingSupplierDetail bsd : details) {
				po = new GroupGuidePrintPo();
				po.setSupplierType("司机");
				po.setSupplierName(bsd.getDriverName());
				po.setContacktWay(bsd.getDriverTel());
				po.setPaymentWay(bs.getCashType());
				sb.append(bsd.getType1Name() + "," + bsd.getType2Name() + "座"
						+ "," + bsd.getCarLisence() + "," + "价格："
						+ bsd.getItemPrice());
				if (bs.getRemark() != "" && bs.getRemark() != null) {
					sb.append(",备注：" + bs.getRemark());
				}
				po.setDetail(sb.toString());
				pos.add(po);
			}
		}
		/**
		 * 预定景区信息
		 */
		List<BookingSupplier> bs5 = bookingSupplierService
				.getBookingSupplierByGroupIdAndSupplierType(tourGroup.getId(),
						5);
		for (BookingSupplier bs : bs5) {
			po = new GroupGuidePrintPo();
			po.setSupplierType("景区");
			po.setSupplierName(bs.getSupplierName());
			po.setContacktWay(bs.getContact() + "-" + bs.getContactMobile());
			po.setPaymentWay(bs.getCashType());
			List<BookingSupplierDetail> details = detailService
					.selectByPrimaryBookId(bs.getId());
			StringBuilder sb = new StringBuilder();
			for (BookingSupplierDetail bsd : details) {
				String dd = "";
				if (bsd.getItemDate() != null) {
					dd = DateUtils.format(bsd.getItemDate());
				}
				sb.append(dd + " 【" + bsd.getType1Name() + "】 "
						+ bsd.getItemPrice().toString().replace(".0", "")
						+ "*(" + bsd.getItemNum().toString().replace(".0", "")
						+ "-"
						+ bsd.getItemNumMinus().toString().replace(".0", "")
						+ ")");
			}
			po.setDetail(sb.toString());

			pos.add(po);
		}
		/**
		 * 组织打印数据
		 */
		for (GroupGuidePrintPo ggp : pos) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("supplierType", ggp.getSupplierType());
			map.put("supplierName", ggp.getSupplierName());
			map.put("contactWay", ggp.getContacktWay());
			map.put("paymentWay", ggp.getPaymentWay());
			map.put("detail", ggp.getDetail());
			mapList.add(map);
		}
		
		//model.addAttribute("routeDayVOList", routeDayVOList);
		//model.addAttribute("mapList", pos);
		result.setRouteDayVOList(routeDayVOList);
		result.setPos(pos);
		
		List<BookingSupplier> hotelList = bookingSupplierService.getHotelInfoByGroupId(groupId) ;
		for (BookingSupplier bs : hotelList) {
			SupplierInfo supplierInfo = supplierService.selectBySupplierId(bs.getSupplierId()) ;
			bs.setCityName(supplierInfo.getCityName());
		}
		//model.addAttribute("hotelList", hotelList);
		result.setHotelList(hotelList);
		
		return result;
	}
	
	@Override
	public PreviewFitTransferResult previewFitTransfer(Integer groupId) {

		TourGroup tourGroup = tourGroupService.selectByPrimaryKey(groupId);
		List<BookingGuide> guides = bookingGuideService.selectGuidesByGroupId(groupId);
		String guideString = "";
		String driverString = "";
		if (guides.size() > 0) {
			guideString = getGuides(guides);
			driverString = getDrivers(guides);
		}
		// 预定车信息
		List<BookingSupplier> bs4 = bookingSupplierService.getBookingSupplierByGroupIdAndSupplierType(groupId, 4);
		StringBuilder sbsb = new StringBuilder();
		for (BookingSupplier bs : bs4) {
			List<BookingSupplierDetail> details = detailService.selectByPrimaryBookId(bs.getId());
			for (BookingSupplierDetail bsd : details) {
				sbsb.append(bsd.getDriverName() + " " + bsd.getDriverTel() + " " + bsd.getCarLisence() + "\n");
			}
		}
		driverString = sbsb.toString();

		List<GroupOrder> orders = groupOrderService.selectOrderByGroupId(groupId);
		List<GroupOrderPrintPo> gopps = new ArrayList<GroupOrderPrintPo>();
		GroupOrderPrintPo gopp = null;
		String total = getHotelTotalNum(orders);
		for (GroupOrder order : orders) {
			// 拿到单条订单信息
			gopp = new GroupOrderPrintPo();
			gopp.setSupplierName(order.getSupplierName() + "\n" + order.getContactName());
			gopp.setReceiveMode(order.getReceiveMode());

			gopp.setSaleOperatorName(order.getSaleOperatorName());
			gopp.setRemark(order.getRemarkInternal());
			gopp.setPlace((order.getProvinceName() == null ? "" : order.getProvinceName())
					+ (order.getCityName() == null ? "" : order.getCityName()));
			// 根据散客订单统计人数
			/*
			 * Integer numAdult = groupOrderGuestService
			 * .selectNumAdultByOrderID(order.getId()); Integer numChild =
			 * groupOrderGuestService .selectNumChildByOrderID(order.getId());
			 */
			gopp.setPersonNum(order.getNumAdult() + "+" + order.getNumChild());
			// 根据散客订单统计客人信息
			List<GroupOrderGuest> guests = groupOrderGuestService.selectByOrderId(order.getId());
			/*
			 * for (GroupOrderGuest guest : guests) { if (guest.getIsLeader() ==
			 * 1) { gopp.setGuesStatic(guest.getName() + " " + guests.size() +
			 * "人" + "\n" + guest.getMobile()); break; } } if
			 * (gopp.getGuesStatic() == null || gopp.getGuesStatic() == "") { //
			 * 如果客人中没有领队，默认取一条数据显示 gopp.setGuesStatic(guests.get(0).getName() +
			 * "\n" + guests.get(0).getMobile()); }
			 */
			gopp.setGuesStatic(order.getReceiveMode());
			gopp.setGuestInfo(getGuestInfo2(guests));
			gopp.setGuests(guests);
			// 根据散客订单统计酒店信息
			List<GroupRequirement> grogShopList = groupRequirementService.selectByOrderAndType(order.getId(), 3);
			StringBuilder sb = new StringBuilder();
			for (GroupRequirement gsl : grogShopList) {
				if (gsl.getHotelLevel() != null) {
					sb.append(dicService.getById(Integer.parseInt(gsl.getHotelLevel())).getValue()+ "\n");
				}
			}
			gopp.setHotelLevel(sb.toString());
			gopp.setHotelNum(getHotelNum(grogShopList));
			// 省外交通
			// 根据散客订单统计接机信息
			List<GroupOrderTransport> groupOrderTransports = groupOrderTransportService.selectByOrderId(order.getId());
			gopp.setAirPickup(getAirInfo(groupOrderTransports, 0));
			// 根据散客订单统计送机信息
			gopp.setAirOff(getAirInfo(groupOrderTransports, 1));
			// 省内交通
			gopp.setTrans(getSourceType(groupOrderTransports));
			gopps.add(gopp);
		}

		List<BookingDelivery> bds = deliveryService.getDeliveryListByGroupId(groupId);
		String deliveryDetail = getDeliveryInfo(bds);

		PreviewFitTransferResult result = new PreviewFitTransferResult();

		result.setOperatormobile(platformEmployeeService.findByEmployeeId(tourGroup.getOperatorId()).getMobile());
		result.setDeliveryDetail(deliveryDetail);
		result.setDriverString(driverString);
		result.setGopps(gopps);
		result.setGuideString(guideString);
		result.setTotal(total);
		result.setTourGroup(tourGroup);

		return result;
	}
	
	@Override
	public CreateIndividualResult createIndividual(Integer groupId) {
		/**
		 * 地接社信息
		 */
		List<BookingDelivery> bds = deliveryService.getDeliveryListByGroupId(groupId);
		String deliveryDetail = getDeliveryInfo(bds);
		TourGroup tourGroup = tourGroupService.selectByPrimaryKey(groupId);
		List<BookingGuide> guides = bookingGuideService
				.selectGuidesByGroupId(groupId);
		String guideString = "";
		String driverString = "";
		if (guides.size() > 0) {
			guideString = getGuides(guides);
			driverString = getDrivers(guides);
		}
		//预定车信息
		List<BookingSupplier> bs4 = bookingSupplierService.getBookingSupplierByGroupIdAndSupplierType(groupId, 4) ;
		StringBuilder sbsb = new StringBuilder() ;
		for (BookingSupplier bs : bs4) {
			List<BookingSupplierDetail> details = detailService.selectByPrimaryBookId(bs.getId()) ;
			for (BookingSupplierDetail bsd : details) {
				sbsb.append(bsd.getDriverName()+" "+bsd.getDriverTel()+" "+bsd.getCarLisence()+"\n") ;
			}
		}
		driverString = sbsb.toString() ;
		List<GroupOrder> orders = groupOrderService
				.selectOrderByGroupId(groupId);
		List<GroupOrderPrintPo> gopps = new ArrayList<GroupOrderPrintPo>();
		GroupOrderPrintPo gopp = null;
		String total = getHotelTotalNum(orders) ;
		for (GroupOrder order : orders) {
			// 拿到单条订单信息
			gopp = new GroupOrderPrintPo();
			// gopp.setReceiveMode(order.getReceiveMode());
			gopp.setSupplierName(order.getSupplierName()+"\n"+order.getContactName());
			gopp.setSaleOperatorName(order.getSaleOperatorName());
			gopp.setRemark(order.getRemarkInternal());
			gopp.setPlace((order.getProvinceName() == null ? "" : order
					.getProvinceName())
					+ (order.getCityName() == null ? "" : order.getCityName()));
			// 根据散客订单统计人数
			/*Integer numAdult = groupOrderGuestService
					.selectNumAdultByOrderID(order.getId());
			Integer numChild = groupOrderGuestService
					.selectNumChildByOrderID(order.getId());
			gopp.setPersonNum((numAdult == null ? 0 : numAdult) + "+"
					+ (numChild == null ? 0 : numChild));*/
			gopp.setPersonNum(order.getNumAdult()+"+"+order.getNumChild());
			// 根据散客订单统计客人信息
			List<GroupOrderGuest> guests = groupOrderGuestService
					.selectByOrderId(order.getId());
			/*for (GroupOrderGuest guest : guests) {
				if (guest.getIsLeader() == 1) {
					gopp.setGuesStatic(guest.getName() + " " + guests.size()
							+ "人" + "\n" + guest.getMobile());
					break;
				}
			}
			if (gopp.getGuesStatic() == null || gopp.getGuesStatic() == "") {
				// 如果客人中没有领队，默认取一条数据显示
				gopp.setGuesStatic(guests.get(0).getName() + "\n"
						+ guests.get(0).getMobile());
			}*/
			gopp.setGuesStatic(order.getReceiveMode());
			gopp.setGuestInfo(getGuestInfo2(guests));

			// 根据散客订单统计酒店信息
			List<GroupRequirement> grogShopList = groupRequirementService
					.selectByOrderAndType(order.getId(), 3);
			StringBuilder sb = new StringBuilder();
			for (GroupRequirement gsl : grogShopList) {
				if (gsl.getHotelLevel() != null) {
					sb.append(dicService.getById(Integer.parseInt(gsl.getHotelLevel()))
							.getValue() + "\n");
				}
			}
			gopp.setHotelLevel(sb.toString());
			gopp.setHotelNum(getHotelNum(grogShopList));
			// 省外交通
			// 根据散客订单统计接机信息
			List<GroupOrderTransport> groupOrderTransports = groupOrderTransportService
					.selectByOrderId(order.getId());
			gopp.setAirPickup(getAirInfo(groupOrderTransports, 0));
			// 根据散客订单统计送机信息
			gopp.setAirOff(getAirInfo(groupOrderTransports, 1));
			// 省内交通
			gopp.setTrans(getSourceType(groupOrderTransports));
			gopps.add(gopp);
		}
		
		CreateIndividualResult result = new CreateIndividualResult();
		result.setDeliveryDetail(deliveryDetail);
		result.setDriverString(driverString);
		result.setGopps(gopps);
		result.setGuideString(guideString);
		result.setTotal(total);
		result.setTourGroup(tourGroup);

		return result;
	}
	
	//下面为公共方法抽取
	/**
	 * 返回地接社信息
	 */
	public String getDeliveryInfo(List<BookingDelivery> bds) {
		StringBuilder sb = new StringBuilder();
		for (BookingDelivery bd : bds) {
			sb.append(bd.getSupplierName() + " " + bd.getContact() + " "
					+ bd.getContactMobile() + " " + "Tel:" + bd.getContactTel()
					+ " " + "Fax:" + bd.getContactFax() + "\n");
		}
		return sb.toString();
	}

	/**
	 * 返回客人信息
	 * 
	 * @param guests
	 * @return
	 */
	public String getGuestInfo(List<GroupOrderGuest> guests) {
		StringBuilder sb = new StringBuilder();
		for (GroupOrderGuest guest : guests) {
			sb.append(guest.getName() + " " + guest.getMobile() + "\n"
					+ guest.getCertificateNum() + "\n");
		}
		return sb.toString();
	}
	
	/**
	 * 返回客人信息
	 * 格式如下：
	 * 	张三(13787654321)  530111198307276576   有手机号的显示方式
	 *  李四  530111198307276576				 没有手机号的显示形式
	 * @param guests
	 * @return
	 */
	public String getGuestInfo2(List<GroupOrderGuest> guests) {
		StringBuilder sb = new StringBuilder();
		if(guests == null || guests.size() == 0){
			return sb.toString();
		}
		
		GroupOrderGuest guest = null;
		for (int i = 0; i < guests.size(); i++) {
			if(i > 0){
				sb.append("\n");
			}
			guest = guests.get(i);
			sb.append(guest.getName());
			if(StringUtils.isNotEmpty(guest.getMobile())){
				sb.append("【"+ guest.getMobile() +"】  ");
			}
			sb.append(guest.getCertificateNum());
			
		}
		return sb.toString();
	}

	/**
	 * 返回客人信息
	 * 格式如下：
	 * 	张三（客源地） 530111198307276576   
	 * @param guests
	 * @return
	 */
	public String getGuestInfo3(List<GroupOrderGuest> guests) {
		StringBuilder sb = new StringBuilder();
		if(guests == null || guests.size() == 0){
			return sb.toString();
		}
		
		GroupOrderGuest guest = null;
		for (int i = 0; i < guests.size(); i++) {
			if(i > 0){
				sb.append("\n");
			}
			guest = guests.get(i);
			sb.append(guest.getName());
			if(StringUtils.isNotEmpty(guest.getNativePlace())){
				sb.append("【"+ guest.getNativePlace() +"】  ");
			}
			sb.append(guest.getCertificateNum());
			
		}
		return sb.toString();
	}
	/**
	 * 返回客人信息(不包含电话号码)
	 * 
	 * @param guests
	 * @return
	 */
	public String getGuestInfoNoPhone(List<GroupOrderGuest> guests) {
		StringBuilder sb = new StringBuilder();
		for (GroupOrderGuest guest : guests) {
			sb.append(guest.getName() + " " + guest.getCertificateNum() + "\n");
		}
		return sb.toString();
	}

	/**
	 * 统计单个订单酒店信息
	 * 
	 * @param grogShopList
	 * @return
	 */
	public String getHotelInfo(List<GroupRequirement> grogShopList) {
		StringBuilder sb = new StringBuilder();
		if (grogShopList.size() > 0) {
			String ll = "";
			String sr = "";
			String dr = "";
			String tr = "";
			GroupRequirement gr = grogShopList.get(0);
			if (gr.getHotelLevel() != null) {
				ll = dicService.getById(Integer.parseInt(gr.getHotelLevel())).getValue() + "\n";
			}
			if (gr.getCountSingleRoom() != null && gr.getCountSingleRoom() != 0) {
				sr = gr.getCountSingleRoom() + "单间" + " ";
			}
			if (gr.getCountDoubleRoom() != null && gr.getCountDoubleRoom() != 0) {
				dr = gr.getCountDoubleRoom() + "标间" + " ";
			}
			if (gr.getCountTripleRoom() != null && gr.getCountTripleRoom() != 0) {
				tr = gr.getCountTripleRoom() + "三人间";
			}
			sb.append(ll + sr + dr + tr);
		}
		return sb.toString();
	}

	/**
	 * 统计所有订单酒店总房间数
	 * 
	 * @param grogShopList
	 * @return
	 */
	public String getHotelTotalNum(List<GroupOrder> orders) {
		StringBuilder sb = new StringBuilder();
		Integer sr = 0;
		Integer dr = 0;
		Integer tr = 0;
		Integer eb = 0;
		Integer pf = 0;
		for (GroupOrder order : orders) {
			List<GroupRequirement> grogShopList = groupRequirementService
					.selectByOrderAndType(order.getId(), 3);
			for (GroupRequirement gr : grogShopList) {
				if (gr.getCountSingleRoom() != null
						&& gr.getCountSingleRoom() != 0) {
					sr += gr.getCountSingleRoom();
				}
				if (gr.getCountDoubleRoom() != null
						&& gr.getCountDoubleRoom() != 0) {
					dr += gr.getCountDoubleRoom();
				}
				if (gr.getCountTripleRoom() != null
						&& gr.getCountTripleRoom() != 0) {
					tr += gr.getCountTripleRoom();
				}
				if (gr.getExtraBed() != null && gr.getExtraBed() != 0) {
					eb += gr.getExtraBed();
				}
				if (gr.getPeiFang() != null && gr.getPeiFang() != 0) {
					pf += gr.getPeiFang();
				}
			}
		}

		if (sr != 0) {
			sb.append(sr + "单 ");
		}
		if (dr != 0) {
			sb.append(dr + "标 ");
		}
		if (tr != 0) {
			sb.append(tr + "三人间 ");
		}
		if (eb != 0) {
			sb.append(eb + "加床 ");
		}
		if (pf != 0) {
			sb.append(pf + "陪房 ");
		}
		return sb.toString();
	}

	/**
	 * 统计单个订单酒店总房间数
	 * 
	 * @param grogShopList
	 * @return
	 */
	public String getHotelNum(List<GroupRequirement> grogShopList) {
		StringBuilder sb = new StringBuilder();
		Integer sr = 0;
		Integer dr = 0;
		Integer tr = 0;
		Integer eb = 0;
		Integer pf = 0;
		for (GroupRequirement gr : grogShopList) {
			if (gr.getCountSingleRoom() != null && gr.getCountSingleRoom() != 0) {
				sr += gr.getCountSingleRoom();
			}
			if (gr.getCountDoubleRoom() != null && gr.getCountDoubleRoom() != 0) {
				dr += gr.getCountDoubleRoom();
			}
			if (gr.getCountTripleRoom() != null && gr.getCountTripleRoom() != 0) {
				tr += gr.getCountTripleRoom();
			}
			if (gr.getExtraBed() != null && gr.getExtraBed() != 0) {
				eb += gr.getExtraBed();
			}
			if (gr.getPeiFang() != null && gr.getPeiFang() != 0) {
				pf += gr.getPeiFang();
			}
		}
		if (sr != 0) {
			sb.append(sr + "单 ");
		}
		if (dr != 0) {
			sb.append(dr + "标 ");
		}
		if (tr != 0) {
			sb.append(tr + "三人间 ");
		}
		if (eb != 0) {
			sb.append(eb + "加床 ");
		}
		if (pf != 0) {
			sb.append(pf + "陪房 ");
		}
		return sb.toString();
	}

	/**
	 * 接送信息
	 * 
	 * @param groupOrderTransports
	 * @param flag
	 *            0表示接信息 1表示送信息
	 * @return
	 */
	public String getAirInfo(List<GroupOrderTransport> groupOrderTransports,
			Integer flag) {
		StringBuilder sb = new StringBuilder();
		if (flag == 0) {
			for (GroupOrderTransport transport : groupOrderTransports) {
				if (transport.getType() == 0 && transport.getSourceType() == 1) {
					sb.append(
						(transport.getDepartureCity()==null?"":transport.getDepartureCity()) + "/"
						+ (transport.getArrivalCity()==null?"":transport.getArrivalCity()) + " "
						+ (transport.getClassNo()==null?"":transport.getClassNo()) + " " 
						+ " 发出时间："+(DateUtils.format(transport.getDepartureDate(),"MM-dd")==null?"":DateUtils.format(transport.getDepartureDate(),"MM-dd")) 
						+ " "
						+(transport.getDepartureTime()==null?"":transport.getDepartureTime()) + "\n");
				}
			}
		}
		if (flag == 1) {
			for (GroupOrderTransport transport : groupOrderTransports) {
				if (transport.getType() == 1 && transport.getSourceType() == 1) {
					sb.append(
						(transport.getDepartureCity()==null?"":transport.getDepartureCity()) + "/"
						+ (transport.getArrivalCity()==null?"":transport.getArrivalCity()) + " "
						+ (transport.getClassNo()==null?"":transport.getClassNo()) + " " 
						+ " 发出时间："+(DateUtils.format(transport.getDepartureDate(),"MM-dd")==null?"":DateUtils.format(transport.getDepartureDate(),"MM-dd")) 
						+ " "
						+(transport.getDepartureTime()==null?"":transport.getDepartureTime()) + "\n");
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 省内交通
	 * 
	 * @param groupOrderTransports
	 * @param flag
	 *            0表示接信息 1表示送信息
	 * @return
	 */
	public String getSourceType(List<GroupOrderTransport> groupOrderTransports) {
		StringBuilder sb = new StringBuilder();
		for (GroupOrderTransport transport : groupOrderTransports) {
			if (transport.getSourceType() == 0) {
				sb.append(
						(transport.getDepartureCity()==null?"":transport.getDepartureCity()) + "/"
						+ (transport.getArrivalCity()==null?"":transport.getArrivalCity()) + " "
						+ (transport.getClassNo()==null?"":transport.getClassNo()) + " " 
						+ " 发出时间："+ (DateUtils.format(transport.getDepartureDate(),"MM-dd")==null?"":DateUtils.format(transport.getDepartureDate(),"MM-dd")) 
						+ " "
						+(transport.getDepartureTime()==null?"":transport.getDepartureTime()) + "\n");
			}
		}
		return sb.toString();
	}
	
	public String getMD(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat s = new SimpleDateFormat("MM-dd");
		Date dd = null;
		try {
			dd = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return date;
		}
		return s.format(dd);
	}

	public String getHM(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat s = new SimpleDateFormat("HH:mm");
		Date dd = null;
		try {
			dd = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		return s.format(dd);
	}
	
	/**
	 * 客人姓名+身份证号
	 * 
	 * @param guests
	 * @return
	 */
	public String getCertificateNums(List<GroupOrderGuest> guests) {
		StringBuilder sb = new StringBuilder();
		for (GroupOrderGuest guest : guests) {
			sb.append(guest.getName() + " " + guest.getCertificateNum() + "\n");
		}
		return sb.toString();
	}

	/**
	 * 组织所有导游信息
	 * @param guides
	 * @return
	 */
	public String getGuides(List<BookingGuide> guides){
		StringBuilder sb = new StringBuilder();
		SupplierGuide sg = null;
		for (BookingGuide guide : guides) {
			sg = guideService.getGuideInfoById(guide.getGuideId());
			sb.append(guide.getGuideName()+" "+guide.getGuideMobile()+" "+sg.getLicenseNo()+"\n") ;
		}
		
		return sb.toString() ;
	}

	/**
	 * 组织所有司机信息
	 */
	public String getDrivers(List<BookingGuide> guides) {
		StringBuilder sb = new StringBuilder();
		for (BookingGuide guide : guides) {
			BookingSupplierDetail bsd = detailService.selectByPrimaryKey(guide.getBookingDetailId());
			if (bsd != null) {
				sb.append(bsd.getDriverName() + " " + bsd.getDriverTel() + "\n");
			}
		}
		return sb.toString();
	}
}
