package com.yimayhd.erpcenter.dal.sys.service;

import com.yimayhd.erpcenter.dal.sys.po.MsgInfoDetail;

public interface MsgInfoDetailDal {

    public MsgInfoDetail findMsgCountByUserId(Integer bizId, Integer userId);
    
    public Integer modifyStatus(MsgInfoDetail mid) ;

}
