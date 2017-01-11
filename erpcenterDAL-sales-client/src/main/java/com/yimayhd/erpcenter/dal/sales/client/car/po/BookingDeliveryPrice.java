package com.yimayhd.erpcenter.dal.sales.client.car.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class BookingDeliveryPrice implements Serializable {

	private static final long serialVersionUID = -7004036770960967316L;
	
	private int id;				
	private int bookingId;			
	private String itemName;		//项目名称
	private BigDecimal unitPrice;	//单价
	private BigDecimal numTimes;	//次数
	private BigDecimal numPerson;	//人数
	private BigDecimal totalPrice;	//金额
	private String remark;			//备注
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getNumTimes() {
		return numTimes;
	}
	public void setNumTimes(BigDecimal numTimes) {
		this.numTimes = numTimes;
	}
	public BigDecimal getNumPerson() {
		return numPerson;
	}
	public void setNumPerson(BigDecimal numPerson) {
		this.numPerson = numPerson;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
