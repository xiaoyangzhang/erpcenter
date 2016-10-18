package com.yihg.sys.impl;

import com.yihg.architect.redis.JedisManager;
import com.yihg.sys.api.PlatformSessionService;
import com.yihg.sys.po.UserSession;

public class PlatformSessionServiceImpl implements PlatformSessionService {

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
