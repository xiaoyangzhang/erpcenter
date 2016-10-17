package com.yimayhd.erpcenter.biz.basic.basic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.basic.client.basic.api.MsgService;
import com.yimayhd.erpcenter.dal.basic.client.basic.dao.MsgPushAppListMapper;
import com.yimayhd.erpcenter.dal.basic.client.basic.dao.MsgPushAppMapper;
import com.yimayhd.erpcenter.dal.basic.client.basic.po.MsgPushApp;
import com.yimayhd.erpcenter.dal.basic.client.basic.po.MsgPushAppList;


public class MsgServiceImpl implements MsgService {

    @Autowired
    private MsgPushAppMapper msgPushAppMapper;

    @Autowired
    private MsgPushAppListMapper msgPushAppListMapper;

    @Override
    public int insertMessageContent(MsgPushApp msgPushApp) {
        msgPushAppMapper.insertSelective(msgPushApp);
        return msgPushApp.getId();
    }

    @Override
    public void insertMessageReceiverInfo(List<MsgPushAppList> msgPushAppLists) {
        msgPushAppListMapper.batchAddMsgPushAppList(msgPushAppLists);
    }


    @Override
    public Map<String, Object> selectContent(String receiverIdentity) {
        //creatorType == 1系统消息;creatorType ==2导游通知
        List<Map<String,Object>> systemMessageList = msgPushAppListMapper.selectMessageList(1, receiverIdentity,null);
        List<Map<String,Object>> guideMessageList = msgPushAppListMapper.selectMessageList(2, receiverIdentity,null);

        int systemMessageNotReadCount = msgPushAppListMapper.selectMessageNotReadCount(1, receiverIdentity);
        int guideMessageNotReadCount = msgPushAppListMapper.selectMessageNotReadCount(2, receiverIdentity);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("systemMessageNotReadCount",systemMessageNotReadCount);
        map.put("guideMessageNotReadCount",guideMessageNotReadCount);
        map.put("systemMessageList",systemMessageList);
        map.put("guideMessageList",guideMessageList);
        return map;
    }

    @Override
    public void updateState(MsgPushAppList msgPushAppList) {
        msgPushAppListMapper.updateByPrimaryKeySelective(msgPushAppList);
    }


    @Override
    public List<Map<String, Object>> selectMoreSystemMessage(String receiverIdentity,String createTime) {
        List<Map<String,Object>> moreSystemMessage = msgPushAppListMapper.selectMessageList(1, receiverIdentity, createTime);
        return moreSystemMessage;
    }

    @Override
    public List<Map<String, Object>> selectMoreGuideMessage(String receiverIdentity,String createTime) {
        List<Map<String,Object>> moreGuideMessage = msgPushAppListMapper.selectMessageList(2, receiverIdentity,createTime);
        return moreGuideMessage;
    }

    @Override
    public int selectMessageNotReadCount(Integer creatorType, String receiverIdentity) {
        return msgPushAppListMapper.selectMessageNotReadCount(creatorType,receiverIdentity);
    }

    @Override
    public List<MsgPushApp> selectMessageByUserId(Integer creatorId,String createTime) {
        return msgPushAppMapper.selectMessageByUserId(creatorId,createTime);
    }

    @Override
    public List<String> selectMsgPushAppListIdentityByPushId(Integer pushId) {
        return msgPushAppListMapper.selectMsgPushAppListIdentityByPushId(pushId);
    }

    @Override
    public List<String> selectNotReadMsgPushAppListIdentityByPushId(Integer pushId) {
        return msgPushAppListMapper.selectNotReadMsgPushAppListIdentityByPushId(pushId);
    }

}

