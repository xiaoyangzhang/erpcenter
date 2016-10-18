package com.yimayhd.erpcenter.dal.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.product.po.ProductGroupSeller;

public interface ProductGroupSellerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductGroupSeller record);

    int insertSelective(ProductGroupSeller record);

    ProductGroupSeller selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductGroupSeller record);

    int updateByPrimaryKey(ProductGroupSeller record);

	List<ProductGroupSeller> selectGroupSellerList(@Param("bizId")Integer bizId,
			@Param("groupId")Integer groupId, @Param("productId")Integer productId);

	ProductGroupSeller selectGroupSeller(@Param("bizId")Integer bizId,
			@Param("productId")Integer productId, @Param("operateId")Integer operateId);
	
	List<ProductGroupSeller> selectGroupSellers(@Param("bizId")Integer bizId, @Param("operatorIds")String operatorIds);
	
	int deleteByGroupId(@Param("groupId")Integer groupId);
	List<ProductGroupSeller> selectSellerListByProductId(@Param("bizId")Integer bizId,
			@Param("productId")Integer productId);

	int deleteByBatch(@Param("groupId")Integer groupId, @Param("productId")Integer productId, @Param("operatorId")Integer operatorId);
	int insertByBatch(ProductGroupSeller record);
}