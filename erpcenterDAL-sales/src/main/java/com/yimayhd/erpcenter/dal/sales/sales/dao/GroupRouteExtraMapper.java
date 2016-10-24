package com.yihg.sales.dao;

import com.yihg.sales.po.GroupRouteExtra;

public interface GroupRouteExtraMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupRouteExtra record);

    int insertSelective(GroupRouteExtra record);

    GroupRouteExtra selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupRouteExtra record);

    int updateByPrimaryKey(GroupRouteExtra record);
}