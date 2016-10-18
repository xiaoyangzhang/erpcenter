package com.yimayhd.erpcenter.biz.basic.service.impl;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.basic.po.MsgPushApp;
import com.yimayhd.erpcenter.dal.basic.po.MsgPushAppList;
import com.yimayhd.erpcenter.dal.basic.service.MsgDal;

public class MsgServiceImpl implements MsgDal {

	@Override
	public int insertMessageContent(MsgPushApp msgPushApp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertMessageReceiverInfo(List<MsgPushAppList> msgPushAppLists) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> selectContent(String receiverIdentity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateState(MsgPushAppList msgPushAppList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> selectMoreSystemMessage(String receiverIdentity, String createTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectMoreGuideMessage(String receiverIdentity, String createTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectMessageNotReadCount(Integer creatorType, String receiverIdentity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MsgPushApp> selectMessageByUserId(Integer creatorId, String createTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> selectMsgPushAppListIdentityByPushId(Integer pushId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> selectNotReadMsgPushAppListIdentityByPushId(Integer pushId) {
		// TODO Auto-generated method stub
		return null;
	}}

