package com.yimayhd.erpcenter.facade.sales.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;

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

/**
 * 财务管理
 * 
 * @author hongfei.guo
 * @create 2016年10月25日 上午10:55:42
 */
public interface FinanceFacade{

	/**
	 * 此方法用来维护团金额的一致性
	 * @param groupId
	 * @return
	 */
	String calcTourGroupAmount(Integer groupId);
	
	/**
	 * 此方法用来批量维护团金额的一致性
	 * @param bizId
	 * @return
	 */
	String batchCalcTourGroupAmount(Integer bizId);
	
	/**
	 * 此方法用来批量维护 单条 booking_supplier total_cash的数据
	 * @param bookingSupplierId
	 * @return
	 * @throws IOException 
	 */
	String calcBookingSupplierTotalCash(Integer bookingSupplierId) throws IOException;
		
	/**
	 * 此方法用来批量维护booking_supplier total_cash的数据
	 * @return
	 * @throws IOException 
	 */
	void batchCalcBookingSupplierTotalCash() throws IOException;
	
	/**
	 * 此方法用来批量维护 单条 group_order total_cash的数据
	 * @param groupOrderId
	 * @return
	 * @throws IOException 
	 */
	String calcGroupOrderTotalCash(Integer groupOrderId) throws IOException;
	
	/**
	 * 此方法用来批量维护group_order total_cash的数据
	 * @param supplierId
	 * @return
	 * @throws IOException 
	 */
	void batchCalcGroupOrderTotalCash(Integer supplierId) throws IOException;
	
	String calcGroupTotalCash(Integer groupId);
	
	void calcGroupTotalCashBath(String startTime, String endTime,Integer bizId) throws IOException;

	/**
	 * 获取审核人列表
	 * @return
	 */
	List<TourGroup> getAuditorList();
	
	/**
	 * 结算单审核预览
	 * @param statementCheckPreviewDTO
	 * @return
	 */
	StatementCheckPreviewResult statementCheckPreview(StatementCheckPreviewDTO statementCheckPreviewDTO);
	
	SettleSealListResult settleSealList(SettleSealListDTO settleSealListDTO);

	/**
	 * 跳转到收款记录详情页面
	 */
	IncomeOrPaytResult incomeView(IncomeOrPayDTO incomeOrPayDTO);

	/**
	 * 跳转到付款记录详情页面
	 */
	IncomeOrPaytResult payView(IncomeOrPayDTO incomeOrPayDTO);
	
	/**
	 * 跳转到收款订单关联页面列表
	 * @param queryDTO
	 * @return PageBean
	 */
	PageBean incomeJoinTableList(IncomeJoinTableListDTO queryDTO);

	/**
	 * 跳转到收款新增页面
	 */
	IncomeOrPaytResult incomeAdd(IncomeOrPayDTO incomeOrPayDTO);

	/**
	 * 跳转到付款新增页面
	 */
	IncomeOrPaytResult payAdd(IncomeOrPayDTO incomeOrPayDTO);
	
	/**
	 * 跳转收款记录页面
	 */
	IncomeOrPaytResult incomeRecordList(IncomeOrPayDTO incomeOrPayDTO);

	/**
	 * 跳转付款记录页面
	 */
	IncomeOrPaytResult cashRecordList(IncomeOrPayDTO incomeOrPayDTO);

	/**
	 * 跳转到收款审核
	 */
	TourGroup auditList(Integer groupId);

	/**
	 * 团收入支出汇总
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年7月30日 下午6:13:51
	 * @param groupId
	 * @return
	 */
	Map queryAuditViewInfo(Integer bizId, Integer groupId);

	PageBean aduditStatisticsList(AduditStatisticsListDTO queryDTO);

	/**
	 * 获取审核人列表
	 * @param name
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	String getAuditUserList(String name, Integer bizId) throws UnsupportedEncodingException;

	/**
	 * 跳转到操作记录页面
	 */
	List<TourGroup> operateLog(Integer groupId);

	/**
	 * 批量审核
	 */
	void audit(AuditDTO auditDTO);
	
	/**
	 * 批量审核
	 */
	String auditList(AuditListDTO auditListDTO);

	/**
	 * 结算单审核
	 */
	String finAudit(FinAuditDTO finAuditDTO);

	/**
	 * 结算单取消审核
	 */
	String finUnAudit(FinAuditDTO finAuditDTO);

	/**
	 * 批量封存
	 */
	String batchSeal(UnsealDTO unsealDTO);

	/**
	 * 解封
	 */
	String unseal(UnsealDTO unsealDTO);

	/**
	 * 付款/收款
	 */
	String pay(PayDTO payDTO);

	/**
	 * 请求供应商账户列表
	 * @param sid
	 * @return
	 */
	String querySupplierBankAccountList(Integer sid);

	/**
	 * 领单-查询
	 */
	ReceiveOrderListSelectResult receiveOrderListSelect(ReceiveOrderListSelectDTO queryDTO);

