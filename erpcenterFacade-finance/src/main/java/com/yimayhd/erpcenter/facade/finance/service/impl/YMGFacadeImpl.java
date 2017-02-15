package com.yimayhd.erpcenter.facade.finance.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.yimayhd.erpcenter.facade.finance.result.QueryYmgOrderListByOpTable;
import org.yimayhd.erpcenter.facade.finance.result.QueryYmgOrderListTableDataResult;
import org.yimayhd.erpcenter.facade.finance.result.SaveSpecialGroupResult;
import org.yimayhd.erpcenter.facade.finance.result.ToEditTaobaoOrderResult;
import org.yimayhd.erpcenter.facade.finance.service.YMGFacade;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.LogOperatorBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductStockBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryPriceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderPriceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderTransportBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.SpecialGroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.taobao.TaobaoOrderBiz;
import com.yimayhd.erpcenter.biz.sys.service.MsgInfoBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.dal.basic.constant.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.LogOperator;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStockDate;
import com.yimayhd.erpcenter.dal.sales.client.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.MergeGroupOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SpecialGroupOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.taobao.po.PlatTaobaoTrade;
import com.yimayhd.erpcenter.dal.sys.po.MsgInfo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.facade.finance.utils.LogUtils;

public class YMGFacadeImpl implements YMGFacade{
	
	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz; 
	
	@Autowired
	private GroupOrderBiz groupOrderBiz;
	
	@Autowired
	private DicBiz dicBiz;
	
	@Autowired
	private ProductInfoBiz productInfoBiz;
	
	@Autowired
	private ProductStockBiz productStockBiz;
	
	@Autowired
	private GroupOrderPriceBiz groupOrderPriceBiz;
	
	@Autowired
	private GroupOrderGuestBiz groupOrderGuestBiz;
	
	@Autowired
	private GroupOrderTransportBiz groupOrderTransportBiz;
	
	@Autowired
	private SpecialGroupOrderBiz specialGroupOrderBiz;
	
	@Autowired
	private TourGroupBiz tourGroupBiz;
	
	@Autowired
	private PlatformOrgBiz orgBiz;
	
	@Autowired
	private LogOperatorBiz logBiz;
	
	@Autowired
	private RegionBiz regionBiz;
	
	@Autowired
	private TaobaoOrderBiz taobaoOrderBiz;
	
	@Autowired
	private BookingDeliveryBiz deliveryBiz;
	
	@Autowired
	private MsgInfoBiz msgInfoBiz;
	
	@Autowired
	private BookingSupplierBiz bookingSupplierBiz;
	
	@Autowired
	private BookingDeliveryPriceBiz bookingDeliveryPriceBiz;
	
