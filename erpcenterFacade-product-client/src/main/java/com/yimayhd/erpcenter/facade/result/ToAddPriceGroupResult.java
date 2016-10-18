/**
 * 
 */
package com.yimayhd.erpcenter.facade.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupPrice;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;

/**
 * @ClassName: WebResult
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author hongfei.guo
 * @date 2016年10月18日
 */
public class ToAddPriceGroupResult extends ResultSupport {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 7546504127191622861L;

	private List<ProductGroup> productGroups;
	private Integer productId;
	private Integer groupSupplierId;
	private String brandName;
	private String productName;
	private String supplierName;
	private Integer supplierId;
	
	public List<ProductGroup> getProductGroups() {
		return productGroups;
	}
	public void setProductGroups(List<ProductGroup> productGroups) {
		this.productGroups = productGroups;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getGroupSupplierId() {
		return groupSupplierId;
	}
	public void setGroupSupplierId(Integer groupSupplierId) {
		this.groupSupplierId = groupSupplierId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
}
