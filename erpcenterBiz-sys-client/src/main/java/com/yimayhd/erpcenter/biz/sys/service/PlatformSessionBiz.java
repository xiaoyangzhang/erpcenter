package com.yimayhd.erpcenter.biz.sys.service;

import com.yimayhd.erpcenter.dal.sys.po.UserSession;

public interface PlatformSessionBiz {
	
	public UserSession getUserSession(String sessionId);
	
	public int setUserSession(String sessionId,int seconds,UserSession userSession);
	
	public int delUserSession(String sessionId);
}
