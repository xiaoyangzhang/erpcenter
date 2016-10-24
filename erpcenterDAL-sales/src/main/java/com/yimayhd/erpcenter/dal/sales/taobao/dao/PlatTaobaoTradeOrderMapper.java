package com.yihg.taobao.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihg.taobao.po.PlatTaobaoTradeOrder;

public interface PlatTaobaoTradeOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlatTaobaoTradeOrder record);

    int insertSelective(PlatTaobaoTradeOrder record);

    PlatTaobaoTradeOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlatTaobaoTradeOrder record);

    int updateByPrimaryKey(PlatTaobaoTradeOrder record);
    
    List<PlatTaobaoTradeOrder> selectByTid(@Param("tid") String tid);
}