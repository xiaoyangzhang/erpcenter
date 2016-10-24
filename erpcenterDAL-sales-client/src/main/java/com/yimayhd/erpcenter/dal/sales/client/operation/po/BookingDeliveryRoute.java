package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import java.io.Serializable;

public class BookingDeliveryRoute  implements Serializable {
    private Integer id;

    private Integer bookingId;

    private Integer routeId;

    
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

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

    
}