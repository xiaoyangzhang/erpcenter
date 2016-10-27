package com.yimayhd.erpcenter.facade.sales.service.impl;

import com.alibaba.fastjson.JSON;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.airticket.AirTicketRequestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.*;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.*;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.*;
import com.yimayhd.erpcenter.facade.sales.query.ChangeGroupDTO;
import com.yimayhd.erpcenter.facade.sales.result.*;
import com.yimayhd.erpcenter.facade.sales.service.TourGroupFacade;
import com.yimayhd.erpcenter.facade.sales.utils.DateUtils;
import com.yimayhd.erpcenter.facade.sales.utils.GroupCodeUtil;
import com.yimayhd.erpcenter.facade.sales.utils.MD5Util;
import com.yimayhd.erpcenter.facade.sales.utils.OpenPlatformConstannt;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.biz.service.SupplierDriverBiz;
import com.yimayhd.erpresource.biz.service.SupplierGuideBiz;
import com.yimayhd.erpresource.biz.service.SupplierImgBiz;
import com.yimayhd.erpresource.dal.constants.BasicConstants;
import com.yimayhd.erpresource.dal.po.*;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

import java.sql.Timestamp;
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
    public ResultSupport pushWap(Integer groupId) {
        ResultSupport resultSupport = new ResultSupport();
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
                    if (null != item.getDriverId()){
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

            String resultString = "{result: 'success',resultCode: '0',msg: ''}";
            try {
                String appKey = OpenPlatformConstannt.openAPI_AssistantMap.get("appKey");
                String secretKey = OpenPlatformConstannt.openAPI_AssistantMap.get("secretKey");
                String timeStamp = DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
                String jsonStr = JSON.toJSONString(groupVo);
                String supplierInfo = JSON.toJSONString(supplierVo);
                //签名
                Map<String, String> params = new HashMap<String, String>();
                params.put("appKey", appKey);
                params.put("timestamp", timeStamp);
                String getSign = MD5Util.getSign_Taobao(secretKey, params);

                // 访问接口
                CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost(OpenPlatformConstannt.openAPI_AssistantMap.get("Url")
                        + OpenPlatformConstannt.openAPI_AssistantMap.get("pushMethod"));

                // 设置连接超时时间
                RequestConfig requestConfig = RequestConfig.custom()
                        .setConnectTimeout(5000).setConnectionRequestTimeout(1000)
                        .setSocketTimeout(5000).build();
                httpPost.setConfig(requestConfig);

                // 访问参数
                List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
                nameValuePairList.add(new BasicNameValuePair("appKey", appKey));
                nameValuePairList.add(new BasicNameValuePair("timestamp", timeStamp));
                nameValuePairList.add(new BasicNameValuePair("sign", getSign));
                nameValuePairList.add(new BasicNameValuePair("jsonStr", jsonStr));
                nameValuePairList.add(new BasicNameValuePair("supplierInfo", supplierInfo));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList, "utf-8"));

                CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);

                try {
                    HttpEntity httpEntity = closeableHttpResponse.getEntity();
                    resultString = EntityUtils.toString(httpEntity);
                } finally {
                    closeableHttpResponse.close();
                }

            } catch (Exception e) {
               // log.error("推送APP错误信息:" + e.getMessage());
               // return errorJson("推送信息失败"+e.getMessage());
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return resultSupport;
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
    public TogroupRequirementResult togroupRequirement(Integer orderId, Integer stateFinance, Integer state, Integer curBizId) {
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
            toGroupListResult.setOrgJsonStr(platformOrgBiz.getComponentOrgTreeJsonStr(curBizId));
            toGroupListResult.setOrgUserJsonStr(platformEmployeeBiz.getComponentOrgUserTreeJsonStr(curBizId));
        } catch (Exception e) {
            logger.error("", e);
        }
        return toGroupListResult;
    }


}
