package com.yimayhd.erpcenter.dal.sales.taobao.dao;


import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.sales.client.taobao.po.PlatTaobaoTradeOrder;

import java.util.List;

public interface PlatTaobaoTradeOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlatTaobaoTradeOrder record);

    int insertSelective(PlatTaobaoTradeOrder record);

    PlatTaobaoTradeOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlatTaobaoTradeOrder record);

    int updateByPrimaryKey(PlatTaobaoTradeOrder record);

    PlatTaobaoTradeOrder selectByTid(@Param("tid") String tid, @Param("oid") String oid);

    List<PlatTaobaoTradeOrder> selectByOrderId(@Param("orderId") Integer orderId);
}