package com.yimayhd.erpcenter.dal.sales.sales.dao;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.PaymentExportVO;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.DeparentmentOrderCondition;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.DepartmentOrderResult;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.ProductGuestCondition;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.ProductGuestStaticsVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderForCarCar;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SaleOperatorOrderStatic;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SaleOperatorVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SalePrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.*;

public interface GroupOrderMapper {

    void updateTotalByPrice(@Param("orderId") Integer orderId);

    int deleteByPrimaryKey(Integer id);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int deleteByOrderId(Integer id);

    int insert(GroupOrder record);

    int insertSelective(GroupOrder record);

    GroupOrder selectByPrimaryKey(Integer id);

    GroupOrder selectOrderByPrimaryKey(@Param("bizId") Integer bizId, @Param("id") Integer id);

    List<GroupOrder> selectOrderByIdList(@Param("bizId") Integer bizId,
                                         @Param("idList") String strIdList);

    int updateByPrimaryKeySelective(GroupOrder record);

    int updateByPrimaryKey(GroupOrder record);

    List<GroupOrder> selectNotGroupListPage(@Param("page") PageBean pageBean,
                                            @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);

    GroupOrder selectTotalNotGroup(@Param("groupOrder") GroupOrder groupOrder,
                                   @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);

    /**
     * @param pageBean
     * @param bizId
     * @param set
     * @param listType
     *            0销售权限，1计调权限
     * @return
     */
    List<GroupOrder> selectFitOrderListPage(@Param("page") PageBean pageBean,
                                            @Param("bizId") Integer bizId, @Param("set") Set<Integer> set,
                                            @Param("listType") Integer listType);

    GroupOrder selectFitOrderTotalCount(@Param("groupOrder") GroupOrder groupOrder,
                                        @Param("bizId") Integer bizId, @Param("set") Set<Integer> set,
                                        @Param("listType") Integer listType);

    /**
     * 根据团号查询团下所有订单数据
     *
     * @return
     */
    List<GroupOrder> selectSubOrderList(@Param("groupId") Integer groupId);

    /**
     * 订单日志-产品下订单明细
     */
    List<GroupOrder> selectProductOrderListPage(@Param("page") PageBean pageBean,
                                                @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);

    GroupOrder selectGroupOrderCodeSort(@Param("bizId") Integer mode,
                                        @Param("startTime") String startTime);

    List<GroupOrder> selectByConListPage(@Param("page") PageBean<GroupOrder> pageBean,
                                         @Param("bizId") Integer bizId, @Param("set") Set<Integer> set,
                                         @Param("listType") Integer listType);

    GroupOrder selectTotalByCon(@Param("groupOrder") GroupOrder groupOrder,
                                @Param("bizId") Integer bizId, @Param("set") Set<Integer> set,
                                @Param("listType") Integer listType);

    List<GroupOrder> selectOrderByGroupId(Integer groupId);

    /*
     * app 计调
     */
    List<GroupOrder> selectOrderByGroupIdAndBizId(@Param("groupId") Integer groupId,
                                                  @Param("bizId") Integer bizId);

    Map<String, Integer> selectOrderByGroupIdAndName(@Param("groupId") Integer groupId,
                                                     @Param("bizId") Integer bizId);

    int delGroupOrder(Integer id);

    GroupOrder selectWordExport(Integer orderId);

    /**
     * 取得以下订单：未并团的散客订单、已并团散客订单、以及已确认的团订单
     */
    List<GroupOrder> selectAllOrderByConListPage(@Param("page") PageBean<GroupOrder> pageBean,
                                                 @Param("bizId") Integer bizId);

    /**
     * 获取总人数
     *
     * @param pageBean
     * @param bizId
     * @return
     */
    Map<String, Object> selectAllOrderByConTotal(@Param("page") PageBean<GroupOrder> pageBean,
                                                 @Param("bizId") Integer bizId);

