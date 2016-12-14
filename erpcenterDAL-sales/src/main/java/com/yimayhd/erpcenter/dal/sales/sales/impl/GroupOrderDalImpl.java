package com.yimayhd.erpcenter.dal.sales.sales.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.*;
import com.yimayhd.erpcenter.dal.sales.client.sales.query.GroupOrderQueryForCarCar;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.common.solr.SolrSearchPageDTO;
import com.yimayhd.erpcenter.common.util.NumberUtil;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceCommission;
import com.yimayhd.erpcenter.dal.sales.client.finance.service.FinanceDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingDeliveryDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingSupplierDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.PaymentExportVO;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.DeparentmentOrderCondition;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.DepartmentOrderResult;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupOrderDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupRequirementDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupRouteDal;
import com.yimayhd.erpcenter.dal.sales.client.solr.dto.GroupOrderDTO;
import com.yimayhd.erpcenter.dal.sales.client.solr.query.GroupOrderPageQueryDTO;
import com.yimayhd.erpcenter.dal.sales.finance.dao.FinanceCommissionDeductionMapper;
import com.yimayhd.erpcenter.dal.sales.finance.dao.FinanceCommissionMapper;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingDeliveryMapper;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingSupplierMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderGuestMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderPriceMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderTransportMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupRequirementMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupRouteMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.TourGroupMapper;
import com.yimayhd.erpcenter.dal.sales.sales.util.GenerateCodeUtil;
import com.yimayhd.erpcenter.dal.sales.solr.sales.converter.FitOrderConverter;
import com.yimayhd.erpcenter.dal.sales.solr.sales.converter.LockOrderConverter;
import com.yimayhd.erpcenter.dal.sales.solr.sales.converter.ProfitOrderConverter;
import com.yimayhd.erpcenter.dal.sales.solr.sales.converter.SaleOrderConverter;
import com.yimayhd.erpcenter.dal.sales.solr.sales.converter.SpecialOrderConverter;
import com.yimayhd.erpcenter.dal.sales.solr.sales.manage.SalesSolrQueryManage;
import org.springframework.util.CollectionUtils;

public class GroupOrderDalImpl implements GroupOrderDal {
    private static final Logger log = LoggerFactory
            .getLogger(GroupOrderDalImpl.class);
    @Autowired
    private GroupOrderMapper groupOrderMapper;
    @Autowired
    private GroupOrderGuestMapper groupOrderGuestMapper;
    @Autowired
    private GroupOrderPriceMapper groupOrderPriceMapper;
    @Autowired
    private TourGroupMapper tourGroupMapper;
    @Autowired
    private BookingDeliveryMapper bookingDeliveryMapper;
    @Autowired
    private BookingSupplierMapper bookingSupplierMapper;
//    @Autowired
//    private TourGroupDal tourGroupDal;
    @Autowired
    private FinanceDal financeDal;
    @Autowired
    private GroupRequirementDal groupRequirementDal;
    @Autowired
    private GroupRouteDal groupRouteDal;
    @Autowired
    private BookingSupplierDal bookingSupplierDal;
    @Autowired
    private BookingDeliveryDal bookingDeliveryDal;
    @Autowired
    private GroupRouteMapper groupRouteMapper;
    @Autowired
    private FinanceCommissionMapper financeCommissionMapper;
    @Autowired
    private FinanceCommissionDeductionMapper financeCommissionDeductionMapper;
    @Autowired
    private SalesSolrQueryManage salesSolrQueryManage;
    
    @Autowired
    private GroupRequirementMapper requirementMapper;
    
    @Autowired
    private GroupOrderTransportMapper transportMapper;
    
    
    @Override
    public GroupOrder findById(Integer id) {
    	if(id <=0){
    		return null;
    	}
        GroupOrder order = groupOrderMapper.selectByPrimaryKey(id);
        TourGroup group = tourGroupMapper.selectByPrimaryKey(order.getGroupId());
        if (group != null) {
            order.setGroupMode(group.getGroupMode());
            order.setGroupCode(group.getGroupCode());
        }
        return order;
    }

    public GroupOrder findOrderById(Integer bizId, Integer id) {
        return groupOrderMapper.selectOrderByPrimaryKey(bizId, id);
    }

    /**
     * return {id: groupOrder}
     */
    public HashMap<Integer, GroupOrder> findOrderByIdList(Integer bizId, List<Integer> idList) {
        String strIdList = StringUtils.join(idList, ',');
        HashMap<Integer, GroupOrder> orderMap = new HashMap<Integer, GroupOrder>();
        if (idList.size() == 0) {
            return orderMap;
        }
        List<GroupOrder> orderList = groupOrderMapper.selectOrderByIdList(bizId, strIdList);
        for (GroupOrder order : orderList) {
            orderMap.put(order.getId(), order);
        }
        return orderMap;
    }

    @Transactional
    @Override
    public void saveGroupOrder(GroupOrderVO groupOrderVO,
                               GroupRouteVO groupRouteVO, List<GroupOrderPrice> insertList) {
        GroupOrder groupOrder = groupOrderVO.getGroupOrder();
        GroupOrder orderCodeSort = groupOrderMapper.selectGroupOrderCodeSort(groupOrder.getBizId(),
                groupOrder.getDepartureDate());
        groupOrder.setOrderNoSort(orderCodeSort == null ? 1 : orderCodeSort
                .getOrderNoSort() + 1);

        String makeCodeByMode = GenerateCodeUtil.makeCodeByMode(groupOrder.getBizId(),
                groupOrder.getOrderNo(), groupOrder.getDepartureDate(),
                orderCodeSort == null ? 1 : orderCodeSort.getOrderNoSort() + 1);
        groupOrder.setOrderNo(makeCodeByMode);

        groupOrderMapper.insert(groupOrder);
        List<GroupOrderGuest> groupOrderGuestList = groupOrderVO
                .getGroupOrderGuestList();
        if (groupOrderGuestList != null && groupOrderGuestList.size() > 0) {
            for (GroupOrderGuest groupOrderGuest : groupOrderGuestList) {
                groupOrderGuest.setOrderId(groupOrder.getId());
                groupOrderGuest.setCreateTime(System.currentTimeMillis());
                groupOrderGuestMapper.insertSelective(groupOrderGuest);
            }
        }

        List<GroupOrderPrice> groupOrderPriceList = groupOrderVO
                .getGroupOrderPriceList();

        if (groupOrderPriceList != null && groupOrderPriceList.size() > 0) {
            for (GroupOrderPrice groupOrderPrice : groupOrderPriceList) {
                // 收入插入
                groupOrderPrice.setOrderId(groupOrder.getId());
                groupOrderPrice.setMode(0);
                groupOrderPrice.setCreateTime(System.currentTimeMillis());
                groupOrderPrice.setTotalPrice(groupOrderPrice.getUnitPrice()
                        * groupOrderPrice.getNumPerson()
                        * groupOrderPrice.getNumTimes());
                groupOrderPriceMapper.insertSelective(groupOrderPrice);
                // 成本插入
                groupOrderPrice.setId(null);
                groupOrderPrice.setUnitPrice(groupOrderPrice.getPrimeCost());
                groupOrderPrice.setMode(1);
                groupOrderPrice.setTotalPrice(groupOrderPrice.getUnitPrice()
                        * groupOrderPrice.getNumPerson()
                        * groupOrderPrice.getNumTimes());
                groupOrderPriceMapper.insertSelective(groupOrderPrice);

            }
            log.debug("插入groupOrderPrice成功..............");
        }
        if (insertList != null && insertList.size() > 0) {

            for (GroupOrderPrice groupOrderPrice : insertList) {
                groupOrderPrice.setOrderId(groupOrder.getId());
                groupOrderPriceMapper.insertSelective(groupOrderPrice);
            }

        }

        List<GroupRouteDayVO> groupRouteDayVOList = groupRouteVO
                .getGroupRouteDayVOList();
        if (groupRouteDayVOList != null && groupRouteDayVOList.size() > 0) {
            for (GroupRouteDayVO groupRouteDayVO : groupRouteDayVOList) {
                GroupRoute groupRoute = groupRouteDayVO.getGroupRoute();
                groupRoute.setGroupId(null);
                groupRoute.setOrderId(groupOrder.getId());
                groupRoute.setCreateTime(System.currentTimeMillis());
            }
        }
        groupRouteDal.saveGroupRoute(groupRouteVO);
    }

    @Transactional
    @Override
    public void updateGroupOrder(GroupOrder groupOrder,
                                 List<GroupOrderGuest> list) {
        groupOrderMapper.updateByPrimaryKeySelective(groupOrder);
        List<GroupOrderGuest> gogList = groupOrderGuestMapper.selectByOrderId(groupOrder.getId());
        for (GroupOrderGuest groupOrderGuest : gogList) {
            groupOrderGuestMapper.deleteByPrimaryKey(groupOrderGuest.getId());
        }
        for (GroupOrderGuest groupOrderGuest : list) {
            groupOrderGuestMapper.insertSelective(groupOrderGuest);
        }
    }

