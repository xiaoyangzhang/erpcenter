package com.yimayhd.erpcenter.dal.basic.service;


import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.basic.po.MsgPushApp;
import com.yimayhd.erpcenter.dal.basic.po.MsgPushAppList;

public interface MsgDal {

    /**
     *  插入信息
     */
    int insertMessageContent(MsgPushApp msgPushApp);

    /**
     * 批量插入接受人员信息
     */
    void insertMessageReceiverInfo(List<MsgPushAppList> msgPushAppLists);

    /**
     * 根据人员信息查询消息
     * @param groupId 团主键
     * @param receiverIdentity 用户证件号
     */
    Map<String, Object> selectContent(String receiverIdentity);

    /**
     * 更改阅读状态
     */
    void updateState(MsgPushAppList msgPushAppList);

    /**
     * 查询更多系统消息
     */
    List<Map<String,Object>> selectMoreSystemMessage(String receiverIdentity,String createTime);

    /**
     * 查询更多导游通知
     */
    List<Map<String,Object>> selectMoreGuideMessage(String receiverIdentity,String createTime);

    /**
     * 查询系统消息+导游通知未读信息数量
     */
    int selectMessageNotReadCount(Integer creatorType, String receiverIdentity);

    /**
     * 根据创建人UserId查询此人多有发送的消息
     */
    List<MsgPushApp> selectMessageByUserId(Integer creatorId,String createTime);

    /**
     * 根据消息Id查询所有接收消息的人证件号
     */
    List<String> selectMsgPushAppListIdentityByPushId(Integer pushId);

    /**
     * 根据消息Id查询未读消息的所有接收消息的人证件号
     */
    List<String> selectNotReadMsgPushAppListIdentityByPushId(Integer pushId);
}
