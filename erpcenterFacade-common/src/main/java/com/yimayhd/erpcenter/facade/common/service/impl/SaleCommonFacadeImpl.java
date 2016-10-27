
/**
 * 
 */
package com.yimayhd.erpcenter.facade.common.service.impl;

import java.util.List;

import org.erpcenterFacade.common.client.service.SaleCommonFacade;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.dal.basic.constant.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpresource.dal.constants.Constants;

/**
 * @ClassName: SaleCommonFacadeImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月25日
 */
public class SaleCommonFacadeImpl implements SaleCommonFacade {

	@Autowired
	private DicBiz dicBiz;
	@Override
	public List<DicInfo> getDeliveryItemList(Integer bizId) {
		List<DicInfo> typeList = dicBiz.getListByTypeCode(BasicConstants.XMFY_DJXM, bizId);
		return typeList;
	}
	
	@Override
	public List<DicInfo> getTransportListByTypeCode(Integer bizId) {
		return dicBiz.getListByTypeCode(BasicConstants.GYXX_JTFS, bizId);
	}

	
	@Override
	public List<DicInfo> getCarListByTypeCode() {
		return dicBiz.getListByTypeCode(Constants.FLEET_TYPE_CODE);
	}

	
	@Override
	public List<DicInfo> getShopListByTypeCode() {
		return dicBiz.getListByTypeCode(Constants.SHOPPING_TYPE_CODE);
	}

}
