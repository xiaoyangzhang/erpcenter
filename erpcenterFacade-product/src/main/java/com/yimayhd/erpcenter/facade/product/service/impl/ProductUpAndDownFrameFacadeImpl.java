package com.yimayhd.erpcenter.facade.product.service.impl;


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
import com.yimayhd.erpcenter.dal.product.constans.Constants;
import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
import com.yimayhd.erpcenter.facade.query.DetailDTO;
import com.yimayhd.erpcenter.facade.query.ToSearchListStateDTO;
import com.yimayhd.erpcenter.facade.query.UpStateDTO;
import com.yimayhd.erpcenter.facade.result.DetailResult;
import com.yimayhd.erpcenter.facade.result.ToSearchListStateResult;
import com.yimayhd.erpcenter.facade.result.UpStateResult;
import com.yimayhd.erpcenter.facade.service.ProductUpAndDownFrameFacade;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/18 18:08
 */
public class ProductUpAndDownFrameFacadeImpl implements ProductUpAndDownFrameFacade {

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
     * 跳转至产品管理页面
     *
     * @param toSearchListStateDTO
     * @return
     */
    @Override
    public ToSearchListStateResult toSearchListState(ToSearchListStateDTO toSearchListStateDTO) {
        ToSearchListStateResult toSearchListStateResult = new ToSearchListStateResult();
        // 省市
        regionBiz.getAllProvince();
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
        parameters.put("bizId", toSearchListStateDTO.getProductInfo().getBizId());
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
        return upStateResult;
    }

    /**
     * 产品详细
     *
     * @param detailDTO
     * @return
     */
    @Override
    public DetailResult detail(DetailDTO detailDTO) {
        DetailResult detailResult = new DetailResult();
        detailResult.setProductInfoVo(productInfoBiz.findProductInfoVoById(detailDTO.getId()));
        detailResult.setProductRouteVo(productRouteBiz.findByProductId(detailDTO.getId()));
        detailResult.setProductRemark(productRemarkBiz.findProductRemarkByProductId(detailDTO.getId()));
        //List<ProductGroup> productGroups = productGroupService.selectProductGroups(id);
        detailResult.setProductGroups(productGroupBiz.selectProductGroupsBySellerId(detailDTO.getId(), detailDTO.getEmployeeId()));
        return detailResult;
    }
}
