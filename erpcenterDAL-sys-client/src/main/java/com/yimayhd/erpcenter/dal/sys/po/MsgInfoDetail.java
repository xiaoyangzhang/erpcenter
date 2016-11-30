package com.yimayhd.erpcenter.dal.sys.po;

import java.io.Serializable;

public class MsgInfoDetail implements Serializable {

    private static final long serialVersionUID = 1243026220214242099L;

    private Integer id;
    private Integer msgInfoId;
    private Integer userId;
    private String userName;
    private Integer status;

    private Integer totalCount;
    private Integer readCount;
    private Integer unreadCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMsgInfoId() {
        return msgInfoId;
    }

    public void setMsgInfoId(Integer msgInfoId) {
        this.msgInfoId = msgInfoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }

}