package com.yimayhd.erpcenter.facade.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductRouteVo;


public class ProductSaveDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ProductInfoVo productInfoVo;
	
	private ProductRouteVo productRouteVo;
	
	/**
	 * 商家编码
	 */
	private String bizCode;
	/**
	 * 产品品牌编码
	 */
	private String brandCode;
	/**
	 * 创建者id
	 */
	private int createId;
	/**
	 * 创建者name
	 */
	private String createName;
	
	private int bizId;
	
	public String getBizCode() {
		return bizCode;
	}
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public int getCreateId() {
		return createId;
	}
	public void setCreateId(int createId) {
		this.createId = createId;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public int getBizId() {
		return bizId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
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
	
}
