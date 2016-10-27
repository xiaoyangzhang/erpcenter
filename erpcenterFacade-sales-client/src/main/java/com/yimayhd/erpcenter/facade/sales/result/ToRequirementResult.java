package com.yimayhd.erpcenter.facade.sales.result;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;

import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/26 16:20
 */
public class ToRequirementResult extends ResultSupport {
    private  List<DicInfo> ftcList;	// 车辆型号
    private  List<DicInfo> jdxjList;	// 酒店星级
    private  List<GroupRequirement> hotelList;// 酒店信息
    private  List<GroupRequirement> fleetList;	// 车队信息
    private  List<GroupRequirement> airTicketList; // 机票信息
    private 	List<GroupRequirement> railwayTicketList; // 火车信息
    private List<GroupRequirement> guideList;// 导游信息
    private  List<GroupRequirement> restaurantList; // 餐厅信息
    private int groupId;

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

    public List<GroupRequirement> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<GroupRequirement> hotelList) {
        this.hotelList = hotelList;
    }

    public List<GroupRequirement> getFleetList() {
        return fleetList;
    }

    public void setFleetList(List<GroupRequirement> fleetList) {
        this.fleetList = fleetList;
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

    public List<GroupRequirement> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<GroupRequirement> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
