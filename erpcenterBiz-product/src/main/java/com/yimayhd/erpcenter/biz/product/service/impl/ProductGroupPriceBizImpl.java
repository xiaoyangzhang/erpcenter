package com.yimayhd.erpcenter.biz.product.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.product.service.ProductGroupPriceBiz;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupPrice;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupPriceDal;
import com.yimayhd.erpcenter.dal.product.vo.PriceCopyVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductPriceVo;

public class ProductGroupPriceBizImpl implements ProductGroupPriceBiz {
	static final Logger logger = LoggerFactory.getLogger(ProductGroupPriceBizImpl.class);
	
	@Autowired
	private ProductGroupPriceDal productGroupPriceDal;
	
	@Override
	 
	public int save(ProductPriceVo priceVo) {
		return productGroupPriceDal.save(priceVo);
	}

    @Override
    public boolean delete(Integer priceId) {
        return productGroupPriceDal.delete(priceId);
    }

    @Override
	public List<ProductGroupPrice> selectProductGroupPrices(Integer groupId, String year, String month) {
		return productGroupPriceDal.selectProductGroupPrices(groupId, year, month);
	}

	

	@Override
	public ProductPriceVo selectByPrimaryKey(Integer id) {
		return productGroupPriceDal.selectByPrimaryKey(id);
	}

	/**
	 * edited by ge 20151027
	 * 如果是统一定价则supplierId传入null即可
	 * 如果是普通模式分别定价，则传入地接社SupplierId
	 */
     
    @Override
    public boolean updateStock(Integer priceId, Integer supplierId, Integer increaseCount) {
        return productGroupPriceDal.updateStock(priceId, supplierId, increaseCount);
    }
    
     
	public int copyGroupPrice(PriceCopyVo copyVo){
    	return productGroupPriceDal.copyGroupPrice(copyVo);
    }
    
	@Override
	public Integer selectProductByProduct(Map parameters) {
		return productGroupPriceDal.selectProductByProduct(parameters);
	}

	@Override
	public int save(ProductGroupPrice price) {
		return productGroupPriceDal.save(price);
	}

	@Override
	public List<Map> getMinPriceByProductIdSetAndDate(
			List<Integer> productIds, Date date) {
		return productGroupPriceDal.getMinPriceByProductIdSetAndDate(productIds, date);
	}

	@Override
	public List<ProductGroupPrice> selectPriceByGroupId(Integer groupId) {
		return productGroupPriceDal.selectPriceByGroupId(groupId);
	}

	@Override
	 
	public void batchInsertPriceGroup(Integer bizId, String json) {
		productGroupPriceDal.batchInsertPriceGroup(bizId, json);
	}
	
	@Override
	public List<ProductGroupPrice> getPriceByPidAndUserIdAndDate(Integer bizId,
			Integer productId, Integer operatorId, Date date) {
		return productGroupPriceDal.getPriceByPidAndUserIdAndDate(bizId,productId,operatorId,date);
	}

}
