package com.yimayhd.erpcenter.facade.sales.result;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.*;

import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/27 15:43
 */
public class TogroupRequirementResult extends ResultSupport {
    private List<DicInfo> ftcList;
    private List<DicInfo> jdxjList;
    private List<GroupRequirement> grogShopList;
    private List<GroupRequirement> motorcadeList;
    private List<GroupRequirement> airTicketList;
    private List<GroupRequirement> railwayTicketList;
    private List<GroupRequirement> guideList;
    private List<GroupRequirement> restaurantList;

    private Integer groupId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public List<GroupRequirement> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<GroupRequirement> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public List<DicInfo> getFtcList() {
        return ftcList;
    }

    public void setFtcList(List<DicInfo> ftcList) {
        this.ftcList = ftcList;
    }

    public List<DicInfo> getJdxjList() {
        return jdxjList;
    }

    public void setJdxjList(List<DicInfo> jdxjList) {
        this.jdxjList = jdxjList;
    }

    public List<GroupRequirement> getGrogShopList() {
        return grogShopList;
    }

    public void setGrogShopList(List<GroupRequirement> grogShopList) {
        this.grogShopList = grogShopList;
    }

    public List<GroupRequirement> getMotorcadeList() {
        return motorcadeList;
    }

    public void setMotorcadeList(List<GroupRequirement> motorcadeList) {
        this.motorcadeList = motorcadeList;
    }

    public List<GroupRequirement> getAirTicketList() {
        return airTicketList;
    }

    public void setAirTicketList(List<GroupRequirement> airTicketList) {
        this.airTicketList = airTicketList;
    }

    public List<GroupRequirement> getRailwayTicketList() {
        return railwayTicketList;
    }

    public void setRailwayTicketList(List<GroupRequirement> railwayTicketList) {
        this.railwayTicketList = railwayTicketList;
    }

    public List<GroupRequirement> getGuideList() {
        return guideList;
    }

    public void setGuideList(List<GroupRequirement> guideList) {
        this.guideList = guideList;
    }
}
