
package com.yimayhd.erpcenter.facade.operation.service;

import java.util.Map;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.facade.operation.result.BookingDeliveryResult;
import com.yimayhd.erpcenter.facade.operation.result.ResultSupport;
import com.yimayhd.erpcenter.facade.operation.result.WebResult;

/**
 * @ClassName: BookingDeliveryFacade
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月25日
 */
public interface BookingDeliveryFacade {
	/**
	 * 
	* created by zhangxiaoyang
	* @date 2016年10月25日
	* @Description:地接社订单明细列表
	* @param 
	* @return List<BookingDelivery>
	* @throws
	 */
	BookingDeliveryResult getDeliveryBookingDetailList(Integer groupId);
	/**
	 * 
	* created by zhangxiaoyang
	* @date 2016年10月25日
	* @Description:预定安排地接社
	* @param 
	* @return List<BookingDelivery>
	* @throws
	 */
	BookingDeliveryResult getDeliveryBookingArrangeList(Integer groupId);
	/**
	 * 新增/编辑地接订单
	* created by zhangxiaoyang
	* @date 2016年10月25日
	* @Description:
	* @param 
	* @return BookingDeliveryResult
	* @throws
	 */
	BookingDeliveryResult getDeliveryEditInfo(Integer groupId,Integer bid);
	/**
	 * 保存地接计调订单
	* created by zhangxiaoyang
	* @date 2016年10月25日
	* @Description:
	* @param 
	* @return ResultSupport
	* @throws
	 */
	WebResult<Map<String, Object>> saveDeliveryBooking(String bookingStr,PlatformEmployeePo userInfo);
	
	ResultSupport angencyConfirm(Integer id);
	
	ResultSupport angencyDelete(Integer id);
	/**
	 * 
	* created by zhangxiaoyang
	* @date 2016年10月26日
	* @Description:查询地接计调订单的信息
	* @param 
	* @return BookingDelivery
	* @throws
	 */
	BookingDeliveryResult getBookingDeliveryInfo(Integer bookingId,Integer bizId);
	BookingDeliveryResult getDeliveryPriceInfo(Integer bizId,PageBean pageBean,Set<Integer> set,String carInfo);
	PageBean  getLocalTravelAngencyGroupList(PageBean pageBean,TourGroupVO tourGroup,Set<Integer> set);

	/**
	 * @author liyong
	 * @param orderId
	 * @param bizId
	 * @return
	 */
	Integer getOrgIdBy(Integer orderId ,Integer bizId);

	/**
	 * @author liyong
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	WebResult<PageBean> pushListTable(PageBean pageBean ,Integer bizId, Set<Integer> set);

	/**
	 * @author liyong
	 * @param groupId
	 * @param bookingId
	 * @param bizId
	 * @param fromAppKey
	 * @param fromSecretKey
	 * @param toAppKey
	 * @return
	 */
	WebResult<String> pushRemoteSave(Integer groupId, Integer bookingId,Integer bizId, String fromAppKey, String fromSecretKey, String toAppKey);

	/**
	 * @author liyong
	 * @param bookingId
	 * @return
	 */
	WebResult<Boolean> updatePushStatus(Integer bookingId);
	BookingDeliveryResult loadBookingDeliveryInfo(Integer gid, Integer bid, Integer bizId);


}
