package com.yimayhd.erpcenter.dal.sales.client.sales.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderForCarCar;
import com.yimayhd.erpcenter.dal.sales.client.sales.query.GroupOrderQueryForCarCar;
import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceCommission;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.PaymentExportVO;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.DeparentmentOrderCondition;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.DepartmentOrderResult;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupPriceVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.MergeGroupOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SaleOperatorOrderStatic;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SaleOperatorVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SalePrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SalesVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;

public interface GroupOrderDal {

    public GroupOrder findById(Integer id);

    /**
     * 查所有的order，包括未并团的散客订单，此时groupId为NULL
     */
    public GroupOrder findOrderById(Integer bizId, Integer id);

    public HashMap<Integer, GroupOrder> findOrderByIdList(Integer bizId, List<Integer> idList);

    public void saveGroupOrder(GroupOrderVO groupOrderVO, GroupRouteVO groupRouteVO, List<GroupOrderPrice> insertList);

    public void updateGroupOrder(GroupOrder groupOrder,
                                 List<GroupOrderGuest> list);

    public void updateGroupOrder(GroupOrder groupOrder);


    /**
     * @see 内部结算(销售)—审核功能
     * @author TengDong
     * @param groupOrder团队订单
     * @return  boolean
     */
    public boolean updateGroupOrderByB2b_export_state(GroupOrder groupOrder);





    public void delGroupOrder(Integer id);

    /**
     * 保存组团订单信息
     *
     * @param groupOrder
     * @return
     */
    GroupOrder insertSelective(GroupOrder groupOrder);

    /**
     * 分页查询未并团
     *
     * @param pageBean
     * @param bizId
     * @return
     */
    public PageBean selectNotGroupListPage(PageBean pageBean, Integer bizId, Set<Integer> set);

    public GroupOrder selectTotalNotGroup(GroupOrder groupOrder, Integer bizId, Set<Integer> set);

    /**
     * 分页查询已并团
     *
     * @param pageBean
     * @param bizId
     * @param listType 0销售权限   1计调权限
     * @return
     */
    public PageBean selectFitOrderListPage(PageBean pageBean, Integer bizId, Set<Integer> set, Integer listType);

    public GroupOrder selectFitOrderTotalCount(GroupOrder groupOrder, Integer bizId, Set<Integer> set, Integer listType);

    /**
     * 订单日志-产品下订单明细
     *
     * @param pageBean
     * @param bizId
     * @return
     */
    public PageBean<GroupOrder> selectProductOrderListPage(PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set);


    public void mergeGroup(List<MergeGroupOrderVO> list, Integer bizId, Integer operid, String operName, String supplierCode) throws ParseException;

    /**
     * @param bizCode 当前商家编码
     * @return
     */
    public String makeCodeByMode(Integer bizId, String bizCode, String dateTime, int sort);


    /**
     * 未确认团查询
     *
     * @return
     */
    PageBean<GroupOrder> selectByConListPage(PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set, Integer listType);

    GroupOrder selectTotalByCon(GroupOrder groupOrder, Integer bizId, Set<Integer> set, Integer listType);

    /**
     * 根据团号查询订单
     *
     * @param groupId
     * @return
     */
    public List<GroupOrder> selectOrderByGroupId(Integer groupId);

    /*
     * app 计调
     */
    List<GroupOrder> selectOrderByGroupIdAndBizId(Integer groupId, Integer bizId);

    /**
     * 根据订单id查询订单信息
     *
     * @param id
     * @return
     */
    GroupOrder selectByPrimaryKey(Integer id);

    /**
     * 按订单查询预算利润
     *
     * @param pageBean
     * @param bizId    当前商家id
     * @return
     */
    PageBean<GroupOrder> selectProfitByConListPage(PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set);

    /**
     * 查询所有订单的成人、小孩、全陪
     *
     * @param pageBean
     * @param bizId
     * @return
     */
    GroupOrder selectProfitByCon(PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set);

    /**
     * 查询以下订单：未并团的散客订单、已并团散客订单、以及已确认的团订单
     */
    PageBean<GroupOrder> selectAllOrderByConListPage(PageBean<GroupOrder> pageBean, Integer bizId);

    Map<String, Object> selectAllOrderByConTotal(PageBean<GroupOrder> pageBean, Integer bizId);

    /**
     * 预算利润查询统计总收入、总成本（带查询条件）
     *
     * @param pageBean
     * @param bizId
     * @return
     */
    GroupOrder selectProfitByConAndMode(@Param("page") PageBean<GroupOrder> pageBean, @Param("bizId") Integer bizId, Set<Integer> set);

    List<GroupOrder> selectPaymentDetailList(PageBean<PaymentExportVO> pageBean, Integer bizId, Set<Integer> set);

    List<Map<String, Object>> getGroupInfoByGroupId(Integer groupId, Integer bizId);

    /**
     * 查询所有团<查询统计-客户团量查询专用>
     *
     * @param curBizId
     * @param dataUserIdSet
     * @return
     */
    List<GroupOrder> selectGroupNumForQuery(GroupOrder groupOrder, Integer curBizId, Set<Integer> dataUserIdSet);

