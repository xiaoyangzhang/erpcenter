package com.yimayhd.erpcenter.dal.product.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.yimayhd.erpcenter.dal.product.po.ProductAttachment;
import com.yimayhd.erpcenter.dal.product.po.ProductContact;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;


public class ProductInfoVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1516765665450614904L;
	private ProductInfo productInfo;
	private List<ProductContact> productContacts;
	private List<ProductAttachment> productAttachments; // 图片
	private List<ProductAttachment> attachments; // 附件
	private Set<Integer> orgIdSet;
	
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
	
	
	public ProductInfo getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
	public List<ProductContact> getProductContacts() {
		return productContacts;
	}
	public void setProductContacts(List<ProductContact> productContacts) {
		this.productContacts = productContacts;
	}
	public List<ProductAttachment> getProductAttachments() {
		return productAttachments;
	}
	public void setProductAttachments(List<ProductAttachment> productAttachments) {
		this.productAttachments = productAttachments;
	}

    public List<ProductAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<ProductAttachment> attachments) {
        this.attachments = attachments;
    }
	public Set<Integer> getOrgIdSet() {
		return orgIdSet;
	}
	public void setOrgIdSet(Set<Integer> orgIdSet) {
		this.orgIdSet = orgIdSet;
	}
    
}
