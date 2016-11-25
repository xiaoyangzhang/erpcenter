package com.yimayhd.erpcenter.dal.product.service;

import com.yimayhd.erpcenter.dal.product.po.ProductRemark;

/**
 * Created by ZhengZiyu on 2015/7/6.
 */
public interface ProductRemarkDal {

    boolean saveProductRemark(ProductRemark productRemark);
    
    boolean copyProductRemark(ProductRemark productRemark);
    
    ProductRemark findProductRemarkByProductId(Integer productId);
}
