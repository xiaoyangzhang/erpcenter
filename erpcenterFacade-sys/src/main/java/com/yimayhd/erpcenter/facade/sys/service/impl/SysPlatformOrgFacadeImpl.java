package com.yimayhd.erpcenter.facade.sys.service.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.dal.sys.po.SysDataRight;
import com.yimayhd.erpcenter.facade.sys.query.PlatformOrgPoDTO;
import com.yimayhd.erpcenter.facade.sys.result.PlatformOrgPoListResult;
import com.yimayhd.erpcenter.facade.sys.result.PlatformOrgPoResult;
import com.yimayhd.erpcenter.facade.sys.result.SysDataRightListResult;
import com.yimayhd.erpcenter.facade.sys.service.SysPlatformOrgFacade;

public class SysPlatformOrgFacadeImpl implements SysPlatformOrgFacade{
	private static final Logger logger=LoggerFactory.getLogger(SysPlatformOrgFacadeImpl.class);

	@Autowired
	private PlatformOrgBiz platformOrgBiz;
	
	@Override
	public PlatformOrgPoListResult findByPid(Integer pid,Integer sysId) {
		List<PlatformOrgPo> platformRolePos =  platformOrgBiz.findByPid(pid, sysId);
		PlatformOrgPoListResult result = new PlatformOrgPoListResult();
		if(platformRolePos!=null)
			result.setPlatformOrgPos(platformRolePos);
		return result;
	}
	@Override
	public PlatformOrgPoResult findByOrgId(Integer orgId) {
		PlatformOrgPo platformOrgPo =  platformOrgBiz.findByOrgId(orgId);
		PlatformOrgPoResult result = new PlatformOrgPoResult();
		if(platformOrgPo!=null)
			result.setPlatformOrgPo(platformOrgPo);
		return result;
	}
	
	@Override
	public int delOrg(Integer orgId) {
		return platformOrgBiz.delOrg(orgId);
	}
	
	@Override
	public PlatformOrgPoListResult getOrgList(Integer sysId, Integer orgId,Integer isSuper) {
		List<PlatformOrgPo> platformRolePos =  platformOrgBiz.getOrgList(sysId, orgId,isSuper);
		PlatformOrgPoListResult result = new PlatformOrgPoListResult();
		if(platformRolePos!=null)
			result.setPlatformOrgPos(platformRolePos);
		return result;
		
	}
	
	@Override
	public PlatformOrgPoListResult getOrgTree(Integer bizId, Integer parentId) {
		List<PlatformOrgPo> platformRolePos = platformOrgBiz.getOrgTree(bizId, parentId);
		PlatformOrgPoListResult result = new PlatformOrgPoListResult();
		if(platformRolePos!=null)
			result.setPlatformOrgPos(platformRolePos);
		return result;
	}
	
	@Override
	public PlatformOrgPoListResult getOrgList(String orgName, int exceptOrgId) {
		List<PlatformOrgPo> platformRolePos =  platformOrgBiz.getOrgList(orgName, exceptOrgId);
		PlatformOrgPoListResult result = new PlatformOrgPoListResult();
		if(platformRolePos!=null)
			result.setPlatformOrgPos(platformRolePos);
		return result;
	}
	@Override
	public SysDataRightListResult getSysDataRightByEmployeeId(Integer employeeId) {
		List<SysDataRight> sysDataRights =  platformOrgBiz.getSysDataRightByEmployeeId(employeeId);
		SysDataRightListResult result = new SysDataRightListResult();
		if(sysDataRights!=null)
			result.setSysDataRights(sysDataRights);
		return result;
	}
	@Override
	public PlatformOrgPoListResult getOrgList(Integer sysId, Integer employeeId) {
		List<PlatformOrgPo> platformRolePos =  platformOrgBiz.getOrgList(sysId, employeeId);
		PlatformOrgPoListResult result = new PlatformOrgPoListResult();
		if(platformRolePos!=null)
			result.setPlatformOrgPos(platformRolePos);
		return result;
		
	}
	
	@Override
	public String getLogoByOrgId(Integer bizId, Integer orgId) {
		return platformOrgBiz.getLogoByOrgId(bizId, orgId);
	}
	@Override
	public String getCompanyCodeByOrgId(Integer bizId, Integer orgId) {
		return platformOrgBiz.getCompanyCodeByOrgId(bizId, orgId);
	}
	@Override
	public String getComponentOrgTreeJsonStr(Integer bizId) {
		return platformOrgBiz.getComponentOrgTreeJsonStr(bizId);
	}
	@Override
	public PlatformOrgPoResult getOrgInfo(int bizId, Integer orgId) {
		PlatformOrgPo platformOrgPo =  platformOrgBiz.getOrgInfo(bizId, orgId);
		PlatformOrgPoResult result = new PlatformOrgPoResult();
		if(platformOrgPo!=null)
			result.setPlatformOrgPo(platformOrgPo);
		return result;
	}
	@Override
	public PlatformOrgPoResult getCompanyByEmployeeId(Integer bizId,
			Integer employeeId) {
		 
		PlatformOrgPo platformOrgPo =  platformOrgBiz.getCompanyByEmployeeId(bizId, employeeId);
		PlatformOrgPoResult result = new PlatformOrgPoResult();
		if(platformOrgPo!=null)
			result.setPlatformOrgPo(platformOrgPo);
		return result;
	}
	//根据当前登录用户的id获取其数据权限
	@Override
	public SysDataRightListResult getViewUserId(Integer userId) {
		List<SysDataRight> sysDataRights = platformOrgBiz.getViewUserId(userId);
		SysDataRightListResult result = new SysDataRightListResult();
		if(sysDataRights!=null)
			result.setSysDataRights(sysDataRights);
		return result;
	}
	
	@Override
	public String getComponentOrgTreeJsonStr2(String orgIds,String userIds) {
		return platformOrgBiz.getComponentOrgTreeJsonStr2(orgIds, userIds);
	}
	@Override
	public PlatformOrgPoListResult getSubLevelOrgList(Integer bizId,
			List<Integer> parentIdList) {
		List<PlatformOrgPo> platformOrgPos =  platformOrgBiz.getSubLevelOrgList(bizId, parentIdList);
		PlatformOrgPoListResult result = new PlatformOrgPoListResult();
		if(platformOrgPos!=null)
			result.setPlatformOrgPos(platformOrgPos);
		return result;
	}
	@Override
	public PlatformOrgPoListResult getSecLevelOrgList(Integer bizId) {		
		List<PlatformOrgPo> platformOrgPos =  platformOrgBiz.getSecLevelOrgList(bizId);
		PlatformOrgPoListResult result = new PlatformOrgPoListResult();
		if(platformOrgPos!=null)
			result.setPlatformOrgPos(platformOrgPos);
		return result;
	}
	@Override
	public PlatformOrgPoListResult getOrgListByIdSet(Integer bizId,
			Set<Integer> sets) {		
		List<PlatformOrgPo> platformOrgPos =  platformOrgBiz.getOrgListByIdSet(bizId, sets);
		PlatformOrgPoListResult result = new PlatformOrgPoListResult();
		if(platformOrgPos!=null)
			result.setPlatformOrgPos(platformOrgPos);
		return result;
	}
	@Override
	public int saveOrg(PlatformOrgPoDTO po) {
		return platformOrgBiz.saveOrg(po.getPlatformOrgPo());
	}
	
	
}
