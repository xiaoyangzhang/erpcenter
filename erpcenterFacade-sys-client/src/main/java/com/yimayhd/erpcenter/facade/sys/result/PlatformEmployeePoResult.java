package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
/**
 * 
 * 描述：PlatformEmployeePo结果封装
 * @author liyong
 * 2016年10月21日
 */
public class PlatformEmployeePoResult extends ResultSupport implements Serializable {
	
	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	private PlatformEmployeePo platformEmployeePo = new PlatformEmployeePo();
	
	public void setPlatformEmployeePo(PlatformEmployeePo platformEmployeePo) {
		this.platformEmployeePo = platformEmployeePo;
	}
	public PlatformEmployeePo getPlatformEmployeePo() {
		return platformEmployeePo;
	}

}
