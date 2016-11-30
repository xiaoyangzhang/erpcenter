package com.yimayhd.erpcenter.biz.sys.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.PlatAuth;

public interface PlatAuthBiz{

    public List<PlatAuth> findByBizIdAndOrgNotZero(Integer bizId);
	
	public PlatAuth findByBizIdAndOrgIdOrSupplierId(Integer bizId, Integer orgId, 
			Integer supplierId);

}
