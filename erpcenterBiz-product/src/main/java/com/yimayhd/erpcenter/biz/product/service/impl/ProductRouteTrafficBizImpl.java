package com.yimayhd.erpcenter.biz.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.product.service.ProductRouteTrafficBiz;
import com.yimayhd.erpcenter.dal.product.po.ProductRouteTraffic;
import com.yimayhd.erpcenter.dal.product.service.ProductRouteTrafficDal;

import java.util.List;

/**
 * Created by ZhengZiyu on 2015/7/7.
 */
public class ProductRouteTrafficBizImpl implements ProductRouteTrafficBiz {

    @Autowired
    private ProductRouteTrafficDal productRouteTrafficDal;

    @Override
    public List<ProductRouteTraffic> findByRouteId(Integer routeId) {
        return productRouteTrafficDal.findByRouteId(routeId);
    }
}
