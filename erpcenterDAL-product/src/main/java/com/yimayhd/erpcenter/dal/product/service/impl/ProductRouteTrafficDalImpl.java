package com.yimayhd.erpcenter.dal.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.product.dao.ProductRouteTrafficMapper;
import com.yimayhd.erpcenter.dal.product.po.ProductRouteTraffic;
import com.yimayhd.erpcenter.dal.product.service.ProductRouteTrafficDal;

import java.util.List;

/**
 * Created by ZhengZiyu on 2015/7/7.
 */
public class ProductRouteTrafficDalImpl implements ProductRouteTrafficDal{

    @Autowired
    private ProductRouteTrafficMapper productRouteTrafficMapper;

    @Override
    public List<ProductRouteTraffic> findByRouteId(Integer routeId) {
        return productRouteTrafficMapper.selectByRouteId(routeId);
    }
}
