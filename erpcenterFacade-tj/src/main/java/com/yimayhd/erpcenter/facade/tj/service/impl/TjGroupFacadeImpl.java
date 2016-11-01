package com.yimayhd.erpcenter.facade.tj.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.tj.TJBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.TJGroupProfit;
import com.yimayhd.erpcenter.facade.tj.client.query.TjSearchDTO;
import com.yimayhd.erpcenter.facade.tj.client.result.SelectLineProfitListResult;
import com.yimayhd.erpcenter.facade.tj.client.result.SelectShopProjectListResult;
import com.yimayhd.erpcenter.facade.tj.client.result.TJGroupProfitResult;
import com.yimayhd.erpcenter.facade.tj.client.result.TjSearchResult;
import com.yimayhd.erpcenter.facade.tj.client.result.ToGroupListResult;
import com.yimayhd.erpcenter.facade.tj.client.service.TjGroupFacade;
import com.yimayhd.erpresource.biz.service.ContractBiz;
/**
 * 财务统计
 * @author wj
 *
 */
public class TjGroupFacadeImpl implements TjGroupFacade{
	@Autowired
	private TJBiz tjBiz;
	@Autowired
	private DicBiz dicBiz;
	@Autowired
	private PlatformOrgBiz platformOrgBiz;
	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;
	@Autowired
	private ContractBiz contractBiz;
	@Autowired
	private TourGroupBiz tourGroupBiz;
	
	private static final Logger log = LoggerFactory.getLogger(TjGroupFacadeImpl.class);
	
	private DecimalFormat df = new DecimalFormat("0.##");
	SimpleDateFormat dt = new SimpleDateFormat("MM-dd");
	
