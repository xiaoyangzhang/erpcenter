package com.yimayhd.erpcenter.facade.supplier.query;

import java.io.Serializable;

import com.yimayhd.erpresource.dal.po.SupplierBill;
/**
 * 发票信息对象
 * @author liyong
 * 2016年10月25日
 */
public class SupplierBillDTO  implements Serializable{
	/**
	 * @author liyong
	 * 2016年10月25日 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 发票信息
	 */
	private SupplierBill supplierBill;
	
	public void setSupplierBill(SupplierBill supplierBill) {
		this.supplierBill = supplierBill;
	}
	
	public SupplierBill getSupplierBill() {
		return supplierBill;
	}

}
