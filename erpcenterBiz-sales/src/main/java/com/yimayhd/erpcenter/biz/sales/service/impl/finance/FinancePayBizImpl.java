package com.yimayhd.erpcenter.biz.sales.service.impl.finance;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinancePayBiz;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;
import com.yimayhd.erpcenter.dal.sales.client.finance.service.FinancePayDal;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by zhangxy on 2017/2/14.
 */
public class FinancePayBizImpl implements FinancePayBiz {
    @Autowired
    private FinancePayDal financePayDal;
    @Override
    public PageBean<FinancePay> loadIncomePayRecordListPage(PageBean<FinancePay> pageBean, Integer bizId) {
        return financePayDal.loadIncomePayRecordListPage(pageBean, bizId);
    }

    @Override
    public Map<String, Object> loadIncomePayRecordTotal(PageBean<FinancePay> pageBean, Integer bizId) {
        return financePayDal.loadIncomePayRecordTotal(pageBean, bizId);
    }

    @Override
    public FinancePay selectPayById(Integer payId) {
        return financePayDal.selectPayById(payId);
    }

    @Override
    public int insertIncomePay(FinancePay record) {
        return financePayDal.insertIncomePay(record);
    }

    @Override
    public int updateIncomePay(FinancePay record) {
        return financePayDal.updateIncomePay(record);
    }

    @Override
    public PageBean loadIncomePayAddTableListPage(PageBean pageBean, Integer bizId) {
        return financePayDal.loadIncomePayAddTableListPage(pageBean, bizId);
    }

    @Override
    public Map<String, Object> findOrderToIncomePayList(String oids, Integer supplierType, String payId) {
        return financePayDal.findOrderToIncomePayList(oids, supplierType, payId);
    }

    @Override
    public FinancePay loadByPrimaryKey(Integer id) {
        return financePayDal.loadByPrimaryKey(id);
    }

    @Override
    public void deletePayById(Integer payId) {
        financePayDal.deletePayById(payId);
    }

    @Override
    public void batchUpdateTotalCash(Integer payId, Integer supplierType) {
        financePayDal.batchUpdateTotalCash(payId, supplierType);
    }
}
