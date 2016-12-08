package com.yimayhd.erpcenter.facade.sys.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.dal.sys.po.SysDataRight;
import com.yimayhd.erpcenter.facade.sys.query.PlatformOrgPoDTO;
import com.yimayhd.erpcenter.facade.sys.result.PlatformOrgPoListResult;
import com.yimayhd.erpcenter.facade.sys.result.PlatformOrgPoResult;
import com.yimayhd.erpcenter.facade.sys.result.PlatformOrgSupplierAuthResult;
import com.yimayhd.erpcenter.facade.sys.result.SysDataRightListResult;

public interface SysPlatformOrgFacade {
	public PlatformOrgPoListResult findByPid(Integer pid,Integer sysId);

	public PlatformOrgPoResult findByOrgId(Integer orgId);

	public int saveOrg(PlatformOrgPoDTO po);

	public int delOrg(Integer orgId);

	public  PlatformOrgPoListResult getOrgList(Integer sysId, Integer orgId,Integer isSuper);
	public  PlatformOrgPoListResult getOrgList(Integer sysId, Integer employeeId);
	
	/**
	 * 查询组织机构树
	 * @param sysId  系统id
	 * @param parentId  父级id，为null则查询此系统下面的所有组织机构
	 * @return
	 */
	//public List<PlatformOrgPo> getOrgTree(Integer sysId, Integer parentId);
	
	/**
	 * add by gejinjun
	 * 注释掉参数为sysId的方法，添加参数为bizId的方法
	 * 只取当前商家的组织结构树即可
	 * @param bizId
	 * @param parentId
	 * @return
	 */
	public PlatformOrgPoListResult getOrgTree(Integer bizId, Integer parentId);
	
	
	/**
	 * 查询组织名称是否已经存在
	 * @param orgName
	 * @param exceptOrgId
	 * @return
	 */
	public PlatformOrgPoListResult getOrgList(String orgName,int exceptOrgId);
	/**
	 * 根据用户id查询用户和其可查看的组织机构下的用户
	 * @param employeeId
	 * @return
	 */
	public SysDataRightListResult getSysDataRightByEmployeeId(Integer employeeId);
	/**
	 * 根据orgId和bizId查询logo
	 * @param bizId
	 * @param orgId
	 * @return
	 */
	public String getLogoByOrgId(Integer bizId, Integer orgId);
	public String getCompanyCodeByOrgId(Integer bizId, Integer orgId);
	
	public String getComponentOrgTreeJsonStr(Integer bizId);
	public String getComponentOrgTreeJsonStr2(String orgIds,String userIds);

	public PlatformOrgPoResult getOrgInfo(int bizId, Integer orgId);

	public PlatformOrgPoResult getCompanyByEmployeeId(Integer bizId, Integer employeeId);		
	public SysDataRightListResult getViewUserId(Integer userId);

	//public String getOrgTreeByUserId();
	

	
	/**
	 * 根据id列表获取单位信息列表
	 * @param bizId
	 * @param sets
	 * @return
	 */
	PlatformOrgPoListResult getOrgListByIdSet(Integer bizId,Set<Integer> sets);
	
	/**
	 * 获取商家二级单位列表
	 * @param bizId
	 * @return
	 */
	PlatformOrgPoListResult getSecLevelOrgList(Integer bizId);
	/**
	 * 根据商家id和父id列表获取下级单位列表
	 * @param bizId
	 * @param parentIdList
	 * @return
	 */
	PlatformOrgPoListResult getSubLevelOrgList(Integer bizId,List<Integer> parentIdList);

	public PlatformOrgPo getCompanyByEmployeeId2(Integer bizId,
												 Integer employeeId) ;

	String[] getOrgMappingSupplierId(Integer orgId);

	PlatformOrgSupplierAuthResult getOrgSupplierAuth(Integer orgId,Integer bizId);

	public PageBean orgSupplierAuthTable(Integer orgId, Integer bizId,Integer page,Integer pageSize,
										 String authStatus,Map<String, Object> requestParam );

	public void saveOrgAuthSuppliers(int orgId,int bizId,List supplierIds,List delSupplierIds);
}
