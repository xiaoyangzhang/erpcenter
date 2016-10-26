package com.yimayhd.erpcenter.dal.sales.client.finance.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Administrator
 *
 */
public class CheckSheetFinance implements Serializable {

	private Integer id;

	private Integer groupId;
	//订单id
	private Integer bookingId;
	//关联booking_supplier表的id
	private Integer bookingIdLink;
	//报账总额
	private BigDecimal total;
	//方式   签单  现付
	private String cashType;
	//商家类别
	private Integer supplierType;
	//商家名称
	private String supplierName;
	//商家id
    private Integer supplierId;
    //日期
    private Date bookingDate;
    //备注
    private String remark;
    //类别 其他收入和其他支出，其他收入1，其他支出2，默认为0
    private Integer subType;
    //数量
    private Double itemNum;
    //价格
    private Double itemPrice;
    //免去
    private Double itemNumMinus;
    //金额
    private Double itemTotal;
    
    private Integer type1Id;

    private String type1Name;

    private Integer type2Id;

    private String type2Name;

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
		this.cashType = cashType;
	}

	public Integer getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(Integer supplierType) {
		this.supplierType = supplierType;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSubType() {
		return subType;
	}

	public void setSubType(Integer subType) {
		this.subType = subType;
	}

	public Double getItemNum() {
		return itemNum;
	}

	public void setItemNum(Double itemNum) {
		this.itemNum = itemNum;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Double getItemNumMinus() {
		return itemNumMinus;
	}

	public void setItemNumMinus(Double itemNumMinus) {
		this.itemNumMinus = itemNumMinus;
	}

	public Double getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(Double itemTotal) {
		this.itemTotal = itemTotal;
	}

	public Integer getType1Id() {
		return type1Id;
	}

	public void setType1Id(Integer type1Id) {
		this.type1Id = type1Id;
	}

	public String getType1Name() {
		return type1Name;
	}

	public void setType1Name(String type1Name) {
		this.type1Name = type1Name;
	}

	public Integer getType2Id() {
		return type2Id;
	}

	public void setType2Id(Integer type2Id) {
		this.type2Id = type2Id;
	}

	public String getType2Name() {
		return type2Name;
	}

	public void setType2Name(String type2Name) {
		this.type2Name = type2Name;
	}
    
}
