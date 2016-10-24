package com.yimayhd.erpcenter.dal.sales.client.operation.vo;

import java.io.Serializable;
import java.util.List;

import com.yihg.operation.po.BookingGuide;
import com.yihg.operation.po.BookingGuideTimes;

public class BookingGuidesVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4404636718454640600L;
	
	private BookingGuide guide;
	private List<BookingGuideTimes> guideTimes;
	public BookingGuide getGuide() {
		return guide;
	}
	public void setGuide(BookingGuide guide) {
		this.guide = guide;
	}
	public List<BookingGuideTimes> getGuideTimes() {
		return guideTimes;
	}
	public void setGuideTimes(List<BookingGuideTimes> guideTimes) {
		this.guideTimes = guideTimes;
	}
	
	
}
