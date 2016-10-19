package com.yimayhd.erpcenter.facade.result;

import com.yimayhd.erpcenter.dal.product.vo.ProductRouteVo;

/**
 * 
* @ClassName: GetProductRouteResult 
* @Description: 
* @author wangjun
* @date 2016年10月18日 下午6:24:10 
*
 */

public class GetProductRouteResult extends ResultSupport{
    private static final long serialVersionUID = -1L;
    private ProductRouteVo productRouteVo;
	public ProductRouteVo getProductRouteVo() {
		return productRouteVo;
	}
	public void setProductRouteVo(ProductRouteVo productRouteVo) {
		this.productRouteVo = productRouteVo;
	}
	
    
    
    
}
