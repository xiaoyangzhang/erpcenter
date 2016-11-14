package com.yimayhd.erpcenter.facade.finance.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.yimayhd.erpcenter.facade.finance.errorcode.FinanceErrorCode;
import org.yimayhd.erpcenter.facade.finance.query.AduditStatisticsListDTO;
import org.yimayhd.erpcenter.facade.finance.query.AuditCommDTO;
import org.yimayhd.erpcenter.facade.finance.query.AuditDTO;
import org.yimayhd.erpcenter.facade.finance.query.AuditListDTO;
import org.yimayhd.erpcenter.facade.finance.query.AuditShopDTO;
import org.yimayhd.erpcenter.facade.finance.query.CheckBillDTO;
import org.yimayhd.erpcenter.facade.finance.query.DiatributeBillDTO;
import org.yimayhd.erpcenter.facade.finance.query.FinAuditDTO;
import org.yimayhd.erpcenter.facade.finance.query.IncomeJoinTableListDTO;
import org.yimayhd.erpcenter.facade.finance.query.IncomeOrPayDTO;
import org.yimayhd.erpcenter.facade.finance.query.PayDTO;
import org.yimayhd.erpcenter.facade.finance.query.QuerySettleCommissionDTO;
import org.yimayhd.erpcenter.facade.finance.query.QuerySettleListDTO;
import org.yimayhd.erpcenter.facade.finance.query.QueryShopCommissionStatsDTO;
import org.yimayhd.erpcenter.facade.finance.query.ReceiveOrderListSelectDTO;
import org.yimayhd.erpcenter.facade.finance.query.SaveDistributeBillDTO;
import org.yimayhd.erpcenter.facade.finance.query.SaveVerifyBillDTO;
import org.yimayhd.erpcenter.facade.finance.query.SettleListPageDTO;
import org.yimayhd.erpcenter.facade.finance.query.SettleSealListDTO;
import org.yimayhd.erpcenter.facade.finance.query.StatementCheckPreviewDTO;
import org.yimayhd.erpcenter.facade.finance.query.SubjectSummaryDTO;
import org.yimayhd.erpcenter.facade.finance.query.ToBookingShopVerifyListDTO;
import org.yimayhd.erpcenter.facade.finance.query.UnsealDTO;
import org.yimayhd.erpcenter.facade.finance.query.VerifyBillDTO;
import org.yimayhd.erpcenter.facade.finance.result.CheckBillResult;
import org.yimayhd.erpcenter.facade.finance.result.DiatributeBillResult;
import org.yimayhd.erpcenter.facade.finance.result.IncomeOrPaytResult;
import org.yimayhd.erpcenter.facade.finance.result.QuerySettleCommissionResult;
import org.yimayhd.erpcenter.facade.finance.result.QuerySettleListResult;
import org.yimayhd.erpcenter.facade.finance.result.QueryShopCommissionStatsResult;
import org.yimayhd.erpcenter.facade.finance.result.ReceiveOrderListSelectResult;
import org.yimayhd.erpcenter.facade.finance.result.ResultSupport;
import org.yimayhd.erpcenter.facade.finance.result.SettleCommissionListResult;
import org.yimayhd.erpcenter.facade.finance.result.SettleListPageResult;
import org.yimayhd.erpcenter.facade.finance.result.SettleSealListResult;
import org.yimayhd.erpcenter.facade.finance.result.StatementCheckPreviewResult;
import org.yimayhd.erpcenter.facade.finance.result.SubjectSummaryResult;
import org.yimayhd.erpcenter.facade.finance.result.ToBookingShopVerifyListlResult;
import org.yimayhd.erpcenter.facade.finance.result.TourGroupDetiailsResult;
import org.yimayhd.erpcenter.facade.finance.result.VerifyBillResult;
import org.yimayhd.erpcenter.facade.finance.result.ViewShopCommissionStatsListResult;
import org.yimayhd.erpcenter.facade.finance.service.FinanceFacade;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBillBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryPriceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.CommonSaleBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderPriceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.SysBizBankAccountBiz;
import com.yimayhd.erpcenter.common.util.NumberUtil;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceGuide;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePayDetail;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.InfoBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDeliveryPrice;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.FinanceBillDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.biz.service.SupplierGuideBiz;
import com.yimayhd.erpresource.dal.constants.BasicConstants;
import com.yimayhd.erpresource.dal.constants.Constants;
import com.yimayhd.erpresource.dal.po.SupplierBankaccount;
import com.yimayhd.erpresource.dal.po.SupplierGuide;
import com.yimayhd.erpresource.dal.po.SupplierInfo;

public class FinanceFacadeImpl implements FinanceFacade{

	@Autowired
	private FinanceBiz financeBiz;
	
	@Autowired
	private TourGroupBiz tourGroupBiz;
	
	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private BookingGuideBiz bookingGuideBiz;
	
	@Autowired
	private DicBiz dicBiz;
	
	@Autowired
	private SysBizBankAccountBiz sysBizBankAccountBiz;
	
	@Autowired
	private FinanceGuideBiz financeGuideBiz;
	
	@Autowired
	private BookingSupplierBiz bookingSupplierBiz;

	@Autowired
	private GroupOrderPriceBiz groupOrderPriceBiz;
	
	@Autowired
	private SupplierBiz supplierBiz;
	
	@Autowired
	private FinanceBillBiz financeBillBiz;
	
	@Autowired
	private BookingShopDetailBiz bookingShopDetailBiz;
	
	@Autowired
	private BookingSupplierDetailBiz bookingSupplierDetailBiz;
	
	@Autowired
	private BookingDeliveryPriceBiz bookingDeliveryPriceBiz;
	
	@Autowired
	private BookingShopBiz bookingShopBiz;
	
	@Autowired
	private SupplierGuideBiz supplierGuideBiz;
	
	@Autowired
	private GroupOrderBiz groupOrderBiz;
	
	@Autowired
	private BookingDeliveryBiz bookingDeliveryBiz;
	
