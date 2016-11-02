package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class ExpGroupNumberResult extends BaseResult {
	private static final long serialVersionUID = 681390243006185589L;
	private List<GroupOrder> allGroupOrder;

	public List<GroupOrder> getAllGroupOrder() {
		return allGroupOrder;
	}

	public void setAllGroupOrder(List<GroupOrder> allGroupOrder) {
		this.allGroupOrder = allGroupOrder;
	}
}
