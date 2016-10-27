package com.yimayhd.erpcenter.facade.sales.query;

import java.io.Serializable;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

//按团查询利润预算
public class ProfitQueryByTourDTO implements Serializable {
	
	private Integer bizId;
	private Set<Integer> userIdSet;
	private TourGroup tour;
	private Integer page;
	private Integer pageSize;

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

	public Set<Integer> getUserIdSet() {
		return userIdSet;
	}

	public void setUserIdSet(Set<Integer> userIdSet) {
		this.userIdSet = userIdSet;
	}

	public TourGroup getTour() {
		return tour;
	}

	public void setTour(TourGroup tour) {
		this.tour = tour;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
