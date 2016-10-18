package com.yimayhd.erpcenter.dal.product.mapper;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductRouteSupplier;

public interface ProductRouteSupplierMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByRouteId(Integer routeId);


    int insert(ProductRouteSupplier record);

    int insertSelective(ProductRouteSupplier record);

    ProductRouteSupplier selectByPrimaryKey(Integer id);

    List<ProductRouteSupplier> selectByRouteId(Integer routeId);

    int updateByPrimaryKeySelective(ProductRouteSupplier record);

    int updateByPrimaryKey(ProductRouteSupplier record);
}