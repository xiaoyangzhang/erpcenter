package com.yimayhd.erpcenter.dal.product.vo;

import java.io.Serializable;
import java.security.acl.Group;
import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupPrice;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupPriceStockallocate;


public class ProductPriceVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2033553525029281748L;
	private ProductGroupPrice groupPrice;
	private List<ProductGroupPriceStockallocate> priceStockallocate;
	private List<ProductGroup> productGroups;
	
	public List<ProductGroup> getProductGroups() {
		return productGroups;
	}
	public void setProductGroups(List<ProductGroup> productGroups) {
		this.productGroups = productGroups;
	}
	public ProductGroupPrice getGroupPrice() {
		return groupPrice;
	}
	public void setGroupPrice(ProductGroupPrice groupPrice) {
		this.groupPrice = groupPrice;
	}
	public List<ProductGroupPriceStockallocate> getPriceStockallocate() {
		return priceStockallocate;
	}
	public void setPriceStockallocate(
			List<ProductGroupPriceStockallocate> priceStockallocate) {
		this.priceStockallocate = priceStockallocate;
	}
	
	
	
}
