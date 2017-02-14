package com.yimayhd.erpcenter.biz.sales.client.service.finance;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;

import java.util.Map;

/**
 * Created by zhangxy on 2017/2/14.
 */
public interface FinancePayBiz {
    /**
     * 收付款查询
     * @param pageBean
     * @param bizId
     * @return
     */
    PageBean<FinancePay> loadIncomePayRecordListPage(PageBean<FinancePay> pageBean, Integer bizId);

    /**
     * 收付款总计查询
     * @param pageBean
     * @param bizId
     * @return
     */
    Map<String, Object> loadIncomePayRecordTotal(PageBean<FinancePay> pageBean, Integer bizId);

    /**
     * 查询付款信息
     * @param payId
     * @return
     */
    FinancePay selectPayById(Integer payId);

    /**
     * 保存新增收款信息
     * @param record
     * @return
     */
    int insertIncomePay(FinancePay record);

    /**
     * 更新收款信息
     */
    int updateIncomePay(FinancePay record);

    /**
     * 收款处理-查询订单信息
     * @param pageBean
     * @param bizId
     * @return
     */
    PageBean loadIncomePayAddTableListPage(PageBean pageBean, Integer bizId);

    /**
     * 添加订单信息到收款
     * @param oids
     * @param supplierType
     * @param payId
     * @return
     */
    Map<String, Object> findOrderToIncomePayList(String oids, Integer supplierType, String payId);

    /**
     * 查看收款信息
     * @param id
     * @return
     */
    FinancePay  loadByPrimaryKey(Integer id);

    /**
     * 删除财务-收款详情
     */
    void deletePayById(Integer payId);

    void batchUpdateTotalCash(Integer payId, Integer supplierType);
}