	/**
	 * 此方法用来维护团金额的一致性
	 * @param request
	 * @param reponse
	 * @param model
	 * @param groupId
	 * @return
	 */
	@Override
	public ResultSupport calcTourGroupAmount(Integer groupId) {
		
		ResultSupport result = new ResultSupport();
		try{
			if(groupId != null){
				financeBiz.calcTourGroupAmount(groupId);
			}
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * 此方法用来批量维护团金额的一致性
	 * @param request
	 * @param reponse
	 * @param model
	 * @param startTime 出团开始日期
	 * @param endTime	出团结束日期
	 * @param bizId
	 * @return
	 */
	@Override
	public ResultSupport batchCalcTourGroupAmount(Integer bizId, Map paramters) {
		
		ResultSupport result = new ResultSupport();
		try{
			PageBean pb = new PageBean();
			if(bizId != null){
				paramters.put("bizId", bizId);
			}
			pb.setParameter(paramters);
			List<TourGroup> results = tourGroupBiz.selectIdList(pb);
			
			if(results != null && results.size() > 0){
				TourGroup group = null;
				for(int i = 0; i < results.size(); i++){
					group = results.get(i);
					financeBiz.calcTourGroupAmount(group.getId());
				}
			}
			
			result.setResultMsg("calculate over! fixed "+ results.size() +" tour groups");
			
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport calcBookingSupplierTotalCash(Integer bookingSupplierId) throws IOException {
		
		ResultSupport result = new ResultSupport();
		try{
			if(bookingSupplierId == null){
				result.setErrorCode(FinanceErrorCode.PARAM_ERROR);
				return result;
			}
			
			financeBiz.calcBookingSupplierTotalCash(bookingSupplierId);
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport calcGroupOrderTotalCash(Integer groupOrderId) throws IOException {
		
		ResultSupport result = new ResultSupport();
		if(groupOrderId == null){
			result.setErrorCode(FinanceErrorCode.PARAM_ERROR);
			return result;
		}
		
		try{
			financeBiz.calcGroupOrderTotalCash(groupOrderId);
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport calcGroupTotalCash(Integer groupId) {
		if(groupId != null){
			financeBiz.calcTotalCash_collection(groupId);
		}
		ResultSupport result = new ResultSupport();
		result.setResultMsg("completed!");
		return result;
	}
	
	/**
	 * 获取审核人列表
	 * @return
	 */
	@Override
	public List<TourGroup> getAuditorList() {
		List<TourGroup> auditorList = tourGroupBiz.getAuditorList();
		return auditorList;
	}

	@Override
	public StatementCheckPreviewResult statementCheckPreview(StatementCheckPreviewDTO queryDTO) {

		PageBean pb = new PageBean();
		pb.setPage(1);
		pb.setPageSize(100000);
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(queryDTO.getSaleOperatorIds()) && StringUtils.isNotBlank(queryDTO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = queryDTO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				queryDTO.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		Map<String,Object> pms  = queryDTO.getParamters();
		if(null!=queryDTO.getSaleOperatorIds() && !"".equals(queryDTO.getSaleOperatorIds())){
			pms.put("operator_id", queryDTO.getSaleOperatorIds());
		}
		pms.put("set", queryDTO.getSet());
		pb.setParameter(pms);
		pb = getCommonService(queryDTO.getSvc()).queryListPage(queryDTO.getSl(), pb);
		
		Map<Integer, String> guideMap = new HashMap<Integer, String>();
		List<Map> results = pb.getResult();
		Map item = null;
		for (int i = 0; i < results.size(); i++) {
			item = results.get(i);
			Integer groupId = Integer.parseInt(item.get("id").toString());
			List<BookingGuide> bookingGuides = bookingGuideBiz.selectGuidesByGroupId(groupId);
			StringBuffer s = new StringBuffer();
			for (int j = 0; j < bookingGuides.size(); j++) {
				if (j == (bookingGuides.size() - 1)) {
					s.append(bookingGuides.get(j).getGuideName());
				} else {
					s.append(bookingGuides.get(j).getGuideName() + ",");
				}
				item.put("userName", bookingGuides.get(j).getUserName());
			}
			guideMap.put(groupId, s.toString());
			
			BigDecimal totalIncome = NumberUtil.parseObj2Num(item.get("total_income"));
			BigDecimal totalCost = NumberUtil.parseObj2Num(item.get("total_cost"));
			
			//团收入 = 团收入 - 购物汇总
			InfoBean shop = financeBiz.statsShopWithCommInfoBean(groupId);
			totalIncome = totalIncome.subtract(shop.getNum());
			
			item.put("total_income", totalIncome);
			item.put("total_cost", totalCost);
			item.put("total_profit", totalIncome.subtract(totalCost));

		}
		
		StatementCheckPreviewResult result = new StatementCheckPreviewResult();
		result.setGuideMap(guideMap);
		result.setPageBean(pb);
		
		return result;
	}

	@Override
	public SettleSealListResult settleSealList(SettleSealListDTO queryDTO) {
		
		PageBean pb = new PageBean();
		pb.setPage(queryDTO.getPage());
		pb.setPageSize(queryDTO.getPageSize());
		
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(queryDTO.getSaleOperatorIds()) && StringUtils.isNotBlank(queryDTO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = queryDTO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				queryDTO.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		Map<String,Object> pms  = queryDTO.getParamters();
		if(null!= queryDTO.getSaleOperatorIds() && !"".equals(queryDTO.getSaleOperatorIds())){
			pms.put("operator_id", queryDTO.getSaleOperatorIds());
		}
		pms.put("set", queryDTO.getSet());
		pb.setParameter(pms);
		pb = getCommonService(queryDTO.getSvc()).queryListPage(queryDTO.getSl(), pb);
		Map<Integer, String> guideMap = new HashMap<Integer, String>();
		List<Map> results = pb.getResult();
		Map item = null;
		for (int i = 0; i < results.size(); i++) {
			item = results.get(i);
			Integer groupId = Integer.parseInt(item.get("id").toString());
			
			List<BookingGuide> bookingGuides = bookingGuideBiz.selectGuidesByGroupId(groupId);
			StringBuffer s = new StringBuffer();
			for (int j = 0; j < bookingGuides.size(); j++) {
				if (j == (bookingGuides.size() - 1)) {
					s.append(bookingGuides.get(j).getGuideName());
				} else {
					s.append(bookingGuides.get(j).getGuideName() + ",");
				}
			}
			guideMap.put(groupId, s.toString());
			
			BigDecimal totalIncome = NumberUtil.parseObj2Num(item.get("total_income"));
			BigDecimal totalIncomeShop = NumberUtil.parseObj2Num(item.get("total_income_shop"));
			BigDecimal totalCost = NumberUtil.parseObj2Num(item.get("total_cost"));
			
			//团收入 = 团收入 - 购物收入
			totalIncome = totalIncome.subtract(totalIncomeShop);
			
			item.put("total_income", totalIncome);
			item.put("total_profit", totalIncome.subtract(totalCost));

		}
		
		SettleSealListResult result = new SettleSealListResult();
		result.setGuideMap(guideMap);
		result.setPageBean(pb);
		return result;
	}

	private IncomeOrPaytResult handResponse(IncomeOrPaytResult result, IncomeOrPayDTO incomeOrPayDTO){
		
		List<DicInfo> payTypeList = dicBiz.getListByTypeCode(BasicConstants.CW_ZFFS);
		result.setPayTypeList(payTypeList);
		
		List<SysBizBankAccount> bizAccountList = sysBizBankAccountBiz.getListByBizId(incomeOrPayDTO.getBizId());
		result.setBizAccountList(bizAccountList);
		return result;
	}
	
	@Override
	public IncomeOrPaytResult incomeView(IncomeOrPayDTO incomeOrPayDTO) {
		
		IncomeOrPaytResult result = new IncomeOrPaytResult();

		this.handResponse(result, incomeOrPayDTO);
		
		FinancePay pay = financeBiz.queryPayById(incomeOrPayDTO.getPayId());
		result.setPay(pay);
		
		return result;
	}

	@Override
	public IncomeOrPaytResult payView(IncomeOrPayDTO incomeOrPayDTO) {
		
		FinancePay pay = financeBiz.queryPayById(incomeOrPayDTO.getPayId());
		IncomeOrPaytResult result = new IncomeOrPaytResult();
		result.setPay(pay);
		return result;
	}

	@Override
	public PageBean incomeJoinTableList(IncomeJoinTableListDTO queryDTO) {
		
		PageBean pageBean = new PageBean();
		pageBean.setPage(queryDTO.getPage());
		pageBean.setPageSize(queryDTO.getPageSize());
		
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(queryDTO.getSaleOperatorIds()) && StringUtils.isNotBlank(queryDTO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = queryDTO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				queryDTO.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		Map<String,Object> pm  = queryDTO.getParamters();
		if(null!=queryDTO.getSaleOperatorIds() && !"".equals(queryDTO.getSaleOperatorIds())){
			pm.put("saleOperatorIds", queryDTO.getSaleOperatorIds());
		}
		pm.put("set", queryDTO.getSet());
		pageBean.setParameter(pm);
		pageBean = financeBiz.selectIncomeJoinTableListPage(pageBean, queryDTO.getBizId());
		
		return pageBean;
		
	}

	@Override
	public IncomeOrPaytResult incomeAdd(IncomeOrPayDTO incomeOrPayDTO) {
		
		IncomeOrPaytResult result = new IncomeOrPaytResult();
		this.handResponse(result, incomeOrPayDTO);
		
		Integer payId = incomeOrPayDTO.getPayId();
		if (payId != null) {
			FinancePay pay = financeBiz.queryPayById(payId);
			result.setPay(pay);
		}else{
			result.setCurrDate(new Date());
		}
		
		return result;
	}

	@Override
	public IncomeOrPaytResult payAdd(IncomeOrPayDTO incomeOrPayDTO) {
		IncomeOrPaytResult result = new IncomeOrPaytResult();
		this.handResponse(result, incomeOrPayDTO);
		
		Integer payId = incomeOrPayDTO.getPayId();
		if (payId != null) {
			FinancePay pay = financeBiz.queryPayById(payId);
			result.setPay(pay);
		}else{
			result.setCurrDate(new Date());
		}
		
		return result;
	}

	@Override
	public IncomeOrPaytResult incomeRecordList(IncomeOrPayDTO incomeOrPayDTO) {
		IncomeOrPaytResult result = new IncomeOrPaytResult();
		this.handResponse(result, incomeOrPayDTO);
		return result;
	}

	@Override
	public IncomeOrPaytResult payRecordList(IncomeOrPayDTO incomeOrPayDTO) {
		IncomeOrPaytResult result = new IncomeOrPaytResult();
		this.handResponse(result, incomeOrPayDTO);
		return result;
	}

	@Override
	public TourGroup auditList(Integer groupId) {
		TourGroup  tourGroup = tourGroupBiz.selectByPrimaryKey(groupId);
		return tourGroup;
	}

	@Override
	public Map queryAuditViewInfo(Integer bizId, Integer groupId) {
		Map map = financeBiz.queryAuditViewInfo(groupId, bizId);
		return map;
	}

	@Override
	public PageBean aduditStatisticsList(AduditStatisticsListDTO queryDTO) {
		
		PageBean pb = new PageBean();
		pb.setPage(queryDTO.getPage());		
		pb.setPageSize(queryDTO.getPageSize());
		
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(queryDTO.getSaleOperatorIds()) && StringUtils.isNotBlank(queryDTO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = queryDTO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				queryDTO.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		Map<String,Object> pms  = queryDTO.getParamters();
		if(null != queryDTO.getSaleOperatorIds() && !"".equals(queryDTO.getSaleOperatorIds())){
			pms.put("operator_id", queryDTO.getSaleOperatorIds());
		}
		pms.put("set", queryDTO.getSet());
		pb.setParameter(pms);
		pb = getCommonService(queryDTO.getSvc()).queryListPage(queryDTO.getSl(), pb);
		
		return pb;
	}

	@Override
	public List<Map<String, String>> getAuditUserList(String name, Integer bizId) throws UnsupportedEncodingException {
		List<Map<String, String>> list = tourGroupBiz.getAuditUserList(bizId,java.net.URLDecoder.decode(name, "UTF-8"));
		return list;
	}

	@Override
	public List<TourGroup> operateLog(Integer groupId) {
		List<TourGroup> operateLogs = financeBiz.getOperateLogs(groupId);
		return operateLogs;
	}

	@Override
	public ResultSupport audit(AuditDTO auditDTO) {
		
		ResultSupport result = new ResultSupport();
		try{
			//处理审核
			financeBiz.audit(auditDTO.getGroupId(), auditDTO.getFeeType(), auditDTO.getCheckedIds(), auditDTO.getUnCheckedIds(),
					auditDTO.getComCheckedIds(), auditDTO.getComUnCheckedIds(), auditDTO.getEmployeeId(), auditDTO.getEmployeeName());
			
			if(StringUtils.isNotEmpty(auditDTO.getFinanceGuides())){
				//处理导游报账
				List<FinanceGuide> list = JSONArray.parseArray(auditDTO.getFinanceGuides(), FinanceGuide.class);
				financeGuideBiz.financeBatchSave(list);
				
				//修改订单结算方式
				FinanceGuide fg = null;
				for(int i = 0; i < list.size(); i++){
					fg = list.get(i);
					BookingSupplier supplier = new BookingSupplier();
					supplier.setId(fg.getBookingIdLink());
					supplier.setCashType(fg.getCashType());
					bookingSupplierBiz.updateByPrimaryKeySelective(supplier);
				}
			}
			
			//审核订单价格
			groupOrderPriceBiz.auditPriceByIds(auditDTO.getPriceCheckedIds(), auditDTO.getPriceUnCheckedIds());
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		
		return result;
	}

	/**
	 * 批量审核
	 */
	@Override
	public ResultSupport auditList(AuditListDTO auditListDTO) {
		
		ResultSupport result = new ResultSupport();
		try{
			//处理审核
			financeBiz.audit(auditListDTO.getData(), auditListDTO.getEmployeeId(), auditListDTO.getEmployeeName());
			
			if(StringUtils.isNotEmpty(auditListDTO.getFinanceGuides())){
				
				//处理导游报账
				List<FinanceGuide> list = JSONArray.parseArray(auditListDTO.getFinanceGuides(), FinanceGuide.class);
				financeGuideBiz.financeBatchSave(list);
				
				//修改订单结算方式
				FinanceGuide fg = null;
				for(int i = 0; i < list.size(); i++){
					fg = list.get(i);
					BookingSupplier supplier = new BookingSupplier();
					supplier.setId(fg.getBookingIdLink());
					supplier.setCashType(fg.getCashType());
					bookingSupplierBiz.updateByPrimaryKeySelective(supplier);
				}
			}
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * 结算单审核
	 */
	@Override
	public ResultSupport finAudit(FinAuditDTO finAuditDTO) {
		
		ResultSupport result = new ResultSupport();
		try{
			financeBiz.audit(finAuditDTO.getGroupId(), finAuditDTO.getEmployeeId(), finAuditDTO.getEmployeeName());
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * 结算单取消审核
	 */
	@Override
	public ResultSupport finUnAudit(FinAuditDTO finAuditDTO) {
		ResultSupport result = new ResultSupport();
		try{
			financeBiz.unAudit(finAuditDTO.getGroupId(), finAuditDTO.getEmployeeName());
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport batchSeal(UnsealDTO unsealDTO) {

		ResultSupport result = new ResultSupport();
		try{
			financeBiz.batchSeal(unsealDTO.getGroupIds(), unsealDTO.getEmployeeId(), unsealDTO.getEmployeeName());
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport unseal(UnsealDTO unsealDTO) {
		ResultSupport result = new ResultSupport();
		try{
			financeBiz.unseal(unsealDTO.getGroupIds(), unsealDTO.getEmployeeId(), unsealDTO.getEmployeeName());
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public String pay(PayDTO payDTO) {
		
		FinancePay pay = payDTO.getFinancePay();
		if(pay != null){
			pay.setUserId(payDTO.getEmployeeId());
			pay.setUserName(payDTO.getEmployeeName());
			pay.setBizId(payDTO.getBizId());
		}
		
		//以下几句获得收付款当前商家的类别，因为其它收入，其它支出是共用一个商家类别，代码里面求各是需要判断用到 ou
		SupplierInfo sInfo = supplierBiz.selectBySupplierId(pay.getSupplierId());
		int SupplierType = pay.getSupplierType();
		if (sInfo != null)
			SupplierType = sInfo.getSupplierType();
		
		FinancePay rt = financeBiz.pay(pay,	JSON.parseArray(pay.getDetails(), FinancePayDetail.class), SupplierType);
		return String.valueOf(rt.getId());
	}

	@Override
	public List<SupplierBankaccount> querySupplierBankAccountList(Integer sid) {
		List<SupplierBankaccount> list = supplierBiz.selectBankBySupplierId(sid);
		return list;
	}

	@Override
	public ReceiveOrderListSelectResult receiveOrderListSelect(ReceiveOrderListSelectDTO queryDTO) {
		
		PageBean pageBean = new PageBean();
		pageBean.setPage(queryDTO.getPage());
		pageBean.setPageSize(queryDTO.getPageSize());
		
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(queryDTO.getSaleOperatorIds()) && StringUtils.isNotBlank(queryDTO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = queryDTO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				queryDTO.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		Map<String,Object> pm  = queryDTO.getParamters();
		if(null != queryDTO.getSaleOperatorIds() && !"".equals(queryDTO.getSaleOperatorIds())){
			pm.put("saleOperatorIds", queryDTO.getSaleOperatorIds());
		}
		pm.put("set", queryDTO.getSet());
		pageBean.setParameter(pm);
		pageBean = financeBillBiz.selectreceiveOrderListSelectPage(pageBean, queryDTO.getBizId(), "finance", queryDTO.getSet());

		List<DicInfo> billTypeList = dicBiz.getListByTypeCode(BasicConstants.LD_DJLX, queryDTO.getBizId());
		
		ReceiveOrderListSelectResult result = new ReceiveOrderListSelectResult();
		result.setPageBean(pageBean);
		result.setBillTypeList(billTypeList);
		
		return result;
	}

	@Override
	public List<Map<String, String>> fuzzyApplicantList(Integer bizId, String name) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			list = financeBillBiz.getFuzzyApplicantList(bizId, java.net.URLDecoder.decode(name, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public DiatributeBillResult diatributeBill(DiatributeBillDTO diatributeBillDTO) {
		
		List<FinanceBillDetail> financeBillDetailList = financeBillBiz.getbillListByIdAndGuideId(diatributeBillDTO.getBizId(), 
				diatributeBillDTO.getId(), diatributeBillDTO.getGuideId());
		
		DiatributeBillResult result = new DiatributeBillResult();
		if (null != financeBillDetailList && financeBillDetailList.size() > 0) {
			Map financeBillDetail = (Map) financeBillDetailList.get(0);
			result.setGuideName((String)financeBillDetail.get("guide_name"));
			result.setApplicant((String)financeBillDetail.get("applicant"));
			result.setAppliTime((String)financeBillDetail.get("appli_time"));
			result.setFinanceBillDetailList(financeBillDetailList);
			
		}
		result.setNowDate(new Date());

		List<DicInfo> billTypeList = dicBiz.getListByTypeCode(BasicConstants.LD_DJLX, diatributeBillDTO.getBizId());
		result.setBillTypeList(billTypeList);

		return result;
	}

	@Override
	public VerifyBillResult verifyBill(VerifyBillDTO verifyBillDTO) {
		
		List<FinanceBillDetail> financeBillDetailList = financeBillBiz.getbillListByIdAndGuideId(verifyBillDTO.getBizId(), 
				verifyBillDTO.getId(),verifyBillDTO.getGuideId());
		
		VerifyBillResult result = new VerifyBillResult();
		if (null != financeBillDetailList && financeBillDetailList.size() > 0) {
			Map financeBillDetail = (Map) financeBillDetailList.get(0);
			result.setGuideName((String)financeBillDetail.get("guide_name"));
			result.setApplicant((String)financeBillDetail.get("applicant"));
			result.setAppliTime((String)financeBillDetail.get("appli_time"));
			result.setApprTime((String)financeBillDetail.get("appr_time"));
			result.setFinanceBillDetailList(financeBillDetailList);
		}
		result.setNowDate(new Date());

		List<DicInfo> billTypeList = dicBiz.getListByTypeCode(BasicConstants.LD_DJLX, verifyBillDTO.getBizId());
		result.setBillTypeList(billTypeList);
		
		return result;
	}

	@Override
	public CheckBillResult checkBill(CheckBillDTO checkBillDTO) {
		List<FinanceBillDetail> financeBillDetailList = financeBillBiz.getbillListByIdAndGuideId(checkBillDTO.getBizId(), 
				checkBillDTO.getId(), checkBillDTO.getGuideId());
		
		CheckBillResult result = new CheckBillResult();
		if (null != financeBillDetailList && financeBillDetailList.size() > 0) {
			Map financeBillDetail = (Map) financeBillDetailList.get(0);
			result.setGuideName((String)financeBillDetail.get("guide_name"));
			result.setApplicant((String)financeBillDetail.get("applicant"));
			result.setAppliTime((String)financeBillDetail.get("appli_time"));
			result.setApprTime((String)financeBillDetail.get("appr_time"));
			result.setVeriTime((String)financeBillDetail.get("veri_time"));
			result.setAppliState(checkBillDTO.getAppliState());
			result.setFinanceBillDetailList(financeBillDetailList);
		}
		List<DicInfo> billTypeList = dicBiz.getListByTypeCode(BasicConstants.LD_DJLX, checkBillDTO.getBizId());
		result.setBillTypeList(billTypeList);
		
		return result;
	}

	@Override
	public CheckBillResult checkBillPrint(CheckBillDTO checkBillDTO) {
		
		List<FinanceBillDetail> financeBillDetailList = financeBillBiz.getbillListByIdAndGuideId(checkBillDTO.getBizId(), 
				checkBillDTO.getId(), checkBillDTO.getGuideId());
		
		CheckBillResult result = new CheckBillResult();
		if (null != financeBillDetailList && financeBillDetailList.size() > 0) {
			Map financeBillDetail = (Map) financeBillDetailList.get(0);
			result.setGuideName((String)financeBillDetail.get("guide_name"));
			result.setApplicant((String)financeBillDetail.get("applicant"));
			result.setAppliTime((String)financeBillDetail.get("appli_time"));
			result.setApprTime((String)financeBillDetail.get("appr_time"));
			result.setVeriTime((String)financeBillDetail.get("veri_time"));
			result.setAppliState(checkBillDTO.getAppliState());
			result.setFinanceBillDetailList(financeBillDetailList);
		}
		List<DicInfo> billTypeList = dicBiz.getListByTypeCode(BasicConstants.LD_DJLX, checkBillDTO.getBizId());
		result.setBillTypeList(billTypeList);
		
		TourGroup tour = tourGroupBiz.selectByPrimaryKey(Integer.parseInt(checkBillDTO.getId()));
		result.setTourGroup(tour);
		return result;
	}

	@Override
	public ResultSupport saveDistributeBill(SaveDistributeBillDTO saveDistributeBillDTO) {
		
		ResultSupport result = new ResultSupport();
		try{
			financeBillBiz.saveDistributeBill(saveDistributeBillDTO.getBillList());
			financeBillBiz.updateFinanceOrder(saveDistributeBillDTO.getLoginName(), saveDistributeBillDTO.getGroupId(), 
					saveDistributeBillDTO.getGuideId(), new Date(), saveDistributeBillDTO.getType());
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport saveVerifyBill(SaveVerifyBillDTO saveVerifyBillDTO) {
		ResultSupport result = new ResultSupport();
		try{
			financeBillBiz.saveDistributeBill(saveVerifyBillDTO.getBillList());
			financeBillBiz.updateFinanceOrder(saveVerifyBillDTO.getLoginName(), saveVerifyBillDTO.getGroupId(), 
					saveVerifyBillDTO.getGuideId(), new Date(), saveVerifyBillDTO.getType());
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport delVerify(String orderId, String type) {
		ResultSupport result = new ResultSupport();
		try{	
			financeBillBiz.delVerify(orderId, type);
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport delReceived(String groupId, String guideId) {
		
		ResultSupport result = new ResultSupport();
		try{	
			financeBillBiz.delReceived(groupId, guideId);
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Map<String, String>> getUserNameList(Integer bizId, String name){
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try{	
			list = financeBillBiz.getUserNameList(bizId, java.net.URLDecoder.decode(name, "UTF-8"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}

	@Override
	public SettleListPageResult settleListPage(SettleListPageDTO queryDTO) {
		
		PageBean pb = new PageBean();
		pb.setPage(queryDTO.getPage());
		pb.setPageSize(queryDTO.getPageSize());
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(queryDTO.getSaleOperatorIds()) && StringUtils.isNotBlank(queryDTO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = queryDTO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				queryDTO.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		Map<String,Object> pms  = queryDTO.getParamters();
		if(null != queryDTO.getSaleOperatorIds() && !"".equals(queryDTO.getSaleOperatorIds())){
			pms.put("operator_id", queryDTO.getSaleOperatorIds());
		}
		pms.put("set", queryDTO.getSet());
		pb.setParameter(pms);
		pb = getCommonService(queryDTO.getSvc()).queryListPage(queryDTO.getSl(), pb);
		
		Map<Integer, String> guideMap = new HashMap<Integer, String>();
		List<Map> results = pb.getResult();
		Map item = null;
		for (int i = 0; i < results.size(); i++) {
			item = results.get(i);
			Integer groupId = Integer.parseInt(item.get("id").toString());
			List<BookingGuide> bookingGuides = bookingGuideBiz.selectGuidesByGroupId(groupId);
			StringBuffer s = new StringBuffer();
			for (int j = 0; j < bookingGuides.size(); j++) {
				if (j == (bookingGuides.size() - 1)) {
					s.append(bookingGuides.get(j).getGuideName());
				} else {
					s.append(bookingGuides.get(j).getGuideName() + ",");
				}
				item.put("userName", bookingGuides.get(j).getUserName());
			}
			guideMap.put(groupId, s.toString());
			
			BigDecimal totalIncome = NumberUtil.parseObj2Num(item.get("total_income"));
			BigDecimal totalCost = NumberUtil.parseObj2Num(item.get("total_cost"));
			
			//团收入 = 团收入 - 购物汇总
			InfoBean shop = financeBiz.statsShopWithCommInfoBean(groupId);
			totalIncome = totalIncome.subtract(shop.getNum());
			
			item.put("total_income", totalIncome);
			item.put("total_profit", totalIncome.subtract(totalCost));

		}

		// 总计查询
		Map sumMap = null;
		if (StringUtils.isNotBlank(queryDTO.getSsl())) {
			Map pm = (Map) pb.getParameter();
			pm.put("parameter", pm);
			sumMap = getCommonService(queryDTO.getSvc()).queryOne(queryDTO.getSsl(), pm);
		}
		
		SettleListPageResult result = new SettleListPageResult();
		result.setPageBean(pb);
		result.setGuideMap(guideMap);
		result.setSumMap(sumMap);
		
		return result;
	}

	@Override
	public QuerySettleListResult querySettleList(QuerySettleListDTO queryDTO) {
		
		String feeType = queryDTO.getFeeType();
		Map<String, Object> pm = queryDTO.getParamters();
		List<Map<String, Object>> list = getCommonService(queryDTO.getSvc()).queryList(queryDTO.getSl(), pm);
		Integer bizId = queryDTO.getBizId();
		if(list != null && list.size() > 0 ){
			
			for(Map<String, Object> item : list){
				Integer bookingId = Integer.parseInt(item.get("id").toString());
				String details = "";
				if("order".equals(feeType)){
					boolean isShow = pm.get("isShow") != null ? Boolean.parseBoolean(pm.get("isShow").toString()) : false;
					List<GroupOrderPrice> priceList = groupOrderPriceBiz.selectByOrderAndType(bookingId, 0);
					details = groupOrderPriceBiz.concatDetailTable(priceList, isShow, queryDTO.getEdit());
				}else if("shop".equals(feeType)){
					List<BookingShopDetail> detailList = bookingShopDetailBiz.getShopDetailListByBookingId(bookingId);
					details = bookingShopDetailBiz.concatDetailTable(detailList);
				}else if("del".equals(feeType)){
					List<BookingDeliveryPrice> priceList = bookingDeliveryPriceBiz.getPriceListByBookingId(bookingId);
					details = bookingDeliveryPriceBiz.concatDetailTable(priceList);
				}else if("sup".equals(feeType) || "otherin".equals(feeType)){
					String remark = item.get("remark") != null ? item.get("remark").toString() : "";
					List<BookingSupplierDetail> detailList = bookingSupplierDetailBiz.selectByPrimaryBookId(bookingId);
					details = bookingSupplierDetailBiz.concatDetailTable(queryDTO.getSupType(), remark, detailList);
				}
				item.put("details", details);
			}
		}
		
		QuerySettleListResult result = new QuerySettleListResult();
		result.setList(list);
		
		if("sup".equals(feeType) || "otherin".equals(feeType)){
			String groupId = queryDTO.getGroupId();
			if(StringUtils.isNotEmpty(groupId)){
				List<BookingGuide> bookingGuides = bookingGuideBiz.selectGuidesByGroupId(Integer.parseInt(groupId));
				result.setBookingGuides(bookingGuides);
			}
			
			//从字典中查询结算方式
			List<DicInfo> cashTypes = null;
			if("otherin".equals(feeType)){
				cashTypes = dicBiz.getListByTypeCode(BasicConstants.QTSR_JSFS,bizId);
			}else{
				cashTypes = dicBiz.getListByTypeCode(BasicConstants.GYXX_JSFS,bizId);
			}
			result.setCashTypes(cashTypes);
		}
		return result;
	}

	@Override
	public ResultSupport deleteFinancePay(Integer id) {
		
		ResultSupport result = new ResultSupport();
		try {
			financeBiz.deletePayById(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
		}
		return result;
	}

	@Override
	public ResultSupport deleteFinancePayDetail(Integer supplierType, Integer locOrderId, Integer payId) {
		ResultSupport result = new ResultSupport();
		try {
			financeBiz.deletePayDetail(supplierType, locOrderId, payId);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
		}
		return result;
	}

	@Override
	public ResultSupport batchDeleteFinancePayDetail(Integer supplierType, String locOrderIds, Integer payId) {
		
		ResultSupport result = new ResultSupport();
		
		if(StringUtils.isEmpty(locOrderIds)){
			return result;
		}
		
		try {
			String[] idArr = locOrderIds.split(",");
			for(int i = 0; i < idArr.length; i++){
				String locOrderId = idArr[i];
				if(StringUtils.isEmpty(locOrderId)){
					continue;
				}
				financeBiz.deletePayDetail(supplierType, Integer.parseInt(locOrderId), payId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
		}
		return result;
	}

	@Override
	public ToBookingShopVerifyListlResult toBookingShopVerifyList(ToBookingShopVerifyListDTO queryDTO) {
		
		PageBean pageBean = new PageBean();
		pageBean.setPage(queryDTO.getPage());
		pageBean.setPageSize(queryDTO.getPageSize());
		
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(queryDTO.getSaleOperatorIds()) && StringUtils.isNotBlank(queryDTO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = queryDTO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				queryDTO.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		Map<String,Object> pm  = queryDTO.getParamters();
		if(null != queryDTO.getSaleOperatorIds() && !"".equals(queryDTO.getSaleOperatorIds())){
			pm.put("operator_id", queryDTO.getSaleOperatorIds());
		}
		pm.put("set", queryDTO.getSet());
		pageBean.setParameter(pm);
		pageBean = bookingShopBiz.selectShopVerifyListPage(pageBean, queryDTO.getBizId());
		
		//合计
		Map<String,Object> sumMap = bookingShopBiz.selectShopVerifySum(pageBean, queryDTO.getBizId());
		List<SupplierGuide> supplierGuides = supplierGuideBiz.getAllGuide();
		
		ToBookingShopVerifyListlResult result = new ToBookingShopVerifyListlResult();
		result.setPageBean(pageBean);
		result.setSumMap(sumMap);
		result.setSupplierGuides(supplierGuides);

		return result;
	}

	@Override
	public ResultSupport auditShop(AuditShopDTO auditShopDTO) {
	
		ResultSupport result = new ResultSupport();
		try {
			financeBiz.auditShop(auditShopDTO.getCheckedIds(), auditShopDTO.getUnCheckedIds(), auditShopDTO.getEmployeeId(),
					auditShopDTO.getEmployeeName());
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
		}
		return result;
	}

	/**
	 * 购物佣金发放统计
	 */
	@Override
	public String shopCommissionStatsList(Integer bizId) {
		String projectTypeJsonStr = dicBiz.getCommProjectTypeTreeJsonStr(BasicConstants.YJ_XMLX, bizId);
		return projectTypeJsonStr;
	}

	@Override
	public String shopCommissionDeductionStatsList(Integer bizId) {
		String projectTypeJsonStr = dicBiz.getCommProjectTypeTreeJsonStr(BasicConstants.YJ_KKXM, bizId);
		return projectTypeJsonStr;
	}

	@Override
	public ViewShopCommissionStatsListResult viewShopCommissionStatsList(Integer bizId) {
		//产品品牌下拉列表数据
		List<DicInfo> brand = dicBiz.getListByTypeCode(BasicConstants.CPXL_PP, bizId);
		String projectTypeJsonStr = dicBiz.getCommProjectTypeTreeJsonStr(BasicConstants.YJ_XMLX, bizId);

		ViewShopCommissionStatsListResult result = new ViewShopCommissionStatsListResult();
		result.setBrand(brand);
		result.setProjectTypeJsonStr(projectTypeJsonStr);
		return result;
	}

	@Override
	public ViewShopCommissionStatsListResult viewShopCommissionDeductionStatsList(Integer bizId) {
		//产品品牌下拉列表数据
		List<DicInfo> brand = dicBiz.getListByTypeCode(BasicConstants.CPXL_PP, bizId);
		String projectTypeJsonStr = dicBiz.getCommProjectTypeTreeJsonStr(BasicConstants.YJ_KKXM, bizId);

		ViewShopCommissionStatsListResult result = new ViewShopCommissionStatsListResult();
		result.setBrand(brand);
		result.setProjectTypeJsonStr(projectTypeJsonStr);
		return result;
	}

	@Override
	public QueryShopCommissionStatsResult queryShopCommissionStats(QueryShopCommissionStatsDTO queryDTO) {
		
		PageBean pageBean = new PageBean();
		pageBean.setPage(queryDTO.getPage());
		pageBean.setPageSize(queryDTO.getPageSize());
		
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(queryDTO.getSaleOperatorIds()) && StringUtils.isNotBlank(queryDTO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = queryDTO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				queryDTO.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		Map<String,Object> pm  = queryDTO.getParamters();
		if(null!=queryDTO.getSaleOperatorIds() && !"".equals(queryDTO.getSaleOperatorIds())){
			pm.put("operator_id", queryDTO.getSaleOperatorIds());
		}
		pm.put("set", queryDTO.getSet());
		pageBean.setParameter(pm);
		pageBean = financeGuideBiz.getCommisionStatsListPage(pageBean);
		List<Map<String, Object>> data = pageBean.getResult();
		List<BookingGuide> guides = new ArrayList<BookingGuide>();
		if(data != null && data.size() > 0){
			for(int i = 0 ; i < data.size(); i++){
				BookingGuide guide = financeGuideBiz.parseDataToGuide(data.get(i), pageBean, false);
				guides.add(guide);
				
				SupplierGuide sg = supplierGuideBiz.getGuideInfoById(guide.getGuideId());
				if(sg != null){
					guide.setBankAccount(sg.getBankAccount());
				}
			}
		}
		pageBean.setResult(guides);

		List<DicInfo> dicInfoList = dicBiz.getListByTypeCode(BasicConstants.YJ_XMLX, queryDTO.getBizId());
		
		//总合计
		Map<String, Object> sums = financeGuideBiz.getCommisionsSum(pageBean);

		QueryShopCommissionStatsResult result = new QueryShopCommissionStatsResult();
		result.setPageBean(pageBean);
		result.setDicInfoList(dicInfoList);
		result.setSums(sums);
		return result;
	}

	@Override
	public QueryShopCommissionStatsResult queryShopCommissionStats2(QueryShopCommissionStatsDTO queryDTO) {
		PageBean pageBean = new PageBean();
		pageBean.setPage(queryDTO.getPage());
		pageBean.setPageSize(queryDTO.getPageSize());
		
		Map paramters = queryDTO.getParamters();
		String carInfo = (String)paramters.get("carInfo");
		if (StringUtils.isNotBlank(carInfo)) {
			List<Integer> groupIds = bookingSupplierBiz.getGroupIdByCarInfo(carInfo);
			if (groupIds!=null && groupIds.size()>0) {
				String groupIdStr = groupIds.toString().substring(1,groupIds.toString().length()-1);
				paramters.put("groupIds", groupIdStr);
				//bookingDeliveryPrice.setGroupIds(groupIdStr);
			}else{
				paramters.put("groupIds", "9999999999");
			}
		}
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(queryDTO.getSaleOperatorIds()) && StringUtils.isNotBlank(queryDTO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = queryDTO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				queryDTO.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		
		if(null != queryDTO.getSaleOperatorIds() && !"".equals(queryDTO.getSaleOperatorIds())){
			paramters.put("operator_id", queryDTO.getSaleOperatorIds());
		}
		paramters.put("set", queryDTO.getSet());
		pageBean.setParameter(paramters);
		pageBean = financeGuideBiz.getCommisionStatsListPage(pageBean);
		List<Map<String, Object>> data = pageBean.getResult();
		List<BookingGuide> guides = new ArrayList<BookingGuide>();
		if(data != null && data.size() > 0){
			for(int i = 0 ; i < data.size(); i++){
				BookingGuide guide = financeGuideBiz.parseDataToGuide(data.get(i), pageBean, false);
				guides.add(guide);
				
				SupplierGuide sg = supplierGuideBiz.getGuideInfoById(guide.getGuideId());
				if(sg != null){
					guide.setBankAccount(sg.getBankAccount());
				}
			}
		}
		
		pageBean.setResult(guides);
		
		//总合计
		Map<String, Object> sums = financeGuideBiz.getCommisionsSum(pageBean);
		
		List<DicInfo> dicInfoList = dicBiz.getListByTypeCode(BasicConstants.YJ_XMLX, queryDTO.getBizId());
		
		QueryShopCommissionStatsResult result = new QueryShopCommissionStatsResult();
		result.setPageBean(pageBean);
		result.setSums(sums);
		result.setDicInfoList(dicInfoList);
		
		return result;
	}

	@Override
	public QueryShopCommissionStatsResult queryShopCommissionDeductionStats(QueryShopCommissionStatsDTO queryDTO) {
		PageBean pageBean = new PageBean();
		pageBean.setPage(queryDTO.getPage());
		pageBean.setPageSize(queryDTO.getPageSize());
		
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(queryDTO.getSaleOperatorIds()) && StringUtils.isNotBlank(queryDTO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = queryDTO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				queryDTO.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		Map<String,Object> pm  = queryDTO.getParamters();
		if(null != queryDTO.getSaleOperatorIds() && !"".equals(queryDTO.getSaleOperatorIds())){
			pm.put("operator_id", queryDTO.getSaleOperatorIds());
		}
		pm.put("set", queryDTO.getSet());
		pageBean.setParameter(pm);
		pageBean = financeGuideBiz.getCommisionDeductionStatsListPage(pageBean);
		List<Map<String, Object>> data = pageBean.getResult();
		List<BookingGuide> guides = new ArrayList<BookingGuide>();
		if(data != null && data.size() > 0){
			for(int i = 0 ; i < data.size(); i++){
				BookingGuide guide = financeGuideBiz.parseDataToGuide(data.get(i), pageBean, true);
				guides.add(guide);
				
				SupplierGuide sg = supplierGuideBiz.getGuideInfoById(guide.getGuideId());
				if(sg != null){
					guide.setBankAccount(sg.getBankAccount());
				}
			}
		}
		pageBean.setResult(guides);

		List<DicInfo> dicInfoList = dicBiz.getListByTypeCode(BasicConstants.YJ_KKXM, queryDTO.getBizId());
		
		//总合计
		Map<String, Object> sums = financeGuideBiz.getCommisionsDeductionSum(pageBean);
		
		QueryShopCommissionStatsResult result = new QueryShopCommissionStatsResult();
		result.setPageBean(pageBean);
		result.setDicInfoList(dicInfoList);
		result.setSums(sums);
		
		return result;
	}

	@Override
	public QueryShopCommissionStatsResult queryShopCommissionDeductionStats2(QueryShopCommissionStatsDTO queryDTO) {
		PageBean pageBean = new PageBean();
		pageBean.setPage(queryDTO.getPage());
		pageBean.setPageSize(queryDTO.getPageSize());
		
		Map paramters = queryDTO.getParamters();
		String carInfo = (String)paramters.get("carInfo");
		if (StringUtils.isNotBlank(carInfo)) {
			List<Integer> groupIds = bookingSupplierBiz.getGroupIdByCarInfo(carInfo);
			if (groupIds!=null && groupIds.size()>0) {
				String groupIdStr = groupIds.toString().substring(1,groupIds.toString().length()-1);
				paramters.put("groupIds", groupIdStr);
				//bookingDeliveryPrice.setGroupIds(groupIdStr);
			}else{
				paramters.put("groupIds", "9999999999");
			}
		}
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(queryDTO.getSaleOperatorIds()) && StringUtils.isNotBlank(queryDTO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = queryDTO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				queryDTO.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		
		if(null != queryDTO.getSaleOperatorIds() && !"".equals(queryDTO.getSaleOperatorIds())){
			paramters.put("operator_id", queryDTO.getSaleOperatorIds());
		}
		paramters.put("set", queryDTO.getSet());
		pageBean.setParameter(paramters);
		pageBean = financeGuideBiz.getCommisionDeductionStatsListPage(pageBean);
		List<Map<String, Object>> data = pageBean.getResult();
		List<BookingGuide> guides = new ArrayList<BookingGuide>();
		if(data != null && data.size() > 0){
			for(int i = 0 ; i < data.size(); i++){
				BookingGuide guide = financeGuideBiz.parseDataToGuide(data.get(i), pageBean, true);
				guides.add(guide);
				
				SupplierGuide sg = supplierGuideBiz.getGuideInfoById(guide.getGuideId());
				if(sg != null){
					guide.setBankAccount(sg.getBankAccount());
				}
			}
		}
		pageBean.setResult(guides);
		
		//总合计
		Map<String, Object> sums = financeGuideBiz.getCommisionsDeductionSum(pageBean);
		
		List<DicInfo> dicInfoList = dicBiz.getListByTypeCode(BasicConstants.YJ_KKXM, queryDTO.getBizId());
		
		QueryShopCommissionStatsResult result = new QueryShopCommissionStatsResult();
		result.setPageBean(pageBean);
		result.setDicInfoList(dicInfoList);
		result.setSums(sums);

		return result;
	}

	@Override
	public ResultSupport auditComm(AuditCommDTO auditCommDTO) {
		
		ResultSupport result = new ResultSupport();
		try{
			financeBiz.auditComm(auditCommDTO.getCheckedIds(), auditCommDTO.getUnCheckedIds(), 
					auditCommDTO.getEmployeeId(), auditCommDTO.getEmployeeName());
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport auditCommDeduction(AuditCommDTO auditCommDTO) {
		ResultSupport result = new ResultSupport();
		try{
			financeBiz.auditCommDeduction(auditCommDTO.getCheckedIds(), auditCommDTO.getUnCheckedIds(), 
					auditCommDTO.getEmployeeId(), auditCommDTO.getEmployeeName());
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public SettleCommissionListResult settleCommissionList(Integer bizId, Integer groupId) {
		
		SettleCommissionListResult result = new SettleCommissionListResult();
		
		List<DicInfo> payTypeList = dicBiz.getListByTypeCode(BasicConstants.GYXX_JSFS);
		result.setPayTypeList(payTypeList);
		
		List<SysBizBankAccount> bizAccountList = sysBizBankAccountBiz.getListByBizId(bizId);
		result.setBizAccountList(bizAccountList);

		List<DicInfo> bankList = dicBiz.getListByTypeCode(BasicConstants.SUPPLIER_BANK);
		result.setBankList(bankList);

		List<BookingGuide> guideList = bookingGuideBiz.selectDistinctListByGroupId(groupId);
		result.setGuideList(guideList);

		String projectTypeJsonStr = dicBiz.getCommProjectTypeTreeJsonStr(BasicConstants.YJ_XMLX, bizId);
		result.setProjectTypeJsonStr(projectTypeJsonStr);
		
		return result;
	}

	@Override
	public SettleCommissionListResult settleCommissionDeductionList(Integer bizId, Integer groupId) {
		SettleCommissionListResult result = new SettleCommissionListResult();
		
		List<DicInfo> payTypeList = dicBiz.getListByTypeCode(BasicConstants.GYXX_JSFS);
		result.setPayTypeList(payTypeList);
		
		List<SysBizBankAccount> bizAccountList = sysBizBankAccountBiz.getListByBizId(bizId);
		result.setBizAccountList(bizAccountList);

		List<DicInfo> bankList = dicBiz.getListByTypeCode(BasicConstants.SUPPLIER_BANK);
		result.setBankList(bankList);

		List<BookingGuide> guideList = bookingGuideBiz.selectDistinctListByGroupId(groupId);
		result.setGuideList(guideList);

		String projectTypeJsonStr = dicBiz.getCommProjectTypeTreeJsonStr(BasicConstants.YJ_KKXM, bizId);
		result.setProjectTypeJsonStr(projectTypeJsonStr);
		
		return result;
	}

	/**
	 * 购物佣金发放统计列表
	 * @return
	 */
	@Override
	public QuerySettleCommissionResult querySettleCommission(QuerySettleCommissionDTO queryDTO) {
		
		PageBean pageBean = new PageBean();
		pageBean.setPage(queryDTO.getPage());
		pageBean.setPageSize(queryDTO.getPageSize());
		
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(queryDTO.getSaleOperatorIds()) && StringUtils.isNotBlank(queryDTO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = queryDTO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				queryDTO.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		Map<String,Object> pm  = queryDTO.getParamters();
		if(null != queryDTO.getSaleOperatorIds() && !"".equals(queryDTO.getSaleOperatorIds())){
			pm.put("operator_id", queryDTO.getSaleOperatorIds());
		}
		pm.put("set", queryDTO.getSet());
		pageBean.setParameter(pm);
		pageBean = financeGuideBiz.getCommisionStatsListPage(pageBean);
		List<Map<String, Object>> data = pageBean.getResult();
		List<BookingGuide> guides = new ArrayList<BookingGuide>();
		if(data != null && data.size() > 0){
			for(int i = 0 ; i < data.size(); i++){
				BookingGuide guide = financeGuideBiz.parseDataToGuide(data.get(i), pageBean, false);
				guides.add(guide);
				
				SupplierGuide sg = supplierGuideBiz.getGuideInfoById(guide.getGuideId());
				if(sg != null){
					guide.setBankAccount(sg.getBankAccount());
				}
			}
		}
		pageBean.setResult(guides);

		List<DicInfo> dicInfoList = dicBiz.getListByTypeCode(BasicConstants.YJ_XMLX, queryDTO.getBizId());
		
		QuerySettleCommissionResult result = new QuerySettleCommissionResult();
		result.setPageBean(pageBean);
		result.setDicInfoList(dicInfoList);

		return result;
	}
	
	/**
	 * 购物佣金扣除统计列表
	 */
	@Override
	public QuerySettleCommissionResult querySettleCommissionDeduction(QuerySettleCommissionDTO queryDTO) {
		PageBean pageBean = new PageBean();
		pageBean.setPage(queryDTO.getPage());
		pageBean.setPageSize(queryDTO.getPageSize());
		
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(queryDTO.getSaleOperatorIds()) && StringUtils.isNotBlank(queryDTO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = queryDTO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				queryDTO.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		Map<String,Object> pm  = queryDTO.getParamters();
		if(null != queryDTO.getSaleOperatorIds() && !"".equals(queryDTO.getSaleOperatorIds())){
			pm.put("operator_id", queryDTO.getSaleOperatorIds());
		}
		pm.put("set", queryDTO.getSet());
		pageBean.setParameter(pm);
		pageBean = financeGuideBiz.getCommisionDeductionStatsListPage(pageBean);
		List<Map<String, Object>> data = pageBean.getResult();
		List<BookingGuide> guides = new ArrayList<BookingGuide>();
		if(data != null && data.size() > 0){
			for(int i = 0 ; i < data.size(); i++){
				BookingGuide guide = financeGuideBiz.parseDataToGuide(data.get(i), pageBean, true);
				guides.add(guide);
				
				SupplierGuide sg = supplierGuideBiz.getGuideInfoById(guide.getGuideId());
				if(sg != null){
					guide.setBankAccount(sg.getBankAccount());
				}
			}
		}
		pageBean.setResult(guides);

		List<DicInfo> dicInfoList = dicBiz.getListByTypeCode(BasicConstants.YJ_KKXM, queryDTO.getBizId());

		QuerySettleCommissionResult result = new QuerySettleCommissionResult();
		result.setPageBean(pageBean);
		result.setDicInfoList(dicInfoList);
			
		return result;
	}

	@Override
	public SubjectSummaryResult subjectSummary(SubjectSummaryDTO queryDTO) {
		PageBean pageBean = new PageBean();
		pageBean.setPage(queryDTO.getPage());
		pageBean.setPageSize(queryDTO.getPageSize());
			
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(queryDTO.getSaleOperatorIds()) && StringUtils.isNotBlank(queryDTO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = queryDTO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				queryDTO.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		Map<String,Object> pm  = queryDTO.getParamters();
		if(null != queryDTO.getSaleOperatorIds() && !"".equals(queryDTO.getSaleOperatorIds())){
			pm.put("operator_id", queryDTO.getSaleOperatorIds());
		}
		pm.put("set", queryDTO.getSet());
		pageBean.setParameter(pm);
		pageBean = financeBiz.getsubjectSummaryListPage(pageBean);
		
		//将一下内容放到了dal中，已方便做开关，支持solr检索
//		List<Map<String,Object>> lists = (List<Map<String,Object>>)pageBean.getResult();
//		for (Map<String, Object> map : lists) {
//			Map<String,Object> qdyj=financeBiz.getsubjectSummaryQDYJ(pageBean,Integer.parseInt(map.get("supplier_id").toString()),Constants.SUMJECT_SUMMARY_QDYJ);
//			map.put("qd_total_cash", qdyj==null?0:qdyj.get("total_cash"));
//			map.put("qd_total", qdyj==null?0:qdyj.get("total"));
//			
//			Map<String,Object> dyxf=financeBiz.getsubjectSummaryQDYJ(pageBean,Integer.parseInt(map.get("supplier_id").toString()),Constants.SUMJECT_SUMMARY_DYXF);
//			map.put("dy_total_cash", dyxf==null?0:dyxf.get("total_cash"));
//			map.put("dy_total", dyxf==null?0:dyxf.get("total"));
//			
//			Map<String,Object> gsxf=financeBiz.getsubjectSummaryQDYJ(pageBean,Integer.parseInt(map.get("supplier_id").toString()),Constants.SUMJECT_SUMMARY_GSXF);
//			map.put("gs_total_cash", gsxf==null?0:gsxf.get("total_cash"));
//			map.put("gs_total", gsxf==null?0:gsxf.get("total"));
//			
//			Map<String,Object> qt=financeBiz.getsubjectSummaryQT(pageBean,Integer.parseInt(map.get("supplier_id").toString()),Constants.SUMJECT_SUMMARY_GSXF,Constants.SUMJECT_SUMMARY_QDYJ,Constants.SUMJECT_SUMMARY_DYXF);
//			map.put("qt_total_cash", qt==null?0:qt.get("total_cash"));
//			map.put("qt_total", qt==null?0:qt.get("total"));
//			
//			//Map<String,Object> dybz=financeService.getsubjectSummaryQDYJ(pageBean,Integer.parseInt(map.get("supplier_id").toString()),null);
//
//			
//			BigDecimal dy_total=new BigDecimal(map.get("dy_total").toString());
//			/**
//			 * 导游报账只针对导游现付才有效
//			 * 比如：订单金额车费8300，结算方式是：导游现付，导游报账5000，那么：结果就是：导游现付=5000，签单月结=3300
//			 */
//			BigDecimal qd_total=new BigDecimal(map.get("qd_total").toString());
//			if(null != dyxf){
//				BigDecimal dybz_total=new BigDecimal(dyxf.get("dybz_total").toString());
//				qd_total = dy_total.subtract(dybz_total).add(qd_total);
//				map.put("dy_total",dybz_total);
//			}
//			map.put("qd_total",qd_total);
//		}

		List<DicInfo> dicInfoList = dicBiz.getListByTypeCode(BasicConstants.YJ_XMLX, queryDTO.getBizId());
		
		SubjectSummaryResult result = new SubjectSummaryResult();
		result.setPageBean(pageBean);
		result.setDicInfoList(dicInfoList);
		
		return result;
	}

	@Override
	public SubjectSummaryResult subjectSummary2(SubjectSummaryDTO queryDTO) {
		PageBean pageBean = new PageBean();
		pageBean.setPage(queryDTO.getPage());
		pageBean.setPageSize(queryDTO.getPageSize());
		
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(queryDTO.getSaleOperatorIds()) && StringUtils.isNotBlank(queryDTO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = queryDTO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				queryDTO.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		Map<String,Object> pm  = queryDTO.getParamters();
		if(null != queryDTO.getSaleOperatorIds() && !"".equals(queryDTO.getSaleOperatorIds())){
			pm.put("operator_id", queryDTO.getSaleOperatorIds());
		}
		pm.put("set", queryDTO.getSet());
		pageBean.setParameter(pm);
		pageBean = financeBiz.getsubjectSummary2ListPage(pageBean,"");
		
		PageBean pageBean2 = new PageBean();
		if(queryDTO.isHasSum()){
			pageBean2 = financeBiz.getsubjectSummary2ListPage(pageBean,"sum");
		}

		SubjectSummaryResult result = new SubjectSummaryResult();
		result.setPageBean(pageBean);
		result.setPageBeanSum(pageBean2);
		return result;
	}

	@Override
	public SubjectSummaryResult subjectSummary3(SubjectSummaryDTO queryDTO) {
		PageBean pageBean = new PageBean();
		pageBean.setPage(queryDTO.getPage());
		pageBean.setPageSize(queryDTO.getPageSize());
			
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(queryDTO.getSaleOperatorIds()) && StringUtils.isNotBlank(queryDTO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = queryDTO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				queryDTO.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		Map<String,Object> pm  = queryDTO.getParamters();
		if(null != queryDTO.getSaleOperatorIds() && !"".equals(queryDTO.getSaleOperatorIds())){
			pm.put("operator_id", queryDTO.getSaleOperatorIds());
		}
		pm.put("set", queryDTO.getSet());
		pageBean.setParameter(pm);
		pageBean = financeBiz.getsubjectSummaryListPage(pageBean);
		List<Map<String,Object>> lists = (List<Map<String,Object>>)pageBean.getResult();
		for (Map<String, Object> map : lists) {
			Map<String,Object> dyxs=financeBiz.getsubjectSummaryQDYJ(pageBean,Integer.parseInt(map.get("supplier_id").toString()),Constants.SUMJECT_SUMMARY_DYXS);
			map.put("dy_total_cash", dyxs==null?0:dyxs.get("total_cash"));
			map.put("dy_total", dyxs==null?0:dyxs.get("total"));
			
			Map<String,Object> gsxs=financeBiz.getsubjectSummaryQDYJ(pageBean,Integer.parseInt(map.get("supplier_id").toString()),Constants.SUMJECT_SUMMARY_GSXS);
			map.put("gs_total_cash", gsxs==null?0:gsxs.get("total_cash"));
			map.put("gs_total", gsxs==null?0:gsxs.get("total"));
			
			Map<String,Object> qt=financeBiz.getsubjectSummaryQT(pageBean,Integer.parseInt(map.get("supplier_id").toString()),Constants.SUMJECT_SUMMARY_GSXS,Constants.SUMJECT_SUMMARY_DYXS,null);
			map.put("qt_total_cash", qt==null?0:qt.get("total_cash"));
			map.put("qt_total", qt==null?0:qt.get("total"));
			
		}

		List<DicInfo> dicInfoList = dicBiz.getListByTypeCode(BasicConstants.YJ_XMLX, queryDTO.getBizId());
		
		SubjectSummaryResult result = new SubjectSummaryResult();
		result.setPageBean(pageBean);
		result.setDicInfoList(dicInfoList);

		return result;
	}

	/**
	 * 获取查询服务
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年8月18日 上午9:34:25
	 * @param svc
	 * @return
	 */
	private CommonSaleBiz getCommonService(String svc) {
		if (StringUtils.isBlank(svc)) {
			svc = "commonSaleBiz";
		}
		return appContext.getBean(svc, CommonSaleBiz.class);
	}

	@Override
	public List<BookingSupplier> getBookingSupplierIdList() {
		
		List<BookingSupplier> results = bookingSupplierBiz.selectIdList();
		return results;
	}

	@Override
	public List<GroupOrder> getGroupOrderIdList(Integer supplierId) {
		List<GroupOrder> results = groupOrderBiz.selectIdList(supplierId);
		return results;
	}

	@Override
	public List<TourGroup> selectGroupByDateZone(String startTime, String endTime, Integer bizId) {
		List<TourGroup> results = tourGroupBiz.selectGroupByDateZone(startTime, endTime, bizId);
		return results;
	}

	@Override
	public TourGroupDetiailsResult getTourGroupDetails(Integer groupId) {
		
		TourGroupDetiailsResult result = new TourGroupDetiailsResult();
		
		// 旅行团信息
		TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(groupId);
		result.setTourGroup(tourGroup);
		
		// 销售订单
		List<GroupOrder> orderList = groupOrderBiz.selectOrderByGroupId(groupId);
		result.setOrderList(orderList);
		
		// 购物店收入
		List<BookingShop> shoppingList = bookingShopBiz.getShopListByGroupId(groupId);
		result.setShoppingList(shoppingList);
		
		// 其他收入
		List<BookingSupplier> otherList = bookingSupplierBiz.getBookingSupplierByGroupIdAndSupplierType(groupId,
						com.yimayhd.erpresource.dal.constants.Constants.OTHERINCOME);
		result.setOtherList(otherList);
		
		// 地接社支出
		List<BookingDelivery> deliveryList = bookingDeliveryBiz.getDeliveryListByGroupId(groupId);
		result.setDeliveryList(deliveryList);
		
		// 供应商支出
		List<BookingSupplier> paymentList = bookingSupplierBiz.selectByPrimaryGroupId(groupId);
		result.setPaymentList(paymentList);
		return result;
	}

	@Override
	public List<GroupOrderPrice> getOrderPriceByOrder(Integer id) {
		List<GroupOrderPrice> priceList = groupOrderPriceBiz.selectByOrder(id);
		return priceList;
	}

	@Override
	public List<BookingSupplierDetail> getBookingSupplierDetailById(Integer bookingSupplierId) {
		
		List<BookingSupplierDetail> detailList = bookingSupplierDetailBiz.selectByPrimaryBookId(bookingSupplierId);
		return detailList;
	}

	@Override
	public SupplierInfo getSupplierById(Integer supplierId) {
		SupplierInfo info = supplierBiz.selectBySupplierId(supplierId);
		return info;
	}
}
