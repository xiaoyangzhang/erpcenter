package com.yimayhd.erpcenter.dal.sales.client.query.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BookingOrderDTO implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	private Integer tgId;
	private Integer bsId;
	private Integer bsdId;
	private Date itemDate;
	private Integer bsdType1Id;
	private Integer groupMode;
	private Date dateStart;
	private Date dateEnd;
	private String productBrandName;
	private String productName;
	private Integer tgOperatorId;
	private Integer bsUserId;
	private Integer bsSupplierId;
	private Integer bsSupplierType;
	private String bsSupplierName;
	private BigDecimal bsTotal;
	private BigDecimal bsTotalCash;
	private Integer bsdItemNum;
	private Integer bsdItemNumMinus;
	private String bsCashType;
	private Integer bizId;
	private Integer groupState;
	public Integer getTgId() {
		return tgId;
	}
	public void setTgId(Integer tgId) {
		this.tgId = tgId;
	}
	public Integer getBsId() {
		return bsId;
	}
	public void setBsId(Integer bsId) {
		this.bsId = bsId;
	}
	public Integer getBsdId() {
		return bsdId;
	}
	public void setBsdId(Integer bsdId) {
		this.bsdId = bsdId;
	}
	public Date getItemDate() {
		return itemDate;
	}
	public void setItemDate(Date itemDate) {
		this.itemDate = itemDate;
	}
	
	public Integer getBsdType1Id() {
		return bsdType1Id;
	}
	public void setBsdType1Id(Integer bsdType1Id) {
		this.bsdType1Id = bsdType1Id;
	}
	public Integer getGroupMode() {
		return groupMode;
	}
	public void setGroupMode(Integer groupMode) {
		this.groupMode = groupMode;
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
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getTgOperatorId() {
		return tgOperatorId;
	}
	public void setTgOperatorId(Integer tgOperatorId) {
		this.tgOperatorId = tgOperatorId;
	}
	public Integer getBsUserId() {
		return bsUserId;
	}
	public void setBsUserId(Integer bsUserId) {
		this.bsUserId = bsUserId;
	}
	public Integer getBsSupplierId() {
		return bsSupplierId;
	}
	public void setBsSupplierId(Integer bsSupplierId) {
		this.bsSupplierId = bsSupplierId;
	}
	public Integer getBsSupplierType() {
		return bsSupplierType;
	}
	public void setBsSupplierType(Integer bsSupplierType) {
		this.bsSupplierType = bsSupplierType;
	}
	public String getBsSupplierName() {
		return bsSupplierName;
	}
	public void setBsSupplierName(String bsSupplierName) {
		this.bsSupplierName = bsSupplierName;
	}
	public BigDecimal getBsTotal() {
		return bsTotal;
	}
	public void setBsTotal(BigDecimal bsTotal) {
		this.bsTotal = bsTotal;
	}
	public BigDecimal getBsTotalCash() {
		return bsTotalCash;
	}
	public void setBsTotalCash(BigDecimal bsTotalCash) {
		this.bsTotalCash = bsTotalCash;
	}
	public Integer getBsdItemNum() {
		return bsdItemNum;
	}
	public void setBsdItemNum(Integer bsdItemNum) {
		this.bsdItemNum = bsdItemNum;
	}
	public Integer getBsdItemNumMinus() {
		return bsdItemNumMinus;
	}
	public void setBsdItemNumMinus(Integer bsdItemNumMinus) {
		this.bsdItemNumMinus = bsdItemNumMinus;
	}
	public String getBsCashType() {
		return bsCashType;
	}
	public void setBsCashType(String bsCashType) {
		this.bsCashType = bsCashType;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Integer getGroupState() {
		return groupState;
	}
	public void setGroupState(Integer groupState) {
		this.groupState = groupState;
	}
	
	
	
	
}
