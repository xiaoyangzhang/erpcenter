package com.yimayhd.erpcenter.biz.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sys.service.PlatformMenuBiz;
import com.yimayhd.erpcenter.dal.sys.po.PlatformMenuPo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformRolePo;
import com.yimayhd.erpcenter.dal.sys.service.PlatformMenuDal;
import com.yimayhd.erpcenter.dal.sys.vo.MenuOptVo;

/**
 * @author : zhangchao
 * @date : 2015年5月25日 下午4:36:10
 * @Description: 接口实现
 */
public class PlatformMenuBizImpl implements PlatformMenuBiz {

	private static final Logger logger = LoggerFactory.getLogger(PlatformMenuBizImpl.class);
	
	@Autowired
	private PlatformMenuDal platformMenuDal;
	/**
	 * @author : zhangchao
	 * @date : 2015年5月26日 上午9:24:23
	 * @Description: 根据系统ID查询菜单
	 */
	@Override
	public List<PlatformMenuPo> getPlatformMenuListBysysId(Integer sysId) {
		return platformMenuDal.getPlatformMenuListBysysId(sysId);
	}

	/**
	 * @author : zhangchao
	 * @date : 2015年5月26日 上午9:24:36
	 * @Description: 根据系统ID和父ID查询菜单
	 */
	@Override
	public List<PlatformMenuPo> getPlatformMenuListBysysIdAndParentId(
			Integer sysId, Integer parentId) {
		
		return platformMenuDal.getPlatformMenuListBysysIdAndParentId(sysId, parentId);
	}

	/**
	 * @author : zhangchao
	 * @date : 2015年5月26日 上午10:09:19
	 * @Description: 新增菜单
	 */
	@Override
	public int addPlatformMenu(PlatformMenuPo platformMenu) {
		return platformMenuDal.addPlatformMenu(platformMenu);
	}

	/**
	 * @author : zhangchao
	 * @date : 2015年5月26日 上午10:10:32
	 * @Description: 修改菜单  根据菜单的ID
	 */
	@Override
	public int updatePlatformMenu(PlatformMenuPo platformMenu) {
		return platformMenuDal.updatePlatformMenu(platformMenu);
	}

	/**
	 * @author : zhangchao
	 * @date : 2015年5月26日 上午10:11:58
	 * @Description: 删除菜单   备注：  同时会删除所属子菜单
	 */
	@Override
	public int deletePlatformMenuByMenuId(Integer menuId) {
		return platformMenuDal.deletePlatformMenuByMenuId(menuId);
	}

	/**
	 * @author : zhangchao
	 * @date : 2015年5月26日 上午10:22:21
	 * @Description: 删除所属子菜单
	 */
	@Override
	public void deletePlatformMenuByParentId(Integer parentId) {
	     platformMenuDal.deletePlatformMenuByParentId(parentId);
	}

	@Override
	public PlatformMenuPo getPlatformMenuMenuId(Integer menuId) {
		return platformMenuDal.getPlatformMenuMenuId(menuId);
	}

	/***
	 * 获取菜单列表
	 * 目前菜单支持三级
	 * 第一二级为菜单
	 * 第三级为操作权限
	 */
	@Override
	public List<PlatformMenuPo> getMenuList(Integer bizId,
			List<PlatformRolePo> roleList,Integer isSuper) {
		return platformMenuDal.getMenuList(bizId, roleList, isSuper);
	}

	private String getRoleIds(List<PlatformRolePo> roleList) {
		String roleIds = null;
		if(roleList!=null&&roleList.size()>=1){
			StringBuffer buffer = new StringBuffer();
			buffer.append("(");
			for(int i=0; i<roleList.size();i++){
				buffer.append(roleList.get(i).getRoleId());
				if(i!=roleList.size()-1){
					buffer.append(",");
				}
			}
			buffer.append(")");
			roleIds = buffer.toString();
		}
		
		if(roleIds==null){
			roleIds = "(0)";
		}
		return roleIds;
	}
	

	@Override
	public MenuOptVo getOptMaps(int bizId,
			List<PlatformRolePo> roleList, Integer isSuper) {
		return platformMenuDal.getOptMaps(bizId, roleList, isSuper);
	}
	
	
	
	@Override
	public List<PlatformMenuPo> getPlatformMenuJosnList(int sysId) {
		return platformMenuDal.getPlatformMenuJosnList(sysId);
	}

	@Override
	public List<PlatformMenuPo> getMenuList(int parentMenuId, String menuName,
			int exceptMenuId) {
		return platformMenuDal.getMenuList(parentMenuId, menuName, exceptMenuId);
	}

	@Override
	public List<PlatformMenuPo> getAllMenuList(Integer sysId, Integer parentId) {
		return platformMenuDal.getAllMenuList(sysId, parentId);
	}

	@Override
	public List<PlatformMenuPo> getMenuListByBizId(Integer bizId, Integer parentId) {
		return platformMenuDal.getMenuListByBizId(bizId, parentId);
	}

	@Override
	public void addPlatformMenuAndBizLink(Integer bizId,String menuIds) {
		 platformMenuDal.addPlatformMenuAndBizLink(bizId, menuIds);
	}

	/*@Override
	public void updatePlatformMenuAndBizLink(PlatformMenuPo po) {

		platformMenuMapper.updatePlatformMenuAndBiz(po);
	}*/

	@Override
	public void deleteSysMenuByBizId(Integer bizId) {
		platformMenuDal.deleteSysMenuByBizId(bizId);
	}

}
