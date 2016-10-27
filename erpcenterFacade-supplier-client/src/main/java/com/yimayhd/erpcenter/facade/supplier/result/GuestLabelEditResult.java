package com.yimayhd.erpcenter.facade.supplier.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpresource.dal.po.SupplierGuest;
import com.yimayhd.erpresource.dal.po.SupplierGuestLabel;
/**
 * 修改客人
 * @author liyong
 * 2016年10月26日 描述：
 */
public class GuestLabelEditResult extends ResultSupport implements Serializable {
	
	/**
	 * @author liyong
	 * 2016年10月26日 描述：
	 */
	private static final long serialVersionUID = 1L;
	
	private SupplierGuest guest ;

	private List<List<SupplierGuestLabel>> supplierGuestLabels = new ArrayList<List<SupplierGuestLabel>>();
	
	private int length;
	
	private List<RegionInfo> allProvince = new ArrayList<RegionInfo>();
	private List<RegionInfo> cityList = new ArrayList<RegionInfo>();
	private List<RegionInfo> adCityList = new ArrayList<RegionInfo>();

	public List<List<SupplierGuestLabel>> getSupplierGuestLabels() {
		return supplierGuestLabels;
	}

	public void setSupplierGuestLabels(List<List<SupplierGuestLabel>> supplierGuestLabels) {
		this.supplierGuestLabels = supplierGuestLabels;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public List<RegionInfo> getAllProvince() {
		return allProvince;
	}

	public void setAllProvince(List<RegionInfo> allProvince) {
		this.allProvince = allProvince;
	}

	public SupplierGuest getGuest() {
		return guest;
	}

	public void setGuest(SupplierGuest guest) {
		this.guest = guest;
	}

	public List<RegionInfo> getCityList() {
		return cityList;
	}

	public void setCityList(List<RegionInfo> cityList) {
		this.cityList = cityList;
	}

	public List<RegionInfo> getAdCityList() {
		return adCityList;
	}

	public void setAdCityList(List<RegionInfo> adCityList) {
		this.adCityList = adCityList;
	}
	
	

}
