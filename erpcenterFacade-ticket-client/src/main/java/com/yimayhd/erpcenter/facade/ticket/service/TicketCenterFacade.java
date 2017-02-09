package com.yimayhd.erpcenter.facade.ticket.service;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.facade.ticket.result.WebResult;

import java.util.Map;

/**
 * Created by zhangxy on 2017/2/7.
 */
public interface TicketCenterFacade {

//    private final SysBizInfoService bizInfoService;
//    private final PlatformEmployeeService platformEmployeeService;
//    private final TrafficResService trafficResService;
//    private final TrafficResProductService trafficResProductService;
//    private final ProductInfoService productInfoService;
//    private final ProductRouteService productRouteService;
//    private final ProductRemarkService productRemarkService;
//    private final PlatformOrgService platformOrgService;
//    private final GroupOrderService groupOrderService;
//    private final SpecialGroupOrderService specialGroupOrderService;
//    private final SupplierService supplierService;
//    private final TourGroupService tourGroupService;
    public WebResult<Map<String,Object>> loginTicketCenter(String loginName, String password, String code);
    public PageBean getResProductListToWX(PageBean pageBean);
    public WebResult<Map<String,Object>> getResProductInfoToWX(Integer trpId, Integer resId);
    public WebResult<Map<String,Object>> getProductInfoToWX(Integer id);
    public WebResult<Map<String,Object>> getResOrderToWX(Integer trpId, Integer resId);
    public WebResult<String> verifyGroupOrderToWX(Integer bizId, Integer supplierId, String depaDate);
//    public WebResult<Map<String,Object>> saveResOrderToWX(ResOrder resOrder);
    public WebResult<Map<String,Object>> getGroupOrderToWX(Integer bizId, Integer extResState, Integer supplierId);
    public WebResult<Map<String,Object>> getGroupOrderDetailToWX(Integer bizId, Integer groupOrderId);
    public WebResult<Map<String,Object>> cancelOrder(Integer groupOrderId);
    public WebResult<Map<String,Object>> getProductInfoByYearToWX(Integer bizId, String dateTime);
    public WebResult<Map<String,Object>> getProductInfoListByTimeToWX(Integer bizId, String dateTime, Integer trId, Integer supplierId);






}
