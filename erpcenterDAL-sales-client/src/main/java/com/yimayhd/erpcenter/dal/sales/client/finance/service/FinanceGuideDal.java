package com.yimayhd.erpcenter.dal.sales.client.finance.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.CheckSheetFinance;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceCommission;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceGuide;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;

/**
 * 导游报账
 * 
 * @author Jing.Zhuo
 * @create 2015年8月13日 上午10:07:03
 */
public interface FinanceGuideDal {

	/**
	 * 审核导游报账单
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年8月13日 上午9:56:10
	 * @param billId
	 * @return
	 */
	void auditGuideBill(Integer billId) throws ClientException;
	
	/**
	 * 取消审核导游报账单
	 * 
	 */
	void delAuditGuideBill(Integer billId);

	/**
	 * 驳回导游报账单
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年8月13日 上午9:56:10
	 * @param billId
	 * @return
	 */
	void rejectGuideBill(Integer billId);

	/**
	 * 报账单付款
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年8月13日 上午11:12:40
	 * @param pay
	 */
	void payGuideBill(FinancePay pay);
	
	void payGuideBill(FinancePay pay,String checkedArr,String name);
	
	/**
	 * 删除导游报账单
	 * @param id
	 */
	void deletePayById(Integer payId);
	
	/**
	 * 删除佣金发放付款记录
	 * @param id
	 */
	void deletePayCommById(Integer payId);
	
	/**
	 * 删除佣金扣除付款记录
	 * @param id
	 */
	void deletePayCommDeductionById(Integer payId);

	/**
	 * @author : xuzejun
	 * @date : 2015年8月13日 下午4:41:50
	 * @Description: 查询导游报账单
	 */
	List<FinanceGuide> selectListByGroupIdAndBookingId(Integer groupId,
			Integer bookingId);

	
    List<CheckSheetFinance> selectListByGroupIdAndBookingIdAndType(@Param("groupId")Integer groupId,@Param("bookingId")Integer bookingId,@Param("supplierType")Integer supplierType);
	/**
	 * @author : xuzejun
	 * @date : 2015年8月14日 上午10:00:23
	 * @Description: 查询报账单关联的计调的数据
	 */
	List<BookingSupplier> getFinanceSupplierByFinanceGuide(
			FinanceGuide financeGuide);
	
	List<BookingSupplier> getFinanceSupplier(
			FinanceGuide financeGuide);
	
	/**
	 * 批量添加导游报账单
	 * @param list
	 */
	void financeBatchSave(List<FinanceGuide> list);
	
	/**
	 * 添加导游报账单
	 * @param item
	 */
	void financeSave(FinanceGuide item);
	
	int save(List<FinanceGuide> list);
	
	void financeDelete(Integer groupId, Integer bookingIdLink, Integer bookingId);
	
	int insertSelective(FinanceGuide record);

	int deleteByPrimaryKey(Integer id);

	/**
	 * 提交报账单
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年8月17日 下午4:10:18
	 * @param billId
	 *            报账单号
	 * @param state
	 *            报账状态
	 */
	void submit(Integer billId, int state);

    FinanceGuide selectByPrimaryKey(Integer id);
    
    Integer selectCountByBookingId(Integer bookingId);

	List<Map<String, String>> getGuideNameList(Integer bizId, String name);
	
	/**
	 * 查询团导游发放佣金
	 * @return
	 */
	List<FinanceCommission> selectCommissionByGroupId(Integer groupId);
	
	/**
	 * 查询团导游扣除佣金
	 * @return
	 */
	List<FinanceCommission> selectCommissionDeductionByGroupId(Integer groupId);
	
	/**
	 * 添加团导游发放佣金
	 * @param com
	 */
	void batchInsertCommission(Integer bizId, Integer userId, String userName, List<FinanceCommission> list);
	
	/**
	 * 添加团导游扣除佣金
	 * @param com
	 */
	void batchInsertCommissionDeduction(Integer bizId, Integer userId, String userName, List<FinanceCommission> list);
	
	/**
	 * 添加团导游佣金
	 * @param com
	 */
	void deleteCommission(Integer bizId, Integer id);
	
	/**
	 * 删除团导游发放佣金
	 * @param com
	 */
	void deleteCommissionByGroupId(Integer bizId, Integer groupId);
	
	/**
	 * 删除团导游扣除佣金
	 * @param com
	 */
	void deleteCommissionDeductionByGroupId(Integer bizId, Integer groupId);
	
