package com.yimayhd.erpcenter.biz.sys.service;

import com.yimayhd.erpcenter.dal.sys.po.MsgInfoDetail;


public interface MsgInfoDetailBiz{

    public MsgInfoDetail findMsgCountByUserId(Integer bizId, Integer userId) ;
    
    public Integer modifyStatus(MsgInfoDetail mid) ;

}
