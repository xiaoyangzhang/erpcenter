package com.yimayhd.erpcenter.facade.tj.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yimayhd.erpcenter.biz.basic.service.LogOperatorBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryPriceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.query.QueryBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.*;
import com.yimayhd.erpcenter.biz.sys.service.MsgInfoBiz;
import com.yimayhd.erpcenter.dal.basic.po.LogOperator;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStockDate;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStockLog;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.*;
import com.yimayhd.erpcenter.facade.tj.client.result.*;

import com.yimayhd.erpcenter.facade.tj.client.utils.LogUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductStockBiz;
import com.yimayhd.erpcenter.biz.product.service.TaoBaoStockBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.taobao.TaobaoOrderBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.common.BaseResult;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.MergeGroupOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SpecialGroupOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.taobao.po.PlatTaobaoTrade;
import com.yimayhd.erpcenter.facade.tj.client.errorcode.TjErrorCode;
import com.yimayhd.erpcenter.facade.tj.client.query.AddSivaInfoDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.ImportTaobaoOrderTableDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.PresellProductStatistics;
import com.yimayhd.erpcenter.facade.tj.client.query.PushTradeQueryDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.SaveSpecialGroupDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.ShopSalesStatisticsQueryDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.TaobaoOrderListByOpDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.TaobaoOrderListTableDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.TaobaoOriginalOrderTableDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.ToEditTaobaoOrderDTO;
import com.yimayhd.erpcenter.facade.tj.client.service.TaobaoFacade;

import org.springframework.web.util.WebUtils;

public class TaobaoFacadeImpl extends BaseResult implements TaobaoFacade{
	private static final Logger LOGGER = LoggerFactory.getLogger("TaobaoFacadeImpl");
	@Autowired
	private DicBiz dicBiz;
	@Autowired
	private RegionBiz regionBiz;
	@Autowired
	private PlatformOrgBiz platformOrgBiz;
	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;
	@Autowired
	private GroupOrderBiz groupOrderBiz;
	@Autowired
	private SpecialGroupOrderBiz specialGroupOrderBiz;
	@Autowired
	private ProductStockBiz productStockBiz;
	@Autowired
	private GroupOrderGuestBiz groupOrderGuestBiz;
	@Autowired
	private TaobaoOrderBiz taobaoOrderBiz;
	@Autowired
	private TourGroupBiz tourGroupBiz;
	@Autowired
	private TaoBaoStockBiz taoBaoStockBiz;
	@Autowired
	private BookingDeliveryBiz bookingDeliveryBiz;
	@Autowired
	private TaoBaoStockBiz taobaoStockBiz;

	@Autowired
	private GroupRouteBiz groupRouteBiz;
	@Autowired
	private MsgInfoBiz msgInfoBiz;

	@Autowired
	private GroupOrderPriceBiz groupOrderPriceBiz;

	@Autowired
	private BookingDeliveryPriceBiz bookingDeliveryPriceBiz;
	@Autowired
	private BookingDeliveryBiz deliveryBiz;
	@Autowired
	private BookingSupplierBiz bookingSupplierBiz;
	@Autowired
	private QueryBiz queryBiz;
	@Autowired
	private LogOperatorBiz logBiz;

	/**
	 * 操作单
	 * @return
	 */
	public TaobaoOrderListResult taobaoOrderList(int bizId) {
		TaobaoOrderListResult result = new TaobaoOrderListResult();
		List<DicInfo> pp = dicBiz.getListByTypeCode(BasicConstants.CPXL_PP,bizId);
		result.setPp(pp);
		List<RegionInfo> allProvince = regionBiz.getAllProvince();
		result.setAllProvince(allProvince);
		List<DicInfo> typeList = dicBiz
				.getListByTypeCode(BasicConstants.SALES_TEAM_TYPE,bizId);
		result.setTypeList(typeList);
		List<DicInfo> sourceTypeList = dicBiz
				.getListByTypeCode(Constants.GUEST_SOURCE_TYPE, bizId);
		result.setSourceTypeList(sourceTypeList);
		result.setOrgTreeJsonStr(platformOrgBiz.getComponentOrgTreeJsonStr(bizId));
		result.setOrgUserTreeJsonStr(platformEmployeeBiz.getComponentOrgUserTreeJsonStr(bizId));
		return result;
	}
	/**
	 * 操作单table
	 * @return
	 * @throws ParseException
	 */
	public TaobaoOrderListTableResult taobaoOrderList_table(TaobaoOrderListTableDTO taobaoOrderListTableDTO) {
		TaobaoOrderListTableResult result = new TaobaoOrderListTableResult();
		GroupOrder groupOrder = taobaoOrderListTableDTO.getGroupOrder();
		int bizId = taobaoOrderListTableDTO.getBizId();
		if(groupOrder.getDateType()!=null && groupOrder.getDateType()==2){
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
			try {
				if(!"".equals(groupOrder.getStartTime())){
					groupOrder.setStartTime(sdf.parse(groupOrder.getStartTime()).getTime()+"");
				}
				if(!"".equals(groupOrder.getEndTime())){
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(sdf.parse(groupOrder.getEndTime()));
					calendar.add(Calendar.DAY_OF_MONTH, +1);// 让日期加1
					groupOrder.setEndTime(calendar.getTime().getTime() + "");
				}
			} catch (ParseException e) {
				LOGGER.error("taobaoOrderList_table dateChange error",e);
				result.setErrorCode(TjErrorCode.DATE_CONVERSION_ERROR);
				return result;
			}

		}
		if (StringUtils.isBlank(groupOrder.getSaleOperatorIds())
				&& StringUtils.isNotBlank(groupOrder.getOrgIds())) {
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
				groupOrder.setSaleOperatorIds(salesOperatorIds.substring(0,
						salesOperatorIds.length() - 1));
			}
		}
		PageBean<GroupOrder> page = new PageBean<GroupOrder>();
//		page.setParameter(groupOrder);
//		page.setPage(groupOrder.getPage()==null?1:groupOrder.getPage());
//		page.setPageSize(groupOrder.getPageSize() == null ? Constants.PAGESIZE
//				: groupOrder.getPageSize());
//		page =groupOrderBiz.selectSpecialOrderListPage(page, bizId,taobaoOrderListTableDTO.getDataUserIdSets());

