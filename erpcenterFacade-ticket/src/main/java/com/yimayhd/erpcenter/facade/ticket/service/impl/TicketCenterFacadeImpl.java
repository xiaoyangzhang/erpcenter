package com.yimayhd.erpcenter.facade.ticket.service.impl;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.facade.ticket.result.WebResult;
import com.yimayhd.erpcenter.facade.ticket.service.TicketCenterFacade;

import java.util.Map;

/**
 * Created by zhangxy on 2017/2/7.
 */
public class TicketCenterFacadeImpl implements TicketCenterFacade {
    @Override
    public WebResult<Map<String, Object>> loginTicketCenter(String loginName, String password, String code) {
        return null;
    }

    @Override
    public PageBean getResProductListToWX(PageBean pageBean) {
        return null;
    }

    @Override
    public WebResult<Map<String, Object>> getResProductInfoToWX(Integer trpId, Integer resId) {
        return null;
    }

    @Override
    public WebResult<Map<String, Object>> getProductInfoToWX(Integer id) {
        return null;
    }

    @Override
    public WebResult<Map<String, Object>> getResOrderToWX(Integer trpId, Integer resId) {
        return null;
    }

    @Override
    public WebResult<String> verifyGroupOrderToWX(Integer bizId, Integer supplierId, String depaDate) {
        return null;
    }

//    @Override
//    public WebResult<Map<String, Object>> saveResOrderToWX(ResOrder resOrder) {
//        return null;
//    }

    @Override
    public WebResult<Map<String, Object>> getGroupOrderToWX(Integer bizId, Integer extResState, Integer supplierId) {
        return null;
    }

    @Override
    public WebResult<Map<String, Object>> getGroupOrderDetailToWX(Integer bizId, Integer groupOrderId) {
        return null;
    }

    @Override
    public WebResult<Map<String, Object>> cancelOrder(Integer groupOrderId) {
        return null;
    }

    @Override
    public WebResult<Map<String, Object>> getProductInfoByYearToWX(Integer bizId, String dateTime) {
        return null;
    }

    @Override
    public WebResult<Map<String, Object>> getProductInfoListByTimeToWX(Integer bizId, String dateTime, Integer trId, Integer supplierId) {
        return null;
    }
}
