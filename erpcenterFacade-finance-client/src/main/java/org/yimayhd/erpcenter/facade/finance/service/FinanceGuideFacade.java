package org.yimayhd.erpcenter.facade.finance.service;

import java.util.List;
import java.util.Map;

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

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;



/**
 * 财务管理
 * 
 * @author hongfei.guo
 * @create 2016年10月25日 上午10:55:42
 */
public interface FinanceGuideFacade{

	PageBean auditList(FinanceGuideAuditListDTO dto);
	
	/**
	 * 跳转到导游报账记录明细页面
	 */
	List<Map<String, Object>> payRecordDetails(Integer payId, Integer type, boolean isDeduction);
	
	/**
	 * 读取导游报账记录列表
	 */
	PageBean payRecordListPage(PayRecordDetailsDTO dto);
	
	/**
	 * 跳转结算页面
	 */
	SettleListResult settleList(Integer bizId);
	
	/**
	 * 审核导游报账单
	 */
	ResultSupport auditGuideBill(Integer billId);
	
	/**
	 * 取消审核导游报账单
	 */
	ResultSupport delAuditGuideBill(Integer billId);
	
	/**
	 * 驳回导游报账单
	 */
	ResultSupport rejectGuideBill(Integer billId);
	
	/**
	 * 报账单付款
	 */
	ResultSupport payGuideBill(FinancePay pay);
	
	/**
	 * 报账单付款-改
	 */
	ResultSupport payGuideBillPlus(PayGuideBillPlusDTO dto); 
	
	/**
	 * 删除导游报账付款
	 */
	ResultSupport deletePay(Integer payId);
	
	/**
	 * 删除佣金发放付款记录
	 */
	ResultSupport deleteCommPay(Integer payId);
	
	/**
	 * 删除佣金扣除付款记录
	 */
	ResultSupport deletePayCommDeduction(Integer payId);
	
	/**
	 * 提交到财务
	 */
	ResultSupport submit2fin(Integer billId);
	
	/**
	 * 获取导游列表
	 */
	List<Map<String,String>> getGuideNameList(Integer bizId, String name);
	
	/**
	 * 跳转到发放佣金录入页面
	 */
	ToAddCommissionResult addCommission(Integer bizId, Integer groupId, String commType);
	
	ToAddCommission2Result addCommission2(AddCommission2DTO dto);
	
	/**
	 * 跳转到导游佣金列表页面
	 */
	CommissionListResult commissionList(Integer bizId, Integer groupId);
	
	/**
	 * 添加导游发放佣金
	 */
	ResultSupport saveCommission(SaveCommissionDTO dto);
	
	/**
	 * 添加导游扣除佣金
	 */
	ResultSupport saveCommissionDeduction(SaveCommissionDTO dto);
	
	/**
	 * 删除导游佣金
	 */
	ResultSupport deleteCommission(Integer id);
	
	QuerySettleListPageResult querySettleListPage(QuerySettleListPageDTO dto);
	
	/**
	 * 购物佣金发放付款
	 */
	ResultSupport payCommBill(FinancePay pay);
	
	/**
	 * 购物佣金扣除付款
	 */
	ResultSupport payCommDeductionBill(FinancePay pay);
	
}