	List<FinanceCommission> getFinanceCommisionByGroupIdAndGuideId(Integer bizId,Integer groupId,
			Integer guideId);
	
	List<FinanceCommission> getFinanceCommisionDeductionByGroupIdAndGuideId(Integer bizId,Integer groupId,
			Integer guideId);
	
	/**
	 * 购物佣金发放统计
	 * @param pageBean
	 * @return
	 */
	PageBean getCommisionStatsListPage(PageBean pageBean);
	
	/**
	 * 购物佣金扣除统计
	 * @param pageBean
	 * @return
	 */
	PageBean getCommisionDeductionStatsListPage(PageBean pageBean);

	PageBean querySettleListPage(PageBean pageBean, Integer curBizId);
	
	BigDecimal getSumTotal(PageBean pageBean, Integer curBizId);
	
	BigDecimal getSumTotalCash(PageBean pageBean, Integer curBizId);

	/**
	 * 购物佣金发放付款
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年8月13日 上午11:12:40
	 * @param pay
	 */
	public void payCommBill(FinancePay pay);
	
	/**
	 * 购物佣金扣除付款
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年8月13日 上午11:12:40
	 * @param pay
	 */
	public void payCommDeductionBill(FinancePay pay);
	
	/**
	 * 查询导游报账付款记录
	 * @param pageBean
	 * @return
	 */
	PageBean selectGuideCashRecordListPage(PageBean pageBean);
	
	/**
	 * 查询导游报账记录详情
	 * @param payId
	 * @return
	 */
	List<Map<String, Object>> selectGuidePayDetailsByPayId(Integer payId);
	
	/**
	 * 查询佣金发放报账记录详情
	 * @param payId
	 * @return
	 */
	List<Map<String, Object>> selectCommPayDetailsByPayId(Integer payId);
	
	/**
	 * 查询佣金扣除报账记录详情
	 * @param payId
	 * @return
	 */
	List<Map<String, Object>> selectCommPayDetailsDeductionByPayId(Integer payId);

	Map<String,Object> getCommisionsSum(PageBean pageBean);
	
	Map<String,Object> getCommisionsDeductionSum(PageBean pageBean);
	
	/**
	 * 查询团应付佣金和
	 * @param groupId
	 * @return
	 */
	BigDecimal selectCommTotalByGroupId(Integer groupId);
	
//	FinanceGuide selectByBookingIdLink(Integer bookingIdLink);
//	int deleteByBookingIdLink(Integer bookingIdLink);
//	BigDecimal selectTotalByBookingIdLinkAndType(Integer bookingIdLink,Integer type);
	
	/**
	 * 查询导游带团的佣金
	 */
	Integer getCommisionTotalSum(Integer groupId, Integer guideId);
	
	/**
	 * 查询导游带团的 佣金总和、已付款总和
	 * @param groupId
	 * @param guideId
	 * @return
	 */
	Map<String, Object> getCommisionTotalSumAndTotalCashSum(@Param("groupId")Integer groupId, @Param("guideId")Integer guideId);
	
	
	Map<String, Object> getCommisionDeductionTotalSumAndTotalCashSum(@Param("groupId")Integer groupId, @Param("guideId")Integer guideId);

	
	List<FinanceCommission> getCommisions(PageBean pageBean);
	
	List<FinanceCommission> getCommisionDeductions(PageBean pageBean);
	
	/**
	 * 查询导游佣金总计
	 * @param pageBean
	 * @return
	 */
	Map<String, Object> getAllTotalSumAndTotalCashSum(PageBean pageBean);
	
	/**
	 * 查询付款信息
	 * @param payId
	 * @return
	 */
	Map<String, Object> selectGuidePayInfo(Integer payId);
	
	/**
	 * 查询佣金发放付款信息
	 * @param payId
	 * @return
	 */
	Map<String, Object> selectCommPayInfo(Integer payId);
	
	/**
	 * 查询佣金扣除付款信息
	 * @param payId
	 * @return
	 */
	Map<String, Object> selectCommPayDeductionInfo(Integer payId);
	
	/**
	 * 转换数据
	 * @param data
	 * @param pageBean
	 * @param isDeduction
	 * @return
	 */
	BookingGuide parseDataToGuide(Map<String, Object> dataMap, PageBean pageBean, boolean isDeduction);

	void changeGroup(Integer groupId, Integer guideId, Integer mguideId);
	
}
