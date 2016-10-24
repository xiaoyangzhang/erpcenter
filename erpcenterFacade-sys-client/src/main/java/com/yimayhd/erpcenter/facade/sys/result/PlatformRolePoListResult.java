package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.PlatformRolePo;
/**
 * 
 * 描述：角色对象列表返回结果的封装
 * @author liyong
 * 2016年10月21日
 */
public class PlatformRolePoListResult extends ResultSupport implements Serializable {
	
	/**
	 * 描述：
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
