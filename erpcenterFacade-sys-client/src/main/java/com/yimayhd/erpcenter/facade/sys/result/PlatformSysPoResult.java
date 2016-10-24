package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.PlatformSysPo;
/**
 * 
 * 描述：PlatformSysPo结果对象封装
 * @author liyong
 * 2016年10月21日
 */
public class PlatformSysPoResult extends ResultSupport implements Serializable {

	/**
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	private PlatformSysPo platformSysPo = new PlatformSysPo();
	
	public void setPlatformSysPo(PlatformSysPo platformSysPo) {
		this.platformSysPo = platformSysPo;
	}
	
	public PlatformSysPo getPlatformSysPo() {
		return platformSysPo;
	}
}
