package com.yimayhd.erpcenter.facade.sys.service;


import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.po.PlatformRoleMenuLinkPo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformRolePo;
import com.yimayhd.erpcenter.facade.sys.query.PlatformRolePoDTO;
import com.yimayhd.erpcenter.facade.sys.result.PlatformRoleMenuLinkPoListResult;
import com.yimayhd.erpcenter.facade.sys.result.PlatformRolePoListResult;
import com.yimayhd.erpcenter.facade.sys.result.PlatformRolePoResult;

public interface SysPlatformRoleFacade {

	/**
	 * 分页查询角色
	 * @param platformRolePo
	 * @param page
	 * @return
	 */
	public PageBean getRoleList(PlatformRolePoDTO platformRolePo ,Integer page);
	/**
	 * 根据角色id查询角色
	 * @param roleId
	 * @return
	 */
	public PlatformRolePoResult findByRoleId(Integer roleId);
	/**
	 * 新增/修改角色
	 * @param platformRolePo
	 * @return
	 */
	public int saveRole(PlatformRolePoDTO platformRolePo);
	/**
	 * 物理删除角色
	 * @param roleId
	 * @return
	 */
	public int delByRoleid(Integer roleId);
	
	/**
	 * 逻辑删除角色
	 * @param roleId
	 * @return
	 */
	public int deleteByRoleid(Integer roleId);
	
	/**
	 * 根据系统id和用户id获取角色信息
	 * @param sysId
	 * @param employeeId
	 * @return
	 */
	public PlatformRolePoListResult getRoleList(Integer bizId ,Integer employeeId,Integer isSuper);
	/**
	 * @Description: 根据角色ID 和  菜单的ID查询
	 */
	public PlatformRoleMenuLinkPoListResult findPlatformRoleMenuLinkPoByRoleIdAndMenuId(
			Integer roleId, Integer menuId);
	/**
	 * @Description: 过呢据菜单ID 查询是否有角色选择
	 */
	public PlatformRoleMenuLinkPoListResult findPlatformRoleMenuLinkPoByMenuId(String menuId);
	/**
	 * 查询角色是否已经存在
	 * @param roleName
	 * @param exceptRoleId
	 * @return
	 */
	public PlatformRolePoListResult getRoleList(String roleName,int exceptRoleId);
	void copyRole(Integer roleId);
}
