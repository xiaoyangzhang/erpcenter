/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.service;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;
import com.yimayhd.erpcenter.facade.sales.result.BookingShopResult;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;

/**
 * @ClassName: BookingGuestShopFacade
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月27日
 */
public interface BookingGuestShopFacade {

	ResultSupport delShopDetail(Integer id);
	
	ResultSupport saveShopDetail(BookingShopDetail shopDetail);
	
	BookingShopResult factShop(Integer id,Integer groupId);
	
	ResultSupport deldetailGuide(Integer id);
}
