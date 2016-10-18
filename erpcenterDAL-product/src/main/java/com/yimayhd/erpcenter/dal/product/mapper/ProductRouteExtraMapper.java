package com.yimayhd.erpcenter.dal.product.mapper;

import com.yimayhd.erpcenter.dal.product.po.ProductRouteExtra;

public interface ProductRouteExtraMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductRouteExtra record);

    int insertSelective(ProductRouteExtra record);

    ProductRouteExtra selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductRouteExtra record);

    int updateByPrimaryKey(ProductRouteExtra record);
}