package com.yimayhd.erpcenter.facade.finance.service.impl;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;
import org.yimayhd.erpcenter.facade.finance.service.FinancePayFacade;

import java.util.Map;

/**
 * Created by zhangxy on 2017/2/14.
 */
public class FinancePayFacadeImpl implements FinancePayFacade {
    @Override
    public PageBean<FinancePay> loadIncomePayRecordListPage(PageBean<FinancePay> pageBean, Integer bizId) {
        return null;
    }

    @Override
    public Map<String, Object> loadIncomePayRecordTotal(PageBean<FinancePay> pageBean, Integer bizId) {
        return null;
    }

    @Override
    public FinancePay selectPayById(Integer payId) {
        return null;
    }

    @Override
    public int insertIncomePay(FinancePay record) {
        return 0;
    }

    @Override
    public int updateIncomePay(FinancePay record) {
        return 0;
    }

    @Override
    public PageBean loadIncomePayAddTableListPage(PageBean pageBean, Integer bizId) {
        return null;
    }

    @Override
    public Map<String, Object> findOrderToIncomePayList(String oids, Integer supplierType, String payId) {
        return null;
    }

    @Override
    public FinancePay loadByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public void deletePayById(Integer payId) {

    }

    @Override
    public void batchUpdateTotalCash(Integer payId, Integer supplierType) {

    }
}
