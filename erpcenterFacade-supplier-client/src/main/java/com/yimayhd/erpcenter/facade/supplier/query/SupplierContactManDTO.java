package com.yimayhd.erpcenter.facade.supplier.query;

import java.io.Serializable;

import com.yimayhd.erpresource.dal.po.SupplierContactMan;
/**
 * 联系人
 * @author liyong
 * 2016年10月25日 描述：
 */
public class SupplierContactManDTO implements Serializable {
	/**
	 * @author liyong
	 * 2016年10月25日 描述：
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 联系人
	 */
	private SupplierContactMan supplierContactMan;
	
	public void setSupplierContactMan(SupplierContactMan supplierContactMan) {
		this.supplierContactMan = supplierContactMan;
	}
	
	public SupplierContactMan getSupplierContactMan() {
		return supplierContactMan;
	}

}
