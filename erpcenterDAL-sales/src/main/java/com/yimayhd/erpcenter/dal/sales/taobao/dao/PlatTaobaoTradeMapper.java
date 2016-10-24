package com.yihg.taobao.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yihg.taobao.po.PlatTaobaoTrade;
import com.yihg.taobao.po.TaobaoOrder;

public interface PlatTaobaoTradeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlatTaobaoTrade record);

    int insertSelective(PlatTaobaoTrade record);

    PlatTaobaoTrade selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlatTaobaoTrade record);

    int updateByPrimaryKey(PlatTaobaoTrade record);
    
    PlatTaobaoTrade selectByTid(@Param("tid") String tid);
    
    /**
	 * 查询淘宝原始单
	 */
	List<PlatTaobaoTrade>selectTaobaoOrderListPage(@Param("page") PageBean<PlatTaobaoTrade> pageBean, @Param("bizId") Integer bizId);
	/**
	 * 查询淘宝原始单BY id
	 */
	List<PlatTaobaoTrade>selectTaobaoOrderById( @Param("ids") String ids);
	/**
	 * 更新淘宝原始单orderId plat_taobao_trade表
	 *@param orderId 
	 * @param ids 订单ID
	 */
	void updateTaobaoOrderId(@Param("orderId") Integer orderId,@Param("ids") String ids);
	/**
	 * 更新淘宝原始单orderId   plat_taobao_trade_order表
	 *@param orderId 
	 * @param ids 订单ID
	 */
	void updateTaobaoOrderIdToOrder(@Param("orderId") Integer orderId,@Param("ids") String ids);
	/**
	 * 更新淘宝原始单orderId为0 plat_taobao_trade表
	 * @param id 
	 */
	void updateTaobaoOrderIdToZero(@Param("id") String id);
	/**
	 * 更新淘宝原始单orderId为0 plat_taobao_trade_order表
	 * @param id 
	 */
	void updateTaobaoOrderIdToZeroToOrder(@Param("id") String id);
	
	/**
	 * 查询淘宝原始单BY orderId
	 * @param id 为OrderId
	 */
	List<PlatTaobaoTrade>selectTaobaoOrderByOrderId( @Param("id") Integer id);
	/**
	 * 更新淘宝原始单状态  CANCEL
	 * @param id 
	 */
	void updateCANCEL(@Param("idss") String idss);
	/**
	 * 更新淘宝原始单状态  NEW
	 * @param id 
	 */
	void updateNEW(@Param("idss") String idss);
}