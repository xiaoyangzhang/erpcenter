package com.yimayhd.erpcenter.facade.basic.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.facade.basic.result.ResultSupport;

/**
 * @author hongfei.guo
 * @create 2016年10月25日 上午10:55:42
 */
public interface RegionFacade {
	
	List<RegionInfo> getRegionById(String pid);
	
	RegionInfo getById(String id);
	
	int update(RegionInfo dicRegion);
	
	int isNode(String id);
	
	int add(RegionInfo dicRegion);
}