    /**
     * 预算利润查询-（按订单方式查询--查询所有订单的预算利润）--走分页
     *
     * @param pageBean
     * @param bizId
     * @return
     */
    List<GroupOrder> selectProfitByConListPage(@Param("page") PageBean<GroupOrder> pageBean,
                                               @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);
    /**
     * 预算利润查询统计所有成人、小孩、全陪数（带查询条件）
     *
     * @param pageBean
     * @param bizId
     * @return
     */
    GroupOrder selectProfitByCon(@Param("page") PageBean<GroupOrder> pageBean,
                                 @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);

    /**
     * 预算利润查询统计总收入、总成本（带查询条件）
     *
     * @param pageBean
     * @param bizId
     * @return
     */
    GroupOrder selectProfitByConAndMode(@Param("page") PageBean<GroupOrder> pageBean,
                                        @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);

    /**
     * 根据订单和订单收客情况进行产品收客统计
     *
     * @param condition
     * @return
     */
    List<ProductGuestStaticsVo> productGuestStatics(
            @Param("condition") ProductGuestCondition condition,
            @Param("set") Set<Integer> userIds);

    /**
     * 查询客源分布
     *
     * @param condition
     * @return
     */
    List<Map<String, Object>> guestSourceStatics(
            @Param("condition") ProductGuestCondition condition,
            @Param("set") Set<Integer> userIds);

    List<Map<String, Object>> guestSourceStatics2(@Param("parameter") Map parameter);

    List<Map<String, Object>> selectIncomeJoinTableListPage(
            @Param("page") PageBean<GroupOrder> pageBean, @Param("bizId") Integer bizId);

    /**
     * 应收款明细导出数据
     *
     * @param pageBean
     * @param bizId
     * @return
     */
    List<GroupOrder> selectPaymentDetailList(@Param("page") PageBean<PaymentExportVO> pageBean,
                                             @Param("bizId") Integer bizId, @Param("set") Set<Integer> userIds);

    List<Map<String, Object>> getGroupInfoByGroupId(@Param("bizId") Integer bizId,
                                                    @Param("groupId") Integer groupId);

    /**
     * 根据团id获取订单的一些信息 add by ge 根据根据团id查询组团社
     *
     * @param groupId
     * @return
     */
    List<Map<String, Object>> getOrderBreifInfoByGroupId(@Param("groupId") Integer groupId);

    /**
     * 查询所有团
     *
     * @param pageBean
     * @param curBizId
     * @param dataUserIdSet
     * @return
     */
    List<GroupOrder> selectGroupNumForQuery(@Param("groupOrder") GroupOrder groupOrder,
                                            @Param("bizId") Integer curBizId, @Param("set") Set<Integer> dataUserIdSet);

    /**
     * 销售计调业绩统计
     *
     * @param pageBean
     * @param bizId
     * @return
     */
    List<SaleOperatorOrderStatic> selectSaleOperatorOrderStaticListPage(
            @Param("page") PageBean<SaleOperatorOrderStatic> pageBean,
            @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);

    SaleOperatorOrderStatic selectSaleOperatorOrderStaticCon(
            @Param("page") PageBean<SaleOperatorOrderStatic> pageBean,
            @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);

    List<GroupOrder> selectSupplierByGroupId(@Param("groupId") Integer groupId);

    List<GroupOrder> selectSupplierByGroupIdAndSupplierId(@Param("groupId") Integer groupId,
                                                          @Param("supplierId") Integer supplierId);

    void updateGroupOrderPersonNum(Integer orderId);

    void updateTourGroupPersonNum(Integer groupId);

    void updateOrderPrice(Integer orderId);

    void updateGroupPrice(Integer groupId);
    
    void updateOrderLockState(Integer orderId);
    
    void updateOrderLockStateByOp(Integer orderId);
    
    void goBackOrderLockStateByOp(Integer orderId);
    
    void updateLockStateToFinance(Integer orderId);
    
