package com.yimayhd.erpcenter.facade.sys.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.PlatformMenuPo;
/**
 * 
 * 描述：菜单参数对象
 * @author liyong
 * 2016年10月21日
 */
public class PlatformMenuPoDTO implements Serializable {
	
	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	private PlatformMenuPo platformMenuPo = new PlatformMenuPo();
	
	public void setPlatformMenuPo(PlatformMenuPo platformMenuPo) {
		this.platformMenuPo = platformMenuPo;
	}
	
	public PlatformMenuPo getPlatformMenuPo() {
		return platformMenuPo;
	}
}
