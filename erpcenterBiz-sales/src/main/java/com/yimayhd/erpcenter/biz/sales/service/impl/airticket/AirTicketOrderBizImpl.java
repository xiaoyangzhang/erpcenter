package com.yimayhd.erpcenter.biz.sales.service.impl.airticket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yimayhd.erpcenter.biz.sales.client.service.airticket.AirTicketOrderBiz;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketOrder;
import com.yimayhd.erpcenter.dal.sales.client.airticket.service.AirTicketOrderDal;

public class AirTicketOrderBizImpl implements AirTicketOrderBiz {
	
	@Autowired
	private AirTicketOrderDal airTicketOrderDal;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer saveOrder(AirTicketOrder airTicketOrder) {
		return airTicketOrderDal.saveOrder(airTicketOrder);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteOrder(Integer orderId,Integer bizId){
		airTicketOrderDal.deleteOrder(orderId,bizId);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateOrder(AirTicketOrder airTicketOrder){
		airTicketOrderDal.updateOrder(airTicketOrder);
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
		airTicketOrderDal.deleteRequestOrder(requestId,bizId);
	}

	@Override
	public List<AirTicketOrder> findOrderList(Integer requestId,Integer bizId){
		return airTicketOrderDal.findOrderList(requestId,bizId);
	}
}
