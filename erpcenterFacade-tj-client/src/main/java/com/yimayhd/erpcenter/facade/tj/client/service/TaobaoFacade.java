package com.yimayhd.erpcenter.facade.tj.client.service;

import java.text.ParseException;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.facade.tj.client.query.ImportTaobaoOrderTableDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.PresellProductStatistics;
import com.yimayhd.erpcenter.facade.tj.client.query.SaveSpecialGroupDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.ShopSalesStatisticsQueryDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.TaobaoOrderListTableDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.TaobaoOriginalOrderTableDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.ToEditTaobaoOrderDTO;
import com.yimayhd.erpcenter.facade.tj.client.result.AddNewTaobaoOrderResult;
import com.yimayhd.erpcenter.facade.tj.client.result.ImportTaobaoOrderTableResult;
import com.yimayhd.erpcenter.facade.tj.client.result.PresellProductStatisticsListResult;
import com.yimayhd.erpcenter.facade.tj.client.result.SaveSpecialGroupResult;
import com.yimayhd.erpcenter.facade.tj.client.result.ShopSalesStatisticsResult;
import com.yimayhd.erpcenter.facade.tj.client.result.TaobaoOrderListResult;
import com.yimayhd.erpcenter.facade.tj.client.result.TaobaoOrderListTableResult;
import com.yimayhd.erpcenter.facade.tj.client.result.ToEditTaobaoOrderResult;

public interface TaobaoFacade{
	
	/**
	 * 操作单
	 * 
	 */
	public TaobaoOrderListResult taobaoOrderList(int bizId);

	/**
	 * 操作单table
	 * @throws ParseException 
	 */
	public TaobaoOrderListTableResult taobaoOrderList_table(TaobaoOrderListTableDTO taobaoOrderListTableDTO);
	
	/*操作单-编辑*/
	public ToEditTaobaoOrderResult toEditTaobaoOrder(ToEditTaobaoOrderDTO toEditTaobaoOrderDTO);

	/**
	 * 跳转到新增订单页面
	 */
	public AddNewTaobaoOrderResult addNewTaobaoOrder(int bizId);
	
	/**
	 * 保存订单
	 */
	public SaveSpecialGroupResult saveSpecialGroup(SaveSpecialGroupDTO saveSpecialGroupDTO);
	
	/**
	 * 淘宝订单导入页面table
	 */
	public ImportTaobaoOrderTableResult import_taobaoOrder_table(ImportTaobaoOrderTableDTO importTaobaoOrderTableDTO);
	
	/**
	 * 确定
	 */
	public String taobaoOrder_GetByIds(String ids);
	
	/**
	 * 淘宝原始单table（爱游）
	 */
	public PageBean taobaoOriginalOrder_table(TaobaoOriginalOrderTableDTO taobaoOriginalOrderTableDTO);
	/**
     * 废弃
     */
	public void updateCancel(String idss);
	/**
     * 还原
     */
	public void updateNew(String idss);
	/**
     * 同步 by time
     */
	public PageBean synchroByTime(TaobaoOriginalOrderTableDTO taobaoOriginalOrderTableDTO);
	/**
     * 同步 by tid
     */
	public PageBean synchroByTid(TaobaoOriginalOrderTableDTO taobaoOriginalOrderTableDTO);
	
	
	public ShopSalesStatisticsResult selectTaobaoshopSalesStatistics(ShopSalesStatisticsQueryDTO queryDTO);
	
	
	public PresellProductStatisticsListResult selectPresellProductStatisticsListPage(PresellProductStatistics queryDTO);
	
	
	public PresellProductStatisticsListResult selectNotPresellProductStatisticsListPage(PresellProductStatistics queryDTO);
	
	
	public PresellProductStatisticsListResult selectSaleOperatorSalesStatisticsListPage(PresellProductStatistics queryDTO);
}
