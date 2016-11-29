package com.yimayhd.erpcenter.facade.tj.client.service;

import java.text.ParseException;
import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.facade.tj.client.query.AddSivaInfoDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.ImportTaobaoOrderTableDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.PresellProductStatistics;
import com.yimayhd.erpcenter.facade.tj.client.query.PushTradeQueryDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.SaveSpecialGroupDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.ShopSalesStatisticsQueryDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.TaobaoOrderListByOpDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.TaobaoOrderListTableDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.TaobaoOriginalOrderTableDTO;
import com.yimayhd.erpcenter.facade.tj.client.query.ToEditTaobaoOrderDTO;
import com.yimayhd.erpcenter.facade.tj.client.result.*;

import org.springframework.ui.Model;

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
	

	public String savePushTrade(PushTradeQueryDTO pushTradeQueryDTO);

	
	public PresellProductStatisticsListResult selectNotPresellProductStatisticsListPage(PresellProductStatistics queryDTO);
	
	
	public PresellProductStatisticsListResult selectSaleOperatorSalesStatisticsListPage(PresellProductStatistics queryDTO);


	WebResult<PageBean> toOrderPreview(TaobaoOrderListTableDTO taobaoOrderListTableDTO);
	
	/**
	 * 计调操作单
	 */
	public TaobaoOrderListByOpDTO taobaoOrderListByOp(TaobaoOrderListByOpDTO taobaoOrderListByOpDTO);
	
	public TaobaoOrderListByOpDTO taobaoOrderListByOp_table(TaobaoOrderListByOpDTO taobaoOrderListByOpDTO) throws ParseException;
	
	public void changeOrderLockState(Integer orderId);
	
	public void changeorderLockStateByOp(Integer orderId) ;
	
	public void goBackOrderLockStateByOp(Integer orderId);
	
	public void updateLockStateToFinance(Integer orderId) ;
	
	public void goBackToOP(Integer orderId);
	/**
     * 选中签证填写信息
     * 
     * @param request
     * @param model
     * @param orderMode
     * @return
     * @throws ParseException
     */
	public AddSivaInfoDTO addSivaInfo(AddSivaInfoDTO addSivaInfoDTO) ;

	/**
     * 保存签证信息
     * 
     * @param orderMode
     * @param countStr
     * @return
     */
	public void saveVisaInfo(AddSivaInfoDTO addSivaInfoDTO);
	
	/**
     * 查询签证客户信息
     * 
     * @param request
     * @param reponse
     * @param model
     * @param mobile
     * @return
     * @throws ParseException
     */
	public List<GroupOrder> loadGroupOrderVisaInfo(String mobile) throws ParseException;
}
