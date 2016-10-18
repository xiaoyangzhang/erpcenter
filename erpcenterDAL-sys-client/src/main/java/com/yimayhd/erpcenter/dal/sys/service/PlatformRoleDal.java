package com.yimayhd.erpcenter.dal.sys.service;


import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.po.PlatformRoleMenuLinkPo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformRolePo;

public interface PlatformRoleDal {

	/**
	 * 分页查询角色
	 * @param platformRolePo
	 * @param page
	 * @return
	 */
	public PageBean getRoleList(PlatformRolePo platformRolePo ,Integer page);
	/**
	 * 根据角色id查询角色
	 * @param roleId
	 * @return
	 */
	public PlatformRolePo findByRoleId(Integer roleId);
	/**
	 * 新增/修改角色
	 * @param platformRolePo
	 * @return
	 */
	public int saveRole(PlatformRolePo platformRolePo);
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
	public List<PlatformRolePo> getRoleList(Integer bizId ,Integer employeeId,Integer isSuper);
	/**
	 * @author : zhangchao
	 * @date : 2015年6月2日 下午4:33:06
	 * @Description: 根据角色ID 和  菜单的ID查询
	 */
	public List<PlatformRoleMenuLinkPo> findPlatformRoleMenuLinkPoByRoleIdAndMenuId(
			Integer roleId, Integer menuId);
	/**
	 * @author : zhangchao
	 * @date : 2015年6月3日 上午11:09:29
	 * @Description: 过呢据菜单ID 查询是否有角色选择
	 */
	public List<PlatformRoleMenuLinkPo> findPlatformRoleMenuLinkPoByMenuId(String menuId);
	/**
	 * 查询角色是否已经存在
	 * @param roleName
	 * @param exceptRoleId
	 * @return
	 */
	public List<PlatformRolePo> getRoleList(String roleName,int exceptRoleId);
	void copyRole(Integer roleId);
}
