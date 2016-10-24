package com.yimayhd.erpcenter.dal.sales.client.query.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ProductGuestShoppingItem implements Serializable {
	private String provinceName;
	private Double totalSum;
	private Integer adultSum;
	private List<Map<String,Object>> citys;
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public Double getTotalSum() {
		return totalSum;
	}
	public void setTotalSum(Double totalSum) {
		this.totalSum = totalSum;
	}
	public Integer getAdultSum() {
		return adultSum;
	}
	public void setAdultSum(Integer adultSum) {
		this.adultSum = adultSum;
	}
	public List<Map<String, Object>> getCitys() {
		return citys;
	}
	public void setCitys(List<Map<String, Object>> citys) {
		this.citys = citys;
	}
}
