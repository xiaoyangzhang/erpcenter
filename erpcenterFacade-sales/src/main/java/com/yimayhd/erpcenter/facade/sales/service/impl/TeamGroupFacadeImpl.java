package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.airticket.AirTicketRequestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRequirementBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TeamGroupBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TeamGroupVO;
import com.yimayhd.erpcenter.facade.sales.errorcode.OperationErrorCode;
import com.yimayhd.erpcenter.facade.sales.query.CopyTourGroupDTO;
import com.yimayhd.erpcenter.facade.sales.query.FindTourGroupByConditionDTO;
import com.yimayhd.erpcenter.facade.sales.query.SaveRequireMentDTO;
import com.yimayhd.erpcenter.facade.sales.query.SaveTeamGroupInfoDTO;
import com.yimayhd.erpcenter.facade.sales.query.ToAddTeamGroupInfoDTO;
import com.yimayhd.erpcenter.facade.sales.query.ToEditTeamGroupInfoDTO;
import com.yimayhd.erpcenter.facade.sales.query.ToSearchListDTO;
import com.yimayhd.erpcenter.facade.sales.result.ContactManListResult;
import com.yimayhd.erpcenter.facade.sales.result.FindTourGroupByConditionResult;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;
import com.yimayhd.erpcenter.facade.sales.result.SaveTeamGroupInfoResult;
import com.yimayhd.erpcenter.facade.sales.result.ToAddTeamGroupInfoResult;
import com.yimayhd.erpcenter.facade.sales.result.ToEditTeamGroupInfoResult;
import com.yimayhd.erpcenter.facade.sales.result.ToGroupListResult;
import com.yimayhd.erpcenter.facade.sales.result.ToRequirementResult;
import com.yimayhd.erpcenter.facade.sales.result.ToSearchListResult;
import com.yimayhd.erpcenter.facade.sales.service.TeamGroupFacade;
import com.yimayhd.erpresource.biz.service.SupplierBiz;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/25 18:30
 */
public class TeamGroupFacadeImpl implements TeamGroupFacade {
    private static final Logger logger = LoggerFactory.getLogger("TeamGroupFacadeImpl");

    @Autowired
    private PlatformEmployeeBiz platformEmployeeBiz;
    @Autowired
    private GroupOrderBiz groupOrderBiz;
    @Autowired
    private DicBiz dicBiz;
    @Autowired
    private RegionBiz regionBiz;
    @Autowired
    private GroupRouteBiz groupRouteBiz;
    @Autowired
    private PlatformOrgBiz platformOrgBiz;
    @Autowired
    private SupplierBiz supplierBiz;
    @Autowired
    private ProductInfoBiz productInfoBiz;
    @Autowired
    private TeamGroupBiz teamGroupBiz;
    @Autowired
    private GroupOrderGuestBiz groupOrderGuestBiz;
    @Autowired
    private GroupRequirementBiz groupRequirementBiz;
    @Autowired
    private TourGroupBiz tourGroupBiz;
    @Autowired
    private FinanceBiz financeBiz;
    @Autowired
    private AirTicketRequestBiz airTicketRequestBiz;




