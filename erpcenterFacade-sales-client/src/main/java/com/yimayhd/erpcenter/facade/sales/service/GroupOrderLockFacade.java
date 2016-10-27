package com.yimayhd.erpcenter.facade.sales.service;

import java.text.ParseException;

import com.yimayhd.erpcenter.facade.sales.query.ToOrderLockTableDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.ToOrderLockListResult;
import com.yimayhd.erpcenter.facade.sales.result.ToOrderLockTableResult;

/**
 * 销售-锁单管理
 * 
 * @author gaotingping
 * 2016年10月26日
 */
public interface GroupOrderLockFacade {

	//跳到锁单列表(锁单管理)
   	ToOrderLockListResult toOrderLockList(Integer bizId);

   	//锁单查询分页
   	ToOrderLockTableResult toOrderLockTable(ToOrderLockTableDTO orderLockTableDTO) throws ParseException;

   	//更新订单锁单状态
   	BaseStateResult updateOrderLockState(Integer orderId, Integer orderLockState);
}
