package com.yimayhd.erpcenter.facade.ticket.query;

import java.util.Map;

import com.yimayhd.erpcenter.facade.ticket.BaseListPage;

public class ShowApplyListDTO extends BaseListPage{
	
	private static final long serialVersionUID = 6707676794554415017L;
	
	private Integer bizId;
	private Map param;
	private String dataUser;
	
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Map getParam() {
		return param;
	}
	public void setParam(Map param) {
		this.param = param;
	}
	public String getDataUser() {
		return dataUser;
	}
	public void setDataUser(String dataUser) {
		this.dataUser = dataUser;
	}
	
}
