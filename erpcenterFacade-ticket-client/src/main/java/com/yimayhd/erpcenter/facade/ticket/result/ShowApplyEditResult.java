/**
 * 
 */
package com.yimayhd.erpcenter.facade.ticket.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;


/**
 * @ClassName: InRecordResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class ShowApplyEditResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;
	
	private GroupOrder order;
	private List<GroupOrderGuest> guestList;
	
	public GroupOrder getOrder() {
		return order;
	}
	public void setOrder(GroupOrder order) {
		this.order = order;
	}
	public List<GroupOrderGuest> getGuestList() {
		return guestList;
	}
	public void setGuestList(List<GroupOrderGuest> guestList) {
		this.guestList = guestList;
	}
	
}
