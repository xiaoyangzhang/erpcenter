package com.yimayhd.erpcenter.dal.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.base.cache.core.factory.ManagerFactory;
import com.yimayhd.erpcenter.dal.sys.po.UserSession;
import com.yimayhd.erpcenter.dal.sys.service.PlatformSessionDal;

public class PlatformSessionDalImpl implements PlatformSessionDal {
	
	@Autowired
	private ManagerFactory managerFactory;

	@Override
	public UserSession getUserSession(String sessionId) {
		
		String key = sessionId;
		String valJson = managerFactory.getStringCommands().get(key.hashCode(), key);
		
		return JSONObject.parseObject(valJson, UserSession.class);
	}

	@Override
	public int setUserSession(String sessionId, int seconds,
			UserSession userSession) {
		try{
			String key = sessionId;
			String value = JSONObject.toJSONString(userSession);
			
			managerFactory.getStringCommands().set(key.hashCode(), key, value);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int delUserSession(String sessionId) {
		managerFactory.getBasicCommands().delete(sessionId.hashCode(), sessionId);
		
		return 0;
	}

}