    List<GroupOrder> selectOrderByGroupIdAndBizIdAndSupplierId(@Param("groupId") Integer groupId,
                                                               @Param("supplierId") Integer supplierId, @Param("bizId") Integer bizId);

    // 一地散
    List<GroupOrder> selectSpecialOrderListPage(@Param("page") PageBean<GroupOrder> pageBean,
                                                @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);

    // 爱游淘宝
    List<GroupOrder> selectTaobaoOrderListPage(@Param("page") PageBean<GroupOrder> pageBean,
                                               @Param("bizId") Integer bizId, @Param("set") Set<Integer> set, @Param("userRightType") Integer userRightType);
    
    List<GroupOrder> selectTaobaoOrderGuestNameListPage(@Param("page") PageBean<GroupOrder> pageBean,
                                                        @Param("bizId") Integer bizId, @Param("set") Set<Integer> set, @Param("userRightType") Integer userRightType);
    
    /**
     *  @see 内部结算销售
     * @Auther TengDong
     * @Date 20161102
     * @param pageBean
     * @param bizId
     * @param set
     * @param userRightType
     * @return GroupOrder
     */
    List<GroupOrder> selectProfitEverifyListPage(@Param("page") PageBean<GroupOrder> pageBean,
                                                 @Param("bizId") Integer bizId, @Param("set") Set<Integer> set, @Param("userRightType") Integer userRightType);
    
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
    GroupOrder selectProfitEverifyByCon(@Param("groupOrder") GroupOrder groupOrder,
                                        @Param("bizId") Integer bizId, @Param("set") Set<Integer> set, @Param("userRightType") Integer userRightType);
    
    Map<String, Object> selectProfitEverifyByTotal(@Param("page") PageBean pageBean, @Param("bizId") Integer bizId,
                                                   @Param("set") Set<Integer> set, @Param("userRightType") Integer userRightType);
	
    
    GroupOrder selectTotalSpecialOrder(@Param("groupOrder") GroupOrder groupOrder,
                                       @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);
    
    GroupOrder selectTotalTaobaoOrder(@Param("groupOrder") GroupOrder groupOrder,
                                      @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);
    
    GroupOrder selectTotalTaobaoGuestNameOrder(@Param("groupOrder") GroupOrder groupOrder,
                                               @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);
    /**
     * 根据接站牌获取团id列表 预定安排，查询条件
     *
     * @param bizId
     * @param receiveMode
     * @return
     */
    List<Map<String, Object>> selectGroupIdsByReceiveMode(TourGroupVO group);

    List<GroupOrder> selectOrderLockByConListPage(@Param("page") PageBean<GroupOrder> pageBean,
                                                  @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);

    List<GroupOrder> selectOrderByParameterListPage(@Param("page") PageBean pageBean,
                                                    @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);

    List<GroupOrder> selectOrderByParameter2(@Param("parameter") Map map);

    List<SaleOperatorVo> selectSaleOperatorByConListPage(
            @Param("page") PageBean<SaleOperatorVo> pageBean, @Param("bizId") Integer bizId,
            @Param("set") Set<Integer> set);

    Map<String, Object> selectSaleOperatorTotalPerson(
            @Param("page") PageBean<SaleOperatorVo> pageBean, @Param("bizId") Integer bizId,
            @Param("set") Set<Integer> set);

    String selectOrderLockByCon(@Param("page") PageBean<GroupOrder> pageBean,
                                @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);

    List<SalePrice> getSalePriceListPage(@Param("page") PageBean<SalePrice> pageBean,
                                         @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);

    BigDecimal getSalePriceToalPrice(@Param("page") PageBean<SalePrice> pageBean,
                                     @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);

    // 初始订单
    List<GroupOrder> selectInitOrderList(@Param("bizId") Integer bizId,
                                         @Param("groupId") Integer groupId);

    List<GroupOrder> selectOrderGroupByProductIdListPage(
            @Param("page") PageBean<GroupOrder> pageBean, @Param("bizId") Integer bizId,
            @Param("set") Set<Integer> set);

