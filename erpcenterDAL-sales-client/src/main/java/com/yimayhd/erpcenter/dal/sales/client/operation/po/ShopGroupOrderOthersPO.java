package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import java.io.Serializable;
/**
 * 客人购物统计solr封装的辅助字段对象
 * @author liyong
 *
 */
public class ShopGroupOrderOthersPO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 订单id
	 */
	private Integer id;
	private String  shopId;
	private String  shopGuideID;
	private String  shopShopDate;
	private String  shopName;

	private String bgGuideName;
	private String bgUserName;

	
	private String bdBuyTotal;
	private Double bdTotalFee;
	private String bdShopFee;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopGuideID() {
		return shopGuideID;
	}
	public void setShopGuideID(String shopGuideID) {
		this.shopGuideID = shopGuideID;
	}
	public String getShopShopDate() {
		return shopShopDate;
	}
	public void setShopShopDate(String shopShopDate) {
		this.shopShopDate = shopShopDate;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getBgGuideName() {
		return bgGuideName;
	}
	public void setBgGuideName(String bgGuideName) {
		this.bgGuideName = bgGuideName;
	}
	public String getBgUserName() {
		return bgUserName;
	}
	public void setBgUserName(String bgUserName) {
		this.bgUserName = bgUserName;
	}
	public String getBdBuyTotal() {
		return bdBuyTotal;
	}
	public void setBdBuyTotal(String bdBuyTotal) {
		this.bdBuyTotal = bdBuyTotal;
	}
	public Double getBdTotalFee() {
		return bdTotalFee;
	}
	public void setBdTotalFee(Double bdTotalFee) {
		this.bdTotalFee = bdTotalFee;
	}
	public String getBdShopFee() {
		return bdShopFee;
	}
	public void setBdShopFee(String bdShopFee) {
		this.bdShopFee = bdShopFee;
	}

	
	
	
}
