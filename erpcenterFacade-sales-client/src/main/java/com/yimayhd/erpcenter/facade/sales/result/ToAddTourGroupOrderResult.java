package com.yimayhd.erpcenter.facade.sales.result;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/27 14:33
 */
public class ToAddTourGroupOrderResult extends ResultSupport{
    private int stateFinance;
    private  int state;
    private  List<RegionInfo> cityList;
    private  List<DicInfo> typeList;
    private  GroupOrder groupOrder;
    private TourGroup tourGroup;
    private List<DicInfo> sourceTypeList;
    private List<RegionInfo> allProvince;

    public int getStateFinance() {
        return stateFinance;
    }

    public void setStateFinance(int stateFinance) {
        this.stateFinance = stateFinance;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<RegionInfo> getCityList() {
        return cityList;
    }

    public void setCityList(List<RegionInfo> cityList) {
        this.cityList = cityList;
    }

    public List<DicInfo> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<DicInfo> typeList) {
        this.typeList = typeList;
    }

    public GroupOrder getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(GroupOrder groupOrder) {
        this.groupOrder = groupOrder;
    }

    public TourGroup getTourGroup() {
        return tourGroup;
    }

    public void setTourGroup(TourGroup tourGroup) {
        this.tourGroup = tourGroup;
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
}
