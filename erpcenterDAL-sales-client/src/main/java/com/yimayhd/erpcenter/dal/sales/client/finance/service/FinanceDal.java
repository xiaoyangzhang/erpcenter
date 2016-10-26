package com.yimayhd.erpcenter.dal.sales.client.finance.service;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePayDetail;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.InfoBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;


/**
 * 财务管理
 * 
 * @author Jing.Zhuo
 * @create 2015年7月28日 上午11:39:28
 */
public interface FinanceDal{
	
	/**
	 * 计算团的收入支出等费用项
	 * 注：请在业务代码执行之后调用
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年7月28日 下午7:39:41
	 * @param tourGroupId 团ID
	 */
	void calcTourGroupAmount(Integer tourGroupId);
	
	/**
	 * 计算供应商订单的已付金额
	 * @param tourGroupId 团ID
	 */
	void calcBookingSupplierTotalCash(Integer bookingSupplierId);
	
	/**
	 * 计算团订单的已付金额
	 * @param tourGroupId 团ID
	 */
	void calcGroupOrderTotalCash(Integer groupOrderId);
	
	/**
	 * 团收入支出汇总
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年7月30日 下午6:13:51
	 * @param groupId
	 * @return
	 */
	Map queryAuditViewInfo(Integer groupId, Integer bizId);
	
	/**
	 * 团购物、佣金汇总
	 * @param groupId
	 * @return
	 */
	public InfoBean statsShopWithCommInfoBean(Integer groupId);
	
	/**
	 * 收支审核
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年8月3日 下午5:36:27
	 * @param groupId 团号
	 * @param feeType 费用类型(标识其他收入或其他支出)
	 * @param checkedIds 需要审核的费用项标识
	 * @param unCheckedIds 取消审核
	 * @param userId 审核人ID
	 * @param userName 审核人
	 */
	void audit(Integer groupId, String feeType, String checkedIds,String unCheckedIds, 
			String comCheckedIds, String comUnCheckedIds, Integer userId, String userName);
	
	/**
	 * 佣金扣款审核
	 * @param groupId
	 * @param feeType
	 * @param comCheckedIds
	 * @param comUnCheckedIds
	 * @param userId
	 * @param userName
	 */
	void auditCommDeduction(Integer groupId, String feeType, String comCheckedIds, 
			String comUnCheckedIds, Integer userId, String userName);
	
	/**
	 * 收支审核
	 * @param data {groupId:1, list:[{feeType:order, checkedIds:123,123, unCheckedIds:123,123}]}
	 * @param userId 审核人ID
	 * @param userName 审核人
	 */
	void audit(String data, Integer userId, String userName);
	
	/**
	 * 结算单审核
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年8月3日 下午5:36:27
	 * @param groupId 团号
	 */
	void audit(Integer groupId, Integer userId, String userName);
	
	/**
	 * 结算单取消审核
	 * @param groupId 团号
	 */
	void unAudit(Integer groupId, String userName); 
	
	/**
	 * 批量封存
	 * @param groupIds
	 */
	void batchSeal(String groupIds, Integer userId, String userName);
	
	/**
	 * 解封
	 * @param groupId
	 */
	void unseal(String groupId, Integer userId, String userName);
	
	/**
	 * 付款
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年8月7日 下午5:07:09
	 * @param pay 付款信息
	 */
	FinancePay pay(FinancePay pay,List<FinancePayDetail> details, Integer supplierType);
	
	/**
	 * 查询付款信息
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年8月8日 上午9:42:41
	 * @param payId 付款记录ID
	 * @return
	 */
	FinancePay queryPayById(Integer payId);
	
	List<TourGroup> getOperateLogs(Integer groupId);
	
	/**
	 * 删除财务-收款付款详情
	 */
	void deletePayById(Integer payId); 
	
	/**
	 * 删除财务-收款付款详情
	 */
	void deletePayDetail(Integer supplierType, Integer locOrderId, Integer payId);
	
	void auditShop(String checkedIds, String unCheckedIds, Integer employeeId,
			String name);
	
	/**
	 * 审核发放佣金
	 * @param checkedIds
	 * @param unCheckedIds
	 * @param userId
	 * @param userName
	 */
	void auditComm(String checkedIds, String unCheckedIds, Integer userId, String userName);
	
	/**
	 * 审核扣除佣金
	 * @param checkedIds
	 * @param unCheckedIds
	 * @param userId
	 * @param userName
	 */
	void auditCommDeduction(String checkedIds, String unCheckedIds, Integer userId, String userName);

	PageBean selectIncomeJoinTableListPage(PageBean pageBean, Integer bizId);

	PageBean getsubjectSummaryListPage(PageBean pageBean);
	
	PageBean getsubjectSummary2ListPage(PageBean pageBean,String sum);

	Map<String,Object> getsubjectSummaryQDYJ(PageBean pageBean,Integer supplierId,String sType);

	Map<String, Object> getsubjectSummaryQT(PageBean pageBean,Integer supplierId,String sType,String sType2,String sType3);

	Map<String, Object> getsubjectSummaryQDYJTotal(PageBean pageBean,String sType);
	
	Map<String, Object> getsubjectSummaryQTTotal(PageBean pageBean,String sType,String sType2,String sType3);
	
	List<FinancePay> getFinancePayBySupplierId(Integer supplierId, Integer bizId) ;
	
	/**
	 * 判断团是否有已审核的订单
	 * @param groupId
	 * @return
	 */
	boolean hasAuditOrder(Integer groupId);
	/**
	 * 判断团是否有酒店订单
	 * @param groupId
	 * @return
	 */
	boolean hasHotelOrder(Integer groupId);
	
	/**
	 * 判断团是否有收付款记录
	 * @param groupId
	 * @return
	 */
	boolean hasPayOrIncomeRecord(Integer groupId);
	
	/**
	 * 根据订单ID，查询订单收付款信息
	 * @param groupId
	 * @return
	 */
	List<Map<String, Object>> selectDetailByLocOrderId(Integer locOrderId);

	/**
	 * 计算某团 收款，付款合计数不正确（group_order、booking_supplier表，booking_delivery表等）
	 * 
	 * @author Ou.zongying
	 * @create 2016年6月14日
	 * @param tourGroupId 团ID
	 */
	void calcTotalCash_collection(Integer groupId);
	java.math.BigDecimal calcTotalCashValue(Integer orderId, Integer supplierType);
	
	/**
	 * 收款 插入finance_pay_detail，finance_pay
	 * 
	 * @author zhoumi
	 * @create 2016年9月23日
	 * @param orderId 订单ID
	 */
	int makeCollections(FinancePay pay,Integer orderId);
	
	List<FinancePayDetail> selectFinancePayList(Integer orderId);
}
