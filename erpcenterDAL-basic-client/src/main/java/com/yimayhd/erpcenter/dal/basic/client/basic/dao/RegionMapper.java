package com.yimayhd.erpcenter.dal.basic.client.basic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.basic.client.basic.po.RegionInfo;


public interface RegionMapper {
	List<RegionInfo> getRegionById(@Param("pid")Integer pid);
	List<RegionInfo> getAllProvince();
	RegionInfo getById(@Param("id")Integer id);
	int update(RegionInfo RegionInfo);
	int isNode(Integer id);
	int delNode(Integer id);
	int add(RegionInfo RegionInfo);
	int getMaxIdByLevel(Integer level);
}
