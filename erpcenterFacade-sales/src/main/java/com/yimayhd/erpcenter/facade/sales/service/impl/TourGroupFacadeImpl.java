package com.yimayhd.erpcenter.facade.sales.service.impl;

import com.alibaba.fastjson.JSON;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.*;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.biz.sys.service.SysBizBankAccountBiz;
import com.yimayhd.erpcenter.common.util.PageParameterCheckAndDealUtil;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.vo.ProductGroupSupplierVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductSupplierCondition;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.*;
import com.yimayhd.erpcenter.dal.sales.client.sales.query.GroupInfoQueryForCarCar;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.*;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;
import com.yimayhd.erpcenter.facade.sales.errorcode.OperationErrorCode;
import com.yimayhd.erpcenter.facade.sales.query.ChangeGroupDTO;
import com.yimayhd.erpcenter.facade.sales.query.ProfitQueryByTourDTO;
import com.yimayhd.erpcenter.facade.sales.query.ToSKConfirmPreviewDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToOrderLockTableDTO;
import com.yimayhd.erpcenter.facade.sales.result.*;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToOrderLockListResult;
import com.yimayhd.erpcenter.facade.sales.service.TourGroupFacade;
import com.yimayhd.erpcenter.facade.sales.utils.DateUtils;
import com.yimayhd.erpcenter.facade.sales.utils.GroupCodeUtil;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.biz.service.SupplierDriverBiz;
import com.yimayhd.erpresource.biz.service.SupplierGuideBiz;
import com.yimayhd.erpresource.biz.service.SupplierImgBiz;
import com.yimayhd.erpresource.dal.constants.BasicConstants;
import com.yimayhd.erpresource.dal.po.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/27 11:01
 */
public class TourGroupFacadeImpl implements TourGroupFacade {
    private static final Logger logger = LoggerFactory.getLogger("TourGroupFacadeImpl");
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
    private BookingSupplierBiz bookingSupplierBiz;
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


