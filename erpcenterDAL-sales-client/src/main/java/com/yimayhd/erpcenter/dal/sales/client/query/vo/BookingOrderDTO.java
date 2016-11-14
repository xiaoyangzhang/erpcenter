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
	private String tgGroupCode;
	private String tgOperatorName;
	private String bsdType1Name;
	private String bsdTicketFlight;
	private String bsdCarLisence;
	private String bsdDriverName;
	private String bsdType2Name;
	private Date bsBookingDate;
	private Date bsdItemDateTo;
	private Integer bgId;
	private String bgGuideMobile;
	private String bgGuideName;
	private Integer bgGroupId;
	
	public Integer getBgId() {
		return bgId;
	}
	public void setBgId(Integer bgId) {
		this.bgId = bgId;
	}
	public String getBgGuideMobile() {
		return bgGuideMobile;
	}
	public void setBgGuideMobile(String bgGuideMobile) {
		this.bgGuideMobile = bgGuideMobile;
	}
	public String getBgGuideName() {
		return bgGuideName;
	}
	public void setBgGuideName(String bgGuideName) {
		this.bgGuideName = bgGuideName;
	}
	public Integer getBgGroupId() {
		return bgGroupId;
	}
	public void setBgGroupId(Integer bgGroupId) {
		this.bgGroupId = bgGroupId;
	}
	public Date getBsdItemDateTo() {
		return bsdItemDateTo;
	}
	public void setBsdItemDateTo(Date bsdItemDateTo) {
		this.bsdItemDateTo = bsdItemDateTo;
	}
	private String bsdItemBrief;
	
	private Integer tgGroupCodeSort;
	private Long bsCreateTime;
	private Integer tgTotalAdult;
	private Integer tgTotalChild;
	private Integer tgTotalGuide;
	private BigDecimal bsdItemTotal;
	private BigDecimal bsdItemPrice;
	private String bsRemark;
	private String bsdTicketDeparture;
	private String bsdTicketArrival;
	private String bsdDriverTel;
	
	public String getTgGroupCode() {
		return tgGroupCode;
	}
	public void setTgGroupCode(String tgGroupCode) {
		this.tgGroupCode = tgGroupCode;
	}
	public String getTgOperatorName() {
		return tgOperatorName;
	}
	public void setTgOperatorName(String tgOperatorName) {
		this.tgOperatorName = tgOperatorName;
	}
	public String getBsdType1Name() {
		return bsdType1Name;
	}
	public void setBsdType1Name(String bsdType1Name) {
		this.bsdType1Name = bsdType1Name;
	}
	public String getBsdTicketFlight() {
		return bsdTicketFlight;
	}
	public void setBsdTicketFlight(String bsdTicketFlight) {
		this.bsdTicketFlight = bsdTicketFlight;
	}
	public String getBsdCarLisence() {
		return bsdCarLisence;
	}
	public void setBsdCarLisence(String bsdCarLisence) {
		this.bsdCarLisence = bsdCarLisence;
	}
	public String getBsdDriverName() {
		return bsdDriverName;
	}
	public void setBsdDriverName(String bsdDriverName) {
		this.bsdDriverName = bsdDriverName;
	}
	public String getBsdType2Name() {
		return bsdType2Name;
	}
	public void setBsdType2Name(String bsdType2Name) {
		this.bsdType2Name = bsdType2Name;
	}
	public Date getBsBookingDate() {
		return bsBookingDate;
	}
	public void setBsBookingDate(Date bsBookingDate) {
		this.bsBookingDate = bsBookingDate;
	}
	public String getBsdItemBrief() {
		return bsdItemBrief;
	}
	public void setBsdItemBrief(String bsdItemBrief) {
		this.bsdItemBrief = bsdItemBrief;
	}
	public Integer getTgGroupCodeSort() {
		return tgGroupCodeSort;
	}
	public void setTgGroupCodeSort(Integer tgGroupCodeSort) {
		this.tgGroupCodeSort = tgGroupCodeSort;
	}
	public Long getBsCreateTime() {
		return bsCreateTime;
	}
	public void setBsCreateTime(Long bsCreateTime) {
		this.bsCreateTime = bsCreateTime;
	}
	public Integer getTgTotalAdult() {
		return tgTotalAdult;
	}
	public void setTgTotalAdult(Integer tgTotalAdult) {
		this.tgTotalAdult = tgTotalAdult;
	}
	public Integer getTgTotalChild() {
		return tgTotalChild;
	}
	public void setTgTotalChild(Integer tgTotalChild) {
		this.tgTotalChild = tgTotalChild;
	}
	public Integer getTgTotalGuide() {
		return tgTotalGuide;
	}
	public void setTgTotalGuide(Integer tgTotalGuide) {
		this.tgTotalGuide = tgTotalGuide;
	}
	public BigDecimal getBsdItemTotal() {
		return bsdItemTotal;
	}
	public void setBsdItemTotal(BigDecimal bsdItemTotal) {
		this.bsdItemTotal = bsdItemTotal;
	}
	public BigDecimal getBsdItemPrice() {
		return bsdItemPrice;
	}
	public void setBsdItemPrice(BigDecimal bsdItemPrice) {
		this.bsdItemPrice = bsdItemPrice;
	}
	public String getBsRemark() {
		return bsRemark;
	}
	public void setBsRemark(String bsRemark) {
		this.bsRemark = bsRemark;
	}
	public String getBsdTicketDeparture() {
		return bsdTicketDeparture;
	}
	public void setBsdTicketDeparture(String bsdTicketDeparture) {
		this.bsdTicketDeparture = bsdTicketDeparture;
	}
	public String getBsdTicketArrival() {
		return bsdTicketArrival;
	}
	public void setBsdTicketArrival(String bsdTicketArrival) {
		this.bsdTicketArrival = bsdTicketArrival;
	}
	public String getBsdDriverTel() {
		return bsdDriverTel;
	}
	public void setBsdDriverTel(String bsdDriverTel) {
		this.bsdDriverTel = bsdDriverTel;
	}
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
