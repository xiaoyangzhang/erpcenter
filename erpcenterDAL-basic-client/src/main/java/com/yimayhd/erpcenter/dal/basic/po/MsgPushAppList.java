package com.yimayhd.erpcenter.dal.basic.po;

import java.io.Serializable;
import java.util.Date;

public class MsgPushAppList implements Serializable {
    private Integer id;

    private Integer pushId;

    private Byte receiverType;

    private String receiverName;

    private String receiverIdentity;

    private Byte readState;

    private Date readTime;

    private Byte deleteState;

    private Date deleteTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPushId() {
        return pushId;
    }

    public void setPushId(Integer pushId) {
        this.pushId = pushId;
    }

    public Byte getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(Byte receiverType) {
        this.receiverType = receiverType;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverIdentity() {
        return receiverIdentity;
    }

    public void setReceiverIdentity(String receiverIdentity) {
        this.receiverIdentity = receiverIdentity == null ? null : receiverIdentity.trim();
    }

    public Byte getReadState() {
        return readState;
    }

    public void setReadState(Byte readState) {
        this.readState = readState;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public Byte getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(Byte deleteState) {
        this.deleteState = deleteState;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}