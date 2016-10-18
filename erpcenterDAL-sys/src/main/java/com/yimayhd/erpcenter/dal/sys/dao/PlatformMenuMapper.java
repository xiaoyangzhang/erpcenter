package com.yimayhd.erpcenter.dal.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.sys.po.PlatformMenuPo;

public interface PlatformMenuMapper {

	List<PlatformMenuPo> getPlatformMenuListBysysId(Integer sysId);

	List<PlatformMenuPo> getPlatformMenuListBysysIdAndParentId(@Param("sysId")int sysId,
			@Param("parentId")int parentId);

	int addPlatformMenu(PlatformMenuPo platformMenu);

	int updatePlatformMenu(PlatformMenuPo platformMenu);

	int deletePlatformMenuByMenuId(Integer menuId);

	void deletePlatformMenuByParentId(Integer parentId);

	PlatformMenuPo getPlatformMenuMenuId(Integer menuId);

	List<PlatformMenuPo> getMenuList(@Param("sysId")Integer sysId,@Param("parentId")Integer parentId,@Param("roleIds")String roleIds);
	
	List<PlatformMenuPo> getAllMenuList(@Param("sysId")Integer sysId,@Param("parentId")Integer parentId);
	
	List<PlatformMenuPo> getMenuListByBizId(@Param("bizId")Integer bizId,@Param("parentId")Integer parentId);
	
	PlatformMenuPo getMenuParent(@Param("sysId")Integer sysId);

	List<PlatformMenuPo> getPlatformMenuJosnList(@Param("sysId")Integer sysId);
	
	List<PlatformMenuPo> getMenuListExceptMenuId(@Param("parentId")int parentMenuId,@Param("name")String menuName, @Param("menuId")int exceptMenuId);
	
	//管理员获取当前商家菜单列表
	List<PlatformMenuPo> getAdminMenuList(@Param("bizId")Integer bizId,@Param("resourceType") Integer resourceType,@Param("parentId")Integer parentId);
	//普通人员获取当前商家当前人所属角色的菜单列表
	List<PlatformMenuPo> getMenuListByRoleIds(@Param("bizId")Integer bizId,@Param("roleIds")String roleIds,@Param("resourceType") Integer resourceType,@Param("parentId")Integer parentId);
	
	void addPlatformMenuAndBizLink(PlatformMenuPo po);
	void updatePlatformMenuAndBiz(PlatformMenuPo menu);

	int deleteSysMenuByBizId(Integer bizId);
}
