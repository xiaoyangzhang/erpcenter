package com.yimayhd.erpcenter.facade.sys.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sys.service.MsgInfoBiz;
import com.yimayhd.erpcenter.biz.sys.service.MsgInfoDetailBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.dal.sys.po.MsgInfo;
import com.yimayhd.erpcenter.dal.sys.po.MsgInfoDetail;
import com.yimayhd.erpcenter.facade.sys.service.SysMsgInfoFacade;



public class SysMsgInfoFacadeImpl implements SysMsgInfoFacade{

	@Autowired
	private  PlatformOrgBiz orgBiz;
	@Autowired
    private  PlatformEmployeeBiz platformEmployeeBiz;
	@Autowired
    private  MsgInfoBiz msgInfoBiz;
	@Autowired
    private  MsgInfoDetailBiz msgInfoDetailBiz;



	@Override
	public String getComponentOrgTreeJsonStr(Integer bizId) {
		// TODO Auto-generated method stub
		return orgBiz.getComponentOrgTreeJsonStr(bizId);
	}

	@Override
	public String getComponentOrgUserTreeJsonStr(Integer bizId) {
		// TODO Auto-generated method stub
		return platformEmployeeBiz.getComponentOrgUserTreeJsonStr(bizId);
	}

	@Override
	public Integer saveMsg(MsgInfo mi, String ids, String names) {
		// TODO Auto-generated method stub
		return msgInfoBiz.saveMsg(mi, ids, names);
	}

	@Override
	public PageBean<MsgInfo> findMsgInfoListPage(PageBean<MsgInfo> pageBean,
			Map<String, String> param) {
		// TODO Auto-generated method stub
		return msgInfoBiz.findMsgInfoListPage(pageBean, param);
	}

	@Override
	public Integer modifyStatus(MsgInfoDetail mid) {
		// TODO Auto-generated method stub
		return msgInfoDetailBiz.modifyStatus(mid);
	}

	@Override
	public MsgInfo findMsgByMid(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return msgInfoBiz.findMsgByMid(param);
	}

	@Override
	public PageBean<MsgInfo> findNoticeListPage(PageBean<MsgInfo> pageBean,
			Map<String, String> param) {
		// TODO Auto-generated method stub
		return msgInfoBiz.findNoticeListPage(pageBean, param);
	}
}
