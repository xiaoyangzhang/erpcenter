package com.yimayhd.erpcenter.dal.sales.client.taobao.service;

import java.util.List;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yihg.sales.po.GroupOrder;
import com.yihg.taobao.po.PlatTaobaoTrade;
import com.yihg.taobao.po.TaobaoOrder;

/**
 * Created by zhoum on 2016/8/11.
 */

public interface TaobaoOrderService {

	/**
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
	
}
