package com.yimayhd.erpcenter.dal.product.vo;

import java.io.Serializable;

public class ProductGroupVo implements Serializable {
	private Integer groupId;
    private String name;
    private String cityDeparture;
    private Float priceCostAdult;//成本价-成人
    private Float priceCostChild;//成本价-儿童      
    private Float priceSettlementAdult;//结算价格-成人
    private Float priceSettlementChild;//结算价格-儿童
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCityDeparture() {
		return cityDeparture;
	}
	public void setCityDeparture(String cityDeparture) {
		this.cityDeparture = cityDeparture;
	}	
	public Float getPriceCostAdult() {
		return priceCostAdult;
	}
	public void setPriceCostAdult(Float priceCostAdult) {
		this.priceCostAdult = priceCostAdult;
	}
	public Float getPriceCostChild() {
		return priceCostChild;
	}
	public void setPriceCostChild(Float priceCostChild) {
		this.priceCostChild = priceCostChild;
	}
	public Float getPriceSettlementAdult() {
		return priceSettlementAdult;
	}
	public void setPriceSettlementAdult(Float priceSettlementAdult) {
		this.priceSettlementAdult = priceSettlementAdult;
	}
	public Float getPriceSettlementChild() {
		return priceSettlementChild;
	}
	public void setPriceSettlementChild(Float priceSettlementChild) {
		this.priceSettlementChild = priceSettlementChild;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
}
