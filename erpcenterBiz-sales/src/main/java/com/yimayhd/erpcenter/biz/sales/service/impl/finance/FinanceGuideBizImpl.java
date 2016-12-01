package com.yimayhd.erpcenter.biz.sales.service.impl.finance;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceGuideBiz;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.CheckSheetFinance;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceCommission;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceGuide;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;
import com.yimayhd.erpcenter.dal.sales.client.finance.service.FinanceGuideDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;

/**
 * 导游报账
 * 
 * @author Jing.Zhuo
 * @create 2015年8月13日 下午12:11:47
 */
public class FinanceGuideBizImpl implements FinanceGuideBiz {

	@Autowired
	private FinanceGuideDal financeGuideDal;

	@Override
	@Transactional
	public void auditGuideBill(Integer billId) {
		
		financeGuideDal.auditGuideBill(billId);
	}
	
	@Override
	@Transactional
	public void delAuditGuideBill(Integer billId) {
		financeGuideDal.delAuditGuideBill(billId);
	}

	@Override
	@Transactional
	public void rejectGuideBill(Integer billId) {

		financeGuideDal.rejectGuideBill(billId);
	}

	@Override
	@Transactional
	public void submit(Integer billId, int state) {
		financeGuideDal.submit(billId, state);
	}

	@Override
	@Transactional
	public void payGuideBill(FinancePay pay) {

		financeGuideDal.payGuideBill(pay);
	}
	
	/**
	 * 导游报账单分开报账
	 */
	@Override
	@Transactional
	public void payGuideBill(FinancePay pay,String checkedArr,String name) {

		financeGuideDal.payGuideBill(pay, checkedArr, name);
	}
	
	/**
	 * 删除导游报账单
	 */
	@Override
	@Transactional
	public void deletePayById(Integer payId){
		financeGuideDal.deletePayById(payId);
	}
	
	/**
	 * 删除佣金发放付款记录
	 */
	@Override
	@Transactional
	public void deletePayCommById(Integer payId){
		financeGuideDal.deletePayCommById(payId);
	}
	
	/**
	 * 删除佣金扣除付款记录
	 */
	@Override
	@Transactional
	public void deletePayCommDeductionById(Integer payId){
		financeGuideDal.deletePayCommDeductionById(payId);
	}

	@Override
	public List<FinanceGuide> selectListByGroupIdAndBookingId(Integer groupId, Integer bookingId) {
		return financeGuideDal.selectListByGroupIdAndBookingId(groupId, bookingId);
	}

	@Override
	public List<BookingSupplier> getFinanceSupplierByFinanceGuide(FinanceGuide financeGuide) {
		return financeGuideDal.getFinanceSupplierByFinanceGuide(financeGuide);
	}
	
	@Override
	public List<BookingSupplier> getFinanceSupplier(FinanceGuide financeGuide) {
		return financeGuideDal.getFinanceSupplier(financeGuide);
	}
	
	/**
	 * 批量添加导游报账订单
	 * @param data
	 * @return
	 */
	@Transactional
	public void financeBatchSave(List<FinanceGuide> list) {
		financeGuideDal.financeBatchSave(list);
	}
	
	/**
	 * 添加导游报账订单
	 * @param data
	 * @return
	 */
	@Transactional
	public void financeSave(FinanceGuide item) {
		financeGuideDal.financeSave(item);
	}

	@Override
	@Transactional
	public int save(List<FinanceGuide> list) {
		return financeGuideDal.save(list);
	}
	
	/**
	 * 删除导游报账订单
	 * @param id
	 * @param bookingId
	 * @return
	 */
	@Transactional
	public void financeDelete(Integer groupId, Integer bookingIdLink,Integer bookingId) {
		financeGuideDal.financeDelete(groupId, bookingIdLink, bookingId);
	}
	
	
	
