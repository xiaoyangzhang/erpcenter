package com.yimayhd.erpcenter.biz.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yimayhd.erpcenter.biz.sys.service.SysBizConfigBiz;
import com.yimayhd.erpcenter.dal.sys.po.SysBizConfig;
import com.yimayhd.erpcenter.dal.sys.service.SysBizConfigDal;

public class SysBizConfigBizImpl implements SysBizConfigBiz {

	@Resource
	private SysBizConfigDal sysBizConfigDal;
	
	@Override
	public List<SysBizConfig> getListByBizId(Integer bizId) {
		return sysBizConfigDal.getListByBizId(bizId);
	}

	@Override
	public Map<String, String> getConfigMapByBizId(int bizId) {
		return sysBizConfigDal.getConfigMapByBizId(bizId);
	}

//	@Override
//	public boolean cnm(Integer bizId, String config) {
//		if(configMapper.cnm(bizId,config).equals("1")){return true;}
//		return false;
//	}

}
