package com.yimayhd.erpcenter.biz.sys.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sys.service.result.SearchEmployeeListResult;
import com.yimayhd.erpcenter.biz.sys.service.result.SearchEmployeeResult;
import com.yimayhd.erpcenter.biz.sys.service.result.SearchOrgEmployListResult;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.dal.sys.po.SysDataRight;

public interface PlatformEmployeeBiz {
	
	
	/**
	 * 根据用户id查询用户信息
	 * @param employeeId
	 * @return
	 */
	public PlatformEmployeePo findByEmployeeId(Integer employeeId);
	/**
	 * 查询用户信息
	 * @param platformEmployeePo
	 * @return
	 */
	public PageBean getEmployeeList(PlatformEmployeePo platformEmployeePo,Integer page);
	/**
	 * 更新用户
	 * @param platformEmployeePo
	 * @param sysId 
	 * @return  
	 */
	public int saveEmployee(PlatformEmployeePo platformEmployeePo, Integer bizId) throws ClientException;
	/**
	 * 根据用户id删除用户信息
	 * @param employeeId
	 * @return
	 */
	public int delByEmployeeId(Integer employeeId);
	/**
	 * 根据用户id逻辑删除用户
	 * @param employeeId
	 * @return
	 */
	public int deleteByEmployeeId(Integer employeeId);
	
	/**
	 * 根据登陆名称获取用户信息
	 * @param loginName
	 * @return
	 */
	public PlatformEmployeePo getEmployeeByLoginName(String loginName);
	/**
	 * 根据商家id和登陆名称获取用户信息
	 * @param loginName
	 * @return
	 */
	public PlatformEmployeePo getEmployeeByBizIdAndLoginName(Integer bizId,String loginName);

	/**
	 * 修改用户名和密码
	 */
	public int updateEmployee(PlatformEmployeePo platformEmployeePo);
	/**
	 * 验证用户名
	 * @param name
	 * @param id
	 * @return
	 */
/*	public int validationName(String name, Integer id);*/
	public int getEmployeeList(String loginName,
			Integer employeeId,Integer bizId);
	
	/**
	 * 根据商家和父ID获取组织机构和用户的数据集合
	 * @param bizId
	 * @param parentOrgId
	 * @return
	 */
	public List<Map<String,String>> getOrgUserDateRightTree(Integer bizId,Integer parentOrgId,String type,Integer employeeId);	
	
	/**
	 * 根据商家和单位id获取当前单位下所有人员id
	 * @param bizId
	 * @param orgId
	 * @return
	 */
	public Set<Integer> getUserIdListByOrgId(Integer bizId,Integer orgId);
	/**
	 * 根据商家和单位id获取当前单位下所有人员id
	 * @param bizId
	 * @param orgIdSet
	 * @return
	 */
	public Set<Integer> getUserIdListByOrgIdList(Integer bizId,Set<Integer> orgIdSet);
	
	public Set<Integer> getDataRightListByUserId(Integer bizId,Integer userId);
	
	public void delSysDataRight(SysDataRight dataRight);
	public int insertSysDataRight(List<PlatformEmployeePo> emplList,Integer employeeId); 
	
	public List<Map<String, String>> getOrgUserTree(Integer bizId,Integer parentOrgId,String type);
	List<Map<String, String>> getOrgUserTreeFuzzy(Integer bizId, String name,
			String type);
	
	/**
	 * 根据orgPath查询该机构下所有用户ID
	 * @param pid
	 * @return
	 */
	public String findByOrgPath(String orgPath) ;
	/**
	 * 获取那些人能查看employeeID
	 * @param curBizId
	 * @param object
	 * @param string
	 * @param employeeId
	 * @return
	 */
	public List<Map<String, String>> getOrgUserDateRightTreeByByViewUsrId(
			Integer curBizId, Integer parentOrgId, String type, Integer employeeId);
	
	/**
	 * 保存数据权限
	 * @param type normal/reverse
	 * @param list 选择的用户id
	 * @param employeeId 
	 */
	public void saveDataRight(List<Map> list, Integer employeeId);
	
	public List<PlatformEmployeePo> getEmployeeListByOrgId(Integer orgId);
	public List<PlatformEmployeePo> getEmployeeListByName(Integer bizId,String name);
	
	public String getComponentOrgUserTreeJsonStr(Integer bizId);
	public String getComponentOrgUserTreeJsonStr2(String userIds,String orgIds);
	/**
	 * 根据商家id和用户id集合查询用户
	 * @param bizId
	 * @param set
	 * @return
	 */
	public List<PlatformEmployeePo> getEmpList(Integer bizId,Set<Integer> set);
	
	/**
	 * 查询部门员工信息
	 */
	public List<PlatformEmployeePo> getOrgIdListByEmployee(Integer bizId, Set<Integer> set);
	
	/**
	 * 根据组织ID，查询组织下所有员工信息
	 * @param orgIds
	 * @return
	 */
	public SearchOrgEmployListResult getOrgEmployeeListPage(List<Integer> orgIds, int page, int pageSize);
	
	/**
	 * 根据组织ID，查询组织下所有员工信息
	 * @param orgIds
	 * @return
	 */
	public SearchOrgEmployListResult getOrgEmployeeListPage(int orgId, int page, int pageSize);
	
	/**
	 * 查询员工信息
	 */
	public SearchEmployeeResult getEmployeeById(long id);
	
	/**
	 * 批量查询员工信息
	 */
	public SearchEmployeeListResult getEmployeeByIds(Set<Long> ids);
}
