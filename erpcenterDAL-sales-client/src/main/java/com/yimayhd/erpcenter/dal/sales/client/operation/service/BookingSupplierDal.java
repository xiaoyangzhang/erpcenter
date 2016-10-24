package com.yimayhd.erpcenter.dal.sales.client.operation.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.math.BigDecimal;
import java.util.Map;


import com.yihg.mybatis.utility.PageBean;


import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingAirTicket;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierPO;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingSupplierAndDetailVO;
import org.apache.ibatis.annotations.Param;


public interface BookingSupplierDal {
	void deleteByPrimaryKey(Integer id) throws ClientException;
	
	/**
	 * 删除订单的时候，同时删除导游报账单
	 * @param id
	 */
	void deleteSupplierWithFinanceByPrimaryKey(Integer id, boolean isOperator) throws ClientException;

	int insert(BookingSupplier record);

	int insertSelective(BookingSupplier record);

	BookingSupplier selectByPrimaryKey(Integer id);
	
	List<BookingSupplier> selectByPrimaryGroupId(Integer groupId);
	List<BookingSupplier> selectByGroupId(Integer groupId);
	
	BookingSupplier selectByPrimaryGroupIdAndType(Integer groupId);
	//BookingSupplier selectByPrimaryGroupId(Integer groupId);

	int updateByPrimaryKeySelective(BookingSupplier record);

	int updateByPrimaryKey(BookingSupplier record);

	/**
	 * 查询出需求订单和实际订单
	 * @param groupId 团id
	 * @param supplierType 业务类型
	 * @param bookingId 预定id 
	 * @return
	 */
	Map<String, Object> selectBookingInfo(Integer groupId, Integer supplierType);
	Map<String, Object> selectBookingInfoPO(Integer groupId, Integer supplierType);
	 int getBookingCountByGroupIdAndSupplierType(Integer groupId,Integer supplierType);
	 BigDecimal getBookingPriceSumByGroupIdAndSupplierType(Integer groupId,Integer supplierType);
	List<BookingSupplier> getBookingSupplierByGroupIdAndSupplierType(Integer groupId,Integer supplierType);
	List<BookingSupplierPO> getBookingSupplierByGroupIdAndSupplierType(Integer groupId,Integer supplierType,String supplierName,String driverName,String carLisence);
	List<BookingSupplier> getBookingSupplierBySupplierType(Integer supplierType);
	BookingSupplier getBookingSupplierInfoByBookingId(Integer bookingId);
	void stateConfirm(Integer id);
	int getBookingCountByTypeAndTime(Integer supplierType);
	List<BookingSupplier> getDriverByGroupIdAndType(Integer supplierType,Integer groupId);
	int getCountBySupplierId(Map parameters);
	int getCountByType1Id(Integer type1Id,Integer supplierType);
	int getOrderCountBySupplierId(Integer supplierId);
	List<BookingSupplier> getBookingSupplierByGroupIdAndType(Integer groupId,Integer supplierType);
	/**
	 * 为解决机票安排而添加的接口
	 * 并团后更新订单对应的机票安排的groupId
	 * 根据传入的散客团机票订单orderId查询然后更新groupId
	 * @param groupId
	 * @param orderIdList
	 */
	void updateOperationAfterMergeGroupOrder(Integer groupId,List<Integer> orderIdList);//
	/**
	 * 为解决机票安排而添加的接口
	 * 将订单从团中删除后，将订单的机票安排的groupId置为null
	 * @param orderId
	 */
	void upateGroupIdAfterDelOrderFromGroup(Integer orderId);

	//查询某天某类型某团的商家,supplierType:2--餐厅;3--酒店;5--景区; itemType:1--早餐，2--午餐，3--晚餐
	List<BookingSupplierAndDetailVO> getBookingSupplierByGroupIdAndSupplierTypeAndGroupDate(Integer groupId,String groupDate);
	int save(BookingSupplier bookingSupplier,String bookingNo);
	
	// 查询、统计机票订单的方法
	PageBean<BookingAirTicket> selectAirTicketBookingListPage(PageBean<BookingAirTicket> pageBean);
	HashMap<String, BigDecimal> sumAirTicketBooking(PageBean<BookingAirTicket> pageBean);
	List<Integer> getGroupIdByCarInfo(String carInfo);

	List<BookingSupplier> selectByGroupIdAndSupplierType(Integer groupId, Integer supplierType);
	
	List<BookingSupplier> getHotelInfoByGroupId(Integer groupId);
	
	List<BookingSupplier> selectIdList();
	
	void updateGroupIdByOrderId(Integer groupId, Integer orderId);

	void fix_SupplierName_All(Integer supplierId, String supplierName);

}
