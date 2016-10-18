package com.yimayhd.erpcenter.biz.basic.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.basic.po.MsgPushApp;
import com.yimayhd.erpcenter.dal.basic.po.MsgPushAppList;
import com.yimayhd.erpcenter.dal.basic.service.MsgDal;

public class MsgBizImpl implements MsgDal {
	@Autowired
	private MsgDal msgDal;

	@Override
	public int insertMessageContent(MsgPushApp msgPushApp) {
		return msgDal.insertMessageContent(msgPushApp);
	}

	@Override
	public void insertMessageReceiverInfo(List<MsgPushAppList> msgPushAppLists) {
		msgDal.insertMessageReceiverInfo(msgPushAppLists);
	}

	@Override
	public Map<String, Object> selectContent(String receiverIdentity) {
		return msgDal.selectContent(receiverIdentity);
	}

	@Override
	public void updateState(MsgPushAppList msgPushAppList) {
		msgDal.updateState(msgPushAppList);		
	}

	@Override
	public List<Map<String, Object>> selectMoreSystemMessage(String receiverIdentity, String createTime) {
		return msgDal.selectMoreGuideMessage(receiverIdentity, createTime);
	}

	@Override
	public List<Map<String, Object>> selectMoreGuideMessage(String receiverIdentity, String createTime) {
		return msgDal.selectMoreGuideMessage(receiverIdentity, createTime);
	}

	@Override
	public int selectMessageNotReadCount(Integer creatorType, String receiverIdentity) {
		return msgDal.selectMessageNotReadCount(creatorType, receiverIdentity);
	}

	@Override
	public List<MsgPushApp> selectMessageByUserId(Integer creatorId, String createTime) {
		return msgDal.selectMessageByUserId(creatorId, createTime);
	}

	@Override
	public List<String> selectMsgPushAppListIdentityByPushId(Integer pushId) {
		return msgDal.selectMsgPushAppListIdentityByPushId(pushId);
	}

	@Override
	public List<String> selectNotReadMsgPushAppListIdentityByPushId(Integer pushId) {
		return msgDal.selectNotReadMsgPushAppListIdentityByPushId(pushId);
	}
}

