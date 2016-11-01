/**
 * 
 */
package com.yimayhd.erpcenter.facade.ticket.result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.airticket.bo.AirTicketRequestBO;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;


/**
 * @ClassName: InRecordResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class ShowApplyListResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;
	
	private PageBean<GroupOrder> pageBean;
	private Map<String, Object> sumPerson;
	private HashMap<Integer, String> orderRequestStatus;
	private HashMap<Integer, List<AirTicketRequestBO>> boMap;
	
	public PageBean<GroupOrder> getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean<GroupOrder> pageBean) {
		this.pageBean = pageBean;
	}
	public Map<String, Object> getSumPerson() {
		return sumPerson;
	}
	public void setSumPerson(Map<String, Object> sumPerson) {
		this.sumPerson = sumPerson;
	}
	public HashMap<Integer, String> getOrderRequestStatus() {
		return orderRequestStatus;
	}
	public void setOrderRequestStatus(HashMap<Integer, String> orderRequestStatus) {
		this.orderRequestStatus = orderRequestStatus;
	}
	public HashMap<Integer, List<AirTicketRequestBO>> getBoMap() {
		return boMap;
	}
	public void setBoMap(HashMap<Integer, List<AirTicketRequestBO>> boMap) {
		this.boMap = boMap;
	}
	
	
}
