package com.yimayhd.erpcenter.facade.sales.service;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.facade.sales.query.BookingShopListDTO;

/**
 * 
* @ClassName: BookingShopFacade 
* @Description: 
* @author wangjun
* @date 2016年10月25日 下午4:32:17 
*
 */
public interface BookingShopFacade {
	PageBean bookingShopList(BookingShopListDTO bookingShopListDTO);
}
