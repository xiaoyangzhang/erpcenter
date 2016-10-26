package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteAttachment;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteSupplier;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteTraffic;

public class GroupRouteDayVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<GroupRouteAttachment> groupRouteAttachmentList;
	private GroupRoute groupRoute;
	private List<GroupRouteTraffic> groupRouteTrafficList;

	private List<GroupRouteSupplier> groupRouteOptionsSupplierList; // 备选商家

	public List<GroupRouteAttachment> getGroupRouteAttachmentList() {
		return groupRouteAttachmentList;
	}

	public void setGroupRouteAttachmentList(
			List<GroupRouteAttachment> groupRouteAttachmentList) {
		this.groupRouteAttachmentList = groupRouteAttachmentList;
	}

	public GroupRoute getGroupRoute() {
		return groupRoute;
	}

	public void setGroupRoute(GroupRoute groupRoute) {
		this.groupRoute = groupRoute;
	}

	public List<GroupRouteTraffic> getGroupRouteTrafficList() {
		return groupRouteTrafficList;
	}

	public void setGroupRouteTrafficList(
			List<GroupRouteTraffic> groupRouteTrafficList) {
		this.groupRouteTrafficList = groupRouteTrafficList;
	}

	public List<GroupRouteSupplier> getGroupRouteOptionsSupplierList() {
		return groupRouteOptionsSupplierList;
	}

	public void setGroupRouteOptionsSupplierList(
			List<GroupRouteSupplier> groupRouteOptionsSupplierList) {
		this.groupRouteOptionsSupplierList = groupRouteOptionsSupplierList;
	}

}
