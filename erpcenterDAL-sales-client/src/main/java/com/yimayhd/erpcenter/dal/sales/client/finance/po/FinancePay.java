package com.yimayhd.erpcenter.dal.sales.client.finance.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FinancePay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8891123968788067709L;

	private Integer id;
	
	private Integer bizId;

	private Date payDate;

	private String payCode;

	private Integer payDirect;

	private Integer supplierType;

	private Integer supplierId;

	private String supplierName;

	private String payType;

	private String leftBank;

	private String leftBankOpen;

	private String leftBankHolder;

	private String leftBankAccount;

	private String rightBank;

	private String rightBankOpen;

	private String rightBankHolder;

	private String rightBankAccount;

	private String remark;

	private BigDecimal cash;

	private Integer verifyId;

	private Integer bookingGuideId;

	private String userName;

	private String details;

	private Integer userId;

	private Long createTime;
	
	private Integer detailCount;
	
	private String commissionIds;
	
	private String commissionDeductionIds;
	
	private Integer groupId;

	private Integer guideId;
	
	private Integer type;
	
	public String getCommissionDeductionIds() {
		return commissionDeductionIds;
	}

	public void setCommissionDeductionIds(String commissionDeductionIds) {
		this.commissionDeductionIds = commissionDeductionIds;
	}
	
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getGuideId() {
		return guideId;
	}

	public void setGuideId(Integer guideId) {
		this.guideId = guideId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getCommissionIds() {
		return commissionIds;
	}

	public void setCommissionIds(String commissionIds) {
		this.commissionIds = commissionIds;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode == null ? null : payCode.trim();
	}

	public Integer getPayDirect() {
		return payDirect;
	}

	public void setPayDirect(Integer payDirect) {
		this.payDirect = payDirect;
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

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType == null ? null : payType.trim();
	}

	public String getLeftBank() {
		return leftBank;
	}

	public void setLeftBank(String leftBank) {
		this.leftBank = leftBank == null ? null : leftBank.trim();
	}

	public String getLeftBankOpen() {
		return leftBankOpen;
	}

	public void setLeftBankOpen(String leftBankOpen) {
		this.leftBankOpen = leftBankOpen == null ? null : leftBankOpen.trim();
	}

	public String getLeftBankHolder() {
		return leftBankHolder;
	}

	public void setLeftBankHolder(String leftBankHolder) {
		this.leftBankHolder = leftBankHolder == null ? null : leftBankHolder.trim();
	}

	public String getLeftBankAccount() {
		return leftBankAccount;
	}

	public void setLeftBankAccount(String leftBankAccount) {
		this.leftBankAccount = leftBankAccount == null ? null : leftBankAccount.trim();
	}

	public String getRightBank() {
		return rightBank;
	}

	public void setRightBank(String rightBank) {
		this.rightBank = rightBank == null ? null : rightBank.trim();
	}

	public String getRightBankOpen() {
		return rightBankOpen;
	}

	public void setRightBankOpen(String rightBankOpen) {
		this.rightBankOpen = rightBankOpen == null ? null : rightBankOpen.trim();
	}

	public String getRightBankHolder() {
		return rightBankHolder;
	}

	public void setRightBankHolder(String rightBankHolder) {
		this.rightBankHolder = rightBankHolder == null ? null : rightBankHolder.trim();
	}

	public String getRightBankAccount() {
		return rightBankAccount;
	}

	public void setRightBankAccount(String rightBankAccount) {
		this.rightBankAccount = rightBankAccount == null ? null : rightBankAccount.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public Integer getVerifyId() {
		return verifyId;
	}

	public void setVerifyId(Integer verifyId) {
		this.verifyId = verifyId;
	}

	public Integer getBookingGuideId() {
		return bookingGuideId;
	}

	public void setBookingGuideId(Integer bookingGuideId) {
		this.bookingGuideId = bookingGuideId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	
	public Integer getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(Integer detailCount) {
		this.detailCount = detailCount;
	}
}