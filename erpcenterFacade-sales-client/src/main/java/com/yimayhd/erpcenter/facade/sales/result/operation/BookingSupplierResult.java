/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.result.operation;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;

/**
 * @ClassName: BookingSupplierResult
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月26日
 */
public class BookingSupplierResult extends ResultSupport implements
		Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -5921331490547254138L;
	private List<BookingSupplierDetail> detailList;
	private List<GroupRoute> routeList;
	private List<BookingGuide> bGuides;
	private List<GroupOrder> orderList;
	private List<GroupOrderGuest> guestList;
	List<GroupRequirement> groupRequirements;
	List<GroupOrderTransport> groupOrderTransports;
	
	public List<GroupRoute> getRouteList() {
		return routeList;
	}
	public void setRouteList(List<GroupRoute> routeList) {
		this.routeList = routeList;
	}
	public List<BookingGuide> getbGuides() {
		return bGuides;
	}
	public void setbGuides(List<BookingGuide> bGuides) {
		this.bGuides = bGuides;
	}
	public List<GroupOrder> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<GroupOrder> orderList) {
		this.orderList = orderList;
	}
	public List<GroupOrderGuest> getGuestList() {
		return guestList;
	}
	public void setGuestList(List<GroupOrderGuest> guestList) {
		this.guestList = guestList;
	}
	public List<GroupRequirement> getGroupRequirements() {
		return groupRequirements;
	}
	public void setGroupRequirements(List<GroupRequirement> groupRequirements) {
		this.groupRequirements = groupRequirements;
	}
	public List<GroupOrderTransport> getGroupOrderTransports() {
		return groupOrderTransports;
	}
	public void setGroupOrderTransports(
			List<GroupOrderTransport> groupOrderTransports) {
		this.groupOrderTransports = groupOrderTransports;
	}
	public List<BookingSupplierDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<BookingSupplierDetail> detailList) {
		this.detailList = detailList;
	}
	
}