    @Override
    public FindTourGroupByConditionResult findTourGroupByConditionLoadModel(FindTourGroupByConditionDTO findTourGroupByConditionDTO,PageBean pageBean1) {
        FindTourGroupByConditionResult findTourGroupByConditionResult = new FindTourGroupByConditionResult();
        try {
            
           /* pageBean = groupOrderBiz.selectByConListPage(pageBean,
                    findTourGroupByConditionDTO.getCurBizId(),
                    findTourGroupByConditionDTO.getDataUserIdSet(), findTourGroupByConditionDTO.getOperatorType());
            result.setPageBean(pageBean);
            GroupOrder order = groupOrderBiz.selectTotalByCon(findTourGroupByConditionDTO.getGroupOrder(),
            		findTourGroupByConditionDTO.getCurBizId(),
                    findTourGroupByConditionDTO.getDataUserIdSet(), findTourGroupByConditionDTO.getOperatorType());
            result.setGroupOrder(order);*/


            PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();

            pageBean.setPageSize(findTourGroupByConditionDTO.getGroupOrder().getPageSize() == null ? Constants.PAGESIZE
                    : findTourGroupByConditionDTO.getGroupOrder().getPageSize());
            pageBean.setPage(findTourGroupByConditionDTO.getGroupOrder().getPage());

            // 如果人员为空并且部门不为空，则取部门下的人id
            if (StringUtils.isBlank(findTourGroupByConditionDTO.getGroupOrder().getSaleOperatorIds())
                    && StringUtils.isNotBlank(findTourGroupByConditionDTO.getGroupOrder().getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = findTourGroupByConditionDTO.getGroupOrder().getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(
                        findTourGroupByConditionDTO.getCurBizId(), set);
                String salesOperatorIds = "";
                for (Integer usrId : set) {
                    salesOperatorIds += usrId + ",";
                }
                if (!salesOperatorIds.equals("")) {
                    findTourGroupByConditionDTO.getGroupOrder().setSaleOperatorIds(salesOperatorIds.substring(0,
                            salesOperatorIds.length() - 1));
                }
            }
            if (findTourGroupByConditionDTO.getGroupOrder().getDateType() != null && findTourGroupByConditionDTO.getGroupOrder().getDateType() == 2) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (findTourGroupByConditionDTO.getGroupOrder().getStartTime() != null
                        && findTourGroupByConditionDTO.getGroupOrder().getStartTime() != "") {
                    findTourGroupByConditionDTO.getGroupOrder().setStartTime(sdf.parse(findTourGroupByConditionDTO.getGroupOrder().getStartTime())
                            .getTime() + "");
                }
                if (findTourGroupByConditionDTO.getGroupOrder().getEndTime() != null
                        && findTourGroupByConditionDTO.getGroupOrder().getEndTime() != "") {
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(sdf.parse(findTourGroupByConditionDTO.getGroupOrder().getEndTime()));
                    calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
                    findTourGroupByConditionDTO.getGroupOrder().setEndTime(calendar.getTime().getTime() + "");
                }
            }
            pageBean.setParameter(findTourGroupByConditionDTO.getGroupOrder());
            pageBean = groupOrderBiz.selectByConListPage(pageBean,
                    findTourGroupByConditionDTO.getCurBizId(),
                    findTourGroupByConditionDTO.getDataUserIdSet(), 0);
            Integer pageTotalAudit = 0;
            Integer pageTotalChild = 0;
            Integer pageTotalGuide = 0;
            List<GroupOrder> result = pageBean.getResult();
            if (result != null && result.size() > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (GroupOrder groupOrder2 : result) {
                    if (groupOrder2.getCreateTime() != null) {
                        Long createTime = groupOrder2.getTourGroup()
                                .getCreateTime();
                        String dateStr = sdf.format(createTime);
                        groupOrder2.getTourGroup().setCreateTimeStr(dateStr);
                    }
                    if (groupOrder2.getTourGroup().getUpdateTime() != null) {
                        Long updateTime = groupOrder2.getTourGroup()
                                .getUpdateTime();
                        String dateStr = sdf.format(updateTime);
                        groupOrder2.getTourGroup().setUpdateTimeStr(dateStr);
                    } else {
                        groupOrder2.getTourGroup().setUpdateTimeStr("无");
                        groupOrder2.getTourGroup().setUpdateName("无");
                    }
                    pageTotalAudit += groupOrder2.getNumAdult();
                    pageTotalChild += groupOrder2.getNumChild();
                    pageTotalGuide += groupOrder2.getNumGuide();
                }
            }
            findTourGroupByConditionResult.setPageTotalAudit(pageTotalAudit);
            findTourGroupByConditionResult.setPageTotalChild(pageTotalChild);
            findTourGroupByConditionResult.setPageTotalGuide(pageTotalGuide);
            GroupOrder order = groupOrderBiz.selectTotalByCon(findTourGroupByConditionDTO.getGroupOrder(),
                    findTourGroupByConditionDTO.getCurBizId(),
                    findTourGroupByConditionDTO.getDataUserIdSet(), 0);
            findTourGroupByConditionDTO.setGroupOrder(order);

            /**
             * 根据组团社id获取组团社名称
             */
            List<GroupOrder> groupList = pageBean.getResult();
            findTourGroupByConditionResult.setGroupOrderList(groupList);
            findTourGroupByConditionResult.setPageBean(pageBean);

        } catch (Exception e) {
            logger.error("获取团队列表数据失败", e);
        }
        return findTourGroupByConditionResult;
    }

