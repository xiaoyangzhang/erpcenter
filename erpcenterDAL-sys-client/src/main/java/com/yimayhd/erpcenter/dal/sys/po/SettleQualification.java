package com.yimayhd.erpcenter.dal.sys.po;

import java.io.Serializable;

public class SettleQualification implements Serializable {
    private Integer id;

    private Integer applyId;

    private Integer credentialId;

    private String credentialName;

    private String credentialPath;

    private Long createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getCredentialName() {
        return credentialName;
    }

    public void setCredentialName(String credentialName) {
        this.credentialName = credentialName == null ? null : credentialName.trim();
    }

    public String getCredentialPath() {
        return credentialPath;
    }

    public void setCredentialPath(String credentialPath) {
        this.credentialPath = credentialPath == null ? null : credentialPath.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}