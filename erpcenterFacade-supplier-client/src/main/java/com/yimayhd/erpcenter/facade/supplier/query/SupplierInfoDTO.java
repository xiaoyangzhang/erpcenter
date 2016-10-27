package com.yimayhd.erpcenter.facade.supplier.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.facade.supplier.result.ResultSupport;
import com.yimayhd.erpresource.dal.po.SupplierInfo;
/**
 * 供应商信息资源分装
 * @author liyong
 * 2016年10月25日 
 */
public class SupplierInfoDTO extends ResultSupport implements Serializable {
	
	/**
	 * @author liyong
	 * 2016年10月25日 描述：
	 */
	private static final long serialVersionUID = 1L;
	private SupplierInfo supplierInfo = new SupplierInfo();
	
	public void setSupplierInfo(SupplierInfo supplierInfo) {
		this.supplierInfo = supplierInfo;
	}
	
	public SupplierInfo getSupplierInfo() {
		return supplierInfo;
	}

}
