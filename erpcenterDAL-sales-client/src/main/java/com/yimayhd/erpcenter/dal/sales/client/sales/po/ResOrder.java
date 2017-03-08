package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;

/**
 * Created by zhangxy on 2017/2/9.
 */
public class ResOrder implements Serializable {
    private static final long serialVersionUID = -3152028923235715998L;

    private Integer trpId;
    private Integer orgId;
    private String departureDate;
    private Integer numAdult;
    private     Integer numChild;
    private Integer numBady;
    private Integer employeeId;
    private String employeeName;

    private Integer bizId;
    private Integer resId;
    private Integer mappingSupplierId;
    private Integer productId;
    private     Integer type;
    private String totalPrice;
    private String adultPrice;
    private String childPrice;
    private     String badyPrice;
    private String remark;

    public Integer getTrpId() {
        return trpId;
    }

    public void setTrpId(Integer trpId) {
        this.trpId = trpId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public Integer getNumAdult() {
        return numAdult;
    }

    public void setNumAdult(Integer numAdult) {
        this.numAdult = numAdult;
    }

    public Integer getNumChild() {
        return numChild;
    }

    public void setNumChild(Integer numChild) {
        this.numChild = numChild;
    }

    public Integer getNumBady() {
        return numBady;
    }

    public void setNumBady(Integer numBady) {
        this.numBady = numBady;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public Integer getMappingSupplierId() {
        return mappingSupplierId;
    }

    public void setMappingSupplierId(Integer mappingSupplierId) {
        this.mappingSupplierId = mappingSupplierId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(String adultPrice) {
        this.adultPrice = adultPrice;
    }

    public String getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(String childPrice) {
        this.childPrice = childPrice;
    }

    public String getBadyPrice() {
        return badyPrice;
    }

    public void setBadyPrice(String badyPrice) {
        this.badyPrice = badyPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
