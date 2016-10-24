package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.PlatformRolePo;
/**
 * 
 * 描述：角色对象的封装
 * @author liyong
 * 2016年10月21日
 */
public class PlatformRolePoResult extends ResultSupport implements Serializable {
	
	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	private PlatformRolePo platformRolePo = new PlatformRolePo();
	
	public void setPlatformRolePo(PlatformRolePo platformRolePo) {
		this.platformRolePo = platformRolePo;
	}

	public PlatformRolePo getPlatformRolePo() {
		return platformRolePo;
	}
}
