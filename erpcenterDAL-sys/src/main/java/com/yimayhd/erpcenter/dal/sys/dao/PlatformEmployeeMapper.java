package com.yimayhd.erpcenter.dal.sys.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;

public interface PlatformEmployeeMapper {
	
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
	public List<PlatformEmployeePo> getEmployeeListPage(PageBean pageBean);
	/**
	 * 更新用户
	 * @param platformEmployeePo
	 * @return
	 */
	public int updateEmployee(PlatformEmployeePo platformEmployeePo);
	/**
	 * 添加用户
	 * @param platformEmployeePo
	 * @return
	 */
	public int addEmployee(PlatformEmployeePo platformEmployeePo);
	/**
	 * 根据用户id删除用户信息
	 * @param employeeId
	 * @return
	 */
	public int delByEmployeeId(Integer employeeId);
	/**
	 * 根据用户id逻辑删除用户信息
	 * @param employeeId
	 * @return
	 */
	public int deleteByEmployeeId(Integer employeeId);
	/**
	 * 根据登陆名称获取用户
	 * @param loginName
	 * @return
	 */
	public PlatformEmployeePo getEmployeeByLoginName(String loginName);
	/**
	 * 根据商家id和登陆名称获取用户
	 * @param loginName
	 * @return
	 */
	public PlatformEmployeePo getEmployeeByBizIdAndLoginName(@Param("bizId") Integer bizId, @Param("loginName") String loginName);
	
	/**
	 * 根据用户id关联机构
	 * @param loginName
	 * @return
	 */
	public int addEmpOrg(Integer employeeId, Integer orgId);
	
	/**
	 * 根据用户id关联角色
	 * @param loginName
	 * @return
	 */
	public int addEmpRole(Integer employeeId, Integer roleId);
	
	/**
	 * 根据用户id和系统id删除所有角色
	 * 
	 */
	public int byEmpDelRole(@Param("employeeId") Integer employeeId, @Param("bizId") Integer bizId);
	/**
	 * 根据用户id和系统id删除所有组织机构
	 * 
	 */
	public void byEmpDelOrg(Integer employeeId, Integer sysId);
	/**
	 * 验证用户名唯一性
	 * @param name
	 * @param id
	 * @return
	 */
	/*public int validationName(@Param("name")String name,@Param("id")Integer id);*/
	public int getEmployeeList(@Param("loginName") String loginName,
                               @Param("employeeId") Integer employeeId, @Param("bizId") Integer bizId);
	
	/**
	 * 根据orgId获取下属人员
	 * 
	 */
	public List<PlatformEmployeePo> getListByOrgId(@Param("orgId") Integer orgId);
	public List<PlatformEmployeePo> getListByOrgId2(@Param("orgId") Integer orgId, @Param("userIds") String userIds);
	public List<PlatformEmployeePo> getUserIdListByOrgId(@Param("bizId") Integer bizId,
                                                         @Param("orgId") Integer orgId);
	
	public List<PlatformEmployeePo> getUserIdListByOrgIdList(@Param("bizId") Integer bizId,
                                                             @Param("list") Set<Integer> orgIdSet);
	
	/**
	 * 根据org_path查询该机构下所有用户
	 * @param orgPath
	 * @return
	 */
	public List<PlatformEmployeePo> findByOrgPath(@Param("orgPath") String orgPath);
	
	public List<PlatformEmployeePo> getEmpListByEmpName(@Param("bizId") Integer bizId, @Param("name") String name);
	public List<PlatformEmployeePo> getEmpListByLoginName(@Param("bizId") Integer bizId, @Param("name") String name);
	/**
	 * 根据商家id和用户集合set查询用户
	 * @param bizId
	 * @param set
	 * @return
	 */
	public List<PlatformEmployeePo> getEmpList(@Param("bizId") Integer bizId, @Param("set") Set<Integer> set);
	
	List<PlatformEmployeePo> getOrgIdListByEmployee(@Param("bizId") Integer bizId, @Param("set") Set<Integer> set);
	
	/**
	 * 根据条件分页查询员工信息
	 * @param pageBean
	 * @return
	 */
	public List<PlatformEmployeePo> getOrgEmployeeListPage(@Param("page") PageBean pageBean);
}
