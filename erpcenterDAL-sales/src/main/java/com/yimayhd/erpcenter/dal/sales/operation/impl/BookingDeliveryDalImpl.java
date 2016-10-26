package com.yimayhd.erpcenter.dal.sales.operation.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.sales.client.finance.service.FinanceDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDeliveryOrder;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDeliveryPrice;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDeliveryRoute;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingDeliveryDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingDeliveryStatics;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingDeliveryMapper;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingDeliveryOrderMapper;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingDeliveryPriceMapper;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingDeliveryRouteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.fastjson.util.TypeUtils;


public class BookingDeliveryDalImpl implements BookingDeliveryDal {
	Logger logger = LoggerFactory.getLogger(BookingDeliveryDalImpl.class);
	@Autowired
	private BookingDeliveryMapper bookingDeliveryMapper;
	@Autowired
	private BookingDeliveryOrderMapper bookingDeliveryOrderMapper;
	@Autowired
	private BookingDeliveryRouteMapper bookingDeliveryRouteMapper;
	@Autowired
	private BookingDeliveryPriceMapper bookingDeliveryPriceMapper;
	@Autowired
	private FinanceDal financeDal;
	
	@Override
	public BookingDeliveryStatics getStaticsByGroupId(Integer groupId) {
		return bookingDeliveryMapper.getStaticsByGroupId(groupId);
	}

	@Override
	public List<BookingDelivery> getDeliveryListByGroupId(Integer groupId) {
		  List<BookingDelivery> bookingDeliveries = bookingDeliveryMapper.getDeliveryListByGroupId(groupId);
//		  for (BookingDelivery bd : bookingDeliveries) {
//			
//			  List<BookingDeliveryOrder> orderList = bookingDeliveryOrderMapper.getOrderListByBookingId(bd.getId());
//			  bd.setOrderList(orderList);
//		}
		return bookingDeliveries;
	}

