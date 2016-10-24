package com.yihg.sales.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihg.sales.po.GroupRouteAttachment;

public interface GroupRouteAttachmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupRouteAttachment record);

    int insertSelective(GroupRouteAttachment record);

    GroupRouteAttachment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupRouteAttachment record);

    int updateByPrimaryKey(GroupRouteAttachment record);
    
    List<GroupRouteAttachment> selectByRouteIdAndType(@Param("objId")Integer objId,@Param("objType")Integer objType,@Param("type")Integer type);
    
    List<GroupRouteAttachment>  selectByRouteId(@Param("objId")Integer objId);
    
    void deleteByObjId(@Param("objId")Integer objId,@Param("objType")Integer objType);
    
    List<GroupRouteAttachment>  selectAttachmentByGroupId(@Param("groupId")Integer groupId);
    List<GroupRouteAttachment>  selectAttachmentByOrderId(@Param("orderId")Integer orderId);
}