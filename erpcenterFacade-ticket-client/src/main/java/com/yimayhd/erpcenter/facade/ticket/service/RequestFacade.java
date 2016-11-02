package com.yimayhd.erpcenter.facade.ticket.service;

import java.math.BigDecimal;
import java.util.Map;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
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
import com.yimayhd.erpcenter.facade.ticket.query.ShowListResourceDTO;
import com.yimayhd.erpcenter.facade.ticket.query.ShowResourceDTO;
import com.yimayhd.erpcenter.facade.ticket.result.EditRequestResult;
import com.yimayhd.erpcenter.facade.ticket.result.GetResourceBoResult;
import com.yimayhd.erpcenter.facade.ticket.result.PickUpGuestResult;
import com.yimayhd.erpcenter.facade.ticket.result.ResultSupport;
import com.yimayhd.erpcenter.facade.ticket.result.ShowApplyEditResult;
import com.yimayhd.erpcenter.facade.ticket.result.ShowApplyListResult;
import com.yimayhd.erpcenter.facade.ticket.result.ShowGroupOrderResult;
import com.yimayhd.erpcenter.facade.ticket.result.ShowListResourceResult;
import com.yimayhd.erpcenter.facade.ticket.result.ShowListResult;
import com.yimayhd.erpcenter.facade.ticket.result.ShowResourceResult;
import com.yimayhd.erpcenter.facade.ticket.result.TicketListResult;

public interface RequestFacade {
	
	
	ShowListResult showList(ShowListDTO dto);
	
	GetResourceBoResult getResoruceBo(GetResourceBoDTO dto);
	
	TicketListResult ticketList(Integer bizId, Integer id);
	
	EditRequestResult editRequest(Integer bizId, Integer id);
	
	/* 添加接送机信息  */
	PickUpGuestResult pickUpGuest(Integer bizId, Integer id);
	
	BookingSupplier selectByPrimaryKey(Integer bookingId);
	
	String showResourceInfo(Integer resourceId, Integer bizId);
	
	String showGroupOrderInfo(Integer groupOrderId, Integer bizId);
	
	String showGroupOrderGuest(Integer groupOrderId);
	
	ShowResourceResult showResource(ShowResourceDTO dto);
	
	ShowGroupOrderResult showGroupOrder(ShowGroupOrderDTO dto);
	
	ResultSupport save(SaveDTO dto);
	
	ResultSupport arrange(ArrangeDTO dto);
	
	ResultSupport reject(RejectDTO dto);
	
	ResultSupport confirm(ConfirmDTO dto);
	
	ResultSupport issue(IssueDTO dto);
	
	ResultSupport delete(DeleteDTO dto);
	
	ResultSupport changePrice(Integer orderId, BigDecimal price);
	
	ResultSupport changePrices(String orderIds, BigDecimal price);
	
	ResultSupport savePickUp(SavePickUpDTO dto);
	
	ResultSupport changeGuestComment(Integer orderId, String comment);
	
	Map<Integer, String> getTicketSuppliers(Integer bizId);
	
	ShowApplyEditResult showApplyEdit(Integer bizId, Integer orderId);
	
	ResultSupport saveBookingSupplier(SaveBookingSupplierDTO dto);
	
	ShowApplyListResult showApplyList(ShowApplyListDTO dto);
	
}
