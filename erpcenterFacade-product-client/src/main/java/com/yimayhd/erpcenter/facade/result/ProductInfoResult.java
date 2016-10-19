/**
 * 
 */
package com.yimayhd.erpcenter.facade.result;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductRouteVo;

/**
 * @ClassName: ProductInfoResult
 * @Description: 产品列表/预览
 * @author zhangxiaoyang
 * @date 2016年10月19日
 */
public class ProductInfoResult extends ResultSupport implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 6636674666861206380L;
	
	private ProductInfoVo productInfoVo;
	
	private ProductRouteVo productRouteVo;
	
	private ProductRemark productRemark;

	public ProductInfoVo getProductInfoVo() {
		return productInfoVo;
	}

	public void setProductInfoVo(ProductInfoVo productInfoVo) {
		this.productInfoVo = productInfoVo;
	}

	public ProductRouteVo getProductRouteVo() {
		return productRouteVo;
	}

	public void setProductRouteVo(ProductRouteVo productRouteVo) {
		this.productRouteVo = productRouteVo;
	}

	public ProductRemark getProductRemark() {
		return productRemark;
	}

	public void setProductRemark(ProductRemark productRemark) {
		this.productRemark = productRemark;
	}
	
	
}
