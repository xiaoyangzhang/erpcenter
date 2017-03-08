package com.yimayhd.erpcenter.dal.sys.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.PlatAuth;

public interface PlatAuthDal {

    public List<PlatAuth> findByBizIdAndOrgNotZero(Integer bizId);
	
    public List<PlatAuth> findByBizIdAndSupplierNotZero(Integer bizId);
    
	public PlatAuth findByBizIdAndOrgIdOrSupplierId(Integer bizId, Integer orgId, 
			Integer supplierId);

}
