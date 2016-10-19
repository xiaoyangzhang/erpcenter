package com.yimayhd.erpcenter.biz.sys.service.impl;

import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.dal.sys.po.SysDataRight;
import com.yimayhd.erpcenter.dal.sys.service.PlatformOrgDal;

public class PlatformOrgBizImpl implements PlatformOrgBiz{
	private static final Logger logger=LoggerFactory.getLogger(PlatformOrgBizImpl.class);

	@Autowired
	private PlatformOrgDal platformOrgDal;
	
	@Override
	public List<PlatformOrgPo> findByPid(Integer pid,Integer sysId) {
		return platformOrgDal.findByPid(pid, sysId);
	}
	@Override
	public PlatformOrgPo findByOrgId(Integer orgId) {
		return platformOrgDal.findByOrgId(orgId);
	}
	
	public int saveOrg(PlatformOrgPo po) {
		return platformOrgDal.saveOrg(po);
	}
	@Override
	public int delOrg(Integer orgId) {
		return platformOrgDal.delOrg(orgId);
	}
	/*@Override
	public List<PlatformOrgPo> getOrgList(Integer sysId, Integer employeeId,Integer isSuper) {
		if(isSuper==1){
			return orgMapper.getAllOrgList(sysId);
		}
		return orgMapper.getOrgList(sysId,employeeId);
	}*/
	@Override
	public List<PlatformOrgPo> getOrgList(Integer sysId, Integer orgId,Integer isSuper) {
		return platformOrgDal.getOrgList(sysId, orgId,isSuper);
	}
	/*@Override
	public List<PlatformOrgPo> getOrgTree(Integer sysId, Integer parentId) {
		
		return orgMapper.getOrgTree(sysId,parentId);
	}*/
	
	@Override
	public List<PlatformOrgPo> getOrgTree(Integer bizId, Integer parentId) {
		return platformOrgDal.getOrgTree(bizId, parentId);
	}
	
	@Override
	public List<PlatformOrgPo> getOrgList(String orgName, int exceptOrgId) {
		return platformOrgDal.getOrgList(orgName, exceptOrgId);
	}
	@Override
	public List<SysDataRight> getSysDataRightByEmployeeId(Integer employeeId) {
		return platformOrgDal.getSysDataRightByEmployeeId(employeeId);
	}
	@Override
	public List<PlatformOrgPo> getOrgList(Integer sysId, Integer employeeId) {
		return platformOrgDal.getOrgList(sysId, employeeId);
		
	}
	
	@Override
	public String getLogoByOrgId(Integer bizId, Integer orgId) {
		return platformOrgDal.getLogoByOrgId(bizId, orgId);
	}
	@Override
	public String getCompanyCodeByOrgId(Integer bizId, Integer orgId) {
		return platformOrgDal.getCompanyCodeByOrgId(bizId, orgId);
	}
	@Override
	public String getComponentOrgTreeJsonStr(Integer bizId) {
		return platformOrgDal.getComponentOrgTreeJsonStr(bizId);
	}
	@Override
	public PlatformOrgPo getOrgInfo(int bizId, Integer orgId) {
		return platformOrgDal.getOrgInfo(bizId, orgId);
	}
	@Override
	public PlatformOrgPo getCompanyByEmployeeId(Integer bizId,
			Integer employeeId) {
		 
		return platformOrgDal.getCompanyByEmployeeId(bizId, employeeId);
	}
	//根据当前登录用户的id获取其数据权限
	@Override
	public List<SysDataRight> getViewUserId(Integer userId) {
		return platformOrgDal.getViewUserId(userId);
	}
	
	@Override
	public String getComponentOrgTreeJsonStr2(String orgIds,String userIds) {
		return platformOrgDal.getComponentOrgTreeJsonStr2(orgIds, userIds);
	}
	@Override
	public List<PlatformOrgPo> getSubLevelOrgList(Integer bizId,
			List<Integer> parentIdList) {
		return platformOrgDal.getSubLevelOrgList(bizId, parentIdList);
	}
	@Override
	public List<PlatformOrgPo> getSecLevelOrgList(Integer bizId) {		
		return platformOrgDal.getSecLevelOrgList(bizId);
	}
	@Override
	public List<PlatformOrgPo> getOrgListByIdSet(Integer bizId,
			Set<Integer> sets) {		
		return platformOrgDal.getOrgListByIdSet(bizId, sets);
	}
	
	
}
