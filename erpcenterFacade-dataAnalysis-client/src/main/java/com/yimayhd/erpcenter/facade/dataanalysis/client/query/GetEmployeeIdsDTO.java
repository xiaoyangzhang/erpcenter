package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

public class GetEmployeeIdsDTO extends BaseDTO {
	private static final long serialVersionUID = -7559549468564744780L;
	private String orgIds;

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
}
