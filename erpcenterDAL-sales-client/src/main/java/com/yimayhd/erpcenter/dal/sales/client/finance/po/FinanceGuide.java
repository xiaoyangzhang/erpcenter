package com.yimayhd.erpcenter.dal.sales.client.finance.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FinanceGuide implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 581494414373462502L;

	private Integer id;

    private Integer groupId;

    private Integer bookingId;

    private Integer bookingIdLink;

    private BigDecimal total;

    private String cashType;
    private Integer supplierType;
    
    private Integer payStatus;
    
    private String payUserName;
    private Date payTime;
    
    

    public Integer getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(Integer supplierType) {
		this.supplierType = supplierType;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getBookingIdLink() {
        return bookingIdLink;
    }

    public void setBookingIdLink(Integer bookingIdLink) {
        this.bookingIdLink = bookingIdLink;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCashType() {
        return cashType;
    }

    public void setCashType(String cashType) {
        this.cashType = cashType == null ? null : cashType.trim();
    }

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayUserName() {
		return payUserName;
	}

	public void setPayUserName(String payUserName) {
		this.payUserName = payUserName;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
    
    
}