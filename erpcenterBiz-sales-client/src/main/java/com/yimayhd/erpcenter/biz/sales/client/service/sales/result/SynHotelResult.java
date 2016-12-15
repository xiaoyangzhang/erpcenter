package com.yimayhd.erpcenter.biz.sales.client.service.sales.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.car.po.HotelMsg;

public class SynHotelResult extends ResultSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<HotelMsg> hotelMsgs;
	public List<HotelMsg> getHotelMsgs() {
		return hotelMsgs;
	}
	public void setHotelMsgs(List<HotelMsg> hotelMsgs) {
		this.hotelMsgs = hotelMsgs;
	}
	
}
