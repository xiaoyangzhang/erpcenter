package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.InfoBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.DepartmentOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.ProductGuestStaticsVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/11/1 11:42
 */
public class QueryResult extends BaseResult {
    private PageBean pageBean;
    private List<DicInfo> dicInfoList;
    private Map<String, Object> personMap;
    private List<PlatformOrgPo> secLevelOrgList;
    private List<PlatformOrgPo> subLevelOrgList;
    private Map<Integer, List<PlatformOrgPo>> orgDepMap;
    private Map<Integer, DepartmentOrderVO> deptOrderVoMap;
    private List<String> dateList;
    private Set<Integer> userIdSet;
    private List<BookingGuide> bookingGuides;
    private InfoBean shop;
    private Map<Integer, String> guideMap;
    private List<TourGroup> tourGroupList;
    private List<RegionInfo> regionInfoList;
    private TourGroup tourGroup;
    private List<Map<String, Object>> listMap;
    private int planedPersonCount;
    private List<GroupOrder> groupOrders;
    private Map sum;
    private Map<Integer, List<PlatformEmployeePo>> empMap;
    private Map<Integer, List<GroupOrder>> orderMap;
    private List<GroupOrder> gOrdersList;
    private Map parameters;

    public Map getSum() {
        return sum;
    }

    public void setSum(Map sum) {
        this.sum = sum;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    private String json;

    public List<GroupOrder> getGroupOrders() {
        return groupOrders;
    }

    public void setGroupOrders(List<GroupOrder> groupOrders) {
        this.groupOrders = groupOrders;
    }

    public int getPlanedPersonCount() {
        return planedPersonCount;
    }

    public void setPlanedPersonCount(int planedPersonCount) {
        this.planedPersonCount = planedPersonCount;
    }

    List<ProductGuestStaticsVo> productGuestStatics;

    public List<ProductGuestStaticsVo> getProductGuestStatics() {
        return productGuestStatics;
    }

    public void setProductGuestStatics(List<ProductGuestStaticsVo> productGuestStatics) {
        this.productGuestStatics = productGuestStatics;
    }

    public List<Map<String, Object>> getListMap() {
        return listMap;
    }

    public void setListMap(List<Map<String, Object>> listMap) {
        this.listMap = listMap;
    }

    public TourGroup getTourGroup() {
        return tourGroup;
    }

    public void setTourGroup(TourGroup tourGroup) {
        this.tourGroup = tourGroup;
    }

    public List<RegionInfo> getRegionInfoList() {
        return regionInfoList;
    }

    public void setRegionInfoList(List<RegionInfo> regionInfoList) {
        this.regionInfoList = regionInfoList;
    }

    public List<TourGroup> getTourGroupList() {
        return tourGroupList;
    }

    public void setTourGroupList(List<TourGroup> tourGroupList) {
        this.tourGroupList = tourGroupList;
    }



    public Map<Integer, String> getGuideMap() {
        return guideMap;
    }

    public void setGuideMap(Map<Integer, String> guideMap) {
        this.guideMap = guideMap;
    }

    public InfoBean getShop() {
        return shop;
    }

    public void setShop(InfoBean shop) {
        this.shop = shop;
    }

    public List<BookingGuide> getBookingGuides() {
        return bookingGuides;
    }

    public void setBookingGuides(List<BookingGuide> bookingGuides) {
        this.bookingGuides = bookingGuides;
    }

    public Set<Integer> getUserIdSet() {
        return userIdSet;
    }

    public void setUserIdSet(Set<Integer> userIdSet) {
        this.userIdSet = userIdSet;
    }

    public List<String> getDateList() {
        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

    public Map<Integer, DepartmentOrderVO> getDeptOrderVoMap() {
        return deptOrderVoMap;
    }

    public void setDeptOrderVoMap(Map<Integer, DepartmentOrderVO> deptOrderVoMap) {
        this.deptOrderVoMap = deptOrderVoMap;
    }

    public Map<Integer, List<PlatformOrgPo>> getOrgDepMap() {
        return orgDepMap;
    }

    public void setOrgDepMap(Map<Integer, List<PlatformOrgPo>> orgDepMap) {
        this.orgDepMap = orgDepMap;
    }

    public List<PlatformOrgPo> getSubLevelOrgList() {
        return subLevelOrgList;
    }

    public void setSubLevelOrgList(List<PlatformOrgPo> subLevelOrgList) {
        this.subLevelOrgList = subLevelOrgList;
    }

    public List<PlatformOrgPo> getSecLevelOrgList() {
        return secLevelOrgList;
    }

    public void setSecLevelOrgList(List<PlatformOrgPo> secLevelOrgList) {
        this.secLevelOrgList = secLevelOrgList;
    }

    public Map<String, Object> getPersonMap() {
        return personMap;
    }

    public void setPersonMap(Map<String, Object> personMap) {
        this.personMap = personMap;
    }

    public List<DicInfo> getDicInfoList() {
        return dicInfoList;
    }

    public void setDicInfoList(List<DicInfo> dicInfoList) {
        this.dicInfoList = dicInfoList;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public Map<Integer, List<PlatformEmployeePo>> getEmpMap() {
        return empMap;
    }

    public void setEmpMap(Map<Integer, List<PlatformEmployeePo>> empMap) {
        this.empMap = empMap;
    }

    public Map<Integer, List<GroupOrder>> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<Integer, List<GroupOrder>> orderMap) {
        this.orderMap = orderMap;
    }

    public List<GroupOrder> getgOrdersList() {
        return gOrdersList;
    }

    public void setgOrdersList(List<GroupOrder> gOrdersList) {
        this.gOrdersList = gOrdersList;
    }

    public Map getParameters() {
        return parameters;
    }

    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }
}
