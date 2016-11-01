package com.yimayhd.erpcenter.facade.sales.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/31 16:56
 */
public class InitGroupResult extends ResultSupport {
    private String orgJsonStr;
    private String orgUserJsonStr;
    private PageBean pageBean;


    List<GroupOrder> orderList;
    List<BookingDelivery> bookingDeliveryList;
    List<BookingSupplier> hotelList;
    List<BookingSupplier> restaurantList;

    List<BookingSupplier> fleetList;
    List<BookingSupplier> scenicsportList;
    List<BookingSupplier> insuranceList;
    List<BookingSupplier> airticketList;
    List<BookingSupplier> trainList;

    TourGroup tourGroup;

    public TourGroup getTourGroup() {
        return tourGroup;
    }

    public void setTourGroup(TourGroup tourGroup) {
        this.tourGroup = tourGroup;
    }

    public List<GroupOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<GroupOrder> orderList) {
        this.orderList = orderList;
    }

    public List<BookingDelivery> getBookingDeliveryList() {
        return bookingDeliveryList;
    }

    public void setBookingDeliveryList(List<BookingDelivery> bookingDeliveryList) {
        this.bookingDeliveryList = bookingDeliveryList;
    }

    public List<BookingSupplier> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<BookingSupplier> hotelList) {
        this.hotelList = hotelList;
    }

    public List<BookingSupplier> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<BookingSupplier> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public List<BookingSupplier> getFleetList() {
        return fleetList;
    }

    public void setFleetList(List<BookingSupplier> fleetList) {
        this.fleetList = fleetList;
    }

    public List<BookingSupplier> getScenicsportList() {
        return scenicsportList;
    }

    public void setScenicsportList(List<BookingSupplier> scenicsportList) {
        this.scenicsportList = scenicsportList;
    }

    public List<BookingSupplier> getInsuranceList() {
        return insuranceList;
    }

    public void setInsuranceList(List<BookingSupplier> insuranceList) {
        this.insuranceList = insuranceList;
    }

    public List<BookingSupplier> getAirticketList() {
        return airticketList;
    }

    public void setAirticketList(List<BookingSupplier> airticketList) {
        this.airticketList = airticketList;
    }

    public List<BookingSupplier> getTrainList() {
        return trainList;
    }

    public void setTrainList(List<BookingSupplier> trainList) {
        this.trainList = trainList;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
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
}
