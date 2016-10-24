package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import java.io.Serializable;
import java.util.Date;

public class BookingGuideTimes implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 62865867671564158L;

	private Integer id;

    private Integer bookingId;

    private String timeStart;

    private String timeEnd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	
   
}