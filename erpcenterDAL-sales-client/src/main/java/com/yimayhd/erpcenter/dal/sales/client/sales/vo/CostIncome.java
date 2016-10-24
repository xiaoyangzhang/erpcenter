package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.List;

import com.yihg.sales.po.GroupOrderPrice;

public class CostIncome implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	private Integer orderId ;
	
	private List<GroupOrderPrice> priceList;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public List<GroupOrderPrice> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<GroupOrderPrice> priceList) {
		this.priceList = priceList;
	}
	
	
}
