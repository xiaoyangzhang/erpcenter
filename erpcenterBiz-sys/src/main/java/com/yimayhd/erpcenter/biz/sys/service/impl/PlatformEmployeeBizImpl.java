package com.yimayhd.erpcenter.biz.sys.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.dal.sys.po.SysDataRight;
import com.yimayhd.erpcenter.dal.sys.service.PlatformEmployeeDal;

public class PlatformEmployeeBizImpl implements PlatformEmployeeBiz {
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformEmployeeBizImpl.class);
	
	
	private PlatformEmployeeDal platformEmployeeDal;
	
	@Override
	public PlatformEmployeePo findByEmployeeId(Integer employeeId) {
		return platformEmployeeDal.findByEmployeeId(employeeId);
	}

	@Override
	public PageBean getEmployeeList(
			PlatformEmployeePo platformEmployeePo,Integer page) {
		return platformEmployeeDal.getEmployeeList(platformEmployeePo,page);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int saveEmployee(PlatformEmployeePo platformEmployeePo,Integer bizId) throws ClientException {
		
		
		return platformEmployeeDal.saveEmployee(platformEmployeePo, bizId);
	}

	@Override
	public int delByEmployeeId(Integer employeeId) {
		
		return platformEmployeeDal.delByEmployeeId(employeeId);
	}
	
	@Override
	public int deleteByEmployeeId(Integer employeeId) {
		return platformEmployeeDal.deleteByEmployeeId(employeeId);
	}

	@Override
	public PlatformEmployeePo getEmployeeByLoginName(String loginName) {
		return platformEmployeeDal.getEmployeeByLoginName(loginName);
	}

	@Override
	public PlatformEmployeePo getEmployeeByBizIdAndLoginName(Integer bizId,String loginName) {
		return platformEmployeeDal.getEmployeeByBizIdAndLoginName(bizId,loginName);
	}
	
	public int updateEmployee(PlatformEmployeePo platformEmployeePo){
		return platformEmployeeDal.updateEmployee(platformEmployeePo);
	}

	/*@Override
	public int validationName(String name, Integer id) {
		return platformEmployeeMapper.validationName(name,id);
		
	}
*/
	@Override
	public int getEmployeeList(String loginName,
			Integer employeeId,Integer bizId) {
		
		return platformEmployeeDal.getEmployeeList( loginName,
				 employeeId, bizId);
	}

	/**
	 * 根据商家id获取组织机构与用户的树节点集合
	 */
	@Override
	public List<Map<String, String>> getOrgUserTree(Integer bizId,Integer parentOrgId,String type) {
		return platformEmployeeDal.getOrgUserTree(bizId, parentOrgId, type);
	}
	
	@Override
	public List<Map<String, String>> getOrgUserTreeFuzzy(Integer bizId,String name,String type) {
		return platformEmployeeDal.getOrgUserTreeFuzzy(bizId, name, type);
	}
	
	
	/**
	 * 根据商家id获取组织机构与用户的树节点集合
	 */
	@Override
	public List<Map<String, String>> getOrgUserDateRightTree(Integer bizId,Integer parentOrgId,String type,Integer employeeId) {
		return platformEmployeeDal.getOrgUserDateRightTree(bizId, parentOrgId, type, employeeId);
	}

	/**
	 * 判断当前部门是否有下级部门
	 * @param orgId
	 * @param orgList
	 * @return
	 */
	private boolean hasSubOrg(int orgId,List<PlatformOrgPo> orgList){
		for(PlatformOrgPo org :orgList){
			if(orgId==org.getParentId().intValue()){
				return true;
			}
		}
		return false;
	}
	
	private Map<Integer,Boolean> convertDataRightToMap(List<SysDataRight> dataRightList){
		Map<Integer,Boolean> map = new HashMap<Integer,Boolean>();
		for(SysDataRight right : dataRightList){
			map.put(right.getViewUserId(),true);
		}
		return map;
	}
	
	@Override
	public List<Map<String, String>> getOrgUserDateRightTreeByByViewUsrId(
			Integer curBizId, Integer parentOrgId, String type, Integer employeeId) {
		return platformEmployeeDal.getOrgUserDateRightTreeByByViewUsrId(curBizId, parentOrgId, type, employeeId);
	}
	
	@Override
	public Set<Integer> getUserIdListByOrgId(Integer bizId, Integer orgId) {
		return platformEmployeeDal.getUserIdListByOrgId(bizId, orgId);
	}

	@Override
	public Set<Integer> getUserIdListByOrgIdList(Integer bizId, Set<Integer> orgIdSet) {
		return platformEmployeeDal.getUserIdListByOrgIdList(bizId, orgIdSet);
	}
	
	@Override
	public void delSysDataRight(SysDataRight dataRight) {
		platformEmployeeDal.delSysDataRight(dataRight);
	}

	@Override
	public int insertSysDataRight(List<PlatformEmployeePo> emplList,Integer employeeId) {
		return platformEmployeeDal.insertSysDataRight(emplList, employeeId);
	}

	@Override
	public Set<Integer> getDataRightListByUserId(Integer bizId,Integer userId) {
		return platformEmployeeDal.getDataRightListByUserId(bizId, userId);
	}

	@Override
	public String findByOrgPath(String orgPath) {
		return platformEmployeeDal.findByOrgPath(orgPath);
	}

	public void saveDataRight(List<Map> list, Integer employeeId) {
		platformEmployeeDal.saveDataRight(list, employeeId);
	}

	@Override
	public List<PlatformEmployeePo> getEmployeeListByOrgId(Integer orgId) {
		return platformEmployeeDal.getEmployeeListByOrgId(orgId);
	}

	@Override
	public String getComponentOrgUserTreeJsonStr(Integer bizId) {
		return platformEmployeeDal.getComponentOrgUserTreeJsonStr(bizId);
	}

	@Override
	public List<PlatformEmployeePo> getEmployeeListByName(Integer bizId,String name) {
		return platformEmployeeDal.getEmployeeListByName(bizId, name);
	}

	@Override
	public String getComponentOrgUserTreeJsonStr2(String userIds,String orgIds) {
		return platformEmployeeDal.getComponentOrgUserTreeJsonStr2(userIds, orgIds);
	}

	@Override
	public List<PlatformEmployeePo> getEmpList(Integer bizId, Set<Integer> set) {
		return platformEmployeeDal.getEmpList(bizId, set);
	}

}
