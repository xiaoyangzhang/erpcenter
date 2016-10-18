package com.yimayhd.erpcenter.dal.sys.po;

import java.io.Serializable;

/**
 * @author : zhangchao
 * @date : 2015年6月1日 下午1:36:36
 * @Description: 角色和菜单关联实体
 */
public class PlatformRoleMenuLinkPo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer roleId;
	private Integer menuId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	
	

}