    GroupOrder selectOrderGroupByProductIdList(@Param("page") PageBean<GroupOrder> pageBean,
                                               @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);

    List<GroupOrder> getDeservedCashGroupByOrderIdListPage(@Param("page") PageBean pageBean,
                                                           @Param("set") Set<Integer> set);

    Map<String, Object> getDeservedCashGroupByOrderIdTotal(@Param("page") PageBean pageBean,
                                                           @Param("set") Set<Integer> set);

    /**
     * 查询订单下成人人数
     *
     * @param orderId
     * @return
     */
    Integer selectTotalNumAdultByOrderId(@Param("orderId") Integer orderId);

    /**
     * 查询某个订单下所有人数
     *
     * @param orderId
     * @return
     */
    Integer selectTotalNumByOrderId(@Param("orderId") Integer orderId);

    /**
     * 应收款统计预览
     *
     * @param pageBean
     * @param bizId
     * @param set
     * @return
     */
    List<GroupOrder> selectPaymentStaticData(@Param("page") PageBean<GroupOrder> pageBean,
                                             @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);

    List<GroupOrder> selectPaymentStaticData2(@Param("page") PageBean<GroupOrder> pageBean,
                                              @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);

    /**
     * 部门订单分析统计部分
     *
     * @param pageBean
     * @param bizId
     * @param set
     * @return
     */
    List<DepartmentOrderResult> departmentOrderStatistics(
            @Param("condition") DeparentmentOrderCondition condition,
            @Param("set") Set<Integer> set);

    List<GroupOrder> selectReserveOrderList();

