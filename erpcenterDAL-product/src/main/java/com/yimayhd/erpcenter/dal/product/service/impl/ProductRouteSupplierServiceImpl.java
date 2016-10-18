package com.yihg.product.impl;

import com.yihg.product.api.ProductRouteSupplierService;
import com.yihg.product.dao.ProductRouteSupplierMapper;
import com.yihg.product.po.ProductRouteSupplier;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ZhengZiyu on 2015/7/8.
 */
public class ProductRouteSupplierServiceImpl implements ProductRouteSupplierService {

    @Autowired
    private ProductRouteSupplierMapper productRouteSupplierMapper;

    @Override
    public List<ProductRouteSupplier> findByRouteId(Integer routeId) {
        return productRouteSupplierMapper.selectByRouteId(routeId);
    }
}