    @Override
    public void delGroupOrder(Integer id) {
        groupOrderMapper.delGroupOrder(id);
    }

    @Override
    public GroupOrder insertSelective(GroupOrder groupOrder) {
        groupOrderMapper.insertSelective(groupOrder);
        return groupOrder;
    }

    @Override
    public PageBean selectNotGroupListPage(PageBean pageBean, Integer bizId,
                                           Set<Integer> set) {
        List<GroupOrder> result = groupOrderMapper.selectNotGroupListPage(
                pageBean, bizId, set);
        pageBean.setResult(result);
        return pageBean;
    }

    @Override
    public GroupOrder selectTotalNotGroup(GroupOrder groupOrder, Integer bizId,
                                          Set<Integer> set) {
        return groupOrderMapper.selectTotalNotGroup(groupOrder, bizId, set);
    }


    @Override
    public PageBean selectFitOrderListPage(PageBean pageBean, Integer bizId,
                                           Set<Integer> set, Integer listType) {
        if(1==1){
        	 List<GroupOrder> result = groupOrderMapper.selectFitOrderListPage(
                     pageBean, bizId, set, listType);
             pageBean.setResult(result);
        }else{
        	GroupOrderPageQueryDTO queryDTO=FitOrderConverter.toQueryDTO( pageBean, bizId, set, listType);
        	SolrSearchPageDTO<GroupOrderDTO> solrPageResult=salesSolrQueryManage.searchFitOrder(queryDTO);
        	return FitOrderConverter.dto2PageBean(solrPageResult,pageBean);
        }
        return pageBean;
    }

    @Override
    public GroupOrder selectFitOrderTotalCount(GroupOrder groupOrder, Integer bizId,
                                               Set<Integer> set, Integer listType) {
       
        if(1==1){
        	 return groupOrderMapper.selectFitOrderTotalCount(groupOrder, bizId, set, listType);
        }else{
        	PageBean<GroupOrder> pageBean=new PageBean<GroupOrder>();
    		pageBean.setParameter(groupOrder);
    		GroupOrderPageQueryDTO queryDTO=FitOrderConverter.toQueryDTO(pageBean, bizId, set,listType);
        	return salesSolrQueryManage.searchSalesOrderTotal(queryDTO);
        }
    }

    @Override
    public void updateGroupOrder(GroupOrder groupOrder) {
        groupOrderMapper.updateByPrimaryKeySelective(groupOrder);

    }

