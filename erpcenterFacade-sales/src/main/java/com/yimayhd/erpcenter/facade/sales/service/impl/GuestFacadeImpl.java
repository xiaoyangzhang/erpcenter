package com.yimayhd.erpcenter.facade.sales.service.impl;

import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.*;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.*;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.biz.sys.service.SysBizBankAccountBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.facade.sales.result.GuestResult;
import com.yimayhd.erpcenter.facade.sales.result.InitGroupResult;
import com.yimayhd.erpcenter.facade.sales.service.GuestFacade;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.biz.service.SupplierDriverBiz;
import com.yimayhd.erpresource.biz.service.SupplierGuideBiz;
import com.yimayhd.erpresource.biz.service.SupplierImgBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/31 18:25
 */
public class GuestFacadeImpl implements GuestFacade {
    private static final Logger logger = LoggerFactory.getLogger("InitGroupFacadeImpl");
    @Autowired
    private PlatformEmployeeBiz platformEmployeeBiz;
    @Autowired
    private GroupOrderBiz groupOrderBiz;
    @Autowired
    private DicBiz dicBiz;
    @Autowired
    private SupplierBiz supplierBiz;
    @Autowired
    private GroupRouteBiz groupRouteBiz;
    @Autowired
    private BookingDeliveryBiz bookingDeliveryBiz;
    @Autowired
    private SupplierDriverBiz supplierDriverBiz;
    @Autowired
    private SupplierGuideBiz supplierGuideBiz;
    @Autowired
    private GroupOrderTransportBiz groupOrderTransportBiz;
    @Autowired
    private GroupOrderGuestBiz groupOrderGuestBiz;
    @Autowired
    private BookingShopBiz bookingShopBiz;
    @Autowired
    private TourGroupBiz tourGroupBiz;
    @Autowired
    private FinanceBiz financeBiz;
    @Autowired
    private BookingSupplierDetailBiz bookingSupplierDetailBiz;
    @Autowired
    private BookingGuideBiz bookingGuideBiz;
    @Autowired
    private SupplierImgBiz supplierImgBiz;
    @Autowired
    private RegionBiz regionBiz;
    @Autowired
    private PlatformOrgBiz platformOrgBiz;
    @Autowired
    private GroupOrderPriceBiz groupOrderPriceBiz;
    @Autowired
    private GroupRequirementBiz groupRequirementBiz;
    @Autowired
    private SysBizBankAccountBiz sysBizBankAccountBiz;
    @Autowired
    private ProductGroupSupplierBiz productGroupSupplierBiz;
    @Autowired
    private ProductGroupBiz productGroupBiz;
    @Autowired
    private SpecialGroupOrderBiz specialGroupOrderBiz;
    @Autowired
    private BookingSupplierBiz bookingSupplierBiz;
    @Override
    public GuestResult saveGuest(GroupOrderGuest groupOrderTransport) {
        GuestResult guestResult = new GuestResult();
        try {
            groupOrderGuestBiz.insertSelective(groupOrderTransport) ;
        } catch (Exception e) {
            logger.error("", e);
        }
        return guestResult;
    }

    @Override
    public GuestResult editGuest(Integer id) {
        GuestResult guestResult = new GuestResult();
        try {
            GroupOrderGuest groupOrderTransport = groupOrderGuestBiz.selectByPrimaryKey(id);
            guestResult.setGroupOrderGuest(groupOrderTransport);
        } catch (Exception e) {
            logger.error("", e);
        }
        return guestResult;
    }

    @Override
    public GuestResult updateGuest(GroupOrderGuest groupOrderTransport) {
        GuestResult guestResult = new GuestResult();
        try {
            groupOrderGuestBiz.updateByPrimaryKeySelective(groupOrderTransport) ;
        } catch (Exception e) {
            logger.error("", e);
        }
        return guestResult;
    }

    @Override
    public GuestResult deleteGuestById(Integer id) {
        GuestResult guestResult = new GuestResult();
        try {
            groupOrderGuestBiz.deleteByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("", e);
        }
        return guestResult;
    }

