package com.yimayhd.erpcenter.biz.sys.service;

import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.dal.sys.po.SettleApply;
import com.yimayhd.erpcenter.dal.sys.po.SysBizInfo;

public interface SysBizInfoBiz {
    /**
     * 根据企业编码获取企业信息
     * @param code
     * @return
     */
	SysBizInfo getBizInfoByCode(String code);
	/**
	 * 增添商家
	 * @param sysInfo
	 */
	void addSysBizInfo(SysBizInfo sysInfo,SettleApply apply,PlatformEmployeePo po,String menuIds);
	/**
	 * 条件查询商家
	 * @param biz
	 * @return
	 */
	PageBean getSysBizInfoList(SysBizInfo biz,Integer page);
	/**
	 * 根据商家id查询商家信息
	 * @param id
	 * @return
	 */
	SysBizInfo selectByPrimaryKey(Integer id);
	/**
	 * 根据主键更新商家信息
	 * @param biz
	 */
	void configSysBizInfoById(SysBizInfo biz,String menuIds);
	String updateStateById(SysBizInfo biz);
	String chkExistByCode(String code);
	void updateSysBizInfo(SysBizInfo biz);
	List<SysBizInfo> getAllBizs();
}
