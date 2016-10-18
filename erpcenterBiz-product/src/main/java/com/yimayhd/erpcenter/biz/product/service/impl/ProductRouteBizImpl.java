package com.yimayhd.erpcenter.biz.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.product.service.ProductRouteBiz;
import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
import com.yimayhd.erpcenter.dal.product.service.ProductRouteDal;
import com.yimayhd.erpcenter.dal.product.vo.ProductRouteVo;

/**
 * Created by ZhengZiyu on 2015/7/7.
 */
public class ProductRouteBizImpl implements ProductRouteBiz{

    @Autowired
    private ProductRouteDal productRouteDal;
    
    
    @Override
    public boolean saveProductRoute(ProductRouteVo productRouteVo) {
    	return productRouteDal.saveProductRoute(productRouteVo);
    }

    @Override
    public boolean editProductRoute(ProductRouteVo productRouteVo) {
        return productRouteDal.editProductRoute(productRouteVo);
    }

    @Override
    public ProductRouteVo findByProductId(Integer productId) {
    	return productRouteDal.findByProductId(productId);
    }

    @Override
    public List<ProductRoute> findProductRouteByProductId(Integer productId) {
        return productRouteDal.findProductRouteByProductId(productId);
    }
    
    @Override
    public  int saveProductRoute1(ProductRoute productRoute){
        return productRouteDal.saveProductRoute1(productRoute);
    }
}
