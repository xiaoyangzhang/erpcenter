package com.yimayhd.erpcenter.facade.sales.query.grouporder;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class ToNotGroupListDTO implements Serializable {
	private static final long serialVersionUID = -4055249708771503982L;
	private GroupOrder groupOrder;
	private Integer bizId;
	private Set<Integer> userIdSet;
	private Map<String,Object> pmBean;

	private String guestCertificateNum;
	private Integer orderId;

	public GroupOrder getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
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

	public Map<String, Object> getPmBean() {
		return pmBean;
	}

	public void setPmBean(Map<String, Object> pmBean) {
		this.pmBean = pmBean;
	}

	public String getGuestCertificateNum() {
		return guestCertificateNum;
	}

	public void setGuestCertificateNum(String guestCertificateNum) {
		this.guestCertificateNum = guestCertificateNum;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
}
