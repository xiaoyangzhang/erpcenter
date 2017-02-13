package com.yimayhd.erpcenter.dal.sales.finance.impl;

import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePayDetail;
import com.yimayhd.erpcenter.dal.sales.client.finance.service.FinancePayDetailDal;
import com.yimayhd.erpcenter.dal.sales.finance.dao.FinancePayDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created by zhangxy on 2017/2/13.
 */
public class FinancePayDetailDalImpl implements FinancePayDetailDal {

    @Autowired
    private FinancePayDetailMapper financePayDetailMapper;

    /**
     * 插入操作
     */
    @Override
    public int insertFinancePayDetail(FinancePayDetail record) {
        int num = financePayDetailMapper.insert(record);
        return num;
    }

    /**
     * 根据pay_id和订单ID进行查询
     */
    @Override
    public FinancePayDetail selectByPayIdAndLocOrderId(Integer payId, Integer locOrderId) {
        FinancePayDetail fpdBean = financePayDetailMapper.selectByPayIdAndLocOrderId(payId, locOrderId);
        return fpdBean;
    }

    /**
     * 更新操作
     */
    @Override
    public int updateFinancePayDetail(FinancePayDetail detail) {
        int updateNum = financePayDetailMapper.update(detail);
        return updateNum;
    }

    /**
     * 点击移除时，删除单条订单信息
     */
    @Override
    public int deleteByPayIdAndOrder(Integer payId, Integer locOrderId) {
        int delNum = financePayDetailMapper.deleteByPayIdAndOrder(payId, locOrderId);
        return delNum;
    }

    /**
     * 点击删除选中时，批量删除收款订单信息
     */
    @Override
    public int batchDeleteByPayIdLocOrderId(Integer payId, Set<Integer> locOrderIdSet) {
        int batchDelNum = financePayDetailMapper.batchDeleteByPayIdLocOrderId(payId, locOrderIdSet);
        return batchDelNum;
    }

    @Override
    public int deleteByPayId(Integer payId) {//deleteByPayId
        int nums = financePayDetailMapper.deleteByPayId(payId);
        return nums;
    }
}
