package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

import java.util.List;

public class QueryGroupNumberResult extends BaseResult {
	private static final long serialVersionUID = 164371480905634696L;
	private String jsonStr;
	private GroupOrder groupOrder;
	private Integer dataType;
	private List<GroupOrder> groupOrderList;

	public List<GroupOrder> getGroupOrderList() {
		return groupOrderList;
	}

	public void setGroupOrderList(List<GroupOrder> groupOrderList) {
		this.groupOrderList = groupOrderList;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public GroupOrder getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
}
