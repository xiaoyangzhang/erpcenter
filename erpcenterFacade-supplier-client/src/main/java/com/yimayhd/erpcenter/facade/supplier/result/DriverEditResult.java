package com.yimayhd.erpcenter.facade.supplier.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpresource.dal.po.SupplierDriver;
/**
 * 跳转司机编辑页面结果对象
 * @author liyong
 * 2016年10月25日 描述：
 */
public class DriverEditResult extends ResultSupport implements Serializable {
	
	/**
	 * @author liyong
	 * 2016年10月25日 描述：
	 */
	private static final long serialVersionUID = 1L;
	private List<DicInfo> mzList = new ArrayList<DicInfo>();
	private List<DicInfo> djList = new ArrayList<DicInfo>();
	private List<DicInfo> xjpdList = new ArrayList<DicInfo>();
	private List<DicInfo> shdtrsList = new ArrayList<DicInfo>();
	private List<DicInfo> carList = new ArrayList<DicInfo>();
	
	private List<RegionInfo> allProvince = new ArrayList<RegionInfo>();
	private List<RegionInfo> cityList = new ArrayList<RegionInfo>();
	private List<RegionInfo> areaList = new ArrayList<RegionInfo>();
	private List<RegionInfo> townList = new ArrayList<RegionInfo>();
	private SupplierDriver driver = new SupplierDriver();
	public List<DicInfo> getMzList() {
		return mzList;
	}
	public void setMzList(List<DicInfo> mzList) {
		this.mzList = mzList;
	}
	public List<DicInfo> getDjList() {
		return djList;
	}
	public void setDjList(List<DicInfo> djList) {
		this.djList = djList;
	}
	public List<DicInfo> getXjpdList() {
		return xjpdList;
	}
	public void setXjpdList(List<DicInfo> xjpdList) {
		this.xjpdList = xjpdList;
	}
	public List<DicInfo> getShdtrsList() {
		return shdtrsList;
	}
	public void setShdtrsList(List<DicInfo> shdtrsList) {
		this.shdtrsList = shdtrsList;
	}
	public List<DicInfo> getCarList() {
		return carList;
	}
	public void setCarList(List<DicInfo> carList) {
		this.carList = carList;
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
	public List<RegionInfo> getTownList() {
		return townList;
	}
	public void setTownList(List<RegionInfo> townList) {
		this.townList = townList;
	}
	public SupplierDriver getDriver() {
		return driver;
	}
	public void setDriver(SupplierDriver driver) {
		this.driver = driver;
	}
	
	

}
