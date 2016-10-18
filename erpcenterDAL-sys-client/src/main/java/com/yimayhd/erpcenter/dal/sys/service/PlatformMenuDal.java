package com.yimayhd.erpcenter.dal.sys.service;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.sys.po.PlatformMenuPo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformRolePo;
import com.yimayhd.erpcenter.dal.sys.vo.MenuOptVo;


/**
 * @author : zhangchao
 * @date : 2015年5月25日 下午4:33:53
 * @Description: 系统菜单接口
 */
public interface PlatformMenuDal {

	/**
	 * @author : zhangchao
	 * @date : 2015年5月26日 上午9:24:23
	 * @Description: 根据系统ID查询菜单列表
	 */
	public List<PlatformMenuPo> getPlatformMenuListBysysId(Integer sysId);
	/**
	 * @author : zhangchao
	 * @date : 2015年5月26日 上午9:24:36
	 * @Description: 根据系统ID和父ID查询菜单列表
	 */
	public List<PlatformMenuPo> getPlatformMenuListBysysIdAndParentId(Integer sysId,Integer parentId);
	/**
	 * @author : zhangchao
	 * @date : 2015年5月26日 上午11:27:07
	 * @Description: 根据菜单ID查询菜单实体
	 */
	PlatformMenuPo  getPlatformMenuMenuId(Integer menuId);
	/**
	 * @author : zhangchao
	 * @date : 2015年5月26日 上午10:09:19
	 * @Description: 新增菜单
	 */
	public int addPlatformMenu(PlatformMenuPo platformMenu);
	/**
	 * @author : zhangchao
	 * @date : 2015年5月26日 上午10:10:32
	 * @Description: 修改菜单  根据菜单的ID
	 */
	public int updatePlatformMenu(PlatformMenuPo platformMenu);
	/**
	 * @author : zhangchao
	 * @date : 2015年5月26日 上午10:11:58
	 * @Description: 删除菜单   备注：  同时会删除所属子菜单
	 */
	public int deletePlatformMenuByMenuId(Integer menuId);
	/**
	 * @author : zhangchao
	 * @date : 2015年5月26日 上午10:22:21
	 * @Description: 删除所属子菜单
	 */
	public void deletePlatformMenuByParentId(Integer parentId);
	/**
	 * 查询父级菜单下是否有重复的菜单名
	 * @param parentMenuId
	 * @param menuName
	 * @param exceptMenuId
	 * @return
	 */
	public List<PlatformMenuPo> getMenuList(int parentMenuId,String menuName, int exceptMenuId);
	
	public List<PlatformMenuPo> getMenuList(Integer bizId, List<PlatformRolePo> roleList, Integer isSuper);
	
	public List<PlatformMenuPo> getPlatformMenuJosnList(int sysId);
	
	public List<PlatformMenuPo> getAllMenuList(Integer sysId,Integer parentId);
	
	public List<PlatformMenuPo> getMenuListByBizId(Integer bizId,Integer parentId);
	/**
	 * 向菜单和商家关联表添加
	 * @param bizId
	 * @param menuID
	 */
	void deleteSysMenuByBizId(Integer bizId);
	public void addPlatformMenuAndBizLink(Integer bizId,String menuIds);
	//void updatePlatformMenuAndBizLink(PlatformMenuPo menu);
	//public Map<String, Map<String,Boolean>> getOptMaps(int bizId,List<PlatformRolePo> roleList, Integer isSuper);
	public MenuOptVo getOptMaps(int bizId,List<PlatformRolePo> roleList, Integer isSuper);
	
}
