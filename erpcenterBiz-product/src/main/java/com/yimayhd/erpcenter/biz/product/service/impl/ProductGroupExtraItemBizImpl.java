package com.yimayhd.erpcenter.biz.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.product.service.ProductGroupExtraItemBiz;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupExtraItem;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupExtraItemDal;

import java.util.List;

/**
 * Created by ZhengZiyu on 2015/7/18.
 */
public class ProductGroupExtraItemBizImpl implements ProductGroupExtraItemBiz {

    @Autowired
    private ProductGroupExtraItemDal productGroupExtraItemDal;

    @Override
    public ProductGroupExtraItem findById(Integer id) {
        return productGroupExtraItemDal.findById(id);
    }

    @Override
    public ProductGroupExtraItem save(ProductGroupExtraItem productGroupExtraItem) {
    	return productGroupExtraItemDal.save(productGroupExtraItem);
    }

    @Override
    public ProductGroupExtraItem edit(ProductGroupExtraItem productGroupExtraItem) {
    	return productGroupExtraItemDal.edit(productGroupExtraItem);
    }

    @Override
    public boolean deleteById(Integer id) {
        return productGroupExtraItemDal.deleteById(id);
    }

    @Override
    public List<ProductGroupExtraItem> findByGroupId(Integer groupId) {
        return productGroupExtraItemDal.findByGroupId(groupId);
    }
}
