package com.yimayhd.erpcenter.dal.product.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductRouteSupplier;

/**
 * Created by ZhengZiyu on 2015/7/8.
 */
public interface ProductRouteSupplierDal {

    List<ProductRouteSupplier> findByRouteId(Integer routeId);
}
