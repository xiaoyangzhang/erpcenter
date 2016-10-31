//package com.yimayhd.erpcenter.facade.sales.service.bak;
//
//import java.text.ParseException;
//
//import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToOrderLockTableDTO;
//import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
//import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToOrderLockListResult;
//import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToOrderLockTableResult;
//
///**
// * 销售-锁单管理
// * 
// * @author gaotingping
// * 2016年10月26日
// */
//public interface GroupOrderLockFacade {
//
//	//跳到锁单列表(锁单管理)
//   	ToOrderLockListResult toOrderLockList(Integer bizId);
//
//   	//锁单查询分页
//   	ToOrderLockTableResult toOrderLockTable(ToOrderLockTableDTO orderLockTableDTO) throws ParseException;
//
//   	//更新订单锁单状态
//   	BaseStateResult updateOrderLockState(Integer orderId, Integer orderLockState);
//}
