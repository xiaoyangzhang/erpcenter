package com.yimayhd.erpcenter.facade.sales.service.impl;

import com.alibaba.fastjson.JSON;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRouteBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.dal.basic.constant.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TeamGroupVO;
import com.yimayhd.erpcenter.facade.sales.query.FindTourGroupByConditionDTO;
import com.yimayhd.erpcenter.facade.sales.query.ToAddTeamGroupInfoDTO;
import com.yimayhd.erpcenter.facade.sales.query.ToSearchListDTO;
import com.yimayhd.erpcenter.facade.sales.result.*;
import com.yimayhd.erpcenter.facade.sales.service.TeamGroupFacade;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

import java.text.SimpleDateFormat;
import java.util.*;

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


    @Override
    public FindTourGroupByConditionResult findTourGroupByConditionLoadModel(FindTourGroupByConditionDTO findTourGroupByConditionDTO) {
        FindTourGroupByConditionResult findTourGroupByConditionResult = new FindTourGroupByConditionResult();
        try {
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
    public ToAddTeamGroupInfoResult getDataByGroupId(ToAddTeamGroupInfoDTO toAddTeamGroupInfoDTO) {
        ToAddTeamGroupInfoResult toAddTeamGroupInfoResult = new ToAddTeamGroupInfoResult();
        try {
            GroupRouteVO groupRouteVO = groupRouteBiz
                    .findGroupRouteByGroupId(toAddTeamGroupInfoDTO.getGroupId());
            toAddTeamGroupInfoResult.setJsonStr(JSON.toJSONString(groupRouteVO));
        } catch (Exception e) {
            logger.error("", e);
        }
        return toAddTeamGroupInfoResult;
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
                    .getListByTypeCode(BasicConstants.CPXL_PP,toSearchListDTO.getBizId());
            if(toSearchListDTO.getPage()==null){
                toSearchListDTO.setPage(1);
            }
            PageBean<ProductInfo> pageBean = new PageBean<ProductInfo>();
            if(toSearchListDTO.getPageSize() ==null){
                pageBean.setPageSize(Constants.PAGESIZE);
            }else{
                pageBean.setPageSize(toSearchListDTO.getPageSize());
            }
            toSearchListDTO.getProductInfo().setTravelDays(1);
            pageBean.setParameter(toSearchListDTO.getProductInfo());
            pageBean.setPage(toSearchListDTO.getPage());
            Map parameters=new HashMap();
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


}
