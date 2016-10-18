package com.yimayhd.erpcenter.biz.sys.service.impl;

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
import com.yimayhd.erpcenter.biz.sys.service.PlatformRoleBiz;
import com.yimayhd.erpcenter.dal.sys.po.PlatformRoleMenuLinkPo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformRolePo;
import com.yimayhd.erpcenter.dal.sys.service.PlatformRoleDal;

public class PlatformRoleBizImpl implements PlatformRoleBiz {
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformRoleBizImpl.class);

	@Autowired
	private PlatformRoleDal platformRoleDal;
	
	@Override
	public PageBean getRoleList(PlatformRolePo platformRolePo ,Integer page) {
		return platformRoleDal.getRoleList(platformRolePo, page);
	}
	
	@Override
	public PlatformRolePo findByRoleId(Integer roleId) {
		return platformRoleDal.findByRoleId(roleId);
	}

	@Override
	public int saveRole(PlatformRolePo platformRolePo) {
		return platformRoleDal.saveRole(platformRolePo);
	}

	@Override
	public int delByRoleid(Integer roleId) {
		return platformRoleDal.delByRoleid(roleId);
	}

	@Override
	public int deleteByRoleid(Integer roleId) {
		return platformRoleDal.delByRoleid(roleId);
	}

	@Override
	public List<PlatformRolePo> getRoleList(Integer bizId, Integer employeeId,Integer isSuper) {
		return platformRoleDal.getRoleList(bizId, employeeId, isSuper);
	}
	@Override
	public List<PlatformRoleMenuLinkPo> findPlatformRoleMenuLinkPoByRoleIdAndMenuId(
			Integer roleId, Integer menuId) {
		return platformRoleDal.findPlatformRoleMenuLinkPoByRoleIdAndMenuId(roleId,menuId);
	}
	
	@Override
	public List<PlatformRoleMenuLinkPo> findPlatformRoleMenuLinkPoByMenuId(String menuId) {
		return platformRoleDal.findPlatformRoleMenuLinkPoByMenuId(menuId);
	}

	@Override
	public List<PlatformRolePo> getRoleList(String roleName, int exceptRoleId) {
		return platformRoleDal.getRoleList(roleName, exceptRoleId);
	}

	@Override
	public void copyRole(Integer roleId) {
		platformRoleDal.copyRole(roleId);
	}

}
