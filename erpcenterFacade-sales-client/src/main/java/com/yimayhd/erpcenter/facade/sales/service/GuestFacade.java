package com.yimayhd.erpcenter.facade.sales.service;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.facade.sales.result.GuestResult;
import com.yimayhd.erpcenter.facade.sales.result.InitGroupResult;

import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/31 18:20
 */
public interface GuestFacade {
    public GuestResult saveGuest(GroupOrderGuest groupOrderTransport);
    public GuestResult editGuest(Integer id);
    public GuestResult updateGuest(GroupOrderGuest groupOrderTransport);
    public GuestResult deleteGuestById(Integer id);
    public GuestResult batchInput(List<String> userArray);
    public GuestResult matchNum(Integer orderId,Integer count);
    public GuestResult matchNum(Integer orderId);
    public GuestResult getCertificateNums(Integer orderId);
    public GuestResult guestCertificateNumValidate(String guestCertificateNum,Integer orderId);
    public GuestResult getGuestOrderInfo( String guestCertificateNum,Integer orderId);



}
