package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToMergeGroupResult extends BaseStateResult {
	private static final long serialVersionUID = -9005290229676058877L;
	private List<GroupOrder> list;

	public List<GroupOrder> getList() {
		return list;
	}

	public void setList(List<GroupOrder> list) {
		this.list = list;
	}
}
