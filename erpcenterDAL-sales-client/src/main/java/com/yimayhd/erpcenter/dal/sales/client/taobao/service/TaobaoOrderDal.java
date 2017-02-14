package com.yimayhd.erpcenter.dal.sales.client.taobao.service;

import java.util.List;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.taobao.po.PlatTaobaoTrade;

/**
 * Created by zhoum on 2016/8/11.
 */

public interface TaobaoOrderDal {	/**
	 * 查询淘宝原始单
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	PageBean<PlatTaobaoTrade> selectTaobaoOrder(PageBean<PlatTaobaoTrade> pageBean, Integer bizId);
	/**
	 * 查询淘宝原始单
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	PageBean<PlatTaobaoTrade> selectTaobaoOrderByTid(PageBean<PlatTaobaoTrade> pageBean, Integer bizId);
	/**
	 * 查询淘宝原始单 import
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	PageBean<PlatTaobaoTrade> selectTaobaoOrderImport(PageBean<PlatTaobaoTrade> pageBean, Integer bizId);
	
	
	
	List<PlatTaobaoTrade> selectTaobaoOrderById(String ids);
	
	/**
	 * 更新淘宝原始单orderId
	 *@param orderId 
	 * @param ids 订单ID
	 */
	void updateTaobaoOrderId(Integer orderId,String ids);
	
	List<PlatTaobaoTrade> selectTaobaoOrderByOrderId(Integer id);
	/**
	 * 更新淘宝原始单orderId为0
	 * @param id 
	 */
	void updateTaobaoOrderIdToZero(String id);
	
	/**
	 * 更新淘宝原始单状态 CANCEL
	 * @param idss
	 */
	void updateCANCEL(String idss);
	
	/**
	 * 更新淘宝原始单状态NEW
	 * @param idss
	 */
	void updateNEW(String idss);
	
	void updateOrderId(Integer orderId);
	/**
     * 查询淘宝原始单 根据时间
     * @param pageBean
     * @param bizId
     * @return
     */
    PageBean<PlatTaobaoTrade> syncTaobaoOrderByTime(PageBean<PlatTaobaoTrade> pageBean, Integer bizId);

    
	PlatTaobaoTrade selectTaobaoshopSalesStatistics(PlatTaobaoTrade  platTaobaoTrade,Integer bizId);
	
	PageBean<PlatTaobaoTrade>selectPresellProductStatisticsListPage(PageBean<PlatTaobaoTrade> pageBean,Integer bizId);
	
	PageBean<PlatTaobaoTrade>selectPresellTaobaoOrderListPage(PageBean<PlatTaobaoTrade> pageBean,Integer bizId);
	
	PageBean<PlatTaobaoTrade>selectNotPresellProductStatisticsListPage(PageBean<PlatTaobaoTrade> pageBean,Integer bizId);
	
	PageBean<PlatTaobaoTrade>selectSaleOperatorSalesStatisticsListPage(PageBean<PlatTaobaoTrade> pageBean,Integer bizId);
	
	PageBean<PlatTaobaoTrade> savePushTrade(String tid, String authClient, String response);
	
	void updateBEYOND(Integer toId);
}
