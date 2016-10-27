package com.yimayhd.erpcenter.facade.sales.query.fitorder;

import java.io.Serializable;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class ToImpNotGroupListDTO implements Serializable {
	private static final long serialVersionUID = 566249391612818223L;
	private GroupOrder groupOrder = null;
	private String idLists = null;
	private Integer bizId;
	private Set<Integer> userIdSet;
	public GroupOrder getGroupOrder() {
		return groupOrder;
	}
	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}
	public String getIdLists() {
		return idLists;
	}
	public void setIdLists(String idLists) {
		this.idLists = idLists;
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
