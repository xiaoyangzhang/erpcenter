package com.yimayhd.erpcenter.dal.sys.po;

import java.io.Serializable;

public class PlatAuth  implements Serializable {
    
    private static final long serialVersionUID = 1825764321389589224L;

    private Integer id;

	private Integer bizId;

	private String platName;

	private String appKey;

	private String appSecret;

	private Integer orgId;

	private Integer supplierId;

	private Integer platStatus;

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

	public String getPlatName() {
		return platName;
	}

	public void setPlatName(String platName) {
		this.platName = platName == null ? null : platName.trim();
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey == null ? null : appKey.trim();
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret == null ? null : appSecret.trim();
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getPlatStatus() {
		return platStatus;
	}

	public void setPlatStatus(Integer platStatus) {
		this.platStatus = platStatus;
	}

}