package com.yimayhd.erpcenter.facade.sales.result;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteDayVO;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/27 11:38
 */
public class GetPushInfoResult extends ResultSupport {
    private TourGroup tourGroup;
    private List<GroupRouteDayVO> routeDayVOList;
    private List<GroupOrderGuest> guestList;
    private List<BookingShop> bookingShops;
    private BookingGuide guide;
    private BookingSupplierDetail supplierDetail;
    private List<BookingSupplierDetail> driverList;
    private List<BookingGuide> guides;

    public TourGroup getTourGroup() {
        return tourGroup;
    }

    public void setTourGroup(TourGroup tourGroup) {
        this.tourGroup = tourGroup;
    }

    public List<GroupRouteDayVO> getRouteDayVOList() {
        return routeDayVOList;
    }

    public void setRouteDayVOList(List<GroupRouteDayVO> routeDayVOList) {
        this.routeDayVOList = routeDayVOList;
    }

    public List<GroupOrderGuest> getGuestList() {
        return guestList;
    }

    public void setGuestList(List<GroupOrderGuest> guestList) {
        this.guestList = guestList;
    }

    public List<BookingShop> getBookingShops() {
        return bookingShops;
    }

    public void setBookingShops(List<BookingShop> bookingShops) {
        this.bookingShops = bookingShops;
    }

    public BookingGuide getGuide() {
        return guide;
    }

    public void setGuide(BookingGuide guide) {
        this.guide = guide;
    }

    public BookingSupplierDetail getSupplierDetail() {
        return supplierDetail;
    }

    public void setSupplierDetail(BookingSupplierDetail supplierDetail) {
        this.supplierDetail = supplierDetail;
    }

    public List<BookingSupplierDetail> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<BookingSupplierDetail> driverList) {
        this.driverList = driverList;
    }

    public List<BookingGuide> getGuides() {
        return guides;
    }

    public void setGuides(List<BookingGuide> guides) {
        this.guides = guides;
    }
}
