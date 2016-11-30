package com.yimayhd.erpcenter.dal.product.dao;

import com.yimayhd.erpcenter.dal.product.po.TaobaoStockLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaobaoStockLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaobaoStockLog record);

    int insertSelective(TaobaoStockLog record);

    TaobaoStockLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaobaoStockLog record);

    int updateByPrimaryKey(TaobaoStockLog record);
    
    List<TaobaoStockLog> selectByStockId(Integer stockId);
    
    
    List<TaobaoStockLog> selectByStockDateIdAndCreateTime(@Param("stockDateId") Integer stockDateId, @Param("startMax") String startMax, @Param("startMin") String startMin);
    
    TaobaoStockLog selectLogNumByOrderId(Integer stockId);
    TaobaoStockLog selectByStockDateIdAndOrderId(@Param("stockDateId") Integer stockDateId, @Param("orderId") Integer orderId);
    
    TaobaoStockLog selectByStockDateIdAndTaobaoOrderId(@Param("stockDateId") Integer stockDateId, @Param("taobaoOrderId") Integer taobaoOrderId);
}