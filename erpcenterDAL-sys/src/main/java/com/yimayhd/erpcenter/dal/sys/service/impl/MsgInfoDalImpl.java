package com.yimayhd.erpcenter.dal.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.dao.MsgInfoDetailMapper;
import com.yimayhd.erpcenter.dal.sys.dao.MsgInfoMapper;
import com.yimayhd.erpcenter.dal.sys.po.MsgInfo;
import com.yimayhd.erpcenter.dal.sys.po.MsgInfoDetail;
import com.yimayhd.erpcenter.dal.sys.service.MsgInfoDal;

public class MsgInfoDalImpl implements MsgInfoDal {

    @Autowired
    private MsgInfoMapper msgInfoMapper;
    @Autowired
    private MsgInfoDetailMapper msgInfoDetailMapper;

    @Override
    public Integer saveMsg(MsgInfo mi, String ids, String names) {
        Integer miid = msgInfoMapper.insertSelective(mi);
        String[] idss = null;
        String[] namess = null;
        if (miid > 0) {
            if (mi.getId() > 0) {
                idss = ids.split(",");
                if(!StringUtils.isBlank(names)){
                	namess = names.split(",");
                }
                MsgInfoDetail mid;
                for (int i = 0; i < idss.length; i++) {
                    mid = new MsgInfoDetail();
                    mid.setMsgInfoId(mi.getId());
                    mid.setUserId(Integer.parseInt(idss[i]));
                    if(null!=namess && namess.length>0){
                    	mid.setUserName(namess[i]);
                    }
                    mid.setStatus(0);
                    try {
                        msgInfoDetailMapper.insertSelective(mid);
                    } catch (Exception e) {
                        return null;
                    }
                }
            }
        }
        return mi.getId();
    }

    @Override
    public PageBean<MsgInfo> findMsgInfoListPage(PageBean<MsgInfo> pageBean,
            Map<String, String> param) {
        pageBean.setResult(msgInfoMapper.selectMsgInfoListPage(pageBean, param));
        return pageBean;
    }

    @Override
    public List<MsgInfo> findMsgInfo(Map<String, Object> param) {
        return msgInfoMapper.selectMsgInfo(param);
    }
    
    @Override
    public MsgInfo findMsgByMid(Map<String, Object> param) {
        return msgInfoMapper.selectMsgByMid(param);
    }
    
    @Override
    public PageBean<MsgInfo> findNoticeListPage(PageBean<MsgInfo> pageBean,
            Map<String, String> param) {
        pageBean.setResult(msgInfoMapper.selectNoticeListPage(pageBean, param));
        return pageBean;
    }
}
