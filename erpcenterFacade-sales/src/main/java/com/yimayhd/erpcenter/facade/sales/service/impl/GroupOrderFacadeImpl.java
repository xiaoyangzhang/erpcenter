package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
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
import com.yimayhd.erpcenter.dal.basic.constant.BasicConstants;
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
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteAttachment;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteSupplier;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteTraffic;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteDayVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.MergeGroupOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SalePrice;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
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
import com.yimayhd.erpcenter.facade.sales.service.GroupOrderFacade;
import com.yimayhd.erpcenter.facade.sales.utils.GroupCodeUtil;
import com.yimayhd.erpcenter.facade.sales.utils.MergeGroupUtils;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.biz.service.SupplierGuideBiz;
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
			DicInfo dicInfo = dicService.getById(tourGroup.getPrudctBrandId() + "");
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
		
		 BaseStateResult baseStateResult=new BaseStateResult();
		 baseStateResult.setSuccess(false);
		 
		 MergeGroupOrderVO mergeGroupOrderVO=mergeGroupDTO.getMergeGroupOrderVO();
		 
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

		groupOrderService.mergeGroup(result, 
				mergeGroupDTO.getBizId(), 
				mergeGroupDTO.getUserId(),
				mergeGroupDTO.getUserName(),
				mergeGroupDTO.getBizCode());
		
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
		
		return new BaseStateResult(true,null);
	}
	
	@Override //FIXME 此方法重复
	public BaseStateResult beforeInsertGroup(String ids) {
		
		BaseStateResult result=new BaseStateResult();
		result.setSuccess(false);
		
		String[] split = ids.split(",");
		List<String> datelist = new ArrayList<String>();
		
//		List<Integer> productlist = new ArrayList<Integer>();
//		List<Integer> brandlist = new ArrayList<Integer>();
//		List<Integer> statelist = new ArrayList<Integer>();
		
		for (String id : split) {
			GroupOrder groupOrder = groupOrderService.findById(Integer.parseInt(id));
			datelist.add(groupOrder.getDepartureDate());
//			productlist.add(groupOrder.getProductId());
//			statelist.add(groupOrder.getStateFinance());
//			brandlist.add(groupOrder.getProductBrandId());
			List<GroupOrderGuest> guestList = groupOrderGuestService.selectByOrderId(Integer.parseInt(id));
			if (guestList == null || guestList.size() == 0) {
				result.setError("订单号:" + groupOrder.getOrderNo()+ "无客人信息,无法并团!");
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
		
		BaseStateResult result=new BaseStateResult();
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
				result.setError("订单号:" + groupOrder.getOrderNo()+ "无客人信息,无法并团!");
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
		
		ToMergeGroupResult result=new ToMergeGroupResult();
		result.setList(list);
		return result;
	}
	
	@Override
	public ToNotGroupListResult toNotGroupList(ToNotGroupListDTO toNotGroupListDTO) throws ParseException{
		
		ToNotGroupListResult returnResult=new ToNotGroupListResult();
		
		GroupOrder groupOrder=toNotGroupListDTO.getGroupOrder();
		Integer bizId=toNotGroupListDTO.getBizId();
		Set<Integer> userIdSet=toNotGroupListDTO.getUserIdSet();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (groupOrder.getReqType() != null && groupOrder.getReqType() == 0) {
			groupOrder.setDateType(1);
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			c.set(year, month, 1);
			groupOrder.setDepartureDate(c.get(Calendar.YEAR) + "-"
					+ (c.get(Calendar.MONTH) + 1) + "-01");
			c.set(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			groupOrder.setEndTime(c.get(Calendar.YEAR) + "-"
					+ (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE));
		}

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(groupOrder.getPageSize() == null ? Constants.PAGESIZE : groupOrder.getPageSize());
		pageBean.setPage(groupOrder.getPage());
		pageBean.setParameter(groupOrder);
		
		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(groupOrder.getSaleOperatorIds())
				&& StringUtils.isNotBlank(groupOrder.getOrgIds())) {
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
				groupOrder.setSaleOperatorIds(salesOperatorIds.substring(0,
						salesOperatorIds.length() - 1));
			}
		}
		//model.addAttribute("groupOrder", groupOrder);
		returnResult.setGroupOrder(groupOrder);

		if (groupOrder.getDateType()!=null && groupOrder.getDateType() == 2) {
			if (groupOrder.getDepartureDate() != null) {
				groupOrder.setDepartureDate(sdf.parse(
						groupOrder.getDepartureDate()).getTime()
						+ "");
			}
			if (groupOrder.getEndTime() != null) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(sdf.parse(groupOrder.getEndTime()));
				calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
				groupOrder.setEndTime(calendar.getTime().getTime() + "");
			}

		}

		pageBean = groupOrderService.selectNotGroupListPage(pageBean,bizId,userIdSet);

		GroupOrder order = groupOrderService.selectTotalNotGroup(groupOrder,bizId,userIdSet);

		if (groupOrder.getDateType()!=null && groupOrder.getDateType() == 2) {
			if (groupOrder.getDepartureDate() != null) {
				groupOrder.setDepartureDate(sdf.format(new Date(Long
						.valueOf(groupOrder.getDepartureDate()))));
			}
			if (groupOrder.getEndTime() != null) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTimeInMillis(Long.valueOf(groupOrder.getEndTime()));
				calendar.add(calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
				groupOrder.setEndTime(sdf.format(calendar.getTime()));
			}
		}
		
//		model.addAttribute("totalAudit",order == null ? 0 : order.getNumAdult());
//		model.addAttribute("totalChild",order == null ? 0 : order.getNumChild());
//		model.addAttribute("total", order == null ? 0 : order.getTotal());
		returnResult.setTotalAudit(order == null ? 0 : order.getNumAdult());
		returnResult.setTotalChild(order == null ? 0 : order.getNumChild());
		returnResult.setTotal(order.getTotal());

		List<GroupOrder> result = pageBean.getResult();
		Integer pageTotalAudit = 0;
		Integer pageTotalChild = 0;
		BigDecimal pageTotal = new BigDecimal(0);

		for (GroupOrder groupOrder2 : result) {
			List<GroupRoute> list = groupRouteService
					.selectByOrderId(groupOrder2.getId());
			if (list != null && list.size() > 0) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(sdf.parse(groupOrder2.getDepartureDate()));
				cal.add(Calendar.DAY_OF_MONTH, +(list.size() - 1));
				Date time = cal.getTime();
				groupOrder2.setFitDate(sdf.format(time));
			}
			pageTotalAudit += groupOrder2.getNumAdult();
			pageTotalChild += groupOrder2.getNumChild();
			pageTotal = pageTotal
					.add(groupOrder2.getTotal() == null ? new BigDecimal(0)
							: groupOrder2.getTotal());
			
			if (groupOrder2.getCreateTime() != null) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Long createTime = groupOrder2.getCreateTime();
				String dateStr = sf.format(createTime);
				groupOrder2.setCreateTimeStr(dateStr);
			}
			
		}
