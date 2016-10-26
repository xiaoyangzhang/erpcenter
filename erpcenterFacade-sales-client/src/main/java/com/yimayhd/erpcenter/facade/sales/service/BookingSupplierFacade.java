/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.service;

import com.yimayhd.erpcenter.facade.sales.query.BookingDeliveryQueryDTO;
import com.yimayhd.erpcenter.facade.sales.result.operation.BookingSupplierResult;

/**
 * @ClassName: BookingSupplierFacade
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月26日
 */
public interface BookingSupplierFacade {

	/**
	 * 
	* created by zhangxiaoyang
	* @date 2016年10月26日
	* @Description:获取团车辆信息
	* @param 
	* @return BookingSupplierResult
	* @throws
	 */
    BookingSupplierResult getDeliveryExportInfo(BookingDeliveryQueryDTO dto);
    

}
