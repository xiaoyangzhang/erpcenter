package com.yimayhd.erpcenter.biz.sales.client.service.sales.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.car.po.GroupOrderGuest;

public class SearchOrderGuestResult extends ResultSupport{

	private static final long serialVersionUID = -5636950350216985901L;
	
	private List<GroupOrderGuest> guestList;
	private long totalCount;
	private long totalPage;

	public List<GroupOrderGuest> getGuestList() {
		return guestList;
	}

	public void setGuestList(List<GroupOrderGuest> guestList) {
		this.guestList = guestList;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
}
