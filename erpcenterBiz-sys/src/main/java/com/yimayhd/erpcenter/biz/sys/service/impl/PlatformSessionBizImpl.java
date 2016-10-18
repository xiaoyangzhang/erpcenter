package com.yimayhd.erpcenter.biz.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.yimayhd.erpcenter.biz.sys.service.PlatformSessionBiz;
import com.yimayhd.erpcenter.dal.sys.po.UserSession;
import com.yimayhd.erpcenter.dal.sys.service.PlatformSessionDal;

public class PlatformSessionBizImpl implements PlatformSessionBiz {

	@Autowired
	private PlatformSessionDal platformSessionDal;
	
	@Override
	public UserSession getUserSession(String sessionId) {
		return platformSessionDal.getUserSession(sessionId);
	}

	@Override
	public int setUserSession(String sessionId, int seconds,
			UserSession userSession) {
		return platformSessionDal.setUserSession(sessionId, seconds, userSession);
	}

	@Override
	public int delUserSession(String sessionId) {
		return platformSessionDal.delUserSession(sessionId);
	}

}
