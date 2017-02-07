package com.yimayhd.erpcenter.facade.ticket.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.airticket.AirTicketOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.airticket.AirTicketRequestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.airticket.AirTicketResourceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderTransportBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.airticket.bo.AirTicketRequestBO;
import com.yimayhd.erpcenter.dal.sales.client.airticket.bo.AirTicketResourceBO;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketLeg;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketOrder;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketRequest;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketResource;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.ticket.errorcode.TicketErrorCode;
import com.yimayhd.erpcenter.facade.ticket.query.ArrangeDTO;
import com.yimayhd.erpcenter.facade.ticket.query.ConfirmDTO;
import com.yimayhd.erpcenter.facade.ticket.query.DeleteDTO;
import com.yimayhd.erpcenter.facade.ticket.query.GetResourceBoDTO;
import com.yimayhd.erpcenter.facade.ticket.query.IssueDTO;
import com.yimayhd.erpcenter.facade.ticket.query.RejectDTO;
import com.yimayhd.erpcenter.facade.ticket.query.SaveBookingSupplierDTO;
import com.yimayhd.erpcenter.facade.ticket.query.SaveDTO;
import com.yimayhd.erpcenter.facade.ticket.query.SavePickUpDTO;
import com.yimayhd.erpcenter.facade.ticket.query.ShowApplyListDTO;
import com.yimayhd.erpcenter.facade.ticket.query.ShowGroupOrderDTO;
import com.yimayhd.erpcenter.facade.ticket.query.ShowListDTO;
import com.yimayhd.erpcenter.facade.ticket.query.ShowResourceDTO;
import com.yimayhd.erpcenter.facade.ticket.result.EditRequestResult;
import com.yimayhd.erpcenter.facade.ticket.result.GetResourceBoResult;
import com.yimayhd.erpcenter.facade.ticket.result.PickUpGuestResult;
import com.yimayhd.erpcenter.facade.ticket.result.ResultSupport;
import com.yimayhd.erpcenter.facade.ticket.result.ShowApplyEditResult;
import com.yimayhd.erpcenter.facade.ticket.result.ShowApplyListResult;
import com.yimayhd.erpcenter.facade.ticket.result.ShowGroupOrderResult;
import com.yimayhd.erpcenter.facade.ticket.result.ShowListResult;
import com.yimayhd.erpcenter.facade.ticket.result.ShowResourceResult;
import com.yimayhd.erpcenter.facade.ticket.result.TicketListResult;
import com.yimayhd.erpcenter.facade.ticket.service.RequestFacade;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.dal.constants.Constants;
import com.yimayhd.erpresource.dal.po.SupplierInfo;

public class RequestFacadeImpl implements RequestFacade{

	@Autowired
	private AirTicketRequestBiz airTicketRequestBiz;
	
	@Autowired
	private GroupOrderBiz groupOrderBiz;
	
	@Autowired
	private AirTicketResourceBiz airTicketResourceBiz;
	
	@Autowired
	private GroupOrderGuestBiz groupOrderGuestBiz; 
	
	@Autowired
	private DicBiz dicBiz;
	
	@Autowired
	private GroupOrderTransportBiz groupOrderTransportBiz;
	

	@Autowired
	private SupplierBiz    supplierBiz;
	
	@Autowired
	private TourGroupBiz tourGroupBiz;
	
	@Autowired
	private AirTicketOrderBiz airTicketOrderBiz;
	
	@Autowired
	private BookingSupplierDetailBiz bookingSupplierDetailBiz;
	
	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;
	@Autowired
	private BookingSupplierBiz bookingSupplierBiz;



