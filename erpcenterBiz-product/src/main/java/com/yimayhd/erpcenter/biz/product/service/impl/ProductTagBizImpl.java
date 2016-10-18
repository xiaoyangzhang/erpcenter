package com.yimayhd.erpcenter.biz.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yimayhd.erpcenter.biz.product.service.ProductTagBiz;
import com.yimayhd.erpcenter.dal.product.service.ProductTagDal;
import com.yimayhd.erpcenter.dal.product.vo.ProductTagVo;

/**
 * Created by ZhengZiyu on 2015/7/6.
 */
public class ProductTagBizImpl implements ProductTagBiz {

    @Autowired
    private ProductTagDal productTagDal;

    @Transactional
    @Override
    public boolean saveProductTags(ProductTagVo productTagVo) {
    	return productTagDal.saveProductTags(productTagVo);
    }

    @Override
    public ProductTagVo findProductTagsByProductId(Integer productId) {
        return productTagDal.findProductTagsByProductId(productId);
    }
}
