package com.yimayhd.erpcenter.facade.finance.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.yimayhd.erpcenter.facade.finance.errorcode.FinanceErrorCode;
import org.yimayhd.erpcenter.facade.finance.query.AddCommission2DTO;
import org.yimayhd.erpcenter.facade.finance.query.FinanceGuideAuditListDTO;
import org.yimayhd.erpcenter.facade.finance.query.PayGuideBillPlusDTO;
import org.yimayhd.erpcenter.facade.finance.query.PayRecordDetailsDTO;
import org.yimayhd.erpcenter.facade.finance.query.QuerySettleListPageDTO;
import org.yimayhd.erpcenter.facade.finance.query.SaveCommissionDTO;
import org.yimayhd.erpcenter.facade.finance.result.CommissionListResult;
import org.yimayhd.erpcenter.facade.finance.result.QuerySettleListPageResult;
import org.yimayhd.erpcenter.facade.finance.result.ResultSupport;
import org.yimayhd.erpcenter.facade.finance.result.SettleListResult;
import org.yimayhd.erpcenter.facade.finance.result.ToAddCommission2Result;
import org.yimayhd.erpcenter.facade.finance.result.ToAddCommissionResult;
import org.yimayhd.erpcenter.facade.finance.service.FinanceGuideFacade;

import com.alibaba.fastjson.JSON;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.CommonSaleBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.SysBizBankAccountBiz;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceCommission;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;
import com.yimayhd.erpresource.biz.service.SupplierGuideBiz;
import com.yimayhd.erpresource.dal.constants.BasicConstants;
import com.yimayhd.erpresource.dal.po.SupplierGuide;

public class FinanceGuideFacadeImpl implements FinanceGuideFacade{

	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;
	
	@Autowired
	private CommonSaleBiz commonSaleBiz;
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private FinanceGuideBiz financeGuideBiz;
	
	@Autowired
	private BookingSupplierDetailBiz bookingSupplierDetailBiz;
	
	@Autowired
	private DicBiz dicBiz;
	
	@Autowired
	private SysBizBankAccountBiz sysBizBankAccountBiz;
	
	@Autowired
	private BookingGuideBiz bookingGuideBiz;
	
	@Autowired
	private TourGroupBiz tourGroupBiz;
	
	@Autowired
	private SupplierGuideBiz supplierGuideBiz;
	
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
	public PageBean auditList(FinanceGuideAuditListDTO dto) {
		
		PageBean pb = new PageBean();
		pb.setPage(dto.getPage());
		pb.setPageSize(dto.getPageSize());
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(dto.getSaleOperatorIds()) && StringUtils.isNotBlank(dto.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = dto.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(dto.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				dto.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		Map<String,Object> pms  = dto.getParamters();
		if(null != dto.getSaleOperatorIds() && !"".equals(dto.getSaleOperatorIds())){
			pms.put("operator_id", dto.getSaleOperatorIds());
		}
		pms.put("set", dto.getSet());
		pb.setParameter(pms);
		pb = getCommonService(dto.getSvc()).queryListPage(dto.getSl(), pb);
		List<Map<String, Object>> results = pb.getResult();
		if(results != null){
			for(Map<String, Object> item : results){
				Integer groupId = Integer.parseInt(item.get("group_id").toString());
				Integer guideId = Integer.parseInt(item.get("guide_id").toString());
				Integer commTotal = financeGuideBiz.getCommisionTotalSum(groupId, guideId);
				item.put("comm_total", commTotal != null ? commTotal : 0);
			}
		}

		return pb;
	}

	@Override
	public List<Map<String, Object>> payRecordDetails(Integer payId, Integer type, boolean isDeduction) {
		
		Map<String, Object> payMap = null;
		
		 //佣金报账
		if(type == 1){
			if(isDeduction){
				payMap = financeGuideBiz.selectCommPayDeductionInfo(payId);
			}else{
				payMap = financeGuideBiz.selectCommPayInfo(payId);
			}
		}
		//导游报账
		else if(type == 2){ 
			payMap = financeGuideBiz.selectGuidePayInfo(payId);
		}
		
		List<Map<String, Object>> list =null;
		if(payMap != null){
			
			if(type == 1){
				if(isDeduction){
					list = financeGuideBiz.selectCommPayDetailsDeductionByPayId(payId);
				}else{
					list = financeGuideBiz.selectCommPayDetailsByPayId(payId);
				}
			}else if(type == 2){
				list = financeGuideBiz.selectGuidePayDetailsByPayId(payId);
				if(list != null && list.size() > 0){
					Map<String, Object> item = null;
					for(int i = 0; i < list.size(); i++){
						item = list.get(i);
						Integer bookingId = Integer.parseInt(item.get("supplier_id").toString());
						Integer supType = Integer.parseInt(item.get("supplier_type").toString());
						String remark = item.get("remark") != null ? item.get("remark").toString() : "";
						List<BookingSupplierDetail> detailList = bookingSupplierDetailBiz.selectByPrimaryBookId(bookingId);
						String details = bookingSupplierDetailBiz.concatDetail(supType, remark, detailList);
						item.put("details", details);
					}
				}
			}
		}
		return list;
	}

	@Override
	public PageBean payRecordListPage(PayRecordDetailsDTO dto) {
		
		PageBean pb = new PageBean();
		pb.setPage(dto.getPage());
		pb.setPageSize(dto.getPageSize());
		String saleOperatorIds = dto.getSaleOperatorIds();
		String orgIds = dto.getOrgIds();
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isEmpty(saleOperatorIds) && !StringUtils.isEmpty(orgIds)){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = orgIds.split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(dto.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				saleOperatorIds = salesOperatorIds.substring(0, salesOperatorIds.length()-1);
			}
		}
		Map pm = dto.getParamters();
		pm.put("saleOperatorIds", saleOperatorIds);
		pm.put("set", dto.getSet());
		pb.setParameter(pm);
		pb = financeGuideBiz.selectGuideCashRecordListPage(pb);
		return pb;
	}

	@Override
	public SettleListResult settleList(Integer bizId) {
		
		List<DicInfo> payTypeList = dicBiz.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);
		List<SysBizBankAccount> bizAccountList = sysBizBankAccountBiz.getListByBizId(bizId);
		List<DicInfo> bankList = dicBiz.getListByTypeCode(BasicConstants.SUPPLIER_BANK);
		SettleListResult result = new SettleListResult();
		result.setPayTypeList(payTypeList);
		result.setBizAccountList(bizAccountList);
		result.setBankList(bankList);
		return result;
	}

