package com.yimayhd.erpcenter.dal.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.product.mapper.ProductRouteSupplierMapper;
import com.yimayhd.erpcenter.dal.product.po.ProductRouteSupplier;
import com.yimayhd.erpcenter.dal.product.service.ProductRouteSupplierDal;

import java.util.List;

/**
 * Created by ZhengZiyu on 2015/7/8.
 */
public class ProductRouteSupplierDalImpl implements ProductRouteSupplierDal {

    @Autowired
    private ProductRouteSupplierMapper productRouteSupplierMapper;

    @Override
    public List<ProductRouteSupplier> findByRouteId(Integer routeId) {
        return productRouteSupplierMapper.selectByRouteId(routeId);
    }
}
