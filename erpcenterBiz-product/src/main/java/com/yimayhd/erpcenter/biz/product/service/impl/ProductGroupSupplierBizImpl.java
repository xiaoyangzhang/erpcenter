package com.yimayhd.erpcenter.biz.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupSupplierBiz;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupSupplierDal;
import com.yimayhd.erpcenter.dal.product.vo.ProductGroupSupplierVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductSupplierCondition;

public class ProductGroupSupplierBizImpl implements ProductGroupSupplierBiz {

	@Autowired
	private ProductGroupSupplierDal productGroupSupplierDal;

	 
	@Override
	public int save(List<ProductGroupSupplier> groupSuppliers) {
		return productGroupSupplierDal.save(groupSuppliers);
	}

	@Override
	public List<ProductGroupSupplier> selectProductGroupSuppliers(
			Integer groupId) {
		return productGroupSupplierDal.selectProductGroupSuppliers(groupId);
	}

	@Override
	public List<ProductGroupSupplierVo> selectProductGroupSupplierVos(Integer groupId, Integer priceId) {
		return productGroupSupplierDal.selectProductGroupSupplierVos(groupId, priceId);
	}

	@Override
	public PageBean<ProductGroupSupplier> selectProductGroupSuppliersPage(PageBean<ProductGroupSupplier> pageBean, Integer groupId) {
		return productGroupSupplierDal.selectProductGroupSuppliersPage(pageBean, groupId);
	}

	@Override
	public int update(ProductGroupSupplier groupSupplier) {
		return productGroupSupplierDal.update(groupSupplier);
	}

	@Override
	public ProductGroupSupplierVo selectProductGroupSupplierVosToSales(
			Integer groupId, Integer priceId, Integer supplierId) {
		return productGroupSupplierDal.selectProductGroupSupplierVosToSales(groupId, priceId, supplierId);
	}

	@Override
	public List<ProductGroupSupplier> selectProductGroupSuppliers2(
			Integer productId) {
		return productGroupSupplierDal.selectProductGroupSuppliers2(productId);
	}
	
	

	@Override
	public ProductGroupSupplier selectgGroupSupplierById(Integer id) {
		return productGroupSupplierDal.selectgGroupSupplierById(id);
	}

	@Override
	public int save(List<ProductInfo> productInfos, Integer productId) {
		return productGroupSupplierDal.save(productInfos, productId);
	}

	@Override
	public List<ProductGroupSupplier> selectSupplierList(
			ProductSupplierCondition condition) {		
		return productGroupSupplierDal.selectSupplierList(condition);
	}

	 
	@Override
	public int deleteByProductSupplierId(Integer productSupplierId) {
		return productGroupSupplierDal.deleteByProductSupplierId(productSupplierId);
	}

	 
	@Override
	public void copyProductSuppliersToTarget(Integer toProductId,
			List<Integer> productSupplierIdList) {
		productGroupSupplierDal.copyProductSuppliersToTarget(toProductId, productSupplierIdList);
	}

	@Override
	public List<ProductGroupSupplier> getProductPriceInfoList(Integer productId) {
		return productGroupSupplierDal.getProductPriceInfoList(productId);
	}

}
