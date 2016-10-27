package com.yimayhd.erpcenter.facade.sales.query.fitorder;

import java.io.Serializable;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

public class GetInsertFitGroupListDTO implements Serializable {
	private static final long serialVersionUID = -1523394624297559742L;
	private Integer tid;
	private TourGroup tourGroup;
	private Integer bizId;
	private Set<Integer> userIdSet;

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public TourGroup getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
	
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
}
