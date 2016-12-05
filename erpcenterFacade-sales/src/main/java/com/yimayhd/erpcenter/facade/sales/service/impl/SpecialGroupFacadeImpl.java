package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderPriceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderTransportBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRequirementBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.SpecialGroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.biz.sys.service.SysBizBankAccountBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.MergeGroupOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SpecialGroupOrderVO;
import com.yimayhd.erpcenter.facade.sales.result.ToAddSpecialGroupResult;
import com.yimayhd.erpcenter.facade.sales.service.SpecialGroupFacade;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.biz.service.SupplierDriverBiz;
import com.yimayhd.erpresource.biz.service.SupplierGuideBiz;
import com.yimayhd.erpresource.biz.service.SupplierImgBiz;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/31 14:00
 */
public class SpecialGroupFacadeImpl implements SpecialGroupFacade {
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
    public ToAddSpecialGroupResult toAddSpecialGroup(Integer userId, String userName,Integer bizId) {
        ToAddSpecialGroupResult toAddSpecialGroupResult = new ToAddSpecialGroupResult();
        try {
            GroupOrder groupOrder  = new GroupOrder();
            groupOrder.setSaleOperatorId(userId);
            groupOrder.setSaleOperatorName(userName);
            groupOrder.setOperatorId(userId);
            groupOrder.setOperatorName(userName);
            SpecialGroupOrderVO vo = new SpecialGroupOrderVO();
            vo.setGroupOrder(groupOrder);
            toAddSpecialGroupResult.setSpecialGroupOrderVO(vo);

            List<DicInfo> jdxjList = dicBiz.getListByTypeCode(BasicConstants.GYXX_JDXJ);
            toAddSpecialGroupResult.setJdxjList(jdxjList);
            List<DicInfo> jtfsList = dicBiz.getListByTypeCode(BasicConstants.GYXX_JTFS, bizId);
            toAddSpecialGroupResult.setJtfsList(jtfsList);
            List<DicInfo> zjlxList = dicBiz.getListByTypeCode(BasicConstants.GYXX_ZJLX);
            toAddSpecialGroupResult.setZjlxList(zjlxList);
            List<DicInfo> sourceTypeList = dicBiz.getListByTypeCode(Constants.GUEST_SOURCE_TYPE, bizId);
            toAddSpecialGroupResult.setSourceTypeList(sourceTypeList);
            List<RegionInfo> allProvince = regionBiz.getAllProvince();
            toAddSpecialGroupResult.setAllProvince(allProvince);
            List<DicInfo> lysfxmList = dicBiz.getListByTypeCode( BasicConstants.GYXX_LYSFXM, bizId);
            toAddSpecialGroupResult.setLysfxmList(lysfxmList);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toAddSpecialGroupResult;
    }

    @Override
    public ToAddSpecialGroupResult toEditSpecialGroup(Integer id, Integer operType,Integer bizId) {
        ToAddSpecialGroupResult toAddSpecialGroupResult = new ToAddSpecialGroupResult();
        try {
            if(operType==null){
                operType=1;
            }
            toAddSpecialGroupResult.setOperType(operType);
            SpecialGroupOrderVO  vo= specialGroupOrderBiz.selectSpeciaOrderlInfoByOrderId(id);
            toAddSpecialGroupResult.setSpecialGroupOrderVO(vo);
            List<DicInfo> jdxjList = dicBiz.getListByTypeCode(BasicConstants.GYXX_JDXJ);
            toAddSpecialGroupResult.setJdxjList(jdxjList);
            List<DicInfo> zjlxList = dicBiz .getListByTypeCode(BasicConstants.GYXX_ZJLX);
            toAddSpecialGroupResult.setZjlxList(zjlxList);
            List<DicInfo> lysfxmList = dicBiz.getListByTypeCode(
                    BasicConstants.GYXX_LYSFXM, bizId);
            toAddSpecialGroupResult.setLysfxmList(lysfxmList);
            List<DicInfo> jtfsList = dicBiz
                    .getListByTypeCode(BasicConstants.GYXX_JTFS, bizId);
            toAddSpecialGroupResult.setJtfsList(jtfsList);
            List<DicInfo> sourceTypeList = dicBiz.getListByTypeCode(Constants.GUEST_SOURCE_TYPE, bizId);
            toAddSpecialGroupResult.setSourceTypeList(sourceTypeList);
            List<RegionInfo> allProvince = regionBiz.getAllProvince();
            toAddSpecialGroupResult.setAllProvince(allProvince);
            List<RegionInfo> cityList = null;
            if(vo.getGroupOrder().getProvinceId()!=null && vo.getGroupOrder().getProvinceId()!=-1){
                cityList=regionBiz.getRegionById(vo.getGroupOrder().getProvinceId()+"");
            }
            toAddSpecialGroupResult.setCityList(cityList);
            String guideStr="";
            List<GroupOrderGuest> guestList = groupOrderGuestBiz.selectByOrderId(id);
            if(guestList!=null){
                for (GroupOrderGuest groupOrderGuest : guestList) {
                    if(groupOrderGuest.getType()==3){
                        guideStr=("".equals(guideStr)?"":(guideStr+" | "))+groupOrderGuest.getName()+" "+groupOrderGuest.getMobile();
                    }
                }
            }
            toAddSpecialGroupResult.setGuideStr(guideStr);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toAddSpecialGroupResult;
    }

    @Override
    public ToAddSpecialGroupResult saveSpecialGroup(SpecialGroupOrderVO vo, Integer orgId, Integer bizId,Integer userId,String userName) {
        ToAddSpecialGroupResult toAddSpecialGroupResult = new ToAddSpecialGroupResult();
        try {
            if(vo.getGroupOrder().getId()==null){
                vo.getGroupOrder().setOrderNo(platformOrgBiz.getCompanyCodeByOrgId(bizId, orgId));
            }
            Integer orderId = specialGroupOrderBiz.saveOrUpdateSpecialOrderInfo(vo,userId,userName,bizId);
            toAddSpecialGroupResult.setOrderId(orderId);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toAddSpecialGroupResult;
    }

    @Override
    public ToAddSpecialGroupResult toSpecialGroupList(Integer bizId) {
        ToAddSpecialGroupResult toAddSpecialGroupResult = new ToAddSpecialGroupResult();
        try {
            List<DicInfo> pp = dicBiz.getListByTypeCode(BasicConstants.CPXL_PP,bizId);
            toAddSpecialGroupResult.setDicInfoList(pp);
            List<RegionInfo> allProvince = regionBiz.getAllProvince();
            toAddSpecialGroupResult.setAllProvince(allProvince);
            List<DicInfo> sourceTypeList = dicBiz
                    .getListByTypeCode(Constants.GUEST_SOURCE_TYPE, bizId);
            toAddSpecialGroupResult.setSourceTypeList(sourceTypeList);
            toAddSpecialGroupResult.setOrgJsonStr(platformOrgBiz.getComponentOrgTreeJsonStr(bizId));
            toAddSpecialGroupResult.setOrgUserJsonStr(  platformEmployeeBiz.getComponentOrgUserTreeJsonStr(bizId));

        } catch (Exception e) {
            logger.error("", e);
        }
        return toAddSpecialGroupResult;
    }

    @Override
    public ToAddSpecialGroupResult getSpecialGroupData(GroupOrder groupOrder, Integer bizId, Set<Integer> userIdSet ) {
        ToAddSpecialGroupResult toAddSpecialGroupResult = new ToAddSpecialGroupResult();
        try {
            if(groupOrder.getDateType()!=null && groupOrder.getDateType()==2){
                SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
                if(!"".equals(groupOrder.getStartTime())){
                    groupOrder.setStartTime(sdf.parse(groupOrder.getStartTime()).getTime()+"");
                }
                if(!"".equals(groupOrder.getEndTime())){
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(sdf.parse(groupOrder.getEndTime()));
                    calendar.add(Calendar.DAY_OF_MONTH, +1);// 让日期加1
                    groupOrder.setEndTime(calendar.getTime().getTime() + "");
                }

            }

            if (StringUtils.isBlank(groupOrder.getSaleOperatorIds())
                    && StringUtils.isNotBlank(groupOrder.getOrgIds())) {
                Set<Integer> set = new HashSet<Integer>();
                String[] orgIdArr = groupOrder.getOrgIds().split(",");
                for (String orgIdStr : orgIdArr) {
                    set.add(Integer.valueOf(orgIdStr));
                }
                set = platformEmployeeBiz.getUserIdListByOrgIdList(
                        bizId, set);
                String salesOperatorIds = "";
                for (Integer usrId : set) {
                    salesOperatorIds += usrId + ",";
                }
                if (!salesOperatorIds.equals("")) {
                    groupOrder.setSaleOperatorIds(salesOperatorIds.substring(0,
                            salesOperatorIds.length() - 1));
                }
            }
            PageBean page = new PageBean();
            page.setParameter(groupOrder);
            page.setPage(groupOrder.getPage()==null?1:groupOrder.getPage());
            page.setPageSize(groupOrder.getPageSize() == null ? Constants.PAGESIZE
                    : groupOrder.getPageSize());
            page =groupOrderBiz.selectSpecialOrderListPage(page,bizId,userIdSet);

            List<GroupOrder> list = page.getResult();
            Integer pageTotalAudit=0;
            Integer pageTotalChild=0;
            Integer pageTotalGuide=0;
            BigDecimal pageTotal=new BigDecimal(0);
            if(page.getResult()!=null && page.getResult().size()>0){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (GroupOrder groupOrder2 : list) {
                    pageTotalAudit+=groupOrder2.getNumAdult()==null?0:groupOrder2.getNumAdult();
                    pageTotalChild+=groupOrder2.getNumChild()==null?0:groupOrder2.getNumChild();
                    pageTotalGuide+=groupOrder2.getNumGuide()==null?0:groupOrder2.getNumGuide();
                    pageTotal =pageTotal.add(groupOrder2.getTotal()==null?new BigDecimal(0):groupOrder2.getTotal());
                    Long createTime = groupOrder2.getCreateTime();
                    String dateStr = sdf.format(createTime);
                    groupOrder2.setCreateTimeStr(dateStr);
                }
            }
            toAddSpecialGroupResult.setPageTotalAudit(pageTotalAudit);
            toAddSpecialGroupResult.setPageTotalChild(pageTotalChild);
            toAddSpecialGroupResult.setPageTotalGuide(pageTotalGuide);
            toAddSpecialGroupResult.setPageTotal(pageTotal);
            toAddSpecialGroupResult.setPage(page);
            GroupOrder go = groupOrderBiz.selectTotalSpecialOrder(groupOrder, bizId,userIdSet);
            toAddSpecialGroupResult.setGroupOrder(go);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toAddSpecialGroupResult;
    }

    @Override
    public ToAddSpecialGroupResult getSpecialGroup(TourGroup tourGroup, Integer tid, Integer bizId, Set<Integer> userIdSet) {
        ToAddSpecialGroupResult toAddSpecialGroupResult = new ToAddSpecialGroupResult();
        try {
            if (tid != null) {
                GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(tid);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                tourGroup.setEndTime(sdf.parse(groupOrder.getDepartureDate()));
            }
            tourGroup.setGroupMode(-1);
            PageBean pageBean = new PageBean();
            pageBean.setPageSize(tourGroup.getPageSize() == null ? Constants.PAGESIZE
                    : tourGroup.getPageSize());
            pageBean.setPage(tourGroup.getPage());
            pageBean.setParameter(tourGroup);
            pageBean = tourGroupBiz.selectSKGroupListPage(pageBean,bizId,userIdSet);
            toAddSpecialGroupResult.setPage(pageBean);
            toAddSpecialGroupResult.setTourGroup(tourGroup);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toAddSpecialGroupResult;
    }

    @Override
    public ToAddSpecialGroupResult toMergeGroup(String ids) {
        ToAddSpecialGroupResult toAddSpecialGroupResult = new ToAddSpecialGroupResult();
        try {
            List<GroupOrder> list = new ArrayList<GroupOrder>();
            String[] split = ids.split(",");
            for (String str : split) {
                GroupOrder groupOrder = groupOrderBiz.findById(Integer
                        .parseInt(str));
                groupOrder.setGroupOrderGuestList((groupOrderGuestBiz
                        .selectByOrderId(groupOrder.getId())));
                list.add(groupOrder);
            }
            toAddSpecialGroupResult.setGroupOrderList(list);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toAddSpecialGroupResult;
    }

    @Override
    public ToAddSpecialGroupResult mergetGroup(MergeGroupOrderVO mergeGroupOrderVO, Integer bizId, Integer orgId,Integer userId,String userName) {
        ToAddSpecialGroupResult toAddSpecialGroupResult = new ToAddSpecialGroupResult();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<GroupOrder> orderList = mergeGroupOrderVO.getOrderList();

            List<MergeGroupOrderVO> result = new ArrayList<MergeGroupOrderVO>();
            for (int i = 0; i < orderList.size();) {
                GroupOrder order = orderList.get(i);
                GroupOrder groupOrder = groupOrderBiz.findById(order.getId());
                groupOrder.setGroupCode(order.getGroupCode());
                orderList.remove(order);
                MergeGroupOrderVO mov = new MergeGroupOrderVO();
                mov.getOrderList().add(groupOrder);

                for (int j = 0; j < orderList.size();) {
                    GroupOrder order2 = orderList.get(j);
                    GroupOrder go = groupOrderBiz.findById(order2.getId());
                    go.setGroupCode(order2.getGroupCode());
                    // 相同，分组，并加入到组容器集合
                    if (go.getGroupCode().equals(groupOrder.getGroupCode())) {
                        mov.getOrderList().add(go);
                        orderList.remove(order2);
                    } else {
                        j++;
                    }
                }
                result.add(mov);
            }
            specialGroupOrderBiz.mergetGroup(result, bizId,userId,userName, platformOrgBiz.getCompanyCodeByOrgId(bizId, orgId));
        } catch (Exception e) {
            logger.error("", e);
        }
        return toAddSpecialGroupResult;
    }

    @Override
    public ToAddSpecialGroupResult toImpNotGroupList(GroupOrder groupOrder, String idLists, Integer bizId, Set<Integer> userIdSet) {
        ToAddSpecialGroupResult toAddSpecialGroupResult = new ToAddSpecialGroupResult();
        try {
            if (null == groupOrder.getEndTime() && null == groupOrder.getDepartureDate()) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                c.set(year, month, 1);
                groupOrder.setDepartureDate(c.get(Calendar.YEAR) + "-"
                        + (c.get(Calendar.MONTH) + 1) + "-01");
                c.set(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH));
                groupOrder.setEndTime(c.get(Calendar.YEAR) + "-"
                        + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE));

            }
            List<Integer> intIds = new ArrayList<Integer>();
            if (StringUtils.isNotBlank(idLists)){

                String[] split = idLists.split(",");
                for (String id : split) {
                    intIds.add(Integer.parseInt(id));
                }
                groupOrder.setIdList(intIds);
            }
            groupOrder.setState(2);
            PageBean pageBean = new PageBean();
            pageBean.setPageSize(groupOrder.getPageSize() == null ? Constants.PAGESIZE
                    : groupOrder.getPageSize());
            pageBean.setPage(groupOrder.getPage()==null?1:groupOrder.getPage());
            pageBean.setParameter(groupOrder);
            pageBean =groupOrderBiz.selectSpecialOrderListPage(pageBean, bizId,userIdSet);
            List<DicInfo> pp = dicBiz.getListByTypeCode(BasicConstants.CPXL_PP,
                    bizId);
            toAddSpecialGroupResult.setDicInfoList(pp);
            toAddSpecialGroupResult.setGroupOrder(groupOrder);
            toAddSpecialGroupResult.setPage(pageBean);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toAddSpecialGroupResult;
    }

    @Override
    public ToAddSpecialGroupResult insertGroup(Integer id, String code) {
        ToAddSpecialGroupResult toAddSpecialGroupResult = new ToAddSpecialGroupResult();
        try {
            TourGroup tourGroup = tourGroupBiz.selectByGroupCode(code);
            if (tourGroup == null) {
               // return errorJson("未查到该团号对应的散客团信息!");
            }
            GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(id);
            groupOrder.setGroupId(tourGroup.getId());
            groupOrderBiz.updateGroupOrder(groupOrder);
            tourGroup.setOrderNum(tourGroup.getOrderNum() == null ? 1 : tourGroup
                    .getOrderNum() + 1);
            tourGroupBiz.updateByPrimaryKey(tourGroup);
            groupOrderBiz.updateGroupPersonNum(tourGroup.getId());
            groupOrderBiz.updateGroupPrice(tourGroup.getId());

            List<GroupRequirement> list = groupRequirementBiz
                    .selectByOrderId(id);
            if (list != null && list.size() > 0) {
                for (GroupRequirement groupRequirement : list) {
                    groupRequirement.setGroupId(tourGroup.getId());
                    groupRequirementBiz
                            .updateByPrimaryKeySelective(groupRequirement);
                }
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return toAddSpecialGroupResult;
    }

    @Override
    public ToAddSpecialGroupResult insertGroupMany(String ids, String code) {
        ToAddSpecialGroupResult toAddSpecialGroupResult = new ToAddSpecialGroupResult();
        try {
            TourGroup tourGroup = tourGroupBiz.selectByGroupCode(code);
            if (tourGroup == null) {
                //return errorJson("未查到该团号对应的散客团信息!");
            }
            String[] split = ids.split(",");
            for (String str : split) {
                GroupOrder groupOrder = groupOrderBiz
                        .selectByPrimaryKey(Integer.parseInt(str));
                groupOrder.setGroupId(tourGroup.getId());
                groupOrderBiz.updateGroupOrder(groupOrder);
                tourGroup.setOrderNum(tourGroup.getOrderNum() == null ? 1
                        : tourGroup.getOrderNum() + 1);
                tourGroupBiz.updateByPrimaryKey(tourGroup);
                groupOrderBiz.updateGroupPersonNum(tourGroup.getId());
                groupOrderBiz.updateGroupPrice(tourGroup.getId());

                List<GroupRequirement> list = groupRequirementBiz
                        .selectByOrderId(Integer.parseInt(str));
                if (list != null && list.size() > 0) {
                    for (GroupRequirement groupRequirement : list) {
                        groupRequirement.setGroupId(tourGroup.getId());
                        groupRequirementBiz
                                .updateByPrimaryKeySelective(groupRequirement);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return toAddSpecialGroupResult;
    }

    @Override
    public ToAddSpecialGroupResult beforeInsertGroup(String ids) {
        ToAddSpecialGroupResult toAddSpecialGroupResult = new ToAddSpecialGroupResult();
        try {
            String[] split = ids.split(",");
            List<String> datelist = new ArrayList<String>();
            for (String id : split) {
                GroupOrder groupOrder = groupOrderBiz.findById(Integer
                        .parseInt(id));
                datelist.add(groupOrder.getDepartureDate());
                List<GroupOrderGuest> guestList = groupOrderGuestBiz
                        .selectByOrderId(Integer.parseInt(id));
            }
            toAddSpecialGroupResult.setDatelist(datelist);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toAddSpecialGroupResult;
    }

    @Override
    public ToAddSpecialGroupResult judgeMergeGroup(String ids) {
        ToAddSpecialGroupResult toAddSpecialGroupResult = new ToAddSpecialGroupResult();
        try {
            String[] split = ids.split(",");
            List<String> datelist = new ArrayList<String>();
            List<Integer> productlist = new ArrayList<Integer>();
            List<Integer> brandlist = new ArrayList<Integer>();
            List<Integer> statelist = new ArrayList<Integer>();
            for (String id : split) {
                GroupOrder groupOrder = groupOrderBiz.findById(Integer
                        .parseInt(id));
                datelist.add(groupOrder.getDepartureDate());
                productlist.add(groupOrder.getProductId());
                statelist.add(groupOrder.getStateFinance());
                brandlist.add(groupOrder.getProductBrandId());
            }
            toAddSpecialGroupResult.setDatelist(datelist);
            toAddSpecialGroupResult.setBrandlist(brandlist);
        } catch (Exception e) {
            logger.error("", e);
        }
        return toAddSpecialGroupResult;
    }
}
