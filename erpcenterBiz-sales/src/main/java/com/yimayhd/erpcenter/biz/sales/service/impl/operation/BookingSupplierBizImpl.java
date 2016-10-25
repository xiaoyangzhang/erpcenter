package com.yimayhd.erpcenter.biz.sales.service.impl.operation;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingAirTicket;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierPO;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingSupplierDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingSupplierAndDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class BookingSupplierBizImpl implements BookingSupplierBiz{

	@Autowired
	private BookingSupplierDal bookingSupplierDal;
	
	@Override
	@Transactional
	public void deleteByPrimaryKey(Integer id) throws ClientException {
		bookingSupplierDal.deleteByPrimaryKey(id);

	}
	
	@Override
	@Transactional
	public void deleteSupplierWithFinanceByPrimaryKey(Integer id, boolean isOperator) throws ClientException {
		bookingSupplierDal.deleteSupplierWithFinanceByPrimaryKey(id,isOperator);
	}

	@Override
	public int insert(BookingSupplier record) {
		  return  bookingSupplierDal.insert(record);
	}

	@Override
	public int insertSelective(BookingSupplier record) {

		return bookingSupplierDal.insertSelective(record);
	}

	@Override
	public BookingSupplier selectByPrimaryKey(Integer id) {
		return bookingSupplierDal.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(BookingSupplier record) {
		return bookingSupplierDal.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BookingSupplier record) {
		return bookingSupplierDal.updateByPrimaryKey(record);
	}

	@Override
	public List<BookingSupplier> selectByPrimaryGroupId(Integer groupId) {
		return bookingSupplierDal.selectByPrimaryGroupId(groupId);
	}
	@Override
	public List<BookingSupplier> selectByGroupId(Integer groupId) {
		return bookingSupplierDal.selectByGroupId(groupId);
	}

	@Override
	public Map<String, Object> selectBookingInfo(Integer groupId, Integer supplierType) {
		return bookingSupplierDal.selectBookingInfo(groupId,supplierType);
	}
	@Override
	public Map<String, Object> selectBookingInfoPO(Integer groupId, Integer supplierType) {
		return bookingSupplierDal.selectBookingInfoPO(groupId,supplierType);
	}

	@Override
	public int getBookingCountByGroupIdAndSupplierType(Integer groupId,Integer supplierType) {
			return bookingSupplierDal.getBookingCountByGroupIdAndSupplierType(groupId,supplierType);
	}

	@Override
	public BigDecimal getBookingPriceSumByGroupIdAndSupplierType(Integer groupId,Integer supplierType) {
		return bookingSupplierDal.getBookingPriceSumByGroupIdAndSupplierType(groupId, supplierType);
	}

	@Override
	public List<BookingSupplier> getBookingSupplierByGroupIdAndSupplierType(
			Integer groupId, Integer supplierType) {
		return bookingSupplierDal.getBookingSupplierByGroupIdAndType(groupId, supplierType);
	}

	@Override
	public BookingSupplier getBookingSupplierInfoByBookingId(Integer bookingId){
		return bookingSupplierDal.getBookingSupplierInfoByBookingId(bookingId);
	}

	@Override
	public BookingSupplier selectByPrimaryGroupIdAndType(Integer groupId) {
		return bookingSupplierDal.selectByPrimaryGroupIdAndType(groupId);
	}

	@Override
	public void stateConfirm(Integer id) {
		bookingSupplierDal.stateConfirm(id);
	}

	@Override
	public int getBookingCountByTypeAndTime(Integer supplierType) {
		return bookingSupplierDal.getBookingCountByTypeAndTime(supplierType);
	}

	@Override
	public List<BookingSupplier> getDriverByGroupIdAndType(
			Integer supplierType, Integer groupId) {
		return bookingSupplierDal.getBookingSupplierByGroupIdAndType(groupId, supplierType);

	}

	@Override
	public int getCountBySupplierId(Map parameters) {
		return bookingSupplierDal.getCountBySupplierId(parameters);
	}

	@Override
	public int getCountByType1Id(Integer type1Id, Integer supplierType) {
		return bookingSupplierDal.getCountByType1Id(type1Id, supplierType);
	}

	@Override
	public int getOrderCountBySupplierId(Integer supplierId) {
		return bookingSupplierDal.getOrderCountBySupplierId(supplierId);
	}
	@Override
	public List<BookingSupplier> getBookingSupplierBySupplierType(
			Integer supplierType) {
		return bookingSupplierDal.getBookingSupplierBySupplierType(supplierType);
	}

	@Override
	public List<BookingSupplierPO> getBookingSupplierByGroupIdAndSupplierType(
			Integer groupId, Integer supplierType, String supplierName,String driverName,String carLisence) {
		return bookingSupplierDal.getBookingSupplierByGroupIdAndSupplierType(groupId, supplierType, supplierName, driverName, carLisence);
	}

	@Override
	public void updateOperationAfterMergeGroupOrder(Integer groupId,
			List<Integer> orderIdList) {
		bookingSupplierDal.updateOperationAfterMergeGroupOrder(groupId, orderIdList);
	}

	@Override
	public List<BookingSupplier> getBookingSupplierByGroupIdAndType(
			Integer groupId, Integer supplierType) {
		return bookingSupplierDal.getBookingSupplierByGroupIdAndType(groupId, supplierType);
	}


	@Override
	public List<BookingSupplierAndDetailVO> getBookingSupplierByGroupIdAndSupplierTypeAndGroupDate(Integer groupId, String groupDate) {
		return bookingSupplierDal.getBookingSupplierByGroupIdAndSupplierTypeAndGroupDate(groupId, groupDate);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int save(BookingSupplier bookingSupplier,String bookingNo) {
		return bookingSupplierDal.save(bookingSupplier,bookingNo);
		
	}

	public PageBean<BookingAirTicket> selectAirTicketBookingListPage(PageBean<BookingAirTicket> pageBean){
		return bookingSupplierDal.selectAirTicketBookingListPage(pageBean);
	}
	
	public HashMap<String, BigDecimal> sumAirTicketBooking(PageBean<BookingAirTicket> pageBean){
		return bookingSupplierDal.sumAirTicketBooking(pageBean);
	}

	@Override
	public List<Integer> getGroupIdByCarInfo(String carInfo) {
		return bookingSupplierDal.getGroupIdByCarInfo(carInfo);
	}

	@Override
	public List<BookingSupplier> selectByGroupIdAndSupplierType(
			Integer groupId, Integer supplierType) {
		return bookingSupplierDal.selectByGroupIdAndSupplierType(groupId,supplierType);
	}

	@Override
	public List<BookingSupplier> getHotelInfoByGroupId(Integer groupId) {
		return bookingSupplierDal.getHotelInfoByGroupId(groupId);
	}

	@Override
	public void upateGroupIdAfterDelOrderFromGroup(Integer orderId) {
		bookingSupplierDal.upateGroupIdAfterDelOrderFromGroup(orderId);
	}
	
	public List<BookingSupplier> selectIdList(){
		return bookingSupplierDal.selectIdList();
	}
	
	@Override
	public void updateGroupIdByOrderId(Integer groupId, Integer orderId) {
		if(groupId == null || orderId == null){
			return;
		}
		bookingSupplierDal.updateGroupIdByOrderId(groupId, orderId);
	}

	/* (non-Javadoc)
	 * @see com.yihg.operation.api.BookingSupplierService#fix_SupplierName_All(java.lang.Integer, java.lang.String)
	 */
	@Override
	public void fix_SupplierName_All(Integer supplierId, String supplierName) {
		bookingSupplierDal.fix_SupplierName_All(supplierId,supplierName);
	}


}
