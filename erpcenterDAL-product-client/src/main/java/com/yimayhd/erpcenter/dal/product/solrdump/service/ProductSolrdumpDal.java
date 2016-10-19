package com.yimayhd.erpcenter.dal.product.solrdump.service;

import com.yimayhd.erpcenter.dal.product.solrdump.domin.ProductStateDO;

public interface ProductSolrdumpDal {

	public boolean productInfoByIdIntoSolr(ProductStateDO productStateDO);
}
