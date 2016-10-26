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

}
