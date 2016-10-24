package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import java.io.Serializable;
import java.util.Date;

public class BookingGuideSheet implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -296598464504014599L;

	private Integer id;

    private Integer bookingId;

    private Integer supplierType;

    private Integer supplierId;

    private Integer applyNum;

    private String applyRemark;

    private Date applyTime;

    private Integer applyUserId;

    private Integer getNum;

    private String getRemark;

    private String getCodes;

    private Integer getUserId;

    private Date getTime;

    private Integer returnNum;

    private String returnRemark;

    private String returnCodes;

    private Integer returnUserId;

    private Date returnTime;

    private String column19;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
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

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public String getApplyRemark() {
        return applyRemark;
    }

    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark == null ? null : applyRemark.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Integer applyUserId) {
        this.applyUserId = applyUserId;
    }

    public Integer getGetNum() {
        return getNum;
    }

    public void setGetNum(Integer getNum) {
        this.getNum = getNum;
    }

    public String getGetRemark() {
        return getRemark;
    }

    public void setGetRemark(String getRemark) {
        this.getRemark = getRemark == null ? null : getRemark.trim();
    }

    public String getGetCodes() {
        return getCodes;
    }

    public void setGetCodes(String getCodes) {
        this.getCodes = getCodes == null ? null : getCodes.trim();
    }

    public Integer getGetUserId() {
        return getUserId;
    }

    public void setGetUserId(Integer getUserId) {
        this.getUserId = getUserId;
    }

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public Integer getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(Integer returnNum) {
        this.returnNum = returnNum;
    }

    public String getReturnRemark() {
        return returnRemark;
    }

    public void setReturnRemark(String returnRemark) {
        this.returnRemark = returnRemark == null ? null : returnRemark.trim();
    }

    public String getReturnCodes() {
        return returnCodes;
    }

    public void setReturnCodes(String returnCodes) {
        this.returnCodes = returnCodes == null ? null : returnCodes.trim();
    }

    public Integer getReturnUserId() {
        return returnUserId;
    }

    public void setReturnUserId(Integer returnUserId) {
        this.returnUserId = returnUserId;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public String getColumn19() {
        return column19;
    }

    public void setColumn19(String column19) {
        this.column19 = column19 == null ? null : column19.trim();
    }
}