package com.yimayhd.erpcenter.facade.sys.service;

import com.yimayhd.erpcenter.facade.sys.query.UserSessionDTO;
import com.yimayhd.erpcenter.facade.sys.result.PlatformEmployeeResult;
import com.yimayhd.erpcenter.facade.sys.result.SysBizInfoResult;
import com.yimayhd.erpcenter.facade.sys.result.UserSessionResult;
/**
 * 
 * 描述：用户登陆相关的Facade
 * @author liyong
 * 2016年10月20日
 */
public interface SysLoginFacade {
	
	 /**
     * 根据企业编码获取企业信息
     * @author liyong
     * @param code
     * @return
     */
	SysBizInfoResult getBizInfoByCode(String code);
	/**
	 * 根据商家id和登陆名称获取用户信息
	 * @param loginName
	 * @return
	 */
	PlatformEmployeeResult getEmployeeByBizIdAndLoginName(Integer bizId,String loginName);
//	/**
//	 * 
//	 * 描述：根据商家id和登陆名称获取用户信息
//	 * @author liyong
//	 * 2016年10月20日 描述：
//	 * @param userId
//	 * @return
//	 */
//	PlatformEmployeeResult findByEmployeeId(String userId);
	/**
	 * 
	 * 描述：获取用户的UserSessionResult通过多个方法
	 * @author liyong
	 * 2016年10月20日 描述：
	 * @param bizId
	 * @param orgId
	 * @param employeeId
	 * @param isSuper
	 * @return
	 */
	UserSessionResult getUserSessionResult(int bizId,Integer orgId,Integer employeeId,Integer isSuper);
	/**
	 * 
	 * 描述：将用户的session 信息放入缓存
	 * @author liyong
	 * 2016年10月20日 描述：
	 * @param sessionId
	 * @param sessionTimeoutSeconds
	 * @param userSessionDTO
	 * @return
	 */
	int setUserSession(String sessionId,int sessionTimeoutSeconds,UserSessionDTO userSessionDTO);
	/**
	 * 
	 * 描述：从缓存中取用户的session
	 * @author liyong
	 * 2016年10月20日 描述：
	 * @param sessionId
	 * @return
	 */
	UserSessionResult getUserSession(String sessionId);
	/**
	 * 
	 * 描述：从缓存中删除session
	 * @author liyong
	 * 2016年10月20日 描述：
	 * @param sessionId
	 * @return
	 */
	int delUserSession(String sessionId);

}
