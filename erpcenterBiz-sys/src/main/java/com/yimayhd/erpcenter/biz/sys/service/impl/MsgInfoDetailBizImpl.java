package com.yimayhd.erpcenter.biz.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sys.service.MsgInfoDetailBiz;
import com.yimayhd.erpcenter.dal.sys.po.MsgInfoDetail;
import com.yimayhd.erpcenter.dal.sys.service.MsgInfoDetailDal;


public class MsgInfoDetailBizImpl implements MsgInfoDetailBiz {

    @Autowired
    private MsgInfoDetailDal msgInfoDetailDal;
    
    @Override
    public MsgInfoDetail findMsgCountByUserId(Integer bizId, Integer userId) {
        return msgInfoDetailDal.findMsgCountByUserId(bizId, userId);
    }
    
    @Override
    public Integer modifyStatus(MsgInfoDetail mid) {
        return msgInfoDetailDal.modifyStatus(mid);
    }

}
