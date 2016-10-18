package com.yimayhd.erpcenter.dal.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.vo.ProductGroupSupplierVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductSupplierCondition;

public interface ProductGroupSupplierMapper {
    int deleteByPrimaryKey(@Param("id")Integer id);

    int insert(ProductGroupSupplier record);

    int insertSelective(ProductGroupSupplier record);

    ProductGroupSupplier selectByPrimaryKey(Integer groupId);
    ProductGroupSupplier selectById(@Param("id")Integer id);

    int updateByPrimaryKeySelective(ProductGroupSupplier record);

    int updateByPrimaryKey(ProductGroupSupplier record);

	List<ProductGroupSupplier> selectProductGroupSuppliers(Integer groupId);
	List<ProductGroupSupplier> selectProductGroupSupplierList(@Param("productId")Integer productId);

	List<ProductGroupSupplierVo> selectProductGroupSupplierVos(@Param("groupId")Integer groupId, @Param("priceId")Integer priceId);
	
	ProductGroupSupplierVo selectProductGroupSupplierVosToSales(@Param("groupId")Integer groupId, @Param("priceId")Integer priceId,@Param("supplierId")Integer supplierId);
	
	List<ProductGroupSupplier> selectProductGroupSupplierListPage(@Param("page")PageBean pageBean, @Param("groupId")Integer groupId);

	List<ProductGroupSupplier> selectSupplierList(ProductSupplierCondition supplier);
	
	Integer copyProduct(@Param("parameter")Map parameter);
	
	Integer copyProductSuppliers(@Param("parameter")Map parameter);
	
	void updateTimeById(@Param("id")Integer id);
	/**
	 * 根据产品id查询组团社、价格组信息
	 * @param productId
	 * @return
	 */
	List<ProductGroupSupplier> getProductPriceInfoList(@Param("productId") Integer productId);
}