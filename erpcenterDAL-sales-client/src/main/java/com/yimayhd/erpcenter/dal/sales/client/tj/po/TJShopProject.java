package com.yimayhd.erpcenter.dal.sales.client.tj.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TJShopProject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer bizId;
	private Integer groupId;
	private String groupCode;
	private Integer groupState;
	private Integer operatorId;
	private Integer operatorCompanyId;
	private String  operatorName;
	private Date dateStart;
	private Date dateEnd;
	private Integer departmentId;
	private Integer supplierId;
	private String supplierName;
	private Integer guideId;
	private Date shopDate;
	private Integer personNumFact;
	private Integer personNum;
	private BigDecimal totalMoney;
	private BigDecimal totalFace;
	private Integer goodsId;
	private String goodsName;
	private BigDecimal buyTotal;
	private BigDecimal repayTotal;
	private Date createTime; 
	private String productName;
	private String productBrandName;
	private Integer bookingId;
	private Integer detailId;
	private BigDecimal repayVal;
	private Integer groupMode;
	private Integer saleOperatorId;
	
	
	
	public BigDecimal getRepayVal() {
		return repayVal;
	}
	public void setRepayVal(BigDecimal repayVal) {
		this.repayVal = repayVal;
	}
	public Integer getDetailId() {
		return detailId;
	}
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public Integer getGroupState() {
		return groupState;
	}
	public void setGroupState(Integer groupState) {
		this.groupState = groupState;
	}
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Integer getOperatorCompanyId() {
		return operatorCompanyId;
	}
	public void setOperatorCompanyId(Integer operatorCompanyId) {
		this.operatorCompanyId = operatorCompanyId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
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
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Integer getGuideId() {
		return guideId;
	}
	public void setGuideId(Integer guideId) {
		this.guideId = guideId;
	}
	public Date getShopDate() {
		return shopDate;
	}
	public void setShopDate(Date shopDate) {
		this.shopDate = shopDate;
	}
	public Integer getPersonNumFact() {
		return personNumFact;
	}
	public void setPersonNumFact(Integer personNumFact) {
		this.personNumFact = personNumFact;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public BigDecimal getBuyTotal() {
		return buyTotal;
	}
	public void setBuyTotal(BigDecimal buyTotal) {
		this.buyTotal = buyTotal;
	}
	public BigDecimal getRepayTotal() {
		return repayTotal;
	}
	public void setRepayTotal(BigDecimal repayTotal) {
		this.repayTotal = repayTotal;
	}
	public Integer getPersonNum() {
		return personNum;
	}
	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}
	public BigDecimal getTotalFace() {
		return totalFace;
	}
	public void setTotalFace(BigDecimal totalFace) {
		this.totalFace = totalFace;
	}
	public Integer getGroupMode() {
		return groupMode;
	}
	public void setGroupMode(Integer groupMode) {
		this.groupMode = groupMode;
	}
	public Integer getSaleOperatorId() {
		return saleOperatorId;
	}
	public void setSaleOperatorId(Integer saleOperatorId) {
		this.saleOperatorId = saleOperatorId;
	}
	
	
}
