package com.yimayhd.erpcenter.dal.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.sys.dao.PlatAuthMapper;
import com.yimayhd.erpcenter.dal.sys.po.PlatAuth;
import com.yimayhd.erpcenter.dal.sys.service.PlatAuthDal;

public class PlatAuthDalImpl implements PlatAuthDal {

	@Autowired
	private PlatAuthMapper pm;
	
	@Override
    public List<PlatAuth> findByBizIdAndOrgNotZero(Integer bizId) {
        return pm.selectByBizIdAndOrgNotZero(bizId);
    }
	
    @Override
	public PlatAuth findByBizIdAndOrgIdOrSupplierId(Integer bizId, Integer orgId, 
			Integer supplierId) {
		return pm.selectByBizIdAndOrgIdOrSupplierId(bizId, orgId, supplierId);
	}

	@Override
	public List<PlatAuth> findByBizIdAndSupplierNotZero(Integer bizId) {
		return pm.selectByBizIdAndSupplierNotZero(bizId);
	}

}
