package com.yimayhd.erpcenter.facade.finance.service.impl;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinancePayBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinancePayDetailBiz;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePayDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.yimayhd.erpcenter.facade.finance.result.FinancePayResult;
import org.yimayhd.erpcenter.facade.finance.service.FinancePayFacade;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhangxy on 2017/2/14.
 */
public class FinancePayFacadeImpl implements FinancePayFacade {
    @Autowired
    private FinancePayBiz financePayBiz;
    @Autowired
    private FinancePayDetailBiz financePayDetailBiz;
    @Override
    public FinancePayResult loadIncomePayRecordListPage(PageBean<FinancePay> pageBean, Integer bizId) {
        FinancePayResult result = new FinancePayResult();
        pageBean = financePayBiz.loadIncomePayRecordListPage(pageBean, bizId);
        result.setPageBean(pageBean);
        //获取收款总计
        Map<String, Object> fp = financePayBiz.loadIncomePayRecordTotal(pageBean, bizId);
        result.setResultMap(fp);
        return result;
    }



    @Override
    public FinancePay selectPayById(Integer payId) {
        return null;
    }

    @Override
    public int insertIncomePay(FinancePay record) {
        return financePayBiz.insertIncomePay(record);
    }

    @Override
    public int updateIncomePay(FinancePay record) {
        return financePayBiz.updateIncomePay(record);
    }

    @Override
    public PageBean loadIncomePayAddTableListPage(PageBean pageBean, Integer bizId) {

         pageBean = financePayBiz.loadIncomePayAddTableListPage(pageBean, bizId);
        return pageBean;
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
        financePayBiz.deletePayById(payId);

        financePayDetailBiz.deleteByPayId(payId);
    }



    @Override
    public void batchUpdateTotalCash(Integer payId, Integer supplierType,List<FinancePayDetail> financePayDetails) {
        Set<Integer> orderIds= new HashSet<Integer>();
        if(!CollectionUtils.isEmpty(financePayDetails)){
            boolean isInsert = payId == null ? true : false;
            for (FinancePayDetail fpd : financePayDetails) {
                fpd.setPayId(payId);
                if(isInsert){
                    financePayDetailBiz.insertFinancePayDetail(fpd);
                    orderIds.add(fpd.getLocOrderId());
                }else {
                    FinancePayDetail detailBean = financePayDetailBiz.selectByPayIdAndLocOrderId(payId, fpd.getLocOrderId());
                    orderIds.add(fpd.getLocOrderId());
                    if(detailBean != null){
                        detailBean.setCash(detailBean.getCash());
                        financePayDetailBiz.updateFinancePayDetail(detailBean);
                    }else{
                        financePayDetailBiz.insertFinancePayDetail(fpd);
                        orderIds.add(fpd.getLocOrderId());
                    }
                }
            }

            //汇总，到业务表的total_cash
            financePayBiz.batchUpdateTotalCash(orderIds, supplierType);
        }
    }
}
