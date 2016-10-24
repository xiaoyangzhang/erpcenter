package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import java.io.Serializable;

public class BookingDeliveryOrder implements Serializable  {
    private Integer id;

    private Integer bookingId;

    private Integer orderId;

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}