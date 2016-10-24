package com.yimayhd.erpcenter.facade.sys.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sys.service.PlatformMenuBiz;
import com.yimayhd.erpcenter.dal.sys.po.PlatformMenuPo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformRolePo;
import com.yimayhd.erpcenter.dal.sys.vo.MenuOptVo;
import com.yimayhd.erpcenter.facade.sys.query.PlatformMenuPoDTO;
import com.yimayhd.erpcenter.facade.sys.query.PlatformRolePoListDTO;
import com.yimayhd.erpcenter.facade.sys.result.MenuOptVoResult;
import com.yimayhd.erpcenter.facade.sys.result.PlatformMenuPoListResult;
import com.yimayhd.erpcenter.facade.sys.result.PlatformOrgPoListResult;
import com.yimayhd.erpcenter.facade.sys.service.SysPlatformMenuFacade;

/**
 * @Description: 接口实现
 */
public class SysPlatformMenuFacadeImpl implements SysPlatformMenuFacade {

	private static final Logger logger = LoggerFactory.getLogger(SysPlatformMenuFacadeImpl.class);
	
	@Autowired
	private PlatformMenuBiz platformMenuBiz;
	/**
	 * @Description: 根据系统ID查询菜单
	 */
	@Override
	public PlatformMenuPoListResult getPlatformMenuListBysysId(Integer sysId) {
		List<PlatformMenuPo> platformMenuPos = platformMenuBiz.getPlatformMenuListBysysId(sysId);
		PlatformMenuPoListResult result = new PlatformMenuPoListResult();
		if(platformMenuPos!=null)
			result.setPlatformMenuPos(platformMenuPos);
		return result;
	}

	/**
	 * @Description: 根据系统ID和父ID查询菜单
	 */
	@Override
	public PlatformMenuPoListResult getPlatformMenuListBysysIdAndParentId(
			Integer sysId, Integer parentId) {
		 List<PlatformMenuPo> platformMenuPos =  platformMenuBiz.getPlatformMenuListBysysIdAndParentId(sysId, parentId);
		PlatformMenuPoListResult result = new PlatformMenuPoListResult();
		if(platformMenuPos!=null)
			result.setPlatformMenuPos(platformMenuPos);
		return result;
	}

	/**
	 * @Description: 新增菜单
	 */
	@Override
	public int addPlatformMenu(PlatformMenuPoDTO platformMenu) {
		return platformMenuBiz.addPlatformMenu(platformMenu.getPlatformMenuPo());
	}

	/**
	 * @Description: 修改菜单  根据菜单的ID
	 */
	@Override
	public int updatePlatformMenu(PlatformMenuPoDTO platformMenu) {
		return platformMenuBiz.updatePlatformMenu(platformMenu.getPlatformMenuPo());
	}

	/**
	 * @Description: 删除菜单   备注：  同时会删除所属子菜单
	 */
	@Override
	public int deletePlatformMenuByMenuId(Integer menuId) {
		return platformMenuBiz.deletePlatformMenuByMenuId(menuId);
	}

	/**
	 * @author : zhangchao
	 * @date : 2015年5月26日 上午10:22:21
	 * @Description: 删除所属子菜单
	 */
	@Override
	public void deletePlatformMenuByParentId(Integer parentId) {
	     platformMenuBiz.deletePlatformMenuByParentId(parentId);
	}

	@Override
	public PlatformMenuPo getPlatformMenuMenuId(Integer menuId) {
		return platformMenuBiz.getPlatformMenuMenuId(menuId);
	}

	/***
	 * 获取菜单列表
	 * 目前菜单支持三级
	 * 第一二级为菜单
	 * 第三级为操作权限
	 */
	@Override
	public PlatformMenuPoListResult getMenuList(Integer bizId,
			PlatformRolePoListDTO roleList,Integer isSuper) {
		 List<PlatformMenuPo> platformMenuPos = platformMenuBiz.getMenuList(bizId, roleList.getPlatformRolePos(), isSuper);
		PlatformMenuPoListResult result = new PlatformMenuPoListResult();
		if(platformMenuPos!=null)
			result.setPlatformMenuPos(platformMenuPos);
		return result;
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
	public MenuOptVoResult getOptMaps(int bizId,
			PlatformRolePoListDTO roleList, Integer isSuper) {
		MenuOptVo menuOptVo =  platformMenuBiz.getOptMaps(bizId, roleList.getPlatformRolePos(), isSuper);
		MenuOptVoResult result = new MenuOptVoResult();
		if(menuOptVo!=null)
			result.setMenuOptVo(menuOptVo);
		return result;
	}
	
	
	
	@Override
	public PlatformMenuPoListResult getPlatformMenuJosnList(int sysId) {
		List<PlatformMenuPo> platformMenuPos = platformMenuBiz.getPlatformMenuJosnList(sysId);
		PlatformMenuPoListResult result = new PlatformMenuPoListResult();
		if(platformMenuPos!=null)
			result.setPlatformMenuPos(platformMenuPos);
		return result;
	}

	@Override
	public PlatformMenuPoListResult getMenuList(int parentMenuId, String menuName,
			int exceptMenuId) {
		List<PlatformMenuPo> platformMenuPos =  platformMenuBiz.getMenuList(parentMenuId, menuName, exceptMenuId);
		PlatformMenuPoListResult result = new PlatformMenuPoListResult();
		if(platformMenuPos!=null)
			result.setPlatformMenuPos(platformMenuPos);
		return result;
	}

	@Override
	public PlatformMenuPoListResult getAllMenuList(Integer sysId, Integer parentId) {
		List<PlatformMenuPo> platformMenuPos = platformMenuBiz.getAllMenuList(sysId, parentId);
		PlatformMenuPoListResult result = new PlatformMenuPoListResult();
		if(platformMenuPos!=null)
			result.setPlatformMenuPos(platformMenuPos);
		return result;
	}

	@Override
	public PlatformMenuPoListResult getMenuListByBizId(Integer bizId, Integer parentId) {
		List<PlatformMenuPo> platformMenuPos = platformMenuBiz.getMenuListByBizId(bizId, parentId);
		PlatformMenuPoListResult result = new PlatformMenuPoListResult();
		if(platformMenuPos!=null)
			result.setPlatformMenuPos(platformMenuPos);
		return result;
	}

	@Override
	public void addPlatformMenuAndBizLink(Integer bizId,String menuIds) {
		 platformMenuBiz.addPlatformMenuAndBizLink(bizId, menuIds);
	}

	/*@Override
	public void updatePlatformMenuAndBizLink(PlatformMenuPo po) {

		platformMenuMapper.updatePlatformMenuAndBiz(po);
	}*/

	@Override
	public void deleteSysMenuByBizId(Integer bizId) {
		platformMenuBiz.deleteSysMenuByBizId(bizId);
	}

}
