package com.yimayhd.erpcenter.facade.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.dal.sys.po.SysDataRight;
import com.yimayhd.erpcenter.facade.sys.query.PlatformEmployeePoDTO;
import com.yimayhd.erpcenter.facade.sys.query.PlatformEmployeePoListDTO;
import com.yimayhd.erpcenter.facade.sys.query.SysDataRightDTO;
import com.yimayhd.erpcenter.facade.sys.result.PlatformEmployeePoListResult;
import com.yimayhd.erpcenter.facade.sys.result.PlatformEmployeePoResult;
import com.yimayhd.erpcenter.facade.sys.service.SysPlatformEmployeeFacade;

public class SysPlatformEmployeeFacadeImpl implements SysPlatformEmployeeFacade {
	
	private static final Logger logger = LoggerFactory.getLogger(SysPlatformEmployeeFacadeImpl.class);
	
	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;
	
	@Override
	public PlatformEmployeePoResult findByEmployeeId(Integer employeeId) {
		PlatformEmployeePo platformEmployeePo =  platformEmployeeBiz.findByEmployeeId(employeeId);
		PlatformEmployeePoResult result = new PlatformEmployeePoResult();
		if(platformEmployeePo!=null)
			result.setPlatformEmployeePo(platformEmployeePo);
		return result;
	}

	@Override
	public PageBean getEmployeeList(
			PlatformEmployeePoDTO platformEmployeePo,Integer page) {
		return platformEmployeeBiz.getEmployeeList(platformEmployeePo.getPlatformEmployeePo(),page);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int saveEmployee(PlatformEmployeePoDTO platformEmployeePo,Integer bizId) throws ClientException {
		
		
		return platformEmployeeBiz.saveEmployee(platformEmployeePo.getPlatformEmployeePo(), bizId);
	}

	@Override
	public int delByEmployeeId(Integer employeeId) {
		
		return platformEmployeeBiz.delByEmployeeId(employeeId);
	}
	
	@Override
	public int deleteByEmployeeId(Integer employeeId) {
		return platformEmployeeBiz.deleteByEmployeeId(employeeId);
	}

	@Override
	public PlatformEmployeePoResult getEmployeeByLoginName(String loginName) {
		PlatformEmployeePo platformEmployeePo = platformEmployeeBiz.getEmployeeByLoginName(loginName);
		PlatformEmployeePoResult result = new PlatformEmployeePoResult();
		if(platformEmployeePo!=null)
			result.setPlatformEmployeePo(platformEmployeePo);
		return result;
	}

	@Override
	public PlatformEmployeePoResult getEmployeeByBizIdAndLoginName(Integer bizId,String loginName) {
		PlatformEmployeePo platformEmployeePo = platformEmployeeBiz.getEmployeeByBizIdAndLoginName(bizId,loginName);
		PlatformEmployeePoResult result = new PlatformEmployeePoResult();
		if(platformEmployeePo!=null)
			result.setPlatformEmployeePo(platformEmployeePo);
		return result;
	}

	/*@Override
	public int validationName(String name, Integer id) {
		return platformEmployeeMapper.validationName(name,id);
		
	}
*/
	@Override
	public int getEmployeeList(String loginName,
			Integer employeeId,Integer bizId) {
		
		return platformEmployeeBiz.getEmployeeList( loginName,
				 employeeId, bizId);
	}

	/**
	 * 根据商家id获取组织机构与用户的树节点集合
	 */
	@Override
	public List<Map<String, String>> getOrgUserTree(Integer bizId,Integer parentOrgId,String type) {
		return platformEmployeeBiz.getOrgUserTree(bizId, parentOrgId, type);
	}
	
	@Override
	public List<Map<String, String>> getOrgUserTreeFuzzy(Integer bizId,String name,String type) {
		return platformEmployeeBiz.getOrgUserTreeFuzzy(bizId, name, type);
	}
	
	
	/**
	 * 根据商家id获取组织机构与用户的树节点集合
	 */
	@Override
	public List<Map<String, String>> getOrgUserDateRightTree(Integer bizId,Integer parentOrgId,String type,Integer employeeId) {
		return platformEmployeeBiz.getOrgUserDateRightTree(bizId, parentOrgId, type, employeeId);
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
		return platformEmployeeBiz.getOrgUserDateRightTreeByByViewUsrId(curBizId, parentOrgId, type, employeeId);
	}
	
	@Override
	public Set<Integer> getUserIdListByOrgId(Integer bizId, Integer orgId) {
		return platformEmployeeBiz.getUserIdListByOrgId(bizId, orgId);
	}

	@Override
	public Set<Integer> getUserIdListByOrgIdList(Integer bizId, Set<Integer> orgIdSet) {
		return platformEmployeeBiz.getUserIdListByOrgIdList(bizId, orgIdSet);
	}
	
	@Override
	public void delSysDataRight(SysDataRightDTO dataRight) {
		platformEmployeeBiz.delSysDataRight(dataRight.getSysDataRight());
	}

	@Override
	public int insertSysDataRight(PlatformEmployeePoListDTO emplList,Integer employeeId) {
		return platformEmployeeBiz.insertSysDataRight(emplList.getPlatformEmployeePos(), employeeId);
	}

	@Override
	public Set<Integer> getDataRightListByUserId(Integer bizId,Integer userId) {
		return platformEmployeeBiz.getDataRightListByUserId(bizId, userId);
	}

	@Override
	public String findByOrgPath(String orgPath) {
		return platformEmployeeBiz.findByOrgPath(orgPath);
	}

	public void saveDataRight(List<Map> list, Integer employeeId) {
		platformEmployeeBiz.saveDataRight(list, employeeId);
	}

	@Override
	public PlatformEmployeePoListResult getEmployeeListByOrgId(Integer orgId) {
		List<PlatformEmployeePo> platformEmployeePos = platformEmployeeBiz.getEmployeeListByOrgId(orgId);
		PlatformEmployeePoListResult result = new PlatformEmployeePoListResult();
		if(platformEmployeePos!=null)
			result.setPlatformEmployeePos(platformEmployeePos);
		return result;
	}

	@Override
	public String getComponentOrgUserTreeJsonStr(Integer bizId) {
		return platformEmployeeBiz.getComponentOrgUserTreeJsonStr(bizId);
	}

	@Override
	public PlatformEmployeePoListResult getEmployeeListByName(Integer bizId,String name) {
		List<PlatformEmployeePo> platformEmployeePos = platformEmployeeBiz.getEmployeeListByName(bizId, name);
		PlatformEmployeePoListResult result = new PlatformEmployeePoListResult();
		if(platformEmployeePos!=null)
			result.setPlatformEmployeePos(platformEmployeePos);
		return result;
	}

	@Override
	public String getComponentOrgUserTreeJsonStr2(String userIds,String orgIds) {
		return platformEmployeeBiz.getComponentOrgUserTreeJsonStr2(userIds, orgIds);
	}

	@Override
	public PlatformEmployeePoListResult getEmpList(Integer bizId, Set<Integer> set) {
		List<PlatformEmployeePo> platformEmployeePos = platformEmployeeBiz.getEmpList(bizId, set);
		PlatformEmployeePoListResult result = new PlatformEmployeePoListResult();
		if(platformEmployeePos!=null)
			result.setPlatformEmployeePos(platformEmployeePos);
		return result;
	}

	@Override
	public int updateEmployee(PlatformEmployeePoDTO platformEmployeePo) {
		return platformEmployeeBiz.updateEmployee(platformEmployeePo.getPlatformEmployeePo());
	}

}
