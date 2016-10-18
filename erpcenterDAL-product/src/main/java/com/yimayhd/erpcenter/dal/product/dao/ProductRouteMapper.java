package com.yimayhd.erpcenter.dal.product.dao;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductRoute;

public interface ProductRouteMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByProductId(Integer productId);

    int insert(ProductRoute record);

    int insertSelective(ProductRoute record);

    ProductRoute selectByPrimaryKey(Integer id);

    List<ProductRoute> selectByProductId(Integer productId);

    int updateByPrimaryKeySelective(ProductRoute record);

    int updateByPrimaryKey(ProductRoute record);
}