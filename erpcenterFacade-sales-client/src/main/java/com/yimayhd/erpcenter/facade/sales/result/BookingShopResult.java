/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.result;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetailDeploy;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroupPriceAndPersons;
import com.yimayhd.erpresource.dal.po.SupplierItem;

/**
 * @ClassName: BookingShopResult
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月26日
 */
public class BookingShopResult extends ResultSupport implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	private List<BookingShop> bookingShops;
	private BigDecimal count;
	private TourGroupPriceAndPersons tourGroupInfo;
	private BookingShop bookingShop;
	private List<BookingGuide> bookingGuides;
	private BookingGuide bookingGuide;
	private List<BookingShopDetail> shopDetails;
	private List<BookingSupplierDetail> supplierDetails;
	private List<SupplierItem> supplierItems;
	private BookingSupplierDetail bookingSupplierDetail;
	private List<BookingShopDetailDeploy> shopDetailDeploys;
	private List<GroupOrder> groupOrders;
	private TourGroup tourGroup;
	
	public TourGroup getTourGroup() {
		return tourGroup;
	}
	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
	public List<BookingShopDetailDeploy> getShopDetailDeploys() {
		return shopDetailDeploys;
	}
	public void setShopDetailDeploys(List<BookingShopDetailDeploy> shopDetailDeploys) {
		this.shopDetailDeploys = shopDetailDeploys;
	}
	public List<GroupOrder> getGroupOrders() {
		return groupOrders;
	}
	public void setGroupOrders(List<GroupOrder> groupOrders) {
		this.groupOrders = groupOrders;
	}
	public BookingSupplierDetail getBookingSupplierDetail() {
		return bookingSupplierDetail;
	}
	public void setBookingSupplierDetail(BookingSupplierDetail bookingSupplierDetail) {
		this.bookingSupplierDetail = bookingSupplierDetail;
	}
	public BookingGuide getBookingGuide() {
		return bookingGuide;
	}
	public void setBookingGuide(BookingGuide bookingGuide) {
		this.bookingGuide = bookingGuide;
	}
	public List<BookingShopDetail> getShopDetails() {
		return shopDetails;
	}
	public void setShopDetails(List<BookingShopDetail> shopDetails) {
		this.shopDetails = shopDetails;
	}
	public List<BookingSupplierDetail> getSupplierDetails() {
		return supplierDetails;
	}
	public void setSupplierDetails(List<BookingSupplierDetail> supplierDetails) {
		this.supplierDetails = supplierDetails;
	}
	public List<SupplierItem> getSupplierItems() {
		return supplierItems;
	}
	public void setSupplierItems(List<SupplierItem> supplierItems) {
		this.supplierItems = supplierItems;
	}
	public BookingShop getBookingShop() {
		return bookingShop;
	}
	public void setBookingShop(BookingShop bookingShop) {
		this.bookingShop = bookingShop;
	}
	public List<BookingGuide> getBookingGuides() {
		return bookingGuides;
	}
	public void setBookingGuides(List<BookingGuide> bookingGuides) {
		this.bookingGuides = bookingGuides;
	}
	public TourGroupPriceAndPersons getTourGroupInfo() {
		return tourGroupInfo;
	}
	public void setTourGroupInfo(TourGroupPriceAndPersons tourGroupInfo) {
		this.tourGroupInfo = tourGroupInfo;
	}
	public List<BookingShop> getBookingShops() {
		return bookingShops;
	}
	public void setBookingShops(List<BookingShop> bookingShops) {
		this.bookingShops = bookingShops;
	}
	public BigDecimal getCount() {
		return count;
	}
	public void setCount(BigDecimal count) {
		this.count = count;
	}
	
}
