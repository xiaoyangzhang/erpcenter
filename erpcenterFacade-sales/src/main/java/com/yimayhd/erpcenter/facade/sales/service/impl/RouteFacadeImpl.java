package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupSupplierBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderPriceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderTransportBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRequirementBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.SpecialGroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.biz.sys.service.SysBizBankAccountBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.vo.ProductRouteVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.facade.sales.result.RouteResult;
import com.yimayhd.erpcenter.facade.sales.service.RouteFacade;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.biz.service.SupplierDriverBiz;
import com.yimayhd.erpresource.biz.service.SupplierGuideBiz;
import com.yimayhd.erpresource.biz.service.SupplierImgBiz;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/31 16:38
 */
public class RouteFacadeImpl implements RouteFacade {
    private static final Logger logger = LoggerFactory.getLogger("SpecialGroupFacadeImpl");
    @Autowired
    private ProductInfoBiz productInfoBiz;
    @Autowired
    private ProductRouteBiz productRouteBiz;
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
    public RouteResult stockProduct_list(ProductInfo productInfo, String productName, String name, Integer page,Integer bizId) {
        RouteResult routeResult = new RouteResult();
        try {
            List<DicInfo> brandList = dicBiz
                    .getListByTypeCode(BasicConstants.CPXL_PP,bizId);
            if(page==null){
                page=1;
            }
            productInfo.setTravelDays(1);
            routeResult.setDicInfoList(brandList);
        } catch (Exception e) {
            logger.error("", e);
        }
        return routeResult;
    }

    @Override
    public RouteResult StockProduct_list_table(ProductInfo productInfo, String productName, String name, Integer page, Integer pageSize, Integer bizId) {
        RouteResult routeResult = new RouteResult();
        try {
            List<DicInfo> brandList = dicBiz
                    .getListByTypeCode(BasicConstants.CPXL_PP,bizId);
            if(page==null){
                page=1;
            }
            PageBean<ProductInfo> pageBean = new PageBean<ProductInfo>();
            if(pageSize==null){
                pageBean.setPageSize(Constants.PAGESIZE);
            }else{
                pageBean.setPageSize(pageSize);
            }
            productInfo.setTravelDays(1);
            pageBean.setParameter(productInfo);
            pageBean.setPage(page);
            Map parameters=new HashMap();
            parameters.put("bizId", bizId);
            parameters.put("name", name);
            parameters.put("productName", productName);
            // pageBean = productInfoService.findProductRoutes(pageBean, parameters);
            pageBean = productInfoBiz.selectStockProductListPage(pageBean, parameters);
            routeResult.setDicInfoList(brandList);
            routeResult.setPage(pageBean);
        } catch (Exception e) {
            logger.error("", e);
        }
        return routeResult;
    }

    @Override
    public RouteResult getRouteVo(Integer productId) {
        RouteResult routeResult = new RouteResult();
        try {
            ProductRouteVo productRouteVo = productRouteBiz.findByProductId(productId);
            routeResult.setProductRouteVo(productRouteVo);
        } catch (Exception e) {
            logger.error("", e);
        }
        return routeResult;
    }
}
