package com.yimayhd.erpcenter.facade.sys.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.PlatformRolePo;
/**
 * 
 * 描述：角色参数的封装
 * @author liyong
 * 2016年10月21日
 */
public class PlatformRolePoDTO implements Serializable {
	
	/**
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
