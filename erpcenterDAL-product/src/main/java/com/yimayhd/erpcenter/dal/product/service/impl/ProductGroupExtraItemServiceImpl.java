package com.yimayhd.erpcenter.dal.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.product.po.ProductGroupExtraItem;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupExtraItemDal;

import java.util.List;

/**
 * Created by ZhengZiyu on 2015/7/18.
 */
public class ProductGroupExtraItemDalImpl implements ProductGroupExtraItemDal {

    @Autowired
    private ProductGroupExtraItemMapper productGroupExtraItemMapper;

    @Override
    public ProductGroupExtraItem findById(Integer id) {
        return productGroupExtraItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public ProductGroupExtraItem save(ProductGroupExtraItem productGroupExtraItem) {
        productGroupExtraItem.setCreateTime(System.currentTimeMillis());
        productGroupExtraItemMapper.insert(productGroupExtraItem);
        return productGroupExtraItem;
    }

    @Override
    public ProductGroupExtraItem edit(ProductGroupExtraItem productGroupExtraItem) {
        productGroupExtraItemMapper.updateByPrimaryKeySelective(productGroupExtraItem);
        return productGroupExtraItem;
    }

    @Override
    public boolean deleteById(Integer id) {
        return productGroupExtraItemMapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public List<ProductGroupExtraItem> findByGroupId(Integer groupId) {
        return productGroupExtraItemMapper.selectByGroupId(groupId);
    }
}
