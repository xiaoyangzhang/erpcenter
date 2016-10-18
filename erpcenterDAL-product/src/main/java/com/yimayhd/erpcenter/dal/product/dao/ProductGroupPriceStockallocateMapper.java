package com.yimayhd.erpcenter.dal.product.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.product.po.ProductGroupPriceStockallocate;

public interface ProductGroupPriceStockallocateMapper {
    int deleteByPrimaryKey(Integer priceId);

    int insert(ProductGroupPriceStockallocate record);

    int insertSelective(ProductGroupPriceStockallocate record);

    List<ProductGroupPriceStockallocate> selectByPrimaryKey(Integer priceId);

    int updateStockByPriceIdAndSupplierId(@Param("priceId")Integer priceId, @Param("supplierId")Integer supplierId, @Param("decreaseStock")Integer decreaseStock);

    int updateByPrimaryKeySelective(ProductGroupPriceStockallocate record);

    int updateByPrimaryKey(ProductGroupPriceStockallocate record);
}