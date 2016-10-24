package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;

public class GroupPriceVo implements Serializable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1534781919292690205L;

	private String receiveMode ; //接站牌
	private String priceDetail ;//每一个订单的价格明细--项目、备注、价格明细
	private Double totalPrice ; //每个订单的总价
	public String getReceiveMode() {
		return receiveMode;
	}
	public void setReceiveMode(String receiveMode) {
		this.receiveMode = receiveMode;
	}
	public String getPriceDetail() {
		return priceDetail;
	}
	public void setPriceDetail(String priceDetail) {
		this.priceDetail = priceDetail;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
