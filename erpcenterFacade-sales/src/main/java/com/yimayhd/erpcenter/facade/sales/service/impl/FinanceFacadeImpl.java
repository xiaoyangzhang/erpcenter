package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
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

	@Override
	public String calcTourGroupAmount(Integer groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String batchCalcTourGroupAmount(Integer bizId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String calcBookingSupplierTotalCash(Integer bookingSupplierId)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchCalcBookingSupplierTotalCash() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String calcGroupOrderTotalCash(Integer groupOrderId)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchCalcGroupOrderTotalCash(Integer supplierId)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String calcGroupTotalCash(Integer groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void calcGroupTotalCashBath(String startTime, String endTime,
			Integer bizId) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TourGroup> getAuditorList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatementCheckPreviewResult statementCheckPreview(
			StatementCheckPreviewDTO statementCheckPreviewDTO) {
		// TODO Auto-generated method stub
		return null;
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
	
	
}
