package com.yimayhd.erpcenter.facade.sys.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yimayhd.erpcenter.biz.sys.service.SysBizConfigBiz;
import com.yimayhd.erpcenter.dal.sys.po.SysBizConfig;
import com.yimayhd.erpcenter.dal.sys.service.SysBizConfigDal;
import com.yimayhd.erpcenter.facade.sys.result.SysBizConfigListResult;
import com.yimayhd.erpcenter.facade.sys.service.SysBizConfigFacade;

public class SysBizConfigFacadeImpl implements SysBizConfigFacade {

	@Resource
	private SysBizConfigBiz sysBizConfigBiz;
	
	@Override
	public SysBizConfigListResult getListByBizId(Integer bizId) {
		List<SysBizConfig> sysBizConfigs =  sysBizConfigBiz.getListByBizId(bizId);
		SysBizConfigListResult result = new SysBizConfigListResult();
		if(sysBizConfigs!=null){
			result.setSysBizConfigs(sysBizConfigs);
		}
		return result;
	}

	@Override
	public Map<String, String> getConfigMapByBizId(int bizId) {
		return sysBizConfigBiz.getConfigMapByBizId(bizId);
	}


}
