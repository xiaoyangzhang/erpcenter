package com.yimayhd.erpcenter.dal.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.sys.dao.MsgInfoDetailMapper;
import com.yimayhd.erpcenter.dal.sys.po.MsgInfoDetail;
import com.yimayhd.erpcenter.dal.sys.service.MsgInfoDetailDal;


public class MsgInfoDetailDalImpl implements MsgInfoDetailDal {

    @Autowired
    private MsgInfoDetailMapper msgInfoDetailMapper;
    
    @Override
    public MsgInfoDetail findMsgCountByUserId(Integer bizId, Integer userId) {
        return msgInfoDetailMapper.selectMsgCountByUserId(bizId, userId);
    }
    
    @Override
    public Integer modifyStatus(MsgInfoDetail mid) {
        return msgInfoDetailMapper.updateByPrimaryKeySelective(mid);
    }

}