	@Override
	public ShowListResult showList(ShowListDTO dto) {
		
		String dateFrom="",  dateTo="", dateType="";
		if (dto.getDateFrom() == null && dto.getDateTo() == null){
			Calendar cal = Calendar.getInstance(); 
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
			cal.set( Calendar.DATE, 1 );
			cal.roll(Calendar.DATE, - 1 );
			dateTo=sdf.format(cal.getTime());
			cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
			dateFrom=sdf.format(cal.getTime());
			dateType = "start";
		}else {
			dateFrom = dto.getDateFrom();
			dateTo = dto.getDateTo();
			dateType = dto.getDateType();
		}
		String productName="", lineName="", contactName="", orderNo="", endIssueDateFrom="", endIssueDateTo="", saleName = "", type;
		try {
			productName = dto.getProductName();
			saleName = dto.getSaleName();
			lineName = dto.getLineName();
			contactName = dto.getContactName();
			endIssueDateFrom = dto.getEndIssueDateFrom();
			endIssueDateTo = dto.getEndIssueDateTo();
			orderNo = dto.getOrderNo();
			dateFrom = dateFrom==null?"":dateFrom;
			dateTo = dateTo==null?"":dateTo;
			productName = productName==null?"":productName;
			lineName = lineName==null?"":lineName;
			saleName = saleName==null?"":saleName;
			contactName = contactName==null?"":contactName;
			orderNo = orderNo==null?"":orderNo;
		}catch(Exception e){
			e.printStackTrace();
		}

		Integer bizId = dto.getBizId();
		PageBean<AirTicketRequest> pageBean = new PageBean<AirTicketRequest>();
		pageBean.setPageSize(dto.getPageSize());
		pageBean.setPage(dto.getPage());
		
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("bizId", bizId);
		parameter.put("dateFrom", dateFrom);
		parameter.put("dateTo", dateTo);
		parameter.put("dateType", dateType);
		parameter.put("productName", productName);
		parameter.put("lineName", lineName);
		parameter.put("saleName", saleName);
		parameter.put("contactName", contactName);
		parameter.put("orderNo", orderNo);
		parameter.put("receiveMode", dto.getReceiveMode());
		parameter.put("issueStatus", dto.getIssueStatus());
		parameter.put("depCity", dto.getDepCity());
		parameter.put("type", dto.getType());
		if (!dto.isArrange()){
			parameter.put("dataUser", dto.getDataUser());
		}
		if (endIssueDateFrom!=null){
			parameter.put("endIssueDateFrom", endIssueDateFrom);
			parameter.put("endIssueTimeFrom", endIssueDateFrom+" 00:00");
		}
		if (endIssueDateTo!=null){
			parameter.put("endIssueDateTo", endIssueDateTo);
			parameter.put("endIssueTimeTo", endIssueDateTo +" 24:00");
		}
		pageBean.setParameter(parameter);
		HashMap<String, Integer> count = airTicketRequestBiz.countRequestList(pageBean);
		pageBean = airTicketRequestBiz.selectRequestListPage(pageBean);
		ArrayList<AirTicketRequestBO> resultBo = new ArrayList<AirTicketRequestBO>();
		
		HashSet<Integer> resourceIdSet = new HashSet<Integer>();
		HashSet<Integer> groupOrderIdSet = new HashSet<Integer>();
		for(AirTicketRequest q : pageBean.getResult()){
			resourceIdSet.add(q.getResourceId());
			groupOrderIdSet.add(q.getGroupOrderId());
		}
		Map<Integer,GroupOrder> orderMap = groupOrderBiz.findOrderByIdList(bizId, new ArrayList<Integer>(groupOrderIdSet));
		Map<Integer,AirTicketResource> resourceMap = airTicketResourceBiz.findResourceByIdList(bizId, new ArrayList<Integer>(resourceIdSet));
		Map<Integer, List<AirTicketLeg>> legsMap = airTicketResourceBiz.findLegsByResourceIdList(new ArrayList<Integer>(resourceIdSet));
		
		for(int i=0; i<pageBean.getResult().size(); i++){
			AirTicketRequest po = pageBean.getResult().get(i);
			AirTicketRequestBO bo = new AirTicketRequestBO(po);
			bo.setArrange(dto.isArrange());
			AirTicketResource resourcePo = resourceMap.get(po.getResourceId());
			AirTicketResourceBO resourceBo = new AirTicketResourceBO(resourcePo);
			resourceBo.setLegList(legsMap.get(resourcePo.getId()));
			bo.setResource(resourcePo);
			bo.setGroupOrder(orderMap.get(po.getGroupOrderId()));
			//AirTicketResourceBO resoruceBo = getResoruceBo(request, model, po.getResourceId());
			bo.setResourceBo(resourceBo);
			resultBo.add(bo);
		}
		
		ShowListResult result = new ShowListResult();
		result.setCount(count);
		result.setPageBean(pageBean);
		result.setResultBo(resultBo);
		
		return result;
	}

	@Override
	public GetResourceBoResult getResoruceBo(GetResourceBoDTO dto) {
		
		AirTicketRequest po = airTicketRequestBiz.findRequest(dto.getId(), dto.getBizId());
		AirTicketRequestBO bo = new AirTicketRequestBO(po);
		bo.setResource(airTicketResourceBiz.findResource(po.getResourceId(), dto.getBizId()));
		bo.setGroupOrder(groupOrderBiz.findById(po.getGroupOrderId()));

		AirTicketResourceBO resourceBo = new AirTicketResourceBO(po.getResource());
		resourceBo.setLegList(airTicketResourceBiz.findLegsByResourceId(resourceBo.getPo().getId()));
		List<GroupOrderGuest> groupGuestList = groupOrderGuestBiz.selectByOrderId(po.getGroupOrderId());
		if(po.getBookingSupplierId() !=null){
			BookingSupplier booking = bookingSupplierBiz.selectByPrimaryKey(po.getBookingSupplierId());
			bo.getGroupOrder().setStateFinance(booking.getStateFinance());
		}
		GetResourceBoResult result = new GetResourceBoResult();
		result.setBo(bo);
		result.setGroupGuestList(groupGuestList);
		result.setResourceBo(resourceBo);
		return result;
	}

	@Override
	public TicketListResult ticketList(Integer bizId, Integer id) {
		
		AirTicketResource resourcePo = airTicketResourceBiz.findResource(id, bizId);
		AirTicketResourceBO resourceBo = new AirTicketResourceBO(resourcePo);
		resourceBo.setLegList(airTicketResourceBiz.findLegsByResourceId(resourceBo.getPo().getId()));
		List<AirTicketRequest> requestList = airTicketRequestBiz.findRequestsByResource(id, bizId);
		List<AirTicketRequestBO> boList = new ArrayList<AirTicketRequestBO>();
		for (int i=0; i<requestList.size(); i++){
			AirTicketRequest po = requestList.get(i);
			po.setResource(resourcePo);
			AirTicketRequestBO bo = new AirTicketRequestBO(po);
			bo.setGroupOrder(groupOrderBiz.findById(po.getGroupOrderId()));
			bo.go.setGroupOrderGuestList(groupOrderGuestBiz.selectByOrderId(po.getGroupOrderId())); 
			boList.add(bo);
		}
		
		TicketListResult result = new TicketListResult();
		result.setResourceBo(resourceBo);
		result.setBoList(boList);
		return result;
	}

