package com.yimayhd.erpcenter.facade.sys.service;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.facade.sys.query.PlatformEmployeePoDTO;
import com.yimayhd.erpcenter.facade.sys.query.SettleApplyDTO;
import com.yimayhd.erpcenter.facade.sys.query.SysBizInfoDTO;
import com.yimayhd.erpcenter.facade.sys.result.SysBizInfoFacadeResult;
import com.yimayhd.erpcenter.facade.sys.result.SysBizInfoListResult;
import com.yimayhd.erpcenter.facade.sys.result.SysBizInfoResult;

public interface SysBizInfoFacade {
    /**
     * 根据企业编码获取企业信息
     * @param code
     * @return
     */
	SysBizInfoFacadeResult getBizInfoByCode(String code);
	/**
	 * 增添商家
	 * @param sysInfo
	 */
	void addSysBizInfo(SysBizInfoDTO sysInfo,SettleApplyDTO apply,PlatformEmployeePoDTO po,String menuIds);
	/**
	 * 条件查询商家
	 * @param biz
	 * @return
	 */
	PageBean getSysBizInfoList(SysBizInfoDTO biz,Integer page);
	/**
	 * 根据商家id查询商家信息
	 * @param id
	 * @return
	 */
	SysBizInfoFacadeResult selectByPrimaryKey(Integer id);
	/**
	 * 根据主键更新商家信息
	 * @param biz
	 */
	void configSysBizInfoById(SysBizInfoDTO biz,String menuIds);
	String updateStateById(SysBizInfoDTO biz);
	String chkExistByCode(String code);
	void updateSysBizInfo(SysBizInfoDTO biz);
	SysBizInfoListResult getAllBizs();
}
