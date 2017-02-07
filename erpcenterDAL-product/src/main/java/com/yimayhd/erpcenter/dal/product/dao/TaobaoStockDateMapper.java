package com.yimayhd.erpcenter.dal.product.dao;

import com.yimayhd.erpcenter.dal.product.po.TaobaoStockDate;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TaobaoStockDateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaobaoStockDate record);

    int insertSelective(TaobaoStockDate record);

    TaobaoStockDate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaobaoStockDate record);

    int updateByPrimaryKey(TaobaoStockDate record);
    
    List<TaobaoStockDate> selectTaobaoStocksByProductIdAndDate(@Param("stockId") Integer stockId,@Param("startDate")Date startDate,@Param("endDate")Date endDate);
    
     int updateByLog(Integer stockDateId);
     
     TaobaoStockDate selectTaobaoStocksByNumIidAndStockDate(@Param("sku") String sku,@Param("stockDate")Date stockDate,@Param("numIid") String numIid);
     
     int updateRemark(TaobaoStockDate record);
     
}