//		model.addAttribute("pageTotalAudit", pageTotalAudit);
//		model.addAttribute("pageTotalChild", pageTotalChild);
//		model.addAttribute("pageTotal", pageTotal);
		returnResult.setPageTotalAudit(pageTotalAudit);
		returnResult.setPageTotalChild(pageTotalChild);
		returnResult.setPageTotal(pageTotal);

		List<DicInfo> pp = dicService.getListByTypeCode(BasicConstants.CPXL_PP,bizId);
		
//		model.addAttribute("pp", pp);
//		model.addAttribute("page", pageBean);
		returnResult.setPp(pp);
		returnResult.setPageBean(pageBean);

		List<RegionInfo> allProvince = regionService.getAllProvince();
		//model.addAttribute("allProvince", allProvince);
		returnResult.setAllProvince(allProvince);
		
		List<RegionInfo> cityList = null;
		if (groupOrder.getProvinceId() != null
				&& groupOrder.getProvinceId() != -1) {
			cityList = regionService.getRegionById(groupOrder.getProvinceId()
					+ "");
		}
//		model.addAttribute("allCity", cityList);
//		Integer bizId = WebUtils.getCurBizId(request);
//		model.addAttribute("orgJsonStr",
//				orgService.getComponentOrgTreeJsonStr(bizId));
//		model.addAttribute("orgUserJsonStr",
//				sysPlatformEmployeeFacade.getComponentOrgUserTreeJsonStr(bizId));
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
			groupOrder.setEndTime(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE));
			groupOrder.setDateType(1);
		}
		
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(groupOrder.getPageSize() == null ? Constants.PAGESIZE : groupOrder.getPageSize());
		pageBean.setPage(groupOrder.getPage());
		pageBean.setParameter(groupOrder);
		pageBean = groupOrderService.selectNotGroupListPage(pageBean, bizId,userIdSet);
		List<DicInfo> pp = dicService.getListByTypeCode(BasicConstants.CPXL_PP, bizId);
		
		ToSecImpNotGroupListResult result=new ToSecImpNotGroupListResult();
		
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
			groupOrder.setEndTime(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE));

		}
		String idLists=toImpNotGroupListDTO.getIdLists();
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
		pageBean = groupOrderService.selectNotGroupListPage(pageBean, 
				toImpNotGroupListDTO.getBizId(),
				toImpNotGroupListDTO.getUserIdSet());
		List<DicInfo> pp = dicService.getListByTypeCode(BasicConstants.CPXL_PP, toImpNotGroupListDTO.getBizId());
		
		ToImpNotGroupListResult result=new ToImpNotGroupListResult();
		result.setGroupOrder(groupOrder);
		result.setPageBean(pageBean);
		result.setPp(pp);
		return result;
	}
	
	@Override
	public BaseStateResult delGroupOrder(Integer curBizId, Integer id) {
		
		BaseStateResult result=new BaseStateResult();
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
		
		BaseStateResult result=new BaseStateResult();
		result.setSuccess(false);
		
		GroupOrderVO groupOrderVO=addGroupOrderDTO.getGroupOrderVO();
		Integer priceId=addGroupOrderDTO.getPriceId();
		Integer priceGroupId=addGroupOrderDTO.getPriceGroupId();

		ProductRouteVo productRouteVo = productRouteService
				.findByProductId(groupOrderVO.getGroupOrder().getProductId());
		GroupRouteVO groupRouteVO = new GroupRouteVO();
		// -----------------------------------------------------
		List<GroupRouteDayVO> groupRouteDayVOList = new ArrayList<GroupRouteDayVO>();
		List<ProductRouteDayVo> productRoteDayVoList = productRouteVo
				.getProductRoteDayVoList();
		// -----------------------------------------------------
		if (productRoteDayVoList != null && productRoteDayVoList.size() > 0) {
			for (int i = 0; i < productRoteDayVoList.size(); i++) {
				ProductRoute productRoute = productRoteDayVoList.get(i)
						.getProductRoute();
				GroupRoute groupRoute = new GroupRoute();
				GroupRouteDayVO groupRouteDayVO = new GroupRouteDayVO();
				try {
					BeanUtils.copyProperties(groupRoute, productRoute);
					groupRoute.setId(null);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					cal.setTime(sdf.parse(groupOrderVO.getGroupOrder()
							.getDepartureDate()));
					cal.add(Calendar.DAY_OF_MONTH, +(i + 1));
					Date time = cal.getTime();
					groupRoute.setGroupDate(time);
					List<ProductAttachment> productAttachmentsList = productRoteDayVoList
							.get(i).getProductAttachments();
					List<GroupRouteAttachment> groupRouteAttachmentList = new ArrayList<GroupRouteAttachment>();
					if (productAttachmentsList != null
							&& productAttachmentsList.size() > 0) {
						GroupRouteAttachment groupRouteAttachment = new GroupRouteAttachment();
						for (ProductAttachment productAttachment : productAttachmentsList) {
							BeanUtils.copyProperties(groupRouteAttachment,
									productAttachment);
							groupRouteAttachment.setId(null);
							groupRouteAttachmentList.add(groupRouteAttachment);
						}
					}

					List<ProductRouteTraffic> productRouteTrafficList = productRoteDayVoList
							.get(i).getProductRouteTrafficList();
					List<GroupRouteTraffic> groupRouteTrafficList = new ArrayList<GroupRouteTraffic>();
					if (productRouteTrafficList != null
							&& productRouteTrafficList.size() > 0) {
						for (ProductRouteTraffic productRouteTraffic : productRouteTrafficList) {
							GroupRouteTraffic groupRouteTraffic = new GroupRouteTraffic();
							BeanUtils.copyProperties(groupRouteTraffic,
									productRouteTraffic);
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
					List<ProductRouteSupplier> productOptionsSupplierList = productRoteDayVoList
							.get(i).getProductOptionsSupplierList();
					if (productOptionsSupplierList != null
							&& productOptionsSupplierList.size() > 0) {
						for (ProductRouteSupplier productRouteSupplier : productOptionsSupplierList) {
							GroupRouteSupplier groupRouteOptionsSupplier = new GroupRouteSupplier();
							BeanUtils.copyProperties(groupRouteOptionsSupplier,
									productRouteSupplier);
							groupRouteOptionsSupplier.setId(null);
							groupRouteOptionsSupplierList
									.add(groupRouteOptionsSupplier);
						}
					}
					groupRouteDayVO.setGroupRoute(groupRoute);
					groupRouteDayVO
							.setGroupRouteAttachmentList(groupRouteAttachmentList);
					groupRouteDayVO
							.setGroupRouteOptionsSupplierList(groupRouteOptionsSupplierList);
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
		List<GroupOrderGuest> groupOrderGuestList2 = groupOrderVO
				.getGroupOrderGuestList();
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
			ProductGroupPrice productGroupPrice = productGroupPriceService
					.selectByPrimaryKey(priceId).getGroupPrice();

			// ----------------------收入----------------------

			GroupOrderPrice audltIn = new GroupOrderPrice();
			audltIn.setCreateTime(System.currentTimeMillis());
			audltIn.setCreatorId(addGroupOrderDTO.getUserId());
			audltIn.setCreatorName(addGroupOrderDTO.getUserName());
			audltIn.setMode(0);
			audltIn.setNumPerson(Double.valueOf(audlt.size()+""));
			audltIn.setUnitPrice(productGroupPrice.getPriceSettlementAdult()
					.doubleValue());
			audltIn.setNumTimes(1.0);
			totalIncome += audlt.size()
					* productGroupPrice.getPriceSettlementAdult().doubleValue()
					* 1;
			audltIn.setTotalPrice(audlt.size()
					* productGroupPrice.getPriceSettlementAdult().doubleValue()
					* 1);
			DicInfo dicInfo = dicService.getDicInfoByTypeCodeAndDicCode(
					BasicConstants.GGXX_LYSFXM, BasicConstants.CR);
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
			childIn.setNumPerson(Double.valueOf(child.size()+""));
			childIn.setUnitPrice(productGroupPrice.getPriceSettlementChild()
					.doubleValue());
			childIn.setNumTimes(1.0);
			totalIncome += child.size()
					* productGroupPrice.getPriceSettlementChild().doubleValue()
					* 1;
			childIn.setTotalPrice(child.size()
					* productGroupPrice.getPriceSettlementChild().doubleValue()
					* 1);
			dicInfo = dicService.getDicInfoByTypeCodeAndDicCode(
					BasicConstants.GGXX_LYSFXM, BasicConstants.ERT);
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
				roomIn.setNumPerson(Double.valueOf(isSingle+""));
				roomIn.setUnitPrice(productGroupPrice
						.getPriceSettlementRoomeSpread().doubleValue());
				roomIn.setNumTimes(1.0);
				totalIncome += isSingle
						* productGroupPrice.getPriceSettlementRoomeSpread()
								.doubleValue() * 1;
				roomIn.setTotalPrice(isSingle
						* productGroupPrice.getPriceSettlementRoomeSpread()
								.doubleValue() * 1);
				dicInfo = dicService.getDicInfoByTypeCodeAndDicCode(
						BasicConstants.GGXX_LYSFXM, BasicConstants.DFC);
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
			audltOut.setNumPerson(Double.valueOf(audlt.size()+""));
			audltOut.setUnitPrice(productGroupPrice.getPriceCostAdult()
					.doubleValue());
			audltOut.setNumTimes(1.0);
			audltOut.setTotalPrice(audlt.size()
					* productGroupPrice.getPriceCostAdult().doubleValue() * 1);
			dicInfo = dicService.getDicInfoByTypeCodeAndDicCode(
					BasicConstants.GGXX_LYSFXM, BasicConstants.CR);
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
			childOut.setNumPerson(Double.valueOf(child.size()+""));
			childOut.setUnitPrice(productGroupPrice.getPriceCostChild()
					.doubleValue());
			childOut.setNumTimes(1.0);
			childOut.setTotalPrice(child.size()
					* productGroupPrice.getPriceCostChild().doubleValue() * 1);
			dicInfo = dicService.getDicInfoByTypeCodeAndDicCode(
					BasicConstants.GGXX_LYSFXM, BasicConstants.ERT);
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
				roomIn.setNumPerson(Double.valueOf(isSingle+""));
				roomIn.setUnitPrice(productGroupPrice.getPriceCostRoomSpread()
						.doubleValue());
				roomIn.setNumTimes(1.0);
				roomIn.setTotalPrice(isSingle
						* productGroupPrice.getPriceCostRoomSpread()
								.doubleValue() * 1);
				dicInfo = dicService.getDicInfoByTypeCodeAndDicCode(
						BasicConstants.GGXX_LYSFXM, BasicConstants.DFC);
				roomIn.setItemId(dicInfo.getId());
				roomIn.setItemName(dicInfo.getValue()); // 单房差
				insertList.add(roomIn);
			}

		}
		List<GroupOrderPrice> groupOrderPriceList = groupOrderVO
				.getGroupOrderPriceList();
		if (groupOrderPriceList != null && groupOrderPriceList.size() > 0) {
			for (GroupOrderPrice groupOrderPrice : groupOrderPriceList) {
				totalIncome += groupOrderPrice.getUnitPrice()
						* groupOrderPrice.getNumPerson()
						* groupOrderPrice.getNumTimes();
			}
		}
		groupOrderVO.getGroupOrder().setTotal(new BigDecimal(totalIncome));

		// modified by gejinjun 2105-11-03 防止出现保存出错但是也更新库存的问题，修改为如下逻辑
		try {
			// 修改订单信息
			groupOrderService.saveGroupOrder(groupOrderVO, groupRouteVO,
					insertList);
			// 更新库存信息
			ProductGroup group = productGroupService
					.getGroupInfoById(priceGroupId);
			boolean updateStock = productGroupPriceService.updateStock(priceId,
					group.getGroupSetting() == 0 ? groupOrderVO.getGroupOrder()
							.getSupplierId() : null, groupOrderVO
							.getGroupOrderGuestList().size());
		} catch (Exception ex) {
			result.setError(ex.getMessage());
			return result;
		}
		
		result.setSuccess(true);
		return result;
	}
	
	@Override
	public ToLookGroupOrderResult toLookGroupOrder(Integer id,Integer bizId) {
		
		ToLookGroupOrderResult result=new ToLookGroupOrderResult();
		
		GroupOrder groupOrder = groupOrderService.findById(id);
		groupOrder.setStateFinance(1);
		//model.addAttribute("groupOrder", groupOrder);
		result.setGroupOrder(groupOrder);
		
		PlatformEmployeePo saleEmployeePo = platformEmployeeService.findByEmployeeId(groupOrder.getSaleOperatorId());
		//model.addAttribute("saleEmployeePo", saleEmployeePo);
		result.setSaleEmployeePo(saleEmployeePo);

		PlatformEmployeePo operaEmployeePo = platformEmployeeService.findByEmployeeId(groupOrder.getOperatorId());
		//model.addAttribute("operaEmployeePo", operaEmployeePo);
		result.setOperaEmployeePo(operaEmployeePo);
		
		ProductInfo productInfo = productInfoService.findProductInfoById(groupOrder.getProductId());
		//model.addAttribute("productInfo", productInfo);
		result.setProductInfo(productInfo);
		
		SupplierInfo supplierInfo = supplierService.selectBySupplierId(groupOrder.getSupplierId());
		//model.addAttribute("supplierInfo", supplierInfo);
		result.setSupplierInfo(supplierInfo);
		
		List<DicInfo> jdxjList = dicService.getListByTypeCode(BasicConstants.GYXX_JDXJ);
		//model.addAttribute("jdxjList", jdxjList);
		result.setJdxjList(jdxjList);

		List<GroupOrderPrice> costList = groupOrderPriceService.selectByOrderAndType(groupOrder.getId(), 0); // mode 0：收入
		
		Double income = 0.0;
		if (costList != null && costList.size() > 0) {
			for (GroupOrderPrice groupOrderPrice : costList) {
				income += groupOrderPrice.getTotalPrice();
			}
		}
		//model.addAttribute("income", income);
		result.setIncome(income);

		List<GroupOrderPrice> budgetList = groupOrderPriceService.selectByOrderAndType(groupOrder.getId(), 1);// 1：预算

		Double budget = 0.0;
		if (budgetList != null && budgetList.size() > 0) {
			for (GroupOrderPrice groupOrderPrice : budgetList) {
				budget += groupOrderPrice.getTotalPrice();
			}
		}
		//model.addAttribute("budget", budget);
		result.setBudget(budget);

		//model.addAttribute("costList", costList);
		//model.addAttribute("budgetList", budgetList);
		result.setCostList(costList);
		result.setBudgetList(budgetList);
		
		List<GroupOrderTransport> transportList = groupOrderTransportService.selectByOrderId(groupOrder.getId());
		//model.addAttribute("transportList", transportList);
		result.setTransportList(transportList);
		
		List<DicInfo> jtfsList = dicService.getListByTypeCode(BasicConstants.GYXX_JTFS, bizId);
		//model.addAttribute("jtfsList", jtfsList);
		result.setJtfsList(jtfsList);

		List<DicInfo> zjlxList = dicService.getListByTypeCode(BasicConstants.GYXX_ZJLX);
		//model.addAttribute("zjlxList", zjlxList);
		result.setZjlxList(zjlxList);

		List<DicInfo> lysfxmList = dicService.getListByTypeCode(BasicConstants.GYXX_LYSFXM, bizId);
		//model.addAttribute("lysfxmList", lysfxmList);
		result.setLysfxmList(lysfxmList);

		List<GroupRequirement> restaurantList = groupRequirementService
				.selectByOrderAndType(groupOrder.getId(), Constants.HOTEL);// 酒店
		List<GroupRequirement> airticketagentList = groupRequirementService
				.selectByOrderAndType(groupOrder.getId(),
						Constants.AIRTICKETAGENT); // 机票
		List<GroupRequirement> trainticketagentList = groupRequirementService
				.selectByOrderAndType(groupOrder.getId(),
						Constants.TRAINTICKETAGENT);// 火车票

		//model.addAttribute("restaurantList", restaurantList);
		//model.addAttribute("airticketagentList", airticketagentList);
		//model.addAttribute("trainticketagentList", trainticketagentList);
		result.setRestaurantList(restaurantList);
		result.setAirticketagentList(airticketagentList);
		result.setTrainticketagentList(trainticketagentList);

		List<GroupOrderGuest> guestList = groupOrderGuestService
				.selectByOrderId(groupOrder.getId());
		for (GroupOrderGuest guest : guestList) {
			List<GroupOrderGuest> guests = groupOrderGuestService
					.getGuestByGuestCertificateNum(guest.getCertificateNum(),
							guest.getOrderId());
			guest.setHistoryNum(guests.size());
		}
		//model.addAttribute("guestList", guestList);
		result.setGuestList(guestList);
		
		return result;
	}
	
	@Override
	public ToEditGroupOrderResult toEditGroupOrder(Integer id, int bizId) {
		
		ToEditGroupOrderResult result=new ToEditGroupOrderResult();
	
		GroupOrder groupOrder = groupOrderService.findById(id);
		//model.addAttribute("groupOrder", groupOrder);
		result.setGroupOrder(groupOrder);
		
		ProductInfo productInfo = productInfoService
				.findProductInfoById(groupOrder.getProductId());
		//model.addAttribute("productInfo", productInfo);
		result.setProductInfo(productInfo);

		PlatformEmployeePo saleEmployeePo = platformEmployeeService
				.findByEmployeeId(groupOrder.getSaleOperatorId());
		//model.addAttribute("saleEmployeePo", saleEmployeePo);
		result.setSaleEmployeePo(saleEmployeePo);

		PlatformEmployeePo operaEmployeePo = platformEmployeeService
				.findByEmployeeId(groupOrder.getOperatorId());
		//model.addAttribute("operaEmployeePo", operaEmployeePo);
		result.setOperaEmployeePo(operaEmployeePo);
		
		SupplierInfo supplierInfo = supplierService
				.selectBySupplierId(groupOrder.getSupplierId());
		//model.addAttribute("supplierInfo", supplierInfo);
		result.setSupplierInfo(supplierInfo);
		
		List<DicInfo> jdxjList = dicService
				.getListByTypeCode(BasicConstants.GYXX_JDXJ);
		//model.addAttribute("jdxjList", jdxjList);
		result.setJdxjList(jdxjList);

		List<DicInfo> sourceTypeList = dicService
				.getListByTypeCode(Constants.GUEST_SOURCE_TYPE,bizId);
		//model.addAttribute("sourceTypeList", sourceTypeList);
		result.setSourceTypeList(sourceTypeList);

		List<RegionInfo> allProvince = regionService.getAllProvince();
		//model.addAttribute("allProvince", allProvince);
		result.setAllProvince(allProvince);
		
		List<RegionInfo> cityList = null;
		if (groupOrder.getProvinceId() != null
				&& groupOrder.getProvinceId() != -1) {
			cityList = regionService.getRegionById(groupOrder.getProvinceId()
					+ "");
		}
		//model.addAttribute("allCity", cityList);
		result.setCityList(cityList);

		List<GroupOrderPrice> costList = groupOrderPriceService
				.selectByOrderAndType(groupOrder.getId(), 0); // mode 0：收入
		Double income = 0.0;
		if (costList != null && costList.size() > 0) {

			for (GroupOrderPrice groupOrderPrice : costList) {
				income += groupOrderPrice.getTotalPrice();
			}

		}
		//model.addAttribute("income", income);
		result.setIncome(income);

		List<GroupOrderPrice> budgetList = groupOrderPriceService
				.selectByOrderAndType(groupOrder.getId(), 1);// 1：预算

		Double budget = 0.0;
		if (budgetList != null && budgetList.size() > 0) {
			for (GroupOrderPrice groupOrderPrice : budgetList) {
				budget += groupOrderPrice.getTotalPrice();
			}
		}
		//model.addAttribute("budget", budget);
		result.setBudget(budget);

		//model.addAttribute("costList", costList);
		//model.addAttribute("budgetList", budgetList);
		result.setCostList(costList);
		result.setBudgetList(budgetList);
		
		List<GroupOrderTransport> transportList = groupOrderTransportService
				.selectByOrderId(groupOrder.getId());
		//model.addAttribute("transportList", transportList);
		result.setTransportList(transportList);

		List<DicInfo> jtfsList = dicService
				.getListByTypeCode(BasicConstants.GYXX_JTFS, bizId);
		//model.addAttribute("jtfsList", jtfsList);
		result.setJtfsList(jtfsList);

		List<DicInfo> zjlxList = dicService
				.getListByTypeCode(BasicConstants.GYXX_ZJLX);
		//model.addAttribute("zjlxList", zjlxList);
		result.setZjlxList(zjlxList);
		
		List<DicInfo> lysfxmList = dicService.getListByTypeCode(
				BasicConstants.GYXX_LYSFXM, bizId);
		//model.addAttribute("lysfxmList", lysfxmList);
		result.setLysfxmList(lysfxmList);

		List<GroupRequirement> restaurantList = groupRequirementService
				.selectByOrderAndType(groupOrder.getId(), Constants.HOTEL);// 酒店
		List<GroupRequirement> airticketagentList = groupRequirementService
				.selectByOrderAndType(groupOrder.getId(),
						Constants.AIRTICKETAGENT); // 机票
		List<GroupRequirement> trainticketagentList = groupRequirementService
				.selectByOrderAndType(groupOrder.getId(),
						Constants.TRAINTICKETAGENT);// 火车票

		//model.addAttribute("restaurantList", restaurantList);
		//model.addAttribute("airticketagentList", airticketagentList);
		//model.addAttribute("trainticketagentList", trainticketagentList);
		result.setRestaurantList(restaurantList);
		result.setAirticketagentList(airticketagentList);
		result.setTrainticketagentList(trainticketagentList);

		List<GroupOrderGuest> guestList = groupOrderGuestService
				.selectByOrderId(groupOrder.getId());
		List<Integer> guestIdList = airTicketRequestService.findIssuedGuestIdList(bizId, groupOrder.getId());
		for (GroupOrderGuest guest : guestList) {
			List<GroupOrderGuest> guests = groupOrderGuestService
					.getGuestByGuestCertificateNum(guest.getCertificateNum(),
							guest.getOrderId());
			guest.setHistoryNum(guests.size());
			guest.setEditType(!guestIdList.contains(guest.getId()));
		}
		//model.addAttribute("guestList", guestList);
		result.setGuestList(guestList);

		ProductGroupPrice productGroupPrice = productGroupPriceService
				.selectByPrimaryKey(groupOrder.getPriceId()).getGroupPrice();
		if(productGroupPrice!=null){
		
		ProductGroup groductGroup = productGroupService
				.getGroupInfoById(productGroupPrice.getGroupId());

		if (groductGroup.getGroupSetting() == 0) {

			ProductGroupSupplierVo supplierVosToSales = productGroupSupplierService
					.selectProductGroupSupplierVosToSales(
							productGroupPrice.getGroupId(),
							groupOrder.getPriceId(), groupOrder.getSupplierId());
			if (supplierVosToSales == null) {
//				model.addAttribute(
//						"allowNum",
//						productGroupPrice.getStockCount()
//								- productGroupPrice.getReceiveCount());
				result.setAllowNum(productGroupPrice.getStockCount()-productGroupPrice.getReceiveCount());
			} else {
				if (supplierVosToSales.getStock() == -1) {
//					model.addAttribute("allowNum",
//							productGroupPrice.getStockCount()-productGroupPrice.getReceiveCount());
					result.setAllowNum(productGroupPrice.getStockCount()-productGroupPrice.getReceiveCount());
				} else {
					//model.addAttribute("allowNum",supplierVosToSales.getStock());
					result.setAllowNum(supplierVosToSales.getStock());
				}
			}

		} else {
			//model.addAttribute("allowNum", productGroupPrice.getStockCount()-productGroupPrice.getReceiveCount());
			result.setAllowNum(productGroupPrice.getStockCount()-productGroupPrice.getReceiveCount());
		}
		}else{
			//model.addAttribute("allowNum",10000);
			result.setAllowNum(10000);
		}
		
		return result;
	}
}
