package com.yimayhd.erpcenter.dal.product.dao;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductTag;

public interface ProductTagMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByProductId(Integer productId);

    int insert(ProductTag record);

    int insertSelective(ProductTag record);

    ProductTag selectByPrimaryKey(Integer id);

    List<ProductTag> selectByProductId(Integer productId);

    int updateByPrimaryKeySelective(ProductTag record);

    int updateByPrimaryKey(ProductTag record);
}