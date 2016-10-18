
package com.yimayhd.erpcenter.dal.product.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.dao.ProductGroupMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductGroupPriceMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductGroupSupplierMapper;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupSupplierDal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

public class ProductGroupSupplierDalImpl implements ProductGroupSupplierDal {

	@Autowired
	private ProductGroupSupplierMapper groupSupplierMapper;
	@Autowired
	private ProductGroupMapper groupMapper;
	@Autowired
	private ProductGroupPriceMapper groupPriceMapper;

	@Transactional
	@Override
	public int save(List<ProductGroupSupplier> groupSuppliers) {
		int i =0;
		for (ProductGroupSupplier productGroupSupplier : groupSuppliers) {
			productGroupSupplier.setState((byte)1);
			productGroupSupplier.setCreateTime(System.currentTimeMillis());
			i = groupSupplierMapper.insertSelective(productGroupSupplier);
		}
		return i;
	}

	@Override
	public List<ProductGroupSupplier> selectProductGroupSuppliers(
			Integer groupId) {
		
		return groupSupplierMapper.selectProductGroupSuppliers(groupId);
	}

	@Override
	public List<ProductGroupSupplierVo> selectProductGroupSupplierVos(Integer groupId, Integer priceId) {
		return groupSupplierMapper.selectProductGroupSupplierVos(groupId, priceId);
	}

	@Override
	public PageBean<ProductGroupSupplier> selectProductGroupSuppliersPage(PageBean<ProductGroupSupplier> pageBean, Integer groupId) {
		List<ProductGroupSupplier> productGroupSuppliers = groupSupplierMapper.selectProductGroupSupplierListPage(pageBean, groupId);
		pageBean.setResult(productGroupSuppliers);
		return pageBean;
	}

	@Override
	public int update(ProductGroupSupplier groupSupplier) {
		
		return groupSupplierMapper.updateByPrimaryKeySelective(groupSupplier);
	}

	@Override
	public ProductGroupSupplierVo selectProductGroupSupplierVosToSales(
			Integer groupId, Integer priceId, Integer supplierId) {
		return groupSupplierMapper.selectProductGroupSupplierVosToSales(groupId, priceId, supplierId);
	}

	@Override
	public List<ProductGroupSupplier> selectProductGroupSuppliers2(
			Integer productId) {
		return groupSupplierMapper.selectProductGroupSupplierList(productId);
	}
	
	

	@Override
	public ProductGroupSupplier selectgGroupSupplierById(Integer id) {
		return groupSupplierMapper.selectById(id);
	}

	@Override
	public int save(List<ProductInfo> productInfos, Integer productId) {		
		Map parameters=new HashMap();
		parameters.put("fromProductId", productId);
		try {
			for (ProductInfo pInfo:productInfos) {
				parameters.put("toProductId", pInfo.getId());
				groupSupplierMapper.copyProduct(parameters);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<ProductGroupSupplier> selectSupplierList(
			ProductSupplierCondition condition) {		
		return groupSupplierMapper.selectSupplierList(condition);
	}

	@Transactional
	@Override
	public int deleteByProductSupplierId(Integer productSupplierId) {
		List<ProductGroup> groupList = groupMapper.getIdListByProductSupplierId(productSupplierId);
		if(groupList!=null && groupList.size()>0){
			List<Integer> list = new ArrayList<Integer>();
			for(ProductGroup group : groupList){
				list.add(group.getId());
			}
			groupPriceMapper.deleteByGroupIdList(list);
		}
		groupMapper.deleteByProductSupplierId(productSupplierId);
		groupSupplierMapper.deleteByPrimaryKey(productSupplierId);
		return 1;
	}

	@Transactional
	@Override
	public void copyProductSuppliersToTarget(Integer toProductId,
			List<Integer> productSupplierIdList) {
		Map parameters=new HashMap();
		parameters.put("toProductId", toProductId);
		for(Integer productSupplierId : productSupplierIdList){
			parameters.put("fromProductSupplierId", productSupplierId);
			groupSupplierMapper.copyProductSuppliers(parameters);
		}
	}

	@Override
	public List<ProductGroupSupplier> getProductPriceInfoList(Integer productId) {
		return groupSupplierMapper.getProductPriceInfoList(productId);
	}

	/*@Override
	public int save(ProductGroupSupplier groupSupplier) {
		groupSupplier.setCreateTime(System.currentTimeMillis());
		groupSupplierMapper.insertSelective(groupSupplier);
		return groupSupplier.getId();
	}*/

	
	
	

}
