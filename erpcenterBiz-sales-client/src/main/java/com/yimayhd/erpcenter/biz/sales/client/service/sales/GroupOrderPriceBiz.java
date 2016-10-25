package com.yimayhd.erpcenter.biz.sales.client.service.sales;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.CostIncome;

/**
 * 销售订单-价格
 * 
 * @author qindz
 *
 */
public interface GroupOrderPriceBiz {

	int deleteByPrimaryKey(Integer id);

	int insert(GroupOrderPrice record);

	int insertSelective(GroupOrderPrice record);

	GroupOrderPrice selectByPrimaryKey(Integer id);

	/**
	 * 根据订单表ID查询价格记录
	 * 
	 * @param orderId
	 *            ----订单表ID，不是订单号
	 * @param mode
	 *            ----价格类型 0是收入1是预算
	 * @return
	 */
	List<GroupOrderPrice> selectByOrderAndType(Integer orderId, Integer mode);

	/**
	 * 根据订单ID查询该订单下是否有成本
	 * @param orderId
	 * @return
	 */
	Boolean selectByOrderAndType(Integer orderId) ;
	
	int updateByPrimaryKeySelective(GroupOrderPrice record);

	int updateByPrimaryKey(GroupOrderPrice record);

	List<GroupOrderPrice> selectByOrder(Integer orderId);
	
	int saveCostIncomeBatch(CostIncome costIncome,Integer creatorId,String creatorName) ;
	
	/**
	 * 将订单价格明细拼接成字符串
	 * @param priceList
	 * @return
	 */
	String concatDetail(List<GroupOrderPrice> priceList);
	
	/**
	 * 将订单价格明细拼接成表格
	 * @param priceList
	 * @return
	 */
	String concatDetailTable(List<GroupOrderPrice> priceList, boolean isShow,String edit);
	
	/**
	 * 修改价格详情的审核状态
	 * @param priceCheckedIds
	 * @param priceUnCheckedIds
	 */
    void auditPriceByIds(String priceCheckedIds, String priceUnCheckedIds);

}
