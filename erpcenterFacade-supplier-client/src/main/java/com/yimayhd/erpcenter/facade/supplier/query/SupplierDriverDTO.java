package com.yimayhd.erpcenter.facade.supplier.query;

import java.io.Serializable;

import com.yimayhd.erpresource.dal.po.SupplierDriver;
/**
 * 司机对象参数
 * @author liyong 
 * 2016年10月25日 描述：
 */
public class SupplierDriverDTO implements Serializable {
	
	/**
	 * @author liyong
	 * 2016年10月25日 描述：
	 */
	private static final long serialVersionUID = 1L;
	private SupplierDriver supplierDriver;
	
	public void setSupplierDriver(SupplierDriver supplierDriver) {
		this.supplierDriver = supplierDriver;
	}
	
	public SupplierDriver getSupplierDriver() {
		return supplierDriver;
	}

}
