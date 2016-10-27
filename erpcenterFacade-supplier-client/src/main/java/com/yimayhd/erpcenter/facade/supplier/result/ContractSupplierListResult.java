package com.yimayhd.erpcenter.facade.supplier.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpresource.dal.po.SupplierInfo;
/**
 * 
 * @author liyong
 * 2016年10月25日 
 */
public class ContractSupplierListResult extends ResultSupport implements Serializable {
	
	/**
	 * @author liyong
	 * 2016年10月25日 描述：
	 */
	private static final long serialVersionUID = 1L;
	private PageBean<SupplierInfo> pageBean = new PageBean<SupplierInfo>();
	/**
	 * 省
	 */
	private List<RegionInfo> allProvince = new ArrayList<RegionInfo>();
	/**
	 * 市
	 */
	private List<RegionInfo> cityList = new ArrayList<RegionInfo>();
	/**
	 * 区
	 */
	private List<RegionInfo> areaList = new ArrayList<RegionInfo>();
	/**
	 * @return getPageBean获取 pageBean 的值
	 */
	public PageBean<SupplierInfo> getPageBean() {
		return pageBean;
	}
	/**
	 * @param setPageBean 设置  pageBean 的值
	 */
	public void setPageBean(PageBean<SupplierInfo> pageBean) {
		this.pageBean = pageBean;
	}
	/**
	 * @return getAllProvince获取 allProvince 的值
	 */
	public List<RegionInfo> getAllProvince() {
		return allProvince;
	}
	/**
	 * @param setAllProvince 设置  allProvince 的值
	 */
	public void setAllProvince(List<RegionInfo> allProvince) {
		this.allProvince = allProvince;
	}
	/**
	 * @return getCityList获取 cityList 的值
	 */
	public List<RegionInfo> getCityList() {
		return cityList;
	}
	/**
	 * @param setCityList 设置  cityList 的值
	 */
	public void setCityList(List<RegionInfo> cityList) {
		this.cityList = cityList;
	}
	/**
	 * @return getAreaList获取 areaList 的值
	 */
	public List<RegionInfo> getAreaList() {
		return areaList;
	}
	/**
	 * @param setAreaList 设置  areaList 的值
	 */
	public void setAreaList(List<RegionInfo> areaList) {
		this.areaList = areaList;
	}
	
	
	
	

}
