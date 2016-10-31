package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;

public class DetailsStocklogDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int resId;
	private String adjustTime;
	private PlatformEmployeePo curUser;
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public String getAdjustTime() {
		return adjustTime;
	}
	public void setAdjustTime(String adjustTime) {
		this.adjustTime = adjustTime;
	}
	public PlatformEmployeePo getCurUser() {
		return curUser;
	}
	public void setCurUser(PlatformEmployeePo curUser) {
		this.curUser = curUser;
	}
	
}