    @Override
    public ToAddTeamGroupInfoResult toAddTeamGroupInfo(ToAddTeamGroupInfoDTO toAddTeamGroupInfoDTO) {
        ToAddTeamGroupInfoResult toAddTeamGroupInfoResult = new ToAddTeamGroupInfoResult();
        try {
            TeamGroupVO teamGroupVO = new TeamGroupVO();
            teamGroupVO.setGroupOrder(toAddTeamGroupInfoDTO.getGroupOrder());
            int bizId = toAddTeamGroupInfoDTO.getCurBizId();
            // 收费类型
            List<DicInfo> typeList = dicBiz.getListByTypeCode(BasicConstants.SALES_TEAM_TYPE, bizId);
            List<DicInfo> sourceTypeList = dicBiz
                    .getListByTypeCode(Constants.GUEST_SOURCE_TYPE, bizId);


            List<RegionInfo> allProvince = regionBiz.getAllProvince();

            List<DicInfo> jtfsList = dicBiz
                    .getListByTypeCode(BasicConstants.GYXX_JTFS, bizId);

            List<DicInfo> zjlxList = dicBiz
                    .getListByTypeCode(BasicConstants.GYXX_ZJLX);

            List<DicInfo> lysfxmList = dicBiz.getListByTypeCode(
                    BasicConstants.GYXX_LYSFXM, bizId);
            toAddTeamGroupInfoResult.setTeamGroupVO(teamGroupVO);
            toAddTeamGroupInfoResult.setAllProvince(allProvince);
            toAddTeamGroupInfoResult.setTypeList(typeList);
            toAddTeamGroupInfoResult.setSourceTypeList(sourceTypeList);
            toAddTeamGroupInfoResult.setJtfsList(jtfsList);
            toAddTeamGroupInfoResult.setZjlxList(zjlxList);
            toAddTeamGroupInfoResult.setLysfxmList(lysfxmList);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toAddTeamGroupInfoResult;
    }

    @Override
    public String getDataByGroupId(int groupId) {
        try {
            GroupRouteVO groupRouteVO = groupRouteBiz.findGroupRouteByGroupId(groupId);
           return  JSON.toJSONString(groupRouteVO);
        } catch (Exception e) {
            logger.error("", e);
        }
        return "";
    }

    @Override
    public ContactManListResult contactManList(int curBizId, int id) {
        ContactManListResult contactManListResult = new ContactManListResult();
        try {
            contactManListResult.setManList(supplierBiz.selectPrivateManBySupplierId(curBizId, id));
        } catch (Exception e) {
            logger.error("", e);
        }
        return contactManListResult;
    }

    @Override
    public ToSearchListResult toSearchList(ToSearchListDTO toSearchListDTO) {
        ToSearchListResult toSearchListResult = new ToSearchListResult();
        try {
            List<DicInfo> brandList = dicBiz
                    .getListByTypeCode(BasicConstants.CPXL_PP, toSearchListDTO.getBizId());
            if (toSearchListDTO.getPage() == null) {
                toSearchListDTO.setPage(1);
            }
            PageBean<ProductInfo> pageBean = new PageBean<ProductInfo>();
            if (toSearchListDTO.getPageSize() == null) {
                pageBean.setPageSize(Constants.PAGESIZE);
            } else {
                pageBean.setPageSize(toSearchListDTO.getPageSize());
            }
            toSearchListDTO.getProductInfo().setTravelDays(1);
            pageBean.setParameter(toSearchListDTO.getProductInfo());
            pageBean.setPage(toSearchListDTO.getPage());
            Map parameters = new HashMap();
            parameters.put("bizId", toSearchListDTO.getBizId());
            parameters.put("name", toSearchListDTO.getName());
            parameters.put("productName", toSearchListDTO.getProductName());
            //parameters.put("orgId", WebUtils.getCurUser(request).getOrgId());
            //parameters.put("set", WebUtils.getDataUserIdSet(request));

            pageBean = productInfoBiz.findProductRoutes(pageBean, parameters);

            toSearchListResult.setBrandList(brandList);
            toSearchListResult.setPageBean(pageBean);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toSearchListResult;
    }

    @Override
    public SaveTeamGroupInfoResult saveTeamGroupInfo(SaveTeamGroupInfoDTO saveTeamGroupInfoDTO) {
        SaveTeamGroupInfoResult saveTeamGroupInfoResult = new SaveTeamGroupInfoResult();
        try {
            saveTeamGroupInfoResult.setTeamGroupVO(teamGroupBiz.saveOrUpdateTeamGroupVO(saveTeamGroupInfoDTO.getCurBizId(), saveTeamGroupInfoDTO.getCurUserId(), saveTeamGroupInfoDTO.getCurUserName(), saveTeamGroupInfoDTO.getTeamGroupVO()));
        } catch (Exception e) {
            logger.error("", e);
        }
        return saveTeamGroupInfoResult;
    }

    @Override
    public ToEditTeamGroupInfoResult toEditTeamGroupInfo(ToEditTeamGroupInfoDTO toEditTeamGroupInfoDTO) {
        ToEditTeamGroupInfoResult toEditTeamGroupInfoResult = new ToEditTeamGroupInfoResult();
        try {
            int bizId = toEditTeamGroupInfoDTO.getCurBizId();
            TeamGroupVO teamGroupVO = teamGroupBiz.selectTeamGroupVOByGroupId(toEditTeamGroupInfoDTO.getGroupId(), toEditTeamGroupInfoDTO.getCurBizId());
            // 收费类型
            List<DicInfo> typeList = dicBiz.getListByTypeCode(BasicConstants.SALES_TEAM_TYPE, bizId);
            List<DicInfo> sourceTypeList = dicBiz
                    .getListByTypeCode(Constants.GUEST_SOURCE_TYPE, bizId);
            List<RegionInfo> allProvince = regionBiz.getAllProvince();
            List<DicInfo> jtfsList = dicBiz
                    .getListByTypeCode(BasicConstants.GYXX_JTFS, bizId);
            List<DicInfo> zjlxList = dicBiz
                    .getListByTypeCode(BasicConstants.GYXX_ZJLX);
            List<DicInfo> lysfxmList = dicBiz.getListByTypeCode(
                    BasicConstants.GYXX_LYSFXM, bizId);
            List<RegionInfo> cityList = null;
            if (teamGroupVO.getGroupOrder().getProvinceId() != null
                    && teamGroupVO.getGroupOrder().getProvinceId() != -1) {
                cityList = regionBiz.getRegionById(teamGroupVO.getGroupOrder()
                        .getProvinceId() + "");
            }
            String guideStr = "";
            List<GroupOrderGuest> guestList = groupOrderGuestBiz.selectByOrderId(teamGroupVO.getGroupOrder().getId());
            if (guestList != null) {
                for (GroupOrderGuest groupOrderGuest : guestList) {
                    if (groupOrderGuest.getType() == 3) {
                        guideStr = ("".equals(guideStr) ? "" : (guideStr + " | ")) + groupOrderGuest.getName() + " " + groupOrderGuest.getMobile();
                    }
                }
            }

            toEditTeamGroupInfoResult.setTeamGroupVO(teamGroupVO);
            toEditTeamGroupInfoResult.setAllProvince(allProvince);
            toEditTeamGroupInfoResult.setTypeList(typeList);
            toEditTeamGroupInfoResult.setSourceTypeList(sourceTypeList);
            toEditTeamGroupInfoResult.setJtfsList(jtfsList);
            toEditTeamGroupInfoResult.setZjlxList(zjlxList);
            toEditTeamGroupInfoResult.setLysfxmList(lysfxmList);
            toEditTeamGroupInfoResult.setCityList(cityList);
            toEditTeamGroupInfoResult.setGuideStr(guideStr);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toEditTeamGroupInfoResult;
    }

    @Override
    public ToRequirementResult toRequirement(Integer orderId, Integer operType) {
        ToRequirementResult toRequirementResult = new ToRequirementResult();
        try {
            GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(orderId);
            toRequirementResult.setGroupId(groupOrder.getGroupId());
            // 车辆型号
            List<DicInfo> ftcList = dicBiz
                    .getListByTypeCode(Constants.FLEET_TYPE_CODE);
            toRequirementResult.setFtcList(ftcList);
            // 酒店星级
            List<DicInfo> jdxjList = dicBiz
                    .getListByTypeCode(BasicConstants.GYXX_JDXJ);
            toRequirementResult.setJdxjList(jdxjList);
            // 酒店信息
            List<GroupRequirement> hotelList = groupRequirementBiz
                    .selectByOrderAndType(orderId, 3);
            toRequirementResult.setHotelList(hotelList);
            // 车队信息
            List<GroupRequirement> fleetList = groupRequirementBiz
                    .selectByOrderAndType(orderId, 4);
            toRequirementResult.setFleetList(fleetList);
            // 机票信息
            List<GroupRequirement> airTicketList = groupRequirementBiz
                    .selectByOrderAndType(orderId, 9);
            toRequirementResult.setAirTicketList(airTicketList);
            // 火车信息
            List<GroupRequirement> railwayTicketList = groupRequirementBiz
                    .selectByOrderAndType(orderId, 10);
            toRequirementResult.setRailwayTicketList(railwayTicketList);
            // 导游信息
            List<GroupRequirement> guideList = groupRequirementBiz
                    .selectByOrderAndType(orderId, 8);
            toRequirementResult.setGuideList(guideList);
            // 餐厅信息
            List<GroupRequirement> restaurantList = groupRequirementBiz
                    .selectByOrderAndType(orderId, 2);
            toRequirementResult.setRestaurantList(restaurantList);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toRequirementResult;
    }

    @Override
    public String copyTourGroup(CopyTourGroupDTO copyTourGroupDTO) {
        try {
            TourGroup group = tourGroupBiz.selectByPrimaryKey(copyTourGroupDTO.getGroupId());
            group.setId(null);
            group.setCreateTime(System.currentTimeMillis());
            group.setTotalAdult(copyTourGroupDTO.getTourGroup().getTotalAdult());
            group.setTotalChild(copyTourGroupDTO.getTourGroup().getTotalChild());
            group.setTotalGuide(copyTourGroupDTO.getTourGroup().getTotalGuide());
            group.setGroupCode(platformOrgBiz.getCompanyCodeByOrgId(copyTourGroupDTO.getCurBizId(), copyTourGroupDTO.getCurUserOrgId()));
            group.setGroupState(0); // 团状态，默认为0
            group.setDateStart(copyTourGroupDTO.getTourGroup().getDateStart());
            group.setDateEnd(copyTourGroupDTO.getTourGroup().getDateEnd());
            group.setTotalIncome(new BigDecimal(0));
            group.setTotalIncomeCash(new BigDecimal(0));
            group.setTotalCostCash(new BigDecimal(0));
            GroupOrder go = groupOrderBiz.selectByPrimaryKey(copyTourGroupDTO.getOrderId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            go.setDepartureDate(sdf.format(copyTourGroupDTO.getTourGroup().getDateStart()));
            go.setId(null);
            go.setCreateTime(System.currentTimeMillis());
            go.setSaleOperatorId(copyTourGroupDTO.getGroupOrder().getSaleOperatorId());
            go.setSaleOperatorName(copyTourGroupDTO.getGroupOrder().getSaleOperatorName());
            go.setOperatorId(copyTourGroupDTO.getGroupOrder().getOperatorId());
            go.setOperatorName(copyTourGroupDTO.getGroupOrder().getOperatorName());
            go.setContactName(copyTourGroupDTO.getGroupOrder().getContactName());
            go.setContactTel(copyTourGroupDTO.getGroupOrder().getContactMobile());
            go.setContactMobile(copyTourGroupDTO.getGroupOrder().getContactMobile());
            go.setContactFax(copyTourGroupDTO.getGroupOrder().getContactFax());
            go.setStateFinance(0);
            go.setOrderNo(platformOrgBiz.getCompanyCodeByOrgId(copyTourGroupDTO.getCurBizId(), copyTourGroupDTO.getCurUserOrgId()));// 订单号调用接口获取
            go.setNumAdult(copyTourGroupDTO.getTourGroup().getTotalAdult());
            go.setNumChild(copyTourGroupDTO.getTourGroup().getTotalChild());
            go.setNumGuide(copyTourGroupDTO.getTourGroup().getTotalGuide());
            go.setSupplierName(copyTourGroupDTO.getGroupOrder().getSupplierName());
            go.setSupplierId(copyTourGroupDTO.getGroupOrder().getSupplierId());
            go.setTotalCash(new BigDecimal(0));
            tourGroupBiz.copyGroup(group, go, copyTourGroupDTO.getGroupId(), copyTourGroupDTO.getOrderId(), copyTourGroupDTO.getInfo(),copyTourGroupDTO.getCurUserId(),copyTourGroupDTO.getCurUserName());
        } catch (Exception e) {
            logger.error("", e);
        }
        return "";
    }

    @Override
    public String deleteGroupOrderById(Integer orderId, Integer groupId,Integer curBizId) {
        try {
            if (financeBiz.hasAuditOrder(groupId)) {
                return "该团有已审核的订单,不允许删除！";
            }

            if (financeBiz.hasPayOrIncomeRecord(groupId)) {
                return "该团有收付款记录,不允许删除！";
            }
            if (airTicketRequestBiz.doesOrderhaveRequested(curBizId, orderId)) {
                return "删除订单前请先取消机票申请。";
            }
            if (financeBiz.hasHotelOrder(groupId)) {
                return "该团有酒、车队订单,不允许删除！";
            }
            int i = tourGroupBiz.deleteTourGroupById(groupId, orderId);
            if (i == 1) {
                return "成功";
            } else {
                return "服务器忙！";
            }
        }catch (Exception e){
            logger.error("", e);
        }
        return "";
    }

    @Override
    public ResultSupport saveRequireMent(SaveRequireMentDTO saveRequireMentDTO) {
        ResultSupport resultSupport = new ResultSupport();
        try {
            teamGroupBiz.saveOrUpdateRequirement(saveRequireMentDTO.getTeamGroupVO(), saveRequireMentDTO.getCurBizId(), saveRequireMentDTO.getCurUserName());
        } catch (Exception e) {
            resultSupport.setSuccess(false);
            logger.error("", e);
        }
        return resultSupport;
    }

    @Override
    public ToGroupListResult toGroupList(int bizId) {
        ToGroupListResult toGroupListResult = new ToGroupListResult();
        try {
            toGroupListResult.setAllProvince(regionBiz.getAllProvince());
            toGroupListResult.setOrgJsonStr(platformOrgBiz.getComponentOrgTreeJsonStr(bizId));
            toGroupListResult.setOrgUserJsonStr(platformEmployeeBiz.getComponentOrgUserTreeJsonStr(bizId));
            toGroupListResult.setSourceTypeList(dicBiz.getListByTypeCode(Constants.GUEST_SOURCE_TYPE, bizId));
        } catch (Exception e) {
            logger.error("", e);
        }
        return toGroupListResult;
    }

	@Override
	public ResultSupport saveOrUpdateRequirement(TeamGroupVO vo,
			Integer curBizId, String userName) {
		ResultSupport resultSupport = new ResultSupport();
		TeamGroupVO saveResult = teamGroupBiz.saveOrUpdateRequirement(vo, curBizId,userName);
		if (saveResult == null) {
			resultSupport.setErrorCode(OperationErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}


}
