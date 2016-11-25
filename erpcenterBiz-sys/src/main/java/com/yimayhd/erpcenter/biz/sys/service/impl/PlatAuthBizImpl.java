package com.yimayhd.erpcenter.biz.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sys.service.PlatAuthBiz;
import com.yimayhd.erpcenter.dal.sys.po.PlatAuth;
import com.yimayhd.erpcenter.dal.sys.service.PlatAuthDal;

public class PlatAuthBizImpl implements PlatAuthBiz {

	@Autowired
	private PlatAuthDal platAuthDal;
	
	@Override
    public List<PlatAuth> findByBizIdAndOrgNotZero(Integer bizId) {
        return platAuthDal.findByBizIdAndOrgNotZero(bizId);
    }
	
    @Override
	public PlatAuth findByBizIdAndOrgIdOrSupplierId(Integer bizId, Integer orgId, 
			Integer supplierId) {
		return platAuthDal.findByBizIdAndOrgIdOrSupplierId(bizId, orgId, supplierId);
	}

}
