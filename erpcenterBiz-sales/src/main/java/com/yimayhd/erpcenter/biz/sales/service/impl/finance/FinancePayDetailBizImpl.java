package com.yimayhd.erpcenter.biz.sales.service.impl.finance;

import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinancePayDetailBiz;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePayDetail;
import com.yimayhd.erpcenter.dal.sales.client.finance.service.FinancePayDetailDal;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created by zhangxy on 2017/2/14.
 */
public class FinancePayDetailBizImpl implements FinancePayDetailBiz {
    @Autowired
    private FinancePayDetailDal financePayDetailDal;
    @Override
    public int insertFinancePayDetail(FinancePayDetail record) {
        return financePayDetailDal.insertFinancePayDetail(record);
    }

    @Override
    public FinancePayDetail selectByPayIdAndLocOrderId(Integer payId, Integer locOrderId) {
        return financePayDetailDal.selectByPayIdAndLocOrderId(payId, locOrderId);
    }

    @Override
    public int updateFinancePayDetail(FinancePayDetail detail) {
        return financePayDetailDal.updateFinancePayDetail(detail);
    }

    @Override
    public int deleteByPayIdAndOrder(Integer payId, Integer locOrderId) {
        return financePayDetailDal.deleteByPayIdAndOrder(payId, locOrderId);
    }

    @Override
    public int batchDeleteByPayIdLocOrderId(Integer payId, Set<Integer> locOrderIdSet) {
        return financePayDetailDal.batchDeleteByPayIdLocOrderId(payId, locOrderIdSet);
    }

    @Override
    public int deleteByPayId(Integer payId) {
        return financePayDetailDal.deleteByPayId(payId);
    }
}
