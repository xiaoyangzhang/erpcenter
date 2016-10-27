package com.yimayhd.erpcenter.facade.supplier.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpresource.dal.po.SupplierContactMan;
/**
 * 联系人列表
 * @author liyong
 * 2016年10月25日 描述：
 */
public class ContactManListResult extends ResultSupport implements Serializable {
	
	/**
	 * @author liyong
	 * 2016年10月25日 描述：
	 */
	private static final long serialVersionUID = 1L;

	private List<SupplierContactMan> manList = new ArrayList<SupplierContactMan>();
	
	private List<SupplierContactMan> allManList = new ArrayList<SupplierContactMan>();
	
	private Integer supplierType;

	public List<SupplierContactMan> getManList() {
		return manList;
	}

	public void setManList(List<SupplierContactMan> manList) {
		this.manList = manList;
	}

	public List<SupplierContactMan> getAllManList() {
		return allManList;
	}

	public void setAllManList(List<SupplierContactMan> allManList) {
		this.allManList = allManList;
	}

	public Integer getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(Integer supplierType) {
		this.supplierType = supplierType;
	}
	

}
