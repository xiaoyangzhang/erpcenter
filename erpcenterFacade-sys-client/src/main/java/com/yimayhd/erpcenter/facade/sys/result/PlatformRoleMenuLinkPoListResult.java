package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.PlatformRoleMenuLinkPo;
/**
 * 
 * 描述：角色菜单关系对象结果
 * @author liyong
 * 2016年10月21日
 */
public class PlatformRoleMenuLinkPoListResult extends ResultSupport implements Serializable {
	
	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 2402647226627831887L;
	private List<PlatformRoleMenuLinkPo> platformRoleMenuLinkPos = new ArrayList<PlatformRoleMenuLinkPo>();
	public void setPlatformRoleMenuLinkPos(List<PlatformRoleMenuLinkPo> platformRoleMenuLinkPos) {
		this.platformRoleMenuLinkPos = platformRoleMenuLinkPos;
	}
	
	public List<PlatformRoleMenuLinkPo> getPlatformRoleMenuLinkPos() {
		return platformRoleMenuLinkPos;
	}

}
