/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

/**
 * @ClassName: BookingDeliveryResult
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月25日
 */
public class BookingDeliveryResult extends ResultSupport implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -5509307324118124849L;
	private List<BookingDelivery> bookingDeliveries;
	private boolean groupCanEdit;
	private TourGroup tourGroup;
	private List<GroupRoute> groupRoutes;
	private List<GroupOrder> groupOrders;
	private BookingDelivery bookingDelivery;
	
	public TourGroup getTourGroup() {
		return tourGroup;
	}
	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
	public List<GroupRoute> getGroupRoutes() {
		return groupRoutes;
	}
	public void setGroupRoutes(List<GroupRoute> groupRoutes) {
		this.groupRoutes = groupRoutes;
	}
	public List<GroupOrder> getGroupOrders() {
		return groupOrders;
	}
	public void setGroupOrders(List<GroupOrder> groupOrders) {
		this.groupOrders = groupOrders;
	}
	public BookingDelivery getBookingDelivery() {
		return bookingDelivery;
	}
	public void setBookingDelivery(BookingDelivery bookingDelivery) {
		this.bookingDelivery = bookingDelivery;
	}
	public List<BookingDelivery> getBookingDeliveries() {
		return bookingDeliveries;
	}
	public void setBookingDeliveries(List<BookingDelivery> bookingDeliveries) {
		this.bookingDeliveries = bookingDeliveries;
	}
	public boolean isGroupCanEdit() {
		return groupCanEdit;
	}
	public void setGroupCanEdit(boolean groupCanEdit) {
		this.groupCanEdit = groupCanEdit;
	}
	
	
}
