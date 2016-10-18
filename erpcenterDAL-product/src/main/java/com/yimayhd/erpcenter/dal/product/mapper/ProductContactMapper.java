package com.yimayhd.erpcenter.dal.product.mapper;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductContact;

public interface ProductContactMapper {
    int deleteByproductId(Integer id);

    int insert(ProductContact record);

    int insertSelective(ProductContact record);

    ProductContact selectByPrimaryKey(Integer id);
    /**
     * 查询负责人
     * @param productId 产品id
     * @return
     */
    List<ProductContact> selectByConList(Integer productId);
    int updateByPrimaryKeySelective(ProductContact record);

    int updateByPrimaryKey(ProductContact record);
}