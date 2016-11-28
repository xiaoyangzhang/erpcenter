package com.yimayhd.erpcenter.facade.sys.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.biz.sys.util.SysConfig;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.dal.sys.po.SysDataRight;
import com.yimayhd.erpcenter.facade.sys.query.PlatformOrgPoDTO;
import com.yimayhd.erpcenter.facade.sys.result.PlatformOrgPoListResult;
import com.yimayhd.erpcenter.facade.sys.result.PlatformOrgPoResult;
import com.yimayhd.erpcenter.facade.sys.result.SysDataRightListResult;
import com.yimayhd.erpcenter.facade.sys.service.SysPlatformOrgFacade;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.dal.po.SupplierInfo;

public class SysPlatformOrgFacadeImpl implements SysPlatformOrgFacade{
	private static final Logger logger=LoggerFactory.getLogger(SysPlatformOrgFacadeImpl.class);

	@Autowired
	private PlatformOrgBiz platformOrgBiz;
	@Autowired
	private SysConfig config;
	@Autowired
	private SupplierBiz supplierBiz;
	
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
	public PlatformOrgPo getCompanyByEmployeeId2(Integer bizId, Integer employeeId) {
		return platformOrgBiz.getCompanyByEmployeeId2(bizId,employeeId);
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
	
	/**
	 * 获取当前公司或商家编码
	 * TODO 设置开关：有的公司取商家编码，有的公司取公司编码
	 * @param request
	 * @return
	 */
	public String getMyBizCode(Integer bizId,Integer orgId){
		//WebUtils.getCurBizCode(request)
		return platformOrgBiz.getCompanyCodeByOrgId(bizId, orgId);
	}
	
	/**
	 * 获取当前公司或商家logo
	 * TODO 设置开关：有的公司取商家logo，有的公司取公司logo
	 * @param request
	 * @return
	 */
	public String getMyBizLogo(Integer bizId,Integer orgId){		
		//WebUtils.getCurBizLogo(path, request)
		return getOrgLogo(bizId, orgId);
	}
	
	/**
	 * 根据orgId向上遍历查找logo
	 * @param bizId
	 * @param orgId
	 * @return
	 */
	public String getOrgLogo(Integer bizId,Integer orgId){
		String logo = platformOrgBiz.getLogoByOrgId(bizId,orgId); 
		if(StringUtils.isNotBlank(logo)){
			return config.getImgServerUrl()+logo;
		}
		return null;
	}
	
	/**
	 * 根据orgId向上遍历查找 登录者对应的默认组团社信息
	 * @param orgId
	 * @return [0]为supplierId, [1]为supplierName
	 */
	public String[] getOrgMappingSupplierId(Integer orgId){
		String ret[] = new String[]{"0",""};
		String SupplierId = platformOrgBiz.getMappingSupplierIdByOrgId(orgId); 
		if(!"0".equals(SupplierId)){
			SupplierInfo supInfo = supplierBiz.selectBySupplierId(Integer.valueOf(SupplierId));
			if (supInfo != null)
				ret[1] = supInfo.getNameFull();
			ret[0] = SupplierId;
		}
		return ret;
	}
	
	
}
