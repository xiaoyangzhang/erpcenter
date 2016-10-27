package com.yimayhd.erpcenter.dal.sales.client.airticket.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketRequest;

public interface AirTicketRequestDal {
	
	/**
	 * 新增或变更申请前，检查申请的机票数是否超过了余票
	 * 
	 */
	boolean checkAvailable(Integer bizId, Integer resourceId, Integer requestId, Integer requestTicketNumber);
	
	/**
	 * 增加
	 */
	Integer saveRequest(AirTicketRequest airTicketRequest);
	
	/**
	 * 删除,要求条件：
	 * 	1、业务主键（requestId）不能为空
	 *  2、商家ID（bizId）不能为空
	 */
	void deleteRequest(Integer requestId,Integer bizId, Integer opId, String opName, String comment);
	
	/**
	 * 更新,要求条件：
	 * 	1、对象不能为空
	 *  2、对象中的商家ID(bizId)不能为空
	 */
	void updateRequest(AirTicketRequest airTicketRequest);
	
	/**
	 * 单条查询,要求条件：
	 * 	1、业务主键（requestId）不能为空
	 *  2、商家ID（bizId）不能为空
	 */
	AirTicketRequest findRequest(Integer requestId,Integer bizId);
	
	/**
	 * 根据输入的条件来筛选符合条件的申请记录,要求条件:
	 * 	1、对象不为空（pageBean : not null）
	 *  2、pageBean中业务对象不能为空(pageBean.parameter : not null)
	 *  3、业务对象中的商家ID对象不能为空
	 */
	PageBean<AirTicketRequest> selectRequestListPage(PageBean<AirTicketRequest> pageBean);
	
	/**
	 * total=> 总票数，applied=>已申请票数
	 */
	HashMap<String, Integer> countRequestList(PageBean<AirTicketRequest> pageBean);
	
	/**
	 * 更新申请的状态
	 */
	Integer setStatus(Integer requestId,Integer bizId, String status, Integer opId, String opname, String comment);
	
	/**
	 * Resource下有Request的个数，删除resource时检查
	 */
	Integer countRequestByResource(Integer resourceId, Integer bizId);
	
	/**
	 * 取得一个resource下所有的请求
	 */
	List<AirTicketRequest> findRequestsByResource(Integer resourceId, Integer bizId);
	
	/**
	 * 更新booking supplier ID，在插入计调的机票订单后 
	 */
	Integer updateBookingSupplierId(Integer requestId,Integer bizId, Integer bookingSupplierId);
	
	/*
	 * 检查订单是否已申请过机票
	 */
	Boolean doesOrderhaveRequested(Integer bizId, Integer orderId);
	
	/** group_order.id => airTicketRequest object (airTicketResource inside) **/
	Map<Integer, List<AirTicketRequest>> findRequestListForGroupOrder(Integer bizId, List<Integer>orderIdList);
	
	/** list<guestId> */
	List<Integer> findIssuedGuestIdList(Integer biz_id, Integer orderId);
}
