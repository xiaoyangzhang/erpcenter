package com.yimayhd.erpcenter.facade.supplier.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpresource.dal.po.SupplierGuestLabel;
/**
 * 客人标签列表返回结果封装
 * @author liyong
 * 2016年10月26日 描述：
 */
public class GuestLabelListResult extends ResultSupport implements Serializable {
	
	/**
	 * @author liyong
	 * 2016年10月26日 描述：
	 */
	private static final long serialVersionUID = 1L;

	private List<List<SupplierGuestLabel>> supplierGuestLabels = new ArrayList<List<SupplierGuestLabel>>();
	
	private int length;
	
	private List<RegionInfo> allProvince = new ArrayList<RegionInfo>();

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
	
	

}
