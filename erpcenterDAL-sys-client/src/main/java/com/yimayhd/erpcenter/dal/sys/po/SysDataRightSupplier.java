package com.yimayhd.erpcenter.dal.sys.po;

import java.io.Serializable;

public class SysDataRightSupplier implements Serializable {
	private static final long serialVersionUID = -3949208326724619359L;

	private int id;

	private int orgId;

	private int bizId;
	
	private int supplierId;

	public int getBizId() {
		return bizId;
	}

	public void setBizId(int bizId) {
		this.bizId = bizId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}


	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

}
