package com.yimayhd.erpcenter.facade.sys.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformRoleBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformRoleMenuLinkPo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformRolePo;
import com.yimayhd.erpcenter.facade.sys.query.PlatformRolePoDTO;
import com.yimayhd.erpcenter.facade.sys.result.PlatformRoleMenuLinkPoListResult;
import com.yimayhd.erpcenter.facade.sys.result.PlatformRolePoListResult;
import com.yimayhd.erpcenter.facade.sys.result.PlatformRolePoResult;
import com.yimayhd.erpcenter.facade.sys.service.SysPlatformRoleFacade;

public class SysPlatformRoleFacadeImpl implements SysPlatformRoleFacade {
	
	private static final Logger logger = LoggerFactory.getLogger(SysPlatformRoleFacadeImpl.class);

	@Autowired
	private PlatformRoleBiz platformRoleBiz;
	@Autowired
	private DicBiz dicBiz;
	
	@Override
	public PageBean getRoleList(PlatformRolePoDTO platformRolePo ,Integer page) {
		return platformRoleBiz.getRoleList(platformRolePo.getPlatformRolePo(), page);
	}
	
	@Override
	public PlatformRolePoResult findByRoleId(Integer roleId) {
		PlatformRolePo platformRolePo =  platformRoleBiz.findByRoleId(roleId);
		PlatformRolePoResult result = new PlatformRolePoResult();
		if(platformRolePo!=null)
			result.setPlatformRolePo(platformRolePo);
		return result;
	}

	@Override
	public int saveRole(PlatformRolePoDTO platformRolePo) {
		return platformRoleBiz.saveRole(platformRolePo.getPlatformRolePo());
	}

	@Override
	public int delByRoleid(Integer roleId) {
		return platformRoleBiz.delByRoleid(roleId);
	}

	@Override
	public int deleteByRoleid(Integer roleId) {
		return platformRoleBiz.delByRoleid(roleId);
	}

	@Override
	public PlatformRolePoListResult getRoleList(Integer bizId, Integer employeeId,Integer isSuper) {
		List<PlatformRolePo> platformRolePos =  platformRoleBiz.getRoleList(bizId, employeeId, isSuper);
		PlatformRolePoListResult result = new PlatformRolePoListResult();
		if(platformRolePos!=null){
			result.setPlatformRolePos(platformRolePos);
		}
		List<DicInfo> roleGroup = dicBiz.getListByTypeCode(BasicConstants.ROLE_GROUP,bizId);
		result.setRoleGroup(roleGroup);
		return result;
	}
	@Override
	public PlatformRoleMenuLinkPoListResult findPlatformRoleMenuLinkPoByRoleIdAndMenuId(
			Integer roleId, Integer menuId) {
		List<PlatformRoleMenuLinkPo> platformRoleMenuLinkPos =  platformRoleBiz.findPlatformRoleMenuLinkPoByRoleIdAndMenuId(roleId,menuId);
		PlatformRoleMenuLinkPoListResult result = new PlatformRoleMenuLinkPoListResult();
		if(platformRoleMenuLinkPos!=null){
			result.setPlatformRoleMenuLinkPos(platformRoleMenuLinkPos);
		}
		return result;
	}
	
	@Override
	public PlatformRoleMenuLinkPoListResult findPlatformRoleMenuLinkPoByMenuId(String menuId) {
		List<PlatformRoleMenuLinkPo> platformRoleMenuLinkPos = platformRoleBiz.findPlatformRoleMenuLinkPoByMenuId(menuId);
		PlatformRoleMenuLinkPoListResult result = new PlatformRoleMenuLinkPoListResult();
		if(platformRoleMenuLinkPos!=null){
			result.setPlatformRoleMenuLinkPos(platformRoleMenuLinkPos);
		}
		return result;
	}

	@Override
	public PlatformRolePoListResult getRoleList(String roleName, int exceptRoleId) {
		List<PlatformRolePo> platformRolePos = platformRoleBiz.getRoleList(roleName, exceptRoleId);
		PlatformRolePoListResult result = new PlatformRolePoListResult();
		if(platformRolePos!=null)
			result.setPlatformRolePos(platformRolePos);
		return result;
	}

	@Override
	public void copyRole(Integer roleId) {
		platformRoleBiz.copyRole(roleId);
	}

}
