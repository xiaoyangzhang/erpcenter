package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.Map;

import com.yihg.mybatis.utility.PageBean;

public class GetNumAndOrderResult extends BaseResult {
	private static final long serialVersionUID = -6806992765430266609L;
	private String citysSupplierIds;
	private String supplierLevel;

	private Map sum;
	private PageBean pb;

	public String getCitysSupplierIds() {
		return citysSupplierIds;
	}

	public void setCitysSupplierIds(String citysSupplierIds) {
		this.citysSupplierIds = citysSupplierIds;
	}

	public String getSupplierLevel() {
		return supplierLevel;
	}

	public void setSupplierLevel(String supplierLevel) {
		this.supplierLevel = supplierLevel;
	}

	public Map getSum() {
		return sum;
	}

	public void setSum(Map sum) {
		this.sum = sum;
	}

	public PageBean getPb() {
		return pb;
	}

	public void setPb(PageBean pb) {
		this.pb = pb;
	}
}
