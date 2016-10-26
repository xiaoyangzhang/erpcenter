package com.yimayhd.erpcenter.dal.sales.finance.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;

public interface FinancePayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FinancePay record);

    int insertSelective(FinancePay record);

    FinancePay selectByPrimaryKey(Integer id);
    
    int update(FinancePay record);
    
    List<Map<String, Object>> selectGuideCashRecordListPage(PageBean pageBean);

    /**
     * 根据商家Id查询商家的支付明细
     * @param supplierId
     * @return
     */
    List<FinancePay> getFinancePayBySupplierId(@Param("supplierId")Integer supplierId, @Param("bizId")Integer bizId) ;
    
    /**
     * 查询团的收付款记录数
     * @param groupId
     * @return
     */
    int selectPayOrIncomeRecordCount(@Param("groupId")Integer groupId);
    
    Map<String, Object> selectGuidePayInfo(@Param("payId")Integer pyaId);
    
    Map<String, Object> selectCommPayInfo(@Param("payId")Integer pyaId);

	/**
	 * 计算某团收款付款不正确
	 * */
	void calcTotalCash_Supplier(@Param("groupId")Integer groupId);
	void calcTotalCash_OtherIn(@Param("groupId")Integer groupId);
	void calcTotalCash_Delivery(@Param("groupId")Integer groupId);
	void calcTotalCash_Sales(@Param("groupId")Integer groupId);
	
	/*
	 * 汇总某订单 的决已付金额 
	 * @param supplierType 1, 120, 16, 其它     ：1为销售订单 ，120为其它收入，16为地接订单 ，其它为房餐车等 
	 */
	java.math.BigDecimal calcTotalCashValue(@Param("orderId")Integer orderId, @Param("supplierType")Integer supplierType);
}