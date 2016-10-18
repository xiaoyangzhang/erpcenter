package com.yimayhd.erpcenter.dal.sys.dao;

import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.PlatformSysPo;

public interface PlatformSysMapper{
	
	/**
	 * 获取各子系统配置信息
	 * @return
	 */
	public List<PlatformSysPo> getPlatformSysList();
	/**
	 * 根据系统编码获取系统配置信息
	 * @param code
	 * @return
	 */
	public PlatformSysPo findByCode(String code);

}
