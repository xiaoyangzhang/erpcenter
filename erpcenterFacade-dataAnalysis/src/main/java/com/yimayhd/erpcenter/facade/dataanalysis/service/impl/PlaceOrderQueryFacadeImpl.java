package com.yimayhd.erpcenter.facade.dataanalysis.service.impl;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupPriceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.query.QueryBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.CommonSaleBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.PlaceOrderQueryDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.PlaceOrderQueryResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.QueryResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.service.PlaceOrderQueryFacade;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.dal.po.SupplierInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.*;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/12/1 15:21
 */
public class PlaceOrderQueryFacadeImpl implements PlaceOrderQueryFacade {
    private static final Logger logger = LoggerFactory.getLogger("PlaceOrderQueryFacadeImpl");

    @Autowired
    private FinanceBiz financeBiz;
    @Autowired
    private ProductGroupPriceBiz productGroupPriceBiz;
    @Autowired
    private BookingShopBiz bookingShopBiz;
    @Autowired
    private QueryBiz queryBiz;
    @Autowired
    private PlatformEmployeeBiz platformEmployeeBiz;
    @Autowired
    private BookingGuideBiz bookingGuideBiz;
    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private TourGroupBiz tourGroupBiz;
    @Autowired
    private SupplierBiz supplierBiz;
    @Autowired
    private DicBiz dicBiz;
    @Autowired
    private RegionBiz regionBiz;
    @Autowired
    private PlatformOrgBiz platformOrgBiz;

    @Override
    public PlaceOrderQueryResult getSupplierDetails(PlaceOrderQueryDTO placeOrderQueryDTO) {
        PlaceOrderQueryResult placeOrderQueryResult = new PlaceOrderQueryResult();
        try {
            Map paramters = placeOrderQueryDTO.getParamters();

            // 如果选择了【省份】作为查询条件
            Map map = new HashMap<String, Object>();
            map.put("provinceId", paramters.get("provinceId"));
            map.put("cityId", paramters.get("cityId"));
            map.put("supplierType", paramters.get("supplierType"));
            map.put("level", paramters.get("detailLevel"));
            map.put("bizId", paramters.get("bizId"));
            if (paramters.get("provinceId") != null) {
                StringBuilder supplierIds = new StringBuilder();
                List<Map<String, Integer>> supplier_province = supplierBiz
                        .searchSupplierByArea(map); // map需要包含 procinceId, cityId,
                // supplier三个参数
                if (supplier_province != null && supplier_province.size() > 0) {
                    for (Map<String, Integer> item : supplier_province) {
                        supplierIds.append(item.get("id") + ",");
                    }
                }
                if (supplierIds.length() > 0)
                    placeOrderQueryDTO.setCitysSupplierIds(supplierIds.toString()
                            .substring(0, supplierIds.toString().length() - 1));
                else
                    placeOrderQueryDTO.setCitysSupplierIds("0");
            }
            // 如果选择了【商家类别】作为查询条件
            if (paramters.get("detailLevel") != null) {
                StringBuilder supplierIds = new StringBuilder();
                List<SupplierInfo> supplier_level = supplierBiz
                        .findSupplierLevelCode(map);
                if (supplier_level != null && supplier_level.size() > 0) {
                    Set<Integer> set = new HashSet<Integer>();
                    for (SupplierInfo item : supplier_level) {
                        supplierIds.append(item.getId() + ",");
                    }
                }
                if (supplierIds.length() > 0)
                placeOrderQueryDTO.setSupplierLevel(supplierIds.toString()
                        .substring(0, supplierIds.toString().length() - 1));
                else
                    placeOrderQueryDTO.setSupplierLevel("0");
            }
            placeOrderQueryResult = commonQuery(placeOrderQueryDTO);
            PageBean pb = placeOrderQueryResult.getPageBean();

            // 总计查询
            if (StringUtils.isNotBlank(placeOrderQueryDTO.getSsl())) {
                Map<String, Object> pm = (Map<String, Object>) pb.getParameter();
                pm.put("parameter", pm);
                Map sum = getCommonService(placeOrderQueryDTO.getSvc()).queryOne(placeOrderQueryDTO.getSsl(), pm);
                placeOrderQueryResult.setSum(sum);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return placeOrderQueryResult;
    }

    @Override
    public PlaceOrderQueryResult sightList(PlaceOrderQueryDTO placeOrderQueryDTO) {
        PlaceOrderQueryResult placeOrderQueryResult = new PlaceOrderQueryResult();
        try {
            //省份
            List<RegionInfo> allProvince = regionBiz.getAllProvince();
            //结算方式
            List<DicInfo> cashTypes = dicBiz.getListByTypeCode(
                    BasicConstants.GYXX_JSFS, placeOrderQueryDTO.getBizId());

            placeOrderQueryResult.setAllProvince(allProvince);
            placeOrderQueryResult.setCashTypes(cashTypes);
        } catch (Exception e) {
            logger.error("", e);
        }
        return placeOrderQueryResult;
    }



    /**
     * 获取查询服务
     *
     * @author Jing.Zhuo
     * @create 2015年8月18日 上午9:34:25
     * @param svc
     * @return
     */
	private CommonSaleBiz getCommonService(String svc) {
		if (StringUtils.isBlank(svc)) {
			svc = "commonSaleBiz";
		}
		return appContext.getBean(svc, CommonSaleBiz.class);
	}

    // 业务查询公共方法
	private PlaceOrderQueryResult commonQuery(PlaceOrderQueryDTO placeOrderQueryDTO) {
           PlaceOrderQueryResult placeOrderQueryResult = new PlaceOrderQueryResult();
			PageBean pb = new PageBean();
			if (placeOrderQueryDTO.getPage() == null) {
				pb.setPage(1);
			} else {

				pb.setPage(placeOrderQueryDTO.getPage());
			}
			if (placeOrderQueryDTO.getPageSize() == null) {
                placeOrderQueryDTO.setPageSize(Constants.PAGESIZE);
			}
			pb.setPageSize(placeOrderQueryDTO.getPageSize());
			Map paramters = placeOrderQueryDTO.getParamters();

			paramters.put("citysSupplierIds",placeOrderQueryDTO.getCitysSupplierIds());
			paramters.put("supplierLevel", placeOrderQueryDTO.getSupplierLevel());
			// paramters.put("supplierDetailLevel",
			// request.getAttribute("supplierDetailLevel"));

			// 如果人员为空并且部门不为空，则取部门下的人id
			if (StringUtils.isBlank((String) paramters.get("saleOperatorIds"))
					&& StringUtils.isNotBlank((String) paramters.get("orgIds"))) {
				Set<Integer> set = new HashSet<Integer>();
				String[] orgIdArr = paramters.get("orgIds").toString().split(",");
				for (String orgIdStr : orgIdArr) {
					set.add(Integer.valueOf(orgIdStr));
				}
				set = platformEmployeeBiz.getUserIdListByOrgIdList(
                        placeOrderQueryDTO.getBizId(), set);
				String salesOperatorIds = "";
				for (Integer usrId : set) {
					salesOperatorIds += usrId + ",";
				}
				if (!salesOperatorIds.equals("")) {
					paramters.put("saleOperatorIds", salesOperatorIds.substring(0,
							salesOperatorIds.length() - 1));
				}
			}
			pb.setParameter(paramters);
			pb = getCommonService(placeOrderQueryDTO.getSvc()).queryListPage(placeOrderQueryDTO.getSl(), pb);
            placeOrderQueryResult.setPageBean(pb);
			return placeOrderQueryResult;
		}
}
