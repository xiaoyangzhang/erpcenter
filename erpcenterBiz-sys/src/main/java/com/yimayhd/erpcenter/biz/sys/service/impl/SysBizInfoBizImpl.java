package com.yimayhd.erpcenter.biz.sys.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sys.service.SysBizInfoBiz;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.dal.sys.po.SettleApply;
import com.yimayhd.erpcenter.dal.sys.po.SysBizInfo;
import com.yimayhd.erpcenter.dal.sys.service.SysBizInfoDal;

public class SysBizInfoBizImpl implements SysBizInfoBiz {

	@Autowired
	private SysBizInfoDal sysBizInfoDal;
	
	@Override
	public SysBizInfo getBizInfoByCode(String code) {
		
		return sysBizInfoDal.getBizInfoByCode(code);
	}

	@Override
	public void addSysBizInfo(SysBizInfo sysBizInfo,SettleApply apply,PlatformEmployeePo empPo,String menuIds) {
		sysBizInfoDal.addSysBizInfo(sysBizInfo, apply, empPo, menuIds);
		
	}

	@Override
	public PageBean getSysBizInfoList(SysBizInfo biz,Integer page) {
		return sysBizInfoDal.getSysBizInfoList(biz, page);
	}

	@Override
	public SysBizInfo selectByPrimaryKey(Integer id) {
		return sysBizInfoDal.selectByPrimaryKey(id);
	}

	
	@Override
	public void configSysBizInfoById(SysBizInfo biz,String menuIds) {
		 sysBizInfoDal.configSysBizInfoById(biz, menuIds);
	}

	@Override
	public String updateStateById(SysBizInfo biz) {
		return sysBizInfoDal.updateStateById(biz);
	}

	@Override
	public String chkExistByCode(String code) {
		return sysBizInfoDal.chkExistByCode(code);
	}

	@Override
	public void updateSysBizInfo(SysBizInfo biz) {
		 sysBizInfoDal.updateSysBizInfo(biz);
	}

	@Override
	public List<SysBizInfo> getAllBizs() {
		return sysBizInfoDal.getAllBizs();
	}

}
