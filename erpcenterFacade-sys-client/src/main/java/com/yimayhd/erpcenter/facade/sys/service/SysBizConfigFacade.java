package com.yimayhd.erpcenter.facade.sys.service;

import java.util.Map;

import com.yimayhd.erpcenter.dal.sys.po.SysBizConfig;
import com.yimayhd.erpcenter.facade.sys.result.SysBizConfigListResult;

public interface SysBizConfigFacade {
	SysBizConfigListResult getListByBizId(Integer bizId);

	Map<String, String> getConfigMapByBizId(int bizId);
	SysBizConfig findByItemValue(int bizId, String itemCode);
}
