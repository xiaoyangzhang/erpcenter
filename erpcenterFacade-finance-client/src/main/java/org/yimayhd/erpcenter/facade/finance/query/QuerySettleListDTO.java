package org.yimayhd.erpcenter.facade.finance.query;

import java.util.Map;

import org.yimayhd.erpcenter.facade.finance.BaseListPage;

public class QuerySettleListDTO extends BaseListPage{
	
	private static final long serialVersionUID = 6707676794554415017L;
	
	private String sl;
	private String rp;
	private String svc;
	private String edit;
	private String feeType; 
	private Integer bizId;
	private Map<String, Object> paramters;
	private Integer supType;
	private String groupId;
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl = sl;
	}
	public String getRp() {
		return rp;
	}
	public void setRp(String rp) {
		this.rp = rp;
	}
	public String getSvc() {
		return svc;
	}
	public void setSvc(String svc) {
		this.svc = svc;
	}
	public String getEdit() {
		return edit;
	}
	public void setEdit(String edit) {
		this.edit = edit;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Map<String, Object> getParamters() {
		return paramters;
	}
	public void setParamters(Map<String, Object> paramters) {
		this.paramters = paramters;
	}
	public Integer getSupType() {
		return supType;
	}
	public void setSupType(Integer supType) {
		this.supType = supType;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	
}
