package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.CommonBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.common.util.NumberUtil;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.InfoBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.sales.errorcode.SaleErrorCode;
import com.yimayhd.erpcenter.facade.sales.query.finance.AduditStatisticsListDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.AuditCommDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.AuditDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.AuditListDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.AuditShopDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.CheckBillDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.DiatributeBillDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.FinAuditDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.IncomeJoinTableListDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.IncomeOrPayDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.PayDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.QuerySettleCommissionDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.QuerySettleListDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.QueryShopCommissionStatsDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.ReceiveOrderListSelectDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.SaveDistributeBillDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.SaveVerifyBillDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.SettleListPageDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.SettleSealListDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.StatementCheckPreviewDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.SubjectSummaryDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.ToBookingShopVerifyListDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.UnsealDTO;
import com.yimayhd.erpcenter.facade.sales.query.finance.VerifyBillDTO;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;
import com.yimayhd.erpcenter.facade.sales.result.finance.CheckBillResult;
import com.yimayhd.erpcenter.facade.sales.result.finance.DiatributeBillResult;
import com.yimayhd.erpcenter.facade.sales.result.finance.IncomeOrPaytResult;
import com.yimayhd.erpcenter.facade.sales.result.finance.QuerySettleCommissionResult;
import com.yimayhd.erpcenter.facade.sales.result.finance.QuerySettleListResult;
import com.yimayhd.erpcenter.facade.sales.result.finance.QueryShopCommissionStatsResult;
import com.yimayhd.erpcenter.facade.sales.result.finance.ReceiveOrderListSelectResult;
import com.yimayhd.erpcenter.facade.sales.result.finance.SettleCommissionListResult;
import com.yimayhd.erpcenter.facade.sales.result.finance.SettleListPageResult;
import com.yimayhd.erpcenter.facade.sales.result.finance.SettleSealListResult;
import com.yimayhd.erpcenter.facade.sales.result.finance.StatementCheckPreviewResult;
import com.yimayhd.erpcenter.facade.sales.result.finance.SubjectSummaryResult;
import com.yimayhd.erpcenter.facade.sales.result.finance.ToBookingShopVerifyListlResult;
import com.yimayhd.erpcenter.facade.sales.result.finance.VerifyBillResult;
import com.yimayhd.erpcenter.facade.sales.result.finance.ViewShopCommissionStatsListResult;
import com.yimayhd.erpcenter.facade.sales.service.FinanceFacade;

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
		
		if(groupId != null){
			financeBiz.calcTourGroupAmount(groupId);
		}
		ResultSupport result = new ResultSupport();
		result.setResultMsg("calculate over!");
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
		
		ResultSupport result = new ResultSupport();
		result.setResultMsg("calculate over! fixed "+ results.size() +" tour groups");
		return result;
	}

	@Override
	public ResultSupport calcBookingSupplierTotalCash(Integer bookingSupplierId) throws IOException {
		
		ResultSupport result = new ResultSupport();
		if(bookingSupplierId == null){
			result.setSuccess(false);
			result.setErrorCode(SaleErrorCode.PARAM_ERROR);
			result.setResultMsg("请输入bookingSupplierId!");
			return result;
		}
		
		financeBiz.calcBookingSupplierTotalCash(bookingSupplierId);

		result.setResultMsg("calculate over!");
		return result;
	}

	@Override
	public ResultSupport calcGroupOrderTotalCash(Integer groupOrderId) throws IOException {
		
		ResultSupport result = new ResultSupport();
		if(groupOrderId == null){
			result.setSuccess(false);
			result.setResultMsg("请输入groupOrderId!");
			return result;
		}
		
		financeBiz.calcGroupOrderTotalCash(groupOrderId);
		
		result.setResultMsg("calculate over!");
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
	public SettleSealListResult settleSealList(
			SettleSealListDTO settleSealListDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IncomeOrPaytResult incomeView(IncomeOrPayDTO incomeOrPayDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IncomeOrPaytResult payView(IncomeOrPayDTO incomeOrPayDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean incomeJoinTableList(IncomeJoinTableListDTO queryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IncomeOrPaytResult incomeAdd(IncomeOrPayDTO incomeOrPayDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IncomeOrPaytResult payAdd(IncomeOrPayDTO incomeOrPayDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IncomeOrPaytResult incomeRecordList(IncomeOrPayDTO incomeOrPayDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IncomeOrPaytResult cashRecordList(IncomeOrPayDTO incomeOrPayDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TourGroup auditList(Integer groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map queryAuditViewInfo(Integer bizId, Integer groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean aduditStatisticsList(AduditStatisticsListDTO queryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuditUserList(String name, Integer bizId)
			throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TourGroup> operateLog(Integer groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void audit(AuditDTO auditDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String auditList(AuditListDTO auditListDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String finAudit(FinAuditDTO finAuditDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String finUnAudit(FinAuditDTO finAuditDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String batchSeal(UnsealDTO unsealDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String unseal(UnsealDTO unsealDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pay(PayDTO payDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String querySupplierBankAccountList(Integer sid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReceiveOrderListSelectResult receiveOrderListSelect(
			ReceiveOrderListSelectDTO queryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> fuzzyApplicantList(Integer bizId,
			String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiatributeBillResult diatributeBill(
			DiatributeBillDTO diatributeBillDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VerifyBillResult verifyBill(VerifyBillDTO verifyBillDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CheckBillResult checkBill(CheckBillDTO checkBillDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CheckBillResult checkBillPrint(CheckBillDTO checkBillDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSupport saveDistributeBill(
			SaveDistributeBillDTO saveDistributeBillDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSupport saveVerifyBill(SaveVerifyBillDTO saveVerifyBillDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSupport delVerify(String orderId, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSupport delReceived(String groupId, String guideId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> getUserNameList(Integer bizId, String name)
			throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SettleListPageResult settleListPage(
			SettleListPageDTO settleListPageDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuerySettleListResult querySettleList(
			QuerySettleListDTO querySettleListDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSupport deleteFinancePay(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSupport deleteFinancePayDetail(Integer supplierType,
			Integer locOrderId, Integer payId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSupport batchDeleteFinancePayDetail(Integer supplierType,
			String locOrderIds, Integer payId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ToBookingShopVerifyListlResult toBookingShopVerifyList(
			ToBookingShopVerifyListDTO queryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSupport auditShop(AuditShopDTO auditShopDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSupport shopCommissionStatsList(Integer bizId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String shopCommissionDeductionStatsList(Integer bizId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ViewShopCommissionStatsListResult viewShopCommissionStatsList(
			Integer bizId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ViewShopCommissionStatsListResult viewShopCommissionDeductionStatsList(
			Integer bizId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryShopCommissionStatsResult toShopStatisticsList(
			QueryShopCommissionStatsDTO queryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryShopCommissionStatsResult toShopStatisticsList2(
			QueryShopCommissionStatsDTO queryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryShopCommissionStatsResult queryShopCommissionDeductionStats(
			QueryShopCommissionStatsDTO queryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryShopCommissionStatsResult queryShopCommissionDeductionStats2(
			QueryShopCommissionStatsDTO queryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSupport auditComm(AuditCommDTO auditCommDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSupport auditCommDeduction(AuditCommDTO auditCommDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SettleCommissionListResult settleCommissionList(Integer bizId,
			Integer groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SettleCommissionListResult settleCommissionDeductionList(
			Integer bizId, Integer groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuerySettleCommissionResult querySettleCommission(
			QuerySettleCommissionDTO queryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuerySettleCommissionResult querySettleCommissionDeduction(
			QuerySettleCommissionDTO queryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubjectSummaryResult subjectSummary(SubjectSummaryDTO queryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubjectSummaryResult subjectSummary2(SubjectSummaryDTO queryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubjectSummaryResult subjectSummary3(SubjectSummaryDTO queryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取查询服务
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年8月18日 上午9:34:25
	 * @param svc
	 * @return
	 */
	private CommonBiz getCommonService(String svc) {
		if (StringUtils.isBlank(svc)) {
			svc = "commonsaleService";
		}
		return appContext.getBean(svc, CommonBiz.class);
	}
	
	
}
