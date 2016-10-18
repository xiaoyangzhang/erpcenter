package com.yimayhd.erpcenter.dal.product.mapper;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductRouteTraffic;

public interface ProductRouteTrafficMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByRouteId(Integer routeId);

    int insert(ProductRouteTraffic record);

    int insertSelective(ProductRouteTraffic record);

    ProductRouteTraffic selectByPrimaryKey(Integer id);

    List<ProductRouteTraffic> selectByRouteId(Integer routeId);

    int updateByPrimaryKeySelective(ProductRouteTraffic record);

    int updateByPrimaryKey(ProductRouteTraffic record);
}