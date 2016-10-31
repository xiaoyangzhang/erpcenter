package com.yimayhd.erpcenter.facade.sales.service.impl;

import com.alibaba.fastjson.JSON;
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
import com.yimayhd.erpcenter.dal.basic.constant.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SpecialGroupOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.Transport;
import com.yimayhd.erpcenter.facade.sales.result.SaveSeatInCoachResult;
import com.yimayhd.erpcenter.facade.sales.result.ToAddSpecialGroupResult;
import com.yimayhd.erpcenter.facade.sales.service.SeatInCoachFacade;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.biz.service.SupplierDriverBiz;
import com.yimayhd.erpresource.biz.service.SupplierGuideBiz;
import com.yimayhd.erpresource.biz.service.SupplierImgBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/31 16:22
 */
public class SeatInCoachFacadeImpl implements SeatInCoachFacade {
    private static final Logger logger = LoggerFactory.getLogger("SpecialGroupFacadeImpl");
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
    @Autowired
    private SpecialGroupOrderBiz specialGroupOrderBiz;

    @Override
    public SaveSeatInCoachResult saveAndEditSeatInCoach(String transport) {
        SaveSeatInCoachResult saveSeatInCoachResult = new SaveSeatInCoachResult();
        try {
            Transport trans = JSON.parseObject(transport, Transport.class);
            saveSeatInCoachResult.setC(groupOrderTransportBiz.saveAndEditSeatInCoach(trans));
        } catch (Exception e) {
            logger.error("", e);
        }
        return saveSeatInCoachResult;
    }

    @Override
    public SaveSeatInCoachResult saveSeatInCoach(GroupOrderTransport groupOrderTransport) {
        SaveSeatInCoachResult saveSeatInCoachResult = new SaveSeatInCoachResult();
        try {
            groupOrderTransportBiz.insertSelective(groupOrderTransport) ;
        } catch (Exception e) {
            logger.error("", e);
        }
        return saveSeatInCoachResult;
    }

    @Override
    public SaveSeatInCoachResult editSeatInCoach(Integer id) {
        SaveSeatInCoachResult saveSeatInCoachResult = new SaveSeatInCoachResult();
        try {
            GroupOrderTransport groupOrderTransport = groupOrderTransportBiz.selectByPrimaryKey(id) ;
            saveSeatInCoachResult.setGroupOrderTransport(groupOrderTransport);
        } catch (Exception e) {
            logger.error("", e);
        }
        return saveSeatInCoachResult;
    }

    @Override
    public SaveSeatInCoachResult updateSeatInCoach(GroupOrderTransport groupOrderTransport) {
        SaveSeatInCoachResult saveSeatInCoachResult = new SaveSeatInCoachResult();
        try {
            groupOrderTransportBiz.updateByPrimaryKeySelective(groupOrderTransport) ;
        } catch (Exception e) {
            logger.error("", e);
        }
        return saveSeatInCoachResult;
    }

    @Override
    public SaveSeatInCoachResult deleteSeatInCoachById(Integer id) {
        SaveSeatInCoachResult saveSeatInCoachResult = new SaveSeatInCoachResult();
        try {
            groupOrderTransportBiz.deleteByPrimaryKey(id) ;
        } catch (Exception e) {
            logger.error("", e);
        }
        return saveSeatInCoachResult;
    }

    @Override
    public SaveSeatInCoachResult batchInput(List<String> userArray) {
        SaveSeatInCoachResult saveSeatInCoachResult = new SaveSeatInCoachResult();
        try {
            GroupOrderTransport got = null ;
            for (int i = 0; i < userArray.size(); i++) {
                String dataString = userArray.get(i) ;
                String[] ss = dataString.replace("\\n","").replace(";","").replace("，",",").replace("：",":").split(",") ;
                got = new GroupOrderTransport() ;
                if(ss.length==6){
                    got.setDepartureTime(ss[0]);
                    got.setArrivalTime(ss[1]);
                    got.setClassNo(ss[2]);
                    got.setDepartureCity(ss[3]);
                    got.setArrivalCity(ss[4]);
                    got.setOrderId(Integer.parseInt(ss[5]));
                }
                if(ss.length==4){
                    got.setDepartureTime(ss[0]);
                    got.setArrivalTime(ss[1]);
                    got.setClassNo(ss[2]);
                    got.setOrderId(Integer.parseInt(ss[3]));
                }
                groupOrderTransportBiz.insertSelective(got) ;
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return saveSeatInCoachResult;
    }
}