    List<GroupOrder> selectAiYouOrders(@Param("bizId") Integer bizId,
                                       @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 根据产品和日期统计人数 用于产品库存对账
     *
     * @param bizId
     * @param productId
     * @param date
     * @return
     */
    Map<String, Object> getCountByProductIdAndDate(@Param("bizId") Integer bizId,
                                                   @Param("productId") Integer productId, @Param("date") Date date);

    List<GroupOrder> selectTopAiYouOrders(@Param("bizId") Integer bizId,
                                          @Param("startTime") String startTime, @Param("limit") Integer limit);

    /**
     * 某个时间段的总应收和总已收，只用到groupOrder中的total、total_cash
     *
     * @param pageBean
     * @param bizId
     * @param set
     * @return
     */
    public GroupOrder selectTotalStatic(@Param("page") PageBean<PaymentExportVO> pageBean,
                                        @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);

    /**
     * 查询团订单的已付款金额
     *
     * @param groupOrderId
     * @return
     */
    GroupOrder selectIncomeOrderTotalCash(@Param("groupOrderId") Integer groupOrderId);

    List<GroupOrder> selectIdList(@Param("supplierId") Integer supplierId);

    Integer existgroupOrder(@Param("supplierId") Integer supplierId);

    /**
     * B2B 查询销售订单信息
     *
     * @param dateStart
     *            出团日期
     * @param dateEnd
     *            出团结束日期
     * @param clientName
     *            组团社名称
     * @return 销售订单情况
     */
    List<GroupOrder> selectGroupOrderListToB2B(@Param("bizId") Integer bizId,
                                               @Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd,
                                               @Param("clientName") String clientName, @Param("exports") Integer exports);

    /**
     * B2B 更新导出订单状态
     *
     * @param ids
     *            订单ID
     * @return 是否成功
     */
    boolean updateB2bExportState(@Param("ids") String ids);

    /**
     * B2B 查询某些订单的详细信息
     *
     * @param ids
     *            订单ID
     * @return 某些订单的详细信息
     */
    List<GroupOrder> selectGroupOrderDetailByIds(@Param("ids") String ids);

    /**
     * 产品收客趋势查询
     * 
     * @param pageBean
     * @param set
     * @return
     */
    List<GroupOrder> selectProductTrendListPageInnerLayer(@Param("page") PageBean pageBean,
                                                          @Param("parameter") Map map);

    List<GroupOrder> selectProductTrendListPageOutLayer(@Param("page") PageBean pageBean,
                                                        @Param("parameter") Map parameters);

    List<GroupOrder> selectGroupOrderById(@Param("orderId") Integer orderId);

    List<GroupOrder> findResOrderListPage(@Param("page") PageBean pageBean,
                                          @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);

    int updateResOrderState(GroupOrder record);

    HashMap<String, BigDecimal> sumGroupOrder(PageBean<GroupOrder> pageBean);

    List<GroupOrder> findResAdminOrderListPage(@Param("page") PageBean pageBean,
                                               @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);

    int updateResAdminOrderState(GroupOrder record);

    HashMap<String, BigDecimal> sumResAdminOrder(PageBean<GroupOrder> pageBean);

    Integer updateExtResState(GroupOrder record);
    
    Integer updateExtVisa(GroupOrder record);
    
    List<GroupOrder> selectByOrderIdExtVisaList(@Param("mobile") String mobile);

    int delGroupOrderId(@Param("id") Integer id);

    List<GroupOrder> selectOrderOverTime();

    List<GroupOrder> selectByTimeListPage(@Param("page") PageBean<GroupOrder> pageBean,
                                          @Param("bizId") Integer bizId);

    GroupOrder selectByPushOrderId(Integer pushOrderId);
	
	int selectSumPersonByProductId(@Param("resId") Integer resId, @Param("productId") Integer productId, @Param("departureDate") String departureDate);
	
	List<GroupOrder> selectBySaleOperatorId(@Param("bizId") Integer bizId, @Param("condition") DeparentmentOrderCondition condition, @Param("set") Set<Integer> set);
	
	List<Map<String,Object>> selectGroupOrderGuestListPage(@Param("page") PageBean<GroupOrder> pageBean,
                                                           @Param("bizId") Integer bizId, @Param("set") Set<Integer> set, @Param("userRightType") Integer userRightType);
	
    List<Map<String,Object>> selectGroupOrderGuestListPageOu(@Param("page") PageBean<GroupOrder> pageBean,
            @Param("bizId") Integer bizId,@Param("set") Set<Integer> set,@Param("userRightType") Integer userRightType,@Param("sidx")String sidx,@Param("sord")String sord);
	
	List<GroupOrder> selectgroupIdByAY(@Param("page") PageBean<GroupOrder> pageBean, @Param("bizId") Integer bizId);
	
	List<GroupOrder>selectMonthlyReportStatisticsListPage(@Param("page") PageBean<GroupOrder> pageBean, @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);
    /**
     *导出用（不分页）
     * 
     * @param pageBean
     * @param set
     * @return
     */
	List<GroupOrder>selectMonthlyReportStatistics(@Param("page") PageBean<GroupOrder> pageBean, @Param("bizId") Integer bizId, @Param("set") Set<Integer> set);
	
	List<GroupOrder>selectProductProfitStatisticsListPage(@Param("page") PageBean<GroupOrder> pageBean,@Param("bizId") Integer bizId);

    List<GroupOrderForCarCar> selectGroupOrdersInGroupsForCarCar(@Param("page")PageBean pageBean);
//    List<GroupOrderForCarCar> selectGroupOrdersInGroupsForCarCarTest(@Param("page")com.yimayhd.erpcenter.common.util.PageBean pageBean);
    /**
     * 根据团id 获取旅游团的所有的游客
     * @author daixiaoman
     * @date 2016年12月5日 上午10:00:19
     */
    List<GroupOrderGuest> selectAllOrderGuestByGroupId(int groupId);

    /**
     * 应收应付统计
     * @author zhoumi
     */
    List<GroupOrder>selectPaymentStatisticsListPage(@Param("page") PageBean<GroupOrder> pageBean,@Param("bizId") Integer bizId);
	
}