package com.yimayhd.erpcenter.dal.sys.service.impl;

import com.yihg.architect.redis.JedisManager;
import com.yimayhd.erpcenter.dal.sys.po.UserSession;
import com.yimayhd.erpcenter.dal.sys.service.PlatformSessionDal;

public class PlatformSessionDalImpl implements PlatformSessionDal {

	@Override
	public UserSession getUserSession(String sessionId) {
		return (UserSession)JedisManager.getObject(sessionId);
	}

	@Override
	public int setUserSession(String sessionId, int seconds,
			UserSession userSession) {
		JedisManager.setObject(sessionId, seconds, userSession);
		return 0;
	}

	@Override
	public int delUserSession(String sessionId) {
		JedisManager.delObject(sessionId);
		return 0;
	}

}
