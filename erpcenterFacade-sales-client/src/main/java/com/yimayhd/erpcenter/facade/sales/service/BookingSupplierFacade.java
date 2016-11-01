/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.FinanceBillDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.facade.sales.query.BookingDeliveryQueryDTO;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;
import com.yimayhd.erpcenter.facade.sales.result.WebResult;
import com.yimayhd.erpcenter.facade.sales.result.operation.BookingSupplierResult;
import com.yimayhd.erpresource.dal.po.SupplierContract;
import com.yimayhd.erpresource.dal.po.SupplierContractPrice;
import com.yimayhd.erpresource.dal.po.SupplierItem;

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
    PageBean selectreceiveOrderListSelectPage(PageBean page,Integer bizId,Set<Integer> set);
    List<FinanceBillDetail> getbillListByIdAndGuideId(Integer bizId,String groupId, String guideId);
    BookingSupplierResult applyPrint(Integer bizId,String groupId, String guideId);
    BookingSupplierResult groupHotelDetailPreview(Map<Object, Object> map);
    BookingSupplierResult getBookingInfo(Integer groupId,Integer supplierType);
    BookingSupplierResult getMoreBookingInfo(Integer groupId,Integer supplierType);
    BookingSupplierResult toCarPreview(Integer groupId,Integer supplierType);
    Map<String, Object> bookingInfoDetail(Integer groupId,Integer supplierType);
    Map<String, Object> moreBookingInfoDetail(Integer groupId,Integer supplierType);
    BookingSupplierResult toEditSupplier(Integer groupId,Integer bookingId);
    BookingSupplierResult viewSupplier(Integer groupId,Integer bookingId);
    PageBean getFleetGroupList(PageBean page,TourGroupVO vo,Set<Integer> set);
    PageBean querySupplierList(PageBean page,TourGroupVO vo,Set<Integer> set,Integer supplierType);
    WebResult<Map<String, Object>> saveBooking(BookingSupplier bookingSupplier,FinanceGuide financeGuide,String bizCode,PlatformEmployeePo curUser);
    ResultSupport delDetail(Integer bookingSupplierDetailId);
    ResultSupport delBookingSupplier(Integer bookingId,boolean flag);
    BookingSupplierResult loadInAndOutcomeData(Integer bookingId,Integer groupId);
    ResultSupport stateConfirm(Integer bookingId);
    List<SupplierContractPrice> findRoomTypeBySupplierId(Integer bizId,Integer supplierId);
    BookingSupplierResult exportBookingHotel(Map paramMap);
    List<SupplierContract> selectCashType(Integer bizId,Integer supplierId,Integer groupId);
    List<SupplierItem> selectItems(Integer supplierId);
    ResultSupport deleteBill(Integer bizId,Integer guideId,Integer groupId);
    ResultSupport batchSaveBillDetail(Integer bizId,String userName,String jsonStr);
    BookingSupplier savePrice(BookingSupplier bookingSupplier);
    BookingSupplierResult bookingSupplierExport(Integer bookingId,Integer bizId);
    PageBean getOutcomeGroupList(PageBean pageBean,TourGroupVO tourGroup, Set<Integer> set);
    PageBean getHotelGroupList(PageBean pageBean,TourGroupVO tourGroup, Set<Integer> set);
    PageBean getRestaurantGroupList(PageBean pageBean,TourGroupVO tourGroup, Set<Integer> set);
    PageBean getEntertaimentGroupList(PageBean pageBean,TourGroupVO tourGroup, Set<Integer> set);
    PageBean getGolfGroupList(PageBean pageBean,TourGroupVO tourGroup, Set<Integer> set);
    PageBean getSightGroupList(PageBean pageBean,TourGroupVO tourGroup, Set<Integer> set);
    PageBean getAirTicketGroupList(PageBean pageBean,TourGroupVO tourGroup, Set<Integer> set);
    PageBean getTrainTicketGroupList(PageBean pageBean,TourGroupVO tourGroup, Set<Integer> set);
    PageBean getInsuranceGroupList(PageBean pageBean,TourGroupVO tourGroup, Set<Integer> set);
    PageBean getIncomeGroupList(PageBean pageBean,TourGroupVO tourGroup, Set<Integer> set);
}
