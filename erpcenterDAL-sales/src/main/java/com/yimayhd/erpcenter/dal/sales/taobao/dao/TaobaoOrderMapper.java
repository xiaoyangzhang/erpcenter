package com.yimayhd.erpcenter.dal.sales.taobao.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.taobao.po.TaobaoOrder;

public interface TaobaoOrderMapper {
	/**
	 * 查询淘宝原始单
	 */
	List<TaobaoOrder>selectTaobaoOrder(@Param("page") PageBean<TaobaoOrder> pageBean, @Param("bizId") Integer bizId);
	/**
	 * 查询淘宝原始单BY id
	 */
	List<TaobaoOrder>selectTaobaoOrderById( @Param("ids") String ids);
	
	/**
	 * 更新淘宝原始单orderId
	 *@param orderId 
	 * @param ids 订单ID
	 */
	void updateTaobaoOrderId(@Param("orderId") Integer orderId,@Param("ids") String ids);
	/**
	 * 查询淘宝原始单BY orderId
	 * @param id 为OrderId
	 */
	List<TaobaoOrder>selectTaobaoOrderByOrderId( @Param("id") Integer id);
	
	/**
	 * 更新淘宝原始单orderId为0
	 * @param id 
	 */
	void updateTaobaoOrderIdToZero(@Param("id") String id);
	
}
