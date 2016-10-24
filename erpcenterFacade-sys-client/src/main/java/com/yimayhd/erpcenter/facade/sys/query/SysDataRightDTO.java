package com.yimayhd.erpcenter.facade.sys.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.SysDataRight;

public class SysDataRightDTO implements Serializable{

	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	
	private SysDataRight sysDataRight = new SysDataRight();

	public void setSysDataRight(SysDataRight sysDataRight) {
		this.sysDataRight = sysDataRight;
	}
	
	public SysDataRight getSysDataRight() {
		return sysDataRight;
	}
}
