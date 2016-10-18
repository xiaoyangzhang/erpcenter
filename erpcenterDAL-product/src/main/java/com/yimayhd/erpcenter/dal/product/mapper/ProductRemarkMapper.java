package com.yimayhd.erpcenter.dal.product.mapper;

import com.yimayhd.erpcenter.dal.product.po.ProductRemark;

public interface ProductRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductRemark record);

    int insertSelective(ProductRemark record);

    ProductRemark selectByPrimaryKey(Integer id);

    ProductRemark selectByProductId(Integer productId);

    int updateByPrimaryKeySelective(ProductRemark record);

    int updateByPrimaryKey(ProductRemark record);

	ProductRemark selectBriefByProductId(Integer productId);
}