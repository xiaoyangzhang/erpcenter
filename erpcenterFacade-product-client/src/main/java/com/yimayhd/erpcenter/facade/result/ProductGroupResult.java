package com.yimayhd.erpcenter.facade.result;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.vo.ProductPriceVo;

public class ProductGroupResult implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	private List<ProductGroup> productGroups;
	private ProductInfo productInfo;
	private PageBean pageBean;
	private Map<Integer,String> map;
	private List<ProductGroupSupplier> groupSuppliers;
	private ProductPriceVo priceVo;
	
	public ProductPriceVo getPriceVo() {
		return priceVo;
	}
	public void setPriceVo(ProductPriceVo priceVo) {
		this.priceVo = priceVo;
	}
	public List<ProductGroupSupplier> getGroupSuppliers() {
		return groupSuppliers;
	}
	public void setGroupSuppliers(List<ProductGroupSupplier> groupSuppliers) {
		this.groupSuppliers = groupSuppliers;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public Map<Integer,String> getMap() {
		return map;
	}
	public void setMap(Map<Integer,String> map) {
		this.map = map;
	}
	public List<ProductGroup> getProductGroups() {
		return productGroups;
	}
	public void setProductGroups(List<ProductGroup> productGroups) {
		this.productGroups = productGroups;
	}
	public ProductInfo getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
	
}
