/**
 * 
 */
package com.yimayhd.erpcenter.facade.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductRight;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;

/**
 * @ClassName: ProductDataRightResult
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月19日
 */
public class ProductDataRightResult extends ResultSupport implements
		Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -5895100754940754874L;

	private List<PlatformOrgPo> orgList;
	
	private List<ProductRight> productRights;

	public List<PlatformOrgPo> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<PlatformOrgPo> orgList) {
		this.orgList = orgList;
	}

	public List<ProductRight> getProductRights() {
		return productRights;
	}

	public void setProductRights(List<ProductRight> productRights) {
		this.productRights = productRights;
	}
	
	
}