    /**
     * 销售业绩统计
     *
     * @param pageBean
     * @param bizId
     * @return
     */
    PageBean<SaleOperatorOrderStatic> selectSaleOperatorOrderStaticListPage(PageBean<SaleOperatorOrderStatic> pageBean, Integer bizId, Set<Integer> set);

    SaleOperatorOrderStatic selectSaleOperatorOrderStaticCon(PageBean<SaleOperatorOrderStatic> pageBean, Integer bizId, Set<Integer> set);

    List<GroupOrder> selectSupplierByGroupId(Integer groupId);

    List<GroupPriceVo> selectSupplierByGroupIdAndSupplierId(Integer groupId, Integer supplierId);

//	void updateOrderAndGroupPersonNum(Integer orderId);

    void updateGroupPersonNum(Integer groupId);

    void updateOrderAndGroupPrice(Integer orderId);

    void updateGroupPrice(Integer groupId);

    void changeOrderLockState(Integer orderId);

    void changeorderLockStateByOp(Integer orderId);

    void goBackOrderLockStateByOp(Integer orderId);

    void updateLockStateToFinance(Integer orderId);

    void goBackToOP(Integer orderId);

    List<GroupOrder> selectOrderByGroupIdAndBizIdAndSupplierId(Integer groupId, Integer supplierId, Integer bizId);


    //一地散
    PageBean<GroupOrder> selectSpecialOrderListPage(PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set);

    //爱游淘宝
    PageBean<GroupOrder> selectTaobaoOrderListPage(PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set, Integer userRightType);

    PageBean<GroupOrder> selectTaobaoOrderGuestNameListPage(PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set, Integer userRightType);

    GroupOrder selectTotalSpecialOrder(GroupOrder groupOrder, Integer bizId, Set<Integer> set);

    GroupOrder selectTotalTaobaoOrder(GroupOrder groupOrder, Integer bizId, Set<Integer> set);

    GroupOrder selectTotalTaobaoGuestNameOrder(GroupOrder groupOrder, Integer bizId, Set<Integer> set);

    PageBean<GroupOrder> selectOrderLockByConListPage(PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set);

    Integer updateOrderLockState(Integer orderId, Integer orderLockState);

    PageBean<SaleOperatorVo> selectSaleOperatorByConListPage(PageBean<SaleOperatorVo> pageBean, Integer bizId, Set<Integer> set);

    PageBean<GroupOrder> selectOrderByParameterListPage(PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set);

    List<GroupOrder> selectOrderByParameter2(Map map);

    String selectOrderLockByCon(PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set);

    PageBean<SalePrice> getSalePriceListPage(PageBean<SalePrice> pageBean, Integer bizId, Set<Integer> set);

    BigDecimal getSalePriceToalPrice(PageBean<SalePrice> pageBean, Integer bizId, Set<Integer> set);

    //初始订单
    List<GroupOrder> selectInitOrderList(Integer bizId, Integer groupId);

    void saveInitGroupInfo(Integer bizId, SalesVO salesVO, Integer userId, String userName);

    /**
     * 查询产品下的订单统计信息
     *
     * @param pageBean
     * @param bizId
     * @param set
     * @return
     */
    PageBean<GroupOrder> selectOrderGroupByProductIdListPage(PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set);

    /**
     * 查询产品下的订单统计信息--总合计统计
     *
     * @param pageBean
     * @param bizId
     * @param set
     * @return
     */
    GroupOrder selectOrderGroupByProductIdList(PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set);

    /**
     * 销售报价查询
     *
     * @param pageBean
     * @param set
     * @return
     */
    PageBean getDeservedCashGroupByOrderId(PageBean pageBean, Set<Integer> set);

    Map<String, Object> getDeservedCashGroupByOrderIdTotal(PageBean pageBean, Set<Integer> set);

    /**
     * 根据团号查询团下所有订单
     *
     * @param groupId
     * @return
     */
    List<GroupOrder> selectSubOrderList(Integer groupId);

    /**
     * 应收款统计预览
     *
     * @param pageBean
     * @param bizId
     * @param set
     * @return
     */
    List<GroupOrder> selectPaymentStaticData(PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set);

    List<GroupOrder> selectPaymentStaticData2(PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set);

    /**
     * 部门订单分析统计数据
     *
     * @param set
     * @return
     */
    List<DepartmentOrderResult> getDepartmentOrderStatistics(DeparentmentOrderCondition condition, Set<Integer> set);

    /**
     * 查询某个订单下所有人数
     *
     * @param orderId
     * @return
     */
    Integer selectTotalNumByOrderId(Integer orderId);

    List<GroupOrder> selectAiYouOrders(Integer bizId, String startTime, String endTime);

    /**
     * 根据产品和日期获取人数（成人儿童和全陪）
     * 用于库存对账
     *
     * @param bizId
     * @param productId
     * @param itemDate
     * @return
     */
    Map<String, Object> getCountByProductIdAndDate(Integer bizId, Integer productId, Date itemDate);

