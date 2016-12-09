package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by DELL on 2016/12/9.
 */
public class TourGroupForCarCar implements Serializable {
    private static final long serialVersionUID = 2412973487524417016L;

    private Integer supplierId;
    private String supplierName;
    private TourGroup tourGroup;
    private String operatorMobile;
    private Integer guideId;
    private String guideName;
    private String guideMobile;

    public String getOperatorMobile() {
        return operatorMobile;
    }

    public void setOperatorMobile(String operatorMobile) {
        this.operatorMobile = operatorMobile;
    }

    public String getGuideMobile() {
        return guideMobile;
    }

    public void setGuideMobile(String guideMobile) {
        this.guideMobile = guideMobile;
    }

    public Integer getGuideId() {
        return guideId;
    }

    public void setGuideId(Integer guideId) {
        this.guideId = guideId;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public TourGroup getTourGroup() {
        return tourGroup;
    }

    public void setTourGroup(TourGroup tourGroup) {
        tourGroup = tourGroup;
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




}
