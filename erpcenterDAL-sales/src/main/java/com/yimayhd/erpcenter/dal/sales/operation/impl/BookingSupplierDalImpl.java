package com.yimayhd.erpcenter.dal.sales.operation.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceGuide;
import com.yimayhd.erpcenter.dal.sales.client.finance.service.FinanceDal;
import com.yimayhd.erpcenter.dal.sales.client.finance.service.FinanceGuideDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.*;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingSupplierDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingSupplierAndDetailVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.finance.dao.FinanceGuideMapper;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingGuideMapper;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingSupplierDetailMapper;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingSupplierMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupRequirementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.yihg.mybatis.utility.PageBean;

public class BookingSupplierDalImpl implements BookingSupplierDal{

	@Autowired
	private BookingSupplierMapper bookingSupplierMapper;
	
	@Autowired
	private GroupRequirementMapper groupRequirementMapper;
	
	@Autowired
	private BookingSupplierDetailMapper bookingSupplierDetailMapper;
	@Autowired
	private GroupOrderMapper orderMapper;
	@Autowired
	private FinanceDal financeDal;
	@Autowired
	private FinanceGuideMapper financeGuideMapper;
	@Autowired
	private BookingGuideMapper bookingGuideMapper;
	@Autowired
	private FinanceGuideDal financeGuideDal;
	
	@Override
	@Transactional
	public void deleteByPrimaryKey(Integer id) throws ClientException {
		
		deleteSupplierWithFinanceByPrimaryKey(id, true);
		
		//由于这个方法ERP-APP也调用了，
		//且删除供应商订单时，要增加对该订单导游是否已报账的逻辑判断，所以修改了此方法， @author guohongfei
		/** 
		BookingSupplier bookingSupplier = bookingSupplierMapper.selectByPrimaryKey(id);
		if(bookingSupplier==null){
			throw new ClientException("该订单已不存在！");
		}else {
			if(bookingSupplier.getCanDelete()){
				
				int result=bookingSupplierMapper.deleteByPrimaryKey(id);
				
				financeService.calcTourGroupAmount(bookingSupplier.getGroupId());
				if(result==0){
					throw new ClientException("删除失败！");
				}
				return;
			}
			throw new ClientException("此单据已产生付未或财务已审核，不能删除！");
		}
		**/
	}
	
	@Override
	@Transactional
	public void deleteSupplierWithFinanceByPrimaryKey(Integer id, boolean isOperator) throws ClientException {
		
		BookingSupplier bookingSupplier = bookingSupplierMapper.selectByPrimaryKey(id);
		if(bookingSupplier==null){
			throw new ClientException("该订单已不存在！");
		}
		
		if(!bookingSupplier.getCanDelete()){
			throw new ClientException("此单据已产生付未或财务已审核，不能删除！");
		}
		
		boolean canDelete = false;
		FinanceGuide fg = financeGuideMapper.selectByBookingIdLink(id);
		BookingGuide bg = null;
		if(fg == null){
			canDelete = true;
		}else{
			bg = bookingGuideMapper.selectByPrimaryKey(fg.getBookingId());
			if(bg.getStateBooking()!=null && bg.getStateBooking() == (byte)2){
				throw new ClientException("此单据导游已报账并且提交财务，不能删除！");
			}
			canDelete = true;
		}
		
		if(canDelete){
			if(isOperator){
				if (Constants.AIRTICKETAGENT.equals(bookingSupplier.getSupplierType()) && bookingSupplier.getOrderId()!=null){
					throw new ClientException("该机票订单由机票管理出票操作生成，不能删除！");
				} 
				if (Constants.TRAINTICKETAGENT.equals(bookingSupplier.getSupplierType()) && bookingSupplier.getOrderId()!=null){
					throw new ClientException("该火车票订单由机票管理出票操作生成，不能删除！");
				} 
			}
			
			int result=bookingSupplierMapper.deleteByPrimaryKey(id);
			if(result==0){
				throw new ClientException("删除失败！");
			}
			
			if(fg != null && bg != null){
				//financeGuideMapper.deleteByBookingIdLink(id);
				financeGuideDal.financeDelete(bg.getGroupId(), id, bg.getId());
			}

			financeDal.calcTourGroupAmount(bookingSupplier.getGroupId());
		}
	}

	@Override
	public int insert(BookingSupplier record) {
		  bookingSupplierMapper.insertSelective(record);
		financeDal.calcTourGroupAmount(record.getGroupId());
		  return record.getId();
	}

