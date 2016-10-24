package com.yimayhd.erpcenter.facade.product.service.impl;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRemarkBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRouteBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.product.constans.Constants;
import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
import com.yimayhd.erpcenter.facade.errorcode.ProductErrorCode;
import com.yimayhd.erpcenter.facade.query.DetailDTO;
import com.yimayhd.erpcenter.facade.query.ToSearchListStateDTO;
import com.yimayhd.erpcenter.facade.query.UpStateDTO;
import com.yimayhd.erpcenter.facade.result.DetailResult;
import com.yimayhd.erpcenter.facade.result.ToSearchListStateResult;
import com.yimayhd.erpcenter.facade.result.UpStateResult;
import com.yimayhd.erpcenter.facade.service.ProductUpAndDownFrameFacade;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/18 18:08
 */
public class ProductUpAndDownFrameFacadeImpl implements ProductUpAndDownFrameFacade {
    private static final Logger logger = LoggerFactory.getLogger("ProductUpAndDownFrameFacadeImpl");

    @Autowired
    private DicBiz dicBiz;
    @Autowired
    private PlatformOrgBiz platformOrgBiz;
    @Autowired
    private PlatformEmployeeBiz platformEmployeeBiz;
    @Autowired
    private RegionBiz regionBiz;
    @Autowired
    private ProductInfoBiz productInfoBiz;
    @Autowired
    private ProductRouteBiz productRouteBiz;
    @Autowired
    private ProductRemarkBiz productRemarkBiz;
    @Autowired
    private ProductGroupBiz productGroupBiz;

    /**
     * 产品列表查询
     *
     * @param toSearchListStateDTO
     * @return
     */
    @Override
    public ToSearchListStateResult toSearchListState(ToSearchListStateDTO toSearchListStateDTO) {
        ToSearchListStateResult toSearchListStateResult = new ToSearchListStateResult();
        try {
            // 省市
            List<RegionInfo> allProvince = regionBiz.getAllProvince();
            // 产品名称
            List<DicInfo> brandList = dicBiz.getListByTypeCode(
                    BasicConstants.CPXL_PP, toSearchListStateDTO.getBizId());
            if (toSearchListStateDTO.getPage() == null) {
                toSearchListStateDTO.setPage(1);
            }
            PageBean pageBean = new PageBean();
            if (toSearchListStateDTO.getPageSize() == null) {
                pageBean.setPageSize(Constants.PAGESIZE);
            } else {
                pageBean.setPageSize(toSearchListStateDTO.getPageSize());
            }
            if (StringUtils.isBlank(toSearchListStateDTO.getOperatorIds())
                    && StringUtils.isNotBlank(toSearchListStateDTO.getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = toSearchListStateDTO.getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(
                        toSearchListStateDTO.getId(), set);
                String salesOperatorIds = "";
                for (Integer usrId : set) {
                    salesOperatorIds += usrId + ",";
                }
                if (!salesOperatorIds.equals("")) {
                    toSearchListStateDTO.setOperatorIds(salesOperatorIds.substring(0,
                            salesOperatorIds.length() - 1));
                }
            }
            pageBean.setParameter(toSearchListStateDTO.getProductInfo());
            pageBean.setPage(toSearchListStateDTO.getPage());
            Map parameters = new HashMap();
            parameters.put("bizId", toSearchListStateDTO.getBizId());
            parameters.put("name", toSearchListStateDTO.getName());
            parameters.put("productName", toSearchListStateDTO.getProductName());
            parameters.put("orgId", toSearchListStateDTO.getOrgId());
            // parameters.put("set", WebUtils.getDataUserIdSet(request));
            pageBean = productInfoBiz.findProductInfos(pageBean, parameters);

            Map<Integer, String> priceStateMap = new HashMap<Integer, String>();
        /*
         * for (Object product : pageBean.getResult()) { ProductInfo info =
		 * (ProductInfo) product; Integer productId = info.getId(); String state
		 * = productInfoService.getProductPriceState(productId);
		 * priceStateMap.put(info.getId(), state); }
		 */
            toSearchListStateResult.setAllProvince(allProvince);
            toSearchListStateResult.setBrandList(brandList);
            toSearchListStateResult.setPriceStateMap(priceStateMap);
            toSearchListStateResult.setPageBean(pageBean);
        } catch (Exception e) {
            logger.error("产品列表查询失败", e);
            toSearchListStateResult.setErrorCode(ProductErrorCode.SYSTEM_ERROR);
        }
        return toSearchListStateResult;
    }

    /**
     * 产品上下架
     *
     * @param upStateDTO
     * @return
     */
    @Override
    public UpStateResult upState(UpStateDTO upStateDTO) {
        UpStateResult upStateResult = new UpStateResult();
        try {
            List<ProductRoute> productRoutes = productRouteBiz
                    .findProductRouteByProductId(upStateDTO.getInfo().getId());
            if (upStateDTO.getInfo().getState() != (byte) -1 && productRoutes.size() == 0) {
                upStateResult.setSuccess(false);
                upStateResult.setResultMsg("产品内无行程内容");
            }
            int c = productInfoBiz.updateProductInfo(upStateDTO.getInfo());
            if (c != 1) {
                upStateResult.setSuccess(false);
                upStateResult.setResultMsg("操作失败！");
            }
        } catch (Exception e) {
            logger.error("修改产品上下架状态失败", e);
            upStateResult.setErrorCode(ProductErrorCode.SYSTEM_ERROR);
        }
        return upStateResult;
    }

    /**
     * 产品详细查询
     *
     * @param detailDTO
     * @return
     */
    @Override
    public DetailResult detail(DetailDTO detailDTO) {
        DetailResult detailResult = new DetailResult();
        try {
            detailResult.setProductInfoVo(productInfoBiz.findProductInfoVoById(detailDTO.getId()));
            detailResult.setProductRouteVo(productRouteBiz.findByProductId(detailDTO.getId()));
            detailResult.setProductRemark(productRemarkBiz.findProductRemarkByProductId(detailDTO.getId()));
            //List<ProductGroup> productGroups = productGroupService.selectProductGroups(id);
            detailResult.setProductGroups(productGroupBiz.selectProductGroupsBySellerId(detailDTO.getId(), detailDTO.getEmployeeId()));
        } catch (Exception e) {
            logger.error("产品详细查询失败", e);
            detailResult.setErrorCode(ProductErrorCode.SYSTEM_ERROR);
        }
        return detailResult;
    }
}
