package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;

public class TrafficChangeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int resId;
	private String type;
	private String numType;
	private int num;
	private PlatformEmployeePo curUser;
	private int userId;
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumType() {
		return numType;
	}
	public void setNumType(String numType) {
		this.numType = numType;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public PlatformEmployeePo getCurUser() {
		return curUser;
	}
	public void setCurUser(PlatformEmployeePo curUser) {
		this.curUser = curUser;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
