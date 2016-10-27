package com.yimayhd.erpcenter.dal.sales.client.finance.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FinanceVerify implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 581494414373462502L;

	private Integer id;
	private String verifyCode;
	private Integer verifyState;
	private Date dateStart;
	private Date dateEnd;
	private Integer supplierType;
	private Integer supplierId;
	private BigDecimal totalPrice;
	private BigDecimal totalCash;
	private BigDecimal totalRecord;
	private Integer userId;
	private Date createTime;
	private BigDecimal totalAdjust;
	private Integer bizId;
	private String supplierName;
	private String userName;
	private Integer sort;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public Integer getVerifyState() {
		return verifyState;
	}
	public void setVerifyState(Integer verifyState) {
		this.verifyState = verifyState;
	}
	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public Integer getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(Integer supplierType) {
		this.supplierType = supplierType;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public BigDecimal getTotalCash() {
		return totalCash;
	}
	public void setTotalCash(BigDecimal totalCash) {
		this.totalCash = totalCash;
	}
	public BigDecimal getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(BigDecimal totalRecord) {
		this.totalRecord = totalRecord;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public BigDecimal getTotalAdjust() {
		return totalAdjust;
	}
	public void setTotalAdjust(BigDecimal totalAdjust) {
		this.totalAdjust = totalAdjust;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
}