package com.yimayhd.erpcenter.dal.sales.airticket.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yimayhd.erpcenter.dal.sales.airticket.dao.AirTicketOrderMapper;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketOrder;
import com.yimayhd.erpcenter.dal.sales.client.airticket.service.AirTicketOrderDal;

public class AirTicketOrderDalImpl implements AirTicketOrderDal {
	
	@Autowired
	private AirTicketOrderMapper orderMapper;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer saveOrder(AirTicketOrder airTicketOrder) {
		return orderMapper.insert(airTicketOrder);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteOrder(Integer orderId,Integer bizId){
		orderMapper.delete(orderId,bizId);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateOrder(AirTicketOrder airTicketOrder){
		orderMapper.update(airTicketOrder);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer saveOrderList(List<AirTicketOrder> orderList){
		Integer lastId=0;
		for (int i = 0; i < orderList.size(); i++) {
			lastId = this.saveOrder(orderList.get(i));
		}
		return lastId;
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteRequestOrder(Integer requestId,Integer bizId){
		orderMapper.deleteRequest(requestId,bizId);
	}

	@Override
	public List<AirTicketOrder> findOrderList(Integer requestId,Integer bizId){
		return orderMapper.findOrderList(requestId,bizId);
	}
}