	@Override
	public EditRequestResult editRequest(Integer bizId, Integer id) {
		
		AirTicketRequest po = airTicketRequestBiz.findRequest(id, bizId);
		AirTicketRequestBO bo = new AirTicketRequestBO(po);
		bo.setResource(airTicketResourceBiz.findResource(po.getResourceId(), bizId));
		bo.setGroupOrder(groupOrderBiz.findById(po.getGroupOrderId()));

		AirTicketResourceBO resourceBo = new AirTicketResourceBO(po.getResource());
		resourceBo.setLegList(airTicketResourceBiz.findLegsByResourceId(resourceBo.getPo().getId()));
		List<GroupOrderGuest> groupGuestList = groupOrderGuestBiz.selectByOrderId(po.getGroupOrderId());
		ArrayList<Boolean> inTicketList= new ArrayList<Boolean>(); // 用于记录客户是否在订单里
		for(int i=0; i<groupGuestList.size(); i++){
			if (bo.isGuestInRequest(groupGuestList.get(i).getId())){
				inTicketList.add(true);
			}else {
				inTicketList.add(false);
			}
		}
		
		EditRequestResult result = new EditRequestResult();
		result.setBo(bo);
		result.setResourceBo(resourceBo);
		result.setGroupGuestList(groupGuestList);
		result.setInTicketList(inTicketList);
		
		return result;
	}
	
	private int getPickUpMethodAir(int bizId){
		Integer methodAir = 154; // 交通方式：飞机 
		List<DicInfo> transportTypeList = dicBiz.getListByTypeCode(BasicConstants.GYXX_JTFS,bizId);
		for (DicInfo d: transportTypeList){
			if (d.getValue().toString().equals("飞机")){methodAir=d.getId(); break;}
		}
		return methodAir;
	}

	/* 添加接送机信息  */
	@Override
	public PickUpGuestResult pickUpGuest(Integer bizId, Integer id) {
		AirTicketRequest po = airTicketRequestBiz.findRequest(id, bizId);
		AirTicketRequestBO bo = new AirTicketRequestBO(po);
		bo.setResource(airTicketResourceBiz.findResource(po.getResourceId(), bizId));
		bo.setGroupOrder(groupOrderBiz.findById(po.getGroupOrderId()));
		AirTicketResourceBO resourceBo = new AirTicketResourceBO(po.getResource());
		resourceBo.setLegList(airTicketResourceBiz.findLegsByResourceId(resourceBo.getPo().getId()));
		List<GroupOrderTransport> existingTransport = groupOrderTransportBiz.selectByOrderId(po.getGroupOrderId());
		List<GroupOrderTransport> existingAir = new ArrayList<GroupOrderTransport>();
		Integer methodAir = this.getPickUpMethodAir(bizId);
		for(GroupOrderTransport t: existingTransport){
			if (t.getMethod()==null){continue;}
			if (t.getMethod().equals(methodAir)){
				existingAir.add(t);
			}
		}
		
		PickUpGuestResult result = new PickUpGuestResult();
		result.setBo(bo);
		result.setResourceBo(resourceBo);
		result.setExistingTransport(JSONArray.toJSONString(existingAir));
		
		return result;
	}

	@Override
	public BookingSupplier selectByPrimaryKey(Integer bookingId) {
		BookingSupplier supplier = bookingSupplierBiz.selectByPrimaryKey(bookingId);
		return supplier;
	}
	@Override
	public String showResourceInfo(Integer resourceId, Integer bizId) {
		try{
			if (resourceId==null){return "";}
			AirTicketResource resourcePo = airTicketResourceBiz.findResource(resourceId, bizId);
			AirTicketResourceBO resourceBo = new AirTicketResourceBO(resourcePo);
			resourceBo.setLegList(airTicketResourceBiz.findLegsByResourceId(resourceBo.getPo().getId()));
			StringBuffer sb = new StringBuffer();
			sb.append("<tr style=\"height:80px;\">");
			sb.append("<td>").append(resourceBo.getPo().getLineName()).append("</td>");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sb.append("<td>").append(sdf.format(resourceBo.getPo().getStartDate())).append("</td>");
			sb.append("<td>").append(resourceBo.getLegHtml()).append("</td>");
			sb.append("<td>").append(resourceBo.getTicketSupplier()).append("</td>");
			sb.append("<td>").append(resourceBo.getPrice()).append("</td>");
			sb.append("<td>").append(resourceBo.getPo().getAvailableNumber().toString()).append("</td>");
			sb.append("<td>").append(resourceBo.getEndIssueTime()).append("</td>");
			sb.append("<script>ticketPrice=\"").append(resourceBo.getPrice()).append("\";</script></tr>");
			return sb.toString();
		}catch(Exception ex){
			ex.printStackTrace();
			return "";
		}
	}

	@Override
	public String showGroupOrderInfo(Integer groupOrderId, Integer bizId) {
		
		if (groupOrderId==null){
			return "";
		}
		
		AirTicketRequest po = new AirTicketRequest();
		AirTicketRequestBO bo = new AirTicketRequestBO(po);
		GroupOrder go = groupOrderBiz.findOrderById(bizId, groupOrderId);
		go.setSupplierName(supplierBiz.selectBySupplierId(go.getSupplierId()).getNameShort());
		bo.setGroupOrder(go);
		
		StringBuffer sb = new StringBuffer();
		sb.append("<tr style=\"height:80px;\">");
		sb.append("<td>").append(go.getOrderNo()).append("</td>");
		sb.append("<td>").append(go.getDepartureDate()).append("</td>");
		sb.append("<td>").append("【"+go.getProductBrandName()+"】"+go.getProductName()).append("</td>");
		sb.append("<td>").append(go.getSupplierName()).append("</td>");
		sb.append("<td>").append(go.getReceiveMode()).append("</td>");
		sb.append("<td>").append(bo.getGroupGuestNumber()).append("</td></tr>");
		return sb.toString();
	}