		if (!"".equals(groupOrder.getGuestName()) || !"".equals(groupOrder.getMobile())) {
			page.setParameter(groupOrder);
			page.setPage(groupOrder.getPage() == null ? 1 : groupOrder.getPage());
			page.setPageSize(groupOrder.getPageSize() == null ? Constants.PAGESIZE : groupOrder.getPageSize());
			page = groupOrderBiz.selectTaobaoOrderGuestNameListPage(page, bizId,
					taobaoOrderListTableDTO.getDataUserIdSets(), 1);
		} else {
			page.setParameter(groupOrder);
			page.setPage(groupOrder.getPage() == null ? 1 : groupOrder.getPage());
			page.setPageSize(groupOrder.getPageSize() == null ? Constants.PAGESIZE : groupOrder.getPageSize());
			page = groupOrderBiz.selectTaobaoOrderListPage(page, bizId,
					taobaoOrderListTableDTO.getDataUserIdSets(), 1);
		}


		result.setPage(page);

//		List<GroupOrder> list = page.getResult();
//		Integer pageTotalAudit=0;
//		Integer pageTotalChild=0;
//		Integer pageTotalGuide=0;
//		BigDecimal pageTotal=new BigDecimal(0);
//		if(page.getResult()!=null && page.getResult().size()>0){
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			for (GroupOrder groupOrder2 : list) {
//				pageTotalAudit+=groupOrder2.getNumAdult()==null?0:groupOrder2.getNumAdult();
//				pageTotalChild+=groupOrder2.getNumChild()==null?0:groupOrder2.getNumChild();
//				pageTotalGuide+=groupOrder2.getNumGuide()==null?0:groupOrder2.getNumGuide();
//				pageTotal =pageTotal.add(groupOrder2.getTotal()==null?new BigDecimal(0):groupOrder2.getTotal());
//				Long createTime = groupOrder2.getCreateTime();
//				String dateStr = sdf.format(createTime);
//				groupOrder2.setCreateTimeStr(dateStr);
//			}
//		}
		List<DicInfo> typeList = dicBiz
				.getListByTypeCode(BasicConstants.SALES_TEAM_TYPE,bizId);
		result.setTypeList(typeList);
//		model.addAttribute("typeList", typeList);
//		model.addAttribute("pageTotalAudit", pageTotalAudit);
//		model.addAttribute("pageTotalChild",pageTotalChild);
//		model.addAttribute("pageTotalGuide",pageTotalGuide);
//		model.addAttribute("pageTotal", pageTotal);
//		model.addAttribute("page", page);
//		GroupOrder go = groupOrderBiz.selectTotalSpecialOrder(groupOrder, bizId,taobaoOrderListTableDTO.getDataUserIdSets());
		GroupOrder go = null;
		if(!"".equals(groupOrder.getGuestName()) || !"".equals(groupOrder.getMobile())){
			go = groupOrderBiz.selectTotalTaobaoGuestNameOrder(groupOrder,
					bizId, taobaoOrderListTableDTO.getDataUserIdSets());
		}else{
			go = groupOrderBiz.selectTotalTaobaoOrder(groupOrder,
					bizId,taobaoOrderListTableDTO.getDataUserIdSets());
		}

		result.setGroupOrder(go);
//		model.addAttribute("totalAudit", go.getNumAdult());
//		model.addAttribute("totalChild", go.getNumChild());
//		model.addAttribute("totalGuide", go.getNumGuide());
//		model.addAttribute("total", go.getTotal());
		return result;
	}

