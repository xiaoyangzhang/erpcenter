package com.yimayhd.erpcenter.dal.product.dao;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductGroupExtraItem;

public interface ProductGroupExtraItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductGroupExtraItem record);

    int insertSelective(ProductGroupExtraItem record);

    ProductGroupExtraItem selectByPrimaryKey(Integer id);

    List<ProductGroupExtraItem> selectByGroupId(Integer groupId);

    int updateByPrimaryKeySelective(ProductGroupExtraItem record);

    int updateByPrimaryKey(ProductGroupExtraItem record);
}