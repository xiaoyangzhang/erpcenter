/**
 * 
 */
package com.yimayhd.erpcenter.facade.ticket.result;

import java.util.HashMap;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;


/**
 * @ClassName: InRecordResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class ShowGroupOrderResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;
	
	private PageBean<GroupOrder> pageBean;
	private HashMap<Integer, String> groupModes;
	private HashMap<Integer, String> orderRequestStatus;
	
	public PageBean<GroupOrder> getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean<GroupOrder> pageBean) {
		this.pageBean = pageBean;
	}
	public HashMap<Integer, String> getGroupModes() {
		return groupModes;
	}
	public void setGroupModes(HashMap<Integer, String> groupModes) {
		this.groupModes = groupModes;
	}
	public HashMap<Integer, String> getOrderRequestStatus() {
		return orderRequestStatus;
	}
	public void setOrderRequestStatus(HashMap<Integer, String> orderRequestStatus) {
		this.orderRequestStatus = orderRequestStatus;
	}
	
	
}
