package com.yimayhd.erpcenter.biz.sys.service;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.po.MsgInfo;

public interface MsgInfoBiz {

    public Integer saveMsg(MsgInfo mi, String ids, String names);

    public PageBean<MsgInfo> findMsgInfoListPage(PageBean<MsgInfo> pageBean,
            Map<String, String> param) ;

    public List<MsgInfo> findMsgInfo(Map<String, Object> param) ;
    
    public MsgInfo findMsgByMid(Map<String, Object> param);
    
    public PageBean<MsgInfo> findNoticeListPage(PageBean<MsgInfo> pageBean,
            Map<String, String> param);
}
