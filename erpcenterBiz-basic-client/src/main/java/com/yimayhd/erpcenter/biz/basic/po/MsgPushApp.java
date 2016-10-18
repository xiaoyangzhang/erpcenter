package com.yimayhd.erpcenter.biz.basic.po;

import java.io.Serializable;
import java.util.Date;

public class MsgPushApp implements Serializable {
    private Integer id;

    private Integer bizId;

    private Integer groupId;

    private String content;

    private Date createTime;

    private Byte creatorType;

    private Integer creatorId;

    private String creatorName;

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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getCreatorType() {
        return creatorType;
    }

    public void setCreatorType(Byte creatorType) {
        this.creatorType = creatorType;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }
}