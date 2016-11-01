
package com.yimayhd.erpcenter.facade.sales.result.operation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;

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
	private Map<String, Object> map;
	private PageBean pageBean;
	private List<GroupOrderGuest> NotGuests;
	private List<GroupOrderGuest> Guests;
	private List<GroupOrderPrintPo> orderPrints;
	
	public List<GroupOrderPrintPo> getOrderPrints() {
		return orderPrints;
	}
	public void setOrderPrints(List<GroupOrderPrintPo> orderPrints) {
		this.orderPrints = orderPrints;
	}
	public List<GroupOrderGuest> getNotGuests() {
		return NotGuests;
	}
	public void setNotGuests(List<GroupOrderGuest> notGuests) {
		NotGuests = notGuests;
	}
	public List<GroupOrderGuest> getGuests() {
		return Guests;
	}
	public void setGuests(List<GroupOrderGuest> guests) {
		Guests = guests;
	}
	private List<BookingSupplierDetail> supplierDetails;
	private List<BookingGuide> bookingGuides;
	
	
	public List<BookingSupplierDetail> getSupplierDetails() {
		return supplierDetails;
	}
	public void setSupplierDetails(List<BookingSupplierDetail> supplierDetails) {
		this.supplierDetails = supplierDetails;
	}
	public List<BookingGuide> getBookingGuides() {
		return bookingGuides;
	}
	public void setBookingGuides(List<BookingGuide> bookingGuides) {
		this.bookingGuides = bookingGuides;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
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
