package com.yimayhd.erpcenter.biz.sales.service.impl.finance;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePayDetail;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.InfoBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.service.FinanceDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

/**
 * 财务管理
 * 
 * @author Jing.Zhuo
 * @create 2015年7月28日 上午11:39:28
 */
public class FinanceBizImpl implements FinanceBiz {

	@Autowired
	private FinanceDal financeDal;

	@SuppressWarnings("unchecked")
	@Override
	public Map queryAuditViewInfo(Integer groupId, Integer bizId) {
		return financeDal.queryAuditViewInfo(groupId, bizId);
	}
	
	/**
	 * 团购物、佣金汇总
	 * @param groupId
	 * @return
	 */
	@Override
	public InfoBean statsShopWithCommInfoBean(Integer groupId){
		return financeDal.statsShopWithCommInfoBean(groupId);
	}
	
	@Transactional
	@Override
	public void calcTourGroupAmount(Integer groupId) {
		financeDal.calcTourGroupAmount(groupId);
	}

	/* (non-Javadoc)
	 * @see com.yihg.finance.api.FinanceService#calcTotalCash_collection(java.lang.Integer)
	 */
	@Override
	public void calcTotalCash_collection(Integer groupId) {
		financeDal.calcTotalCash_collection(groupId);
	}

	@Override
	@Transactional
	public void audit(Integer groupId, String feeType, String checkedIds, String unCheckedIds, 
			String comCheckedIds, String comUnCheckedIds, Integer userId, String userName) {
		
		financeDal.audit(groupId, feeType, checkedIds, unCheckedIds, comCheckedIds, comUnCheckedIds, userId, userName);
	}
	
	@Override
	@Transactional
	public void auditCommDeduction(Integer groupId, String feeType, String comCheckedIds, 
			String comUnCheckedIds, Integer userId, String userName) {
		
		financeDal.auditCommDeduction(groupId, feeType, comCheckedIds, comUnCheckedIds, userId, userName);
	}
	
	@Override
	@Transactional
	public void audit(String data, Integer userId, String userName) {
		
		financeDal.audit(data, userId, userName);
	}

	@Override
	@Transactional
	public void audit(Integer groupId, Integer userId, String userName) {

		financeDal.audit(groupId, userId, userName);
	}
	
	@Override
	@Transactional
	public void unAudit(Integer groupId, String userName) {

		financeDal.unAudit(groupId, userName);
	}
	
	@Override
	@Transactional
	public void batchSeal(String groupIds, Integer userId, String userName) {

		financeDal.batchSeal(groupIds, userId, userName);
	}
	
	@Override
	@Transactional
	public void unseal(String groupId, Integer userId, String userName) {
		
		financeDal.unseal(groupId, userId, userName);
	}
	
	@Override
	@Transactional
	public int makeCollections(FinancePay pay,Integer orderId) {
		return financeDal.makeCollections(pay, orderId);
	}
	
	@Override
	@Transactional
	public FinancePay pay(FinancePay pay, List<FinancePayDetail> details, Integer supplierType) {
		return financeDal.pay(pay, details, supplierType);
	}

	@Override
	public FinancePay queryPayById(Integer payId) {
		return financeDal.queryPayById(payId);
	}

	@Override
	public List<TourGroup> getOperateLogs(Integer groupId) {
		return financeDal.getOperateLogs(groupId);
	}

	@Override
	@Transactional
	public void deletePayById(Integer payId) {
		financeDal.deletePayById(payId);
	}
	
	@Override
	@Transactional
	public void deletePayDetail(Integer supplierType, Integer locOrderId, Integer payId) {

		financeDal.deletePayDetail(supplierType, locOrderId, payId);
		
	}

	@Override
	public void auditShop(String checkedIds, String unCheckedIds, Integer employeeId, String name) {
		financeDal.auditShop(checkedIds, unCheckedIds, employeeId, name);
	}
	
