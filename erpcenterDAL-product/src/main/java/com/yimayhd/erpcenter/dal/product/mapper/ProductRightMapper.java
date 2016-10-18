package com.yimayhd.erpcenter.dal.product.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.product.po.ProductRight;

public interface ProductRightMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductRight record);

    int insertSelective(ProductRight record);

    ProductRight selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductRight record);

    int updateByPrimaryKey(ProductRight record);
    
    int deleteByProductId(Integer productId);
    
    int insertBatch(@Param("productId")Integer productId,@Param("list")Set<Integer> orgIdSet);
    
    List<ProductRight> selectRightListByProductId(Integer productId);
}