package com.yimayhd.erpcenter.facade.sys.service;

import com.yimayhd.erpcenter.facade.sys.result.PlatAuthListResult;


public interface SysPlatAuthFacade {

    public PlatAuthListResult findByBizIdAndOrgNotZero(Integer bizId);
	
//	public PlatAuth findByBizIdAndOrgIdOrSupplierId(Integer bizId, Integer orgId,
//													Integer supplierId);

}
