package com.yimayhd.erpcenter.facade.finance.service.impl;

import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePayDetail;
import org.yimayhd.erpcenter.facade.finance.service.FinancePayDetailFacade;

import java.util.Set;

/**
 * Created by zhangxy on 2017/2/14.
 */
public class FinancePayDetailFacadeImpl implements FinancePayDetailFacade {
    @Override
    public int insertFinancePayDetail(FinancePayDetail record) {
        return 0;
    }

    @Override
    public FinancePayDetail selectByPayIdAndLocOrderId(Integer payId, Integer locOrderId) {
        return null;
    }

    @Override
    public int updateFinancePayDetail(FinancePayDetail detail) {
        return 0;
    }

    @Override
    public int deleteByPayIdAndOrder(Integer payId, Integer locOrderId) {
        return 0;
    }

    @Override
    public int batchDeleteByPayIdLocOrderId(Integer payId, Set<Integer> locOrderIdSet) {
        return 0;
    }

    @Override
    public int deleteByPayId(Integer payId) {
        return 0;
    }
}
