package com.yimayhd.erpcenter.biz.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.product.service.ProductRouteSupplierBiz;
import com.yimayhd.erpcenter.dal.product.po.ProductRouteSupplier;
import com.yimayhd.erpcenter.dal.product.service.ProductRouteSupplierDal;

import java.util.List;

/**
 * Created by ZhengZiyu on 2015/7/8.
 */
public class ProductRouteSupplierBizImpl implements ProductRouteSupplierBiz {

    @Autowired
    private ProductRouteSupplierDal productRouteSupplierDal;;

    @Override
    public List<ProductRouteSupplier> findByRouteId(Integer routeId) {
        return productRouteSupplierDal.findByRouteId(routeId);
    }
}
