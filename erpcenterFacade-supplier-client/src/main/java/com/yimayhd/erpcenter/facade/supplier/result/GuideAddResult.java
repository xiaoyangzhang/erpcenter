package com.yimayhd.erpcenter.facade.supplier.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpresource.dal.po.SupplierGuide;
/**
 * 导游添加页面信息结果
 * @author liyong
 * 2016年10月25日 
 */
public class GuideAddResult extends ResultSupport implements Serializable {
	

	/**
	 * @author liyong
	 */
	private static final long serialVersionUID = 1L;
	private List<DicInfo> mzList = new ArrayList<DicInfo>();
	private List<DicInfo> djList = new ArrayList<DicInfo>();
	private List<DicInfo> xjpdList = new ArrayList<DicInfo>();
	private List<DicInfo> shdtrsList = new ArrayList<DicInfo>();
	private List<RegionInfo> allProvince = new ArrayList<RegionInfo>();
	private List<RegionInfo> cityList = new ArrayList<RegionInfo>();
	private List<RegionInfo> areaList = new ArrayList<RegionInfo>();
	private List<RegionInfo> townList = new ArrayList<RegionInfo>();
	private String images_source;
	
	private SupplierGuide supplierGuide = new SupplierGuide();
	
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
	public String getImages_source() {
		return images_source;
	}
	public void setImages_source(String images_source) {
		this.images_source = images_source;
	}
	public SupplierGuide getSupplierGuide() {
		return supplierGuide;
	}
	public void setSupplierGuide(SupplierGuide supplierGuide) {
		this.supplierGuide = supplierGuide;
	}
	


}