	@Override
	@Transactional
	public void auditComm(String checkedIds, String unCheckedIds,Integer userId, String userName) {
		financeDal.auditComm(checkedIds, unCheckedIds, userId, userName);
	}
	
	@Override
	@Transactional
	public void auditCommDeduction(String checkedIds, String unCheckedIds,Integer userId, String userName) {
		financeDal.auditCommDeduction(checkedIds, unCheckedIds, userId, userName);
	}
	
	@Override
	public PageBean selectIncomeJoinTableListPage(PageBean pageBean, Integer bizId) {
		
		return financeDal.selectIncomeJoinTableListPage(pageBean, bizId);
	}

	@Override
	public PageBean getsubjectSummaryListPage(PageBean pageBean) {
		return financeDal.getsubjectSummaryListPage(pageBean);
	}
	
	@Override
	public PageBean getsubjectSummary2ListPage(PageBean pageBean,String sum) {
		return financeDal.getsubjectSummary2ListPage(pageBean, sum);
	}

	@Override
	public Map<String, Object> getsubjectSummaryQDYJ(PageBean pageBean,Integer supplierId,String sType) {
		return financeDal.getsubjectSummaryQDYJ(pageBean, supplierId, sType);
	}
	
	@Override
	public Map<String, Object> getsubjectSummaryQT(PageBean pageBean,Integer supplierId,String sType,String sType2,String sType3) {
		return financeDal.getsubjectSummaryQT(pageBean, supplierId, sType, sType2, sType3);
	}
	
	@Override
	public Map<String, Object> getsubjectSummaryQDYJTotal(PageBean pageBean,String sType) {
		return financeDal.getsubjectSummaryQDYJTotal(pageBean, sType);
	}
	
	@Override
	public Map<String, Object> getsubjectSummaryQTTotal(PageBean pageBean,String sType,String sType2,String sType3) {
		return financeDal.getsubjectSummaryQTTotal(pageBean, sType, sType2, sType3);
	}

	@Override
	public List<FinancePay> getFinancePayBySupplierId(Integer supplierId, Integer bizId) {
		return financeDal.getFinancePayBySupplierId(supplierId, bizId);
	}
	//判断是否有酒店,车队订单
	@Override
	public boolean hasHotelOrder(Integer groupId) {
		return financeDal.hasHotelOrder(groupId);
	}
				
	@Override
	public boolean hasAuditOrder(Integer groupId) {
		return financeDal.hasAuditOrder(groupId);
	}

	@Override
	public boolean hasPayOrIncomeRecord(Integer groupId){
		return financeDal.hasPayOrIncomeRecord(groupId);
	}

	@Override
	public List<Map<String, Object>> selectDetailByLocOrderId(Integer locOrderId) {
		
		return financeDal.selectDetailByLocOrderId(locOrderId);
	}

	@Transactional
	@Override
	public void calcBookingSupplierTotalCash(Integer bookingSupplierId) {
		
		financeDal.calcBookingSupplierTotalCash(bookingSupplierId);
	}
	
	@Transactional
	@Override
	public void calcGroupOrderTotalCash(Integer groupOrderId) {
		
		financeDal.calcGroupOrderTotalCash(groupOrderId);
	}
	
	
	/* (non-Javadoc)
	 * @see com.yihg.finance.api.FinanceService#calcTotalCashValue(java.lang.Integer, java.lang.Integer)
	 * @param supplierType 1, 120(12), 16, 其它     ：1为销售订单 ，120(12)为其它收入，16为地接订单 ，其它为房餐车等 
	 */
	@Override
	public BigDecimal calcTotalCashValue(Integer orderId, Integer supplierType) {
		return financeDal.calcTotalCashValue(orderId, supplierType);
	}
	
	@Override
	public List<FinancePayDetail> selectFinancePayList(Integer orderId){
		return financeDal.selectFinancePayList(orderId);
	}
}
