package com.yimayhd.erpcenter.biz.basic.client.basic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.biz.basic.basic.po.MsgPushApp;

public interface MsgPushAppMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsgPushApp record);

    int insertSelective(MsgPushApp record);

    MsgPushApp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsgPushApp record);

    int updateByPrimaryKey(MsgPushApp record);

    List<MsgPushApp> selectMessageByUserId(@Param("creatorId") Integer creatorId,@Param("createTime") String createTime);
}