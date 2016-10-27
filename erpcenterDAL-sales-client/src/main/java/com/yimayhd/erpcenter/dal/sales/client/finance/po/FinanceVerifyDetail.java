package com.yimayhd.erpcenter.dal.sales.client.finance.po;

import java.io.Serializable;
import java.math.BigDecimal;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

public class FinanceVerifyDetail implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 581494414373462502L;

	private Integer id;
	/**
	 * 对账单id
	 */
	private Integer verifyId;
	/**
	 * 与booking_id互柝
	 */
	private Integer orderId;
	/**
	 * 与order_id互柝
	 */
	private Integer bookingId;
	/**
	 * 调账金额
	 */
	private BigDecimal totalAdjust;
	private String remarl;
	private Integer bizId;
	
	private String remark;
	
	private TourGroup tourGroup;
	private BookingSupplier bookingSupplier;
	private FinanceVerify financeVerify;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVerifyId() {
		return verifyId;
	}
	public void setVerifyId(Integer verifyId) {
		this.verifyId = verifyId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public BigDecimal getTotalAdjust() {
		return totalAdjust;
	}
	public void setTotalAdjust(BigDecimal totalAdjust) {
		this.totalAdjust = totalAdjust;
	}
	public String getRemarl() {
		return remarl;
	}
	public void setRemarl(String remarl) {
		this.remarl = remarl;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public TourGroup getTourGroup() {
		return tourGroup;
	}
	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
	public BookingSupplier getBookingSupplier() {
		return bookingSupplier;
	}
	public void setBookingSupplier(BookingSupplier bookingSupplier) {
		this.bookingSupplier = bookingSupplier;
	}
	public FinanceVerify getFinanceVerify() {
		return financeVerify;
	}
	public void setFinanceVerify(FinanceVerify financeVerify) {
		this.financeVerify = financeVerify;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}