	/*操作单-编辑*/
	public ToEditTaobaoOrderResult toEditTaobaoOrder(ToEditTaobaoOrderDTO toEditTaobaoOrderDTO){
		ToEditTaobaoOrderResult result = new ToEditTaobaoOrderResult();
		int bizId = toEditTaobaoOrderDTO.getBizId();
		int orderId = toEditTaobaoOrderDTO.getOrderId();
		Integer operType = toEditTaobaoOrderDTO.getOperType();
		if(operType==null){
			operType=1;
		}
		result.setOperType(operType);
		SpecialGroupOrderVO  vo= specialGroupOrderBiz.selectSpeciaOrderlInfoByOrderId(orderId);
		result.setSpecialGroupOrderVO(vo);
		List<DicInfo> jdxjList = dicBiz.getListByTypeCode(BasicConstants.GYXX_JDXJ);
		result.setJdxjList(jdxjList);
		List<DicInfo> zjlxList = dicBiz
				.getListByTypeCode(BasicConstants.GYXX_ZJLX);
		result.setZjlxList(zjlxList);
		List<DicInfo> lysfxmList = dicBiz.getListByTypeCode(
				BasicConstants.GYXX_LYSFXM, bizId);
		result.setLysfxmList(lysfxmList);
		List<DicInfo> jtfsList = dicBiz
				.getListByTypeCode(BasicConstants.GYXX_JTFS, bizId);
		result.setJtfsList(jtfsList);
		List<DicInfo> typeList = dicBiz
				.getListByTypeCode(BasicConstants.SALES_TEAM_TYPE,bizId);
		result.setTypeList(typeList);
		List<RegionInfo> allProvince = regionBiz.getAllProvince();
		result.setAllProvince(allProvince);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		GroupOrder groupOrder = groupOrderBiz.findById(orderId);
		int count=0;
		if("stock".equals(vo.getGroupOrder().getOrderBusiness())){
			try {
//				count = productStockBiz.getRestCountByProductIdAndDate(groupOrder.getProductId(),sdf.parse(groupOrder.getDepartureDate()));
				TaobaoStockDate tsd = productStockBiz.selectStockDataById(groupOrder.getProductId());
				if (tsd != null)
					count = tsd.getStockCount() - tsd.getSaleCount();
			} catch (Exception e) {
				LOGGER.error("toEditTaobaoOrder dateChange error",e);
				result.setErrorCode(TjErrorCode.DATE_CONVERSION_ERROR);
				return result;
			}
		}
		result.setCount(count);
		List<RegionInfo> cityList = null;
		if(vo.getGroupOrder().getProvinceId()!=null && vo.getGroupOrder().getProvinceId()!=-1){
			cityList=regionBiz.getRegionById(vo.getGroupOrder().getProvinceId()+"");
		}
		result.setCityList(cityList);

		List<RegionInfo> DepartCityList = null;
		if (vo.getGroupOrder().getDepartProvinceId() != null && vo.getGroupOrder().getDepartProvinceId() != -1) {
			DepartCityList = regionBiz.getRegionById(vo.getGroupOrder().getDepartProvinceId() + "");
		}
		result.setDepartCityList(DepartCityList);
		String guideStr="";
		List<GroupOrderGuest> guestList = groupOrderGuestBiz.selectByOrderId(orderId);
		if(guestList!=null){
			for (GroupOrderGuest groupOrderGuest : guestList) {
				if(groupOrderGuest.getType()==3){
					guideStr=("".equals(guideStr)?"":(guideStr+" | "))+groupOrderGuest.getName()+" "+groupOrderGuest.getMobile();
				}
			}
		}
		result.setGuideStr(guideStr);
		List<PlatTaobaoTrade> orders = null;
		orders=taobaoOrderBiz.selectTaobaoOrderByOrderId(orderId);
		result.setOrders(orders);
		String tbOrderIds = "";
		if ( orders != null){
			for (PlatTaobaoTrade item : orders) {
				tbOrderIds += item.getId() +",";
			}
		}
		if (!"".equals(tbOrderIds))
			tbOrderIds = tbOrderIds.substring(0, tbOrderIds.length()-1);
		result.setTbOrderIds(tbOrderIds);


		if (groupOrder.getGroupId() != null) {
			Map<String, Object> datas = bookingSupplierBiz.AYSelectBookingInfo(groupOrder.getGroupId());
			result.setGroupCanEdit(tourGroupBiz.checkGroupCanEdit(groupOrder.getGroupId()));
			result.setBookingInfo(datas);

			List<BookingDelivery> list = deliveryBiz.getDeliveryListByGroupId(groupOrder.getGroupId());
			if (list != null && list.size() > 0) {
				for (BookingDelivery delivery : list) {
					delivery.setPriceList(bookingDeliveryPriceBiz.getPriceListByBookingId(delivery.getId()));
				}
			}
			result.setBdList(list);
			TourGroup tg = tourGroupBiz.selectByPrimaryKey(groupOrder.getGroupId());
			result.setTg(tg);
		}
		result.setGroupOrder(groupOrder);

		return result;
	}

	/**
	 * 跳转到新增订单页面
	 * @return
	 */
	public AddNewTaobaoOrderResult addNewTaobaoOrder(int bizId) {
		AddNewTaobaoOrderResult result = new AddNewTaobaoOrderResult();
//		model.addAttribute("operType", 1);
//		GroupOrder groupOrder  = new GroupOrder();
//		groupOrder.setSaleOperatorId(WebUtils.getCurUserId(request));
//		groupOrder.setSaleOperatorName(WebUtils.getCurUser(request).getName());
//		groupOrder.setOperatorId(WebUtils.getCurUserId(request));
//		groupOrder.setOperatorName(WebUtils.getCurUser(request).getName());
//		SpecialGroupOrderVO  vo = new SpecialGroupOrderVO();
//		vo.setGroupOrder(groupOrder);
//		model.addAttribute("vo", vo);


		List<DicInfo> jdxjList = dicBiz.getListByTypeCode(BasicConstants.GYXX_JDXJ);
		result.setJdxjList(jdxjList);
		List<DicInfo> jtfsList = dicBiz
				.getListByTypeCode(BasicConstants.GYXX_JTFS, bizId);
		result.setJtfsList(jtfsList);
		List<DicInfo> zjlxList = dicBiz
				.getListByTypeCode(BasicConstants.GYXX_ZJLX);
		result.setZjlxList(zjlxList);
		List<DicInfo> typeList = dicBiz
				.getListByTypeCode(BasicConstants.SALES_TEAM_TYPE,bizId);
		result.setTypeList(typeList);
		List<RegionInfo> allProvince = regionBiz.getAllProvince();
		result.setAllProvince(allProvince);

		List<DicInfo> lysfxmList = dicBiz.getListByTypeCode(
				BasicConstants.GYXX_LYSFXM, bizId);
		result.setLysfxmList(lysfxmList);
//		model.addAttribute("config", config);
		return result;
	}

