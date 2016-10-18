package com.yimayhd.erpcenter.dal.sys.po;

import java.io.Serializable;

public class SysBizConfig implements Serializable {
    private Integer id;

    private Integer bizId;

    private String itemCode;

    private String itemValue;

    private String itemDesp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue == null ? null : itemValue.trim();
    }

    public String getItemDesp() {
        return itemDesp;
    }

    public void setItemDesp(String itemDesp) {
        this.itemDesp = itemDesp == null ? null : itemDesp.trim();
    }
}