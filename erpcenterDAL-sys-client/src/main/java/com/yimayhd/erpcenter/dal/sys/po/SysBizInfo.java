package com.yimayhd.erpcenter.dal.sys.po;

import java.io.Serializable;
import java.util.Date;

public class SysBizInfo implements Serializable{
    private Integer id;

    private String name;

    private String code;

    private Integer userCountLimit;

    private Byte isDateLimit;

    private Date dateLimit;

    private Date dateRegister;

    private Integer creatorId;

    private String creatorName;
    
    private String logo;

    private Long createTime;
    private Byte state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getUserCountLimit() {
        return userCountLimit;
    }

    public void setUserCountLimit(Integer userCountLimit) {
        this.userCountLimit = userCountLimit;
    }

    public Byte getIsDateLimit() {
        return isDateLimit;
    }

    public void setIsDateLimit(Byte isDateLimit) {
        this.isDateLimit = isDateLimit;
    }

    public Date getDateLimit() {
        return dateLimit;
    }

    public void setDateLimit(Date dateLimit) {
        this.dateLimit = dateLimit;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
}