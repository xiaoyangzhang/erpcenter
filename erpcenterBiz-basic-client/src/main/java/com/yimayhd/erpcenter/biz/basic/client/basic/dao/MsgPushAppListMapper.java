package com.yimayhd.erpcenter.biz.basic.client.basic.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.biz.basic.basic.po.MsgPushAppList;


public interface MsgPushAppListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsgPushAppList record);

    int insertSelective(MsgPushAppList record);

    MsgPushAppList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsgPushAppList record);

    int updateByPrimaryKey(MsgPushAppList record);

    void batchAddMsgPushAppList(List<MsgPushAppList> msgPushAppLists);

    List<Map<String,Object>> selectMessageList(@Param("creatorType") Integer creatorType,
                                               @Param("receiverIdentity") String receiverIdentity,
                                               @Param("createTime")String createTime);

    int selectMessageNotReadCount(@Param("creatorType") Integer creatorType,
                                  @Param("receiverIdentity") String receiverIdentity);


    List<String> selectMsgPushAppListIdentityByPushId(Integer pushId);

    List<String> selectNotReadMsgPushAppListIdentityByPushId(Integer pushId);

}