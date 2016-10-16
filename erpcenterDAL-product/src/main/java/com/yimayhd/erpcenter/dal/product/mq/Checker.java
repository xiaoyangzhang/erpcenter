package com.yimayhd.erpcenter.dal.product.mq;

import com.alibaba.rocketmq.client.producer.LocalTransactionState;

public interface Checker {
    /**
     * 是否需要处理
     * @param topic
     * @param tag
     * @return
     */
    boolean in(String topic, String tag);

    /**
     * check事务消息处理好了没
     * @param msg
     * @return
     */
    LocalTransactionState check(Object msg);
}