    @Override
    public ToChangeGroupResult toChangeGroup(int groupId) {
        ToChangeGroupResult toChangeGroupResult = new ToChangeGroupResult();
        try {
            TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(groupId);
            List<GroupOrder> orderList = null;
            tourGroup.setGroupCode(tourGroup.getGroupCode() + "变更");
            tourGroup.setGroupCodeMark(tourGroup.getGroupCodeMark());

            if (tourGroup.getGroupMode() == 0 || tourGroup.getGroupMode() == -1 || tourGroup.getGroupMode() > 0) {
                orderList = groupOrderBiz.selectOrderByGroupId(groupId);
            }
            toChangeGroupResult.setTourGroup(tourGroup);
            toChangeGroupResult.setOrderList(orderList);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toChangeGroupResult;
    }

    @Override
    public ToChangeGroupResult changeGroup(ChangeGroupDTO changeGroupDTO) {
        ToChangeGroupResult toChangeGroupResult = new ToChangeGroupResult();
        try {
            int groupId = tourGroupBiz.changeGroup(changeGroupDTO.getGroupId(), changeGroupDTO.getTourGroup(), changeGroupDTO.getOrderIds(), changeGroupDTO.getCurUserId(), changeGroupDTO.getCurUserName(), changeGroupDTO.getInfo());
            financeBiz.calcTourGroupAmount(groupId);                         // 增加计算
            TourGroup group = tourGroupBiz.selectByPrimaryKey(groupId);
            toChangeGroupResult.setGroupId(groupId);
            toChangeGroupResult.setTourGroup(group);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toChangeGroupResult;
    }

    @Override
    public GetPushInfoResult getPushInfo(int groupId) {
        GetPushInfoResult getPushInfoResult = new GetPushInfoResult();
        try {
            TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(groupId);
            GroupRouteVO groupRouteVO = groupRouteBiz
                    .findGroupRouteByGroupId(groupId);
            List<GroupRouteDayVO> routeDayVOList = groupRouteVO
                    .getGroupRouteDayVOList();
            List<GroupOrderGuest> guestList = new ArrayList<GroupOrderGuest>();
            List<GroupOrder> groupOrdrList = groupOrderBiz
                    .selectOrderByGroupId(groupId);
            for (GroupOrder groupOrder : groupOrdrList) {
                List<GroupOrderGuest> selectByOrderId = groupOrderGuestBiz
                        .selectByOrderId(groupOrder.getId());
                guestList.addAll(selectByOrderId);
            }
            BookingGuide guide = bookingGuideBiz.selectByGroupId(groupId);
            BookingSupplierDetail supplierDetail = null;
            if (guide != null) {
                supplierDetail = bookingSupplierDetailBiz
                        .selectByPrimaryKey(guide.getBookingDetailId());
            }
            List<BookingSupplierDetail> driverList = bookingSupplierDetailBiz
                    .getDriversByGroupIdAndType(groupId, Constants.FLEET);
            List<BookingShop> bookingShops = bookingShopBiz
                    .getShopListByGroupId(groupId);
            List<BookingGuide> guides = bookingGuideBiz
                    .selectGuidesByGroupId(groupId);

            getPushInfoResult.setTourGroup(tourGroup);
            getPushInfoResult.setRouteDayVOList(routeDayVOList);
            getPushInfoResult.setGuestList(guestList);
            getPushInfoResult.setBookingShops(bookingShops);
            getPushInfoResult.setGuide(guide);
            getPushInfoResult.setSupplierDetail(supplierDetail);
            getPushInfoResult.setDriverList(driverList);
            getPushInfoResult.setGuides(guides);
        } catch (Exception e) {
            logger.error("", e);
        }
        return getPushInfoResult;
    }

    @Override
    public ResultSupport updateGuide(Integer groupId, Integer driverId, Integer guideId) {
        ResultSupport resultSupport = new ResultSupport();
        try {
            BookingGuide guide = new BookingGuide();
            if (driverId == null) {
                driverId = -1;
            }
            guide.setId(guideId);
            guide.setBookingDetailId(driverId);
            guide.setGroupId(groupId);
            bookingGuideBiz.updateByPrimaryKeySelective(guide);
        } catch (Exception e) {
            logger.error("", e);
        }
        return resultSupport;
    }

    @Override
    public ResultSupport updateShop(String guideName, Integer guideId, Integer shopId) {
        ResultSupport resultSupport = new ResultSupport();
        try {
            if (guideId == null) {
                guideId = -1;
            }
            BookingShop bs = new BookingShop();
            bs.setId(shopId);
            bs.setGuideId(guideId);
            bs.setGuideName(guideName);
            bookingShopBiz.updateByPrimaryKeySelective(bs);
        } catch (Exception e) {
            logger.error("", e);
        }
        return resultSupport;
    }

    @Override
    public ResultSupport pushInfo(Integer groupId) {
        ResultSupport resultSupport = new ResultSupport();
        try {
            TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(groupId);
/*
            List<Customer> customerList = new ArrayList<Customer>();

            // PlatformEmployeePo user = sysPlatformEmployeeFacade
            // .findByEmployeeId(tourGroup.getOperatorId());
            // Customer ct = new Customer();
            // ct.setName(user.getName());
            // ct.setMobile(user.getMobile());
            // ct.setGender((byte) user.getGender().intValue());
            // ct.setType((byte) 3);
            // ct.setBizId(WebUtils.getCurBizId(request));
            // customerList.add(ct);

            List<TravelHistoryRoute> travelHistoryRouteList = new ArrayList<TravelHistoryRoute>();
            List<GroupOrder> groupOrdrList = groupOrderBiz
                    .selectOrderByGroupId(groupId);

            for (int i = 0; i < groupOrdrList.size(); i++) {

                List<GroupOrderGuest> selectByOrderId = groupOrderGuestService
                        .selectByOrderId(groupOrdrList.get(i).getId());

                if (selectByOrderId != null && selectByOrderId.size() > 0) {
                    for (GroupOrderGuest groupOrderGuest : selectByOrderId) {
                        Customer customer = new Customer();
                        customer.setName(groupOrderGuest.getName());
                        customer.setMobile(groupOrderGuest.getMobile());
                        customer.setAge(groupOrderGuest.getAge());
                        customer.setGender((byte) groupOrderGuest.getGender()
                                .intValue());
                        customer.setIdentityNo(groupOrderGuest.getCertificateNum());
                        customer.setIdentityType((byte) groupOrderGuest
                                .getCertificateTypeId().intValue());
                        customer.setNativePlaceName(groupOrderGuest
                                .getNativePlace());
                        customer.setNote(groupOrderGuest.getRemark());
                        customer.setType((byte) 1);
                        customer.setBizId(WebUtils.getCurBizId(request));
                        customerList.add(customer);

                        TravelHistoryRoute thr = new TravelHistoryRoute();
                        thr.setGroupId(groupId);
                        thr.setCreatorName(groupOrderGuest.getName());
                        thr.setGroupDate(tourGroup.getDateStart());
                        thr.setCreatorIdentity(groupOrderGuest.getCertificateNum());
                        thr.setTotalDays(tourGroup.getDaynum());
                        thr.setBizId(WebUtils.getCurBizId(request));
                        thr.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        thr.setRouteTitle(tourGroup.getProductBrandName());
                        thr.setRouteName(tourGroup.getProductName());
                        thr.setUserType(1);
                        thr.setFamilyNo(i + 1);
                        travelHistoryRouteList.add(thr);
                    }
                }

            }

            BookingGuide guide = bookingGuideService.selectByGroupId(groupId);
            if (guide != null) {
                SupplierGuide supplierGuide = supplierGuideService
                        .getGuideInfoById(guide.getGuideId());
                Customer customer = new Customer();
                customer.setName(supplierGuide.getName());
                customer.setMobile(supplierGuide.getMobile());
                customer.setGender((byte) supplierGuide.getGender().intValue());
                customer.setIdentityNo(supplierGuide.getIdCardNo());
                customer.setNativePlaceName(supplierGuide.getNativePlace());
                customer.setType((byte) 2);
                customer.setBizId(WebUtils.getCurBizId(request));
                customerList.add(customer);

                TravelHistoryRoute thr = new TravelHistoryRoute();
                thr.setGroupId(groupId);
                thr.setCreatorName(supplierGuide.getName());
                thr.setGroupDate(tourGroup.getDateStart());
                thr.setCreatorIdentity(supplierGuide.getIdCardNo());
                thr.setTotalDays(tourGroup.getDaynum());
                thr.setBizId(WebUtils.getCurBizId(request));
                thr.setCreateTime(new Timestamp(System.currentTimeMillis()));
                thr.setRouteTitle(tourGroup.getProductBrandName());
                thr.setRouteName(tourGroup.getProductName());
                thr.setUserType(2);
                travelHistoryRouteList.add(thr);

            }

            try {
                customerService.batchInsertCustomer(customerList);
            } catch (Exception e) {
                log.error("推送APP错误信息:" + e.getMessage());
                return errorJson("推送信息失败,请检查客人信息是否正确");
            }

            try {
                travelHistoryRouteService.batchInsertTravelHistoryRoute(
                        travelHistoryRouteList, groupId);
            } catch (Exception e) {
                log.error("推送APP错误信息:" + e.getMessage());
                return errorJson("推送信息失败,请检查行程信息是否正确");
            }*/
        } catch (Exception e) {
            logger.error("", e);
        }
        return resultSupport;
    }

    @Override
    public PushWapResult pushWap(Integer groupId) {
        PushWapResult pushWapResult = new PushWapResult();
        try {
            TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(groupId);

            //团信息
            AssistantGroupVO groupVo = new AssistantGroupVO();
            AssistantGroup g = new AssistantGroup();
            List<AssistantGroupOrder> orderList = new ArrayList<AssistantGroupOrder>();
            List<AssistantGroupOrderGuest> orderGuestList = new ArrayList<AssistantGroupOrderGuest>();
            List<AssistantGroupOrderTransport> orderTransportList = new ArrayList<AssistantGroupOrderTransport>();
            List<AssistantGroupGuide> guideList = new ArrayList<AssistantGroupGuide>();
            List<AssistantGroupRoute> routeList = new ArrayList<AssistantGroupRoute>();
            List<AssistantGroupRouteSupplier> routeSupplierList = new ArrayList<AssistantGroupRouteSupplier>();
            List<AssistantGroupRouteAttachment> routeAttachList = new ArrayList<AssistantGroupRouteAttachment>();

            groupVo.setGroup(g);
            groupVo.setOrderList(orderList);
            groupVo.setOrderGuestList(orderGuestList);
            groupVo.setOrderTransportList(orderTransportList);
            groupVo.setGuideList(guideList);
            groupVo.setRouteList(routeList);
            groupVo.setRouteSupplierList(routeSupplierList);
            groupVo.setRouteAttachmentList(routeAttachList);


            //group赋值
            g.setBizId(tourGroup.getBizId());
            g.setId(0);
            g.setGroupId(groupId);
            g.setGroupMode(tourGroup.getGroupMode());
            g.setGroupCode(tourGroup.getGroupCode());
            g.setProductBrandName(tourGroup.getProductBrandName());
            g.setProductName(tourGroup.getProductName());
            g.setDaynum(tourGroup.getDaynum());
            g.setDateStart(DateUtils.format(tourGroup.getDateStart(), "yyyy-MM-dd"));
            g.setDateEnd(DateUtils.format(tourGroup.getDateEnd(),"yyyy-MM-dd"));
            g.setTotalAdult(tourGroup.getTotalAdult());
            g.setTotalChild(tourGroup.getTotalChild());
            g.setTotalGuide(tourGroup.getTotalGuide());
            g.setWarmNotice(tourGroup.getWarmNotice());
            g.setOperatorName(tourGroup.getOperatorName());
            g.setUpdateState(1);
            g.setUpdateTime(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

            //groupOrder赋值
            List<GroupOrder> groupOrdrList = groupOrderBiz.selectOrderByGroupId(groupId);
            for (int i = 0; i < groupOrdrList.size(); i++) {
                GroupOrder ord = groupOrdrList.get(i);
                //订单信息
                AssistantGroupOrder ord1 = new AssistantGroupOrder();
                ord1.setId(0);
                ord1.setOrderId(ord.getId());
                ord1.setBizId(ord.getBizId());
                ord1.setGroupId(ord.getGroupId());
                ord1.setOrderType(ord.getOrderType());
                ord1.setSupplierName(ord.getSupplierName());
                ord1.setContactName(ord.getContactName());
                ord1.setContactTel(ord.getContactTel());
                ord1.setContactMobile(ord.getContactMobile());
                ord1.setSaleOperatorName(ord.getSaleOperatorName());
                ord1.setNumAdult(ord.getNumAdult());
                ord1.setNumChild(ord.getNumChild());
                ord1.setNumGuide(ord.getNumGuide());
                ord1.setDepartureDate(ord.getDepartureDate());
                ord1.setProductBrandName(ord.getProductBrandName());
                ord1.setProductName(ord.getProductName());
                ord1.setReceiveMode(ord.getReceiveMode());
                ord1.setState(ord.getState());
                ord1.setProvinceName(ord.getProvinceName());
                ord1.setCityName(ord.getCityName());
                orderList.add(ord1);

                //客人
                List<GroupOrderGuest> guestList = groupOrderGuestBiz.selectByOrderId(ord.getId());
                if (guestList != null && guestList.size() > 0) {
                    for (GroupOrderGuest item : guestList) {
                        AssistantGroupOrderGuest guest = new AssistantGroupOrderGuest();
                        guest.setId(0);
                        guest.setGroupId(tourGroup.getId());
                        guest.setOrderId(item.getOrderId());
                        guest.setName(item.getName());
                        guest.setType(item.getType());
                        guest.setCertificateNum(item.getCertificateNum());
                        guest.setGender(item.getGender());
                        guest.setMobile(item.getMobile());
                        guest.setNativePlace(item.getNativePlace());
                        guest.setAge(item.getAge());
                        guest.setCareer(item.getCareer());
                        guest.setIsSingleRoom(item.getIsSingleRoom());
                        guest.setIsLeader(item.getIsLeader());
                        guest.setIsGuide(item.getIsGuide()==null?0:item.getIsGuide());
                        guest.setRemark(item.getRemark());
                        orderGuestList.add(guest);
                    }
                }
                //接送交通
                List<GroupOrderTransport> transList =  groupOrderTransportBiz.selectByOrderId(ord.getId());
                if (transList != null && transList.size() > 0) {
                    for (GroupOrderTransport item : transList) {
                        AssistantGroupOrderTransport trans = new AssistantGroupOrderTransport();
                        trans.setId(0);
                        trans.setGroupId(tourGroup.getId());
                        trans.setOrderId(item.getOrderId());
                        trans.setClassNo(item.getClassNo());
                        trans.setDepartureCity(item.getDepartureCity());
                        trans.setDepartureDate("".equals(item.getArrivalDate())?"": DateUtils.format(item.getDepartureDate()));
                        trans.setDepartureTime(item.getDepartureTime());
                        trans.setArrivalCity(item.getArrivalCity());
                        trans.setArrivalDate("".equals(item.getArrivalDate())?"": DateUtils.format(item.getArrivalDate()));
                        trans.setArrivalTime(item.getArrivalTime());
                        trans.setTrans_type(item.getType());

                        orderTransportList.add(trans);
                    }
                }

                //TODO 订单行程 暂时先不做
                List<GroupRoute> rlist = groupRouteBiz.selectByOrderId(ord.getId());
                if (rlist != null && rlist.size() > 0) {
                    for (GroupRoute item : rlist) {
                        AssistantGroupRoute gRoute = new AssistantGroupRoute();
                        gRoute.setId(0);
                        gRoute.setRouteId(item.getId());
                        gRoute.setOrderId(item.getOrderId());
                        gRoute.setGroupId(0);
                        if (tourGroup.getGroupMode()>0){
                            //团队情况
                            gRoute.setGroupId(tourGroup.getId());
                        }
                        gRoute.setDayNum(item.getDayNum());
                        gRoute.setGroupDate(DateUtils.format(item.getGroupDate()));
                        gRoute.setBreakfast(item.getBreakfast());
                        gRoute.setLunch(item.getLunch());
                        gRoute.setSupper(item.getSupper());
                        gRoute.setHotelName(item.getHotelName());
                        gRoute.setRouteDesp(item.getRouteDesp());
                        gRoute.setRouteTip(item.getRouteTip());

                        routeList.add(gRoute);
                    }
                }
            }

            //guideList 司机及导游
            List<BookingGuide> gList = bookingGuideBiz.selectGuidesByGroupId(groupId);
            if (gList != null && gList.size() > 0) {
                for (BookingGuide item : gList) {
                    AssistantGroupGuide gGuide = new AssistantGroupGuide();
                    gGuide.setId(0);
                    gGuide.setGroupId(groupId);
                    gGuide.setGuideId(item.getGuideId());
                    gGuide.setGuideName(item.getGuideName());
                    gGuide.setState(item.getIsDefault());

                    gGuide.setGuideGender(0);
                    gGuide.setGuidePhoto("");
                    gGuide.setGuideMobile("");
                    gGuide.setGuideCertificateNo("");
                    gGuide.setGuideLicenseNo("");
                    gGuide.setDriverGender(0);
                    gGuide.setDriverName("");
                    gGuide.setDriverMobile("");
                    gGuide.setDriverLicenseCar("");
                    gGuide.setDriverPhoto("");
                    gGuide.setDriverCompany("");
                    gGuide.setDriverId(null == item.getDriverId()?0:item.getDriverId());

                    SupplierGuide guideInfo = supplierGuideBiz.getGuideInfoById(item.getGuideId()); //数据库读取导游信息
                    if (null != guideInfo){
                        gGuide.setGuideMobile(guideInfo.getMobile());
                        gGuide.setGuideCertificateNo(guideInfo.getIdCardNo());
                        gGuide.setGuideLicenseNo(guideInfo.getLicenseNo());
                        gGuide.setGuideGender(guideInfo.getGender()==0?1:0);  //导游基础信息1女，0男， 接口数据：0女，1男
                    }
                    /**if (null != item.getDriverId()){
                        SupplierDriver sDriver = supplierDriverBiz.getDriverInfoById(item.getDriverId());//司机信息
                        if (sDriver != null){
                            gGuide.setDriverName(sDriver.getName());
                            gGuide.setDriverMobile(sDriver.getMobile());
                            gGuide.setDriverGender(sDriver.getGender()==0?1:0);  //司机基础信息1女，0男， 接口数据：0女，1男
                        }
                    }
                    if (null != item.getBookingDetailId()){
                        //车牌号
                        BookingSupplierDetail bookingSupplierDetail = bookingSupplierDetailBiz.selectByPrimaryKey(item.getBookingDetailId());
                        if (null != bookingSupplierDetail){
                            gGuide.setDriverLicenseCar(bookingSupplierDetail.getCarLisence());
                            //车队
                            BookingSupplier bs = bookingSupplierBiz.selectByPrimaryKey(bookingSupplierDetail.getBookingId());
                            gGuide.setDriverCompany(bs.getSupplierName());
                        }
                    }*/
                    if (null != item.getBookingDetailId()) {
                        // 车牌号
                        BookingSupplierDetail bookingSupplierDetail = bookingSupplierDetailBiz
                                .selectByPrimaryKey(item.getBookingDetailId());
                        if (null != bookingSupplierDetail) {
                            gGuide.setDriverLicenseCar(bookingSupplierDetail.getCarLisence());
                            // 车队
                            BookingSupplier bs = bookingSupplierBiz
                                    .selectByPrimaryKey(bookingSupplierDetail.getBookingId());
                            gGuide.setDriverCompany(bs.getSupplierName());

                            if (bookingSupplierDetail.getDriverId() != null){
                                SupplierDriver sDriver = supplierDriverBiz .getDriverInfoById(bookingSupplierDetail.getDriverId());// 司机信息
                                if (sDriver != null) {
                                    gGuide.setDriverName(sDriver.getName());
                                    gGuide.setDriverMobile(sDriver.getMobile());
                                    gGuide.setDriverGender(sDriver.getGender() == 0 ? 1 : 0); // 司机基础信息1女，0男，
                                }
                            }

                        }
                    }
                    guideList.add(gGuide);
                }
            }

            //routeList 行程线路
            List<GroupRoute> rlist = groupRouteBiz.selectByGroupId(groupId);
            if (rlist != null && rlist.size() > 0) {
                for (GroupRoute item : rlist) {
                    AssistantGroupRoute gRoute = new AssistantGroupRoute();
                    gRoute.setId(0);
                    gRoute.setRouteId(item.getId());
                    gRoute.setOrderId(0);
                    gRoute.setGroupId(groupId);
                    gRoute.setDayNum(item.getDayNum());
                    gRoute.setGroupDate(DateUtils.format(item.getGroupDate()));
                    gRoute.setBreakfast(item.getBreakfast());
                    gRoute.setLunch(item.getLunch());
                    gRoute.setSupper(item.getSupper());
                    gRoute.setHotelName(item.getHotelName());
                    gRoute.setRouteDesp(item.getRouteDesp());
                    gRoute.setRouteTip(item.getRouteTip());

                    routeList.add(gRoute);
                }
            }

            //routeSupplierList 行程路线每天的商家
            List<GroupRouteSupplier> gslist = groupRouteBiz.selectGroupRouteSupplierByGroupId(groupId);
            if (gslist != null && gslist.size() > 0) {
                for (GroupRouteSupplier item : gslist) {
                    AssistantGroupRouteSupplier gs = new AssistantGroupRouteSupplier();
                    gs.setId(0);
                    gs.setGroupId(groupId);
                    gs.setOrderId(0);
                    gs.setRouteId(item.getRouteId());
                    gs.setSupplierType(item.getSupplierType());
                    gs.setSupplierId(item.getSupplierId());
                    gs.setSupplierName(item.getSupplierName());

                    routeSupplierList.add(gs);
                }
            }


            //routeAttachList 行程路线每天图片
            List<GroupRouteAttachment> galist = groupRouteBiz.selectAttachmentByGroupId(groupId);
            if (galist != null && galist.size() > 0) {
                for (GroupRouteAttachment item : galist) {
                    AssistantGroupRouteAttachment ra = new AssistantGroupRouteAttachment();
                    ra.setId(0);
                    ra.setGroupId(groupId);
                    ra.setOrderId(0);
                    ra.setRouteId(item.getObjId());
                    ra.setName(item.getName());
                    ra.setPath(item.getPath());
                    routeAttachList.add(ra);
                }
            }

            //团行程用到的供应商及图片信息
            AssistantSupplierVO supplierVo = new AssistantSupplierVO();
            List<AssistantSupplier> supplierList = new ArrayList<AssistantSupplier>();
            List<AssistantSupplierImgType> supplierImgTypeList = new ArrayList<AssistantSupplierImgType>();
            List<AssistantSupplierImg> supplierImgList = new ArrayList<AssistantSupplierImg>();
            supplierVo.setSupplier(supplierList);
            supplierVo.setSupplierImgTypes(supplierImgTypeList);
            supplierVo.setSupplierImgs(supplierImgList);

            Set<Integer> supplierIds = new HashSet<Integer>();
            for(AssistantGroupRouteSupplier item : routeSupplierList){
                supplierIds.add(item.getSupplierId());
            }
            if (supplierIds.isEmpty())
                supplierIds.add(new Integer(0));

            List<SupplierInfo> supList = supplierBiz.selectSupplierListByIds(supplierIds);
            List<SupplierImgType>imgTypeList = supplierBiz.selectImgTypeBySupplierIds(supplierIds);
            List<SupplierImg> imgList = supplierImgBiz.selectImgBySupplierIds(supplierIds);
            for(SupplierInfo item: supList){
                AssistantSupplier as = new AssistantSupplier();
                as.setId(0);
                as.setSupplierId(item.getId());
                as.setSupplierType(item.getSupplierType());
                as.setNameFull(item.getNameFull());
                as.setNameEn(item.getNameEn());
                as.setLevel("");
                as.setProvinceName(item.getProvinceName());
                as.setCityName(item.getCityName());
                as.setAreaName(item.getAreaName());
                as.setTownName(item.getTownName());
                as.setAddress(item.getAddress());
                as.setWebsite(item.getWebsite());
                as.setPositionLat(item.getPositionLat());
                as.setPositionLon(item.getPositionLon());
                as.setIntroBrief(item.getIntroBrief());
                as.setIntroTip(item.getIntroTip());
                as.setState(item.getState());
                supplierList.add(as);
            }
            for(SupplierImgType item: imgTypeList){
                AssistantSupplierImgType st = new AssistantSupplierImgType();
                st.setId(0);
                st.setImgtypeId(item.getId());
                st.setSupplierId(item.getSupplierId());
                st.setBussinessType(item.getBussinessType());
                st.setTypeCode(item.getTypeCode());
                st.setTypeName(item.getTypeName());
                supplierImgTypeList.add(st);

                for(SupplierImg imItem: imgList){
                    if (imItem.getObjId().equals(item.getId())){
                        AssistantSupplierImg im = new AssistantSupplierImg();
                        im.setId(0);
                        im.setImgId(imItem.getId());
                        im.setImgName(imItem.getImgName());
                        im.setImgPath(imItem.getImgPath());
                        im.setImgtypeId(item.getId());
                        supplierImgList.add(im);
                    }
                }
            }

            pushWapResult.setGroupVo(groupVo);
            pushWapResult.setSupplierVo(supplierVo);
        } catch (Exception e) {
            logger.error("", e);
        }
        return pushWapResult;
    }

    @Override
    public ToChangeGroupResult toEditGroup(Integer groupId) {
        ToChangeGroupResult toChangeGroupResult = new ToChangeGroupResult();
        try {
            TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(groupId);
            toChangeGroupResult.setTourGroup(tourGroup);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toChangeGroupResult;
    }

    @Override
    public ToAddTourGroupOrderResult toAddTourGroupOrder(Integer groupId, Integer orderId, Integer state, String curUserName, Integer CurUserId,Integer bizId) {
        ToAddTourGroupOrderResult toAddTourGroupOrderResult = new ToAddTourGroupOrderResult();
        try {
            /**
             * 如果订单id==null,团id!=null，多查询一次订单id
             */
            if (orderId == null && groupId != null) {
                // 定制团中团和订单一对一的关系
                List<GroupOrder> orders = groupOrderBiz
                        .selectOrderByGroupId(groupId);
                if (orders.size() > 0) {
                    // 防止别人调错，多加一层验证
                    if (orders.get(0).getOrderType() == 1) {
                        orderId = orders.get(0).getId();
                    }
                }
            }

            // 收费类型
            List<DicInfo> typeList = dicBiz
                    .getListByTypeCode(BasicConstants.SALES_TEAM_TYPE,bizId);
            GroupOrder groupOrder = null;
            TourGroup tourGroup = null;
            if (orderId != null) {
                Map<String, Object> map = tourGroupBiz
                        .selectByGroupOrderId(orderId);
                groupOrder = (GroupOrder) map.get("groupOrder");
                tourGroup = (TourGroup) map.get("tourGroup");
                if (null != state && state.equals(4)) {
                    /**
                     * state=0的时候，只能查看，所以可以在这里设置财务状态等于1，财务状态=1的时候也只能查看
                     */
                    toAddTourGroupOrderResult.setStateFinance(1);
                    toAddTourGroupOrderResult.setState(state);
                } else {
                    if (null != groupOrder.getStateFinance()) {
                        toAddTourGroupOrderResult.setStateFinance(groupOrder.getStateFinance());
                    } else {
                        toAddTourGroupOrderResult.setStateFinance(0);
                    }
                    /**
                     * 将团状态放到model中是为了：当团状态为已确认团的时候，控制成本为不可维护
                     */
                    toAddTourGroupOrderResult.setState(state);
                }
                List<RegionInfo> cityList = null;
                if (groupOrder.getProvinceId() != null
                        && groupOrder.getProvinceId() != -1) {
                    cityList = regionBiz.getRegionById(groupOrder
                            .getProvinceId() + "");
                }
                toAddTourGroupOrderResult.setCityList(cityList);
            } else if (groupId != null && orderId == null) {
                /**
                 * 操作计调和销售计调默认为当前登陆用户
                 */
                groupOrder = new GroupOrder();
                tourGroup = tourGroupBiz.selectByPrimaryKey(groupId);
                groupOrder.setOperatorName(curUserName);
                /**
                 * 新增的时候财务默认状态未审核
                 */
                if (null != state && state.equals(4)) {
                    toAddTourGroupOrderResult.setStateFinance(1);
                } else {
                    toAddTourGroupOrderResult.setStateFinance(0);
                    toAddTourGroupOrderResult.setState(state);
                }
            } else {
                /**
                 * 操作计调和销售计调默认为当前登陆用户
                 */
                groupOrder = new GroupOrder();
                tourGroup = new TourGroup();
                groupOrder.setOperatorName(curUserName);
                groupOrder.setOperatorId(CurUserId);
                groupOrder.setSaleOperatorId(CurUserId);
                groupOrder.setSaleOperatorName(curUserName);
                /**
                 * 新增的时候财务默认状态未审核
                 */
                if (null != state && state.equals(4)) {
                  //  model.addAttribute("stateFinance", 1);
                    toAddTourGroupOrderResult.setStateFinance(1);
                } else {
                   // model.addAttribute("stateFinance", 0);
                  //  model.addAttribute("state", state);
                    toAddTourGroupOrderResult.setStateFinance(0);
                    toAddTourGroupOrderResult.setState(state);
                }
            }

            toAddTourGroupOrderResult.setTypeList(typeList);
            toAddTourGroupOrderResult.setGroupOrder(groupOrder);
            toAddTourGroupOrderResult.setTourGroup(tourGroup);
            List<DicInfo> sourceTypeList = dicBiz
                    .getListByTypeCode(Constants.GUEST_SOURCE_TYPE,bizId);
            toAddTourGroupOrderResult.setSourceTypeList(sourceTypeList);
            List<RegionInfo> allProvince = regionBiz.getAllProvince();
            toAddTourGroupOrderResult.setAllProvince(allProvince);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toAddTourGroupOrderResult;
    }

    @Override
    public ToChangeGroupResult saveTourGroupOrder(TourGroup tourGroup,GroupOrder groupOrder, Integer curBizId ,Integer curUserOrgId,Integer curUserId, String curUserName) {
        ToChangeGroupResult toChangeGroupResult = new ToChangeGroupResult();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            /**
             * 页面传过来的订单id不为空的时候新增，为空的时候修改
             */
            if (groupOrder.getId() == null) {
                tourGroup.setBizId(curBizId); // 用当前登陆用户ID表示商家ID
                tourGroup.setGroupCode(platformOrgBiz.getCompanyCodeByOrgId(curBizId, curUserOrgId));//
                tourGroup.setGroupState(0); // 团状态，默认为0
                tourGroup.setCreateTime(new Date().getTime()); // 创建时间
               // groupOrder.setBizId(curBizId); // 用当前登陆用户ID表示商家ID
                groupOrder.setState(1);
               // groupOrder.setBizId(curBizId);
                groupOrder.setOrderNo(platformOrgBiz.getCompanyCodeByOrgId(curBizId, curUserOrgId));// 订单号调用接口获取
                groupOrder.setCreatorId(groupOrder.getOperatorId()); // 操作人和操作计调相同
                groupOrder.setCreatorName(groupOrder.getOperatorName());
                groupOrder.setCreateTime(new Date().getTime()); // 创建时间
                groupOrder.setOrderType(1); // 定制团订单
                groupOrder = tourGroupBiz
                        .insertSelective(tourGroup, groupOrder);
                logger.info("后台保存旅行团和旅行团订单信息成功");
            } else {
                tourGroup.setId(groupOrder.getGroupId());
                TourGroup tourGroup2 = tourGroupBiz
                        .selectByPrimaryKey(tourGroup.getId());
                if (tourGroup.getDateStart().compareTo(tourGroup2.getDateStart()) != 0) {
                    tourGroup.setGroupCode(platformOrgBiz.getCompanyCodeByOrgId(curBizId, curUserOrgId));
                    List<GroupRoute> list = groupRouteBiz
                            .selectByGroupId(tourGroup.getId());
                    if (list != null && list.size() > 0) {
                        Date startTime = tourGroup.getDateStart();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(startTime);
                        cal.add(Calendar.DAY_OF_MONTH, +(list.size() - 1));
                        Date time = cal.getTime();
                        tourGroup.setDateEnd(time);
                    }
                    groupOrder.setOrderNo(platformOrgBiz.getCompanyCodeByOrgId(curBizId, curUserOrgId));
                } else {
                    tourGroup
                            .setGroupCode(GroupCodeUtil.getCode(
                                    tourGroup2.getGroupCode(),
                                    tourGroup.getGroupCodeMark()));
                }
                tourGroup.setUpdateId(curUserId);
                tourGroup.setUpdateName(curUserName);
                tourGroup.setUpdateTime(System.currentTimeMillis());
                groupOrder = tourGroupBiz.updateByPrimaryKeySelective(
                        tourGroup, groupOrder);
                toChangeGroupResult.setGroupOrde(groupOrder);
                logger.info("后台更新旅行团和旅行团订单信息成功");
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return toChangeGroupResult;
    }

    @Override
    public ResultSupport unifiedSaveOtherInfo(OtherInfoVO otherInfoVO, String curUserName, Integer CurUserId) {
        ResultSupport resultSupport = new ResultSupport();
        try {

            List<GroupOrderPrice> priceList = otherInfoVO.getGroupOrderPriceList();
            if (priceList != null && priceList.size() > 0) {
                for (GroupOrderPrice groupOrderPrice : priceList) {
                    groupOrderPrice.setCreateTime(System.currentTimeMillis());
                    groupOrderPrice.setRowState(0);
                    groupOrderPrice.setItemName(dicBiz.getById(groupOrderPrice.getItemId()).getValue());
                    groupOrderPrice.setCreatorId(CurUserId);
                    groupOrderPrice.setCreatorName(curUserName);
                    groupOrderPriceBiz.insertSelective(groupOrderPrice);
                }
            }
            List<GroupOrderTransport> tranList = otherInfoVO
                    .getGroupOrderTransportList();
            if (tranList != null && tranList.size() > 0) {
                for (GroupOrderTransport groupOrderTransport : tranList) {
                    groupOrderTransport.setCreateTime(System.currentTimeMillis());
                    groupOrderTransportBiz.insertSelective(groupOrderTransport);
                }
            }

            List<GroupOrderPrice> costList = otherInfoVO.getGroupOrderCostList();
            if (costList != null && costList.size() > 0) {
                for (GroupOrderPrice groupOrderPrice : costList) {
                    groupOrderPrice.setCreateTime(System.currentTimeMillis());
                    groupOrderPrice.setRowState(0);
                    groupOrderPrice.setItemName(dicBiz.getById(groupOrderPrice.getItemId()).getValue());
                    groupOrderPrice.setCreatorId(CurUserId);
                    groupOrderPrice.setCreatorName(curUserName);
                    groupOrderPriceBiz.insertSelective(groupOrderPrice);
                }
            }

            List<GroupOrderGuest> guestList = otherInfoVO.getGroupOrderGuestList();
            if (guestList != null && guestList.size() > 0) {
                for (GroupOrderGuest groupOrderGuest : guestList) {
                    groupOrderGuestBiz.insertSelective(groupOrderGuest);
                }
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return resultSupport;
    }

    @Override
    public ToOtherInfoResult toOtherInfo(Integer orderId, Integer stateFinance, Integer state, Integer curBizId) {
        ToOtherInfoResult toOtherInfoResult = new ToOtherInfoResult();
        try {
            GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(orderId);
            List<DicInfo> jtfsList = dicBiz
                    .getListByTypeCode(BasicConstants.GYXX_JTFS, curBizId);
            toOtherInfoResult.setJtfsList(jtfsList);
            List<DicInfo> zjlxList = dicBiz
                    .getListByTypeCode(BasicConstants.GYXX_ZJLX);
            toOtherInfoResult.setZjlxList(zjlxList);

            List<DicInfo> lysfxmList = dicBiz.getListByTypeCode(
                    BasicConstants.GYXX_LYSFXM, curBizId);
            toOtherInfoResult.setLysfxmList(lysfxmList);
            // 0表示收入
            List<GroupOrderPrice> groupOrderPricesZero = groupOrderPriceBiz
                    .selectByOrderAndType(orderId, 0);
            toOtherInfoResult.setGroupOrderPricesZero(groupOrderPricesZero);
            // 1表示成本
            List<GroupOrderPrice> groupOrderPricesOne = groupOrderPriceBiz
                    .selectByOrderAndType(orderId, 1);
            toOtherInfoResult.setGroupOrderPricesOne(groupOrderPricesOne);
            // 接送信息
            List<GroupOrderTransport> groupOrderTransports = groupOrderTransportBiz
                    .selectByOrderId(orderId);
            toOtherInfoResult.setGroupOrderTransports(groupOrderTransports);
            // 客人列表
            List<GroupOrderGuest> groupOrderGuests = groupOrderGuestBiz
                    .selectByOrderId(orderId);
            // StringBuilder sb = new StringBuilder();
            for (GroupOrderGuest guest : groupOrderGuests) {
                List<GroupOrderGuest> guests = groupOrderGuestBiz
                        .getGuestByGuestCertificateNum(guest.getCertificateNum(),
                                guest.getOrderId());
                guest.setHistoryNum(guests.size());
                // sb.append(guest.getCertificateNum()+",") ;
            }
		/* model.addAttribute("cerNums", sb.toString()); */
       /*     model.addAttribute("groupOrderGuests", groupOrderGuests);
            model.addAttribute("orderId", orderId);
            model.addAttribute("groupOrder", groupOrder);
            model.addAttribute("stateFinance", stateFinance);
            model.addAttribute("groupId", groupOrder.getGroupId());
            model.addAttribute("state", state);*/

            toOtherInfoResult.setGroupOrderGuests(groupOrderGuests);
            toOtherInfoResult.setGroupOrder(groupOrder);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toOtherInfoResult;
    }

    @Override
    public TogroupRequirementResult togroupRequirement(Integer orderId, Integer stateFinance, Integer state) {
        TogroupRequirementResult togroupRequirementResult = new TogroupRequirementResult();
        try {
            GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(orderId);
            // 车辆型号
            List<DicInfo> ftcList = dicBiz
                    .getListByTypeCode(Constants.FLEET_TYPE_CODE);
            togroupRequirementResult.setFtcList(ftcList);
            // 酒店星级
            List<DicInfo> jdxjList = dicBiz
                    .getListByTypeCode(BasicConstants.GYXX_JDXJ);

            togroupRequirementResult.setJdxjList(jdxjList);
            // 酒店信息
            List<GroupRequirement> grogShopList = groupRequirementBiz
                    .selectByOrderAndType(orderId, 3);

            togroupRequirementResult.setGrogShopList(grogShopList);
            // 车队信息
            List<GroupRequirement> motorcadeList = groupRequirementBiz
                    .selectByOrderAndType(orderId, 4);
            togroupRequirementResult.setMotorcadeList(motorcadeList);
            // 机票信息
            List<GroupRequirement> airTicketList = groupRequirementBiz
                    .selectByOrderAndType(orderId, 9);
            togroupRequirementResult.setAirTicketList(airTicketList);
            // 火车信息
            List<GroupRequirement> railwayTicketList = groupRequirementBiz
                    .selectByOrderAndType(orderId, 10);
            togroupRequirementResult.setRailwayTicketList(railwayTicketList);
            // 导游信息
            List<GroupRequirement> guideList = groupRequirementBiz
                    .selectByOrderAndType(orderId, 8);

            togroupRequirementResult.setGuideList(guideList);
            // 餐厅信息
            List<GroupRequirement> restaurantList = groupRequirementBiz
                    .selectByOrderAndType(orderId, 2);
            togroupRequirementResult.setRestaurantList(restaurantList);
            togroupRequirementResult.setGroupId(groupOrder.getGroupId());
        } catch (Exception e) {
            logger.error("", e);
        }
        return togroupRequirementResult;
    }

    @Override
    public ToGroupListResult findTourGroupByCondition(String yesOrNo, GroupOrder groupOrder, Integer curBizId) {
        ToGroupListResult toGroupListResult = new ToGroupListResult();
        try {
            List<RegionInfo> allProvince = regionBiz.getAllProvince();
            toGroupListResult.setAllProvince(allProvince);
            toGroupListResult.setPpList(dicBiz.getListByTypeCode(BasicConstants.CPXL_PP,curBizId));
            toGroupListResult.setSourceTypeList(dicBiz.getListByTypeCode(Constants.GUEST_SOURCE_TYPE,curBizId));
            toGroupListResult.setTypeList(dicBiz.getListByTypeCode(BasicConstants.SALES_TEAM_TYPE,curBizId));
            toGroupListResult.setOrgJsonStr(platformOrgBiz.getComponentOrgTreeJsonStr(curBizId));
            toGroupListResult.setOrgUserJsonStr(platformEmployeeBiz.getComponentOrgUserTreeJsonStr(curBizId));
        } catch (Exception e) {
            logger.error("", e);
        }
        return toGroupListResult;
    }

    @Override
    public ToPreviewResult createSalesConfirm(Integer orderId, Integer agency, Integer curBizId,Integer orgId) {
        ToPreviewResult toPreviewResult = new ToPreviewResult();
        try {
            GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(orderId);
            List<GroupOrderGuest> guests = groupOrderGuestBiz
                    .selectByOrderId(orderId);
            List<GroupOrderPrice> prices = groupOrderPriceBiz
                    .selectByOrder(orderId);
            if(agency!=1){ 
                GroupOrderPrice gop = new GroupOrderPrice();
                gop.setItemName(Constants.PRICETYPE);
                gop.setUnitPrice(Constants.PRICE);
                gop.setNumTimes(Constants.TIMES);
                gop.setNumPerson(new Double(groupOrderBiz.selectTotalNumByOrderId(orderId)))  ;
                gop.setTotalPrice(gop.getUnitPrice()*gop.getNumPerson());
                prices.add(gop) ;
            }
            /* 银行信息 */
            List<SysBizBankAccount> sysBizBankAccountList = sysBizBankAccountBiz.getListByBizId(curBizId);
            toPreviewResult.setSysBizBankAccountList(sysBizBankAccountList);


            List<GroupRoute> routes = groupRouteBiz.selectByOrderId(orderId);
            SupplierInfo supplier = supplierBiz.selectBySupplierId(groupOrder
                    .getSupplierId());

           // String imgPath = bizSettingCommon.getMyBizLogo(request);
//            String imgPath = platformOrgBiz.getLogoByOrgIdURL(curBizId, orgId);

           // PlatformEmployeePo employee = sysPlatformEmployeeFacade
                   // .findByEmployeeId(groupOrder.getSaleOperatorId()).getPlatformEmployeePo();
            PlatformEmployeePo employee =  platformEmployeeBiz.findByEmployeeId(groupOrder.getSaleOperatorId());
            String  company = platformOrgBiz.findByOrgId(employee.getOrgId()).getName(); // 当前单位


            // 统计订单下的全陪
            String guestGuideString = "";
            // 订单所属团下的所有导游
            String guideString = "";
            // 根据散客订单统计客人信息
            List<BookingGuide> guides = null;
            if (null != groupOrder.getGroupId()) {
                guides = bookingGuideBiz.selectGuidesByGroupId(groupOrder
                        .getGroupId());
                StringBuilder sb = new StringBuilder();
                SupplierGuide sg = null;
                for (BookingGuide guide : guides) {
                    sg = supplierGuideBiz.getGuideInfoById(guide.getGuideId());
                    sb.append(guide.getGuideName() + " " + guide.getGuideMobile()
                            + " " + sg.getLicenseNo() + "\n");
                }
                guideString = sb.toString();
            }

            if (guests.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (GroupOrderGuest guest : guests) {
                    if (guest.getType() == 3) {
                        sb.append(guest.getName() + " " + guest.getMobile());
                    }
                }
                guestGuideString = sb.toString();
            }


            // 根据散客订单统计酒店信息
            List<GroupRequirement> grogShopList = groupRequirementBiz
                    .selectByOrderAndType(groupOrder.getId(), 3);
            // 根据散客订单统计接机信息
            List<GroupOrderTransport> groupOrderTransports = groupOrderTransportBiz
                    .selectByOrderId(groupOrder.getId());
            toPreviewResult.setEmployee(employee);
            toPreviewResult.setGroupOrder(groupOrder);
            toPreviewResult.setGuests(guests);
            toPreviewResult.setPriceList(prices);
            toPreviewResult.setRouteList(routes);
            toPreviewResult.setSupplier(supplier);
//            toPreviewResult.setImgPath(imgPath);
            toPreviewResult.setCompany(company);
            toPreviewResult.setGuestGuideString(guestGuideString);
            toPreviewResult.setGuideString(guideString);
            toPreviewResult.setGrogShopList(grogShopList);
            toPreviewResult.setGroupOrderTransports(groupOrderTransports);

        } catch (Exception e) {
            logger.error("", e);
        }
        return toPreviewResult;
    }

    @Override
    public ToPreviewResult createSalesChargeNoRoute(Integer orderId, Integer curBizId,Integer orgId) {
        ToPreviewResult toPreviewResult = new ToPreviewResult();
        try {
            GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(orderId);
            List<GroupOrderGuest> guests = groupOrderGuestBiz
                    .selectByOrderId(orderId);
            List<GroupOrderPrice> prices = groupOrderPriceBiz
                    .selectByOrder(orderId);
            GroupOrderPrice gop = new GroupOrderPrice();
            gop.setItemName(Constants.PRICETYPE);
            gop.setUnitPrice(Constants.PRICE);
            gop.setNumTimes(Constants.TIMES);
            gop.setNumPerson(new Double(groupOrderBiz.selectTotalNumByOrderId(orderId)))  ;
            gop.setTotalPrice(gop.getUnitPrice()*gop.getNumPerson());
            prices.add(gop) ;
            List<GroupRoute> routes = groupRouteBiz.selectByOrderId(orderId);
          //  PlatformEmployeePo employeePo = WebUtils.getCurUser(request);
          //  PlatformEmployeePo employee =  platformEmployeeBiz.findByEmployeeId(groupOrder.getSaleOperatorId());
            SupplierInfo supplier = supplierBiz.selectBySupplierId(groupOrder
                    .getSupplierId());

           // String imgPath = bizSettingCommon.getMyBizLogo(request);
//            String imgPath = platformOrgBiz.getLogoByOrgId(curBizId, orgId);
            List<SysBizBankAccount> sysBizBankAccountList = sysBizBankAccountBiz
                    .getListByBizId(curBizId);
            toPreviewResult.setGroupOrder(groupOrder);
            toPreviewResult.setGuests(guests);
            toPreviewResult.setPriceList(prices);
            toPreviewResult.setGroupOrderPrice(gop);
            toPreviewResult.setRouteList(routes);
            toPreviewResult.setSupplier(supplier);
//            toPreviewResult.setImgPath(imgPath);
            toPreviewResult.setSysBizBankAccountList(sysBizBankAccountList);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toPreviewResult;
    }

    @Override
    public ToPreviewResult createSalesConfirmNoRoute(Integer orderId, Integer curBizId,Integer orgId) {
        ToPreviewResult toPreviewResult = new ToPreviewResult();
        try {
            GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(orderId);
            List<GroupOrderGuest> guests = groupOrderGuestBiz
                    .selectByOrderId(orderId);
            List<GroupOrderPrice> prices = groupOrderPriceBiz
                    .selectByOrder(orderId);
            GroupOrderPrice gop = new GroupOrderPrice();
            gop.setItemName(Constants.PRICETYPE);
            gop.setUnitPrice(Constants.PRICE);
            gop.setNumTimes(Constants.TIMES);
            gop.setNumPerson(new Double(groupOrderBiz.selectTotalNumByOrderId(orderId)))  ;
            gop.setTotalPrice(gop.getUnitPrice()*gop.getNumPerson());
            prices.add(gop) ;
            SupplierInfo supplier = supplierBiz.selectBySupplierId(groupOrder
                    .getSupplierId());

         //   PlatformEmployeePo employee = sysPlatformEmployeeFacade
            //        .findByEmployeeId(groupOrder.getSaleOperatorId()).getPlatformEmployeePo();
            PlatformEmployeePo employee =  platformEmployeeBiz.findByEmployeeId(groupOrder.getSaleOperatorId());
//            String imgPath = platformOrgBiz.getLogoByOrgId(curBizId, orgId);
            String company = platformOrgBiz.findByOrgId(employee.getOrgId()).getName();



            // 统计订单下的全陪
            String guestGuideString = "";
            // 订单所属团下的所有导游
            String guideString = "";
            // 根据散客订单统计客人信息
            List<BookingGuide> guides = null;
            if (null != groupOrder.getGroupId()) {
                guides = bookingGuideBiz.selectGuidesByGroupId(groupOrder
                        .getGroupId());
                StringBuilder sb = new StringBuilder();
                SupplierGuide sg = null;
                for (BookingGuide guide : guides) {
                    sg = supplierGuideBiz.getGuideInfoById(guide.getGuideId());
                    sb.append(guide.getGuideName() + " " + guide.getGuideMobile()
                            + " " + sg.getLicenseNo() + "\n");
                }
                guideString = sb.toString();
            }

            if (guests.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (GroupOrderGuest guest : guests) {
                    if (guest.getType() == 3) {
                        sb.append(guest.getName() + " " + guest.getMobile());
                    }
                }
                guestGuideString = sb.toString();
            }

            toPreviewResult.setGuideString(guideString);
            toPreviewResult.setGuestGuideString(guestGuideString);

            /* 银行信息 */
            List<SysBizBankAccount> sysBizBankAccountList = sysBizBankAccountBiz.getListByBizId(curBizId);
            toPreviewResult.setSysBizBankAccountList(sysBizBankAccountList);

            // 根据散客订单统计酒店信息
            List<GroupRequirement> grogShopList = groupRequirementBiz
                    .selectByOrderAndType(groupOrder.getId(), 3);
            // 根据散客订单统计接机信息
            List<GroupOrderTransport> groupOrderTransports = groupOrderTransportBiz
                    .selectByOrderId(groupOrder.getId());

            toPreviewResult.setGrogShopList(grogShopList);
            toPreviewResult.setGroupOrderTransports(groupOrderTransports);
//            toPreviewResult.setImgPath(imgPath);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toPreviewResult;
    }

    @Override
    public ToPreviewResult createSalesCharge(Integer orderId, Integer curBizId,Integer orgId) {
        ToPreviewResult toPreviewResult = new ToPreviewResult();
        try {
            GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(orderId);
            List<GroupOrderGuest> guests = groupOrderGuestBiz.selectByOrderId(orderId);
            List<GroupOrderPrice> prices = groupOrderPriceBiz.selectByOrder(orderId);
            GroupOrderPrice gop = new GroupOrderPrice();
            gop.setItemName(Constants.PRICETYPE);
            gop.setUnitPrice(Constants.PRICE);
            gop.setNumTimes(Constants.TIMES);
            gop.setNumPerson(new Double(groupOrderBiz.selectTotalNumByOrderId(orderId)))  ;
            gop.setTotalPrice(gop.getUnitPrice()*gop.getNumPerson());
            prices.add(gop) ;
            List<GroupRoute> routes = groupRouteBiz.selectByOrderId(orderId);
            SupplierInfo supplier = supplierBiz.selectBySupplierId(groupOrder .getSupplierId());
//            String imgPath = platformOrgBiz.getLogoByOrgId(curBizId, orgId);
            List<SysBizBankAccount> sysBizBankAccountList = sysBizBankAccountBiz
                    .getListByBizId(curBizId);
            toPreviewResult.setGroupOrder(groupOrder);
            toPreviewResult.setGuests(guests);
            toPreviewResult.setPriceList(prices);
            toPreviewResult.setRouteList(routes);
            toPreviewResult.setSupplier(supplier);
//            toPreviewResult.setImgPath(imgPath);
            toPreviewResult.setSysBizBankAccountList(sysBizBankAccountList);

        } catch (Exception e) {
            logger.error("", e);
        }
        return toPreviewResult;
    }

    @Override
    public ToPreviewResult createGuestNames(Integer orderId, Integer curBizId,Integer orgId) {
        ToPreviewResult toPreviewResult = new ToPreviewResult();
        try {
            GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(orderId);
            List<GroupOrderGuest> guests = groupOrderGuestBiz.selectByOrderId(orderId);
//            String imgPath = platformOrgBiz.getLogoByOrgId(curBizId, orgId);
            toPreviewResult.setGroupOrder(groupOrder);
            toPreviewResult.setGuests(guests);
//            toPreviewResult.setImgPath(imgPath);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toPreviewResult;

    }

    @Override
    public ToPreviewResult saleTravelContract(Integer orderId, Integer curBizId) {
        ToPreviewResult toPreviewResult = new ToPreviewResult();
        try {
            GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(orderId);
            List<GroupOrderGuest> guests = groupOrderGuestBiz .selectByOrderId(orderId);
            GroupRoute groupRoute=groupRouteBiz.selectDayNumAndMaxday(orderId,null);
            GroupOrderGuest genderSum=groupOrderGuestBiz.selectGenderSum(orderId);
            toPreviewResult.setGroupOrder(groupOrder);
            toPreviewResult.setGuests(guests);
            toPreviewResult.setGroupRoute(groupRoute);
            toPreviewResult.setGroupOrderGuest(genderSum);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toPreviewResult;
    }

    @Override
    public ToPreviewResult saleInsurance(Integer orderId, Integer curBizId) {
        ToPreviewResult toPreviewResult = new ToPreviewResult();
        try {
            GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(orderId);
            List<GroupOrderGuest> guests = groupOrderGuestBiz
                    .selectByOrderId(orderId);
            GroupRoute groupRoute=groupRouteBiz.selectDayNumAndMaxday(null,groupOrder.getGroupId());
            toPreviewResult.setGroupOrder(groupOrder);
            toPreviewResult.setGuests(guests);
            toPreviewResult.setGroupRoute(groupRoute);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toPreviewResult;
    }

    @Override
    public ToPreviewResult toProfitQueryTableZT(GroupOrder order, Set<Integer> orgIdSet, String supplierType, Integer curBizId) {
        ToPreviewResult toPreviewResult = new ToPreviewResult();
        try {
            PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();
            pageBean.setPage(order.getPage());
            pageBean.setPageSize(order.getPageSize() == null ? Constants.PAGESIZE
                    : order.getPageSize());
            pageBean.setParameter(order);
            // 如果人员为空并且部门不为空，则取部门下的人id
            if (StringUtils.isBlank(order.getSaleOperatorIds())
                    && StringUtils.isNotBlank(order.getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = order.getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                //set = sysPlatformEmployeeFacade.getUserIdListByOrgIdList(
                 //       WebUtils.getCurBizId(request), set);
                set = platformEmployeeBiz.getUserIdListByOrgIdList(curBizId, orgIdSet);
                String salesOperatorIds = "";
                for (Integer usrId : set) {
                    salesOperatorIds += usrId + ",";
                }
                if (!salesOperatorIds.equals("")) {
                    order.setSaleOperatorIds(salesOperatorIds.substring(0,
                            salesOperatorIds.length() - 1));
                }
            }
            pageBean = groupOrderBiz.selectProfitByConListPage(pageBean,curBizId,orgIdSet);
            GroupOrder staticInfo = groupOrderBiz.selectProfitByCon(pageBean,curBizId,orgIdSet);
            GroupOrder groupOrder = groupOrderBiz.selectProfitByConAndMode(pageBean,curBizId,orgIdSet);
            toPreviewResult.setGroupOrder(groupOrder);
            toPreviewResult.setStaticInfo(staticInfo);
            toPreviewResult.setPageBean(pageBean);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toPreviewResult;
    }

    @Override
    public ToPreviewResult getSupplier(String prefixText, String supplierType, Integer curBizId) {
        ToPreviewResult toPreviewResult = new ToPreviewResult();
        try {
            toPreviewResult.setSupplierInfos(supplierBiz.findSupplierListByTypeAndName(supplierType, prefixText,
                    curBizId));
        } catch (Exception e) {
            logger.error("", e);
        }
        return toPreviewResult;
    }

    @Override
    public ToPreviewResult getPriceSupplierName(String keyword, Integer productId, Integer curBizId) {
        ToPreviewResult toPreviewResult = new ToPreviewResult();
        try {
            ProductSupplierCondition psc = new ProductSupplierCondition();
            psc.setPageSize(10);
            psc.setSupplierName(keyword);
            psc.setProductId(productId);
            List<ProductGroupSupplier> supplierList = productGroupSupplierBiz
                    .selectSupplierList(psc);
            toPreviewResult.setSupplierList(supplierList);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toPreviewResult;
    }

    @Override
    public ToPreviewResult getSupplierNameForAgency(String keyword, Integer groupId, Integer priceId, String supplierType,Integer curBizId) {
        ToPreviewResult toPreviewResult = new ToPreviewResult();
        try {
            List<ProductGroupSupplierVo> groupSuppliersList = null;
            ProductGroup group = productGroupBiz.getGroupInfoById(groupId);
            if (group.getGroupSetting() == 0) {
                groupSuppliersList = productGroupSupplierBiz.selectProductGroupSupplierVos(groupId, priceId);
                toPreviewResult.getJson().put("result", groupSuppliersList);
                toPreviewResult.getJson().put("type", 0);
            }else{
                List<SupplierInfo> supplierInfos = supplierBiz
                        .findSupplierListByTypeAndName(supplierType,keyword,curBizId);
                toPreviewResult.getJson().put("result", supplierInfos);
                toPreviewResult.getJson().put("type", 1);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return toPreviewResult;
    }

    @Override
    public ToPreviewResult getSupplierName(String keyword, String supplierType, Integer curBizId) {
        ToPreviewResult toPreviewResult = new ToPreviewResult();
        try {
            toPreviewResult.setSupplierInfos(supplierBiz
                    .findSupplierListByTypeAndName(supplierType, keyword, curBizId));
        } catch (Exception e) {
            logger.error("", e);
        }
        return toPreviewResult;
    }

    @Override
    public ToPreviewResult getSupplierAndContact(String prefixText, Integer supplierId, Integer curBizId) {
        ToPreviewResult toPreviewResult = new ToPreviewResult();
        try {
            toPreviewResult.setSupplierContactManList(supplierBiz.selectPrivateManBySupplierIdAndName(curBizId, supplierId, prefixText));
        } catch (Exception e) {
            logger.error("", e);
        }
        return toPreviewResult;
    }

    @Override
    public ToPreviewResult getContactName(String keyword, Integer supplierId,Integer curBizId) {
        ToPreviewResult toPreviewResult = new ToPreviewResult();
        try {
            toPreviewResult.setSupplierContactManList(supplierBiz.selectPrivateManBySupplierIdAndName(curBizId, supplierId, keyword));
        } catch (Exception e) {
            logger.error("", e);
        }
        return toPreviewResult;
    }

    @Override
    public ToPreviewResult validatorSupplier(String supplierName, Integer supplierId) {
        ToPreviewResult toPreviewResult = new ToPreviewResult();
        try {
            toPreviewResult.setSupplier(supplierBiz.selectBySupplierId(supplierId));
        } catch (Exception e) {
            logger.error("", e);
        }
        return toPreviewResult;
    }

    @Override
    public ToPreviewResult getSelSupplier(Integer groupId, Integer priceId, String prefixText, String supplierType) {
        ToPreviewResult toPreviewResult = new ToPreviewResult();
        try {
            List<ProductGroupSupplierVo> groupSuppliersList = productGroupSupplierBiz
                    .selectProductGroupSupplierVos(groupId, priceId);
            List<AutocompleteInfo> infoList = new ArrayList<AutocompleteInfo>();
            AutocompleteInfo info = null;
            for (ProductGroupSupplierVo vo : groupSuppliersList) {
                info = new AutocompleteInfo();
                info.setId(vo.getGroupId() + "");
                info.setText(vo.getSupplierName());
                infoList.add(info);
            }
            toPreviewResult.setInfoList(infoList);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toPreviewResult;
    }

    @Override
    public ToPreviewResult toPreview(Integer orderId, Integer num, Integer agency, Integer curBizId,Integer orgId) {
        ToPreviewResult toPreviewResult = new ToPreviewResult();
        try {
            if(null==agency){
                agency = 0 ;
            }
          //  String imgPath = bizSettingCommon.getMyBizLogo(request);
//            String imgPath = platformOrgBiz.getLogoByOrgId(curBizId, orgId);
            GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(orderId);
            SupplierInfo supplier = supplierBiz.selectBySupplierId(groupOrder
                    .getSupplierId());

         //   PlatformEmployeePo employee = sysPlatformEmployeeFacade
             //       .findByEmployeeId(groupOrder.getSaleOperatorId()).getPlatformEmployeePo();
            PlatformEmployeePo employee =  platformEmployeeBiz.findByEmployeeId(groupOrder.getSaleOperatorId());

            List<GroupOrderGuest> guests = groupOrderGuestBiz
                    .selectByOrderId(orderId);
            List<GroupOrderPrice> priceList = groupOrderPriceBiz
                    .selectByOrder(orderId);
            if(agency!=1){
                GroupOrderPrice gop = new GroupOrderPrice();
                gop.setItemName(Constants.PRICETYPE);
                gop.setUnitPrice(Constants.PRICE);
                gop.setNumTimes(Constants.TIMES);
                gop.setNumPerson(new Double(groupOrderBiz.selectTotalNumByOrderId(orderId)))  ;
                gop.setTotalPrice(gop.getUnitPrice()*gop.getNumPerson());
                priceList.add(gop) ;
            }

            List<GroupRoute> routeList = groupRouteBiz.selectByOrderId(orderId);

            // 客人接送信息
            GroupOrderPrintPo gopp = new GroupOrderPrintPo();
            gopp.setSupplierName(groupOrder.getSupplierName());
            gopp.setSaleOperatorName(groupOrder.getSaleOperatorName());
            gopp.setRemark(groupOrder.getRemark());
            gopp.setPlace((groupOrder.getProvinceName() == null ? "" : groupOrder
                    .getProvinceName())
                    + (groupOrder.getCityName() == null ? "" : groupOrder
                    .getCityName()));
            // 根据散客订单统计人数
            Integer numAdult = groupOrderGuestBiz
                    .selectNumAdultByOrderID(groupOrder.getId());
            Integer numChild = groupOrderGuestBiz
                    .selectNumChildByOrderID(groupOrder.getId());
            Integer numGuide = groupOrderGuestBiz
                    .selectNumGuideByOrderID(groupOrder.getId());
            gopp.setPersonNum(numAdult + "+" + numChild + "+" + numGuide);
            // 统计订单下的全陪
            String guestGuideString = "";
            // 订单所属团下的所有导游
            String guideString = "";
            // 根据散客订单统计客人信息
            List<BookingGuide> guides = null;
            if (null != groupOrder.getGroupId()) {
                guides = bookingGuideBiz.selectGuidesByGroupId(groupOrder
                        .getGroupId());
                StringBuilder sb = new StringBuilder();
                SupplierGuide sg = null;
                for (BookingGuide guide : guides) {
                    sg = supplierGuideBiz.getGuideInfoById(guide.getGuideId());
                    sb.append(guide.getGuideName() + " " + guide.getGuideMobile()
                            + " " + sg.getLicenseNo() + "\n");
                }
                guideString = sb.toString();
            }

            if (guests.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (GroupOrderGuest guest : guests) {
                    if (guest.getType() == 3) {
                        sb.append(guest.getName() + " " + guest.getMobile());
                    }
                }
                guestGuideString = sb.toString();
                for (GroupOrderGuest guest : guests) {
                    if (guest.getIsLeader() == 1) {
                        gopp.setGuesStatic(guest.getName() + "\n"
                                + guest.getMobile());
                        break;
                    }
                }
                if (gopp.getGuestInfo() == null || gopp.getGuestInfo() == "") {
                    // 如果客人中没有领队，默认取一条数据显示
                    gopp.setGuesStatic(guests.get(0).getName() + "\n"
                            + guests.get(0).getMobile());
                }
                // gopp.setGuestInfo(getGuestInfoNoPhone(guests));
            }

            // 根据散客订单统计酒店信息
            List<GroupRequirement> grogShopList = groupRequirementBiz
                    .selectByOrderAndType(groupOrder.getId(), 3);
            if (grogShopList.size() > 0) {
                if (null != grogShopList.get(0) && grogShopList.get(0).getHotelLevel() != null && !StringUtils.isBlank(grogShopList.get(0).getHotelLevel()) ) {
                    DicInfo info = dicBiz.getById(Integer.parseInt(grogShopList.get(0).getHotelLevel()));
                    if(info!=null){
                        gopp.setHotelLevel(info.getValue()+ "\n");
                    }
                }
                gopp.setHotelNum(getHotelNum(grogShopList));
            }
            // 省外交通
            // 根据散客订单统计接机信息
            List<GroupOrderTransport> groupOrderTransports = groupOrderTransportBiz
                    .selectByOrderId(groupOrder.getId());
            gopp.setAirPickup(getAirInfo(groupOrderTransports, 0));
            // 根据散客订单统计送机信息
            gopp.setAirOff(getAirInfo(groupOrderTransports, 1));
            // 省内交通
            gopp.setTrans(getSourceType(groupOrderTransports));
            String  company = platformOrgBiz.findByOrgId(employee.getOrgId()).getName(); // 当前单位

            toPreviewResult.setGuideString(guideString);
            toPreviewResult.setGuestGuideString(guestGuideString);
//            toPreviewResult.setImgPath(imgPath);
            toPreviewResult.setGroupOrder(groupOrder);
            toPreviewResult.setSupplier(supplier);
            toPreviewResult.setCompany(company);
            toPreviewResult.setEmployee(employee);
            toPreviewResult.setRouteList(routeList);
            toPreviewResult.setPriceList(priceList);
            toPreviewResult.setGopp(gopp);
            toPreviewResult.setGuests(guests);
            toPreviewResult.setGrogShopList(grogShopList);
            toPreviewResult.setGroupOrderTransports(groupOrderTransports);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toPreviewResult;
    }

    @Override
    public ToSaleChargeResult toSaleCharge(Integer orderId, Integer num, Integer curUserId, Integer curBizId,Integer orgId) {
        ToSaleChargeResult toSaleChargeResult = new ToSaleChargeResult();
        try {
          //  String imgPath = bizSettingCommon.getMyBizLogo(request);
//            String imgPath = platformOrgBiz.getLogoByOrgId(curBizId, orgId);
            GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(orderId);
            SupplierInfo supplier = supplierBiz.selectBySupplierId(groupOrder.getSupplierId());

          //  PlatformEmployeePo employee = sysPlatformEmployeeFacade
           //         .findByEmployeeId(groupOrder.getSaleOperatorId()).getPlatformEmployeePo();
            PlatformEmployeePo employee =  platformEmployeeBiz.findByEmployeeId(groupOrder.getSaleOperatorId());

            List<GroupOrderGuest> guests = groupOrderGuestBiz.selectByOrderId(orderId);
            List<GroupOrderPrice> priceList = groupOrderPriceBiz.selectByOrder(orderId);
            GroupOrderPrice gop = new GroupOrderPrice();
            gop.setItemName(Constants.PRICETYPE);
            gop.setUnitPrice(Constants.PRICE);
            gop.setNumTimes(Constants.TIMES);
            gop.setNumPerson(new Double(groupOrderBiz.selectTotalNumByOrderId(orderId)))  ;
            gop.setTotalPrice(gop.getUnitPrice()*gop.getNumPerson());
            priceList.add(gop) ;
            List<GroupRoute> routeList = groupRouteBiz.selectByOrderId(orderId);
            List<SysBizBankAccount> accountList = sysBizBankAccountBiz.getListByBizId(curBizId);
            if (!CollectionUtils.isEmpty(priceList)) {
                toSaleChargeResult.setOtherPrice(String.valueOf(priceList.get(priceList.size() - 1).getTotalPrice()));
            } else {
                toSaleChargeResult.setOtherPrice("0");
            }

            toSaleChargeResult.setGroupOrder(groupOrder);
            toSaleChargeResult.setGuests(guests);
//            toSaleChargeResult.setImgPath(imgPath);
            toSaleChargeResult.setSupplier(supplier);
            toSaleChargeResult.setCompany(platformOrgBiz.findByOrgId(employee.getOrgId()).getName());
            toSaleChargeResult.setEmployee(employee);
            toSaleChargeResult.setRouteList(routeList);
            toSaleChargeResult.setPriceList(priceList);
            toSaleChargeResult.setAccountList(accountList);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toSaleChargeResult;
    }

    @Override
    public ToSKChargePreviewResult toSKChargePreview(Integer groupId,Integer curUserId ,Integer curBizId,Integer supplierId,Integer orgId) {
        ToSKChargePreviewResult toSKChargePreviewResult = new ToSKChargePreviewResult();
        try {
           // String imgPath = bizSettingCommon.getMyBizLogo(request);
//            String imgPath = platformOrgBiz.getLogoByOrgId(curBizId, orgId);
            TourGroup tour = tourGroupBiz.selectByPrimaryKey(groupId);
         //   PlatformEmployeePo po = sysPlatformEmployeeFacade
            //        .findByEmployeeId(WebUtils.getCurUserId(request)).getPlatformEmployeePo();
            PlatformEmployeePo po =  platformEmployeeBiz.findByEmployeeId(curUserId);
            po.setOrgName(platformOrgBiz.findByOrgId(po.getOrgId()).getName());
            List<GroupOrder> suppliers = groupOrderBiz
                    .selectSupplierByGroupId(groupId);
            SupplierInfo supplierInfo = null;
            List<SupplierInfo> supplierList = new ArrayList<SupplierInfo>();

            for (GroupOrder order : suppliers) {
                supplierInfo = supplierBiz.selectBySupplierId(order
                        .getSupplierId());
                supplierList.add(supplierInfo);
            }
            // 行程
            GroupOrder supplier = null;
            if (null == supplierId && supplierList.size() > 0) {
                supplierId = supplierList.get(0).getId();
            }
            GroupRouteVO vo = groupRouteBiz
                    .findGroupRouteByGroupIdAndSupplierId(groupId, supplierId);

            // List<GroupRouteDayVO> groupRouteDayVOs = vo.getGroupRouteDayVOList();

            List<GroupPriceVo> vos = groupOrderBiz
                    .selectSupplierByGroupIdAndSupplierId(groupId, supplierId);

            List<GroupOrder> orders = groupOrderBiz
                    .selectOrderByGroupIdAndBizIdAndSupplierId(groupId, supplierId,curBizId);
            for (GroupOrder groupOrder : orders) {
                if (groupOrder.getSupplierId() == supplierId) {
                    supplier = groupOrder;
                }
            }
            GroupOrderPrintPo gopp = null;
            List<GroupOrderPrintPo> gopps = new ArrayList<GroupOrderPrintPo>();
            for (GroupOrder order : orders) {
                List<GroupOrderGuest> guests = groupOrderGuestBiz
                        .selectByOrderId(order.getId());
                gopp = new GroupOrderPrintPo();
                // 客人接送信息
                gopp.setSupplierName(order.getSupplierName());
                gopp.setSaleOperatorName(order.getSaleOperatorName());
                gopp.setRemark(order.getRemarkInternal());
                gopp.setPlace((order.getProvinceName() == null ? "" : order
                        .getProvinceName())
                        + (order.getCityName() == null ? "" : order.getCityName()));
                gopp.setPersonNum(order.getNumAdult()+"+"+order.getNumChild());
                // 根据散客订单统计客人信息
                if (guests.size() > 0) {
                    gopp.setCertificateNums(getGuestInfoNoPhone(guests));
                }
                gopp.setReceiveMode(order.getReceiveMode());
                // 根据散客订单统计酒店信息
                List<GroupRequirement> grogShopList = groupRequirementBiz
                        .selectByOrderAndType(order.getId(), 3);
                if (grogShopList.size() > 0) {
                    if (grogShopList.get(0).getHotelLevel() != null) {
                        gopp.setHotelLevel(dicBiz.getById(
                                Integer.parseInt(grogShopList.get(0).getHotelLevel())).getValue()
                                + "\n");
                    }
                    gopp.setHotelNum(getHotelNum(grogShopList));
                }
                // 省外交通
                // 根据散客订单统计接机信息
                List<GroupOrderTransport> groupOrderTransports = groupOrderTransportBiz
                        .selectByOrderId(order.getId());
                gopp.setAirPickup("接机：" + getAirInfo(groupOrderTransports, 0));
                // 根据散客订单统计送机信息
                gopp.setAirOff("送机：" + getAirInfo(groupOrderTransports, 1));

                // 省内交通
                gopp.setTrans("省内：" + getSourceType(groupOrderTransports));
                gopps.add(gopp);
            }
            List<BookingGuide> guides = bookingGuideBiz
                    .selectGuidesByGroupId(groupId);

            toSKChargePreviewResult.setSupplier(supplier);
            toSKChargePreviewResult.setGuides(guides);
            toSKChargePreviewResult.setGopps(gopps);
            toSKChargePreviewResult.setVos(vos);
            toSKChargePreviewResult.setSupplierList(supplierList);
            toSKChargePreviewResult.setPo(po);
//            toSKChargePreviewResult.setImgPath(imgPath);
            toSKChargePreviewResult.setTour(tour);

        } catch (Exception e) {
            logger.error("", e);
        }
        return toSKChargePreviewResult;
    }


    /**
     * 省内交通
     *
     * @param groupOrderTransports 0表示接信息 1表示送信息
     * @return
     */
    public String getSourceType(List<GroupOrderTransport> groupOrderTransports) {
        StringBuilder sb = new StringBuilder();
        for (GroupOrderTransport transport : groupOrderTransports) {
            if (transport.getSourceType() == 0) {
                sb.append((transport.getDepartureCity() == null ? ""
                        : transport.getDepartureCity())
                        + "/"
                        + (transport.getArrivalCity() == null ? "" : transport
                        .getArrivalCity())
                        + " "
                        + (transport.getClassNo() == null ? "" : transport
                        .getClassNo())
                        + " "
                        + " 发出时间："
                        + (DateUtils.format(transport.getDepartureDate(),
                        "MM-dd") == null ? "" : DateUtils.format(
                        transport.getDepartureDate(), "MM-dd"))
                        + " "
                        + (transport.getDepartureTime() == null ? ""
                        : transport.getDepartureTime()) + "\n");
            }
        }
        return sb.toString();
    }

    /**
     * 接送信息
     *
     * @param groupOrderTransports
     * @param flag
     *            0表示接信息 1表示送信息
     * @return
     */
    public String getAirInfo(List<GroupOrderTransport> groupOrderTransports,
                             Integer flag) {
        StringBuilder sb = new StringBuilder();
        if (flag == 0) {
            for (GroupOrderTransport transport : groupOrderTransports) {
                if (transport.getType() == 0 && transport.getSourceType() == 1) {
                    sb.append((transport.getDepartureCity() == null ? ""
                            : transport.getDepartureCity())
                            + "/"
                            + (transport.getArrivalCity() == null ? ""
                            : transport.getArrivalCity())
                            + " "
                            + (transport.getClassNo() == null ? "" : transport
                            .getClassNo())
                            + " "
                            + " 发出时间："
                            + (DateUtils.format(transport.getDepartureDate(),
                            "MM-dd") == null ? "" : DateUtils.format(
                            transport.getDepartureDate(), "MM-dd"))
                            + " "
                            + (transport.getDepartureTime() == null ? ""
                            : transport.getDepartureTime()) + "\n");
                }
            }
        }
        if (flag == 1) {
            for (GroupOrderTransport transport : groupOrderTransports) {
                if (transport.getType() == 1 && transport.getSourceType() == 1) {
                    sb.append((transport.getDepartureCity() == null ? ""
                            : transport.getDepartureCity())
                            + "/"
                            + (transport.getArrivalCity() == null ? ""
                            : transport.getArrivalCity())
                            + " "
                            + (transport.getClassNo() == null ? "" : transport
                            .getClassNo())
                            + " "
                            + " 发出时间："
                            + (DateUtils.format(transport.getDepartureDate(),
                            "MM-dd") == null ? "" : DateUtils.format(
                            transport.getDepartureDate(), "MM-dd"))
                            + " "
                            + (transport.getDepartureTime() == null ? ""
                            : transport.getDepartureTime()) + "\n");
                }
            }
        }
        return sb.toString();
    }



    /**
     * 返回客人信息(不包含电话号码)
     *
     * @param guests
     * @return
     */
    public String getGuestInfoNoPhone(List<GroupOrderGuest> guests) {
        StringBuilder sb = new StringBuilder();
        for (GroupOrderGuest guest : guests) {
            sb.append(guest.getName() + " " + guest.getCertificateNum() + "\n");
        }
        return sb.toString();
    }

    /**
     * 返回酒店信息（不包含星级）
     *
     * @param grogShopList
     * @return
     */
    public String getHotelNum(List<GroupRequirement> grogShopList) {
        StringBuilder sb = new StringBuilder();
        if (grogShopList.size() > 0) {
            String sr = "";
            String dr = "";
            String tr = "";
            String eb = "";
            String pf = "";
            GroupRequirement gr = grogShopList.get(0);
            if (gr.getCountSingleRoom() != null && gr.getCountSingleRoom() != 0) {
                sr = gr.getCountSingleRoom() + "单间" + " ";
            }
            if (gr.getCountDoubleRoom() != null && gr.getCountDoubleRoom() != 0) {
                dr = gr.getCountDoubleRoom() + "标间" + " ";
            }
            if (gr.getCountTripleRoom() != null && gr.getCountTripleRoom() != 0) {
                tr = gr.getCountTripleRoom() + "三人间" + "";
            }
            if (gr.getExtraBed() != null && gr.getExtraBed() != 0) {
                eb = gr.getExtraBed() + "加床" + "";
            }
            if (gr.getPeiFang() != null && gr.getPeiFang() != 0) {
                pf = gr.getPeiFang() + "陪房";
            }
            sb.append(sr + dr + tr + eb + pf);
        }
        return sb.toString();
    }


    @Override
    public BookingProfitTableResult getSupplierInfo(Integer supplierId) {
        BookingProfitTableResult bookingProfitTableResult = new BookingProfitTableResult();
        try {
            bookingProfitTableResult.setSupplierInfo(supplierBiz.selectBySupplierId(supplierId));
        } catch (Exception e) {
            logger.error("", e);
        }
        return bookingProfitTableResult;
    }

    @Override
    public BookingProfitTableResult getBankInfo(Integer curBizId) {
        BookingProfitTableResult bookingProfitTableResult = new BookingProfitTableResult();
        try {
            bookingProfitTableResult.setSysBizBankAccountList(sysBizBankAccountBiz.getListByBizId(curBizId));
        } catch (Exception e) {
            logger.error("", e);
        }
        return bookingProfitTableResult;
    }

    @Override
    public BookingProfitTableResult bookingProfitTable(TourGroup tourGroup, Integer curBizId, Set<Integer> orgIdSet) {
        BookingProfitTableResult bookingProfitTableResult = new BookingProfitTableResult();
        try {
            PageBean pageBean=new PageBean();
            if (tourGroup.getPage()==null) {
                pageBean.setPage(1);
            }
            else {

                pageBean.setPage(tourGroup.getPage());
            }
            if (tourGroup.getPageSize()==null) {
                pageBean.setPageSize(Constants.PAGESIZE);
            }
            else {
                pageBean.setPageSize(tourGroup.getPageSize());
            }
            if (StringUtils.isBlank(tourGroup.getSaleOperatorIds())
                    && StringUtils.isNotBlank(tourGroup.getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = tourGroup.getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(curBizId, set);
                String operatorIds = "";
                for (Integer usrId : set) {
                    operatorIds += usrId + ",";
                }
                if (!operatorIds.equals("")) {
                    tourGroup.setSaleOperatorIds(operatorIds.substring(0,
                            operatorIds.length() - 1));
                }
            }
            pageBean.setParameter(tourGroup);
         //   model.addAttribute("sum", tourGroupBiz.selectBookingProfitTotal(pageBean, curBizId, WebUtils.getDataUserIdSet(request)));
            bookingProfitTableResult.setSum(tourGroupBiz.selectBookingProfitTotal(pageBean, curBizId,orgIdSet));
            pageBean= tourGroupBiz.selectBookingProfitList(pageBean, curBizId, orgIdSet);
            List result = pageBean.getResult();
            if (result !=null && result.size()>0) {
                for (Object obj : result) {
                    TourGroup	tGroup=(TourGroup)obj;
                    //List<GroupOrder> groupOrderList = tGroup.getGroupOrderList();
                    //if (groupOrderList !=null && groupOrderList.size()>0) {
                    if (tGroup.getGroupMode()<1) {
                        tGroup.setSupplierName("散客团");
                    }
//				else {
//					tGroup.setSupplierName(groupOrderList.get(0).getSupplierName()==null?"":groupOrderList.get(0).getSupplierName());
//				}
//				 for (GroupOrder gOrder : groupOrderList) {
//					List<GroupOrderPrice> orderPrices = gOrder.getOrderPrices();
//					if (orderPrices !=null && orderPrices.size()>0) {
//						tGroup.setTotalBudget(orderPrices.get(0).getTotalPrice()==null?new BigDecimal(0):new BigDecimal(orderPrices.get(0).getTotalPrice()));
//					}
//				}
                    //}
                }
            }
            //model.addAttribute("page", pageBean);
            bookingProfitTableResult.setPageBean(pageBean);
        } catch (Exception e) {
            logger.error("", e);
        }
        return bookingProfitTableResult;
    }

    @Override
    public BookingProfitTableResult toProfitQueryList(Integer bizId) {
        BookingProfitTableResult bookingProfitTableResult = new BookingProfitTableResult();
        try {
            List<RegionInfo> allProvince = regionBiz.getAllProvince();
            bookingProfitTableResult.setRegionInfoList(allProvince);
            bookingProfitTableResult.setOrgJsonStr(platformOrgBiz.getComponentOrgTreeJsonStr(bizId));
            bookingProfitTableResult.setOrgUserJsonStr(platformEmployeeBiz.getComponentOrgUserTreeJsonStr(bizId));
        } catch (Exception e) {
            logger.error("", e);
        }
        return bookingProfitTableResult;
    }
    @Override
    public BookingProfitTableResult toSaleProfitListByTour(Integer bizId){
        BookingProfitTableResult bookingProfitTableResult = new BookingProfitTableResult();
        try {
            bookingProfitTableResult.setOrgJsonStr(platformOrgBiz.getComponentOrgTreeJsonStr(bizId));
            bookingProfitTableResult.setOrgUserJsonStr(platformEmployeeBiz.getComponentOrgUserTreeJsonStr(bizId));
        } catch (Exception e) {
            logger.error("", e);
        }
        return bookingProfitTableResult;
    }
    public ToOrderLockListResult toOrderLockList(Integer bizId){
    	ToOrderLockListResult result=new ToOrderLockListResult();

    	result.setAllProvince(regionBiz.getAllProvince());
		result.setOrgJsonStr(platformOrgBiz.getComponentOrgTreeJsonStr(bizId));
		result.setOrgUserJsonStr(platformEmployeeBiz.getComponentOrgUserTreeJsonStr(bizId));
		
		return result;
    }
    
	public ToProfitQueryTableResult toProfitQueryTable(ToOrderLockTableDTO orderLockTableDTO){
		Integer bizId = orderLockTableDTO.getBizId();
		Set<Integer> userIdSet = orderLockTableDTO.getUserIdSet();
		GroupOrder order = orderLockTableDTO.getOrder();

		// FIXME 下面的这些代码可以重用
		PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();
		pageBean.setPage(order.getPage());
		pageBean.setPageSize(order.getPageSize() == null ? Constants.PAGESIZE : order.getPageSize());
		pageBean.setParameter(order);

		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(order.getSaleOperatorIds()) && StringUtils.isNotBlank(order.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = order.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				order.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}
		pageBean = groupOrderBiz.selectProfitByConListPage(pageBean, bizId, userIdSet);
		GroupOrder staticInfo = groupOrderBiz.selectProfitByCon(pageBean, bizId, userIdSet);
		GroupOrder groupOrder = groupOrderBiz.selectProfitByConAndMode(pageBean, bizId, userIdSet);

		ToProfitQueryTableResult result = new ToProfitQueryTableResult();
		result.setGroupOrder(groupOrder);
		result.setPageBean(pageBean);
		result.setStaticInfo(staticInfo);

		return result;
	}
    public ProfitQueryByTourResult toSaleProfitTableByTour(ProfitQueryByTourDTO profitQueryByTourDTO){

        PageBean<TourGroup> pageBean = new PageBean<TourGroup>();
        pageBean.setPage(profitQueryByTourDTO.getPage());
        Integer pageSize = profitQueryByTourDTO.getPageSize();
        if (pageSize == null) {
            pageSize = com.yimayhd.erpcenter.dal.product.constans.Constants.PAGESIZE;
        }
        pageBean.setPageSize(pageSize);
        TourGroup tour = profitQueryByTourDTO.getTour();
        pageBean.setParameter(tour);
        // 如果人员为空并且部门不为空，则取部门下的人id
        if (StringUtils.isBlank(tour.getSaleOperatorIds())
                && StringUtils.isNotBlank(tour.getOrgIds())) {
            Set<Integer> set = new HashSet<Integer>();
            String[] orgIdArr = tour.getOrgIds().split(",");
            for (String orgIdStr : orgIdArr) {
                set.add(Integer.valueOf(orgIdStr));
            }
            set = platformEmployeeBiz.getUserIdListByOrgIdList(profitQueryByTourDTO.getBizId(),set);
            String salesOperatorIds = "";
            for (Integer usrId : set) {
                salesOperatorIds += usrId + ",";
            }
            if (!salesOperatorIds.equals("")) {
                tour.setSaleOperatorIds(
                        salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
            }
        }
        pageBean = tourGroupBiz.selectSaleProfitByTourListPage(pageBean,
                profitQueryByTourDTO.getBizId(),profitQueryByTourDTO.getUserIdSet());
        // 统计成人、小孩、全陪
        PageBean<TourGroup> pb = tourGroupBiz.selectSaleProfitByTourCon(pageBean,
                profitQueryByTourDTO.getBizId(), profitQueryByTourDTO.getUserIdSet());

        // 总成本、总收入
        TourGroup group = tourGroupBiz.selectSaleProfitByTourConAndMode(pageBean,
                profitQueryByTourDTO.getBizId(), profitQueryByTourDTO.getUserIdSet());

        TourGroup groupCost = tourGroupBiz.selectSumCostProfit(tour,
                profitQueryByTourDTO.getBizId(), profitQueryByTourDTO.getUserIdSet());

        if (group == null) {
            group = new TourGroup();
            group.setIncome(new BigDecimal(0));
            group.setTotalBudget(new BigDecimal(0));
        }
        if (groupCost == null) {
            groupCost = new TourGroup();
            groupCost.setSumTotalIncome(new BigDecimal(0));
            groupCost.setSumTotalCost(new BigDecimal(0));
        }
        ProfitQueryByTourResult result = new ProfitQueryByTourResult();
        result.setPageBean(pageBean);
        result.setPb(pb);
        result.setGroup(group);
        result.setGroupCost(groupCost);
        return result;
    }

    @Override
    public BookingProfitTableResult operatorSummaryTable(TourGroup tour, Integer bizId,Set<Integer> userIdSet) {
        PageBean pageBean = new PageBean();
        pageBean.setPage(1);
        pageBean.setPageSize(10000);
        if (StringUtils.isBlank(tour.getSaleOperatorIds())
                && StringUtils.isNotBlank(tour.getOrgIds())) {
            Set<Integer> set = new HashSet<Integer>();
            String[] orgIdArr = tour.getOrgIds().split(",");
            for (String orgIdStr : orgIdArr) {
                set.add(Integer.valueOf(orgIdStr));
            }
            set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId,set);
            String operatorIds = "";
            for (Integer usrId : set) {
                operatorIds += usrId + ",";
            }
            if (!operatorIds.equals("")) {
                tour.setSaleOperatorIds(operatorIds.substring(0, operatorIds.length() - 1));
            }
        }
        pageBean.setParameter(tour);
        Map<String,Object> map = tourGroupBiz.selectBookingProfitTotal(pageBean,bizId, userIdSet);
        pageBean = tourGroupBiz.selectBookingProfitList(pageBean, bizId, userIdSet);
        List result = pageBean.getResult();
        if (result != null && result.size() > 0) {
            for (Object obj : result) {
                TourGroup tGroup = (TourGroup) obj;
                if (tGroup.getGroupMode() < 1) {
                    tGroup.setSupplierName("散客团");
                }
            }
        }
        BookingProfitTableResult result1 = new BookingProfitTableResult();
        result1.setPageBean(pageBean);
        result1.setSum(map);
        return  result1;
    }

    @Override
    public BookingProfitTableResult toProfitSaleExcel(GroupOrder groupOrder, Integer bizId, Set<Integer> userIdSet) {
        if (StringUtils.isBlank(groupOrder.getSaleOperatorIds()) && StringUtils.isNotBlank(groupOrder.getOrgIds())) {
            Set<Integer> set = new HashSet<Integer>();
            String[] orgIdArr = groupOrder.getOrgIds().split(",");
            for (String orgIdStr : orgIdArr) {
                set.add(Integer.valueOf(orgIdStr));
            }
            set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
            String salesOperatorIds = "";
            for (Integer usrId : set) {
                salesOperatorIds += usrId + ",";
            }
            if (!salesOperatorIds.equals("")) {
                groupOrder.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
            }
        }
        PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();
        pageBean.setPage(1);
        pageBean.setPageSize(10000);
        pageBean.setParameter(groupOrder);
        pageBean = groupOrderBiz.selectProfitEverifyListPage(pageBean, bizId, userIdSet, 1);
        Map<String,Object> map = groupOrderBiz.selectProfitEverifyByTotal(pageBean, bizId, userIdSet, 1);
        List<DicInfo> typeList = dicBiz.getListByTypeCode(com.yimayhd.erpcenter.common.contants.BasicConstants.SALES_TEAM_TYPE,
                bizId);
        BookingProfitTableResult result1 = new BookingProfitTableResult();
        result1.setPageBean(pageBean);
        result1.setTypeList(typeList);
        result1.setSum(map);
        return  result1;
    }

    @Override
    public WebResult<List<TourGroupForCarCar>> selectGroupInfoWithArrangedTransAndGuideForCarCar(PageBean pageBean) {
        logger.info("param pageBean={}", JSON.toJSONString(pageBean));
        WebResult<List<TourGroupForCarCar>> result = new WebResult<List<TourGroupForCarCar>>();
        GroupInfoQueryForCarCar paramerter = (GroupInfoQueryForCarCar) pageBean.getParameter();
        if (StringUtils.isBlank(paramerter.getSupplierName())) {
            logger.error("params error:pageBean={}",JSON.toJSONString(pageBean));
            result.setErrorCode(OperationErrorCode.PARAM_ERROR);
            return result;
        }
        PageParameterCheckAndDealUtil.pageAndPageSizeCheckAndDealUtil(pageBean);
        List<TourGroupForCarCar> tourGroupForCarCars = tourGroupBiz.selectGroupInfoWithArrangedTransForCarCar(pageBean);
        result.setValue(tourGroupForCarCars);
        return result;
    }

    public ProfitQueryByTourResult toProfitQueryTableByTour(ProfitQueryByTourDTO profitQueryByTourDTO){
		Integer bizId = profitQueryByTourDTO.getBizId();
		TourGroup tour = profitQueryByTourDTO.getTour();
		Set<Integer> userIdSet = profitQueryByTourDTO.getUserIdSet();
		Integer page = profitQueryByTourDTO.getPage();
		Integer pageSize = profitQueryByTourDTO.getPageSize();
		
		PageBean<TourGroup> pageBean = new PageBean<TourGroup>();
		pageBean.setPage(page);
		if (pageSize == null) {
			pageSize = Constants.PAGESIZE;
		}
		pageBean.setPageSize(pageSize);
		pageBean.setParameter(tour);
		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(tour.getSaleOperatorIds())&& StringUtils.isNotBlank(tour.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = tour.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				tour.setSaleOperatorIds(salesOperatorIds.substring(0,salesOperatorIds.length() - 1));
			}
		}
		pageBean = tourGroupBiz.selectProfitByTourConListPage(pageBean,bizId,userIdSet);
		
		// 统计成人、小孩、全陪
		PageBean<TourGroup> pb = tourGroupBiz.selectProfitByTourCon(pageBean,bizId,userIdSet);

		// 总成本、总收入
		TourGroup group = tourGroupBiz.selectProfitByTourConAndMode(pageBean,bizId,userIdSet);
		if (group == null) {
			group = new TourGroup();
			group.setIncome(new BigDecimal(0));
			group.setTotalBudget(new BigDecimal(0));
		}
		
		ProfitQueryByTourResult result=new ProfitQueryByTourResult();
		result.setPageBean(pageBean);
		result.setPb(pb);
		result.setGroup(group);
		
		return result;
	}
	
	public ToSKConfirmPreviewResult toSKConfirmPreview(ToSKConfirmPreviewDTO toSKConfirmPreviewDTO){
		Integer groupId=toSKConfirmPreviewDTO.getGroupId();
		Integer curUserId=toSKConfirmPreviewDTO.getCurUserId();
		Integer supplierId=toSKConfirmPreviewDTO.getSupplierId();
		Integer curBizId=toSKConfirmPreviewDTO.getCurBizId();
		
		
		//FIXME 这个在web中做了单独的封装
//		String imgPath = null;
		
		TourGroup tour = tourGroupBiz.selectByPrimaryKey(groupId);
		PlatformEmployeePo po = platformEmployeeBiz.findByEmployeeId(curUserId);
		po.setOrgName(platformOrgBiz.findByOrgId(po.getOrgId()).getName());
		List<GroupOrder> suppliers = groupOrderBiz.selectSupplierByGroupId(groupId);
		SupplierInfo supplierInfo = null;
		List<SupplierInfo> supplierList = new ArrayList<SupplierInfo>();

		for (GroupOrder order : suppliers) {
			supplierInfo = supplierBiz.selectBySupplierId(order.getSupplierId());
			supplierList.add(supplierInfo);
		}
		
		// 行程
		GroupOrder supplier = null;
		if (null == supplierId && supplierList.size() > 0) {
			supplierId = supplierList.get(0).getId();
		}
		GroupRouteVO vo = groupRouteBiz.findGroupRouteByGroupIdAndSupplierId(groupId, supplierId);

		List<GroupRouteDayVO> groupRouteDayVOs = vo.getGroupRouteDayVOList();

		List<GroupPriceVo> vos = groupOrderBiz.selectSupplierByGroupIdAndSupplierId(groupId, supplierId);

		List<GroupOrder> orders = groupOrderBiz.selectOrderByGroupIdAndBizIdAndSupplierId(groupId, supplierId,curBizId);
		for (GroupOrder groupOrder : orders) {
			if (groupOrder.getSupplierId().equals(supplierId)) {
				supplier = groupOrder;
			}
		}
		GroupOrderPrintPo gopp = null;
		List<GroupOrderPrintPo> gopps = new ArrayList<GroupOrderPrintPo>();
		for (GroupOrder order : orders) {
			List<GroupOrderGuest> guests = groupOrderGuestBiz.selectByOrderId(order.getId());
			gopp = new GroupOrderPrintPo();
			// 客人接送信息
			gopp.setSupplierName(order.getSupplierName());
			gopp.setSaleOperatorName(order.getSaleOperatorName());
			gopp.setRemark(order.getRemarkInternal());
			gopp.setPlace((order.getProvinceName() == null ? "" : order.getProvinceName()) + (order.getCityName() == null ? "" : order.getCityName()));
			gopp.setPersonNum(order.getNumAdult()+"+"+order.getNumChild());
			// 根据散客订单统计客人信息
			if (guests.size() > 0) {
				gopp.setCertificateNums(getGuestInfoNoPhone(guests));
				for (GroupOrderGuest guest : guests) {
					if (guest.getIsLeader() == 1) {
						gopp.setGuesStatic(guest.getName() + "\n"+ guest.getMobile());
						break;
					}
				}
				/*
				if (gopp.getGuestInfo() == null || gopp.getGuestInfo() == "") {
					// 如果客人中没有领队，默认取一条数据显示
					gopp.setGuesStatic(guests.get(0).getName() + "\n"
							+ guests.get(0).getMobile());
				}*/
			}
			
			gopp.setReceiveMode(order.getReceiveMode());
			// 根据散客订单统计酒店信息
			List<GroupRequirement> grogShopList = groupRequirementBiz.selectByOrderAndType(order.getId(), 3);
			if (grogShopList.size() > 0) {
				if (grogShopList.get(0).getHotelLevel() != null) {
					//FIXME 这里dicService实现的有问题
					//gopp.setHotelLevel(dicService.getById(grogShopList.get(0).getHotelLevel()).getValue()+ "\n");
				}
				gopp.setHotelNum(getHotelNum(grogShopList));
			}
			// 省外交通
			// 根据散客订单统计接机信息
			List<GroupOrderTransport> groupOrderTransports = groupOrderTransportBiz.selectByOrderId(order.getId());
			gopp.setAirPickup("接机：" + getAirInfo(groupOrderTransports, 0));
			
			// 根据散客订单统计送机信息
			gopp.setAirOff("送机：" + getAirInfo(groupOrderTransports, 1));

			// 省内交通
			gopp.setTrans("省内：" + getSourceType(groupOrderTransports));
			gopps.add(gopp);
		}
		List<BookingGuide> guides = bookingGuideBiz.selectGuidesByGroupId(groupId);
		
		ToSKConfirmPreviewResult result=new ToSKConfirmPreviewResult();
		
		result.setGopps(gopps);
		result.setGroupRouteDayVOs(groupRouteDayVOs);
		result.setGuide(getGuides(guides));
		result.setGuides(guides);
//		result.setImgPath(imgPath);
		result.setPo(po);
		result.setSupplier(supplier);
		result.setSupplierList(supplierList);
		result.setTour(tour);
		result.setVos(vos);
		
		return result;
	}

    @Override
    public ProfitEverifyTableResult toProfitEverifyTable(GroupOrder groupOrder, Integer page, Integer pageSize, Integer bizId, Set<Integer> dataUserIdSet) {
        try {
            ProfitEverifyTableResult result = new ProfitEverifyTableResult();
            PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();
            if(page==null){
                pageBean.setPage(1);
            }else{
                pageBean.setPage(page);
            }
            if(pageSize==null){
                pageBean.setPageSize(com.yimayhd.erpcenter.dal.product.constans.Constants.PAGESIZE);
            }else{
                pageBean.setPageSize(pageSize);
            }

            if (groupOrder.getDateType() != null && groupOrder.getDateType() == 2) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (!"".equals(groupOrder.getStartTime())) {
                    groupOrder.setStartTime(sdf.parse(groupOrder.getStartTime()).getTime() + "");
                }
                if (!"".equals(groupOrder.getEndTime())) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(sdf.parse(groupOrder.getEndTime()));
                    calendar.add(Calendar.DAY_OF_MONTH, +1);// 让日期加1
                    groupOrder.setEndTime(calendar.getTime().getTime() + "");
                }

            }

            if (StringUtils.isBlank(groupOrder.getSaleOperatorIds()) && StringUtils.isNotBlank(groupOrder.getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = groupOrder.getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
                String salesOperatorIds = "";
                for (Integer usrId : set) {
                    salesOperatorIds += usrId + ",";
                }
                if (!salesOperatorIds.equals("")) {
                    groupOrder.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
                }
            }

            pageBean.setPage(page);
            pageBean.setParameter(groupOrder);
            //查询内部结算信息
            pageBean = groupOrderBiz.selectProfitEverifyListPage(pageBean, bizId,dataUserIdSet, 1);

            List<GroupOrder> list = pageBean.getResult();
            Integer pageTotalAudit = 0;
            Integer pageTotalChild = 0;
            Integer pageTotalGuide = 0;
            //收入
            BigDecimal sum_total = new BigDecimal(0);
            //其它收入
            BigDecimal sum_qdtotal = new BigDecimal(0);
            //成本
            BigDecimal sum_budget = new BigDecimal(0);
            //毛利
            BigDecimal sum_g_profit = new BigDecimal(0);

            List<DicInfo> typeList = dicBiz.getListByTypeCode(BasicConstants.SALES_TEAM_TYPE,
                    bizId);

            if (pageBean.getResult() != null && pageBean.getResult().size() > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (GroupOrder groupOrder2 : list) {
                    pageTotalAudit += groupOrder2.getNumAdult() == null ? 0 : groupOrder2.getNumAdult();
                    pageTotalChild += groupOrder2.getNumChild() == null ? 0 : groupOrder2.getNumChild();
                    pageTotalGuide += groupOrder2.getNumGuide() == null ? 0 : groupOrder2.getNumGuide();
                    sum_total=sum_total.add(groupOrder2.getTotal()==null?new BigDecimal(0): groupOrder2.getTotal());
                    sum_qdtotal=sum_qdtotal.add(groupOrder2.getQdtotal()==null?new BigDecimal(0):groupOrder2.getQdtotal());
                    sum_budget=sum_budget.add(groupOrder2.getBudget()==null?new BigDecimal(0):groupOrder2.getBudget());
                    sum_g_profit=sum_g_profit.add(groupOrder2.getG_profit()==null?new BigDecimal(0):groupOrder2.getG_profit());

                    Long createTime = groupOrder2.getCreateTime();
                    String dateStr = sdf.format(createTime);
                    groupOrder2.setCreateTimeStr(dateStr);
                    for (DicInfo item : typeList) {
                        if (item.getId().equals(groupOrder2.getOrderMode()))
                            groupOrder2.setOrderModeType(item.getValue());
                    }
                }
            }

            //查询内部结算信息
            GroupOrder groupOrderTatol=groupOrderBiz.selectProfitEverifyByCon(groupOrder,bizId,dataUserIdSet, 1);
            result.setPageTotalAudit(pageTotalAudit);
            result.setPageTotalChild(pageTotalChild);
            result.setPageTotalGuide(pageTotalGuide);
            result.setSum_budget(sum_budget);
            result.setSum_g_profit(sum_g_profit);
            result.setSum_qdtotal(sum_qdtotal);
            result.setPageBean(pageBean);
            if(groupOrderTatol==null){
                result.setGroupOrderTatol(true);
                return result;
            }
            //人数
            String numberPeople=groupOrderTatol.getNumAdult()+"+"+groupOrderTatol.getNumChild()+"+"+groupOrderTatol.getNumGuide();
            //收入
            BigDecimal z_sum_total = (BigDecimal) (groupOrderTatol.getSumTotal()==null?(new BigDecimal(0)):groupOrderTatol.getSumTotal());
            //其它收入
            BigDecimal z_sum_qdtotal = (BigDecimal) (groupOrderTatol.getSumQdtotal()==null?(new BigDecimal(0)):groupOrderTatol.getSumQdtotal());
            //成本
            BigDecimal z_sum_budget = (BigDecimal) (groupOrderTatol.getSumBudget()==null?(new BigDecimal(0)):groupOrderTatol.getSumBudget());
            //毛利
            BigDecimal z_sum_profit =(BigDecimal) (groupOrderTatol.getSumProfit()==null?(new BigDecimal(0)):groupOrderTatol.getSumProfit());
            result.setGroupOrderTatol(false);
            result.setNumberPeople(numberPeople);
            result.setZ_sum_total(z_sum_total);
            result.setZ_sum_budget(z_sum_budget);
            result.setZ_sum_qdtotal(z_sum_qdtotal);
            result.setZ_sum_profit(z_sum_profit);
            return result;
        }catch (Exception e){
            logger.error("toProfitEverifyTable error:",e);
        }

        return null;
    }

    /**
	 * 组织所有导游信息
	 * 
	 * @param guides
	 * @return
	 */
	public String getGuides(List<BookingGuide> guides) {
		StringBuilder sb = new StringBuilder();
		for (BookingGuide guide : guides) {
			sb.append(guide.getGuideName() + " " + guide.getGuideMobile()
					+ "\n");
		}
		return sb.toString();
	}

    @Override
    public ToProfitExcelResult toProfitExcel(GroupOrder groupOrder,Integer bizId,Set<Integer> dataUserIdSet) {

        ToProfitExcelResult result = new ToProfitExcelResult();
        if (StringUtils.isBlank(groupOrder.getSaleOperatorIds()) && StringUtils.isNotBlank(groupOrder.getOrgIds())) {
            Set<Integer> set = new HashSet<Integer>();
            String[] orgIdArr = groupOrder.getOrgIds().split(",");
            for (String orgIdStr : orgIdArr) {
                set.add(Integer.valueOf(orgIdStr));
            }
            set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
            String salesOperatorIds = "";
            for (Integer usrId : set) {
                salesOperatorIds += usrId + ",";
            }
            if (!salesOperatorIds.equals("")) {
                groupOrder.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
            }
        }
        PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();
        pageBean.setPage(1);
        pageBean.setPageSize(10000);
        pageBean.setParameter(groupOrder);
        pageBean = groupOrderBiz.selectProfitByConListPage(pageBean, bizId,
                dataUserIdSet);
        GroupOrder staticInfo = groupOrderBiz.selectProfitByCon(pageBean, bizId,
                dataUserIdSet);
        GroupOrder groupOrderProfit = groupOrderBiz.selectProfitByConAndMode(pageBean,
                bizId, dataUserIdSet);

        result.setPageBean(pageBean);
        result.setStaticInfo(staticInfo);
        result.setGroupOrderProfit(groupOrderProfit);
        return result;


    }

    @Override
    public ToProfitExcelResult getGroupIdsByTravelExportStatus(Integer c, Integer bizId) {
        ToProfitExcelResult toProfitExcelResult = new ToProfitExcelResult();
        List<Integer> list = tourGroupBiz.getGroupIdsByTravelExportStatus(c, bizId);
        toProfitExcelResult.setList(list);
        return toProfitExcelResult;
    }

    @Override
    public PageBean selectTourGroupCodeListPage(PageBean pageBean, Integer bizId, Set<Integer> dataUserIdSet) {
        return tourGroupBiz.selectTourGroupCodeListPage(pageBean,bizId,dataUserIdSet);
    }

    @Override
    public Map<String, Object> changerGroupCodePage(Integer groupId, Integer bizId) {
        TourGroup tg =tourGroupBiz.selectByPrimaryKey(groupId);
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("oldGroupCode",tg.getGroupCode());

        List<GroupOrder> lists=groupOrderBiz.selectOrderByGroupId(groupId);
        GroupOrder go=lists.get(0);
        Integer orgId=0;
        if(tg.getGroupMode()>0){
            PlatformEmployeePo salePEP = platformEmployeeBiz
                    .findByEmployeeId(go.getSaleOperatorId());
            orgId=salePEP.getOrgId();
        }else{
            PlatformEmployeePo operatorPEP = platformEmployeeBiz
                    .findByEmployeeId(tg.getOperatorId());
            orgId=operatorPEP.getOrgId();
        }
        String supplierCode = platformOrgBiz.getCompanyCodeByOrgId(bizId,orgId);
        TourGroup tourGroup=tourGroupBiz.selectGroupCodeSort(tg.getBizId(), tg.getGroupMode(), go.getDepartureDate());
        String makeCodeByMode = tourGroupBiz.makeCodeByMode(supplierCode, tg.getGroupMode(), go.getDepartureDate(),
                tg.getGroupCodeMark(), tourGroup == null ? 1: tourGroup.getGroupCodeSort() + 1);
        resultMap.put("groupCodeSort",tourGroup == null ? 1: tourGroup.getGroupCodeSort() + 1);
        resultMap.put("newGroupCode",makeCodeByMode);
        return resultMap;
    }

    @Override
    public WebResult<Boolean> updateTourGroup(TourGroup tourGroup) {
        int result = tourGroupBiz.updateTourGroup(tourGroup);
        WebResult<Boolean> webResult = new WebResult<Boolean>();
        if (result < 1) {
            webResult.setErrorCode(OperationErrorCode.MODIFY_ERROR);
        }
        return webResult;
    }

    @Override
    public WebResult<Boolean> updateWapType(Integer groupId) {
	    tourGroupBiz.updateWapType(groupId);
	    WebResult<Boolean> result = new WebResult<Boolean>();
        return result;
    }

    @Override
    public List<TourGroup> selectTotalByResId(Integer resId) {
        return tourGroupBiz.selectTotalByResId(resId);
    }

}