	@Override
	@Transactional
	public int deleteByPrimaryKey(Integer id) {
		return financeGuideDal.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(FinanceGuide record) {
		return financeGuideDal.insertSelective(record);
	}

	@Override
	public List<CheckSheetFinance> selectListByGroupIdAndBookingIdAndType(
			Integer groupId, Integer bookingId, Integer supplierType) {
		return financeGuideDal.selectListByGroupIdAndBookingIdAndType(groupId, bookingId, supplierType);
	}

	@Override
	public FinanceGuide selectByPrimaryKey(Integer id) {
		return financeGuideDal.selectByPrimaryKey(id);
	}

	@Override
	public Integer selectCountByBookingId(Integer bookingId) {
		return financeGuideDal.selectCountByBookingId(bookingId);
	}

	@Override
	public List<Map<String, String>> getGuideNameList(Integer bizId, String name) {
		return financeGuideDal.getGuideNameList(bizId, name);
	}
	
	/**
	 * 查询团导游发放佣金
	 * @param groupId
	 * @return
	 */
	@Override
	public List<FinanceCommission> selectCommissionByGroupId(Integer groupId) {
		return financeGuideDal.selectCommissionByGroupId(groupId);
	}
	
	/**
	 * 查询团导游扣除佣金
	 * @param groupId
	 * @return
	 */
	@Override
	public List<FinanceCommission> selectCommissionDeductionByGroupId(Integer groupId) {
		return financeGuideDal.selectCommissionDeductionByGroupId(groupId);
	}
	
	/**
	 * 添加团导游发放佣金
	 * @param com
	 */
	@Override
	@Transactional
	public void batchInsertCommission(Integer bizId, Integer userId, String userName, List<FinanceCommission> list){
		
		financeGuideDal.batchInsertCommission(bizId, userId, userName, list);
	}
	
	/**
	 * 添加团导游扣除佣金
	 * @param com
	 */
	@Override
	@Transactional
	public void batchInsertCommissionDeduction(Integer bizId, Integer userId, String userName, List<FinanceCommission> list){
		
		financeGuideDal.batchInsertCommissionDeduction(bizId, userId, userName, list);
	}

	/**
	 * 删除单条导游佣金
	 */
	@Override
	public void deleteCommission(Integer bizId, Integer id) {
		financeGuideDal.deleteCommission(bizId, id);
	}
	
	/**
	 * 删除团导游发放佣金
	 */
	public void deleteCommissionByGroupId(Integer bizId, Integer groupId) {
		financeGuideDal.deleteCommissionByGroupId(bizId, groupId);
	}
	
	/**
	 * 删除团导游扣除佣金
	 */
	public void deleteCommissionDeductionByGroupId(Integer bizId, Integer groupId) {
		financeGuideDal.deleteCommissionDeductionByGroupId(bizId, groupId);
	}

	@Override
	public List<FinanceCommission> getFinanceCommisionByGroupIdAndGuideId(Integer bizId,Integer groupId,
			Integer guideId) {
		return financeGuideDal.getFinanceCommisionByGroupIdAndGuideId(bizId, groupId, guideId);
	}
	
	@Override
	public List<FinanceCommission> getFinanceCommisionDeductionByGroupIdAndGuideId(Integer bizId,Integer groupId,
			Integer guideId) {
		return financeGuideDal.getFinanceCommisionDeductionByGroupIdAndGuideId(bizId, groupId, guideId);
	}

	/**
	 * 购物佣金发放统计
	 */
	@Override
	public PageBean getCommisionStatsListPage(PageBean pageBean) {
		return financeGuideDal.getCommisionStatsListPage(pageBean);
	}
	
	/**
	 * 购物佣金扣除统计
	 */
	@Override
	public PageBean getCommisionDeductionStatsListPage(PageBean pageBean) {
		return financeGuideDal.getCommisionDeductionStatsListPage(pageBean);
	}
	
	@Override
	public BookingGuide parseDataToGuide(Map<String, Object> dataMap, PageBean pageBean, boolean isDeduction){
		
		return financeGuideDal.parseDataToGuide(dataMap, pageBean, isDeduction);
	}
	
	@Override
	public PageBean querySettleListPage(PageBean pageBean, Integer bizId) {
		return financeGuideDal.querySettleListPage(pageBean, bizId);
	}
	
	@Override
	public BigDecimal getSumTotal(PageBean pageBean, Integer bizId) {
		return financeGuideDal.getSumTotal(pageBean, bizId);
	}
	
	@Override
	public BigDecimal getSumTotalCash(PageBean pageBean, Integer bizId) {
		return financeGuideDal.getSumTotalCash(pageBean, bizId);
	}
	
	@Override
	@Transactional
	public void payCommBill(FinancePay pay) {
		
		financeGuideDal.payCommBill(pay);
	}
	
	@Override
	@Transactional
	public void payCommDeductionBill(FinancePay pay) {
		
		financeGuideDal.payCommDeductionBill(pay);
	}

	@Override
	public PageBean selectGuideCashRecordListPage(PageBean pageBean) {
		return financeGuideDal.selectGuideCashRecordListPage(pageBean);
	}

	@Override
	public List<Map<String, Object>> selectGuidePayDetailsByPayId(Integer payId) {
		return financeGuideDal.selectGuidePayDetailsByPayId(payId);
	}
	
	@Override
	public List<Map<String, Object>> selectCommPayDetailsByPayId(Integer payId) {
		return financeGuideDal.selectCommPayDetailsByPayId(payId);
	}
	
	@Override
	public List<Map<String, Object>> selectCommPayDetailsDeductionByPayId(Integer payId) {
		return financeGuideDal.selectCommPayDetailsDeductionByPayId(payId);
	}

	@Override
	public Map<String, Object> getCommisionsSum(PageBean pageBean) {
		return financeGuideDal.getCommisionsSum(pageBean);
	}
	
	@Override
	public Map<String, Object> getCommisionsDeductionSum(PageBean pageBean) {
		return financeGuideDal.getCommisionsDeductionSum(pageBean);
	}
	
	@Override
	public BigDecimal selectCommTotalByGroupId(Integer groupId) {
		return financeGuideDal.selectCommTotalByGroupId(groupId);
	}

	@Override
	public Integer getCommisionTotalSum(Integer groupId, Integer guideId) {
		return financeGuideDal.getCommisionTotalSum(groupId, guideId);
	}
	
	@Override
	public Map<String, Object> getCommisionTotalSumAndTotalCashSum(Integer groupId, Integer guideId) {
		
		return financeGuideDal.getCommisionTotalSumAndTotalCashSum(groupId, guideId);
	}

	@Override
	public List<FinanceCommission> getCommisions(PageBean pageBean) {
		return financeGuideDal.getCommisions(pageBean);
	}
	
	@Override
	public List<FinanceCommission> getCommisionDeductions(PageBean pageBean) {
		return financeGuideDal.getCommisionDeductions(pageBean);
	}

	@Override
	public Map<String, Object> getAllTotalSumAndTotalCashSum(PageBean pageBean) {
		return financeGuideDal.getAllTotalSumAndTotalCashSum(pageBean);
	}

	@Override
	public Map<String, Object> selectGuidePayInfo(Integer payId) {
		return financeGuideDal.selectGuidePayInfo(payId);
	}
	
	@Override
	public Map<String, Object> selectCommPayInfo(Integer payId) {
		return financeGuideDal.selectCommPayInfo(payId);
	}
	
	@Override
	public Map<String, Object> selectCommPayDeductionInfo(Integer payId) {
		return financeGuideDal.selectCommPayDeductionInfo(payId);
	}

	@Override
	public void changeGroup(Integer groupId, Integer guideId, Integer mguideId) {
		financeGuideDal.changeGroup(groupId, guideId, mguideId);
	}
}
