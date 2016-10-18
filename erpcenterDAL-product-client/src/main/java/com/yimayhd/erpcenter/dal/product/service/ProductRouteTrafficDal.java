package com.yimayhd.erpcenter.dal.product.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductRouteTraffic;

/**
 * Created by ZhengZiyu on 2015/7/7.
 */
public interface ProductRouteTrafficDal {

    List<ProductRouteTraffic> findByRouteId(Integer routeId);
}