	@Override
	public String showGroupOrderGuest(Integer groupOrderId) {
		if (groupOrderId==null){return "";}
		List<GroupOrderGuest> groupGuestList = groupOrderGuestBiz.selectByOrderId(groupOrderId);
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<groupGuestList.size(); i++){
			GroupOrderGuest guest = groupGuestList.get(i);
			sb.append("<tr style=\"height:30px;\" id=\"trGuest").append(guest.getId()).append("\">");
			sb.append("<td><input type=\"checkbox\" name=\"chkGuest\" value=\"").append(guest.getId()).append("\"></input></td>");
			sb.append("<td>").append(guest.getName()).append("</td>");
			sb.append("<td>").append(guest.getCertificateNum()).append("</td>");
			sb.append("<td>").append(guest.getMobile()).append("</td>");
			sb.append("<td>").append(guest.getRemark()==null?"":guest.getRemark()).append("</td>");
			sb.append("<td><span id='price'></span></td>").append("<td><span id='comment'></span></td></tr>");
		}
		return sb.toString();
	}

	@Override
	public ShowResourceResult showResource(ShowResourceDTO dto) {
		
		PageBean<AirTicketResource> pageBean = new PageBean<AirTicketResource>();
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("bizId", dto.getBizId());
		pageBean.setParameter(parameter);
		pageBean = airTicketResourceBiz.selectResourceListPage(pageBean);

		ArrayList<AirTicketResourceBO> boList = new ArrayList<AirTicketResourceBO>();
		for(int i=0; i<pageBean.getResult().size(); i++){
			boList.add(new AirTicketResourceBO(pageBean.getResult().get(i)));			
		}
		
		ShowResourceResult result = new ShowResourceResult();
		result.setPageBean(pageBean);
		result.setBoList(boList);

		return result;
	}

	@Override
	public ShowGroupOrderResult showGroupOrder(ShowGroupOrderDTO dto) {
		
		PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>(); 
		Integer bizId = dto.getBizId();
		//Set<Integer> userSet = WebUtils.getDataUserIdSet(request);
		pageBean.setPage(dto.getPage());
		pageBean.setPageSize(dto.getPageSize());

		HashMap<String, String> param = new HashMap<String, String>();
		param.put("departureDateFrom", dto.getDepartureDateFrom());
		param.put("departureDateTo", dto.getDepartureDateTo());
		param.put("orderNo", dto.getOrderNo());
		param.put("productName", dto.getProductName());
		param.put("receiveMode", dto.getReceiveMode());
		param.put("dataUser", dto.getDataUser()); //只取有权限看的订单
		String airApplyState = dto.getAirApplyState();
		if (airApplyState!=null && (airApplyState.equals("Y") || airApplyState.equals("N"))){
			param.put("airApplyState", airApplyState);
		}
		pageBean.setParameter(param);
		pageBean = groupOrderBiz.selectAllOrderByConListPage(pageBean, bizId);
		
		List<GroupOrder> orderList = pageBean.getResult();
		HashMap<Integer, String> groupModes = new HashMap<Integer, String>();
		HashMap<Integer, String> orderSuppliers = new HashMap<Integer, String>();
		HashMap<Integer, String> orderRequestStatus = new HashMap<Integer, String>();
		for (GroupOrder o : orderList) {
			if (o.getSupplierId() != null) {
				if (orderSuppliers.containsKey(o.getSupplierId())){
					o.setSupplierName(orderSuppliers.get(o.getSupplierId()));
				}else {
					SupplierInfo si = supplierBiz.selectBySupplierId(o.getSupplierId());
					o.setSupplierName(si.getNameShort());
				}
			}
			if (o.getGroupId()==null){
				groupModes.put(o.getId(), "散客订单");
			}else {
				TourGroup tg = tourGroupBiz.selectByPrimaryKey(o.getGroupId());
				if (tg.getGroupMode()==0){
					groupModes.put(o.getId(), "散客团");
				}else {
					groupModes.put(o.getId(), "团队");
				}
			}
			if (airTicketRequestBiz.doesOrderhaveRequested(bizId, o.getId())){
				orderRequestStatus.put(o.getId(), "已申请");
			}else{
				orderRequestStatus.put(o.getId(), "未申请");
			}
		}
		
		ShowGroupOrderResult result = new ShowGroupOrderResult();
		result.setPageBean(pageBean);
		result.setGroupModes(groupModes);
		result.setOrderRequestStatus(orderRequestStatus);
		
		return result;
	}

	@Override
	public ResultSupport save(SaveDTO dto) {
		
		ResultSupport result = new ResultSupport();
		try{
			Integer id = dto.getId();
			Integer resourceId = dto.getResourceId();
			String resourceNumber = dto.getResourceNumber();
			Integer groupOrderId = dto.getGroupOrderId();
			List<AirTicketOrder> orderList = JSON.parseArray(dto.getJsonOrders(), AirTicketOrder.class);
			
			Integer bizId = dto.getBizId();
			Integer curUserId = dto.getCurUserId();
			String curUserName = dto.getCurUserName();
			
			AirTicketRequest po = new AirTicketRequest();
			if (id!=null){po.setId(id);}
			po.setBizId(bizId);
			po.setGroupOrderId(groupOrderId);
			po.setResourceId(resourceId);
			po.setResourceNumber(resourceNumber);
			po.setGuestNumber(orderList.size());
			if (id==null){
				po.setCreaterId(curUserId);
				po.setCreaterName(curUserName);
			}else {
				po.setOperatorId(curUserId);
				po.setOperatorName(curUserName);
			}
			GroupOrder groupOrder = groupOrderBiz.findOrderById(bizId, groupOrderId);
			if (groupOrder==null || groupOrder.getState()==-1){
				throw new ClientException("订单已被删除。");
			}
			if (!airTicketRequestBiz.checkAvailable(bizId, resourceId, id, orderList.size())){
				throw new ClientException("申请票数不能超过可用票数");
			}
			Integer resourceIdBefore = null; // 为了refresh变更前的resource的applied_number;
			if (id==null){
				po.setStatus("ARRANGING");
				Integer newId = airTicketRequestBiz.saveRequest(po);
				po.setId(newId);
			}else {
				resourceIdBefore = airTicketRequestBiz.findRequest(id, bizId).getResourceId();
				po.setStatus("ARRANGING");
				airTicketRequestBiz.updateRequest(po);
			}
			
			for(int i=0; i<orderList.size(); i++){
				orderList.get(i).setBizId(bizId);
				orderList.get(i).setRequestId(po.getId());
				orderList.get(i).setResourceId(resourceId);
			}
			airTicketOrderBiz.deleteRequestOrder(po.getId(), bizId);
			airTicketOrderBiz.saveOrderList(orderList);
			if (resourceIdBefore!=null && resourceIdBefore!=po.getResourceId()){
				airTicketResourceBiz.refreshAppliedNumber(resourceIdBefore);
			}
			airTicketResourceBiz.refreshAppliedNumber(po.getResourceId());
		}catch(Exception ex){
			result.setErrorCode(TicketErrorCode.MODIFY_ERROR);
			result.setResultMsg(ex.getMessage());
			ex.printStackTrace();
		}
		return result;
	}
	
	private void refreshAppliedNumber(Integer requestId, Integer bizId){
		Integer resourceId = airTicketRequestBiz.findRequest(requestId, bizId).getResourceId();
		airTicketResourceBiz.refreshAppliedNumber(resourceId);
	}

	@Override
	public ResultSupport arrange(ArrangeDTO dto) {

		ResultSupport result = new ResultSupport();
		try{
			airTicketRequestBiz.setStatus(dto.getRequestId(), dto.getBizId(), "CONFIRMING", dto.getOpId(), dto.getOpName(), dto.getComment());
			this.refreshAppliedNumber(dto.getRequestId(), dto.getBizId());
		}catch(Exception ex){
			result.setErrorCode(TicketErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ResultSupport reject(RejectDTO dto) {
		
		ResultSupport result = new ResultSupport();
		try{
			
			Integer id = dto.getRequestId();
			Integer opId = dto.getOpId();
			String opName = dto.getOpName();
			Integer bizId = dto.getBizId();
			AirTicketRequest po = airTicketRequestBiz.findRequest(id, bizId);
			BookingSupplier booking = bookingSupplierBiz.selectByPrimaryKey(po.getBookingSupplierId());
			if (booking!=null && booking.getStateFinance()>=1){ // 如果订单已审核，则禁止撤回。
				throw new ClientException("订单已被财务审核，退回需要先取消审核。");
			}
			if (po.getBookingSupplierId()!=null ){
				BookingSupplier bs = bookingSupplierBiz.selectByPrimaryKey(po.getBookingSupplierId());
				if (bs!=null){
					bookingSupplierBiz.deleteSupplierWithFinanceByPrimaryKey(po.getBookingSupplierId(), false);
				}
				airTicketRequestBiz.updateBookingSupplierId(id, bizId, null);
			}
			airTicketRequestBiz.setStatus(id, bizId, "REJECTED", opId, opName, dto.getComment());
			
			List<GroupOrderTransport> existingTransport = groupOrderTransportBiz.selectByOrderId(po.getGroupOrderId());
			for (GroupOrderTransport e : existingTransport){
				if (e.getMethod()!=null && e.getMethod().equals(this.getPickUpMethodAir(bizId))){
					groupOrderTransportBiz.deleteByPrimaryKey(e.getId());
				}
			}
			this.refreshAppliedNumber(id, bizId);
			
		}catch(Exception ex){
			result.setErrorCode(TicketErrorCode.MODIFY_ERROR);
			result.setResultMsg(ex.getMessage());
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport confirm(ConfirmDTO dto) {
		
		ResultSupport result = new ResultSupport();
		try{
			airTicketRequestBiz.setStatus(dto.getRequestId(), dto.getBizId(), "ISSUING", dto.getOpId(), dto.getOpName(), dto.getComment());
			this.refreshAppliedNumber(dto.getRequestId(), dto.getBizId());
		}catch(Exception ex){
			result.setErrorCode(TicketErrorCode.MODIFY_ERROR);
			result.setResultMsg(ex.getMessage());
			ex.printStackTrace();
		}
		return result;
	}
	
	@Override
	public ResultSupport issue(IssueDTO dto) {
		
		ResultSupport result = new ResultSupport();
		try{
			airTicketRequestBiz.setStatus(dto.getRequestId(), dto.getBizId(), "ISSUED", dto.getOpId(), dto.getOpName(), dto.getComment());
			this.saveBookingSupplier(dto.getRequestId(), dto.getOpId(), dto.getOpName(), dto.getBizId());
			this.refreshAppliedNumber(dto.getRequestId(), dto.getBizId());
		}catch(Exception ex){
			result.setErrorCode(TicketErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		
		return result;
	}
	
	private String saveBookingSupplier(Integer requestId, Integer operatorId, String operatorName, Integer bizId){
		AirTicketRequest po = airTicketRequestBiz.findRequest(requestId, bizId);
		AirTicketResource resource = airTicketResourceBiz.findResource(po.getResourceId(), bizId);
		List<AirTicketLeg> legList = airTicketResourceBiz.findLegsByResourceId(po.getResourceId());
		GroupOrder go = groupOrderBiz.findById(po.getGroupOrderId());

		BookingSupplier bs = new BookingSupplier();
		bs.setStateBooking(0);
		bs.setStateFinance(0);
		if (resource.getType().equals("TRAIN")){
			bs.setSupplierType(Constants.TRAINTICKETAGENT);
		}else {
			bs.setSupplierType(Constants.AIRTICKETAGENT);
		}
		if(go.getGroupId()!=null){
			bs.setGroupId(go.getTourGroup().getId());
		}
		bs.setOrderId(go.getId());
		bs.setUserId(operatorId);
		bs.setUserName(operatorName);
		bs.setSupplierId(resource.getTicketSupplierId());
		bs.setSupplierName(resource.getTicketSupplier());
		bs.setContact(resource.getContact());
		bs.setContactTel(resource.getContactTel());
		bs.setContactMobile(resource.getContactMobile());
		bs.setContactFax(resource.getContactFax());
		//bs.setCashType(po.getPaymentType());
		bs.setCashType("公司现付"); //2016-5-25 欧宗莹更改，由于　po里该字段永远是null,根据业务要求出票１００%是公司现付
		//bs.setRemark(go.getTourGroup().getRemark());
		
		int count = bookingSupplierBiz.getBookingCountByTypeAndTime(bs.getSupplierType());
		bs.setBookingNo("YM"+Constants.SUPPLIERSHORTCODEMAP.get(bs.getSupplierType())
				+new SimpleDateFormat("yyMMdd").format(new Date())+(count+100));
		bs.setBookingDate(new Date());
		bs.setCreateTime(new Date().getTime());
		
		ArrayList<BookingSupplierDetail> bsdList = new ArrayList<BookingSupplierDetail>();
		HashMap<BigDecimal, Double> priceMap = new HashMap<BigDecimal, Double>();
		for (AirTicketOrder order : po.getOrderList()){
			BigDecimal price = order.getPrice();
			if (priceMap.containsKey(price)){
				priceMap.put(price, priceMap.get(price)+1.0);
			}else {
				priceMap.put(price, 1.0);
			}
		}
		for (BigDecimal price : priceMap.keySet()){
			BookingSupplierDetail bsd = new BookingSupplierDetail();
			bsd.setType1Name(resource.getSeatType());
			bsd.setTicketDeparture(resource.getDepCity());
			bsd.setTicketArrival(legList.get(legList.size()-1).getArrCity());
			bsd.setItemDate(resource.getDepDate());
			bsd.setTicketFlight(resource.getLineName());
			bsd.setItemPrice(price.doubleValue());
			bsd.setItemNum(priceMap.get(price));
			bsd.setItemNumMinus(0.0);
			bsd.setItemTotal(price.doubleValue() * priceMap.get(price));
			bsdList.add(bsd);
		}
		bs.setDetailList(bsdList);
		
		double priceSum=0.0;
		for (BookingSupplierDetail detail : bsdList) {
			priceSum=priceSum+detail.getItemTotal();
		}
		bs.setTotal(new BigDecimal(priceSum));
		
		int bsId=bookingSupplierBiz.insert(bs);
		for (BookingSupplierDetail detail : bsdList) {
			detail.setBookingId(bsId);
			bookingSupplierDetailBiz.insert(detail);
		}
		airTicketRequestBiz.updateBookingSupplierId(po.getId(), bizId, bsId);
		return null;
	}

	@Override
	public ResultSupport delete(DeleteDTO dto) {
		
		ResultSupport result = new ResultSupport();
		try{
			Integer resourceId = airTicketRequestBiz.findRequest(dto.getRequestId(), dto.getBizId()).getResourceId();
			airTicketRequestBiz.deleteRequest(dto.getRequestId(), dto.getBizId(), dto.getOpId(), dto.getOpName(), dto.getComment());
			airTicketOrderBiz.deleteRequestOrder(dto.getRequestId(), dto.getBizId());
			airTicketResourceBiz.refreshAppliedNumber(resourceId);
		}catch(Exception ex){
			result.setErrorCode(TicketErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport changePrice(Integer orderId, BigDecimal price) {
		ResultSupport result = new ResultSupport();
		try{
			AirTicketOrder o = new AirTicketOrder();
			o.setId(orderId);
			o.setPrice(price);
			airTicketOrderBiz.updateOrder(o);
		}catch(Exception ex){
			result.setErrorCode(TicketErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport changePrices(String orderIds, BigDecimal price) {
		ResultSupport result = new ResultSupport();
		try{
			orderIds = StringUtils.trim(orderIds);
			String[] ids = StringUtils.split(orderIds, ',');
			for (String id: ids){
				AirTicketOrder o = new AirTicketOrder();
				o.setId(Integer.parseInt(id));
				o.setPrice(price);
				airTicketOrderBiz.updateOrder(o);
			}
		}catch(Exception ex){
			result.setErrorCode(TicketErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSupport savePickUp(SavePickUpDTO dto) {
		ResultSupport result = new ResultSupport();
		try{
			Integer orderId = dto.getOrderId();
			Integer resourceId = dto.getResourceId();
			String jsonString = dto.getJsonString();
			List<GroupOrderTransport> list = JSON.parseArray(jsonString, GroupOrderTransport.class);
			Integer methodAir = this.getPickUpMethodAir(dto.getBizId()); // 交通方式：飞机 
			// 先删除已存的接送飞机信息
			//TODO 一个订单可能会有多次资源申请
			List<GroupOrderTransport> existingTransport = groupOrderTransportBiz.selectByOrderId(orderId);
			List<AirTicketLeg> legList = airTicketResourceBiz.findLegsByResourceId(resourceId);
			for (GroupOrderTransport e : existingTransport){
				if (e.getMethod()!=null && e.getMethod().equals(methodAir) && isTransportInLeg(e, legList)){
					groupOrderTransportBiz.deleteByPrimaryKey(e.getId());
				}
			}
			// 添加新的接送飞机信息
			for (GroupOrderTransport t: list){
				t.setOrderId(orderId);
				t.setMethod(methodAir);
				groupOrderTransportBiz.insertSelective(t);
			}
		}catch(Exception ex){
			result.setErrorCode(TicketErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}
	
	public boolean isTransportInLeg(GroupOrderTransport e, List<AirTicketLeg> legList){ // 判断接送机信息是否在机票资源的航班中，在的话先删除再添加
		for (AirTicketLeg leg: legList){
			if (e.getDepartureDate()!=null && e.getClassNo()!=null && 
					e.getDepartureDate().equals(leg.getDepDate()) && e.getClassNo().trim().equals(leg.getAirCode())){
				return true;
			}
		}
		return false;
	}

	@Override
	public ResultSupport changeGuestComment(Integer orderId, String comment) {
		ResultSupport result = new ResultSupport();
		try{
			AirTicketOrder o = new AirTicketOrder();
			o.setId(orderId);
			o.setComment(comment);
			airTicketOrderBiz.updateOrder(o);
		}catch(Exception ex){
			result.setErrorCode(TicketErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public Map<Integer, String> getTicketSuppliers(Integer bizId) {
		
		Map<Integer, String> ticketSuppliers=new HashMap<Integer, String>();
		SupplierInfo supplierInfo = new SupplierInfo();
		supplierInfo.setSupplierType(Constants.AIRTICKETAGENT);
		PageBean<SupplierInfo> pageBean = new PageBean<SupplierInfo>();
		pageBean.setPageSize(1000);
		pageBean.setParameter(supplierInfo);
		pageBean.setPage(1);
		pageBean = supplierBiz.selectAllSupplierListPage(pageBean, bizId);
		for (int i=0; i<pageBean.getResult().size(); i++){
			SupplierInfo s = pageBean.getResult().get(i);
			ticketSuppliers.put(s.getId(), s.getNameFull());
		}
		return ticketSuppliers;
	}

	@Override
	public ShowApplyEditResult showApplyEdit(Integer bizId, Integer orderId) {
		GroupOrder order = groupOrderBiz.findById(orderId);
		List<GroupOrderGuest> guestList = groupOrderGuestBiz.selectByOrderId(order.getId());
		ShowApplyEditResult result = new ShowApplyEditResult();
		result.setOrder(order);
		result.setGuestList(guestList);
		return result;
	}

	@Override
	public ResultSupport saveBookingSupplier(SaveBookingSupplierDTO dto) {
		
		ResultSupport result = new ResultSupport();
		try{
			Integer bizId = dto.getBizId();
			Integer operatorId = dto.getOperatorId();
			String operatorName = dto.getOperatorName();
			AirTicketRequest po = airTicketRequestBiz.findRequest(dto.getRequestId(), bizId);
			AirTicketResource resource = airTicketResourceBiz.findResource(po.getResourceId(), bizId);
			List<AirTicketLeg> legList = airTicketResourceBiz.findLegsByResourceId(po.getResourceId());
			GroupOrder go = groupOrderBiz.findById(po.getGroupOrderId());

			BookingSupplier bs = new BookingSupplier();
			bs.setStateBooking(0);
			bs.setStateFinance(0);
			if (resource.getType().equals("TRAIN")){
				bs.setSupplierType(Constants.TRAINTICKETAGENT);
			}else {
				bs.setSupplierType(Constants.AIRTICKETAGENT);
			}
			if(go.getGroupId()!=null){
				bs.setGroupId(go.getTourGroup().getId());
			}
			bs.setOrderId(go.getId());
			bs.setUserId(operatorId);
			bs.setUserName(operatorName);
			bs.setSupplierId(resource.getTicketSupplierId());
			bs.setSupplierName(resource.getTicketSupplier());
			bs.setContact(resource.getContact());
			bs.setContactTel(resource.getContactTel());
			bs.setContactMobile(resource.getContactMobile());
			bs.setContactFax(resource.getContactFax());
			//bs.setCashType(po.getPaymentType());
			bs.setCashType("公司现付"); //2016-5-25 欧宗莹更改，由于　po里该字段永远是null,根据业务要求出票１００%是公司现付
			//bs.setRemark(go.getTourGroup().getRemark());
			
			int count = bookingSupplierBiz.getBookingCountByTypeAndTime(bs.getSupplierType());
			bs.setBookingNo("YM"+Constants.SUPPLIERSHORTCODEMAP.get(bs.getSupplierType())
					+new SimpleDateFormat("yyMMdd").format(new Date())+(count+100));
			bs.setBookingDate(new Date());
			bs.setCreateTime(new Date().getTime());
			
			ArrayList<BookingSupplierDetail> bsdList = new ArrayList<BookingSupplierDetail>();
			HashMap<BigDecimal, Double> priceMap = new HashMap<BigDecimal, Double>();
			for (AirTicketOrder order : po.getOrderList()){
				BigDecimal price = order.getPrice();
				if (priceMap.containsKey(price)){
					priceMap.put(price, priceMap.get(price)+1.0);
				}else {
					priceMap.put(price, 1.0);
				}
			}
			for (BigDecimal price : priceMap.keySet()){
				BookingSupplierDetail bsd = new BookingSupplierDetail();
				bsd.setType1Name(resource.getSeatType());
				bsd.setTicketDeparture(resource.getDepCity());
				bsd.setTicketArrival(legList.get(legList.size()-1).getArrCity());
				bsd.setItemDate(resource.getDepDate());
				bsd.setTicketFlight(resource.getLineName());
				bsd.setItemPrice(price.doubleValue());
				bsd.setItemNum(priceMap.get(price));
				bsd.setItemNumMinus(0.0);
				bsd.setItemTotal(price.doubleValue() * priceMap.get(price));
				bsdList.add(bsd);
			}
			bs.setDetailList(bsdList);
			
			double priceSum=0.0;
			for (BookingSupplierDetail detail : bsdList) {
				priceSum=priceSum+detail.getItemTotal();
			}
			bs.setTotal(new BigDecimal(priceSum));

			
			int bsId=bookingSupplierBiz.insert(bs);
			for (BookingSupplierDetail detail : bsdList) {
				detail.setBookingId(bsId);
				bookingSupplierDetailBiz.insert(detail);
			}
			airTicketRequestBiz.updateBookingSupplierId(po.getId(), bizId, bsId);
		}catch(Exception ex){
			result.setErrorCode(TicketErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public ShowApplyListResult showApplyList(ShowApplyListDTO dto) {
		PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>(); 
		Integer bizId = dto.getBizId();
		//Set<Integer> userSet = WebUtils.getDataUserIdSet(request);
		pageBean.setPage(dto.getPage());
		pageBean.setPageSize(dto.getPageSize());
		
		Map param = dto.getParam();
		param.put("dataUser", dto.getDataUser()); //只取有权限看的订单
		
		Set<Integer> operatorIdSet = new HashSet<Integer>();
		if (param.get("saleOperatorIds")!=null){
			for (String oId: StringUtils.split((String)param.get("saleOperatorIds"), ',')){
				operatorIdSet.add(Integer.parseInt(oId));
			}
		}
		if (param.get("saleOperatorIds")==null && param.get("orgIds")!=null){
			Set<Integer> orgIdSet = new HashSet<Integer>();
			for (String oId : StringUtils.split((String)param.get("orgIds"), ',')){
				orgIdSet.add(Integer.parseInt(oId));
			}
			operatorIdSet.addAll(platformEmployeeBiz.getUserIdListByOrgIdList(dto.getBizId(), orgIdSet));
		}
		if (operatorIdSet.size()!=0){
			param.put("saleOperatorIds", StringUtils.join(operatorIdSet, ','));
		}
		
		pageBean.setParameter(param);
		pageBean = groupOrderBiz.selectAllOrderByConListPage(pageBean, bizId);
		//查询总人数
		Map<String, Object> sumPerson = groupOrderBiz.selectAllOrderByConTotal(pageBean, bizId);
		List<Integer> orderIdList = new ArrayList<Integer>();
		for (GroupOrder o : pageBean.getResult()){
			orderIdList.add(o.getId());
		}
		Map<Integer, List<AirTicketRequest>> requestMap = airTicketRequestBiz.findRequestListForGroupOrder(bizId, orderIdList);
		
		HashMap<Integer, String> orderRequestStatus = new HashMap<Integer, String>();
		List<Integer> resourceIdList = new ArrayList<Integer>();
		for (GroupOrder o : pageBean.getResult()){
			if (requestMap.containsKey(o.getId())){
				orderRequestStatus.put(o.getId(), "已申请");
				for(AirTicketRequest q : requestMap.get(o.getId())){
					resourceIdList.add(q.getResourceId());
				}
			}else {
				orderRequestStatus.put(o.getId(), "未申请");
			}
		}
		// get legs by resource IDs
		Map<Integer, List<AirTicketLeg>> legMap = airTicketResourceBiz.findLegsByResourceIdList(resourceIdList);
		HashMap<Integer, List<AirTicketRequestBO>> boMap = new HashMap<Integer, List<AirTicketRequestBO>>();
		for (Integer orderId: requestMap.keySet()){
			ArrayList<AirTicketRequestBO> l = new ArrayList<AirTicketRequestBO>();
			for (AirTicketRequest q : requestMap.get(orderId)){
				AirTicketRequestBO qBo = new AirTicketRequestBO(q);
				qBo.setArrange(false);
				qBo.getResourceBo().setLegList(legMap.get(qBo.getResourceId()));
				l.add(qBo);
			}
			boMap.put(orderId, l);
		}
		
		/*TourGroup tg = tourGroupService.selectByPrimaryKey(o.getGroupId());
		if (tg.getGroupMode()==0){
			groupModes.put(o.getId(), "散客团");
		}else {
			groupModes.put(o.getId(), "团队");
		}*/
		
		ShowApplyListResult result = new ShowApplyListResult();
		result.setPageBean(pageBean);
		result.setSumPerson(sumPerson);
		result.setOrderRequestStatus(orderRequestStatus);
		result.setBoMap(boMap);
		return result;
	}
	
}
