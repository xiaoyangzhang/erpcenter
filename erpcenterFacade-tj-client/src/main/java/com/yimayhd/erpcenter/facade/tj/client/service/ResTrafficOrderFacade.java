package com.yimayhd.erpcenter.facade.tj.client.service;

import java.text.ParseException;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.facade.tj.client.query.LockListTableDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.TrafficOrderDTO;
import com.yimayhd.erpcenter.facade.tj.client.result.AdminOrderListTableResult;
import com.yimayhd.erpcenter.facade.tj.client.result.LockListTableResult;

public interface ResTrafficOrderFacade {
	
	public List<DicInfo> loadGroupOrderInfo(int bizId);
	
	/**
	 * 订单数据展示Table
	 * @param request
	 * @param model
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public LockListTableResult lockListTable(LockListTableDTO lockListTableDTO);
	
	public String toUpdateProductPrice(TrafficOrderDTO trafficOrderDTO);
	
	/**
	 * 管理员端订单列表页面跳转
	 * @param request
	 * @param model
	 * @return
	 */
	public List<DicInfo> loadAdminOrderInfo(int bizId);
	
	/**
	 * 订单数据展示Table
	 * @param request
	 * @param model
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public AdminOrderListTableResult adminOrderListTable(TrafficOrderDTO trafficOrderDTO);
	
	/**
	 * 取消订单，更新操作
	 * @param request
	 * @param id
	 * @param causeRemark
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	public String toUpdateAdminExtResState(TrafficOrderDTO trafficOrderDTO) ;
	
	/**
	 * 跳转至状态修改页面
	 * @param request
	 * @param model
	 * @param id
	 * @param resId
	 * @return
	 */
	public GroupOrder toUpdateOrderState(String id);
	
	/**
	 * 保存修改的订单状态
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	public String toUpdateExtResState(TrafficOrderDTO trafficOrderDTO);
	
	public String toDeleteOrderInfo(TrafficOrderDTO trafficOrderDTO);
	
	

}
