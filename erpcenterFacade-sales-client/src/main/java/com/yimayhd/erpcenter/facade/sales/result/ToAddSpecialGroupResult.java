package com.yimayhd.erpcenter.facade.sales.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SpecialGroupOrderVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/31 13:57
 */
public class ToAddSpecialGroupResult extends ResultSupport {
    private SpecialGroupOrderVO specialGroupOrderVO;
    private List<DicInfo> jdxjList;
    private List<DicInfo> jtfsList;
    private List<DicInfo> zjlxList;
    private List<DicInfo> sourceTypeList;
    private List<RegionInfo> allProvince;
    private List<DicInfo> lysfxmList;

    private String guideStr;
    private Integer operType;
    private List<RegionInfo> cityList;
    private Integer orderId;
    private String orgJsonStr;
    private String orgUserJsonStr;
    private List<DicInfo> dicInfoList;


    private PageBean page;
    private Integer pageTotalAudit;
    private Integer pageTotalChild;
    private Integer pageTotalGuide;
    private BigDecimal pageTotal;
    private GroupOrder groupOrder;
    List<GroupOrder> groupOrderList;
    private TourGroup tourGroup;


    public List<GroupOrder> getGroupOrderList() {
        return groupOrderList;
    }

    public void setGroupOrderList(List<GroupOrder> groupOrderList) {
        this.groupOrderList = groupOrderList;
    }

    public TourGroup getTourGroup() {
        return tourGroup;
    }

    public void setTourGroup(TourGroup tourGroup) {
        this.tourGroup = tourGroup;
    }

    public GroupOrder getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(GroupOrder groupOrder) {
        this.groupOrder = groupOrder;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public Integer getPageTotalAudit() {
        return pageTotalAudit;
    }

    public void setPageTotalAudit(Integer pageTotalAudit) {
        this.pageTotalAudit = pageTotalAudit;
    }

    public Integer getPageTotalChild() {
        return pageTotalChild;
    }

    public void setPageTotalChild(Integer pageTotalChild) {
        this.pageTotalChild = pageTotalChild;
    }

    public Integer getPageTotalGuide() {
        return pageTotalGuide;
    }

    public void setPageTotalGuide(Integer pageTotalGuide) {
        this.pageTotalGuide = pageTotalGuide;
    }

    public BigDecimal getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(BigDecimal pageTotal) {
        this.pageTotal = pageTotal;
    }

    public String getOrgJsonStr() {
        return orgJsonStr;
    }

    public void setOrgJsonStr(String orgJsonStr) {
        this.orgJsonStr = orgJsonStr;
    }

    public String getOrgUserJsonStr() {
        return orgUserJsonStr;
    }

    public void setOrgUserJsonStr(String orgUserJsonStr) {
        this.orgUserJsonStr = orgUserJsonStr;
    }



    public List<DicInfo> getDicInfoList() {
        return dicInfoList;
    }

    public void setDicInfoList(List<DicInfo> dicInfoList) {
        this.dicInfoList = dicInfoList;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public List<RegionInfo> getCityList() {
        return cityList;
    }

    public void setCityList(List<RegionInfo> cityList) {
        this.cityList = cityList;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public String getGuideStr() {
        return guideStr;
    }

    public void setGuideStr(String guideStr) {
        this.guideStr = guideStr;
    }

    public SpecialGroupOrderVO getSpecialGroupOrderVO() {
        return specialGroupOrderVO;
    }

    public void setSpecialGroupOrderVO(SpecialGroupOrderVO specialGroupOrderVO) {
        this.specialGroupOrderVO = specialGroupOrderVO;
    }

    public List<DicInfo> getJdxjList() {
        return jdxjList;
    }

    public void setJdxjList(List<DicInfo> jdxjList) {
        this.jdxjList = jdxjList;
    }

    public List<DicInfo> getJtfsList() {
        return jtfsList;
    }

    public void setJtfsList(List<DicInfo> jtfsList) {
        this.jtfsList = jtfsList;
    }

    public List<DicInfo> getZjlxList() {
        return zjlxList;
    }

    public void setZjlxList(List<DicInfo> zjlxList) {
        this.zjlxList = zjlxList;
    }

    public List<DicInfo> getSourceTypeList() {
        return sourceTypeList;
    }

    public void setSourceTypeList(List<DicInfo> sourceTypeList) {
        this.sourceTypeList = sourceTypeList;
    }

    public List<RegionInfo> getAllProvince() {
        return allProvince;
    }

    public void setAllProvince(List<RegionInfo> allProvince) {
        this.allProvince = allProvince;
    }

    public List<DicInfo> getLysfxmList() {
        return lysfxmList;
    }

    public void setLysfxmList(List<DicInfo> lysfxmList) {
        this.lysfxmList = lysfxmList;
    }
}
