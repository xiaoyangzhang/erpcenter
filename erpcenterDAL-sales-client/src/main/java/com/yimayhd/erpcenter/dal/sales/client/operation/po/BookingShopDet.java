package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BookingShopDet implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1478135671605461815L;
	 
	private Integer id;

    private Integer groupId;

    private String bookingNo;

    private Integer supplierId;

    private Integer guideId;

    private String shopDate;

    private Integer personNum;
   
   
    private BigDecimal personRepayPrice;//人员返款单价

    private BigDecimal  personRepayTotal;

    private BigDecimal  personBuyAvg;

    private BigDecimal  totalFace;
    private BigDecimal  totalRepay;
    private BigDecimal  totalCash;

    private String shopCode;

    private String remark;

    private Byte stateFinance;

    private Integer userId;
    private String userName;
    private Long createTime;
    private String guideName;
    private String supplierName;
	private Date bookingDate;
	private String auditUser;
	private Integer auditUserId;
	private Date auditTime;
	private Integer personNumFact;//实际进店人数
	
    private BigDecimal  shopBuyTotal;

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

	public String getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getGuideId() {
		return guideId;
	}

	public void setGuideId(Integer guideId) {
		this.guideId = guideId;
	}

	public String getShopDate() {
		return shopDate;
	}

	public void setShopDate(String shopDate) {
		this.shopDate = shopDate;
	}

	public Integer getPersonNum() {
		return personNum;
	}

	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}

	public BigDecimal getPersonRepayPrice() {
		return personRepayPrice;
	}

	public void setPersonRepayPrice(BigDecimal personRepayPrice) {
		this.personRepayPrice = personRepayPrice;
	}

	public BigDecimal getPersonRepayTotal() {
		return personRepayTotal;
	}

	public void setPersonRepayTotal(BigDecimal personRepayTotal) {
		this.personRepayTotal = personRepayTotal;
	}

	public BigDecimal getPersonBuyAvg() {
		return personBuyAvg;
	}

	public void setPersonBuyAvg(BigDecimal personBuyAvg) {
		this.personBuyAvg = personBuyAvg;
	}

	public BigDecimal getTotalFace() {
		return totalFace;
	}

	public void setTotalFace(BigDecimal totalFace) {
		this.totalFace = totalFace;
	}

	public BigDecimal getTotalRepay() {
		return totalRepay;
	}

	public void setTotalRepay(BigDecimal totalRepay) {
		this.totalRepay = totalRepay;
	}

	public BigDecimal getTotalCash() {
		return totalCash;
	}

	public void setTotalCash(BigDecimal totalCash) {
		this.totalCash = totalCash;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Byte getStateFinance() {
		return stateFinance;
	}

	public void setStateFinance(Byte stateFinance) {
		this.stateFinance = stateFinance;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getGuideName() {
		return guideName;
	}

	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	public Integer getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getPersonNumFact() {
		return personNumFact;
	}

	public void setPersonNumFact(Integer personNumFact) {
		this.personNumFact = personNumFact;
	}

	public BigDecimal getShopBuyTotal() {
		return shopBuyTotal;
	}

	public void setShopBuyTotal(BigDecimal shopBuyTotal) {
		this.shopBuyTotal = shopBuyTotal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}