	/**
	 * 保存订单
	 * @return
	 */
	public SaveSpecialGroupResult saveSpecialGroup(SaveSpecialGroupDTO saveSpecialGroupDTO){
		SaveSpecialGroupResult saveSpecialGroupResult = new SaveSpecialGroupResult();
		SpecialGroupOrderVO vo = saveSpecialGroupDTO.getVo();
		int bizId = saveSpecialGroupDTO.getBizId();
		int userId = saveSpecialGroupDTO.getUserId();
		String userName = saveSpecialGroupDTO.getUserName();
		if(vo.getGroupOrder().getId()==null){
			vo.getGroupOrder().setOrderNo(saveSpecialGroupDTO.getOrderNo());
		}
		Integer orderId=0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Integer newNum = vo.getGroupOrder().getNumAdult()
				+ vo.getGroupOrder().getNumChild();
		Integer oldNum = 0;
		Boolean isStock = false;
		try {
			if("stock".equals(vo.getGroupOrder().getOrderBusiness())){
				TaobaoStockDate tsd = productStockBiz.selectStockDataById(vo.getGroupOrder().getProductId());
				if (tsd != null) {

					if (vo.getGroupOrder().getId() != null) {
						GroupOrder groupOrder =groupOrderBiz.findById(vo.getGroupOrder().getId());
						oldNum = groupOrder.getNumAdult()
								+groupOrder.getNumChild();
					}
					//查出库存(剩余人数)
					int freeCount = tsd.getStockCount() - tsd.getSaleCount(); // 查出库存(剩余人数)
					//实际库存应该是修改前人数+库存
					freeCount = oldNum + freeCount;
					if(newNum > freeCount){
						//如果新增人数大于库存,则不能保存
						saveSpecialGroupResult.setErrorCode(TjErrorCode.INVENTORY_SHORTAGE);
						saveSpecialGroupResult.setResultMsg(errorJson("由于库存剩余数有变化，目前剩余库存不足【" + newNum + "】！实际库存还有【" + freeCount + "】"));
						return saveSpecialGroupResult;
					}
					isStock = true;
				}


				if (vo.getGroupOrder().getId() == null) {
					productStockBiz.updateStockCount(vo.getGroupOrder()
							.getProductId(), sdf.parse(vo.getGroupOrder()
							.getDepartureDate()), newNum );
					}
			}

			GroupOrder go = null;
			List<GroupOrderPrice> incomeList = null;
			List<GroupOrderGuest> guestList = null;
			List<GroupOrderTransport> transList = null;
			// 日志保存
			List<LogOperator> logList = new ArrayList<LogOperator>();


			orderId = specialGroupOrderBiz.saveOrUpdateSpecialOrderInfo(vo, saveSpecialGroupDTO.getUserId(),
					saveSpecialGroupDTO.getUserName(), saveSpecialGroupDTO.getBizId());

			// 日志OrderId赋值
			LogUtils.LogRow_SetValue(logList, "group_order", orderId, null);
			LogUtils.LogRow_SetValue(logList, "group_order_price", null, orderId);
			LogUtils.LogRow_SetValue(logList, "group_order_guest", null, orderId);
			LogUtils.LogRow_SetValue(logList, "group_order_transport", null, orderId);

			if (isStock) {
				TaobaoStockLog sLog = taobaoStockBiz
						.selectLogByStockDateIdAndOrderId(vo.getGroupOrder().getProductId(), orderId);
				if (sLog == null) {
					sLog = new TaobaoStockLog();
					sLog.setId(0);
					sLog.setStockId(vo.getGroupOrder().getProductBrandId());
					sLog.setStockDateId(vo.getGroupOrder().getProductId());
					sLog.setCreateUser(saveSpecialGroupDTO.getUserName());
					sLog.setOrderId(orderId);
					sLog.setTaobaoOrderId(0);
					sLog.setNum(newNum);
					productStockBiz.insertTaobaoStockLogSelective(sLog);
				} else {
					sLog.setNum(newNum);
					productStockBiz.updateTaobaoStockLogSelective(sLog);
				}
				productStockBiz.updateByLog(sLog.getStockDateId());
			}
			//todo取出原来ids，并对比现在在的ids，得到要删除的ids ,　　比如原来：１,２,３,４　删除了2,3－> 14,
			String id = saveSpecialGroupDTO.getTaobaoOrderId();
			if(id !="" && id.length()>0){
				id=id.substring(0,id.length()-1);
				taobaoOrderBiz.updateTaobaoOrderIdToZero(id);// 已改为platTaobaoTradeOrderId
			}
			String ids = saveSpecialGroupDTO.getTaobaoOrderIds();
			if(ids.length()>0 && ids !=""){
				taobaoOrderBiz.updateTaobaoOrderId(orderId, ids);// 已改为platTaobaoTradeOrderId
			}
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
				specialGroupOrderBiz.mergetGroupTaobao(result, bizId,
						userId, userName, saveSpecialGroupDTO.getMyBizCode());
				GroupOrder groupOrder=groupOrderBiz.findById(orderId);
				TourGroup tourGroup=tourGroupBiz.selectByPrimaryKey(groupOrder.getGroupId());
				tourGroup.setGroupMode(saveSpecialGroupDTO.getGroupMode());
				tourGroupBiz.updateByPrimaryKey(tourGroup);
			}
			// 插入到日志
			logBiz.insert(logList);
		} catch (ParseException e) {
			saveSpecialGroupResult.setErrorCode(TjErrorCode.MODIFY_ERROR);
			saveSpecialGroupResult.setResultMsg("操作失败,请检查后重试！");
			return saveSpecialGroupResult;
		}
		if("stock".equals(vo.getGroupOrder().getOrderBusiness())&&vo.getGroupOrder().getId() != null){
			try {productStockBiz.updateStockCount(vo.getGroupOrder()
						.getProductId(), sdf.parse(vo.getGroupOrder()
						.getDepartureDate()), newNum - oldNum);
			}catch (Exception e) {
				saveSpecialGroupResult.setErrorCode(TjErrorCode.UPDATE_ERROR);
				saveSpecialGroupResult.setResultMsg("更新库存失败！");
				return saveSpecialGroupResult;
			}
		 }
		saveSpecialGroupResult.setResultJson(successJson("groupId", orderId + ""));
		return saveSpecialGroupResult;
	}

	/**
	 * 淘宝订单导入页面table
	 * @return
	 */
	public ImportTaobaoOrderTableResult import_taobaoOrder_table(ImportTaobaoOrderTableDTO importTaobaoOrderTableDTO) {
		ImportTaobaoOrderTableResult result = new ImportTaobaoOrderTableResult();
		int bizId = importTaobaoOrderTableDTO.getBizId();
		PageBean<PlatTaobaoTrade> pageBean = importTaobaoOrderTableDTO.getPageBean();
//		Integer page = importTaobaoOrderTableDTO.getPage();
//		Integer pageSize = importTaobaoOrderTableDTO.getPageSize();
//		PageBean<PlatTaobaoTrade> pageBean = new PageBean<PlatTaobaoTrade>();
//		if(page==null){
//			pageBean.setPage(1);
//		}else{
//			pageBean.setPage(page);
//		}
//		if(pageSize==null){
//			pageBean.setPageSize(Constants.PAGESIZE);
//		}else{
//			pageBean.setPageSize(pageSize);
//		}
//		pageBean.setPage(page);
//		Map<String,Object> pm  = WebUtils.getQueryParamters(request);
//		pm.put("startMin",pm.get("startMin")+" 00:00:00");
//		pm.put("startMax",pm.get("startMax")+" 23:59:59");
//		pageBean.setParameter(pm);
		pageBean=taobaoOrderBiz.selectTaobaoOrderImport(pageBean, bizId);
		result.setPageBean(pageBean);
		return result;
	}

	/**
	 * 确定
	 * @return
	 */
	public String taobaoOrder_GetByIds(String ids){
		List<PlatTaobaoTrade> orders = null;
		orders=taobaoOrderBiz.selectTaobaoOrderById(ids);
		return JSON.toJSONString(orders);
	}

	/**
	 * 淘宝原始单table（爱游）
	 * @return
	 */
	public PageBean taobaoOriginalOrder_table(TaobaoOriginalOrderTableDTO taobaoOriginalOrderTableDTO) {
//		PageBean<PlatTaobaoTrade> pageBean = new PageBean<PlatTaobaoTrade>();
//		if(page==null){
//			pageBean.setPage(1);
//		}else{
//			pageBean.setPage(page);
//		}
//		if(pageSize==null){
//			pageBean.setPageSize(Constants.PAGESIZE);
//		}else{
//			pageBean.setPageSize(pageSize);
//		}
//		Map<String,Object> pm  = WebUtils.getQueryParamters(request);
//		pm.put("myStoreId",authClient);
//		pm.put("curUserName",WebUtils.getCurrentUserSession(request).getName());
//
//		pm.put("startMin",pm.get("startMin")+" 00:00:00");
//		pm.put("startMax",pm.get("startMax")+" 23:59:59");
//
//		pageBean.setParameter(pm);

		PageBean<PlatTaobaoTrade> pageBean = taobaoOrderBiz.selectTaobaoOrder(taobaoOriginalOrderTableDTO.getPageBean(), taobaoOriginalOrderTableDTO.getBizId());

		return pageBean;
	}
	/**
     * 废弃
     */
	public void updateCancel(String idss){
		taobaoOrderBiz.updateCANCEL(idss);
	}
	/**
     * 还原
     */
	public void updateNew(String idss){
		taobaoOrderBiz.updateNEW(idss);
	}
	/**
     * 同步 by time
     */
	public PageBean synchroByTime(TaobaoOriginalOrderTableDTO taobaoOriginalOrderTableDTO){
//		PageBean<PlatTaobaoTrade> pageBean = new PageBean<PlatTaobaoTrade>();
//		if(page==null){
//			pageBean.setPage(1);
//		}else{
//			pageBean.setPage(page);
//		}
//		if(pageSize==null){
//			pageBean.setPageSize(Constants.PAGESIZE);
//		}else{
//			pageBean.setPageSize(pageSize);
//		}
//		Map<String,Object> pm  = WebUtils.getQueryParamters(request);
//		pm.put("myStoreId",authClient);
//		pm.put("curUserName",WebUtils.getCurrentUserSession(request).getName());
//		pm.put("startMin",startTime+" 00:00:00");
//		pm.put("startMax",endTime+" 23:59:59");
//
//		pageBean.setParameter(pm);

		PageBean<PlatTaobaoTrade> pageBean = taobaoOrderBiz.syncTaobaoOrderByTime(taobaoOriginalOrderTableDTO.getPageBean(), taobaoOriginalOrderTableDTO.getBizId());

		List<PlatTaobaoTrade> pttList = pageBean.getResult();
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();

		// 获取备注(扣除库存)
		for (PlatTaobaoTrade pt : pttList) {
			if (pt.getReceiveCount() != null && new Integer(pt.getReceiveCount()) > 0) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("numIid", pt.getNumIid());// 自编码、日期、数量,
				// PlatTaobaoTradeOrderId
				map.put("depDate", pt.getDepartureDate());
				map.put("receiveCount", pt.getReceiveCount());
				map.put("receiveCount", pt.getReceiveCount());
				map.put("taobaoOrderId", pt.getId().toString());
				mapList.add(map);
			}
		}
		taoBaoStockBiz.updateProductStockByTaobao(mapList);
		return pageBean ;
	}
	/**
     * 同步 by tid
     */
	public PageBean synchroByTid(TaobaoOriginalOrderTableDTO taobaoOriginalOrderTableDTO){
//		PageBean<PlatTaobaoTrade> pageBean = new PageBean<PlatTaobaoTrade>();
//		if(page==null){
//			pageBean.setPage(1);
//		}else{
//			pageBean.setPage(page);
//		}
//		if(pageSize==null){
//			pageBean.setPageSize(Constants.PAGESIZE);
//		}else{
//			pageBean.setPageSize(pageSize);
//		}
//		Map<String,Object> pm  = WebUtils.getQueryParamters(request);
//		pm.put("myStoreId",authClient);
//		pm.put("curUserName",WebUtils.getCurrentUserSession(request).getName());
//		pm.put("tid",tid);
//
//		pageBean.setParameter(pm);

		PageBean<PlatTaobaoTrade> pageBean=taobaoOrderBiz.selectTaobaoOrderByTid(taobaoOriginalOrderTableDTO.getPageBean(),taobaoOriginalOrderTableDTO.getBizId());
		List<PlatTaobaoTrade> pttList = pageBean.getResult();
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		for (PlatTaobaoTrade pt : pttList) {
			if (pt.getReceiveCount() != null && new Integer(pt.getReceiveCount()) > 0) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("numIid", pt.getNumIid());// 自编码、日期、数量,
				// PlatTaobaoTradeOrderId
				map.put("depDate", pt.getDepartureDate());
				map.put("receiveCount", pt.getReceiveCount());
				map.put("receiveCount", pt.getReceiveCount());
				map.put("taobaoOrderId", pt.getId().toString());
				mapList.add(map);
			}
		}
		taoBaoStockBiz.updateProductStockByTaobao(mapList);

		return pageBean ;
	}
	@Override
	public ShopSalesStatisticsResult selectTaobaoshopSalesStatistics(ShopSalesStatisticsQueryDTO queryDTO) {
		ShopSalesStatisticsResult result = new ShopSalesStatisticsResult();
		PlatTaobaoTrade trade = taobaoOrderBiz.selectTaobaoshopSalesStatistics(queryDTO.getPlatTaobaoTrade(),
				 queryDTO.getBizId());

		result.setTrade(trade);

		return result;
	}
	@Override
	public PresellProductStatisticsListResult selectPresellProductStatisticsListPage(
			PresellProductStatistics queryDTO) {
		PresellProductStatisticsListResult result = new PresellProductStatisticsListResult();
		PageBean<PlatTaobaoTrade> pageBean = taobaoOrderBiz.selectPresellProductStatisticsListPage(queryDTO.getPageBean(), queryDTO.getBizId());
		result.setPageBean(pageBean);

		return result;
	}

	public String savePushTrade(PushTradeQueryDTO pushTradeQueryDTO) {
		PageBean<PlatTaobaoTrade> pageBean = new PageBean<PlatTaobaoTrade>();
        pageBean.setPage(1);
        pageBean.setPageSize(Constants.PAGESIZE);

        pageBean = taobaoOrderBiz.savePushTrade(pushTradeQueryDTO.getTid(), pushTradeQueryDTO.getAuthClient(), pushTradeQueryDTO.getResponse());

        List<PlatTaobaoTrade> pttList = pageBean.getResult();
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();

     // 获取备注(扣除库存)
        for (PlatTaobaoTrade pt : pttList) {
            if (pt.getReceiveCount() !=null && new Integer(pt.getReceiveCount())>0){
                Map<String, String> map = new HashMap<String, String>();
                map.put("numIid", pt.getNumIid());//自编码、日期、数量, PlatTaobaoTradeOrderId
                map.put("depDate", pt.getDepartureDate());
                map.put("receiveCount", pt.getReceiveCount());
                map.put("receiveCount", pt.getReceiveCount());
                map.put("taobaoOrderId", pt.getId().toString());
                mapList.add(map);
            }
        }
        taoBaoStockBiz.updateProductStockByTaobao(mapList);
        return "OK";
	}
	@Override
	public PresellProductStatisticsListResult selectNotPresellProductStatisticsListPage(PresellProductStatistics queryDTO) {
		PresellProductStatisticsListResult result = new PresellProductStatisticsListResult();
		PageBean<PlatTaobaoTrade> pageBean = taobaoOrderBiz.selectNotPresellProductStatisticsListPage(queryDTO.getPageBean(), queryDTO.getBizId());
		result.setPageBean(pageBean);

		return result;
	}



	@Override
	public PresellProductStatisticsListResult selectSaleOperatorSalesStatisticsListPage(PresellProductStatistics queryDTO) {
		PresellProductStatisticsListResult result = new PresellProductStatisticsListResult();
		PageBean<PlatTaobaoTrade> pageBean = taobaoOrderBiz.selectSaleOperatorSalesStatisticsListPage(queryDTO.getPageBean(), queryDTO.getBizId());
		result.setPageBean(pageBean);

		return result;
	}
	@Override
	public WebResult<PageBean> toOrderPreview(TaobaoOrderListTableDTO taobaoOrderListTableDTO){
		WebResult<PageBean> webResult = new WebResult<PageBean>();
		try{
			GroupOrder groupOrder = taobaoOrderListTableDTO.getGroupOrder();

			if (StringUtils.isBlank(groupOrder.getSaleOperatorIds()) && StringUtils.isNotBlank(groupOrder.getOrgIds())) {
				Set<Integer> set = new HashSet<Integer>();
				String[] orgIdArr = groupOrder.getOrgIds().split(",");
				for (String orgIdStr : orgIdArr) {
					set.add(Integer.valueOf(orgIdStr));
				}
				set = platformEmployeeBiz.getUserIdListByOrgIdList(taobaoOrderListTableDTO.getBizId(), set);
				String salesOperatorIds = "";
				for (Integer usrId : set) {
					salesOperatorIds += usrId + ",";
				}
				if (!salesOperatorIds.equals("")) {
					groupOrder.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
				}
			}
			PageBean<GroupOrder> page = new PageBean<GroupOrder>();
			if (!"".equals(groupOrder.getGuestName())) {
				page.setPage(1);
				page.setPageSize(10000);
				page.setParameter(groupOrder);
				page = groupOrderBiz.selectOperatorGuestNameListPage(page, taobaoOrderListTableDTO.getBizId(),
						taobaoOrderListTableDTO.getDataUserIdSets(), 1);
			} else {
				page.setPage(1);
				page.setPageSize(10000);
				page.setParameter(groupOrder);
				page = groupOrderBiz.selectOperatorOrderListPage(page,taobaoOrderListTableDTO.getBizId(),
						taobaoOrderListTableDTO.getDataUserIdSets(), 1);
			}
			webResult.setValue(page);
			webResult.setSuccess(true);
		}catch (Exception e){
			webResult.setSuccess(false);
		}
		return webResult;

	}
	@Override
	public TaobaoOrderListByOpDTO taobaoOrderListByOp(TaobaoOrderListByOpDTO taobaoOrderListByOpDTO) {
		List<DicInfo> pp = dicBiz.getListByTypeCode(BasicConstants.CPXL_PP, taobaoOrderListByOpDTO.getBizId());
		taobaoOrderListByOpDTO.setPp(pp);
		List<RegionInfo> allProvince = regionBiz.getAllProvince();
		taobaoOrderListByOpDTO.setAllProvince(allProvince);


		List<DicInfo> typeList = dicBiz.getListByTypeCode(BasicConstants.SALES_TEAM_TYPE, taobaoOrderListByOpDTO.getBizId());
		//model.addAttribute("typeList", typeList);
		taobaoOrderListByOpDTO.setTypeList(typeList);
		List<DicInfo> sourceTypeList = dicBiz.getListByTypeCode(Constants.GUEST_SOURCE_TYPE, taobaoOrderListByOpDTO.getBizId());
		//model.addAttribute("sourceTypeList", sourceTypeList);
		taobaoOrderListByOpDTO.setSourceTypeList(sourceTypeList);
//		model.addAttribute("orgJsonStr", orgService.getComponentOrgTreeJsonStr(bizId));
//		model.addAttribute("orgUserJsonStr", platformEmployeeService.getComponentOrgUserTreeJsonStr(bizId));
		taobaoOrderListByOpDTO.setOrgJsonStr(platformOrgBiz.getComponentOrgTreeJsonStr(taobaoOrderListByOpDTO.getBizId()));
		taobaoOrderListByOpDTO.setOrgUserJsonStr(platformEmployeeBiz.getComponentOrgUserTreeJsonStr(taobaoOrderListByOpDTO.getBizId()));
		return taobaoOrderListByOpDTO;
	}
	
	  public TaobaoOrderListByOpDTO taobaoOrderListByOp_table(TaobaoOrderListByOpDTO taobaoOrderListByOpDTO) throws ParseException {
		  GroupOrder groupOrder=taobaoOrderListByOpDTO.getGroupOrder();
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
	            set = platformEmployeeBiz.getUserIdListByOrgIdList(taobaoOrderListByOpDTO.getBizId(), set);
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
	            page = groupOrderBiz.selectTaobaoOrderGuestNameListPage(page, taobaoOrderListByOpDTO.getBizId(),
	            		taobaoOrderListByOpDTO.getDataUserIdSets(), 0);
	        } else {
	            page.setParameter(groupOrder);
	            page.setPage(groupOrder.getPage() == null ? 1 : groupOrder.getPage());
	            page.setPageSize(groupOrder.getPageSize() == null ? Constants.PAGESIZE : groupOrder.getPageSize());
	            page = groupOrderBiz.selectTaobaoOrderListPage(page, taobaoOrderListByOpDTO.getBizId(),
	            		taobaoOrderListByOpDTO.getDataUserIdSets(), 0);
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
	            }
	        }
	        List<DicInfo> typeList = dicBiz.getListByTypeCode(BasicConstants.SALES_TEAM_TYPE,
	        		taobaoOrderListByOpDTO.getBizId());
