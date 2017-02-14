package com.yimayhd.erpcenter.biz.sales.client.service.finance;

import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePayDetail;

import java.util.Set;

/**
 * Created by zhangxy on 2017/2/14.
 */
public interface FinancePayDetailBiz {
    /**
     * 插入操作
     * @param record
     * @return
     */
    int insertFinancePayDetail(FinancePayDetail record);

    /**
     * 根据pay_id和订单ID进行查询
     * @param payId
     * @param locOrderId
     * @return
     */
    FinancePayDetail selectByPayIdAndLocOrderId(Integer payId, Integer locOrderId);

    /**
     * 更新操作
     * @param detail
     * @return
     */
    int updateFinancePayDetail(FinancePayDetail detail);

    /**
     * 点击移除时，删除单条订单信息
     * @param payId
     * @param locOrderId
     * @return
     */
    int deleteByPayIdAndOrder(Integer payId,Integer locOrderId);

    /**
     * 点击删除选中时，批量删除收款订单信息
     * @param payId
     * @param locOrderIdSet
     * @return
     */
    int batchDeleteByPayIdLocOrderId(Integer payId,Set<Integer> locOrderIdSet);

    int deleteByPayId(Integer payId);
}
