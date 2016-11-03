package com.yimayhd.erpcenter.facade.sales.service;

import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;

public interface AgencyOrderDealFacade {

	ResultSupport execute();
	ResultSupport executeUpdateResOrderState(Integer orderState);
}
