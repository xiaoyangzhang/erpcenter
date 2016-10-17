package com.yimayhd.erpcenter.dal.product.po;

import java.io.Serializable;

public class ProductTag implements Serializable{

    private static final long serialVersionUID = 3896868112603223932L;

    private Integer id;

    private Integer productId;

    private String tagType;

    private Integer tagId;

    private String tagName;

    private Integer isExtra;

    private Long createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType == null ? null : tagType.trim();
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    public Integer getIsExtra() {
        return isExtra;
    }

    public void setIsExtra(Integer isExtra) {
        this.isExtra = isExtra;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}