	/**
	 * 获取申请人列表
	 * @param bizId
	 * @param name
	 */
	List<Map<String, String>> fuzzyApplicantList(Integer bizId, String name);

	/**
	 * 派单
	 */
	DiatributeBillResult diatributeBill(DiatributeBillDTO diatributeBillDTO);

	/**
	 * 销单
	 */
	VerifyBillResult verifyBill(VerifyBillDTO verifyBillDTO);
	/**
	 * 查单
	 */
	CheckBillResult checkBill(CheckBillDTO checkBillDTO);
	
	/**
	 * 查单-打印
	 */
	CheckBillResult checkBillPrint(CheckBillDTO checkBillDTO);

	/**
	 * 派单保存
	 * @return
	 */
	ResultSupport saveDistributeBill(SaveDistributeBillDTO saveDistributeBillDTO);

	/**
	 * 销单保存
	 * @return
	 */
	ResultSupport saveVerifyBill(SaveVerifyBillDTO saveVerifyBillDTO);
	
	/**
	 * 取消销单
	 * @return
	 */
	ResultSupport delVerify(String orderId, String type);
	
	/**
	 * 取消领单
	 * @return
	 */
	ResultSupport delReceived(String groupId, String guideId);

	/**
	 * 获取操作员列表
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	List<Map<String, String>> getUserNameList(Integer bizId, String name) throws UnsupportedEncodingException;

	/**
	 * 结算单审核分页查询
	 */
	SettleListPageResult settleListPage(SettleListPageDTO settleListPageDTO);

	/**
	 * 结算单审核列表查询
	 */
	QuerySettleListResult querySettleList(QuerySettleListDTO querySettleListDTO);

	/**
	 * 删除财务-收款付款
	 */
	ResultSupport deleteFinancePay(Integer id);

	/**
	 * 删除财务-收款付款详情
	 */
	ResultSupport deleteFinancePayDetail(Integer supplierType, Integer locOrderId, Integer payId);
	
	/**
	 * 批量删除财务-收款付款详情
	 */
	ResultSupport batchDeleteFinancePayDetail(Integer supplierType, String locOrderIds, Integer payId);

	/**
	 * 购物审核列表
	 */
	ToBookingShopVerifyListlResult toBookingShopVerifyList(ToBookingShopVerifyListDTO queryDTO);

	/**
	 * 批量审核购物店
	 */
	ResultSupport auditShop(AuditShopDTO auditShopDTO);

	/**
	 * 购物佣金发放统计
	 */
	ResultSupport shopCommissionStatsList(Integer bizId);
	
	/**
	 * 购物佣金扣除统计
	 */
	String shopCommissionDeductionStatsList(Integer bizId);

	ViewShopCommissionStatsListResult viewShopCommissionStatsList(Integer bizId);
	
	ViewShopCommissionStatsListResult viewShopCommissionDeductionStatsList(Integer bizId);

	/**
	 * 购物佣金发放统计列表
	 */
	QueryShopCommissionStatsResult toShopStatisticsList(QueryShopCommissionStatsDTO queryDTO);
	
	
	QueryShopCommissionStatsResult toShopStatisticsList2(QueryShopCommissionStatsDTO queryDTO);
	
	/**
	 * 购物佣金扣除统计列表
	 */
	QueryShopCommissionStatsResult queryShopCommissionDeductionStats(QueryShopCommissionStatsDTO queryDTO);

	QueryShopCommissionStatsResult queryShopCommissionDeductionStats2(QueryShopCommissionStatsDTO queryDTO);

	/**
	 * 批量审核购物发放佣金
	 */
	ResultSupport auditComm(AuditCommDTO auditCommDTO);
	
	/**
	 * 批量审核购物扣除佣金
	 */
	ResultSupport auditCommDeduction(AuditCommDTO auditCommDTO);

	/**
	 * 跳转到购物佣金发放结算页面
	 */
	SettleCommissionListResult settleCommissionList(Integer bizId, Integer groupId);
	
	/**
	 * 跳转到购物佣金扣除结算页面
	 */
	SettleCommissionListResult settleCommissionDeductionList(Integer bizId, Integer groupId);

	/**
	 * 购物佣金发放统计列表
	 */
	QuerySettleCommissionResult querySettleCommission(QuerySettleCommissionDTO queryDTO);
	
	/**
	 * 购物佣金扣除统计列表
	 */
	QuerySettleCommissionResult querySettleCommissionDeduction(QuerySettleCommissionDTO queryDTO);
	
	/**
	 * 科目汇总表1
	 */
	SubjectSummaryResult subjectSummary(SubjectSummaryDTO queryDTO);
	
	
	/**
	 * 科目汇总表2
	 */
	SubjectSummaryResult subjectSummary2(SubjectSummaryDTO queryDTO);
	
	/**
	 * 科目汇总表3
	 */
	SubjectSummaryResult subjectSummary3(SubjectSummaryDTO queryDTO);
}
