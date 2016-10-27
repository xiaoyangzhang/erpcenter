package com.yimayhd.erpcenter.facade.supplier.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
/**
 * 供应商资源列表
 * @author liyong
 * 2016年10月25日 
 */
public class SuplierListResult extends ResultSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;


	private PageBean pageBean = new PageBean();

	
	private List<RegionInfo> allProvince = new ArrayList<RegionInfo>();

	private List<RegionInfo> cityList = new ArrayList<RegionInfo>();

	private List<RegionInfo> areaList = new ArrayList<RegionInfo>();
	private List<DicInfo> travelagencylevelList = new ArrayList<DicInfo>();

	private List<DicInfo> restaurantlevelList = new ArrayList<DicInfo>();
	private List<DicInfo> levelList = new ArrayList<DicInfo>();

	private List<DicInfo> fleetlevelList = new ArrayList<DicInfo>();

	private List<DicInfo> scenicspotlevelList = new ArrayList<DicInfo>();

	private List<DicInfo> shoppinglevelList = new ArrayList<DicInfo>();
	private List<DicInfo> entertainmentlevelList = new ArrayList<DicInfo>();

	private List<DicInfo> guidelevelList = new ArrayList<DicInfo>();

	private List<DicInfo> airticketagentlevelList = new ArrayList<DicInfo>();

	private List<DicInfo> trainticketagentlevelList = new ArrayList<DicInfo>();

	private List<DicInfo> golflevelList = new ArrayList<DicInfo>();
	private List<DicInfo> insuranclevelList = new ArrayList<DicInfo>();

	private List<DicInfo> otherlevelList = new ArrayList<DicInfo>();

	private List<DicInfo> localtravelList = new ArrayList<DicInfo>();

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public List<RegionInfo> getAllProvince() {
		return allProvince;
	}

	public void setAllProvince(List<RegionInfo> allProvince) {
		this.allProvince = allProvince;
	}

	public List<RegionInfo> getCityList() {
		return cityList;
	}

	public void setCityList(List<RegionInfo> cityList) {
		this.cityList = cityList;
	}

	public List<RegionInfo> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<RegionInfo> areaList) {
		this.areaList = areaList;
	}

	public List<DicInfo> getTravelagencylevelList() {
		return travelagencylevelList;
	}

	public void setTravelagencylevelList(List<DicInfo> travelagencylevelList) {
		this.travelagencylevelList = travelagencylevelList;
	}

	public List<DicInfo> getRestaurantlevelList() {
		return restaurantlevelList;
	}

	public void setRestaurantlevelList(List<DicInfo> restaurantlevelList) {
		this.restaurantlevelList = restaurantlevelList;
	}

	public List<DicInfo> getLevelList() {
		return levelList;
	}

	public void setLevelList(List<DicInfo> levelList) {
		this.levelList = levelList;
	}

	public List<DicInfo> getFleetlevelList() {
		return fleetlevelList;
	}

	public void setFleetlevelList(List<DicInfo> fleetlevelList) {
		this.fleetlevelList = fleetlevelList;
	}

	public List<DicInfo> getScenicspotlevelList() {
		return scenicspotlevelList;
	}

	public void setScenicspotlevelList(List<DicInfo> scenicspotlevelList) {
		this.scenicspotlevelList = scenicspotlevelList;
	}

	public List<DicInfo> getShoppinglevelList() {
		return shoppinglevelList;
	}

	public void setShoppinglevelList(List<DicInfo> shoppinglevelList) {
		this.shoppinglevelList = shoppinglevelList;
	}

	public List<DicInfo> getEntertainmentlevelList() {
		return entertainmentlevelList;
	}

	public void setEntertainmentlevelList(List<DicInfo> entertainmentlevelList) {
		this.entertainmentlevelList = entertainmentlevelList;
	}

	public List<DicInfo> getGuidelevelList() {
		return guidelevelList;
	}

	public void setGuidelevelList(List<DicInfo> guidelevelList) {
		this.guidelevelList = guidelevelList;
	}

	public List<DicInfo> getAirticketagentlevelList() {
		return airticketagentlevelList;
	}

	public void setAirticketagentlevelList(List<DicInfo> airticketagentlevelList) {
		this.airticketagentlevelList = airticketagentlevelList;
	}

	public List<DicInfo> getTrainticketagentlevelList() {
		return trainticketagentlevelList;
	}

	public void setTrainticketagentlevelList(List<DicInfo> trainticketagentlevelList) {
		this.trainticketagentlevelList = trainticketagentlevelList;
	}

	public List<DicInfo> getGolflevelList() {
		return golflevelList;
	}

	public void setGolflevelList(List<DicInfo> golflevelList) {
		this.golflevelList = golflevelList;
	}

	public List<DicInfo> getInsuranclevelList() {
		return insuranclevelList;
	}

	public void setInsuranclevelList(List<DicInfo> insuranclevelList) {
		this.insuranclevelList = insuranclevelList;
	}

	public List<DicInfo> getOtherlevelList() {
		return otherlevelList;
	}

	public void setOtherlevelList(List<DicInfo> otherlevelList) {
		this.otherlevelList = otherlevelList;
	}

	public List<DicInfo> getLocaltravelList() {
		return localtravelList;
	}

	public void setLocaltravelList(List<DicInfo> localtravelList) {
		this.localtravelList = localtravelList;
	}



}
