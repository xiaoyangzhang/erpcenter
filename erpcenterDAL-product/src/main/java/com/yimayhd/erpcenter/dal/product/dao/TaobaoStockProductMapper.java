package com.yimayhd.erpcenter.dal.product.dao;


import com.yimayhd.erpcenter.dal.product.po.TaobaoStockProduct;

import java.util.List;

public interface TaobaoStockProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaobaoStockProduct record);

    int insertSelective(TaobaoStockProduct record);

    TaobaoStockProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaobaoStockProduct record);

    int updateByPrimaryKey(TaobaoStockProduct record);
    
    List<TaobaoStockProduct> findStockProductStockId(Integer stockId);
    
    int deleteTaoBaoStockProduct(TaobaoStockProduct record);
    
    TaobaoStockProduct findStockProductInfo(TaobaoStockProduct record);
}