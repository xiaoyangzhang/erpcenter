package com.yimayhd.erpcenter.facade.tj.client.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpresource.dal.po.SupplierItem;

public class ToAddSupplierResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BookingSupplier supplier;
	private List<BookingSupplierDetail> detailList;
	private List<SupplierItem> supplierItems;
	private List<BookingGuide> bookingGuides;
	private List<DicInfo> cashTypes;
	public BookingSupplier getSupplier() {
		return supplier;
	}
	public void setSupplier(BookingSupplier supplier) {
		this.supplier = supplier;
	}
	public List<BookingSupplierDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<BookingSupplierDetail> detailList) {
		this.detailList = detailList;
	}
	public List<SupplierItem> getSupplierItems() {
		return supplierItems;
	}
	public void setSupplierItems(List<SupplierItem> supplierItems) {
		this.supplierItems = supplierItems;
	}
	public List<BookingGuide> getBookingGuides() {
		return bookingGuides;
	}
	public void setBookingGuides(List<BookingGuide> bookingGuides) {
		this.bookingGuides = bookingGuides;
	}
	public List<DicInfo> getCashTypes() {
		return cashTypes;
	}
	public void setCashTypes(List<DicInfo> cashTypes) {
		this.cashTypes = cashTypes;
	}
	
	
 
}