//	        model.addAttribute("typeList", typeList);
//	        model.addAttribute("pageTotalAudit", pageTotalAudit);
//	        model.addAttribute("pageTotalChild", pageTotalChild);
//	        model.addAttribute("pageTotalGuide", pageTotalGuide);
//	        model.addAttribute("pageTotal", pageTotal);
//	        model.addAttribute("page", page);
	        
	        taobaoOrderListByOpDTO.setTypeList(typeList);
	        taobaoOrderListByOpDTO.setPageTotalAudit(pageTotalAudit);
	        taobaoOrderListByOpDTO.setPageTotalChild(pageTotalChild);
	        taobaoOrderListByOpDTO.setPageTotalGuide(pageTotalGuide);
	        taobaoOrderListByOpDTO.setPageTotal(pageTotal);
	        taobaoOrderListByOpDTO.setPage(page);
	        GroupOrder go = null;
	        if(!"".equals(groupOrder.getGuestName()) || !"".equals(groupOrder.getMobile())){
	        	go = groupOrderBiz.selectTotalTaobaoGuestNameOrder(groupOrder,
	        			taobaoOrderListByOpDTO.getBizId(), taobaoOrderListByOpDTO.getDataUserIdSets());
	        }else{
	        	go = groupOrderBiz.selectTotalTaobaoOrder(groupOrder,
	        			taobaoOrderListByOpDTO.getBizId(), taobaoOrderListByOpDTO.getDataUserIdSets());
	        }