    @Transactional
    @Override
    public void mergeGroup(List<MergeGroupOrderVO> list, Integer bizId,
                           Integer operid, String operName, String supplierCode)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (MergeGroupOrderVO mergeGroupOrderVO2 : list) {
            List<GroupOrder> orderList2 = mergeGroupOrderVO2.getOrderList();
            List<Integer> idList = new ArrayList<Integer>();
            int total = 0;
            for (GroupOrder groupOrder : orderList2) {
                total += groupOrder.getTotal().floatValue();
                idList.add(groupOrder.getId());
            }
            TourGroup tourGroup = mergeGroupOrderVO2.getTourGroup();
            tourGroup.setId(null);
            tourGroup.setBizId(bizId);
            tourGroup.setOrderNum(orderList2.size());
            tourGroup.setGroupMode(0);
            TourGroup selectGroupCodeSort = tourGroupMapper
                    .selectGroupCodeSort(bizId, 0, orderList2.get(0).getDepartureDate());
            String makeCodeByMode = GenerateCodeUtil.makeCodeByMode(
                    supplierCode,
                    0,
                    orderList2.get(0).getDepartureDate(), "",
                    selectGroupCodeSort == null ? 1 : selectGroupCodeSort
                            .getGroupCodeSort() + 1);
            tourGroup.setGroupCodeSort(selectGroupCodeSort == null ? 1
                    : selectGroupCodeSort.getGroupCodeSort() + 1);
            tourGroup.setGroupCode(makeCodeByMode);
            tourGroup.setGroupState(1); // 默认已确认2015-12-08【欧】
            tourGroup.setPrudctBrandId(orderList2.get(0).getProductBrandId());
            tourGroup.setProductBrandName(orderList2.get(0)
                    .getProductBrandName());
            tourGroup.setProductId(orderList2.get(0).getProductId());
            tourGroup.setProductName(orderList2.get(0).getProductName());
            tourGroup.setOperatorId(operid);
            tourGroup.setOperatorName(operName);
            tourGroup.setDateStart(sdf.parse(orderList2.get(0)
                    .getDepartureDate()));
            tourGroup.setTotalIncome(new BigDecimal(total));
            tourGroup.setCreateTime(System.currentTimeMillis());
            tourGroupMapper.insertSelective(tourGroup);
            bookingSupplierDal.updateOperationAfterMergeGroupOrder(tourGroup.getId(), idList);
            for (GroupOrder groupOrder : orderList2) {
                groupOrder.setGroupId(tourGroup.getId());
                groupOrder.setOperatorId(operid);
                groupOrder.setOperatorName(operName);
                groupOrderMapper.updateByPrimaryKeySelective(groupOrder);
                List<GroupRequirement> list2 = groupRequirementDal
                        .selectByOrderId(groupOrder.getId());
                if (list2 != null && list2.size() > 0) {
                    for (GroupRequirement groupRequirement : list2) {
                        groupRequirement.setGroupId(tourGroup.getId());
                        groupRequirementDal
                                .updateByPrimaryKeySelective(groupRequirement);
                    }
                }
            }
            updateGroupPersonNum(tourGroup.getId());
            GroupRouteVO groupRouteVO = mergeGroupOrderVO2.getGroupRouteVO();
            groupRouteVO.setTourGroup(tourGroup);
            List<GroupRouteDayVO> groupRouteDayVOList = groupRouteVO
                    .getGroupRouteDayVOList();
            if (groupRouteDayVOList != null && groupRouteDayVOList.size() > 0) {
                for (GroupRouteDayVO groupRouteDayVO : groupRouteDayVOList) {
                    GroupRoute groupRoute = groupRouteDayVO.getGroupRoute();
                    groupRoute.setId(null);
                    groupRoute.setGroupId(tourGroup.getId());
                    groupRoute.setOrderId(null);
                    groupRoute.setCreateTime(System.currentTimeMillis());
                }
            }
            groupRouteDal.saveGroupRoute(groupRouteVO);


        }


    }

    // 获得当天0点时间
    public long getTimesmorning(String dateTime) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateTime));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    // 获得当天24点时间
    public long getTimesnight(String dateTime) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateTime));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    @Override
    public String makeCodeByMode(Integer bizId, String bizCode,
                                 String dateTime, int sort) {
        String code = bizCode + dateTime.replace("-", "") + sort;
        return code;
    }

    @Override
    public PageBean<GroupOrder> selectByConListPage(
            PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set, Integer listType) {
        
        if(1==1){
        	List<GroupOrder> result = groupOrderMapper.selectByConListPage(
                    pageBean, bizId, set, listType);
            pageBean.setResult(result);
        }else{
        	GroupOrderPageQueryDTO queryDTO=SaleOrderConverter.toQueryDTO( pageBean, bizId, set, listType);
        	SolrSearchPageDTO<GroupOrderDTO> solrPageResult=salesSolrQueryManage.searchSalesOrder(queryDTO);
        	return SaleOrderConverter.dto2PageBean(solrPageResult,pageBean);
        }
        
        return pageBean;
    }

    @Override
    public List<GroupOrder> selectOrderByGroupId(Integer groupId) {
        return groupOrderMapper.selectOrderByGroupId(groupId);
    }

    @Override
    public GroupOrder selectByPrimaryKey(Integer id) {
        return groupOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageBean<GroupOrder> selectProfitByConListPage(
            PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set) {
       
        if(1==1){
        	 List<GroupOrder> orders = groupOrderMapper.selectProfitByConListPage(pageBean, bizId, set);
             pageBean.setResult(orders);
        }else{
        	GroupOrderPageQueryDTO queryDTO=ProfitOrderConverter.toQueryDTO( pageBean, bizId, set);
        	SolrSearchPageDTO<GroupOrderDTO> solrPageResult=salesSolrQueryManage.searchProfitOrder(queryDTO);
        	return ProfitOrderConverter.dto2PageBean(solrPageResult,pageBean);
        }
        return pageBean;
    }


    public PageBean<GroupOrder> selectAllOrderByConListPage(PageBean<GroupOrder> pageBean, Integer bizId) {
        List<GroupOrder> result = groupOrderMapper.selectAllOrderByConListPage(pageBean, bizId);
        pageBean.setResult(result);
        return pageBean;
    }

    @Override
    public GroupOrder selectTotalByCon(GroupOrder groupOrder,
                                       Integer bizId, Set<Integer> set, Integer listType) {
    	
    	if(1==1){
    		return groupOrderMapper.selectTotalByCon(groupOrder, bizId, set, listType);
    	}else{
    		PageBean<GroupOrder> pageBean=new PageBean<GroupOrder>();
    		pageBean.setParameter(groupOrder);
    		GroupOrderPageQueryDTO queryDTO=SaleOrderConverter.toQueryDTO(pageBean, bizId, set, listType);
        	return salesSolrQueryManage.searchSalesOrderTotal(queryDTO);
    	}
        
    }

    @Override
    public List<GroupOrder> selectOrderByGroupIdAndBizId(Integer groupId,
                                                         Integer bizId) {
        return groupOrderMapper.selectOrderByGroupIdAndBizId(groupId, bizId);
    }

    @Override
    public GroupOrder selectProfitByCon(
            PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set) {
    	if(1==1){
    		return groupOrderMapper.selectProfitByCon(pageBean, bizId, set);
    	}else{
    		GroupOrderPageQueryDTO queryDTO=ProfitOrderConverter.toQueryDTO(pageBean, bizId, set);
        	return salesSolrQueryManage.searchProfitOrderTotal(queryDTO);
    	}
        
    }

    @Override
    public GroupOrder selectProfitByConAndMode(PageBean<GroupOrder> pageBean,
                                               Integer bizId, Set<Integer> set) {
       //同selectProfitByCon，在一次solr查询中已经把总人数和总金额都查出来了
        if(1==1){
        	 return groupOrderMapper.selectProfitByConAndMode(pageBean, bizId, set);
    	}else{
    		GroupOrderPageQueryDTO queryDTO=ProfitOrderConverter.toQueryDTO(pageBean, bizId, set);
        	return salesSolrQueryManage.searchProfitOrderTotal(queryDTO);
    	}
    }

    @Override
    public List<GroupOrder> selectPaymentDetailList(
            PageBean<PaymentExportVO> pageBean, Integer bizId, Set<Integer> set) {
        return groupOrderMapper.selectPaymentDetailList(pageBean, bizId, set);
    }

    @Override
    public List<Map<String, Object>> getGroupInfoByGroupId(Integer bizId,
                                                           Integer groupId) {
        return groupOrderMapper.getGroupInfoByGroupId(bizId, groupId);
    }

    @Override
    public List<GroupOrder> selectGroupNumForQuery(GroupOrder groupOrder, Integer curBizId,
                                                   Set<Integer> dataUserIdSet) {
        List<GroupOrder> list = groupOrderMapper.selectGroupNumForQuery(groupOrder, curBizId, dataUserIdSet);
        return list;
    }

    @Override
    public PageBean<SaleOperatorOrderStatic> selectSaleOperatorOrderStaticListPage(
            PageBean<SaleOperatorOrderStatic> pageBean, Integer bizId, Set<Integer> set) {
        List<SaleOperatorOrderStatic> list = groupOrderMapper.selectSaleOperatorOrderStaticListPage(pageBean, bizId, set);
        pageBean.setResult(list);
        return pageBean;
    }

    @Override
    public SaleOperatorOrderStatic selectSaleOperatorOrderStaticCon(
            PageBean<SaleOperatorOrderStatic> pageBean, Integer bizId, Set<Integer> set) {
        return groupOrderMapper.selectSaleOperatorOrderStaticCon(pageBean, bizId, set);
    }

    @Override
    public List<GroupOrder> selectSupplierByGroupId(Integer groupId) {
        return groupOrderMapper.selectSupplierByGroupId(groupId);
    }

   
    @Transactional
    @Override
    public void updateGroupPersonNum(Integer groupId) {
        groupOrderMapper.updateTourGroupPersonNum(groupId);
        bookingDeliveryDal.updateDeliveryGuestCountOnModifySKGuestCount(groupId);

    }

    @Override
    public void updateOrderAndGroupPrice(Integer orderId) {
        GroupOrder groupOrder = groupOrderMapper.selectByPrimaryKey(orderId);
        groupOrderMapper.updateOrderPrice(orderId);
        if (groupOrder.getGroupId() != null) {
            financeDal.calcTourGroupAmount(groupOrder.getGroupId());
        }

    }

    @Override
    public void updateGroupPrice(Integer groupId) {
        financeDal.calcTourGroupAmount(groupId);
    }

    @Override
    public void changeOrderLockState(Integer orderId) {
    	groupOrderMapper.updateOrderLockState(orderId);
    }

    @Override
    public void changeorderLockStateByOp(Integer orderId) {
    	groupOrderMapper.updateOrderLockStateByOp(orderId);
    }

    @Override
    public void goBackOrderLockStateByOp(Integer orderId) {
    	groupOrderMapper.goBackOrderLockStateByOp(orderId);
    }

    @Override
    public void updateLockStateToFinance(Integer orderId) {
    	GroupOrder groupOrder=groupOrderMapper.selectByPrimaryKey(orderId);
    	if(groupOrder.getGroupId() != null){
    	List<GroupOrder> lists=groupOrderMapper.selectOrderByGroupId(groupOrder.getGroupId());
    	for(GroupOrder item:lists){
    		groupOrderMapper.updateLockStateToFinance(item.getId());
    	}
    	}
    }

    @Override
    public void goBackToOP(Integer orderId) {
    	GroupOrder groupOrder=groupOrderMapper.selectByPrimaryKey(orderId);
    	if(groupOrder.getGroupId() != null){
    	List<GroupOrder> lists=groupOrderMapper.selectOrderByGroupId(groupOrder.getGroupId());
    	for(GroupOrder item:lists){
    		groupOrderMapper.updateOrderLockStateByOp(item.getId());
    	}
    	}
    }

    @Override
    public List<GroupPriceVo> selectSupplierByGroupIdAndSupplierId(Integer groupId,
            Integer supplierId) {
        List<GroupOrder> orders = groupOrderMapper.selectSupplierByGroupIdAndSupplierId(groupId,
                supplierId);
        List<GroupPriceVo> vos = new ArrayList<GroupPriceVo>();
        GroupPriceVo vo = null;
        for (GroupOrder order : orders) {
            vo = new GroupPriceVo();
            Double totalPrice = Double.parseDouble("0");
            vo.setReceiveMode(order.getReceiveMode());
            List<GroupOrderPrice> prices = groupOrderPriceMapper.selectByOrderAndType(order.getId(), 0); //统计收入
            StringBuilder sb = new StringBuilder();
            for (GroupOrderPrice price : prices) {
                sb.append(price.getItemName() + " " + (price.getRemark() == null ? "" : price.getRemark()) + " " + NumberUtil.formatDouble(price.getUnitPrice()) + "*" +
                        NumberUtil.formatDouble(price.getNumTimes()) + "*" +
                        NumberUtil.formatDouble(price.getNumPerson()) + "=" + NumberUtil.formatDouble(price.getTotalPrice()) + "\n");
                totalPrice += price.getTotalPrice();
            }
            vo.setPriceDetail(sb.toString());
            vo.setTotalPrice(totalPrice);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<GroupOrder> selectOrderByGroupIdAndBizIdAndSupplierId(
            Integer groupId, Integer supplierId, Integer bizId) {
        return groupOrderMapper.selectOrderByGroupIdAndBizIdAndSupplierId(groupId, supplierId, bizId);
    }


    @Override
    public PageBean<GroupOrder> selectSpecialOrderListPage(
            PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set) {
        if(1==1){
        	List<GroupOrder> list = groupOrderMapper.selectSpecialOrderListPage(pageBean, bizId, set);
            pageBean.setResult(list);
        }else{
        	GroupOrderPageQueryDTO queryDTO=SpecialOrderConverter.toQueryDTO( pageBean, bizId, set);
        	SolrSearchPageDTO<GroupOrderDTO> solrPageResult=salesSolrQueryManage.searchSpecialOrder(queryDTO);
        	return SpecialOrderConverter.dto2PageBean(solrPageResult,pageBean);
        }
        return pageBean;
    }

    @Override
    public PageBean<GroupOrder> selectTaobaoOrderListPage(PageBean<GroupOrder> pageBean,
                                                          Integer bizId, Set<Integer> set, Integer userRightType) {
        List<GroupOrder> list = groupOrderMapper.selectTaobaoOrderListPage(pageBean, bizId, set, userRightType);
        pageBean.setResult(list);
        return pageBean;
    }

    @Override
    public PageBean<GroupOrder> selectTaobaoOrderGuestNameListPage(PageBean<GroupOrder> pageBean,
                                                                   Integer bizId, Set<Integer> set, Integer userRightType) {
        List<GroupOrder> list = groupOrderMapper.selectTaobaoOrderGuestNameListPage(pageBean, bizId, set, userRightType);
        pageBean.setResult(list);
        return pageBean;
    }
    @Override
    public GroupOrder selectTotalSpecialOrder(GroupOrder groupOrder, Integer bizId, Set<Integer> set) {
        if(1==1){
        	return groupOrderMapper.selectTotalSpecialOrder(groupOrder, bizId, set);
        }else{
        	PageBean<GroupOrder> pageBean=new PageBean<GroupOrder>();
    		pageBean.setParameter(groupOrder);
    		GroupOrderPageQueryDTO queryDTO=SpecialOrderConverter.toQueryDTO(pageBean, bizId, set);
        	return salesSolrQueryManage.searchSalesOrderTotal(queryDTO);
        }
    }


    @Override
    public GroupOrder selectTotalTaobaoOrder(GroupOrder groupOrder, Integer bizId,
            Set<Integer> set) {
        return groupOrderMapper.selectTotalTaobaoOrder(groupOrder, bizId, set);
    }

    @Override
    public GroupOrder selectTotalTaobaoGuestNameOrder(GroupOrder groupOrder, Integer bizId,
            Set<Integer> set) {
        return groupOrderMapper.selectTotalTaobaoGuestNameOrder(groupOrder, bizId, set);
    }

    @Override
    public PageBean<GroupOrder> selectOrderLockByConListPage(PageBean<GroupOrder> pageBean,
            Integer bizId, Set<Integer> set) {
        List<GroupOrder> orders = groupOrderMapper.selectOrderLockByConListPage(pageBean, bizId,
                set);
        pageBean.setResult(orders);
        return pageBean;
    }

    @Override
    public Integer updateOrderLockState(Integer orderId, Integer orderLockState) {
        try {
            if (orderLockState == 1) {
                orderLockState = 0;
            } else if (orderLockState == 0) {
                orderLockState = 1;
            }
            GroupOrder order = new GroupOrder();
            order.setId(orderId);
            order.setOrderLockState(orderLockState);
            int state = groupOrderMapper.updateByPrimaryKeySelective(order);
            if (state == 1) {
                groupOrderPriceMapper.updatePriceLockState(orderId, orderLockState);
            }
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public PageBean<SaleOperatorVo> selectSaleOperatorByConListPage(
            PageBean<SaleOperatorVo> pageBean, Integer bizId, Set<Integer> set) {
        List<SaleOperatorVo> orders = groupOrderMapper.selectSaleOperatorByConListPage(pageBean, bizId, set);
        pageBean.setResult(orders);
        return pageBean;
    }

    @Override
    public String selectOrderLockByCon(
            PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set) {
        if(1==1){
        	return groupOrderMapper.selectOrderLockByCon(pageBean, bizId, set);
        }else{
        	GroupOrderPageQueryDTO queryDTO=LockOrderConverter.toQueryDTO( pageBean, bizId, set);
        	return salesSolrQueryManage.searchLockOrderTotal(queryDTO);
        	 
        }
    }

    @Override
    public PageBean<SalePrice> getSalePriceListPage(
            PageBean<SalePrice> pageBean, Integer bizId, Set<Integer> set) {
        List<SalePrice> sps = groupOrderMapper.getSalePriceListPage(pageBean, bizId, set);
        pageBean.setResult(sps);
        return pageBean;
    }

    @Override
    public BigDecimal getSalePriceToalPrice(
            PageBean<SalePrice> pageBean, Integer bizId, Set<Integer> set) {
        return groupOrderMapper.getSalePriceToalPrice(pageBean, bizId, set);
    }

    @Override
    public List<GroupOrder> selectInitOrderList(Integer bizId, Integer groupId) {
        return groupOrderMapper.selectInitOrderList(bizId, groupId);
    }

    @Transactional
    @Override
    public void saveInitGroupInfo(Integer bizId, SalesVO salesVO, Integer userId, String userName) {
        // 团信息
        TourGroup tourGroup = salesVO.getTourGroup();
        if (tourGroup.getId() == null) {
            tourGroup.setBizId(bizId);
            tourGroup.setGroupState(1);
            tourGroup.setCreateTime(System.currentTimeMillis());
            tourGroupMapper.insertSelective(tourGroup);
        } else {
            tourGroupMapper.updateByPrimaryKeySelective(tourGroup);
        }
        //组团社
        this.initGroupOrder(bizId, salesVO, userId, userName);
        //地接社
        this.initDevilery(bizId, salesVO, userId, userName);
        //酒店
        this.initHotel(bizId, salesVO, userId, userName, Constants.HOTEL);
        //餐厅
        this.initHotel(bizId, salesVO, userId, userName, Constants.RESTAURANT);
        //车队
        this.initHotel(bizId, salesVO, userId, userName, Constants.FLEET);
        //景点、
        this.initHotel(bizId, salesVO, userId, userName, Constants.SCENICSPOT);
        //保险
        this.initHotel(bizId, salesVO, userId, userName, Constants.INSURANCE);
        //机票
        this.initHotel(bizId, salesVO, userId, userName, Constants.AIRTICKETAGENT);
        //火车
        this.initHotel(bizId, salesVO, userId, userName, Constants.TRAINTICKETAGENT);

        //统计团里的金额
        financeDal.calcTourGroupAmount(tourGroup.getId());


    }

    /**
     * 期初数据-组团社增删改
     *
     * @param bizId
     * @param salesVO
     * @param userId
     * @param userName
     */
    public void initGroupOrder(Integer bizId, SalesVO salesVO, Integer userId, String userName) {
        // 团信息
        TourGroup tourGroup = tourGroupMapper.selectByPrimaryKey(salesVO.getTourGroup().getId());
        //组团社
        List<GroupOrder> orderList = salesVO.getGroupOrderList();
        List<GroupOrder> list = groupOrderMapper.selectInitOrderList(bizId, tourGroup.getId());

        if (orderList != null && orderList.size() > 0) {
            for (GroupOrder groupOrder : orderList) {
                if (groupOrder.getId() == null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    groupOrder.setGroupId(tourGroup.getId());
                    groupOrder.setCreatorId(userId);
                    groupOrder.setCreatorName(userName);
                    groupOrder.setState(1);
                    groupOrder.setBizId(bizId);
                    groupOrder.setOperatorId(tourGroup.getOperatorId());
                    groupOrder.setOperatorName(tourGroup.getOperatorName());
                    groupOrder.setDepartureDate(sdf.format(tourGroup.getDateStart()));
                    groupOrder.setCreateTime(System.currentTimeMillis());
                    groupOrderMapper.insert(groupOrder);
                } else {
                    groupOrderMapper.updateByPrimaryKeySelective(groupOrder);
                    //过滤，以便于删除
                    Iterator<GroupOrder> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        GroupOrder next = iterator.next();
                        if (groupOrder.getId().equals(next.getId())) {
                            iterator.remove();
                        }
                    }
                }
            }
        }

        if (list != null && list.size() > 0) {
            for (GroupOrder groupOrder : list) {
                groupOrderMapper.deleteByPrimaryKey(groupOrder.getId());
            }
        }
        tourGroupMapper.updateTotalIncome(tourGroup.getId());
    }

    /**
     * 期初数据-地接社增删改
     *
     * @param bizId
     * @param salesVO
     * @param userId
     * @param userName
     */
    public void initDevilery(Integer bizId, SalesVO salesVO, Integer userId, String userName) {
        // 团信息
        TourGroup tourGroup = salesVO.getTourGroup();
        List<BookingDelivery> bookingDeliveryList = salesVO.getDeliveryList();
        List<BookingDelivery> bookingDeliveryList2 = bookingDeliveryDal.selectInitDeliveryList(tourGroup.getId());
        if (null != bookingDeliveryList && bookingDeliveryList.size() > 0) {
            for (BookingDelivery bookingDelivery : bookingDeliveryList) {
                if (null == bookingDelivery.getId()) {
                    bookingDelivery.setGroupId(tourGroup.getId());
                    bookingDelivery.setSupplierId(bookingDelivery.getSupplierId());
                    bookingDelivery.setSupplierName(bookingDelivery.getSupplierName());
                    bookingDelivery.setUserId(tourGroup.getOperatorId());
                    bookingDelivery.setUserName(tourGroup.getOperatorName());
                    bookingDelivery.setCreateTime(System.currentTimeMillis());
                    bookingDeliveryMapper.insert(bookingDelivery);
                } else {
                    bookingDeliveryMapper.updateByPrimaryKeySelective(bookingDelivery);
                    //过滤，以便于删除
                    Iterator<BookingDelivery> iterator = bookingDeliveryList2.iterator();
                    while (iterator.hasNext()) {
                        BookingDelivery next = iterator.next();
                        if (bookingDelivery.getId().equals(next.getId())) {
                            iterator.remove();
                        }
                    }
                }
            }
        }
        if (bookingDeliveryList2 != null && bookingDeliveryList2.size() > 0) {
            for (BookingDelivery bookingDelivery : bookingDeliveryList2) {
                bookingDeliveryMapper.deleteByPrimaryKey(bookingDelivery.getId());
            }
        }
    }

    /**
     * 期初数据-supplier增删改
     *
     * @param bizId
     * @param salesVO
     * @param userId
     * @param userName
     */
    public void initHotel(Integer bizId, SalesVO salesVO, Integer userId, String userName, Integer type) {
        // 团信息
        TourGroup tourGroup = salesVO.getTourGroup();
        List<BookingSupplier> hotel = null;
        if (type.intValue() == Constants.HOTEL.intValue()) {
            hotel = salesVO.getHotelList();
        } else if (type.intValue() == Constants.RESTAURANT.intValue()) {
            hotel = salesVO.getRestaurantList();
        } else if (type.intValue() == Constants.FLEET.intValue()) {
            hotel = salesVO.getFleetList();
        } else if (type.intValue() == Constants.SCENICSPOT.intValue()) {
            hotel = salesVO.getScenicsportList();
        } else if (type.intValue() == Constants.INSURANCE.intValue()) {
            hotel = salesVO.getInsuranceList();
        } else if (type.intValue() == Constants.AIRTICKETAGENT.intValue()) {
            hotel = salesVO.getAirticketList();
        } else if (type.intValue() == Constants.TRAINTICKETAGENT.intValue()) {
            hotel = salesVO.getTrainList();
        }
        List<BookingSupplier> hotel2 = bookingSupplierDal.selectByGroupIdAndSupplierType(tourGroup.getId(), type);
        if (null != hotel && hotel.size() > 0) {
            for (BookingSupplier bookingSupplier : hotel) {
                if (null == bookingSupplier.getId()) {
                    bookingSupplier.setGroupId(tourGroup.getId());
                    bookingSupplier.setSupplierId(bookingSupplier.getSupplierId());
                    bookingSupplier.setSupplierName(bookingSupplier.getSupplierName());
                    bookingSupplier.setSupplierType(type);
                    bookingSupplier.setCreateTime(System.currentTimeMillis());
                    bookingSupplierMapper.insert(bookingSupplier);
                } else {
                    bookingSupplierMapper.updateByPrimaryKeySelective(bookingSupplier);
                    //过滤，以便于删除
                    Iterator<BookingSupplier> iterator = hotel2.iterator();
                    while (iterator.hasNext()) {
                        BookingSupplier next = iterator.next();
                        if (bookingSupplier.getId().equals(next.getId())) {
                            iterator.remove();
                        }
                    }
                }
            }
        }
        if (hotel2 != null && hotel2.size() > 0) {
            for (BookingSupplier bookingSupplier : hotel2) {
                bookingSupplierMapper.deleteByPrimaryKey(bookingSupplier.getId());
            }
        }
    }


    @Override
    public PageBean<GroupOrder> selectOrderByParameterListPage(PageBean<GroupOrder> pageBean,
                                                               Integer bizId, Set<Integer> set) {
        List<GroupOrder> orders = groupOrderMapper.selectOrderByParameterListPage(pageBean, bizId, set);
        pageBean.setResult(orders);
        return pageBean;
    }

    @Override
    public List<GroupOrder> selectOrderByParameter2(Map map) {
        return groupOrderMapper.selectOrderByParameter2(map);
    }

    @Override
    public PageBean<GroupOrder> selectOrderGroupByProductIdListPage(
            PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set) {
        List<GroupOrder> orders = groupOrderMapper.selectOrderGroupByProductIdListPage(pageBean, bizId, set);
        pageBean.setResult(orders);
        return pageBean;
    }

    @Override
    public GroupOrder selectOrderGroupByProductIdList(
            PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set) {
        return groupOrderMapper.selectOrderGroupByProductIdList(pageBean, bizId, set);
    }

    @Override
    public PageBean getDeservedCashGroupByOrderId(PageBean pageBean, Set<Integer> set) {
        List<GroupOrder> orders = groupOrderMapper.getDeservedCashGroupByOrderIdListPage(pageBean, set);
        if (orders != null && orders.size() > 0) {
            for (GroupOrder groupOrder : orders) {
                groupOrder.setGuestInfo(groupOrderGuestMapper.getGuestInfoByOrderId(groupOrder.getId()));
                groupOrder.setPriceInfo(convertListToStr(groupOrderPriceMapper.getPriceInfoByOrderId(groupOrder.getId())));
            }
        }
        pageBean.setResult(orders);
        return pageBean;
    }

    private String convertListToStr(List<GroupOrderPrice> priceList) {
        StringBuilder sBuilder = new StringBuilder();
        DecimalFormat dFormat = new DecimalFormat("#.##");
        if (priceList != null && priceList.size() > 0) {
            for (GroupOrderPrice price : priceList) {
                sBuilder.append(price.getItemName() + "/" + dFormat.format(
                        price.getUnitPrice()) + "/" + dFormat.format(price.getNumPerson()) + "/" +
                        dFormat.format(price.getNumTimes()) + "/" + dFormat.format(price.getTotalPrice()) + ",");
            }
            return sBuilder.substring(0, sBuilder.length() - 1);
        } else {

            return "";
        }
    }

    @Override
    public PageBean<GroupOrder> selectProductOrderListPage(
            PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set) {
        List<GroupOrder> orders = groupOrderMapper.selectProductOrderListPage(pageBean, bizId, set);
        pageBean.setResult(orders);
        return pageBean;
    }

    @Override
    public List<GroupOrder> selectSubOrderList(Integer groupId) {
        return groupOrderMapper.selectSubOrderList(groupId);
    }

    @Override
    public List<GroupOrder> selectPaymentStaticData(
            PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set) {
        return groupOrderMapper.selectPaymentStaticData(pageBean, bizId, set);
    }

    @Override
    public List<GroupOrder> selectPaymentStaticData2(PageBean<GroupOrder> pageBean, Integer bizId,
                                                     Set<Integer> set) {
        return groupOrderMapper.selectPaymentStaticData2(pageBean, bizId, set);
    }

    @Override
    public List<DepartmentOrderResult> getDepartmentOrderStatistics(DeparentmentOrderCondition condition, Set<Integer> set) {

        return groupOrderMapper.departmentOrderStatistics(condition, set);
    }

    @Override
    public Integer selectTotalNumByOrderId(Integer orderId) {
        return groupOrderMapper.selectTotalNumByOrderId(orderId);
    }

    @Override
    public List<GroupOrder> selectAiYouOrders(Integer bizId, String startTime, String endTime) {
        return groupOrderMapper.selectAiYouOrders(bizId, startTime, endTime);
    }

    @Override
    public Map<String, Object> getCountByProductIdAndDate(Integer bizId,
                                                          Integer productId, Date itemDate) {
        return groupOrderMapper.getCountByProductIdAndDate(bizId, productId, itemDate);
    }

    @Override
    public List<GroupOrder> selectTopAiYouOrders(Integer bizId, String startTime, Integer limit) {
        return groupOrderMapper.selectTopAiYouOrders(bizId, startTime, limit);
    }

    @Override
    public Map<String, Object> getDeservedCashGroupByOrderIdTotal(
            PageBean pageBean, Set<Integer> set) {
        return groupOrderMapper.getDeservedCashGroupByOrderIdTotal(pageBean, set);
    }

    @Override
    public Map<String, Object> selectAllOrderByConTotal(
            PageBean<GroupOrder> pageBean, Integer bizId) {
        return groupOrderMapper.selectAllOrderByConTotal(pageBean, bizId);
    }

    @Override
    public GroupOrder selectTotalStatic(PageBean<PaymentExportVO> pageBean,
                                        Integer bizId, Set<Integer> set) {
        return groupOrderMapper.selectTotalStatic(pageBean,
                bizId, set);
    }

    /**
     * 根据接站牌获取团id列表
     * 预定安排，查询条件
     * @return
     */
    @Override
    public List<Map<String, Object>> selectGroupIdsByReceiveMode(TourGroupVO group) {
        return groupOrderMapper.selectGroupIdsByReceiveMode(group);
    }

    public List<GroupOrder> selectIdList(Integer supplierId) {
        return groupOrderMapper.selectIdList(supplierId);
    }

    @Override
    public Integer existgroupOrder(Integer supplierId) {
        return groupOrderMapper.existgroupOrder(supplierId);
    }

    @Transactional
    @Override
    public Integer saveAiYouDataToGroupOrder(List<GroupOrder> groupOrderList) {
        GroupOrder groupOrder = null;
        for (int i = 0; i < groupOrderList.size(); i++) {
            groupOrder = groupOrderList.get(i);
            // 订单信息
            groupOrderMapper.insert(groupOrder);

            if (groupOrder.getId() > 0) {
                // 订单客人信息
                if (groupOrderList.get(i).getGroupOrderGuestList().size() > 0) {
                    GroupOrderGuest groupOrderGuest;
                    for (int j = 0; j < groupOrderList.get(i).getGroupOrderGuestList().size(); j++) {
                        groupOrderGuest = groupOrderList.get(i).getGroupOrderGuestList().get(j);
                        groupOrderGuest.setOrderId(groupOrder.getId());

                        groupOrderGuestMapper.insert(groupOrderGuest);
                    }
                }

                // 订单金额信息
                if (groupOrderList.get(i).getOrderPrices().size() > 0) {
                    GroupOrderPrice groupOrderPrice;
                    for (int k = 0; k < groupOrderList.get(i).getOrderPrices().size(); k++) {
                        groupOrderPrice = groupOrderList.get(i).getOrderPrices().get(k);
                        groupOrderPrice.setOrderId(groupOrder.getId());

                        groupOrderPriceMapper.insert(groupOrderPrice);
                    }
                }

                // 订单行程信息
                if (groupOrderList.get(i).getGroupRouteList().size() > 0) {
                    GroupRoute groupRoute;
                    for (int l = 0; l < groupOrderList.get(i).getGroupRouteList().size(); l++) {
                        groupRoute = groupOrderList.get(i).getGroupRouteList().get(l);

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar c = Calendar.getInstance();
                        try {
                            c.setTime(sdf.parse(groupOrder.getDepartureDate()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        c.add(Calendar.DAY_OF_MONTH, groupRoute.getDayNum() - 1);
                        Date t = c.getTime();
                        groupRoute.setGroupDate(t);
                        groupRoute.setId(null);
                        groupRoute.setOrderId(groupOrder.getId());

                        groupRouteMapper.insert(groupRoute);
                    }
                }
            }
        }

        if (groupOrder != null) {
            return 1;
        } else {
            return 0;
        }
    }
	@Override
	public List<GroupOrder> selectProductTrendListPageInnerLayer(PageBean<GroupOrder> pageBean, Map parameters) {
		List<GroupOrder> orders = groupOrderMapper.selectProductTrendListPageInnerLayer(pageBean,parameters) ;
		return orders;
	}
	
	@Override
	public PageBean<GroupOrder> selectProductTrendListPageOutLayer(PageBean<GroupOrder> pageBean, Map parameters) {
		List<GroupOrder> orders = groupOrderMapper.selectProductTrendListPageOutLayer(pageBean, parameters) ;
		pageBean.setResult(orders);
		return pageBean;
	}
	
	@Override
	public List<GroupOrder> selectGroupOrderById(Integer orderId) {
		List<GroupOrder> orders = groupOrderMapper.selectGroupOrderById(orderId) ;
		return orders;
	}

	@Override
	public PageBean<GroupOrder> selectResGroupOrderList(
			PageBean<GroupOrder> pageBean, Integer bizId,Set<Integer> set) {
		List<GroupOrder> list = groupOrderMapper.findResOrderListPage(pageBean,bizId,set);
		pageBean.setResult(list);
		return pageBean;
	}

	@Override
	public Integer findUpdateGroupOrderState(String id, String causeRemark) {
		GroupOrder goBean = new GroupOrder();
		goBean.setRemarkInternal(causeRemark);
		goBean.setId(Integer.valueOf(id));
		goBean.setExtResState(2);
		goBean.setState(-1);
		int num = groupOrderMapper.updateResOrderState(goBean);
		return num;
	}

	@Override
	public HashMap<String, BigDecimal> sumGroupOrder(PageBean<GroupOrder> pageBean) {
		return groupOrderMapper.sumGroupOrder(pageBean);
	}
	
	@Override
	public PageBean<GroupOrder> selectResAdminOrderList(
			PageBean<GroupOrder> pageBean, Integer bizId,Set<Integer> set) {
		List<GroupOrder> list = groupOrderMapper.findResAdminOrderListPage(pageBean,bizId,set);
		pageBean.setResult(list);
		return pageBean;
	}
	
	@Override
	public HashMap<String, BigDecimal> sumResAdminOrder(PageBean<GroupOrder> pageBean) {
		return groupOrderMapper.sumResAdminOrder(pageBean);
	}
	
	@Override
	public Integer findUpdateAdminOrderState(String id, String causeRemark) {
		GroupOrder goBean = new GroupOrder();
		goBean.setRemarkInternal(causeRemark);
		goBean.setId(Integer.valueOf(id));
		goBean.setExtResState(2);
		int num = groupOrderMapper.updateResAdminOrderState(goBean);
		return num;
	}

	@Override
	public Integer loadUpdateExtResState(GroupOrder record) {
		return groupOrderMapper.updateExtResState(record);
	}


	@Override
    public Integer delGroupOrderId(Integer id) {
		return groupOrderMapper.delGroupOrderId(id);
    }

    @Override
    public Integer selectSumPersonByProductId(Integer resId, Integer productId, String departureDate) {
        Integer sumPerson = groupOrderMapper.selectSumPersonByProductId(resId, productId, departureDate);
        return sumPerson;
    }

	@Override
	public List<GroupOrder> selectOrderOverTime() {
		return groupOrderMapper.selectOrderOverTime();
	}

	@Override
	public List<FinanceCommission> selectFinanceCommissionByGroupId(Integer groupId) {
		List<FinanceCommission> orders = financeCommissionMapper.selectByGroupId(groupId);
		return orders;
	}

	@Override
	public List<FinanceCommission> selectFCByGroupId(Integer groupId) {
		List<FinanceCommission> orders = financeCommissionDeductionMapper.selectByGroupId(groupId);
		return orders;
	}

	/**
    @Override
    public PageBean<GroupOrder> findByTime(String orderStr, PageBean<GroupOrder> pageBean, Integer bizId, Integer userId, String userName) {
        return groupOrderPriceMapper.findByTime(orderStr, pageBean, bizId, userId, userName);
    }
	*/
    
    /**
	 * 根据订单列表查询price信息
	 * @param orderIds
	 * @return
	 */
    /**
	public GroupOrderPrice getPriceTotalByOrderIds(List<Integer> orderIds,Integer mode){
		return groupOrderPriceMapper.getPriceTotalByOrderIds(orderIds,mode);
	}
	
	*/
	
	
	/**
	@Override
    public PageBean<GroupOrder> selectOrderListPage( PageBean<GroupOrder> pageBean) {
        List<GroupOrder> orders = groupOrderMapper.selectOrderDumpListPage(pageBean);
        pageBean.setResult(orders);
        return pageBean;
    }
	*/
	
    @Transactional
    private void saveOrUpdateTransferOrder(String orderStr, Integer bizId, Integer userId,
            String userName) {
        if (StringUtils.isNotBlank(orderStr)) {
            JSONObject jsonObj = JSONObject.parseObject(orderStr);

            if (jsonObj.get("data") != null) {
                List<TransferOrderVO> orderVoList = JSONArray
                        .parseArray(jsonObj.getJSONArray("data").toString(), TransferOrderVO.class);

                for (TransferOrderVO orderVo : orderVoList) {
                    TransferOrder tfOrder = new TransferOrder();
                    tfOrder = orderVo.getTransferOrder();

                    GroupOrder gOrder = new GroupOrder();
                    gOrder = groupOrderMapper.selectByPushOrderId(tfOrder.getId());

                    // 订单
                    Integer orderId = saveOrUpdateGroupOrder(bizId, gOrder, tfOrder, userId,
                            userName);
                    // 订单客人
                    saveOrUpdateGroupOrderGuest(orderId, gOrder, orderVo.getGuests());
                    // 订单价格
                    saveOrUpdateGroupOrderPrice(orderId, gOrder, orderVo.getPrices(), userId,
                            userName);
                    // 订单行程
                    saveOrUpdateGroupRoute(orderId, gOrder, orderVo.getRoutes());
                }
            }
        }
    }

    @Transactional
    @Override
    public Integer importGroupOrder(String[] orderIds, Integer saleOperatorId,
            String saleOperatorName, Integer operatorId, String operatorName, Integer supplierId,
            String supplierName, Integer orderType) {
        GroupOrder groupOrder = new GroupOrder();
        for (String str : orderIds) {
            groupOrder.setId(Integer.parseInt(str));
            groupOrder.setSaleOperatorId(saleOperatorId);
            groupOrder.setSaleOperatorName(saleOperatorName);
            groupOrder.setOperatorId(operatorId);
            groupOrder.setOperatorName(operatorName);
            groupOrder.setSupplierId(supplierId);
            groupOrder.setSupplierName(supplierName);
            groupOrder.setState(1);
            groupOrder.setOrderType(orderType);

            groupOrderMapper.updateByPrimaryKeySelective(groupOrder);
        }
        return groupOrder == null ? 0 : groupOrder.getId();
    }

    private Integer saveOrUpdateGroupOrder(Integer bizId, GroupOrder groupOrder,
                                           TransferOrder tfOrder, Integer userId, String userName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (groupOrder == null) {
            groupOrder = new GroupOrder();
            groupOrder.setBizId(bizId);
            groupOrder.setDepartureDate(sdf.format(tfOrder.getDateStart()));
            groupOrder.setGroupCode(tfOrder.getOrderCodeSend());
            groupOrder.setContactName(tfOrder.getSupplierUserName());
            groupOrder.setContactTel(tfOrder.getSupplierUserMobile());
            groupOrder.setContactMobile(tfOrder.getSupplierUserMobile());
            groupOrder.setContactFax(tfOrder.getSupplierUserFax());
            groupOrder.setNumAdult(Integer.valueOf(tfOrder.getPersonAdult()));
            groupOrder.setNumChild(Integer.valueOf(tfOrder.getPersonChild()));
            groupOrder.setNumGuide(Integer.valueOf(tfOrder.getPersonGuide()));
            groupOrder.setProductName(tfOrder.getOrderProductName());
            groupOrder.setOrderType(Integer.valueOf(0));
            groupOrder.setState(Integer.valueOf(0));
            groupOrder.setTotal(tfOrder.getTotal());
            groupOrder.setServiceStandard(tfOrder.getRemarkService());
            groupOrder.setRemark(tfOrder.getRemark());
            groupOrder.setCreatorId(userId);
            groupOrder.setCreatorName(userName);
            groupOrder.setCreateTime(Long.valueOf(System.currentTimeMillis()));
            groupOrder.setPushOrderId(tfOrder.getId());
            groupOrder.setPushTimeUpdate(tfOrder.getTimeUpdate());
            groupOrder.setPushSupplierName(tfOrder.getApiSupplierName());

            groupOrderMapper.insertSelective(groupOrder);
        } else {
            if (groupOrder.getPushTimeUpdate() != tfOrder.getTimeUpdate()) {
                groupOrderMapper.updateByPrimaryKeySelective(groupOrder);
            }
        }
        return groupOrder.getId();
    }

    private void saveOrUpdateGroupOrderGuest(Integer orderId, GroupOrder groupOrder,
            List<TransferOrderGuest> tfoGuests) {
        for (TransferOrderGuest guest : tfoGuests) {
            GroupOrderGuest gOrderGuest = new GroupOrderGuest();
            gOrderGuest.setOrderId(orderId);
            gOrderGuest.setName(guest.getName());
            gOrderGuest.setType(guest.getType());
            gOrderGuest.setCertificateNum(guest.getCertificateNum());
            gOrderGuest.setGender(guest.getGender());
            gOrderGuest.setMobile(guest.getMobile());
            gOrderGuest.setNativePlace(guest.getNativePlace());
            gOrderGuest.setAge(guest.getAge());
            gOrderGuest.setCareer(guest.getCareer());
            gOrderGuest.setIsSingleRoom(guest.getIsSingleRoom());
            gOrderGuest.setRemark(guest.getRemark());
            gOrderGuest.setCreateTime(Long.valueOf(System.currentTimeMillis()));

            if (groupOrder == null) {
                groupOrderGuestMapper.insertSelective(gOrderGuest);
            } else {
                groupOrderGuestMapper.updateByPrimaryKeySelective(gOrderGuest);
            }
        }

    }

    private void saveOrUpdateGroupOrderPrice(Integer orderId, GroupOrder groupOrder,
            List<TransferOrderPrice> tfoPrices, Integer userId, String userName) {
        for (TransferOrderPrice price : tfoPrices) {
            GroupOrderPrice gOrderPrice = new GroupOrderPrice();

            gOrderPrice.setOrderId(orderId);
            gOrderPrice.setItemName(price.getItem());
            gOrderPrice.setRemark(price.getRemark());
            gOrderPrice
                    .setUnitPrice(Double.valueOf(Double.parseDouble(price.getPrice().toString())));
            gOrderPrice.setNumTimes(Double.valueOf(1.5D));
            gOrderPrice.setNumPerson(
                    Double.valueOf(Double.parseDouble(price.getNumPerson().toString())));
            gOrderPrice
                    .setTotalPrice(Double.valueOf(Double.parseDouble(price.getTotal().toString())));
            gOrderPrice.setRowState(Integer.valueOf(1));
            gOrderPrice.setCreatorId(userId);
            gOrderPrice.setCreatorName(userName);
            gOrderPrice.setCreateTime(Long.valueOf(System.currentTimeMillis()));

            if (groupOrder == null) {
                groupOrderPriceMapper.insertSelective(gOrderPrice);
            } else {
                groupOrderPriceMapper.updateByPrimaryKeySelective(gOrderPrice);
            }
        }
    }

    private void saveOrUpdateGroupRoute(Integer orderId, GroupOrder groupOrder,
            List<TransferOrderRoute> tfoRoutes) {
        for (TransferOrderRoute route : tfoRoutes) {
            GroupRoute gRoute = new GroupRoute();
            gRoute.setOrderId(orderId);
            gRoute.setDayNum(route.getDayVal());
            gRoute.setGroupDate(route.getDateVal());
            gRoute.setBreakfast(route.getBreakfast());
            gRoute.setLunch(route.getLunch());
            gRoute.setSupper(route.getSupper());
            gRoute.setHotelName(route.getHotels());
            gRoute.setRouteDesp(route.getRouteDesp());
            gRoute.setCreateTime(Long.valueOf(System.currentTimeMillis()));

            if (groupOrder == null) {
                groupRouteMapper.insertSelective(gRoute);
            } else {
                groupRouteMapper.updateByPrimaryKeySelective(gRoute);
            }
        }
    }

	@Override
	public List<GroupOrder> selectBySaleOperatorId(Integer bizId, DeparentmentOrderCondition
			condition, Set<Integer> set) {
		return groupOrderMapper.selectBySaleOperatorId(bizId, condition,set);
	}

	@Override
	public PageBean selectGroupOrderGuestListPage(PageBean pageBean, Integer bizId,Set<Integer> set,Integer userRightType) {
		List<Map<String,Object>> result = groupOrderMapper.selectGroupOrderGuestListPage(pageBean, bizId,set,userRightType);
	    pageBean.setResult(result);
	    return pageBean;
	}
	
    @Override
    public PageBean selectGroupOrderGuestListPageOu(PageBean pageBean, Integer bizId, Set<Integer> set, Integer userRightType, final String sidx, String sord) {
        List<Map<String,Object>> result = groupOrderMapper.selectGroupOrderGuestListPageOu(pageBean, bizId,set,userRightType, sidx, sord);
        System.out.println("--1----"+result);
        pageBean.setResult(result);
        return pageBean;
    }

	@Override
	public PageBean<GroupOrder>selectMonthlyReportStatisticsListPage(PageBean<GroupOrder> pageBean, Integer bizId){
		List<GroupOrder> groupOrders=groupOrderMapper.selectgroupIdByAY(pageBean, bizId);
		Set<Integer> groupIds= new HashSet() ;
		for(GroupOrder item:groupOrders){
			groupIds.add(item.getGroupId());
		}
		if(groupIds.size()==0){
			return pageBean;
		}
		List<GroupOrder> gos=groupOrderMapper.selectMonthlyReportStatisticsListPage(pageBean, bizId, groupIds);
		pageBean.setResult(gos);
		return pageBean;
	}

	@Override
	public List<GroupOrder>selectMonthlyReportStatistics(PageBean<GroupOrder> pageBean, Integer bizId){
		List<GroupOrder> groupOrders=groupOrderMapper.selectgroupIdByAY(pageBean, bizId);
		Set<Integer> groupIds= new HashSet() ;
		for(GroupOrder item:groupOrders){
			groupIds.add(item.getGroupId());
		}
		if(groupIds.size()==0){
			return groupOrders;
		}
		List<GroupOrder> gos=groupOrderMapper.selectMonthlyReportStatistics(pageBean, bizId, groupIds);
		return gos;
	}


	   /**
     * @see —审核功能
     * @author TengDong
     * @param record 团队订单
     * @return  boolean
     */
	public boolean updateGroupOrderByB2b_export_state(GroupOrder record) {
		try{
			int sign=groupOrderMapper.updateByPrimaryKeySelective(record);
			if(sign>0){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			log.info("------------内部结算(销售)—审核功能异常-----------------"+e.getMessage());
			return false;
		}

	}


	@Override
	public PageBean<GroupOrder> selectOperatorOrderListPage(PageBean<GroupOrder> pageBean, Integer bizId,
			Set<Integer> set, Integer userRightType) {
		GroupOrder paramGoOrder = (GroupOrder) pageBean.getParameter();
		List<GroupOrderGuest> guestList = groupOrderGuestMapper.selectOrderIdsByNameOrMobile(pageBean);
		if(guestList != null && guestList.size() > 0){
			Set<Integer> orderIdSet = new HashSet<Integer>();
			for(GroupOrderGuest guest : guestList){
				orderIdSet.add(guest.getOrderId());
			}
			if(orderIdSet.size()==0){
				orderIdSet.add(-1);
			}
			paramGoOrder.setOrderIdSet(orderIdSet);
		}
		 List<GroupOrder> orderList =
				 groupOrderMapper.selectTaobaoOrderListPage(pageBean, bizId, set, userRightType);
		 if(orderList != null && orderList.size() > 0){
			 GroupOrder order = null;
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < orderList.size(); i++){
					if(i > 0){
						sb.append(",");
					}
					order = orderList.get(i);
					sb.append(order.getId());
				}

			String orderIds = sb.toString();

			List<Map<String, Object>> guests = groupOrderGuestMapper.selectGuestForOrders(pageBean, orderIds);
			this.mergeGuestsInOrders(orderList, guests);

			List<Map<String, Object>> requirements = requirementMapper.selectRequirementForOrders(pageBean, orderIds);
			this.mergeRequirementsInOrders(orderList, requirements);

			List<Map<String, Object>> transports = transportMapper.selectTransportForOrders(pageBean, orderIds);
			this.mergeTransportsInOrders(orderList, transports);
		}
		 pageBean.setResult(orderList);
		 return pageBean;
	 }


	@Override
	public PageBean<GroupOrder> selectOperatorGuestNameListPage(PageBean<GroupOrder> pageBean, Integer bizId,
			Set<Integer> set, Integer userRightType) {
		GroupOrder paramGoOrder = (GroupOrder) pageBean.getParameter();
		List<GroupOrderGuest> guestList = groupOrderGuestMapper.selectOrderIdsByNameOrMobile(pageBean);
		if(guestList != null && guestList.size() > 0){
			Set<Integer> orderIdSet = new HashSet<Integer>();
			for(GroupOrderGuest guest : guestList){
				orderIdSet.add(guest.getOrderId());
			}
			if(orderIdSet.size()==0){
				orderIdSet.add(-1);
			}
			paramGoOrder.setOrderIdSet(orderIdSet);
		}
		 List<GroupOrder> orderList =
				 groupOrderMapper.selectTaobaoOrderGuestNameListPage(pageBean, bizId, set, userRightType);
		 if(orderList != null && orderList.size() > 0){
			 GroupOrder order = null;
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < orderList.size(); i++){
					if(i > 0){
						sb.append(",");
					}
					order = orderList.get(i);
					sb.append(order.getId());
				}

			String orderIds = sb.toString();

			List<Map<String, Object>> guests = groupOrderGuestMapper.selectGuestForOrders(pageBean, orderIds);
			this.mergeGuestsInOrders(orderList, guests);

			List<Map<String, Object>> requirements = requirementMapper.selectRequirementForOrders(pageBean, orderIds);
			this.mergeRequirementsInOrders(orderList, requirements);

			List<Map<String, Object>> transports = transportMapper.selectTransportForOrders(pageBean, orderIds);
			this.mergeTransportsInOrders(orderList, transports);
		}
		 pageBean.setResult(orderList);
		 return pageBean;
	 }

	public void mergeRequirementsInOrders(List<GroupOrder> orders, List<Map<String, Object>> requirements){
		if(orders == null || orders.size() == 0 || requirements == null || requirements.size() == 0){
			return;
		}

		GroupOrder order = null;

		for(int i = 0; i < orders.size(); i++){
			order = orders.get(i);

			Map<String, Object> requirement = null;
			for(int j = 0; j < requirements.size(); j++){
				requirement = requirements.get(j);
				Integer requirementOrderId = (Integer)requirement.get("order_id");
				if(order.getId().intValue() == requirementOrderId.intValue()){
					order.setHotelLevels(requirement.get("hotelLevels").toString());
					order.setHotelNums(requirement.get("hotelNums").toString());
					break;
				}
			}
		}
	}

	public void mergeGuestsInOrders(List<GroupOrder> orders, List<Map<String, Object>> guests){
		if(orders == null || orders.size() == 0 || guests == null || guests.size() == 0){
			return;
		}

		GroupOrder order = null;

		for(int i = 0; i < orders.size(); i++){
			order = orders.get(i);

			Map<String, Object> guest = null;
			for(int j = 0; j < guests.size(); j++){
				guest = guests.get(j);
				Integer guestOrderId = (Integer)guest.get("order_id");
				if(order.getId().intValue() == guestOrderId.intValue()){
					order.setGuestNames(guest.get("guestNames").toString());
					break;
				}
			}
		}
	}

	public void mergeTransportsInOrders(List<GroupOrder> orders, List<Map<String, Object>> transports){
		if(orders == null || orders.size() == 0 || transports == null || transports.size() == 0){
			return;
		}

		GroupOrder order = null;

		for(int i = 0; i < orders.size(); i++){
			order = orders.get(i);

			Map<String, Object> transport = null;
			for(int j = 0; j < transports.size(); j++){
				transport = transports.get(j);
				Integer guestOrderId = (Integer)transport.get("order_id");
				if(order.getId().intValue() == guestOrderId.intValue()){
					order.setUpAir(transport.get("upAir").toString());
					order.setOffAir(transport.get("offAir").toString());
					order.setTrans(transport.get("trans").toString());
					break;
				}
			}
		}
	}





	@Override
	public Integer updateExtVisa(GroupOrder record) {
		int num = groupOrderMapper.updateExtVisa(record);
		return num;
	}

	 @Override
    public List<GroupOrder> selectByOrderIdExtVisaListPage(String mobile) {
        List<GroupOrder> list = groupOrderMapper.selectByOrderIdExtVisaList(mobile);
        return list;
    }

	    /**
	     *   总合计
	     * @Auther TengDong
	     * @Date 20161102
	     * @param groupOrder
	     * @param bizId
	     * @param set
	     * @param userRightType
	     * @return
	     */
	public GroupOrder selectProfitEverifyByCon(
			GroupOrder groupOrder, Integer bizId, Set<Integer> set,
			Integer userRightType) {

			GroupOrder mapResult=groupOrderMapper.selectProfitEverifyByCon(groupOrder,bizId,set,userRightType);

		return mapResult;

	}

	@Override
	public Map<String, Object> selectProfitEverifyByTotal(PageBean pageBean, Integer bizId,
			Set<Integer> set,Integer userRightType) {
		return groupOrderMapper.selectProfitEverifyByTotal(pageBean, bizId, set,userRightType);
	}

	 /**
     * @see
     * @Auth TengDong
     * @Date 20161031
     * @param pageBean  model
     * @param bizId
     * @param set
     * @param userRightType
     * @return
     */
	public PageBean<GroupOrder> selectProfitEverifyListPage(
			PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set,
			Integer userRightType) {
		List<GroupOrder> result = groupOrderMapper.selectProfitEverifyListPage(pageBean, bizId, set, userRightType);
		pageBean.setResult(result);
		return pageBean;

	}

	  	@Override
	    public PageBean<GroupOrder> findByTime(String orderStr, PageBean<GroupOrder> pageBean,
	            Integer bizId, Integer userId, String userName) {

	        // 保存订单
	        saveOrUpdateTransferOrder(orderStr, bizId, userId, userName);

	        // 获取接口中心团订单信息
	        List<GroupOrder> orders = groupOrderMapper.selectByTimeListPage(pageBean, bizId);
	        pageBean.setResult(orders);

	        return pageBean;
	    }
	  	
		@Override
		public PageBean<GroupOrder>selectProductProfitStatisticsListPage(PageBean<GroupOrder> pageBean, Integer bizId){
			List<GroupOrder> groupOrders=groupOrderMapper.selectProductProfitStatisticsListPage(pageBean, bizId);
			pageBean.setResult(groupOrders);
			return pageBean;
		}

    @Override
    public List<GroupOrderForCarCar> selectGroupOrdersInGroupsForCarCar(PageBean<GroupOrderQueryForCarCar> pageBean) {
        List<GroupOrderForCarCar> groupOrderForCarCars = groupOrderMapper.selectGroupOrdersInGroupsForCarCar(pageBean);
        if (CollectionUtils.isEmpty(groupOrderForCarCars)) {
            return Collections.emptyList();
        }
        return groupOrderForCarCars;
    }


}

