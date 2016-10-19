/**
 * 
 */
package com.yimayhd.erpcenter.facade.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;

/**
 * @ClassName: WebResult
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author hongfei.guo
 * @date 2016年10月18日
 */
public class ToSupplierListResult extends ResultSupport {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 7546504127191622861L;

	private String productName;
	private List<ProductGroupSupplier> groupSuppliers;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<ProductGroupSupplier> getGroupSuppliers() {
		return groupSuppliers;
	}

	public void setGroupSuppliers(List<ProductGroupSupplier> groupSuppliers) {
		this.groupSuppliers = groupSuppliers;
	}

}
