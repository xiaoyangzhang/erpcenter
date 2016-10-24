package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yihg.sales.po.AssistantGroup;
import com.yihg.sales.po.AssistantGroupGuide;
import com.yihg.sales.po.AssistantGroupOrder;
import com.yihg.sales.po.AssistantGroupOrderGuest;
import com.yihg.sales.po.AssistantGroupOrderTransport;
import com.yihg.sales.po.AssistantGroupRoute;
import com.yihg.sales.po.AssistantGroupRouteAttachment;
import com.yihg.sales.po.AssistantGroupRouteSupplier;
import com.yihg.sales.po.GroupOrder;

public class AssistantGroupVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3087365965318446604L;
	private AssistantGroup group;
	private List<AssistantGroupGuide> guideList;
	private List<AssistantGroupOrder> orderList;
	private List<AssistantGroupOrderGuest> orderGuestList;
	private List<AssistantGroupOrderTransport> orderTransportList;
	private List<AssistantGroupRoute> routeList;
	private List<AssistantGroupRouteAttachment> routeAttachmentList;
	
	public AssistantGroup getGroup() {
		return group;
	}
	public void setGroup(AssistantGroup group) {
		this.group = group;
	}
	public List<AssistantGroupGuide> getGuideList() {
		return guideList;
	}
	public void setGuideList(List<AssistantGroupGuide> guideList) {
		this.guideList = guideList;
	}
	public List<AssistantGroupOrder> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<AssistantGroupOrder> orderList) {
		this.orderList = orderList;
	}
	public List<AssistantGroupOrderGuest> getOrderGuestList() {
		return orderGuestList;
	}
	public void setOrderGuestList(List<AssistantGroupOrderGuest> orderGuestList) {
		this.orderGuestList = orderGuestList;
	}
	public List<AssistantGroupOrderTransport> getOrderTransportList() {
		return orderTransportList;
	}
	public void setOrderTransportList(
			List<AssistantGroupOrderTransport> orderTransportList) {
		this.orderTransportList = orderTransportList;
	}
	public List<AssistantGroupRoute> getRouteList() {
		return routeList;
	}
	public void setRouteList(List<AssistantGroupRoute> routeList) {
		this.routeList = routeList;
	}
	public List<AssistantGroupRouteAttachment> getRouteAttachmentList() {
		return routeAttachmentList;
	}
	public void setRouteAttachmentList(
			List<AssistantGroupRouteAttachment> routeAttachmentList) {
		this.routeAttachmentList = routeAttachmentList;
	}
	public List<AssistantGroupRouteSupplier> getRouteSupplierList() {
		return routeSupplierList;
	}
	public void setRouteSupplierList(
			List<AssistantGroupRouteSupplier> routeSupplierList) {
		this.routeSupplierList = routeSupplierList;
	}
	private List<AssistantGroupRouteSupplier> routeSupplierList;
	
    
    
    
}