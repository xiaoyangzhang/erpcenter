package com.yimayhd.erpcenter.dal.product.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductGroupExtraItem;

/**
 * Created by ZhengZiyu on 2015/7/18.
 */
public interface ProductGroupExtraItemDal {
    ProductGroupExtraItem findById(Integer id);

    ProductGroupExtraItem save(ProductGroupExtraItem productGroupExtraItem);

    ProductGroupExtraItem edit(ProductGroupExtraItem productGroupExtraItem);

    boolean deleteById(Integer id);

    List<ProductGroupExtraItem> findByGroupId(Integer groupId);
}
