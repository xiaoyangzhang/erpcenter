package com.yimayhd.erpcenter.biz.product.service;

import com.yimayhd.erpcenter.dal.product.po.ProductRemark;


/**
 * Created by ZhengZiyu on 2015/7/6.
 */
public interface ProductRemarkBiz {

    boolean saveProductRemark(ProductRemark productRemark);
    
    boolean copyProductRemark(ProductRemark productRemark);
    
    ProductRemark findProductRemarkByProductId(Integer productId);
}
