package com.yimayhd.erpcenter.biz.sales.service.impl.airticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.airticket.AirTicketRequestBiz;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketRequest;
import com.yimayhd.erpcenter.dal.sales.client.airticket.service.AirTicketRequestDal;

public class AirTicketRequestBizImpl implements AirTicketRequestBiz{
	
	@Autowired
	private AirTicketRequestDal airTicketRequestDal;
	
	@Override
	@Transactional
	public boolean checkAvailable(Integer bizId, Integer resourceId, Integer requestId, Integer requestTicketNumber){
		return airTicketRequestDal.checkAvailable(bizId, resourceId, requestId, requestTicketNumber);
	}
	
	@Override
	public Integer saveRequest(AirTicketRequest airTicketRequest) {
		return airTicketRequestDal.saveRequest(airTicketRequest);
	}
	@Override
	@Transactional
	public void deleteRequest(Integer requestId,Integer bizId, Integer opId, String opName, String comment){
		airTicketRequestDal.deleteRequest(requestId, bizId, opId, opName, comment);
	}
	
	@Override
	@Transactional
	public void updateRequest(AirTicketRequest airTicketRequest){
		airTicketRequestDal.updateRequest(airTicketRequest);
	}
	@Override
	@Transactional
	public AirTicketRequest findRequest(Integer requestId,Integer bizId){
		return airTicketRequestDal.findRequest(requestId, bizId);
	}
	@Override
	public PageBean<AirTicketRequest> selectRequestListPage(PageBean<AirTicketRequest> pageBean){
		return airTicketRequestDal.selectRequestListPage(pageBean);
	}
	
	@Override
	public HashMap<String, Integer> countRequestList(PageBean<AirTicketRequest> pageBean){
		return airTicketRequestDal.countRequestList(pageBean);
	}
	
	@Override
	@Transactional
	public Integer setStatus(Integer requestId,Integer bizId, String status, Integer opId, String opName, String comment){
		return airTicketRequestDal.setStatus(requestId, bizId, status, opId, opName, comment);
	}
	
	public Integer countRequestByResource(Integer resourceId, Integer bizId){
		return airTicketRequestDal.countRequestByResource(resourceId, bizId);
	}
	
	@Override
	public List<AirTicketRequest> findRequestsByResource(Integer resourceId, Integer bizId){
		return airTicketRequestDal.findRequestsByResource(resourceId, bizId);
	}
	
	@Override
	public Integer updateBookingSupplierId(Integer requestId,Integer bizId, Integer bookingSupplierId){
		return airTicketRequestDal.updateBookingSupplierId(requestId, bizId, bookingSupplierId);
	}
	
	@Override
	public Boolean doesOrderhaveRequested(Integer bizId, Integer orderId){
		return airTicketRequestDal.doesOrderhaveRequested(bizId, orderId);
	}
	
	@Override
	public Map<Integer, List<AirTicketRequest>> findRequestListForGroupOrder(Integer bizId, List<Integer>orderIdList){
		return airTicketRequestDal.findRequestListForGroupOrder(bizId, orderIdList);
	}
	
	@Override
	public List<Integer> findIssuedGuestIdList(Integer biz_id, Integer orderId){
		return airTicketRequestDal.findIssuedGuestIdList(biz_id, orderId);
	}
}
