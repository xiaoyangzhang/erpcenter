package com.yimayhd.erpcenter.facade.sales.query.grouporder;

import java.io.Serializable;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SalePrice;

public class ToDeliveryPriceTableDTO implements Serializable {
	private static final long serialVersionUID = 4365288129528045907L;
	private SalePrice sp;
	private Integer bizId;
	private Set<Integer> userIdSet;

	public SalePrice getSp() {
		return sp;
	}

	public void setSp(SalePrice sp) {
		this.sp = sp;
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
