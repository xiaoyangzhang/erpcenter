package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import java.io.Serializable;
import java.math.BigDecimal;

import org.omg.CORBA.PRIVATE_MEMBER;

public class BookingShopDetailDeploy implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer bookingDetailId;

    private Integer bookingId;
    private Integer orderId;

    private BigDecimal buyTotal;
    
    private String remark;
    
    private String OrderNo;
    private String supplierName;
    private int guestSize;
    private String guestNames;
    private Integer adultNum;
    private Integer childNum;
    private Integer shoppingDataState;//购物金额录入状态
    
    
	public Integer getShoppingDataState() {
		return shoppingDataState;
	}

	public void setShoppingDataState(Integer shoppingDataState) {
		this.shoppingDataState = shoppingDataState;
	}

	public Integer getAdultNum() {
		return adultNum;
	}

	public void setAdultNum(Integer adultNum) {
		this.adultNum = adultNum;
	}

	public Integer getChildNum() {
		return childNum;
	}

	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public String getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	

	public int getGuestSize() {
		return guestSize;
	}

	public void setGuestSize(int guestSize) {
		this.guestSize = guestSize;
	}

	public String getGuestNames() {
		return guestNames;
	}

	public void setGuestNames(String guestNames) {
		this.guestNames = guestNames;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookingDetailId() {
        return bookingDetailId;
    }

    public void setBookingDetailId(Integer bookingDetailId) {
        this.bookingDetailId = bookingDetailId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getBuyTotal() {
        return buyTotal;
    }

    public void setBuyTotal(BigDecimal buyTotal) {
        this.buyTotal = buyTotal;
    }
}