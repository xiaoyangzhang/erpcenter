package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;

public class Transport implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	private Integer orderId ;
	
	private List<GroupOrderTransport> transportList;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public List<GroupOrderTransport> getTransportList() {
		return transportList;
	}

	public void setTransportList(List<GroupOrderTransport> transportList) {
		this.transportList = transportList;
	}


}
