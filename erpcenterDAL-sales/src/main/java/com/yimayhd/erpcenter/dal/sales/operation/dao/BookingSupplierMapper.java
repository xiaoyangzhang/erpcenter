package com.yimayhd.erpcenter.dal.sales.operation.dao;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingAirTicket;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierPO;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingSupplierAndDetailVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BookingSupplierMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookingSupplier record);

    int insertSelective(BookingSupplier record);

    BookingSupplier selectByPrimaryKey(Integer id);
    
    List<BookingSupplier> selectByPrimaryGroupId(@Param("groupId") Integer groupId);
    List<BookingSupplier> selectByGroupId(@Param("groupId") Integer groupId);
	
	BookingSupplier selectByPrimaryGroupIdAndType(@Param("groupId") Integer groupId);

    int updateByPrimaryKeySelective(BookingSupplier record);

    int updateByPrimaryKey(BookingSupplier record);
    List<BookingSupplier> getBookingSupplierByGroupIdAndType(@Param("groupId") Integer groupId, @Param("supplierType") Integer supplierType);
    List<BookingSupplier> getBookingSupplierByGroupId(@Param("groupId") Integer groupId);
    List<BookingSupplierPO> getBookingSupplierPOByGroupIdAndType(@Param("groupId") Integer groupId, @Param("supplierType") Integer supplierType);
    List<BookingSupplierPO> getBookingSupplierPOByGroupIdAndTypeAndName(@Param("groupId") Integer groupId, @Param("supplierType") Integer supplierType, @Param("supplierName") String supplierName, @Param("driverName") String driverName, @Param("carLisence") String carLisence);
    
    List<BookingSupplier> getHotelAndFleetListByGroupId(Integer groupId);
    List<BookingSupplier> getSupplierListByGroupId(Integer groupId);
    //查询某个团在某个供应商处的订单
   BigDecimal getBookingCountByGroupIdAndSupplierType(@Param("groupId") Integer groupId, @Param("supplierType") Integer supplierType);
   //查询某个团订单总金额
    BigDecimal getBookingPriceSumByGroupIdAndSupplierType(@Param("groupId") Integer groupId, @Param("supplierType") Integer supplierType);
    //生成订单号
    Integer getBookingCountByTypeAndTime(@Param("supplierType") Integer supplierType, @Param("curDay") Long curDay, @Param("nextDay") Long nextDay);
	
    List<BookingSupplier> getBookingSupplierBySupplierType(@Param("supplierType") Integer supplierType);
    //根据导游报账单查询
	List<BookingSupplier> getFinanceSupplierByFinanceGuide(
            FinanceGuide financeGuide);
	
	List<BookingSupplier> getFinanceSupplier(
            FinanceGuide financeGuide);
	//根据supplierId查询某个供应商的订单中的车票的总数
	Integer getCountBySupplierId(@Param("parameter") Map parameters);
	Integer getCountBytypeId(@Param("type1Id") Integer type1Id, @Param("supplierType") Integer supplierType);
	Integer getOrderCountBySupplierId(@Param("supplierId") Integer supplierId);
	void updateGroupIdByOrderIds(@Param("groupId") Integer groupId, @Param("orderIds") List<Integer> orderIds);

    List<BookingSupplierAndDetailVO> getBookingSupplierByGroupIdAndSupplierTypeAndGroupDate(@Param("groupId") Integer groupId, @Param("groupDate") String groupDate);
    
    List<BookingAirTicket> selectAirTicketBookingListPage(PageBean<BookingAirTicket> pageBean);
    HashMap<String, BigDecimal> sumAirTicketBooking(PageBean<BookingAirTicket> pageBean);

	List<Map<String, Object>> getsubjectSummaryListPage(PageBean pageBean);
	
	List<Map<String, Object>> getsubjectSummary2ListPage(@Param("page") PageBean pageBean, @Param("sum") String sum);

	Map<String, Object> getsubjectSummaryQDYJ(@Param("page") PageBean pageBean, @Param("supplierId") Integer supplierId, @Param("sType") String sType);

	Map<String, Object> getsubjectSummaryQT(@Param("page") PageBean pageBean, @Param("supplierId") Integer supplierId, @Param("sType") String sType, @Param("sType2") String sType2, @Param("sType3") String sType3);

	Map<String, Object> getsubjectSummaryQDYJTotal(@Param("page") PageBean pageBean, @Param("sType") String sType);
	
	Map<String, Object> getsubjectSummaryQTTotal(@Param("page") PageBean pageBean, @Param("sType") String sType, @Param("sType2") String sType2, @Param("sType3") String sType3);

	//根据车队或车牌号查询groupId
	List<Integer> getGroupIdByCarInfo(@Param("carInfo") String carInfo);

	List<BookingSupplier> selectByGroupIdAndSupplierType(@Param("groupId") Integer groupId,
                                                         @Param("supplierType") Integer supplierType);
	//散客导游单中的酒店预定信息
	List<BookingSupplier> getHotelInfoByGroupId(@Param("groupId") Integer groupId);

	void updateAirGroupIdNullByOrderId(@Param("orderId") Integer orderId);
	
	List<BookingSupplier> selectIdList();
	
	BookingSupplier selectPayOrIncomeOrderTotalCash(@Param("bookingSupplierId") Integer bookingSupplierId);
	
	BookingSupplier selectPayOrIncomeGuideTotalCash(@Param("bookingSupplierId") Integer bookingSupplierId);
	
	int updateGroupIdByOrderId(@Param("groupId") Integer groupId, @Param("orderId") Integer orderId);

	
	int fix_SupplierName_bookingSupplier(@Param("supplierId") Integer supplierId, @Param("supplierName") String supplierName);
	int fix_SupplierName_financePay(@Param("supplierId") Integer supplierId, @Param("supplierName") String supplierName);
	int fix_SupplierName_financeVerify(@Param("supplierId") Integer supplierId, @Param("supplierName") String supplierName);
	int fix_SupplierName_groupOrder(@Param("supplierId") Integer supplierId, @Param("supplierName") String supplierName);
	int fix_SupplierName_bookingShop(@Param("supplierId") Integer supplierId, @Param("supplierName") String supplierName);
	int fix_SupplierName_bookingDelivery(@Param("supplierId") Integer supplierId, @Param("supplierName") String supplierName);
	
	
	List<BookingSupplier> selectTicketInfo(@Param("resId") Integer resId);
}