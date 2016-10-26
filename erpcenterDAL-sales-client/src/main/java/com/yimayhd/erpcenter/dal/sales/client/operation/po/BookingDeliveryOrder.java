<<<<<<< 8a989fc60c5453369ca5ddcb7a3b0a2d11ffa98f
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
=======
package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import java.io.Serializable;

public class BookingDeliveryOrder implements Serializable  {
    /**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -7458603995683210343L;

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
>>>>>>> 添加结果类
}