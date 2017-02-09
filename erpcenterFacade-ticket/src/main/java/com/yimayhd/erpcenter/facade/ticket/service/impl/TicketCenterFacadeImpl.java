package com.yimayhd.erpcenter.facade.ticket.service.impl;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.product.service.*;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.SpecialGroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.biz.sys.service.SysBizInfoBiz;
import com.yimayhd.erpcenter.dal.product.constans.Constants;
import com.yimayhd.erpcenter.dal.product.po.*;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductRouteVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.ResOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SpecialGroupOrderVO;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.dal.sys.po.SysBizInfo;
import com.yimayhd.erpcenter.dal.sys.po.UserSession;
import com.yimayhd.erpcenter.facade.ticket.errorcode.TicketErrorCode;
import com.yimayhd.erpcenter.facade.ticket.result.WebResult;
import com.yimayhd.erpcenter.facade.ticket.service.TicketCenterFacade;
import com.yimayhd.erpcenter.facade.ticket.utils.MD5Util;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhangxy on 2017/2/7.
 */
public class TicketCenterFacadeImpl implements TicketCenterFacade {
    @Autowired
    private SysBizInfoBiz sysBizInfoBiz;
    @Autowired
    private PlatformEmployeeBiz platformEmployeeBiz;
    @Autowired
    private PlatformOrgBiz platformOrgBiz;
    @Autowired
    private TrafficResBiz trafficResBiz;
    @Autowired
    private ProductInfoBiz productInfoBiz;
    @Autowired
    private ProductRouteBiz productRouteBiz;
    @Autowired
    private ProductRemarkBiz productRemarkBiz;
    @Autowired
    private GroupOrderBiz groupOrderBiz;
    @Autowired
    private SupplierBiz supplierBiz;
    @Autowired
    private SpecialGroupOrderBiz specialGroupOrderBiz;
    @Autowired
    private TourGroupBiz tourGroupBiz;
    @Autowired
    private TrafficResProductBiz trafficResProductBiz;
    @Override
    public WebResult<Map<String, Object>> loginTicketCenter(String loginName, String password, String code) {
        code = "XTSM";
        WebResult<Map<String, Object>> webResult = new WebResult<Map<String, Object>>();


        SysBizInfo curBizInfo = sysBizInfoBiz.getBizInfoByCode(code);
        if (curBizInfo == null) {
            webResult.setErrorCode(TicketErrorCode.CODE_NOT_EXIST);
            return webResult;
        }

        PlatformEmployeePo platformEmployeePo = platformEmployeeBiz
                .getEmployeeByBizIdAndLoginName(curBizInfo.getId(), loginName);
        if (platformEmployeePo != null) {
            if (MD5Util.authenticatePassword(platformEmployeePo.getPassword(), password)) {
                UserSession userSession = new UserSession();
                platformEmployeePo.setPassword("");
                userSession.setEmployeeInfo(platformEmployeePo);
                PlatformOrgPo orgInfo = platformOrgBiz.getOrgInfo(platformEmployeePo.getBizId(),
                        platformEmployeePo.getOrgId());
                userSession.setOrgInfo(orgInfo);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("userSession", userSession);
                webResult.setValue(map);
                return webResult;
            } else {
                webResult.setErrorCode(TicketErrorCode.NOT_MATCH);
                return webResult;
            }
        } else {
            webResult.setErrorCode(TicketErrorCode.NAME_NOT_EXIST);
            return webResult;
        }
    }

    @Override
    public PageBean getResProductListToWX(PageBean pageBean) {

        return trafficResBiz.findResProductListToWX(pageBean);
    }

    @Override
    public WebResult<TrafficResProduct> getResProductInfoToWX(Integer trpId, Integer resId) {
        WebResult<TrafficResProduct> webResult = new WebResult<TrafficResProduct>();
        TrafficResProduct resProduct = trafficResBiz.findResProductToWX(trpId, resId);
        if (resProduct == null) {
            webResult.setErrorCode(TicketErrorCode.QUERY_ERROR);
            return webResult;
        }
        webResult.setValue(resProduct);
        return webResult;
    }

