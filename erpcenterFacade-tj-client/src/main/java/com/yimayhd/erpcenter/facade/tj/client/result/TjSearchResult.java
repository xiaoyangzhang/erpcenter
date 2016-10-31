package com.yimayhd.erpcenter.facade.tj.client.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;

public class TjSearchResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<DicInfo> pp;
	private List<DicInfo> sourceTypeList;
	private int bizId;
	private String orgJsonStr;
	private String orgUserJsonStr;
	public List<DicInfo> getPp() {
		return pp;
	}
	public void setPp(List<DicInfo> pp) {
		this.pp = pp;
	}
	public int getBizId() {
		return bizId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
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
	public List<DicInfo> getSourceTypeList() {
		return sourceTypeList;
	}
	public void setSourceTypeList(List<DicInfo> sourceTypeList) {
		this.sourceTypeList = sourceTypeList;
	}
	
}
