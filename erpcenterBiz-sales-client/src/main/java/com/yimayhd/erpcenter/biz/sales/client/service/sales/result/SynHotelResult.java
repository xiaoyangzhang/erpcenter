package com.yimayhd.erpcenter.biz.sales.client.service.sales.result;

import java.util.Date;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.car.po.HotelMsg;

public class SynHotelResult extends ResultSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 团id
	 */
	private int groupId;
	/**
	 * 接送类型
	 */
	private int type;
	/**
	 * 出发时间
	 */
	private Date departureDate;
	/**
	 * 到达时间
	 */
	private Date arrivalDate;
	
	private List<HotelMsg> hotelMsgs;
	public List<HotelMsg> getHotelMsgs() {
		return hotelMsgs;
	}
	public void setHotelMsgs(List<HotelMsg> hotelMsgs) {
		this.hotelMsgs = hotelMsgs;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
}
