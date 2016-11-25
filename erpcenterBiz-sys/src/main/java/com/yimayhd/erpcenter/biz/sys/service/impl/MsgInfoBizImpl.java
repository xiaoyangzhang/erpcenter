package com.yimayhd.erpcenter.biz.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sys.service.MsgInfoBiz;
import com.yimayhd.erpcenter.dal.sys.po.MsgInfo;
import com.yimayhd.erpcenter.dal.sys.service.MsgInfoDal;

public class MsgInfoBizImpl implements MsgInfoBiz {

    @Autowired
    private MsgInfoDal msgInfoDal;

    @Override
    public Integer saveMsg(MsgInfo mi, String ids, String names) {
    	return msgInfoDal.saveMsg(mi, ids, names);
    }

    @Override
    public PageBean<MsgInfo> findMsgInfoListPage(PageBean<MsgInfo> pageBean,
            Map<String, String> param) {
    	return msgInfoDal.findMsgInfoListPage(pageBean, param);
    }

    @Override
    public List<MsgInfo> findMsgInfo(Map<String, Object> param) {
        return msgInfoDal.findMsgInfo(param);
    }
    
    @Override
    public MsgInfo findMsgByMid(Map<String, Object> param) {
        return msgInfoDal.findMsgByMid(param);
    }
    
    @Override
    public PageBean<MsgInfo> findNoticeListPage(PageBean<MsgInfo> pageBean,
            Map<String, String> param) {
        return msgInfoDal.findNoticeListPage(pageBean, param);
    }
}
