package com.yimayhd.erpcenter.dal.sys.service.impl;

import java.nio.MappedByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javassist.expr.NewArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.dao.PlatformRoleMapper;
import com.yimayhd.erpcenter.dal.sys.po.PlatformRoleMenuLinkPo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformRolePo;
import com.yimayhd.erpcenter.dal.sys.service.PlatformRoleDal;
import com.yimayhd.erpcenter.dal.sys.utils.DateUtils;
import com.yimayhd.erpcenter.dal.sys.utils.StrUtils;


public class PlatformRoleDalImpl implements PlatformRoleDal {
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformRoleDalImpl.class);

	@Autowired
	private PlatformRoleMapper platformRoleMapper;
	
	@Override
	public PageBean getRoleList(PlatformRolePo platformRolePo ,Integer page) {
		
		PageBean pageBean = new PageBean();
		pageBean.setPage(platformRolePo.getPage());
		pageBean.setParameter(platformRolePo);

		pageBean.setPageSize(platformRolePo.getPageSize());

		List<PlatformRolePo> result = platformRoleMapper.getRoleListPage(pageBean);
		pageBean.setResult(result);
		return pageBean;
		
	}
	
	@Override
	public PlatformRolePo findByRoleId(Integer roleId) {
		return platformRoleMapper.findByRoleId(roleId); 
	}

	@Override
	public int saveRole(PlatformRolePo platformRolePo) {
		String menuIds = platformRolePo.getMenuIds();
		int result = 0;
		if(platformRolePo.getRoleId()!=null){
			platformRolePo.setUpdateTime(DateUtils.getNow());
			result = platformRoleMapper.updateRole(platformRolePo);
			//先删除角色和菜单的 关联信息
			platformRoleMapper.delPlatformRoleMenuLinkPoByRoleId(platformRolePo.getRoleId());
			List<String> menuList = StrUtils.strSplitList(menuIds);
			for (String menuId : menuList) {
				platformRoleMapper.addPlatformRoleMenuLinkPo(platformRolePo.getRoleId(), Integer.valueOf(menuId));
			}
		}
		else{
			platformRolePo.setCreateTime(DateUtils.getNow());
			platformRolePo.setUpdateTime(DateUtils.getNow());
			result = platformRoleMapper.addRole(platformRolePo);

			List<String> menuList = StrUtils.strSplitList(menuIds);
			for (String menuId : menuList) {
				platformRoleMapper.addPlatformRoleMenuLinkPo(platformRolePo.getRoleId(), Integer.valueOf(menuId));
			}
		}
		return result;
	}

	@Override
	public int delByRoleid(Integer roleId) {
		int result = 0;
		platformRoleMapper.delByRoleId(roleId);
		return result; 
	}

	@Override
	public int deleteByRoleid(Integer roleId) {
		int result = 0;
		PlatformRolePo platformRolePo = platformRoleMapper.findByRoleId(roleId);
		/**
		 * 缺少角色是否可以删除逻辑  如已分配用户
		 */
		
		
		platformRolePo.setDelStatus(0);
		result = platformRoleMapper.updateRole(platformRolePo);
		return result;
	}

	@Override
	public List<PlatformRolePo> getRoleList(Integer bizId, Integer employeeId,Integer isSuper) {
		if(isSuper==1){
			return platformRoleMapper.getAllRoleList(bizId);
			//return new ArrayList<PlatformRolePo>();
		}else{
			return platformRoleMapper.getRoleList(bizId,employeeId);
		}
	}
	@Override
	public List<PlatformRoleMenuLinkPo> findPlatformRoleMenuLinkPoByRoleIdAndMenuId(
			Integer roleId, Integer menuId) {
		// TODO Auto-generated method stub
		return platformRoleMapper.findPlatformRoleMenuLinkPoByRoleIdAndMenuId(roleId,menuId);
	}
	
	@Override
	public List<PlatformRoleMenuLinkPo> findPlatformRoleMenuLinkPoByMenuId(String menuId) {
		return platformRoleMapper.findPlatformRoleMenuLinkPoByMenuId(menuId);
	}

	@Override
	public List<PlatformRolePo> getRoleList(String roleName, int exceptRoleId) {
		return platformRoleMapper.getRoleListExceptId(roleName, exceptRoleId);
	}

	@Override
	public void copyRole(Integer roleId) {
		PlatformRolePo platformRole = platformRoleMapper.findByRoleId(roleId);
		PlatformRolePo role=new PlatformRolePo();
		if (platformRole!=null ) {
			role.setBizId(platformRole.getBizId());
			role.setSysId(platformRole.getSysId());
			role.setCode(platformRole.getCode());
			role.setComment(platformRole.getComment());
			role.setDelStatus(platformRole.getDelStatus());
			role.setGroupId(platformRole.getGroupId());
			role.setGroupName(platformRole.getGroupName());
			role.setName("复制-"+platformRole.getName());
			role.setStatus(platformRole.getStatus());
			role.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			role.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}
		platformRoleMapper.addRole(role);
		List<PlatformRoleMenuLinkPo> roleMenuLinkPos = platformRoleMapper.findPlatformRoleMenuLinkPoByRoleId(roleId);
		for (PlatformRoleMenuLinkPo linkPo : roleMenuLinkPos) {
//			PlatformRoleMenuLinkPo roleMenuLinkPo=new PlatformRoleMenuLinkPo();
//			roleMenuLinkPo.setRoleId(role.getRoleId());
//			roleMenuLinkPo.setMenuId(linkPo.getMenuId());
			platformRoleMapper.addPlatformRoleMenuLinkPo(role.getRoleId(), linkPo.getMenuId());
		}
		//return null;
	}

}
