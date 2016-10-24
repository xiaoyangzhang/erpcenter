package com.yimayhd.erpcenter.facade.sys.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.PlatformRolePo;
/**
 * 
 * 描述：List<PlatformRolePo>对象参数的封装
 * @author liyong
 * 2016年10月21日
 */
public class PlatformRolePoListDTO implements  Serializable{
	
	/**
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	private List<PlatformRolePo> platformRolePos = new ArrayList<PlatformRolePo>();
	
	public void setPlatformRolePos(List<PlatformRolePo> platformRolePos) {
		this.platformRolePos = platformRolePos;
	}
	
	public List<PlatformRolePo> getPlatformRolePos() {
		return platformRolePos;
	}

}
