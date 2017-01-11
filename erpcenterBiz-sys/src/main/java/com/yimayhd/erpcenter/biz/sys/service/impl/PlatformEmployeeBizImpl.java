package com.yimayhd.erpcenter.biz.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.errorCode.SysErrorCode;
import com.yimayhd.erpcenter.biz.sys.service.result.SearchEmployeeListResult;
import com.yimayhd.erpcenter.biz.sys.service.result.SearchEmployeeResult;
import com.yimayhd.erpcenter.biz.sys.service.result.SearchOrgEmployListResult;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.sys.constants.Constants;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.dal.sys.po.SysDataRight;
import com.yimayhd.erpcenter.dal.sys.service.PlatformEmployeeDal;
import com.yimayhd.erpcenter.dal.sys.service.PlatformOrgDal;

public class PlatformEmployeeBizImpl implements PlatformEmployeeBiz {
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformEmployeeBizImpl.class);
	
	@Autowired
	private PlatformEmployeeDal platformEmployeeDal;
	@Autowired
	private PlatformOrgDal platformOrgDal;
	
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
	
	/**
	 * 查询部门员工信息
	 */
	@Override
	public List<PlatformEmployeePo> getOrgIdListByEmployee(Integer bizId, Set<Integer> set) {
		List<PlatformEmployeePo> empBeanList = platformEmployeeDal.getOrgIdListByEmployee(bizId, set);
		return empBeanList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public SearchOrgEmployListResult getOrgEmployeeListPage(List<Integer> orgIds, int page, int pageSize) {
		SearchOrgEmployListResult result = new SearchOrgEmployListResult();
		if(CollectionUtils.isEmpty(orgIds)){
			result.setErrorCode(SysErrorCode.PARAM_ERROR);
			return result;
		}
		
		try{
			List<Integer> allOrgIds = new ArrayList<Integer>();
			allOrgIds.addAll(orgIds);
			while(true){
				orgIds = platformOrgDal.selectOrgListByParentIds(orgIds);
				if(CollectionUtils.isEmpty(orgIds)){
					break;
				}
				allOrgIds.addAll(orgIds);
			}
			PageBean pageBean = new PageBean();
			if(page == 0){
				pageBean.setPage(1);
			}else{
				pageBean.setPage(page);
			}
			if(pageSize == 0){
				pageBean.setPageSize(Constants.PAGESIZE);
			}else{
				pageBean.setPageSize(pageSize);
			}
			
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("orgIds", allOrgIds);
			pageBean.setParameter(parameter);
			List<PlatformEmployeePo> employeeList = platformEmployeeDal.getOrgEmployeeListPage(pageBean);
			result.setEmployeeList(employeeList);
			result.setTotalCount(pageBean.getTotalCount());
			result.setTotalPage(pageBean.getTotalPage());
		}catch(Exception ex){
			result.setErrorCode(SysErrorCode.SYSTEM_ERROR);
			ex.printStackTrace();
		}
		
		return result;
	}

	@Override
	public SearchOrgEmployListResult getOrgEmployeeListPage(int orgId, int page, int pageSize) {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(orgId);
		SearchOrgEmployListResult result = getOrgEmployeeListPage(ids, page, pageSize);
		return result;
	}

	@Override
	public SearchEmployeeResult getEmployeeById(long id) {
		
		SearchEmployeeResult result = new SearchEmployeeResult();
		try{
			Set<Long> ids = new HashSet<Long>();
			ids.add(id);
			SearchEmployeeListResult listResult = getEmployeeByIds(ids);
			if(!listResult.isSuccess()){
				return result;
			}
			
			List<PlatformEmployeePo> list = listResult.getEmployeeList();
			if(list != null && list.size() > 0){
				result.setEmployee(list.get(0));
			}
		}catch(Exception ex){
			ex.printStackTrace();
			result.setErrorCode(SysErrorCode.SYSTEM_ERROR);
		}
		
		return result;
	}

	@Override
	public SearchEmployeeListResult getEmployeeByIds(Set<Long> ids) {
		
		SearchEmployeeListResult result = new SearchEmployeeListResult();
		if(ids == null || ids.size() == 0){
			result.setErrorCode(SysErrorCode.PARAM_ERROR);
			return result;
		}
		
		try{
			List<PlatformEmployeePo> list = platformEmployeeDal.getEmployeeByIds(ids);
			result.setEmployeeList(list);
		}catch(Exception ex){
			ex.printStackTrace();
			result.setErrorCode(SysErrorCode.SYSTEM_ERROR);
		}
		
		return result;
	}

}
