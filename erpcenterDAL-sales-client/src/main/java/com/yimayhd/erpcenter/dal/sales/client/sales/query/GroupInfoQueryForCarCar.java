package com.yimayhd.erpcenter.dal.sales.client.sales.query;

import java.io.Serializable;

/**
 * Created by DELL on 2016/12/9.
 */
public class GroupInfoQueryForCarCar implements Serializable {

    private static final long serialVersionUID = -7936710476334760862L;
    private long startTime;
    private long endTime;
    private String supplierName;
    private String bizIds;

    public String getBizIds() {
        return bizIds;
    }

    public void setBizIds(String bizIds) {
        this.bizIds = bizIds;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
