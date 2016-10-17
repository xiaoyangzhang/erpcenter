package com.yimayhd.erpcenter.dal.product.po;

import java.io.Serializable;

public class ProductGroupExtraItem implements Serializable {
    private static final long serialVersionUID = -4965772682762781943L;
    private Integer id;

    private Integer groupId;

    private Integer itemId;

    private String itemName;

    private String remark;

    private Float priceSale;

    private Float priceCost;

    private Long createTime;

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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Float getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(Float priceSale) {
        this.priceSale = priceSale;
    }

    public Float getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(Float priceCost) {
        this.priceCost = priceCost;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}