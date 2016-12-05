package org.yimayhd.erpcenter.facade.finance.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

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
import org.yimayhd.erpcenter.facade.finance.query.PushWapListTableDTO;
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
import org.yimayhd.erpcenter.facade.finance.result.QueryPushWapListTableResult;
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

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpresource.dal.po.SupplierBankaccount;
import com.yimayhd.erpresource.dal.po.SupplierInfo;

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
	ResultSupport calcTourGroupAmount(Integer groupId);
	
	/**
	 * 此方法用来批量维护团金额的一致性
	 * @param bizId
	 * @return
	 */
	ResultSupport batchCalcTourGroupAmount(Integer bizId, Map paramters);
	
	/**
	 * 此方法用来批量维护 单条 booking_supplier total_cash的数据
	 * @param bookingSupplierId
	 * @return
	 * @throws IOException 
	 */
	ResultSupport calcBookingSupplierTotalCash(Integer bookingSupplierId) throws IOException;
	
	/**
	 * 此方法用来批量维护 单条 group_order total_cash的数据
	 * @param groupOrderId
	 * @return
	 * @throws IOException 
	 */
	ResultSupport calcGroupOrderTotalCash(Integer groupOrderId) throws IOException;
	
	
	ResultSupport calcGroupTotalCash(Integer groupId);
	
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
	StatementCheckPreviewResult statementCheckPreview(StatementCheckPreviewDTO queryDTO);
	
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
	IncomeOrPaytResult payRecordList(IncomeOrPayDTO incomeOrPayDTO);

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
	Map queryAuditViewInfo(Integer groupId, Integer bizId);

	PageBean aduditStatisticsList(AduditStatisticsListDTO queryDTO);

	/**
	 * 获取审核人列表
	 * @param name
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	List<Map<String, String>> getAuditUserList(String name, Integer bizId) throws UnsupportedEncodingException;

	/**
	 * 跳转到操作记录页面
	 */
	List<TourGroup> operateLog(Integer groupId);

	/**
	 * 批量审核
	 */
	ResultSupport audit(AuditDTO auditDTO);
	
	/**
	 * 批量审核
	 */
	ResultSupport auditList(AuditListDTO auditListDTO);

	/**
	 * 结算单审核
	 */
	ResultSupport finAudit(FinAuditDTO finAuditDTO);

	/**
	 * 结算单取消审核
	 */
	ResultSupport finUnAudit(FinAuditDTO finAuditDTO);

	/**
	 * 批量封存
	 */
	ResultSupport batchSeal(UnsealDTO unsealDTO);

	/**
	 * 解封
	 */
	ResultSupport unseal(UnsealDTO unsealDTO);

	/**
	 * 付款/收款
	 */
	String pay(PayDTO payDTO);

	/**
	 * 请求供应商账户列表
	 * @param sid
	 * @return
	 */
	List<SupplierBankaccount> querySupplierBankAccountList(Integer sid);

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
	List<Map<String, String>> getUserNameList(Integer bizId, String name);

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
	String shopCommissionStatsList(Integer bizId);
	
	/**
	 * 购物佣金扣除统计
	 */
	String shopCommissionDeductionStatsList(Integer bizId);

	ViewShopCommissionStatsListResult viewShopCommissionStatsList(Integer bizId);
	
	ViewShopCommissionStatsListResult viewShopCommissionDeductionStatsList(Integer bizId);

	/**
	 * 购物佣金发放统计列表
	 */
	QueryShopCommissionStatsResult queryShopCommissionStats(QueryShopCommissionStatsDTO queryDTO);
	
	
	QueryShopCommissionStatsResult queryShopCommissionStats2(QueryShopCommissionStatsDTO queryDTO);
	
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
	
	
	List<BookingSupplier> getBookingSupplierIdList();
	
	List<GroupOrder> getGroupOrderIdList(Integer supplierId);
	
	List<TourGroup> selectGroupByDateZone(String startTime, String endTime, Integer bizId);
	
	/**
	 * 查询旅行团信息、销售订单、购物店收入、其他收入、地接社支出、供应商支出
	 * @param groupId
	 * @return
	 */
	TourGroupDetiailsResult getTourGroupDetails(Integer groupId);
	
	
	List<GroupOrderPrice> getOrderPriceByOrder(Integer id); 
	
	
	List<BookingSupplierDetail> getBookingSupplierDetailById(Integer bookingSupplierId);
	
	SupplierInfo getSupplierById(Integer supplierId);
	
	/**
	 * 行程助手推送
	 */
	QueryPushWapListTableResult pushWapListTable(PushWapListTableDTO queryDTO);
}
