package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class TaobaoOrderListTableDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GroupOrder groupOrder;
	
	private int bizId;
	
	private Set<Integer> dataUserIdSets;

	private Integer rows;

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Set<Integer> getDataUserIdSets() {
		return dataUserIdSets;
	}

	public void setDataUserIdSets(Set<Integer> dataUserIdSets) {
		this.dataUserIdSets = dataUserIdSets;
	}

	public GroupOrder getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}

	public int getBizId() {
		return bizId;
	}

	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	
	

}
