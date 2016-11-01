package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpresource.dal.po.SupplierInfo;

public class GetNumAndOrderResult extends BaseResult {
	private static final long serialVersionUID = 3758933455656311638L;
	private List<Map<String, Integer>> supplierProvince;

	private List<SupplierInfo> supplierLevel;

	public List<Map<String, Integer>> getSupplierProvince() {
		return supplierProvince;
	}

	public void setSupplierProvince(List<Map<String, Integer>> supplierProvince) {
		this.supplierProvince = supplierProvince;
	}

	public List<SupplierInfo> getSupplierLevel() {
		return supplierLevel;
	}

	public void setSupplierLevel(List<SupplierInfo> supplierLevel) {
		this.supplierLevel = supplierLevel;
	}
}
