package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class GroupOrderDTO extends BaseDTO {

	/**
	 * @author liyong
	 * 2016年11月1日 描述：
	 */
	private static final long serialVersionUID = 1L;
	private GroupOrder groupOrder;
	
	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}
	
	public GroupOrder getGroupOrder() {
		return groupOrder;
	}
}
