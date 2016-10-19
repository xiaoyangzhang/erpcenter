package com.yimayhd.erpcenter.facade.result;

import com.yimayhd.erpcenter.dal.product.po.ProductRemark;

/**
 * 
* @ClassName: ToProductRemarkResult 
* @Description: 
* @author wangjun
* @date 2016年10月19日 上午11:05:38 
*
 */

public class ToProductRemarkResult extends ResultSupport{
    private static final long serialVersionUID = -1L;
    
    private ProductRemark productRemark;

	public ProductRemark getProductRemark() {
		return productRemark;
	}

	public void setProductRemark(ProductRemark productRemark) {
		this.productRemark = productRemark;
	}
    
    
}
