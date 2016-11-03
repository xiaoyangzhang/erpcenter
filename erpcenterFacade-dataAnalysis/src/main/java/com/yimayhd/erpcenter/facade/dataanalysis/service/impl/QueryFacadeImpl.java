package com.yimayhd.erpcenter.facade.dataanalysis.service.impl;

import com.alibaba.fastjson.util.TypeUtils;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.CommonBiz;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupPriceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.*;
import com.yimayhd.erpcenter.biz.sales.client.service.query.QueryBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.biz.sys.service.SysBizBankAccountBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.common.util.NumberUtil;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.InfoBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.DepartmentOrderResult;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.DepartmentOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.ProductGuestStaticsVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.QueryDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GuestInfoStatisticsResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.QueryResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.service.QueryFacade;
import com.yimayhd.erpcenter.facade.sales.result.ToChangeGroupResult;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.dal.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import com.yimayhd.erpcenter.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.util.WebUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/11/1 18:07
 */
public class QueryFacadeImpl implements QueryFacade {
    private static final Logger logger = LoggerFactory.getLogger("QueryFacadeImpl");
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
    private GroupOrderBiz groupOrderBiz;
    @Autowired
    private DicBiz dicBiz;
    @Autowired
    private RegionBiz regionBiz;
    @Autowired
    private PlatformOrgBiz platformOrgBiz;

