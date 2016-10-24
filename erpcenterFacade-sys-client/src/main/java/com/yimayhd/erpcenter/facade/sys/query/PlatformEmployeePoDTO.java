package com.yimayhd.erpcenter.facade.sys.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
/**
 * 
 * 描述：PlatformEmployeePo参数分装类
 * @author liyong
 * 2016年10月21日
 */
public class PlatformEmployeePoDTO implements Serializable{
	
	/**
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	
	private PlatformEmployeePo platformEmployeePo = new PlatformEmployeePo();

	public PlatformEmployeePo getPlatformEmployeePo() {
		return platformEmployeePo;
	}
	
	public void setPlatformEmployeePo(PlatformEmployeePo platformEmployeePo) {
		this.platformEmployeePo = platformEmployeePo;
	}
}
