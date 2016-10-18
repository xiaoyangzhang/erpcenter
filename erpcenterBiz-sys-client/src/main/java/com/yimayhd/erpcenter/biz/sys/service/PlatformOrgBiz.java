package com.yimayhd.erpcenter.biz.sys.service;

import java.util.List;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.dal.sys.po.SysDataRight;

public interface PlatformOrgBiz {
	public List<PlatformOrgPo> findByPid(Integer pid,Integer sysId);

	public PlatformOrgPo findByOrgId(Integer orgId);

	public int saveOrg(PlatformOrgPo po);

	public int delOrg(Integer orgId);

	public  List<PlatformOrgPo> getOrgList(Integer sysId, Integer orgId,Integer isSuper);
	public  List<PlatformOrgPo> getOrgList(Integer sysId, Integer employeeId);
	
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
	public List<PlatformOrgPo> getOrgTree(Integer bizId, Integer parentId);
	
	
	/**
	 * 查询组织名称是否已经存在
	 * @param orgName
	 * @param exceptOrgId
	 * @return
	 */
	public List<PlatformOrgPo> getOrgList(String orgName,int exceptOrgId);
	/**
	 * 根据用户id查询用户和其可查看的组织机构下的用户
	 * @param employeeId
	 * @return
	 */
	public List<SysDataRight> getSysDataRightByEmployeeId(Integer employeeId);
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

	public PlatformOrgPo getOrgInfo(int bizId, Integer orgId);

	public PlatformOrgPo getCompanyByEmployeeId(Integer bizId, Integer employeeId);		
	public List<SysDataRight> getViewUserId(Integer userId);

	//public String getOrgTreeByUserId();
	

	
	/**
	 * 根据id列表获取单位信息列表
	 * @param bizId
	 * @param sets
	 * @return
	 */
	List<PlatformOrgPo> getOrgListByIdSet(Integer bizId,Set<Integer> sets);
	
	/**
	 * 获取商家二级单位列表
	 * @param bizId
	 * @return
	 */
	List<PlatformOrgPo> getSecLevelOrgList(Integer bizId);
	/**
	 * 根据商家id和父id列表获取下级单位列表
	 * @param bizId
	 * @param parentIdList
	 * @return
	 */
	List<PlatformOrgPo> getSubLevelOrgList(Integer bizId,List<Integer> parentIdList);

}
