package com.yimayhd.erpcenter.facade.sys.service;

import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.po.MsgInfo;
import com.yimayhd.erpcenter.dal.sys.po.MsgInfoDetail;

public interface SysMsgInfoFacade {

	String getComponentOrgTreeJsonStr(Integer bizId);
	String getComponentOrgUserTreeJsonStr(Integer bizId);
	Integer saveMsg(MsgInfo mi, String ids, String names);
	PageBean<MsgInfo> findMsgInfoListPage(PageBean<MsgInfo> pageBean, Map<String, String> param);
	Integer modifyStatus(MsgInfoDetail mid);
	MsgInfo findMsgByMid(Map<String, Object> param);
	PageBean<MsgInfo> findNoticeListPage(PageBean<MsgInfo> pageBean, Map<String, String> param);
}
