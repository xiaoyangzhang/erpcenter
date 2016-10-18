package com.yimayhd.erpcenter.dal.product.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductSales;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticsResultVOPlus;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticsResultVo;

public interface ProductInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(Integer id);
    ProductInfo selectStockCount(@Param("productId")Integer productId,@Param("itemDate")String itemDate);
    ProductInfo selectBySupplierId(@Param("supplierId") Integer supplierId);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);
    
    void updateProductSysId(@Param("productId")Integer productId,@Param("productSysId")Integer productSysId);

	List<ProductInfo> selectProductInfoListPage(@Param("page") PageBean pageBean,@Param("parameter")Map parameters);
	
	/**
	 * 按创建者来过滤
	 * @param pageBean
	 * @param parameters
	 * @return
	 */
	List<ProductInfo> selectProductInfoListPage2(@Param("page") PageBean pageBean,@Param("parameter")Map parameters);
	
	List<ProductInfo> selectProductAndPriceListPage(@Param("page") PageBean pageBean);
	/**
	 * 销售人员选择行程
	 * @param pageBean
	 * @param parameters
	 * @return
	 */
	List<ProductInfo> selectProductRouteListPage(@Param("page") PageBean pageBean,@Param("parameter")Map parameters);
	
	List<ProductInfo> selectStockProductListPage(@Param("page") PageBean pageBean,@Param("parameter")Map parameters);
			//@Param("bizId") Integer bizId,@Param("name") String name, @Param("productName")String productName,@Param("orgId")Integer orgId);	
	
	int getBizAndBrandCodeCount(@Param("bizId")Integer bizId , @Param("brandId")Integer brandId);

	List<ProductSales> selectProductSalesListPage(@Param("page")PageBean<ProductSales> pageBean, @Param("bizId")Integer bizId, @Param("orgId")Integer orgId);
	
	List<ProductSales> selectProductSalesPlusListPage(@Param("page")PageBean<ProductSales> pageBean, @Param("bizId")Integer bizId, @Param("orgId")Integer orgId);

	List<StockStaticsResultVo> getProductBreifListPage(@Param("page")PageBean pageBean);
	List<StockStaticsResultVOPlus> getProductStockListPage(@Param("page")PageBean pageBean);

	Integer getCurBizCountByProductIdAndCode(@Param("bizId")Integer bizId, @Param("productId")Integer productId,
			@Param("code")String code);
	
	List<Map<String,Object>> getAllIdByBizAndState(@Param("bizId")Integer bizId, @Param("state")Integer state);
	List<ProductInfo> searchProductByNameAndDate(@Param("bizId")Integer bizId, @Param("prodKeyword")String prodKeyword, @Param("dateStart")String dateStart);
	ProductInfo selectByIdAndBizId(@Param("id")Integer id,@Param("bizId")Integer bizId);
	
	int fix_SupplierName_productGroupSupplier(@Param("supplierId")Integer supplierId, @Param("supplierName")String supplierName);
	
	
	ProductInfo selectProductInfoByPsId(@Param("productSysId")Integer productSysId);
	
}