	@Override
	public int insertSelective(BookingSupplier record) {
		bookingSupplierMapper.insertSelective(record);
		financeDal.calcTourGroupAmount(record.getGroupId());
		return record.getId();
	}

	@Override
	public BookingSupplier selectByPrimaryKey(Integer id) {
		return bookingSupplierMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(BookingSupplier record) {
		return bookingSupplierMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BookingSupplier record) {
		return bookingSupplierMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<BookingSupplier> selectByPrimaryGroupId(Integer groupId) {
		return bookingSupplierMapper.selectByPrimaryGroupId(groupId);
	}
	@Override
	public List<BookingSupplier> selectByGroupId(Integer groupId) {
		return bookingSupplierMapper.selectByGroupId(groupId);
	}

	@Override
	public Map<String, Object> selectBookingInfo(Integer groupId, Integer supplierType) {
		List<GroupRequirement> groupRequirements = null;
		//餐厅 2 房 3 车队 4 导游 8 机票 9 火车票 10 有计调需求		
		if(supplierType.equals(Constants.RESTAURANT) || supplierType.equals(Constants.HOTEL) || supplierType.equals(Constants.FLEET) 
				|| supplierType.equals(Constants.GUIDE)	|| supplierType.equals(Constants.AIRTICKETAGENT) || supplierType.equals(Constants.TRAINTICKETAGENT)){
			groupRequirements = groupRequirementMapper.selectByGroupIdAndType(groupId, supplierType);
			if (groupRequirements!=null && groupRequirements.size()>0) {
				for (GroupRequirement req : groupRequirements) {
					GroupOrder groupOrder = orderMapper.selectByPrimaryKey(req.getOrderId());
					if (groupOrder!=null) {
						req.setNameFull(groupOrder.getOrderType()==1?groupOrder.getSupplierName():"散客团");
						req.setReceiveMode(groupOrder.getReceiveMode());
					}
				}
			}			
		}
		List<BookingSupplier> bookingList = bookingSupplierMapper.getBookingSupplierByGroupIdAndType(groupId, supplierType);
		//for (BookingSupplier booking : bookingList) {
		//	 BigDecimal priceSum = bookingSupplierDetailMapper.getBookingPriceSumByBookingId(booking.getId());
			// List<BookingSupplierDetail> selectByPrimaryBookId = bookingSupplierDetailMapper.selectByPrimaryBookId(booking.getId());
		//	booking.setTotal(priceSum);
			
		//}
		if(bookingList!=null && bookingList.size()>0){
			for (BookingSupplier booking : bookingList) {
				booking.setDetailList(bookingSupplierDetailMapper.selectByPrimaryBookId(booking.getId()));
			}
		}
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("requirementInfos", groupRequirements);
		datas.put("bookingList", bookingList);
		
		return datas;
	}
	@Override
	public Map<String, Object> selectBookingInfoPO(Integer groupId, Integer supplierType) {
		List<GroupRequirement> groupRequirements = null;
		//餐厅 2 房 3 车队 4 导游 8 机票 9 火车票 10 有计调需求		
		if(supplierType.equals(Constants.RESTAURANT) || supplierType.equals(Constants.HOTEL) || supplierType.equals(Constants.FLEET) 
				|| supplierType.equals(Constants.GUIDE)	|| supplierType.equals(Constants.AIRTICKETAGENT) || supplierType.equals(Constants.TRAINTICKETAGENT)){
			groupRequirements = groupRequirementMapper.selectByGroupIdAndType(groupId, supplierType);
			if (groupRequirements!=null && groupRequirements.size()>0) {
				GroupOrder groupOrder = orderMapper
						.selectByPrimaryKey(groupRequirements.get(0).getOrderId());
				
					for (GroupRequirement req : groupRequirements) {
						if (groupOrder!=null) {
						req.setNameFull(groupOrder.getSupplierName()!=null?groupOrder.getSupplierName():"");
					}
						else {
							req.setNameFull("");
						}
				}
			}
		}
		List<BookingSupplierPO> bookingList = bookingSupplierMapper.getBookingSupplierPOByGroupIdAndType(groupId, supplierType);

		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("requirementInfos", groupRequirements);
		datas.put("bookingList", bookingList);
		
		return datas;
	}

	@Override
	public int getBookingCountByGroupIdAndSupplierType(Integer groupId,Integer supplierType) {
		
		
		BigDecimal count=bookingSupplierMapper.getBookingCountByGroupIdAndSupplierType(groupId, supplierType);
				return count==null?0:count.intValue();
	}

	@Override
	public BigDecimal getBookingPriceSumByGroupIdAndSupplierType(Integer groupId,Integer supplierType) {
		BigDecimal count=bookingSupplierMapper.getBookingPriceSumByGroupIdAndSupplierType(groupId,supplierType);
		return count==null? new BigDecimal(0.00):count;
	}

	@Override
	public List<BookingSupplier> getBookingSupplierByGroupIdAndSupplierType(
			Integer groupId, Integer supplierType) {
		return bookingSupplierMapper.getBookingSupplierByGroupIdAndType(groupId, supplierType);
	}

	@Override
	public BookingSupplier getBookingSupplierInfoByBookingId(Integer bookingId){
		BookingSupplier supplier = bookingSupplierMapper.selectByPrimaryKey(bookingId);
		List<BookingSupplierDetail> detailList = bookingSupplierDetailMapper.selectByPrimaryBookId(bookingId);
		supplier.setDetailList(detailList);
		return supplier;
	}

	@Override
	public BookingSupplier selectByPrimaryGroupIdAndType(Integer groupId) {
		return bookingSupplierMapper.selectByPrimaryGroupIdAndType(groupId);
	}

	@Override
	public void stateConfirm(Integer id) {
		BookingSupplier booking=new BookingSupplier();
		booking.setId(id);
		booking.setStateBooking(1);
		bookingSupplierMapper.updateByPrimaryKeySelective(booking);
	}

	@Override
	public int getBookingCountByTypeAndTime(Integer supplierType) {
		Date date=new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		Long curDay=date.getTime();
		date.setDate(date.getDate()+1);
		Long nextDay=date.getTime();
		return bookingSupplierMapper.getBookingCountByTypeAndTime(supplierType, curDay, nextDay);
	}

	@Override
	public List<BookingSupplier> getDriverByGroupIdAndType(
			Integer supplierType, Integer groupId) {
		return bookingSupplierMapper.getBookingSupplierByGroupIdAndType(groupId, supplierType);

	}

	@Override
	public int getCountBySupplierId(Map parameters) {
		return bookingSupplierMapper.getCountBySupplierId(parameters);
	}

	@Override
	public int getCountByType1Id(Integer type1Id, Integer supplierType) {
		return bookingSupplierMapper.getCountBytypeId(type1Id, supplierType);
	}

	@Override
	public int getOrderCountBySupplierId(Integer supplierId) {
		return bookingSupplierMapper.getOrderCountBySupplierId(supplierId);
	}
	@Override
	public List<BookingSupplier> getBookingSupplierBySupplierType(
			Integer supplierType) {
		return bookingSupplierMapper.getBookingSupplierBySupplierType(supplierType);
	}

	@Override
	public List<BookingSupplierPO> getBookingSupplierByGroupIdAndSupplierType(
			Integer groupId, Integer supplierType, String supplierName,String driverName,String carLisence) {
		return bookingSupplierMapper.getBookingSupplierPOByGroupIdAndTypeAndName(groupId, supplierType,supplierName,driverName,carLisence);
	}

	@Override
	public void updateOperationAfterMergeGroupOrder(Integer groupId,
			List<Integer> orderIdList) {
		bookingSupplierMapper.updateGroupIdByOrderIds(groupId, orderIdList);
	}

	@Override
	public List<BookingSupplier> getBookingSupplierByGroupIdAndType(
			Integer groupId, Integer supplierType) {
		return bookingSupplierMapper.getBookingSupplierByGroupIdAndType(groupId, supplierType);
	}


	@Override
	public List<BookingSupplierAndDetailVO> getBookingSupplierByGroupIdAndSupplierTypeAndGroupDate(Integer groupId, String groupDate) {
		return bookingSupplierMapper.getBookingSupplierByGroupIdAndSupplierTypeAndGroupDate(groupId, groupDate);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int save(BookingSupplier bookingSupplier,String bookingNo) {
		double priceSum=0.0;
		int id=0;
		List<BookingSupplierDetail> detailList = bookingSupplier.getDetailList();
		for (BookingSupplierDetail detail : detailList) {
			priceSum=priceSum+detail.getItemTotal();
		}
		bookingSupplier.setTotal(new BigDecimal(priceSum));
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(new Date());
		try {
			bookingSupplier.setBookingDate(format.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//如果是车、其他收入、其他支出
		//则不用删除明细数据，直接更新即可
		Boolean flag = bookingSupplier.getSupplierType().equals(Constants.FLEET)
				|| bookingSupplier.getSupplierType().equals(Constants.OTHERINCOME)
				|| bookingSupplier.getSupplierType().equals(Constants.OTHEROUTCOME);
		
		//如果bookingId不为null,更新
		if(bookingSupplier.getId()!=null){
			if(!flag){
				bookingSupplierDetailMapper.deleteByBookingId(bookingSupplier.getId());	
			}
			if (bookingSupplier.getStateBooking()!=null && bookingSupplier.getStateBooking().equals(1)) {
				bookingSupplier.setStateBooking(2);
			}
			bookingSupplierMapper.updateByPrimaryKey(bookingSupplier);
			if(flag){//如果是三种类型中的一种，则直接更新即可
				if(detailList!=null && detailList.size()>0){
					BookingSupplierDetail detail = detailList.get(0);
					detail.setBookingId(bookingSupplier.getId());
					bookingSupplierDetailMapper.updateByPrimaryKey(detail);
				}
				//从新统计财务数据
				financeDal.calcTourGroupAmount(bookingSupplier.getGroupId());
				return bookingSupplier.getId(); 
			}
		}
		//如果bookingId为null，新增
		else{
			bookingSupplier.setStateBooking(0);
			bookingSupplier.setStateFinance(0);
			bookingSupplier.setCreateTime(new Date().getTime());
			bookingSupplier.setBookingNo(bookingNo);
			id=bookingSupplierMapper.insert(bookingSupplier);
		}
		
		for (BookingSupplierDetail detail : detailList) {
			detail.setBookingId(bookingSupplier.getId());
			bookingSupplierDetailMapper.insert(detail);
		}
		
		//从新统计财务数据
		financeDal.calcTourGroupAmount(bookingSupplier.getGroupId());
		
		return bookingSupplier.getId();
		
	}

	public PageBean<BookingAirTicket> selectAirTicketBookingListPage(PageBean<BookingAirTicket> pageBean){
		List<BookingAirTicket> result = bookingSupplierMapper.selectAirTicketBookingListPage(pageBean);
		pageBean.setResult(result);
		return pageBean;
	}
	
	public HashMap<String, BigDecimal> sumAirTicketBooking(PageBean<BookingAirTicket> pageBean){
		return bookingSupplierMapper.sumAirTicketBooking(pageBean);
	}

	@Override
	public List<Integer> getGroupIdByCarInfo(String carInfo) {
		return bookingSupplierMapper.getGroupIdByCarInfo(carInfo);
	}

	@Override
	public List<BookingSupplier> selectByGroupIdAndSupplierType(
			Integer groupId, Integer supplierType) {
		return bookingSupplierMapper.selectByGroupIdAndSupplierType(groupId,supplierType);
	}

	@Override
	public List<BookingSupplier> getHotelInfoByGroupId(Integer groupId) {
		return bookingSupplierMapper.getHotelInfoByGroupId(groupId);
	}

	@Override
	public void upateGroupIdAfterDelOrderFromGroup(Integer orderId) {
		bookingSupplierMapper.updateAirGroupIdNullByOrderId(orderId);		
	}
	
	public List<BookingSupplier> selectIdList(){
		return bookingSupplierMapper.selectIdList();
	}
	
	@Override
	public void updateGroupIdByOrderId(Integer groupId, Integer orderId) {
		if(groupId == null || orderId == null){
			return;
		}
		bookingSupplierMapper.updateGroupIdByOrderId(groupId, orderId);
	}

	/* (non-Javadoc)
	 * @see com.yihg.operation.api.BookingSupplierService#fix_SupplierName_All(java.lang.Integer, java.lang.String)
	 */
	@Override
	public void fix_SupplierName_All(Integer supplierId, String supplierName) {
		 bookingSupplierMapper.fix_SupplierName_bookingSupplier(supplierId,supplierName);
		 bookingSupplierMapper.fix_SupplierName_financePay(supplierId, supplierName);
		 bookingSupplierMapper.fix_SupplierName_financeVerify(supplierId, supplierName);
		 bookingSupplierMapper.fix_SupplierName_groupOrder(supplierId, supplierName);
		 bookingSupplierMapper.fix_SupplierName_bookingShop(supplierId, supplierName);
		 bookingSupplierMapper.fix_SupplierName_bookingDelivery(supplierId, supplierName);
	}


}
