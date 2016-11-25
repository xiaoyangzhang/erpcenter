package com.yimayhd.erpcenter.dal.product.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
import com.yimayhd.erpcenter.dal.product.vo.ProductRouteVo;

/**
 * Created by ZhengZiyu on 2015/7/7.
 */
public interface ProductRouteDal {

//    List<ProductRoute> findByProductId(Integer productId);

    boolean saveProductRoute(ProductRouteVo productRouteVo);
    
    boolean copyProductRoute(ProductRouteVo productRouteVo);
    
    boolean editProductRoute(ProductRouteVo productRouteVo);

    ProductRouteVo findByProductId(Integer productId);

    List<ProductRoute> findProductRouteByProductId(Integer productId);
    
    int saveProductRoute1(ProductRoute productRoute);

}
