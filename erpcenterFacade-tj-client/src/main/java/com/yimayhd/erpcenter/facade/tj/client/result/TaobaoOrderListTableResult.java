package com.yimayhd.erpcenter.facade.tj.client.result;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class TaobaoOrderListTableResult extends ResultSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PageBean<GroupOrder> page;
	private GroupOrder groupOrder;
	private List<DicInfo> typeList;
	
	public PageBean<GroupOrder> getPage() {
		return page;
	}
	public void setPage(PageBean<GroupOrder> page) {
		this.page = page;
	}
	public GroupOrder getGroupOrder() {
		return groupOrder;
	}
	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}
	public List<DicInfo> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<DicInfo> typeList) {
		this.typeList = typeList;
	}
	
	
}
