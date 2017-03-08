package com.yimayhd.erpcenter.biz.sys.service;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.sys.po.SysBizConfig;

public interface SysBizConfigBiz {
	List<SysBizConfig> getListByBizId(Integer bizId);

	Map<String, String> getConfigMapByBizId(int bizId);
	
	SysBizConfig findByItemValue(int bizId,String itemCode);
}
