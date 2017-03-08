package com.yimayhd.erpcenter.dal.sales.finance.impl;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;
import com.yimayhd.erpcenter.dal.sales.client.finance.service.FinancePayDal;
import com.yimayhd.erpcenter.dal.sales.finance.dao.FinancePayMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhangxy on 2017/2/13.
 */
public class FinancePayDalImpl implements FinancePayDal {
    @Autowired
    private FinancePayMapper financePayMapper;

    @Autowired
    private GroupOrderMapper groupOrderMapper;

    /**
     * 收付款查询
     */
    @Override
    public PageBean<FinancePay> loadIncomePayRecordListPage(PageBean<FinancePay> pageBean, Integer bizId) {
        List<FinancePay> fPayList = financePayMapper.findIncomePayRecordListPage(pageBean, bizId);
        pageBean.setResult(fPayList);
        return pageBean;
    }

    /**
     * 收付款总计查询
     */
    @Override
    public Map<String, Object> loadIncomePayRecordTotal(PageBean<FinancePay> pageBean, Integer bizId) {
        Map<String, Object> financePayBean = financePayMapper.findIncomePayRecordTotal(pageBean, bizId);
        return financePayBean;
    }

    /**
     * 查询付款信息
     */
    @Override
    public FinancePay selectPayById(Integer payId) {
        return financePayMapper.selectByPrimaryKey(payId);
    }

    /**
     * 保存新增收款信息
     */
    @Override
    public int insertIncomePay(FinancePay record) {
        financePayMapper.insert(record);
        return record.getId();
    }

    /**
     * 更新收款信息
     */
    @Override
    public int updateIncomePay(FinancePay record) {
        financePayMapper.update(record);
        return record.getId();
    }

    @Override
    public PageBean loadIncomePayAddTableListPage(PageBean pageBean, Integer bizId) {
        List<Map<String,Object>> result = groupOrderMapper.selectIncomePayAddTableListPage(pageBean, bizId);
        pageBean.setResult(result);
        return pageBean;
    }

    /**
     * 添加订单信息到收款
     */
    @Override
    public Map<String, Object> findOrderToIncomePayList(String ids, Integer supplierType, String payId) {
        Map<String, Object> mapObj = financePayMapper.findOrderToIncomePayList(ids, supplierType, payId);
        return mapObj;
    }

    /**
     * 查看收款详情
     */
    @Override
    public FinancePay loadByPrimaryKey(Integer id) {
        //根据Id进行查询
        FinancePay fpBean = financePayMapper.selectByPrimaryKey(id);
        return fpBean;
    }

    @Override
    @Transactional
    public void deletePayById(Integer payId) {
        financePayMapper.deleteByPrimaryKey(payId);
    }

    @Override
    public void batchUpdateTotalCash(Set<Integer> set, Integer supplierType) {
        financePayMapper.batchUpdate_TotalCash(set, supplierType);

    }
}
