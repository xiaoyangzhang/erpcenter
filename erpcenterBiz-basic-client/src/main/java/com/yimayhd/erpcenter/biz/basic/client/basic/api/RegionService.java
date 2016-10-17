package com.yimayhd.erpcenter.biz.basic.client.basic.api;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.client.basic.po.RegionInfo;



public interface RegionService {
	List<RegionInfo> getRegionById(String pid);
	List<RegionInfo> getAllProvince();
	RegionInfo getById(String id);
	int update(RegionInfo dicRegion);
	int isNode(String id);
	int delNode(String id);
	int add(RegionInfo dicRegion);
	/**
	 * 将数据库中的地区信息存入redis
	 */
	void uploadRegion();
}
