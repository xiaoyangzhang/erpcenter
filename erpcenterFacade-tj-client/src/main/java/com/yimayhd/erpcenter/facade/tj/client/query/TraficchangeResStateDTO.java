package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;

public class TraficchangeResStateDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int state;
	private PlatformEmployeePo curUser;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public PlatformEmployeePo getCurUser() {
		return curUser;
	}
	public void setCurUser(PlatformEmployeePo curUser) {
		this.curUser = curUser;
	}
	
}
