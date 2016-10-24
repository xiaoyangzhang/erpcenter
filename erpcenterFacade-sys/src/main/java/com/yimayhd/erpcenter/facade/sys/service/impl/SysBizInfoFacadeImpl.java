package com.yimayhd.erpcenter.facade.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sys.service.SysBizInfoBiz;
import com.yimayhd.erpcenter.dal.sys.po.SysBizInfo;
import com.yimayhd.erpcenter.facade.sys.query.PlatformEmployeePoDTO;
import com.yimayhd.erpcenter.facade.sys.query.SettleApplyDTO;
import com.yimayhd.erpcenter.facade.sys.query.SysBizInfoDTO;
import com.yimayhd.erpcenter.facade.sys.result.SysBizInfoFacadeResult;
import com.yimayhd.erpcenter.facade.sys.result.SysBizInfoListResult;
import com.yimayhd.erpcenter.facade.sys.service.SysBizInfoFacade;

public class SysBizInfoFacadeImpl implements SysBizInfoFacade {
	@Autowired
	private SysBizInfoBiz sysBizInfoBiz;

	@Override
	public SysBizInfoFacadeResult getBizInfoByCode(String code) {
		
		SysBizInfo sysBizInfo =  sysBizInfoBiz.getBizInfoByCode(code);
		SysBizInfoFacadeResult result = new SysBizInfoFacadeResult();
		if(sysBizInfo!=null)
			result.setSysBizInfo(sysBizInfo);
		return result;
	}

	@Override
	public void addSysBizInfo(SysBizInfoDTO sysBizInfo,SettleApplyDTO apply,PlatformEmployeePoDTO empPo,String menuIds) {
		sysBizInfoBiz.addSysBizInfo(sysBizInfo.getSysBizInfo(), apply.getSettleApply(), empPo.getPlatformEmployeePo(), menuIds);
		
	}

	@Override
	public PageBean getSysBizInfoList(SysBizInfoDTO biz,Integer page) {
		return sysBizInfoBiz.getSysBizInfoList(biz.getSysBizInfo(), page);
	}

	@Override
	public SysBizInfoFacadeResult selectByPrimaryKey(Integer id) {
		SysBizInfo sysBizInfo =  sysBizInfoBiz.selectByPrimaryKey(id);
		SysBizInfoFacadeResult result = new SysBizInfoFacadeResult();
		if(sysBizInfo!=null)
			result.setSysBizInfo(sysBizInfo);
		return result;
	}

	
	@Override
	public void configSysBizInfoById(SysBizInfoDTO biz,String menuIds) {
		 sysBizInfoBiz.configSysBizInfoById(biz.getSysBizInfo(), menuIds);
	}

	@Override
	public String updateStateById(SysBizInfoDTO biz) {
		return sysBizInfoBiz.updateStateById(biz.getSysBizInfo());
	}

	@Override
	public String chkExistByCode(String code) {
		return sysBizInfoBiz.chkExistByCode(code);
	}

	@Override
	public void updateSysBizInfo(SysBizInfoDTO biz) {
		 sysBizInfoBiz.updateSysBizInfo(biz.getSysBizInfo());
	}

	@Override
	public SysBizInfoListResult getAllBizs() {
		List<SysBizInfo> sysBizInfos = sysBizInfoBiz.getAllBizs();
		SysBizInfoListResult result = new SysBizInfoListResult();
		if(sysBizInfos!=null)
			result.setSysBizInfos(sysBizInfos);
		
		return result;
	}

}
