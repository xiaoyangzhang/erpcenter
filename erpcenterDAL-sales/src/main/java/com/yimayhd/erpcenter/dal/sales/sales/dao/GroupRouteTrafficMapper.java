package com.yihg.sales.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihg.sales.po.GroupRouteTraffic;

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