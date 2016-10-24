package com.yihg.sales.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihg.sales.po.GroupRouteSupplier;

public interface GroupRouteSupplierMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupRouteSupplier record);

    int insertSelective(GroupRouteSupplier record);

    GroupRouteSupplier selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupRouteSupplier record);

    int updateByPrimaryKey(GroupRouteSupplier record);
    
    List<GroupRouteSupplier> selectGroupRouteSupplierByRouteId(Integer routeId);

    List<GroupRouteSupplier> selectGroupRouteSupplierByRouteIdAndType(@Param("routeId") Integer routeId,@Param("supplierType")Integer type);
    
    void deleteByRouteId(Integer routeId);
    List<GroupRouteSupplier> selectSupplierByGroupIdAndType(@Param("groupId")Integer groupId, @Param("type")Integer type);
    
    List<GroupRouteSupplier> selectGroupRouteSupplierByGroupId(@Param("groupId")Integer groupId);
    List<GroupRouteSupplier> selectGroupRouteSupplierByOrderId(@Param("orderId")Integer orderId);
    
}