package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.io.Serializable;
import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class ReportStatisticsNoPageResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3170238633932857375L;
	
	
	private List<GroupOrder> orderList;


	public List<GroupOrder> getOrderList() {
		return orderList;
	}


	public void setOrderList(List<GroupOrder> orderList) {
		this.orderList = orderList;
	}

	

}
