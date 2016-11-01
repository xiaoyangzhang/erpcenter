package com.yimayhd.erpcenter.facade.sales.service.impl;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupSupplierBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.*;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.*;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.biz.sys.service.SysBizBankAccountBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SalesVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.facade.sales.result.InitGroupResult;
import com.yimayhd.erpcenter.facade.sales.result.RouteResult;
import com.yimayhd.erpcenter.facade.sales.result.quality.QualityListResult;
import com.yimayhd.erpcenter.facade.sales.service.InitGroupFacade;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.biz.service.SupplierDriverBiz;
import com.yimayhd.erpresource.biz.service.SupplierGuideBiz;
import com.yimayhd.erpresource.biz.service.SupplierImgBiz;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/31 17:16
 */
public class InitGroupFacadeImpl implements InitGroupFacade {
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
    public InitGroupResult qualityList(Integer bizId) {
        InitGroupResult initGroupResult = new InitGroupResult();
        try {
            initGroupResult.setOrgJsonStr(platformOrgBiz.getComponentOrgTreeJsonStr(bizId));
            initGroupResult.setOrgUserJsonStr(platformEmployeeBiz.getComponentOrgUserTreeJsonStr(bizId));
        } catch (Exception e) {
            logger.error("", e);
        }
        return initGroupResult;
    }

    @Override
    public InitGroupResult initGroupList(Integer pageSize, Integer page, TourGroupVO group, String guideName, Map<String, Object> pm, Set<Integer> userIdSet,Integer bizId) {
        InitGroupResult initGroupResult = new InitGroupResult();
        try {
            PageBean pageBean = new PageBean();
            if (page == null) {
                pageBean.setPage(1);
            } else {
                pageBean.setPage(page);
            }
            if (pageSize == null) {
                pageBean.setPageSize(Constants.PAGESIZE);
            } else {
                pageBean.setPageSize(pageSize);
            }
            //如果人员为空并且部门不为空，则取部门下的人id
            if(StringUtils.isBlank(group.getSaleOperatorIds()) && StringUtils.isNotBlank(group.getOrgIds())){
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = group.getOrgIds().split(",");
                for(String orgIdStr : orgIdArr){
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
                String salesOperatorIds="";
                for(Integer usrId : set){
                    salesOperatorIds+=usrId+",";
                }
                if(!salesOperatorIds.equals("")){
                    group.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
                }
            }
            if(null!=group.getSaleOperatorIds() && !"".equals(group.getSaleOperatorIds())){
                pm.put("operator_id", group.getSaleOperatorIds());
            }
            pm.put("set", userIdSet);
            pageBean.setParameter(pm);
            pageBean = tourGroupBiz.selectInitGroupList(pageBean);
            initGroupResult.setPageBean(pageBean);
        } catch (Exception e) {
            logger.error("", e);
        }
        return initGroupResult;
    }

    @Override
    public InitGroupResult getInitGroupList(Integer groupId, Integer bizId) {
        InitGroupResult initGroupResult = new InitGroupResult();
        try {
            List<GroupOrder> orderList =null;
            List<BookingDelivery> bookingDeliveryList =null;
            List<BookingSupplier> hotelList = null;
            List<BookingSupplier> restaurantList = null;

            List<BookingSupplier> fleetList = null;
            List<BookingSupplier> scenicsportList = null;
            List<BookingSupplier> insuranceList = null;
            List<BookingSupplier> airticketList = null;
            List<BookingSupplier> trainList = null;

            TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(groupId);
            if(tourGroup!=null){
                orderList = groupOrderBiz.selectInitOrderList(bizId, tourGroup.getId());
                bookingDeliveryList = bookingDeliveryBiz.selectInitDeliveryList(tourGroup.getId());
                hotelList = bookingSupplierBiz.selectByGroupIdAndSupplierType(groupId,Constants.HOTEL);
                restaurantList = bookingSupplierBiz.selectByGroupIdAndSupplierType(groupId,Constants.RESTAURANT);

                fleetList = bookingSupplierBiz.selectByGroupIdAndSupplierType(groupId,Constants.FLEET);
                scenicsportList = bookingSupplierBiz.selectByGroupIdAndSupplierType(groupId,Constants.SCENICSPOT);
                insuranceList = bookingSupplierBiz.selectByGroupIdAndSupplierType(groupId,Constants.INSURANCE);
                airticketList = bookingSupplierBiz.selectByGroupIdAndSupplierType(groupId,Constants.AIRTICKETAGENT);
                trainList = bookingSupplierBiz.selectByGroupIdAndSupplierType(groupId,Constants.TRAINTICKETAGENT);
            }
            initGroupResult.setTourGroup(tourGroup);
            initGroupResult.setOrderList(orderList);
            initGroupResult.setBookingDeliveryList(bookingDeliveryList);
            initGroupResult.setHotelList(hotelList);
            initGroupResult.setRestaurantList(restaurantList);
            initGroupResult.setFleetList(fleetList);
            initGroupResult.setScenicsportList(scenicsportList);
            initGroupResult.setInsuranceList(insuranceList);
            initGroupResult.setAirticketList(airticketList);
            initGroupResult.setTrainList(trainList);
        } catch (Exception e) {
            logger.error("", e);
        }
        return initGroupResult;
    }

    @Override
    public InitGroupResult saveInitGroupInfo(SalesVO salesVO, Integer bizId,Integer userId,String userName) {
        InitGroupResult initGroupResult = new InitGroupResult();
        try {
            groupOrderBiz.saveInitGroupInfo(bizId, salesVO,userId,userName);
        } catch (Exception e) {
            logger.error("", e);
        }
        return initGroupResult;
    }

    @Override
    public InitGroupResult deleteInitGroupInfo(Integer groupId) {
        InitGroupResult initGroupResult = new InitGroupResult();
        try {
            tourGroupBiz.deleteInitGroupInfo(groupId);
        } catch (Exception e) {
            logger.error("", e);
        }
        return initGroupResult;
    }
}
