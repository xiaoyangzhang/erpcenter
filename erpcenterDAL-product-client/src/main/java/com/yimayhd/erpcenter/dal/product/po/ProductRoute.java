package com.yimayhd.erpcenter.dal.product.po;

import java.io.Serializable;

public class ProductRoute implements Serializable {
    private static final long serialVersionUID = -4444365477608373025L;
    private Integer id;

    private Integer productId;

    private Integer dayNum;

    private String breakfast;

    private String lunch;

    private String supper;

    private Integer hotelId;

    private String hotelName;

    private String routeDesp;

    private String routeTip;

    private Long createTime;
    
    private String routeShort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
    }


    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast == null ? null : breakfast.trim();
    }


    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch == null ? null : lunch.trim();
    }

    public String getSupper() {
        return supper;
    }

    public void setSupper(String supper) {
        this.supper = supper == null ? null : supper.trim();
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName == null ? null : hotelName.trim();
    }

    public String getRouteDesp() {
        return routeDesp;
    }

    public void setRouteDesp(String routeDesp) {
        this.routeDesp = routeDesp == null ? null : routeDesp.trim();
    }

    public String getRouteTip() {
        return routeTip;
    }

    public void setRouteTip(String routeTip) {
        this.routeTip = routeTip == null ? null : routeTip.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

	public String getRouteShort() {
		return routeShort;
	}

	public void setRouteShort(String routeShort) {
		this.routeShort = routeShort == null? null : routeShort.trim();
	}
    
    
}