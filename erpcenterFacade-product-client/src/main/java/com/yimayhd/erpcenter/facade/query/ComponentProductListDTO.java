/**
 * 
 */
package com.yimayhd.erpcenter.facade.query;

import java.io.Serializable;
import java.util.Set;

import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.facade.BaseListPage;

/**
 * @ClassName: ProductListQuery
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author hongfei.guo
 * @date 2016年10月18日
 */
public class ComponentProductListDTO extends BaseListPage implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -8777986428324383209L;
	
	private ProductInfo productInfo;
	private String productName; 
	private String name;
	private Integer orgId;
	private String priceMode;
	private Set<Integer> set;
	
	public Set<Integer> getSet() {
		return set;
	}
	public void setSet(Set<Integer> set) {
		this.set = set;
	}
	public String getPriceMode() {
		return priceMode;
	}
	public void setPriceMode(String priceMode) {
		this.priceMode = priceMode;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public ProductInfo getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
