package com.yimayhd.erpcenter.facade.sys.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sys.service.MsgInfoDetailBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformMenuBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformRoleBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformSessionBiz;
import com.yimayhd.erpcenter.biz.sys.service.SysBizConfigBiz;
import com.yimayhd.erpcenter.biz.sys.service.SysBizInfoBiz;
import com.yimayhd.erpcenter.dal.sys.po.MsgInfoDetail;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformMenuPo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformRolePo;
import com.yimayhd.erpcenter.dal.sys.po.SysBizInfo;
import com.yimayhd.erpcenter.dal.sys.po.UserSession;
import com.yimayhd.erpcenter.dal.sys.vo.MenuOptVo;
import com.yimayhd.erpcenter.facade.sys.query.UserSessionDTO;
import com.yimayhd.erpcenter.facade.sys.result.PlatformEmployeeResult;
import com.yimayhd.erpcenter.facade.sys.result.SysBizInfoResult;
import com.yimayhd.erpcenter.facade.sys.result.UserSessionResult;
import com.yimayhd.erpcenter.facade.sys.service.SysLoginFacade;

public class SysLoginFacadImpl implements SysLoginFacade{
	@Autowired
	private SysBizInfoBiz sysBizInfoBiz;
	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;
	
	@Autowired
	private PlatformRoleBiz platformRoleBiz;
	@Autowired
	private PlatformOrgBiz platformOrgBiz;
	@Autowired
	private PlatformMenuBiz platformMenuBiz;
	@Autowired
	private SysBizConfigBiz sysBizConfigBiz;
	@Autowired
	private PlatformSessionBiz platformSessionBiz;
	@Autowired
	private MsgInfoDetailBiz msgInfoDetailBiz;

	@Override
	public SysBizInfoResult getBizInfoByCode(String code) {
		SysBizInfoResult result = new SysBizInfoResult();
		SysBizInfo sys = sysBizInfoBiz.getBizInfoByCode(code);//根据企业编码获取企业信息
		BeanUtils.copyProperties(sys, result);//将对应的属性值拷贝到result中
		return result;
	}

	@Override
	public PlatformEmployeeResult getEmployeeByBizIdAndLoginName(Integer bizId, String loginName) {
		PlatformEmployeeResult result = new PlatformEmployeeResult();
		PlatformEmployeePo po = platformEmployeeBiz.getEmployeeByBizIdAndLoginName(bizId, loginName);//根据商家id和登陆名称获取用户信息
		if (po == null) {
			return null;
		}
		BeanUtils.copyProperties(po, result);//将对应的属性值拷贝到result中
		return result;
	}
	

	
	@Override
	public UserSessionResult getUserSessionResult(int bizId, Integer orgId, Integer employeeId, Integer isSuper) {
		UserSessionResult result = new UserSessionResult();
		PlatformOrgPo orgInfo = platformOrgBiz.getOrgInfo(bizId,orgId);
		List<PlatformRolePo> roleList = platformRoleBiz.getRoleList(bizId, employeeId,isSuper);
		List<PlatformMenuPo> menuList = platformMenuBiz.getMenuList(bizId, roleList,isSuper);
		MenuOptVo menuOptVo = platformMenuBiz.getOptMaps(bizId, roleList,isSuper);
		Set<Integer> userIdSet = platformEmployeeBiz.getDataRightListByUserId(bizId,employeeId);
		//添加配置
		Map<String,String> bizConfigMap = sysBizConfigBiz.getConfigMapByBizId(bizId);
		result.setMenuOptMap(menuOptVo.getMenuOptMap());
		result.setOptMap(menuOptVo.getOptMap());
		//userSession.setOrgList(orgList);
		result.setOrgInfo(orgInfo);
		result.setRoleList(roleList);
		result.setMenuList(menuList);
		//model.addAttribute("userSession", userSession);
		result.setDataUserIdSet(userIdSet);
		result.setBizConfigMap(bizConfigMap);
		
		return result;
	}

	@Override
	public int setUserSession(String sessionId, int sessionTimeoutSeconds, UserSessionDTO userSessionDTO) {
		UserSession userSession = userSessionDTO.getUserSession();
		return platformSessionBiz.setUserSession(sessionId, sessionTimeoutSeconds, userSession);
	}

	@Override
	public UserSessionResult getUserSession(String sessionId) {
		UserSessionResult result = new UserSessionResult();
		UserSession userSession =  platformSessionBiz.getUserSession(sessionId);
		BeanUtils.copyProperties(userSession, result);
		return result;
	}

	@Override
	public int delUserSession(String sessionId) {
		
		return platformSessionBiz.delUserSession(sessionId);
	}
	
	public MsgInfoDetail findMsgCountByUserId(Integer bizId,Integer userId){
		return msgInfoDetailBiz.findMsgCountByUserId(bizId, userId);
	};

}