//	        model.addAttribute("totalAudit", go.getNumAdult());
//	        model.addAttribute("totalChild", go.getNumChild());
//	        model.addAttribute("totalGuide", go.getNumGuide());
//	        model.addAttribute("total", go.getTotal());
	        taobaoOrderListByOpDTO.setGroupOrder(go);
//	        UserSession user = WebUtils.getCurrentUserSession(request);
//	        Map<String, Boolean> optMap = user.getOptMap();
//	        String menuCode = PermissionConstants.JDGL_JDCZD;
//	        model.addAttribute("CHANGE_PRICE",
//	                optMap.containsKey(menuCode.concat("_").concat(PermissionConstants.CHANGE_PRICE)));
	        return taobaoOrderListByOpDTO;
	    }
	@Override
	public void changeOrderLockState(Integer orderId) {
		// TODO Auto-generated method stub
		groupOrderBiz.changeOrderLockState(orderId);
	}
	@Override
	public void changeorderLockStateByOp(Integer orderId) {
		// TODO Auto-generated method stub
		groupOrderBiz.changeorderLockStateByOp(orderId);
	}
	@Override
	public void goBackOrderLockStateByOp(Integer orderId) {
		// TODO Auto-generated method stub
		groupOrderBiz.goBackOrderLockStateByOp(orderId);
	}
	@Override
	public void updateLockStateToFinance(Integer orderId) {
		// TODO Auto-generated method stub
		groupOrderBiz.updateLockStateToFinance(orderId);
	}
	@Override
	public void goBackToOP(Integer orderId) {
		// TODO Auto-generated method stub
		groupOrderBiz.goBackToOP(orderId);
	}
	@Override
	public AddSivaInfoDTO addSivaInfo(AddSivaInfoDTO addSivaInfoDTO) {
	        GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(addSivaInfoDTO.getOrderId());
	        String extVisa = groupOrder.getExtVisaInfo();

	        GroupOrder orderBean = new GroupOrder();
	        if (!"".equals(extVisa)) {
	            String[] guestInfo = extVisa.split("@");
	            orderBean.setCompanyName(guestInfo[0]);
	            orderBean.setExpressOrderNo(guestInfo[1]);
	            orderBean.setPatTime(guestInfo[2]);
	            orderBean.setReceiptTime(guestInfo[3]);
	            orderBean.setSendSignTime(guestInfo[4]);
	            orderBean.setSendTime(guestInfo[5]);

	        } else {
	            orderBean.setPatTime(addSivaInfoDTO.getBookingDate().substring(0, 10));
	        }
//	        model.addAttribute("orderBean", orderBean);
//	        model.addAttribute("orderMode", orderMode);
//	        model.addAttribute("orderId", orderId);
//	        int bizId = WebUtils.getCurBizId(request);
//	        model.addAttribute("bizId", bizId);
	        addSivaInfoDTO.setOrderBean(orderBean);
	        
	        return addSivaInfoDTO;
	    }
	
	public void saveVisaInfo(AddSivaInfoDTO addSivaInfoDTO) {
        groupOrderBiz.updateExtVisa(addSivaInfoDTO.getOrderBean());
    }
	
	public List<GroupOrder> loadGroupOrderVisaInfo(String mobile) throws ParseException{
        List<GroupOrder> goBeanList = groupOrderBiz.selectByOrderIdExtVisaListPage(mobile);
        List<GroupOrder> groupOrderList = new ArrayList<GroupOrder>();
        if (goBeanList.size() > 0) {
            GroupOrder orderBean = null;
            for (GroupOrder go : goBeanList) {
                orderBean = new GroupOrder();
                orderBean.setGuestName(go.getGuestName());
                orderBean.setMobile(go.getMobile());
                String[] extVisaInfo = go.getExtVisaInfo().split(",");
                for (String guestString : extVisaInfo) {
                    if (guestString.length() > 0) {
                        String[] guestInfo = guestString.split("@");
                        orderBean.setPatTime(guestInfo[2]);
                        orderBean.setReceiptTime(guestInfo[3]);
                        orderBean.setSendSignTime(guestInfo[4]);
                        orderBean.setSendTime(guestInfo[5]);
                    }
                    groupOrderList.add(orderBean);
                }
            }

        }
//        model.addAttribute("groupOrderList", groupOrderList);
        return groupOrderList;
    }
}
