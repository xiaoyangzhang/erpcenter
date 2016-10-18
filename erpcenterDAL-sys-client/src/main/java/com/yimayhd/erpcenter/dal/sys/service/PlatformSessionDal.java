package com.yimayhd.erpcenter.dal.sys.service;

import com.yimayhd.erpcenter.dal.sys.po.UserSession;

public interface PlatformSessionDal {
	
	public UserSession getUserSession(String sessionId);
	
	public int setUserSession(String sessionId,int seconds,UserSession userSession);
	
	public int delUserSession(String sessionId);
}
