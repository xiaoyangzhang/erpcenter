package com.yimayhd.erpcenter.facade.operation.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

public class ToAddShopResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<BookingGuide> guides;
	private List<BookingSupplierDetail> driverList;
	private TourGroup tourGroup;
	private int groupId;

	public List<BookingGuide> getGuides() {
		return guides;
	}

	public void setGuides(List<BookingGuide> guides) {
		this.guides = guides;
	}


	public List<BookingSupplierDetail> getDriverList() {
		return driverList;
	}

	public void setDriverList(List<BookingSupplierDetail> driverList) {
		this.driverList = driverList;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public TourGroup getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
	
	

}
