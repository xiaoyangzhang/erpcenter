package com.yimayhd.erpcenter.facade.sys.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.UserSession;
/**
 * 
 * 描述：UserSession的参数封装类
 * @author liyong
 * 2016年10月20日
 */
public class UserSessionDTO implements Serializable {
	
	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月20日 
	 */
	private static final long serialVersionUID = 1L;
	private UserSession userSession = new UserSession();

	public UserSession getUserSession() {
		return userSession;
	}
	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}
	
	

}
