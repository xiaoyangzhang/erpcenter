package com.yimayhd.erpcenter.dal.sales.airticket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketOrder;

public interface AirTicketOrderMapper {
	/**
	 * 根据主键和商家ID删除单条记录(逻辑删除)
	 * @param order
	 * @return
	 */
    int delete(@Param("id") Integer id,@Param("bizId") Integer bizId);

    /**
	 * 根据requestId和商家ID删除单条记录(逻辑删除)
	 * @param order
	 * @return
	 */
    int deleteRequest(@Param("id") Integer id,@Param("bizId") Integer bizId);
    
    /**
     * 插入单条记录
     * @param order
     * @return
     */
    int insert(AirTicketOrder order);
    
    int update(AirTicketOrder order);

    /**
     * 根据request_id和biz_id查询当前申请下所有的订单
     * @param order
     * @return
     */
    List<AirTicketOrder> findOrderList(@Param("requestId") Integer requestId,@Param("bizId") Integer bizId);
    
    List<Integer> findIssuedGuestIdList(@Param("bizId") Integer biz_id, @Param("orderId") Integer orderId);
}