    @Override
    public WebResult<Map<String, Object>> getProductInfoToWX(Integer id) {
        ProductInfoVo productInfoVo = productInfoBiz.findProductInfoVoById(id);
        ProductRouteVo productRouteVo = productRouteBiz.findByProductId(id);
        ProductRemark productRemark = productRemarkBiz.findProductRemarkByProductId(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("productInfoVo", productInfoVo);
        map.put("productRouteVo", productRouteVo);
        map.put("productRemark", productRemark);
        WebResult<Map<String, Object>>  webResult = new WebResult<Map<String, Object>>();
        webResult.setValue(map);
        return webResult;
    }


    @Override
    public WebResult<String> verifyGroupOrderToWX(Integer bizId, Integer supplierId, String depaDate) {
        WebResult<String> webResult = new WebResult<String>();
        Integer groupOrderCount = groupOrderBiz.findGroupOrderCountBySidAndDate(bizId, supplierId, depaDate);
        if (groupOrderCount > 0) {
            webResult.setErrorCode(TicketErrorCode.NO_WAY_TO_RESERVE);
            return webResult;
        } else {
            return webResult;
        }
    }

    @Override
    public WebResult<Map<String, Object>> saveResOrderToWX(ResOrder resOrder) throws ParseException {
        Integer num;
        Integer orderId = 0;
        Integer isAdd = 0;
        GroupOrder go = new GroupOrder();
        GroupRequirement hotelInfo = new GroupRequirement();
        hotelInfo.setCountDoubleRoom(0);
        hotelInfo.setExtraBed(0);

        go.setExtResId(resOrder.getResId());
        go.setOrderType(0);
        go.setSupplierId(resOrder.getMappingSupplierId());
        go.setSupplierName(supplierBiz.selectBySupplierId(resOrder.getMappingSupplierId()).getNameFull());
        go.setOperatorId(resOrder.getEmployeeId());
        go.setOperatorName(resOrder.getEmployeeName());
        go.setSaleOperatorId(resOrder.getEmployeeId());
        go.setSaleOperatorName(resOrder.getEmployeeName());
        go.setNumAdult(resOrder.getNumAdult());
        go.setNumChild(resOrder.getNumChild());
        go.setNumChildBaby(resOrder.getNumBady());
        go.setProductId(resOrder.getProductId());
        go.setTotal(new BigDecimal(resOrder.getTotalPrice()));
        go.setRemark(resOrder.getRemark());

        ProductInfo productInfo = productInfoBiz.findProductInfoById(resOrder.getProductId());

        go.setProductBrandId(productInfo.getBrandId());
        go.setProductBrandName(productInfo.getBrandName());
        go.setProductName(productInfo.getNameCity());
        go.setType(resOrder.getType());

        go.setExtResConfirmId(null);
        go.setExtResConfirmName("");

        go.setContactName("");
        go.setContactTel("");
        go.setContactMobile("");
        go.setContactFax("");

        go.setSourceTypeId("-1");
        go.setProvinceId(null);
        go.setCityId(null);


        TrafficResProduct trafficResProduct = trafficResBiz.selectTrafficProductInfo(resOrder.getTrpId());
        if (trafficResProduct.getReserveTime() > 0) {
            go.setExtResCleanTime(trafficResProduct.getReserveTime());
        }

        num = resOrder.getNumAdult() + resOrder.getNumChild();

        String code = platformOrgBiz.getCompanyCodeByOrgId(resOrder.getBizId() , resOrder.getOrgId());
        go.setOrderNo(code);

        go.setExtResPrepay(((new BigDecimal(resOrder.getNumAdult()).multiply(trafficResProduct.getAdultMinDeposit()))
                .add(new BigDecimal(resOrder.getNumChild()).multiply(trafficResProduct.getChildMinDeposit())))
                .add(new BigDecimal(resOrder.getNumBady()).multiply(trafficResProduct.getBadyMinDeposit())));

        go.setDepartureDate(resOrder.getDepartureDate());


        List<GroupOrderPrice> groupOrderPriceList = new ArrayList<GroupOrderPrice>();

        GroupOrderPrice gpAdult = new GroupOrderPrice();
        gpAdult.setMode(0);
        gpAdult.setItemId(137);
        gpAdult.setItemName("成人");
        gpAdult.setNumTimes(1D);
        gpAdult.setPriceLockState(1);

        if (resOrder.getNumAdult() > 0) {
            gpAdult.setNumPerson(Double.parseDouble(resOrder.getNumAdult().toString()));
            gpAdult.setUnitPrice(Double.parseDouble(resOrder.getAdultPrice()));
            gpAdult.setTotalPrice((Double.parseDouble(resOrder.getAdultPrice()) * resOrder.getNumAdult()));
            gpAdult.setRemark("");

            groupOrderPriceList.add(gpAdult);
        }

        GroupOrderPrice gpChild = new GroupOrderPrice();
        gpChild.setMode(0);
        gpChild.setItemId(137);
        gpChild.setItemName("成人");
        gpChild.setNumTimes(1D);
        gpChild.setPriceLockState(1);
        if (resOrder.getNumChild() > 0) {
            gpChild.setNumPerson(Double.parseDouble(resOrder.getNumAdult().toString()));
            gpChild.setUnitPrice(Double.parseDouble(resOrder.getChildPrice()));
            gpChild.setTotalPrice((Double.parseDouble(resOrder.getChildPrice()) * resOrder.getNumAdult()));
            gpChild.setRemark("");

            groupOrderPriceList.add(gpChild);
        }

        GroupOrderPrice gpBady = new GroupOrderPrice();
        gpBady.setMode(0);
        gpBady.setItemId(137);
        gpBady.setItemName("成人");
        gpBady.setNumTimes(1D);
        gpBady.setPriceLockState(1);
        if (resOrder.getNumBady() > 0) {
            gpBady.setNumPerson(Double.parseDouble(resOrder.getNumAdult().toString()));
            gpBady.setUnitPrice(Double.parseDouble(resOrder.getBadyPrice()));
            gpBady.setTotalPrice((Double.parseDouble(resOrder.getBadyPrice()) * resOrder.getNumBady()));
            gpBady.setRemark("");

            groupOrderPriceList.add(gpBady);
        }


        SpecialGroupOrderVO vo = new SpecialGroupOrderVO();
        vo.setGroupOrder(go);
        vo.setHotelInfo(hotelInfo);
        vo.setGroupOrderPriceList(groupOrderPriceList);

        //保存订单
        orderId = specialGroupOrderBiz.saveOrUpdateSpecialOrderInfo(vo, resOrder.getEmployeeId(), resOrder.getEmployeeName(), resOrder.getBizId());

        //减资源库存
        TrafficResStocklog trafficResStocklog = new TrafficResStocklog();
        // 全款
        trafficResStocklog.setAdjustAction(Constants.TRAFFICRES_STOCK_ACTION.ORDER_SOLD.toString());
        trafficResStocklog.setOrderId(orderId);
        trafficResStocklog.setAdjustNum(num);
        trafficResStocklog.setResId(vo.getGroupOrder().getExtResId());
        trafficResStocklog.setAdjustTime(new Date());
        trafficResStocklog.setUserId(resOrder.getEmployeeId());
        trafficResStocklog.setUserName(resOrder.getEmployeeName());

        if (isAdd == 1) {
            trafficResBiz.insertTrafficResStocklog(trafficResStocklog); //Stock表
        } else {
            trafficResBiz.updateTrafficResStockLogByOrderId(trafficResStocklog);
        }

        //更新产品已售数量
        if (null != trafficResProduct) {
            Integer sumPerson = groupOrderBiz.selectSumPersonByProductId(trafficResProduct.getResId(),
                    trafficResProduct.getProductCode(), vo.getGroupOrder().getDepartureDate());
            trafficResProduct.setNumSold(sumPerson);
            trafficResBiz.updateNumSoldById(trafficResProduct);
        }

        //更新资源的库存数量
        trafficResBiz.updateStockOrStockDisable(vo.getGroupOrder().getExtResId());
        WebResult<Map<String,Object>> webResult = new WebResult<Map<String, Object>>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("groupId",orderId+"");
        webResult.setValue(map);
        return webResult;
    }

    @Override
    public WebResult<List<GroupOrder>> getGroupOrderToWX(Integer bizId, Integer extResState, Integer supplierId) {
        WebResult<List<GroupOrder>> webResult = new WebResult<List<GroupOrder>>();
        List<GroupOrder> groupOrders = groupOrderBiz.findGroupOrderBysIdAndResState(bizId, extResState, supplierId);
        if (CollectionUtils.isEmpty(groupOrders)) {
            webResult.setValue(Collections.<GroupOrder>emptyList());
        }else {
            webResult.setValue(groupOrders);
        }
        return webResult;
    }

    @Override
    public WebResult<Map<String, Object>> getGroupOrderDetailToWX(Integer bizId, Integer groupOrderId) {
        GroupOrder groupOrder = groupOrderBiz.findOrderById(bizId, groupOrderId);
        if (groupOrder.getRemark() == null) {
            groupOrder.setRemark("");
        }

        TrafficRes tr = trafficResBiz.selectTrafficResAndLineInfoById(groupOrder.getExtResId());

        TrafficResProduct trp = trafficResBiz.selectTrafficProductInfoByProductCode(groupOrder.getProductId(),
                groupOrder.getExtResId());

        trp.setAdultSame(trp.getAdultSuggestPrice().subtract(trp.getAdultSamePay()));
        trp.setAdultProxy((trp.getAdultSuggestPrice().subtract(trp.getAdultSamePay()))
                .subtract(trp.getAdultProxyPay()));
        trp.setChildSame(trp.getChildSuggestPrice().subtract(trp.getChildSamePay()));
        trp.setChildProxy((trp.getChildSuggestPrice().subtract(trp.getChildSamePay()))
                .subtract(trp.getChildProxyPay()));
        trp.setBabySame(trp.getBabySuggestPrice().subtract(trp.getBabySamePay()));
        trp.setBabyProxy((trp.getBabySuggestPrice().subtract(trp.getBabySamePay())).subtract(trp.getBadyProxyPay()));

        String confirmDate = "";
        if (groupOrder.getGroupId() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date date = new Date(tourGroupBiz.selectByPrimaryKey(groupOrder.getGroupId()).getCreateTime());

            confirmDate = sdf.format(date);
        }

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("trafficResProduct", trp);
        map.put("groupOrder", groupOrder);
        map.put("tr", tr);
        map.put("confirmDate", confirmDate);
        WebResult<Map<String, Object>> webResult = new WebResult<Map<String, Object>>();
        webResult.setValue(map);
        return webResult;
    }

    @Override
    public WebResult<Map<String, Object>> cancelOrder(Integer groupOrderId) {
        TrafficResStocklog trafficResStocklog = new TrafficResStocklog();
        trafficResStocklog.setAdjustState(2);

        GroupOrder order = groupOrderBiz.selectByPrimaryKey(groupOrderId);

        Integer extResState = 2;

        //更新group_order表
        order.setExtResState(extResState);
        order.setState(0);
        int nums = groupOrderBiz.loadUpdateExtResState(order);

        //若状态改为已确认，则需要更改 traffic_res_stocklog 预留订单状态为已确认
        trafficResStocklog.setResId(order.getExtResId());
        trafficResStocklog.setOrderId(order.getId());
        trafficResBiz.updateStockLog_AdjustState(trafficResStocklog);

        //更新产品已售数量
        TrafficResProduct trafficResProduct = trafficResBiz.selectTrafficProductInfoByProductCode(
                order.getProductId(), order.getExtResId());
        if (null != trafficResProduct) {
            Integer sumPerson = groupOrderBiz.selectSumPersonByProductId(trafficResProduct.getResId(),
                    trafficResProduct.getProductCode(), order.getDepartureDate());
            trafficResProduct.setNumSold(sumPerson);
            trafficResBiz.updateNumSoldById(trafficResProduct);
        }

        //更新库存
        trafficResBiz.updateStockOrStockDisable(order.getExtResId());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", nums);
        WebResult<Map<String, Object>> webResult = new WebResult<Map<String, Object>>();
        webResult.setValue(map);
        return webResult;
    }

    @Override
    public WebResult<List<TrafficRes>> getProductInfoByYearToWX(Integer bizId, String dateTime,String endTime) {
        List<TrafficRes> trafficResList = trafficResBiz.findProductInfoByYearToWX(bizId, dateTime, endTime);
        WebResult<List<TrafficRes>> webResult = new WebResult<List<TrafficRes>>();
        if (CollectionUtils.isEmpty(trafficResList)) {
            webResult.setValue(Collections.<TrafficRes>emptyList());
        }else {
            webResult.setValue(trafficResList);
        }
        return webResult;
    }

    @Override
    public WebResult<List<TrafficResProduct>> getProductInfoListByTimeToWX(Integer bizId, String dateTime, Integer trId, Integer supplierId) {
        List<TrafficResProduct> trafficResProductList = trafficResProductBiz.findProductInfoListByTimeToWX(bizId, dateTime, trId, supplierId);
        WebResult<List<TrafficResProduct>> webResult = new WebResult<List<TrafficResProduct>>();
        if (CollectionUtils.isEmpty(trafficResProductList)) {
            webResult.setValue(Collections.<TrafficResProduct>emptyList());
        }else {
            webResult.setValue(trafficResProductList);
        }
        return webResult;
    }
}
