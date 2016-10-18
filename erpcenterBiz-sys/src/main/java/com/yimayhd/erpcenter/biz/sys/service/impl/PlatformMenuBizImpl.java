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
import com.yimayhd.erpcenter.dal.sys.service.PlatformMenuDal;

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
		// TODO Auto-generated method stub
		platformMenuMapper.deletePlatformMenuByParentId(parentId);
	}

	@Override
	public PlatformMenuPo getPlatformMenuMenuId(Integer menuId) {
		// TODO Auto-generated method stub
		return platformMenuMapper.getPlatformMenuMenuId(menuId);
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
		List<PlatformMenuPo> list = null;
		String roleIds = getRoleIds(roleList);
		
		//获取子系统的顶级菜单id
		//Integer parentId = platformMenuMapper.getMenuParent(bizId).getMenuId();
		//根菜单root
		final Integer parentId = 0;
		//菜单类型
		final Integer menuType = 1;
		//操作类型
		final Integer optType = 0;
		if(isSuper==1){
			//list = platformMenuMapper.getAllMenuList(bizId,parentId);//一级菜单列表
			list = platformMenuMapper.getAdminMenuList(bizId, menuType, parentId);//一级菜单列表
		}else{
			//list = platformMenuMapper.getMenuList(bizId,parentId,roleIds);//一级菜单列表
			list = platformMenuMapper.getMenuListByRoleIds(bizId,roleIds,menuType,parentId);//一级菜单列表
		}
		if(list!=null){
			for(int i=0;i<list.size();i++){
				PlatformMenuPo parentMenu = list.get(i);
				List<PlatformMenuPo> childMenuList = null;
				//logger.info("parentId:"+ parentMenu.getMenuId());
				if(isSuper==1){
					//childMenuList= platformMenuMapper.getAllMenuList(bizId,parentMenu.getMenuId());//二级菜单列表
					childMenuList= platformMenuMapper.getAdminMenuList(bizId,menuType,parentMenu.getMenuId());//二级菜单列表
				}else{
					//childMenuList= platformMenuMapper.getMenuList(bizId,parentMenu.getMenuId(),roleIds);//二级菜单列表
					childMenuList= platformMenuMapper.getMenuListByRoleIds(bizId,roleIds,menuType,parentMenu.getMenuId());//二级菜单列表
				}
				
				parentMenu.setChildMenuList(childMenuList);
				
				/*if(childMenuList!=null&&childMenuList.size()>0){
					for(int j=0;j<childMenuList.size();j++){
						PlatformMenuPo menuOpt = childMenuList.get(j);
						List<PlatformMenuPo> optList =null;
						if(isSuper==1){
							//optList= platformMenuMapper.getAllMenuList(bizId,menuOpt.getMenuId());//操作列表
							optList= platformMenuMapper.getAdminMenuList(bizId,optType,menuOpt.getMenuId());//操作列表
						}
						else{
							//optList= platformMenuMapper.getMenuList(bizId,menuOpt.getMenuId(),roleIds);//操作列表
							optList= platformMenuMapper.getMenuListByRoleIds(bizId,roleIds,optType,menuOpt.getMenuId());//操作列表
						}
						if(optList!=null&&optList.size()>=1){
							menuOpt.setChildMenuList(optList);
							childMenuList.remove(j);
							childMenuList.add(j, menuOpt);
						}
						menuOpt.setChildMenuList(optList);
					}
				}*/
			}
		}
		return list;
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
		MenuOptVo optVo = new MenuOptVo();
		//菜单类型
		final Integer menuType = 1;
		//操作类型
		final Integer optType = 0;
		List<PlatformMenuPo> menuList = null;
		List<PlatformMenuPo> optList = null;
		if(isSuper==1){
			menuList = platformMenuMapper.getAdminMenuList(bizId, menuType, null);
			optList = platformMenuMapper.getAdminMenuList(bizId, optType, null);
		}else{
			String roleIds = getRoleIds(roleList);
			menuList = platformMenuMapper.getMenuListByRoleIds(bizId,roleIds,menuType,null);
			optList = platformMenuMapper.getMenuListByRoleIds(bizId,roleIds,optType,null);
		}
		//菜单code为key，菜单操作为value
		Map<String, Map<String,Boolean>> menuOptMap = new HashMap<String,Map<String,Boolean>>();
		//记录所有的操作权限
		Map<String,Boolean> optMap = new HashMap<String,Boolean>();
		//菜单的id为key，code为value
		Map<Integer,String> menuIdCodeMap = new HashMap<Integer,String>();
		if(menuList!=null&&menuList.size()>0){
			for(PlatformMenuPo menu : menuList){
				menuIdCodeMap.put(menu.getMenuId(), menu.getCode());
				Map<String,Boolean> map = new HashMap<String,Boolean>();
				menuOptMap.put(menu.getCode(), map);
			}
		}
		if(optList!=null&&optList.size()>0){
			for(PlatformMenuPo opt : optList){
				if(menuIdCodeMap.containsKey(opt.getParentId())){
					String parentCode = menuIdCodeMap.get(opt.getParentId());
					Map<String,Boolean> map = menuOptMap.get(parentCode);
					map.put(opt.getCode(), true);
					
					optMap.put(parentCode+"_"+opt.getCode(), true);
				}
			}
		}
		optVo.setMenuOptMap(menuOptMap);
		optVo.setOptMap(optMap);
		return optVo;
	}
	
	
	
	@Override
	public List<PlatformMenuPo> getPlatformMenuJosnList(int sysId) {
		return platformMenuMapper.getPlatformMenuJosnList(sysId);
	}

	@Override
	public List<PlatformMenuPo> getMenuList(int parentMenuId, String menuName,
			int exceptMenuId) {
		return platformMenuMapper.getMenuListExceptMenuId(parentMenuId, menuName, exceptMenuId);
	}

	@Override
	public List<PlatformMenuPo> getAllMenuList(Integer sysId, Integer parentId) {
		return platformMenuMapper.getAllMenuList(sysId, parentId);
	}

	@Override
	public List<PlatformMenuPo> getMenuListByBizId(Integer bizId, Integer parentId) {
		return  platformMenuMapper.getMenuListByBizId(bizId, parentId);
	}

	@Override
	public void addPlatformMenuAndBizLink(Integer bizId,String menuIds) {
		String[] menuIDs = menuIds.split(",");
		PlatformMenuPo po=new PlatformMenuPo();
		po.setBizId(bizId);
		for (String menuId : menuIDs) {
			po.setMenuId(Integer.parseInt(menuId));
			platformMenuMapper.addPlatformMenuAndBizLink(po);
		}
	}

	/*@Override
	public void updatePlatformMenuAndBizLink(PlatformMenuPo po) {

		platformMenuMapper.updatePlatformMenuAndBiz(po);
	}*/

	@Override
	public void deleteSysMenuByBizId(Integer bizId) {
		platformMenuMapper.deleteSysMenuByBizId(bizId);
		
	}

}