	/**
	 * 添加默认级别事务，保存失败后回滚
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int saveBooking(BookingDelivery bookingDelivery) {

		if(bookingDelivery.getId()==null){
			//新增
			bookingDeliveryMapper.insert(bookingDelivery);
			Integer bookingId = bookingDelivery.getId();			
			List<BookingDeliveryOrder> orderList =bookingDelivery.getOrderList();
			if(orderList!=null && orderList.size()>0){
				for(BookingDeliveryOrder order : orderList){
					if(order.getId()==null){
						order.setBookingId(bookingId);
					}
				}
				bookingDeliveryOrderMapper.insertBatch(orderList);
			}
			List<BookingDeliveryRoute> routeList = bookingDelivery.getRouteList();
			if(routeList!=null && routeList.size()>0){
				for(BookingDeliveryRoute route : routeList){
					if(route.getId()==null){
						route.setBookingId(bookingId);
					}
				}
				bookingDeliveryRouteMapper.insertBatch(routeList);
			}	
			List<BookingDeliveryPrice> priceList = bookingDelivery.getPriceList();
			if(priceList!=null && priceList.size()>0){
				for(BookingDeliveryPrice price : priceList){
					price.setBookingId(bookingId);
					price.setCreateTime(System.currentTimeMillis());
					price.setCreatorId(bookingDelivery.getUserId());
					price.setCreatorName(bookingDelivery.getUserName());					
				}
				bookingDeliveryPriceMapper.insertBatch(priceList);
			}
			
		}else{
			//修改			
			if(bookingDelivery.getStateBooking()!=null && bookingDelivery.getStateBooking()==1){
				//改为变更
				bookingDelivery.setStateBooking(2);
			}
			bookingDeliveryMapper.updateByPrimaryKeySelective(bookingDelivery);
			List<BookingDeliveryOrder> orderList =bookingDelivery.getOrderList();
			List<BookingDeliveryOrder> addOrderList = new ArrayList<BookingDeliveryOrder>();
			List<BookingDeliveryOrder> updateOrderList = new ArrayList<BookingDeliveryOrder>();
			if(orderList!=null && orderList.size()>0){
				for(BookingDeliveryOrder order : orderList){
					if(order.getId()!=null){
						updateOrderList.add(order);
					}else{
						order.setBookingId(bookingDelivery.getId());
						addOrderList.add(order);
					}
				}
				//删除除updateList之外的数据库数据
				if(updateOrderList.size()>0){
					bookingDeliveryOrderMapper.deleteBatch(updateOrderList);
				}
				if(addOrderList.size()>0){
					bookingDeliveryOrderMapper.insertBatch(addOrderList);					
				}
				//bookingDeliveryOrderMapper.updateBatch(updateOrderList);
			}
			
			List<BookingDeliveryRoute> routeList = bookingDelivery.getRouteList();
			List<BookingDeliveryRoute> addRouteList = new ArrayList<BookingDeliveryRoute>();
			List<BookingDeliveryRoute> updateRouteList = new ArrayList<BookingDeliveryRoute>();
			if(routeList!=null && routeList.size()>0){
				for(BookingDeliveryRoute route : routeList){
					if(route.getId()!=null){
						updateRouteList.add(route);
					}else{
						route.setBookingId(bookingDelivery.getId());
						addRouteList.add(route);
					}
				}
				if(updateRouteList.size()>0){
					bookingDeliveryRouteMapper.deleteBatch(updateRouteList);
				}
				if(addRouteList.size()>0){
					bookingDeliveryRouteMapper.insertBatch(addRouteList);
				}
			}
			//修改更新方式：全部删除，然后全部插入
			List<BookingDeliveryPrice> priceList = bookingDelivery.getPriceList();
			bookingDeliveryPriceMapper.deleteByBookingId(bookingDelivery.getId());			
			//bookingDeliveryPriceMapper.deleteByBookingId(bookingDelivery.getId());
			//List<BookingDeliveryPrice> addPriceList = new ArrayList<BookingDeliveryPrice>();
			//List<BookingDeliveryPrice> updatePriceList = new ArrayList<BookingDeliveryPrice>();
			if(priceList!=null && priceList.size()>0){
				for(BookingDeliveryPrice price : priceList){
					if(price.getId()==null){
						price.setBookingId(bookingDelivery.getId());
						price.setCreateTime(System.currentTimeMillis());
						price.setCreatorId(bookingDelivery.getUserId());
						price.setCreatorName(bookingDelivery.getUserName());
						//addPriceList.add(price);
					}else{
						price.setBookingId(bookingDelivery.getId());
						//updatePriceList.add(price);
					}
				}
				/*
				if(updatePriceList.size()>0){
					bookingDeliveryPriceMapper.deleteBatch(updatePriceList);
					//bookingDeliveryPriceMapper.updateBatch(updatePriceList);					
					//System.out.println(delete);
				}
				if(addPriceList.size()>0){
					bookingDeliveryPriceMapper.insertBatch(addPriceList);
					//System.out.println(insert);
				}*/
				bookingDeliveryPriceMapper.insertBatch(priceList);
			}
		}
		 bookingDeliveryMapper.updateTotal(bookingDelivery.getId());
		 //更新团里的统计信息
		financeDal.calcTourGroupAmount(bookingDelivery.getGroupId());
		 return bookingDelivery.getId();
	}

	@Override
	public BookingDelivery getBookingInfoById(Integer bookingId) {
		BookingDelivery delivery = bookingDeliveryMapper.selectByPrimaryKey(bookingId);
		if(delivery!=null){
			List<BookingDeliveryOrder> orderList = bookingDeliveryOrderMapper.getOrderListByBookingId(bookingId);
			List<BookingDeliveryRoute> routeList = bookingDeliveryRouteMapper.getRouteListByBookingId(bookingId);
			List<BookingDeliveryPrice> priceList = bookingDeliveryPriceMapper.getPriceListByBookingId(bookingId);
			delivery.setOrderList(orderList);
			delivery.setRouteList(routeList);
			delivery.setPriceList(priceList);
			try {
				delivery.setOrderListJson(JSON.json(orderList));
				delivery.setRouteListJson(JSON.json(routeList));
				delivery.setPriceListJson(JSON.json(priceList));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return delivery;
	}
	
	@Override
	public void angencyConfirm(Integer id) {
		BookingDelivery delivery = new BookingDelivery();
		delivery.setId(id);		
		delivery.setStateBooking(1);
		bookingDeliveryMapper.updateByPrimaryKeySelective(delivery);
	}

	public void agencyStateChange(Integer id) {
		BookingDelivery delivery = new BookingDelivery();
		delivery.setId(id);		
		delivery.setStateBooking(2);
		bookingDeliveryMapper.updateByPrimaryKeySelective(delivery);
	}

	@Override
	public void financeAudit(Integer id) {
		BookingDelivery delivery = new BookingDelivery();
		delivery.setId(id);		
		delivery.setStateFinance(1);
		bookingDeliveryMapper.updateByPrimaryKeySelective(delivery);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void angencyDelete(Integer id) throws ClientException {
		BookingDelivery delivery = bookingDeliveryMapper.selectByPrimaryKey(id);
		if(delivery==null){
			throw new ClientException("下接单已不存在！");
		}else{
			if(delivery.getCanDelete()){
				int result = bookingDeliveryMapper.deleteByPrimaryKey(id);
				bookingDeliveryOrderMapper.deleteByBookingId(id);
				bookingDeliveryRouteMapper.deleteByBookingId(id);
				bookingDeliveryPriceMapper.deleteByBookingId(id);
				
				//更新团里的统计信息
				financeDal.calcTourGroupAmount(delivery.getGroupId());
				
				if(result==0){
					throw new ClientException("删除失败！");
				}
				return;
			}
			throw new ClientException("此单据已产生付未或财务已审核，不能删除！");
		}
	}

	/**
	 * 修改散客团人数时更新地接社人数
	 * @param groupId
	 * @return
	 */
	@Override
	public int updateDeliveryGuestCountOnModifySKGuestCount(Integer groupId) {
		if(groupId==null){
			return -1;
		}
		List<Map<String,Integer>> list = bookingDeliveryMapper.getBookingIdListByGroupId(groupId);
		if(list!=null && list.size()>0){
			for(Map<String,Integer> map :list){
				Integer bookingId = map.get("id");
				Map<String,Object> guestCntMap = bookingDeliveryMapper.getGuestCountByBookingId(bookingId);
				if(guestCntMap == null){//如果下接订单没关联订单则map为空
					continue;
				}
				Integer adultNum = TypeUtils.castToInt(guestCntMap.get("adultNum"));
				Integer childNum = TypeUtils.castToInt(guestCntMap.get("childNum"));
				Integer guideNum = TypeUtils.castToInt(guestCntMap.get("guideNum"));
				BookingDelivery delivery = new BookingDelivery();
				delivery.setId(bookingId);
				delivery.setPersonAdult(adultNum);
				delivery.setPersonChild(childNum);
				delivery.setPersonGuide(guideNum);
				bookingDeliveryMapper.updateByPrimaryKeySelective(delivery);
			}			
			return 1;
		}
		return 0;
	}
	/**
	 * 修改团队人数时更新地接社人数
	 * @param groupId
	 * @param adultCnt
	 * @param childCnt
	 * @param guideCnt
	 * @return
	 */
	@Override
	public int updateDeliveryGuestCountOnModifyTeamGuestCount(
			Integer groupId, Integer adultCnt, Integer childCnt,
			Integer guideCnt) {
		if(groupId==null){
			return -1;
		}
		if(adultCnt==null && childCnt==null && guideCnt==null){
			return -2;
		}
		adultCnt = adultCnt == null ? 0:adultCnt;
		childCnt = childCnt == null ? 0:childCnt;
		guideCnt = guideCnt == null ? 0:guideCnt;
		BookingDelivery delivery = new BookingDelivery();
		delivery.setGroupId(groupId);
		delivery.setPersonAdult(adultCnt);
		delivery.setPersonChild(childCnt);
		delivery.setPersonGuide(guideCnt);
		return bookingDeliveryMapper.updateGuestCntByGroupId(delivery);
	}

	@Override
	public List<BookingDelivery> selectInitDeliveryList(Integer groupId) {
		return bookingDeliveryMapper.selectInitDeliveryList(groupId);
	}

	/*@Override
	public BookingDelivery getBookingDeliveryByOrderId(Integer orderId) {
		return bookingDeliveryMapper.getBookingDeliveryByOrderId(orderId);
	}
	*/
	
}
