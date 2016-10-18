package com.yimayhd.erpcenter.biz.product.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.vo.ProductGroupSupplierVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductSupplierCondition;

/**
 * @author : xuzejun
 * @date : 2015年7月2日 下午3:58:56
 * @Description: 价格组接口
 */
public interface ProductGroupSupplierBiz {
	/**
	 * 插入
	 * @param record
	 * @return
	 */
	int save(List<ProductGroupSupplier> groupSuppliers);
	//int save(ProductGroupSupplier groupSuppliers);
	int save(List<ProductInfo> productInfos,Integer productId);

	int update(ProductGroupSupplier groupSupplier);
	List<ProductGroupSupplier> selectProductGroupSuppliers(Integer groupId);
	List<ProductGroupSupplier> selectProductGroupSuppliers2(Integer productId);
	List<ProductGroupSupplierVo> selectProductGroupSupplierVos(Integer groupId, Integer priceId);
	ProductGroupSupplierVo selectProductGroupSupplierVosToSales(Integer groupId, Integer priceId,Integer supplierId);
	PageBean<ProductGroupSupplier> selectProductGroupSuppliersPage(PageBean<ProductGroupSupplier> pageBean, Integer groupId);
	ProductGroupSupplier selectgGroupSupplierById(Integer id);
	
	List<ProductGroupSupplier> selectSupplierList(ProductSupplierCondition supplier);
	
	int deleteByProductSupplierId(Integer productSupplierId);
	void copyProductSuppliersToTarget(Integer toProductId,
			List<Integer> productSupplierIdList); 
	/**
	 * 根据产品id查询组团社和价格组信息
	 * @param productId
	 * @return
	 */
	List<ProductGroupSupplier> getProductPriceInfoList(Integer productId);
}
