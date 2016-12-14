package com.yimayhd.erpcenter.dal.sys.dao;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;

import java.util.List;
import java.util.Set;

public interface PlatformOrgMapper {
	public List<PlatformOrgPo> findByPid(@Param("pid") Integer pid, @Param("sysId") Integer sysId);

	public PlatformOrgPo findByOrgId(Integer orgId);

	public int add(PlatformOrgPo po);

	public int update(PlatformOrgPo po);

	public int del(Integer orgId);

	public List<PlatformOrgPo> getOrgList(@Param("sysId") Integer sysId, @Param("employeeId") Integer employeeId);
	
	public List<PlatformOrgPo> getAllOrgList(@Param("sysId") Integer sysId);

	//public List<PlatformOrgPo> getOrgTree(@Param("sysId")Integer sysId, @Param("parentId")Integer parentId);
	public List<PlatformOrgPo> getOrgTree(@Param("bizId") Integer bizId, @Param("parentId") Integer parentId);
	public List<PlatformOrgPo> getOrgTree2(@Param("orgIds") String orgIds, @Param("parentId") Integer parentId);
	public List<PlatformOrgPo> getOrgTree3(@Param("userIds") String userIds);
	
	public List<PlatformOrgPo> getOrgListExceptId(@Param("name") String name, @Param("orgId") int exceptOrgId);
	public String getLogoByOrgId(@Param("bizId") Integer bizId, @Param("orgId") Integer orgId);
	public String getCompanyCodeByOrgId(@Param("orgId") Integer orgId, @Param("bizId") Integer bizId);
	public List<PlatformOrgPo> getOrgListByOrgIdListAndBiz(@Param("bizId") Integer bizId, @Param("list") Set<Integer> orgIdList);

	public PlatformOrgPo getCompanyByEmployeeId(@Param("bizId") Integer bizId,
                                                @Param("employeeId") Integer employeeId);

	public PlatformOrgPo getCompanyByEmployeeId2(@Param("bizId") Integer bizId,
                                                 @Param("employeeId") Integer employeeId);

	public List<PlatformOrgPo> selectSubLevelOrgList(@Param("bizId") Integer bizId,
                                                     @Param("list") List<Integer> parentIdList);

	public List<PlatformOrgPo> selectSecLevelOrgList(@Param("bizId") Integer bizId);

	public List<PlatformOrgPo> getOrgListByIdSet(@Param("bizId") Integer bizId,
                                                 @Param("sets") Set<Integer> sets);
	
	public List<PlatformOrgPo> selectOrgListByIdSet(@Param("bizId") Integer bizId, @Param("sets") Set<Integer> sets);
	
	public List<PlatformOrgPo> selectSubDeptNumOrgList(@Param("bizId") Integer bizId,
                                                       @Param("list") List<Integer> parentIdList);
	
	/**
	 * 根据parentIds查询部门
	 * @param parentIdList
	 * @return
	 */
	public List<Integer> selectOrgListByParentIds(@Param("list") List<Integer> parentIdList);
}