	@Override
	public ResultSupport auditGuideBill(Integer billId) {
		
		ResultSupport result = new ResultSupport();
		try{
			financeGuideBiz.auditGuideBill(billId);
		}catch(ClientException ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			result.setResultMsg(ex.getMessage());
			ex.printStackTrace();
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport delAuditGuideBill(Integer billId) {
		
		ResultSupport result = new ResultSupport();
		try{
			financeGuideBiz.delAuditGuideBill(billId);
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport rejectGuideBill(Integer billId) {
		
		ResultSupport result = new ResultSupport();
		try{
			financeGuideBiz.rejectGuideBill(billId);
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport payGuideBill(FinancePay pay) {
		ResultSupport result = new ResultSupport();
		try{
			financeGuideBiz.payGuideBill(pay);
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport payGuideBillPlus(PayGuideBillPlusDTO dto) {
		
		ResultSupport result = new ResultSupport();
		try{
			FinancePay pay = dto.getPay();
			financeGuideBiz.payGuideBill(pay, dto.getCheckedArr(), pay.getUserName());
			
			//佣金发放付款
			pay.setBookingGuideId(null);
			financeGuideBiz.payCommBill(pay);
			
			//佣金扣除付款
			pay.setCommissionIds(pay.getCommissionDeductionIds());
			financeGuideBiz.payCommDeductionBill(pay);
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport deletePay(Integer payId) {
		ResultSupport result = new ResultSupport();
		try{
			financeGuideBiz.deletePayById(payId);
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport deleteCommPay(Integer payId) {
		ResultSupport result = new ResultSupport();
		try{
			financeGuideBiz.deletePayCommById(payId);
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport deletePayCommDeduction(Integer payId) {
		ResultSupport result = new ResultSupport();
		try{
			financeGuideBiz.deletePayCommDeductionById(payId);
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport submit2fin(Integer billId) {
		ResultSupport result = new ResultSupport();
		try{
			financeGuideBiz.submit(billId, 2);
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Map<String, String>> getGuideNameList(Integer bizId, String name) {
		
		List<Map<String,String>> list = new ArrayList<Map<String, String>>();
		try{
			list = financeGuideBiz.getGuideNameList(bizId,java.net.URLDecoder.decode(name,"UTF-8"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}

	@Override
	public ToAddCommissionResult addCommission(Integer bizId, Integer groupId, String commType) {
		List<FinanceCommission> comList = financeGuideBiz.selectCommissionByGroupId(groupId);
		List<BookingGuide> guideList = bookingGuideBiz.selectDistinctListByGroupId(groupId);
		List<DicInfo> dicInfoList = dicBiz.getListByTypeCode(commType, bizId);
		
		ToAddCommissionResult result = new ToAddCommissionResult();
		result.setComList(comList);
		result.setGuideList(guideList);
		result.setDicInfoList(dicInfoList);
		return result;
	}
	
	@Override
	public ToAddCommissionResult addCommissionDeduction(Integer bizId, Integer groupId, String commType) {
		List<FinanceCommission> comList = financeGuideBiz.selectCommissionDeductionByGroupId(groupId);
		List<BookingGuide> guideList = bookingGuideBiz.selectDistinctListByGroupId(groupId);
		List<DicInfo> dicInfoList = dicBiz.getListByTypeCode(commType, bizId);
		
		ToAddCommissionResult result = new ToAddCommissionResult();
		result.setComList(comList);
		result.setGuideList(guideList);
		result.setDicInfoList(dicInfoList);
		return result;
	}

	@Override
	public ToAddCommission2Result addCommission2(AddCommission2DTO dto) {
		
		Integer groupId = dto.getGroupId();
		Integer guideId = dto.getGuideId();
		Integer bizId = dto.getBizId();
		
		List<FinanceCommission> comList = financeGuideBiz.selectCommissionByGroupId(groupId);
		List<BookingGuide> guideList = bookingGuideBiz.selectDistinctListByGroupId(groupId);
		List<DicInfo> dicInfoList = dicBiz.getListByTypeCode(BasicConstants.YJ_XMLX, dto.getBizId());
		List<DicInfo> dicInfoList2 = dicBiz.getListByTypeCode(BasicConstants.YJ_KKXM, dto.getBizId());
		
		TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(groupId);
		if(guideId==null){
			if(guideList.size()>0){
				guideId=guideList.get(0).getGuideId();
			}
		}
		//根据团id和导游Id查询佣金
		List<FinanceCommission> financeCommissions = financeGuideBiz.getFinanceCommisionByGroupIdAndGuideId(bizId, groupId, guideId);
		List<FinanceCommission> financeCommissionDeductions = financeGuideBiz.getFinanceCommisionDeductionByGroupIdAndGuideId(bizId, groupId, guideId);
		//导游信息
		SupplierGuide supplierGuide = supplierGuideBiz.getGuideInfoById(guideId);
		
		ToAddCommission2Result result = new ToAddCommission2Result();
		result.setComList(comList);
		result.setDicInfoList(dicInfoList);
		result.setDicInfoList2(dicInfoList2);
		result.setFinanceCommissionDeductions(financeCommissionDeductions);
		result.setFinanceCommissions(financeCommissions);
		result.setGuideList(guideList);
		result.setSupplierGuide(supplierGuide);
		result.setTourGroup(tourGroup);
		
		return result;
		
	}

	@Override
	public CommissionListResult commissionList(Integer bizId, Integer groupId) {
		List<FinanceCommission> comList = financeGuideBiz.selectCommissionByGroupId(groupId);
		List<BookingGuide> guideList = bookingGuideBiz.selectDistinctListByGroupId(groupId);
		List<DicInfo> dicInfoList = dicBiz.getListByTypeCode(BasicConstants.YJ_XMLX, bizId);
		CommissionListResult result = new CommissionListResult();
		result.setComList(comList);
		result.setGuideList(guideList);
		result.setDicInfoList(dicInfoList);
		return result;
	}

	@Override
	public ResultSupport saveCommission(SaveCommissionDTO dto) {
		
		ResultSupport result = new ResultSupport();
		try{
			String content = dto.getContent();
			if(StringUtils.isEmpty(content) || "[]".equals(content)){
				financeGuideBiz.deleteCommissionByGroupId(dto.getBizId(), dto.getGroupId());
			}
			List<FinanceCommission> list = JSON.parseArray(content, FinanceCommission.class);
			financeGuideBiz.batchInsertCommission(dto.getBizId(), dto.getEmployeeId(), dto.getEmployeeName(), list);
		}catch(Exception e){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			e.printStackTrace();
		}	
		return result;
	}

	@Override
	public ResultSupport saveCommissionDeduction(SaveCommissionDTO dto) {
		ResultSupport result = new ResultSupport();
		try{
			String content = dto.getContent();
			if(StringUtils.isEmpty(content) || "[]".equals(content)){
				financeGuideBiz.deleteCommissionDeductionByGroupId(dto.getBizId(), dto.getGroupId());
			}
			List<FinanceCommission> list = JSON.parseArray(content, FinanceCommission.class);
			financeGuideBiz.batchInsertCommissionDeduction(dto.getBizId(), dto.getEmployeeId(), dto.getEmployeeName(), list);
		}catch(Exception e){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			e.printStackTrace();
		}	
		return result;
	}

	@Override
	public ResultSupport deleteCommission(Integer id) {
		
		ResultSupport result = new ResultSupport();
		if(id == null){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			result.setResultMsg("要删除的佣金ID不能为空");
		}
		
		try{
			financeGuideBiz.deleteByPrimaryKey(id);
		}catch(Exception e){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public QuerySettleListPageResult querySettleListPage(QuerySettleListPageDTO dto) {
		
		PageBean pageBean = new PageBean();
		pageBean.setPage(dto.getPage());
		pageBean.setPageSize(dto.getPageSize());
		
		//如果人员为空并且部门不为空，则取部门下的人id
		if(StringUtils.isBlank(dto.getSaleOperatorIds()) && StringUtils.isNotBlank(dto.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = dto.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(dto.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				dto.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		Map<String,Object> pm  = dto.getParamters();
		if(null!=dto.getSaleOperatorIds() && !"".equals(dto.getSaleOperatorIds())){
			pm.put("operator_id", dto.getSaleOperatorIds());
		}
		
		pm.put("set", dto.getSet());
		pageBean.setParameter(pm);
		
		pageBean = financeGuideBiz.querySettleListPage(pageBean, dto.getBizId());
		List<Map<String, Object>> results = pageBean.getResult();
		if(results != null){
			for(Map<String, Object> item : results){
				
				Integer groupId = Integer.parseInt(item.get("group_id").toString());
				Integer guideId = Integer.parseInt(item.get("guide_id").toString());
				Map<String, Object> commMap = financeGuideBiz.getCommisionTotalSumAndTotalCashSum(groupId, guideId);
				if(commMap != null){
					BigDecimal total = new BigDecimal(commMap.get("total").toString());
					BigDecimal totalCash = new BigDecimal(commMap.get("total_cash").toString());
					item.put("comm_total", total);
					item.put("comm_total_cash", totalCash);
				}else{
					item.put("comm_total", 0);
					item.put("comm_total_cash", 0);
				}
				
				Map<String, Object> commDeductionMap = financeGuideBiz.getCommisionDeductionTotalSumAndTotalCashSum(groupId, guideId);
				if(commDeductionMap != null){
					BigDecimal total = new BigDecimal(commDeductionMap.get("total").toString());
					BigDecimal totalCash = new BigDecimal(commDeductionMap.get("total_cash").toString());
					item.put("comm_deduction_total", total);
					item.put("comm_deduction_total_cash", totalCash);
				}else{
					item.put("comm_deduction_total", 0);
					item.put("comm_deduction_total_cash", 0);
				}
				
			}
		}
		
		BigDecimal sumTotalAmount = financeGuideBiz.getSumTotal(pageBean, dto.getBizId());
		BigDecimal sumTotalCash = financeGuideBiz.getSumTotalCash(pageBean, dto.getBizId());
		
		QuerySettleListPageResult result = new QuerySettleListPageResult();
		result.setSumTotalAmount(sumTotalAmount);
		result.setSumTotalCash(sumTotalCash);
		
		result.setPageBean(pageBean);
		
		Map<String, Object>	allCommTotalMap = financeGuideBiz.getAllTotalSumAndTotalCashSum(pageBean);
		if(allCommTotalMap != null){
			BigDecimal total = new BigDecimal(allCommTotalMap.get("total").toString());
			BigDecimal totalCash = new BigDecimal(allCommTotalMap.get("total_cash").toString());
			
			result.setSumCommTotal(total);
			result.setSumCommTotalCash(totalCash);
		}
		return result;
	}

	@Override
	public ResultSupport payCommBill(FinancePay pay) {
		
		ResultSupport result = new ResultSupport();
		try{
			financeGuideBiz.payCommBill(pay);
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport payCommDeductionBill(FinancePay pay) {
		ResultSupport result = new ResultSupport();
		try{
			financeGuideBiz.payCommDeductionBill(pay);
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}
	
}
