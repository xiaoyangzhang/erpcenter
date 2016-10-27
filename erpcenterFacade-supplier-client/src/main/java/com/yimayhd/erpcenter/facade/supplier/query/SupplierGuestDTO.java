package com.yimayhd.erpcenter.facade.supplier.query;

import java.io.Serializable;

import com.yimayhd.erpresource.dal.po.SupplierGuest;
/**
 * 客人参数信息
 * @author liyong
 * 2016年10月26日 描述：
 */
public class SupplierGuestDTO implements Serializable {
	
	/**
	 * @author liyong
	 * 2016年10月26日 描述：
	 */
	private static final long serialVersionUID = 1L;
	private SupplierGuest supplierGuest = new SupplierGuest();
	
	public void setSupplierGuest(SupplierGuest supplierGuest) {
		this.supplierGuest = supplierGuest;
	}
	
	public SupplierGuest getSupplierGuest() {
		return supplierGuest;
	}

}
