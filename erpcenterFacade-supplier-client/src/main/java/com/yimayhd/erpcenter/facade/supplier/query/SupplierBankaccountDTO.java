package com.yimayhd.erpcenter.facade.supplier.query;

import java.io.Serializable;

import com.yimayhd.erpresource.dal.po.SupplierBankaccount;
/**
 * 添加银行帐号信息参数对象
 * @author liyong
 * 2016年10月25日 
 */
public class SupplierBankaccountDTO  implements Serializable{
	
	/**
	 * @author liyong
	 * 2016年10月25日 描述：
	 */
	private static final long serialVersionUID = 1L;
	private SupplierBankaccount supplierBankaccount =new SupplierBankaccount();
	
	public void setSupplierBankaccount(SupplierBankaccount supplierBankaccount) {
		this.supplierBankaccount = supplierBankaccount;
	}
	
	public SupplierBankaccount getSupplierBankaccount() {
		return supplierBankaccount;
	}

}
