package com.yimayhd.erpcenter.facade.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.product.vo.ProductTagVo;

public class ProductTagDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 产品标签类
	 */
	private ProductTagVo productTagVo;

	public ProductTagVo getProductTagVo() {
		return productTagVo;
	}

	public void setProductTagVo(ProductTagVo productTagVo) {
		this.productTagVo = productTagVo;
	}
	
	
}
