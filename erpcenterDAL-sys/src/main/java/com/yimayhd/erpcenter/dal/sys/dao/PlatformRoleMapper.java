package com.yimayhd.erpcenter.dal.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.po.PlatformRoleMenuLinkPo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformRolePo;

public interface PlatformRoleMapper {

	/**
	 * 查询角色信息
	 * @param platformRolePo
	 * @return
	 */
	public List<PlatformRolePo> getRoleListPage(PageBean pageBean);
	/**
	 * 根据角色id查询角色
	 * @param roleId
	 * @return
	 */
	public PlatformRolePo  findByRoleId(Integer roleId);
	/**
	 * 增加角色信息
	 */
	public int addRole(PlatformRolePo platformRolePo);
	/**
	 * 更新角色信息
	 * @param platformRolePo
	 * @return
	 */
	public int updateRole(PlatformRolePo platformRolePo);
	/**
	 * 根据角色id删除角色
	 * @param roleId
	 * @return
	 */
	public int delByRoleId(Integer roleId);
	/**
	 * 根据系统id和用户id获取角色信息
	 * @param sysId
	 * @param employeeId
	 * @return
	 */
	public List<PlatformRolePo> getRoleList(@Param("bizId")Integer bizId, @Param("employeeId")Integer employeeId);
	/**
	 * 根据系统id获取角色信息
	 * @param sysId
	 * @param employeeId
	 * @return
	 */
	public List<PlatformRolePo> getAllRoleList(@Param("bizId")Integer bizId);
	/**
	 * @author : zhangchao
	 * @date : 2015年6月1日 下午3:43:21
	 * @Description: 添加角色和 菜单的关联管理
	 */
	public void addPlatformRoleMenuLinkPo(Integer roleId, Integer menuId);
	/**
	 * @author : zhangchao
	 * @date : 2015年6月2日 下午4:34:20
	 * @Description: 根据角色ID和 菜单的ID  查询
	 */
	public List<PlatformRoleMenuLinkPo> findPlatformRoleMenuLinkPoByRoleIdAndMenuId(
			@Param("roleId")Integer roleId, @Param("menuId")Integer menuId);
	
	public List<PlatformRoleMenuLinkPo> findPlatformRoleMenuLinkPoByRoleId(
			@Param("roleId")Integer roleId);
	/**
	 * @author : zhangchao
	 * @date : 2015年6月3日 上午9:23:00
	 * @Description: 根据角色ID删除权限菜单关联表
	 */
	public void delPlatformRoleMenuLinkPoByRoleId(@Param("roleId")Integer roleId);
	/**
	 * @author : zhangchao
	 * @date : 2015年6月3日 上午11:10:34
	 * @Description: 根据菜单ID
	 */
	public List<PlatformRoleMenuLinkPo> findPlatformRoleMenuLinkPoByMenuId(@Param("menuId")String menuId);
	
	public List<PlatformRolePo> getRoleListExceptId(@Param("name")String roleName,@Param("roleId")int exceptRoleId);
	//Integer copyRole(@Param("roleId")Integer roleId);
}
