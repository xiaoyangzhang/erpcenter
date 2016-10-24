package com.yimayhd.erpcenter.dal.sales.airticket.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.airticket.dao.AirTicketOrderMapper;
import com.yimayhd.erpcenter.dal.sales.airticket.dao.AirTicketRequestMapper;
import com.yimayhd.erpcenter.dal.sales.airticket.dao.AirTicketResourceMapper;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketOrder;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketRequest;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketResource;
import com.yimayhd.erpcenter.dal.sales.client.airticket.service.AirTicketRequestDal;
import com.yimayhd.erpcenter.dal.sales.client.airticket.service.AirTicketResourceDal;

public class AirTicketRequestDalImpl implements AirTicketRequestDal{
	
	@Autowired
	private AirTicketRequestMapper requestMapper;
	@Autowired
	private AirTicketResourceMapper resourceMapper;
	@Autowired
	private AirTicketOrderMapper orderMapper;
	@Autowired
	private AirTicketResourceDal resourceDal;
	
	@Override
	@Transactional
	public boolean checkAvailable(Integer bizId, Integer resourceId, Integer requestId, Integer requestTicketNumber){
		Integer availableTickets = resourceDal.findResource(resourceId, bizId).getAvailableNumber();
		if (requestId==null){ // new request
			if (requestTicketNumber > availableTickets){
				return false;
			}
		}else {
			Integer curSize = orderMapper.findOrderList(requestId, bizId).size();
			if (requestTicketNumber-curSize > availableTickets){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public Integer saveRequest(AirTicketRequest airTicketRequest) {
		String comment = this.getPreComment(airTicketRequest.getCreaterName(), 
				airTicketRequest.getStatus(), airTicketRequest.getComment());
		airTicketRequest.setComment(comment);
		requestMapper.insert(airTicketRequest);
		return airTicketRequest.getId();
	}
	@Override
	@Transactional
	public void deleteRequest(Integer requestId,Integer bizId, Integer opId, String opName, String comment){
		//关联删除订单
		orderMapper.deleteRequest(requestId, bizId);
		//删除申请
		comment = this.getPreComment(opName, "DELETE", comment);
		requestMapper.delete(requestId,bizId, opId, opName, comment);
	}
	
	@Override
	@Transactional
	public void updateRequest(AirTicketRequest airTicketRequest){
		String comment = this.getPreComment(airTicketRequest.getOperatorName(), 
				airTicketRequest.getStatus(), airTicketRequest.getComment()); 
		airTicketRequest.setComment(comment);
		requestMapper.update(airTicketRequest);
	}
	@Override
	@Transactional
	public AirTicketRequest findRequest(Integer requestId,Integer bizId){
		AirTicketRequest request = requestMapper.findRequest(requestId, bizId);
		if (request==null){return null;}
		List<AirTicketOrder> orderList = orderMapper.findOrderList(requestId, bizId);
		AirTicketResource resource = resourceMapper.findResource(request.getResourceId(), bizId);
		request.setResource(resource);
		request.setOrderList(orderList);
		return request;
	}
	@Override
	public PageBean<AirTicketRequest> selectRequestListPage(PageBean<AirTicketRequest> pageBean){
		@SuppressWarnings("unchecked")
		Map<String, Object> param =  (Map<String, Object>)pageBean.getParameter();
		List<AirTicketRequest> list;
		if (param.size()==1){ // no filter criteria, only bizId=xxx
			list = requestMapper.selectRequestListPage(pageBean);
		}else { // with filter criteria, need join with group_order table.
			list = requestMapper.selectRequestListPageWithGroupOrder(pageBean);
		}
		/*for(int i=0; i<list.size(); i++){
			List<AirTicketOrder> orderList = orderMapper.findOrderList(list.get(i).getId(), list.get(i).getBizId());
			list.get(i).setOrderList(orderList);
		}*/
		pageBean.setResult(list);
		return pageBean;
	}
	
	@Override
	public HashMap<String, Integer> countRequestList(PageBean<AirTicketRequest> pageBean){
		HashMap<String, Integer> countApplied = requestMapper.countAppliedTickets(pageBean);
		HashMap<String, Integer> countAvailable = requestMapper.countAvailableTickets(pageBean);
		if (countAvailable!=null){
			countAvailable.putAll(countApplied);
			return countAvailable;
		}else {
			HashMap<String, Integer> count = new HashMap<String, Integer>();
			count.put("total", 0);
			count.put("available", 0);
			count.put("applied", 0);
			return count;
		}
		
	}
	
	
	@Override
	@Transactional
	public Integer setStatus(Integer requestId,Integer bizId, String status, Integer opId, String opName, String comment){
		String remark = comment;
		String opLog = this.getPreComment(opName, status, comment);
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("requestId", requestId);
		param.put("bizId", bizId);
		param.put("status", status);
		param.put("opId", opId);
		param.put("opName", opName);
		param.put("opLog", opLog);
		param.put("remark", remark);
		int ret = requestMapper.setStatus(param);
		return ret;
	}
	
	private String getPreComment(String opName, String status, String comment){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		StringBuffer pre_comment = new StringBuffer(sdf.format(new Date()));
		pre_comment.append("  ").append(opName);
		if (status.equals("ARRANGING")){
			pre_comment.append("提出申请");
		}else if (status.equals("CONFIRMING")){
			pre_comment.append("进行安排");
		}else if (status.equals("REJECTED")){
			pre_comment.append("拒绝申请");
		}else if (status.equals("ISSUING")){
			pre_comment.append("核对申请");
		}else if (status.equals("ISSUED")){
			pre_comment.append("变更状态为出票");
		}else if (status.equals("DELETE")){
			pre_comment.append("删除申请");
		}
		if (comment==null){
			comment = "";
		}
		comment = comment.trim();
		if (!comment.equals("")) {
			comment = comment + "\n";
		}
		return pre_comment.append("\n").append(comment).toString();
	}
	
	public Integer countRequestByResource(Integer resourceId, Integer bizId){
		return requestMapper.countRequestByResource(resourceId, bizId);
	}
	
	@Override
	public List<AirTicketRequest> findRequestsByResource(Integer resourceId, Integer bizId){
		
		List<Integer> requestIds = requestMapper.findRequestsByResource(resourceId, bizId);
		List<AirTicketRequest> requestList = new ArrayList<AirTicketRequest>();
		for(int i=0; i<requestIds.size(); i++){
			Integer requestId = requestIds.get(i);
			AirTicketRequest request = requestMapper.findRequest(requestId, bizId);
			List<AirTicketOrder> orderList = orderMapper.findOrderList(requestId, bizId);
			request.setOrderList(orderList);
			requestList.add(request);
		}
		return requestList;
	}
	
	@Override
	public Integer updateBookingSupplierId(Integer requestId,Integer bizId, Integer bookingSupplierId){
		return requestMapper.updateBookingSupplierId(requestId, bizId, bookingSupplierId);
	}
	
	@Override
	public Boolean doesOrderhaveRequested(Integer bizId, Integer orderId){
		List<Integer> requestIds = requestMapper.selectRequestIdByOrderId(bizId, orderId);
		if (requestIds==null || requestIds.size()==0){
			return false;
		}else {
			return true;
		}
	}
	
	@Override
	public Map<Integer, List<AirTicketRequest>> findRequestListForGroupOrder(Integer bizId, List<Integer>orderIdList){
		HashMap<Integer, List<AirTicketRequest>> ret = new HashMap<Integer, List<AirTicketRequest>>();
		if (orderIdList.size()==0){
			return ret;
		}
		String strOrderIds = StringUtils.join(orderIdList, ',');
		List<AirTicketRequest> requestList = requestMapper.findRequestsByGroupOrderId(bizId, strOrderIds);
		for (AirTicketRequest q : requestList){
			Integer orderId = q.getGroupOrderId();
			q.setResource(resourceMapper.findResource(q.getResourceId(), bizId));
			if (ret.containsKey(orderId)){
				ret.get(orderId).add(q);
			}else {
				ArrayList<AirTicketRequest> l = new ArrayList<AirTicketRequest>();
				l.add(q);
				ret.put(orderId, l);
			}
		}
		return ret;
	}
	
	@Override
	public List<Integer> findIssuedGuestIdList(Integer biz_id, Integer orderId){
		return orderMapper.findIssuedGuestIdList(biz_id, orderId);
	}
}
