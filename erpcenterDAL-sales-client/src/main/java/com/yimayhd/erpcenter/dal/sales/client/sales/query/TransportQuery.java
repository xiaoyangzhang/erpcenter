package com.yimayhd.erpcenter.dal.sales.client.sales.query;

import java.io.Serializable;
import java.util.Set;

public class TransportQuery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int page;
	private int pageSize;
	private Set<Integer> orderIds;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Set<Integer> getOrderIds() {
		return orderIds;
	}
	public void setOrderIds(Set<Integer> orderIds) {
		this.orderIds = orderIds;
	}
	
	
	
}
