package com.yimayhd.erpcenter.facade.tj.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductStockBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.SpecialGroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.taobao.TaobaoOrderBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.common.BaseResult;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.MergeGroupOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SpecialGroupOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.taobao.po.PlatTaobaoTrade;
import com.yimayhd.erpcenter.facade.tj.client.errorcode.TjErrorCode;
import com.yimayhd.erpcenter.facade.tj.client.query.ImportTaobaoOrderTableDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.PresellProductStatistics;
import com.yimayhd.erpcenter.facade.tj.client.query.SaveSpecialGroupDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.ShopSalesStatisticsQueryDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.TaobaoOrderListTableDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.TaobaoOriginalOrderTableDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.ToEditTaobaoOrderDTO;
import com.yimayhd.erpcenter.facade.tj.client.result.AddNewTaobaoOrderResult;
import com.yimayhd.erpcenter.facade.tj.client.result.ImportTaobaoOrderTableResult;
import com.yimayhd.erpcenter.facade.tj.client.result.PresellProductStatisticsListResult;
import com.yimayhd.erpcenter.facade.tj.client.result.SaveSpecialGroupResult;
import com.yimayhd.erpcenter.facade.tj.client.result.ShopSalesStatisticsResult;
import com.yimayhd.erpcenter.facade.tj.client.result.TaobaoOrderListResult;
import com.yimayhd.erpcenter.facade.tj.client.result.TaobaoOrderListTableResult;
import com.yimayhd.erpcenter.facade.tj.client.result.ToEditTaobaoOrderResult;
import com.yimayhd.erpcenter.facade.tj.client.service.TaobaoFacade;

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
	
	/**
	 * 操作单
	 * 
	 * @param request
	 * @param model
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
	 * @param request
	 * @param reponse
	 * @param model
	 * @param groupOrder
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
		page.setParameter(groupOrder);
		page.setPage(groupOrder.getPage()==null?1:groupOrder.getPage());
		page.setPageSize(groupOrder.getPageSize() == null ? Constants.PAGESIZE
				: groupOrder.getPageSize());
		page =groupOrderBiz.selectSpecialOrderListPage(page, bizId,taobaoOrderListTableDTO.getDataUserIdSets());
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
		GroupOrder go = groupOrderBiz.selectTotalSpecialOrder(groupOrder, bizId,taobaoOrderListTableDTO.getDataUserIdSets());
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
				count = productStockBiz.getRestCountByProductIdAndDate(groupOrder.getProductId(),sdf.parse(groupOrder.getDepartureDate()));
			} catch (ParseException e) {
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
		
		return result;
	}

	/**
	 * 跳转到新增订单页面
	 * @param request
	 * @param reponse
	 * @param model
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
	 * @param request
	 * @param reponse
	 * @param model
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
		Integer orderId;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Integer newNum = vo.getGroupOrder().getNumAdult()
				+ vo.getGroupOrder().getNumChild();
		Integer oldNum = 0;
		try {
			if("stock".equals(vo.getGroupOrder().getOrderBusiness())){
			if (vo.getGroupOrder().getId() != null) {
				GroupOrder groupOrder =groupOrderBiz.findById(vo.getGroupOrder().getId());
				oldNum = groupOrder.getNumAdult()
						+groupOrder.getNumChild();
			}
			//查出库存(剩余人数)
			int freeCount = productStockBiz.getRestCountByProductIdAndDate(vo.getGroupOrder().getProductId(),sdf.parse(vo.getGroupOrder().getDepartureDate()));
			//实际库存应该是修改前人数+库存
			freeCount = oldNum + freeCount;
			if(newNum > freeCount){
				//如果新增人数大于库存,则不能保存
				saveSpecialGroupResult.setErrorCode(TjErrorCode.INVENTORY_SHORTAGE);
				saveSpecialGroupResult.setResultMsg(errorJson("由于库存剩余数有变化，目前剩余库存不足【" + newNum + "】！实际库存还有【" + freeCount + "】"));
				return saveSpecialGroupResult;
			}
			
			if (vo.getGroupOrder().getId() == null) {
				productStockBiz.updateStockCount(vo.getGroupOrder()
						.getProductId(), sdf.parse(vo.getGroupOrder()
						.getDepartureDate()), newNum );
				}
			}
			orderId = specialGroupOrderBiz.saveOrUpdateSpecialOrderInfo(vo,userId,userName,bizId);
			//todo取出原来ids，并对比现在在的ids，得到要删除的ids ,　　比如原来：１,２,３,４　删除了2,3－> 14, 
			String id = saveSpecialGroupDTO.getTaobaoOrderId();
			if(id !="" && id.length()>0){
				id=id.substring(0,id.length()-1);
				taobaoOrderBiz.updateTaobaoOrderIdToZero(id);
			}
			String ids = saveSpecialGroupDTO.getTaobaoOrderIds();
			if(ids.length()>0 && ids !=""){
				taobaoOrderBiz.updateTaobaoOrderId(orderId, ids);
			}
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
		saveSpecialGroupResult.setResultJson(successJson("groupId",orderId+""));
		return saveSpecialGroupResult;
	}
	
	/**
	 * 淘宝订单导入页面table
	 * @param request
	 * @param model
	 * @param pageSize
	 * @param page
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
	 * @param request
	 * @param model
	 * @return
	 */
	public String taobaoOrder_GetByIds(String ids){
		List<PlatTaobaoTrade> orders = null; 
		orders=taobaoOrderBiz.selectTaobaoOrderById(ids);
		return JSON.toJSONString(orders);
	}
	
	/**
	 * 淘宝原始单table（爱游）
	 * @param request
	 * @param model
	 * @param pageSize
	 * @param page
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
		
		PageBean<PlatTaobaoTrade> pageBean = taobaoOrderBiz.selectTaobaoOrder(taobaoOriginalOrderTableDTO.getPageBean(), taobaoOriginalOrderTableDTO.getBizId());
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
	
	
}