	@Override
	public QueryYmgOrderListTableDataResult queryYmgOrderListTableData(String sidx, String sord, Integer rows, 
			GroupOrder groupOrder, Integer bizId, Set<Integer> dataUserIdSet) throws ParseException {
		
		groupOrder.setSidx(sidx);// 来获得排序的列名，
		groupOrder.setSord(sord);// 来获得排序方式
		if (groupOrder.getDateType() != null && groupOrder.getDateType() == 2) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (!"".equals(groupOrder.getStartTime())) {
				groupOrder.setStartTime(sdf.parse(groupOrder.getStartTime()).getTime() + "");
			}
			if (!"".equals(groupOrder.getEndTime())) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(sdf.parse(groupOrder.getEndTime()));
				calendar.add(Calendar.DAY_OF_MONTH, +1);// 让日期加1
				groupOrder.setEndTime(calendar.getTime().getTime() + "");
			}

		}

		if (StringUtils.isBlank(groupOrder.getSaleOperatorIds()) && StringUtils.isNotBlank(groupOrder.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = groupOrder.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				groupOrder.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}
		PageBean<GroupOrder> page = new PageBean<GroupOrder>();
		if (!"".equals(groupOrder.getGuestName()) || !"".equals(groupOrder.getMobile())) {
			page.setParameter(groupOrder);
			page.setPage(groupOrder.getPage() == null ? 1 : groupOrder.getPage());
			page.setPageSize(rows);
			page = groupOrderBiz.selectTaobaoOrderGuestNameListPage(page, bizId,
					dataUserIdSet, 1);
		} else {
			page.setParameter(groupOrder);
			page.setPage(groupOrder.getPage() == null ? 1 : groupOrder.getPage());
			page.setPageSize(rows);
			page = groupOrderBiz.selectTaobaoOrderListPage(page, bizId,
					dataUserIdSet, 1);
		}
		List<GroupOrder> list = page.getResult();
		Integer pageTotalAudit = 0;
		Integer pageTotalChild = 0;
		Integer pageTotalGuide = 0;
		List<DicInfo> typeList = dicBiz.getListByTypeCode(BasicConstants.SALES_TEAM_TYPE, bizId);
		BigDecimal pageTotal = new BigDecimal(0);
		if (page.getResult() != null && page.getResult().size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (GroupOrder groupOrder2 : list) {
				pageTotalAudit += groupOrder2.getNumAdult() == null ? 0 : groupOrder2.getNumAdult();
				pageTotalChild += groupOrder2.getNumChild() == null ? 0 : groupOrder2.getNumChild();
				pageTotalGuide += groupOrder2.getNumGuide() == null ? 0 : groupOrder2.getNumGuide();
				pageTotal = pageTotal.add(groupOrder2.getTotal() == null ? new BigDecimal(0) : groupOrder2.getTotal());
				Long createTime = groupOrder2.getCreateTime();
				String dateStr = sdf.format(createTime);
				groupOrder2.setCreateTimeStr(dateStr);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String today = formatter.format(new Date());
				groupOrder2.setFitDate(today);
				for (DicInfo item : typeList) {
					if (item.getId().equals(groupOrder2.getOrderMode()))
						groupOrder2.setOrderModeType(item.getValue());
				}
				if (groupOrder2.getProductId()!=null){ 
					ProductInfo productInfo = productInfoBiz.findProductInfoById(groupOrder2.getProductId());
					groupOrder2.setQuartzTime(productInfo.getObligateHour());
				}
			}
		}
		
		QueryYmgOrderListTableDataResult result = new QueryYmgOrderListTableDataResult();
		result.setTypeList(typeList);
		result.setPage(page);
		return result;
	}

	@Override
	public GroupOrder ymgOrderListPostFooter(GroupOrder groupOrder, Set<Integer> dataUserIdSet, Integer bizId) throws ParseException {
		
		if (groupOrder.getDateType() != null && groupOrder.getDateType() == 2) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (!"".equals(groupOrder.getStartTime())) {
				groupOrder.setStartTime(sdf.parse(groupOrder.getStartTime()).getTime() + "");
			}
			if (!"".equals(groupOrder.getEndTime())) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(sdf.parse(groupOrder.getEndTime()));
				calendar.add(Calendar.DAY_OF_MONTH, +1);// 让日期加1
				groupOrder.setEndTime(calendar.getTime().getTime() + "");
			}

		}

		if (StringUtils.isBlank(groupOrder.getSaleOperatorIds()) && StringUtils.isNotBlank(groupOrder.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = groupOrder.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				groupOrder.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}
		PageBean<GroupOrder> page = new PageBean<GroupOrder>();
		GroupOrder go = null;
		if (!"".equals(groupOrder.getGuestName()) || !"".equals(groupOrder.getMobile())) {
			go = groupOrderBiz.selectTotalTaobaoGuestNameOrder(groupOrder, bizId,
					dataUserIdSet);
		} else {
			go = groupOrderBiz.selectTotalTaobaoOrder(groupOrder, bizId, dataUserIdSet);
		}
		return go;
	}

	@Override
	public QueryYmgOrderListByOpTable ymgOrderListByOpTable(GroupOrder groupOrder, Integer bizId, Set<Integer> dataUserIdSet) throws ParseException {
		if (groupOrder.getDateType() != null && groupOrder.getDateType() == 2) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (!"".equals(groupOrder.getStartTime())) {
				groupOrder.setStartTime(sdf.parse(groupOrder.getStartTime()).getTime() + "");
			}
			if (!"".equals(groupOrder.getEndTime())) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(sdf.parse(groupOrder.getEndTime()));
				calendar.add(Calendar.DAY_OF_MONTH, +1);// 让日期加1
				groupOrder.setEndTime(calendar.getTime().getTime() + "");
			}

		}

		if (StringUtils.isBlank(groupOrder.getSaleOperatorIds()) && StringUtils.isNotBlank(groupOrder.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = groupOrder.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				groupOrder.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}
		PageBean<GroupOrder> page = new PageBean<GroupOrder>();
		if (!"".equals(groupOrder.getGuestName()) || !"".equals(groupOrder.getMobile())) {
			page.setParameter(groupOrder);
			page.setPage(groupOrder.getPage() == null ? 1 : groupOrder.getPage());
			page.setPageSize(groupOrder.getPageSize() == null ? Constants.PAGESIZE : groupOrder.getPageSize());
			page = groupOrderBiz.selectTaobaoOrderGuestNameListPage(page, bizId, dataUserIdSet, 0);
		} else {
			page.setParameter(groupOrder);
			page.setPage(groupOrder.getPage() == null ? 1 : groupOrder.getPage());
			page.setPageSize(groupOrder.getPageSize() == null ? Constants.PAGESIZE : groupOrder.getPageSize());
			page = groupOrderBiz.selectTaobaoOrderListPage(page, bizId, dataUserIdSet, 0);
		}

		List<GroupOrder> list = page.getResult();
		Integer pageTotalAudit = 0;
		Integer pageTotalChild = 0;
		Integer pageTotalGuide = 0;
		BigDecimal pageTotal = new BigDecimal(0);
		if (page.getResult() != null && page.getResult().size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (GroupOrder groupOrder2 : list) {
				pageTotalAudit += groupOrder2.getNumAdult() == null ? 0 : groupOrder2.getNumAdult();
				pageTotalChild += groupOrder2.getNumChild() == null ? 0 : groupOrder2.getNumChild();
				pageTotalGuide += groupOrder2.getNumGuide() == null ? 0 : groupOrder2.getNumGuide();
				pageTotal = pageTotal.add(groupOrder2.getTotal() == null ? new BigDecimal(0) : groupOrder2.getTotal());
				Long createTime = groupOrder2.getCreateTime();
				String dateStr = sdf.format(createTime);
				groupOrder2.setCreateTimeStr(dateStr);
				if (groupOrder2.getProductId()!=null){ 
					ProductInfo productInfo = productInfoBiz.findProductInfoById(groupOrder2.getProductId());
					groupOrder2.setQuartzTime(productInfo.getObligateHour());
				}
			}
		}
		
		List<DicInfo> typeList = dicBiz.getListByTypeCode(BasicConstants.SALES_TEAM_TYPE, bizId);
		
		GroupOrder go = null;
		if (!"".equals(groupOrder.getGuestName()) || !"".equals(groupOrder.getMobile())) {
			go = groupOrderBiz.selectTotalTaobaoGuestNameOrder(groupOrder, bizId, dataUserIdSet);
		} else {
			go = groupOrderBiz.selectTotalTaobaoOrder(groupOrder, bizId, dataUserIdSet);
		}
		
		QueryYmgOrderListByOpTable result = new QueryYmgOrderListByOpTable();
		result.setTypeList(typeList);
		result.setPageTotalAudit(pageTotalAudit);
		result.setPageTotalChild(pageTotalChild);
		result.setPageTotalGuide(pageTotalGuide);
		result.setPageTotal(pageTotal);
		result.setPage(page);
		result.setTotalAdult(go.getNumAdult());
		result.setTotalChild(go.getNumChild());
		result.setTotalGuide(go.getNumGuide());
		result.setTotal(go.getTotal());
		return result;

	}
	
	@Override
	public SaveSpecialGroupResult saveSpecialGroup(SpecialGroupOrderVO vo, String ids, String id, 
			Integer GroupMode, String myBizCode, Integer bizId, PlatformEmployeePo curUser) throws ParseException{
		
		SaveSpecialGroupResult saveResult = new SaveSpecialGroupResult();
		
		if (vo.getGroupOrder().getId() == null) {
			vo.getGroupOrder().setOrderType(1);
			vo.getGroupOrder().setOrderNo(myBizCode);
		}
		ProductInfo productInfo = productInfoBiz.findProductInfoById(vo.getGroupOrder().getProductId());
		Integer orderId = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Integer newNum = vo.getGroupOrder().getNumAdult() + vo.getGroupOrder().getNumChild();
		Integer oldNum = 0;
		try {
			GroupOrder go = new GroupOrder();
			if (vo.getGroupOrder().getId() != null) {
				go = groupOrderBiz.findById(vo.getGroupOrder().getId());
				
				if(vo.getGroupOrder().getOrderType() < 1){
					oldNum = go.getNumAdult()
						+ go.getNumChild();
			
					//查出库存(剩余人数)
					int freeCount = productStockBiz.getRestCountByProductIdAndDate(go.getProductId(),sdf.parse(go.getDepartureDate()));
					//实际库存应该是修改前人数+库存
					freeCount = oldNum + freeCount;
					if(newNum > freeCount){
						//如果新增人数大于库存,则不能保存
						saveResult.setSuccess(false);
						saveResult.setResultMsg("由于库存剩余数有变化，目前剩余库存不足【" + newNum + "】！实际库存还有【" + freeCount + "】");
						return saveResult;
					}
				}
			}
			List<GroupOrderPrice> incomeList = groupOrderPriceBiz.selectByOrder(vo.getGroupOrder().getId());
			List<GroupOrderGuest> guestList = groupOrderGuestBiz.selectByOrderId(vo.getGroupOrder().getId());
			List<GroupOrderTransport> transList = groupOrderTransportBiz.selectByOrderId(vo.getGroupOrder().getId());

			// 日志保存
			List<LogOperator> logList = new ArrayList<LogOperator>();
			logList.addAll(LogUtils.LogRow_GroupOrder(curUser, vo.getGroupOrder(), go)); // GroupOrder
			logList.addAll(LogUtils.LogRow_GroupOrderPrice(curUser, orderId, vo.getGroupOrderPriceList(), incomeList)); // groupOrderPrice
			logList.addAll(LogUtils.LogRow_GroupOrderGuest(curUser, orderId, vo.getGroupOrderGuestList(), guestList)); // groupOrderGuest
			logList.addAll(
					LogUtils.LogRow_GroupOrderTransport(curUser, orderId, vo.getGroupOrderTransportList(), transList)); // groupOrderTransport
			
			orderId = specialGroupOrderBiz.saveOrUpdateSpecialOrderInfo(vo, curUser.getEmployeeId(),
					curUser.getName(), bizId);

			// 日志OrderId赋值
			LogUtils.LogRow_SetValue(logList, "group_order", orderId, null);
			LogUtils.LogRow_SetValue(logList, "group_order_price", null, orderId);
			LogUtils.LogRow_SetValue(logList, "group_order_guest", null, orderId);
			LogUtils.LogRow_SetValue(logList, "group_order_transport", null, orderId);

			if (vo.getGroupOrder().getOrderType() == 1) {
				// 团队情况（不需要并团）
				List<GroupOrder> orderList = groupOrderBiz.selectGroupOrderById(orderId);
				List<MergeGroupOrderVO> result = new ArrayList<MergeGroupOrderVO>();
				for (int i = 0; i < orderList.size();) {
					GroupOrder order = orderList.get(i);
					GroupOrder groupOrder = groupOrderBiz.findById(order.getId());
					groupOrder.setGroupCode(order.getGroupCode());
					orderList.remove(order);
					MergeGroupOrderVO mov = new MergeGroupOrderVO();
					mov.getOrderList().add(groupOrder);
					result.add(mov);
				}
				PlatformEmployeePo platformEmployeePo = platformEmployeeBiz
						.findByEmployeeId(vo.getGroupOrder().getSaleOperatorId());
				String supplierCode = orgBiz.getCompanyCodeByOrgId(bizId,
						platformEmployeePo.getOrgId());
				specialGroupOrderBiz.mergetGroupTaobao(result, bizId,
						vo.getGroupOrder().getOperatorId(), vo.getGroupOrder().getOperatorName(), supplierCode);
				GroupOrder groupOrder = groupOrderBiz.findById(orderId);

				TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(groupOrder.getGroupId());
				tourGroup.setGroupMode(groupOrder.getOrderType());
				 TourGroup list = tourGroupBiz.selectGroupCodeSort(tourGroup.getBizId(),null,sdf.format(tourGroup.getDateStart()));
				String makeCodeByMode = tourGroupBiz.makeCodeForAgency(supplierCode, 
						productInfo.getCode()== null ? null : productInfo.getCode(), sdf.format(tourGroup.getDateStart()), sdf.format(tourGroup.getDateEnd()), tourGroup.getGroupCodeMark(), 
								list == null ? 1 : list.getGroupCodeSort() + 1);
				tourGroup.setGroupCode(makeCodeByMode);
				tourGroupBiz.updateByPrimaryKey(tourGroup);
			}

			// 插入到日志
			logBiz.insert(logList);
		} catch (ParseException e) {
			saveResult.setSuccess(false);
			saveResult.setResultMsg("操作失败,请检查后重试！");
			return saveResult;
		}
		
		try {
			if(productInfo!=null  && vo.getGroupOrder().getOrderType() < 1){
				if(vo.getGroupOrder().getType()==0){ //预留
					productStockBiz.updateReserveCount(vo.getGroupOrder().getProductId(), sdf.parse(vo.getGroupOrder().getDepartureDate()), newNum - oldNum);
					
				}else{
				

				productStockBiz.updateStockCount(vo.getGroupOrder()
						.getProductId(), sdf.parse(vo.getGroupOrder()
						.getDepartureDate()), newNum - oldNum);
				
				}
			}
			
			
			
			
		} catch (Exception e) {
			
			saveResult.setSuccess(false);
			saveResult.setResultMsg("更新库存失败！");
			return saveResult;
		}
		
	/*	if ("stock".equals(vo.getGroupOrder().getOrderBusiness()) && vo.getGroupOrder().getId() != null) {
			try {
				productStockService.updateStockCount(vo.getGroupOrder().getProductId(),
						sdf.parse(vo.getGroupOrder().getDepartureDate()), newNum - oldNum);
			} catch (Exception e) {
				return errorJson("更新库存失败！");
			}
		}*/
		
		saveResult.setSuccess(true);
		saveResult.setOrderId(orderId);
		return saveResult;
	}
	
	@Override
	public ToEditTaobaoOrderResult toEditTaobaoOrder(Integer see, Integer id, Integer operType, Integer parentId, Integer curUserId, Integer bizId){
		
		ToEditTaobaoOrderResult toEditResult = new ToEditTaobaoOrderResult();
		
		SpecialGroupOrderVO vo = specialGroupOrderBiz.selectSpeciaOrderlInfoByOrderId(id);
		toEditResult.setVo(vo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		GroupOrder groupOrder = groupOrderBiz.findById(id);
		int count = 0;
		if ("stock".equals(vo.getGroupOrder().getOrderBusiness())) {
			TaobaoStockDate tsd = productStockBiz.selectStockDataById(groupOrder.getProductId());
			if (tsd != null)
				count = tsd.getStockCount() - tsd.getSaleCount();
		}
		toEditResult.setCount(count);;
		List<RegionInfo> cityList = null;
		if (vo.getGroupOrder().getProvinceId() != null && vo.getGroupOrder().getProvinceId() != -1) {
			cityList = regionBiz.getRegionById(vo.getGroupOrder().getProvinceId() + "");
		}
		toEditResult.setCityList(cityList);
		List<RegionInfo> DepartCityList = null;
		if (vo.getGroupOrder().getDepartProvinceId() != null && vo.getGroupOrder().getDepartProvinceId() != -1) {
			DepartCityList = regionBiz.getRegionById(vo.getGroupOrder().getDepartProvinceId() + "");
		}
		toEditResult.setDepartCityList(DepartCityList);
		String guideStr = "";
		List<GroupOrderGuest> guestList = groupOrderGuestBiz.selectByOrderId(id);
		if (guestList != null) {
			for (GroupOrderGuest groupOrderGuest : guestList) {
				if (groupOrderGuest.getType() == 3) {
					guideStr = ("".equals(guideStr) ? "" : (guideStr + " | ")) + groupOrderGuest.getName() + " "
							+ groupOrderGuest.getMobile();
				}
			}
		}
		toEditResult.setGuideStr(guideStr);
		List<PlatTaobaoTrade> orders = null;
		orders = taobaoOrderBiz.selectTaobaoOrderByOrderId(id); // 已改为查
																	// platTaobaoTradeOrderId
		toEditResult.setOrders(orders);
		String tbOrderIds = "";
		if (orders != null) {
			for (PlatTaobaoTrade item : orders) {
				tbOrderIds += item.getId() + ",";
			}
		}
		if (!"".equals(tbOrderIds))
			tbOrderIds = tbOrderIds.substring(0, tbOrderIds.length() - 1);
		toEditResult.setTbOrderIds(tbOrderIds);

		// 获取消息
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bizId", bizId);
		map.put("userId", curUserId);
		map.put("OrgId", parentId);
		map.put("orderId", id);

		toEditResult.setMsgInfo(getMsgInfo(map));

		if (groupOrder.getGroupId() != null) {
			Map<String, Object> datas = bookingSupplierBiz.AYSelectBookingInfo(groupOrder.getGroupId());
			toEditResult.setGroupCanEdit(tourGroupBiz.checkGroupCanEdit(groupOrder.getGroupId()));
			toEditResult.setGroupId(groupOrder.getGroupId());
			toEditResult.setDatas(datas);

			List<BookingDelivery> list = deliveryBiz.getDeliveryListByGroupId(groupOrder.getGroupId());
			if (list != null && list.size() > 0) {
				for (BookingDelivery delivery : list) {
					delivery.setPriceList(bookingDeliveryPriceBiz.getPriceListByBookingId(delivery.getId()));
				}
			}
			toEditResult.setList(list);
			TourGroup tg = tourGroupBiz.selectByPrimaryKey(groupOrder.getGroupId());
			toEditResult.setTg(tg);
		}
		return toEditResult;
	}
	
	private List<MsgInfo> getMsgInfo(Map<String, Object> map) {
		return msgInfoBiz.findMsgInfo(map);
	}
}
