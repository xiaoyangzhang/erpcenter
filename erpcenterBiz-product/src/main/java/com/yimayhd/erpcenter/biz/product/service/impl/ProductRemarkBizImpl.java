package com.yimayhd.erpcenter.biz.product.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yimayhd.erpcenter.biz.product.service.ProductRemarkBiz;
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.service.ProductRemarkDal;

/**
 * Created by ZhengZiyu on 2015/7/6.
 */
public class ProductRemarkBizImpl implements ProductRemarkBiz{

    @Autowired
    private ProductRemarkDal productRemarkDal;

    @Transactional
    @Override
    public boolean saveProductRemark(ProductRemark productRemark) {
    	return productRemarkDal.saveProductRemark(productRemark);
    }

    @Override
    public ProductRemark findProductRemarkByProductId(Integer productId) {
        return productRemarkDal.findProductRemarkByProductId(productId);
    }
}
