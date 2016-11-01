package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

public class GetOrgAndUserTreeJsonStrResult extends BaseResult {
	private static final long serialVersionUID = -93836277800211614L;
	private String orgJsonStr;
	private String orgUserJsonStr;

	public String getOrgJsonStr() {
		return orgJsonStr;
	}

	public void setOrgJsonStr(String orgJsonStr) {
		this.orgJsonStr = orgJsonStr;
	}

	public String getOrgUserJsonStr() {
		return orgUserJsonStr;
	}

	public void setOrgUserJsonStr(String orgUserJsonStr) {
		this.orgUserJsonStr = orgUserJsonStr;
	}
}