    @Override
    public QueryResult productTrendTableList(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            // 如果人员为空并且部门不为空，则取部门下的人id
            Set<Integer> set = new HashSet<Integer>();
            if (StringUtils.isBlank(queryDTO.getSaleOperatorIds())
                    && StringUtils.isNotBlank(queryDTO.getOrgIds())) {
                String[] orgIdArr = queryDTO.getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(
                        queryDTO.getBizId(), set);
                String salesOperatorIds = "";
                for (Integer usrId : set) {
                    salesOperatorIds += usrId + ",";
                }
                if (!salesOperatorIds.equals("")) {
                    queryDTO.setSaleOperatorIds(salesOperatorIds.substring(0,
                            salesOperatorIds.length() - 1));
                }
            }

            Map<String, Object> parameters = queryDTO.getParameters();
            if (null != queryDTO.getSaleOperatorIds() && !"".equals(queryDTO.getSaleOperatorIds())) {
                parameters.put("orgIds", queryDTO.getSaleOperatorIds());
            }
            parameters.put("set", queryDTO.getUserIdSet());
            // parameters.put("orgIds", groupOrder.getOrgIds());
            // parameters.put("saleOperatorIds", groupOrder.getSaleOperatorIds());
            // Map parameters = new HashMap();
            // parameters.put("bizId", bizId);
            // parameters.put("productBrandId", productBrandId);
            // parameters.put("startTime", startTime);
            // parameters.put("endTime", endTime);
            // parameters.put("groupMode", groupMode);

            // parameters.put("productName", groupOrder.getProductName());
            // parameters.put("orgId", WebUtils.getCurUser(request).getOrgId());
            // parameters.put("set", WebUtils.getDataUserIdSet(request));

            // ===================InnerLayer=============================================
		/*
		 * PageBean pageBeanInnerLayer = new PageBean(); if (pageSize == null) {
		 * pageBeanInnerLayer.setPageSize(Constants.PAGESIZE); } else {
		 * pageBeanInnerLayer.setPageSize(pageSize); }
		 */
            // pageBeanInnerLayer.setParameter(groupOrder);
            // pageBeanInnerLayer.setPage(page);
            PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();
            pageBean.setPage(1);
            pageBean.setPageSize(1000000);

            List<GroupOrder> pbInnerLayer = groupOrderBiz
                    .selectProductTrendListPageInnerLayer(pageBean, parameters);
            // List<GroupOrder> goListInnerLayer = pbInnerLayer.getResult();
            for (GroupOrder go : pbInnerLayer) {
                System.out.println("----1---" + go.getProductBrandName()
                        + "--------" + go.getProductName() + ","
                        + go.getDepartureDate() + "," + go.getPerson());
            }
            // =====================InnerLayer===========================================

            // =====================OutLayer===========================================
            PageBean pageBeanOutLayer = new PageBean();
            if (queryDTO.getPage() == null) {
                queryDTO.setPage(1);
            }
            if (queryDTO.getPageSize() == null) {
                pageBeanOutLayer.setPageSize(Constants.PAGESIZE);
            } else {
                pageBeanOutLayer.setPageSize(queryDTO.getPageSize());
            }
            pageBeanOutLayer.setParameter(queryDTO.getGroupOrder().getGroupMode());
            pageBeanOutLayer.setPage(queryDTO.getPage());

            PageBean<GroupOrder> pbOutLayer = groupOrderBiz
                    .selectProductTrendListPageOutLayer(pageBeanOutLayer,
                            parameters);
            List<GroupOrder> goListOutLayer = pbOutLayer.getResult();

            for (GroupOrder gg : goListOutLayer) {
                // List<Map<String, Object>> listMap = new
                // ArrayList<Map<String,Object>>();
                // System.out.println(gg.getProductBrandName()+","+gg.getProductName()+","+gg.getDepartureDate()+","+gg.getNumAdult()+","+gg.getNumChild());

                for (GroupOrder go : pbInnerLayer) {
                    // System.out.println("-------"+go.getProductBrandName()+"--------"+go.getProductName()+","+go.getDepartureDate()+","+go.getNumAdult()+","+go.getNumChild()+","+go.getPerson());
                    if (go.getProductBrandName().equals(gg.getProductBrandName())
                            && go.getProductName().equals(gg.getProductName())) {
                        String firstDate = go.getDepartureDate().substring(8, 10);
                        if ("01".equals(firstDate)) {
                            gg.setDay1(go.getPerson());
                        }
                        if ("02".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay2(go.getPerson());
                        }
                        if ("03".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay3(go.getPerson());
                        }
                        if ("04".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay4(go.getPerson());
                        }
                        if ("05".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay5(go.getPerson());
                        }
                        if ("06".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay6(go.getPerson());
                        }
                        if ("07".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay7(go.getPerson());
                        }
                        if ("08".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay8(go.getPerson());
                        }
                        if ("09".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay9(go.getPerson());
                        }
                        if ("10".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay10(go.getPerson());
                        }
                        if ("11".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay11(go.getPerson());
                        }
                        if ("12".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay12(go.getPerson());
                        }
                        if ("13".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay13(go.getPerson());
                        }
                        if ("14".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay14(go.getPerson());
                        }
                        if ("15".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay15(go.getPerson());
                        }
                        if ("16".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay16(go.getPerson());
                        }
                        if ("17".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay17(go.getPerson());
                        }
                        if ("18".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay18(go.getPerson());
                        }
                        if ("19".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay19(go.getPerson());
                        }
                        if ("20".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay20(go.getPerson());
                        }
                        if ("21".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay21(go.getPerson());
                        }
                        if ("22".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay22(go.getPerson());
                        }
                        if ("23".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay23(go.getPerson());
                        }
                        if ("24".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay24(go.getPerson());
                        }
                        if ("25".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay25(go.getPerson());
                        }
                        if ("26".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay26(go.getPerson());
                        }
                        if ("27".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay27(go.getPerson());
                        }
                        if ("28".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay28(go.getPerson());
                        }
                        if ("29".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay29(go.getPerson());
                        }
                        if ("30".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay30(go.getPerson());
                        }
                        if ("31".equals(go.getDepartureDate().substring(8, 10))) {
                            gg.setDay31(go.getPerson());
                        }
                    }
                }
            }
            queryResult.setPageBean(pbOutLayer);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult productTrendList(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            // 获取产品品牌信息
            List<DicInfo> brandList = dicBiz.getListByTypeCode(
                    BasicConstants.CPXL_PP, queryDTO.getBizId());
            queryResult.setDicInfoList(brandList);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult receivePersonCountTable(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            Map parameters = queryDTO.getParameters();
            if (StringUtils.isBlank(queryDTO.getSaleOperatorIds())
                    && StringUtils.isNotBlank(queryDTO.getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = queryDTO.getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(
                        queryDTO.getBizId(), set);
                String OperatorIds = "";
                for (Integer usrId : set) {
                    OperatorIds += usrId + ",";
                }
                if (!OperatorIds.equals("")) {
                    parameters.put("saleOperatorIds",
                            OperatorIds.substring(0, OperatorIds.length() - 1));
                }
            }
            Map<String, Object> personMap = queryBiz
                    .getReceivePersonStatistics(parameters);
            queryResult.setPersonMap(personMap);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult departmentOrderList(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            Integer bizId = queryDTO.getBizId();
            List<PlatformOrgPo> secLevelOrgList = null;
            String staticsOrgIds = queryDTO.getStaticsOrgIds();
            if (staticsOrgIds != null) {
                String[] orgIdArr = staticsOrgIds.split(",");
                Set<Integer> sets = new HashSet<Integer>();
                for (String orgIdStr : orgIdArr) {
                    sets.add(TypeUtils.castToInt(orgIdStr));
                }
                // 获取商户二级部门的集合
                secLevelOrgList = platformOrgBiz.getOrgListByIdSet(bizId, sets);
            } else {
                // 获取商户二级部门的集合
                secLevelOrgList = platformOrgBiz.getSecLevelOrgList(bizId);
            }
            if (StringUtils.isBlank(queryDTO.getCondition().getOperatorIds())
                    && StringUtils.isNotBlank(queryDTO.getCondition().getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = queryDTO.getCondition().getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(
                        bizId, set);
                String OperatorIds = "";
                for (Integer usrId : set) {
                    OperatorIds += usrId + ",";
                }
                if (!OperatorIds.equals("")) {
                    queryDTO.getCondition().setOperatorIds(OperatorIds.substring(0,
                            OperatorIds.length() - 1));
                }
            }
            queryResult.setSecLevelOrgList(secLevelOrgList);
            List<Integer> orgIdList = new ArrayList<Integer>();
            for (PlatformOrgPo org : secLevelOrgList) {
                orgIdList.add(org.getOrgId());
            }
            // 获取当前商户三级部门的集合
            List<PlatformOrgPo> subLevelOrgList = platformOrgBiz.getSubLevelOrgList(
                    bizId, orgIdList);
            queryResult.setSubLevelOrgList(subLevelOrgList);
            Map<Integer, List<PlatformOrgPo>> orgDepMap = new HashMap<Integer, List<PlatformOrgPo>>();
            for (PlatformOrgPo org : subLevelOrgList) {
                List<PlatformOrgPo> depList = null;
                if (orgDepMap.containsKey(org.getParentId())) {
                    depList = orgDepMap.get(org.getParentId());
                } else {
                    depList = new ArrayList<PlatformOrgPo>();
                }
                depList.add(org);
                orgDepMap.put(org.getParentId(), depList);
            }
            queryResult.setOrgDepMap(orgDepMap);
            // 获取当前商家的用户集合
            List<PlatformEmployeePo> employeeList = platformEmployeeBiz
                    .getEmployeeListByName(bizId, null);
            // 存储用户及其所属的部门
            Map<Integer, Integer> empOrgMap = new HashMap<Integer, Integer>();
            for (PlatformEmployeePo employee : employeeList) {
                empOrgMap.put(employee.getEmployeeId(), employee.getOrgId());
            }
            // 获取各用户的订单统计信息
            queryDTO.getCondition().setBizId(bizId);
            if (queryDTO.getCondition().getDateType() == 0) {
                queryDTO.getCondition().setStartTimeNum(queryDTO.getCondition().getStartTime().getTime());
                queryDTO.getCondition().setEndTimeNum(queryDTO.getCondition().getEndTime().getTime());
            }
            List<DepartmentOrderResult> orderList = groupOrderBiz
                    .getDepartmentOrderStatistics(queryDTO.getCondition(),
                            queryDTO.getUserIdSet());
            // 存储部门和其对应的订单信息
            // List<Map<Integer, Integer>> orgOrderMapList=new
            // ArrayList<Map<Integer, Integer>>();
            // Map<Integer, DepartmentOrderVO> orgOrderMap = new HashMap<Integer,
            // DepartmentOrderVO>();

            List<String> dateList = new ArrayList<String>();
            Map<Date, Integer> dateMap = new HashMap<Date, Integer>();
            for (int i = 0; i < 7; i++) {
                dateList.add(DateUtils.format(
                        DateUtils.addDay(
                                queryDTO.getCondition().getStartTime(), i), "yyyy-MM-dd"));
                dateMap.put(
                       DateUtils.addDay(
                               queryDTO.getCondition().getStartTime(), i), i);
            }

            Map<Integer, DepartmentOrderVO> deptOrderVoMap = new HashMap<Integer, DepartmentOrderVO>();
            DepartmentOrderVO vo = null;
            for (DepartmentOrderResult order : orderList) {
                if (!empOrgMap.containsKey(order.getOperatorId())) {
                    continue;
                }
                Integer orgId = empOrgMap.get(order.getOperatorId());
                if (deptOrderVoMap.containsKey(orgId)) {
                    vo = deptOrderVoMap.get(orgId);
                } else {
                    vo = new DepartmentOrderVO();
                }
                int idx = 0;
                if (queryDTO.getCondition().getDateType() == 0) {
                    idx = dateMap.get(order.getCreateDate());
                } else {
                    idx = dateMap.get(order.getDepartureDate());
                }
                switch (idx) {
                    case 0:
                        vo.setDay1NumAdult(zeroIfNull(vo.getDay1NumAdult())
                                + zeroIfNull(order.getNumAdult()));
                        vo.setDay1NumChild(zeroIfNull(vo.getDay1NumChild())
                                + zeroIfNull(order.getNumChild()));
                        break;
                    case 1:
                        vo.setDay2NumAdult(zeroIfNull(vo.getDay2NumAdult())
                                + zeroIfNull(order.getNumAdult()));
                        vo.setDay2NumChild(zeroIfNull(vo.getDay2NumChild())
                                + zeroIfNull(order.getNumChild()));
                        break;
                    case 2:
                        vo.setDay3NumAdult(zeroIfNull(vo.getDay3NumAdult())
                                + zeroIfNull(order.getNumAdult()));
                        vo.setDay3NumChild(zeroIfNull(vo.getDay3NumChild())
                                + zeroIfNull(order.getNumChild()));
                        break;
                    case 3:
                        vo.setDay4NumAdult(zeroIfNull(vo.getDay4NumAdult())
                                + zeroIfNull(order.getNumAdult()));
                        vo.setDay4NumChild(zeroIfNull(vo.getDay4NumChild())
                                + zeroIfNull(order.getNumChild()));
                        break;
                    case 4:
                        vo.setDay5NumAdult(zeroIfNull(vo.getDay5NumAdult())
                                + zeroIfNull(order.getNumAdult()));
                        vo.setDay5NumChild(zeroIfNull(vo.getDay5NumChild())
                                + zeroIfNull(order.getNumChild()));
                        break;
                    case 5:
                        vo.setDay6NumAdult(zeroIfNull(vo.getDay6NumAdult())
                                + zeroIfNull(order.getNumAdult()));
                        vo.setDay6NumChild(zeroIfNull(vo.getDay6NumChild())
                                + zeroIfNull(order.getNumChild()));
                        break;
                    case 6:
                        vo.setDay7NumAdult(zeroIfNull(vo.getDay7NumAdult())
                                + zeroIfNull(order.getNumAdult()));
                        vo.setDay7NumChild(zeroIfNull(vo.getDay7NumChild())
                                + zeroIfNull(order.getNumChild()));
                        break;
                    default:
                        break;
                }
                if (order.getAffirmOrderCount() != null) {
                    vo.setAffirmOrderCount(zeroIfNull(vo.getAffirmOrderCount())
                            + zeroIfNull(order.getAffirmOrderCount()));
                }
                if (order.getReserveOrderCount() != null) {
                    vo.setReserveOrderCount(zeroIfNull(vo.getReserveOrderCount())
                            + zeroIfNull(order.getReserveOrderCount()));
                }
                deptOrderVoMap.put(orgId, vo);
            }
            queryResult.setDeptOrderVoMap(deptOrderVoMap);
            queryResult.setDateList(dateList);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult groupProfitExportExcel(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            PageBean pb = new PageBean();
            pb.setPage(1);
            pb.setPageSize(100000);
            // 如果人员为空并且部门不为空，则取部门下的人id
            if (StringUtils.isBlank(queryDTO.getGroup().getSaleOperatorIds()) && StringUtils.isNotBlank(queryDTO.getGroup().getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = queryDTO.getGroup().getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                //	set = platformEmployeeService.getUserIdListByOrgIdList(WebUtils.getCurBizId(request), set);
                set = queryResult.getUserIdSet();
                String salesOperatorIds = "";
                for (Integer usrId : set) {
                    salesOperatorIds += usrId + ",";
                }
                if (!salesOperatorIds.equals("")) {
                    queryDTO.getGroup().setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
                }
            }
            Map<String, Object> pms = queryDTO.getParameters();
            if (null != queryDTO.getGroup().getSaleOperatorIds() && !"".equals(queryDTO.getGroup().getSaleOperatorIds())) {
                pms.put("operator_id", queryDTO.getGroup().getSaleOperatorIds());
            }
            // pms.put("set", WebUtils.getDataUserIdSet(request));
            pb.setParameter(pms);
            pb = getCommonService(queryDTO.getSvc()).queryListPage(queryDTO.getSl(), pb);
            // model.addAttribute("pageBean", pb);

            Map<Integer, String> guideMap = new HashMap<Integer, String>();
            List<Map> results = pb.getResult();
            Map item = null;
            for (int i = 0; i < results.size(); i++) {
                item = results.get(i);
                Integer groupId = Integer.parseInt(item.get("id").toString());
                List<BookingGuide> bookingGuides = bookingGuideBiz.selectGuidesByGroupId(groupId);
                StringBuffer s = new StringBuffer();
                for (int j = 0; j < bookingGuides.size(); j++) {
                    if (j == (bookingGuides.size() - 1)) {
                        s.append(bookingGuides.get(j).getGuideName());
                    } else {
                        s.append(bookingGuides.get(j).getGuideName() + ",");
                    }
                }
                guideMap.put(groupId, s.toString());

                BigDecimal totalIncome = NumberUtil.parseObj2Num(item.get("total_income"));
                BigDecimal totalCost = NumberUtil.parseObj2Num(item.get("total_cost"));

                // 团收入 = 团收入 - 购物汇总
                InfoBean shop = financeBiz.statsShopWithCommInfoBean(groupId);
                totalIncome = totalIncome.subtract(shop.getNum());

                item.put("total_income", totalIncome);
                item.put("total_profit", totalIncome.subtract(totalCost));
                item.put("total_cost", totalCost);

            }
            queryResult.setPageBean(pb);
            queryResult.setGuideMap(guideMap);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult settleListPage(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            PageBean pb = new PageBean();
            pb.setPage(queryDTO.getPage());
            if (queryDTO.getPageSize() == null) {
                queryDTO.setPageSize(Constants.PAGESIZE);
            }
            pb.setPageSize(queryDTO.getPageSize());
            // 如果人员为空并且部门不为空，则取部门下的人id
            if (StringUtils.isBlank(queryDTO.getGroup().getSaleOperatorIds())
                    && StringUtils.isNotBlank(queryDTO.getGroup().getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = queryDTO.getGroup().getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(
                        queryDTO.getBizId(), set);
                String salesOperatorIds = "";
                for (Integer usrId : set) {
                    salesOperatorIds += usrId + ",";
                }
                if (!salesOperatorIds.equals("")) {
                    queryDTO.getGroup().setSaleOperatorIds(salesOperatorIds.substring(0,
                            salesOperatorIds.length() - 1));
                }
            }
            Map<String, Object> pms = queryDTO.getParameters();
            if (null != queryDTO.getGroup().getSaleOperatorIds()
                    && !"".equals(queryDTO.getGroup().getSaleOperatorIds())) {
                pms.put("operator_id", queryDTO.getGroup().getSaleOperatorIds());
            }
            // pms.put("set", WebUtils.getDataUserIdSet(request));
            pb.setParameter(pms);
            pb = getCommonService(queryDTO.getSvc()).queryListPage(queryDTO.getSl(), pb);
            queryResult.setPageBean(pb);

            Map<Integer, String> guideMap = new HashMap<Integer, String>();
            List<Map> results = pb.getResult();
            Map item = null;
            for (int i = 0; i < results.size(); i++) {
                item = results.get(i);
                Integer groupId = Integer.parseInt(item.get("id").toString());
                List<BookingGuide> bookingGuides = bookingGuideBiz
                        .selectGuidesByGroupId(groupId);
                StringBuffer s = new StringBuffer();
                for (int j = 0; j < bookingGuides.size(); j++) {
                    if (j == (bookingGuides.size() - 1)) {
                        s.append(bookingGuides.get(j).getGuideName());
                    } else {
                        s.append(bookingGuides.get(j).getGuideName() + ",");
                    }
                }
                guideMap.put(groupId, s.toString());

                BigDecimal totalIncome = NumberUtil.parseObj2Num(item
                        .get("total_income"));
                BigDecimal totalCost = NumberUtil.parseObj2Num(item
                        .get("total_cost"));

                // 团收入 = 团收入 - 购物汇总
                InfoBean shop = financeBiz.statsShopWithCommInfoBean(groupId);
                totalIncome = totalIncome.subtract(shop.getNum());

                item.put("total_income", totalIncome);
                item.put("total_profit", totalIncome.subtract(totalCost));

            }
            queryResult.setGuideMap(guideMap);

            // 总计查询
            if (StringUtils.isNotBlank(queryDTO.getSsl())) {
                Map pm = (Map) pb.getParameter();
                pm.put("parameter", pm);
                getCommonService(queryDTO.getSvc()).queryOne(queryDTO.getSsl(), pm);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult groupProfitPreview(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            PageBean pb = new PageBean();
            pb.setPage(1);
            pb.setPageSize(100000);
            // 如果人员为空并且部门不为空，则取部门下的人id
            if (StringUtils.isBlank(queryDTO.getGroup().getSaleOperatorIds())
                    && StringUtils.isNotBlank(queryDTO.getGroup().getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = queryDTO.getGroup().getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(
                        queryDTO.getBizId(), set);
                String salesOperatorIds = "";
                for (Integer usrId : set) {
                    salesOperatorIds += usrId + ",";
                }
                if (!salesOperatorIds.equals("")) {
                    queryDTO.getGroup().setSaleOperatorIds(salesOperatorIds.substring(0,
                            salesOperatorIds.length() - 1));
                }
            }
            Map<String, Object> pms = queryDTO.getParameters();
            if (null != queryDTO.getGroup().getSaleOperatorIds()
                    && !"".equals(queryDTO.getGroup().getSaleOperatorIds())) {
                pms.put("operator_id", queryDTO.getGroup().getSaleOperatorIds());
            }
            // pms.put("set", WebUtils.getDataUserIdSet(request));
            pb.setParameter(pms);
            pb = getCommonService(queryDTO.getSvc()).queryListPage(queryDTO.getSl(), pb);
            queryResult.setPageBean(pb);

            Map<Integer, String> guideMap = new HashMap<Integer, String>();
            List<Map> results = pb.getResult();
            Map item = null;
            for (int i = 0; i < results.size(); i++) {
                item = results.get(i);
                Integer groupId = Integer.parseInt(item.get("id").toString());
                List<BookingGuide> bookingGuides = bookingGuideBiz
                        .selectGuidesByGroupId(groupId);
                StringBuffer s = new StringBuffer();
                for (int j = 0; j < bookingGuides.size(); j++) {
                    if (j == (bookingGuides.size() - 1)) {
                        s.append(bookingGuides.get(j).getGuideName());
                    } else {
                        s.append(bookingGuides.get(j).getGuideName() + ",");
                    }
                }
                guideMap.put(groupId, s.toString());

                BigDecimal totalIncome = NumberUtil.parseObj2Num(item
                        .get("total_income"));
                BigDecimal totalCost = NumberUtil.parseObj2Num(item
                        .get("total_cost"));

                // 团收入 = 团收入 - 购物汇总
                InfoBean shop = financeBiz.statsShopWithCommInfoBean(groupId);
                totalIncome = totalIncome.subtract(shop.getNum());

                item.put("total_income", totalIncome);
                item.put("total_cost", totalCost);
                item.put("total_profit", totalIncome.subtract(totalCost));
            }
            queryResult.setGuideMap(guideMap);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult settleList() {
        QueryResult queryResult = new QueryResult();
        try {
            List<TourGroup> auditorList = tourGroupBiz.getAuditorList();
            queryResult.setTourGroupList(auditorList);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public GuestInfoStatisticsResult guestInfoStatistics(QueryDTO queryDTO) {
        GuestInfoStatisticsResult guestInfoStatisticsResult = new GuestInfoStatisticsResult();
        try {
            Map parameters = queryDTO.getParameters();
            if (StringUtils.isBlank(queryDTO.getGroupOrder().getSaleOperatorIds())
                    && StringUtils.isNotBlank(queryDTO.getGroupOrder().getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = queryDTO.getGroupOrder().getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(
                        queryDTO.getBizId(), set);
                String salesOperatorIds = "";
                for (Integer usrId : set) {
                    salesOperatorIds += usrId + ",";
                }
                if (!salesOperatorIds.equals("")) {
                    parameters.put("saleOperatorIds", salesOperatorIds.substring(0,
                            salesOperatorIds.length() - 1));
                }
            }

            queryDTO.getGroupOrder().setBizId(queryDTO.getBizId());
            // 如果选择日期类型为输单日期，将日期转换为毫秒值
            if (queryDTO.getGroupOrder().getDateType() != null && queryDTO.getGroupOrder().getDateType() == 1) {
                if (queryDTO.getGroupOrder().getStartTime() != null) {
                    // groupOrder.setStartTime(new SimpleDateFormat("yyyy-MM-dd")
                    // .parse(groupOrder.getStartTime()).getTime() + "");
                    parameters.put("startTime", new SimpleDateFormat("yyyy-MM-dd")
                            .parse(queryDTO.getGroupOrder().getStartTime()).getTime() + "");
                }
                if (queryDTO.getGroupOrder().getEndTime() != null) {
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(new SimpleDateFormat("yyyy-MM-dd")
                            .parse(queryDTO.getGroupOrder().getEndTime()));
                    calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
                    // groupOrder.setEndTime(calendar.getTime().getTime() + "");
                    parameters.put("endTime", calendar.getTime().getTime() + "");
                }

            }

            // parameters.put("set", WebUtils.getDataUserIdSet(request));
            // parameters.put("bizId", WebUtils.getCurBizId(request));
            String jsonSexStr = queryBiz.getGuestSexInfo(parameters);
            guestInfoStatisticsResult.setJsonSexStr(jsonSexStr);
            String jsonAgeStr = queryBiz.getGuestAgeInfo(parameters);
            guestInfoStatisticsResult.setJsonAgeStr(jsonAgeStr);
            String jsonAirStr = queryBiz.getGuestAirTimeInfo(parameters);
            guestInfoStatisticsResult.setJsonAirStr(jsonAirStr);
            String jsonSourceStr = queryBiz.getGuestSouceInfo(parameters);
            guestInfoStatisticsResult.setJsonSourceStr(jsonSourceStr);
        } catch (Exception e) {
            logger.error("", e);
        }
        return guestInfoStatisticsResult;
    }

    @Override
    public QueryResult supplierGuestSourceShop(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            if (StringUtils.isBlank(queryDTO.getGroupOrder().getSaleOperatorIds())
                    && StringUtils.isNotBlank(queryDTO.getGroupOrder().getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = queryDTO.getGroupOrder().getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(
                        queryDTO.getBizId(), set);
                String OperatorIds = "";
                for (Integer usrId : set) {
                    OperatorIds += usrId + ",";
                }
                if (!OperatorIds.equals("")) {
                    queryDTO.getGroupOrder().setSaleOperatorIds(OperatorIds.substring(0,
                            OperatorIds.length() - 1));
                }
            }
            PageBean pageBean = new PageBean();
            if (queryDTO.getGroupOrder().getPage() == null) {
                queryDTO.getGroupOrder().setPage(1);
            }
            if (queryDTO.getGroupOrder().getPageSize() == null) {
                queryDTO.getGroupOrder().setPageSize(Constants.PAGESIZE);
            }
            queryDTO.getGroupOrder().setBizId(queryDTO.getBizId());
            pageBean.setPage(queryDTO.getGroupOrder().getPage());
            pageBean.setPageSize(queryDTO.getGroupOrder().getPageSize());
            pageBean.setParameter(queryDTO.getGroupOrder());

            queryResult.setPageBean(queryBiz.selectSupplierGuestShopStatics(pageBean,
                    queryDTO.getUserIdSet()));
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult supplierGuestSourceShopList() {
        QueryResult queryResult = new QueryResult();
        try {
            List<RegionInfo> allProvince = regionBiz.getAllProvince();
            queryResult.setRegionInfoList(allProvince);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult groupDateQueryData(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            PageBean<TourGroup> pageBean = new PageBean<TourGroup>();
            if (queryDTO.getPage() == null) {
                queryDTO.getTourGroup().setPage(1);
            }
            pageBean.setPage(queryDTO.getTourGroup().getPage());

            if (queryDTO.getTourGroup().getPageSize() == null
                    || queryDTO.getTourGroup().getPageSize().equals("")) {
                queryDTO.getTourGroup().setPageSize(Constants.PAGESIZE);
            }
            pageBean.setPageSize(queryDTO.getTourGroup().getPageSize());

            if (StringUtils.isBlank(queryDTO.getTourGroup().getOperatorIds())
                    && StringUtils.isNotBlank(queryDTO.getTourGroup().getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = queryDTO.getTourGroup().getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(
                        queryDTO.getBizId(), set);
                String OperatorIds = "";
                for (Integer usrId : set) {
                    OperatorIds += usrId + ",";
                }
                if (!OperatorIds.equals("")) {
                    queryDTO.getTourGroup().setOperatorIds(OperatorIds.substring(0,
                            OperatorIds.length() - 1));
                }
            }
            pageBean.setParameter(queryDTO.getTourGroup());
            pageBean = tourGroupBiz.selectTourGroupToQueryListPage(pageBean,
                    queryDTO.getBizId(),
                    queryDTO.getUserIdSet());
            List<TourGroup> result = pageBean.getResult();
            if (result != null && result.size() > 0) {
                for (TourGroup tg : result) {
                    List<BookingGuide> guideList = bookingGuideBiz
                            .selectGuidesByGroupId(tg.getId());
                    tg.setGuideList(guideList);
                }
            }
            TourGroup tg = tourGroupBiz.selectTourGroupToQueryCon(queryDTO.getTourGroup(),
                    queryDTO.getBizId(),
                    queryDTO.getUserIdSet());
            queryResult.setPageBean(pageBean);
            queryResult.setTourGroup(tg);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult deservedCashTable(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();
            if (queryDTO.getGroupOrder().getPage() == null) {
                queryDTO.getGroupOrder().setPage(1);
            }
            pageBean.setPage(queryDTO.getGroupOrder().getPage());

            if (queryDTO.getGroupOrder().getPageSize() == null) {
                queryDTO.getGroupOrder().setPageSize(Constants.PAGESIZE);
            }
            pageBean.setPageSize(queryDTO.getGroupOrder().getPageSize());

            if (StringUtils.isBlank(queryDTO.getGroupOrder().getSaleOperatorIds())
                    && StringUtils.isNotBlank(queryDTO.getGroupOrder().getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = queryDTO.getGroupOrder().getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(
                        queryDTO.getBizId(), set);
                String salesOperatorIds = "";
                for (Integer usrId : set) {
                    salesOperatorIds += usrId + ",";
                }
                if (!salesOperatorIds.equals("")) {
                    queryDTO.getGroupOrder().setSaleOperatorIds(salesOperatorIds.substring(0,
                            salesOperatorIds.length() - 1));
                }
            }
            queryDTO.getGroupOrder().setBizId(queryDTO.getBizId());
            if (queryDTO.getGroupOrder().getDateType() == 1
                    && StringUtils.isNotBlank(queryDTO.getGroupOrder().getEndTime())) {
                String endTime = DateFormatUtils
                        .format(DateUtils.addDay(
                                new SimpleDateFormat("yyyy-MM-dd").parse(queryDTO.getGroupOrder()
                                        .getEndTime()), 1), "yyyy-MM-dd");
                queryDTO.getGroupOrder().setEndTime(endTime);
            }
            pageBean.setParameter(queryDTO.getGroupOrder());
            Map<String, Object> sum = groupOrderBiz
                    .getDeservedCashGroupByOrderIdTotal(pageBean,
                            queryDTO.getUserIdSet());
            pageBean = groupOrderBiz.getDeservedCashGroupByOrderId(pageBean,
                    queryDTO.getUserIdSet());
		/*
		 * List<GroupOrder> result = pageBean.getResult(); Map parameters=new
		 * HashMap(); if(result!=null && result.size()>0){ for (int i = 0; i <
		 * result.size(); i++) { Integer supplierId =
		 * result.get(i).getSupplierId(); parameters.put("supplierId",
		 * supplierId); List<GroupOrder> orders =
		 * groupOrderService.selectOrderByParameter2(parameters);
		 * result.get(i).setOrderList(orders);
		 * result.get(i).setRowSpan(orders.size()); } }
		 */
            queryResult.setPageBean(pageBean);
            queryResult.setPersonMap(sum);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult deservedCashList(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            List<DicInfo> lysfxmList = dicBiz.getListByTypeCode(
                    BasicConstants.GYXX_LYSFXM, queryDTO.getBizId());
            queryResult.setDicInfoList(lysfxmList);
            List<RegionInfo> allProvince = regionBiz.getAllProvince();
            queryResult.setRegionInfoList(allProvince);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult productGuestNumTable(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();
            if (queryDTO.getGroupOrder().getPage() == null) {
                queryDTO.getGroupOrder().setPage(1);
            }
            pageBean.setPage(queryDTO.getGroupOrder().getPage());

            if (queryDTO.getGroupOrder().getPageSize() == null) {
                queryDTO.getGroupOrder().setPageSize(Constants.PAGESIZE);
            }
            pageBean.setPageSize(queryDTO.getGroupOrder().getPageSize());

            if (StringUtils.isBlank(queryDTO.getGroupOrder().getSaleOperatorIds())
                    && StringUtils.isNotBlank(queryDTO.getGroupOrder().getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = queryDTO.getGroupOrder().getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(
                        queryDTO.getBizId(), set);
                String salesOperatorIds = "";
                for (Integer usrId : set) {
                    salesOperatorIds += usrId + ",";
                }
                if (!salesOperatorIds.equals("")) {
                    queryDTO.getGroupOrder().setSaleOperatorIds(salesOperatorIds.substring(0,
                            salesOperatorIds.length() - 1));
                }
            }
            pageBean.setParameter(queryDTO.getGroupOrder());
            pageBean = groupOrderBiz.selectOrderByParameterListPage(pageBean,
                    queryDTO.getBizId(),
                    queryDTO.getUserIdSet());
            List<GroupOrder> result = pageBean.getResult();
            Map parameters = queryDTO.getParameters();
            if (result != null && result.size() > 0) {
                for (int i = 0; i < result.size(); i++) {
                    Integer supplierId = result.get(i).getSupplierId();
                    parameters.put("supplierId", supplierId);
                    // parameters.put("set", WebUtils.getDataUserIdSet(request));
                    List<GroupOrder> orders = groupOrderBiz
                            .selectOrderByParameter2(parameters);
                    result.get(i).setOrderList(orders);
                    result.get(i).setRowSpan(orders.size());
                }
            }
            queryResult.setPageBean(pageBean);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult exportExcel7(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {

            if (StringUtils.isBlank(queryDTO.getProductGuestCondition().getOperatorIds()) && StringUtils.isNotBlank(queryDTO.getProductGuestCondition().getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = queryDTO.getProductGuestCondition().getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
                String salesOperatorIds = "";
                for (Integer usrId : set) {
                    salesOperatorIds += usrId + ",";
                }
                if (!salesOperatorIds.equals("")) {
                    queryDTO.getProductGuestCondition().setOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
                }
            }
            if (queryDTO.getProductGuestCondition().getStartDate() != null) {
                queryDTO.getProductGuestCondition().setStartDateNum(queryDTO.getProductGuestCondition().getStartDate().getTime());
            }
            if (queryDTO.getProductGuestCondition().getEndDate() != null) {
                // 添加时间时，结束时间需要加一天
                queryDTO.getProductGuestCondition().setEndDateNum(DateUtils.addDay(queryDTO.getProductGuestCondition().getEndDate(), 1).getTime());
            }
            queryDTO.getProductGuestCondition().setBizId(queryDTO.getBizId());
            List<Map<String, Object>> guestSourceStatics = queryBiz.guestSourceStatics2(queryDTO.getProductGuestCondition(),
                    queryDTO.getUserIdSet());


          //  List<Map<String, Object>> guestSourceStatics = queryBiz
           //         .guestSourceStatics2(queryDTO.getProductGuestCondition(),
           //                 queryDTO.getUserIdSet());
           queryResult.setListMap(guestSourceStatics);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult productSourcePreview(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            if (StringUtils.isBlank(queryDTO.getProductGuestCondition().getOperatorIds())
                    && StringUtils.isNotBlank(queryDTO.getProductGuestCondition().getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = queryDTO.getProductGuestCondition().getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(
                        queryDTO.getBizId(), set);
                String salesOperatorIds = "";
                for (Integer usrId : set) {
                    salesOperatorIds += usrId + ",";
                }
                if (!salesOperatorIds.equals("")) {
                    queryDTO.getProductGuestCondition().setOperatorIds(salesOperatorIds.substring(0,
                            salesOperatorIds.length() - 1));
                }
            }
            if (queryDTO.getProductGuestCondition().getStartDate() != null) {
                queryDTO.getProductGuestCondition().setStartDateNum(queryDTO.getProductGuestCondition().getStartDate().getTime());
            }
            if (queryDTO.getProductGuestCondition().getEndDate() != null) {
                // 添加时间时，结束时间需要加一天
                queryDTO.getProductGuestCondition().setEndDateNum(DateUtils.addDay(
                        queryDTO.getProductGuestCondition().getEndDate(), 1).getTime());
            }
            queryDTO.getProductGuestCondition().setBizId(queryDTO.getBizId());
            List<Map<String, Object>> guestSourceStatics = queryBiz
                    .guestSourceStatics2(queryDTO.getProductGuestCondition(),
                            queryDTO.getUserIdSet());
            queryResult.setListMap(guestSourceStatics);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult exportExcel6(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            if (StringUtils.isBlank(queryDTO.getProductGuestCondition().getOperatorIds()) && StringUtils.isNotBlank(queryDTO.getProductGuestCondition().getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = queryDTO.getProductGuestCondition().getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
                String salesOperatorIds = "";
                for (Integer usrId : set) {
                    salesOperatorIds += usrId + ",";
                }
                if (!salesOperatorIds.equals("")) {
                    queryDTO.getProductGuestCondition().setOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
                }
            }
            if (queryDTO.getProductGuestCondition().getStartDate() != null) {
                queryDTO.getProductGuestCondition().setStartDateNum(queryDTO.getProductGuestCondition().getStartDate().getTime());
            }
            if (queryDTO.getProductGuestCondition().getEndDate() != null) {
                queryDTO.getProductGuestCondition().setEndDateNum(queryDTO.getProductGuestCondition().getEndDate().getTime());
            }
            queryDTO.getProductGuestCondition().setBizId(queryDTO.getBizId());
            List<ProductGuestStaticsVo> productGuestStatics = queryBiz.productGuestStatics2(queryDTO.getProductGuestCondition(),
                    queryDTO.getUserIdSet());

            queryResult.setProductGuestStatics(productGuestStatics);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult productGuestSourceShoppingStatics(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            if(StringUtils.isBlank(queryDTO.getProductGuestShoppingCondition().getOperatorIds()) && StringUtils.isNotBlank(queryDTO.getProductGuestShoppingCondition().getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = queryDTO.getProductGuestShoppingCondition().getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
                String operatorIds = "";
                for (Integer usrId : set) {
                    operatorIds += usrId + ",";
                }
                if (!operatorIds.equals("")) {
                    queryDTO.getProductGuestShoppingCondition().setOperatorIds(operatorIds.substring(0, operatorIds.length() - 1));
                }
            }
            PageBean pageBean = new PageBean();
            if (queryDTO.getProductGuestShoppingCondition().getPage() == null) {
                queryDTO.getProductGuestShoppingCondition().setPage(1);
            }
            if (queryDTO.getProductGuestShoppingCondition().getPageSize() == null) {
                queryDTO.getProductGuestShoppingCondition().setPageSize(Constants.PAGESIZE);
            }
            queryDTO.getProductGuestShoppingCondition().setBizId(queryDTO.getBizId());
            queryDTO.getProductGuestShoppingCondition().setDataRightSet(queryDTO.getUserIdSet());
            pageBean.setPage(queryDTO.getProductGuestShoppingCondition().getPage());
            pageBean.setPageSize(queryDTO.getProductGuestShoppingCondition().getPageSize());
            pageBean.setParameter(queryDTO.getProductGuestShoppingCondition());
            pageBean = queryBiz.selectProductGuestShopStatics(pageBean);
            queryResult.setPageBean(pageBean);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult productGuestSourceShoppingList() {
        QueryResult queryResult = new QueryResult();
        try {
            List<RegionInfo> allProvince = regionBiz.getAllProvince();
            queryResult.setRegionInfoList(allProvince);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult commonQuery(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            PageBean pb = new PageBean();
            if (queryDTO.getPage() == null) {
                pb.setPage(1);
            } else {

                pb.setPage(queryDTO.getPage());
            }
            if (queryDTO.getPageSize() == null) {
                queryDTO.setPageSize(Constants.PAGESIZE);
            }
            pb.setPageSize(queryDTO.getPageSize());
            Map paramters = queryDTO.getParameters();

            paramters.put("citysSupplierIds",queryDTO.getCitysSupplierIds());
            paramters.put("supplierLevel", queryDTO.getSupplierLevel());
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
                        queryDTO.getBizId(), set);
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
            pb = getCommonService(queryDTO.getSvc()).queryListPage(queryDTO.getSl(), pb);
            queryResult.setPageBean(pb);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult productProfitList(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            queryResult.setPlanedPersonCount(productGroupPriceBiz.selectProductByProduct(queryDTO.getParameters()));
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult loadOrderId(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            List<GroupOrder> groupOrders = groupOrderBiz
                    .selectOrderByGroupId(queryDTO.getGroupId());
            queryResult.setGroupOrders(groupOrders);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult productGuestDetailPreview(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            if (StringUtils.isBlank(queryDTO.getProductGuestCondition().getOperatorIds()) && StringUtils.isNotBlank(queryDTO.getProductGuestCondition().getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = queryDTO.getProductGuestCondition().getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(queryDTO.getBizId(), set);
                String salesOperatorIds = "";
                for (Integer usrId : set) {
                    salesOperatorIds += usrId + ",";
                }
                if (!salesOperatorIds.equals("")) {
                    queryDTO.getProductGuestCondition().setOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
                }
            }
            // if (condition.getStartDate() != null) {
            // condition.setStartDateNum(condition.getStartDate().getTime());
            // }
            // if (condition.getEndDate() != null) {
            // condition.setEndDateNum(condition.getEndDate().getTime());
            // }
            if (queryDTO.getProductGuestCondition().getDateType() != null && queryDTO.getProductGuestCondition().getDateType() == 1) {
                if (!"".equals(queryDTO.getProductGuestCondition().getStartDate())) {
                    queryDTO.getProductGuestCondition().setStartDateNum(queryDTO.getProductGuestCondition().getStartDate().getTime());
                }
                if (!"".equals(queryDTO.getProductGuestCondition().getEndDate())) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(queryDTO.getProductGuestCondition().getEndDate());
                    calendar.add(Calendar.DAY_OF_MONTH, +1);// 让日期加1
                    queryDTO.getProductGuestCondition().setEndDateNum(calendar.getTime().getTime());
                }
            }

          //  String imgPath = bizSettingCommon.getMyBizLogo(request);
          //  model.addAttribute("imgPath", imgPath);
            queryDTO.getProductGuestCondition().setBizId(queryDTO.getBizId());
            List<ProductGuestStaticsVo> productGuestStatics = queryBiz.productGuestStatics2(queryDTO.getProductGuestCondition(),
                    queryDTO.getUserIdSet());
            queryResult.setProductGuestStatics(productGuestStatics);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult getAccountDetail(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            QueryResult queryResult_1 = commonQuery(queryDTO);
            PageBean pb = queryResult_1.getPageBean();
            if (StringUtils.isNotBlank(queryDTO.getSsl())) {
                Map pm = (Map) pb.getParameter();
                pm.put("parameter", pm);
                queryResult.setSum(getCommonService(queryDTO.getSvc()).queryOne(queryDTO.getSsl(), pm));
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

   /* @Override
    public QueryResult toBookingShopList(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            queryResult.setPageBean(bookingShopBiz.selectShopListPage(queryDTO.getPageBean(),
                    queryDTO.getBizId()));
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

  @Override
    public QueryResult queryGuestSourceStatics(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            queryDTO.getProductGuestCondition().setBizId(queryDTO.getBizId());
            if (queryDTO.getProductGuestCondition().getStartDate() != null) {
                queryDTO.getProductGuestCondition().setStartDateNum(queryDTO.getProductGuestCondition().getStartDate().getTime());
            }
            if (queryDTO.getProductGuestCondition().getEndDate() != null) {
                // 添加时间时，结束时间需要加一天
                queryDTO.getProductGuestCondition().setEndDateNum(DateUtils.addDay(
                        queryDTO.getProductGuestCondition().getEndDate(), 1).getTime());
            }
            String jsonStr = queryBiz.guestSourceStatics(queryDTO.getProductGuestCondition(),
                    queryDTO.getUserIdSet());
            queryResult.setJson(jsonStr);
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }

    @Override
    public QueryResult queryGroupNumber(QueryDTO queryDTO) {
        QueryResult queryResult = new QueryResult();
        try {
            List<GroupOrder> allGroupOrder = groupOrderBiz
                    .selectGroupNumForQuery(queryDTO.getGroupOrder(),
                            queryDTO.getBizId(),
                            queryDTO.getUserIdSet());
           // String jsonStr = queryBiz.productGuestStatics(allGroupOrder,
           //         queryDTO.getDataType());

         //   String jsonStr = queryBiz.getGroupNumStatics(allGroupOrder, queryDTO.getDataType());
           // queryResult.setJson();
        } catch (Exception e) {
            logger.error("", e);
        }
        return queryResult;
    }**/

    /**
     * 获取查询服务
     *
     * @author Jing.Zhuo
     * @create 2015年8月18日 上午9:34:25
     * @param svc
     * @return
     */
    private CommonBiz getCommonService(String svc) {
        if (StringUtils.isBlank(svc)) {
            svc = "commonsaleService";
        }
        return appContext.getBean(svc, CommonBiz.class);
    }

    private Integer zeroIfNull(Integer cnt) {
        return cnt == null ? 0 : cnt;
    }
}