    /**
     * 查询最近10条导入记录
     *
     * @param bizId
     * @return
     */
    List<GroupOrder> selectTopAiYouOrders(Integer bizId, String startTime, Integer limit);

    public GroupOrder selectTotalStatic(PageBean<PaymentExportVO> pageBean, Integer bizId, Set<Integer> set);

    /**
     * 根据接站牌获取团id列表
     * 预定安排，查询条件
     *
     * @return
     */
    List<Map<String, Object>> selectGroupIdsByReceiveMode(TourGroupVO group);

    List<GroupOrder> selectIdList(Integer supplierId);

    Integer existgroupOrder(Integer supplierId);
	
	/**
	 * 产品收客趋势查询
	 * @param pageBean
	 * @return
	 */
	List<GroupOrder> selectProductTrendListPageInnerLayer(PageBean<GroupOrder> pageBean, Map parameters);
	
	PageBean<GroupOrder> selectProductTrendListPageOutLayer(PageBean<GroupOrder> pageBean,Map parameters);

    /**
     * 爱游库数据(aiyou爱游、yimay怡美...)导入到订单表(订单，订单客人，订单价格，行程)
     *
     * @param groupOrderList 订单详细信息(订单，订单客人，订单价格，行程)
     * @return 保存1成功0失败
     */
    Integer saveAiYouDataToGroupOrder(List<GroupOrder> groupOrderList);
    
    public List<GroupOrder> selectGroupOrderById(Integer orderId);
    
    PageBean<GroupOrder> selectResGroupOrderList(PageBean<GroupOrder> pageBean,Integer bizId,Set<Integer> set);
    
    Integer findUpdateGroupOrderState(String id,String causeRemark);
    
    HashMap<String, BigDecimal> sumGroupOrder(PageBean<GroupOrder> pageBean);
    
    PageBean<GroupOrder> selectResAdminOrderList(PageBean<GroupOrder> pageBean,Integer bizId,Set<Integer> set);

    HashMap<String, BigDecimal> sumResAdminOrder(PageBean<GroupOrder> pageBean);
    
    Integer findUpdateAdminOrderState(String id,String causeRemark);
    
    Integer loadUpdateExtResState(GroupOrder record);
    
    Integer updateExtVisa(GroupOrder record);

    List<GroupOrder> selectByOrderIdExtVisaListPage(String mobile);

    Integer delGroupOrderId(Integer id);

    Integer selectSumPersonByProductId(Integer resId,Integer productId,String departureDate);
    
    List<GroupOrder> selectOrderOverTime();
    
    List<FinanceCommission> selectFinanceCommissionByGroupId(Integer groupId);
    
    List<FinanceCommission> selectFCByGroupId(Integer groupId);
    
    PageBean<GroupOrder> findByTime(String orderStr,PageBean<GroupOrder> pageBean,
            Integer bizId, Integer userId, String userName);

    Integer importGroupOrder(String[] orderIds, Integer saleOperatorId,
            String saleOperatorName, Integer operatorId, String operatorName, Integer supplierId,
            String supplierName, Integer orderType);

    List<GroupOrder> selectBySaleOperatorId(Integer bizId,DeparentmentOrderCondition condition, Set<Integer> set);

    PageBean selectGroupOrderGuestListPage(PageBean pageBean, Integer bizId,Set<Integer> set,Integer userRightType);

    PageBean<GroupOrder>selectMonthlyReportStatisticsListPage(PageBean<GroupOrder> pageBean, Integer bizId);

    PageBean selectGroupOrderGuestListPageOu(PageBean pageBean, Integer bizId,Set<Integer> set,Integer userRightType,String sidx,String sord);
    
    List<GroupOrder>selectMonthlyReportStatistics(PageBean<GroupOrder> pageBean, Integer bizId);


    PageBean<GroupOrder> selectOperatorOrderListPage(PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set, Integer userRightType);

    PageBean<GroupOrder> selectOperatorGuestNameListPage(PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set, Integer userRightType);

    /**
     * @see 内部结算
     * @Auth TengDong
     * @Date 20161031
     * @param pageBean  model
     * @param bizId
     * @param set
     * @param userRightType
     * @return
     */
    public PageBean<GroupOrder> selectProfitEverifyListPage(PageBean<GroupOrder> pageBean,
                                                            Integer bizId, Set<Integer> set, Integer userRightType);


    /**
     * @see 内部结算销售 总合计
     * @Auther TengDong
     * @Date 20161102
     * @param pageBean
     * @param bizId
     * @param set
     * @param userRightType
     * @return
     */
    public GroupOrder selectProfitEverifyByCon(GroupOrder groupOrder, Integer bizId, Set<Integer> set,
                                               Integer userRightType);

    Map<String, Object> selectProfitEverifyByTotal(PageBean pageBean,Integer bizId,Set<Integer> set,Integer userRightType);

    PageBean<GroupOrder> selectProductProfitStatisticsListPage(PageBean<GroupOrder> pageBean, Integer bizId);

    GroupOrderForCarCar selectGroupOrdersInGroupsForCarCar(PageBean<GroupOrderQueryForCarCar> pageBean);
} 

