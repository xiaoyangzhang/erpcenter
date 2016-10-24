package com.yimayhd.erpcenter.dal.sales.finance.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;

public interface FinancePayCommissionDeductionMapper {
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
    List<FinancePay> getFinancePayBySupplierId(@Param("supplierId")Integer supplierId) ;
    
    /**
     * 查询团的收付款记录数
     * @param groupId
     * @return
     */
    int selectPayOrIncomeRecordCount(@Param("groupId")Integer groupId);
    
    Map<String, Object> selectGuidePayInfo(@Param("payId")Integer pyaId);
    
    Map<String, Object> selectCommPayInfo(@Param("payId")Integer pyaId);
}