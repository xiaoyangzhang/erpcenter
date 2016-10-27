package com.yimayhd.erpcenter.dal.sales.sales.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteTraffic;


public interface GroupRouteTrafficMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(GroupRouteTraffic record);

	int insertSelective(GroupRouteTraffic record);

	GroupRouteTraffic selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(GroupRouteTraffic record);

	int updateByPrimaryKey(GroupRouteTraffic record);

	List<GroupRouteTraffic> selectGroupRouteTrafficByRouteId(@Param("routeId")Integer routeId);
	
	void deleteByRouteId(Integer routeId);
}