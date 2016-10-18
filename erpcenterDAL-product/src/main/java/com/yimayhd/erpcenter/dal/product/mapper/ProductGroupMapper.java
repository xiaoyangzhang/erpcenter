package com.yimayhd.erpcenter.dal.product.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.vo.ProductGroupVo;

public interface ProductGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductGroup record);

    int insertSelective(ProductGroup record);

    ProductGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductGroup record);

    int updateByPrimaryKey(ProductGroup record);
    
    List<ProductGroup> selectProductGroups(Integer productId);
    List<ProductGroup> selectProductGroupList(@Param("groupSupplierId")Integer groupSupplierId);
    List<ProductGroupVo> selectGroupsByProductIdAndSupplierIdAndDate(@Param("supplierId")Integer supplierId,@Param("productId")Integer productId,@Param("groupDate")Date groupDate);

	int validateName(@Param("name")String name, @Param("productId")Integer productId,@Param("id")Integer id);
	int updateStateBySupplierId(@Param("groupSupplierId")Integer groupSupplierId );
	
	List<ProductGroup> getIdListByProductSupplierId(@Param("groupSupplierId")Integer productSupplierId);
	int deleteByProductSupplierId(@Param("groupSupplierId")Integer productSupplierId);
	
	List<ProductGroup> selectProductGroupsBySellerId(@Param("productId")Integer productId,@Param("sellerId")Integer sellerId);
	
}