    @Override
    public GuestResult batchInput(List<String> userArray) {
        GuestResult guestResult = new GuestResult();
        try {
            GroupOrderGuest groupOrderGuest = null;
            //身份证存的是id
            DicInfo dicInfo = dicBiz.getDicInfoByTypeCodeAndDicCode(BasicConstants.GYXX_ZJLX, BasicConstants.GYXX_ZJLX_SFZ);
            for (int i = 0; i < userArray.size(); i++) {
                String dataString = userArray.get(i);
                String[] ss = dataString.replace("\\n", "").replace(";", "").replace("，", ",").split(",");
                groupOrderGuest = new GroupOrderGuest();
                if (ss.length == 5) {
                    groupOrderGuest.setName(ss[0]);
                    groupOrderGuest.setCertificateNum(ss[1]);
                    groupOrderGuest.setMobile(ss[2]);
                    groupOrderGuest.setOrderId(Integer.parseInt(ss[3]));
                    groupOrderGuest.setType(Integer.parseInt(ss[4]));
                    groupOrderGuest.setIsSingleRoom(0);
                    groupOrderGuest.setIsLeader(0);
                    groupOrderGuest.setGender(1);
                    groupOrderGuest.setCreateTime(new Date().getTime());
                } else if (ss.length == 8) {
                    String s = ss[1];
                    int gender = 0;
                    if (s.equals("男")) {
                        gender = 1;
                    } else {
                        gender = 0;
                    }
                    groupOrderGuest.setName(ss[0]);
                    groupOrderGuest.setGender(gender);
                    groupOrderGuest.setAge(Integer.parseInt(ss[2]));
                    groupOrderGuest.setNativePlace(ss[3]);
                    groupOrderGuest.setCertificateTypeId(dicInfo.getId());
                    groupOrderGuest.setCertificateNum(ss[4]);
                    groupOrderGuest.setMobile(ss[5]);
                    groupOrderGuest.setOrderId(Integer.parseInt(ss[6]));
                    groupOrderGuest.setType(Integer.parseInt(ss[7]));
                    groupOrderGuest.setIsSingleRoom(0);
                    groupOrderGuest.setIsLeader(0);
                    groupOrderGuest.setCreateTime(new Date().getTime());
                }
            }
                groupOrderGuestBiz.insertSelective(groupOrderGuest);
            }catch(Exception e){
                logger.error("", e);
            }
            return guestResult;
        }

    @Override
    public GuestResult matchNum(Integer orderId, Integer count) {
        GuestResult guestResult = new GuestResult();
        try {
            GroupOrder groupOrder = groupOrderBiz.findById(orderId) ;
            Integer num = groupOrder.getNumAdult()+groupOrder.getNumChild()+groupOrder.getNumGuide() ;
            Integer numAdult = groupOrderGuestBiz.selectNumAdultByOrderID(orderId) ;
            Integer numChild = groupOrderGuestBiz.selectNumChildByOrderID(orderId) ;
            Integer numGuide = groupOrderGuestBiz.selectNumGuideByOrderID(orderId) ;
            Integer it = num-(numAdult+numChild+numGuide) ;
            guestResult.setIt(it);
        }catch(Exception e){
            logger.error("", e);
        }
        return guestResult;
    }

    @Override
    public GuestResult matchNum(Integer orderId) {
        GuestResult guestResult = new GuestResult();
        try {
            GroupOrder groupOrder = groupOrderBiz.findById(orderId) ;
            Integer num = groupOrder.getNumGuide() ;
            Integer numGuide = groupOrderGuestBiz.selectNumGuideByOrderID(orderId) ;
            Integer it = num-numGuide;
        }catch(Exception e){
            logger.error("", e);
        }
        return guestResult;
    }

    @Override
    public GuestResult getCertificateNums(Integer orderId) {
        GuestResult guestResult = new GuestResult();
        try {
            // 客人列表
            List<GroupOrderGuest> groupOrderGuests = groupOrderGuestBiz
                    .selectByOrderId(orderId);
            StringBuilder sb = new StringBuilder();
            for (GroupOrderGuest guest : groupOrderGuests) {
                sb.append(guest.getCertificateNum()+",") ;
            }
            guestResult.setCertificateNums(sb.toString());
        }catch(Exception e){
            logger.error("", e);
        }
        return guestResult;
    }

    @Override
    public GuestResult guestCertificateNumValidate(String guestCertificateNum, Integer orderId) {
        GuestResult guestResult = new GuestResult();
        try {
            List<GroupOrderGuest> guests = groupOrderGuestBiz.getGuestByGuestCertificateNum(guestCertificateNum,orderId);
            guestResult.setGroupOrderGuestList(guests);
        }catch(Exception e){
            logger.error("", e);
        }
        return guestResult;
    }

    @Override
    public GuestResult getGuestOrderInfo(String guestCertificateNum, Integer orderId) {
        GuestResult guestResult = new GuestResult();
        try {
            List<GroupOrder> orders = new ArrayList<GroupOrder>() ;
            List<GroupOrderGuest> guests = groupOrderGuestBiz.getGuestByGuestCertificateNum(guestCertificateNum,orderId) ;
            for (GroupOrderGuest guest : guests) {
                orders.add(groupOrderBiz.selectByPrimaryKey(guest.getOrderId())) ;
            }
            guestResult.setGroupOrderList(orders);
        }catch(Exception e){
            logger.error("", e);
        }
        return guestResult;
    }
}
