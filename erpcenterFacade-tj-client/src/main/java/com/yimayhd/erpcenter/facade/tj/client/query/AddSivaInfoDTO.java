package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class AddSivaInfoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer orderId;
	private Integer orderMode;
	private GroupOrder orderBean;
	private int bizId;
	private String bookingDate;
	/**
	 * @return the bookingDate
	 */
	public String getBookingDate() {
		return bookingDate;
	}
	/**
	 * @param bookingDate the bookingDate to set
	 */
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	/**
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the orderMode
	 */
	public Integer getOrderMode() {
		return orderMode;
	}
	/**
	 * @param orderMode the orderMode to set
	 */
	public void setOrderMode(Integer orderMode) {
		this.orderMode = orderMode;
	}
	/**
	 * @return the orderBean
	 */
	public GroupOrder getOrderBean() {
		return orderBean;
	}
	/**
	 * @param orderBean the orderBean to set
	 */
	public void setOrderBean(GroupOrder orderBean) {
		this.orderBean = orderBean;
	}
	/**
	 * @return the bizId
	 */
	public int getBizId() {
		return bizId;
	}
	/**
	 * @param bizId the bizId to set
	 */
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	
	
}
