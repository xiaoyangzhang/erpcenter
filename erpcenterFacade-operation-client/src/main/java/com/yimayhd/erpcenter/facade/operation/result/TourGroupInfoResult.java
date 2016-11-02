/**
 * 
 */
package com.yimayhd.erpcenter.facade.operation.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteVO;

/**
 * @ClassName: TourGroupInfo
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月26日
 */
public class TourGroupInfoResult extends ResultSupport implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	private TourGroup tourGroup;
	private List<GroupRequirement> groupRequirements;
	private GroupRouteVO groupRoute;
	private List<GroupOrder> orders;
	
	public List<GroupOrder> getOrders() {
		return orders;
	}
	public void setOrders(List<GroupOrder> orders) {
		this.orders = orders;
	}
	public TourGroup getTourGroup() {
		return tourGroup;
	}
	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
	
	public List<GroupRequirement> getGroupRequirements() {
		return groupRequirements;
	}
	public void setGroupRequirements(List<GroupRequirement> groupRequirements) {
		this.groupRequirements = groupRequirements;
	}
	public GroupRouteVO getGroupRoute() {
		return groupRoute;
	}
	public void setGroupRoute(GroupRouteVO groupRoute) {
		this.groupRoute = groupRoute;
	}
	
	
}