	public ToGroupListResult list(TjSearchDTO tjSearchDTO){
		ToGroupListResult result = new ToGroupListResult();
		int bizId = tjSearchDTO.getBizId();
		TourGroupVO group = tjSearchDTO.getGroup();
		String page = tjSearchDTO.getPage()+"";
		String pageSize = tjSearchDTO.getPageSize()+"";
		Map<String, Object> pm = tjSearchDTO.getPm();
		String dataUserIdSet = tjSearchDTO.getDataUserIdString();
		String from = tjSearchDTO.getDateFrom();
		String to = tjSearchDTO.getDateTo();
		result.setOrgJsonStr(platformOrgBiz.getComponentOrgTreeJsonStr(bizId));
		result.setOrgUserJsonStr(platformEmployeeBiz.getComponentOrgUserTreeJsonStr(bizId));
		result.setBizId(bizId);
		
		PageBean<TJGroupProfit> pageBean = new PageBean<TJGroupProfit>();
		if (pageSize != null){
			pageBean.setPageSize(new Integer(pageSize));
		}else{
			pageBean.setPageSize(Constants.PAGESIZE);
		}
		if (page != null){
			pageBean.setPage(new Integer(page));
		}
		
//		setArchiveTime(request, model, BasicConstants.TJ_GROUP_PROFIT);
		
		String dateFrom="",  dateTo="";
		if (from==null && to==null){
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
			cal.set( Calendar.DATE, 1 );
			cal.roll(Calendar.DATE, - 1 );
			dateTo=sdf.format(cal.getTime());
			cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
			dateFrom=sdf.format(cal.getTime());
		}else {
			dateFrom = from;
			dateTo = to;
		}
		
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(group.getSaleOperatorIds()) && StringUtils.isNotBlank(group.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = group.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				group.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		pm.put("dateFrom", dateFrom.equals("-1") ? null : dateFrom);
		pm.put("dateTo", dateTo.equals("-1") ? null : dateTo);
		pm.put("dataUser", dataUserIdSet);
		if(null!=group.getSaleOperatorIds() && !"".equals(group.getSaleOperatorIds())){
			pm.put("operatorId", group.getSaleOperatorIds());
		}
		pageBean.setParameter(pm);
		
		TJGroupProfit totalTj = tjBiz.selectTotalGroupProfit(pageBean);
		pageBean = tjBiz.selectGroupProfitListPage(pageBean);
		this.addOrderDetail(pageBean);
		
		result.setPageBean(pageBean);
//		model.addAttribute("pageTotalTj", this.getPageTotal(pageBean.getResult()));
		result.setTotalTj(totalTj);
		return result;
	}
	
	public TjSearchResult GroupProfitList(int bizId){
		TjSearchResult result = new TjSearchResult();
		result.setBizId(bizId);
		result.setOrgJsonStr(platformOrgBiz.getComponentOrgTreeJsonStr(bizId));
		result.setOrgUserJsonStr(platformEmployeeBiz.getComponentOrgUserTreeJsonStr(bizId));
		
		/*if (group.getStartTime()==null && group.getEndTime()==null){
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.set( Calendar.DATE, 1 );
			cal.roll(Calendar.DATE, - 1 );
			group.setStartTime(cal.getTime());

			cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
			group.setEndTime(cal.getTime());
		}*/
		
		return result;
	}

	public ToGroupListResult GroupProfitList_get(TjSearchDTO tjSearchDTO){
		ToGroupListResult result = new ToGroupListResult();
		int bizId = tjSearchDTO.getBizId();
		TourGroupVO group = tjSearchDTO.getGroup();
		String dataUserIdString = tjSearchDTO.getDataUserIdString();
		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(group.getSaleOperatorIds()) && StringUtils.isNotBlank(group.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = group.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				group.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}
		int pageSize = group.getPageSize() == null ? Constants.PAGESIZE : group.getPageSize();
		int page = group.getPage() == null ? 1 : group.getPage();
		PageBean<TJGroupProfit> pageBean = new PageBean<TJGroupProfit>();
		pageBean.setPageSize(pageSize);
		pageBean.setPage(page);
		
		String dataUser = dataUserIdString; //数据权限
		pageBean.setParameter(group);
		
		TJGroupProfit totalTj = tjBiz.selectTotalGroupProfitOu(pageBean, bizId, dataUser);
		pageBean = tjBiz.selectGroupProfitListPageOu(pageBean, bizId, dataUser);
		this.addOrderDetail(pageBean);
		result.setPageBean(pageBean);
//		model.addAttribute("pageTotalTj", this.getPageTotal(pageBean.getResult()));
		result.setTotalTj(totalTj);
		return result;
	}
	
	public PageBean GroupProfitList_GetData(TjSearchDTO tjSearchDTO){
		int bizId = tjSearchDTO.getBizId();
		TourGroupVO group = tjSearchDTO.getGroup();
		String dataUserIdString = tjSearchDTO.getDataUserIdString();
		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(group.getSaleOperatorIds()) && StringUtils.isNotBlank(group.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = group.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				group.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}
		
		int pageSize = group.getPageSize() == null ? Constants.PAGESIZE : group.getPageSize();
		int page = group.getPage() == null ? 1 : group.getPage();
		PageBean<TJGroupProfit> pageBean = new PageBean<TJGroupProfit>();
		pageBean.setPageSize(pageSize);
		pageBean.setPage(page);
		
		String dataUser = dataUserIdString; //数据权限
		pageBean.setParameter(group);
		
		pageBean = tjBiz.selectGroupProfitListPageOu(pageBean, bizId, dataUser);
		return pageBean;
	}
	
	public TJGroupProfitResult GroupProfitList_PostFooter(TjSearchDTO tjSearchDTO){
		TJGroupProfitResult result = new TJGroupProfitResult();
		int bizId = tjSearchDTO.getBizId();
		TourGroupVO group = tjSearchDTO.getGroup();
		String dataUserIdString = tjSearchDTO.getDataUserIdString();
		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(group.getSaleOperatorIds()) && StringUtils.isNotBlank(group.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = group.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				group.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}
		
		int pageSize = group.getPageSize() == null ? Constants.PAGESIZE : group.getPageSize();
		int page = group.getPage() == null ? 1 : group.getPage();
		PageBean<TJGroupProfit> pageBean = new PageBean<TJGroupProfit>();
		pageBean.setPageSize(pageSize);
		pageBean.setPage(page);
		
		String dataUser = dataUserIdString; //数据权限
		pageBean.setParameter(group);
		
		TJGroupProfit totalAll = tjBiz.selectTotalGroupProfitOu(pageBean, bizId, dataUser);
		result.setTjGroupProfit(totalAll);
		return result;
	}
	
	public SelectShopProjectListResult toGroupProfitPrint(TjSearchDTO tjSearchDTO){
		SelectShopProjectListResult result = new SelectShopProjectListResult();
		int bizId = tjSearchDTO.getBizId();
		TourGroupVO group = tjSearchDTO.getGroup();
		Map<String, Object> pm = tjSearchDTO.getPm();
		String dataUserIdString = tjSearchDTO.getDataUserIdString();
		
		if (StringUtils.isBlank(group.getSaleOperatorIds()) && StringUtils.isNotBlank(group.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = group.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				group.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}
		if(null!=group.getSaleOperatorIds() && !"".equals(group.getSaleOperatorIds())){
			pm.put("operatorId", group.getSaleOperatorIds());
		}
		String dataUser = dataUserIdString; //数据权限
		pm.put("dataUser", dataUser);
		
		PageBean<TJGroupProfit> pageBean = new PageBean<TJGroupProfit>();
		pageBean.setPageSize(100000);
		pageBean.setPage(1);		
		pageBean.setParameter(pm);
		
		pageBean = tjBiz.selectGroupProfitListPageOu(pageBean, bizId, dataUser);
		//pageBean = tjService.selectGroupProfitListPage(pageBean);
		this.addOrderDetail2(pageBean);
		
//		String imgPath = bizSettingCommon.getMyBizLogo(request);
//		model.addAttribute("imgPath", imgPath);
		result.setPageBean(pageBean);
//		model.addAttribute("pageTotalTj", this.getPageTotal(pageBean.getResult()));
//		model.addAttribute("printMsg", "打印人："+WebUtils.getCurUser(request).getName()+" <br/>打印时间："+DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return result;
	}
	
	public PageBean<TJGroupProfit> selectGroupProfitListPageOu(PageBean pageBean,int bizId,String dataUser){
		return tjBiz.selectGroupProfitListPageOu(pageBean, bizId, dataUser);
	}
	
	private void addOrderDetail(PageBean pageBean){
		List<TJGroupProfit> list = pageBean.getResult();
		if(list != null && list.size() > 0){
			StringBuilder detailStr = null;
			TJGroupProfit groupProfit = null;
			for(int i = 0; i < list.size(); i++){
				groupProfit = list.get(i);
				List<GroupOrder> orderList = tourGroupBiz.selectOrderAndGuestInfoByGroupId(groupProfit.getGroupId());
				if(orderList == null || orderList.size() == 0){
					continue;
				}
				
				detailStr = new StringBuilder();
				for(int j = 0; j < orderList.size(); j++){
					if(j > 0){
						detailStr.append("<br/>");
					}
					GroupOrder order = orderList.get(j);
					String total = order.getTotal()!=null?df.format(order.getTotal()):df.format(new BigDecimal(0));
					detailStr.append("<label class='order-lb1'>"+ order.getSupplierName()+"</label>");
					detailStr.append("<label class='order-lb2'>"+ order.getNumAdult() +"+"+ order.getNumChild() +"</label>");
					detailStr.append("<label class='order-lb3'>"+ total +"</label>");
					detailStr.append("<label class='order-lb4'>"+ order.getSaleOperatorName()+"</label>");
					detailStr.append("<label class='order-lb5'>"+ order.getSourceTypeName()+"</label>");
				}
				groupProfit.setOrderDetails(detailStr.toString());
			}
		}
	}
	
	/**
	 * 处理销售订单的明细显示
	 * @param pageBean
	 */
	private void addOrderDetail2(PageBean pageBean) {
		List<TJGroupProfit> list = pageBean.getResult();
		if (list != null && list.size() > 0) {
			StringBuilder detailStr = new StringBuilder();
			TJGroupProfit groupProfit = null;
			for (int i = 0; i < list.size(); i++) {
				groupProfit = list.get(i);
				if (!StringUtils.isBlank(groupProfit.getOrderSupplierNames())){
					for (String row : groupProfit.getOrderSupplierNames().split(",")) {
						if (!StringUtils.isBlank(row)){
							String[] rowAry = row.split("@");
							if (rowAry.length > 1) {
								detailStr.append("<p>");
								detailStr.append("<label class='order-lb1'>" + rowAry[0] + "</label>");
								detailStr.append("<label class='order-lb2'>" + rowAry[1] + "+" + rowAry[2] + "</label>");
								detailStr.append("<label class='order-lb3'>" + df.format(new BigDecimal(rowAry[4])) + "</label>");
								detailStr.append("<label class='order-lb4'>" + rowAry[5] + "</label>");
								if (rowAry.length == 7)
									detailStr.append("<label class='order-lb5'>" + rowAry[6] + "</label>");
								detailStr.append("</p>");
							}
						}
					}
				}

				groupProfit.setOrderDetails(detailStr.toString());
				detailStr.setLength(0);
			}
		}
	}	
	private void addOrderDetailExcl(PageBean pageBean){
		List<TJGroupProfit> list = pageBean.getResult();
		if (list != null && list.size() > 0) {
			StringBuilder detailStr = new StringBuilder();
			TJGroupProfit groupProfit = null;
			for (int i = 0; i < list.size(); i++) {
				groupProfit = list.get(i);
				if (!StringUtils.isBlank(groupProfit.getOrderSupplierNames())){
					for (String row : groupProfit.getOrderSupplierNames().split(",")) {
						if (!StringUtils.isBlank(row)){
							String[] rowAry = row.split("@");
							if (rowAry.length > 1) {
								detailStr.append("" + rowAry[0] + " ");
								detailStr.append("" + rowAry[1] + "+" + rowAry[2] + " ");
								detailStr.append("" + df.format(new BigDecimal(rowAry[4])) + " ");
								detailStr.append("" + rowAry[5] + " ");
								if (rowAry.length == 7)
									detailStr.append("" + rowAry[6] + "");
								detailStr.append("\r\n");
							}
						}
					}
				}

				groupProfit.setOrderDetails(detailStr.toString());
				detailStr.setLength(0);
			}
		}
		
		
	}
	
	private TJGroupProfit getPageTotal(List<TJGroupProfit> tjList){
		TJGroupProfit pageTotal = new TJGroupProfit();
		for (TJGroupProfit tj: tjList){
			pageTotal.setTotalAdult(pageTotal.getTotalAdult() + tj.getTotalAdult());
			pageTotal.setTotalChild(pageTotal.getTotalChild() + tj.getTotalChild());
			pageTotal.setTotalGuide(pageTotal.getTotalGuide() + tj.getTotalGuide());
			pageTotal.setIncomeOrder(pageTotal.getIncomeOrder().add(tj.getIncomeOrder()));
			pageTotal.setIncomeOther(pageTotal.getIncomeOther().add(tj.getIncomeOther()));
			pageTotal.setIncomeShop(pageTotal.getIncomeShop().add(tj.getIncomeShop()));
			pageTotal.setExpenseTravelagency(pageTotal.getExpenseTravelagency().add(tj.getExpenseTravelagency()));
			pageTotal.setExpenseHotel(pageTotal.getExpenseHotel().add(tj.getExpenseHotel()));
			pageTotal.setExpenseRestaurant(pageTotal.getExpenseRestaurant().add(tj.getExpenseRestaurant()));
			pageTotal.setExpenseFleet(pageTotal.getExpenseFleet().add(tj.getExpenseFleet()));
			pageTotal.setExpenseScenicspot(pageTotal.getExpenseScenicspot().add(tj.getExpenseScenicspot()));
			pageTotal.setExpenseAirticket(pageTotal.getExpenseAirticket().add(tj.getExpenseAirticket()));
			pageTotal.setExpenseTrainticket(pageTotal.getExpenseTrainticket().add(tj.getExpenseTrainticket()));
			pageTotal.setExpenseInsurance(pageTotal.getExpenseInsurance().add(tj.getExpenseInsurance()));
			pageTotal.setExpenseOther(pageTotal.getExpenseOther().add(tj.getExpenseOther()));
			pageTotal.setTotalIncome(pageTotal.getTotalIncome().add(tj.getTotalIncome()));
			pageTotal.setTotalExpense(pageTotal.getTotalExpense().add(tj.getTotalExpense()));
			pageTotal.setTotalProfit(pageTotal.getTotalProfit().add(tj.getTotalProfit()));
			pageTotal.setProfitPerGuest(pageTotal.getProfitPerGuest().add(tj.getProfitPerGuest()));
		}
		if (tjList.size()!=0){
			pageTotal.setProfitPerGuest(pageTotal.getProfitPerGuest().divide(new BigDecimal(tjList.size()), 2, BigDecimal.ROUND_HALF_UP));
		}
		return pageTotal;
	}
	
//	@RequestMapping("archiveAllGroupProfit.do")
//	public void archiveAllGroupProfit(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException, InterruptedException{
//		model.addAttribute("archiveType", "All");
//		this.initGroupProfitTable(request, response, model);
//	}
//	@RequestMapping("archiveIncrementalGroupProfit.do")
//	public void archiveIncrementalGroupProfit(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException, InterruptedException{
//		model.addAttribute("archiveType", "Incremental");
//		this.initGroupProfitTable(request, response, model);
//	}
//	
//	private void initGroupProfitTable(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException, InterruptedException{
//		response.setContentType("text/html;charset=utf-8");
//		response.getWriter().write("开始归档");
//		response.flushBuffer();
//		
//		List<TJGroupQueryId> groupIdList = new ArrayList<TJGroupQueryId>();
//		if (model.get("archiveType").equals("Incremental")){
//			groupIdList = tjService.findUpdatedGroupIdList("tj_group_profit");
//		}else if (model.get("archiveType").equals("All")){
//			groupIdList = tjService.findAllGroupIdList();
//		}else {
//
//		}
//
//		Long startTime = System.currentTimeMillis();
//		if (model.get("archiveType").equals("All")){
//			tjService.clearAllGroup();
//		}else if (groupIdList.size()>0){
//			tjService.clearUpdatedGroup(groupIdList);
//		}
//		for(int i=0; i<groupIdList.size(); i++){
//			Integer groupId = groupIdList.get(i).getGroupId();
//			Integer bizId = groupIdList.get(i).getBizId();
//			TJGroupProfit tj = new TJGroupProfit();
//			TourGroup g = tourGroupService.selectByPrimaryKey(groupId);
//			if (g==null){
//				continue;
//			}
//			if (g.getGroupState()!=null && g.getGroupState()!=1 && g.getGroupState()!=3 && g.getGroupState()!=4){
//				continue;
//			}
//			tj.setBizId(bizId);
//			tj.setGroupId(groupId);
//			tj.setGroupCode(g.getGroupCode());
//			tj.setGroupMode(g.getGroupMode());
//			tj.setGroupState(g.getGroupState());
//			tj.setDateStart(g.getDateStart());
//			tj.setDateEnd(g.getDateEnd());
//			tj.setProductBrandName(g.getProductBrandName());
//			tj.setProductName(g.getProductName());
//			tj.setTotalAdult(g.getTotalAdult()==null?0:g.getTotalAdult());
//			tj.setTotalChild(g.getTotalChild()==null?0:g.getTotalChild());
//			tj.setTotalGuide(g.getTotalGuide()==null?0:g.getTotalGuide());
//			tj.setOperatorId(g.getOperatorId());
//			tj.setOperatorName(g.getOperatorName());
//			tj.setOperatorCompanyId(this.getCompanyIdByOperatorId(g.getOperatorId()));
//			// 取得组团社信息
//			Set<String> orderSupplierNameList = new HashSet<String>();
//			Set<String> orderSupplierIdList = new HashSet<String>();
//			Set<String> orderIdList = new HashSet<String>();
//			//销售ID
//			Integer saleId = null;
//			for(GroupOrder order: tourGroupService.selectOrderAndGuestInfoByGroupId(groupId)){
//				orderSupplierNameList.add(order.getSupplierName());
//				orderSupplierIdList.add(order.getSupplierId().toString());
//				orderIdList.add(order.getId().toString());
//				if(null == saleId){
//					saleId = order.getSaleOperatorId();
//				}
//			}
//			tj.setSaleOperatorId(saleId);
//			tj.setOrderSupplierIds(StringUtils.join(orderSupplierIdList, ","));
//			tj.setOrderSupplierNames(StringUtils.join(orderSupplierNameList, ","));
//			tj.setOrderIds(StringUtils.join(orderIdList, ","));
//			// 取得地接社信息
//			ArrayList<String> deliveryNameList = new ArrayList<String>();
//			ArrayList<String> deliveryIdList = new ArrayList<String>();
//			for (BookingDelivery delivery : bookingDeliveryService.getDeliveryListByGroupId(groupId)){
//				if (delivery.getSupplierId()==null){
//					log.error("wrong delivery at " + groupId);
//				}
//				deliveryNameList.add(delivery.getSupplierName());
//				deliveryIdList.add(delivery.getSupplierId().toString());
//			}
//			tj.setDeliveryNames(StringUtils.join(deliveryNameList, "<br/>"));
//			tj.setDeliveryIds(StringUtils.join(deliveryIdList, ","));
//			
//			Map fMap = financeService.queryAuditViewInfo(groupId, bizId);
//			// 组团社收入
//			InfoBean incomeOrder = (InfoBean)fMap.get("order");
//			tj.setIncomeOrder(incomeOrder.getNum());
//			tj.setIncomeOrderState(incomeOrder.getIsAudited()?1:0);
//			// 其他收入
//			InfoBean incomeOther = (InfoBean)fMap.get("otherin");
//			tj.setIncomeOther(incomeOther.getNum());
//			tj.setIncomeOtherState(incomeOther.getIsAudited()?1:0);
//			// 购物收入
//			//InfoBean incomeShop = (InfoBean)fMap.get("shop");
//			tj.setIncomeShop(g.getTotalIncomeShop()==null?new BigDecimal(0):g.getTotalIncomeShop());
//			tj.setIncomeShopState(0); // TODO 此处没有取是否审核过
//			// 地接社费用
//			InfoBean expenseDel = (InfoBean)fMap.get("del");
//			tj.setExpenseTravelagency(expenseDel.getNum());
//			tj.setExpenseTravelagencyState(expenseDel.getIsAudited()?1:0);
//			// 其他供应商支出
//			Collection<InfoBean> sups = (Collection<InfoBean>)fMap.get("sup");
//			for(InfoBean info: sups){
//				if (Constants.RESTAURANT.equals(Integer.parseInt(info.getId()))){
//					tj.setExpenseRestaurant(info.getNum());
//					tj.setExpenseRestaurantState(info.getIsAudited()?1:0);
//				}else if (Constants.HOTEL.equals(Integer.parseInt(info.getId()))){
//					tj.setExpenseHotel(info.getNum());
//					tj.setExpenseHotelState(info.getIsAudited()?1:0);
//				}else if (Constants.FLEET.equals(Integer.parseInt(info.getId()))){
//					tj.setExpenseFleet(info.getNum());
//					tj.setExpenseFleetState(info.getIsAudited()?1:0);
//				}else if (Constants.SCENICSPOT.equals(Integer.parseInt(info.getId()))){
//					tj.setExpenseScenicspot(info.getNum());
//					tj.setExpenseScenicspotState(info.getIsAudited()?1:0);
//				}else if (Constants.AIRTICKETAGENT.equals(Integer.parseInt(info.getId()))){
//					tj.setExpenseAirticket(info.getNum());
//					tj.setExpenseAirticketState(info.getIsAudited()?1:0);
//				}else if (Constants.TRAINTICKETAGENT.equals(Integer.parseInt(info.getId()))){
//					tj.setExpenseTrainticket(info.getNum());
//					tj.setExpenseTrainticketState(info.getIsAudited()?1:0);
//				}else if (Constants.INSURANCE.equals(Integer.parseInt(info.getId()))){
//					tj.setExpenseInsurance(info.getNum());
//					tj.setExpenseInsuranceState(info.getIsAudited()?1:0);
//				}else if (Constants.OTHEROUTCOME.equals(Integer.parseInt(info.getId()))){
//					tj.setExpenseOther(info.getNum());
//					tj.setExpenseOtherState(info.getIsAudited()?1:0);
//				}else {
//					log.error("Wrong supplier type, groupId:" + groupId + " and supplierType:"+info.getId());
//				}
//			}
//			// 计算总收入、支出、毛利润
//			tj.setTotalIncome(tj.getIncomeOrder().add(tj.getIncomeOther()).add(tj.getIncomeShop()));
//			tj.setTotalExpense(tj.getExpenseTravelagency().add(tj.getExpenseRestaurant()).add(tj.getExpenseFleet())
//					.add(tj.getExpenseHotel()).add(tj.getExpenseScenicspot()).add(tj.getExpenseAirticket())
//					.add(tj.getExpenseTrainticket()).add(tj.getExpenseInsurance()).add(tj.getExpenseOther()));
//			tj.setTotalProfit(tj.getTotalIncome().subtract(tj.getTotalExpense()));
//			if (tj.getTotalAdult()+tj.getTotalChild()!=0){
//				tj.setProfitPerGuest(tj.getTotalProfit().divide(new BigDecimal(tj.getTotalAdult()+tj.getTotalChild()), 2, BigDecimal.ROUND_HALF_DOWN));
//			}
//
//			BookingShopSelect groupShop = bookingShopService.getShopSelect(groupId);
//			if (groupShop!=null){
//				tj.setShopSales(groupShop.getFactSales());
//				tj.setShopSalesState(groupShop.isAudited()?1:0);
//				tj.setShopRepay(groupShop.getTotalRepay());
//				tj.setShopRepayState(groupShop.isAudited()?1:0);
//			}
//			List<FinanceCommission> commissions = financeGuideService.selectCommissionByGroupId(groupId);
//			BigDecimal tmpComm=new BigDecimal(0);
//			Integer tmpCommState=(commissions.size()>0)?1:0;
//			for (FinanceCommission comm: commissions){
//				tmpComm=tmpComm.add(comm.getTotal());
//				if (comm.getStateFinance()==0){
//					tmpCommState=0;
//				}
//			}
//			tj.setShopCommission(tmpComm);
//			tj.setShopCommissionState(tmpCommState);
//			tjService.insertGroupProfit(tj);
//			if (i%10==0){
//				response.getWriter().write(".");
//				response.flushBuffer();
//			}
//		}
//		insertArchiveRecord(request, startTime, BasicConstants.TJ_GROUP_PROFIT);
//		response.getWriter().write("归档完成。");
//		response.flushBuffer();
//	}
	
//	@RequestMapping("test")
//	@ResponseBody
//	public String test(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException, InterruptedException{
//		HashMap<String, String> json = new HashMap<String, String>();
//		response.setContentType("text/html;charset=utf-8");
//		response.getWriter().write("开始归档");
//		response.flushBuffer();
//		
//		for (int i=0; i<100; i++){
//			
//			
//		}
//		
//		return JSON.toJSONString(json);
//	}
	
	/**
	 * 线路利润统计
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public TjSearchResult toLineProfitList(int bizId) {
		TjSearchResult result = new TjSearchResult();
		result.setBizId(bizId);
		result.setOrgJsonStr(platformOrgBiz.getComponentOrgTreeJsonStr(bizId));
		result.setOrgUserJsonStr(platformEmployeeBiz.getComponentOrgUserTreeJsonStr(bizId));
		return result;
	}
	
	/**
	 * 线路利润统计2
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */             
	public TjSearchResult toLineProfits(int bizId) {
		TjSearchResult result = new TjSearchResult();
		result.setBizId(bizId);
		result.setOrgJsonStr( platformOrgBiz.getComponentOrgTreeJsonStr(bizId));
		result.setOrgUserJsonStr(platformEmployeeBiz.getComponentOrgUserTreeJsonStr(bizId));
		//客源类别
		List<DicInfo> sourceTypeList = dicBiz
				.getListByTypeCode(Constants.GUEST_SOURCE_TYPE,bizId);
		result.setSourceTypeList(sourceTypeList);
		return result;
	}
	
	/**
	 * 查询线路利润
	 * @param request
	 * @param model
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public SelectLineProfitListResult selectLineProfitList(TjSearchDTO tjSearchDTO) {
		int bizId = tjSearchDTO.getBizId();
		Integer page = tjSearchDTO.getPage();
		Integer pageSize = tjSearchDTO.getPageSize();
		String orgIds = tjSearchDTO.getOrgIds();
		String saleOperatorIds = tjSearchDTO.getSaleOperatorIds();
		Map<String, Object> pm = tjSearchDTO.getPm();
		Set<Integer> dataUserIdSet = tjSearchDTO.getDataUserIdSet();
		
		SelectLineProfitListResult result = new SelectLineProfitListResult();
		PageBean pageBean = new PageBean();
		if (page == null) {
			pageBean.setPage(1);
		} else {
			pageBean.setPage(page);
		}
		if (pageSize == null) {
			pageBean.setPageSize(Constants.PAGESIZE);
		} else {
			pageBean.setPageSize(pageSize);
		}
		
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(saleOperatorIds) && StringUtils.isNotBlank(orgIds)){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = orgIds.split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				saleOperatorIds=salesOperatorIds.substring(0, salesOperatorIds.length()-1);
			}
		}
		if(null!=saleOperatorIds && !"".equals(saleOperatorIds)){
			pm.put("operator_id", saleOperatorIds);
		}
		pm.put("set", dataUserIdSet);
		pageBean.setParameter(pm);
		pageBean = tjBiz.selectLineProfitListPage(pageBean);
		result.setPageBean(pageBean);
		Map<String,Object> map = tjBiz.selectLineProfitCount(pageBean);
		if(null!=map){
			result.setAllIncomeOrder(map.get("all_income_order")+"");
			result.setAllIncomeOther(map.get("all_income_other")+"");
			result.setAllShopRepay(map.get("all_shop_repay")+"");
			result.setAllTotalCost(map.get("all_total_cost")+"");
			result.setAllShopCommission(map.get("all_shop_commission")+"");
			result.setAllTotalProfit(map.get("all_total_profit")+"");
			result.setAllShopSales(map.get("all_shop_sales")+"");
			result.setAllSumPerson(map.get("all_sum_person")+"");
			result.setAllTotalAdult(map.get("total_adult")+"");
			result.setAllTotalChild(map.get("total_child")+"");
			result.setAllTotalGuide(map.get("total_guide")+"");
		}
		return result;
	}
	

	
	/**
	 * 查询线路利润2
	 * @param request
	 * @param model
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public SelectLineProfitListResult selectLineProfits(TjSearchDTO tjSearchDTO) {
		int bizId = tjSearchDTO.getBizId();
		Integer page = tjSearchDTO.getPage();
		Integer pageSize = tjSearchDTO.getPageSize();
		String orgIds = tjSearchDTO.getOrgIds();
		String saleOperatorIds = tjSearchDTO.getSaleOperatorIds();
		Map<String, Object> pm = tjSearchDTO.getPm();
		Set<Integer> dataUserIdSet = tjSearchDTO.getDataUserIdSet();
		
		SelectLineProfitListResult result = new SelectLineProfitListResult();
		
		PageBean pageBean = new PageBean();
		if (page == null) {
			pageBean.setPage(1);
		} else {
			pageBean.setPage(page);
		}
		if (pageSize == null) {
			pageBean.setPageSize(Constants.PAGESIZE);
		} else {
			pageBean.setPageSize(pageSize);
		}
		
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(saleOperatorIds) && StringUtils.isNotBlank(orgIds)){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = orgIds.split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				saleOperatorIds=salesOperatorIds.substring(0, salesOperatorIds.length()-1);
			}
		}
		if(null!=saleOperatorIds && !"".equals(saleOperatorIds)){
			pm.put("operator_id", saleOperatorIds);
		}
		pm.put("set", dataUserIdSet);
		pageBean.setParameter(pm);
		pageBean = tjBiz.selectLineProfitListPage(pageBean);
		result.setPageBean(pageBean);
		Map<String,Object> map = tjBiz.selectLineProfitCount(pageBean);
		if(null!=map){
			result.setAllIncomeOrder(map.get("all_income_order")+"");
			result.setAllIncomeOther(map.get("all_income_other")+"");
			result.setAllShopRepay(map.get("all_shop_repay")+"");
			result.setAllTotalCost(map.get("all_total_cost")+"");
			result.setAllShopCommission(map.get("all_shop_commission")+"");
			result.setAllTotalProfit(map.get("all_total_profit")+"");
			result.setAllShopSales(map.get("all_shop_sales")+"");
			result.setAllSumPerson(map.get("all_sum_person")+"");
			result.setAllTotalAdult(map.get("total_adult")+"");
			result.setAllTotalChild(map.get("total_child")+"");
			result.setAllTotalGuide(map.get("total_guide")+"");
		}
		return result;
	}
	
	

//	
//	/**
//	 * 取得最新归档时间，放到Model里
//	 * @param request
//	 * @param model
//	 */
//	private void setArchiveTime(HttpServletRequest request, ModelMap model, String type){
//		Date recordEndTime = tjService.selectRecordEndTime(type, WebUtils.getCurBizId(request));
//		model.addAttribute("recordEndTime", DateUtils.format(recordEndTime, DateUtils.FORMAT_LONG));
//	}
	
//	/**
//	 * 添加归档记录
//	 * @param request
//	 * @param startTime
//	 */
//	private void insertArchiveRecord(HttpServletRequest request, Long startTime, String type){
//		Long endTime = System.currentTimeMillis();
//		//根据类型查询此表上一次统计的结束时间作为本次统计的开始时间
//		Date startDate = tjService.selectRecordEndTime(type, WebUtils.getCurBizId(request));
//		TJRecord tjRecord = new TJRecord();
//		tjRecord.setBizId(WebUtils.getCurBizId(request));
//		tjRecord.setType(type);
//		tjRecord.setStartTime(startDate);
//		tjRecord.setEndTime(new Date());
//		//耗时  秒
//		Long taketime = (endTime-startTime)/1000;
//		tjRecord.setTakeTime(taketime.intValue());
//		//插入记录
//		tjService.insetTJRecord(tjRecord);
//	}
	
	/**
	 * 线路利润统计打印页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public SelectShopProjectListResult lineProfitPrint(TjSearchDTO tjSearchDTO) {
		SelectShopProjectListResult result = new SelectShopProjectListResult();
		int bizId = tjSearchDTO.getBizId();
		Map<String, Object> pm = tjSearchDTO.getPm();
		Set<Integer> dataUserIdSet = tjSearchDTO.getDataUserIdSet();
		String orgIds = tjSearchDTO.getOrgIds();
		String saleOperatorIds = tjSearchDTO.getSaleOperatorIds();
		
		PageBean pageBean = new PageBean();
		pageBean.setPage(1);
		pageBean.setPageSize(1000000);
		
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(saleOperatorIds) && StringUtils.isNotBlank(orgIds)){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = orgIds.split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				saleOperatorIds=salesOperatorIds.substring(0, salesOperatorIds.length()-1);
			}
		}
		if(null!=saleOperatorIds && !"".equals(saleOperatorIds)){
			pm.put("operator_id", saleOperatorIds);
		}
		pm.put("set", dataUserIdSet);
		pageBean.setParameter(pm);
		
		pageBean = tjBiz.selectLineProfitListPage(pageBean);
		Map<String,Object> map = tjBiz.selectLineProfitCount(pageBean);
		if(null!=map){
			result.setAllTotalPerson(map.get("all_sum_person")+"");
		}
//		String imgPath = bizSettingCommon.getMyBizLogo(request);
//		model.addAttribute("imgPath", imgPath);
		result.setPageBean(pageBean);
//		model.addAttribute("printMsg", "打印人："+WebUtils.getCurUser(request).getName()+" 打印时间："+DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return result;
	}
	
	public PageBean selectLineProfitListPage(PageBean pageBean){
		return tjBiz.selectLineProfitListPage(pageBean);
	}
	
	public Map<String,Object> selectLineProfitCount(PageBean pageBean){
		return tjBiz.selectLineProfitCount(pageBean);
	}
	
}
