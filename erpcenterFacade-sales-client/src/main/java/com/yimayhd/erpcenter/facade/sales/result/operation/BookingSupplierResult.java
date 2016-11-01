/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.result.operation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.FinanceBill;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.FinanceBillDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteVO;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;
import com.yimayhd.erpresource.dal.po.SupplierItem;
import com.yimayhd.erpresource.dal.vo.SupplierCarVO;

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
	private List<GroupRequirement> groupRequirements;
	private List<GroupOrderTransport> groupOrderTransports;
	private List<SupplierCarVO> carVOList;
	private PageBean pageBean;
	private TourGroup tourGroup;
	private List<FinanceBillDetail> financeBillDetails;
	private List<BookingSupplier> bookingSuppliers;
	private List<Map<String, Object>> mapList;
	private boolean groupAbleEdit;
	
	private GroupRouteVO routeVO;
	private BookingSupplier bookingSupplier;
	private List<SupplierItem> supplierItems;
	private String customers;
	private List<String> strList;
	private List<GroupRoute> groupRoutes;
	
	public List<GroupRoute> getGroupRoutes() {
		return groupRoutes;
	}
	public void setGroupRoutes(List<GroupRoute> groupRoutes) {
		this.groupRoutes = groupRoutes;
	}
	public List<String> getStrList() {
		return strList;
	}
	public void setStrList(List<String> strList) {
		this.strList = strList;
	}
	public String getCustomers() {
		return customers;
	}
	public void setCustomers(String customers) {
		this.customers = customers;
	}
	public List<SupplierItem> getSupplierItems() {
		return supplierItems;
	}
	public void setSupplierItems(List<SupplierItem> supplierItems) {
		this.supplierItems = supplierItems;
	}
	public BookingSupplier getBookingSupplier() {
		return bookingSupplier;
	}
	public void setBookingSupplier(BookingSupplier bookingSupplier) {
		this.bookingSupplier = bookingSupplier;
	}
	public GroupRouteVO getRouteVO() {
		return routeVO;
	}
	public void setRouteVO(GroupRouteVO routeVO) {
		this.routeVO = routeVO;
	}
	public boolean isGroupAbleEdit() {
		return groupAbleEdit;
	}
	public void setGroupAbleEdit(boolean groupAbleEdit) {
		this.groupAbleEdit = groupAbleEdit;
	}
	public List<Map<String, Object>> getMapList() {
		return mapList;
	}
	public void setMapList(List<Map<String, Object>> mapList) {
		this.mapList = mapList;
	}
	public List<BookingSupplier> getBookingSuppliers() {
		return bookingSuppliers;
	}
	public void setBookingSuppliers(List<BookingSupplier> bookingSuppliers) {
		this.bookingSuppliers = bookingSuppliers;
	}
	public TourGroup getTourGroup() {
		return tourGroup;
	}
	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
	
	public List<FinanceBillDetail> getFinanceBillDetails() {
		return financeBillDetails;
	}
	public void setFinanceBillDetails(List<FinanceBillDetail> financeBillDetails) {
		this.financeBillDetails = financeBillDetails;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public List<SupplierCarVO> getCarVOList() {
		return carVOList;
	}
	public void setCarVOList(List<SupplierCarVO> carVOList) {
		this.carVOList = carVOList;
	}
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
