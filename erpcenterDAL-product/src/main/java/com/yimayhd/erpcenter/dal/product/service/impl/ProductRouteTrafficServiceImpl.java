package com.yihg.product.impl;

import com.yihg.product.api.ProductRouteTrafficService;
import com.yihg.product.dao.ProductRouteTrafficMapper;
import com.yihg.product.po.ProductRouteTraffic;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ZhengZiyu on 2015/7/7.
 */
public class ProductRouteTrafficServiceImpl implements ProductRouteTrafficService {

    @Autowired
    private ProductRouteTrafficMapper productRouteTrafficMapper;

    @Override
    public List<ProductRouteTraffic> findByRouteId(Integer routeId) {
        return productRouteTrafficMapper.selectByRouteId(routeId);
    }
}
