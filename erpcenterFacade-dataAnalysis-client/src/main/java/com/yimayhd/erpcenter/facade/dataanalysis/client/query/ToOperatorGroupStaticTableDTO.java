package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.OperatorGroupStatic;

public class ToOperatorGroupStaticTableDTO extends BaseDTO {
	private static final long serialVersionUID = -9210966990028932001L;
	private OperatorGroupStatic ogs;
	private Integer page;
	private Integer pageSize;
//	private Integer bizId;
//	private Set<Integer> userIdSet;

	public OperatorGroupStatic getOgs() {
		return ogs;
	}

	public void setOgs(OperatorGroupStatic ogs) {
		this.ogs = ogs;
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

//	public Integer getBizId() {
//		return bizId;
//	}
//
//	public void setBizId(Integer bizId) {
//		this.bizId = bizId;
//	}
//
//	public Set<Integer> getUserIdSet() {
//		return userIdSet;
//	}
//
//	public void setUserIdSet(Set<Integer> userIdSet) {
//		this.userIdSet = userIdSet;
//	}
}
